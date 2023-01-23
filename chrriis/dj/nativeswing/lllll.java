//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing;

import java.awt.event.*;

class lllll implements HierarchyListener
{
    final /* synthetic */ NativeComponentProxyPanel this$0;
    
    lllll(final NativeComponentProxyPanel this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void hierarchyChanged(final HierarchyEvent e) {
        final long changeFlags = e.getChangeFlags();
        if ((changeFlags & 0x4L) != 0x0L) {
            if (this.this$0.isVisibilityConstrained) {
                this.this$0.adjustEmbeddedPanelShape();
            }
            else {
                this.this$0.adjustEmbeddedPanelBounds();
            }
        }
    }
}
