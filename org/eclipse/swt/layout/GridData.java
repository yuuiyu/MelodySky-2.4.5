//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.layout;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;

public final class GridData
{
    public int verticalAlignment;
    public int horizontalAlignment;
    public int widthHint;
    public int heightHint;
    public int horizontalIndent;
    public int verticalIndent;
    public int horizontalSpan;
    public int verticalSpan;
    public boolean grabExcessHorizontalSpace;
    public boolean grabExcessVerticalSpace;
    public int minimumWidth;
    public int minimumHeight;
    public boolean exclude;
    public static final int BEGINNING = 1;
    public static final int CENTER = 2;
    public static final int END = 3;
    public static final int FILL = 4;
    public static final int VERTICAL_ALIGN_BEGINNING = 2;
    public static final int VERTICAL_ALIGN_CENTER = 4;
    public static final int VERTICAL_ALIGN_END = 8;
    public static final int VERTICAL_ALIGN_FILL = 16;
    public static final int HORIZONTAL_ALIGN_BEGINNING = 32;
    public static final int HORIZONTAL_ALIGN_CENTER = 64;
    public static final int HORIZONTAL_ALIGN_END = 128;
    public static final int HORIZONTAL_ALIGN_FILL = 256;
    public static final int GRAB_HORIZONTAL = 512;
    public static final int GRAB_VERTICAL = 1024;
    public static final int FILL_VERTICAL = 1040;
    public static final int FILL_HORIZONTAL = 768;
    public static final int FILL_BOTH = 1808;
    int cacheWidth;
    int cacheHeight;
    int defaultWhint;
    int defaultHhint;
    int defaultWidth;
    int defaultHeight;
    int currentWhint;
    int currentHhint;
    int currentWidth;
    int currentHeight;
    
    public GridData() {
        this.verticalAlignment = 2;
        this.horizontalAlignment = 1;
        this.widthHint = -1;
        this.heightHint = -1;
        this.horizontalIndent = 0;
        this.verticalIndent = 0;
        this.horizontalSpan = 1;
        this.verticalSpan = 1;
        this.grabExcessHorizontalSpace = false;
        this.grabExcessVerticalSpace = false;
        this.minimumWidth = 0;
        this.minimumHeight = 0;
        this.exclude = false;
        this.cacheWidth = -1;
        this.cacheHeight = -1;
        this.defaultWidth = -1;
        this.defaultHeight = -1;
        this.currentWidth = -1;
        this.currentHeight = -1;
    }
    
    public GridData(final int style) {
        this.verticalAlignment = 2;
        this.horizontalAlignment = 1;
        this.widthHint = -1;
        this.heightHint = -1;
        this.horizontalIndent = 0;
        this.verticalIndent = 0;
        this.horizontalSpan = 1;
        this.verticalSpan = 1;
        this.grabExcessHorizontalSpace = false;
        this.grabExcessVerticalSpace = false;
        this.minimumWidth = 0;
        this.minimumHeight = 0;
        this.exclude = false;
        this.cacheWidth = -1;
        this.cacheHeight = -1;
        this.defaultWidth = -1;
        this.defaultHeight = -1;
        this.currentWidth = -1;
        this.currentHeight = -1;
        if ((style & 0x2) != 0x0) {
            this.verticalAlignment = 1;
        }
        if ((style & 0x4) != 0x0) {
            this.verticalAlignment = 2;
        }
        if ((style & 0x10) != 0x0) {
            this.verticalAlignment = 4;
        }
        if ((style & 0x8) != 0x0) {
            this.verticalAlignment = 3;
        }
        if ((style & 0x20) != 0x0) {
            this.horizontalAlignment = 1;
        }
        if ((style & 0x40) != 0x0) {
            this.horizontalAlignment = 2;
        }
        if ((style & 0x100) != 0x0) {
            this.horizontalAlignment = 4;
        }
        if ((style & 0x80) != 0x0) {
            this.horizontalAlignment = 3;
        }
        this.grabExcessHorizontalSpace = ((style & 0x200) != 0x0);
        this.grabExcessVerticalSpace = ((style & 0x400) != 0x0);
    }
    
    public GridData(final int horizontalAlignment, final int verticalAlignment, final boolean grabExcessHorizontalSpace, final boolean grabExcessVerticalSpace) {
        this(horizontalAlignment, verticalAlignment, grabExcessHorizontalSpace, grabExcessVerticalSpace, 1, 1);
    }
    
    public GridData(final int horizontalAlignment, final int verticalAlignment, final boolean grabExcessHorizontalSpace, final boolean grabExcessVerticalSpace, final int horizontalSpan, final int verticalSpan) {
        this.verticalAlignment = 2;
        this.horizontalAlignment = 1;
        this.widthHint = -1;
        this.heightHint = -1;
        this.horizontalIndent = 0;
        this.verticalIndent = 0;
        this.horizontalSpan = 1;
        this.verticalSpan = 1;
        this.grabExcessHorizontalSpace = false;
        this.grabExcessVerticalSpace = false;
        this.minimumWidth = 0;
        this.minimumHeight = 0;
        this.exclude = false;
        this.cacheWidth = -1;
        this.cacheHeight = -1;
        this.defaultWidth = -1;
        this.defaultHeight = -1;
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.horizontalAlignment = horizontalAlignment;
        this.verticalAlignment = verticalAlignment;
        this.grabExcessHorizontalSpace = grabExcessHorizontalSpace;
        this.grabExcessVerticalSpace = grabExcessVerticalSpace;
        this.horizontalSpan = horizontalSpan;
        this.verticalSpan = verticalSpan;
    }
    
