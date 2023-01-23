//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;

public class ExpandItem extends Item
{
    ExpandBar parent;
    Control control;
    boolean expanded;
    boolean hover;
    int x;
    int y;
    int width;
    int height;
    int imageHeight;
    int imageWidth;
    static final int TEXT_INSET = 6;
    static final int BORDER = 1;
    static final int CHEVRON_SIZE = 24;
    
    public ExpandItem(final ExpandBar parent, final int style) {
        this(parent, style, checkNull(parent).getItemCount());
    }
    
    public ExpandItem(final ExpandBar parent, final int style, final int index) {
        super((Widget)parent, style);
        (this.parent = parent).createItem(this, style, index);
    }
    
    static ExpandBar checkNull(final ExpandBar control) {
        if (control == null) {
            SWT.error(4);
        }
        return control;
    }
    
    private void drawChevron(final long hDC, final RECT rect) {
        final long oldBrush = OS.SelectObject(hDC, OS.GetSysColorBrush(15));
        OS.PatBlt(hDC, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top, 15728673);
        OS.SelectObject(hDC, oldBrush);
        rect.left += 4;
        rect.top += 4;
        rect.right -= 4;
        rect.bottom -= 4;
        final long hPen = OS.CreatePen(0, 1, this.parent.getForegroundPixel());
        final long oldPen = OS.SelectObject(hDC, hPen);
        int[] polyline1;
        int[] polyline2;
        if (this.expanded) {
            final int px = rect.left + 5;
            int py = rect.top + 7;
            polyline1 = new int[] { px, py, px + 1, py, px + 1, py - 1, px + 2, py - 1, px + 2, py - 2, px + 3, py - 2, px + 3, py - 3, px + 3, py - 2, px + 4, py - 2, px + 4, py - 1, px + 5, py - 1, px + 5, py, px + 7, py };
            py += 4;
            polyline2 = new int[] { px, py, px + 1, py, px + 1, py - 1, px + 2, py - 1, px + 2, py - 2, px + 3, py - 2, px + 3, py - 3, px + 3, py - 2, px + 4, py - 2, px + 4, py - 1, px + 5, py - 1, px + 5, py, px + 7, py };
        }
        else {
            final int px = rect.left + 5;
            int py = rect.top + 4;
            polyline1 = new int[] { px, py, px + 1, py, px + 1, py + 1, px + 2, py + 1, px + 2, py + 2, px + 3, py + 2, px + 3, py + 3, px + 3, py + 2, px + 4, py + 2, px + 4, py + 1, px + 5, py + 1, px + 5, py, px + 7, py };
            py += 4;
            polyline2 = new int[] { px, py, px + 1, py, px + 1, py + 1, px + 2, py + 1, px + 2, py + 2, px + 3, py + 2, px + 3, py + 3, px + 3, py + 2, px + 4, py + 2, px + 4, py + 1, px + 5, py + 1, px + 5, py, px + 7, py };
        }
        OS.Polyline(hDC, polyline1, polyline1.length / 2);
        OS.Polyline(hDC, polyline2, polyline2.length / 2);
        if (this.hover) {
            final long whitePen = OS.CreatePen(0, 1, OS.GetSysColor(20));
            final long darkGrayPen = OS.CreatePen(0, 1, OS.GetSysColor(16));
            OS.SelectObject(hDC, whitePen);
            final int[] points1 = { rect.left, rect.bottom, rect.left, rect.top, rect.right, rect.top };
            OS.Polyline(hDC, points1, points1.length / 2);
            OS.SelectObject(hDC, darkGrayPen);
            final int[] points2 = { rect.right, rect.top, rect.right, rect.bottom, rect.left, rect.bottom };
            OS.Polyline(hDC, points2, points2.length / 2);
            OS.SelectObject(hDC, oldPen);
            OS.DeleteObject(whitePen);
            OS.DeleteObject(darkGrayPen);
        }
        else {
            OS.SelectObject(hDC, oldPen);
        }
        OS.DeleteObject(hPen);
    }
    
