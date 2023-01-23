//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import java.util.function.*;

class lIlIlI extends KeyAdapter
{
    final /* synthetic */ Consumer val$c;
    final /* synthetic */ KeyListener this$0;
    
    lIlIlI(final KeyListener this$0, final Consumer val$c) {
        this.this$0 = this$0;
        this.val$c = val$c;
    }
    
    public void keyReleased(final KeyEvent e) {
        this.val$c.accept(e);
    }
}
