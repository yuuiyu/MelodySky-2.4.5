//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.jna.platform;

import java.awt.event.*;

class lllII extends WindowAdapter
{
    final /* synthetic */ Runnable val$action;
    final /* synthetic */ WindowUtils.NativeWindowUtils this$0;
    
    lllII(final WindowUtils.NativeWindowUtils this$0, final Runnable val$action) {
        this.this$0 = this$0;
        this.val$action = val$action;
    }
    
    @Override
    public void windowOpened(final WindowEvent e) {
        e.getWindow().removeWindowListener(this);
        this.val$action.run();
    }
    
    @Override
    public void windowClosed(final WindowEvent e) {
        e.getWindow().removeWindowListener(this);
    }
}
