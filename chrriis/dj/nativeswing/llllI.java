//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.beans.*;

class llllI extends MouseAdapter
{
    final /* synthetic */ NativeComponentProxyPanel this$0;
    
    llllI(final NativeComponentProxyPanel this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void mousePressed(final MouseEvent e) {
        this.adjustFocus();
    }
    
    protected void adjustFocus() {
        Component parent = (Component)this.this$0;
        while (parent != null && !(parent instanceof Window)) {
            if (parent instanceof JInternalFrame) {
                final Window windowAncestor = SwingUtilities.getWindowAncestor((Component)this.this$0);
                if (windowAncestor != null) {
                    final boolean focusableWindowState = windowAncestor.getFocusableWindowState();
                    windowAncestor.setFocusableWindowState(false);
                    try {
                        ((JInternalFrame)parent).setSelected(true);
                    }
                    catch (PropertyVetoException ex) {}
                    windowAncestor.setFocusableWindowState(focusableWindowState);
                    break;
                }
                break;
            }
            else {
                parent = parent.getParent();
            }
        }
    }
}
