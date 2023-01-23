//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.events.*;

class llIIll implements MouseListener
{
    final /* synthetic */ PopupList this$0;
    
    llIIll(final PopupList this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void mouseDoubleClick(final MouseEvent e) {
    }
    
    @Override
    public void mouseDown(final MouseEvent e) {
    }
    
    @Override
    public void mouseUp(final MouseEvent e) {
        this.this$0.shell.setVisible(false);
    }
}
