//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer.throwables;

public class MixinTransformerError extends Error
{
    private static final long serialVersionUID = 1L;
    
    public MixinTransformerError(final String message) {
        super(message);
    }
    
    public MixinTransformerError(final Throwable cause) {
        super(cause);
    }
    
    public MixinTransformerError(final String message, final Throwable cause) {
        super(message, cause);
    }
}
