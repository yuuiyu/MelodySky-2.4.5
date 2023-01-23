//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;

class lIIlIIl extends AccessibleControlAdapter
{
    final /* synthetic */ CCombo this$0;
    
    lIIlIIl(final CCombo this$0) {
        this.this$0 = this$0;
    }
    
    public void getRole(final AccessibleControlEvent e) {
        e.detail = (this.this$0.text.getEditable() ? 42 : 41);
    }
}
