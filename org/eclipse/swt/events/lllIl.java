//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import java.util.function.*;

class lllIl extends ShellAdapter
{
    final /* synthetic */ Consumer val$c;
    final /* synthetic */ ShellListener this$0;
    
    lllIl(final ShellListener this$0, final Consumer val$c) {
        this.this$0 = this$0;
        this.val$c = val$c;
    }
    
    @Override
    public void shellDeactivated(final ShellEvent e) {
        this.val$c.accept(e);
    }
}
