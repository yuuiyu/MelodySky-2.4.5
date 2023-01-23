//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import java.util.function.*;

class lIlII extends VisibilityWindowAdapter
{
    final /* synthetic */ Consumer val$c;
    final /* synthetic */ VisibilityWindowListener this$0;
    
    lIlII(final VisibilityWindowListener this$0, final Consumer val$c) {
        this.this$0 = this$0;
        this.val$c = val$c;
    }
    
    @Override
    public void hide(final WindowEvent e) {
        this.val$c.accept(e);
    }
}
