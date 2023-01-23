//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer.throwables;

import org.spongepowered.asm.mixin.extensibility.*;
import org.spongepowered.asm.mixin.refmap.*;

public class InvalidInterfaceMixinException extends InvalidMixinException
{
    private static final long serialVersionUID = 2L;
    
    public InvalidInterfaceMixinException(final IMixinInfo mixin, final String message) {
        super(mixin, message);
    }
    
    public InvalidInterfaceMixinException(final IReferenceMapperContext context, final String message) {
        super(context, message);
    }
    
    public InvalidInterfaceMixinException(final IMixinInfo mixin, final Throwable cause) {
        super(mixin, cause);
    }
    
    public InvalidInterfaceMixinException(final IReferenceMapperContext context, final Throwable cause) {
        super(context, cause);
    }
    
    public InvalidInterfaceMixinException(final IMixinInfo mixin, final String message, final Throwable cause) {
        super(mixin, message, cause);
    }
    
    public InvalidInterfaceMixinException(final IReferenceMapperContext context, final String message, final Throwable cause) {
        super(context, message, cause);
    }
}
