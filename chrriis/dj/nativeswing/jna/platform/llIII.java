//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.jna.platform;

import java.awt.event.*;

class llIII implements HierarchyListener
{
    final /* synthetic */ Runnable val$action;
    final /* synthetic */ WindowUtils.NativeWindowUtils this$0;
    
    llIII(final WindowUtils.NativeWindowUtils this$0, final Runnable val$action) {
        this.this$0 = this$0;
        this.val$action = val$action;
    }
    
    @Override
    public void hierarchyChanged(final HierarchyEvent e) {
        if ((e.getChangeFlags() & 0x2L) != 0x0L && e.getComponent().isDisplayable()) {
            e.getComponent().removeHierarchyListener(this);
            this.val$action.run();
        }
    }
}
