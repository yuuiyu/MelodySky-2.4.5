//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;

class ViewFormLayout extends Layout
{
    @Override
    protected Point computeSize(final Composite composite, final int wHint, final int hHint, final boolean flushCache) {
        final ViewForm form = (ViewForm)composite;
        final Control left = form.topLeft;
        final Control center = form.topCenter;
        final Control right = form.topRight;
        final Control content = form.content;
        Point leftSize = new Point(0, 0);
        if (left != null) {
            leftSize = this.computeChildSize(left, -1, -1, flushCache);
        }
        Point centerSize = new Point(0, 0);
        if (center != null) {
            centerSize = this.computeChildSize(center, -1, -1, flushCache);
        }
        Point rightSize = new Point(0, 0);
        if (right != null) {
            rightSize = this.computeChildSize(right, -1, -1, flushCache);
        }
        final Point size = new Point(0, 0);
        if (form.separateTopCenter || (wHint != -1 && leftSize.x + centerSize.x + rightSize.x > wHint)) {
            size.x = leftSize.x + rightSize.x;
            if (leftSize.x > 0 && rightSize.x > 0) {
                final Point point10;
                final Point point = point10 = size;
                point10.x += form.horizontalSpacing;
            }
            size.x = Math.max(centerSize.x, size.x);
            size.y = Math.max(leftSize.y, rightSize.y);
            if (center != null) {
                final Point point11;
                final Point point2 = point11 = size;
                point11.y += centerSize.y;
                if (left != null || right != null) {
                    final Point point12;
                    final Point point3 = point12 = size;
                    point12.y += form.verticalSpacing;
                }
            }
        }
        else {
            size.x = leftSize.x + centerSize.x + rightSize.x;
            int count = -1;
            if (leftSize.x > 0) {
                ++count;
            }
            if (centerSize.x > 0) {
                ++count;
            }
            if (rightSize.x > 0) {
                ++count;
            }
            if (count > 0) {
                final Point point13;
                final Point point4 = point13 = size;
                point13.x += count * form.horizontalSpacing;
            }
            size.y = Math.max(leftSize.y, Math.max(centerSize.y, rightSize.y));
        }
        if (content != null) {
            if (left != null || right != null || center != null) {
                final Point point14;
                final Point point5 = point14 = size;
                ++point14.y;
            }
            Point contentSize = new Point(0, 0);
            contentSize = this.computeChildSize(content, -1, -1, flushCache);
            size.x = Math.max(size.x, contentSize.x);
            final Point point15;
            final Point point6 = point15 = size;
            point15.y += contentSize.y;
            if (size.y > contentSize.y) {
                final Point point16;
                final Point point7 = point16 = size;
                point16.y += form.verticalSpacing;
            }
        }
        final Point point17;
        final Point point8 = point17 = size;
        point17.x += 2 * form.marginWidth;
        final Point point18;
        final Point point9 = point18 = size;
        point18.y += 2 * form.marginHeight;
        if (wHint != -1) {
            size.x = wHint;
        }
        if (hHint != -1) {
            size.y = hHint;
        }
        return size;
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
        final ViewForm form = (ViewForm)composite;
        final Control left = form.topLeft;
        final Control center = form.topCenter;
        final Control right = form.topRight;
        final Control content = form.content;
        final Rectangle rect = composite.getClientArea();
        Point leftSize = new Point(0, 0);
        if (left != null && !left.isDisposed()) {
            leftSize = this.computeChildSize(left, -1, -1, flushCache);
        }
        Point centerSize = new Point(0, 0);
        if (center != null && !center.isDisposed()) {
            centerSize = this.computeChildSize(center, -1, -1, flushCache);
        }
        Point rightSize = new Point(0, 0);
        if (right != null && !right.isDisposed()) {
            rightSize = this.computeChildSize(right, -1, -1, flushCache);
        }
        int minTopWidth = leftSize.x + centerSize.x + rightSize.x + 2 * form.marginWidth + 2 * form.highlight;
        int count = -1;
        if (leftSize.x > 0) {
            ++count;
        }
        if (centerSize.x > 0) {
            ++count;
        }
        if (rightSize.x > 0) {
            ++count;
        }
        if (count > 0) {
            minTopWidth += count * form.horizontalSpacing;
        }
        int x = rect.x + rect.width - form.marginWidth - form.highlight;
        int y = rect.y + form.marginHeight + form.highlight;
        boolean top = false;
        if (form.separateTopCenter || minTopWidth > rect.width) {
            final int topHeight = Math.max(rightSize.y, leftSize.y);
            if (right != null && !right.isDisposed()) {
                top = true;
                x -= rightSize.x;
                right.setBounds(x, y, rightSize.x, topHeight);
                x -= form.horizontalSpacing;
            }
            if (left != null && !left.isDisposed()) {
                top = true;
                final int trim = this.computeTrim(left);
                final int leftW = x - rect.x - form.marginWidth - form.highlight - trim;
                leftSize = this.computeChildSize(left, leftW, -1, false);
                left.setBounds(rect.x + form.marginWidth + form.highlight, y, leftSize.x, topHeight);
            }
            if (top) {
                y += topHeight + form.verticalSpacing;
            }
            if (center != null && !center.isDisposed()) {
                top = true;
                final int trim = this.computeTrim(center);
                final int w = rect.width - 2 * form.marginWidth - 2 * form.highlight - trim;
                final Point size = this.computeChildSize(center, w, -1, false);
                if (size.x < centerSize.x) {
                    centerSize = size;
                }
                center.setBounds(rect.x + rect.width - form.marginWidth - form.highlight - centerSize.x, y, centerSize.x, centerSize.y);
                y += centerSize.y + form.verticalSpacing;
            }
        }
        else {
            final int topHeight = Math.max(rightSize.y, Math.max(centerSize.y, leftSize.y));
            if (right != null && !right.isDisposed()) {
                top = true;
                x -= rightSize.x;
                right.setBounds(x, y, rightSize.x, topHeight);
                x -= form.horizontalSpacing;
            }
            if (center != null && !center.isDisposed()) {
                top = true;
                x -= centerSize.x;
                center.setBounds(x, y, centerSize.x, topHeight);
                x -= form.horizontalSpacing;
            }
            if (left != null && !left.isDisposed()) {
                top = true;
                final Rectangle trim2 = (left instanceof Composite) ? ((Composite)left).computeTrim(0, 0, 0, 0) : new Rectangle(0, 0, 0, 0);
                final int w = x - rect.x - form.marginWidth - form.highlight - trim2.width;
                final int h = topHeight - trim2.height;
                leftSize = this.computeChildSize(left, w, h, false);
                left.setBounds(rect.x + form.marginWidth + form.highlight, y, leftSize.x, topHeight);
            }
            if (top) {
                y += topHeight + form.verticalSpacing;
            }
        }
        final int oldSeperator = form.separator;
        form.separator = -1;
        if (content != null && !content.isDisposed()) {
            if (left != null || right != null || center != null) {
                form.separator = y;
                ++y;
            }
            content.setBounds(rect.x + form.marginWidth + form.highlight, y, rect.width - 2 * form.marginWidth - 2 * form.highlight, rect.y + rect.height - y - form.marginHeight - form.highlight);
        }
        if (oldSeperator != form.separator) {
            int t;
            int b;
            if (oldSeperator == -1) {
                t = form.separator;
                b = form.separator + 1;
            }
            else if (form.separator == -1) {
                t = oldSeperator;
                b = oldSeperator + 1;
            }
            else {
                t = Math.min(form.separator, oldSeperator);
                b = Math.max(form.separator, oldSeperator);
            }
            form.redraw(form.borderLeft, t, form.getSize().x - form.borderLeft - form.borderRight, b - t, false);
        }
    }
}
