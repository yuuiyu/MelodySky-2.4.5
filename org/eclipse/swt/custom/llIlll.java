//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;

class llIlll extends AccessibleControlAdapter
{
    final /* synthetic */ StyledText this$0;
    
    llIlll(final StyledText this$0) {
        this.this$0 = this$0;
    }
    
    public void getRole(final AccessibleControlEvent e) {
        e.detail = 42;
    }
    
    public void getState(final AccessibleControlEvent e) {
        int state = 0;
        if (this.this$0.isEnabled()) {
            state |= 0x100000;
        }
        if (this.this$0.isFocusControl()) {
            state |= 0x4;
        }
        if (!this.this$0.isVisible()) {
            state |= 0x8000;
        }
        if (!this.this$0.getEditable()) {
            state |= 0x40;
        }
        if (this.this$0.isSingleLine()) {
            state |= 0x8000000;
        }
        else {
            state |= 0x10000000;
        }
        e.detail = state;
    }
    
    public void getValue(final AccessibleControlEvent e) {
        e.result = this.this$0.getText();
    }
}
