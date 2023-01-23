//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import java.util.function.*;

class llllIl extends CTabFolder2Adapter
{
    final /* synthetic */ Consumer val$c;
    final /* synthetic */ CTabFolder2Listener this$0;
    
    llllIl(final CTabFolder2Listener this$0, final Consumer val$c) {
        this.this$0 = this$0;
        this.val$c = val$c;
    }
    
    public void maximize(final CTabFolderEvent e) {
        this.val$c.accept(e);
    }
}
