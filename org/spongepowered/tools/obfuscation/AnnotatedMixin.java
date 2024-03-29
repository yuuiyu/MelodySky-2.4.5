//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import javax.annotation.processing.*;
import org.spongepowered.tools.obfuscation.mapping.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import java.lang.annotation.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.tools.obfuscation.interfaces.*;
import java.util.*;
import javax.lang.model.type.*;
import javax.tools.*;
import javax.lang.model.element.*;
import org.spongepowered.tools.obfuscation.struct.*;

class AnnotatedMixin
{
    private final AnnotationHandle annotation;
    private final Messager messager;
    private final ITypeHandleProvider typeProvider;
    private final IObfuscationManager obf;
    private final IMappingConsumer mappings;
    private final TypeElement mixin;
    private final TypeHandle handle;
    private final List<TypeHandle> targets;
    private final TypeHandle primaryTarget;
    private final String classRef;
    private final boolean remap;
    private final boolean virtual;
    private final AnnotatedMixinElementHandlerOverwrite overwrites;
    private final AnnotatedMixinElementHandlerShadow shadows;
    private final AnnotatedMixinElementHandlerInjector injectors;
    private final AnnotatedMixinElementHandlerAccessor accessors;
    private final AnnotatedMixinElementHandlerSoftImplements softImplements;
    
    public AnnotatedMixin(final IMixinAnnotationProcessor ap, final TypeElement type) {
        this.targets = new ArrayList<TypeHandle>();
        this.typeProvider = ap.getTypeProvider();
        this.obf = ap.getObfuscationManager();
        this.mappings = this.obf.createMappingConsumer();
        this.messager = ap;
        this.mixin = type;
        this.handle = new TypeHandle(type);
        this.virtual = this.handle.getAnnotation((Class<? extends Annotation>)Pseudo.class).exists();
        this.annotation = this.handle.getAnnotation((Class<? extends Annotation>)Mixin.class);
        this.classRef = type.getQualifiedName().toString().replace('.', '/');
        this.primaryTarget = this.initTargets();
        this.remap = (this.annotation.getBoolean("remap", true) && this.targets.size() > 0);
        this.overwrites = new AnnotatedMixinElementHandlerOverwrite(ap, this);
        this.shadows = new AnnotatedMixinElementHandlerShadow(ap, this);
        this.injectors = new AnnotatedMixinElementHandlerInjector(ap, this);
        this.accessors = new AnnotatedMixinElementHandlerAccessor(ap, this);
        this.softImplements = new AnnotatedMixinElementHandlerSoftImplements(ap, this);
    }
    
    AnnotatedMixin runValidators(final IMixinValidator.ValidationPass pass, final Collection<IMixinValidator> validators) {
        for (final IMixinValidator validator : validators) {
            if (!validator.validate(pass, this.mixin, this.annotation, this.targets)) {
                break;
            }
        }
        return this;
    }
    
    private TypeHandle initTargets() {
        TypeHandle primaryTarget = null;
        try {
            for (final TypeMirror target : this.annotation.getList()) {
                final TypeHandle type = new TypeHandle((DeclaredType)target);
                if (this.targets.contains(type)) {
                    continue;
                }
                this.addTarget(type);
                if (primaryTarget != null) {
                    continue;
                }
                primaryTarget = type;
            }
        }
        catch (Exception ex) {
            this.printMessage(Diagnostic.Kind.WARNING, "Error processing public targets: " + ex.getClass().getName() + ": " + ex.getMessage(), this);
        }
        try {
            for (final String privateTarget : this.annotation.getList("targets")) {
                TypeHandle type = this.typeProvider.getTypeHandle(privateTarget);
                if (this.targets.contains(type)) {
                    continue;
                }
                if (this.virtual) {
                    type = this.typeProvider.getSimulatedHandle(privateTarget, this.mixin.asType());
                }
                else {
                    if (type == null) {
                        this.printMessage(Diagnostic.Kind.ERROR, "Mixin target " + privateTarget + " could not be found", this);
                        return null;
                    }
                    if (type.isPublic()) {
                        this.printMessage(Diagnostic.Kind.WARNING, "Mixin target " + privateTarget + " is public and must be specified in value", this);
                        return null;
                    }
                }
                this.addSoftTarget(type, privateTarget);
                if (primaryTarget != null) {
                    continue;
                }
                primaryTarget = type;
            }
        }
        catch (Exception ex) {
            this.printMessage(Diagnostic.Kind.WARNING, "Error processing private targets: " + ex.getClass().getName() + ": " + ex.getMessage(), this);
        }
        if (primaryTarget == null) {
            this.printMessage(Diagnostic.Kind.ERROR, "Mixin has no targets", this);
        }
        return primaryTarget;
    }
    
