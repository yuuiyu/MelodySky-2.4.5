//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public final class PaintEvent extends TypedEvent
{
    public GC gc;
    public int x;
    public int y;
    public int width;
    public int height;
    public int count;
    static final long serialVersionUID = 3256446919205992497L;
    
    public PaintEvent(final Event e) {
        super(e);
        this.gc = e.gc;
        this.x = e.x;
        this.y = e.y;
        this.width = e.width;
        this.height = e.height;
        this.count = e.count;
    }
    
    @Override
    public String toString() {
        final String string = super.toString();
        return string.substring(0, string.length() - 1) + " gc=" + this.gc + " x=" + this.x + " y=" + this.y + " width=" + this.width + " height=" + this.height + " count=" + this.count;
    }
}
