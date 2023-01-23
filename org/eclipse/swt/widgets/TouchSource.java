//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.*;

public final class TouchSource
{
    long handle;
    boolean direct;
    Rectangle bounds;
    
    TouchSource(final long handle, final boolean direct, final Rectangle bounds) {
        this.handle = handle;
        this.direct = direct;
        this.bounds = bounds;
    }
    
    public boolean isDirect() {
        return this.direct;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(this.bounds.x, this.bounds.y, this.bounds.width, this.bounds.height);
    }
    
    @Override
    public String toString() {
        return "TouchSource {handle=" + this.handle + " direct=" + this.direct + " bounds=" + this.bounds;
    }
}
