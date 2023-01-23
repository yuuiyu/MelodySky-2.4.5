//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;
import org.eclipse.swt.*;

class llIlII extends AccessibleControlAdapter
{
    final /* synthetic */ CCombo this$0;
    
    llIlII(final CCombo this$0) {
        this.this$0 = this$0;
    }
    
    public void getDefaultAction(final AccessibleControlEvent e) {
        e.result = (this.this$0.isDropped() ? SWT.getMessage("SWT_Close") : SWT.getMessage("SWT_Open"));
    }
}
