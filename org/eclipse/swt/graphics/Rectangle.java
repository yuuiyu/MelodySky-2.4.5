//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import java.io.*;
import org.eclipse.swt.*;

public final class Rectangle implements Serializable
{
    public int x;
    public int y;
    public int width;
    public int height;
    static final long serialVersionUID = 3256439218279428914L;
    
    public Rectangle(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void add(final Rectangle rect) {
        if (rect == null) {
            SWT.error(4);
        }
        final int left = (this.x < rect.x) ? this.x : rect.x;
        final int top = (this.y < rect.y) ? this.y : rect.y;
        int lhs = this.x + this.width;
        int rhs = rect.x + rect.width;
        final int right = (lhs > rhs) ? lhs : rhs;
        lhs = this.y + this.height;
        rhs = rect.y + rect.height;
        final int bottom = (lhs > rhs) ? lhs : rhs;
        this.x = left;
        this.y = top;
        this.width = right - left;
        this.height = bottom - top;
    }
    
    public boolean contains(final int x, final int y) {
        return x >= this.x && y >= this.y && x < this.x + this.width && y < this.y + this.height;
    }
    
    public boolean contains(final Point pt) {
        if (pt == null) {
            SWT.error(4);
        }
        return this.contains(pt.x, pt.y);
    }
    
    @Override
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Rectangle)) {
            return false;
        }
        final Rectangle r = (Rectangle)object;
        return r.x == this.x && r.y == this.y && r.width == this.width && r.height == this.height;
    }
    
    @Override
    public int hashCode() {
        return this.x ^ this.y ^ this.width ^ this.height;
    }
    
    public void intersect(final Rectangle rect) {
        if (rect == null) {
            SWT.error(4);
        }
        if (this == rect) {
            return;
        }
        final int left = (this.x > rect.x) ? this.x : rect.x;
        final int top = (this.y > rect.y) ? this.y : rect.y;
        int lhs = this.x + this.width;
        int rhs = rect.x + rect.width;
        final int right = (lhs < rhs) ? lhs : rhs;
        lhs = this.y + this.height;
        rhs = rect.y + rect.height;
        final int bottom = (lhs < rhs) ? lhs : rhs;
        this.x = ((right < left) ? 0 : left);
        this.y = ((bottom < top) ? 0 : top);
        this.width = ((right < left) ? 0 : (right - left));
        this.height = ((bottom < top) ? 0 : (bottom - top));
    }
    
    public Rectangle intersection(final Rectangle rect) {
        if (rect == null) {
            SWT.error(4);
        }
        if (this == rect) {
            return new Rectangle(this.x, this.y, this.width, this.height);
        }
        final int left = (this.x > rect.x) ? this.x : rect.x;
        final int top = (this.y > rect.y) ? this.y : rect.y;
        int lhs = this.x + this.width;
        int rhs = rect.x + rect.width;
        final int right = (lhs < rhs) ? lhs : rhs;
        lhs = this.y + this.height;
        rhs = rect.y + rect.height;
        final int bottom = (lhs < rhs) ? lhs : rhs;
        return new Rectangle((right < left) ? 0 : left, (bottom < top) ? 0 : top, (right < left) ? 0 : (right - left), (bottom < top) ? 0 : (bottom - top));
    }
    
    public boolean intersects(final int x, final int y, final int width, final int height) {
        return x < this.x + this.width && y < this.y + this.height && x + width > this.x && y + height > this.y;
    }
    
    public boolean intersects(final Rectangle rect) {
        if (rect == null) {
            SWT.error(4);
        }
        return rect == this || this.intersects(rect.x, rect.y, rect.width, rect.height);
    }
    
    public boolean isEmpty() {
        return this.width <= 0 || this.height <= 0;
    }
    
    @Override
    public String toString() {
        return "Rectangle {" + this.x + ", " + this.y + ", " + this.width + ", " + this.height;
    }
    
    public Rectangle union(final Rectangle rect) {
        if (rect == null) {
            SWT.error(4);
        }
        final int left = (this.x < rect.x) ? this.x : rect.x;
        final int top = (this.y < rect.y) ? this.y : rect.y;
        int lhs = this.x + this.width;
        int rhs = rect.x + rect.width;
        final int right = (lhs > rhs) ? lhs : rhs;
        lhs = this.y + this.height;
        rhs = rect.y + rect.height;
        final int bottom = (lhs > rhs) ? lhs : rhs;
        return new Rectangle(left, top, right - left, bottom - top);
    }
}
