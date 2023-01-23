//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;

class llllll extends AccessibleAdapter
{
    final /* synthetic */ CLabel this$0;
    
    llllll(final CLabel this$0) {
        this.this$0 = this$0;
    }
    
    public void getName(final AccessibleEvent e) {
        e.result = this.this$0.getText();
    }
    
    public void getHelp(final AccessibleEvent e) {
        e.result = this.this$0.getToolTipText();
    }
    
    public void getKeyboardShortcut(final AccessibleEvent e) {
        final char mnemonic = this.this$0._findMnemonic(CLabel.access$000(this.this$0));
        if (mnemonic != '\0') {
            e.result = "Alt+" + mnemonic;
        }
    }
}
