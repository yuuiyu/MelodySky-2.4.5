//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing;

import java.awt.event.*;
import java.awt.*;

class lIllII implements HierarchyBoundsListener
{
    final /* synthetic */ NativeComponentProxyPanel this$0;
    
    lIllII(final NativeComponentProxyPanel this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void ancestorMoved(final HierarchyEvent e) {
        final Component component = e.getChanged();
        if (component instanceof Window) {
            return;
        }
        this.this$0.adjustEmbeddedPanelBounds();
    }
    
    @Override
    public void ancestorResized(final HierarchyEvent e) {
        this.this$0.adjustEmbeddedPanelBounds();
    }
}
