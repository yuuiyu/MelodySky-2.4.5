//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import java.util.function.*;

class lIlII extends AccessibleAdapter
{
    final /* synthetic */ Consumer val$c;
    final /* synthetic */ AccessibleListener this$0;
    
    lIlII(final AccessibleListener this$0, final Consumer val$c) {
        this.this$0 = this$0;
        this.val$c = val$c;
    }
    
    public void getKeyboardShortcut(final AccessibleEvent e) {
        this.val$c.accept(e);
    }
}
