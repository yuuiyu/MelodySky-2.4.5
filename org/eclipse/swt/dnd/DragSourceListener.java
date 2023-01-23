//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.*;

public interface DragSourceListener extends SWTEventListener
{
    void dragStart(final DragSourceEvent p0);
    
    void dragSetData(final DragSourceEvent p0);
    
    void dragFinished(final DragSourceEvent p0);
}
