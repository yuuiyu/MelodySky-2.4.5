//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class TreeColumn extends Item
{
    Tree parent;
    boolean resizable;
    boolean moveable;
    String toolTipText;
    int id;
    
    public TreeColumn(final Tree parent, final int style) {
        super((Widget)parent, checkStyle(style));
        this.resizable = true;
        (this.parent = parent).createItem(this, parent.getColumnCount());
    }
    
    public TreeColumn(final Tree parent, final int style, final int index) {
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
    
    public boolean getMoveable() {
        this.checkWidget();
        return this.moveable;
    }
    
    String getNameText() {
        return this.getText();
    }
    
    public Tree getParent() {
        this.checkWidget();
        return this.parent;
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
        final long hwndHeader = this.parent.hwndHeader;
        if (hwndHeader == 0L) {
            return 0;
        }
        final HDITEM hdItem = new HDITEM();
        hdItem.mask = 1;
        OS.SendMessage(hwndHeader, 4619, (long)index, hdItem);
        return hdItem.cxy;
    }
    
    public void pack() {
        this.checkWidget();
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        int columnWidth = 0;
        final long hwnd = this.parent.handle;
        final long hwndHeader = this.parent.hwndHeader;
        final RECT headerRect = new RECT();
        OS.SendMessage(hwndHeader, 4615, (long)index, headerRect);
        final long hDC = OS.GetDC(hwnd);
        long oldFont = 0L;
        final long newFont = OS.SendMessage(hwnd, 49, 0L, 0L);
        if (newFont != 0L) {
            oldFont = OS.SelectObject(hDC, newFont);
        }
        final TVITEM tvItem = new TVITEM();
        tvItem.mask = 28;
        tvItem.hItem = OS.SendMessage(hwnd, 4362, 0L, 0L);
        while (tvItem.hItem != 0L) {
            OS.SendMessage(hwnd, 4414, 0L, tvItem);
            final TreeItem item = (tvItem.lParam != -1L) ? this.parent.items[(int)tvItem.lParam] : null;
            if (item != null) {
                int itemRight = 0;
                if (this.parent.hooks(41)) {
                    final int detail = ((tvItem.state & 0x2) != 0x0) ? 2 : 0;
                    final Event event = this.parent.sendMeasureItemEvent(item, index, hDC, detail);
                    if (this.isDisposed()) {
                        break;
                    }
                    if (this.parent.isDisposed()) {
                        break;
                    }
                    final Rectangle bounds = event.getBoundsInPixels();
                    itemRight = bounds.x + bounds.width;
                }
                else {
                    long hFont = item.fontHandle(index);
                    if (hFont != -1L) {
                        hFont = OS.SelectObject(hDC, hFont);
                    }
                    final RECT itemRect = item.getBounds(index, true, true, false, false, false, hDC);
                    if (hFont != -1L) {
                        OS.SelectObject(hDC, hFont);
                    }
                    itemRight = itemRect.right;
                }
                columnWidth = Math.max(columnWidth, itemRight - headerRect.left);
            }
            tvItem.hItem = OS.SendMessage(hwnd, 4362, 6L, tvItem.hItem);
        }
        final RECT rect = new RECT();
        final int flags = 3072;
        final char[] buffer = this.text.toCharArray();
        OS.DrawText(hDC, buffer, buffer.length, rect, 3072);
        int headerWidth = rect.right - rect.left + 12;
        if (OS.IsAppThemed()) {
            headerWidth += 3;
        }
        if (this.image != null || this.parent.sortColumn == this) {
            Image headerImage = null;
            if (this.parent.sortColumn == this && this.parent.sortDirection != 0) {
                headerWidth += 10;
            }
            else {
                headerImage = this.image;
            }
            if (headerImage != null) {
                final Rectangle bounds2 = headerImage.getBoundsInPixels();
                headerWidth += bounds2.width;
            }
            int margin = 0;
            if (hwndHeader != 0L) {
                margin = (int)OS.SendMessage(hwndHeader, 4629, 0L, 0L);
            }
            else {
                margin = OS.GetSystemMetrics(45) * 3;
            }
            headerWidth += margin * 2;
        }
        if (newFont != 0L) {
            OS.SelectObject(hDC, oldFont);
        }
        OS.ReleaseDC(hwnd, hDC);
        final int gridWidth = this.parent.linesVisible ? 1 : 0;
        this.setWidthInPixels(Math.max(headerWidth, columnWidth + gridWidth));
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
        final long hwndHeader = this.parent.hwndHeader;
        if (hwndHeader == 0L) {
            return;
        }
        final HDITEM hdItem = new HDITEM();
        hdItem.mask = 4;
        OS.SendMessage(hwndHeader, 4619, (long)index, hdItem);
        final HDITEM hditem5;
        final HDITEM hditem = hditem5 = hdItem;
        hditem5.fmt &= 0xFFFFFFFC;
        if ((this.style & 0x4000) == 0x4000) {
            final HDITEM hditem6;
            final HDITEM hditem2 = hditem6 = hdItem;
            hditem6.fmt |= 0x0;
        }
        if ((this.style & 0x1000000) == 0x1000000) {
            final HDITEM hditem7;
            final HDITEM hditem3 = hditem7 = hdItem;
            hditem7.fmt |= 0x2;
        }
        if ((this.style & 0x20000) == 0x20000) {
            final HDITEM hditem8;
            final HDITEM hditem4 = hditem8 = hdItem;
            hditem8.fmt |= 0x1;
        }
        OS.SendMessage(hwndHeader, 4620, (long)index, hdItem);
        if (index != 0) {
            final long hwnd = this.parent.handle;
            this.parent.forceResize();
            final RECT rect = new RECT();
            final RECT headerRect = new RECT();
            OS.GetClientRect(hwnd, rect);
            OS.SendMessage(hwndHeader, 4615, (long)index, headerRect);
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
        final long hwndHeader = this.parent.hwndHeader;
        if (hwndHeader == 0L) {
            return;
        }
        final HDITEM hdItem = new HDITEM();
        hdItem.mask = 52;
        OS.SendMessage(hwndHeader, 4619, (long)index, hdItem);
        final HDITEM hditem11;
        final HDITEM hditem = hditem11 = hdItem;
        hditem11.fmt &= 0xFFFFEFFF;
        if (image != null) {
            if (sort) {
                final HDITEM hditem12;
                final HDITEM hditem2 = hditem12 = hdItem;
                hditem12.mask &= 0xFFFFFFDF;
                final HDITEM hditem13;
                final HDITEM hditem3 = hditem13 = hdItem;
                hditem13.fmt &= 0xFFFFF7FF;
                final HDITEM hditem14;
                final HDITEM hditem4 = hditem14 = hdItem;
                hditem14.fmt |= 0x2000;
                hdItem.hbm = image.handle;
            }
            else {
                final HDITEM hditem15;
                final HDITEM hditem5 = hditem15 = hdItem;
                hditem15.mask &= 0xFFFFFFEF;
                final HDITEM hditem16;
                final HDITEM hditem6 = hditem16 = hdItem;
                hditem16.fmt &= 0xFFFFDFFF;
                final HDITEM hditem17;
                final HDITEM hditem7 = hditem17 = hdItem;
                hditem17.fmt |= 0x800;
                hdItem.iImage = this.parent.imageIndexHeader(image);
            }
            if (right) {
                final HDITEM hditem18;
                final HDITEM hditem8 = hditem18 = hdItem;
                hditem18.fmt |= 0x1000;
            }
        }
        else {
            final HDITEM hditem19;
            final HDITEM hditem9 = hditem19 = hdItem;
            hditem19.mask &= 0xFFFFFFCF;
            final HDITEM hditem20;
            final HDITEM hditem10 = hditem20 = hdItem;
            hditem20.fmt &= 0xFFFFD7FF;
        }
        OS.SendMessage(hwndHeader, 4620, (long)index, hdItem);
    }
    
    public void setMoveable(final boolean moveable) {
        this.checkWidget();
        this.moveable = moveable;
    }
    
    public void setResizable(final boolean resizable) {
        this.checkWidget();
        this.resizable = resizable;
    }
    
    void setSortDirection(final int direction) {
        final long hwndHeader = this.parent.hwndHeader;
        if (hwndHeader != 0L) {
            final int index = this.parent.indexOf(this);
            if (index == -1) {
                return;
            }
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
            if (OS.IsAppThemed()) {
                final long hwnd = this.parent.handle;
                this.parent.forceResize();
                final RECT rect = new RECT();
                final RECT headerRect = new RECT();
                OS.GetClientRect(hwnd, rect);
                OS.SendMessage(hwndHeader, 4615, (long)index, headerRect);
                rect.left = headerRect.left;
                rect.right = headerRect.right;
                OS.InvalidateRect(hwnd, rect, true);
            }
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
        final long hHeap = OS.GetProcessHeap();
        final char[] buffer = this.fixMnemonic(string);
        final int byteCount = buffer.length * 2;
        final long pszText = OS.HeapAlloc(hHeap, 8, byteCount);
        OS.MoveMemory(pszText, buffer, byteCount);
        final long hwndHeader = this.parent.hwndHeader;
        if (hwndHeader == 0L) {
            return;
        }
        final HDITEM hdItem = new HDITEM();
        hdItem.mask = 2;
        hdItem.pszText = pszText;
        final long result = OS.SendMessage(hwndHeader, 4620, (long)index, hdItem);
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
        final long hwndHeader = this.parent.hwndHeader;
        if (hwndHeader == 0L) {
            return;
        }
        final HDITEM hdItem = new HDITEM();
        hdItem.mask = 1;
        hdItem.cxy = width;
        OS.SendMessage(hwndHeader, 4620, (long)index, hdItem);
        final RECT headerRect = new RECT();
        OS.SendMessage(hwndHeader, 4615, (long)index, headerRect);
        this.parent.forceResize();
        final long hwnd = this.parent.handle;
        final RECT rect = new RECT();
        OS.GetClientRect(hwnd, rect);
        rect.left = headerRect.left;
        OS.InvalidateRect(hwnd, rect, true);
        this.parent.setScrollWidth();
    }
    
    void updateToolTip(final int index) {
        final long hwndHeaderToolTip = this.parent.headerToolTipHandle;
        if (hwndHeaderToolTip != 0L) {
            final long hwndHeader = this.parent.hwndHeader;
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
