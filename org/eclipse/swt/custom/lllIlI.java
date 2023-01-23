//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;
import org.eclipse.swt.graphics.*;

class lllIlI extends AccessibleControlAdapter
{
    final /* synthetic */ CCombo this$0;
    
    lllIlI(final CCombo this$0) {
        this.this$0 = this$0;
    }
    
    public void getChildAtPoint(final AccessibleControlEvent e) {
        final Point testPoint = this.this$0.toControl(e.x, e.y);
        if (this.this$0.getBounds().contains(testPoint)) {
            e.childID = -1;
        }
    }
    
    public void getLocation(final AccessibleControlEvent e) {
        final Rectangle location = this.this$0.getBounds();
        final Point pt = this.this$0.getParent().toDisplay(location.x, location.y);
        e.x = pt.x;
        e.y = pt.y;
        e.width = location.width;
        e.height = location.height;
    }
    
    public void getChildCount(final AccessibleControlEvent e) {
        e.detail = 0;
    }
    
    public void getRole(final AccessibleControlEvent e) {
        e.detail = 46;
    }
    
    public void getState(final AccessibleControlEvent e) {
        e.detail = 0;
    }
    
    public void getValue(final AccessibleControlEvent e) {
        e.result = this.this$0.getText();
    }
}
