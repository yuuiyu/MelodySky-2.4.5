//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.awt.event.*;
import chrriis.dj.nativeswing.swtimpl.components.internal.*;
import chrriis.dj.nativeswing.*;

class lllIlIl implements ActionListener
{
    final /* synthetic */ DefaultWebBrowserDecorator val$this$0;
    final /* synthetic */ DefaultWebBrowserDecorator.WebBrowserMenuBar this$1;
    
    lllIlIl(final DefaultWebBrowserDecorator.WebBrowserMenuBar this$1, final DefaultWebBrowserDecorator val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        JWebBrowser newWebBrowser = null;
        switch (lIIlIIlI.$SwitchMap$chrriis$dj$nativeswing$swtimpl$components$internal$INativeWebBrowser$WebBrowserRuntime[((INativeWebBrowser)DefaultWebBrowserDecorator.access$800(this.this$1.this$0).getNativeComponent()).getRuntime().ordinal()]) {
            case 1: {
                newWebBrowser = new JWebBrowser(new NSOption[] { JWebBrowser.useWebkitRuntime() });
                break;
            }
            case 2: {
                newWebBrowser = new JWebBrowser(new NSOption[] { JWebBrowser.useXULRunnerRuntime() });
                break;
            }
            case 3: {
                newWebBrowser = new JWebBrowser(new NSOption[] { JWebBrowser.useEdgeRuntime() });
                break;
            }
            default: {
                newWebBrowser = new JWebBrowser(new NSOption[0]);
                break;
            }
        }
        JWebBrowser.copyAppearance(DefaultWebBrowserDecorator.access$800(this.this$1.this$0), newWebBrowser);
        JWebBrowser.copyContent(DefaultWebBrowserDecorator.access$800(this.this$1.this$0), newWebBrowser);
        final JWebBrowserWindow webBrowserWindow = WebBrowserWindowFactory.create(newWebBrowser);
        webBrowserWindow.setVisible(true);
    }
}
