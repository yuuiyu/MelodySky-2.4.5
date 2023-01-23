//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;
import org.eclipse.swt.*;

class llIIlI extends AccessibleAdapter
{
    final /* synthetic */ CCombo this$0;
    
    llIIlI(final CCombo this$0) {
        this.this$0 = this$0;
    }
    
    public void getName(final AccessibleEvent e) {
        e.result = (this.this$0.isDropped() ? SWT.getMessage("SWT_Close") : SWT.getMessage("SWT_Open"));
    }
    
    public void getKeyboardShortcut(final AccessibleEvent e) {
        e.result = "Alt+Down Arrow";
    }
    
    public void getHelp(final AccessibleEvent e) {
        e.result = this.this$0.getToolTipText();
    }
}
