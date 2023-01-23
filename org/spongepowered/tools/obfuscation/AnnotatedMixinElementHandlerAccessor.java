//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.interfaces.*;
import org.spongepowered.asm.mixin.refmap.*;
import org.spongepowered.asm.mixin.extensibility.*;
import javax.tools.*;
import javax.annotation.processing.*;
import org.spongepowered.asm.mixin.gen.*;
import java.util.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;
import com.google.common.base.*;
import javax.lang.model.element.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import javax.lang.model.type.*;

public class AnnotatedMixinElementHandlerAccessor extends AnnotatedMixinElementHandler implements IReferenceMapperContext
{
    public AnnotatedMixinElementHandlerAccessor(final IMixinAnnotationProcessor ap, final AnnotatedMixin mixin) {
        super(ap, mixin);
    }
    
    public ReferenceMapper getReferenceMapper() {
        return null;
    }
    
    public String getClassRef() {
        return this.mixin.getClassRef();
    }
    
    public IMixinInfo getMixin() {
        throw new UnsupportedOperationException("MixinInfo not available at compile time");
    }
    
    public void registerAccessor(final AnnotatedElementAccessor elem) {
        if (elem.getAccessorType() == null) {
            elem.printMessage((Messager)this.ap, Diagnostic.Kind.WARNING, (CharSequence)"Unsupported accessor type");
            return;
        }
        final String targetName = this.getAccessorTargetName(elem);
        if (targetName == null) {
            elem.printMessage((Messager)this.ap, Diagnostic.Kind.WARNING, (CharSequence)"Cannot inflect accessor target name");
            return;
        }
        elem.setTargetName(targetName);
        for (final TypeHandle target : this.mixin.getTargets()) {
            if (elem.getAccessorType() == AccessorInfo.AccessorType.METHOD_PROXY) {
                this.registerInvokerForTarget((AnnotatedElementInvoker)elem, target);
            }
            else {
                this.registerAccessorForTarget(elem, target);
            }
        }
    }
    
    private void registerAccessorForTarget(final AnnotatedElementAccessor elem, final TypeHandle target) {
        final FieldHandle targetField = target.findField(elem.getTargetName(), elem.getTargetTypeName(), false);
        if (targetField == null) {
            elem.printMessage((Messager)this.ap, Diagnostic.Kind.ERROR, (CharSequence)("Could not locate @Accessor target " + elem + " in target " + target));
            return;
        }
        ObfuscationData<MappingField> obfData = this.obf.getDataProvider().getObfField(targetField.asMapping(false).move(target.getName()));
        if (obfData.isEmpty()) {
            final String info = this.mixin.isMultiTarget() ? (" in target " + target) : "";
            elem.printMessage((Messager)this.ap, Diagnostic.Kind.WARNING, (CharSequence)("Unable to locate obfuscation mapping" + info + " for @Accessor target " + elem));
            return;
        }
        obfData = (ObfuscationData<MappingField>)AnnotatedMixinElementHandler.stripOwnerData((ObfuscationData)obfData);
        try {
            this.obf.getReferenceManager().addFieldMapping(this.mixin.getClassRef(), elem.getTargetName(), elem.getContext(), obfData);
        }
        catch (ReferenceManager.ReferenceConflictException ex) {
            elem.printMessage((Messager)this.ap, Diagnostic.Kind.ERROR, (CharSequence)("Mapping conflict for @Accessor target " + elem + ": " + ex.getNew() + " for target " + target + " conflicts with existing mapping " + ex.getOld()));
        }
    }
    