    void drawItem(final GC gc, final long hTheme, final RECT clipRect, final boolean drawFocus) {
        final long hDC = gc.handle;
        final int headerHeight = this.parent.getBandHeight();
        final RECT rect = new RECT();
        OS.SetRect(rect, this.x, this.y, this.x + this.width, this.y + headerHeight);
        if (hTheme != 0L) {
            OS.DrawThemeBackground(hTheme, hDC, 8, 0, rect, clipRect);
        }
        else {
            final long oldBrush = OS.SelectObject(hDC, OS.GetSysColorBrush(15));
            OS.PatBlt(hDC, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top, 15728673);
            OS.SelectObject(hDC, oldBrush);
        }
        if (this.image != null) {
            final RECT rect5;
            final RECT rect2 = rect5 = rect;
            rect5.left += 6;
            if (this.imageHeight > headerHeight) {
                gc.drawImage(this.image, DPIUtil.autoScaleDown(rect.left), DPIUtil.autoScaleDown(rect.top + headerHeight - this.imageHeight));
            }
            else {
                gc.drawImage(this.image, DPIUtil.autoScaleDown(rect.left), DPIUtil.autoScaleDown(rect.top + (headerHeight - this.imageHeight) / 2));
            }
            final RECT rect6;
            final RECT rect3 = rect6 = rect;
            rect6.left += this.imageWidth;
        }
        if (this.text.length() > 0) {
            final RECT rect7;
            final RECT rect4 = rect7 = rect;
            rect7.left += 6;
            char[] buffer;
            if ((this.style & Integer.MIN_VALUE) != 0x0) {
                final int bits = OS.GetWindowLong(this.parent.handle, -20);
                if ((bits & 0x400000) != 0x0) {
                    buffer = ("\u202a" + this.text).toCharArray();
                }
                else {
                    buffer = ("\u202b" + this.text).toCharArray();
                }
            }
            else {
                buffer = this.text.toCharArray();
            }
            if (hTheme != 0L) {
                OS.DrawThemeText(hTheme, hDC, 8, 0, buffer, buffer.length, 36, 0, rect);
            }
            else {
                final int oldBkMode = OS.SetBkMode(hDC, 1);
                OS.DrawText(hDC, buffer, buffer.length, rect, 36);
                OS.SetBkMode(hDC, oldBkMode);
            }
        }
        final int chevronSize = 24;
        rect.left = rect.right - 24;
        rect.top = this.y + (headerHeight - 24) / 2;
        rect.bottom = rect.top + 24;
        if (hTheme != 0L) {
            final int partID = this.expanded ? 6 : 7;
            final int stateID = this.hover ? 2 : 1;
            OS.DrawThemeBackground(hTheme, hDC, partID, stateID, rect, clipRect);
        }
        else {
            this.drawChevron(hDC, rect);
        }
        if (drawFocus) {
            OS.SetRect(rect, this.x + 1, this.y + 1, this.x + this.width - 2, this.y + headerHeight - 2);
            OS.DrawFocusRect(hDC, rect);
        }
        if (this.expanded && !this.parent.isAppThemed()) {
            final long pen = OS.CreatePen(0, 1, OS.GetSysColor(15));
            final long oldPen = OS.SelectObject(hDC, pen);
            final int[] points = { this.x, this.y + headerHeight, this.x, this.y + headerHeight + this.height, this.x + this.width - 1, this.y + headerHeight + this.height, this.x + this.width - 1, this.y + headerHeight - 1 };
            OS.Polyline(hDC, points, points.length / 2);
            OS.SelectObject(hDC, oldPen);
            OS.DeleteObject(pen);
        }
    }
    
    @Override
    void destroyWidget() {
        this.parent.destroyItem(this);
        this.releaseHandle();
    }
    
    public Control getControl() {
        this.checkWidget();
        return this.control;
    }
    
    public boolean getExpanded() {
        this.checkWidget();
        return this.expanded;
    }
    
