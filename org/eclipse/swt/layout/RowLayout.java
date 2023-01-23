//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.layout;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;

public final class RowLayout extends Layout
{
    public int type;
    public int marginWidth;
    public int marginHeight;
    public int spacing;
    public boolean wrap;
    public boolean pack;
    public boolean fill;
    public boolean center;
    public boolean justify;
    public int marginLeft;
    public int marginTop;
    public int marginRight;
    public int marginBottom;
    
    public RowLayout() {
        this.type = 256;
        this.marginWidth = 0;
        this.marginHeight = 0;
        this.spacing = 3;
        this.wrap = true;
        this.pack = true;
        this.fill = false;
        this.center = false;
        this.justify = false;
        this.marginLeft = 3;
        this.marginTop = 3;
        this.marginRight = 3;
        this.marginBottom = 3;
    }
    
    public RowLayout(final int type) {
        this.type = 256;
        this.marginWidth = 0;
        this.marginHeight = 0;
        this.spacing = 3;
        this.wrap = true;
        this.pack = true;
        this.fill = false;
        this.center = false;
        this.justify = false;
        this.marginLeft = 3;
        this.marginTop = 3;
        this.marginRight = 3;
        this.marginBottom = 3;
        this.type = type;
    }
    
    @Override
    protected Point computeSize(final Composite composite, final int wHint, final int hHint, final boolean flushCache) {
        final Point extent = (this.type == 256) ? this.layoutHorizontal(composite, false, wHint != -1 && this.wrap, wHint, flushCache) : this.layoutVertical(composite, false, hHint != -1 && this.wrap, hHint, flushCache);
        if (wHint != -1) {
            extent.x = wHint;
        }
        if (hHint != -1) {
            extent.y = hHint;
        }
        return extent;
    }
    
    Point computeSize(final Control control, final boolean flushCache) {
        int wHint = -1;
        int hHint = -1;
        final RowData data = (RowData)control.getLayoutData();
        if (data != null) {
            wHint = data.width;
            hHint = data.height;
        }
        return control.computeSize(wHint, hHint, flushCache);
    }
    
