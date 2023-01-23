//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.layout;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;

public final class FormData
{
    public int width;
    public int height;
    public FormAttachment left;
    public FormAttachment right;
    public FormAttachment top;
    public FormAttachment bottom;
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
    FormAttachment cacheLeft;
    FormAttachment cacheRight;
    FormAttachment cacheTop;
    FormAttachment cacheBottom;
    boolean isVisited;
    boolean needed;
    
    public FormData() {
        this.width = -1;
        this.height = -1;
        this.cacheWidth = -1;
        this.cacheHeight = -1;
        this.defaultWidth = -1;
        this.defaultHeight = -1;
        this.currentWidth = -1;
        this.currentHeight = -1;
    }
    
    public FormData(final int width, final int height) {
        this.width = -1;
        this.height = -1;
        this.cacheWidth = -1;
        this.cacheHeight = -1;
        this.defaultWidth = -1;
        this.defaultHeight = -1;
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.width = width;
        this.height = height;
    }
    
    void computeSize(final Control control, final int wHint, final int hHint, final boolean flushCache) {
        if (this.cacheWidth != -1 && this.cacheHeight != -1) {
            return;
        }
        if (wHint == this.width && hHint == this.height) {
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
        this.defaultWidth = -1;
        this.defaultHeight = -1;
        this.currentWidth = -1;
        this.currentHeight = -1;
    }
    
    int getWidth(final Control control, final boolean flushCache) {
        this.needed = true;
        this.computeSize(control, this.width, this.height, flushCache);
        return this.cacheWidth;
    }
    
    int getHeight(final Control control, final boolean flushCache) {
        this.computeSize(control, this.width, this.height, flushCache);
        return this.cacheHeight;
    }
    
    FormAttachment getBottomAttachment(final Control control, final int spacing, final boolean flushCache) {
        if (this.cacheBottom != null) {
            return this.cacheBottom;
        }
        if (this.isVisited) {
            return this.cacheBottom = new FormAttachment(0, this.getHeight(control, flushCache));
        }
        if (this.bottom == null) {
            if (this.top == null) {
                return this.cacheBottom = new FormAttachment(0, this.getHeight(control, flushCache));
            }
            return this.cacheBottom = this.getTopAttachment(control, spacing, flushCache).plus(this.getHeight(control, flushCache));
        }
        else {
            Control bottomControl = this.bottom.control;
            if (bottomControl != null) {
                if (bottomControl.isDisposed()) {
                    bottomControl = null;
                    this.bottom.control = null;
                }
                else if (bottomControl.getParent() != control.getParent()) {
                    bottomControl = null;
                }
            }
            if (bottomControl == null) {
                return this.cacheBottom = this.bottom;
            }
            this.isVisited = true;
            final FormData bottomData = (FormData)bottomControl.getLayoutData();
            final FormAttachment bottomAttachment = bottomData.getBottomAttachment(bottomControl, spacing, flushCache);
            switch (this.bottom.alignment) {
                case 1024: {
                    this.cacheBottom = bottomAttachment.plus(this.bottom.offset);
                    break;
                }
                case 16777216: {
                    final FormAttachment topAttachment = bottomData.getTopAttachment(bottomControl, spacing, flushCache);
                    final FormAttachment bottomHeight = bottomAttachment.minus(topAttachment);
                    this.cacheBottom = bottomAttachment.minus(bottomHeight.minus(this.getHeight(control, flushCache)).divide(2));
                    break;
                }
                default: {
                    final FormAttachment topAttachment = bottomData.getTopAttachment(bottomControl, spacing, flushCache);
                    this.cacheBottom = topAttachment.plus(this.bottom.offset - spacing);
                    break;
                }
            }
            this.isVisited = false;
            return this.cacheBottom;
        }
    }
    
    FormAttachment getLeftAttachment(final Control control, final int spacing, final boolean flushCache) {
        if (this.cacheLeft != null) {
            return this.cacheLeft;
        }
        if (this.isVisited) {
            return this.cacheLeft = new FormAttachment(0, 0);
        }
        if (this.left == null) {
            if (this.right == null) {
                return this.cacheLeft = new FormAttachment(0, 0);
            }
            return this.cacheLeft = this.getRightAttachment(control, spacing, flushCache).minus(this.getWidth(control, flushCache));
        }
        else {
            Control leftControl = this.left.control;
            if (leftControl != null) {
                if (leftControl.isDisposed()) {
                    leftControl = null;
                    this.left.control = null;
                }
                else if (leftControl.getParent() != control.getParent()) {
                    leftControl = null;
                }
            }
            if (leftControl == null) {
                return this.cacheLeft = this.left;
            }
            this.isVisited = true;
            final FormData leftData = (FormData)leftControl.getLayoutData();
            final FormAttachment leftAttachment = leftData.getLeftAttachment(leftControl, spacing, flushCache);
            switch (this.left.alignment) {
                case 16384: {
                    this.cacheLeft = leftAttachment.plus(this.left.offset);
                    break;
                }
                case 16777216: {
                    final FormAttachment rightAttachment = leftData.getRightAttachment(leftControl, spacing, flushCache);
                    final FormAttachment leftWidth = rightAttachment.minus(leftAttachment);
                    this.cacheLeft = leftAttachment.plus(leftWidth.minus(this.getWidth(control, flushCache)).divide(2));
                    break;
                }
                default: {
                    final FormAttachment rightAttachment = leftData.getRightAttachment(leftControl, spacing, flushCache);
                    this.cacheLeft = rightAttachment.plus(this.left.offset + spacing);
                    break;
                }
            }
            this.isVisited = false;
            return this.cacheLeft;
        }
    }
    
    String getName() {
        final String string = this.getClass().getName();
        final int index = string.lastIndexOf(46);
        if (index == -1) {
            return string;
        }
        return string.substring(index + 1, string.length());
    }
    
    FormAttachment getRightAttachment(final Control control, final int spacing, final boolean flushCache) {
        if (this.cacheRight != null) {
            return this.cacheRight;
        }
        if (this.isVisited) {
            return this.cacheRight = new FormAttachment(0, this.getWidth(control, flushCache));
        }
        if (this.right == null) {
            if (this.left == null) {
                return this.cacheRight = new FormAttachment(0, this.getWidth(control, flushCache));
            }
            return this.cacheRight = this.getLeftAttachment(control, spacing, flushCache).plus(this.getWidth(control, flushCache));
        }
        else {
            Control rightControl = this.right.control;
            if (rightControl != null) {
                if (rightControl.isDisposed()) {
                    rightControl = null;
                    this.right.control = null;
                }
                else if (rightControl.getParent() != control.getParent()) {
                    rightControl = null;
                }
            }
            if (rightControl == null) {
                return this.cacheRight = this.right;
            }
            this.isVisited = true;
            final FormData rightData = (FormData)rightControl.getLayoutData();
            final FormAttachment rightAttachment = rightData.getRightAttachment(rightControl, spacing, flushCache);
            switch (this.right.alignment) {
                case 131072: {
                    this.cacheRight = rightAttachment.plus(this.right.offset);
                    break;
                }
                case 16777216: {
                    final FormAttachment leftAttachment = rightData.getLeftAttachment(rightControl, spacing, flushCache);
                    final FormAttachment rightWidth = rightAttachment.minus(leftAttachment);
                    this.cacheRight = rightAttachment.minus(rightWidth.minus(this.getWidth(control, flushCache)).divide(2));
                    break;
                }
                default: {
                    final FormAttachment leftAttachment = rightData.getLeftAttachment(rightControl, spacing, flushCache);
                    this.cacheRight = leftAttachment.plus(this.right.offset - spacing);
                    break;
                }
            }
            this.isVisited = false;
            return this.cacheRight;
        }
    }
    
    FormAttachment getTopAttachment(final Control control, final int spacing, final boolean flushCache) {
        if (this.cacheTop != null) {
            return this.cacheTop;
        }
        if (this.isVisited) {
            return this.cacheTop = new FormAttachment(0, 0);
        }
        if (this.top == null) {
            if (this.bottom == null) {
                return this.cacheTop = new FormAttachment(0, 0);
            }
            return this.cacheTop = this.getBottomAttachment(control, spacing, flushCache).minus(this.getHeight(control, flushCache));
        }
        else {
            Control topControl = this.top.control;
            if (topControl != null) {
                if (topControl.isDisposed()) {
                    topControl = null;
                    this.top.control = null;
                }
                else if (topControl.getParent() != control.getParent()) {
                    topControl = null;
                }
            }
            if (topControl == null) {
                return this.cacheTop = this.top;
            }
            this.isVisited = true;
            final FormData topData = (FormData)topControl.getLayoutData();
            final FormAttachment topAttachment = topData.getTopAttachment(topControl, spacing, flushCache);
            switch (this.top.alignment) {
                case 128: {
                    this.cacheTop = topAttachment.plus(this.top.offset);
                    break;
                }
                case 16777216: {
                    final FormAttachment bottomAttachment = topData.getBottomAttachment(topControl, spacing, flushCache);
                    final FormAttachment topHeight = bottomAttachment.minus(topAttachment);
                    this.cacheTop = topAttachment.plus(topHeight.minus(this.getHeight(control, flushCache)).divide(2));
                    break;
                }
                default: {
                    final FormAttachment bottomAttachment = topData.getBottomAttachment(topControl, spacing, flushCache);
                    this.cacheTop = bottomAttachment.plus(this.top.offset + spacing);
                    break;
                }
            }
            this.isVisited = false;
            return this.cacheTop;
        }
    }
    
    @Override
    public String toString() {
        String string = this.getName() + " {";
        if (this.width != -1) {
            string = string + "width=" + this.width + " ";
        }
        if (this.height != -1) {
            string = string + "height=" + this.height + " ";
        }
        if (this.left != null) {
            string = string + "left=" + this.left + " ";
        }
        if (this.right != null) {
            string = string + "right=" + this.right + " ";
        }
        if (this.top != null) {
            string = string + "top=" + this.top + " ";
        }
        if (this.bottom != null) {
            string = string + "bottom=" + this.bottom + " ";
        }
        string = string.trim();
        string += "}";
        return string;
    }
}
