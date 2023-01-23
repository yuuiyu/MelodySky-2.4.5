//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.awt.event.*;

class llllIlI implements ActionListener
{
    final /* synthetic */ DefaultWebBrowserDecorator val$this$0;
    final /* synthetic */ DefaultWebBrowserDecorator.WebBrowserButtonBar this$1;
    
    llllIlI(final DefaultWebBrowserDecorator.WebBrowserButtonBar this$1, final DefaultWebBrowserDecorator val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        DefaultWebBrowserDecorator.access$800(this.this$1.this$0).navigateBack();
        DefaultWebBrowserDecorator.access$900(this.this$1.this$0).requestFocus();
    }
}
