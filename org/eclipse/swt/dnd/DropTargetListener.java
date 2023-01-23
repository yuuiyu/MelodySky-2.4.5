//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.*;

public interface DropTargetListener extends SWTEventListener
{
    void dragEnter(final DropTargetEvent p0);
    
    void dragLeave(final DropTargetEvent p0);
    
    void dragOperationChanged(final DropTargetEvent p0);
    
    void dragOver(final DropTargetEvent p0);
    
    void drop(final DropTargetEvent p0);
    
    void dropAccept(final DropTargetEvent p0);
}
