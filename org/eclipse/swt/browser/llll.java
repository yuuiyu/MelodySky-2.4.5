//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import java.util.function.*;

class llll extends VisibilityWindowAdapter
{
    final /* synthetic */ Consumer val$c;
    final /* synthetic */ VisibilityWindowListener this$0;
    
    llll(final VisibilityWindowListener this$0, final Consumer val$c) {
        this.this$0 = this$0;
        this.val$c = val$c;
    }
    
    @Override
    public void show(final WindowEvent e) {
        this.val$c.accept(e);
    }
}
