//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.awt.event.*;

class lIIllIlI extends KeyAdapter
{
    final /* synthetic */ DefaultWebBrowserDecorator val$this$0;
    final /* synthetic */ DefaultWebBrowserDecorator.WebBrowserLocationBar this$1;
    
    lIIllIlI(final DefaultWebBrowserDecorator.WebBrowserLocationBar this$1, final DefaultWebBrowserDecorator val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    @Override
    public void keyPressed(final KeyEvent e) {
        if (e.getKeyCode() == 27) {
            this.this$1.updateLocation();
            DefaultWebBrowserDecorator.WebBrowserLocationBar.access$1100(this.this$1).selectAll();
        }
    }
}
