//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal;

import java.util.function.*;

public class DefaultExceptionHandler
{
    public static final Consumer<RuntimeException> RUNTIME_EXCEPTION_HANDLER;
    public static final Consumer<Error> RUNTIME_ERROR_HANDLER;
    
    static {
        RUNTIME_EXCEPTION_HANDLER = (exception -> {
            throw exception;
        });
        RUNTIME_ERROR_HANDLER = (error -> {
            throw error;
        });
    }
}
