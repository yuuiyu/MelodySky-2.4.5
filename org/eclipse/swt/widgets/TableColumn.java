//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class TableColumn extends Item
{
    Table parent;
    boolean resizable;
    boolean moveable;
    String toolTipText;
    int id;
    
    public TableColumn(final Table parent, final int style) {
        super((Widget)parent, checkStyle(style));
        this.resizable = true;
        (this.parent = parent).createItem(this, parent.getColumnCount());
    }
    
    public TableColumn(final Table parent, final int style, final int index) {
        super((Widget)parent, checkStyle(style));
        this.resizable = true;
        (this.parent = parent).createItem(this, index);
    }
    
    public void addControlListener(final ControlListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(11, (Listener)typedListener);
        this.addListener(10, (Listener)typedListener);
    }
    
    public void addSelectionListener(final SelectionListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(13, (Listener)typedListener);
        this.addListener(14, (Listener)typedListener);
    }
    
    static int checkStyle(final int style) {
        return Widget.checkBits(style, 16384, 16777216, 131072, 0, 0, 0);
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    void destroyWidget() {
        this.parent.destroyItem(this);
        this.releaseHandle();
    }
    
    public int getAlignment() {
        this.checkWidget();
        if ((this.style & 0x4000) != 0x0) {
            return 16384;
        }
        if ((this.style & 0x1000000) != 0x0) {
            return 16777216;
        }
        if ((this.style & 0x20000) != 0x0) {
            return 131072;
        }
        return 16384;
    }
    
    String getNameText() {
        return this.getText();
    }
    
    public Table getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public boolean getMoveable() {
        this.checkWidget();
        return this.moveable;
    }
    
    public boolean getResizable() {
        this.checkWidget();
        return this.resizable;
    }
    
    public String getToolTipText() {
        this.checkWidget();
        return this.toolTipText;
    }
    
    public int getWidth() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getWidthInPixels());
    }
    
    int getWidthInPixels() {
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return 0;
        }
        final long hwnd = this.parent.handle;
        return (int)OS.SendMessage(hwnd, 4125, (long)index, 0L);
    }
    
    private int calcAutoWidth(final int index, final boolean withHeader) {
        final long hwnd = this.parent.handle;
        final int style = OS.GetWindowLong(hwnd, -16);
        final boolean isTableVisible = (style & 0x10000000) != 0x0;
        final boolean isTableDrawing = this.parent.getDrawing();
        final boolean needsDisableRedraw = isTableVisible && isTableDrawing;
        try {
            if (needsDisableRedraw) {
                OS.SendMessage(hwnd, 11, 0L, 0L);
            }
            final int oldWidth = (int)OS.SendMessage(hwnd, 4125, (long)index, 0L);
            RECT rect = null;
            final boolean fixWidth = index == this.parent.getColumnCount() - 1;
            if (fixWidth) {
                rect = new RECT();
                OS.GetWindowRect(hwnd, rect);
                OS.UpdateWindow(hwnd);
                final int flags = 30;
                OS.SetWindowPos(hwnd, 0L, 0, 0, 0, rect.bottom - rect.top, 30);
            }
            final int resizeType = withHeader ? -2 : -1;
            OS.SendMessage(hwnd, 4126, (long)index, (long)resizeType);
            if (fixWidth) {
                final int flags2 = 22;
                OS.SetWindowPos(hwnd, 0L, 0, 0, rect.right - rect.left, rect.bottom - rect.top, 22);
            }
            final int newWidth = (int)OS.SendMessage(hwnd, 4125, (long)index, 0L);
            OS.SendMessage(hwnd, 4126, (long)index, (long)oldWidth);
            return newWidth;
        }
        finally {
            if (needsDisableRedraw) {
                OS.SendMessage(hwnd, 11, 1L, 0L);
            }
        }
    }
    
    public void pack() {
        this.checkWidget();
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        final long hwnd = this.parent.handle;
        final int oldWidth = (int)OS.SendMessage(hwnd, 4125, (long)index, 0L);
        final TCHAR buffer = new TCHAR(this.parent.getCodePage(), this.text, true);
        int headerWidth = (int)OS.SendMessage(hwnd, 4183, 0L, buffer) + 12;
        if (OS.IsAppThemed()) {
            headerWidth += 3;
        }
        boolean hasHeaderImage = false;
        if (this.image != null || this.parent.sortColumn == this) {
            hasHeaderImage = true;
            if (this.parent.sortColumn == this && this.parent.sortDirection != 0) {
                headerWidth += 10;
            }
            else if (this.image != null) {
                final Rectangle bounds = this.image.getBoundsInPixels();
                headerWidth += bounds.width;
            }
            final long hwndHeader = OS.SendMessage(hwnd, 4127, 0L, 0L);
            final int margin = (int)OS.SendMessage(hwndHeader, 4629, 0L, 0L);
            headerWidth += margin * 4;
        }
        this.parent.ignoreColumnResize = true;
        int columnWidth = 0;
        if (this.parent.hooks(41)) {
            final RECT headerRect = new RECT();
            final long hwndHeader2 = OS.SendMessage(hwnd, 4127, 0L, 0L);
            OS.SendMessage(hwndHeader2, 4615, (long)index, headerRect);
            OS.MapWindowPoints(hwndHeader2, hwnd, headerRect, 2);
            final long hDC = OS.GetDC(hwnd);
            long oldFont = 0L;
            final long newFont = OS.SendMessage(hwnd, 49, 0L, 0L);
            if (newFont != 0L) {
                oldFont = OS.SelectObject(hDC, newFont);
            }
            for (int count = (int)OS.SendMessage(hwnd, 4100, 0L, 0L), i = 0; i < count; ++i) {
                final TableItem item = this.parent._getItem(i, false);
                if (item != null) {
                    long hFont = item.fontHandle(index);
                    if (hFont != -1L) {
                        hFont = OS.SelectObject(hDC, hFont);
                    }
                    final Event event = this.parent.sendMeasureItemEvent(item, i, index, hDC);
                    if (hFont != -1L) {
                        hFont = OS.SelectObject(hDC, hFont);
                    }
                    if (this.isDisposed()) {
                        break;
                    }
                    if (this.parent.isDisposed()) {
                        break;
                    }
                    final Rectangle bounds2 = event.getBoundsInPixels();
                    columnWidth = Math.max(columnWidth, bounds2.x + bounds2.width - headerRect.left);
                }
            }
            if (newFont != 0L) {
                OS.SelectObject(hDC, oldFont);
            }
            OS.ReleaseDC(hwnd, hDC);
        }
        else {
            columnWidth = this.calcAutoWidth(index, false);
            if (index == 0) {
                if (this.parent.imageList == null) {
                    columnWidth += 2;
                }
                if ((this.parent.style & 0x20) != 0x0) {
                    final long hStateList = OS.SendMessage(hwnd, 4098, 2L, 0L);
                    if (hStateList != 0L) {
                        final int[] cx = { 0 };
                        final int[] cy = { 0 };
                        OS.ImageList_GetIconSize(hStateList, cx, cy);
                        columnWidth += cx[0];
                    }
                }
            }
        }
        if (headerWidth > columnWidth) {
            if (!hasHeaderImage) {
                columnWidth = this.calcAutoWidth(index, true);
            }
            else {
                columnWidth = headerWidth;
            }
        }
        OS.SendMessage(hwnd, 4126, (long)index, (long)columnWidth);
        this.parent.ignoreColumnResize = false;
        if (oldWidth != columnWidth) {
            this.updateToolTip(index);
            this.sendEvent(11);
            if (this.isDisposed()) {
                return;
            }
            boolean moved = false;
            final TableColumn[] columns = this.parent.getColumns();
            for (final int columnindex : this.parent.getColumnOrder()) {
                final TableColumn column = columns[columnindex];
                if (moved && !column.isDisposed()) {
                    column.updateToolTip(columnindex);
                    column.sendEvent(10);
                }
                if (column == this) {
                    moved = true;
                }
            }
        }
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
    }
    
    void releaseParent() {
        super.releaseParent();
        if (this.parent.sortColumn == this) {
            this.parent.sortColumn = null;
        }
    }
    
    public void removeControlListener(final ControlListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(10, (SWTEventListener)listener);
        this.eventTable.unhook(11, (SWTEventListener)listener);
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
    
    public void setAlignment(final int alignment) {
        this.checkWidget();
        if ((alignment & 0x1024000) == 0x0) {
            return;
        }
        final int index = this.parent.indexOf(this);
        if (index == -1 || index == 0) {
            return;
        }
        this.style &= 0xFEFDBFFF;
        this.style |= (alignment & 0x1024000);
        final long hwnd = this.parent.handle;
        final LVCOLUMN lvColumn = new LVCOLUMN();
        lvColumn.mask = 1;
        OS.SendMessage(hwnd, 4191, (long)index, lvColumn);
        final LVCOLUMN lvcolumn3;
        final LVCOLUMN lvcolumn = lvcolumn3 = lvColumn;
        lvcolumn3.fmt &= 0xFFFFFFFC;
        int fmt = 0;
        if ((this.style & 0x4000) == 0x4000) {
            fmt = 0;
        }
        if ((this.style & 0x1000000) == 0x1000000) {
            fmt = 2;
        }
        if ((this.style & 0x20000) == 0x20000) {
            fmt = 1;
        }
        final LVCOLUMN lvcolumn4;
        final LVCOLUMN lvcolumn2 = lvcolumn4 = lvColumn;
        lvcolumn4.fmt |= fmt;
        OS.SendMessage(hwnd, 4192, (long)index, lvColumn);
        if (index != 0) {
            this.parent.forceResize();
            final RECT rect = new RECT();
            final RECT headerRect = new RECT();
            OS.GetClientRect(hwnd, rect);
            final long hwndHeader = OS.SendMessage(hwnd, 4127, 0L, 0L);
            OS.SendMessage(hwndHeader, 4615, (long)index, headerRect);
            OS.MapWindowPoints(hwndHeader, hwnd, headerRect, 2);
            rect.left = headerRect.left;
            rect.right = headerRect.right;
            OS.InvalidateRect(hwnd, rect, true);
        }
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        super.setImage(image);
        if (this.parent.sortColumn != this || this.parent.sortDirection != 0) {
            this.setImage(image, false, false);
        }
    }
    
    void setImage(final Image image, final boolean sort, final boolean right) {
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        final long hwnd = this.parent.handle;
        final LVCOLUMN lvColumn = new LVCOLUMN();
        lvColumn.mask = 17;
        OS.SendMessage(hwnd, 4191, (long)index, lvColumn);
        if (image != null) {
            final LVCOLUMN lvcolumn5;
            final LVCOLUMN lvcolumn = lvcolumn5 = lvColumn;
            lvcolumn5.fmt |= 0x800;
            lvColumn.iImage = this.parent.imageIndexHeader(image);
            if (right) {
                final LVCOLUMN lvcolumn6;
                final LVCOLUMN lvcolumn2 = lvcolumn6 = lvColumn;
                lvcolumn6.fmt |= 0x1000;
            }
        }
        else {
            final LVCOLUMN lvcolumn7;
            final LVCOLUMN lvcolumn3 = lvcolumn7 = lvColumn;
            lvcolumn7.mask &= 0xFFFFFFEF;
            final LVCOLUMN lvcolumn8;
            final LVCOLUMN lvcolumn4 = lvcolumn8 = lvColumn;
            lvcolumn8.fmt &= 0xFFFFE7FF;
        }
        OS.SendMessage(hwnd, 4192, (long)index, lvColumn);
    }
    
    public void setMoveable(final boolean moveable) {
        this.checkWidget();
        this.moveable = moveable;
        this.parent.updateMoveable();
    }
    
    public void setResizable(final boolean resizable) {
        this.checkWidget();
        this.resizable = resizable;
    }
    
    void setSortDirection(final int direction) {
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        final long hwnd = this.parent.handle;
        final long hwndHeader = OS.SendMessage(hwnd, 4127, 0L, 0L);
        final HDITEM hdItem = new HDITEM();
        hdItem.mask = 36;
        OS.SendMessage(hwndHeader, 4619, (long)index, hdItem);
        switch (direction) {
            case 128: {
                final HDITEM hditem11;
                final HDITEM hditem = hditem11 = hdItem;
                hditem11.fmt &= 0xFFFFF5FF;
                final HDITEM hditem12;
                final HDITEM hditem2 = hditem12 = hdItem;
                hditem12.fmt |= 0x400;
                if (this.image == null) {
                    final HDITEM hditem13;
                    final HDITEM hditem3 = hditem13 = hdItem;
                    hditem13.mask &= 0xFFFFFFDF;
                    break;
                }
                break;
            }
            case 1024: {
                final HDITEM hditem14;
                final HDITEM hditem4 = hditem14 = hdItem;
                hditem14.fmt &= 0xFFFFF3FF;
                final HDITEM hditem15;
                final HDITEM hditem5 = hditem15 = hdItem;
                hditem15.fmt |= 0x200;
                if (this.image == null) {
                    final HDITEM hditem16;
                    final HDITEM hditem6 = hditem16 = hdItem;
                    hditem16.mask &= 0xFFFFFFDF;
                    break;
                }
                break;
            }
            case 0: {
                final HDITEM hditem17;
                final HDITEM hditem7 = hditem17 = hdItem;
                hditem17.fmt &= 0xFFFFF9FF;
                if (this.image != null) {
                    final HDITEM hditem18;
                    final HDITEM hditem8 = hditem18 = hdItem;
                    hditem18.fmt |= 0x800;
                    hdItem.iImage = this.parent.imageIndexHeader(this.image);
                    break;
                }
                final HDITEM hditem19;
                final HDITEM hditem9 = hditem19 = hdItem;
                hditem19.fmt &= 0xFFFFF7FF;
                final HDITEM hditem20;
                final HDITEM hditem10 = hditem20 = hdItem;
                hditem20.mask &= 0xFFFFFFDF;
                break;
            }
        }
        OS.SendMessage(hwndHeader, 4620, (long)index, hdItem);
        this.parent.forceResize();
        final RECT rect = new RECT();
        OS.GetClientRect(hwnd, rect);
        if ((int)OS.SendMessage(hwnd, 4096, 0L, 0L) != -1) {
            final int oldColumn = (int)OS.SendMessage(hwnd, 4270, 0L, 0L);
            final int newColumn = (direction == 0) ? -1 : index;
            OS.SendMessage(hwnd, 4236, (long)newColumn, 0L);
            final RECT headerRect = new RECT();
            if (oldColumn != -1 && OS.SendMessage(hwndHeader, 4615, (long)oldColumn, headerRect) != 0L) {
                OS.MapWindowPoints(hwndHeader, hwnd, headerRect, 2);
                rect.left = headerRect.left;
                rect.right = headerRect.right;
                OS.InvalidateRect(hwnd, rect, true);
            }
        }
        final RECT headerRect2 = new RECT();
        if (OS.SendMessage(hwndHeader, 4615, (long)index, headerRect2) != 0L) {
            OS.MapWindowPoints(hwndHeader, hwnd, headerRect2, 2);
            rect.left = headerRect2.left;
            rect.right = headerRect2.right;
            OS.InvalidateRect(hwnd, rect, true);
        }
    }
    
    public void setText(final String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        if (string.equals(this.text)) {
            return;
        }
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        super.setText(string);
        final long hwnd = this.parent.handle;
        final LVCOLUMN lvColumn = new LVCOLUMN();
        lvColumn.mask = 1;
        OS.SendMessage(hwnd, 4191, (long)index, lvColumn);
        final long hHeap = OS.GetProcessHeap();
        final char[] buffer = this.fixMnemonic(string);
        final int byteCount = buffer.length * 2;
        final long pszText = OS.HeapAlloc(hHeap, 8, byteCount);
        OS.MoveMemory(pszText, buffer, byteCount);
        final LVCOLUMN lvcolumn2;
        final LVCOLUMN lvcolumn = lvcolumn2 = lvColumn;
        lvcolumn2.mask |= 0x4;
        lvColumn.pszText = pszText;
        final long result = OS.SendMessage(hwnd, 4192, (long)index, lvColumn);
        if (pszText != 0L) {
            OS.HeapFree(hHeap, 0, pszText);
        }
        if (result == 0L) {
            this.error(13);
        }
    }
    
    public void setToolTipText(final String string) {
        this.checkWidget();
        this.toolTipText = string;
        final long hwndHeaderToolTip = this.parent.headerToolTipHandle;
        if (hwndHeaderToolTip == 0L) {
            this.parent.createHeaderToolTips();
            this.parent.updateHeaderToolTips();
        }
    }
    
    public void setWidth(final int width) {
        this.checkWidget();
        this.setWidthInPixels(DPIUtil.autoScaleUp(width));
    }
    
    void setWidthInPixels(final int width) {
        if (width < 0) {
            return;
        }
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        final long hwnd = this.parent.handle;
        if (width != (int)OS.SendMessage(hwnd, 4125, (long)index, 0L)) {
            OS.SendMessage(hwnd, 4126, (long)index, (long)width);
        }
    }
    
    void updateToolTip(final int index) {
        final long hwndHeaderToolTip = this.parent.headerToolTipHandle;
        if (hwndHeaderToolTip != 0L) {
            final long hwnd = this.parent.handle;
            final long hwndHeader = OS.SendMessage(hwnd, 4127, 0L, 0L);
            final RECT rect = new RECT();
            if (OS.SendMessage(hwndHeader, 4615, (long)index, rect) != 0L) {
                final TOOLINFO lpti = new TOOLINFO();
                lpti.cbSize = TOOLINFO.sizeof;
                lpti.hwnd = hwndHeader;
                lpti.uId = this.id;
                lpti.left = rect.left;
                lpti.top = rect.top;
                lpti.right = rect.right;
                lpti.bottom = rect.bottom;
                OS.SendMessage(hwndHeaderToolTip, 1076, 0L, lpti);
            }
        }
    }
}
