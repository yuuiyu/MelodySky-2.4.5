//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;
import org.eclipse.swt.*;

class lIIIllI extends AccessibleAdapter
{
    final /* synthetic */ CTabFolder this$0;
    
    lIIIllI(final CTabFolder this$0) {
        this.this$0 = this$0;
    }
    
    public void getName(final AccessibleEvent e) {
        CTabItem item = null;
        final int childID = e.childID;
        if (childID == -1) {
            if (this.this$0.selectedIndex != -1) {
                item = this.this$0.items[this.this$0.selectedIndex];
            }
        }
        else if (childID >= 0 && childID < this.this$0.items.length) {
            item = this.this$0.items[childID];
        }
        e.result = ((item == null) ? null : this.this$0.stripMnemonic(item.getText()));
    }
    
    public void getHelp(final AccessibleEvent e) {
        String help = null;
        final int childID = e.childID;
        if (childID == -1) {
            help = this.this$0.getToolTipText();
        }
        else if (childID >= 0 && childID < this.this$0.items.length) {
            help = this.this$0.items[childID].getToolTipText();
        }
        e.result = help;
    }
    
    public void getKeyboardShortcut(final AccessibleEvent e) {
        String shortcut = null;
        final int childID = e.childID;
        if (childID >= 0 && childID < this.this$0.items.length) {
            final String text = this.this$0.items[childID].getText();
            if (text != null) {
                final char mnemonic = this.this$0._findMnemonic(text);
                if (mnemonic != '\0') {
                    shortcut = SWT.getMessage("SWT_Page_Mnemonic", new Object[] { mnemonic });
                }
            }
        }
        if (childID == -1) {
            shortcut = SWT.getMessage("SWT_SwitchPage_Shortcut");
        }
        e.result = shortcut;
    }
}
