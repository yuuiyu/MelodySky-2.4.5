//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

class RunnableLock
{
    Runnable runnable;
    Thread thread;
    Throwable throwable;
    
    RunnableLock(final Runnable runnable) {
        this.runnable = runnable;
    }
    
    boolean done() {
        return this.runnable == null || this.throwable != null;
    }
    
    void run(final Display display) {
        if (this.runnable != null) {
            try {
                this.runnable.run();
            }
            catch (RuntimeException exception) {
                display.getRuntimeExceptionHandler().accept(exception);
            }
            catch (Error error) {
                display.getErrorHandler().accept(error);
            }
        }
        this.runnable = null;
    }
}
