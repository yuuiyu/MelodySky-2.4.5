//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.awt.event.*;

class lIlIIlII implements ActionListener
{
    final /* synthetic */ DefaultWebBrowserDecorator val$this$0;
    final /* synthetic */ DefaultWebBrowserDecorator.WebBrowserMenuBar this$1;
    
    lIlIIlII(final DefaultWebBrowserDecorator.WebBrowserMenuBar this$1, final DefaultWebBrowserDecorator val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        final String path = this.this$1.this$0.askLocation();
        if (path != null) {
            DefaultWebBrowserDecorator.access$800(this.this$1.this$0).navigate(path);
        }
    }
}