    private void printMessage(final Diagnostic.Kind kind, final CharSequence msg, final AnnotatedMixin mixin) {
        this.messager.printMessage(kind, msg, this.mixin, this.annotation.asMirror());
    }
    
    private void addSoftTarget(final TypeHandle type, final String reference) {
        final ObfuscationData<String> obfClassData = this.obf.getDataProvider().getObfClass(type);
        if (!obfClassData.isEmpty()) {
            this.obf.getReferenceManager().addClassMapping(this.classRef, reference, obfClassData);
        }
        this.addTarget(type);
    }
    
    private void addTarget(final TypeHandle type) {
        this.targets.add(type);
    }
    
    @Override
    public String toString() {
        return this.mixin.getSimpleName().toString();
    }
    
    public AnnotationHandle getAnnotation() {
        return this.annotation;
    }
    
    public TypeElement getMixin() {
        return this.mixin;
    }
    
    public TypeHandle getHandle() {
        return this.handle;
    }
    
    public String getClassRef() {
        return this.classRef;
    }
    
    public boolean isInterface() {
        return this.mixin.getKind() == ElementKind.INTERFACE;
    }
    
    @Deprecated
    public TypeHandle getPrimaryTarget() {
        return this.primaryTarget;
    }
    
    public List<TypeHandle> getTargets() {
        return this.targets;
    }
    
    public boolean isMultiTarget() {
        return this.targets.size() > 1;
    }
    
    public boolean remap() {
        return this.remap;
    }
    
    public IMappingConsumer getMappings() {
        return this.mappings;
    }
    
    public void registerOverwrite(final ExecutableElement method, final AnnotationHandle overwrite) {
        this.overwrites.registerOverwrite(new AnnotatedMixinElementHandlerOverwrite.AnnotatedElementOverwrite(method, overwrite));
    }
    
    public void registerShadow(final VariableElement field, final AnnotationHandle shadow, final boolean shouldRemap) {
        this.shadows.registerShadow(this.shadows.new AnnotatedElementShadowField(field, shadow, shouldRemap));
    }
    
    public void registerShadow(final ExecutableElement method, final AnnotationHandle shadow, final boolean shouldRemap) {
        this.shadows.registerShadow(this.shadows.new AnnotatedElementShadowMethod(method, shadow, shouldRemap));
    }
    
    public void registerInjector(final ExecutableElement method, final AnnotationHandle inject, final InjectorRemap remap) {
        this.injectors.registerInjector(new AnnotatedMixinElementHandlerInjector.AnnotatedElementInjector(method, inject, remap));
        final List<AnnotationHandle> ats = inject.getAnnotationList("at");
        for (final AnnotationHandle at : ats) {
            this.registerInjectionPoint(method, inject, at, remap, "@At(%s)");
        }
        final List<AnnotationHandle> slices = inject.getAnnotationList("slice");
        for (final AnnotationHandle slice : slices) {
            final String id = slice.getValue("id", "");
            final AnnotationHandle from = slice.getAnnotation("from");
            if (from != null) {
                this.registerInjectionPoint(method, inject, from, remap, "@Slice[" + id + "](from=@At(%s))");
            }
            final AnnotationHandle to = slice.getAnnotation("to");
            if (to != null) {
                this.registerInjectionPoint(method, inject, to, remap, "@Slice[" + id + "](to=@At(%s))");
            }
        }
    }
    
    public void registerInjectionPoint(final ExecutableElement element, final AnnotationHandle inject, final AnnotationHandle at, final InjectorRemap remap, final String format) {
        this.injectors.registerInjectionPoint(new AnnotatedMixinElementHandlerInjector.AnnotatedElementInjectionPoint(element, inject, at, remap), format);
    }
    
    public void registerAccessor(final ExecutableElement element, final AnnotationHandle accessor) {
        this.accessors.registerAccessor(new AnnotatedMixinElementHandlerAccessor.AnnotatedElementAccessor(element, accessor));
    }
    
    public void registerInvoker(final ExecutableElement element, final AnnotationHandle invoker) {
        this.accessors.registerAccessor(new AnnotatedMixinElementHandlerAccessor.AnnotatedElementInvoker(element, invoker));
    }
    
    public void registerSoftImplements(final AnnotationHandle implementsAnnotation) {
        this.softImplements.process(implementsAnnotation);
    }
}
