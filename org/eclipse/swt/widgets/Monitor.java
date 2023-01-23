//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.*;

public final class Monitor
{
    long handle;
    int x;
    int y;
    int width;
    int height;
    int clientX;
    int clientY;
    int clientWidth;
    int clientHeight;
    int zoom;
    
    Monitor() {
    }
    
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Monitor)) {
            return false;
        }
        final Monitor monitor = (Monitor)object;
        return this.handle == monitor.handle;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
    
    public Rectangle getClientArea() {
        return new Rectangle(this.clientX, this.clientY, this.clientWidth, this.clientHeight);
    }
    
    public int getZoom() {
        return this.zoom;
    }
    
    void setBounds(final Rectangle rect) {
        this.x = rect.x;
        this.y = rect.y;
        this.width = rect.width;
        this.height = rect.height;
    }
    
    void setClientArea(final Rectangle rect) {
        this.clientX = rect.x;
        this.clientY = rect.y;
        this.clientWidth = rect.width;
        this.clientHeight = rect.height;
    }
    
    @Override
    public int hashCode() {
        return (int)this.handle;
    }
}
