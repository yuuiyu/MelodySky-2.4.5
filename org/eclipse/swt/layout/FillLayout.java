//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.layout;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;

public final class FillLayout extends Layout
{
    public int type;
    public int marginWidth;
    public int marginHeight;
    public int spacing;
    
    public FillLayout() {
        this.type = 256;
        this.marginWidth = 0;
        this.marginHeight = 0;
        this.spacing = 0;
    }
    
    public FillLayout(final int type) {
        this.type = 256;
        this.marginWidth = 0;
        this.marginHeight = 0;
        this.spacing = 0;
        this.type = type;
    }
    
    @Override
    protected Point computeSize(final Composite composite, final int wHint, final int hHint, final boolean flushCache) {
        final Control[] children = composite.getChildren();
        final int count = children.length;
        int maxWidth = 0;
        int maxHeight = 0;
        for (final Control child : children) {
            int w = wHint;
            int h = hHint;
            if (count > 0) {
                if (this.type == 256 && wHint != -1) {
                    w = Math.max(0, (wHint - (count - 1) * this.spacing) / count);
                }
                if (this.type == 512 && hHint != -1) {
                    h = Math.max(0, (hHint - (count - 1) * this.spacing) / count);
                }
            }
            final Point size = this.computeChildSize(child, w, h, flushCache);
            maxWidth = Math.max(maxWidth, size.x);
            maxHeight = Math.max(maxHeight, size.y);
        }
        int width = 0;
        int height = 0;
        if (this.type == 256) {
            width = count * maxWidth;
            if (count != 0) {
                width += (count - 1) * this.spacing;
            }
            height = maxHeight;
        }
        else {
            width = maxWidth;
            height = count * maxHeight;
            if (count != 0) {
                height += (count - 1) * this.spacing;
            }
        }
        width += this.marginWidth * 2;
        height += this.marginHeight * 2;
        if (wHint != -1) {
            width = wHint;
        }
        if (hHint != -1) {
            height = hHint;
        }
        return new Point(width, height);
    }
    
    Point computeChildSize(final Control control, final int wHint, final int hHint, final boolean flushCache) {
        final Object data = control.getLayoutData();
        FillData fillData;
        if (data instanceof FillData) {
            fillData = (FillData)data;
        }
        else {
            fillData = new FillData();
            if (data == null) {
                control.setLayoutData(fillData);
            }
        }
        Point size = null;
        if (wHint == -1 && hHint == -1) {
            size = fillData.computeSize(control, wHint, hHint, flushCache);
        }
        else {
            if (control instanceof Scrollable) {
                final Rectangle rect = ((Scrollable)control).computeTrim(0, 0, 0, 0);
                final int trimX = rect.width;
                final int trimY = rect.height;
            }
            else {
                final int trimY;
                final int trimX = trimY = control.getBorderWidth() * 2;
            }
            int trimX;
            final int w = (wHint == -1) ? wHint : Math.max(0, wHint - trimX);
            int trimY;
            final int h = (hHint == -1) ? hHint : Math.max(0, hHint - trimY);
            size = fillData.computeSize(control, w, h, flushCache);
        }
        return size;
    }
    
    @Override
    protected boolean flushCache(final Control control) {
        final Object data = control.getLayoutData();
        if (data instanceof FillData) {
            ((FillData)data).flushCache();
            return true;
        }
        return false;
    }
    
    String getName() {
        final String string = this.getClass().getName();
        final int index = string.lastIndexOf(46);
        if (index == -1) {
            return string;
        }
        return string.substring(index + 1, string.length());
    }
    
    @Override
    protected void layout(final Composite composite, final boolean flushCache) {
        final Rectangle rect = composite.getClientArea();
        final Control[] children = composite.getChildren();
        final int count = children.length;
        if (count == 0) {
            return;
        }
        int width = rect.width - this.marginWidth * 2;
        int height = rect.height - this.marginHeight * 2;
        if (this.type == 256) {
            width -= (count - 1) * this.spacing;
            int x = rect.x + this.marginWidth;
            final int extra = width % count;
            final int y = rect.y + this.marginHeight;
            final int cellWidth = width / count;
            for (int i = 0; i < count; ++i) {
                final Control child = children[i];
                int childWidth = cellWidth;
                if (i == 0) {
                    childWidth += extra / 2;
                }
                else if (i == count - 1) {
                    childWidth += (extra + 1) / 2;
                }
                child.setBounds(x, y, childWidth, height);
                x += childWidth + this.spacing;
            }
        }
        else {
            height -= (count - 1) * this.spacing;
            final int x = rect.x + this.marginWidth;
            final int cellHeight = height / count;
            int y = rect.y + this.marginHeight;
            final int extra2 = height % count;
            for (int i = 0; i < count; ++i) {
                final Control child = children[i];
                int childHeight = cellHeight;
                if (i == 0) {
                    childHeight += extra2 / 2;
                }
                else if (i == count - 1) {
                    childHeight += (extra2 + 1) / 2;
                }
                child.setBounds(x, y, width, childHeight);
                y += childHeight + this.spacing;
            }
        }
    }
    
    @Override
    public String toString() {
        String string = this.getName() + " {";
        string = string + "type=" + ((this.type == 512) ? "SWT.VERTICAL" : "SWT.HORIZONTAL");
        if (this.marginWidth != 0) {
            string = string + "marginWidth=" + this.marginWidth;
        }
        if (this.marginHeight != 0) {
            string = string + "marginHeight=" + this.marginHeight;
        }
        if (this.spacing != 0) {
            string = string + "spacing=" + this.spacing;
        }
        string = string.trim();
        return string;
    }
}
