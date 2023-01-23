//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.interfaces.*;
import javax.tools.*;
import javax.annotation.processing.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;
import org.spongepowered.tools.obfuscation.struct.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import javax.lang.model.element.*;
import java.util.*;

class AnnotatedMixinElementHandlerInjector extends AnnotatedMixinElementHandler
{
    AnnotatedMixinElementHandlerInjector(final IMixinAnnotationProcessor ap, final AnnotatedMixin mixin) {
        super(ap, mixin);
    }
    
    public void registerInjector(final AnnotatedElementInjector elem) {
        if (this.mixin.isInterface()) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, "Injector in interface is unsupported", elem.getElement());
        }
        final String reference = elem.getAnnotation().getValue("method");
        final MemberInfo targetMember = MemberInfo.parse(reference);
        if (targetMember.name == null) {
            return;
        }
        try {
            targetMember.validate();
        }
        catch (InvalidMemberDescriptorException ex) {
            elem.printMessage((Messager)this.ap, Diagnostic.Kind.ERROR, (CharSequence)ex.getMessage());
        }
        if (targetMember.desc != null) {
            this.validateReferencedTarget((ExecutableElement)elem.getElement(), elem.getAnnotation(), targetMember, elem.toString());
        }
        if (!elem.shouldRemap()) {
            return;
        }
        for (final TypeHandle target : this.mixin.getTargets()) {
            if (!this.registerInjector(elem, reference, targetMember, target)) {
                break;
            }
        }
    }
    
    private boolean registerInjector(final AnnotatedElementInjector elem, final String reference, final MemberInfo targetMember, final TypeHandle target) {
        final String desc = target.findDescriptor(targetMember);
        if (desc == null) {
            final Diagnostic.Kind error = this.mixin.isMultiTarget() ? Diagnostic.Kind.ERROR : Diagnostic.Kind.WARNING;
            if (target.isSimulated()) {
                elem.printMessage((Messager)this.ap, Diagnostic.Kind.NOTE, (CharSequence)(elem + " target '" + reference + "' in @Pseudo mixin will not be obfuscated"));
            }
            else if (target.isImaginary()) {
                elem.printMessage((Messager)this.ap, error, (CharSequence)(elem + " target requires method signature because enclosing type information for " + target + " is unavailable"));
            }
            else if (!"<init>".equals(targetMember.name)) {
                elem.printMessage((Messager)this.ap, error, (CharSequence)("Unable to determine signature for " + elem + " target method"));
            }
            return true;
        }
        final String targetName = elem + " target " + targetMember.name;
        final MappingMethod targetMethod = target.getMappingMethod(targetMember.name, desc);
        ObfuscationData<MappingMethod> obfData = this.obf.getDataProvider().getObfMethod(targetMethod);
        if (obfData.isEmpty()) {
            if (!target.isSimulated()) {
                final Diagnostic.Kind error2 = "<init>".equals(targetMember.name) ? Diagnostic.Kind.WARNING : Diagnostic.Kind.ERROR;
                elem.addMessage(error2, "No obfuscation mapping for " + targetName, elem.getElement(), elem.getAnnotation());
                return false;
            }
            obfData = this.obf.getDataProvider().getRemappedMethod(targetMethod);
        }
        try {
            if ((targetMember.owner == null && this.mixin.isMultiTarget()) || target.isSimulated()) {
                obfData = (ObfuscationData<MappingMethod>)AnnotatedMixinElementHandler.stripOwnerData((ObfuscationData)obfData);
            }
            this.obf.getReferenceManager().addMethodMapping(this.classRef, reference, obfData);
        }
        catch (ReferenceManager.ReferenceConflictException ex) {
            final String conflictType = this.mixin.isMultiTarget() ? "Multi-target" : "Target";
            elem.printMessage((Messager)this.ap, Diagnostic.Kind.ERROR, (CharSequence)(conflictType + " reference conflict for " + targetName + ": " + reference + " -> " + ex.getNew() + " previously defined as " + ex.getOld()));
        }
        return true;
    }
    
    public void registerInjectionPoint(final AnnotatedElementInjectionPoint elem, final String format) {
        if (this.mixin.isInterface()) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, "Injector in interface is unsupported", elem.getElement());
        }
        if (!elem.getAt().getBoolean("remap", true)) {
            return;
        }
        final String type = InjectionPointData.parseType((String)elem.getAt().getValue("value"));
        final String target = elem.getAt().getValue("target");
        if ("NEW".equals(type)) {
            this.remapNewTarget(String.format(format, type + ".<target>"), target, elem);
            this.remapNewTarget(String.format(format, type + ".args[class]"), elem.getAtArg("class"), elem);
        }
        else {
            this.remapReference(String.format(format, type + ".<target>"), target, elem);
        }
    }
    
    protected final void remapNewTarget(final String subject, final String reference, final AnnotatedElementInjectionPoint elem) {
        if (reference == null) {
            return;
        }
        final MemberInfo member = MemberInfo.parse(reference);
        final String target = member.toCtorType();
        if (target != null) {
            final String desc = member.toCtorDesc();
            final MappingMethod m = new MappingMethod(target, ".", (desc != null) ? desc : "()V");
            final ObfuscationData<MappingMethod> remapped = this.obf.getDataProvider().getRemappedMethod(m);
            if (remapped.isEmpty()) {
                this.ap.printMessage(Diagnostic.Kind.WARNING, "Cannot find class mapping for " + subject + " '" + target + "'", elem.getElement(), elem.getAnnotation().asMirror());
                return;
            }
            final ObfuscationData<String> mappings = new ObfuscationData<String>();
            for (final ObfuscationType type : remapped) {
                final MappingMethod mapping = remapped.get(type);
                if (desc == null) {
                    mappings.add(type, mapping.getOwner());
                }
                else {
                    mappings.add(type, mapping.getDesc().replace(")V", ")L" + mapping.getOwner() + ";"));
                }
            }
            this.obf.getReferenceManager().addClassMapping(this.classRef, reference, mappings);
        }
        elem.notifyRemapped();
    }
    
    protected final boolean remapReference(final String subject, final String reference, final AnnotatedElementInjectionPoint elem) {
        if (reference == null) {
            return false;
        }
        final AnnotationMirror errorsOn = ((this.ap.getCompilerEnvironment() == IMixinAnnotationProcessor.CompilerEnvironment.JDT) ? elem.getAt() : elem.getAnnotation()).asMirror();
        final MemberInfo targetMember = MemberInfo.parse(reference);
        if (!targetMember.isFullyQualified()) {
            final String missing = (targetMember.owner == null) ? ((targetMember.desc == null) ? "owner and signature" : "owner") : "signature";
            this.ap.printMessage(Diagnostic.Kind.ERROR, subject + " is not fully qualified, missing " + missing, elem.getElement(), errorsOn);
            return false;
        }
        try {
            targetMember.validate();
        }
        catch (InvalidMemberDescriptorException ex) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, ex.getMessage(), elem.getElement(), errorsOn);
        }
        try {
            if (targetMember.isField()) {
                final ObfuscationData<MappingField> obfFieldData = this.obf.getDataProvider().getObfFieldRecursive(targetMember);
                if (obfFieldData.isEmpty()) {
                    this.ap.printMessage(Diagnostic.Kind.WARNING, "Cannot find field mapping for " + subject + " '" + reference + "'", elem.getElement(), errorsOn);
                    return false;
                }
                this.obf.getReferenceManager().addFieldMapping(this.classRef, reference, targetMember, obfFieldData);
            }
            else {
                final ObfuscationData<MappingMethod> obfMethodData = this.obf.getDataProvider().getObfMethodRecursive(targetMember);
                if (obfMethodData.isEmpty() && (targetMember.owner == null || !targetMember.owner.startsWith("java/lang/"))) {
                    this.ap.printMessage(Diagnostic.Kind.WARNING, "Cannot find method mapping for " + subject + " '" + reference + "'", elem.getElement(), errorsOn);
                    return false;
                }
                this.obf.getReferenceManager().addMethodMapping(this.classRef, reference, targetMember, obfMethodData);
            }
        }
        catch (ReferenceManager.ReferenceConflictException ex2) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, "Unexpected reference conflict for " + subject + ": " + reference + " -> " + ex2.getNew() + " previously defined as " + ex2.getOld(), elem.getElement(), errorsOn);
            return false;
        }
        return true;
    }
    
    static class AnnotatedElementInjector extends AnnotatedMixinElementHandler.AnnotatedElement<ExecutableElement>
    {
        private final InjectorRemap state;
        
        public AnnotatedElementInjector(final ExecutableElement element, final AnnotationHandle annotation, final InjectorRemap shouldRemap) {
            super((Element)element, annotation);
            this.state = shouldRemap;
        }
        
        public boolean shouldRemap() {
            return this.state.shouldRemap();
        }
        
        public void addMessage(final Diagnostic.Kind kind, final CharSequence msg, final Element element, final AnnotationHandle annotation) {
            this.state.addMessage(kind, msg, element, annotation);
        }
        
        public String toString() {
            return this.getAnnotation().toString();
        }
    }
    
    static class AnnotatedElementInjectionPoint extends AnnotatedMixinElementHandler.AnnotatedElement<ExecutableElement>
    {
        private final AnnotationHandle at;
        private Map<String, String> args;
        private final InjectorRemap state;
        
        public AnnotatedElementInjectionPoint(final ExecutableElement element, final AnnotationHandle inject, final AnnotationHandle at, final InjectorRemap state) {
            super((Element)element, inject);
            this.at = at;
            this.state = state;
        }
        
        public AnnotationHandle getAt() {
            return this.at;
        }
        
        public String getAtArg(final String key) {
            if (this.args == null) {
                this.args = new HashMap<String, String>();
                for (final String arg : this.at.getList("args")) {
                    if (arg == null) {
                        continue;
                    }
                    final int eqPos = arg.indexOf(61);
                    if (eqPos > -1) {
                        this.args.put(arg.substring(0, eqPos), arg.substring(eqPos + 1));
                    }
                    else {
                        this.args.put(arg, "");
                    }
                }
            }
            return this.args.get(key);
        }
        
        public void notifyRemapped() {
            this.state.notifyRemapped();
        }
    }
}
