//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.internal.*;
import java.util.function.*;

public interface MouseListener extends SWTEventListener
{
    void mouseDoubleClick(final MouseEvent p0);
    
    void mouseDown(final MouseEvent p0);
    
    void mouseUp(final MouseEvent p0);
    
    default MouseListener mouseDoubleClickAdapter(final Consumer<MouseEvent> c) {
        return (MouseListener)new lIIlIl(this, (Consumer)c);
    }
    
    default MouseListener mouseDownAdapter(final Consumer<MouseEvent> c) {
        return (MouseListener)new lIllIl(this, (Consumer)c);
    }
    
    default MouseListener mouseUpAdapter(final Consumer<MouseEvent> c) {
        return (MouseListener)new llIIl(this, (Consumer)c);
    }
}
