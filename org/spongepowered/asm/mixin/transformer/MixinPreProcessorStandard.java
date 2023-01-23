//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import java.util.*;
import org.spongepowered.asm.util.*;
import java.lang.annotation.*;
import org.spongepowered.asm.mixin.transformer.meta.*;
import org.spongepowered.asm.mixin.gen.*;
import org.spongepowered.asm.mixin.gen.throwables.*;
import org.spongepowered.asm.mixin.refmap.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.transformer.throwables.*;
import org.spongepowered.asm.mixin.extensibility.*;
import org.spongepowered.asm.lib.tree.*;
import org.apache.logging.log4j.*;

class MixinPreProcessorStandard
{
    private static final Logger logger;
    protected final MixinInfo mixin;
    protected final MixinInfo.MixinClassNode classNode;
    private final boolean verboseLogging;
    private final boolean strictUnique;
    private boolean prepared;
    private boolean attached;
    
    MixinPreProcessorStandard(final MixinInfo mixin, final MixinInfo.MixinClassNode classNode) {
        this.mixin = mixin;
        this.classNode = classNode;
        final MixinEnvironment env = mixin.getParent().getEnvironment();
        this.verboseLogging = env.getOption(MixinEnvironment.Option.DEBUG_VERBOSE);
        this.strictUnique = env.getOption(MixinEnvironment.Option.DEBUG_UNIQUE);
    }
    
    MixinPreProcessorStandard prepare() {
        if (this.prepared) {
            return this;
        }
        this.prepared = true;
        for (final MixinInfo.MixinMethodNode mixinMethod : this.classNode.mixinMethods) {
            final ClassInfo.Method method = this.mixin.getClassInfo().findMethod((MethodNode)mixinMethod);
            this.prepareMethod(mixinMethod, method);
        }
        for (final FieldNode mixinField : this.classNode.fields) {
            this.prepareField(mixinField);
        }
        return this;
    }
    
    protected void prepareMethod(final MixinInfo.MixinMethodNode mixinMethod, final ClassInfo.Method method) {
        this.prepareShadow(mixinMethod, method);
        this.prepareSoftImplements(mixinMethod, method);
    }
    
    protected void prepareShadow(final MixinInfo.MixinMethodNode mixinMethod, final ClassInfo.Method method) {
        final AnnotationNode shadowAnnotation = ASMHelper.getVisibleAnnotation((MethodNode)mixinMethod, (Class<? extends Annotation>)Shadow.class);
        if (shadowAnnotation == null) {
            return;
        }
        final String prefix = ASMHelper.getAnnotationValue(shadowAnnotation, "prefix", (Class<?>)Shadow.class);
        if (mixinMethod.name.startsWith(prefix)) {
            ASMHelper.setVisibleAnnotation((MethodNode)mixinMethod, (Class<? extends Annotation>)MixinRenamed.class, "originalName", mixinMethod.name);
            final String newName = mixinMethod.name.substring(prefix.length());
            mixinMethod.name = method.renameTo(newName);
        }
    }
    
    protected void prepareSoftImplements(final MixinInfo.MixinMethodNode mixinMethod, final ClassInfo.Method method) {
        for (final InterfaceInfo iface : this.mixin.getSoftImplements()) {
            if (iface.renameMethod((MethodNode)mixinMethod)) {
                method.renameTo(mixinMethod.name);
            }
        }
    }
    
    protected void prepareField(final FieldNode mixinField) {
    }
    
    MixinPreProcessorStandard conform(final TargetClassContext target) {
        return this.conform(target.getClassInfo());
    }
    
    MixinPreProcessorStandard conform(final ClassInfo target) {
        for (final MixinInfo.MixinMethodNode mixinMethod : this.classNode.mixinMethods) {
            if (mixinMethod.isInjector()) {
                final ClassInfo.Method method = this.mixin.getClassInfo().findMethod((MethodNode)mixinMethod, 10);
                this.conformInjector(target, mixinMethod, method);
            }
        }
        return this;
    }
    
    private void conformInjector(final ClassInfo targetClass, final MixinInfo.MixinMethodNode mixinMethod, final ClassInfo.Method method) {
        final MethodMapper methodMapper = targetClass.getMethodMapper();
        methodMapper.remapHandlerMethod(this.mixin, (MethodNode)mixinMethod, method);
    }
    
    MixinTargetContext createContextFor(final TargetClassContext target) {
        final MixinTargetContext context = new MixinTargetContext(this.mixin, (ClassNode)this.classNode, target);
        this.conform(target);
        this.attach(context);
        return context;
    }
    
