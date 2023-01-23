//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.*;

class lllllI extends AccessibleControlAdapter
{
    final /* synthetic */ CTabFolder this$0;
    
    lllllI(final CTabFolder this$0) {
        this.this$0 = this$0;
    }
    
    public void getChildAtPoint(final AccessibleControlEvent e) {
        final Point testPoint = this.this$0.toControl(e.x, e.y);
        int childID = -2;
        for (int i = 0; i < this.this$0.items.length; ++i) {
            if (this.this$0.items[i].getBounds().contains(testPoint)) {
                childID = i;
                break;
            }
        }
        if (childID == -2) {
            final Rectangle bounds;
            final Rectangle location;
            final Rectangle rectangle = location = (bounds = this.this$0.getBounds());
            final int n = 0;
            rectangle.y = 0;
            bounds.x = 0;
            final Rectangle rectangle2 = location;
            rectangle2.height -= this.this$0.getClientArea().height;
            if (location.contains(testPoint)) {
                childID = -1;
            }
        }
        e.childID = childID;
    }
    
    public void getLocation(final AccessibleControlEvent e) {
        Rectangle location = null;
        Point pt = null;
        final int childID = e.childID;
        if (childID == -1) {
            location = this.this$0.getBounds();
            pt = this.this$0.getParent().toDisplay(location.x, location.y);
        }
        else {
            if (childID >= 0 && childID < this.this$0.items.length && this.this$0.items[childID].showing && !this.this$0.items[childID].isDisposed()) {
                location = this.this$0.items[childID].getBounds();
            }
            if (location != null) {
                pt = this.this$0.toDisplay(location.x, location.y);
            }
        }
        if (location != null && pt != null) {
            e.x = pt.x;
            e.y = pt.y;
            e.width = location.width;
            e.height = location.height;
        }
    }
    
    public void getChildCount(final AccessibleControlEvent e) {
        e.detail = this.this$0.items.length;
    }
    
    public void getDefaultAction(final AccessibleControlEvent e) {
        String action = null;
        final int childID = e.childID;
        if (childID >= 0 && childID < this.this$0.items.length) {
            action = SWT.getMessage("SWT_Switch");
        }
        e.result = action;
    }
    
    public void getFocus(final AccessibleControlEvent e) {
        int childID = -2;
        if (this.this$0.isFocusControl()) {
            if (this.this$0.selectedIndex == -1) {
                childID = -1;
            }
            else {
                childID = this.this$0.selectedIndex;
            }
        }
        e.childID = childID;
    }
    
    public void getRole(final AccessibleControlEvent e) {
        int role = 0;
        final int childID = e.childID;
        if (childID == -1) {
            role = 60;
        }
        else if (childID >= 0 && childID < this.this$0.items.length) {
            role = 37;
        }
        e.detail = role;
    }
    
    public void getSelection(final AccessibleControlEvent e) {
        e.childID = ((this.this$0.selectedIndex == -1) ? -2 : this.this$0.selectedIndex);
    }
    
    public void getState(final AccessibleControlEvent e) {
        int state = 0;
        final int childID = e.childID;
        if (childID == -1) {
            state = 0;
        }
        else if (childID >= 0 && childID < this.this$0.items.length) {
            state = 2097152;
            if (this.this$0.isFocusControl()) {
                state |= 0x100000;
            }
            if (this.this$0.selectedIndex == childID) {
                state |= 0x2;
                if (this.this$0.isFocusControl()) {
                    state |= 0x4;
                }
            }
        }
        e.detail = state;
    }
    
    public void getChildren(final AccessibleControlEvent e) {
        final int childIdCount = this.this$0.items.length;
        final Object[] children = new Object[childIdCount];
        for (int i = 0; i < childIdCount; ++i) {
            children[i] = i;
        }
        e.children = children;
    }
}