    private void registerInvokerForTarget(final AnnotatedElementInvoker elem, final TypeHandle target) {
        final MethodHandle targetMethod = target.findMethod(elem.getTargetName(), elem.getTargetTypeName(), false);
        if (targetMethod == null) {
            elem.printMessage((Messager)this.ap, Diagnostic.Kind.ERROR, (CharSequence)("Could not locate @Invoker target " + elem + " in target " + target));
            return;
        }
        ObfuscationData<MappingMethod> obfData = this.obf.getDataProvider().getObfMethod(targetMethod.asMapping(false).move(target.getName()));
        if (obfData.isEmpty()) {
            final String info = this.mixin.isMultiTarget() ? (" in target " + target) : "";
            elem.printMessage((Messager)this.ap, Diagnostic.Kind.WARNING, (CharSequence)("Unable to locate obfuscation mapping" + info + " for @Accessor target " + elem));
            return;
        }
        obfData = (ObfuscationData<MappingMethod>)AnnotatedMixinElementHandler.stripOwnerData((ObfuscationData)obfData);
        try {
            this.obf.getReferenceManager().addMethodMapping(this.mixin.getClassRef(), elem.getTargetName(), elem.getContext(), obfData);
        }
        catch (ReferenceManager.ReferenceConflictException ex) {
            elem.printMessage((Messager)this.ap, Diagnostic.Kind.ERROR, (CharSequence)("Mapping conflict for @Invoker target " + elem + ": " + ex.getNew() + " for target " + target + " conflicts with existing mapping " + ex.getOld()));
        }
    }
    
    private String getAccessorTargetName(final AnnotatedElementAccessor elem) {
        final String value = elem.getAnnotationValue();
        if (Strings.isNullOrEmpty(value)) {
            return this.inflectAccessorTarget(elem);
        }
        return value;
    }
    
    private String inflectAccessorTarget(final AnnotatedElementAccessor elem) {
        return AccessorInfo.inflectTarget(elem.getSimpleName(), elem.getAccessorType(), "", (IReferenceMapperContext)this, false);
    }
    
    static class AnnotatedElementAccessor extends AnnotatedMixinElementHandler.AnnotatedElement<ExecutableElement>
    {
        private final TypeMirror returnType;
        private String targetName;
        
        public AnnotatedElementAccessor(final ExecutableElement element, final AnnotationHandle annotation) {
            super((Element)element, annotation);
            this.returnType = ((ExecutableElement)this.getElement()).getReturnType();
        }
        
        public String getAnnotationValue() {
            return this.getAnnotation().getValue();
        }
        
        public TypeMirror getTargetType() {
            switch (this.getAccessorType()) {
                case FIELD_GETTER: {
                    return this.returnType;
                }
                case FIELD_SETTER: {
                    return ((VariableElement)((ExecutableElement)this.getElement()).getParameters().get(0)).asType();
                }
                default: {
                    return null;
                }
            }
        }
        
        public String getTargetTypeName() {
            return TypeUtils.getTypeName(this.getTargetType());
        }
        
        public String getAccessorDesc() {
            return TypeUtils.getInternalName(this.getTargetType());
        }
        
        public MemberInfo getContext() {
            return new MemberInfo(this.getTargetName(), (String)null, this.getAccessorDesc());
        }
        
        public AccessorInfo.AccessorType getAccessorType() {
            return (this.returnType.getKind() == TypeKind.VOID) ? AccessorInfo.AccessorType.FIELD_SETTER : AccessorInfo.AccessorType.FIELD_GETTER;
        }
        
        public void setTargetName(final String targetName) {
            this.targetName = targetName;
        }
        
        public String getTargetName() {
            return this.targetName;
        }
        
        public String toString() {
            return (this.targetName != null) ? this.targetName : "<invalid>";
        }
    }
    
    static class AnnotatedElementInvoker extends AnnotatedElementAccessor
    {
        public AnnotatedElementInvoker(final ExecutableElement element, final AnnotationHandle annotation) {
            super(element, annotation);
        }
        
        @Override
        public String getAccessorDesc() {
            return TypeUtils.getDescriptor((ExecutableElement)this.getElement());
        }
        
        @Override
        public AccessorInfo.AccessorType getAccessorType() {
            return AccessorInfo.AccessorType.METHOD_PROXY;
        }
        
        @Override
        public String getTargetTypeName() {
            return TypeUtils.getJavaSignature(this.getElement());
        }
    }
}
