//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.lib.util.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.mixin.throwables.*;

public class MixinTransformerModuleCheckClass implements IMixinTransformerModule
{
    public void preApply(final TargetClassContext context) {
    }
    
    public void postApply(final TargetClassContext context) {
        try {
            context.getClassNode().accept((ClassVisitor)new CheckClassAdapter((ClassVisitor)new MixinClassWriter(2)));
        }
        catch (RuntimeException ex) {
            throw new ValidationFailedException(ex.getMessage(), ex);
        }
    }
    
    public static class ValidationFailedException extends MixinException
    {
        private static final long serialVersionUID = 1L;
        
        public ValidationFailedException(final String message, final Throwable cause) {
            super(message, cause);
        }
        
        public ValidationFailedException(final String message) {
            super(message);
        }
        
        public ValidationFailedException(final Throwable cause) {
            super(cause);
        }
    }
}
