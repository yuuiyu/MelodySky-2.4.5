//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import java.util.function.*;

final class llIIII extends ControlAdapter
{
    final /* synthetic */ Consumer val$c;
    
    llIIII(final Consumer val$c) {
        this.val$c = val$c;
    }
    
    public void controlResized(final ControlEvent e) {
        this.val$c.accept(e);
    }
}