    public GridData(final int width, final int height) {
        this.verticalAlignment = 2;
        this.horizontalAlignment = 1;
        this.widthHint = -1;
        this.heightHint = -1;
        this.horizontalIndent = 0;
        this.verticalIndent = 0;
        this.horizontalSpan = 1;
        this.verticalSpan = 1;
        this.grabExcessHorizontalSpace = false;
        this.grabExcessVerticalSpace = false;
        this.minimumWidth = 0;
        this.minimumHeight = 0;
        this.exclude = false;
        this.cacheWidth = -1;
        this.cacheHeight = -1;
        this.defaultWidth = -1;
        this.defaultHeight = -1;
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.widthHint = width;
        this.heightHint = height;
    }
    
    void computeSize(final Control control, final int wHint, final int hHint, final boolean flushCache) {
        if (this.cacheWidth != -1 && this.cacheHeight != -1) {
            return;
        }
        if (wHint == this.widthHint && hHint == this.heightHint) {
            if (this.defaultWidth == -1 || this.defaultHeight == -1 || wHint != this.defaultWhint || hHint != this.defaultHhint) {
                final Point size = control.computeSize(wHint, hHint, flushCache);
                this.defaultWhint = wHint;
                this.defaultHhint = hHint;
                this.defaultWidth = size.x;
                this.defaultHeight = size.y;
            }
            this.cacheWidth = this.defaultWidth;
            this.cacheHeight = this.defaultHeight;
            return;
        }
        if (this.currentWidth == -1 || this.currentHeight == -1 || wHint != this.currentWhint || hHint != this.currentHhint) {
            final Point size = control.computeSize(wHint, hHint, flushCache);
            this.currentWhint = wHint;
            this.currentHhint = hHint;
            this.currentWidth = size.x;
            this.currentHeight = size.y;
        }
        this.cacheWidth = this.currentWidth;
        this.cacheHeight = this.currentHeight;
    }
    
    void flushCache() {
        this.cacheHeight = -1;
        this.cacheWidth = -1;
        this.defaultHeight = -1;
        this.defaultWidth = -1;
        this.currentHeight = -1;
        this.currentWidth = -1;
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
    public String toString() {
        String hAlign = "";
        switch (this.horizontalAlignment) {
            case 4: {
                hAlign = "SWT.FILL";
                break;
            }
            case 1: {
                hAlign = "SWT.BEGINNING";
                break;
            }
            case 16384: {
                hAlign = "SWT.LEFT";
                break;
            }
            case 16777224: {
                hAlign = "SWT.END";
                break;
            }
            case 3: {
                hAlign = "GridData.END";
                break;
            }
            case 131072: {
                hAlign = "SWT.RIGHT";
                break;
            }
            case 16777216: {
                hAlign = "SWT.CENTER";
                break;
            }
            case 2: {
                hAlign = "GridData.CENTER";
                break;
            }
            default: {
                hAlign = "Undefined " + this.horizontalAlignment;
                break;
            }
        }
        String vAlign = "";
        switch (this.verticalAlignment) {
            case 4: {
                vAlign = "SWT.FILL";
                break;
            }
            case 1: {
                vAlign = "SWT.BEGINNING";
                break;
            }
            case 128: {
                vAlign = "SWT.TOP";
                break;
            }
            case 16777224: {
                vAlign = "SWT.END";
                break;
            }
            case 3: {
                vAlign = "GridData.END";
                break;
            }
            case 1024: {
                vAlign = "SWT.BOTTOM";
                break;
            }
            case 16777216: {
                vAlign = "SWT.CENTER";
                break;
            }
            case 2: {
                vAlign = "GridData.CENTER";
                break;
            }
            default: {
                vAlign = "Undefined " + this.verticalAlignment;
                break;
            }
        }
        String string = this.getName() + " {";
        string = string + "horizontalAlignment=" + hAlign + " ";
        if (this.horizontalIndent != 0) {
            string = string + "horizontalIndent=" + this.horizontalIndent + " ";
        }
        if (this.horizontalSpan != 1) {
            string = string + "horizontalSpan=" + this.horizontalSpan + " ";
        }
        if (this.grabExcessHorizontalSpace) {
            string = string + "grabExcessHorizontalSpace=" + this.grabExcessHorizontalSpace + " ";
        }
        if (this.widthHint != -1) {
            string = string + "widthHint=" + this.widthHint + " ";
        }
        if (this.minimumWidth != 0) {
            string = string + "minimumWidth=" + this.minimumWidth + " ";
        }
        string = string + "verticalAlignment=" + vAlign + " ";
        if (this.verticalIndent != 0) {
            string = string + "verticalIndent=" + this.verticalIndent + " ";
        }
        if (this.verticalSpan != 1) {
            string = string + "verticalSpan=" + this.verticalSpan + " ";
        }
        if (this.grabExcessVerticalSpace) {
            string = string + "grabExcessVerticalSpace=" + this.grabExcessVerticalSpace + " ";
        }
        if (this.heightHint != -1) {
            string = string + "heightHint=" + this.heightHint + " ";
        }
        if (this.minimumHeight != 0) {
            string = string + "minimumHeight=" + this.minimumHeight + " ";
        }
        if (this.exclude) {
            string = string + "exclude=" + this.exclude + " ";
        }
        string = string.trim();
        string += "}";
        return string;
    }
}
