//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;

class lIlllI extends AccessibleControlAdapter
{
    final /* synthetic */ CLabel this$0;
    
    lIlllI(final CLabel this$0) {
        this.this$0 = this$0;
    }
    
    public void getChildAtPoint(final AccessibleControlEvent e) {
        e.childID = -1;
    }
    
    public void getLocation(final AccessibleControlEvent e) {
        final Rectangle rect = this.this$0.getDisplay().map(this.this$0.getParent(), null, this.this$0.getBounds());
        e.x = rect.x;
        e.y = rect.y;
        e.width = rect.width;
        e.height = rect.height;
    }
    
    public void getChildCount(final AccessibleControlEvent e) {
        e.detail = 0;
    }
    
    public void getRole(final AccessibleControlEvent e) {
        e.detail = 41;
    }
    
    public void getState(final AccessibleControlEvent e) {
        e.detail = 64;
    }
}
