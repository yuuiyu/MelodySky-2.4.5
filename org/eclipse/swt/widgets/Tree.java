//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class Tree extends Composite
{
    TreeItem[] items;
    TreeColumn[] columns;
    int columnCount;
    ImageList imageList;
    ImageList headerImageList;
    TreeItem currentItem;
    TreeColumn sortColumn;
    RECT focusRect;
    long hwndParent;
    long hwndHeader;
    long hAnchor;
    long hInsert;
    long hSelect;
    int lastID;
    long hFirstIndexOf;
    long hLastIndexOf;
    int lastIndexOf;
    int itemCount;
    int sortDirection;
    boolean dragStarted;
    boolean gestureCompleted;
    boolean insertAfter;
    boolean shrink;
    boolean ignoreShrink;
    boolean ignoreSelect;
    boolean ignoreExpand;
    boolean ignoreDeselect;
    boolean ignoreResize;
    boolean lockSelection;
    boolean oldSelected;
    boolean newSelected;
    boolean ignoreColumnMove;
    boolean linesVisible;
    boolean customDraw;
    boolean painted;
    boolean ignoreItemHeight;
    boolean ignoreCustomDraw;
    boolean ignoreDrawForeground;
    boolean ignoreDrawBackground;
    boolean ignoreDrawFocus;
    boolean ignoreDrawSelection;
    boolean ignoreDrawHot;
    boolean ignoreFullSelection;
    boolean explorerTheme;
    boolean createdAsRTL;
    boolean headerItemDragging;
    int scrollWidth;
    int selectionForeground;
    long headerToolTipHandle;
    long itemToolTipHandle;
    long lastTimerID;
    int lastTimerCount;
    int headerBackground;
    int headerForeground;
    static final boolean ENABLE_TVS_EX_FADEINOUTEXPANDOS;
    static final int TIMER_MAX_COUNT = 8;
    static final int INSET = 3;
    static final int GRID_WIDTH = 1;
    static final int SORT_WIDTH = 10;
    static final int HEADER_MARGIN = 12;
    static final int HEADER_EXTRA = 3;
    static final int INCREMENT = 5;
    static final int EXPLORER_EXTRA = 2;
    static final int DRAG_IMAGE_SIZE = 301;
    static final long TreeProc;
    static final TCHAR TreeClass;
    static final long HeaderProc;
    static final TCHAR HeaderClass;
    
    public Tree(final Composite parent, final int style) {
        super(parent, checkStyle(style));
        this.lastTimerID = -1L;
        this.headerBackground = -1;
        this.headerForeground = -1;
    }
    
    static int checkStyle(int style) {
        if ((style & 0x10) == 0x0) {
            style |= 0x300;
        }
        if ((style & 0x100) != 0x0 && (style & 0x200) == 0x0) {
            style |= 0x200;
        }
        return Widget.checkBits(style, 4, 2, 0, 0, 0, 0);
    }
    
    void _addListener(final int eventType, final Listener listener) {
        super._addListener(eventType, listener);
        switch (eventType) {
            case 29: {
                if ((this.state & 0x8000) != 0x0) {
                    int bits = OS.GetWindowLong(this.handle, -16);
                    bits &= 0xFFFFFFEF;
                    OS.SetWindowLong(this.handle, -16, bits);
                    break;
                }
                break;
            }
            case 40:
            case 41:
            case 42: {
                this.customDraw = true;
                this.style |= 0x20000000;
                if (this.isCustomToolTip()) {
                    this.createItemToolTips();
                }
                OS.SendMessage(this.handle, 4385, 0L, 0L);
                int bits = OS.GetWindowLong(this.handle, -16);
                if (eventType == 41) {
                    bits |= 0x8000;
                }
                if ((this.style & 0x10000) != 0x0 && eventType != 41 && !this.explorerTheme) {
                    bits &= 0xFFFFEFFF;
                }
                if (bits == OS.GetWindowLong(this.handle, -16)) {
                    break;
                }
                OS.SetWindowLong(this.handle, -16, bits);
                OS.InvalidateRect(this.handle, (RECT)null, true);
                final int count = (int)OS.SendMessage(this.handle, 4357, 0L, 0L);
                if (count != 0 && (bits & 0x8000) != 0x0) {
                    OS.ShowScrollBar(this.handle, 0, false);
                    break;
                }
                break;
            }
        }
    }
    
    TreeItem _getItem(final long hItem) {
        final TVITEM tvItem = new TVITEM();
        tvItem.mask = 20;
        tvItem.hItem = hItem;
        if (OS.SendMessage(this.handle, 4414, 0L, tvItem) != 0L) {
            return this._getItem(tvItem.hItem, (int)tvItem.lParam);
        }
        return null;
    }
    
    TreeItem _getItem(final long hItem, final int id) {
        if ((this.style & 0x10000000) == 0x0) {
            return this.items[id];
        }
        return (id != -1) ? this.items[id] : new TreeItem(this, 0, -1L, -1L, hItem);
    }
    
    void _removeListener(final int eventType, final Listener listener) {
        super._removeListener(eventType, listener);
        switch (eventType) {
            case 41: {
                if ((this.style & 0x100) != 0x0 && (this.state & 0x1000) == 0x0) {
                    int bits = OS.GetWindowLong(this.handle, -16);
                    bits &= 0xFFFF7FFF;
                    OS.SetWindowLong(this.handle, -16, bits);
                    OS.InvalidateRect(this.handle, (RECT)null, true);
                    break;
                }
                break;
            }
        }
    }
    
    void _setBackgroundPixel(final int newPixel) {
        final int oldPixel = (int)OS.SendMessage(this.handle, 4383, 0L, 0L);
        if (oldPixel != newPixel) {
            if (oldPixel != -1) {
                OS.SendMessage(this.handle, 4381, 0L, -1L);
            }
            OS.SendMessage(this.handle, 4381, 0L, (long)newPixel);
            if (this.explorerTheme && Tree.ENABLE_TVS_EX_FADEINOUTEXPANDOS) {
                int bits2 = (int)OS.SendMessage(this.handle, 4397, 0L, 0L);
                if (newPixel == -1 && this.findImageControl() == null) {
                    bits2 |= 0x40;
                }
                else {
                    bits2 &= 0xFFFFFFBF;
                }
                OS.SendMessage(this.handle, 4396, 0L, (long)bits2);
            }
            if ((this.style & 0x20) != 0x0) {
                this.setCheckboxImageList();
            }
        }
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
    
    public void addTreeListener(final TreeListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(17, (Listener)typedListener);
        this.addListener(18, (Listener)typedListener);
    }
    
    long borderHandle() {
        return (this.hwndParent != 0L) ? this.hwndParent : this.handle;
    }
    
    LRESULT CDDS_ITEMPOSTPAINT(final NMTVCUSTOMDRAW nmcd, final long wParam, final long lParam) {
        if (this.ignoreCustomDraw) {
            return null;
        }
        if (nmcd.left == nmcd.right) {
            return new LRESULT(0L);
        }
        final long hDC = nmcd.hdc;
        OS.RestoreDC(hDC, -1);
        final TreeItem item = this.getItem(nmcd);
        if (item == null) {
            return null;
        }
        if (nmcd.left >= nmcd.right || nmcd.top >= nmcd.bottom) {
            return null;
        }
        if (!OS.IsWindowVisible(this.handle)) {
            return null;
        }
        if ((this.style & 0x10000) != 0x0 || this.findImageControl() != null || this.ignoreDrawSelection || this.explorerTheme) {
            OS.SetBkMode(hDC, 1);
        }
        final boolean selected = this.isItemSelected(nmcd);
        final boolean hot = this.explorerTheme && (nmcd.uItemState & 0x40) != 0x0;
        if (OS.IsWindowEnabled(this.handle) && this.explorerTheme) {
            final int bits = OS.GetWindowLong(this.handle, -16);
            if ((bits & 0x200) != 0x0) {
                OS.SetTextColor(hDC, this.getForegroundPixel());
            }
        }
        int[] order = null;
        final RECT clientRect = new RECT();
        OS.GetClientRect(this.scrolledHandle(), clientRect);
        if (this.hwndHeader != 0L) {
            OS.MapWindowPoints(this.hwndParent, this.handle, clientRect, 2);
            if (this.columnCount != 0) {
                order = new int[this.columnCount];
                OS.SendMessage(this.hwndHeader, 4625, (long)this.columnCount, order);
            }
        }
        int sortIndex = -1;
        int clrSortBk = -1;
        if (OS.IsAppThemed() && this.sortColumn != null && this.sortDirection != 0 && this.findImageControl() == null) {
            sortIndex = this.indexOf(this.sortColumn);
            clrSortBk = this.getSortColumnPixel();
        }
        int x = 0;
        Point size = null;
        for (int i = 0; i < Math.max(1, this.columnCount); ++i) {
            final int index = (order == null) ? i : order[i];
            int width = nmcd.right - nmcd.left;
            if (this.columnCount > 0 && this.hwndHeader != 0L) {
                final HDITEM hdItem = new HDITEM();
                hdItem.mask = 1;
                OS.SendMessage(this.hwndHeader, 4619, (long)index, hdItem);
                width = hdItem.cxy;
            }
            if (i == 0 && (this.style & 0x10000) != 0x0) {
                final boolean clear = !this.explorerTheme && !this.ignoreDrawSelection && this.findImageControl() == null;
                if (clear || (selected && !this.ignoreDrawSelection) || (hot && !this.ignoreDrawHot)) {
                    boolean draw = true;
                    final RECT pClipRect = new RECT();
                    OS.SetRect(pClipRect, width, nmcd.top, nmcd.right, nmcd.bottom);
                    if (this.explorerTheme) {
                        if (this.hooks(40)) {
                            final RECT itemRect;
                            final RECT rect11;
                            final RECT bounds3 = rect11 = (itemRect = item.getBounds(index, (boolean)(1 != 0), (boolean)(1 != 0), (boolean)(0 != 0), (boolean)(0 != 0), (boolean)(1 != 0), hDC));
                            rect11.left -= 2;
                            final RECT rect12;
                            final RECT rect4 = rect12 = itemRect;
                            rect12.right += 3;
                            pClipRect.left = itemRect.left;
                            pClipRect.right = itemRect.right;
                            if (this.columnCount > 0 && this.hwndHeader != 0L) {
                                final HDITEM hdItem2 = new HDITEM();
                                hdItem2.mask = 1;
                                OS.SendMessage(this.hwndHeader, 4619, (long)index, hdItem2);
                                pClipRect.right = Math.min(pClipRect.right, nmcd.left + hdItem2.cxy);
                            }
                        }
                        final RECT pRect = new RECT();
                        OS.SetRect(pRect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
                        if (this.columnCount > 0 && this.hwndHeader != 0L) {
                            int totalWidth = 0;
                            final HDITEM hdItem3 = new HDITEM();
                            hdItem3.mask = 1;
                            for (int j = 0; j < this.columnCount; ++j) {
                                OS.SendMessage(this.hwndHeader, 4619, (long)j, hdItem3);
                                totalWidth += hdItem3.cxy;
                            }
                            if (totalWidth > clientRect.right - clientRect.left) {
                                pRect.left = 0;
                                pRect.right = totalWidth;
                            }
                            else {
                                pRect.left = clientRect.left;
                                pRect.right = clientRect.right;
                            }
                        }
                        draw = false;
                        final long hTheme = OS.OpenThemeData(this.handle, Display.TREEVIEW);
                        int iStateId = selected ? 3 : 2;
                        if (OS.GetFocus() != this.handle && selected && !hot) {
                            iStateId = 5;
                        }
                        OS.DrawThemeBackground(hTheme, hDC, 1, iStateId, pRect, pClipRect);
                        OS.CloseThemeData(hTheme);
                    }
                    if (draw) {
                        this.fillBackground(hDC, OS.GetBkColor(hDC), pClipRect);
                    }
                }
            }
            if (x + width > clientRect.left) {
                RECT rect5 = new RECT();
                RECT backgroundRect = null;
                boolean drawItem = true;
                boolean drawText = true;
                boolean drawImage = true;
                boolean drawBackground = false;
                if (i == 0) {
                    drawItem = (drawImage = (drawText = false));
                    if (this.findImageControl() != null) {
                        if (this.explorerTheme) {
                            if (OS.IsWindowEnabled(this.handle) && !this.hooks(40)) {
                                Image image = null;
                                if (index == 0) {
                                    image = item.image;
                                }
                                else {
                                    final Image[] images = item.images;
                                    if (images != null) {
                                        image = images[index];
                                    }
                                }
                                if (image != null) {
                                    final Rectangle bounds4 = image.getBounds();
                                    if (size == null) {
                                        size = DPIUtil.autoScaleDown(this.getImageSize());
                                    }
                                    if (!this.ignoreDrawForeground) {
                                        final GCData data = new GCData();
                                        data.device = (Device)this.display;
                                        final GC gc = GC.win32_new(hDC, data);
                                        final RECT iconRect = item.getBounds(index, false, true, false, false, true, hDC);
                                        gc.setClipping(DPIUtil.autoScaleDown(new Rectangle(iconRect.left, iconRect.top, iconRect.right - iconRect.left, iconRect.bottom - iconRect.top)));
                                        gc.drawImage(image, 0, 0, bounds4.width, bounds4.height, DPIUtil.autoScaleDown(iconRect.left), DPIUtil.autoScaleDown(iconRect.top), size.x, size.y);
                                        OS.SelectClipRgn(hDC, 0L);
                                        gc.dispose();
                                    }
                                }
                            }
                        }
                        else {
                            drawItem = (drawText = (drawBackground = true));
                            rect5 = item.getBounds(index, true, false, false, false, true, hDC);
                            if (this.linesVisible) {
                                final RECT rect13;
                                final RECT rect6 = rect13 = rect5;
                                ++rect13.right;
                                final RECT rect14;
                                final RECT rect7 = rect14 = rect5;
                                ++rect14.bottom;
                            }
                        }
                    }
                    if (selected && !this.ignoreDrawSelection && !this.ignoreDrawBackground) {
                        if (!this.explorerTheme) {
                            this.fillBackground(hDC, OS.GetBkColor(hDC), rect5);
                        }
                        drawBackground = false;
                    }
                    backgroundRect = rect5;
                    if (this.hooks(40)) {
                        drawItem = (drawText = (drawImage = true));
                        rect5 = item.getBounds(index, true, true, false, false, true, hDC);
                        if ((this.style & 0x10000) != 0x0) {
                            backgroundRect = rect5;
                        }
                        else {
                            backgroundRect = item.getBounds(index, true, false, false, false, true, hDC);
                        }
                    }
                }
                else {
                    this.selectionForeground = -1;
                    final boolean ignoreDrawForeground = false;
                    this.ignoreDrawHot = false;
                    this.ignoreDrawFocus = false;
                    this.ignoreDrawSelection = false;
                    this.ignoreDrawBackground = false;
                    this.ignoreDrawForeground = false;
                    OS.SetRect(rect5, x, nmcd.top, x + width, nmcd.bottom);
                    backgroundRect = rect5;
                }
                int clrText = -1;
                int clrTextBk = -1;
                long hFont = item.fontHandle(index);
                if (this.selectionForeground != -1) {
                    clrText = this.selectionForeground;
                }
                if (OS.IsWindowEnabled(this.handle)) {
                    boolean drawForeground = false;
                    if (selected) {
                        if (i != 0 && (this.style & 0x10000) == 0x0) {
                            OS.SetTextColor(hDC, this.getForegroundPixel());
                            OS.SetBkColor(hDC, this.getBackgroundPixel());
                            drawForeground = (drawBackground = true);
                        }
                    }
                    else {
                        drawForeground = (drawBackground = true);
                    }
                    if (drawForeground) {
                        clrText = ((item.cellForeground != null) ? item.cellForeground[index] : -1);
                        if (clrText == -1) {
                            clrText = item.foreground;
                        }
                    }
                    if (drawBackground) {
                        clrTextBk = ((item.cellBackground != null) ? item.cellBackground[index] : -1);
                        if (clrTextBk == -1) {
                            clrTextBk = item.background;
                        }
                        if (clrTextBk == -1 && index == sortIndex) {
                            clrTextBk = clrSortBk;
                        }
                    }
                }
                else if (clrTextBk == -1 && index == sortIndex) {
                    drawBackground = true;
                    clrTextBk = clrSortBk;
                }
                if (this.explorerTheme && (selected || (nmcd.uItemState & 0x40) != 0x0)) {
                    if ((this.style & 0x10000) != 0x0) {
                        drawBackground = false;
                    }
                    else if (i == 0) {
                        drawBackground = false;
                        if (!this.hooks(40)) {
                            drawText = false;
                        }
                    }
                }
                if (drawItem) {
                    if (i != 0) {
                        if (this.hooks(41)) {
                            this.sendMeasureItemEvent(item, index, hDC, selected ? 2 : 0);
                            if (this.isDisposed()) {
                                break;
                            }
                            if (item.isDisposed()) {
                                break;
                            }
                        }
                        if (this.hooks(40)) {
                            final RECT cellRect = item.getBounds(index, true, true, true, true, true, hDC);
                            final int nSavedDC = OS.SaveDC(hDC);
                            final GCData data2 = new GCData();
                            data2.device = (Device)this.display;
                            data2.foreground = OS.GetTextColor(hDC);
                            data2.background = OS.GetBkColor(hDC);
                            if (!selected || (this.style & 0x10000) == 0x0) {
                                if (clrText != -1) {
                                    data2.foreground = clrText;
                                }
                                if (clrTextBk != -1) {
                                    data2.background = clrTextBk;
                                }
                            }
                            data2.font = item.getFont(index);
                            data2.uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
                            final GC gc2 = GC.win32_new(hDC, data2);
                            final Event event = new Event();
                            event.item = (Widget)item;
                            event.index = index;
                            event.gc = gc2;
                            final Event event12;
                            final Event event2 = event12 = event;
                            event12.detail |= 0x10;
                            if (clrTextBk != -1) {
                                final Event event13;
                                final Event event3 = event13 = event;
                                event13.detail |= 0x8;
                            }
                            if ((this.style & 0x10000) != 0x0) {
                                if (hot) {
                                    final Event event14;
                                    final Event event4 = event14 = event;
                                    event14.detail |= 0x20;
                                }
                                if (selected) {
                                    final Event event15;
                                    final Event event5 = event15 = event;
                                    event15.detail |= 0x2;
                                }
                                if (!this.explorerTheme && OS.SendMessage(this.handle, 4362, 9L, 0L) == nmcd.dwItemSpec && this.handle == OS.GetFocus()) {
                                    final int uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
                                    if ((uiState & 0x1) == 0x0) {
                                        final Event event16;
                                        final Event event6 = event16 = event;
                                        event16.detail |= 0x4;
                                    }
                                }
                            }
                            final Rectangle boundsInPixels = new Rectangle(cellRect.left, cellRect.top, cellRect.right - cellRect.left, cellRect.bottom - cellRect.top);
                            event.setBoundsInPixels(boundsInPixels);
                            gc2.setClipping(DPIUtil.autoScaleDown(boundsInPixels));
                            this.sendEvent(40, event);
                            event.gc = null;
                            final int newTextClr = data2.foreground;
                            gc2.dispose();
                            OS.RestoreDC(hDC, nSavedDC);
                            if (this.isDisposed()) {
                                break;
                            }
                            if (item.isDisposed()) {
                                break;
                            }
                            if (event.doit) {
                                this.ignoreDrawForeground = ((event.detail & 0x10) == 0x0);
                                this.ignoreDrawBackground = ((event.detail & 0x8) == 0x0);
                                if ((this.style & 0x10000) != 0x0) {
                                    this.ignoreDrawSelection = ((event.detail & 0x2) == 0x0);
                                    this.ignoreDrawFocus = ((event.detail & 0x4) == 0x0);
                                    this.ignoreDrawHot = ((event.detail & 0x20) == 0x0);
                                }
                            }
                            else {
                                final boolean ignoreDrawForeground2 = true;
                                this.ignoreDrawHot = true;
                                this.ignoreDrawFocus = true;
                                this.ignoreDrawSelection = true;
                                this.ignoreDrawBackground = true;
                                this.ignoreDrawForeground = true;
                            }
                            if (selected && this.ignoreDrawSelection) {
                                this.ignoreDrawHot = true;
                            }
                            if ((this.style & 0x10000) != 0x0) {
                                if (this.ignoreDrawSelection) {
                                    this.ignoreFullSelection = true;
                                }
                                if (!this.ignoreDrawSelection || !this.ignoreDrawHot) {
                                    if (!selected && !hot) {
                                        this.selectionForeground = OS.GetSysColor(14);
                                    }
                                    else if (!this.explorerTheme) {
                                        drawBackground = true;
                                        this.ignoreDrawBackground = false;
                                        if ((this.handle == OS.GetFocus() || this.display.getHighContrast()) && OS.IsWindowEnabled(this.handle)) {
                                            clrTextBk = OS.GetSysColor(13);
                                        }
                                        else {
                                            clrTextBk = OS.GetSysColor(15);
                                        }
                                        if (!this.ignoreFullSelection && index == this.columnCount - 1) {
                                            final RECT selectionRect = new RECT();
                                            OS.SetRect(selectionRect, backgroundRect.left, backgroundRect.top, nmcd.right, backgroundRect.bottom);
                                            backgroundRect = selectionRect;
                                        }
                                    }
                                    else {
                                        final RECT pRect2 = new RECT();
                                        OS.SetRect(pRect2, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
                                        if (this.columnCount > 0 && this.hwndHeader != 0L) {
                                            int totalWidth2 = 0;
                                            final HDITEM hdItem4 = new HDITEM();
                                            hdItem4.mask = 1;
                                            for (int k = 0; k < this.columnCount; ++k) {
                                                OS.SendMessage(this.hwndHeader, 4619, (long)k, hdItem4);
                                                totalWidth2 += hdItem4.cxy;
                                            }
                                            if (totalWidth2 > clientRect.right - clientRect.left) {
                                                pRect2.left = 0;
                                                pRect2.right = totalWidth2;
                                            }
                                            else {
                                                pRect2.left = clientRect.left;
                                                pRect2.right = clientRect.right;
                                            }
                                            if (index == this.columnCount - 1) {
                                                final RECT selectionRect2 = new RECT();
                                                OS.SetRect(selectionRect2, backgroundRect.left, backgroundRect.top, pRect2.right, backgroundRect.bottom);
                                                backgroundRect = selectionRect2;
                                            }
                                        }
                                        final long hTheme2 = OS.OpenThemeData(this.handle, Display.TREEVIEW);
                                        int iStateId2 = selected ? 3 : 2;
                                        if (OS.GetFocus() != this.handle && selected && !hot) {
                                            iStateId2 = 5;
                                        }
                                        OS.DrawThemeBackground(hTheme2, hDC, 1, iStateId2, pRect2, backgroundRect);
                                        OS.CloseThemeData(hTheme2);
                                    }
                                }
                                else if (selected) {
                                    this.selectionForeground = newTextClr;
                                    if (!this.explorerTheme && clrTextBk == -1 && OS.IsWindowEnabled(this.handle)) {
                                        Control control = this.findBackgroundControl();
                                        if (control == null) {
                                            control = (Control)this;
                                        }
                                        clrTextBk = control.getBackgroundPixel();
                                    }
                                }
                            }
                        }
                        if (this.selectionForeground != -1) {
                            clrText = this.selectionForeground;
                        }
                    }
                    if (!this.ignoreDrawBackground) {
                        if (clrTextBk != -1) {
                            if (drawBackground) {
                                this.fillBackground(hDC, clrTextBk, backgroundRect);
                            }
                        }
                        else {
                            final Control control2 = this.findImageControl();
                            if (control2 != null) {
                                if (i == 0) {
                                    final int right = Math.min(rect5.right, width);
                                    OS.SetRect(rect5, rect5.left, rect5.top, right, rect5.bottom);
                                    if (drawBackground) {
                                        this.fillImageBackground(hDC, control2, rect5, 0, 0);
                                    }
                                }
                                else if (drawBackground) {
                                    this.fillImageBackground(hDC, control2, rect5, 0, 0);
                                }
                            }
                        }
                    }
                    final RECT rect15;
                    final RECT rect8 = rect15 = rect5;
                    rect15.left += 2;
                    if (drawImage) {
                        Image image2 = null;
                        if (index == 0) {
                            image2 = item.image;
                        }
                        else {
                            final Image[] images2 = item.images;
                            if (images2 != null) {
                                image2 = images2[index];
                            }
                        }
                        final int inset = (i != 0) ? 3 : 0;
                        final int offset = (i != 0) ? 3 : 5;
                        if (image2 != null) {
                            final Rectangle bounds5 = image2.getBounds();
                            if (size == null) {
                                size = DPIUtil.autoScaleDown(this.getImageSize());
                            }
                            if (!this.ignoreDrawForeground) {
                                final int y1 = rect5.top + DPIUtil.autoScaleUp((this.getItemHeight() - size.y) / 2);
                                final int x2 = Math.max(rect5.left, rect5.left - inset + 1);
                                final GCData data3 = new GCData();
                                data3.device = (Device)this.display;
                                final GC gc3 = GC.win32_new(hDC, data3);
                                gc3.setClipping(DPIUtil.autoScaleDown(new Rectangle(x2, rect5.top, rect5.right - x2, rect5.bottom - rect5.top)));
                                gc3.drawImage(image2, 0, 0, bounds5.width, bounds5.height, DPIUtil.autoScaleDown(x2), DPIUtil.autoScaleDown(y1), size.x, size.y);
                                OS.SelectClipRgn(hDC, 0L);
                                gc3.dispose();
                            }
                            OS.SetRect(rect5, rect5.left + DPIUtil.autoScaleUp(size.x) + offset, rect5.top, rect5.right - inset, rect5.bottom);
                        }
                        else if (i == 0) {
                            if (OS.SendMessage(this.handle, 4360, 0L, 0L) != 0L) {
                                if (size == null) {
                                    size = this.getImageSize();
                                }
                                rect5.left = Math.min(rect5.left + size.x + offset, rect5.right);
                            }
                        }
                        else {
                            OS.SetRect(rect5, rect5.left + offset, rect5.top, rect5.right - inset, rect5.bottom);
                        }
                    }
                    if (drawText && rect5.left < rect5.right) {
                        String string = null;
                        if (index == 0) {
                            string = item.text;
                        }
                        else {
                            final String[] strings = item.strings;
                            if (strings != null) {
                                string = strings[index];
                            }
                        }
                        if (string != null) {
                            if (hFont != -1L) {
                                hFont = OS.SelectObject(hDC, hFont);
                            }
                            if (clrText != -1) {
                                clrText = OS.SetTextColor(hDC, clrText);
                            }
                            if (clrTextBk != -1) {
                                clrTextBk = OS.SetBkColor(hDC, clrTextBk);
                            }
                            int flags = 2084;
                            if (i != 0) {
                                flags |= 0x8000;
                            }
                            final TreeColumn column = (this.columns != null) ? this.columns[index] : null;
                            if (column != null) {
                                if ((column.style & 0x1000000) != 0x0) {
                                    flags |= 0x1;
                                }
                                if ((column.style & 0x20000) != 0x0) {
                                    flags |= 0x2;
                                }
                            }
                            if (string != null && string.length() > 8192) {
                                string = string.substring(0, 8192 - "...".length()) + "...";
                            }
                            final char[] buffer = string.toCharArray();
                            if (!this.ignoreDrawForeground) {
                                OS.DrawText(hDC, buffer, buffer.length, rect5, flags);
                            }
                            OS.DrawText(hDC, buffer, buffer.length, rect5, flags | 0x400);
                            if (hFont != -1L) {
                                hFont = OS.SelectObject(hDC, hFont);
                            }
                            if (clrText != -1) {
                                clrText = OS.SetTextColor(hDC, clrText);
                            }
                            if (clrTextBk != -1) {
                                clrTextBk = OS.SetBkColor(hDC, clrTextBk);
                            }
                        }
                    }
                }
                if (this.selectionForeground != -1) {
                    clrText = this.selectionForeground;
                }
                if (this.hooks(42)) {
                    final RECT itemRect2 = item.getBounds(index, true, true, false, false, false, hDC);
                    final int nSavedDC = OS.SaveDC(hDC);
                    final GCData data2 = new GCData();
                    data2.device = (Device)this.display;
                    data2.font = item.getFont(index);
                    data2.foreground = OS.GetTextColor(hDC);
                    data2.background = OS.GetBkColor(hDC);
                    if (selected && (this.style & 0x10000) != 0x0) {
                        if (this.selectionForeground != -1) {
                            data2.foreground = this.selectionForeground;
                        }
                    }
                    else {
                        if (clrText != -1) {
                            data2.foreground = clrText;
                        }
                        if (clrTextBk != -1) {
                            data2.background = clrTextBk;
                        }
                    }
                    data2.uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
                    final GC gc2 = GC.win32_new(hDC, data2);
                    final Event event = new Event();
                    event.item = (Widget)item;
                    event.index = index;
                    event.gc = gc2;
                    final Event event17;
                    final Event event7 = event17 = event;
                    event17.detail |= 0x10;
                    if (clrTextBk != -1) {
                        final Event event18;
                        final Event event8 = event18 = event;
                        event18.detail |= 0x8;
                    }
                    if (hot) {
                        final Event event19;
                        final Event event9 = event19 = event;
                        event19.detail |= 0x20;
                    }
                    if (selected && (i == 0 || (this.style & 0x10000) != 0x0)) {
                        final Event event20;
                        final Event event10 = event20 = event;
                        event20.detail |= 0x2;
                    }
                    if (!this.explorerTheme && OS.SendMessage(this.handle, 4362, 9L, 0L) == nmcd.dwItemSpec && (i == 0 || (this.style & 0x10000) != 0x0) && this.handle == OS.GetFocus()) {
                        final int uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
                        if ((uiState & 0x1) == 0x0) {
                            final Event event21;
                            final Event event11 = event21 = event;
                            event21.detail |= 0x4;
                        }
                    }
                    event.setBoundsInPixels(new Rectangle(itemRect2.left, itemRect2.top, itemRect2.right - itemRect2.left, itemRect2.bottom - itemRect2.top));
                    final RECT cellRect2 = item.getBounds(index, true, true, true, true, true, hDC);
                    final int cellWidth = cellRect2.right - cellRect2.left;
                    final int cellHeight = cellRect2.bottom - cellRect2.top;
                    gc2.setClipping(DPIUtil.autoScaleDown(new Rectangle(cellRect2.left, cellRect2.top, cellWidth, cellHeight)));
                    this.sendEvent(42, event);
                    if (data2.focusDrawn) {
                        this.focusRect = null;
                    }
                    event.gc = null;
                    gc2.dispose();
                    OS.RestoreDC(hDC, nSavedDC);
                    if (this.isDisposed()) {
                        break;
                    }
                    if (item.isDisposed()) {
                        break;
                    }
                }
            }
            x += width;
            if (x > clientRect.right) {
                break;
            }
        }
        if (this.linesVisible) {
            if ((this.style & 0x10000) != 0x0 && this.columnCount != 0) {
                final HDITEM hdItem5 = new HDITEM();
                hdItem5.mask = 1;
                OS.SendMessage(this.hwndHeader, 4619, 0L, hdItem5);
                final RECT rect9 = new RECT();
                OS.SetRect(rect9, nmcd.left + hdItem5.cxy, nmcd.top, nmcd.right, nmcd.bottom);
                OS.DrawEdge(hDC, rect9, 8, 8);
            }
            final RECT rect10 = new RECT();
            OS.SetRect(rect10, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
            OS.DrawEdge(hDC, rect10, 8, 8);
        }
        if (!this.ignoreDrawFocus && this.focusRect != null) {
            OS.DrawFocusRect(hDC, this.focusRect);
            this.focusRect = null;
        }
        else if (!this.explorerTheme && this.handle == OS.GetFocus()) {
            final int uiState2 = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
            if ((uiState2 & 0x1) == 0x0) {
                final long hItem = OS.SendMessage(this.handle, 4362, 9L, 0L);
                if (hItem == item.handle && !this.ignoreDrawFocus && this.findImageControl() != null) {
                    if ((this.style & 0x10000) != 0x0) {
                        final RECT focusRect = new RECT();
                        OS.SetRect(focusRect, 0, nmcd.top, clientRect.right + 1, nmcd.bottom);
                        OS.DrawFocusRect(hDC, focusRect);
                    }
                    else {
                        final int index2 = (int)OS.SendMessage(this.hwndHeader, 4623, 0L, 0L);
                        final RECT focusRect2 = item.getBounds(index2, true, false, false, false, false, hDC);
                        final RECT clipRect = item.getBounds(index2, true, false, false, false, true, hDC);
                        OS.IntersectClipRect(hDC, clipRect.left, clipRect.top, clipRect.right, clipRect.bottom);
                        OS.DrawFocusRect(hDC, focusRect2);
                        OS.SelectClipRgn(hDC, 0L);
                    }
                }
            }
        }
        return new LRESULT(0L);
    }
    
    LRESULT CDDS_ITEMPREPAINT(final NMTVCUSTOMDRAW nmcd, final long wParam, final long lParam) {
        final TreeItem item = this.getItem(nmcd);
        if (item == null) {
            return null;
        }
        final long hDC = nmcd.hdc;
        final int index = (this.hwndHeader != 0L) ? ((int)OS.SendMessage(this.hwndHeader, 4623, 0L, 0L)) : 0;
        final long hFont = item.fontHandle(index);
        if (hFont != -1L) {
            OS.SelectObject(hDC, hFont);
        }
        if (this.ignoreCustomDraw || nmcd.left == nmcd.right) {
            return new LRESULT((hFont == -1L) ? 0L : 2L);
        }
        RECT clipRect = null;
        if (this.columnCount != 0) {
            clipRect = new RECT();
            final HDITEM hdItem = new HDITEM();
            hdItem.mask = 1;
            OS.SendMessage(this.hwndHeader, 4619, (long)index, hdItem);
            OS.SetRect(clipRect, nmcd.left, nmcd.top, nmcd.left + hdItem.cxy, nmcd.bottom);
        }
        int clrText = -1;
        int clrTextBk = -1;
        if (OS.IsWindowEnabled(this.handle)) {
            clrText = ((item.cellForeground != null) ? item.cellForeground[index] : -1);
            if (clrText == -1) {
                clrText = item.foreground;
            }
            clrTextBk = ((item.cellBackground != null) ? item.cellBackground[index] : -1);
            if (clrTextBk == -1) {
                clrTextBk = item.background;
            }
        }
        int clrSortBk = -1;
        if (OS.IsAppThemed() && this.sortColumn != null && this.sortDirection != 0 && this.findImageControl() == null && this.indexOf(this.sortColumn) == index) {
            clrSortBk = this.getSortColumnPixel();
            if (clrTextBk == -1) {
                clrTextBk = clrSortBk;
            }
        }
        final boolean selected = this.isItemSelected(nmcd);
        final boolean hot = this.explorerTheme && (nmcd.uItemState & 0x40) != 0x0;
        boolean focused = this.explorerTheme && (nmcd.uItemState & 0x10) != 0x0;
        if (OS.IsWindowVisible(this.handle) && nmcd.left < nmcd.right && nmcd.top < nmcd.bottom) {
            if (hFont != -1L) {
                OS.SelectObject(hDC, hFont);
            }
            if (this.linesVisible) {
                final RECT rect = new RECT();
                OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
                OS.DrawEdge(hDC, rect, 8, 8);
            }
            Event measureEvent = null;
            Rectangle boundsInPixels = null;
            if (this.hooks(41)) {
                measureEvent = this.sendMeasureItemEvent(item, index, hDC, selected ? 2 : 0);
                boundsInPixels = measureEvent.getBoundsInPixels();
                if (this.isDisposed() || item.isDisposed()) {
                    return null;
                }
            }
            this.selectionForeground = -1;
            final boolean b = false;
            this.ignoreFullSelection = false;
            this.ignoreDrawHot = false;
            this.ignoreDrawFocus = false;
            this.ignoreDrawSelection = false;
            this.ignoreDrawBackground = false;
            this.ignoreDrawForeground = false;
            if (this.hooks(40)) {
                final RECT rect2 = new RECT();
                OS.SetRect(rect2, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
                final RECT cellRect = item.getBounds(index, true, true, true, true, true, hDC);
                if (clrSortBk != -1) {
                    this.drawBackground(hDC, cellRect, clrSortBk, 0, 0);
                }
                else if (OS.IsWindowEnabled(this.handle) || this.findImageControl() != null) {
                    this.drawBackground(hDC, rect2);
                }
                else {
                    this.fillBackground(hDC, OS.GetBkColor(hDC), rect2);
                }
                final int nSavedDC = OS.SaveDC(hDC);
                final GCData data = new GCData();
                data.device = (Device)this.display;
                if (selected && this.explorerTheme) {
                    data.foreground = OS.GetSysColor(8);
                }
                else {
                    data.foreground = OS.GetTextColor(hDC);
                }
                data.background = OS.GetBkColor(hDC);
                if (!selected) {
                    if (clrText != -1) {
                        data.foreground = clrText;
                    }
                    if (clrTextBk != -1) {
                        data.background = clrTextBk;
                    }
                }
                data.uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
                data.font = item.getFont(index);
                final GC gc = GC.win32_new(hDC, data);
                final Event event = new Event();
                event.index = index;
                event.item = (Widget)item;
                event.gc = gc;
                final Event event7;
                final Event event2 = event7 = event;
                event7.detail |= 0x10;
                if (clrTextBk != -1) {
                    final Event event8;
                    final Event event3 = event8 = event;
                    event8.detail |= 0x8;
                }
                if (hot) {
                    final Event event9;
                    final Event event4 = event9 = event;
                    event9.detail |= 0x20;
                }
                if (selected) {
                    final Event event10;
                    final Event event5 = event10 = event;
                    event10.detail |= 0x2;
                }
                if (OS.SendMessage(this.handle, 4362, 9L, 0L) == nmcd.dwItemSpec && this.handle == OS.GetFocus()) {
                    final int uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
                    if ((uiState & 0x1) == 0x0 && (!this.explorerTheme || !selected)) {
                        focused = true;
                        final Event event11;
                        final Event event6 = event11 = event;
                        event11.detail |= 0x4;
                    }
                }
                final Rectangle boundsInPixels2 = new Rectangle(cellRect.left, cellRect.top, cellRect.right - cellRect.left, cellRect.bottom - cellRect.top);
                event.setBoundsInPixels(boundsInPixels2);
                gc.setClipping(DPIUtil.autoScaleDown(boundsInPixels2));
                this.sendEvent(40, event);
                event.gc = null;
                final int newTextClr = data.foreground;
                gc.dispose();
                OS.RestoreDC(hDC, nSavedDC);
                if (this.isDisposed() || item.isDisposed()) {
                    return null;
                }
                if (event.doit) {
                    this.ignoreDrawForeground = ((event.detail & 0x10) == 0x0);
                    this.ignoreDrawBackground = ((event.detail & 0x8) == 0x0);
                    this.ignoreDrawSelection = ((event.detail & 0x2) == 0x0);
                    this.ignoreDrawFocus = ((event.detail & 0x4) == 0x0);
                    this.ignoreDrawHot = ((event.detail & 0x20) == 0x0);
                }
                else {
                    final boolean ignoreDrawForeground = true;
                    this.ignoreDrawHot = true;
                    this.ignoreDrawFocus = true;
                    this.ignoreDrawSelection = true;
                    this.ignoreDrawBackground = true;
                    this.ignoreDrawForeground = true;
                }
                if (selected && this.ignoreDrawSelection) {
                    this.ignoreDrawHot = true;
                }
                if (!this.ignoreDrawBackground && clrTextBk != -1) {
                    boolean draw = !selected && !hot;
                    if (!this.explorerTheme && selected) {
                        draw = !this.ignoreDrawSelection;
                    }
                    if (draw) {
                        if (this.columnCount == 0) {
                            if ((this.style & 0x10000) != 0x0) {
                                this.fillBackground(hDC, clrTextBk, rect2);
                            }
                            else {
                                final RECT textRect = item.getBounds(index, true, false, false, false, true, hDC);
                                if (measureEvent != null) {
                                    textRect.right = Math.min(cellRect.right, boundsInPixels.x + boundsInPixels.width);
                                }
                                this.fillBackground(hDC, clrTextBk, textRect);
                            }
                        }
                        else {
                            this.fillBackground(hDC, clrTextBk, cellRect);
                        }
                    }
                }
                if (this.ignoreDrawSelection) {
                    this.ignoreFullSelection = true;
                }
                if (!this.ignoreDrawSelection || !this.ignoreDrawHot) {
                    if (!selected && !hot) {
                        final int getSysColor = OS.GetSysColor(14);
                        this.selectionForeground = getSysColor;
                        clrText = getSysColor;
                    }
                    if (this.explorerTheme) {
                        if ((this.style & 0x10000) == 0x0) {
                            final RECT pRect = item.getBounds(index, true, true, false, false, false, hDC);
                            final RECT pClipRect = item.getBounds(index, true, true, true, false, true, hDC);
                            if (measureEvent != null) {
                                pRect.right = Math.min(pClipRect.right, boundsInPixels.x + boundsInPixels.width);
                            }
                            else {
                                final RECT rect14;
                                final RECT rect3 = rect14 = pRect;
                                rect14.right += 2;
                                final RECT rect15;
                                final RECT rect4 = rect15 = pClipRect;
                                rect15.right += 2;
                            }
                            final RECT rect16;
                            final RECT rect5 = rect16 = pRect;
                            rect16.left -= 2;
                            final RECT rect17;
                            final RECT rect6 = rect17 = pClipRect;
                            rect17.left -= 2;
                            final long hTheme = OS.OpenThemeData(this.handle, Display.TREEVIEW);
                            int iStateId = selected ? 3 : 2;
                            if (OS.GetFocus() != this.handle && selected && !hot) {
                                iStateId = 5;
                            }
                            OS.DrawThemeBackground(hTheme, hDC, 1, iStateId, pRect, pClipRect);
                            OS.CloseThemeData(hTheme);
                        }
                    }
                    else if ((this.style & 0x10000) != 0x0) {
                        if ((this.style & 0x10000) != 0x0 && this.columnCount == 0) {
                            this.fillBackground(hDC, OS.GetBkColor(hDC), rect2);
                        }
                        else {
                            this.fillBackground(hDC, OS.GetBkColor(hDC), cellRect);
                        }
                    }
                    else {
                        final RECT textRect2 = item.getBounds(index, true, false, false, false, true, hDC);
                        if (measureEvent != null) {
                            textRect2.right = Math.min(cellRect.right, boundsInPixels.x + boundsInPixels.width);
                        }
                        this.fillBackground(hDC, OS.GetBkColor(hDC), textRect2);
                    }
                }
                else {
                    if (selected || hot) {
                        final int selectionForeground = newTextClr;
                        this.selectionForeground = selectionForeground;
                        clrText = selectionForeground;
                        final boolean b2 = true;
                        this.ignoreDrawHot = true;
                        this.ignoreDrawSelection = true;
                    }
                    if (this.explorerTheme) {
                        nmcd.uItemState |= 0x4;
                        final int newColor = (clrText == -1) ? this.getForegroundPixel() : clrText;
                        if (nmcd.clrText == newColor) {
                            nmcd.clrText |= 0x20000000;
                            if (nmcd.clrText == newColor) {
                                nmcd.clrText &= 0xDFFFFFFF;
                            }
                        }
                        else {
                            nmcd.clrText = newColor;
                        }
                        OS.MoveMemory(lParam, nmcd, NMTVCUSTOMDRAW.sizeof);
                    }
                }
                if (focused && !this.ignoreDrawFocus && (this.style & 0x10000) == 0x0) {
                    final RECT textRect2 = item.getBounds(index, true, this.explorerTheme, false, false, true, hDC);
                    if (measureEvent != null) {
                        textRect2.right = Math.min(cellRect.right, boundsInPixels.x + boundsInPixels.width);
                    }
                    nmcd.uItemState &= 0xFFFFFFEF;
                    OS.MoveMemory(lParam, nmcd, NMTVCUSTOMDRAW.sizeof);
                    this.focusRect = textRect2;
                }
                if (this.explorerTheme) {
                    if (selected || (hot && this.ignoreDrawHot)) {
                        nmcd.uItemState &= 0xFFFFFFBF;
                    }
                    OS.MoveMemory(lParam, nmcd, NMTVCUSTOMDRAW.sizeof);
                }
                final RECT itemRect = item.getBounds(index, true, true, false, false, false, hDC);
                OS.SaveDC(hDC);
                OS.SelectClipRgn(hDC, 0L);
                if (this.explorerTheme) {
                    final RECT rect18;
                    final RECT rect7 = rect18 = itemRect;
                    rect18.left -= 2;
                    final RECT rect19;
                    final RECT rect8 = rect19 = itemRect;
                    rect19.right += 2;
                }
                final RECT rect20;
                final RECT rect9 = rect20 = itemRect;
                ++rect20.right;
                if (this.linesVisible) {
                    final RECT rect21;
                    final RECT rect10 = rect21 = itemRect;
                    ++rect21.bottom;
                }
                if (clipRect != null) {
                    OS.IntersectClipRect(hDC, clipRect.left, clipRect.top, clipRect.right, clipRect.bottom);
                }
                OS.ExcludeClipRect(hDC, itemRect.left, itemRect.top, itemRect.right, itemRect.bottom);
                return new LRESULT(16L);
            }
            else if ((this.style & 0x10000) != 0x0) {
                final int bits = OS.GetWindowLong(this.handle, -16);
                if ((bits & 0x1000) == 0x0) {
                    final RECT rect11 = new RECT();
                    OS.SetRect(rect11, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
                    if (selected) {
                        this.fillBackground(hDC, OS.GetBkColor(hDC), rect11);
                    }
                    else if (OS.IsWindowEnabled(this.handle)) {
                        this.drawBackground(hDC, rect11);
                    }
                    nmcd.uItemState &= 0xFFFFFFEF;
                    OS.MoveMemory(lParam, nmcd, NMTVCUSTOMDRAW.sizeof);
                }
            }
        }
        LRESULT result = null;
        if (clrText == -1 && clrTextBk == -1 && hFont == -1L) {
            result = new LRESULT(16L);
        }
        else {
            result = new LRESULT(18L);
            if (hFont != -1L) {
                OS.SelectObject(hDC, hFont);
            }
            if (OS.IsWindowEnabled(this.handle) && OS.IsWindowVisible(this.handle)) {
                if (clrTextBk != -1) {
                    final int bits2 = OS.GetWindowLong(this.handle, -16);
                    if ((bits2 & 0x1000) == 0x0) {
                        if (this.columnCount != 0 && this.hwndHeader != 0L) {
                            final RECT rect12 = new RECT();
                            final HDITEM hdItem2 = new HDITEM();
                            hdItem2.mask = 1;
                            OS.SendMessage(this.hwndHeader, 4619, (long)index, hdItem2);
                            OS.SetRect(rect12, nmcd.left, nmcd.top, nmcd.left + hdItem2.cxy, nmcd.bottom);
                            if (!OS.IsAppThemed()) {
                                final RECT itemRect2 = new RECT();
                                if (OS.TreeView_GetItemRect(this.handle, item.handle, itemRect2, true)) {
                                    rect12.left = Math.min(itemRect2.left, rect12.right);
                                }
                            }
                            if ((this.style & 0x10000) != 0x0) {
                                if (!selected) {
                                    this.fillBackground(hDC, clrTextBk, rect12);
                                }
                            }
                            else {
                                this.fillBackground(hDC, clrTextBk, rect12);
                            }
                        }
                        else if ((this.style & 0x10000) != 0x0) {
                            final RECT rect12 = new RECT();
                            OS.SetRect(rect12, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
                            if (!selected) {
                                this.fillBackground(hDC, clrTextBk, rect12);
                            }
                        }
                    }
                }
                if (!selected) {
                    nmcd.clrText = ((clrText == -1) ? this.getForegroundPixel() : clrText);
                    nmcd.clrTextBk = ((clrTextBk == -1) ? this.getBackgroundPixel() : clrTextBk);
                    OS.MoveMemory(lParam, nmcd, NMTVCUSTOMDRAW.sizeof);
                }
            }
        }
        if (OS.IsWindowEnabled(this.handle)) {
            if (this.explorerTheme && this.findImageControl() != null && !selected && (nmcd.uItemState & 0x41) == 0x0) {
                nmcd.uItemState |= 0x4;
                final int newColor2 = (clrText == -1) ? this.getForegroundPixel() : clrText;
                if (nmcd.clrText == newColor2) {
                    nmcd.clrText |= 0x20000000;
                    if (nmcd.clrText == newColor2) {
                        nmcd.clrText &= 0xDFFFFFFF;
                    }
                }
                else {
                    nmcd.clrText = newColor2;
                }
                OS.MoveMemory(lParam, nmcd, NMTVCUSTOMDRAW.sizeof);
                if (clrTextBk != -1) {
                    if ((this.style & 0x10000) != 0x0) {
                        final RECT rect12 = new RECT();
                        if (this.columnCount != 0) {
                            final HDITEM hdItem2 = new HDITEM();
                            hdItem2.mask = 1;
                            OS.SendMessage(this.hwndHeader, 4619, (long)index, hdItem2);
                            OS.SetRect(rect12, nmcd.left, nmcd.top, nmcd.left + hdItem2.cxy, nmcd.bottom);
                        }
                        else {
                            OS.SetRect(rect12, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
                        }
                        this.fillBackground(hDC, clrTextBk, rect12);
                    }
                    else {
                        final RECT textRect3 = item.getBounds(index, true, false, true, false, true, hDC);
                        this.fillBackground(hDC, clrTextBk, textRect3);
                    }
                }
            }
        }
        else if (clrSortBk != -1) {
            final RECT rect13 = new RECT();
            final HDITEM hdItem3 = new HDITEM();
            hdItem3.mask = 1;
            OS.SendMessage(this.hwndHeader, 4619, (long)index, hdItem3);
            OS.SetRect(rect13, nmcd.left, nmcd.top, nmcd.left + hdItem3.cxy, nmcd.bottom);
            this.fillBackground(hDC, clrSortBk, rect13);
        }
        OS.SaveDC(hDC);
        if (clipRect != null) {
            final long hRgn = OS.CreateRectRgn(clipRect.left, clipRect.top, clipRect.right, clipRect.bottom);
            final POINT lpPoint = new POINT();
            OS.GetWindowOrgEx(hDC, lpPoint);
            OS.OffsetRgn(hRgn, -lpPoint.x, -lpPoint.y);
            OS.SelectClipRgn(hDC, hRgn);
            OS.DeleteObject(hRgn);
        }
        return result;
    }
    
    LRESULT CDDS_POSTPAINT(final NMTVCUSTOMDRAW nmcd, final long wParam, final long lParam) {
        if (this.ignoreCustomDraw) {
            return null;
        }
        if (OS.IsWindowVisible(this.handle)) {
            if (OS.IsAppThemed() && this.sortColumn != null && this.sortDirection != 0 && this.findImageControl() == null) {
                final int index = this.indexOf(this.sortColumn);
                if (index != -1) {
                    int top = nmcd.top;
                    long hItem = 0L;
                    if (OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                        hItem = this.getBottomItem();
                    }
                    else {
                        hItem = OS.SendMessage(this.handle, 4362, 10L, 0L);
                    }
                    if (hItem != 0L) {
                        final RECT rect = new RECT();
                        if (OS.TreeView_GetItemRect(this.handle, hItem, rect, false)) {
                            top = rect.bottom;
                        }
                    }
                    final RECT rect = new RECT();
                    OS.SetRect(rect, nmcd.left, top, nmcd.right, nmcd.bottom);
                    final RECT headerRect = new RECT();
                    OS.SendMessage(this.hwndHeader, 4615, (long)index, headerRect);
                    rect.left = headerRect.left;
                    rect.right = headerRect.right;
                    this.fillBackground(nmcd.hdc, this.getSortColumnPixel(), rect);
                }
            }
            if (this.linesVisible) {
                final long hDC = nmcd.hdc;
                if (this.hwndHeader != 0L) {
                    int x = 0;
                    final RECT rect2 = new RECT();
                    final HDITEM hdItem = new HDITEM();
                    hdItem.mask = 1;
                    for (int i = 0; i < this.columnCount; ++i) {
                        final int index2 = (int)OS.SendMessage(this.hwndHeader, 4623, (long)i, 0L);
                        OS.SendMessage(this.hwndHeader, 4619, (long)index2, hdItem);
                        OS.SetRect(rect2, x, nmcd.top, x + hdItem.cxy, nmcd.bottom);
                        OS.DrawEdge(hDC, rect2, 8, 4);
                        x += hdItem.cxy;
                    }
                }
                int height = 0;
                final RECT rect2 = new RECT();
                long hItem2 = 0L;
                if (OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                    hItem2 = this.getBottomItem();
                }
                else {
                    hItem2 = OS.SendMessage(this.handle, 4362, 10L, 0L);
                }
                if (hItem2 != 0L && OS.TreeView_GetItemRect(this.handle, hItem2, rect2, false)) {
                    height = rect2.bottom - rect2.top;
                }
                if (height == 0) {
                    height = (int)OS.SendMessage(this.handle, 4380, 0L, 0L);
                    OS.GetClientRect(this.handle, rect2);
                    OS.SetRect(rect2, rect2.left, rect2.top, rect2.right, rect2.top + height);
                    OS.DrawEdge(hDC, rect2, 8, 8);
                }
                if (height != 0) {
                    while (rect2.bottom < nmcd.bottom) {
                        final int top2 = rect2.top + height;
                        OS.SetRect(rect2, rect2.left, top2, rect2.right, top2 + height);
                        OS.DrawEdge(hDC, rect2, 8, 8);
                    }
                }
            }
        }
        return new LRESULT(0L);
    }
    
    LRESULT CDDS_PREPAINT(final NMTVCUSTOMDRAW nmcd, final long wParam, final long lParam) {
        if (this.explorerTheme && ((OS.IsWindowEnabled(this.handle) && this.hooks(40)) || this.hasCustomBackground() || this.findImageControl() != null)) {
            final RECT rect = new RECT();
            OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
            this.drawBackground(nmcd.hdc, rect);
        }
        return new LRESULT(48L);
    }
    
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        if (this.hwndParent != 0L && hwnd == this.hwndParent) {
            return OS.DefWindowProc(hwnd, msg, wParam, lParam);
        }
        if (this.hwndHeader != 0L && hwnd == this.hwndHeader) {
            return OS.CallWindowProc(Tree.HeaderProc, hwnd, msg, wParam, lParam);
        }
        switch (msg) {
            case 7: {
                if ((this.style & 0x4) != 0x0) {
                    break;
                }
                long hItem = OS.SendMessage(this.handle, 4362, 9L, 0L);
                if (hItem != 0L) {
                    break;
                }
                hItem = OS.SendMessage(this.handle, 4362, 5L, 0L);
                if (hItem == 0L) {
                    break;
                }
                final TVITEM tvItem = new TVITEM();
                tvItem.mask = 24;
                tvItem.hItem = hItem;
                OS.SendMessage(this.handle, 4414, 0L, tvItem);
                this.hSelect = hItem;
                final boolean ignoreDeselect = true;
                this.lockSelection = true;
                this.ignoreSelect = true;
                this.ignoreDeselect = true;
                OS.SendMessage(this.handle, 4363, 9L, hItem);
                final boolean ignoreDeselect2 = false;
                this.lockSelection = false;
                this.ignoreSelect = false;
                this.ignoreDeselect = false;
                this.hSelect = 0L;
                if ((tvItem.state & 0x2) == 0x0) {
                    OS.SendMessage(this.handle, 4415, 0L, tvItem);
                    break;
                }
                break;
            }
        }
        long hItem = 0L;
        boolean redraw = false;
        switch (msg) {
            case 256: {
                if (wParam == 17L) {
                    break;
                }
                if (wParam == 16L) {
                    break;
                }
            }
            case 5:
            case 257:
            case 258:
            case 260:
            case 261:
            case 262:
            case 276:
            case 277:
            case 646: {
                redraw = (this.findImageControl() != null && this.getDrawing() && OS.IsWindowVisible(this.handle));
                if (redraw) {
                    OS.DefWindowProc(this.handle, 11, 0L, 0L);
                }
            }
            case 48:
            case 275:
            case 512:
            case 513:
            case 514:
            case 515:
            case 516:
            case 517:
            case 518:
            case 519:
            case 520:
            case 521:
            case 522:
            case 523:
            case 524:
            case 525:
            case 673:
            case 675: {
                if (this.findImageControl() != null) {
                    hItem = OS.SendMessage(this.handle, 4362, 5L, 0L);
                    break;
                }
                break;
            }
        }
        final long code = OS.CallWindowProc(Tree.TreeProc, hwnd, msg, wParam, lParam);
        Label_0941: {
            switch (msg) {
                case 256: {
                    if (wParam == 17L) {
                        break;
                    }
                    if (wParam == 16L) {
                        break;
                    }
                }
                case 5:
                case 257:
                case 258:
                case 260:
                case 261:
                case 262:
                case 276:
                case 277:
                case 646: {
                    if (!redraw) {
                        break Label_0941;
                    }
                    OS.DefWindowProc(this.handle, 11, 1L, 0L);
                    OS.InvalidateRect(this.handle, (RECT)null, true);
                    if (this.hwndHeader != 0L) {
                        OS.InvalidateRect(this.hwndHeader, (RECT)null, true);
                    }
                    break Label_0941;
                }
                case 48:
                case 275:
                case 512:
                case 513:
                case 514:
                case 515:
                case 516:
                case 517:
                case 518:
                case 519:
                case 520:
                case 521:
                case 522:
                case 523:
                case 524:
                case 525:
                case 673:
                case 675: {
                    if (this.findImageControl() != null && hItem != OS.SendMessage(this.handle, 4362, 5L, 0L)) {
                        OS.InvalidateRect(this.handle, (RECT)null, true);
                    }
                    this.updateScrollBar();
                    break;
                }
                case 15: {
                    this.painted = true;
                    break;
                }
            }
        }
        return code;
    }
    
    void checkBuffered() {
        super.checkBuffered();
        if ((this.style & 0x10000000) != 0x0) {
            this.style |= 0x20000000;
            OS.SendMessage(this.handle, 4385, 0L, 0L);
        }
        if (OS.IsAppThemed()) {
            final int exStyle = (int)OS.SendMessage(this.handle, 4397, 0L, 0L);
            if ((exStyle & 0x4) != 0x0) {
                this.style |= 0x20000000;
            }
        }
    }
    
    boolean checkData(final TreeItem item, final boolean redraw) {
        if ((this.style & 0x10000000) == 0x0) {
            return true;
        }
        if (!item.cached) {
            final TreeItem parentItem = item.getParentItem();
            return this.checkData(item, (parentItem == null) ? this.indexOf(item) : parentItem.indexOf(item), redraw);
        }
        return true;
    }
    
    boolean checkData(final TreeItem item, final int index, final boolean redraw) {
        if ((this.style & 0x10000000) == 0x0) {
            return true;
        }
        if (!item.cached) {
            item.cached = true;
            final Event event = new Event();
            event.item = (Widget)item;
            event.index = index;
            final TreeItem oldItem = this.currentItem;
            this.currentItem = item;
            final long hTopItem = OS.SendMessage(this.handle, 4362, 5L, 0L);
            this.sendEvent(36, event);
            this.currentItem = oldItem;
            if (this.isDisposed() || item.isDisposed()) {
                return false;
            }
            if (redraw) {
                item.redraw();
            }
            if (hTopItem != OS.SendMessage(this.handle, 4362, 5L, 0L)) {
                OS.InvalidateRect(this.handle, (RECT)null, true);
            }
        }
        return true;
    }
    
    boolean checkScroll(final long hItem) {
        if (this.getDrawing()) {
            return false;
        }
        long hRoot;
        long hParent;
        for (hRoot = OS.SendMessage(this.handle, 4362, 0L, 0L), hParent = OS.SendMessage(this.handle, 4362, 3L, hItem); hParent != hRoot && hParent != 0L; hParent = OS.SendMessage(this.handle, 4362, 3L, hParent)) {}
        return hParent == 0L;
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    public void clear(final int index, final boolean all) {
        this.checkWidget();
        long hItem = OS.SendMessage(this.handle, 4362, 0L, 0L);
        if (hItem == 0L) {
            this.error(6);
        }
        hItem = this.findItem(hItem, index);
        if (hItem == 0L) {
            this.error(6);
        }
        final TVITEM tvItem = new TVITEM();
        tvItem.mask = 20;
        this.clear(hItem, tvItem);
        if (all) {
            hItem = OS.SendMessage(this.handle, 4362, 4L, hItem);
            this.clearAll(hItem, tvItem, all);
        }
    }
    
    void clear(final long hItem, final TVITEM tvItem) {
        tvItem.hItem = hItem;
        TreeItem item = null;
        if (OS.SendMessage(this.handle, 4414, 0L, tvItem) != 0L) {
            item = ((tvItem.lParam != -1L) ? this.items[(int)tvItem.lParam] : null);
        }
        if (item != null) {
            if ((this.style & 0x10000000) != 0x0 && !item.cached) {
                return;
            }
            item.clear();
            item.redraw();
        }
    }
    
    public void clearAll(final boolean all) {
        this.checkWidget();
        final long hItem = OS.SendMessage(this.handle, 4362, 0L, 0L);
        if (hItem == 0L) {
            return;
        }
        if (all) {
            boolean redraw = false;
            for (final TreeItem item : this.items) {
                if (item != null && item != this.currentItem) {
                    item.clear();
                    redraw = true;
                }
            }
            if (redraw) {
                OS.InvalidateRect(this.handle, (RECT)null, true);
            }
        }
        else {
            final TVITEM tvItem = new TVITEM();
            tvItem.mask = 20;
            this.clearAll(hItem, tvItem, all);
        }
    }
    
    void clearAll(long hItem, final TVITEM tvItem, final boolean all) {
        while (hItem != 0L) {
            this.clear(hItem, tvItem);
            if (all) {
                final long hFirstItem = OS.SendMessage(this.handle, 4362, 4L, hItem);
                this.clearAll(hFirstItem, tvItem, all);
            }
            hItem = OS.SendMessage(this.handle, 4362, 1L, hItem);
        }
    }
    
    long CompareFunc(final long lParam1, final long lParam2, final long lParamSort) {
        final TreeItem item1 = this.items[(int)lParam1];
        final TreeItem item2 = this.items[(int)lParam2];
        final String text1 = item1.getText((int)lParamSort);
        final String text2 = item2.getText((int)lParamSort);
        return (this.sortDirection == 128) ? text1.compareTo(text2) : ((long)text2.compareTo(text1));
    }
    
    Point computeSizeInPixels(final int wHint, final int hHint, final boolean changed) {
        int width = 0;
        int height = 0;
        if (this.hwndHeader != 0L) {
            final HDITEM hdItem = new HDITEM();
            hdItem.mask = 1;
            for (int i = 0; i < this.columnCount; ++i) {
                OS.SendMessage(this.hwndHeader, 4619, (long)i, hdItem);
                width += hdItem.cxy;
            }
            final RECT rect = new RECT();
            OS.GetWindowRect(this.hwndHeader, rect);
            height += rect.bottom - rect.top;
        }
        final RECT rect2 = new RECT();
        for (long hItem = OS.SendMessage(this.handle, 4362, 0L, 0L); hItem != 0L; hItem = OS.SendMessage(this.handle, 4362, 6L, hItem)) {
            if ((this.style & 0x10000000) == 0x0 && !this.painted) {
                final TVITEM tvItem = new TVITEM();
                tvItem.mask = 17;
                tvItem.hItem = hItem;
                tvItem.pszText = -1L;
                this.ignoreCustomDraw = true;
                OS.SendMessage(this.handle, 4415, 0L, tvItem);
                this.ignoreCustomDraw = false;
            }
            if (OS.TreeView_GetItemRect(this.handle, hItem, rect2, true)) {
                width = Math.max(width, rect2.right);
                height += rect2.bottom - rect2.top;
            }
        }
        if (width == 0) {
            width = 64;
        }
        if (height == 0) {
            height = 64;
        }
        if (wHint != -1) {
            width = wHint;
        }
        if (hHint != -1) {
            height = hHint;
        }
        final int border = this.getBorderWidthInPixels();
        width += border * 2;
        height += border * 2;
        if ((this.style & 0x200) != 0x0) {
            width += OS.GetSystemMetrics(2);
        }
        if ((this.style & 0x100) != 0x0) {
            height += OS.GetSystemMetrics(3);
        }
        return new Point(width, height);
    }
    
    void createHandle() {
        super.createHandle();
        this.state &= 0xFFFFFEFD;
        if (OS.IsAppThemed()) {
            this.explorerTheme = true;
            OS.SetWindowTheme(this.handle, Display.EXPLORER, (char[])null);
            int bits = 20;
            if (Tree.ENABLE_TVS_EX_FADEINOUTEXPANDOS) {
                bits |= 0x40;
            }
            OS.SendMessage(this.handle, 4396, 0L, (long)bits);
            this.setForegroundPixel(-1);
        }
        if ((this.style & 0x20) != 0x0) {
            this.setCheckboxImageList();
        }
        final long hFont = OS.GetStockObject(13);
        OS.SendMessage(this.handle, 48, hFont, 0L);
        final int indent = DPIUtil.autoScaleUpUsingNativeDPI(16);
        OS.SendMessage(this.handle, 4359, (long)indent, 0L);
        this.createdAsRTL = ((this.style & 0x4000000) != 0x0);
    }
    
    void createHeaderToolTips() {
        if (this.headerToolTipHandle != 0L) {
            return;
        }
        int bits = 0;
        if ((this.style & 0x4000000) != 0x0) {
            bits |= 0x400000;
        }
        this.headerToolTipHandle = OS.CreateWindowEx(bits, new TCHAR(0, "tooltips_class32", true), (TCHAR)null, 2, Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0, this.handle, 0L, OS.GetModuleHandle((char[])null), (CREATESTRUCT)null);
        if (this.headerToolTipHandle == 0L) {
            this.error(2);
        }
        this.maybeEnableDarkSystemTheme(this.headerToolTipHandle);
        OS.SendMessage(this.headerToolTipHandle, 1048, 0L, 32767L);
    }
    
    void createItem(final TreeColumn column, final int index) {
        if (this.hwndHeader == 0L) {
            this.createParent();
        }
        if (0 > index || index > this.columnCount) {
            this.error(6);
        }
        if (this.columnCount == this.columns.length) {
            final TreeColumn[] newColumns = new TreeColumn[this.columns.length + 4];
            System.arraycopy(this.columns, 0, newColumns, 0, this.columns.length);
            this.columns = newColumns;
        }
        for (final TreeItem item : this.items) {
            if (item != null) {
                final String[] strings = item.strings;
                if (strings != null) {
                    final String[] temp = new String[this.columnCount + 1];
                    System.arraycopy(strings, 0, temp, 0, index);
                    System.arraycopy(strings, index, temp, index + 1, this.columnCount - index);
                    item.strings = temp;
                }
                final Image[] images = item.images;
                if (images != null) {
                    final Image[] temp2 = new Image[this.columnCount + 1];
                    System.arraycopy(images, 0, temp2, 0, index);
                    System.arraycopy(images, index, temp2, index + 1, this.columnCount - index);
                    item.images = temp2;
                }
                if (index == 0 && this.columnCount != 0) {
                    if (strings == null) {
                        (item.strings = new String[this.columnCount + 1])[1] = item.text;
                    }
                    item.text = "";
                    if (images == null) {
                        (item.images = new Image[this.columnCount + 1])[1] = item.image;
                    }
                    item.image = null;
                }
                if (item.cellBackground != null) {
                    final int[] cellBackground = item.cellBackground;
                    final int[] temp3 = new int[this.columnCount + 1];
                    System.arraycopy(cellBackground, 0, temp3, 0, index);
                    System.arraycopy(cellBackground, index, temp3, index + 1, this.columnCount - index);
                    temp3[index] = -1;
                    item.cellBackground = temp3;
                }
                if (item.cellForeground != null) {
                    final int[] cellForeground = item.cellForeground;
                    final int[] temp3 = new int[this.columnCount + 1];
                    System.arraycopy(cellForeground, 0, temp3, 0, index);
                    System.arraycopy(cellForeground, index, temp3, index + 1, this.columnCount - index);
                    temp3[index] = -1;
                    item.cellForeground = temp3;
                }
                if (item.cellFont != null) {
                    final Font[] cellFont = item.cellFont;
                    final Font[] temp4 = new Font[this.columnCount + 1];
                    System.arraycopy(cellFont, 0, temp4, 0, index);
                    System.arraycopy(cellFont, index, temp4, index + 1, this.columnCount - index);
                    item.cellFont = temp4;
                }
            }
        }
        System.arraycopy(this.columns, index, this.columns, index + 1, this.columnCount++ - index);
        this.columns[index] = column;
        final long hHeap = OS.GetProcessHeap();
        final long pszText = OS.HeapAlloc(hHeap, 8, 2);
        final HDITEM hdItem = new HDITEM();
        hdItem.mask = 6;
        hdItem.pszText = pszText;
        if ((column.style & 0x4000) == 0x4000) {
            hdItem.fmt = 0;
        }
        if ((column.style & 0x1000000) == 0x1000000) {
            hdItem.fmt = 2;
        }
        if ((column.style & 0x20000) == 0x20000) {
            hdItem.fmt = 1;
        }
        OS.SendMessage(this.hwndHeader, 4618, (long)index, hdItem);
        if (pszText != 0L) {
            OS.HeapFree(hHeap, 0, pszText);
        }
        if (this.columnCount == 1) {
            this.scrollWidth = 0;
            if ((this.style & 0x100) != 0x0) {
                int bits = OS.GetWindowLong(this.handle, -16);
                bits |= 0x8000;
                OS.SetWindowLong(this.handle, -16, bits);
            }
            final int count = (int)OS.SendMessage(this.handle, 4357, 0L, 0L);
            if (count != 0) {
                OS.ShowScrollBar(this.handle, 0, false);
            }
            this.createItemToolTips();
            if (this.itemToolTipHandle != 0L) {
                OS.SendMessage(this.itemToolTipHandle, 1027, 0L, -1L);
            }
        }
        this.setScrollWidth();
        this.updateImageList();
        this.updateScrollBar();
        if (this.columnCount == 1 && OS.SendMessage(this.handle, 4357, 0L, 0L) != 0L) {
            OS.InvalidateRect(this.handle, (RECT)null, true);
        }
        if (this.headerToolTipHandle != 0L) {
            final RECT rect = new RECT();
            if (OS.SendMessage(this.hwndHeader, 4615, (long)index, rect) != 0L) {
                final TOOLINFO lpti = new TOOLINFO();
                lpti.cbSize = TOOLINFO.sizeof;
                lpti.uFlags = 16;
                lpti.hwnd = this.hwndHeader;
                final TOOLINFO toolinfo = lpti;
                final int id = this.display.nextToolTipId++;
                column.id = id;
                toolinfo.uId = id;
                lpti.left = rect.left;
                lpti.top = rect.top;
                lpti.right = rect.right;
                lpti.bottom = rect.bottom;
                lpti.lpszText = -1L;
                OS.SendMessage(this.headerToolTipHandle, 1074, 0L, lpti);
            }
        }
    }
    
    void createItem(final TreeItem item, final long hParent, final long hInsertAfter, final long hItem) {
        int id = -1;
        if (item != null) {
            for (id = ((this.lastID < this.items.length) ? this.lastID : 0); id < this.items.length && this.items[id] != null; ++id) {}
            if (id == this.items.length) {
                int length = 0;
                if (this.getDrawing() && OS.IsWindowVisible(this.handle)) {
                    length = this.items.length + 4;
                }
                else {
                    this.shrink = true;
                    length = Math.max(4, this.items.length * 3 / 2);
                }
                this.itemsGrowArray(length);
            }
            this.lastID = id + 1;
        }
        long hNewItem = 0L;
        long hFirstItem = OS.SendMessage(this.handle, 4362, 4L, hParent);
        final boolean fixParent = hFirstItem == 0L;
        if (hItem == 0L) {
            final TVINSERTSTRUCT tvInsert = new TVINSERTSTRUCT();
            tvInsert.hParent = hParent;
            tvInsert.hInsertAfter = hInsertAfter;
            tvInsert.lParam = id;
            tvInsert.pszText = -1L;
            final TVINSERTSTRUCT tvinsertstruct = tvInsert;
            final TVINSERTSTRUCT tvinsertstruct2 = tvInsert;
            final int n = -1;
            tvinsertstruct2.iSelectedImage = -1;
            tvinsertstruct.iImage = -1;
            tvInsert.mask = 55;
            if ((this.style & 0x20) != 0x0) {
                final TVINSERTSTRUCT tvinsertstruct3 = tvInsert;
                tvinsertstruct3.mask |= 0x8;
                tvInsert.state = 4096;
                tvInsert.stateMask = 61440;
            }
            this.ignoreCustomDraw = true;
            hNewItem = OS.SendMessage(this.handle, 4402, 0L, tvInsert);
            this.ignoreCustomDraw = false;
            if (hNewItem == 0L) {
                this.error(14);
            }
        }
        else {
            final TVITEM tvItem = new TVITEM();
            tvItem.mask = 20;
            final TVITEM tvitem = tvItem;
            hNewItem = hItem;
            tvitem.hItem = hItem;
            tvItem.lParam = id;
            OS.SendMessage(this.handle, 4415, 0L, tvItem);
        }
        if (item != null) {
            item.handle = hNewItem;
            this.items[id] = item;
        }
        if (hFirstItem == 0L && (hInsertAfter == -65535L || hInsertAfter == -65534L)) {
            final long n2;
            hFirstItem = (n2 = hNewItem);
            this.hLastIndexOf = n2;
            this.hFirstIndexOf = n2;
            final int n3 = 0;
            this.lastIndexOf = 0;
            this.itemCount = 0;
        }
        if (hFirstItem == this.hFirstIndexOf && this.itemCount != -1) {
            ++this.itemCount;
        }
        if (hItem == 0L) {
            if (fixParent && hParent != -65536L && this.getDrawing() && OS.IsWindowVisible(this.handle)) {
                final RECT rect = new RECT();
                if (OS.TreeView_GetItemRect(this.handle, hParent, rect, false)) {
                    OS.InvalidateRect(this.handle, rect, true);
                }
            }
            if ((this.style & 0x10000000) != 0x0 && this.currentItem != null) {
                final RECT rect = new RECT();
                if (OS.TreeView_GetItemRect(this.handle, hNewItem, rect, false)) {
                    final RECT damageRect = new RECT();
                    final boolean damaged = OS.GetUpdateRect(this.handle, damageRect, true);
                    if (damaged && damageRect.top < rect.bottom) {
                        final long rgn = OS.CreateRectRgn(0, 0, 0, 0);
                        final int result = OS.GetUpdateRgn(this.handle, rgn, true);
                        if (result != 1) {
                            OS.OffsetRgn(rgn, 0, rect.bottom - rect.top);
                            OS.InvalidateRgn(this.handle, rgn, true);
                        }
                        OS.DeleteObject(rgn);
                    }
                }
            }
            if (this.getDrawing()) {
                this.updateScrollBar();
            }
            if (item != null && id == 0) {
                final Event event = new Event();
                event.detail = 0;
                this.sendEvent(56, event);
            }
        }
    }
    
    void createItemToolTips() {
        if (this.itemToolTipHandle != 0L) {
            return;
        }
        int bits1 = OS.GetWindowLong(this.handle, -16);
        bits1 |= 0x80;
        OS.SetWindowLong(this.handle, -16, bits1);
        int bits2 = 0;
        if ((this.style & 0x4000000) != 0x0) {
            bits2 |= 0x400000;
        }
        bits2 |= 0x20;
        this.itemToolTipHandle = OS.CreateWindowEx(bits2, new TCHAR(0, "tooltips_class32", true), (TCHAR)null, 50, Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0, this.handle, 0L, OS.GetModuleHandle((char[])null), (CREATESTRUCT)null);
        if (this.itemToolTipHandle == 0L) {
            this.error(2);
        }
        this.maybeEnableDarkSystemTheme(this.itemToolTipHandle);
        OS.SendMessage(this.itemToolTipHandle, 1027, 3L, 0L);
        OS.SendMessage(this.itemToolTipHandle, 1048, 0L, 32767L);
        final TOOLINFO lpti = new TOOLINFO();
        lpti.cbSize = TOOLINFO.sizeof;
        lpti.hwnd = this.handle;
        lpti.uId = this.handle;
        lpti.uFlags = 272;
        lpti.lpszText = -1L;
        OS.SendMessage(this.itemToolTipHandle, 1074, 0L, lpti);
    }
    
    void createParent() {
        this.forceResize();
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        OS.MapWindowPoints(0L, this.parent.handle, rect, 2);
        final int oldStyle = OS.GetWindowLong(this.handle, -16);
        int newStyle = super.widgetStyle();
        newStyle &= 0xEFFFFFFF;
        if ((oldStyle & 0x4000000) != 0x0) {
            newStyle |= 0x4000000;
        }
        if ((oldStyle & 0x800000) != 0x0) {
            final int noBorderStyle = oldStyle & 0xFF7FFFFF;
            OS.SetWindowLong(this.handle, -16, noBorderStyle);
        }
        this.hwndParent = OS.CreateWindowEx(super.widgetExtStyle(), super.windowClass(), (TCHAR)null, newStyle, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top, this.parent.handle, 0L, OS.GetModuleHandle((char[])null), (CREATESTRUCT)null);
        if (this.hwndParent == 0L) {
            this.error(2);
        }
        OS.SetWindowLongPtr(this.hwndParent, -12, this.hwndParent);
        this.maybeEnableDarkSystemTheme(this.hwndParent);
        int bits = 1048576;
        if ((this.style & 0x4000000) != 0x0) {
            bits |= 0x400000;
        }
        this.hwndHeader = OS.CreateWindowEx(bits, Tree.HeaderClass, (TCHAR)null, 1140850890, 0, 0, 0, 0, this.hwndParent, 0L, OS.GetModuleHandle((char[])null), (CREATESTRUCT)null);
        if (this.hwndHeader == 0L) {
            this.error(2);
        }
        OS.SetWindowLongPtr(this.hwndHeader, -12, this.hwndHeader);
        this.maybeEnableDarkSystemTheme(this.hwndHeader);
        final long hFont = OS.SendMessage(this.handle, 49, 0L, 0L);
        if (hFont != 0L) {
            OS.SendMessage(this.hwndHeader, 48, hFont, 0L);
        }
        final long hwndInsertAfter = OS.GetWindow(this.handle, 3);
        final int flags = 19;
        OS.SetWindowPos(this.hwndParent, hwndInsertAfter, 0, 0, 0, 0, 19);
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        info.fMask = 3;
        OS.GetScrollInfo(this.hwndParent, 0, info);
        info.nPage = info.nMax + 1;
        OS.SetScrollInfo(this.hwndParent, 0, info, true);
        OS.GetScrollInfo(this.hwndParent, 1, info);
        info.nPage = info.nMax + 1;
        OS.SetScrollInfo(this.hwndParent, 1, info, true);
        this.customDraw = true;
        this.deregister();
        if ((oldStyle & 0x10000000) != 0x0) {
            OS.ShowWindow(this.hwndParent, 5);
        }
        final long hwndFocus = OS.GetFocus();
        if (hwndFocus == this.handle) {
            OS.SetFocus(this.hwndParent);
        }
        OS.SetParent(this.handle, this.hwndParent);
        if (hwndFocus == this.handle) {
            OS.SetFocus(this.handle);
        }
        this.register();
        this.subclass();
    }
    
    void createWidget() {
        super.createWidget();
        this.items = new TreeItem[4];
        this.columns = new TreeColumn[4];
        this.itemCount = -1;
    }
    
    private boolean customHeaderDrawing() {
        return this.headerBackground != -1 || this.headerForeground != -1;
    }
    
    int defaultBackground() {
        return OS.GetSysColor(5);
    }
    
    void deregister() {
        super.deregister();
        if (this.hwndParent != 0L) {
            this.display.removeControl(this.hwndParent);
        }
        if (this.hwndHeader != 0L) {
            this.display.removeControl(this.hwndHeader);
        }
    }
    
    void deselect(long hItem, final TVITEM tvItem, final long hIgnoreItem) {
        while (hItem != 0L) {
            if (hItem != hIgnoreItem) {
                tvItem.hItem = hItem;
                OS.SendMessage(this.handle, 4415, 0L, tvItem);
            }
            final long hFirstItem = OS.SendMessage(this.handle, 4362, 4L, hItem);
            this.deselect(hFirstItem, tvItem, hIgnoreItem);
            hItem = OS.SendMessage(this.handle, 4362, 1L, hItem);
        }
    }
    
    public void deselect(final TreeItem item) {
        this.checkWidget();
        if (item == null) {
            this.error(4);
        }
        if (item.isDisposed()) {
            this.error(5);
        }
        final TVITEM tvItem = new TVITEM();
        tvItem.mask = 24;
        tvItem.stateMask = 2;
        tvItem.hItem = item.handle;
        OS.SendMessage(this.handle, 4415, 0L, tvItem);
    }
    
    public void deselectAll() {
        this.checkWidget();
        final TVITEM tvItem = new TVITEM();
        tvItem.mask = 24;
        tvItem.stateMask = 2;
        if ((this.style & 0x4) != 0x0) {
            final long hItem = OS.SendMessage(this.handle, 4362, 9L, 0L);
            if (hItem != 0L) {
                tvItem.hItem = hItem;
                OS.SendMessage(this.handle, 4415, 0L, tvItem);
            }
        }
        else {
            final long oldProc = OS.GetWindowLongPtr(this.handle, -4);
            OS.SetWindowLongPtr(this.handle, -4, Tree.TreeProc);
            if ((this.style & 0x10000000) != 0x0) {
                final long hItem2 = OS.SendMessage(this.handle, 4362, 0L, 0L);
                this.deselect(hItem2, tvItem, 0L);
            }
            else {
                for (final TreeItem item : this.items) {
                    if (item != null) {
                        tvItem.hItem = item.handle;
                        OS.SendMessage(this.handle, 4415, 0L, tvItem);
                    }
                }
            }
            OS.SetWindowLongPtr(this.handle, -4, oldProc);
        }
    }
    
    void destroyItem(final TreeColumn column) {
        if (this.hwndHeader == 0L) {
            this.error(15);
        }
        int index;
        for (index = 0; index < this.columnCount && this.columns[index] != column; ++index) {}
        final int[] oldOrder = new int[this.columnCount];
        OS.SendMessage(this.hwndHeader, 4625, (long)this.columnCount, oldOrder);
        int orderIndex;
        for (orderIndex = 0; orderIndex < this.columnCount && oldOrder[orderIndex] != index; ++orderIndex) {}
        final RECT headerRect = new RECT();
        OS.SendMessage(this.hwndHeader, 4615, (long)index, headerRect);
        if (OS.SendMessage(this.hwndHeader, 4610, (long)index, 0L) == 0L) {
            this.error(15);
        }
        System.arraycopy(this.columns, index + 1, this.columns, index, --this.columnCount - index);
        this.columns[this.columnCount] = null;
        for (final TreeItem item : this.items) {
            if (item != null) {
                if (this.columnCount == 0) {
                    item.strings = null;
                    item.images = null;
                    item.cellBackground = null;
                    item.cellForeground = null;
                    item.cellFont = null;
                }
                else {
                    if (item.strings != null) {
                        final String[] strings = item.strings;
                        if (index == 0) {
                            item.text = ((strings[1] != null) ? strings[1] : "");
                        }
                        final String[] temp = new String[this.columnCount];
                        System.arraycopy(strings, 0, temp, 0, index);
                        System.arraycopy(strings, index + 1, temp, index, this.columnCount - index);
                        item.strings = temp;
                    }
                    else if (index == 0) {
                        item.text = "";
                    }
                    if (item.images != null) {
                        final Image[] images = item.images;
                        if (index == 0) {
                            item.image = images[1];
                        }
                        final Image[] temp2 = new Image[this.columnCount];
                        System.arraycopy(images, 0, temp2, 0, index);
                        System.arraycopy(images, index + 1, temp2, index, this.columnCount - index);
                        item.images = temp2;
                    }
                    else if (index == 0) {
                        item.image = null;
                    }
                    if (item.cellBackground != null) {
                        final int[] cellBackground = item.cellBackground;
                        final int[] temp3 = new int[this.columnCount];
                        System.arraycopy(cellBackground, 0, temp3, 0, index);
                        System.arraycopy(cellBackground, index + 1, temp3, index, this.columnCount - index);
                        item.cellBackground = temp3;
                    }
                    if (item.cellForeground != null) {
                        final int[] cellForeground = item.cellForeground;
                        final int[] temp3 = new int[this.columnCount];
                        System.arraycopy(cellForeground, 0, temp3, 0, index);
                        System.arraycopy(cellForeground, index + 1, temp3, index, this.columnCount - index);
                        item.cellForeground = temp3;
                    }
                    if (item.cellFont != null) {
                        final Font[] cellFont = item.cellFont;
                        final Font[] temp4 = new Font[this.columnCount];
                        System.arraycopy(cellFont, 0, temp4, 0, index);
                        System.arraycopy(cellFont, index + 1, temp4, index, this.columnCount - index);
                        item.cellFont = temp4;
                    }
                }
            }
        }
        if (this.columnCount == 0) {
            this.scrollWidth = 0;
            if (!this.hooks(41)) {
                int bits = OS.GetWindowLong(this.handle, -16);
                if ((this.style & 0x100) != 0x0) {
                    bits &= 0xFFFF7FFF;
                }
                OS.SetWindowLong(this.handle, -16, bits);
                OS.InvalidateRect(this.handle, (RECT)null, true);
            }
            if (this.itemToolTipHandle != 0L) {
                OS.SendMessage(this.itemToolTipHandle, 1027, 3L, 0L);
            }
        }
        else {
            if (index == 0) {
                final TreeColumn treeColumn3;
                final TreeColumn treeColumn = treeColumn3 = this.columns[0];
                treeColumn3.style &= 0xFEFDBFFF;
                final TreeColumn treeColumn4;
                final TreeColumn treeColumn2 = treeColumn4 = this.columns[0];
                treeColumn4.style |= 0x4000;
                final HDITEM hdItem = new HDITEM();
                hdItem.mask = 36;
                OS.SendMessage(this.hwndHeader, 4619, (long)index, hdItem);
                final HDITEM hditem3;
                final HDITEM hditem = hditem3 = hdItem;
                hditem3.fmt &= 0xFFFFFFFC;
                final HDITEM hditem4;
                final HDITEM hditem2 = hditem4 = hdItem;
                hditem4.fmt |= 0x0;
                OS.SendMessage(this.hwndHeader, 4620, (long)index, hdItem);
            }
            final RECT rect = new RECT();
            OS.GetClientRect(this.handle, rect);
            rect.left = headerRect.left;
            OS.InvalidateRect(this.handle, rect, true);
        }
        this.setScrollWidth();
        this.updateImageList();
        this.updateScrollBar();
        if (this.columnCount != 0) {
            final int[] newOrder = new int[this.columnCount];
            OS.SendMessage(this.hwndHeader, 4625, (long)this.columnCount, newOrder);
            final TreeColumn[] newColumns = new TreeColumn[this.columnCount - orderIndex];
            for (int i = orderIndex; i < newOrder.length; ++i) {
                (newColumns[i - orderIndex] = this.columns[newOrder[i]]).updateToolTip(newOrder[i]);
            }
            for (final TreeColumn newColumn : newColumns) {
                if (!newColumn.isDisposed()) {
                    newColumn.sendEvent(10);
                }
            }
        }
        if (this.headerToolTipHandle != 0L) {
            final TOOLINFO lpti = new TOOLINFO();
            lpti.cbSize = TOOLINFO.sizeof;
            lpti.uId = column.id;
            lpti.hwnd = this.hwndHeader;
            OS.SendMessage(this.headerToolTipHandle, 1075, 0L, lpti);
        }
    }
    
    void destroyItem(final TreeItem item, final long hItem) {
        final long n = 0L;
        this.hLastIndexOf = 0L;
        this.hFirstIndexOf = 0L;
        this.itemCount = -1;
        long hParent = 0L;
        boolean fixRedraw = false;
        if ((this.style & 0x20000000) == 0x0 && this.getDrawing() && OS.IsWindowVisible(this.handle)) {
            final RECT rect = new RECT();
            fixRedraw = !OS.TreeView_GetItemRect(this.handle, hItem, rect, false);
        }
        if (fixRedraw) {
            hParent = OS.SendMessage(this.handle, 4362, 3L, hItem);
            OS.UpdateWindow(this.handle);
            OS.DefWindowProc(this.handle, 11, 0L, 0L);
        }
        final boolean ignoreDeselect = true;
        this.lockSelection = true;
        this.ignoreSelect = true;
        this.ignoreDeselect = true;
        final long hwndToolTip = OS.SendMessage(this.handle, 4377, 0L, 0L);
        if (hwndToolTip != 0L) {
            OS.SendMessage(hwndToolTip, 1052, 0L, 0L);
        }
        final boolean b = true;
        this.ignoreShrink = true;
        this.shrink = true;
        OS.SendMessage(this.handle, 4353, 0L, hItem);
        this.ignoreShrink = false;
        final boolean ignoreDeselect2 = false;
        this.lockSelection = false;
        this.ignoreSelect = false;
        this.ignoreDeselect = false;
        if (fixRedraw) {
            OS.DefWindowProc(this.handle, 11, 1L, 0L);
            OS.ValidateRect(this.handle, (RECT)null);
            if (OS.SendMessage(this.handle, 4362, 4L, hParent) == 0L) {
                final RECT rect2 = new RECT();
                if (OS.TreeView_GetItemRect(this.handle, hParent, rect2, false)) {
                    OS.InvalidateRect(this.handle, rect2, true);
                }
            }
        }
        final int count = (int)OS.SendMessage(this.handle, 4357, 0L, 0L);
        if (count == 0) {
            if (this.imageList != null) {
                OS.SendMessage(this.handle, 4361, 0L, 0L);
                this.display.releaseImageList(this.imageList);
            }
            this.imageList = null;
            if (this.hwndParent == 0L && !this.linesVisible && !this.hooks(41) && !this.hooks(40) && !this.hooks(42)) {
                this.customDraw = false;
            }
            this.items = new TreeItem[4];
            this.scrollWidth = 0;
            this.setScrollWidth();
        }
        if (this.getDrawing()) {
            this.updateScrollBar();
        }
        if (count == 0) {
            final Event event = new Event();
            event.detail = 1;
            this.sendEvent(56, event);
        }
    }
    
    void destroyScrollBar(final int type) {
        super.destroyScrollBar(type);
        int bits = OS.GetWindowLong(this.handle, -16);
        if ((this.style & 0x300) == 0x0) {
            bits &= 0xFFCFFFFF;
            bits |= 0x2000;
        }
        else if ((this.style & 0x100) == 0x0) {
            bits &= 0xFFEFFFFF;
            bits |= 0x8000;
        }
        OS.SetWindowLong(this.handle, -16, bits);
    }
    
    void enableDrag(final boolean enabled) {
        int bits = OS.GetWindowLong(this.handle, -16);
        if (enabled && this.hooks(29)) {
            bits &= 0xFFFFFFEF;
        }
        else {
            bits |= 0x10;
        }
        OS.SetWindowLong(this.handle, -16, bits);
    }
    
    void enableWidget(final boolean enabled) {
        super.enableWidget(enabled);
        Control control = this.findBackgroundControl();
        if (control == null) {
            control = (Control)this;
        }
        if (control.backgroundImage == null) {
            this._setBackgroundPixel(this.hasCustomBackground() ? control.getBackgroundPixel() : -1);
        }
        if (this.hwndParent != 0L) {
            OS.EnableWindow(this.hwndParent, enabled);
        }
        this.updateFullSelection();
    }
    
    boolean findCell(final int x, final int y, final TreeItem[] item, final int[] index, final RECT[] cellRect, final RECT[] itemRect) {
        boolean found = false;
        final TVHITTESTINFO lpht = new TVHITTESTINFO();
        lpht.x = x;
        lpht.y = y;
        OS.SendMessage(this.handle, 4369, 0L, lpht);
        if (lpht.hItem != 0L) {
            item[0] = this._getItem(lpht.hItem);
            final POINT pt = new POINT();
            pt.x = x;
            pt.y = y;
            final long hDC = OS.GetDC(this.handle);
            long oldFont = 0L;
            final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
            if (newFont != 0L) {
                oldFont = OS.SelectObject(hDC, newFont);
            }
            final RECT rect = new RECT();
            if (this.hwndParent != 0L) {
                OS.GetClientRect(this.hwndParent, rect);
                OS.MapWindowPoints(this.hwndParent, this.handle, rect, 2);
            }
            else {
                OS.GetClientRect(this.handle, rect);
            }
            final int count = Math.max(1, this.columnCount);
            final int[] order = new int[count];
            if (this.hwndHeader != 0L) {
                OS.SendMessage(this.hwndHeader, 4625, (long)count, order);
            }
            index[0] = 0;
            boolean quit = false;
            while (index[0] < count && !quit) {
                long hFont = item[0].fontHandle(order[index[0]]);
                if (hFont != -1L) {
                    hFont = OS.SelectObject(hDC, hFont);
                }
                cellRect[0] = item[0].getBounds(order[index[0]], true, false, true, false, true, hDC);
                if (cellRect[0].left > rect.right) {
                    quit = true;
                }
                else {
                    cellRect[0].right = Math.min(cellRect[0].right, rect.right);
                    if (OS.PtInRect(cellRect[0], pt)) {
                        if (this.isCustomToolTip()) {
                            final int state = (int)OS.SendMessage(this.handle, 4391, lpht.hItem, 2L);
                            final int detail = ((state & 0x2) != 0x0) ? 2 : 0;
                            final Event event = this.sendMeasureItemEvent(item[0], order[index[0]], hDC, detail);
                            if (this.isDisposed()) {
                                break;
                            }
                            if (item[0].isDisposed()) {
                                break;
                            }
                            final Rectangle boundsInPixels = event.getBoundsInPixels();
                            itemRect[0] = new RECT();
                            itemRect[0].left = boundsInPixels.x;
                            itemRect[0].right = boundsInPixels.x + boundsInPixels.width;
                            itemRect[0].top = boundsInPixels.y;
                            itemRect[0].bottom = boundsInPixels.y + boundsInPixels.height;
                        }
                        else {
                            itemRect[0] = item[0].getBounds(order[index[0]], true, false, false, false, false, hDC);
                        }
                        if (itemRect[0].right > cellRect[0].right) {
                            found = true;
                        }
                        quit = true;
                    }
                }
                if (hFont != -1L) {
                    OS.SelectObject(hDC, hFont);
                }
                if (!found) {
                    final int n = 0;
                    final int n2 = 0;
                    ++index[n2];
                }
            }
            if (newFont != 0L) {
                OS.SelectObject(hDC, oldFont);
            }
            OS.ReleaseDC(this.handle, hDC);
        }
        return found;
    }
    
    int findIndex(final long hFirstItem, final long hItem) {
        if (hFirstItem == 0L) {
            return -1;
        }
        if (hFirstItem == this.hFirstIndexOf) {
            if (this.hFirstIndexOf == hItem) {
                this.hLastIndexOf = this.hFirstIndexOf;
                return this.lastIndexOf = 0;
            }
            if (this.hLastIndexOf == hItem) {
                return this.lastIndexOf;
            }
            long hPrevItem = OS.SendMessage(this.handle, 4362, 2L, this.hLastIndexOf);
            if (hPrevItem == hItem) {
                this.hLastIndexOf = hPrevItem;
                return --this.lastIndexOf;
            }
            long hNextItem = OS.SendMessage(this.handle, 4362, 1L, this.hLastIndexOf);
            if (hNextItem == hItem) {
                this.hLastIndexOf = hNextItem;
                return ++this.lastIndexOf;
            }
            int previousIndex;
            for (previousIndex = this.lastIndexOf - 1; hPrevItem != 0L && hPrevItem != hItem; hPrevItem = OS.SendMessage(this.handle, 4362, 2L, hPrevItem), --previousIndex) {}
            if (hPrevItem == hItem) {
                this.hLastIndexOf = hPrevItem;
                return this.lastIndexOf = previousIndex;
            }
            int nextIndex;
            for (nextIndex = this.lastIndexOf + 1; hNextItem != 0L && hNextItem != hItem; hNextItem = OS.SendMessage(this.handle, 4362, 1L, hNextItem), ++nextIndex) {}
            if (hNextItem == hItem) {
                this.hLastIndexOf = hNextItem;
                return this.lastIndexOf = nextIndex;
            }
            return -1;
        }
        else {
            int index;
            long hNextItem2;
            for (index = 0, hNextItem2 = hFirstItem; hNextItem2 != 0L && hNextItem2 != hItem; hNextItem2 = OS.SendMessage(this.handle, 4362, 1L, hNextItem2), ++index) {}
            if (hNextItem2 == hItem) {
                this.itemCount = -1;
                this.hFirstIndexOf = hFirstItem;
                this.hLastIndexOf = hNextItem2;
                return this.lastIndexOf = index;
            }
            return -1;
        }
    }
    
    Widget findItem(final long hItem) {
        return (Widget)this._getItem(hItem);
    }
    
    long findItem(final long hFirstItem, final int index) {
        if (hFirstItem == 0L) {
            return 0L;
        }
        if (hFirstItem == this.hFirstIndexOf) {
            if (index == 0) {
                this.lastIndexOf = 0;
                return this.hLastIndexOf = this.hFirstIndexOf;
            }
            if (this.lastIndexOf == index) {
                return this.hLastIndexOf;
            }
            if (this.lastIndexOf - 1 == index) {
                --this.lastIndexOf;
                return this.hLastIndexOf = OS.SendMessage(this.handle, 4362, 2L, this.hLastIndexOf);
            }
            if (this.lastIndexOf + 1 == index) {
                ++this.lastIndexOf;
                return this.hLastIndexOf = OS.SendMessage(this.handle, 4362, 1L, this.hLastIndexOf);
            }
            if (index < this.lastIndexOf) {
                int previousIndex;
                long hPrevItem;
                for (previousIndex = this.lastIndexOf - 1, hPrevItem = OS.SendMessage(this.handle, 4362, 2L, this.hLastIndexOf); hPrevItem != 0L && index < previousIndex; hPrevItem = OS.SendMessage(this.handle, 4362, 2L, hPrevItem), --previousIndex) {}
                if (index == previousIndex) {
                    this.lastIndexOf = previousIndex;
                    return this.hLastIndexOf = hPrevItem;
                }
            }
            else {
                int nextIndex;
                long hNextItem;
                for (nextIndex = this.lastIndexOf + 1, hNextItem = OS.SendMessage(this.handle, 4362, 1L, this.hLastIndexOf); hNextItem != 0L && nextIndex < index; hNextItem = OS.SendMessage(this.handle, 4362, 1L, hNextItem), ++nextIndex) {}
                if (index == nextIndex) {
                    this.lastIndexOf = nextIndex;
                    return this.hLastIndexOf = hNextItem;
                }
            }
            return 0L;
        }
        else {
            int nextIndex;
            long hNextItem;
            for (nextIndex = 0, hNextItem = hFirstItem; hNextItem != 0L && nextIndex < index; hNextItem = OS.SendMessage(this.handle, 4362, 1L, hNextItem), ++nextIndex) {}
            if (index == nextIndex) {
                this.itemCount = -1;
                this.lastIndexOf = nextIndex;
                this.hFirstIndexOf = hFirstItem;
                return this.hLastIndexOf = hNextItem;
            }
            return 0L;
        }
    }
    
    TreeItem getFocusItem() {
        final long hItem = OS.SendMessage(this.handle, 4362, 9L, 0L);
        return (hItem != 0L) ? this._getItem(hItem) : null;
    }
    
    public int getGridLineWidth() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getGridLineWidthInPixels());
    }
    
    int getGridLineWidthInPixels() {
        return 1;
    }
    
    public Color getHeaderBackground() {
        this.checkWidget();
        return Color.win32_new((Device)this.display, this.getHeaderBackgroundPixel());
    }
    
    private int getHeaderBackgroundPixel() {
        return (this.headerBackground != -1) ? this.headerBackground : this.defaultBackground();
    }
    
    public Color getHeaderForeground() {
        this.checkWidget();
        return Color.win32_new((Device)this.display, this.getHeaderForegroundPixel());
    }
    
    private int getHeaderForegroundPixel() {
        return (this.headerForeground != -1) ? this.headerForeground : this.defaultForeground();
    }
    
    public int getHeaderHeight() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getHeaderHeightInPixels());
    }
    
    int getHeaderHeightInPixels() {
        if (this.hwndHeader == 0L) {
            return 0;
        }
        final RECT rect = new RECT();
        OS.GetWindowRect(this.hwndHeader, rect);
        return rect.bottom - rect.top;
    }
    
    public boolean getHeaderVisible() {
        this.checkWidget();
        if (this.hwndHeader == 0L) {
            return false;
        }
        final int bits = OS.GetWindowLong(this.hwndHeader, -16);
        return (bits & 0x10000000) != 0x0;
    }
    
    Point getImageSize() {
        if (this.imageList != null) {
            return this.imageList.getImageSize();
        }
        return new Point(0, this.getItemHeightInPixels());
    }
    
    long getBottomItem() {
        long hItem = OS.SendMessage(this.handle, 4362, 5L, 0L);
        if (hItem == 0L) {
            return 0L;
        }
        for (int index = 0, count = (int)OS.SendMessage(this.handle, 4368, 0L, 0L); index <= count; ++index) {
            final long hNextItem = OS.SendMessage(this.handle, 4362, 6L, hItem);
            if (hNextItem == 0L) {
                return hItem;
            }
            hItem = hNextItem;
        }
        return hItem;
    }
    
    public TreeColumn getColumn(final int index) {
        this.checkWidget();
        if (0 > index || index >= this.columnCount) {
            this.error(6);
        }
        return this.columns[index];
    }
    
    public int getColumnCount() {
        this.checkWidget();
        return this.columnCount;
    }
    
    public int[] getColumnOrder() {
        this.checkWidget();
        if (this.columnCount == 0) {
            return new int[0];
        }
        final int[] order = new int[this.columnCount];
        OS.SendMessage(this.hwndHeader, 4625, (long)this.columnCount, order);
        return order;
    }
    
    public TreeColumn[] getColumns() {
        this.checkWidget();
        final TreeColumn[] result = new TreeColumn[this.columnCount];
        System.arraycopy(this.columns, 0, result, 0, this.columnCount);
        return result;
    }
    
    public TreeItem getItem(final int index) {
        this.checkWidget();
        if (index < 0) {
            this.error(6);
        }
        final long hFirstItem = OS.SendMessage(this.handle, 4362, 0L, 0L);
        if (hFirstItem == 0L) {
            this.error(6);
        }
        final long hItem = this.findItem(hFirstItem, index);
        if (hItem == 0L) {
            this.error(6);
        }
        return this._getItem(hItem);
    }
    
    TreeItem getItem(final NMTVCUSTOMDRAW nmcd) {
        int id = (int)nmcd.lItemlParam;
        if ((this.style & 0x10000000) != 0x0 && id == -1) {
            final TVITEM tvItem = new TVITEM();
            tvItem.mask = 20;
            tvItem.hItem = nmcd.dwItemSpec;
            OS.SendMessage(this.handle, 4414, 0L, tvItem);
            id = (int)tvItem.lParam;
        }
        return this._getItem(nmcd.dwItemSpec, id);
    }
    
    public TreeItem getItem(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        return this.getItemInPixels(DPIUtil.autoScaleUp(point));
    }
    
    TreeItem getItemInPixels(final Point point) {
        final TVHITTESTINFO lpht = new TVHITTESTINFO();
        lpht.x = point.x;
        lpht.y = point.y;
        OS.SendMessage(this.handle, 4369, 0L, lpht);
        if (lpht.hItem != 0L) {
            int flags = 70;
            if ((this.style & 0x10000) != 0x0) {
                flags |= 0x28;
            }
            else if (this.hooks(41)) {
                final TVHITTESTINFO tvhittestinfo3;
                final TVHITTESTINFO tvhittestinfo = tvhittestinfo3 = lpht;
                tvhittestinfo3.flags &= 0xFFFFFFF9;
                if (this.hitTestSelection(lpht.hItem, lpht.x, lpht.y)) {
                    final TVHITTESTINFO tvhittestinfo4;
                    final TVHITTESTINFO tvhittestinfo2 = tvhittestinfo4 = lpht;
                    tvhittestinfo4.flags |= 0x6;
                }
            }
            if ((lpht.flags & flags) != 0x0) {
                return this._getItem(lpht.hItem);
            }
        }
        return null;
    }
    
    public int getItemCount() {
        this.checkWidget();
        final long hItem = OS.SendMessage(this.handle, 4362, 0L, 0L);
        if (hItem == 0L) {
            return 0;
        }
        return this.getItemCount(hItem);
    }
    
    int getItemCount(final long hItem) {
        int count = 0;
        long hFirstItem = hItem;
        if (hItem == this.hFirstIndexOf) {
            if (this.itemCount != -1) {
                return this.itemCount;
            }
            hFirstItem = this.hLastIndexOf;
            count = this.lastIndexOf;
        }
        while (hFirstItem != 0L) {
            hFirstItem = OS.SendMessage(this.handle, 4362, 1L, hFirstItem);
            ++count;
        }
        if (hItem == this.hFirstIndexOf) {
            this.itemCount = count;
        }
        return count;
    }
    
    public int getItemHeight() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getItemHeightInPixels());
    }
    
    int getItemHeightInPixels() {
        return (int)OS.SendMessage(this.handle, 4380, 0L, 0L);
    }
    
    public TreeItem[] getItems() {
        this.checkWidget();
        final long hItem = OS.SendMessage(this.handle, 4362, 0L, 0L);
        if (hItem == 0L) {
            return new TreeItem[0];
        }
        return this.getItems(hItem);
    }
    
    TreeItem[] getItems(final long hTreeItem) {
        int count = 0;
        for (long hItem = hTreeItem; hItem != 0L; hItem = OS.SendMessage(this.handle, 4362, 1L, hItem), ++count) {}
        int index = 0;
        TreeItem[] result = new TreeItem[count];
        final TVITEM tvItem = new TVITEM();
        tvItem.mask = 20;
        tvItem.hItem = hTreeItem;
        while (tvItem.hItem != 0L) {
            OS.SendMessage(this.handle, 4414, 0L, tvItem);
            final TreeItem item = this._getItem(tvItem.hItem, (int)tvItem.lParam);
            if (item != null) {
                result[index++] = item;
            }
            tvItem.hItem = OS.SendMessage(this.handle, 4362, 1L, tvItem.hItem);
        }
        if (index != count) {
            final TreeItem[] newResult = new TreeItem[index];
            System.arraycopy(result, 0, newResult, 0, index);
            result = newResult;
        }
        return result;
    }
    
    public boolean getLinesVisible() {
        this.checkWidget();
        return this.linesVisible;
    }
    
    long getNextSelection(long hItem) {
        while (hItem != 0L) {
            final int state = (int)OS.SendMessage(this.handle, 4391, hItem, 2L);
            if ((state & 0x2) != 0x0) {
                return hItem;
            }
            final long hFirstItem = OS.SendMessage(this.handle, 4362, 4L, hItem);
            final long hSelected = this.getNextSelection(hFirstItem);
            if (hSelected != 0L) {
                return hSelected;
            }
            hItem = OS.SendMessage(this.handle, 4362, 1L, hItem);
        }
        return 0L;
    }
    
    public TreeItem getParentItem() {
        this.checkWidget();
        return null;
    }
    
    int getSelection(long hItem, final TVITEM tvItem, final TreeItem[] selection, int index, final int count, final boolean bigSelection, final boolean all) {
        while (hItem != 0L) {
            boolean expanded = true;
            if (bigSelection) {
                tvItem.hItem = hItem;
                OS.SendMessage(this.handle, 4414, 0L, tvItem);
                if ((tvItem.state & 0x2) != 0x0) {
                    if (selection != null && index < selection.length) {
                        final TreeItem item = this._getItem(hItem, (int)tvItem.lParam);
                        if (item != null) {
                            selection[index] = item;
                        }
                        else {
                            --index;
                        }
                    }
                    ++index;
                }
                expanded = ((tvItem.state & 0x20) != 0x0);
            }
            else {
                final int state = (int)OS.SendMessage(this.handle, 4391, hItem, 34L);
                if ((state & 0x2) != 0x0) {
                    if (tvItem != null && selection != null && index < selection.length) {
                        tvItem.hItem = hItem;
                        OS.SendMessage(this.handle, 4414, 0L, tvItem);
                        final TreeItem item2 = this._getItem(hItem, (int)tvItem.lParam);
                        if (item2 != null) {
                            selection[index] = item2;
                        }
                        else {
                            --index;
                        }
                    }
                    ++index;
                }
                expanded = ((state & 0x20) != 0x0);
            }
            if (index == count) {
                break;
            }
            if (all) {
                if (expanded) {
                    final long hFirstItem = OS.SendMessage(this.handle, 4362, 4L, hItem);
                    if ((index = this.getSelection(hFirstItem, tvItem, selection, index, count, bigSelection, all)) == count) {
                        break;
                    }
                }
                hItem = OS.SendMessage(this.handle, 4362, 1L, hItem);
            }
            else {
                hItem = OS.SendMessage(this.handle, 4362, 6L, hItem);
            }
        }
        return index;
    }
    
    public TreeItem[] getSelection() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            final long hItem = OS.SendMessage(this.handle, 4362, 9L, 0L);
            if (hItem == 0L) {
                return new TreeItem[0];
            }
            final TVITEM tvItem = new TVITEM();
            tvItem.mask = 28;
            tvItem.hItem = hItem;
            OS.SendMessage(this.handle, 4414, 0L, tvItem);
            if ((tvItem.state & 0x2) == 0x0) {
                return new TreeItem[0];
            }
            final TreeItem item = this._getItem(tvItem.hItem, (int)tvItem.lParam);
            if (item == null) {
                return new TreeItem[0];
            }
            return new TreeItem[] { item };
        }
        else {
            int count = 0;
            final TreeItem[] guess = new TreeItem[((this.style & 0x10000000) != 0x0) ? 8 : 1];
            final long oldProc = OS.GetWindowLongPtr(this.handle, -4);
            OS.SetWindowLongPtr(this.handle, -4, Tree.TreeProc);
            if ((this.style & 0x10000000) != 0x0) {
                final TVITEM tvItem2 = new TVITEM();
                tvItem2.mask = 28;
                final long hItem2 = OS.SendMessage(this.handle, 4362, 0L, 0L);
                count = this.getSelection(hItem2, tvItem2, guess, 0, -1, false, true);
            }
            else {
                for (final TreeItem item2 : this.items) {
                    if (item2 != null) {
                        final long hItem3 = item2.handle;
                        final int state = (int)OS.SendMessage(this.handle, 4391, hItem3, 2L);
                        if ((state & 0x2) != 0x0) {
                            if (count < guess.length) {
                                guess[count] = item2;
                            }
                            ++count;
                        }
                    }
                }
            }
            OS.SetWindowLongPtr(this.handle, -4, oldProc);
            if (count == 0) {
                return new TreeItem[0];
            }
            if (count == guess.length) {
                return guess;
            }
            TreeItem[] result = new TreeItem[count];
            if (count < guess.length) {
                System.arraycopy(guess, 0, result, 0, count);
                return result;
            }
            OS.SetWindowLongPtr(this.handle, -4, Tree.TreeProc);
            final TVITEM tvItem3 = new TVITEM();
            tvItem3.mask = 28;
            final long hItem4 = OS.SendMessage(this.handle, 4362, 0L, 0L);
            final int itemCount = (int)OS.SendMessage(this.handle, 4357, 0L, 0L);
            final boolean bigSelection = result.length > itemCount / 2;
            if (count != this.getSelection(hItem4, tvItem3, result, 0, count, bigSelection, false)) {
                count = this.getSelection(hItem4, tvItem3, result, 0, count, bigSelection, true);
            }
            if (count != result.length) {
                final TreeItem[] newResult = new TreeItem[count];
                System.arraycopy(result, 0, newResult, 0, count);
                result = newResult;
            }
            OS.SetWindowLongPtr(this.handle, -4, oldProc);
            return result;
        }
    }
    
    public int getSelectionCount() {
        this.checkWidget();
        if ((this.style & 0x4) == 0x0) {
            int count = 0;
            final long oldProc = OS.GetWindowLongPtr(this.handle, -4);
            OS.SetWindowLongPtr(this.handle, -4, Tree.TreeProc);
            if ((this.style & 0x10000000) != 0x0) {
                final long hItem = OS.SendMessage(this.handle, 4362, 0L, 0L);
                count = this.getSelection(hItem, null, null, 0, -1, false, true);
            }
            else {
                for (final TreeItem item : this.items) {
                    if (item != null) {
                        final long hItem2 = item.handle;
                        final int state = (int)OS.SendMessage(this.handle, 4391, hItem2, 2L);
                        if ((state & 0x2) != 0x0) {
                            ++count;
                        }
                    }
                }
            }
            OS.SetWindowLongPtr(this.handle, -4, oldProc);
            return count;
        }
        final long hItem3 = OS.SendMessage(this.handle, 4362, 9L, 0L);
        if (hItem3 == 0L) {
            return 0;
        }
        final int state2 = (int)OS.SendMessage(this.handle, 4391, hItem3, 2L);
        return ((state2 & 0x2) != 0x0) ? 1 : 0;
    }
    
    public TreeColumn getSortColumn() {
        this.checkWidget();
        return this.sortColumn;
    }
    
    int getSortColumnPixel() {
        final int pixel = (OS.IsWindowEnabled(this.handle) || this.hasCustomBackground()) ? this.getBackgroundPixel() : OS.GetSysColor(15);
        return this.getSlightlyDifferentBackgroundColor(pixel);
    }
    
    public int getSortDirection() {
        this.checkWidget();
        return this.sortDirection;
    }
    
    public TreeItem getTopItem() {
        this.checkWidget();
        final long hItem = OS.SendMessage(this.handle, 4362, 5L, 0L);
        return (hItem != 0L) ? this._getItem(hItem) : null;
    }
    
    boolean hitTestSelection(final long hItem, final int x, final int y) {
        if (hItem == 0L) {
            return false;
        }
        final TreeItem item = this._getItem(hItem);
        if (item == null) {
            return false;
        }
        if (!this.hooks(41)) {
            return false;
        }
        boolean result = false;
        final int[] order = { 0 };
        final int[] index = { 0 };
        final long hDC = OS.GetDC(this.handle);
        long oldFont = 0L;
        final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
        if (newFont != 0L) {
            oldFont = OS.SelectObject(hDC, newFont);
        }
        long hFont = item.fontHandle(order[index[0]]);
        if (hFont != -1L) {
            hFont = OS.SelectObject(hDC, hFont);
        }
        final int state = (int)OS.SendMessage(this.handle, 4391, hItem, 2L);
        final int detail = ((state & 0x2) != 0x0) ? 2 : 0;
        final Event event = this.sendMeasureItemEvent(item, order[index[0]], hDC, detail);
        if (event.getBoundsInPixels().contains(x, y)) {
            result = true;
        }
        if (newFont != 0L) {
            OS.SelectObject(hDC, oldFont);
        }
        OS.ReleaseDC(this.handle, hDC);
        return result;
    }
    
    int imageIndex(final Image image, final int index) {
        if (image == null) {
            return -2;
        }
        if (this.imageList == null) {
            final Rectangle bounds = image.getBoundsInPixels();
            this.imageList = this.display.getImageList(this.style & 0x4000000, bounds.width, bounds.height);
        }
        int imageIndex = this.imageList.indexOf(image);
        if (imageIndex == -1) {
            imageIndex = this.imageList.add(image);
        }
        if (this.hwndHeader == 0L || OS.SendMessage(this.hwndHeader, 4623, 0L, 0L) == index) {
            final long hImageList = this.imageList.getHandle();
            final long hOldImageList = OS.SendMessage(this.handle, 4360, 0L, 0L);
            if (hOldImageList != hImageList) {
                OS.SendMessage(this.handle, 4361, 0L, hImageList);
                this.updateScrollBar();
            }
        }
        return imageIndex;
    }
    
    int imageIndexHeader(final Image image) {
        if (image == null) {
            return -2;
        }
        if (this.headerImageList == null) {
            final Rectangle bounds = image.getBoundsInPixels();
            this.headerImageList = this.display.getImageList(this.style & 0x4000000, bounds.width, bounds.height);
            int index = this.headerImageList.indexOf(image);
            if (index == -1) {
                index = this.headerImageList.add(image);
            }
            final long hImageList = this.headerImageList.getHandle();
            if (this.hwndHeader != 0L) {
                OS.SendMessage(this.hwndHeader, 4616, 0L, hImageList);
            }
            this.updateScrollBar();
            return index;
        }
        final int index2 = this.headerImageList.indexOf(image);
        if (index2 != -1) {
            return index2;
        }
        return this.headerImageList.add(image);
    }
    
    public int indexOf(final TreeColumn column) {
        this.checkWidget();
        if (column == null) {
            this.error(4);
        }
        if (column.isDisposed()) {
            this.error(5);
        }
        for (int i = 0; i < this.columnCount; ++i) {
            if (this.columns[i] == column) {
                return i;
            }
        }
        return -1;
    }
    
    public int indexOf(final TreeItem item) {
        this.checkWidget();
        if (item == null) {
            this.error(4);
        }
        if (item.isDisposed()) {
            this.error(5);
        }
        final long hItem = OS.SendMessage(this.handle, 4362, 0L, 0L);
        return (hItem == 0L) ? -1 : this.findIndex(hItem, item.handle);
    }
    
    boolean isCustomToolTip() {
        return this.hooks(41);
    }
    
    boolean isItemSelected(final NMTVCUSTOMDRAW nmcd) {
        boolean selected = false;
        if (OS.IsWindowEnabled(this.handle)) {
            final TVITEM tvItem = new TVITEM();
            tvItem.mask = 24;
            tvItem.hItem = nmcd.dwItemSpec;
            OS.SendMessage(this.handle, 4414, 0L, tvItem);
            if ((tvItem.state & 0xA) != 0x0) {
                selected = true;
                if (this.handle == OS.GetFocus()) {
                    if (OS.GetTextColor(nmcd.hdc) != OS.GetSysColor(14)) {
                        selected = false;
                    }
                    else if (OS.GetBkColor(nmcd.hdc) != OS.GetSysColor(13)) {
                        selected = false;
                    }
                }
            }
            else if (nmcd.dwDrawStage == 65538 && OS.GetTextColor(nmcd.hdc) == OS.GetSysColor(14) && OS.GetBkColor(nmcd.hdc) == OS.GetSysColor(13)) {
                selected = true;
            }
        }
        return selected;
    }
    
    boolean isUseWsBorder() {
        return true;
    }
    
    int itemsGetFreeCapacity() {
        int count = 0;
        for (final TreeItem item : this.items) {
            if (item == null) {
                ++count;
            }
        }
        return count;
    }
    
    void itemsGrowArray(final int newCapacity) {
        final TreeItem[] newItems = new TreeItem[newCapacity];
        System.arraycopy(this.items, 0, newItems, 0, this.items.length);
        this.items = newItems;
    }
    
    void redrawSelection() {
        if ((this.style & 0x4) != 0x0) {
            final long hItem = OS.SendMessage(this.handle, 4362, 9L, 0L);
            if (hItem != 0L) {
                final RECT rect = new RECT();
                if (OS.TreeView_GetItemRect(this.handle, hItem, rect, false)) {
                    OS.InvalidateRect(this.handle, rect, true);
                }
            }
        }
        else {
            long hItem = OS.SendMessage(this.handle, 4362, 5L, 0L);
            if (hItem != 0L) {
                final RECT rect = new RECT();
                for (int index = 0, count = (int)OS.SendMessage(this.handle, 4368, 0L, 0L); index <= count && hItem != 0L; hItem = OS.SendMessage(this.handle, 4362, 6L, hItem), ++index) {
                    final int state = (int)OS.SendMessage(this.handle, 4391, hItem, 2L);
                    if ((state & 0x2) != 0x0 && OS.TreeView_GetItemRect(this.handle, hItem, rect, false)) {
                        OS.InvalidateRect(this.handle, rect, true);
                    }
                }
            }
        }
    }
    
    void register() {
        super.register();
        if (this.hwndParent != 0L) {
            this.display.addControl(this.hwndParent, (Control)this);
        }
        if (this.hwndHeader != 0L) {
            this.display.addControl(this.hwndHeader, (Control)this);
        }
    }
    
    void releaseItem(final long hItem, final TVITEM tvItem, final boolean release) {
        if (hItem == this.hAnchor) {
            this.hAnchor = 0L;
        }
        if (hItem == this.hInsert) {
            this.hInsert = 0L;
        }
        tvItem.hItem = hItem;
        if (OS.SendMessage(this.handle, 4414, 0L, tvItem) != 0L && tvItem.lParam != -1L) {
            if (tvItem.lParam < this.lastID) {
                this.lastID = (int)tvItem.lParam;
            }
            if (release) {
                final TreeItem item = this.items[(int)tvItem.lParam];
                if (item != null) {
                    item.release(false);
                }
            }
            this.items[(int)tvItem.lParam] = null;
        }
    }
    
    void releaseItems(long hItem, final TVITEM tvItem) {
        for (hItem = OS.SendMessage(this.handle, 4362, 4L, hItem); hItem != 0L; hItem = OS.SendMessage(this.handle, 4362, 1L, hItem)) {
            this.releaseItems(hItem, tvItem);
            this.releaseItem(hItem, tvItem, true);
        }
    }
    
    void releaseHandle() {
        super.releaseHandle();
        final long n = 0L;
        this.hwndHeader = 0L;
        this.hwndParent = 0L;
    }
    
    void releaseChildren(final boolean destroy) {
        if (this.items != null) {
            for (final TreeItem item : this.items) {
                if (item != null && !item.isDisposed()) {
                    item.release(false);
                }
            }
            this.items = null;
        }
        if (this.columns != null) {
            for (final TreeColumn column : this.columns) {
                if (column != null && !column.isDisposed()) {
                    column.release(false);
                }
            }
            this.columns = null;
        }
        super.releaseChildren(destroy);
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.customDraw = false;
        if (this.imageList != null) {
            OS.SendMessage(this.handle, 4361, 0L, 0L);
            this.display.releaseImageList(this.imageList);
        }
        if (this.headerImageList != null) {
            if (this.hwndHeader != 0L) {
                OS.SendMessage(this.hwndHeader, 4616, 0L, 0L);
            }
            this.display.releaseImageList(this.headerImageList);
        }
        final ImageList list = null;
        this.headerImageList = list;
        this.imageList = list;
        final long hStateList = OS.SendMessage(this.handle, 4360, 2L, 0L);
        OS.SendMessage(this.handle, 4361, 2L, 0L);
        if (hStateList != 0L) {
            OS.ImageList_Destroy(hStateList);
        }
        if (this.itemToolTipHandle != 0L) {
            OS.DestroyWindow(this.itemToolTipHandle);
        }
        if (this.headerToolTipHandle != 0L) {
            OS.DestroyWindow(this.headerToolTipHandle);
        }
        final long n = 0L;
        this.headerToolTipHandle = 0L;
        this.itemToolTipHandle = 0L;
    }
    
    public void removeAll() {
        this.checkWidget();
        final long n = 0L;
        this.hLastIndexOf = 0L;
        this.hFirstIndexOf = 0L;
        this.itemCount = -1;
        for (final TreeItem item : this.items) {
            if (item != null && !item.isDisposed()) {
                item.release(false);
            }
        }
        final boolean b = true;
        this.ignoreSelect = true;
        this.ignoreDeselect = true;
        final boolean redraw = this.getDrawing() && OS.IsWindowVisible(this.handle);
        if (redraw) {
            OS.DefWindowProc(this.handle, 11, 0L, 0L);
        }
        final boolean b2 = true;
        this.ignoreShrink = true;
        this.shrink = true;
        final long result = OS.SendMessage(this.handle, 4353, 0L, -65536L);
        this.ignoreShrink = false;
        if (redraw) {
            OS.DefWindowProc(this.handle, 11, 1L, 0L);
            OS.InvalidateRect(this.handle, (RECT)null, true);
        }
        final boolean b3 = false;
        this.ignoreSelect = false;
        this.ignoreDeselect = false;
        if (result == 0L) {
            this.error(15);
        }
        if (this.imageList != null) {
            OS.SendMessage(this.handle, 4361, 0L, 0L);
            this.display.releaseImageList(this.imageList);
        }
        this.imageList = null;
        if (this.hwndParent == 0L && !this.linesVisible && !this.hooks(41) && !this.hooks(40) && !this.hooks(42)) {
            this.customDraw = false;
        }
        final long n2 = 0L;
        this.hLastIndexOf = 0L;
        this.hFirstIndexOf = 0L;
        this.hInsert = 0L;
        this.hAnchor = 0L;
        this.itemCount = -1;
        this.items = new TreeItem[4];
        this.scrollWidth = 0;
        this.setScrollWidth();
        this.updateScrollBar();
    }
    
    public void removeSelectionListener(final SelectionListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        this.eventTable.unhook(13, (SWTEventListener)listener);
        this.eventTable.unhook(14, (SWTEventListener)listener);
    }
    
    public void removeTreeListener(final TreeListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(17, (SWTEventListener)listener);
        this.eventTable.unhook(18, (SWTEventListener)listener);
    }
    
    void reskinChildren(final int flags) {
        if (this.items != null) {
            for (final TreeItem item : this.items) {
                if (item != null) {
                    item.reskinChildren(flags);
                }
            }
        }
        if (this.columns != null) {
            for (final TreeColumn column : this.columns) {
                if (column != null) {
                    column.reskinChildren(flags);
                }
            }
        }
        super.reskinChildren(flags);
    }
    
    public void setInsertMark(final TreeItem item, final boolean before) {
        this.checkWidget();
        long hItem = 0L;
        if (item != null) {
            if (item.isDisposed()) {
                this.error(5);
            }
            hItem = item.handle;
        }
        this.hInsert = hItem;
        this.insertAfter = !before;
        OS.SendMessage(this.handle, 4378, (long)(this.insertAfter ? 1 : 0), this.hInsert);
    }
    
    public void setItemCount(int count) {
        this.checkWidget();
        count = Math.max(0, count);
        this.setItemCount(count, -65536L);
    }
    
    void setItemCount(final int count, final long hParent) {
        long itemInsertAfter = 0L;
        int numInserted = 0;
        long itemDeleteFrom = 0L;
        int itemCount = 0;
        long itemPrev = -65535L;
        long itemNext;
        for (itemNext = OS.SendMessage(this.handle, 4362, 4L, hParent); itemNext != 0L && itemCount < count; itemNext = OS.SendMessage(this.handle, 4362, 1L, itemNext), ++itemCount) {
            itemPrev = itemNext;
        }
        if (itemCount == count && itemNext == 0L) {
            return;
        }
        if (itemCount == count) {
            itemDeleteFrom = itemNext;
        }
        else if (itemNext == 0L) {
            itemInsertAfter = itemPrev;
            numInserted = count - itemCount;
        }
        boolean redraw = false;
        if (OS.SendMessage(this.handle, 4357, 0L, 0L) == 0L) {
            redraw = (this.getDrawing() && OS.IsWindowVisible(this.handle));
            if (redraw) {
                OS.DefWindowProc(this.handle, 11, 0L, 0L);
            }
        }
        boolean expanded = false;
        final TVITEM tvItem = new TVITEM();
        tvItem.mask = 20;
        if (!redraw && (this.style & 0x10000000) != 0x0 && hParent != -65536L) {
            final int state = (int)OS.SendMessage(this.handle, 4391, hParent, 32L);
            expanded = ((state & 0x20) != 0x0);
        }
        if (itemDeleteFrom != 0L) {
            while (itemDeleteFrom != 0L) {
                tvItem.hItem = itemDeleteFrom;
                OS.SendMessage(this.handle, 4414, 0L, tvItem);
                itemDeleteFrom = OS.SendMessage(this.handle, 4362, 1L, itemDeleteFrom);
                final TreeItem item = (tvItem.lParam != -1L) ? this.items[(int)tvItem.lParam] : null;
                if (item != null && !item.isDisposed()) {
                    item.dispose();
                }
                else {
                    this.releaseItem(tvItem.hItem, tvItem, false);
                    this.destroyItem(null, tvItem.hItem);
                }
            }
        }
        else {
            final int freeCapacity = this.itemsGetFreeCapacity();
            if (numInserted > freeCapacity) {
                this.itemsGrowArray(this.items.length + numInserted - freeCapacity);
            }
            if ((this.style & 0x10000000) != 0x0) {
                for (int i = 0; i < numInserted; ++i) {
                    if (expanded) {
                        this.ignoreShrink = true;
                    }
                    this.createItem(null, hParent, itemInsertAfter, 0L);
                    if (expanded) {
                        this.ignoreShrink = false;
                    }
                }
            }
            else {
                for (int i = 0; i < numInserted; ++i) {
                    new TreeItem(this, 0, hParent, itemInsertAfter, 0L);
                }
            }
        }
        if (redraw) {
            OS.DefWindowProc(this.handle, 11, 1L, 0L);
            OS.InvalidateRect(this.handle, (RECT)null, true);
        }
    }
    
    void setItemHeight(final int itemHeight) {
        this.checkWidget();
        if (itemHeight < -1) {
            this.error(5);
        }
        OS.SendMessage(this.handle, 4379, (long)itemHeight, 0L);
    }
    
    public void setLinesVisible(final boolean show) {
        this.checkWidget();
        if (this.linesVisible == show) {
            return;
        }
        this.linesVisible = show;
        if (this.hwndParent == 0L && this.linesVisible) {
            this.customDraw = true;
        }
        OS.InvalidateRect(this.handle, (RECT)null, true);
        if (this.hwndHeader != 0L) {
            OS.InvalidateRect(this.hwndHeader, (RECT)null, true);
        }
    }
    
    long scrolledHandle() {
        if (this.hwndHeader == 0L) {
            return this.handle;
        }
        return (this.columnCount == 0 && this.scrollWidth == 0) ? this.handle : this.hwndParent;
    }
    
    void select(long hItem, final TVITEM tvItem) {
        while (hItem != 0L) {
            tvItem.hItem = hItem;
            OS.SendMessage(this.handle, 4415, 0L, tvItem);
            final int state = (int)OS.SendMessage(this.handle, 4391, hItem, 32L);
            if ((state & 0x20) != 0x0) {
                final long hFirstItem = OS.SendMessage(this.handle, 4362, 4L, hItem);
                this.select(hFirstItem, tvItem);
            }
            hItem = OS.SendMessage(this.handle, 4362, 1L, hItem);
        }
    }
    
    public void select(final TreeItem item) {
        this.checkWidget();
        if (item == null) {
            this.error(4);
        }
        if (item.isDisposed()) {
            this.error(5);
        }
        if ((this.style & 0x4) == 0x0) {
            this.expandToItem(item);
            final TVITEM tvItem = new TVITEM();
            tvItem.mask = 24;
            tvItem.stateMask = 2;
            tvItem.state = 2;
            tvItem.hItem = item.handle;
            OS.SendMessage(this.handle, 4415, 0L, tvItem);
            return;
        }
        final long hItem = item.handle;
        final int state = (int)OS.SendMessage(this.handle, 4391, hItem, 2L);
        if ((state & 0x2) != 0x0) {
            return;
        }
        SCROLLINFO hInfo = null;
        final int bits = OS.GetWindowLong(this.handle, -16);
        if ((bits & 0xA000) == 0x0) {
            hInfo = new SCROLLINFO();
            hInfo.cbSize = SCROLLINFO.sizeof;
            hInfo.fMask = 23;
            OS.GetScrollInfo(this.handle, 0, hInfo);
        }
        final SCROLLINFO vInfo = new SCROLLINFO();
        vInfo.cbSize = SCROLLINFO.sizeof;
        vInfo.fMask = 23;
        OS.GetScrollInfo(this.handle, 1, vInfo);
        final boolean redraw = this.getDrawing() && OS.IsWindowVisible(this.handle);
        if (redraw) {
            OS.UpdateWindow(this.handle);
            OS.DefWindowProc(this.handle, 11, 0L, 0L);
        }
        this.setSelection(item);
        if (hInfo != null) {
            final long hThumb = OS.MAKELPARAM(4, hInfo.nPos);
            OS.SendMessage(this.handle, 276, hThumb, 0L);
        }
        OS.SetScrollInfo(this.handle, 1, vInfo, true);
        final long vThumb = OS.MAKELPARAM(4, vInfo.nPos);
        OS.SendMessage(this.handle, 277, vThumb, 0L);
        if (redraw) {
            OS.DefWindowProc(this.handle, 11, 1L, 0L);
            OS.InvalidateRect(this.handle, (RECT)null, true);
            if ((this.style & 0x20000000) == 0x0) {
                final int oldStyle = this.style;
                this.style |= 0x20000000;
                OS.UpdateWindow(this.handle);
                this.style = oldStyle;
            }
        }
    }
    
    public void selectAll() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            return;
        }
        final TVITEM tvItem = new TVITEM();
        tvItem.mask = 24;
        tvItem.state = 2;
        tvItem.stateMask = 2;
        final long oldProc = OS.GetWindowLongPtr(this.handle, -4);
        OS.SetWindowLongPtr(this.handle, -4, Tree.TreeProc);
        final long hItem = OS.SendMessage(this.handle, 4362, 0L, 0L);
        this.select(hItem, tvItem);
        OS.SetWindowLongPtr(this.handle, -4, oldProc);
    }
    
    Event sendEraseItemEvent(final TreeItem item, final NMTTCUSTOMDRAW nmcd, final int column, final RECT cellRect) {
        final int nSavedDC = OS.SaveDC(nmcd.hdc);
        final RECT insetRect = this.toolTipInset(cellRect);
        OS.SetWindowOrgEx(nmcd.hdc, insetRect.left, insetRect.top, (POINT)null);
        final GCData data = new GCData();
        data.device = (Device)this.display;
        data.foreground = OS.GetTextColor(nmcd.hdc);
        data.background = OS.GetBkColor(nmcd.hdc);
        data.font = item.getFont(column);
        data.uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
        final GC gc = GC.win32_new(nmcd.hdc, data);
        final Event event = new Event();
        event.item = (Widget)item;
        event.index = column;
        event.gc = gc;
        final Event event3;
        final Event event2 = event3 = event;
        event3.detail |= 0x10;
        event.setBoundsInPixels(new Rectangle(cellRect.left, cellRect.top, cellRect.right - cellRect.left, cellRect.bottom - cellRect.top));
        this.sendEvent(40, event);
        event.gc = null;
        gc.dispose();
        OS.RestoreDC(nmcd.hdc, nSavedDC);
        return event;
    }
    
    Event sendMeasureItemEvent(final TreeItem item, final int index, final long hDC, final int detail) {
        final RECT itemRect = item.getBounds(index, true, true, false, false, false, hDC);
        final int nSavedDC = OS.SaveDC(hDC);
        final GCData data = new GCData();
        data.device = (Device)this.display;
        data.font = item.getFont(index);
        final GC gc = GC.win32_new(hDC, data);
        final Event event = new Event();
        event.item = (Widget)item;
        event.gc = gc;
        event.index = index;
        event.setBoundsInPixels(new Rectangle(itemRect.left, itemRect.top, itemRect.right - itemRect.left, itemRect.bottom - itemRect.top));
        event.detail = detail;
        this.sendEvent(41, event);
        event.gc = null;
        gc.dispose();
        OS.RestoreDC(hDC, nSavedDC);
        if (this.isDisposed() || item.isDisposed()) {
            return null;
        }
        final Rectangle rect = event.getBoundsInPixels();
        if (this.hwndHeader != 0L && this.columnCount == 0 && rect.x + rect.width > this.scrollWidth) {
            this.setScrollWidth(this.scrollWidth = rect.x + rect.width);
        }
        if (rect.height > this.getItemHeightInPixels()) {
            this.setItemHeight(rect.height);
        }
        return event;
    }
    
    Event sendPaintItemEvent(final TreeItem item, final NMTTCUSTOMDRAW nmcd, final int column, final RECT itemRect) {
        final int nSavedDC = OS.SaveDC(nmcd.hdc);
        final RECT insetRect = this.toolTipInset(itemRect);
        OS.SetWindowOrgEx(nmcd.hdc, insetRect.left, insetRect.top, (POINT)null);
        final GCData data = new GCData();
        data.device = (Device)this.display;
        data.font = item.getFont(column);
        data.foreground = OS.GetTextColor(nmcd.hdc);
        data.background = OS.GetBkColor(nmcd.hdc);
        data.uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
        final GC gc = GC.win32_new(nmcd.hdc, data);
        final Event event = new Event();
        event.item = (Widget)item;
        event.index = column;
        event.gc = gc;
        final Event event3;
        final Event event2 = event3 = event;
        event3.detail |= 0x10;
        event.setBoundsInPixels(new Rectangle(itemRect.left, itemRect.top, itemRect.right - itemRect.left, itemRect.bottom - itemRect.top));
        this.sendEvent(42, event);
        event.gc = null;
        gc.dispose();
        OS.RestoreDC(nmcd.hdc, nSavedDC);
        return event;
    }
    
    void setBackgroundImage(final long hBitmap) {
        super.setBackgroundImage(hBitmap);
        if (hBitmap != 0L) {
            if (OS.SendMessage(this.handle, 4383, 0L, 0L) == -1L) {
                OS.SendMessage(this.handle, 4381, 0L, -1L);
            }
            this._setBackgroundPixel(-1);
        }
        else {
            Control control = this.findBackgroundControl();
            if (control == null) {
                control = (Control)this;
            }
            if (control.backgroundImage == null) {
                this.setBackgroundPixel(control.getBackgroundPixel());
            }
        }
        this.updateFullSelection();
    }
    
    void setBackgroundPixel(final int pixel) {
        final Control control = this.findImageControl();
        if (control != null) {
            this.setBackgroundImage(control.backgroundImage);
            return;
        }
        this._setBackgroundPixel(pixel);
        this.updateFullSelection();
    }
    
    void setCursor() {
        final Cursor cursor = this.findCursor();
        final long hCursor = (cursor == null) ? OS.LoadCursor(0L, 32512L) : cursor.handle;
        OS.SetCursor(hCursor);
    }
    
    public void setColumnOrder(final int[] order) {
        this.checkWidget();
        if (order == null) {
            this.error(4);
        }
        if (this.columnCount == 0) {
            if (order.length != 0) {
                this.error(5);
            }
            return;
        }
        if (order.length != this.columnCount) {
            this.error(5);
        }
        final int[] oldOrder = new int[this.columnCount];
        OS.SendMessage(this.hwndHeader, 4625, (long)this.columnCount, oldOrder);
        boolean reorder = false;
        final boolean[] seen = new boolean[this.columnCount];
        for (int i = 0; i < order.length; ++i) {
            final int index = order[i];
            if (index < 0 || index >= this.columnCount) {
                this.error(6);
            }
            if (seen[index]) {
                this.error(5);
            }
            seen[index] = true;
            if (index != oldOrder[i]) {
                reorder = true;
            }
        }
        if (reorder) {
            final RECT[] oldRects = new RECT[this.columnCount];
            for (int j = 0; j < this.columnCount; ++j) {
                oldRects[j] = new RECT();
                OS.SendMessage(this.hwndHeader, 4615, (long)j, oldRects[j]);
            }
            OS.SendMessage(this.hwndHeader, 4626, (long)order.length, order);
            OS.InvalidateRect(this.handle, (RECT)null, true);
            this.updateImageList();
            final TreeColumn[] newColumns = new TreeColumn[this.columnCount];
            System.arraycopy(this.columns, 0, newColumns, 0, this.columnCount);
            final RECT newRect = new RECT();
            for (int k = 0; k < this.columnCount; ++k) {
                final TreeColumn column = newColumns[k];
                if (!column.isDisposed()) {
                    OS.SendMessage(this.hwndHeader, 4615, (long)k, newRect);
                    if (newRect.left != oldRects[k].left) {
                        column.updateToolTip(k);
                        column.sendEvent(10);
                    }
                }
            }
        }
    }
    
    void setCheckboxImageList() {
        if ((this.style & 0x20) == 0x0) {
            return;
        }
        final int count = 5;
        int flags = 32;
        if ((this.style & 0x4000000) != 0x0) {
            flags |= 0x2000;
        }
        if (!OS.IsAppThemed()) {
            flags |= 0x1;
        }
        final int height;
        final int width = height = (int)OS.SendMessage(this.handle, 4380, 0L, 0L);
        final long hStateList = OS.ImageList_Create(width, height, flags, 5, 5);
        final long hDC = OS.GetDC(this.handle);
        final long memDC = OS.CreateCompatibleDC(hDC);
        final long hBitmap = OS.CreateCompatibleBitmap(hDC, width * 5, height);
        final long hOldBitmap = OS.SelectObject(memDC, hBitmap);
        final RECT rect = new RECT();
        OS.SetRect(rect, 0, 0, width * 5, height);
        int clrBackground = 0;
        if (OS.IsAppThemed()) {
            Control control = this.findBackgroundControl();
            if (control == null) {
                control = (Control)this;
            }
            clrBackground = control.getBackgroundPixel();
        }
        else {
            clrBackground = 33554687;
            if ((clrBackground & 0xFFFFFF) == OS.GetSysColor(5)) {
                clrBackground = 33619712;
            }
        }
        final long hBrush = OS.CreateSolidBrush(clrBackground);
        OS.FillRect(memDC, rect, hBrush);
        OS.DeleteObject(hBrush);
        final long oldFont = OS.SelectObject(hDC, this.defaultFont());
        final TEXTMETRIC tm = new TEXTMETRIC();
        OS.GetTextMetrics(hDC, tm);
        OS.SelectObject(hDC, oldFont);
        int itemWidth = Math.min(tm.tmHeight, width);
        int itemHeight = Math.min(tm.tmHeight, height);
        if (OS.IsAppThemed()) {
            final SIZE size = new SIZE();
            OS.GetThemePartSize(this.display.hButtonTheme(), memDC, 3, 0, (RECT)null, 1, size);
            itemWidth = Math.min(size.cx, itemWidth);
            itemHeight = Math.min(size.cy, itemHeight);
        }
        final int left = (width - itemWidth) / 2;
        final int top = (height - itemHeight) / 2 + 1;
        OS.SetRect(rect, left + width, top, left + width + itemWidth, top + itemHeight);
        if (OS.IsAppThemed()) {
            final long hTheme = this.display.hButtonTheme();
            OS.DrawThemeBackground(hTheme, memDC, 3, 1, rect, (RECT)null);
            final RECT rect14;
            final RECT rect2 = rect14 = rect;
            rect14.left += width;
            final RECT rect15;
            final RECT rect3 = rect15 = rect;
            rect15.right += width;
            OS.DrawThemeBackground(hTheme, memDC, 3, 5, rect, (RECT)null);
            final RECT rect16;
            final RECT rect4 = rect16 = rect;
            rect16.left += width;
            final RECT rect17;
            final RECT rect5 = rect17 = rect;
            rect17.right += width;
            OS.DrawThemeBackground(hTheme, memDC, 3, 1, rect, (RECT)null);
            final RECT rect18;
            final RECT rect6 = rect18 = rect;
            rect18.left += width;
            final RECT rect19;
            final RECT rect7 = rect19 = rect;
            rect19.right += width;
            OS.DrawThemeBackground(hTheme, memDC, 3, 9, rect, (RECT)null);
        }
        else {
            OS.DrawFrameControl(memDC, rect, 4, 16384);
            final RECT rect20;
            final RECT rect8 = rect20 = rect;
            rect20.left += width;
            final RECT rect21;
            final RECT rect9 = rect21 = rect;
            rect21.right += width;
            OS.DrawFrameControl(memDC, rect, 4, 17408);
            final RECT rect22;
            final RECT rect10 = rect22 = rect;
            rect22.left += width;
            final RECT rect23;
            final RECT rect11 = rect23 = rect;
            rect23.right += width;
            OS.DrawFrameControl(memDC, rect, 4, 16640);
            final RECT rect24;
            final RECT rect12 = rect24 = rect;
            rect24.left += width;
            final RECT rect25;
            final RECT rect13 = rect25 = rect;
            rect25.right += width;
            OS.DrawFrameControl(memDC, rect, 4, 17664);
        }
        OS.SelectObject(memDC, hOldBitmap);
        OS.DeleteDC(memDC);
        OS.ReleaseDC(this.handle, hDC);
        if (OS.IsAppThemed()) {
            OS.ImageList_Add(hStateList, hBitmap, 0L);
        }
        else {
            OS.ImageList_AddMasked(hStateList, hBitmap, clrBackground);
        }
        OS.DeleteObject(hBitmap);
        final long hOldStateList = OS.SendMessage(this.handle, 4360, 2L, 0L);
        OS.SendMessage(this.handle, 4361, 2L, hStateList);
        if (hOldStateList != 0L) {
            OS.ImageList_Destroy(hOldStateList);
        }
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        super.setFont(font);
        if ((this.style & 0x20) != 0x0) {
            this.setCheckboxImageList();
        }
    }
    
    void setForegroundPixel(int pixel) {
        if (this.explorerTheme && pixel == -1) {
            pixel = this.defaultForeground();
        }
        OS.SendMessage(this.handle, 4382, 0L, (long)pixel);
    }
    
    public void setHeaderBackground(final Color color) {
        this.checkWidget();
        int pixel = -1;
        if (color != null) {
            if (color.isDisposed()) {
                this.error(5);
            }
            pixel = color.handle;
        }
        if (pixel == this.headerBackground) {
            return;
        }
        this.headerBackground = pixel;
        if (this.getHeaderVisible()) {
            OS.InvalidateRect(this.hwndHeader, (RECT)null, true);
        }
    }
    
    public void setHeaderForeground(final Color color) {
        this.checkWidget();
        int pixel = -1;
        if (color != null) {
            if (color.isDisposed()) {
                this.error(5);
            }
            pixel = color.handle;
        }
        if (pixel == this.headerForeground) {
            return;
        }
        this.headerForeground = pixel;
        if (this.getHeaderVisible()) {
            OS.InvalidateRect(this.hwndHeader, (RECT)null, true);
        }
    }
    
    public void setHeaderVisible(final boolean show) {
        this.checkWidget();
        if (this.hwndHeader == 0L) {
            if (!show) {
                return;
            }
            this.createParent();
        }
        int bits = OS.GetWindowLong(this.hwndHeader, -16);
        if (show) {
            if ((bits & 0x8) == 0x0) {
                return;
            }
            bits &= 0xFFFFFFF7;
            OS.SetWindowLong(this.hwndHeader, -16, bits);
            OS.ShowWindow(this.hwndHeader, 5);
        }
        else {
            if ((bits & 0x8) != 0x0) {
                return;
            }
            bits |= 0x8;
            OS.SetWindowLong(this.hwndHeader, -16, bits);
            OS.ShowWindow(this.hwndHeader, 0);
        }
        this.setScrollWidth();
        this.updateHeaderToolTips();
        this.updateScrollBar();
    }
    
    public void setRedraw(final boolean redraw) {
        this.checkWidget();
        long hItem = 0L;
        final boolean willEnableDraw = redraw && this.drawCount == 1;
        if (willEnableDraw) {
            final int count = (int)OS.SendMessage(this.handle, 4357, 0L, 0L);
            if (count == 0) {
                final TVINSERTSTRUCT tvInsert = new TVINSERTSTRUCT();
                tvInsert.hInsertAfter = -65535L;
                hItem = OS.SendMessage(this.handle, 4402, 0L, tvInsert);
            }
            OS.DefWindowProc(this.handle, 11, 1L, 0L);
            this.updateScrollBar();
        }
        super.setRedraw(redraw);
        final boolean haveDisabledDraw = !redraw && this.drawCount == 1;
        if (haveDisabledDraw) {
            OS.DefWindowProc(this.handle, 11, 0L, 0L);
        }
        if (hItem != 0L) {
            this.ignoreShrink = true;
            OS.SendMessage(this.handle, 4353, 0L, hItem);
            this.ignoreShrink = false;
        }
    }
    
    void setScrollWidth() {
        if (this.hwndHeader == 0L || this.hwndParent == 0L) {
            return;
        }
        int width = 0;
        final HDITEM hdItem = new HDITEM();
        for (int i = 0; i < this.columnCount; ++i) {
            hdItem.mask = 1;
            OS.SendMessage(this.hwndHeader, 4619, (long)i, hdItem);
            width += hdItem.cxy;
        }
        this.setScrollWidth(Math.max(this.scrollWidth, width));
    }
    
    void setScrollWidth(final int width) {
        if (this.hwndHeader == 0L || this.hwndParent == 0L) {
            return;
        }
        int left = 0;
        final RECT rect = new RECT();
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        info.fMask = 3;
        if (this.columnCount == 0 && width == 0) {
            OS.GetScrollInfo(this.hwndParent, 0, info);
            info.nPage = info.nMax + 1;
            OS.SetScrollInfo(this.hwndParent, 0, info, true);
            OS.GetScrollInfo(this.hwndParent, 1, info);
            info.nPage = info.nMax + 1;
            OS.SetScrollInfo(this.hwndParent, 1, info, true);
        }
        else if ((this.style & 0x100) != 0x0) {
            OS.GetClientRect(this.hwndParent, rect);
            OS.GetScrollInfo(this.hwndParent, 0, info);
            info.nMax = width;
            info.nPage = rect.right - rect.left + 1;
            OS.SetScrollInfo(this.hwndParent, 0, info, true);
            info.fMask = 4;
            OS.GetScrollInfo(this.hwndParent, 0, info);
            left = info.nPos;
        }
        if (this.horizontalBar != null) {
            this.horizontalBar.setIncrement(5);
            this.horizontalBar.setPageIncrement(info.nPage);
        }
        OS.GetClientRect(this.hwndParent, rect);
        final long hHeap = OS.GetProcessHeap();
        final HDLAYOUT playout = new HDLAYOUT();
        playout.prc = OS.HeapAlloc(hHeap, 8, RECT.sizeof);
        playout.pwpos = OS.HeapAlloc(hHeap, 8, WINDOWPOS.sizeof);
        OS.MoveMemory(playout.prc, rect, RECT.sizeof);
        OS.SendMessage(this.hwndHeader, 4613, 0L, playout);
        final WINDOWPOS pos = new WINDOWPOS();
        OS.MoveMemory(pos, playout.pwpos, WINDOWPOS.sizeof);
        if (playout.prc != 0L) {
            OS.HeapFree(hHeap, 0, playout.prc);
        }
        if (playout.pwpos != 0L) {
            OS.HeapFree(hHeap, 0, playout.pwpos);
        }
        OS.SetWindowPos(this.hwndHeader, 0L, pos.x - left, pos.y, pos.cx + left, pos.cy, 16);
        final int w = pos.cx + ((this.columnCount == 0 && width == 0) ? 0 : OS.GetSystemMetrics(2));
        final int h = rect.bottom - rect.top - pos.cy;
        final boolean oldIgnore = this.ignoreResize;
        this.ignoreResize = true;
        OS.SetWindowPos(this.handle, 0L, pos.x - left, pos.y + pos.cy, w + left, h, 20);
        this.ignoreResize = oldIgnore;
    }
    
    void setSelection(long hItem, final TVITEM tvItem, final TreeItem[] selection) {
        while (hItem != 0L) {
            int index;
            for (index = 0; index < selection.length; ++index) {
                final TreeItem item = selection[index];
                if (item != null && item.handle == hItem) {
                    break;
                }
            }
            tvItem.hItem = hItem;
            OS.SendMessage(this.handle, 4414, 0L, tvItem);
            if ((tvItem.state & 0x2) != 0x0) {
                if (index == selection.length) {
                    tvItem.state = 0;
                    OS.SendMessage(this.handle, 4415, 0L, tvItem);
                }
            }
            else if (index != selection.length) {
                this.expandToItem(this._getItem(hItem));
                tvItem.state = 2;
                OS.SendMessage(this.handle, 4415, 0L, tvItem);
            }
            final long hFirstItem = OS.SendMessage(this.handle, 4362, 4L, hItem);
            this.setSelection(hFirstItem, tvItem, selection);
            hItem = OS.SendMessage(this.handle, 4362, 1L, hItem);
        }
    }
    
    public void setSelection(final TreeItem item) {
        this.checkWidget();
        if (item == null) {
            this.error(4);
        }
        this.setSelection(new TreeItem[] { item });
    }
    
    public void setSelection(final TreeItem[] items) {
        this.checkWidget();
        if (items == null) {
            this.error(4);
        }
        final int length = items.length;
        if (length == 0 || ((this.style & 0x4) != 0x0 && length > 1)) {
            this.deselectAll();
            return;
        }
        TreeItem item = items[0];
        if (item != null) {
            if (item.isDisposed()) {
                this.error(5);
            }
            final long hOldItem = OS.SendMessage(this.handle, 4362, 9L, 0L);
            final long handle = item.handle;
            this.hAnchor = handle;
            final long hNewItem = handle;
            final boolean fixScroll = this.checkScroll(hNewItem);
            if (fixScroll) {
                OS.SendMessage(this.handle, 11, 1L, 0L);
                OS.DefWindowProc(this.handle, 11, 0L, 0L);
            }
            this.ignoreSelect = true;
            OS.SendMessage(this.handle, 4363, 9L, hNewItem);
            this.ignoreSelect = false;
            if (OS.SendMessage(this.handle, 4368, 0L, 0L) == 0L) {
                OS.SendMessage(this.handle, 4363, 5L, hNewItem);
                final long hParent = OS.SendMessage(this.handle, 4362, 3L, hNewItem);
                if (hParent == 0L) {
                    OS.SendMessage(this.handle, 276, 6L, 0L);
                }
            }
            if (fixScroll) {
                OS.DefWindowProc(this.handle, 11, 1L, 0L);
                OS.SendMessage(this.handle, 11, 0L, 0L);
            }
            if (hOldItem == hNewItem) {
                final TVITEM tvItem = new TVITEM();
                tvItem.mask = 24;
                tvItem.state = 2;
                tvItem.stateMask = 2;
                tvItem.hItem = hNewItem;
                OS.SendMessage(this.handle, 4415, 0L, tvItem);
                this.showItem(hNewItem);
            }
        }
        if ((this.style & 0x4) != 0x0) {
            return;
        }
        final TVITEM tvItem2 = new TVITEM();
        tvItem2.mask = 24;
        tvItem2.stateMask = 2;
        final long oldProc = OS.GetWindowLongPtr(this.handle, -4);
        OS.SetWindowLongPtr(this.handle, -4, Tree.TreeProc);
        if ((this.style & 0x10000000) != 0x0) {
            final long hItem = OS.SendMessage(this.handle, 4362, 0L, 0L);
            this.setSelection(hItem, tvItem2, items);
        }
        else {
            for (final TreeItem item2 : this.items) {
                item = item2;
                if (item != null) {
                    int index;
                    for (index = 0; index < length && items[index] != item; ++index) {}
                    tvItem2.hItem = item.handle;
                    OS.SendMessage(this.handle, 4414, 0L, tvItem2);
                    if ((tvItem2.state & 0x2) != 0x0) {
                        if (index == length) {
                            tvItem2.state = 0;
                            OS.SendMessage(this.handle, 4415, 0L, tvItem2);
                        }
                    }
                    else if (index != length) {
                        this.expandToItem(item);
                        tvItem2.state = 2;
                        OS.SendMessage(this.handle, 4415, 0L, tvItem2);
                    }
                }
            }
        }
        OS.SetWindowLongPtr(this.handle, -4, oldProc);
    }
    
    void expandToItem(final TreeItem item) {
        final TreeItem parentItem = item.getParentItem();
        if (parentItem != null && !parentItem.getExpanded()) {
            this.expandToItem(parentItem);
            parentItem.setExpanded(true);
            final Event event = new Event();
            event.item = (Widget)parentItem;
            this.sendEvent(17, event);
        }
    }
    
    public void setSortColumn(final TreeColumn column) {
        this.checkWidget();
        if (column != null && column.isDisposed()) {
            this.error(5);
        }
        if (this.sortColumn != null && !this.sortColumn.isDisposed()) {
            this.sortColumn.setSortDirection(0);
        }
        this.sortColumn = column;
        if (this.sortColumn != null && this.sortDirection != 0) {
            this.sortColumn.setSortDirection(this.sortDirection);
        }
    }
    
    public void setSortDirection(final int direction) {
        this.checkWidget();
        if ((direction & 0x480) == 0x0 && direction != 0) {
            return;
        }
        this.sortDirection = direction;
        if (this.sortColumn != null && !this.sortColumn.isDisposed()) {
            this.sortColumn.setSortDirection(direction);
        }
    }
    
    public void setTopItem(final TreeItem item) {
        this.checkWidget();
        if (item == null) {
            this.error(4);
        }
        if (item.isDisposed()) {
            this.error(5);
        }
        final long hItem = item.handle;
        final long hTopItem = OS.SendMessage(this.handle, 4362, 5L, 0L);
        if (hItem == hTopItem) {
            return;
        }
        final boolean fixScroll = this.checkScroll(hItem);
        boolean redraw = false;
        if (fixScroll) {
            OS.SendMessage(this.handle, 11, 1L, 0L);
            OS.DefWindowProc(this.handle, 11, 0L, 0L);
        }
        else {
            redraw = (this.getDrawing() && OS.IsWindowVisible(this.handle));
            if (redraw) {
                OS.DefWindowProc(this.handle, 11, 0L, 0L);
            }
        }
        SCROLLINFO hInfo = null;
        final int bits = OS.GetWindowLong(this.handle, -16);
        final long hParent = OS.SendMessage(this.handle, 4362, 3L, hItem);
        if (hParent != 0L && (bits & 0xA000) == 0x0) {
            hInfo = new SCROLLINFO();
            hInfo.cbSize = SCROLLINFO.sizeof;
            hInfo.fMask = 23;
            OS.GetScrollInfo(this.handle, 0, hInfo);
        }
        OS.SendMessage(this.handle, 4363, 5L, hItem);
        if (hParent != 0L) {
            if (hInfo != null) {
                final long hThumb = OS.MAKELPARAM(4, hInfo.nPos);
                OS.SendMessage(this.handle, 276, hThumb, 0L);
            }
        }
        else {
            OS.SendMessage(this.handle, 276, 6L, 0L);
        }
        if (fixScroll) {
            OS.DefWindowProc(this.handle, 11, 1L, 0L);
            OS.SendMessage(this.handle, 11, 0L, 0L);
        }
        else if (redraw) {
            OS.DefWindowProc(this.handle, 11, 1L, 0L);
            OS.InvalidateRect(this.handle, (RECT)null, true);
        }
        this.updateScrollBar();
    }
    
    void showItem(final long hItem) {
        if (OS.SendMessage(this.handle, 4368, 0L, 0L) == 0L) {
            final boolean fixScroll = this.checkScroll(hItem);
            if (fixScroll) {
                OS.SendMessage(this.handle, 11, 1L, 0L);
                OS.DefWindowProc(this.handle, 11, 0L, 0L);
            }
            OS.SendMessage(this.handle, 4363, 5L, hItem);
            OS.SendMessage(this.handle, 276, 6L, 0L);
            if (fixScroll) {
                OS.DefWindowProc(this.handle, 11, 1L, 0L);
                OS.SendMessage(this.handle, 11, 0L, 0L);
            }
        }
        else {
            boolean scroll = true;
            final RECT itemRect = new RECT();
            if (OS.TreeView_GetItemRect(this.handle, hItem, itemRect, true)) {
                this.forceResize();
                final RECT rect = new RECT();
                OS.GetClientRect(this.handle, rect);
                final POINT pt = new POINT();
                pt.x = itemRect.left;
                pt.y = itemRect.top;
                if (OS.PtInRect(rect, pt)) {
                    pt.y = itemRect.bottom;
                    if (OS.PtInRect(rect, pt)) {
                        scroll = false;
                    }
                }
            }
            if (scroll) {
                final boolean fixScroll2 = this.checkScroll(hItem);
                if (fixScroll2) {
                    OS.SendMessage(this.handle, 11, 1L, 0L);
                    OS.DefWindowProc(this.handle, 11, 0L, 0L);
                }
                OS.SendMessage(this.handle, 4372, 0L, hItem);
                if (fixScroll2) {
                    OS.DefWindowProc(this.handle, 11, 1L, 0L);
                    OS.SendMessage(this.handle, 11, 0L, 0L);
                }
            }
        }
        this.updateScrollBar();
    }
    
    public void showColumn(final TreeColumn column) {
        this.checkWidget();
        if (column == null) {
            this.error(4);
        }
        if (column.isDisposed()) {
            this.error(5);
        }
        if (column.parent != this) {
            return;
        }
        final int index = this.indexOf(column);
        if (index == -1) {
            return;
        }
        if (0 <= index && index < this.columnCount) {
            this.forceResize();
            final RECT rect = new RECT();
            OS.GetClientRect(this.hwndParent, rect);
            OS.MapWindowPoints(this.hwndParent, this.handle, rect, 2);
            final RECT headerRect = new RECT();
            OS.SendMessage(this.hwndHeader, 4615, (long)index, headerRect);
            final boolean scrollBecauseLeft = headerRect.left < rect.left;
            boolean scrollBecauseRight = false;
            if (!scrollBecauseLeft) {
                final int width = Math.min(rect.right - rect.left, headerRect.right - headerRect.left);
                scrollBecauseRight = (headerRect.left + width > rect.right);
            }
            if (scrollBecauseLeft || headerRect.right - headerRect.left > rect.right - rect.left) {
                final SCROLLINFO info = new SCROLLINFO();
                info.cbSize = SCROLLINFO.sizeof;
                info.fMask = 4;
                info.nPos = Math.max(0, headerRect.left - 1);
                OS.SetScrollInfo(this.hwndParent, 0, info, true);
                this.setScrollWidth();
            }
            else if (scrollBecauseRight) {
                final SCROLLINFO info = new SCROLLINFO();
                info.cbSize = SCROLLINFO.sizeof;
                info.fMask = 4;
                final int wideRect = rect.right - rect.left;
                final int wideHeader = headerRect.right - headerRect.left;
                info.nPos = Math.max(0, wideHeader + headerRect.left - wideRect - 1);
                info.nPos = Math.min(rect.right - 1, info.nPos);
                OS.SetScrollInfo(this.hwndParent, 0, info, true);
                this.setScrollWidth();
            }
        }
    }
    
    public void showItem(final TreeItem item) {
        this.checkWidget();
        if (item == null) {
            this.error(4);
        }
        if (item.isDisposed()) {
            this.error(5);
        }
        this.showItem(item.handle);
    }
    
    public void showSelection() {
        this.checkWidget();
        long hItem = 0L;
        if ((this.style & 0x4) != 0x0) {
            hItem = OS.SendMessage(this.handle, 4362, 9L, 0L);
            if (hItem == 0L) {
                return;
            }
            final int state = (int)OS.SendMessage(this.handle, 4391, hItem, 2L);
            if ((state & 0x2) == 0x0) {
                return;
            }
        }
        else {
            final long oldProc = OS.GetWindowLongPtr(this.handle, -4);
            OS.SetWindowLongPtr(this.handle, -4, Tree.TreeProc);
            if ((this.style & 0x10000000) != 0x0) {
                final long hRoot = OS.SendMessage(this.handle, 4362, 0L, 0L);
                hItem = this.getNextSelection(hRoot);
            }
            else {
                for (int index = 0; index < this.items.length; ++index) {
                    final TreeItem item = this.items[index];
                    if (item != null) {
                        final int state2 = (int)OS.SendMessage(this.handle, 4391, item.handle, 2L);
                        if ((state2 & 0x2) != 0x0) {
                            hItem = item.handle;
                            break;
                        }
                    }
                }
            }
            OS.SetWindowLongPtr(this.handle, -4, oldProc);
        }
        if (hItem != 0L) {
            this.showItem(hItem);
        }
    }
    
    void sort() {
        this.checkWidget();
        if ((this.style & 0x10000000) != 0x0) {
            return;
        }
        this.sort(-65536L, false);
    }
    
    void sort(final long hParent, final boolean all) {
        int itemCount = (int)OS.SendMessage(this.handle, 4357, 0L, 0L);
        if (itemCount == 0 || itemCount == 1) {
            return;
        }
        final long n = 0L;
        this.hLastIndexOf = 0L;
        this.hFirstIndexOf = 0L;
        itemCount = -1;
        if (this.sortDirection == 128 || this.sortDirection == 0) {
            OS.SendMessage(this.handle, 4371, (long)(all ? 1 : 0), hParent);
        }
        else {
            final Callback compareCallback = new Callback((Object)this, "CompareFunc", 3);
            final long lpfnCompare = compareCallback.getAddress();
            final TVSORTCB psort = new TVSORTCB();
            psort.hParent = hParent;
            psort.lpfnCompare = lpfnCompare;
            psort.lParam = ((this.sortColumn == null) ? 0L : this.indexOf(this.sortColumn));
            OS.SendMessage(this.handle, 4373, (long)(all ? 1 : 0), psort);
            compareCallback.dispose();
        }
    }
    
    void subclass() {
        super.subclass();
        if (this.hwndHeader != 0L) {
            OS.SetWindowLongPtr(this.hwndHeader, -4, this.display.windowProc);
        }
    }
    
    RECT toolTipInset(final RECT rect) {
        final RECT insetRect = new RECT();
        OS.SetRect(insetRect, rect.left - 1, rect.top - 1, rect.right + 1, rect.bottom + 1);
        return insetRect;
    }
    
    RECT toolTipRect(final RECT rect) {
        final RECT toolRect = new RECT();
        OS.SetRect(toolRect, rect.left - 1, rect.top - 1, rect.right + 1, rect.bottom + 1);
        return toolRect;
    }
    
    String toolTipText(final NMTTDISPINFO hdr) {
        final long hwndToolTip = OS.SendMessage(this.handle, 4377, 0L, 0L);
        if (hwndToolTip == hdr.hwndFrom && this.toolTipText != null) {
            return "";
        }
        if (this.headerToolTipHandle == hdr.hwndFrom) {
            for (int i = 0; i < this.columnCount; ++i) {
                final TreeColumn column = this.columns[i];
                if (column.id == hdr.idFrom) {
                    return column.toolTipText;
                }
            }
            return super.toolTipText(hdr);
        }
        if (this.itemToolTipHandle == hdr.hwndFrom) {
            if (this.toolTipText != null) {
                return "";
            }
            final int pos = OS.GetMessagePos();
            final POINT pt = new POINT();
            OS.POINTSTOPOINT(pt, (long)pos);
            OS.ScreenToClient(this.handle, pt);
            final int[] index = { 0 };
            final TreeItem[] item = { null };
            final RECT[] cellRect = { null };
            final RECT[] itemRect = { null };
            if (this.findCell(pt.x, pt.y, item, index, cellRect, itemRect)) {
                String text = null;
                if (index[0] == 0) {
                    text = item[0].text;
                }
                else {
                    final String[] strings = item[0].strings;
                    if (strings != null) {
                        text = strings[index[0]];
                    }
                }
                if (this.isCustomToolTip()) {
                    text = " ";
                }
                if (text != null) {
                    return text;
                }
            }
        }
        return super.toolTipText(hdr);
    }
    
    long topHandle() {
        return (this.hwndParent != 0L) ? this.hwndParent : this.handle;
    }
    
    void updateFullSelection() {
        if ((this.style & 0x10000) != 0x0) {
            final int oldBits;
            int newBits = oldBits = OS.GetWindowLong(this.handle, -16);
            if ((newBits & 0x1000) != 0x0) {
                if ((!OS.IsWindowEnabled(this.handle) || this.findImageControl() != null) && !this.explorerTheme) {
                    newBits &= 0xFFFFEFFF;
                }
            }
            else if (OS.IsWindowEnabled(this.handle) && this.findImageControl() == null && !this.hooks(40) && !this.hooks(42)) {
                newBits |= 0x1000;
            }
            if (newBits != oldBits) {
                OS.SetWindowLong(this.handle, -16, newBits);
                OS.InvalidateRect(this.handle, (RECT)null, true);
            }
        }
    }
    
    void updateHeaderToolTips() {
        if (this.headerToolTipHandle == 0L) {
            return;
        }
        final RECT rect = new RECT();
        final TOOLINFO lpti = new TOOLINFO();
        lpti.cbSize = TOOLINFO.sizeof;
        lpti.uFlags = 16;
        lpti.hwnd = this.hwndHeader;
        lpti.lpszText = -1L;
        for (int i = 0; i < this.columnCount; ++i) {
            final TreeColumn column = this.columns[i];
            if (OS.SendMessage(this.hwndHeader, 4615, (long)i, rect) != 0L) {
                final TOOLINFO toolinfo = lpti;
                final TreeColumn treeColumn = column;
                final int id = this.display.nextToolTipId++;
                treeColumn.id = id;
                toolinfo.uId = id;
                lpti.left = rect.left;
                lpti.top = rect.top;
                lpti.right = rect.right;
                lpti.bottom = rect.bottom;
                OS.SendMessage(this.headerToolTipHandle, 1074, 0L, lpti);
            }
        }
    }
    
    void updateImageList() {
        if (this.imageList == null) {
            return;
        }
        if (this.hwndHeader == 0L) {
            return;
        }
        int i = 0;
        final int index = (int)OS.SendMessage(this.hwndHeader, 4623, 0L, 0L);
        while (i < this.items.length) {
            final TreeItem item = this.items[i];
            if (item != null) {
                Image image = null;
                if (index == 0) {
                    image = item.image;
                }
                else {
                    final Image[] images = item.images;
                    if (images != null) {
                        image = images[index];
                    }
                }
                if (image != null) {
                    break;
                }
            }
            ++i;
        }
        final long hImageList = (i == this.items.length) ? 0L : this.imageList.getHandle();
        final long hOldImageList = OS.SendMessage(this.handle, 4360, 0L, 0L);
        if (hImageList != hOldImageList) {
            OS.SendMessage(this.handle, 4361, 0L, hImageList);
        }
    }
    
    void updateMenuLocation(final Event event) {
        final Rectangle clientArea = this.getClientAreaInPixels();
        int x = clientArea.x;
        int y = clientArea.y;
        final TreeItem focusItem = this.getFocusItem();
        if (focusItem != null) {
            Rectangle bounds = focusItem.getBoundsInPixels(0);
            if (focusItem.text != null && focusItem.text.length() != 0) {
                bounds = focusItem.getBoundsInPixels();
            }
            x = Math.max(x, bounds.x + bounds.width / 2);
            x = Math.min(x, clientArea.x + clientArea.width);
            y = Math.max(y, bounds.y + bounds.height);
            y = Math.min(y, clientArea.y + clientArea.height);
        }
        final Point pt = this.toDisplayInPixels(x, y);
        event.setLocationInPixels(pt.x, pt.y);
    }
    
    void updateOrientation() {
        super.updateOrientation();
        RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        int width = rect.right - rect.left;
        int height = rect.bottom - rect.top;
        OS.SetWindowPos(this.handle, 0L, 0, 0, width - 1, height - 1, 6);
        OS.SetWindowPos(this.handle, 0L, 0, 0, width, height, 6);
        if (this.hwndParent != 0L) {
            int bits = OS.GetWindowLong(this.hwndParent, -20);
            if ((this.style & 0x4000000) != 0x0) {
                bits |= 0x400000;
            }
            else {
                bits &= 0xFFBFFFFF;
            }
            bits &= 0xFFFFDFFF;
            OS.SetWindowLong(this.hwndParent, -20, bits);
            rect = new RECT();
            OS.GetWindowRect(this.hwndParent, rect);
            width = rect.right - rect.left;
            height = rect.bottom - rect.top;
            OS.SetWindowPos(this.hwndParent, 0L, 0, 0, width - 1, height - 1, 6);
            OS.SetWindowPos(this.hwndParent, 0L, 0, 0, width, height, 6);
        }
        if (this.hwndHeader != 0L) {
            int bits = OS.GetWindowLong(this.hwndHeader, -20);
            if ((this.style & 0x4000000) != 0x0) {
                bits |= 0x400000;
            }
            else {
                bits &= 0xFFBFFFFF;
            }
            OS.SetWindowLong(this.hwndHeader, -20, bits);
            OS.InvalidateRect(this.hwndHeader, (RECT)null, true);
        }
        if ((this.style & 0x20) != 0x0) {
            this.setCheckboxImageList();
        }
        if (this.imageList != null) {
            final Point size = this.imageList.getImageSize();
            this.display.releaseImageList(this.imageList);
            this.imageList = this.display.getImageList(this.style & 0x4000000, size.x, size.y);
            for (final TreeItem item : this.items) {
                if (item != null) {
                    final Image image = item.image;
                    if (image != null) {
                        final int index = this.imageList.indexOf(image);
                        if (index == -1) {
                            this.imageList.add(image);
                        }
                    }
                }
            }
            final long hImageList = this.imageList.getHandle();
            OS.SendMessage(this.handle, 4361, 0L, hImageList);
        }
        if (this.hwndHeader != 0L && this.headerImageList != null) {
            final Point size = this.headerImageList.getImageSize();
            this.display.releaseImageList(this.headerImageList);
            this.headerImageList = this.display.getImageList(this.style & 0x4000000, size.x, size.y);
            if (this.columns != null) {
                for (int i = 0; i < this.columns.length; ++i) {
                    final TreeColumn column = this.columns[i];
                    if (column != null) {
                        final Image image2 = column.image;
                        if (image2 != null) {
                            final HDITEM hdItem = new HDITEM();
                            hdItem.mask = 4;
                            OS.SendMessage(this.hwndHeader, 4619, (long)i, hdItem);
                            if ((hdItem.fmt & 0x800) != 0x0) {
                                int index2 = this.headerImageList.indexOf(image2);
                                if (index2 == -1) {
                                    index2 = this.headerImageList.add(image2);
                                }
                                hdItem.mask = 32;
                                hdItem.iImage = index2;
                                OS.SendMessage(this.hwndHeader, 4620, (long)i, hdItem);
                            }
                        }
                    }
                }
            }
            final long hImageListHeader = this.headerImageList.getHandle();
            OS.SendMessage(this.hwndHeader, 4616, 0L, hImageListHeader);
        }
    }
    
    void updateScrollBar() {
        if (this.hwndParent != 0L && (this.columnCount != 0 || this.scrollWidth != 0)) {
            final SCROLLINFO info = new SCROLLINFO();
            info.cbSize = SCROLLINFO.sizeof;
            info.fMask = 23;
            final int itemCount = (int)OS.SendMessage(this.handle, 4357, 0L, 0L);
            if (itemCount == 0) {
                OS.GetScrollInfo(this.hwndParent, 1, info);
                info.nPage = info.nMax + 1;
                OS.SetScrollInfo(this.hwndParent, 1, info, true);
            }
            else {
                OS.GetScrollInfo(this.handle, 1, info);
                if (info.nPage == 0) {
                    final SCROLLBARINFO psbi = new SCROLLBARINFO();
                    psbi.cbSize = SCROLLBARINFO.sizeof;
                    OS.GetScrollBarInfo(this.handle, -5, psbi);
                    if ((psbi.rgstate[0] & 0x8000) != 0x0) {
                        info.nPage = info.nMax + 1;
                    }
                }
                OS.SetScrollInfo(this.hwndParent, 1, info, true);
            }
        }
    }
    
    void unsubclass() {
        super.unsubclass();
        if (this.hwndHeader != 0L) {
            OS.SetWindowLongPtr(this.hwndHeader, -4, Tree.HeaderProc);
        }
    }
    
    int widgetStyle() {
        int bits = super.widgetStyle() | 0x20 | 0x4 | 0x1 | 0x4000;
        if (OS.IsAppThemed()) {
            bits |= 0x200;
            if ((this.style & 0x10000) != 0x0) {
                bits |= 0x1000;
            }
        }
        else if ((this.style & 0x10000) != 0x0) {
            bits |= 0x1000;
        }
        else {
            bits |= 0x2;
        }
        if ((this.style & 0x300) == 0x0) {
            bits &= 0xFFCFFFFF;
            bits |= 0x2000;
        }
        else if ((this.style & 0x100) == 0x0) {
            bits &= 0xFFEFFFFF;
            bits |= 0x8000;
        }
        return bits | 0x10;
    }
    
    TCHAR windowClass() {
        return Tree.TreeClass;
    }
    
    long windowProc() {
        return Tree.TreeProc;
    }
    
    long windowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.hwndHeader != 0L && hwnd == this.hwndHeader) {
            Label_0321: {
                switch (msg) {
                    case 123: {
                        final LRESULT result = this.wmContextMenu(hwnd, wParam, lParam);
                        if (result != null) {
                            return result.value;
                        }
                        break;
                    }
                    case 675: {
                        this.updateHeaderToolTips();
                        this.updateHeaderToolTips();
                        break;
                    }
                    case 78: {
                        final NMHDR hdr = new NMHDR();
                        OS.MoveMemory(hdr, lParam, NMHDR.sizeof);
                        switch (hdr.code) {
                            case -530:
                            case -522:
                            case -521: {
                                return OS.SendMessage(this.handle, msg, wParam, lParam);
                            }
                            default: {
                                break Label_0321;
                            }
                        }
                        break;
                    }
                    case 32: {
                        if (wParam != hwnd) {
                            break;
                        }
                        final int hitTest = (short)OS.LOWORD(lParam);
                        if (hitTest != 1) {
                            break;
                        }
                        final HDHITTESTINFO pinfo = new HDHITTESTINFO();
                        final int pos = OS.GetMessagePos();
                        final POINT pt = new POINT();
                        OS.POINTSTOPOINT(pt, (long)pos);
                        OS.ScreenToClient(hwnd, pt);
                        pinfo.x = pt.x;
                        pinfo.y = pt.y;
                        final int index = (int)OS.SendMessage(this.hwndHeader, 4614, 0L, pinfo);
                        if (0 <= index && index < this.columnCount && !this.columns[index].resizable && (pinfo.flags & 0xC) != 0x0) {
                            OS.SetCursor(OS.LoadCursor(0L, 32512L));
                            return 1L;
                        }
                        break;
                    }
                }
            }
            return this.callWindowProc(hwnd, msg, wParam, lParam);
        }
        if (this.hwndParent != 0L && hwnd == this.hwndParent) {
            switch (msg) {
                case 3: {
                    this.sendEvent(10);
                    return 0L;
                }
                case 5: {
                    this.setScrollWidth();
                    if (this.ignoreResize) {
                        return 0L;
                    }
                    this.setResizeChildren(false);
                    final long code = this.callWindowProc(hwnd, 5, wParam, lParam);
                    this.sendEvent(11);
                    if (this.isDisposed()) {
                        return 0L;
                    }
                    if (this.layout != null) {
                        this.markLayout(false, false);
                        this.updateLayout(false, false);
                    }
                    this.setResizeChildren(true);
                    this.updateScrollBar();
                    return code;
                }
                case 133: {
                    final LRESULT result = this.wmNCPaint(hwnd, wParam, lParam);
                    if (result != null) {
                        return result.value;
                    }
                    break;
                }
                case 791: {
                    final LRESULT result = this.wmPrint(hwnd, wParam, lParam);
                    if (result != null) {
                        return result.value;
                    }
                    break;
                }
                case 21:
                case 78:
                case 273: {
                    return OS.SendMessage(this.handle, msg, wParam, lParam);
                }
                case 276: {
                    if (this.horizontalBar != null && (lParam == 0L || lParam == this.hwndParent)) {
                        this.wmScroll(this.horizontalBar, true, this.hwndParent, 276, wParam, lParam);
                    }
                    this.setScrollWidth();
                    break;
                }
                case 277: {
                    final SCROLLINFO info = new SCROLLINFO();
                    info.cbSize = SCROLLINFO.sizeof;
                    info.fMask = 23;
                    OS.GetScrollInfo(this.hwndParent, 1, info);
                    if (OS.LOWORD(wParam) == 5) {
                        info.nPos = info.nTrackPos;
                    }
                    OS.SetScrollInfo(this.handle, 1, info, true);
                    final long code2 = OS.SendMessage(this.handle, 277, wParam, lParam);
                    OS.GetScrollInfo(this.handle, 1, info);
                    OS.SetScrollInfo(this.hwndParent, 1, info, true);
                    return code2;
                }
            }
            return this.callWindowProc(hwnd, msg, wParam, lParam);
        }
        if (msg != Display.DI_GETDRAGIMAGE || ((this.style & 0x2) == 0x0 && !this.hooks(40) && !this.hooks(42))) {
            return super.windowProc(hwnd, msg, wParam, lParam);
        }
        final long hItem = OS.SendMessage(this.handle, 4362, 5L, 0L);
        final TreeItem[] items = new TreeItem[10];
        final TVITEM tvItem = new TVITEM();
        tvItem.mask = 28;
        final int count = this.getSelection(hItem, tvItem, items, 0, 10, false, true);
        if (count == 0) {
            return 0L;
        }
        final POINT mousePos = new POINT();
        OS.POINTSTOPOINT(mousePos, (long)OS.GetMessagePos());
        OS.MapWindowPoints(0L, this.handle, mousePos, 1);
        final RECT clientRect = new RECT();
        OS.GetClientRect(this.handle, clientRect);
        final RECT rect = items[0].getBounds(0, true, true, false);
        if ((this.style & 0x10000) != 0x0) {
            final int width = 301;
            rect.left = Math.max(clientRect.left, mousePos.x - 150);
            if (clientRect.right > rect.left + 301) {
                rect.right = rect.left + 301;
            }
            else {
                rect.right = clientRect.right;
                rect.left = Math.max(clientRect.left, rect.right - 301);
            }
        }
        else {
            rect.left = Math.max(rect.left, clientRect.left);
            rect.right = Math.min(rect.right, clientRect.right);
        }
        final long hRgn = OS.CreateRectRgn(rect.left, rect.top, rect.right, rect.bottom);
        for (int i = 1; i < count; ++i) {
            if (rect.bottom - rect.top > 301) {
                break;
            }
            if (rect.bottom > clientRect.bottom) {
                break;
            }
            final RECT itemRect = items[i].getBounds(0, true, true, false);
            if ((this.style & 0x10000) != 0x0) {
                itemRect.left = rect.left;
                itemRect.right = rect.right;
            }
            else {
                itemRect.left = Math.max(itemRect.left, clientRect.left);
                itemRect.right = Math.min(itemRect.right, clientRect.right);
            }
            final long rectRgn = OS.CreateRectRgn(itemRect.left, itemRect.top, itemRect.right, itemRect.bottom);
            OS.CombineRgn(hRgn, hRgn, rectRgn, 2);
            OS.DeleteObject(rectRgn);
            rect.bottom = itemRect.bottom;
        }
        OS.GetRgnBox(hRgn, rect);
        final long hdc = OS.GetDC(this.handle);
        final long memHdc = OS.CreateCompatibleDC(hdc);
        final BITMAPINFOHEADER bmiHeader = new BITMAPINFOHEADER();
        bmiHeader.biSize = BITMAPINFOHEADER.sizeof;
        bmiHeader.biWidth = rect.right - rect.left;
        bmiHeader.biHeight = -(rect.bottom - rect.top);
        bmiHeader.biPlanes = 1;
        bmiHeader.biBitCount = 32;
        bmiHeader.biCompression = 0;
        final byte[] bmi = new byte[BITMAPINFOHEADER.sizeof];
        OS.MoveMemory(bmi, bmiHeader, BITMAPINFOHEADER.sizeof);
        final long[] pBits = { 0L };
        final long memDib = OS.CreateDIBSection(0L, bmi, 0, pBits, 0L, 0);
        if (memDib == 0L) {
            this.error(2);
        }
        final long oldMemBitmap = OS.SelectObject(memHdc, memDib);
        final int colorKey = 253;
        final POINT pt2 = new POINT();
        OS.SetWindowOrgEx(memHdc, rect.left, rect.top, pt2);
        OS.FillRect(memHdc, rect, this.findBrush(253L, 0));
        OS.OffsetRgn(hRgn, -rect.left, -rect.top);
        OS.SelectClipRgn(memHdc, hRgn);
        OS.PrintWindow(this.handle, memHdc, 0);
        OS.SetWindowOrgEx(memHdc, pt2.x, pt2.y, (POINT)null);
        OS.SelectObject(memHdc, oldMemBitmap);
        OS.DeleteDC(memHdc);
        OS.ReleaseDC(0L, hdc);
        OS.DeleteObject(hRgn);
        final SHDRAGIMAGE shdi = new SHDRAGIMAGE();
        shdi.hbmpDragImage = memDib;
        shdi.crColorKey = 253;
        shdi.sizeDragImage.cx = bmiHeader.biWidth;
        shdi.sizeDragImage.cy = -bmiHeader.biHeight;
        shdi.ptOffset.x = mousePos.x - rect.left;
        shdi.ptOffset.y = mousePos.y - rect.top;
        if ((this.style & 0x8000000) != 0x0) {
            shdi.ptOffset.x = shdi.sizeDragImage.cx - shdi.ptOffset.x;
        }
        OS.MoveMemory(lParam, shdi, SHDRAGIMAGE.sizeof);
        return 1L;
    }
    
    LRESULT WM_CHAR(final long wParam, final long lParam) {
        final LRESULT result = super.WM_CHAR(wParam, lParam);
        if (result != null) {
            return result;
        }
        switch ((int)wParam) {
            case 32: {
                final long hItem = OS.SendMessage(this.handle, 4362, 9L, 0L);
                if (hItem != 0L) {
                    this.hAnchor = hItem;
                    OS.SendMessage(this.handle, 4372, 0L, hItem);
                    final TVITEM tvItem = new TVITEM();
                    tvItem.mask = 28;
                    tvItem.hItem = hItem;
                    if ((this.style & 0x20) != 0x0) {
                        tvItem.stateMask = 61440;
                        OS.SendMessage(this.handle, 4414, 0L, tvItem);
                        int state = tvItem.state >> 12;
                        if ((state & 0x1) != 0x0) {
                            ++state;
                        }
                        else {
                            --state;
                        }
                        tvItem.state = state << 12;
                        OS.SendMessage(this.handle, 4415, 0L, tvItem);
                        final long id = OS.SendMessage(this.handle, 4395, hItem, 0L);
                        OS.NotifyWinEvent(32773, this.handle, -4, (int)id);
                    }
                    tvItem.stateMask = 2;
                    OS.SendMessage(this.handle, 4414, 0L, tvItem);
                    if ((this.style & 0x2) != 0x0 && OS.GetKeyState(17) < 0) {
                        if ((tvItem.state & 0x2) != 0x0) {
                            final TVITEM tvitem4;
                            final TVITEM tvitem = tvitem4 = tvItem;
                            tvitem4.state &= 0xFFFFFFFD;
                        }
                        else {
                            final TVITEM tvitem5;
                            final TVITEM tvitem2 = tvitem5 = tvItem;
                            tvitem5.state |= 0x2;
                        }
                    }
                    else {
                        final TVITEM tvitem6;
                        final TVITEM tvitem3 = tvitem6 = tvItem;
                        tvitem6.state |= 0x2;
                    }
                    OS.SendMessage(this.handle, 4415, 0L, tvItem);
                    final TreeItem item = this._getItem(hItem, (int)tvItem.lParam);
                    Event event = new Event();
                    event.item = (Widget)item;
                    this.sendSelectionEvent(13, event, false);
                    if ((this.style & 0x20) != 0x0) {
                        event = new Event();
                        event.item = (Widget)item;
                        event.detail = 32;
                        this.sendSelectionEvent(13, event, false);
                    }
                }
                return LRESULT.ZERO;
            }
            case 13: {
                final Event event2 = new Event();
                final long hItem2 = OS.SendMessage(this.handle, 4362, 9L, 0L);
                if (hItem2 != 0L) {
                    event2.item = (Widget)this._getItem(hItem2);
                }
                this.sendSelectionEvent(14, event2, false);
                return LRESULT.ZERO;
            }
            case 27: {
                return LRESULT.ZERO;
            }
            default: {
                return result;
            }
        }
    }
    
    LRESULT WM_ERASEBKGND(final long wParam, final long lParam) {
        final LRESULT result = super.WM_ERASEBKGND(wParam, lParam);
        if ((this.style & 0x20000000) != 0x0) {
            return LRESULT.ONE;
        }
        if (this.findImageControl() != null) {
            return LRESULT.ONE;
        }
        return result;
    }
    
    LRESULT WM_GETOBJECT(final long wParam, final long lParam) {
        if (((this.style & 0x20) != 0x0 || this.hwndParent != 0L) && this.accessible == null) {
            this.accessible = this.new_Accessible((Control)this);
        }
        return super.WM_GETOBJECT(wParam, lParam);
    }
    
    LRESULT WM_HSCROLL(final long wParam, final long lParam) {
        boolean fixScroll = false;
        if ((this.style & 0x20000000) != 0x0) {
            fixScroll = ((this.style & 0x10000000) != 0x0 || this.hooks(40) || this.hooks(42));
        }
        if (fixScroll) {
            this.style &= 0xDFFFFFFF;
            if (this.explorerTheme) {
                OS.SendMessage(this.handle, 4396, 4L, 0L);
            }
        }
        final LRESULT result = super.WM_HSCROLL(wParam, lParam);
        if (fixScroll) {
            this.style |= 0x20000000;
            if (this.explorerTheme) {
                OS.SendMessage(this.handle, 4396, 4L, 4L);
            }
        }
        if (result != null) {
            return result;
        }
        return result;
    }
    
    LRESULT WM_KEYDOWN(final long wParam, final long lParam) {
        final LRESULT result = super.WM_KEYDOWN(wParam, lParam);
        if (result != null) {
            return result;
        }
        switch ((int)wParam) {
            case 37:
            case 39: {
                final boolean isRTL = (this.style & 0x4000000) != 0x0;
                if (isRTL != this.createdAsRTL) {
                    final long code = this.callWindowProc(this.handle, 256, (wParam == 39L) ? 37L : 39L, lParam);
                    return new LRESULT(code);
                }
                break;
            }
            case 32: {
                return LRESULT.ZERO;
            }
            case 107: {
                if (OS.GetKeyState(17) < 0 && this.hwndHeader != 0L) {
                    final TreeColumn[] newColumns = new TreeColumn[this.columnCount];
                    System.arraycopy(this.columns, 0, newColumns, 0, this.columnCount);
                    for (int i = 0; i < this.columnCount; ++i) {
                        final TreeColumn column = newColumns[i];
                        if (!column.isDisposed() && column.getResizable()) {
                            column.pack();
                        }
                    }
                    break;
                }
                break;
            }
            case 33:
            case 34:
            case 35:
            case 36:
            case 38:
            case 40: {
                OS.SendMessage(this.handle, 295, 3L, 0L);
                if (this.itemToolTipHandle != 0L) {
                    OS.ShowWindow(this.itemToolTipHandle, 0);
                }
                if ((this.style & 0x4) != 0x0) {
                    break;
                }
                if (OS.GetKeyState(16) < 0) {
                    final long hItem = OS.SendMessage(this.handle, 4362, 9L, 0L);
                    if (hItem != 0L) {
                        if (this.hAnchor == 0L) {
                            this.hAnchor = hItem;
                        }
                        final boolean b = true;
                        this.ignoreDeselect = true;
                        this.ignoreSelect = true;
                        final long code2 = this.callWindowProc(this.handle, 256, wParam, lParam);
                        final boolean b2 = false;
                        this.ignoreDeselect = false;
                        this.ignoreSelect = false;
                        final long hNewItem = OS.SendMessage(this.handle, 4362, 9L, 0L);
                        final TVITEM tvItem = new TVITEM();
                        tvItem.mask = 24;
                        tvItem.stateMask = 2;
                        long hDeselectItem = hItem;
                        final RECT rect1 = new RECT();
                        if (!OS.TreeView_GetItemRect(this.handle, this.hAnchor, rect1, false)) {
                            this.hAnchor = hItem;
                            OS.TreeView_GetItemRect(this.handle, this.hAnchor, rect1, false);
                        }
                        final RECT rect2 = new RECT();
                        OS.TreeView_GetItemRect(this.handle, hDeselectItem, rect2, false);
                        for (int flags = (rect1.top < rect2.top) ? 7 : 6; hDeselectItem != this.hAnchor; hDeselectItem = OS.SendMessage(this.handle, 4362, (long)flags, hDeselectItem)) {
                            tvItem.hItem = hDeselectItem;
                            OS.SendMessage(this.handle, 4415, 0L, tvItem);
                        }
                        long hSelectItem = this.hAnchor;
                        OS.TreeView_GetItemRect(this.handle, hNewItem, rect1, false);
                        OS.TreeView_GetItemRect(this.handle, hSelectItem, rect2, false);
                        tvItem.state = 2;
                        for (int flags2 = (rect1.top < rect2.top) ? 7 : 6; hSelectItem != hNewItem; hSelectItem = OS.SendMessage(this.handle, 4362, (long)flags2, hSelectItem)) {
                            tvItem.hItem = hSelectItem;
                            OS.SendMessage(this.handle, 4415, 0L, tvItem);
                        }
                        tvItem.hItem = hNewItem;
                        OS.SendMessage(this.handle, 4415, 0L, tvItem);
                        tvItem.mask = 20;
                        tvItem.hItem = hNewItem;
                        OS.SendMessage(this.handle, 4414, 0L, tvItem);
                        final Event event = new Event();
                        event.item = (Widget)this._getItem(hNewItem, (int)tvItem.lParam);
                        this.sendSelectionEvent(13, event, false);
                        return new LRESULT(code2);
                    }
                }
                if (OS.GetKeyState(17) < 0) {
                    final long hItem = OS.SendMessage(this.handle, 4362, 9L, 0L);
                    if (hItem != 0L) {
                        final TVITEM tvItem2 = new TVITEM();
                        tvItem2.mask = 24;
                        tvItem2.stateMask = 2;
                        tvItem2.hItem = hItem;
                        OS.SendMessage(this.handle, 4414, 0L, tvItem2);
                        final boolean oldSelected = (tvItem2.state & 0x2) != 0x0;
                        long hNewItem2 = 0L;
                        switch ((int)wParam) {
                            case 38: {
                                hNewItem2 = OS.SendMessage(this.handle, 4362, 7L, hItem);
                                break;
                            }
                            case 40: {
                                hNewItem2 = OS.SendMessage(this.handle, 4362, 6L, hItem);
                                break;
                            }
                            case 36: {
                                hNewItem2 = OS.SendMessage(this.handle, 4362, 0L, 0L);
                                break;
                            }
                            case 33: {
                                hNewItem2 = OS.SendMessage(this.handle, 4362, 5L, 0L);
                                if (hNewItem2 == hItem) {
                                    OS.SendMessage(this.handle, 277, 2L, 0L);
                                    hNewItem2 = OS.SendMessage(this.handle, 4362, 5L, 0L);
                                    break;
                                }
                                break;
                            }
                            case 34: {
                                final RECT rect3 = new RECT();
                                final RECT clientRect = new RECT();
                                OS.GetClientRect(this.handle, clientRect);
                                hNewItem2 = OS.SendMessage(this.handle, 4362, 5L, 0L);
                                do {
                                    final long hVisible = OS.SendMessage(this.handle, 4362, 6L, hNewItem2);
                                    if (hVisible == 0L) {
                                        break;
                                    }
                                    if (!OS.TreeView_GetItemRect(this.handle, hVisible, rect3, false)) {
                                        break;
                                    }
                                    if (rect3.bottom > clientRect.bottom) {
                                        break;
                                    }
                                    if ((hNewItem2 = hVisible) != hItem) {
                                        continue;
                                    }
                                    OS.SendMessage(this.handle, 277, 3L, 0L);
                                } while (hNewItem2 != 0L);
                                break;
                            }
                            case 35: {
                                hNewItem2 = OS.SendMessage(this.handle, 4362, 10L, 0L);
                                break;
                            }
                        }
                        if (hNewItem2 != 0L) {
                            OS.SendMessage(this.handle, 4372, 0L, hNewItem2);
                            tvItem2.hItem = hNewItem2;
                            OS.SendMessage(this.handle, 4414, 0L, tvItem2);
                            final boolean newSelected = (tvItem2.state & 0x2) != 0x0;
                            final boolean redraw = !newSelected && this.getDrawing() && OS.IsWindowVisible(this.handle);
                            if (redraw) {
                                OS.UpdateWindow(this.handle);
                                OS.DefWindowProc(this.handle, 11, 0L, 0L);
                            }
                            this.hSelect = hNewItem2;
                            this.ignoreSelect = true;
                            OS.SendMessage(this.handle, 4363, 9L, hNewItem2);
                            this.ignoreSelect = false;
                            this.hSelect = 0L;
                            if (oldSelected) {
                                tvItem2.state = 2;
                                tvItem2.hItem = hItem;
                                OS.SendMessage(this.handle, 4415, 0L, tvItem2);
                            }
                            if (!newSelected) {
                                tvItem2.state = 0;
                                tvItem2.hItem = hNewItem2;
                                OS.SendMessage(this.handle, 4415, 0L, tvItem2);
                            }
                            if (redraw) {
                                final RECT rect4 = new RECT();
                                final RECT rect5 = new RECT();
                                OS.TreeView_GetItemRect(this.handle, hItem, rect4, false);
                                OS.TreeView_GetItemRect(this.handle, hNewItem2, rect5, false);
                                OS.DefWindowProc(this.handle, 11, 1L, 0L);
                                OS.InvalidateRect(this.handle, rect4, true);
                                OS.InvalidateRect(this.handle, rect5, true);
                                OS.UpdateWindow(this.handle);
                            }
                            return LRESULT.ZERO;
                        }
                    }
                }
                final long code3 = this.callWindowProc(this.handle, 256, wParam, lParam);
                this.hAnchor = OS.SendMessage(this.handle, 4362, 9L, 0L);
                return new LRESULT(code3);
            }
        }
        return result;
    }
    
    LRESULT WM_KILLFOCUS(final long wParam, final long lParam) {
        boolean redraw = (this.style & 0x2) != 0x0;
        if (!redraw && this.imageList != null) {
            final int bits = OS.GetWindowLong(this.handle, -16);
            if ((bits & 0x1000) == 0x0) {
                redraw = true;
            }
        }
        if (redraw) {
            this.redrawSelection();
        }
        return super.WM_KILLFOCUS(wParam, lParam);
    }
    
    LRESULT WM_LBUTTONDBLCLK(final long wParam, final long lParam) {
        final TVHITTESTINFO lpht = new TVHITTESTINFO();
        lpht.x = OS.GET_X_LPARAM(lParam);
        lpht.y = OS.GET_Y_LPARAM(lParam);
        OS.SendMessage(this.handle, 4369, 0L, lpht);
        if (lpht.hItem != 0L && (this.style & 0x20) != 0x0 && (lpht.flags & 0x40) != 0x0) {
            final Display display = this.display;
            display.captureChanged = false;
            this.sendMouseEvent(3, 1, this.handle, lParam);
            if (!this.sendMouseEvent(8, 1, this.handle, lParam)) {
                if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                    OS.SetCapture(this.handle);
                }
                return LRESULT.ZERO;
            }
            if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                OS.SetCapture(this.handle);
            }
            OS.SetFocus(this.handle);
            final TVITEM tvItem = new TVITEM();
            tvItem.hItem = lpht.hItem;
            tvItem.mask = 28;
            tvItem.stateMask = 61440;
            OS.SendMessage(this.handle, 4414, 0L, tvItem);
            int state = tvItem.state >> 12;
            if ((state & 0x1) != 0x0) {
                ++state;
            }
            else {
                --state;
            }
            tvItem.state = state << 12;
            OS.SendMessage(this.handle, 4415, 0L, tvItem);
            final long id = OS.SendMessage(this.handle, 4395, tvItem.hItem, 0L);
            OS.NotifyWinEvent(32773, this.handle, -4, (int)id);
            final Event event = new Event();
            event.item = (Widget)this._getItem(tvItem.hItem, (int)tvItem.lParam);
            event.detail = 32;
            this.sendSelectionEvent(13, event, false);
            return LRESULT.ZERO;
        }
        else {
            final LRESULT result = super.WM_LBUTTONDBLCLK(wParam, lParam);
            if (result == LRESULT.ZERO) {
                return result;
            }
            if (lpht.hItem != 0L) {
                int flags = 70;
                if ((this.style & 0x10000) != 0x0) {
                    flags |= 0x28;
                }
                else if (this.hooks(41)) {
                    final TVHITTESTINFO tvhittestinfo3;
                    final TVHITTESTINFO tvhittestinfo = tvhittestinfo3 = lpht;
                    tvhittestinfo3.flags &= 0xFFFFFFF9;
                    if (this.hitTestSelection(lpht.hItem, lpht.x, lpht.y)) {
                        final TVHITTESTINFO tvhittestinfo4;
                        final TVHITTESTINFO tvhittestinfo2 = tvhittestinfo4 = lpht;
                        tvhittestinfo4.flags |= 0x6;
                    }
                }
                if ((lpht.flags & flags) != 0x0) {
                    final Event event2 = new Event();
                    event2.item = (Widget)this._getItem(lpht.hItem);
                    this.sendSelectionEvent(14, event2, false);
                }
            }
            return result;
        }
    }
    
    LRESULT WM_LBUTTONDOWN(final long wParam, final long lParam) {
        final TVHITTESTINFO lpht = new TVHITTESTINFO();
        lpht.x = OS.GET_X_LPARAM(lParam);
        lpht.y = OS.GET_Y_LPARAM(lParam);
        OS.SendMessage(this.handle, 4369, 0L, lpht);
        if (lpht.hItem == 0L || (lpht.flags & 0x10) != 0x0) {
            final Display display = this.display;
            display.captureChanged = false;
            if (!this.sendMouseEvent(3, 1, this.handle, lParam)) {
                if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                    OS.SetCapture(this.handle);
                }
                return LRESULT.ZERO;
            }
            boolean fixSelection = false;
            boolean deselected = false;
            final long hOldSelection = OS.SendMessage(this.handle, 4362, 9L, 0L);
            if (lpht.hItem != 0L && (this.style & 0x2) != 0x0 && hOldSelection != 0L) {
                final TVITEM tvItem = new TVITEM();
                tvItem.mask = 24;
                tvItem.hItem = lpht.hItem;
                OS.SendMessage(this.handle, 4414, 0L, tvItem);
                if ((tvItem.state & 0x20) != 0x0) {
                    fixSelection = true;
                    tvItem.stateMask = 2;
                    long hNext = OS.SendMessage(this.handle, 4362, 6L, lpht.hItem);
                    while (hNext != 0L) {
                        if (hNext == this.hAnchor) {
                            this.hAnchor = 0L;
                        }
                        tvItem.hItem = hNext;
                        OS.SendMessage(this.handle, 4414, 0L, tvItem);
                        if ((tvItem.state & 0x2) != 0x0) {
                            deselected = true;
                        }
                        tvItem.state = 0;
                        OS.SendMessage(this.handle, 4415, 0L, tvItem);
                        long hItem;
                        for (hItem = (hNext = OS.SendMessage(this.handle, 4362, 6L, hNext)); hItem != 0L && hItem != lpht.hItem; hItem = OS.SendMessage(this.handle, 4362, 3L, hItem)) {}
                        if (hItem == 0L) {
                            break;
                        }
                    }
                }
            }
            final boolean b = false;
            this.gestureCompleted = false;
            this.dragStarted = false;
            if (fixSelection) {
                this.hSelect = lpht.hItem;
                final boolean ignoreDeselect = true;
                this.lockSelection = true;
                this.ignoreSelect = true;
                this.ignoreDeselect = true;
            }
            final long code = this.callWindowProc(this.handle, 513, wParam, lParam);
            if (OS.GetFocus() != this.handle) {
                OS.SetFocus(this.handle);
            }
            if (fixSelection) {
                this.hSelect = 0L;
                final boolean ignoreDeselect2 = false;
                this.lockSelection = false;
                this.ignoreSelect = false;
                this.ignoreDeselect = false;
            }
            final long hNewSelection = OS.SendMessage(this.handle, 4362, 9L, 0L);
            if (hOldSelection != hNewSelection) {
                this.hAnchor = hNewSelection;
            }
            if (this.dragStarted && !display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                OS.SetCapture(this.handle);
            }
            if ((lpht.flags & 0x10) != 0x0) {
                final int bits = OS.GetWindowLong(this.handle, -16);
                if ((bits & 0x1000) == 0x0 && OS.SendMessage(this.handle, 4360, 0L, 0L) == 0L) {
                    final long hItem2 = OS.SendMessage(this.handle, 4362, 9L, 0L);
                    if (hItem2 != 0L) {
                        final RECT rect = new RECT();
                        if (OS.TreeView_GetItemRect(this.handle, hItem2, rect, false)) {
                            OS.InvalidateRect(this.handle, rect, true);
                        }
                    }
                }
            }
            if (deselected) {
                final Event event = new Event();
                event.item = (Widget)this._getItem(lpht.hItem);
                this.sendSelectionEvent(13, event, false);
            }
            return new LRESULT(code);
        }
        else if ((this.style & 0x20) != 0x0 && (lpht.flags & 0x40) != 0x0) {
            final Display display = this.display;
            display.captureChanged = false;
            if (!this.sendMouseEvent(3, 1, this.handle, lParam)) {
                if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                    OS.SetCapture(this.handle);
                }
                return LRESULT.ZERO;
            }
            if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                OS.SetCapture(this.handle);
            }
            OS.SetFocus(this.handle);
            final TVITEM tvItem2 = new TVITEM();
            tvItem2.hItem = lpht.hItem;
            tvItem2.mask = 28;
            tvItem2.stateMask = 61440;
            OS.SendMessage(this.handle, 4414, 0L, tvItem2);
            int state = tvItem2.state >> 12;
            if ((state & 0x1) != 0x0) {
                ++state;
            }
            else {
                --state;
            }
            tvItem2.state = state << 12;
            OS.SendMessage(this.handle, 4415, 0L, tvItem2);
            final long id = OS.SendMessage(this.handle, 4395, tvItem2.hItem, 0L);
            OS.NotifyWinEvent(32773, this.handle, -4, (int)id);
            final Event event2 = new Event();
            event2.item = (Widget)this._getItem(tvItem2.hItem, (int)tvItem2.lParam);
            event2.detail = 32;
            this.sendSelectionEvent(13, event2, false);
            return LRESULT.ZERO;
        }
        else {
            boolean selected = false;
            boolean fakeSelection = false;
            if (lpht.hItem != 0L) {
                if ((this.style & 0x10000) != 0x0) {
                    final int bits2 = OS.GetWindowLong(this.handle, -16);
                    if ((bits2 & 0x1000) == 0x0) {
                        fakeSelection = true;
                    }
                }
                else if (this.hooks(41)) {
                    selected = this.hitTestSelection(lpht.hItem, lpht.x, lpht.y);
                    if (selected && (lpht.flags & 0x46) == 0x0) {
                        fakeSelection = true;
                    }
                }
            }
            if (!selected && (this.style & 0x10000) == 0x0 && (lpht.flags & 0x46) == 0x0) {
                final Display display2 = this.display;
                display2.captureChanged = false;
                if (!this.sendMouseEvent(3, 1, this.handle, lParam)) {
                    if (!display2.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                        OS.SetCapture(this.handle);
                    }
                    return LRESULT.ZERO;
                }
                final long code2 = this.callWindowProc(this.handle, 513, wParam, lParam);
                if (OS.GetFocus() != this.handle) {
                    OS.SetFocus(this.handle);
                }
                if (!display2.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                    OS.SetCapture(this.handle);
                }
                return new LRESULT(code2);
            }
            else {
                final TVITEM tvItem3 = new TVITEM();
                tvItem3.mask = 24;
                tvItem3.stateMask = 2;
                boolean hittestSelected = false;
                if ((this.style & 0x2) != 0x0) {
                    tvItem3.hItem = lpht.hItem;
                    OS.SendMessage(this.handle, 4414, 0L, tvItem3);
                    hittestSelected = ((tvItem3.state & 0x2) != 0x0);
                }
                final long hOldItem = OS.SendMessage(this.handle, 4362, 9L, 0L);
                if ((this.style & 0x2) != 0x0) {
                    tvItem3.hItem = hOldItem;
                    OS.SendMessage(this.handle, 4414, 0L, tvItem3);
                    if (hittestSelected || (wParam & 0x8L) != 0x0L) {
                        final int uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
                        if ((uiState & 0x1) != 0x0) {
                            OS.SendMessage(this.handle, 295, 3L, 0L);
                        }
                        OS.UpdateWindow(this.handle);
                        OS.DefWindowProc(this.handle, 11, 0L, 0L);
                    }
                    else {
                        this.deselectAll();
                    }
                }
                final Display display3 = this.display;
                display3.captureChanged = false;
                if (!this.sendMouseEvent(3, 1, this.handle, lParam)) {
                    if (!display3.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                        OS.SetCapture(this.handle);
                    }
                    return LRESULT.ZERO;
                }
                this.hSelect = lpht.hItem;
                final boolean b2 = false;
                this.gestureCompleted = false;
                this.dragStarted = false;
                final boolean b3 = true;
                this.ignoreSelect = true;
                this.ignoreDeselect = true;
                final long code3 = this.callWindowProc(this.handle, 513, wParam, lParam);
                if (OS.GetFocus() != this.handle) {
                    OS.SetFocus(this.handle);
                }
                long hNewItem = OS.SendMessage(this.handle, 4362, 9L, 0L);
                if (fakeSelection) {
                    if (hOldItem == 0L || (hNewItem == hOldItem && lpht.hItem != hOldItem)) {
                        OS.SendMessage(this.handle, 4363, 9L, lpht.hItem);
                        hNewItem = OS.SendMessage(this.handle, 4362, 9L, 0L);
                    }
                    if (!this.dragStarted && (this.state & 0x8000) != 0x0 && this.hooks(29)) {
                        this.dragStarted = this.dragDetect(this.handle, lpht.x, lpht.y, false, (boolean[])null, (boolean[])null);
                    }
                }
                final boolean b4 = false;
                this.ignoreSelect = false;
                this.ignoreDeselect = false;
                this.hSelect = 0L;
                if (this.dragStarted && !display3.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                    OS.SetCapture(this.handle);
                }
                if ((this.style & 0x4) != 0x0 && hOldItem == hNewItem) {
                    tvItem3.mask = 24;
                    tvItem3.state = 2;
                    tvItem3.stateMask = 2;
                    tvItem3.hItem = hNewItem;
                    OS.SendMessage(this.handle, 4415, 0L, tvItem3);
                }
                if ((this.style & 0x2) != 0x0) {
                    if (hittestSelected || (wParam & 0x8L) != 0x0L) {
                        if (hOldItem == hNewItem && hOldItem == lpht.hItem) {
                            if ((wParam & 0x8L) != 0x0L) {
                                final TVITEM tvitem3;
                                final TVITEM tvitem = tvitem3 = tvItem3;
                                tvitem3.state ^= 0x2;
                                if (this.dragStarted) {
                                    tvItem3.state = 2;
                                }
                                OS.SendMessage(this.handle, 4415, 0L, tvItem3);
                            }
                        }
                        else {
                            if ((tvItem3.state & 0x2) != 0x0) {
                                tvItem3.state = 2;
                                OS.SendMessage(this.handle, 4415, 0L, tvItem3);
                            }
                            if ((wParam & 0x8L) != 0x0L && !this.dragStarted && hittestSelected) {
                                tvItem3.state = 0;
                                tvItem3.hItem = lpht.hItem;
                                OS.SendMessage(this.handle, 4415, 0L, tvItem3);
                            }
                        }
                        final RECT rect2 = new RECT();
                        final RECT rect3 = new RECT();
                        OS.TreeView_GetItemRect(this.handle, hOldItem, rect2, false);
                        OS.TreeView_GetItemRect(this.handle, hNewItem, rect3, false);
                        OS.DefWindowProc(this.handle, 11, 1L, 0L);
                        OS.InvalidateRect(this.handle, rect2, true);
                        OS.InvalidateRect(this.handle, rect3, true);
                        OS.UpdateWindow(this.handle);
                    }
                    if ((wParam & 0x8L) == 0x0L && (!hittestSelected || !this.dragStarted)) {
                        tvItem3.state = 0;
                        final long oldProc = OS.GetWindowLongPtr(this.handle, -4);
                        OS.SetWindowLongPtr(this.handle, -4, Tree.TreeProc);
                        if ((this.style & 0x10000000) != 0x0) {
                            final long hItem3 = OS.SendMessage(this.handle, 4362, 0L, 0L);
                            this.deselect(hItem3, tvItem3, hNewItem);
                        }
                        else {
                            for (final TreeItem item : this.items) {
                                if (item != null && item.handle != hNewItem) {
                                    tvItem3.hItem = item.handle;
                                    OS.SendMessage(this.handle, 4415, 0L, tvItem3);
                                }
                            }
                        }
                        tvItem3.hItem = hNewItem;
                        tvItem3.state = 2;
                        OS.SendMessage(this.handle, 4415, 0L, tvItem3);
                        OS.SetWindowLongPtr(this.handle, -4, oldProc);
                        if ((wParam & 0x4L) != 0x0L) {
                            final RECT rect4 = new RECT();
                            if (this.hAnchor == 0L) {
                                this.hAnchor = hNewItem;
                            }
                            if (OS.TreeView_GetItemRect(this.handle, this.hAnchor, rect4, false)) {
                                final RECT rect5 = new RECT();
                                if (OS.TreeView_GetItemRect(this.handle, hNewItem, rect5, false)) {
                                    final int flags = (rect4.top < rect5.top) ? 6 : 7;
                                    tvItem3.state = 2;
                                    final TVITEM tvitem2 = tvItem3;
                                    final long hAnchor = this.hAnchor;
                                    tvitem2.hItem = hAnchor;
                                    long hItem4 = hAnchor;
                                    OS.SendMessage(this.handle, 4415, 0L, tvItem3);
                                    while (hItem4 != hNewItem) {
                                        tvItem3.hItem = hItem4;
                                        OS.SendMessage(this.handle, 4415, 0L, tvItem3);
                                        hItem4 = OS.SendMessage(this.handle, 4362, (long)flags, hItem4);
                                    }
                                }
                            }
                        }
                    }
                }
                if ((wParam & 0x4L) == 0x0L) {
                    this.hAnchor = hNewItem;
                }
                if (!this.gestureCompleted) {
                    tvItem3.hItem = hNewItem;
                    tvItem3.mask = 20;
                    OS.SendMessage(this.handle, 4414, 0L, tvItem3);
                    final Event event3 = new Event();
                    event3.item = (Widget)this._getItem(tvItem3.hItem, (int)tvItem3.lParam);
                    this.sendSelectionEvent(13, event3, false);
                }
                this.gestureCompleted = false;
                if (this.dragStarted) {
                    this.sendDragEvent(1, OS.GET_X_LPARAM(lParam), OS.GET_Y_LPARAM(lParam));
                }
                else {
                    final int bits3 = OS.GetWindowLong(this.handle, -16);
                    if ((bits3 & 0x10) == 0x0) {
                        this.sendMouseEvent(4, 1, this.handle, lParam);
                    }
                }
                this.dragStarted = false;
                return new LRESULT(code3);
            }
        }
    }
    
    LRESULT WM_MOUSEMOVE(final long wParam, final long lParam) {
        final Display display = this.display;
        final LRESULT result = super.WM_MOUSEMOVE(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (this.itemToolTipHandle != 0L) {
            int mask = 19;
            if (display.xMouse) {
                mask |= 0x60;
            }
            if ((wParam & (long)mask) == 0x0L) {
                final int x = OS.GET_X_LPARAM(lParam);
                final int y = OS.GET_Y_LPARAM(lParam);
                final int[] index = { 0 };
                final TreeItem[] item = { null };
                final RECT[] cellRect = { null };
                final RECT[] itemRect = { null };
                if (this.findCell(x, y, item, index, cellRect, itemRect)) {
                    if (OS.SendMessage(this.itemToolTipHandle, 1083, 0L, 0L) == 0L && OS.IsWindowVisible(this.itemToolTipHandle)) {
                        OS.ShowWindow(this.itemToolTipHandle, 0);
                    }
                    final TOOLINFO lpti = new TOOLINFO();
                    lpti.cbSize = TOOLINFO.sizeof;
                    lpti.hwnd = this.handle;
                    lpti.uId = this.handle;
                    lpti.uFlags = 272;
                    lpti.left = cellRect[0].left;
                    lpti.top = cellRect[0].top;
                    lpti.right = cellRect[0].right;
                    lpti.bottom = cellRect[0].bottom;
                    OS.SendMessage(this.itemToolTipHandle, 1076, 0L, lpti);
                }
            }
        }
        return result;
    }
    
    LRESULT WM_MOUSEWHEEL(final long wParam, final long lParam) {
        final LRESULT result = super.WM_MOUSEWHEEL(wParam, lParam);
        if (this.itemToolTipHandle != 0L) {
            OS.ShowWindow(this.itemToolTipHandle, 0);
        }
        return result;
    }
    
    LRESULT WM_MOVE(final long wParam, final long lParam) {
        if (this.itemToolTipHandle != 0L) {
            OS.ShowWindow(this.itemToolTipHandle, 0);
        }
        if (this.ignoreResize) {
            return null;
        }
        return super.WM_MOVE(wParam, lParam);
    }
    
    LRESULT WM_RBUTTONDOWN(final long wParam, final long lParam) {
        final Display display = this.display;
        display.captureChanged = false;
        if (!this.sendMouseEvent(3, 3, this.handle, lParam)) {
            if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                OS.SetCapture(this.handle);
            }
            return LRESULT.ZERO;
        }
        if (OS.GetFocus() != this.handle) {
            OS.SetFocus(this.handle);
        }
        final TVHITTESTINFO lpht = new TVHITTESTINFO();
        lpht.x = OS.GET_X_LPARAM(lParam);
        lpht.y = OS.GET_Y_LPARAM(lParam);
        OS.SendMessage(this.handle, 4369, 0L, lpht);
        if (lpht.hItem != 0L) {
            boolean fakeSelection = (this.style & 0x10000) != 0x0;
            if (!fakeSelection) {
                if (this.hooks(41)) {
                    fakeSelection = this.hitTestSelection(lpht.hItem, lpht.x, lpht.y);
                }
                else {
                    final int flags = 6;
                    fakeSelection = ((lpht.flags & 0x6) != 0x0);
                }
            }
            if (fakeSelection && (wParam & 0xCL) == 0x0L) {
                final TVITEM tvItem = new TVITEM();
                tvItem.mask = 24;
                tvItem.stateMask = 2;
                tvItem.hItem = lpht.hItem;
                OS.SendMessage(this.handle, 4414, 0L, tvItem);
                if ((tvItem.state & 0x2) == 0x0) {
                    this.ignoreSelect = true;
                    OS.SendMessage(this.handle, 4363, 9L, 0L);
                    this.ignoreSelect = false;
                    OS.SendMessage(this.handle, 4363, 9L, lpht.hItem);
                }
            }
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_PAINT(final long wParam, final long lParam) {
        if ((this.state & 0x1000) != 0x0) {
            return LRESULT.ZERO;
        }
        if (this.shrink && !this.ignoreShrink && this.items != null) {
            int count;
            for (count = this.items.length - 1; count >= 0 && this.items[count] == null; --count) {}
            ++count;
            if (this.items.length > 4 && this.items.length - count > 3) {
                final int length = Math.max(4, (count + 3) / 4 * 4);
                final TreeItem[] newItems = new TreeItem[length];
                System.arraycopy(this.items, 0, newItems, 0, count);
                this.items = newItems;
            }
            this.shrink = false;
        }
        if ((this.style & 0x20000000) != 0x0 || this.findImageControl() != null) {
            boolean doubleBuffer = true;
            if (this.explorerTheme) {
                final int exStyle = (int)OS.SendMessage(this.handle, 4397, 0L, 0L);
                if ((exStyle & 0x4) != 0x0) {
                    doubleBuffer = false;
                }
            }
            if (doubleBuffer) {
                GC gc = null;
                long paintDC = 0L;
                final PAINTSTRUCT ps = new PAINTSTRUCT();
                final boolean hooksPaint = this.hooks(9) || this.filters(9);
                if (hooksPaint) {
                    final GCData data = new GCData();
                    data.ps = ps;
                    data.hwnd = this.handle;
                    gc = GC.win32_new((Drawable)this, data);
                    paintDC = gc.handle;
                }
                else {
                    paintDC = OS.BeginPaint(this.handle, ps);
                }
                final int width = ps.right - ps.left;
                final int height = ps.bottom - ps.top;
                if (width != 0 && height != 0) {
                    final long hDC = OS.CreateCompatibleDC(paintDC);
                    final POINT lpPoint1 = new POINT();
                    final POINT lpPoint2 = new POINT();
                    OS.SetWindowOrgEx(hDC, ps.left, ps.top, lpPoint1);
                    OS.SetBrushOrgEx(hDC, ps.left, ps.top, lpPoint2);
                    final long hBitmap = OS.CreateCompatibleBitmap(paintDC, width, height);
                    final long hOldBitmap = OS.SelectObject(hDC, hBitmap);
                    final RECT rect = new RECT();
                    OS.SetRect(rect, ps.left, ps.top, ps.right, ps.bottom);
                    this.drawBackground(hDC, rect);
                    this.callWindowProc(this.handle, 15, hDC, 0L);
                    OS.SetWindowOrgEx(hDC, lpPoint1.x, lpPoint1.y, (POINT)null);
                    OS.SetBrushOrgEx(hDC, lpPoint2.x, lpPoint2.y, (POINT)null);
                    OS.BitBlt(paintDC, ps.left, ps.top, width, height, hDC, 0, 0, 13369376);
                    OS.SelectObject(hDC, hOldBitmap);
                    OS.DeleteObject(hBitmap);
                    OS.DeleteObject(hDC);
                    if (hooksPaint) {
                        final Event event = new Event();
                        event.gc = gc;
                        event.setBoundsInPixels(new Rectangle(ps.left, ps.top, ps.right - ps.left, ps.bottom - ps.top));
                        this.sendEvent(9, event);
                        event.gc = null;
                    }
                }
                if (hooksPaint) {
                    gc.dispose();
                }
                else {
                    OS.EndPaint(this.handle, ps);
                }
                return LRESULT.ZERO;
            }
        }
        return super.WM_PAINT(wParam, lParam);
    }
    
    LRESULT WM_SETCURSOR(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SETCURSOR(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (OS.WIN32_VERSION >= OS.VERSION(6, 1) && wParam == this.handle) {
            final int hitTest = (short)OS.LOWORD(lParam);
            if (hitTest == 1) {
                OS.SetCursor(OS.LoadCursor(0L, 32512L));
                return LRESULT.ONE;
            }
        }
        return null;
    }
    
    LRESULT WM_SETFOCUS(final long wParam, final long lParam) {
        boolean redraw = (this.style & 0x2) != 0x0;
        if (!redraw && this.imageList != null) {
            final int bits = OS.GetWindowLong(this.handle, -16);
            if ((bits & 0x1000) == 0x0) {
                redraw = true;
            }
        }
        if (redraw) {
            this.redrawSelection();
        }
        return super.WM_SETFOCUS(wParam, lParam);
    }
    
    LRESULT WM_SETFONT(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SETFONT(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (this.hwndHeader != 0L) {
            OS.SendMessage(this.hwndHeader, 48, 0L, lParam);
            OS.SendMessage(this.hwndHeader, 48, wParam, lParam);
        }
        if (this.itemToolTipHandle != 0L) {
            OS.ShowWindow(this.itemToolTipHandle, 0);
            OS.SendMessage(this.itemToolTipHandle, 48, wParam, lParam);
        }
        if (this.headerToolTipHandle != 0L) {
            OS.SendMessage(this.headerToolTipHandle, 48, wParam, lParam);
            this.updateHeaderToolTips();
        }
        return result;
    }
    
    LRESULT WM_SETREDRAW(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SETREDRAW(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (this.itemToolTipHandle != 0L) {
            OS.ShowWindow(this.itemToolTipHandle, 0);
        }
        final long code = OS.DefWindowProc(this.handle, 11, wParam, lParam);
        return (code == 0L) ? LRESULT.ZERO : new LRESULT(code);
    }
    
    LRESULT WM_SIZE(final long wParam, final long lParam) {
        if (this.itemToolTipHandle != 0L) {
            OS.ShowWindow(this.itemToolTipHandle, 0);
        }
        final int bits = OS.GetWindowLong(this.handle, -16);
        if ((bits & 0x8000) != 0x0) {
            OS.ShowScrollBar(this.handle, 0, false);
        }
        if (this.explorerTheme && (this.style & 0x10000) != 0x0) {
            OS.InvalidateRect(this.handle, (RECT)null, false);
        }
        if (this.ignoreResize) {
            return null;
        }
        return super.WM_SIZE(wParam, lParam);
    }
    
    LRESULT WM_SYSCOLORCHANGE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SYSCOLORCHANGE(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (this.explorerTheme && this.foreground == -1) {
            this.setForegroundPixel(-1);
        }
        if ((this.style & 0x20) != 0x0) {
            this.setCheckboxImageList();
        }
        return result;
    }
    
    LRESULT WM_VSCROLL(final long wParam, final long lParam) {
        boolean fixScroll = false;
        if ((this.style & 0x20000000) != 0x0) {
            final int code = OS.LOWORD(wParam);
            switch (code) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 6:
                case 7: {
                    fixScroll = ((this.style & 0x10000000) != 0x0 || this.hooks(40) || this.hooks(42));
                    break;
                }
            }
        }
        if (fixScroll) {
            this.style &= 0xDFFFFFFF;
            if (this.explorerTheme) {
                OS.SendMessage(this.handle, 4396, 4L, 0L);
            }
        }
        final LRESULT result = super.WM_VSCROLL(wParam, lParam);
        if (fixScroll) {
            this.style |= 0x20000000;
            if (this.explorerTheme) {
                OS.SendMessage(this.handle, 4396, 4L, 4L);
            }
        }
        if (result != null) {
            return result;
        }
        return result;
    }
    
    LRESULT WM_TIMER(final long wParam, final long lParam) {
        final LRESULT result = super.WM_TIMER(wParam, lParam);
        if (result != null) {
            return result;
        }
        final long bits = OS.SendMessage(this.handle, 4397, 0L, 0L);
        if ((bits & 0x40L) != 0x0L) {
            if (!OS.IsWindowVisible(this.handle)) {
                if (this.lastTimerID == wParam) {
                    ++this.lastTimerCount;
                }
                else {
                    this.lastTimerCount = 0;
                }
                this.lastTimerID = wParam;
                if (this.lastTimerCount >= 8) {
                    OS.CallWindowProc(Tree.TreeProc, this.handle, 512, 0L, 0L);
                    this.lastTimerID = -1L;
                    this.lastTimerCount = 0;
                }
            }
            else {
                this.lastTimerID = -1L;
                this.lastTimerCount = 0;
            }
        }
        return result;
    }
    
    LRESULT wmColorChild(final long wParam, final long lParam) {
        if (this.findImageControl() != null) {
            return new LRESULT(OS.GetStockObject(5));
        }
        return null;
    }
    
    LRESULT wmNotify(final NMHDR hdr, final long wParam, final long lParam) {
        if (hdr.hwndFrom == this.itemToolTipHandle && this.itemToolTipHandle != 0L) {
            final LRESULT result = this.wmNotifyToolTip(hdr, wParam, lParam);
            if (result != null) {
                return result;
            }
        }
        if (hdr.hwndFrom == this.hwndHeader && this.hwndHeader != 0L) {
            final LRESULT result = this.wmNotifyHeader(hdr, wParam, lParam);
            if (result != null) {
                return result;
            }
        }
        return super.wmNotify(hdr, wParam, lParam);
    }
    
    LRESULT wmNotifyChild(final NMHDR hdr, final long wParam, final long lParam) {
        Label_2219: {
            switch (hdr.code) {
                case -452: {
                    final NMTVDISPINFO lptvdi = new NMTVDISPINFO();
                    OS.MoveMemory(lptvdi, lParam, NMTVDISPINFO.sizeof);
                    if ((this.style & 0x10000000) != 0x0) {
                        boolean checkVisible = true;
                        if (!this.ignoreShrink && this.items != null && lptvdi.lParam != -1L && this.items[(int)lptvdi.lParam] != null && this.items[(int)lptvdi.lParam].cached) {
                            checkVisible = false;
                        }
                        if (checkVisible) {
                            if (!this.getDrawing()) {
                                break;
                            }
                            if (!OS.IsWindowVisible(this.handle)) {
                                break;
                            }
                            final RECT itemRect = new RECT();
                            if (!OS.TreeView_GetItemRect(this.handle, lptvdi.hItem, itemRect, false)) {
                                break;
                            }
                            final RECT rect = new RECT();
                            OS.GetClientRect(this.handle, rect);
                            if (!OS.IntersectRect(rect, rect, itemRect)) {
                                break;
                            }
                            if (this.ignoreShrink) {
                                OS.InvalidateRect(this.handle, rect, true);
                                break;
                            }
                        }
                    }
                    if (this.items == null) {
                        break;
                    }
                    int id = (int)lptvdi.lParam;
                    if ((this.style & 0x10000000) != 0x0 && id == -1) {
                        final TVITEM tvItem = new TVITEM();
                        tvItem.mask = 20;
                        tvItem.hItem = lptvdi.hItem;
                        OS.SendMessage(this.handle, 4414, 0L, tvItem);
                        id = (int)tvItem.lParam;
                    }
                    final TreeItem item = this._getItem(lptvdi.hItem, id);
                    if (item == null) {
                        break;
                    }
                    if (item.isDisposed()) {
                        break;
                    }
                    if (!item.cached) {
                        if ((this.style & 0x10000000) != 0x0 && !this.checkData(item, false)) {
                            break;
                        }
                        if (this.painted) {
                            item.cached = true;
                        }
                    }
                    int index = 0;
                    if (this.hwndHeader != 0L) {
                        index = (int)OS.SendMessage(this.hwndHeader, 4623, 0L, 0L);
                    }
                    if ((lptvdi.mask & 0x1) != 0x0) {
                        String string = null;
                        if (index == 0) {
                            string = item.text;
                        }
                        else {
                            final String[] strings = item.strings;
                            if (strings != null) {
                                string = strings[index];
                            }
                        }
                        if (string != null) {
                            final int length = Math.min(string.length() + 1, lptvdi.cchTextMax);
                            final char[] buffer = new char[length];
                            string.getChars(0, length - 1, buffer, 0);
                            OS.MoveMemory(lptvdi.pszText, buffer, length * 2);
                            lptvdi.cchTextMax = length;
                        }
                    }
                    if ((lptvdi.mask & 0x22) != 0x0) {
                        Image image = null;
                        if (index == 0) {
                            image = item.image;
                        }
                        else {
                            final Image[] images = item.images;
                            if (images != null) {
                                image = images[index];
                            }
                        }
                        final NMTVDISPINFO nmtvdispinfo = lptvdi;
                        final NMTVDISPINFO nmtvdispinfo2 = lptvdi;
                        final int n = -2;
                        nmtvdispinfo2.iSelectedImage = -2;
                        nmtvdispinfo.iImage = -2;
                        if (image != null) {
                            final NMTVDISPINFO nmtvdispinfo3 = lptvdi;
                            final NMTVDISPINFO nmtvdispinfo4 = lptvdi;
                            final int imageIndex = this.imageIndex(image, index);
                            nmtvdispinfo4.iSelectedImage = imageIndex;
                            nmtvdispinfo3.iImage = imageIndex;
                        }
                        if (this.explorerTheme && OS.IsWindowEnabled(this.handle) && this.findImageControl() != null) {
                            final NMTVDISPINFO nmtvdispinfo5 = lptvdi;
                            final NMTVDISPINFO nmtvdispinfo6 = lptvdi;
                            final int n2 = -2;
                            nmtvdispinfo6.iSelectedImage = -2;
                            nmtvdispinfo5.iImage = -2;
                        }
                    }
                    OS.MoveMemory(lParam, lptvdi, NMTVDISPINFO.sizeof);
                    break;
                }
                case -12: {
                    if (hdr.hwndFrom == this.hwndHeader) {
                        break;
                    }
                    if (this.hooks(41) && this.hwndHeader == 0L) {
                        this.createParent();
                    }
                    if (!this.customDraw && this.findImageControl() == null && OS.IsAppThemed()) {
                        if (this.sortColumn == null) {
                            break;
                        }
                        if (this.sortDirection == 0) {
                            break;
                        }
                    }
                    final NMTVCUSTOMDRAW nmcd = new NMTVCUSTOMDRAW();
                    OS.MoveMemory(nmcd, lParam, NMTVCUSTOMDRAW.sizeof);
                    switch (nmcd.dwDrawStage) {
                        case 1: {
                            return this.CDDS_PREPAINT(nmcd, wParam, lParam);
                        }
                        case 65537: {
                            return this.CDDS_ITEMPREPAINT(nmcd, wParam, lParam);
                        }
                        case 65538: {
                            return this.CDDS_ITEMPOSTPAINT(nmcd, wParam, lParam);
                        }
                        case 2: {
                            return this.CDDS_POSTPAINT(nmcd, wParam, lParam);
                        }
                        default: {
                            break Label_2219;
                        }
                    }
                    break;
                }
                case -3: {
                    if (this.hooks(41)) {
                        return LRESULT.ONE;
                    }
                    if (!this.hooks(14)) {
                        break;
                    }
                    final POINT pt = new POINT();
                    final int pos = OS.GetMessagePos();
                    OS.POINTSTOPOINT(pt, (long)pos);
                    OS.ScreenToClient(this.handle, pt);
                    final TVHITTESTINFO lpht = new TVHITTESTINFO();
                    lpht.x = pt.x;
                    lpht.y = pt.y;
                    OS.SendMessage(this.handle, 4369, 0L, lpht);
                    if (lpht.hItem != 0L && (lpht.flags & 0x46) != 0x0) {
                        return LRESULT.ONE;
                    }
                    break;
                }
                case -417: {
                    if ((this.style & 0x2) == 0x0) {
                        break;
                    }
                    if (this.hSelect == 0L) {
                        break;
                    }
                    final NMTVITEMCHANGE pnm = new NMTVITEMCHANGE();
                    OS.MoveMemory(pnm, lParam, NMTVITEMCHANGE.sizeof);
                    if (this.hSelect == pnm.hItem) {
                        break;
                    }
                    return LRESULT.ONE;
                }
                case -450: {
                    if ((this.style & 0x2) != 0x0 && this.lockSelection) {
                        final NMTREEVIEW treeView = new NMTREEVIEW();
                        OS.MoveMemory(treeView, lParam, NMTREEVIEW.sizeof);
                        TVITEM tvItem2 = treeView.itemOld;
                        this.oldSelected = ((tvItem2.state & 0x2) != 0x0);
                        tvItem2 = treeView.itemNew;
                        this.newSelected = ((tvItem2.state & 0x2) != 0x0);
                    }
                    if (this.ignoreSelect) {
                        break;
                    }
                    if (this.ignoreDeselect) {
                        break;
                    }
                    this.hAnchor = 0L;
                    if ((this.style & 0x2) != 0x0) {
                        this.deselectAll();
                        break;
                    }
                    break;
                }
                case -451: {
                    NMTREEVIEW treeView = null;
                    if ((this.style & 0x2) != 0x0 && this.lockSelection) {
                        if (this.oldSelected) {
                            if (treeView == null) {
                                treeView = new NMTREEVIEW();
                                OS.MoveMemory(treeView, lParam, NMTREEVIEW.sizeof);
                            }
                            final TVITEM tvItem2 = treeView.itemOld;
                            tvItem2.mask = 8;
                            tvItem2.stateMask = 2;
                            tvItem2.state = 2;
                            OS.SendMessage(this.handle, 4415, 0L, tvItem2);
                        }
                        if (!this.newSelected && this.ignoreSelect) {
                            if (treeView == null) {
                                treeView = new NMTREEVIEW();
                                OS.MoveMemory(treeView, lParam, NMTREEVIEW.sizeof);
                            }
                            final TVITEM tvItem2 = treeView.itemNew;
                            tvItem2.mask = 8;
                            tvItem2.stateMask = 2;
                            tvItem2.state = 0;
                            OS.SendMessage(this.handle, 4415, 0L, tvItem2);
                        }
                    }
                    if (!this.ignoreSelect) {
                        if (treeView == null) {
                            treeView = new NMTREEVIEW();
                            OS.MoveMemory(treeView, lParam, NMTREEVIEW.sizeof);
                        }
                        final TVITEM tvItem2 = treeView.itemNew;
                        this.hAnchor = tvItem2.hItem;
                        final Event event = new Event();
                        event.item = (Widget)this._getItem(tvItem2.hItem, (int)tvItem2.lParam);
                        this.sendSelectionEvent(13, event, false);
                    }
                    this.updateScrollBar();
                    break;
                }
                case -454: {
                    if (this.itemToolTipHandle != 0L) {
                        OS.ShowWindow(this.itemToolTipHandle, 0);
                    }
                    boolean runExpanded = false;
                    if ((this.style & 0x10000000) != 0x0) {
                        this.style &= 0xDFFFFFFF;
                    }
                    if (this.hooks(40) || this.hooks(42)) {
                        this.style &= 0xDFFFFFFF;
                    }
                    if (this.findImageControl() != null && this.getDrawing() && OS.IsWindowVisible(this.handle)) {
                        OS.DefWindowProc(this.handle, 11, 0L, 0L);
                    }
                    if (this.hInsert != 0L) {
                        OS.SendMessage(this.handle, 4378, 0L, 0L);
                    }
                    if (!this.ignoreExpand) {
                        final NMTREEVIEW treeView2 = new NMTREEVIEW();
                        OS.MoveMemory(treeView2, lParam, NMTREEVIEW.sizeof);
                        final TVITEM tvItem = treeView2.itemNew;
                        if (this.items == null) {
                            break;
                        }
                        final TreeItem item2 = this._getItem(tvItem.hItem, (int)tvItem.lParam);
                        if (item2 == null) {
                            break;
                        }
                        final Event event2 = new Event();
                        event2.item = (Widget)item2;
                        switch (treeView2.action) {
                            case 2: {
                                if ((tvItem.state & 0x20) != 0x0) {
                                    break;
                                }
                                this.sendEvent(17, event2);
                                if (this.isDisposed()) {
                                    return LRESULT.ZERO;
                                }
                                break;
                            }
                            case 1: {
                                this.sendEvent(18, event2);
                                if (this.isDisposed()) {
                                    return LRESULT.ZERO;
                                }
                                break;
                            }
                        }
                        final long hFirstItem = OS.SendMessage(this.handle, 4362, 4L, tvItem.hItem);
                        runExpanded = (hFirstItem == 0L);
                    }
                    if (!runExpanded) {
                        break;
                    }
                }
                case -455: {
                    if ((this.style & 0x10000000) != 0x0) {
                        this.style |= 0x20000000;
                    }
                    if (this.hooks(40) || this.hooks(42)) {
                        this.style |= 0x20000000;
                    }
                    if (this.findImageControl() != null && this.getDrawing()) {
                        OS.DefWindowProc(this.handle, 11, 1L, 0L);
                        OS.InvalidateRect(this.handle, (RECT)null, true);
                    }
                    if (this.hInsert != 0L) {
                        OS.SendMessage(this.handle, 4378, (long)(this.insertAfter ? 1 : 0), this.hInsert);
                    }
                    if (this.imageList != null) {
                        final NMTREEVIEW treeView = new NMTREEVIEW();
                        OS.MoveMemory(treeView, lParam, NMTREEVIEW.sizeof);
                        final TVITEM tvItem2 = treeView.itemNew;
                        if (tvItem2.hItem != 0L) {
                            final int bits = OS.GetWindowLong(this.handle, -16);
                            if ((bits & 0x1000) == 0x0) {
                                final RECT rect = new RECT();
                                if (OS.TreeView_GetItemRect(this.handle, tvItem2.hItem, rect, false)) {
                                    OS.InvalidateRect(this.handle, rect, true);
                                }
                            }
                        }
                    }
                    this.updateScrollBar();
                    break;
                }
                case -456: {
                    if (OS.GetKeyState(1) >= 0) {
                        break;
                    }
                }
                case -457: {
                    this.dragStarted = true;
                    final NMTREEVIEW treeView = new NMTREEVIEW();
                    OS.MoveMemory(treeView, lParam, NMTREEVIEW.sizeof);
                    final TVITEM tvItem2 = treeView.itemNew;
                    if (tvItem2.hItem != 0L && (tvItem2.state & 0x2) == 0x0) {
                        this.hSelect = tvItem2.hItem;
                        final boolean b = true;
                        this.ignoreDeselect = true;
                        this.ignoreSelect = true;
                        OS.SendMessage(this.handle, 4363, 9L, tvItem2.hItem);
                        final boolean b2 = false;
                        this.ignoreDeselect = false;
                        this.ignoreSelect = false;
                        this.hSelect = 0L;
                        break;
                    }
                    break;
                }
            }
        }
        return super.wmNotifyChild(hdr, wParam, lParam);
    }
    
    LRESULT wmNotifyHeader(final NMHDR hdr, final long wParam, final long lParam) {
        Label_2811: {
            switch (hdr.code) {
                case -326:
                case -325: {
                    final NMHEADER phdn = new NMHEADER();
                    OS.MoveMemory(phdn, lParam, NMHEADER.sizeof);
                    final TreeColumn column = this.columns[phdn.iItem];
                    if (column != null && !column.getResizable()) {
                        return LRESULT.ONE;
                    }
                    this.ignoreColumnMove = true;
                    if (hdr.code == -325 && column != null) {
                        column.pack();
                        break;
                    }
                    break;
                }
                case -12: {
                    final NMCUSTOMDRAW nmcd = new NMCUSTOMDRAW();
                    OS.MoveMemory(nmcd, lParam, NMCUSTOMDRAW.sizeof);
                    switch (nmcd.dwDrawStage) {
                        case 1: {
                            return new LRESULT(this.customHeaderDrawing() ? 48L : 0L);
                        }
                        case 65537: {
                            final RECT rect = new RECT();
                            OS.SetRect(rect, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
                            int pixel = this.getHeaderBackgroundPixel();
                            if ((nmcd.uItemState & 0x1) != 0x0) {
                                pixel = this.getDifferentColor(pixel);
                            }
                            final long brush = OS.CreateSolidBrush(pixel);
                            OS.FillRect(nmcd.hdc, rect, brush);
                            OS.DeleteObject(brush);
                            return new LRESULT(4L);
                        }
                        case 2: {
                            final POINT cursorPos = new POINT();
                            OS.GetCursorPos(cursorPos);
                            OS.MapWindowPoints(0L, this.hwndHeader, cursorPos, 1);
                            int highlightedHeaderDividerX = -1;
                            int lastColumnRight = -1;
                            final RECT[] rects = new RECT[this.columnCount];
                            for (int i = 0; i < this.columnCount; ++i) {
                                rects[i] = new RECT();
                                OS.SendMessage(this.hwndHeader, 4615, (long)i, rects[i]);
                                if (rects[i].right > lastColumnRight) {
                                    lastColumnRight = rects[i].right;
                                }
                                if (this.columns[i] == this.sortColumn && this.sortDirection != 0) {
                                    final long pen = OS.CreatePen(0, 1, this.getHeaderForegroundPixel());
                                    final long oldPen = OS.SelectObject(nmcd.hdc, pen);
                                    final int center = rects[i].left + (rects[i].right - rects[i].left) / 2;
                                    final int leg = DPIUtil.autoScaleUpUsingNativeDPI(3);
                                    if (this.sortDirection == 128) {
                                        OS.Polyline(nmcd.hdc, new int[] { center - leg, 1 + leg, center + 1, 0 }, 2);
                                        OS.Polyline(nmcd.hdc, new int[] { center + leg, 1 + leg, center - 1, 0 }, 2);
                                    }
                                    else if (this.sortDirection == 1024) {
                                        OS.Polyline(nmcd.hdc, new int[] { center - leg, 1, center + 1, 1 + leg + 1 }, 2);
                                        OS.Polyline(nmcd.hdc, new int[] { center + leg, 1, center - 1, 1 + leg + 1 }, 2);
                                    }
                                    OS.SelectObject(nmcd.hdc, oldPen);
                                    OS.DeleteObject(pen);
                                }
                                long pen = OS.CreatePen(0, this.getGridLineWidthInPixels(), this.getSlightlyDifferentColor(this.getHeaderBackgroundPixel()));
                                long oldPen = OS.SelectObject(nmcd.hdc, pen);
                                OS.Polyline(nmcd.hdc, new int[] { rects[i].right - 1, rects[i].top, rects[i].right - 1, rects[i].bottom }, 2);
                                OS.SelectObject(nmcd.hdc, oldPen);
                                OS.DeleteObject(pen);
                                pen = OS.CreatePen(0, this.getGridLineWidthInPixels(), OS.GetSysColor(15));
                                oldPen = OS.SelectObject(nmcd.hdc, pen);
                                OS.Polyline(nmcd.hdc, new int[] { rects[i].right - 1, rects[i].top, rects[i].right - 1, rects[i].bottom }, 2);
                                if (i == 0) {
                                    OS.Polyline(nmcd.hdc, new int[] { nmcd.left, nmcd.bottom - 1, nmcd.right, nmcd.bottom - 1 }, 2);
                                }
                                OS.SelectObject(nmcd.hdc, oldPen);
                                OS.DeleteObject(pen);
                                if (this.headerItemDragging && highlightedHeaderDividerX == -1) {
                                    final int distanceToLeftBorder = cursorPos.x - rects[i].left;
                                    final int distanceToRightBorder = rects[i].right - cursorPos.x;
                                    if (distanceToLeftBorder >= 0 && distanceToRightBorder >= 0) {
                                        highlightedHeaderDividerX = ((distanceToLeftBorder <= distanceToRightBorder) ? (rects[i].left - 1) : rects[i].right);
                                    }
                                }
                                int x = rects[i].left + 3 + 2;
                                if (this.columns[i].image != null) {
                                    final GCData data = new GCData();
                                    data.device = (Device)this.display;
                                    final GC gc = GC.win32_new(nmcd.hdc, data);
                                    final int y = Math.max(0, (nmcd.bottom - this.columns[i].image.getBoundsInPixels().height) / 2);
                                    gc.drawImage(this.columns[i].image, DPIUtil.autoScaleDown(x), DPIUtil.autoScaleDown(y));
                                    x += this.columns[i].image.getBoundsInPixels().width + 12;
                                    gc.dispose();
                                }
                                if (this.columns[i].text != null) {
                                    int flags = 2084;
                                    if ((this.columns[i].style & 0x1000000) != 0x0) {
                                        flags |= 0x1;
                                    }
                                    if ((this.columns[i].style & 0x20000) != 0x0) {
                                        flags |= 0x2;
                                    }
                                    final char[] buffer = this.columns[i].text.toCharArray();
                                    OS.SetBkMode(nmcd.hdc, 1);
                                    OS.SetTextColor(nmcd.hdc, this.getHeaderForegroundPixel());
                                    final RECT textRect = new RECT();
                                    textRect.left = x;
                                    textRect.top = rects[i].top;
                                    textRect.right = rects[i].right;
                                    textRect.bottom = rects[i].bottom;
                                    OS.DrawText(nmcd.hdc, buffer, buffer.length, textRect, flags);
                                }
                            }
                            if (lastColumnRight < nmcd.right) {
                                final RECT rect2 = new RECT();
                                OS.SetRect(rect2, lastColumnRight, nmcd.top, nmcd.right, nmcd.bottom - 1);
                                final long brush2 = OS.CreateSolidBrush(this.getHeaderBackgroundPixel());
                                OS.FillRect(nmcd.hdc, rect2, brush2);
                                OS.DeleteObject(brush2);
                            }
                            if (highlightedHeaderDividerX != -1) {
                                final long pen2 = OS.CreatePen(0, 4, OS.GetSysColor(13));
                                final long oldPen2 = OS.SelectObject(nmcd.hdc, pen2);
                                OS.Polyline(nmcd.hdc, new int[] { highlightedHeaderDividerX, nmcd.top, highlightedHeaderDividerX, nmcd.bottom }, 2);
                                OS.SelectObject(nmcd.hdc, oldPen2);
                                OS.DeleteObject(pen2);
                            }
                            return new LRESULT(0L);
                        }
                        default: {
                            break Label_2811;
                        }
                    }
                    break;
                }
                case -16: {
                    if (!this.ignoreColumnMove) {
                        for (int j = 0; j < this.columnCount; ++j) {
                            final TreeColumn column = this.columns[j];
                            column.updateToolTip(j);
                        }
                        this.updateImageList();
                    }
                    this.ignoreColumnMove = false;
                    break;
                }
                case -310: {
                    if (this.ignoreColumnMove) {
                        return LRESULT.ONE;
                    }
                    final NMHEADER phdn = new NMHEADER();
                    OS.MoveMemory(phdn, lParam, NMHEADER.sizeof);
                    if (phdn.iItem == -1) {
                        break;
                    }
                    final TreeColumn column = this.columns[phdn.iItem];
                    if (column != null && !column.getMoveable()) {
                        this.ignoreColumnMove = true;
                        return LRESULT.ONE;
                    }
                    this.headerItemDragging = true;
                    break;
                }
                case -311: {
                    this.headerItemDragging = false;
                    final NMHEADER phdn = new NMHEADER();
                    OS.MoveMemory(phdn, lParam, NMHEADER.sizeof);
                    if (phdn.iItem == -1 || phdn.pitem == 0L) {
                        break;
                    }
                    final HDITEM pitem = new HDITEM();
                    OS.MoveMemory(pitem, phdn.pitem, HDITEM.sizeof);
                    if ((pitem.mask & 0x80) != 0x0 && pitem.iOrder != -1) {
                        final int[] order = new int[this.columnCount];
                        OS.SendMessage(this.hwndHeader, 4625, (long)this.columnCount, order);
                        int index;
                        for (index = 0; index < order.length && order[index] != phdn.iItem; ++index) {}
                        if (index == order.length) {
                            index = 0;
                        }
                        if (index != pitem.iOrder) {
                            final int start = Math.min(index, pitem.iOrder);
                            final int end = Math.max(index, pitem.iOrder);
                            final RECT rect3 = new RECT();
                            final RECT headerRect = new RECT();
                            OS.GetClientRect(this.handle, rect3);
                            OS.SendMessage(this.hwndHeader, 4615, (long)order[start], headerRect);
                            rect3.left = Math.max(rect3.left, headerRect.left);
                            OS.SendMessage(this.hwndHeader, 4615, (long)order[end], headerRect);
                            rect3.right = Math.min(rect3.right, headerRect.right);
                            OS.InvalidateRect(this.handle, rect3, true);
                            this.ignoreColumnMove = false;
                            for (int k = start; k <= end; ++k) {
                                final TreeColumn column2 = this.columns[order[k]];
                                if (!column2.isDisposed()) {
                                    column2.postEvent(10);
                                }
                            }
                        }
                        break;
                    }
                    break;
                }
                case -320: {
                    final NMHEADER phdn = new NMHEADER();
                    OS.MoveMemory(phdn, lParam, NMHEADER.sizeof);
                    if (phdn.pitem == 0L) {
                        break;
                    }
                    final HDITEM newItem = new HDITEM();
                    OS.MoveMemory(newItem, phdn.pitem, HDITEM.sizeof);
                    if ((newItem.mask & 0x1) != 0x0) {
                        final RECT rect4 = new RECT();
                        OS.GetClientRect(this.handle, rect4);
                        final HDITEM oldItem = new HDITEM();
                        oldItem.mask = 1;
                        OS.SendMessage(this.hwndHeader, 4619, (long)phdn.iItem, oldItem);
                        final int deltaX = newItem.cxy - oldItem.cxy;
                        final RECT headerRect2 = new RECT();
                        OS.SendMessage(this.hwndHeader, 4615, (long)phdn.iItem, headerRect2);
                        final int gridWidth = this.linesVisible ? 1 : 0;
                        rect4.left = headerRect2.right - gridWidth;
                        final int newX = rect4.left + deltaX;
                        rect4.right = Math.max(rect4.right, rect4.left + Math.abs(deltaX));
                        if (this.explorerTheme || this.findImageControl() != null || this.hooks(41) || this.hooks(40) || this.hooks(42)) {
                            final RECT rect6;
                            final RECT rect5 = rect6 = rect4;
                            rect6.left -= OS.GetSystemMetrics(83);
                            OS.InvalidateRect(this.handle, rect4, true);
                            OS.OffsetRect(rect4, deltaX, 0);
                            OS.InvalidateRect(this.handle, rect4, true);
                        }
                        else {
                            final int flags2 = 6;
                            OS.ScrollWindowEx(this.handle, deltaX, 0, rect4, (RECT)null, 0L, (RECT)null, 6);
                        }
                        if (OS.SendMessage(this.hwndHeader, 4623, (long)phdn.iItem, 0L) != 0L) {
                            rect4.left = headerRect2.left;
                            rect4.right = newX;
                            OS.InvalidateRect(this.handle, rect4, true);
                        }
                        this.setScrollWidth();
                        break;
                    }
                    break;
                }
                case -321: {
                    final NMHEADER phdn = new NMHEADER();
                    OS.MoveMemory(phdn, lParam, NMHEADER.sizeof);
                    if (phdn.pitem != 0L) {
                        final HDITEM pitem = new HDITEM();
                        OS.MoveMemory(pitem, phdn.pitem, HDITEM.sizeof);
                        if ((pitem.mask & 0x1) != 0x0) {
                            if (this.ignoreColumnMove) {
                                final int flags3 = 384;
                                OS.RedrawWindow(this.handle, (RECT)null, 0L, 384);
                            }
                            final TreeColumn column3 = this.columns[phdn.iItem];
                            if (column3 != null) {
                                column3.updateToolTip(phdn.iItem);
                                column3.sendEvent(11);
                                if (this.isDisposed()) {
                                    return LRESULT.ZERO;
                                }
                                final TreeColumn[] newColumns = new TreeColumn[this.columnCount];
                                System.arraycopy(this.columns, 0, newColumns, 0, this.columnCount);
                                final int[] order2 = new int[this.columnCount];
                                OS.SendMessage(this.hwndHeader, 4625, (long)this.columnCount, order2);
                                boolean moved = false;
                                for (int l = 0; l < this.columnCount; ++l) {
                                    final TreeColumn nextColumn = newColumns[order2[l]];
                                    if (moved && !nextColumn.isDisposed()) {
                                        nextColumn.updateToolTip(order2[l]);
                                        nextColumn.sendEvent(10);
                                    }
                                    if (nextColumn == column3) {
                                        moved = true;
                                    }
                                }
                            }
                        }
                        this.setScrollWidth();
                        break;
                    }
                    break;
                }
                case -322: {
                    final NMHEADER phdn = new NMHEADER();
                    OS.MoveMemory(phdn, lParam, NMHEADER.sizeof);
                    final TreeColumn column = this.columns[phdn.iItem];
                    if (column != null) {
                        column.sendSelectionEvent(13);
                        break;
                    }
                    break;
                }
                case -323: {
                    final NMHEADER phdn = new NMHEADER();
                    OS.MoveMemory(phdn, lParam, NMHEADER.sizeof);
                    final TreeColumn column = this.columns[phdn.iItem];
                    if (column != null) {
                        column.sendSelectionEvent(14);
                        break;
                    }
                    break;
                }
            }
        }
        return null;
    }
    
    LRESULT wmNotifyToolTip(final NMHDR hdr, final long wParam, final long lParam) {
        switch (hdr.code) {
            case -12: {
                final NMTTCUSTOMDRAW nmcd = new NMTTCUSTOMDRAW();
                OS.MoveMemory(nmcd, lParam, NMTTCUSTOMDRAW.sizeof);
                return this.wmNotifyToolTip(nmcd, lParam);
            }
            case -521: {
                final LRESULT result = super.wmNotify(hdr, wParam, lParam);
                if (result != null) {
                    return result;
                }
                final int pos = OS.GetMessagePos();
                final POINT pt = new POINT();
                OS.POINTSTOPOINT(pt, (long)pos);
                OS.ScreenToClient(this.handle, pt);
                final int[] index = { 0 };
                final TreeItem[] item = { null };
                final RECT[] cellRect = { null };
                final RECT[] itemRect = { null };
                if (this.findCell(pt.x, pt.y, item, index, cellRect, itemRect)) {
                    final RECT toolRect = this.toolTipRect(itemRect[0]);
                    OS.MapWindowPoints(this.handle, 0L, toolRect, 2);
                    final int width = toolRect.right - toolRect.left;
                    final int height = toolRect.bottom - toolRect.top;
                    int flags = 21;
                    if (this.isCustomToolTip()) {
                        flags &= 0xFFFFFFFE;
                    }
                    OS.SetWindowPos(this.itemToolTipHandle, 0L, toolRect.left, toolRect.top, width, height, flags);
                    return LRESULT.ONE;
                }
                return result;
            }
            default: {
                return null;
            }
        }
    }
    
    LRESULT wmNotifyToolTip(final NMTTCUSTOMDRAW nmcd, final long lParam) {
        switch (nmcd.dwDrawStage) {
            case 1: {
                if (this.isCustomToolTip()) {
                    return new LRESULT(18L);
                }
                break;
            }
            case 2: {
                if (OS.SendMessage(this.itemToolTipHandle, 1083, 0L, 0L) == 0L) {
                    break;
                }
                final TOOLINFO lpti = new TOOLINFO();
                lpti.cbSize = TOOLINFO.sizeof;
                if (OS.SendMessage(this.itemToolTipHandle, 1083, 0L, lpti) == 0L) {
                    break;
                }
                final int[] index = { 0 };
                final TreeItem[] item = { null };
                final RECT[] cellRect = { null };
                final RECT[] itemRect = { null };
                final int pos = OS.GetMessagePos();
                final POINT pt = new POINT();
                OS.POINTSTOPOINT(pt, (long)pos);
                OS.ScreenToClient(this.handle, pt);
                if (this.findCell(pt.x, pt.y, item, index, cellRect, itemRect)) {
                    final long hDC = OS.GetDC(this.handle);
                    long hFont = item[0].fontHandle(index[0]);
                    if (hFont == -1L) {
                        hFont = OS.SendMessage(this.handle, 49, 0L, 0L);
                    }
                    final long oldFont = OS.SelectObject(hDC, hFont);
                    boolean drawForeground = true;
                    cellRect[0] = item[0].getBounds(index[0], true, true, false, false, false, hDC);
                    if (this.hooks(40)) {
                        final Event event = this.sendEraseItemEvent(item[0], nmcd, index[0], cellRect[0]);
                        if (this.isDisposed()) {
                            break;
                        }
                        if (item[0].isDisposed()) {
                            break;
                        }
                        drawForeground = (event.doit && (event.detail & 0x10) != 0x0);
                    }
                    if (drawForeground) {
                        final int nSavedDC = OS.SaveDC(nmcd.hdc);
                        final int gridWidth = this.getLinesVisible() ? 1 : 0;
                        final RECT insetRect = this.toolTipInset(cellRect[0]);
                        OS.SetWindowOrgEx(nmcd.hdc, insetRect.left, insetRect.top, (POINT)null);
                        final GCData data = new GCData();
                        data.device = (Device)this.display;
                        data.foreground = OS.GetTextColor(nmcd.hdc);
                        data.background = OS.GetBkColor(nmcd.hdc);
                        data.font = Font.win32_new((Device)this.display, hFont);
                        final GC gc = GC.win32_new(nmcd.hdc, data);
                        int x = cellRect[0].left + 3;
                        if (index[0] != 0) {
                            x -= gridWidth;
                        }
                        final Image image = item[0].getImage(index[0]);
                        if (image != null || index[0] == 0) {
                            final Point size = this.getImageSize();
                            final RECT imageRect = item[0].getBounds(index[0], false, true, false, false, false, hDC);
                            if (this.imageList == null) {
                                size.x = imageRect.right - imageRect.left;
                            }
                            if (image != null) {
                                final Rectangle rect = image.getBounds();
                                gc.drawImage(image, rect.x, rect.y, rect.width, rect.height, DPIUtil.autoScaleDown(x), DPIUtil.autoScaleDown(imageRect.top), DPIUtil.autoScaleDown(size.x), DPIUtil.autoScaleDown(size.y));
                                x += 3 + ((index[0] == 0) ? 1 : 0);
                            }
                            x += size.x;
                        }
                        else {
                            x += 3;
                        }
                        final String string = item[0].getText(index[0]);
                        if (string != null) {
                            int flags = 2084;
                            final TreeColumn column = (this.columns != null) ? this.columns[index[0]] : null;
                            if (column != null) {
                                if ((column.style & 0x1000000) != 0x0) {
                                    flags |= 0x1;
                                }
                                if ((column.style & 0x20000) != 0x0) {
                                    flags |= 0x2;
                                }
                            }
                            final char[] buffer = string.toCharArray();
                            final RECT textRect = new RECT();
                            OS.SetRect(textRect, x, cellRect[0].top, cellRect[0].right, cellRect[0].bottom);
                            OS.DrawText(nmcd.hdc, buffer, buffer.length, textRect, flags);
                        }
                        gc.dispose();
                        OS.RestoreDC(nmcd.hdc, nSavedDC);
                    }
                    if (this.hooks(42)) {
                        itemRect[0] = item[0].getBounds(index[0], true, true, false, false, false, hDC);
                        this.sendPaintItemEvent(item[0], nmcd, index[0], itemRect[0]);
                    }
                    OS.SelectObject(hDC, oldFont);
                    OS.ReleaseDC(this.handle, hDC);
                    break;
                }
                break;
            }
        }
        return null;
    }
    
    static {
        ENABLE_TVS_EX_FADEINOUTEXPANDOS = (System.getProperty("org.eclipse.swt.internal.win32.enableFadeInOutExpandos") != null);
        TreeClass = new TCHAR(0, "SysTreeView32", true);
        HeaderClass = new TCHAR(0, "SysHeader32", true);
        final WNDCLASS lpWndClass = new WNDCLASS();
        OS.GetClassInfo(0L, Tree.TreeClass, lpWndClass);
        TreeProc = lpWndClass.lpfnWndProc;
        OS.GetClassInfo(0L, Tree.HeaderClass, lpWndClass);
        HeaderProc = lpWndClass.lpfnWndProc;
    }
}
