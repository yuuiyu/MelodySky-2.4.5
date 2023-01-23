//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.events.*;

class lIIIlIl implements KeyListener
{
    final /* synthetic */ PopupList this$0;
    
    lIIIlIl(final PopupList this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void keyReleased(final KeyEvent e) {
    }
    
    @Override
    public void keyPressed(final KeyEvent e) {
        if (e.character == '\r') {
            this.this$0.shell.setVisible(false);
        }
    }
}