    MixinPreProcessorStandard attach(final MixinTargetContext context) {
        if (this.attached) {
            throw new IllegalStateException("Preprocessor was already attached");
        }
        this.attached = true;
        this.attachMethods(context);
        this.attachFields(context);
        this.transform(context);
        return this;
    }
    
    protected void attachMethods(final MixinTargetContext context) {
        final Iterator<MixinInfo.MixinMethodNode> iter = this.classNode.mixinMethods.iterator();
        while (iter.hasNext()) {
            final MixinInfo.MixinMethodNode mixinMethod = iter.next();
            if (!this.validateMethod(context, mixinMethod)) {
                iter.remove();
            }
            else {
                if (this.attachInjectorMethod(context, mixinMethod)) {
                    continue;
                }
                if (this.attachAccessorMethod(context, mixinMethod)) {
                    iter.remove();
                }
                else if (this.attachShadowMethod(context, mixinMethod)) {
                    iter.remove();
                }
                else {
                    if (this.attachOverwriteMethod(context, mixinMethod)) {
                        continue;
                    }
                    if (this.attachUniqueMethod(context, mixinMethod)) {
                        iter.remove();
                    }
                    else {
                        this.attachMethod(mixinMethod);
                    }
                }
            }
        }
    }
    
    protected boolean validateMethod(final MixinTargetContext context, final MixinInfo.MixinMethodNode mixinMethod) {
        return true;
    }
    
    protected boolean attachInjectorMethod(final MixinTargetContext context, final MixinInfo.MixinMethodNode mixinMethod) {
        return mixinMethod.isInjector();
    }
    
    protected boolean attachAccessorMethod(final MixinTargetContext context, final MixinInfo.MixinMethodNode mixinMethod) {
        return this.attachAccessorMethod(context, mixinMethod, (Class<? extends Annotation>)Accessor.class) || this.attachAccessorMethod(context, mixinMethod, (Class<? extends Annotation>)Invoker.class);
    }
    
    private boolean attachAccessorMethod(final MixinTargetContext context, final MixinInfo.MixinMethodNode mixinMethod, final Class<? extends Annotation> type) {
        final AnnotationNode annotation = mixinMethod.getVisibleAnnotation((Class)type);
        if (annotation == null) {
            return false;
        }
        final ClassInfo.Method method = this.getSpecialMethod(mixinMethod, type);
        if (!method.isAbstract()) {
            throw new InvalidAccessorException((IReferenceMapperContext)context, "@" + ASMHelper.getSimpleName(type) + " method " + mixinMethod.name + " is not abstract");
        }
        if (method.isStatic()) {
            throw new InvalidAccessorException((IReferenceMapperContext)context, "@" + ASMHelper.getSimpleName(type) + " method " + mixinMethod.name + " cannot be static");
        }
        context.addAccessorMethod((MethodNode)mixinMethod, type);
        return true;
    }
    
    protected boolean attachShadowMethod(final MixinTargetContext context, final MixinInfo.MixinMethodNode mixinMethod) {
        if (this.attachSpecialMethod(context, mixinMethod, (Class<? extends Annotation>)Shadow.class, false)) {
            context.addShadowMethod((MethodNode)mixinMethod);
            return true;
        }
        return false;
    }
    
    protected boolean attachOverwriteMethod(final MixinTargetContext context, final MixinInfo.MixinMethodNode mixinMethod) {
        return this.attachSpecialMethod(context, mixinMethod, (Class<? extends Annotation>)Overwrite.class, true);
    }
    
    protected boolean attachSpecialMethod(final MixinTargetContext context, final MixinInfo.MixinMethodNode mixinMethod, final Class<? extends Annotation> type, final boolean overwrite) {
        final AnnotationNode annotation = mixinMethod.getVisibleAnnotation((Class)type);
        if (annotation == null) {
            return false;
        }
        if (overwrite) {
            this.checkMixinNotUnique(mixinMethod, type);
        }
        final ClassInfo.Method method = this.getSpecialMethod(mixinMethod, type);
        MethodNode target = context.findMethod((MethodNode)mixinMethod, annotation);
        if (target == null) {
            if (overwrite) {
                return false;
            }
            target = context.findRemappedMethod((MethodNode)mixinMethod);
            if (target == null) {
                throw new InvalidMixinException((IMixinInfo)this.mixin, "@" + ASMHelper.getSimpleName(type) + " method " + mixinMethod.name + " was not located in the target class");
            }
            mixinMethod.name = method.renameTo(target.name);
        }
        if ("<init>".equals(target.name)) {
            throw new InvalidMixinException((IMixinInfo)this.mixin, "Nice try! " + mixinMethod.name + " cannot alias a constructor!");
        }
        if (!target.name.equals(mixinMethod.name)) {
            if (!overwrite && (target.access & 0x2) == 0x0) {
                throw new InvalidMixinException((IMixinInfo)this.mixin, "Non-private method cannot be aliased. Found " + target.name);
            }
            mixinMethod.name = method.renameTo(target.name);
        }
        return true;
    }
    
