//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import java.util.concurrent.atomic.*;
import java.net.*;
import java.io.*;

class lllIllI extends Thread
{
    final /* synthetic */ AtomicReference val$isServerSocketToBeClosed;
    final /* synthetic */ ServerSocket val$serverSocket;
    final /* synthetic */ SWTNativeComponent this$0;
    
    lllIllI(final SWTNativeComponent this$0, final String x0, final AtomicReference val$isServerSocketToBeClosed, final ServerSocket val$serverSocket) {
        this.this$0 = this$0;
        this.val$isServerSocketToBeClosed = val$isServerSocketToBeClosed;
        this.val$serverSocket = val$serverSocket;
        super(x0);
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 50; ++i) {
            if (!this.val$isServerSocketToBeClosed.get()) {
                return;
            }
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
        if (this.val$isServerSocketToBeClosed.get()) {
            try {
                this.val$serverSocket.close();
            }
            catch (IOException ex2) {}
        }
    }
}
