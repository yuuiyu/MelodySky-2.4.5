//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.struct;

import org.spongepowered.asm.mixin.transformer.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.mixin.injection.code.*;
import org.spongepowered.asm.util.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.google.common.base.*;

public class CallbackInjectionInfo extends InjectionInfo
{
    protected CallbackInjectionInfo(final MixinTargetContext mixin, final MethodNode method, final AnnotationNode annotation) {
        super(mixin, method, annotation);
    }
    
    @Override
    protected Injector parseInjector(final AnnotationNode injectAnnotation) {
        final boolean cancellable = ASMHelper.getAnnotationValue(injectAnnotation, "cancellable", false);
        final LocalCapture locals = ASMHelper.getAnnotationValue(injectAnnotation, "locals", LocalCapture.class, LocalCapture.NO_CAPTURE);
        final String identifier = ASMHelper.getAnnotationValue(injectAnnotation, "id", "");
        return (Injector)new CallbackInjector((InjectionInfo)this, cancellable, locals, identifier);
    }
    
    @Override
    public String getSliceId(final String id) {
        return Strings.nullToEmpty(id);
    }
}
