//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.mixin.transformer.throwables.*;
import org.spongepowered.asm.mixin.extensibility.*;

enum lIII
{
    lIII(final String x0, final int x2) {
    }
    
    @Override
    IMixinErrorHandler.ErrorAction onError(final IMixinErrorHandler handler, final String context, final InvalidMixinException ex, final IMixinInfo mixin, final IMixinErrorHandler.ErrorAction action) {
        try {
            return handler.onPrepareError(mixin.getConfig(), (Throwable)ex, mixin, action);
        }
        catch (AbstractMethodError ame) {
            return action;
        }
    }
    
    @Override
    protected String getContext(final IMixinInfo mixin, final String context) {
        return String.format("preparing %s in %s", mixin.getName(), context);
    }
}
