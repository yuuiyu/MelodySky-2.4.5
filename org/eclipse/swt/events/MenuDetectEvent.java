//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.widgets.*;

public final class MenuDetectEvent extends TypedEvent
{
    public int x;
    public int y;
    public boolean doit;
    public int detail;
    private static final long serialVersionUID = -3061660596590828941L;
    
    public MenuDetectEvent(final Event e) {
        super(e);
        this.x = e.x;
        this.y = e.y;
        this.doit = e.doit;
        this.detail = e.detail;
    }
    
    @Override
    public String toString() {
        final String string = super.toString();
        return string.substring(0, string.length() - 1) + " x=" + this.x + " y=" + this.y + " doit=" + this.doit + " detail=" + this.detail;
    }
}
