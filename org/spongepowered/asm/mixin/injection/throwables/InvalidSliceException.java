//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.throwables;

import org.spongepowered.asm.mixin.refmap.*;
import org.spongepowered.asm.mixin.injection.struct.*;

public class InvalidSliceException extends InvalidInjectionException
{
    private static final long serialVersionUID = 1L;
    
    public InvalidSliceException(final IReferenceMapperContext context, final String message) {
        super(context, message);
    }
    
    public InvalidSliceException(final InjectionInfo info, final String message) {
        super(info, message);
    }
    
    public InvalidSliceException(final IReferenceMapperContext context, final Throwable cause) {
        super(context, cause);
    }
    
    public InvalidSliceException(final InjectionInfo info, final Throwable cause) {
        super(info, cause);
    }
    
    public InvalidSliceException(final IReferenceMapperContext context, final String message, final Throwable cause) {
        super(context, message, cause);
    }
    
    public InvalidSliceException(final InjectionInfo info, final String message, final Throwable cause) {
        super(info, message, cause);
    }
}
