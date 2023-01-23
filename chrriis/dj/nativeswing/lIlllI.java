//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing;

import java.awt.event.*;

class lIlllI implements HierarchyListener
{
    final /* synthetic */ NativeComponentWrapper this$0;
    
    lIlllI(final NativeComponentWrapper this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void hierarchyChanged(final HierarchyEvent e) {
        final long changeFlags = e.getChangeFlags();
        if ((changeFlags & 0x1L) != 0x0L) {
            this.this$0.checkParent();
        }
    }
}
