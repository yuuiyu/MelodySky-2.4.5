//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;

public class Event
{
    public Display display;
    public Widget widget;
    public int type;
    public int detail;
    public Widget item;
    public int index;
    public GC gc;
    public int x;
    public int y;
    public int width;
    public int height;
    public int count;
    public int time;
    public int button;
    public char character;
    public int keyCode;
    public int keyLocation;
    public int stateMask;
    public int start;
    public int end;
    public String text;
    public int[] segments;
    public char[] segmentsChars;
    public boolean doit;
    public Object data;
    public Touch[] touches;
    public int xDirection;
    public int yDirection;
    public double magnification;
    public double rotation;
    
    public Event() {
        this.doit = true;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
    
    Rectangle getBoundsInPixels() {
        return DPIUtil.autoScaleUp(this.getBounds());
    }
    
    Point getLocation() {
        return new Point(this.x, this.y);
    }
    
    Point getLocationInPixels() {
        return DPIUtil.autoScaleUp(new Point(this.x, this.y));
    }
    
    public void setBounds(final Rectangle rect) {
        this.x = rect.x;
        this.y = rect.y;
        this.width = rect.width;
        this.height = rect.height;
    }
    
    void setBoundsInPixels(final Rectangle rect) {
        this.setBounds(DPIUtil.autoScaleDown(rect));
    }
    
    void setLocationInPixels(final int x, final int y) {
        this.x = DPIUtil.autoScaleDown(x);
        this.y = DPIUtil.autoScaleDown(y);
    }
    
    @Override
    public String toString() {
        return "Event {type=" + this.type + " " + this.widget + " time=" + this.time + " data=" + this.data + " x=" + this.x + " y=" + this.y + " width=" + this.width + " height=" + this.height + " detail=" + this.detail;
    }
}
