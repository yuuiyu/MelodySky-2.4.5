//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal;

import org.eclipse.swt.widgets.*;

public class ExceptionStash implements AutoCloseable
{
    Throwable storedThrowable;
    
    public void stash(Throwable throwable) {
        try {
            final Display display = Display.getCurrent();
            if (display != null) {
                if (throwable instanceof RuntimeException) {
                    display.getRuntimeExceptionHandler().accept((RuntimeException)throwable);
                    return;
                }
                if (throwable instanceof Error) {
                    display.getErrorHandler().accept((Error)throwable);
                    return;
                }
            }
        }
        catch (Throwable ex) {
            throwable = ex;
        }
        if (this.storedThrowable != null) {
            this.storedThrowable.addSuppressed(throwable);
        }
        else {
            this.storedThrowable = throwable;
        }
    }
    
    @Override
    public void close() {
        if (this.storedThrowable == null) {
            return;
        }
        final Throwable throwable = this.storedThrowable;
        this.storedThrowable = null;
        if (throwable instanceof RuntimeException) {
            throw (RuntimeException)throwable;
        }
        if (throwable instanceof Error) {
            throw (Error)throwable;
        }
    }
}
