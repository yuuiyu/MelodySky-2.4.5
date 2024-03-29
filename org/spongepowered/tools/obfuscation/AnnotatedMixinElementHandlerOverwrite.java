//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.interfaces.*;
import javax.tools.*;
import java.util.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;
import javax.annotation.processing.*;
import java.lang.reflect.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import javax.lang.model.element.*;

class AnnotatedMixinElementHandlerOverwrite extends AnnotatedMixinElementHandler
{
    AnnotatedMixinElementHandlerOverwrite(final IMixinAnnotationProcessor ap, final AnnotatedMixin mixin) {
        super(ap, mixin);
    }
    
    public void registerOverwrite(final AnnotatedElementOverwrite elem) {
        final AnnotatedMixinElementHandler.AliasedElementName name = new AnnotatedMixinElementHandler.AliasedElementName(elem.getElement(), elem.getAnnotation());
        this.validateTargetMethod((ExecutableElement)elem.getElement(), elem.getAnnotation(), name, "@Overwrite");
        this.checkConstraints((ExecutableElement)elem.getElement(), elem.getAnnotation());
        if (!this.mixin.remap()) {
            return;
        }
        for (final TypeHandle target : this.mixin.getTargets()) {
            if (!this.registerOverwriteForTarget(elem, target)) {
                return;
            }
        }
        if (!"true".equalsIgnoreCase(this.ap.getOption("disableOverwriteChecker"))) {
            final Diagnostic.Kind overwriteErrorKind = "error".equalsIgnoreCase(this.ap.getOption("overwriteErrorLevel")) ? Diagnostic.Kind.ERROR : Diagnostic.Kind.WARNING;
            final String javadoc = this.ap.getJavadocProvider().getJavadoc(elem.getElement());
            if (javadoc == null) {
                this.ap.printMessage(overwriteErrorKind, "@Overwrite is missing javadoc comment", elem.getElement());
                return;
            }
            if (!javadoc.toLowerCase().contains("@author")) {
                this.ap.printMessage(overwriteErrorKind, "@Overwrite is missing an @author tag", elem.getElement());
            }
            if (!javadoc.toLowerCase().contains("@reason")) {
                this.ap.printMessage(overwriteErrorKind, "@Overwrite is missing an @reason tag", elem.getElement());
            }
        }
    }
    
    private boolean registerOverwriteForTarget(final AnnotatedElementOverwrite elem, final TypeHandle target) {
        final MappingMethod targetMethod = new MappingMethod(target.getName(), elem.getSimpleName(), elem.getDesc());
        final ObfuscationData<MappingMethod> obfData = this.obf.getDataProvider().getObfMethod(targetMethod);
        if (obfData.isEmpty()) {
            Diagnostic.Kind error = Diagnostic.Kind.ERROR;
            try {
                final Method md = ((ExecutableElement)elem.getElement()).getClass().getMethod("isStatic", (Class<?>[])new Class[0]);
                if (md.invoke(elem.getElement(), new Object[0])) {
                    error = Diagnostic.Kind.WARNING;
                }
            }
            catch (Exception ex2) {}
            this.ap.printMessage(error, "No obfuscation mapping for @Overwrite method", elem.getElement());
            return false;
        }
        try {
            this.addMethodMappings(elem.getSimpleName(), elem.getDesc(), (ObfuscationData)obfData);
        }
        catch (Mappings.MappingConflictException ex) {
            elem.printMessage((Messager)this.ap, Diagnostic.Kind.ERROR, (CharSequence)("Mapping conflict for @Overwrite method: " + ex.getNew().getSimpleName() + " for target " + target + " conflicts with existing mapping " + ex.getOld().getSimpleName()));
            return false;
        }
        return true;
    }
    
    static class AnnotatedElementOverwrite extends AnnotatedMixinElementHandler.AnnotatedElement<ExecutableElement>
    {
        public AnnotatedElementOverwrite(final ExecutableElement element, final AnnotationHandle annotation) {
            super((Element)element, annotation);
        }
    }
}
