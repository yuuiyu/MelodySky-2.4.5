//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;

class lIIIIll extends AccessibleAdapter
{
    final /* synthetic */ TableCursor this$0;
    
    lIIIIll(final TableCursor this$0) {
        this.this$0 = this$0;
    }
    
    public void getName(final AccessibleEvent e) {
        if (this.this$0.row == null) {
            return;
        }
        final int columnIndex = (this.this$0.column == null) ? 0 : this.this$0.table.indexOf(this.this$0.column);
        e.result = this.this$0.row.getText(columnIndex);
    }
}
