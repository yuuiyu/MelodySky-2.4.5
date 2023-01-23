//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import java.util.function.*;

class lIllII extends CTabFolder2Adapter
{
    final /* synthetic */ Consumer val$c;
    final /* synthetic */ CTabFolder2Listener this$0;
    
    lIllII(final CTabFolder2Listener this$0, final Consumer val$c) {
        this.this$0 = this$0;
        this.val$c = val$c;
    }
    
    public void restore(final CTabFolderEvent e) {
        this.val$c.accept(e);
    }
}