    public int getHeaderHeight() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getHeaderHeightInPixels());
    }
    
    int getHeaderHeightInPixels() {
        return Math.max(this.parent.getBandHeight(), this.imageHeight);
    }
    
    public int getHeight() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getHeightInPixels());
    }
    
    int getHeightInPixels() {
        return this.height;
    }
    
    public ExpandBar getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    int getPreferredWidth(final long hTheme, final long hDC) {
        int width = 36;
        if (this.image != null) {
            width += 6 + this.imageWidth;
        }
        if (this.text.length() > 0) {
            final RECT rect = new RECT();
            final char[] buffer = this.text.toCharArray();
            if (hTheme != 0L) {
                OS.GetThemeTextExtent(hTheme, hDC, 8, 0, buffer, buffer.length, 32, (RECT)null, rect);
            }
            else {
                OS.DrawText(hDC, buffer, buffer.length, rect, 1024);
            }
            width += rect.right - rect.left;
        }
        return width;
    }
    
    boolean isHover(final int x, final int y) {
        final int bandHeight = this.parent.getBandHeight();
        return this.x < x && x < this.x + this.width && this.y < y && y < this.y + bandHeight;
    }
    
    void redraw(final boolean all) {
        final long parentHandle = this.parent.handle;
        final int headerHeight = this.parent.getBandHeight();
        final RECT rect = new RECT();
        final int left = all ? this.x : (this.x + this.width - headerHeight);
        OS.SetRect(rect, left, this.y, this.x + this.width, this.y + headerHeight);
        OS.InvalidateRect(parentHandle, rect, true);
        if (this.imageHeight > headerHeight) {
            OS.SetRect(rect, this.x + 6, this.y + headerHeight - this.imageHeight, this.x + 6 + this.imageWidth, this.y);
            OS.InvalidateRect(parentHandle, rect, true);
        }
        if (!this.parent.isAppThemed()) {
            OS.SetRect(rect, this.x, this.y + headerHeight, this.x + this.width, this.y + headerHeight + this.height + 1);
            OS.InvalidateRect(parentHandle, rect, true);
        }
    }
    
    @Override
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
    }
    
    @Override
    void releaseWidget() {
        super.releaseWidget();
        this.control = null;
    }
    
    void setBoundsInPixels(int x, int y, int width, int height, final boolean move, final boolean size) {
        this.redraw(true);
        final int headerHeight = this.parent.getBandHeight();
        if (move) {
            if (this.imageHeight > headerHeight) {
                y += this.imageHeight - headerHeight;
            }
            this.x = x;
            this.y = y;
            this.redraw(true);
        }
        if (size) {
            this.width = width;
            this.height = height;
            this.redraw(true);
        }
        if (this.control != null && !this.control.isDisposed()) {
            if (!this.parent.isAppThemed()) {
                ++x;
                width = Math.max(0, width - 2);
                height = Math.max(0, height - 1);
            }
            if (move && size) {
                this.control.setBoundsInPixels(x, y + headerHeight, width, height);
            }
            if (move && !size) {
                this.control.setLocationInPixels(x, y + headerHeight);
            }
            if (!move && size) {
                this.control.setSizeInPixels(width, height);
            }
        }
    }
    
    public void setControl(final Control control) {
        this.checkWidget();
        if (control != null) {
            if (control.isDisposed()) {
                this.error(5);
            }
            if (control.parent != this.parent) {
                this.error(32);
            }
        }
        if ((this.control = control) != null) {
            final int headerHeight = this.parent.getBandHeight();
            control.setVisible(this.expanded);
            if (!this.parent.isAppThemed()) {
                final int width = Math.max(0, this.width - 2);
                final int height = Math.max(0, this.height - 1);
                control.setBoundsInPixels(this.x + 1, this.y + headerHeight, width, height);
            }
            else {
                control.setBoundsInPixels(this.x, this.y + headerHeight, this.width, this.height);
            }
        }
    }
    
    public void setExpanded(final boolean expanded) {
        this.checkWidget();
        this.expanded = expanded;
        this.parent.showItem(this);
    }
    
    public void setHeight(final int height) {
        this.checkWidget();
        this.setHeightInPixels(DPIUtil.autoScaleUp(height));
    }
    
    void setHeightInPixels(final int height) {
        if (height < 0) {
            return;
        }
        this.setBoundsInPixels(0, 0, this.width, height, false, true);
        if (this.expanded) {
            this.parent.layoutItems(this.parent.indexOf(this) + 1, true);
        }
    }
    
    @Override
    public void setImage(final Image image) {
        super.setImage(image);
        final int oldImageHeight = this.imageHeight;
        if (image != null) {
            final Rectangle bounds = image.getBoundsInPixels();
            this.imageHeight = bounds.height;
            this.imageWidth = bounds.width;
        }
        else {
            final int n = 0;
            this.imageWidth = 0;
            this.imageHeight = 0;
        }
        if (oldImageHeight != this.imageHeight) {
            this.parent.layoutItems(this.parent.indexOf(this), true);
        }
        else {
            this.redraw(true);
        }
    }
    
    @Override
    public void setText(final String string) {
        super.setText(string);
        if ((this.state & 0x400000) != 0x0) {
            this.updateTextDirection(100663296);
        }
        this.redraw(true);
    }
}
