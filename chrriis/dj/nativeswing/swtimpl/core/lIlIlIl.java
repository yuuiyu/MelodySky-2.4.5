//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import java.net.*;
import chrriis.dj.nativeswing.swtimpl.*;

class lIlIlIl extends NativeInterfaceAdapter
{
    final /* synthetic */ ServerSocket val$serverSocket;
    final /* synthetic */ SWTNativeComponent this$0;
    
    lIlIlIl(final SWTNativeComponent this$0, final ServerSocket val$serverSocket) {
        this.this$0 = this$0;
        this.val$serverSocket = val$serverSocket;
    }
    
    @Override
    public void nativeInterfaceClosed() {
        NativeInterface.removeNativeInterfaceListener(this);
        try {
            this.val$serverSocket.close();
        }
        catch (Exception ex) {}
    }
}
