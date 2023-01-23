//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.awt.event.*;

class llIIlll implements ActionListener
{
    final /* synthetic */ JWebBrowserWindow val$webBrowserWindow;
    final /* synthetic */ DefaultWebBrowserDecorator this$0;
    
    llIIlll(final DefaultWebBrowserDecorator this$0, final JWebBrowserWindow val$webBrowserWindow) {
        this.this$0 = this$0;
        this.val$webBrowserWindow = val$webBrowserWindow;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        this.val$webBrowserWindow.dispose();
    }
}