    @Override
    protected boolean flushCache(final Control control) {
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
    
    @Override
    protected void layout(final Composite composite, final boolean flushCache) {
        final Rectangle clientArea = composite.getClientArea();
        if (this.type == 256) {
            this.layoutHorizontal(composite, true, this.wrap, clientArea.width, flushCache);
        }
        else {
            this.layoutVertical(composite, true, this.wrap, clientArea.height, flushCache);
        }
    }
    
    Point layoutHorizontal(final Composite composite, final boolean move, final boolean wrap, final int width, final boolean flushCache) {
        final Control[] children = composite.getChildren();
        int count = 0;
        for (int i = 0; i < children.length; ++i) {
            final Control control = children[i];
            final RowData data = (RowData)control.getLayoutData();
            if (data == null || !data.exclude) {
                children[count++] = children[i];
            }
        }
        if (count == 0) {
            return new Point(this.marginLeft + this.marginWidth * 2 + this.marginRight, this.marginTop + this.marginHeight * 2 + this.marginBottom);
        }
        int childWidth = 0;
        int childHeight = 0;
        int maxHeight = 0;
        if (!this.pack) {
            for (final Control child : children) {
                Point size = this.computeSize(child, flushCache);
                if (width > -1 && width < size.x && wrap) {
                    size = child.computeSize(width, (child.getLayoutData() == null) ? -1 : ((RowData)child.getLayoutData()).height, flushCache);
                }
                childWidth = Math.max(childWidth, size.x);
                childHeight = Math.max(childHeight, size.y);
            }
            maxHeight = childHeight;
        }
        int clientX = 0;
        int clientY = 0;
        if (move) {
            final Rectangle rect = composite.getClientArea();
            clientX = rect.x;
            clientY = rect.y;
        }
        int[] wraps = null;
        boolean wrapped = false;
        Rectangle[] bounds = null;
        if (move && (this.justify || this.fill || this.center)) {
            bounds = new Rectangle[count];
            wraps = new int[count];
        }
        int maxX = 0;
        int x = this.marginLeft + this.marginWidth;
        int y = this.marginTop + this.marginHeight;
        for (int k = 0; k < count; ++k) {
            final Control child2 = children[k];
            if (this.pack) {
                Point size2 = this.computeSize(child2, flushCache);
                if (width > -1 && width < size2.x && wrap) {
                    size2 = child2.computeSize(width, (child2.getLayoutData() == null) ? -1 : ((RowData)child2.getLayoutData()).height, flushCache);
                }
                childWidth = size2.x;
                childHeight = size2.y;
            }
            if (wrap && k != 0 && x + childWidth > width) {
                wrapped = true;
                if (move && (this.justify || this.fill || this.center)) {
                    wraps[k - 1] = maxHeight;
                }
                x = this.marginLeft + this.marginWidth;
                y += this.spacing + maxHeight;
                if (this.pack) {
                    maxHeight = 0;
                }
            }
            if (this.pack || this.fill || this.center) {
                maxHeight = Math.max(maxHeight, childHeight);
            }
            if (move) {
                final int childX = x + clientX;
                final int childY = y + clientY;
                if (this.justify || this.fill || this.center) {
                    bounds[k] = new Rectangle(childX, childY, childWidth, childHeight);
                }
                else {
                    child2.setBounds(childX, childY, childWidth, childHeight);
                }
            }
            maxX = Math.max(maxX, x += this.spacing + childWidth);
        }
        maxX = Math.max(clientX + this.marginLeft + this.marginWidth, maxX - this.spacing);
        if (!wrapped) {
            maxX += this.marginRight + this.marginWidth;
        }
        if (move && (this.justify || this.fill || this.center)) {
            int space = 0;
            int margin = 0;
            if (!wrapped) {
                space = Math.max(0, (width - maxX) / (count + 1));
                margin = Math.max(0, (width - maxX) % (count + 1) / 2);
            }
            else if (this.fill || this.justify || this.center) {
                int last = 0;
                if (count > 0) {
                    wraps[count - 1] = maxHeight;
                }
                for (int l = 0; l < count; ++l) {
                    if (wraps[l] != 0) {
                        final int wrapCount = l - last + 1;
                        if (this.justify) {
                            int wrapX = 0;
                            for (int m = last; m <= l; ++m) {
                                wrapX += bounds[m].width + this.spacing;
                            }
                            space = Math.max(0, (width - wrapX) / (wrapCount + 1));
                            margin = Math.max(0, (width - wrapX) % (wrapCount + 1) / 2);
                        }
                        for (int j2 = last; j2 <= l; ++j2) {
                            if (this.justify) {
                                final Rectangle rectangle = bounds[j2];
                                rectangle.x += space * (j2 - last + 1) + margin;
                            }
                            if (this.fill) {
                                bounds[j2].height = wraps[l];
                            }
                            else if (this.center) {
                                final Rectangle rectangle2 = bounds[j2];
                                rectangle2.y += Math.max(0, (wraps[l] - bounds[j2].height) / 2);
                            }
                        }
                        last = l + 1;
                    }
                }
            }
            for (int i2 = 0; i2 < count; ++i2) {
                if (!wrapped) {
                    if (this.justify) {
                        final Rectangle rectangle3 = bounds[i2];
                        rectangle3.x += space * (i2 + 1) + margin;
                    }
                    if (this.fill) {
                        bounds[i2].height = maxHeight;
                    }
                    else if (this.center) {
                        final Rectangle rectangle4 = bounds[i2];
                        rectangle4.y += Math.max(0, (maxHeight - bounds[i2].height) / 2);
                    }
                }
                children[i2].setBounds(bounds[i2]);
            }
        }
        return new Point(maxX, y + maxHeight + this.marginBottom + this.marginHeight);
    }
    
    Point layoutVertical(final Composite composite, final boolean move, final boolean wrap, final int height, final boolean flushCache) {
        final Control[] children = composite.getChildren();
        int count = 0;
        for (int i = 0; i < children.length; ++i) {
            final Control control = children[i];
            final RowData data = (RowData)control.getLayoutData();
            if (data == null || !data.exclude) {
                children[count++] = children[i];
            }
        }
        if (count == 0) {
            return new Point(this.marginLeft + this.marginWidth * 2 + this.marginRight, this.marginTop + this.marginHeight * 2 + this.marginBottom);
        }
        int childWidth = 0;
        int childHeight = 0;
        int maxWidth = 0;
        if (!this.pack) {
            for (final Control child : children) {
                Point size = this.computeSize(child, flushCache);
                if (height > -1 && height < size.y && wrap) {
                    size = child.computeSize((child.getLayoutData() == null) ? -1 : ((RowData)child.getLayoutData()).width, height, flushCache);
                }
                childWidth = Math.max(childWidth, size.x);
                childHeight = Math.max(childHeight, size.y);
            }
            maxWidth = childWidth;
        }
        int clientX = 0;
        int clientY = 0;
        if (move) {
            final Rectangle rect = composite.getClientArea();
            clientX = rect.x;
            clientY = rect.y;
        }
        int[] wraps = null;
        boolean wrapped = false;
        Rectangle[] bounds = null;
        if (move && (this.justify || this.fill || this.center)) {
            bounds = new Rectangle[count];
            wraps = new int[count];
        }
        int maxY = 0;
        int x = this.marginLeft + this.marginWidth;
        int y = this.marginTop + this.marginHeight;
        for (int k = 0; k < count; ++k) {
            final Control child2 = children[k];
            if (this.pack) {
                Point size2 = this.computeSize(child2, flushCache);
                if (height > -1 && height < size2.y && wrap) {
                    size2 = child2.computeSize((child2.getLayoutData() == null) ? -1 : ((RowData)child2.getLayoutData()).width, height, flushCache);
                }
                childWidth = size2.x;
                childHeight = size2.y;
            }
            if (wrap && k != 0 && y + childHeight > height) {
                wrapped = true;
                if (move && (this.justify || this.fill || this.center)) {
                    wraps[k - 1] = maxWidth;
                }
                x += this.spacing + maxWidth;
                y = this.marginTop + this.marginHeight;
                if (this.pack) {
                    maxWidth = 0;
                }
            }
            if (this.pack || this.fill || this.center) {
                maxWidth = Math.max(maxWidth, childWidth);
            }
            if (move) {
                final int childX = x + clientX;
                final int childY = y + clientY;
                if (this.justify || this.fill || this.center) {
                    bounds[k] = new Rectangle(childX, childY, childWidth, childHeight);
                }
                else {
                    child2.setBounds(childX, childY, childWidth, childHeight);
                }
            }
            maxY = Math.max(maxY, y += this.spacing + childHeight);
        }
        maxY = Math.max(clientY + this.marginTop + this.marginHeight, maxY - this.spacing);
        if (!wrapped) {
            maxY += this.marginBottom + this.marginHeight;
        }
        if (move && (this.justify || this.fill || this.center)) {
            int space = 0;
            int margin = 0;
            if (!wrapped) {
                space = Math.max(0, (height - maxY) / (count + 1));
                margin = Math.max(0, (height - maxY) % (count + 1) / 2);
            }
            else if (this.fill || this.justify || this.center) {
                int last = 0;
                if (count > 0) {
                    wraps[count - 1] = maxWidth;
                }
                for (int l = 0; l < count; ++l) {
                    if (wraps[l] != 0) {
                        final int wrapCount = l - last + 1;
                        if (this.justify) {
                            int wrapY = 0;
                            for (int m = last; m <= l; ++m) {
                                wrapY += bounds[m].height + this.spacing;
                            }
                            space = Math.max(0, (height - wrapY) / (wrapCount + 1));
                            margin = Math.max(0, (height - wrapY) % (wrapCount + 1) / 2);
                        }
                        for (int j2 = last; j2 <= l; ++j2) {
                            if (this.justify) {
                                final Rectangle rectangle = bounds[j2];
                                rectangle.y += space * (j2 - last + 1) + margin;
                            }
                            if (this.fill) {
                                bounds[j2].width = wraps[l];
                            }
                            else if (this.center) {
                                final Rectangle rectangle2 = bounds[j2];
                                rectangle2.x += Math.max(0, (wraps[l] - bounds[j2].width) / 2);
                            }
                        }
                        last = l + 1;
                    }
                }
            }
            for (int i2 = 0; i2 < count; ++i2) {
                if (!wrapped) {
                    if (this.justify) {
                        final Rectangle rectangle3 = bounds[i2];
                        rectangle3.y += space * (i2 + 1) + margin;
                    }
                    if (this.fill) {
                        bounds[i2].width = maxWidth;
                    }
                    else if (this.center) {
                        final Rectangle rectangle4 = bounds[i2];
                        rectangle4.x += Math.max(0, (maxWidth - bounds[i2].width) / 2);
                    }
                }
                children[i2].setBounds(bounds[i2]);
            }
        }
        return new Point(x + maxWidth + this.marginRight + this.marginWidth, maxY);
    }
    
    @Override
    public String toString() {
        String string = this.getName() + " {";
        string = string + "type=" + ((this.type != 256) ? "SWT.VERTICAL" : "SWT.HORIZONTAL") + " ";
        if (this.marginWidth != 0) {
            string = string + "marginWidth=" + this.marginWidth + " ";
        }
        if (this.marginHeight != 0) {
            string = string + "marginHeight=" + this.marginHeight + " ";
        }
        if (this.marginLeft != 0) {
            string = string + "marginLeft=" + this.marginLeft + " ";
        }
        if (this.marginTop != 0) {
            string = string + "marginTop=" + this.marginTop + " ";
        }
        if (this.marginRight != 0) {
            string = string + "marginRight=" + this.marginRight + " ";
        }
        if (this.marginBottom != 0) {
            string = string + "marginBottom=" + this.marginBottom + " ";
        }
        if (this.spacing != 0) {
            string = string + "spacing=" + this.spacing + " ";
        }
        string = string + "wrap=" + this.wrap + " ";
        string = string + "pack=" + this.pack + " ";
        string = string + "fill=" + this.fill + " ";
        string = string + "justify=" + this.justify + " ";
        string = string.trim();
        string += "}";
        return string;
    }
}