    protected ClassInfo.Method getSpecialMethod(final MixinInfo.MixinMethodNode mixinMethod, final Class<? extends Annotation> type) {
        final ClassInfo.Method method = this.mixin.getClassInfo().findMethod((MethodNode)mixinMethod, 10);
        this.checkMethodNotUnique(type, method);
        return method;
    }
    
    protected void checkMethodNotUnique(final Class<? extends Annotation> type, final ClassInfo.Method method) {
        if (method.isUnique()) {
            final String annotation = "@" + ASMHelper.getSimpleName(type);
            throw new InvalidMixinException((IMixinInfo)this.mixin, annotation + " method " + method.getName() + " cannot be @Unique");
        }
    }
    
    protected void checkMixinNotUnique(final MixinInfo.MixinMethodNode mixinMethod, final Class<? extends Annotation> type) {
        if (this.mixin.isUnique()) {
            final String annotation = "@" + ASMHelper.getSimpleName(type);
            throw new InvalidMixinException((IMixinInfo)this.mixin, annotation + " method " + mixinMethod.name + " found in a @Unique mixin");
        }
    }
    
    protected boolean attachUniqueMethod(final MixinTargetContext context, final MixinInfo.MixinMethodNode mixinMethod) {
        final ClassInfo.Method method = this.mixin.getClassInfo().findMethod((MethodNode)mixinMethod, 10);
        if (method == null || (!method.isUnique() && !this.mixin.isUnique())) {
            return false;
        }
        final MethodNode target = context.findMethod((MethodNode)mixinMethod, null);
        if (target == null) {
            return false;
        }
        if ((mixinMethod.access & 0x6) != 0x0) {
            final String uniqueName = context.getUniqueName((MethodNode)mixinMethod);
            MixinPreProcessorStandard.logger.log(this.mixin.getLoggingLevel(), "Renaming @Unique method {}{} to {} in {}", new Object[] { mixinMethod.name, mixinMethod.desc, uniqueName, this.mixin });
            mixinMethod.name = method.renameTo(uniqueName);
            return false;
        }
        if (this.strictUnique) {
            throw new InvalidMixinException((IMixinInfo)this.mixin, "Method conflict, @Unique method " + mixinMethod.name + " in " + this.mixin + " cannot overwrite " + target.name + target.desc + " in " + context.getTarget());
        }
        MixinPreProcessorStandard.logger.warn("Discarding @Unique public method {} in {} because it already exists in {}", new Object[] { mixinMethod.name, this.mixin, context.getTarget() });
        return true;
    }
    
    protected void attachMethod(final MixinInfo.MixinMethodNode mixinMethod) {
        final ClassInfo.Method method = this.mixin.getClassInfo().findMethod((MethodNode)mixinMethod);
        if (method == null) {
            return;
        }
        final ClassInfo.Method parentMethod = this.mixin.getClassInfo().findMethodInHierarchy((MethodNode)mixinMethod, ClassInfo.SearchType.SUPER_CLASSES_ONLY);
        if (parentMethod != null && parentMethod.isRenamed()) {
            mixinMethod.name = method.renameTo(parentMethod.getName());
        }
    }
    
