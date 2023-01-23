//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class CoolItem extends Item
{
    CoolBar parent;
    Control control;
    int id;
    boolean ideal;
    boolean minimum;
    
    public CoolItem(final CoolBar parent, final int style) {
        super((Widget)parent, style);
        (this.parent = parent).createItem(this, parent.getItemCount());
    }
    
    public CoolItem(final CoolBar parent, final int style, final int index) {
        super((Widget)parent, style);
        (this.parent = parent).createItem(this, index);
    }
    
    public void addSelectionListener(final SelectionListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(13, typedListener);
        this.addListener(14, typedListener);
    }
    
    @Override
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    public Point computeSize(int wHint, int hHint) {
        this.checkWidget();
        wHint = ((wHint != -1) ? DPIUtil.autoScaleUp(wHint) : wHint);
        hHint = ((hHint != -1) ? DPIUtil.autoScaleUp(hHint) : hHint);
        return DPIUtil.autoScaleDown(this.computeSizeInPixels(wHint, hHint));
    }
    
    Point computeSizeInPixels(final int wHint, final int hHint) {
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return new Point(0, 0);
        }
        int width = wHint;
        int height = hHint;
        if (wHint == -1) {
            width = 32;
        }
        if (hHint == -1) {
            height = 32;
        }
        if ((this.parent.style & 0x200) != 0x0) {
            height += this.parent.getMargin(index);
        }
        else {
            width += this.parent.getMargin(index);
        }
        return new Point(width, height);
    }
    
    @Override
    void destroyWidget() {
        this.parent.destroyItem(this);
        this.releaseHandle();
    }
    
    public Rectangle getBounds() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getBoundsInPixels());
    }
    
    Rectangle getBoundsInPixels() {
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return new Rectangle(0, 0, 0, 0);
        }
        final long hwnd = this.parent.handle;
        final RECT rect = new RECT();
        OS.SendMessage(hwnd, 1033, (long)index, rect);
        final MARGINS margins = new MARGINS();
        OS.SendMessage(hwnd, 1064, 0L, margins);
        final RECT rect5;
        final RECT rect2 = rect5 = rect;
        rect5.left -= margins.cxLeftWidth;
        final RECT rect6;
        final RECT rect3 = rect6 = rect;
        rect6.right += margins.cxRightWidth;
        if (!this.parent.isLastItemOfRow(index)) {
            final RECT rect7;
            final RECT rect4 = rect7 = rect;
            rect7.right += (((this.parent.style & 0x800000) == 0x0) ? 2 : 0);
        }
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        if ((this.parent.style & 0x200) != 0x0) {
            return new Rectangle(rect.top, rect.left, height, width);
        }
        return new Rectangle(rect.left, rect.top, width, height);
    }
    
    Rectangle getClientArea() {
        this.checkWidget();
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return new Rectangle(0, 0, 0, 0);
        }
        final long hwnd = this.parent.handle;
        final RECT insetRect = new RECT();
        OS.SendMessage(hwnd, 1058, (long)index, insetRect);
        final RECT rect = new RECT();
        OS.SendMessage(hwnd, 1033, (long)index, rect);
        final int x = rect.left + insetRect.left;
        int y = rect.top;
        int width = rect.right - rect.left - insetRect.left;
        int height = rect.bottom - rect.top;
        if ((this.parent.style & 0x800000) == 0x0) {
            y += insetRect.top;
            width -= insetRect.right;
            height -= insetRect.top + insetRect.bottom;
        }
        if (index == 0) {
            final REBARBANDINFO rbBand = new REBARBANDINFO();
            rbBand.cbSize = REBARBANDINFO.sizeof;
            rbBand.fMask = 2048;
            OS.SendMessage(hwnd, 1052, (long)index, rbBand);
            width = width - rbBand.cxHeader + 1;
        }
        return new Rectangle(x, y, Math.max(0, width), Math.max(0, height));
    }
    
    public Control getControl() {
        this.checkWidget();
        return this.control;
    }
    
    public CoolBar getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    @Override
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
        this.id = -1;
        this.control = null;
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
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        if (this.control != null && this.control.isDisposed()) {
            this.control = null;
        }
        final Control oldControl = this.control;
        final Control newControl = control;
        final long hwnd = this.parent.handle;
        final long hwndChild = (newControl != null) ? control.topHandle() : 0L;
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        rbBand.fMask = 16;
        rbBand.hwndChild = hwndChild;
        this.control = newControl;
        long hwndAbove = 0L;
        if (newControl != null) {
            hwndAbove = OS.GetWindow(hwndChild, 3);
        }
        final boolean hideNew = newControl != null && !newControl.getVisible();
        final boolean showOld = oldControl != null && oldControl.getVisible();
        OS.SendMessage(hwnd, 1035, (long)index, rbBand);
        if (hideNew) {
            newControl.setVisible(false);
        }
        if (showOld) {
            oldControl.setVisible(true);
        }
        if (hwndAbove != 0L && hwndAbove != hwndChild) {
            final int flags = 19;
            OS.SetWindowPos(hwndChild, hwndAbove, 0, 0, 0, 0, 19);
        }
    }
    
    public Point getPreferredSize() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getPreferredSizeInPixels());
    }
    
    Point getPreferredSizeInPixels() {
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return new Point(0, 0);
        }
        final long hwnd = this.parent.handle;
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        rbBand.fMask = 544;
        OS.SendMessage(hwnd, 1052, (long)index, rbBand);
        final int width = rbBand.cxIdeal + this.parent.getMargin(index);
        if ((this.parent.style & 0x200) != 0x0) {
            return new Point(rbBand.cyMaxChild, width);
        }
        return new Point(width, rbBand.cyMaxChild);
    }
    
    public void setPreferredSize(final int width, final int height) {
        this.checkWidget();
        this.setPreferredSizeInPixels(DPIUtil.autoScaleUp(width), DPIUtil.autoScaleUp(height));
    }
    
    void setPreferredSizeInPixels(int width, int height) {
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        width = Math.max(0, width);
        height = Math.max(0, height);
        this.ideal = true;
        final long hwnd = this.parent.handle;
        int cxIdeal;
        int cyMaxChild;
        if ((this.parent.style & 0x200) != 0x0) {
            cxIdeal = Math.max(0, height - this.parent.getMargin(index));
            cyMaxChild = width;
        }
        else {
            cxIdeal = Math.max(0, width - this.parent.getMargin(index));
            cyMaxChild = height;
        }
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        rbBand.fMask = 32;
        OS.SendMessage(hwnd, 1052, (long)index, rbBand);
        rbBand.fMask = 544;
        rbBand.cxIdeal = cxIdeal;
        rbBand.cyMaxChild = cyMaxChild;
        if (!this.minimum) {
            rbBand.cyMinChild = cyMaxChild;
        }
        OS.SendMessage(hwnd, 1035, (long)index, rbBand);
    }
    
    public void setPreferredSize(Point size) {
        this.checkWidget();
        if (size == null) {
            this.error(4);
        }
        size = DPIUtil.autoScaleUp(size);
        this.setPreferredSizeInPixels(size.x, size.y);
    }
    
    public Point getSize() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getSizeInPixels());
    }
    
    Point getSizeInPixels() {
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return new Point(0, 0);
        }
        final long hwnd = this.parent.handle;
        final RECT rect = new RECT();
        OS.SendMessage(hwnd, 1033, (long)index, rect);
        final MARGINS margins = new MARGINS();
        OS.SendMessage(hwnd, 1064, 0L, margins);
        final RECT rect5;
        final RECT rect2 = rect5 = rect;
        rect5.left -= margins.cxLeftWidth;
        final RECT rect6;
        final RECT rect3 = rect6 = rect;
        rect6.right += margins.cxRightWidth;
        if (!this.parent.isLastItemOfRow(index)) {
            final RECT rect7;
            final RECT rect4 = rect7 = rect;
            rect7.right += (((this.parent.style & 0x800000) == 0x0) ? 2 : 0);
        }
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        if ((this.parent.style & 0x200) != 0x0) {
            return new Point(height, width);
        }
        return new Point(width, height);
    }
    
    public void setSize(final int width, final int height) {
        this.checkWidget();
        this.setSizeInPixels(DPIUtil.autoScaleUp(width), DPIUtil.autoScaleUp(height));
    }
    
    void setSizeInPixels(int width, int height) {
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        width = Math.max(0, width);
        height = Math.max(0, height);
        final long hwnd = this.parent.handle;
        int cx;
        int cyChild;
        int cxIdeal;
        if ((this.parent.style & 0x200) != 0x0) {
            cx = height;
            cyChild = width;
            cxIdeal = Math.max(0, height - this.parent.getMargin(index));
        }
        else {
            cx = width;
            cyChild = height;
            cxIdeal = Math.max(0, width - this.parent.getMargin(index));
        }
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        rbBand.fMask = 544;
        OS.SendMessage(hwnd, 1052, (long)index, rbBand);
        if (!this.ideal) {
            rbBand.cxIdeal = cxIdeal;
        }
        if (!this.minimum) {
            rbBand.cyMinChild = cyChild;
        }
        rbBand.cyChild = cyChild;
        if (!this.parent.isLastItemOfRow(index)) {
            final MARGINS margins = new MARGINS();
            OS.SendMessage(hwnd, 1064, 0L, margins);
            cx -= margins.cxLeftWidth + margins.cxRightWidth;
            final int separator = ((this.parent.style & 0x800000) == 0x0) ? 2 : 0;
            rbBand.cx = cx - separator;
            final REBARBANDINFO rebarbandinfo2;
            final REBARBANDINFO rebarbandinfo = rebarbandinfo2 = rbBand;
            rebarbandinfo2.fMask |= 0x40;
        }
        OS.SendMessage(hwnd, 1035, (long)index, rbBand);
    }
    
    public void setSize(Point size) {
        this.checkWidget();
        if (size == null) {
            this.error(4);
        }
        size = DPIUtil.autoScaleUp(size);
        this.setSizeInPixels(size.x, size.y);
    }
    
    public Point getMinimumSize() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getMinimumSizeInPixels());
    }
    
    Point getMinimumSizeInPixels() {
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return new Point(0, 0);
        }
        final long hwnd = this.parent.handle;
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        rbBand.fMask = 32;
        OS.SendMessage(hwnd, 1052, (long)index, rbBand);
        if ((this.parent.style & 0x200) != 0x0) {
            return new Point(rbBand.cyMinChild, rbBand.cxMinChild);
        }
        return new Point(rbBand.cxMinChild, rbBand.cyMinChild);
    }
    
    public void setMinimumSize(final int width, final int height) {
        this.checkWidget();
        this.setMinimumSizeInPixels(DPIUtil.autoScaleUp(width), DPIUtil.autoScaleUp(height));
    }
    
    void setMinimumSizeInPixels(int width, int height) {
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        width = Math.max(0, width);
        height = Math.max(0, height);
        this.minimum = true;
        final long hwnd = this.parent.handle;
        int cxMinChild;
        int cyMinChild;
        if ((this.parent.style & 0x200) != 0x0) {
            cxMinChild = height;
            cyMinChild = width;
        }
        else {
            cxMinChild = width;
            cyMinChild = height;
        }
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        rbBand.fMask = 32;
        OS.SendMessage(hwnd, 1052, (long)index, rbBand);
        rbBand.cxMinChild = cxMinChild;
        rbBand.cyMinChild = cyMinChild;
        OS.SendMessage(hwnd, 1035, (long)index, rbBand);
    }
    
    public void setMinimumSize(Point size) {
        this.checkWidget();
        if (size == null) {
            this.error(4);
        }
        size = DPIUtil.autoScaleUp(size);
        this.setMinimumSizeInPixels(size.x, size.y);
    }
    
    boolean getWrap() {
        final int index = this.parent.indexOf(this);
        final long hwnd = this.parent.handle;
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        rbBand.fMask = 1;
        OS.SendMessage(hwnd, 1052, (long)index, rbBand);
        return (rbBand.fStyle & 0x1) != 0x0;
    }
    
    void setWrap(final boolean wrap) {
        final int index = this.parent.indexOf(this);
        final long hwnd = this.parent.handle;
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        rbBand.fMask = 1;
        OS.SendMessage(hwnd, 1052, (long)index, rbBand);
        if (wrap) {
            final REBARBANDINFO rebarbandinfo3;
            final REBARBANDINFO rebarbandinfo = rebarbandinfo3 = rbBand;
            rebarbandinfo3.fStyle |= 0x1;
        }
        else {
            final REBARBANDINFO rebarbandinfo4;
            final REBARBANDINFO rebarbandinfo2 = rebarbandinfo4 = rbBand;
            rebarbandinfo4.fStyle &= 0xFFFFFFFE;
        }
        OS.SendMessage(hwnd, 1035, (long)index, rbBand);
    }
    
    public void removeSelectionListener(final SelectionListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(13, (SWTEventListener)listener);
        this.eventTable.unhook(14, (SWTEventListener)listener);
    }
}
