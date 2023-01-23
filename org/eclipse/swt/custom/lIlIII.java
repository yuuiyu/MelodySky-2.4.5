//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;

class lIlIII extends AccessibleAdapter
{
    final /* synthetic */ TreeCursor this$0;
    
    lIlIII(final TreeCursor this$0) {
        this.this$0 = this$0;
    }
    
    public void getName(final AccessibleEvent e) {
        if (this.this$0.row == null) {
            return;
        }
        final int columnIndex = (this.this$0.column == null) ? 0 : this.this$0.tree.indexOf(this.this$0.column);
        e.result = this.this$0.row.getText(columnIndex);
    }
}
