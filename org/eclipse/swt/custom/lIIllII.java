//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;

class lIIllII extends AccessibleAdapter
{
    final /* synthetic */ StyledText this$0;
    
    lIIllII(final StyledText this$0) {
        this.this$0 = this$0;
    }
    
    public void getName(final AccessibleEvent e) {
        String name = null;
        final String text = this.this$0.getAssociatedLabel();
        if (text != null) {
            name = this.this$0.stripMnemonic(text);
        }
        e.result = name;
    }
    
    public void getHelp(final AccessibleEvent e) {
        e.result = this.this$0.getToolTipText();
    }
    
    public void getKeyboardShortcut(final AccessibleEvent e) {
        String shortcut = null;
        final String text = this.this$0.getAssociatedLabel();
        if (text != null) {
            final char mnemonic = this.this$0._findMnemonic(text);
            if (mnemonic != '\0') {
                shortcut = "Alt+" + mnemonic;
            }
        }
        e.result = shortcut;
    }
}
