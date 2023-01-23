//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;
import org.eclipse.swt.graphics.*;

class lIIlIll extends AccessibleTextAdapter
{
    final /* synthetic */ CCombo this$0;
    
    lIIlIll(final CCombo this$0) {
        this.this$0 = this$0;
    }
    
    public void getCaretOffset(final AccessibleTextEvent e) {
        e.offset = this.this$0.text.getCaretPosition();
    }
    
    public void getSelectionRange(final AccessibleTextEvent e) {
        final Point sel = this.this$0.text.getSelection();
        e.offset = sel.x;
        e.length = sel.y - sel.x;
    }
}
