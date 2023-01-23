//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.jna.platform;

import java.awt.*;
import javax.swing.*;

class lllll implements Runnable
{
    final /* synthetic */ Window val$w;
    final /* synthetic */ boolean val$transparent;
    final /* synthetic */ WindowUtils.X11WindowUtils this$0;
    
    lllll(final WindowUtils.X11WindowUtils this$0, final Window val$w, final boolean val$transparent) {
        this.this$0 = this$0;
        this.val$w = val$w;
        this.val$transparent = val$transparent;
    }
    
    @Override
    public void run() {
        final JRootPane root = ((RootPaneContainer)this.val$w).getRootPane();
        final JLayeredPane lp = root.getLayeredPane();
        final Container content = root.getContentPane();
        if (content instanceof WindowUtils.X11WindowUtils.X11TransparentContentPane) {
            ((WindowUtils.X11WindowUtils.X11TransparentContentPane)content).setTransparent(this.val$transparent);
        }
        else if (this.val$transparent) {
            final WindowUtils.X11WindowUtils.X11TransparentContentPane x11content = this.this$0.new X11TransparentContentPane(content);
            root.setContentPane(x11content);
            lp.add(new WindowUtils.RepaintTrigger(x11content), JLayeredPane.DRAG_LAYER);
        }
        this.this$0.setLayersTransparent(this.val$w, this.val$transparent);
        this.this$0.setForceHeavyweightPopups(this.val$w, this.val$transparent);
        this.this$0.setDoubleBuffered(this.val$w, !this.val$transparent);
    }
}
