//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl;

import java.util.concurrent.atomic.*;

final class lIIII extends Thread
{
    final /* synthetic */ AtomicBoolean val$isInitialized;
    
    lIIII(final String x0, final AtomicBoolean val$isInitialized) {
        this.val$isInitialized = val$isInitialized;
        super(x0);
    }
    
    @Override
    public void run() {
        try {
            NativeInterface.initialize();
        }
        finally {
            this.val$isInitialized.set(true);
            synchronized (this.val$isInitialized) {
                this.val$isInitialized.notify();
            }
        }
        if (!NativeInterface.isEventPumpRunning()) {
            NativeInterface.runEventPump();
        }
    }
}
