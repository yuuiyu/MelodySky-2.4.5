//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import javax.swing.*;
import java.awt.*;

class lIIIIllI extends JProgressBar
{
    final /* synthetic */ DefaultWebBrowserDecorator val$this$0;
    final /* synthetic */ DefaultWebBrowserDecorator.WebBrowserStatusBar this$1;
    
    lIIIIllI(final DefaultWebBrowserDecorator.WebBrowserStatusBar this$1, final DefaultWebBrowserDecorator val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.getParent().getWidth() / 10, 0);
    }
}
