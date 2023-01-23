//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import java.util.concurrent.atomic.*;

final class llIIllI implements Runnable
{
    final /* synthetic */ Runnable val$runnable;
    final /* synthetic */ AtomicReference val$exceptionReference;
    final /* synthetic */ AtomicBoolean val$isExecutorCallComplete;
    
    llIIllI(final Runnable val$runnable, final AtomicReference val$exceptionReference, final AtomicBoolean val$isExecutorCallComplete) {
        this.val$runnable = val$runnable;
        this.val$exceptionReference = val$exceptionReference;
        this.val$isExecutorCallComplete = val$isExecutorCallComplete;
    }
    
    @Override
    public void run() {
        try {
            this.val$runnable.run();
        }
        catch (Throwable t) {
            this.val$exceptionReference.set(t);
            synchronized (this.val$isExecutorCallComplete) {
                this.val$isExecutorCallComplete.set(true);
                this.val$isExecutorCallComplete.notify();
            }
        }
        finally {
            synchronized (this.val$isExecutorCallComplete) {
                this.val$isExecutorCallComplete.set(true);
                this.val$isExecutorCallComplete.notify();
            }
        }
    }
}
