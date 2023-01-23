//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.internal.*;
import java.util.function.*;

public interface MouseTrackListener extends SWTEventListener
{
    void mouseEnter(final MouseEvent p0);
    
    void mouseExit(final MouseEvent p0);
    
    void mouseHover(final MouseEvent p0);
    
    default MouseTrackListener mouseEnterAdapter(final Consumer<MouseEvent> c) {
        return (MouseTrackListener)new llIlI(this, (Consumer)c);
    }
    
    default MouseTrackListener mouseExitAdapter(final Consumer<MouseEvent> c) {
        return (MouseTrackListener)new lIIllI(this, (Consumer)c);
    }
    
    default MouseTrackListener mouseHoverAdapter(final Consumer<MouseEvent> c) {
        return (MouseTrackListener)new lIllll(this, (Consumer)c);
    }
}
