//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.awt.event.*;

final class lIIIllIl extends WindowAdapter
{
    final /* synthetic */ WebBrowserWindowFactory.WebBrowserWindowStrategy val$webBrowserWindowStrategy;
    
    lIIIllIl(final WebBrowserWindowFactory.WebBrowserWindowStrategy val$webBrowserWindowStrategy) {
        this.val$webBrowserWindowStrategy = val$webBrowserWindowStrategy;
    }
    
    @Override
    public void windowOpened(final WindowEvent e) {
        this.val$webBrowserWindowStrategy.getWebBrowser().requestFocus();
    }
}
