//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.awt.event.*;

class lIlIIlIl implements ItemListener
{
    final /* synthetic */ DefaultWebBrowserDecorator val$this$0;
    final /* synthetic */ DefaultWebBrowserDecorator.WebBrowserMenuBar this$1;
    
    lIlIIlIl(final DefaultWebBrowserDecorator.WebBrowserMenuBar this$1, final DefaultWebBrowserDecorator val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    @Override
    public void itemStateChanged(final ItemEvent e) {
        this.this$1.this$0.setButtonBarVisible(e.getStateChange() == 1);
    }
}
