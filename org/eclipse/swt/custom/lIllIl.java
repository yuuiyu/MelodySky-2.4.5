//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;

class lIllIl extends AccessibleAdapter
{
    final /* synthetic */ CTabFolder this$0;
    
    lIllIl(final CTabFolder this$0) {
        this.this$0 = this$0;
    }
    
    public void getName(final AccessibleEvent e) {
        if (e.childID != -1 && this.this$0.chevronItem != null && e.childID == this.this$0.chevronTb.indexOf(this.this$0.chevronItem)) {
            e.result = this.this$0.chevronItem.getToolTipText();
        }
    }
}
