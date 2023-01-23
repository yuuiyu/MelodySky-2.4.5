//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.widgets.*;

public class MouseEvent extends TypedEvent
{
    public int button;
    public int stateMask;
    public int x;
    public int y;
    public int count;
    static final long serialVersionUID = 3257288037011566898L;
    
    public MouseEvent(final Event e) {
        super(e);
        this.x = e.x;
        this.y = e.y;
        this.button = e.button;
        this.stateMask = e.stateMask;
        this.count = e.count;
    }
    
    @Override
    public String toString() {
        final String string = super.toString();
        return string.substring(0, string.length() - 1) + " button=" + this.button + " stateMask=0x" + Integer.toHexString(this.stateMask) + " x=" + this.x + " y=" + this.y + " count=" + this.count;
    }
}
