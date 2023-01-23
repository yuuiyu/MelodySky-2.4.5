//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.events.*;

class lIlIlI implements ControlListener
{
    final /* synthetic */ TreeEditor this$0;
    
    lIlIlI(final TreeEditor this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void controlMoved(final ControlEvent e) {
        this.this$0.layout();
    }
    
    @Override
    public void controlResized(final ControlEvent e) {
        this.this$0.layout();
    }
}
