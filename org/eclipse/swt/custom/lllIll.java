//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;

class lllIll extends AccessibleAdapter
{
    final /* synthetic */ CTabFolder this$0;
    
    lllIll(final CTabFolder this$0) {
        this.this$0 = this$0;
    }
    
    public void getName(final AccessibleEvent e) {
        if (e.childID != -1) {
            if (this.this$0.minItem != null && e.childID == this.this$0.minMaxTb.indexOf(this.this$0.minItem)) {
                e.result = this.this$0.minItem.getToolTipText();
            }
            else if (this.this$0.maxItem != null && e.childID == this.this$0.minMaxTb.indexOf(this.this$0.maxItem)) {
                e.result = this.this$0.maxItem.getToolTipText();
            }
        }
    }
}
