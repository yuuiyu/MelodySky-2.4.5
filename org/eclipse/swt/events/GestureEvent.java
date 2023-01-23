//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.widgets.*;

public class GestureEvent extends TypedEvent
{
    public int stateMask;
    public int detail;
    public int x;
    public int y;
    public double rotation;
    public int xDirection;
    public int yDirection;
    public double magnification;
    public boolean doit;
    static final long serialVersionUID = -8348741538373572182L;
    
    public GestureEvent(final Event e) {
        super(e);
        this.stateMask = e.stateMask;
        this.x = e.x;
        this.y = e.y;
        this.detail = e.detail;
        this.rotation = e.rotation;
        this.xDirection = e.xDirection;
        this.yDirection = e.yDirection;
        this.magnification = e.magnification;
        this.doit = e.doit;
    }
    
    @Override
    public String toString() {
        final String string = super.toString();
        return string.substring(0, string.length() - 1) + " stateMask=0x" + Integer.toHexString(this.stateMask) + " detail=" + this.detail + " x=" + this.x + " y=" + this.y + " rotation=" + this.rotation + " xDirection=" + this.xDirection + " yDirection=" + this.yDirection + " magnification=" + this.magnification;
    }
}
