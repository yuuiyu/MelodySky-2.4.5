//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;

class CBannerLayout extends Layout
{
    @Override
    protected Point computeSize(final Composite composite, final int wHint, final int hHint, final boolean flushCache) {
        final CBanner banner = (CBanner)composite;
        final Control left = banner.left;
        final Control right = banner.right;
        final Control bottom = banner.bottom;
        final boolean showCurve = left != null && right != null;
        int height = hHint;
        int width = wHint;
        Point bottomSize = new Point(0, 0);
        if (bottom != null) {
            final int trim = this.computeTrim(bottom);
            final int w = (wHint == -1) ? -1 : Math.max(0, width - trim);
            bottomSize = this.computeChildSize(bottom, w, -1, flushCache);
        }
        Point rightSize = new Point(0, 0);
        if (right != null) {
            final int trim2 = this.computeTrim(right);
            int w2 = -1;
            if (banner.rightWidth != -1) {
                w2 = banner.rightWidth - trim2;
                if (left != null) {
                    w2 = Math.min(w2, width - banner.curve_width + 2 * banner.curve_indent - 10 - trim2);
                }
                w2 = Math.max(0, w2);
            }
            rightSize = this.computeChildSize(right, w2, -1, flushCache);
            if (wHint != -1) {
                width -= rightSize.x + banner.curve_width - 2 * banner.curve_indent;
            }
        }
        Point leftSize = new Point(0, 0);
        if (left != null) {
            final int trim3 = this.computeTrim(left);
            final int w3 = (wHint == -1) ? -1 : Math.max(0, width - trim3);
            leftSize = this.computeChildSize(left, w3, -1, flushCache);
        }
        width = leftSize.x + rightSize.x;
        height = bottomSize.y;
        if (bottom != null && (left != null || right != null)) {
            height += 3;
        }
        if (left != null) {
            if (right == null) {
                height += leftSize.y;
            }
            else {
                height += Math.max(leftSize.y, (banner.rightMinHeight == -1) ? rightSize.y : banner.rightMinHeight);
            }
        }
        else {
            height += rightSize.y;
        }
        if (showCurve) {
            width += banner.curve_width - 2 * banner.curve_indent;
            height += 7;
        }
        if (wHint != -1) {
            width = wHint;
        }
        if (hHint != -1) {
            height = hHint;
        }
        return new Point(width, height);
    }
    
    Point computeChildSize(final Control control, final int wHint, final int hHint, final boolean flushCache) {
        Object data = control.getLayoutData();
        if (data == null || !(data instanceof CLayoutData)) {
            data = new CLayoutData();
            control.setLayoutData(data);
        }
        return ((CLayoutData)data).computeSize(control, wHint, hHint, flushCache);
    }
    
    int computeTrim(final Control c) {
        if (c instanceof Scrollable) {
            final Rectangle rect = ((Scrollable)c).computeTrim(0, 0, 0, 0);
            return rect.width;
        }
        return c.getBorderWidth() * 2;
    }
    
    @Override
    protected boolean flushCache(final Control control) {
        final Object data = control.getLayoutData();
        if (data instanceof CLayoutData) {
            ((CLayoutData)data).flushCache();
        }
        return true;
    }
    
    @Override
    protected void layout(final Composite composite, final boolean flushCache) {
        final CBanner banner = (CBanner)composite;
        final Control left = banner.left;
        final Control right = banner.right;
        final Control bottom = banner.bottom;
        final Point size = banner.getSize();
        final boolean showCurve = left != null && right != null;
        int width = size.x - 2 * banner.getBorderWidth();
        int height = size.y - 2 * banner.getBorderWidth();
        Point bottomSize = new Point(0, 0);
        if (bottom != null) {
            final int trim = this.computeTrim(bottom);
            final int w = Math.max(0, width - trim);
            bottomSize = this.computeChildSize(bottom, w, -1, flushCache);
            height -= bottomSize.y + 1 + 2;
        }
        if (showCurve) {
            height -= 7;
        }
        height = Math.max(0, height);
        Point rightSize = new Point(0, 0);
        if (right != null) {
            final int trim2 = this.computeTrim(right);
            int w2 = -1;
            if (banner.rightWidth != -1) {
                w2 = banner.rightWidth - trim2;
                if (left != null) {
                    w2 = Math.min(w2, width - banner.curve_width + 2 * banner.curve_indent - 10 - trim2);
                }
                w2 = Math.max(0, w2);
            }
            rightSize = this.computeChildSize(right, w2, -1, flushCache);
            width -= rightSize.x - banner.curve_indent + banner.curve_width - banner.curve_indent;
        }
        Point leftSize = new Point(0, 0);
        if (left != null) {
            final int trim3 = this.computeTrim(left);
            final int w3 = Math.max(0, width - trim3);
            leftSize = this.computeChildSize(left, w3, -1, flushCache);
        }
        int x = 0;
        int y = 0;
        final int oldStart = banner.curveStart;
        Rectangle leftRect = null;
        Rectangle rightRect = null;
        Rectangle bottomRect = null;
        if (bottom != null) {
            bottomRect = new Rectangle(x, y + size.y - bottomSize.y, bottomSize.x, bottomSize.y);
        }
        if (showCurve) {
            y += 4;
        }
        if (left != null) {
            leftRect = new Rectangle(x, y, leftSize.x, leftSize.y);
            banner.curveStart = x + leftSize.x - banner.curve_indent;
            x += leftSize.x - banner.curve_indent + banner.curve_width - banner.curve_indent;
        }
        if (right != null) {
            if (left != null) {
                rightSize.y = Math.max(leftSize.y, (banner.rightMinHeight == -1) ? rightSize.y : banner.rightMinHeight);
            }
            rightRect = new Rectangle(x, y, rightSize.x, rightSize.y);
        }
        if (banner.curveStart < oldStart) {
            banner.redraw(banner.curveStart - 200, 0, oldStart + banner.curve_width - banner.curveStart + 200 + 5, size.y, false);
        }
        if (banner.curveStart > oldStart) {
            banner.redraw(oldStart - 200, 0, banner.curveStart + banner.curve_width - oldStart + 200 + 5, size.y, false);
        }
        banner.update();
        banner.curveRect = new Rectangle(banner.curveStart, 0, banner.curve_width, size.y);
        if (bottomRect != null) {
            bottom.setBounds(bottomRect);
        }
        if (rightRect != null) {
            right.setBounds(rightRect);
        }
        if (leftRect != null) {
            left.setBounds(leftRect);
        }
    }
}
