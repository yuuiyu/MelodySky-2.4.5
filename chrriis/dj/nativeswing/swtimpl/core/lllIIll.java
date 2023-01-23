//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;

class lllIIll extends ControlAdapter
{
    private boolean isAdjusting;
    final /* synthetic */ SWTNativeComponent.CMN_createControl this$0;
    
    lllIIll(final SWTNativeComponent.CMN_createControl this$0) {
        this.this$0 = this$0;
    }
    
    public void controlMoved(final ControlEvent e) {
        if (this.isAdjusting) {
            return;
        }
        final Shell shell = (Shell)e.widget;
        final Point location = shell.getLocation();
        if (location.x != 0 || location.y != 0) {
            this.isAdjusting = true;
            shell.setLocation(0, 0);
            this.isAdjusting = false;
        }
    }
}
