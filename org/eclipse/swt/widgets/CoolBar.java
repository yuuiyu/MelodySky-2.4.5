//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;

public class CoolBar extends Composite
{
    CoolItem[] items;
    CoolItem[] originalItems;
    boolean locked;
    boolean ignoreResize;
    static final long ReBarProc;
    static final TCHAR ReBarClass;
    static final int SEPARATOR_WIDTH = 2;
    static final int MAX_WIDTH = 32767;
    static final int DEFAULT_COOLBAR_WIDTH = 0;
    static final int DEFAULT_COOLBAR_HEIGHT = 0;
    
    public CoolBar(final Composite parent, final int style) {
        super(parent, checkStyle(style));
        if ((style & 0x200) != 0x0) {
            this.style |= 0x200;
            final int bits = OS.GetWindowLong(this.handle, -16);
            OS.SetWindowLong(this.handle, -16, bits | 0x80);
        }
        else {
            this.style |= 0x100;
        }
    }
    
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        return OS.CallWindowProc(CoolBar.ReBarProc, hwnd, msg, wParam, lParam);
    }
    
    static int checkStyle(int style) {
        style |= 0x80000;
        return style & 0xFFFFFCFF;
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    Point computeSizeInPixels(final int wHint, final int hHint, final boolean changed) {
        int width = 0;
        int height = 0;
        final int border = this.getBorderWidthInPixels();
        final int newWidth = (wHint == -1) ? 16383 : (wHint + border * 2);
        final int newHeight = (hHint == -1) ? 16383 : (hHint + border * 2);
        final int count = (int)OS.SendMessage(this.handle, 1036, 0L, 0L);
        if (count != 0) {
            this.ignoreResize = true;
            boolean redraw = false;
            if (OS.IsWindowVisible(this.handle)) {
                redraw = true;
                OS.UpdateWindow(this.handle);
                OS.DefWindowProc(this.handle, 11, 0L, 0L);
            }
            final RECT oldRect = new RECT();
            OS.GetWindowRect(this.handle, oldRect);
            final int oldWidth = oldRect.right - oldRect.left;
            final int oldHeight = oldRect.bottom - oldRect.top;
            final int flags = 30;
            OS.SetWindowPos(this.handle, 0L, 0, 0, newWidth, newHeight, 30);
            final RECT rect = new RECT();
            OS.SendMessage(this.handle, 1033, (long)(count - 1), rect);
            height = Math.max(height, rect.bottom);
            OS.SetWindowPos(this.handle, 0L, 0, 0, oldWidth, oldHeight, 30);
            final REBARBANDINFO rbBand = new REBARBANDINFO();
            rbBand.cbSize = REBARBANDINFO.sizeof;
            rbBand.fMask = 513;
            int rowWidth = 0;
            for (int i = 0; i < count; ++i) {
                OS.SendMessage(this.handle, 1052, (long)i, rbBand);
                if ((rbBand.fStyle & 0x1) != 0x0) {
                    width = Math.max(width, rowWidth);
                    rowWidth = 0;
                }
                rowWidth += rbBand.cxIdeal + this.getMargin(i);
            }
            width = Math.max(width, rowWidth);
            if (redraw) {
                OS.DefWindowProc(this.handle, 11, 1L, 0L);
            }
            this.ignoreResize = false;
        }
        if (width == 0) {
            width = 0;
        }
        if (height == 0) {
            height = 0;
        }
        if ((this.style & 0x200) != 0x0) {
            final int tmp = width;
            width = height;
            height = tmp;
        }
        if (wHint != -1) {
            width = wHint;
        }
        if (hHint != -1) {
            height = hHint;
        }
        height += border * 2;
        width += border * 2;
        return new Point(width, height);
    }
    
    void createHandle() {
        super.createHandle();
        this.state &= 0xFFFFFEFD;
        final long hFont = OS.GetStockObject(13);
        OS.SendMessage(this.handle, 48, hFont, 0L);
    }
    
    void createItem(final CoolItem item, final int index) {
        final int count = (int)OS.SendMessage(this.handle, 1036, 0L, 0L);
        if (0 > index || index > count) {
            this.error(6);
        }
        int id;
        for (id = 0; id < this.items.length && this.items[id] != null; ++id) {}
        if (id == this.items.length) {
            final CoolItem[] newItems = new CoolItem[this.items.length + 4];
            System.arraycopy(this.items, 0, newItems, 0, this.items.length);
            this.items = newItems;
        }
        final long hHeap = OS.GetProcessHeap();
        final long lpText = OS.HeapAlloc(hHeap, 8, 2);
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        rbBand.fMask = 261;
        rbBand.fStyle = 192;
        if ((item.style & 0x4) != 0x0) {
            final REBARBANDINFO rebarbandinfo3;
            final REBARBANDINFO rebarbandinfo = rebarbandinfo3 = rbBand;
            rebarbandinfo3.fStyle |= 0x200;
        }
        rbBand.lpText = lpText;
        rbBand.wID = id;
        final int lastIndex = this.getLastIndexOfRow(index - 1);
        final boolean fixLast = index == lastIndex + 1;
        if (fixLast) {
            final REBARBANDINFO rebarbandinfo4;
            final REBARBANDINFO rebarbandinfo2 = rebarbandinfo4 = rbBand;
            rebarbandinfo4.fMask |= 0x40;
            rbBand.cx = 32767;
        }
        if (index == 0 && count > 0) {
            this.getItem(0).setWrap(false);
        }
        if (OS.SendMessage(this.handle, 1034, (long)index, rbBand) == 0L) {
            this.error(14);
        }
        if (fixLast) {
            this.resizeToPreferredWidth(lastIndex);
        }
        OS.HeapFree(hHeap, 0, lpText);
        this.items[item.id = id] = item;
        final int length = this.originalItems.length;
        final CoolItem[] newOriginals = new CoolItem[length + 1];
        System.arraycopy(this.originalItems, 0, newOriginals, 0, index);
        System.arraycopy(this.originalItems, index, newOriginals, index + 1, length - index);
        newOriginals[index] = item;
        this.originalItems = newOriginals;
    }
    
    void createWidget() {
        super.createWidget();
        this.items = new CoolItem[4];
        this.originalItems = new CoolItem[0];
    }
    
    void destroyItem(final CoolItem item) {
        int index = (int)OS.SendMessage(this.handle, 1040, (long)item.id, 0L);
        final int count = (int)OS.SendMessage(this.handle, 1036, 0L, 0L);
        if (count != 0) {
            final int lastIndex = this.getLastIndexOfRow(index);
            if (index == lastIndex) {
                this.resizeToMaximumWidth(lastIndex - 1);
            }
        }
        final Control control = item.control;
        final boolean wasVisible = control != null && !control.isDisposed() && control.getVisible();
        CoolItem nextItem = null;
        if (item.getWrap() && index + 1 < count) {
            nextItem = this.getItem(index + 1);
            this.ignoreResize = !nextItem.getWrap();
        }
        if (OS.SendMessage(this.handle, 1026, (long)index, 0L) == 0L) {
            this.error(15);
        }
        this.items[item.id] = null;
        item.id = -1;
        if (this.ignoreResize) {
            nextItem.setWrap(true);
            this.ignoreResize = false;
        }
        if (wasVisible) {
            control.setVisible(true);
        }
        for (index = 0; index < this.originalItems.length && this.originalItems[index] != item; ++index) {}
        final int length = this.originalItems.length - 1;
        final CoolItem[] newOriginals = new CoolItem[length];
        System.arraycopy(this.originalItems, 0, newOriginals, 0, index);
        System.arraycopy(this.originalItems, index + 1, newOriginals, index, length - index);
        this.originalItems = newOriginals;
    }
    
    void drawThemeBackground(final long hDC, final long hwnd, final RECT rect) {
        if (OS.IsAppThemed() && this.background == -1 && (this.style & 0x800000) != 0x0) {
            final Control control = this.findBackgroundControl();
            if (control != null && control.backgroundImage != null) {
                this.fillBackground(hDC, control.getBackgroundPixel(), rect);
                return;
            }
        }
        final RECT rect2 = new RECT();
        OS.GetClientRect(this.handle, rect2);
        OS.MapWindowPoints(this.handle, hwnd, rect2, 2);
        final POINT lpPoint = new POINT();
        OS.SetWindowOrgEx(hDC, -rect2.left, -rect2.top, lpPoint);
        OS.SendMessage(this.handle, 791, hDC, 12L);
        OS.SetWindowOrgEx(hDC, lpPoint.x, lpPoint.y, (POINT)null);
    }
    
    Control findThemeControl() {
        if ((this.style & 0x800000) != 0x0) {
            return (Control)this;
        }
        return (Control)((this.background == -1 && this.backgroundImage == null) ? this : super.findThemeControl());
    }
    
    int getMargin(final int index) {
        int margin = 0;
        final MARGINS margins = new MARGINS();
        OS.SendMessage(this.handle, 1064, 0L, margins);
        margin += margins.cxLeftWidth + margins.cxRightWidth;
        final RECT rect = new RECT();
        OS.SendMessage(this.handle, 1058, (long)index, rect);
        if ((this.style & 0x800000) != 0x0) {
            if ((this.style & 0x200) != 0x0) {
                margin += rect.top + 4;
            }
            else {
                margin += rect.left + 4;
            }
        }
        else if ((this.style & 0x200) != 0x0) {
            margin += rect.top + rect.bottom;
        }
        else {
            margin += rect.left + rect.right;
        }
        if ((this.style & 0x800000) == 0x0 && !this.isLastItemOfRow(index)) {
            margin += 2;
        }
        return margin;
    }
    
    public CoolItem getItem(final int index) {
        this.checkWidget();
        final int count = (int)OS.SendMessage(this.handle, 1036, 0L, 0L);
        if (0 > index || index >= count) {
            this.error(6);
        }
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        rbBand.fMask = 256;
        OS.SendMessage(this.handle, 1052, (long)index, rbBand);
        return this.items[rbBand.wID];
    }
    
    public int getItemCount() {
        this.checkWidget();
        return (int)OS.SendMessage(this.handle, 1036, 0L, 0L);
    }
    
    public int[] getItemOrder() {
        this.checkWidget();
        final int count = (int)OS.SendMessage(this.handle, 1036, 0L, 0L);
        final int[] indices = new int[count];
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        rbBand.fMask = 256;
        for (int i = 0; i < count; ++i) {
            OS.SendMessage(this.handle, 1052, (long)i, rbBand);
            CoolItem item;
            int index;
            for (item = this.items[rbBand.wID], index = 0; index < this.originalItems.length && this.originalItems[index] != item; ++index) {}
            if (index == this.originalItems.length) {
                this.error(8);
            }
            indices[i] = index;
        }
        return indices;
    }
    
    public CoolItem[] getItems() {
        this.checkWidget();
        final int count = (int)OS.SendMessage(this.handle, 1036, 0L, 0L);
        final CoolItem[] result = new CoolItem[count];
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        rbBand.fMask = 256;
        for (int i = 0; i < count; ++i) {
            OS.SendMessage(this.handle, 1052, (long)i, rbBand);
            result[i] = this.items[rbBand.wID];
        }
        return result;
    }
    
    public Point[] getItemSizes() {
        this.checkWidget();
        final Point[] sizes = this.getItemSizesInPixels();
        if (sizes != null) {
            for (int i = 0; i < sizes.length; ++i) {
                sizes[i] = DPIUtil.autoScaleDown(sizes[i]);
            }
        }
        return sizes;
    }
    
    Point[] getItemSizesInPixels() {
        final int count = (int)OS.SendMessage(this.handle, 1036, 0L, 0L);
        final Point[] sizes = new Point[count];
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        rbBand.fMask = 32;
        final int separator = ((this.style & 0x800000) == 0x0) ? 2 : 0;
        final MARGINS margins = new MARGINS();
        for (int i = 0; i < count; ++i) {
            final RECT rect = new RECT();
            OS.SendMessage(this.handle, 1033, (long)i, rect);
            OS.SendMessage(this.handle, 1052, (long)i, rbBand);
            OS.SendMessage(this.handle, 1064, 0L, margins);
            final RECT rect5;
            final RECT rect2 = rect5 = rect;
            rect5.left -= margins.cxLeftWidth;
            final RECT rect6;
            final RECT rect3 = rect6 = rect;
            rect6.right += margins.cxRightWidth;
            if (!this.isLastItemOfRow(i)) {
                final RECT rect7;
                final RECT rect4 = rect7 = rect;
                rect7.right += separator;
            }
            if ((this.style & 0x200) != 0x0) {
                sizes[i] = new Point(rbBand.cyChild, rect.right - rect.left);
            }
            else {
                sizes[i] = new Point(rect.right - rect.left, rbBand.cyChild);
            }
        }
        return sizes;
    }
    
    int getLastIndexOfRow(final int index) {
        final int count = (int)OS.SendMessage(this.handle, 1036, 0L, 0L);
        if (count == 0) {
            return -1;
        }
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        rbBand.fMask = 1;
        for (int i = index + 1; i < count; ++i) {
            OS.SendMessage(this.handle, 1052, (long)i, rbBand);
            if ((rbBand.fStyle & 0x1) != 0x0) {
                return i - 1;
            }
        }
        return count - 1;
    }
    
    boolean isLastItemOfRow(final int index) {
        final int count = (int)OS.SendMessage(this.handle, 1036, 0L, 0L);
        if (index + 1 == count) {
            return true;
        }
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        rbBand.fMask = 1;
        OS.SendMessage(this.handle, 1052, (long)(index + 1), rbBand);
        return (rbBand.fStyle & 0x1) != 0x0;
    }
    
    public boolean getLocked() {
        this.checkWidget();
        return this.locked;
    }
    
    public int[] getWrapIndices() {
        this.checkWidget();
        final CoolItem[] items = this.getItems();
        final int[] indices = new int[items.length];
        int count = 0;
        for (int i = 0; i < items.length; ++i) {
            if (items[i].getWrap()) {
                indices[count++] = i;
            }
        }
        final int[] result = new int[count];
        System.arraycopy(indices, 0, result, 0, count);
        return result;
    }
    
    public int indexOf(final CoolItem item) {
        this.checkWidget();
        if (item == null) {
            this.error(4);
        }
        if (item.isDisposed()) {
            this.error(5);
        }
        return (int)OS.SendMessage(this.handle, 1040, (long)item.id, 0L);
    }
    
    void resizeToPreferredWidth(final int index) {
        final int count = (int)OS.SendMessage(this.handle, 1036, 0L, 0L);
        if (0 <= index && index < count) {
            final REBARBANDINFO rbBand = new REBARBANDINFO();
            rbBand.cbSize = REBARBANDINFO.sizeof;
            rbBand.fMask = 512;
            OS.SendMessage(this.handle, 1052, (long)index, rbBand);
            final RECT rect = new RECT();
            OS.SendMessage(this.handle, 1058, (long)index, rect);
            rbBand.cx = rbBand.cxIdeal + rect.left;
            if ((this.style & 0x800000) == 0x0) {
                final REBARBANDINFO rebarbandinfo2;
                final REBARBANDINFO rebarbandinfo = rebarbandinfo2 = rbBand;
                rebarbandinfo2.cx += rect.right;
            }
            rbBand.fMask = 64;
            OS.SendMessage(this.handle, 1035, (long)index, rbBand);
        }
    }
    
    void resizeToMaximumWidth(final int index) {
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        rbBand.fMask = 64;
        rbBand.cx = 32767;
        OS.SendMessage(this.handle, 1035, (long)index, rbBand);
    }
    
    void releaseChildren(final boolean destroy) {
        if (this.items != null) {
            for (final CoolItem item : this.items) {
                if (item != null && !item.isDisposed()) {
                    item.release(false);
                }
            }
            this.items = null;
        }
        super.releaseChildren(destroy);
    }
    
    void removeControl(final Control control) {
        super.removeControl(control);
        for (final CoolItem item : this.items) {
            if (item != null && item.control == control) {
                item.setControl(null);
            }
        }
    }
    
    void reskinChildren(final int flags) {
        if (this.items != null) {
            for (final CoolItem item : this.items) {
                if (item != null) {
                    item.reskin(flags);
                }
            }
        }
        super.reskinChildren(flags);
    }
    
    void setBackgroundPixel(int pixel) {
        if (pixel == -1) {
            pixel = this.defaultBackground();
        }
        OS.SendMessage(this.handle, 1043, 0L, (long)pixel);
        this.setItemColors((int)OS.SendMessage(this.handle, 1046, 0L, 0L), pixel);
        if (!OS.IsWindowVisible(this.handle)) {
            return;
        }
        final int flags = 1157;
        OS.RedrawWindow(this.handle, (RECT)null, 0L, 1157);
    }
    
    void setForegroundPixel(int pixel) {
        if (pixel == -1) {
            pixel = this.defaultForeground();
        }
        OS.SendMessage(this.handle, 1045, 0L, (long)pixel);
        this.setItemColors(pixel, (int)OS.SendMessage(this.handle, 1044, 0L, 0L));
    }
    
    void setItemColors(final int foreColor, final int backColor) {
        final int count = (int)OS.SendMessage(this.handle, 1036, 0L, 0L);
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        rbBand.fMask = 2;
        rbBand.clrFore = foreColor;
        rbBand.clrBack = backColor;
        for (int i = 0; i < count; ++i) {
            OS.SendMessage(this.handle, 1035, (long)i, rbBand);
        }
    }
    
    public void setItemLayout(final int[] itemOrder, final int[] wrapIndices, final Point[] sizes) {
        this.checkWidget();
        if (sizes == null) {
            this.error(4);
        }
        final Point[] sizesInPoints = new Point[sizes.length];
        for (int i = 0; i < sizes.length; ++i) {
            sizesInPoints[i] = DPIUtil.autoScaleUp(sizes[i]);
        }
        this.setItemLayoutInPixels(itemOrder, wrapIndices, sizesInPoints);
    }
    
    void setItemLayoutInPixels(final int[] itemOrder, final int[] wrapIndices, final Point[] sizes) {
        this.setRedraw(false);
        this.setItemOrder(itemOrder);
        this.setWrapIndices(wrapIndices);
        this.setItemSizes(sizes);
        this.setRedraw(true);
    }
    
    void setItemOrder(final int[] itemOrder) {
        if (itemOrder == null) {
            this.error(4);
        }
        final int itemCount = (int)OS.SendMessage(this.handle, 1036, 0L, 0L);
        if (itemOrder.length != itemCount) {
            this.error(5);
        }
        final boolean[] set = new boolean[itemCount];
        for (final int index : itemOrder) {
            if (index < 0 || index >= itemCount) {
                this.error(6);
            }
            if (set[index]) {
                this.error(5);
            }
            set[index] = true;
        }
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        for (int i = 0; i < itemOrder.length; ++i) {
            final int id = this.originalItems[itemOrder[i]].id;
            final int index = (int)OS.SendMessage(this.handle, 1040, (long)id, 0L);
            if (index != i) {
                final int lastItemSrcRow = this.getLastIndexOfRow(index);
                final int lastItemDstRow = this.getLastIndexOfRow(i);
                if (index == lastItemSrcRow) {
                    this.resizeToPreferredWidth(index);
                }
                if (i == lastItemDstRow) {
                    this.resizeToPreferredWidth(i);
                }
                OS.SendMessage(this.handle, 1063, (long)index, (long)i);
                if (index == lastItemSrcRow && index - 1 >= 0) {
                    this.resizeToMaximumWidth(index - 1);
                }
                if (i == lastItemDstRow) {
                    this.resizeToMaximumWidth(i);
                }
            }
        }
    }
    
    void setItemSizes(final Point[] sizes) {
        if (sizes == null) {
            this.error(4);
        }
        final int count = (int)OS.SendMessage(this.handle, 1036, 0L, 0L);
        if (sizes.length != count) {
            this.error(5);
        }
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        rbBand.fMask = 256;
        for (int i = 0; i < count; ++i) {
            OS.SendMessage(this.handle, 1052, (long)i, rbBand);
            this.items[rbBand.wID].setSizeInPixels(sizes[i].x, sizes[i].y);
        }
    }
    
    public void setLocked(final boolean locked) {
        this.checkWidget();
        this.locked = locked;
        final int count = (int)OS.SendMessage(this.handle, 1036, 0L, 0L);
        final REBARBANDINFO rbBand = new REBARBANDINFO();
        rbBand.cbSize = REBARBANDINFO.sizeof;
        rbBand.fMask = 1;
        for (int i = 0; i < count; ++i) {
            OS.SendMessage(this.handle, 1052, (long)i, rbBand);
            if (locked) {
                final REBARBANDINFO rebarbandinfo3;
                final REBARBANDINFO rebarbandinfo = rebarbandinfo3 = rbBand;
                rebarbandinfo3.fStyle |= 0x100;
            }
            else {
                final REBARBANDINFO rebarbandinfo4;
                final REBARBANDINFO rebarbandinfo2 = rebarbandinfo4 = rbBand;
                rebarbandinfo4.fStyle &= 0xFFFFFEFF;
            }
            OS.SendMessage(this.handle, 1035, (long)i, rbBand);
        }
    }
    
    public void setWrapIndices(int[] indices) {
        this.checkWidget();
        if (indices == null) {
            indices = new int[0];
        }
        final int count = this.getItemCount();
        for (final int index : indices) {
            if (index < 0 || index >= count) {
                this.error(6);
            }
        }
        this.setRedraw(false);
        final CoolItem[] items = this.getItems();
        for (int i = 0; i < items.length; ++i) {
            final CoolItem item = items[i];
            if (item.getWrap()) {
                this.resizeToPreferredWidth(i - 1);
                item.setWrap(false);
            }
        }
        this.resizeToMaximumWidth(count - 1);
        for (final int index2 : indices) {
            if (0 <= index2 && index2 < items.length) {
                final CoolItem item2 = items[index2];
                item2.setWrap(true);
                this.resizeToMaximumWidth(index2 - 1);
            }
        }
        this.setRedraw(true);
    }
    
    int widgetStyle() {
        int bits = super.widgetStyle() | 0x40 | 0x4;
        bits |= 0x8200;
        if ((this.style & 0x800000) == 0x0) {
            bits |= 0x400;
        }
        return bits;
    }
    
    TCHAR windowClass() {
        return CoolBar.ReBarClass;
    }
    
    long windowProc() {
        return CoolBar.ReBarProc;
    }
    
    LRESULT WM_COMMAND(final long wParam, final long lParam) {
        final LRESULT result = super.WM_COMMAND(wParam, lParam);
        if (result != null) {
            return result;
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_ERASEBKGND(final long wParam, final long lParam) {
        final LRESULT result = super.WM_ERASEBKGND(wParam, lParam);
        if (!OS.IsAppThemed()) {
            this.drawBackground(wParam);
            return null;
        }
        return result;
    }
    
    LRESULT WM_NOTIFY(final long wParam, final long lParam) {
        final LRESULT result = super.WM_NOTIFY(wParam, lParam);
        if (result != null) {
            return result;
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_SETREDRAW(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SETREDRAW(wParam, lParam);
        if (result != null) {
            return result;
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_SIZE(final long wParam, final long lParam) {
        if (!this.ignoreResize) {
            return super.WM_SIZE(wParam, lParam);
        }
        final long code = this.callWindowProc(this.handle, 5, wParam, lParam);
        if (code == 0L) {
            return LRESULT.ZERO;
        }
        return new LRESULT(code);
    }
    
    LRESULT wmNotifyChild(final NMHDR hdr, final long wParam, final long lParam) {
        Label_0522: {
            switch (hdr.code) {
                case -835: {
                    final int pos = OS.GetMessagePos();
                    final POINT pt = new POINT();
                    OS.POINTSTOPOINT(pt, (long)pos);
                    OS.ScreenToClient(this.handle, pt);
                    final int button = (this.display.lastButton != 0) ? this.display.lastButton : 1;
                    if (!this.sendDragEvent(button, pt.x, pt.y)) {
                        return LRESULT.ONE;
                    }
                    break;
                }
                case -839: {
                    final NMREBARCHILDSIZE lprbcs = new NMREBARCHILDSIZE();
                    OS.MoveMemory(lprbcs, lParam, NMREBARCHILDSIZE.sizeof);
                    if (lprbcs.uBand == -1) {
                        break;
                    }
                    final CoolItem item = this.items[lprbcs.wID];
                    final Control control = item.control;
                    if (control != null) {
                        final int width = lprbcs.rcChild_right - lprbcs.rcChild_left;
                        final int height = lprbcs.rcChild_bottom - lprbcs.rcChild_top;
                        control.setBoundsInPixels(lprbcs.rcChild_left, lprbcs.rcChild_top, width, height);
                        break;
                    }
                    break;
                }
                case -831: {
                    if (this.ignoreResize) {
                        break;
                    }
                    final Point size = this.getSizeInPixels();
                    final int border = this.getBorderWidthInPixels();
                    final int barHeight = (int)OS.SendMessage(this.handle, 1051, 0L, 0L);
                    if ((this.style & 0x200) != 0x0) {
                        this.setSizeInPixels(barHeight + 2 * border, size.y);
                        break;
                    }
                    this.setSizeInPixels(size.x, barHeight + 2 * border);
                    break;
                }
                case -841: {
                    final NMREBARCHEVRON lpnm = new NMREBARCHEVRON();
                    OS.MoveMemory(lpnm, lParam, NMREBARCHEVRON.sizeof);
                    final CoolItem item = this.items[lpnm.wID];
                    if (item != null) {
                        final Event event = new Event();
                        event.detail = 4;
                        if ((this.style & 0x200) != 0x0) {
                            event.setLocationInPixels(lpnm.right, lpnm.top);
                        }
                        else {
                            event.setLocationInPixels(lpnm.left, lpnm.bottom);
                        }
                        item.sendSelectionEvent(13, event, false);
                        break;
                    }
                    break;
                }
                case -12: {
                    if (this.findBackgroundControl() == null && (this.style & 0x800000) == 0x0) {
                        break;
                    }
                    final NMCUSTOMDRAW nmcd = new NMCUSTOMDRAW();
                    OS.MoveMemory(nmcd, lParam, NMCUSTOMDRAW.sizeof);
                    switch (nmcd.dwDrawStage) {
                        case 3: {
                            return new LRESULT(68L);
                        }
                        case 4: {
                            this.drawBackground(nmcd.hdc);
                            break Label_0522;
                        }
                        default: {
                            break Label_0522;
                        }
                    }
                    break;
                }
            }
        }
        return super.wmNotifyChild(hdr, wParam, lParam);
    }
    
    static {
        ReBarClass = new TCHAR(0, "ReBarWindow32", true);
        final WNDCLASS lpWndClass = new WNDCLASS();
        OS.GetClassInfo(0L, CoolBar.ReBarClass, lpWndClass);
        ReBarProc = lpWndClass.lpfnWndProc;
    }
}
