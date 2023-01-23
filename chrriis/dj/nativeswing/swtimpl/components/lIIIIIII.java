//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

class lIIIIIII implements ActionListener
{
    final /* synthetic */ DefaultWebBrowserDecorator val$this$0;
    final /* synthetic */ DefaultWebBrowserDecorator.WebBrowserMenuBar this$1;
    
    lIIIIIII(final DefaultWebBrowserDecorator.WebBrowserMenuBar this$1, final DefaultWebBrowserDecorator val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        final JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(DefaultWebBrowserDecorator.access$800(this.this$1.this$0)) == 0) {
            try {
                DefaultWebBrowserDecorator.access$800(this.this$1.this$0).navigate(fileChooser.getSelectedFile().getAbsolutePath());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
