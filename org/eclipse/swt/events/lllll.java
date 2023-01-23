//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import java.util.function.*;

class lllll extends TreeAdapter
{
    final /* synthetic */ Consumer val$c;
    final /* synthetic */ TreeListener this$0;
    
    lllll(final TreeListener this$0, final Consumer val$c) {
        this.this$0 = this$0;
        this.val$c = val$c;
    }
    
    @Override
    public void treeExpanded(final TreeEvent e) {
        this.val$c.accept(e);
    }
}
