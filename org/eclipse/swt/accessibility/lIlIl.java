//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.win32.*;

class lIlIl implements Runnable
{
    final /* synthetic */ Accessible this$0;
    
    lIlIl(final Accessible this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (!this.this$0.isATRunning()) {
            return;
        }
        OS.NotifyWinEvent(32779, this.this$0.control.handle, -8, this.this$0.eventChildID());
        if (!Accessible.UseIA2) {
            return;
        }
        OS.NotifyWinEvent(283, this.this$0.control.handle, -4, this.this$0.eventChildID());
    }
}
