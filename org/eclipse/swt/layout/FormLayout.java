//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.layout;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public final class FormLayout extends Layout
{
    public int marginWidth;
    public int marginHeight;
    public int marginLeft;
    public int marginTop;
    public int marginRight;
    public int marginBottom;
    public int spacing;
    
    public FormLayout() {
        this.marginWidth = 0;
        this.marginHeight = 0;
        this.marginLeft = 0;
        this.marginTop = 0;
        this.marginRight = 0;
        this.marginBottom = 0;
        this.spacing = 0;
    }
    
    int computeHeight(final Control control, final FormData data, final boolean flushCache) {
        final FormAttachment top = data.getTopAttachment(control, this.spacing, flushCache);
        final FormAttachment bottom = data.getBottomAttachment(control, this.spacing, flushCache);
        final FormAttachment height = bottom.minus(top);
        if (height.numerator != 0) {
            return height.solveY(data.getHeight(control, flushCache));
        }
        if (bottom.numerator == 0) {
            return bottom.offset;
        }
        if (bottom.numerator == bottom.denominator) {
            return -top.offset;
        }
        if (bottom.offset <= 0) {
            return -top.offset * top.denominator / bottom.numerator;
        }
        final int divider = bottom.denominator - bottom.numerator;
        return bottom.denominator * bottom.offset / divider;
    }
    
    @Override
    protected Point computeSize(final Composite composite, final int wHint, final int hHint, final boolean flushCache) {
        final Point size = this.layout(composite, false, 0, 0, wHint, hHint, flushCache);
        if (wHint != -1) {
            size.x = wHint;
        }
        if (hHint != -1) {
            size.y = hHint;
        }
        return size;
    }
    
    @Override
    protected boolean flushCache(final Control control) {
        final Object data = control.getLayoutData();
        if (data != null) {
            ((FormData)data).flushCache();
        }
        return true;
    }
    
    String getName() {
        final String string = this.getClass().getName();
        final int index = string.lastIndexOf(46);
        if (index == -1) {
            return string;
        }
        return string.substring(index + 1, string.length());
    }
    
    int computeWidth(final Control control, final FormData data, final boolean flushCache) {
        final FormAttachment left = data.getLeftAttachment(control, this.spacing, flushCache);
        final FormAttachment right = data.getRightAttachment(control, this.spacing, flushCache);
        final FormAttachment width = right.minus(left);
        if (width.numerator != 0) {
            return width.solveY(data.getWidth(control, flushCache));
        }
        if (right.numerator == 0) {
            return right.offset;
        }
        if (right.numerator == right.denominator) {
            return -left.offset;
        }
        if (right.offset <= 0) {
            return -left.offset * left.denominator / left.numerator;
        }
        final int divider = right.denominator - right.numerator;
        return right.denominator * right.offset / divider;
    }
    
    @Override
    protected void layout(final Composite composite, final boolean flushCache) {
        final Rectangle rect = composite.getClientArea();
        final int x = rect.x + this.marginLeft + this.marginWidth;
        final int y = rect.y + this.marginTop + this.marginHeight;
        final int width = Math.max(0, rect.width - this.marginLeft - 2 * this.marginWidth - this.marginRight);
        final int height = Math.max(0, rect.height - this.marginTop - 2 * this.marginHeight - this.marginBottom);
        this.layout(composite, true, x, y, width, height, flushCache);
    }
    
    Point layout(final Composite composite, final boolean move, final int x, final int y, final int width, final int height, final boolean flushCache) {
        final Control[] children2;
        final Control[] children = children2 = composite.getChildren();
        for (final Control child2 : children2) {
            FormData data2 = (FormData)child2.getLayoutData();
            if (data2 == null) {
                data2 = new FormData();
                child2.setLayoutData(data2);
            }
            if (flushCache) {
                data2.flushCache();
            }
            data2.cacheBottom = null;
            data2.cacheTop = null;
            data2.cacheRight = null;
            data2.cacheLeft = null;
        }
        boolean[] flush = null;
        Rectangle[] bounds = null;
        int w = 0;
        int h = 0;
        for (int i = 0; i < children.length; ++i) {
            final Control child3 = children[i];
            final FormData data3 = (FormData)child3.getLayoutData();
            if (width != -1) {
                data3.needed = false;
                final FormAttachment left = data3.getLeftAttachment(child3, this.spacing, flushCache);
                final FormAttachment right = data3.getRightAttachment(child3, this.spacing, flushCache);
                final int x2 = left.solveX(width);
                final int x3 = right.solveX(width);
                if (data3.height == -1 && !data3.needed) {
                    int trim = 0;
                    if (child3 instanceof Scrollable) {
                        final Rectangle rect = ((Scrollable)child3).computeTrim(0, 0, 0, 0);
                        trim = rect.width;
                    }
                    else {
                        trim = child3.getBorderWidth() * 2;
                    }
                    data3.cacheHeight = -1;
                    data3.cacheWidth = -1;
                    final int currentWidth = Math.max(0, x3 - x2 - trim);
                    data3.computeSize(child3, currentWidth, data3.height, flushCache);
                    if (flush == null) {
                        flush = new boolean[children.length];
                    }
                    flush[i] = true;
                }
                w = Math.max(x3, w);
                if (move) {
                    if (bounds == null) {
                        bounds = new Rectangle[children.length];
                    }
                    bounds[i] = new Rectangle(0, 0, 0, 0);
                    bounds[i].x = x + x2;
                    bounds[i].width = x3 - x2;
                }
            }
            else {
                w = Math.max(this.computeWidth(child3, data3, flushCache), w);
            }
        }
        for (int i = 0; i < children.length; ++i) {
            final Control child3 = children[i];
            final FormData data3 = (FormData)child3.getLayoutData();
            if (height != -1) {
                final int y2 = data3.getTopAttachment(child3, this.spacing, flushCache).solveX(height);
                final int y3 = data3.getBottomAttachment(child3, this.spacing, flushCache).solveX(height);
                h = Math.max(y3, h);
                if (move) {
                    bounds[i].y = y + y2;
                    bounds[i].height = y3 - y2;
                }
            }
            else {
                h = Math.max(this.computeHeight(child3, data3, flushCache), h);
            }
        }
        for (int i = 0; i < children.length; ++i) {
            final Control child3 = children[i];
            final FormData data3 = (FormData)child3.getLayoutData();
            if (flush != null && flush[i]) {
                data3.cacheHeight = -1;
                data3.cacheWidth = -1;
            }
            data3.cacheBottom = null;
            data3.cacheTop = null;
            data3.cacheRight = null;
            data3.cacheLeft = null;
        }
        if (move) {
            for (int i = 0; i < children.length; ++i) {
                children[i].setBounds(bounds[i]);
            }
        }
        w += this.marginLeft + this.marginWidth * 2 + this.marginRight;
        return new Point(w, h += this.marginTop + this.marginHeight * 2 + this.marginBottom);
    }
    
    @Override
    public String toString() {
        String string = this.getName() + " {";
        if (this.marginWidth != 0) {
            string = string + "marginWidth=" + this.marginWidth + " ";
        }
        if (this.marginHeight != 0) {
            string = string + "marginHeight=" + this.marginHeight + " ";
        }
        if (this.marginLeft != 0) {
            string = string + "marginLeft=" + this.marginLeft + " ";
        }
        if (this.marginRight != 0) {
            string = string + "marginRight=" + this.marginRight + " ";
        }
        if (this.marginTop != 0) {
            string = string + "marginTop=" + this.marginTop + " ";
        }
        if (this.marginBottom != 0) {
            string = string + "marginBottom=" + this.marginBottom + " ";
        }
        if (this.spacing != 0) {
            string = string + "spacing=" + this.spacing + " ";
        }
        string = string.trim();
        string += "}";
        return string;
    }
}