    protected void attachFields(final MixinTargetContext context) {
        final Iterator<FieldNode> iter = this.classNode.fields.iterator();
        while (iter.hasNext()) {
            final FieldNode mixinField = iter.next();
            final AnnotationNode shadow = ASMHelper.getVisibleAnnotation(mixinField, (Class<? extends Annotation>)Shadow.class);
            final boolean isShadow = shadow != null;
            if (!this.validateField(context, mixinField, shadow)) {
                iter.remove();
            }
            else {
                context.transformDescriptor(mixinField);
                final ClassInfo.Field field = this.mixin.getClassInfo().findField(mixinField);
                if (field.isUnique() && isShadow) {
                    throw new InvalidMixinException((IMixinInfo)this.mixin, "@Shadow field " + mixinField.name + " cannot be @Unique");
                }
                FieldNode target = context.findField(mixinField, shadow);
                if (target == null) {
                    if (shadow == null) {
                        continue;
                    }
                    target = context.findRemappedField(mixinField);
                    if (target == null) {
                        throw new InvalidMixinException((IMixinInfo)this.mixin, "Shadow field " + mixinField.name + " was not located in the target class");
                    }
                    mixinField.name = field.renameTo(target.name);
                }
                if (field.isUnique()) {
                    if ((mixinField.access & 0x6) != 0x0) {
                        final String uniqueName = context.getUniqueName(mixinField);
                        MixinPreProcessorStandard.logger.log(this.mixin.getLoggingLevel(), "Renaming @Unique field {}{} to {} in {}", new Object[] { mixinField.name, mixinField.desc, uniqueName, this.mixin });
                        mixinField.name = field.renameTo(uniqueName);
                    }
                    else {
                        if (this.strictUnique) {
                            throw new InvalidMixinException((IMixinInfo)this.mixin, "Field conflict, @Unique field " + mixinField.name + " in " + this.mixin + " cannot overwrite " + target.name + target.desc + " in " + context.getTarget());
                        }
                        MixinPreProcessorStandard.logger.warn("Discarding @Unique public field {} in {} because it already exists in {}. Note that declared FIELD INITIALISERS will NOT be removed!", new Object[] { mixinField.name, this.mixin, context.getTarget() });
                        iter.remove();
                    }
                }
                else {
                    if (!target.desc.equals(mixinField.desc)) {
                        throw new InvalidMixinException((IMixinInfo)this.mixin, "The field " + mixinField.name + " in the target class has a conflicting signature");
                    }
                    if (!target.name.equals(mixinField.name)) {
                        if ((target.access & 0x2) == 0x0 && (target.access & 0x1000) == 0x0) {
                            throw new InvalidMixinException((IMixinInfo)this.mixin, "Non-private field cannot be aliased. Found " + target.name);
                        }
                        mixinField.name = field.renameTo(target.name);
                    }
                    iter.remove();
                    if (!isShadow) {
                        continue;
                    }
                    final boolean isFinal = field.isDecoratedFinal();
                    if (this.verboseLogging && ASMHelper.hasFlag(target, 16) != isFinal) {
                        final String message = isFinal ? "@Shadow field {}::{} is decorated with @Final but target is not final" : "@Shadow target {}::{} is final but shadow is not decorated with @Final";
                        MixinPreProcessorStandard.logger.warn(message, new Object[] { this.mixin, mixinField.name });
                    }
                    context.addShadowField(mixinField, field);
                }
            }
        }
    }
    
    protected boolean validateField(final MixinTargetContext context, final FieldNode field, final AnnotationNode shadow) {
        if (ASMHelper.hasFlag(field, 8) && !ASMHelper.hasFlag(field, 2) && !ASMHelper.hasFlag(field, 4096) && shadow == null) {
            throw new InvalidMixinException((IReferenceMapperContext)context, String.format("Mixin %s contains non-private static field %s:%s", context, field.name, field.desc));
        }
        final String prefix = ASMHelper.getAnnotationValue(shadow, "prefix", (Class<?>)Shadow.class);
        if (field.name.startsWith(prefix)) {
            throw new InvalidMixinException((IReferenceMapperContext)context, String.format("@Shadow field %s.%s has a shadow prefix. This is not allowed.", context, field.name));
        }
        if (!"super$".equals(field.name)) {
            return true;
        }
        if (field.access != 2) {
            throw new InvalidMixinException((IMixinInfo)this.mixin, "Imaginary super field " + context + "." + field.name + " must be private and non-final");
        }
        if (!field.desc.equals("L" + this.mixin.getClassRef() + ";")) {
            throw new InvalidMixinException((IMixinInfo)this.mixin, "Imaginary super field " + context + "." + field.name + " must have the same type as the parent mixin");
        }
        return false;
    }
    
    protected void transform(final MixinTargetContext context) {
        for (final MethodNode mixinMethod : this.classNode.methods) {
            for (final AbstractInsnNode insn : mixinMethod.instructions) {
                if (insn instanceof MethodInsnNode) {
                    final MethodInsnNode methodNode = (MethodInsnNode)insn;
                    final ClassInfo.Method method = ClassInfo.forName(methodNode.owner).findMethodInHierarchy(methodNode, ClassInfo.SearchType.ALL_CLASSES, 2);
                    if (method == null || !method.isRenamed()) {
                        continue;
                    }
                    methodNode.name = method.getName();
                }
                else {
                    if (!(insn instanceof FieldInsnNode)) {
                        continue;
                    }
                    final FieldInsnNode fieldNode = (FieldInsnNode)insn;
                    final ClassInfo.Field field = ClassInfo.forName(fieldNode.owner).findField(fieldNode, 2);
                    if (field == null || !field.isRenamed()) {
                        continue;
                    }
                    fieldNode.name = field.getName();
                }
            }
        }
    }
    
    static {
        logger = LogManager.getLogger("mixin");
    }
}
