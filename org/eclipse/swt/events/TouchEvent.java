//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.widgets.*;

public class TouchEvent extends TypedEvent
{
    public Touch[] touches;
    public int stateMask;
    public int x;
    public int y;
    static final long serialVersionUID = -8348741538373572182L;
    
    public TouchEvent(final Event e) {
        super(e);
        this.touches = e.touches;
        this.stateMask = e.stateMask;
        this.x = e.x;
        this.y = e.y;
    }
    
    @Override
    public String toString() {
        String string = super.toString();
        string = string.substring(0, string.length() - 1);
        string = string + " stateMask=0x" + Integer.toHexString(this.stateMask) + " x=" + this.x + " y=" + this.y;
        if (this.touches != null) {
            for (final Touch touch : this.touches) {
                string = string + "\n     " + touch.toString();
            }
        }
        return string;
    }
}
