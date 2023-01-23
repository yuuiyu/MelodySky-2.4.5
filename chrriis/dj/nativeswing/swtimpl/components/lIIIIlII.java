//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import javax.swing.event.*;

class lIIIIlII implements PopupMenuListener
{
    final /* synthetic */ DefaultWebBrowserDecorator val$this$0;
    final /* synthetic */ DefaultWebBrowserDecorator.WebBrowserMenuBar this$1;
    
    lIIIIlII(final DefaultWebBrowserDecorator.WebBrowserMenuBar this$1, final DefaultWebBrowserDecorator val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    @Override
    public void popupMenuCanceled(final PopupMenuEvent e) {
    }
    
    @Override
    public void popupMenuWillBecomeInvisible(final PopupMenuEvent e) {
        DefaultWebBrowserDecorator.access$1002(this.this$1.this$0, false);
    }
    
    @Override
    public void popupMenuWillBecomeVisible(final PopupMenuEvent e) {
        DefaultWebBrowserDecorator.access$1002(this.this$1.this$0, true);
        if (!this.this$1.this$0.isButtonBarVisible()) {
            DefaultWebBrowserDecorator.access$100(this.this$1.this$0);
        }
    }
}
