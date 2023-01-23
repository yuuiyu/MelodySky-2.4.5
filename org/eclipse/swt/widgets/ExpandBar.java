//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.graphics.*;

public class ExpandBar extends Composite
{
    ExpandItem[] items;
    int itemCount;
    ExpandItem focusItem;
    int spacing;
    int yCurrentScroll;
    long hFont;
    
    public ExpandBar(final Composite parent, final int style) {
        super(parent, checkStyle(style));
        this.spacing = 4;
    }
    
    public void addExpandListener(final ExpandListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(17, (Listener)typedListener);
        this.addListener(18, (Listener)typedListener);
    }
    
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        return OS.DefWindowProc(hwnd, msg, wParam, lParam);
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    static int checkStyle(int style) {
        style &= 0xFFFFFEFF;
        return style | 0x40000;
    }
    
    Point computeSizeInPixels(final int wHint, final int hHint, final boolean changed) {
        int height = 0;
        int width = 0;
        if ((wHint == -1 || hHint == -1) && this.itemCount > 0) {
            final long hDC = OS.GetDC(this.handle);
            long hTheme = 0L;
            if (this.isAppThemed()) {
                hTheme = this.display.hExplorerBarTheme();
            }
            long hCurrentFont = 0L;
            long oldFont = 0L;
            if (hTheme == 0L) {
                if (this.hFont != 0L) {
                    hCurrentFont = this.hFont;
                }
                else {
                    final NONCLIENTMETRICS info = new NONCLIENTMETRICS();
                    info.cbSize = NONCLIENTMETRICS.sizeof;
                    if (OS.SystemParametersInfo(41, 0, info, 0)) {
                        final LOGFONT logFont = info.lfCaptionFont;
                        hCurrentFont = OS.CreateFontIndirect(logFont);
                    }
                }
                if (hCurrentFont != 0L) {
                    oldFont = OS.SelectObject(hDC, hCurrentFont);
                }
            }
            height += this.spacing;
            for (int i = 0; i < this.itemCount; ++i) {
                final ExpandItem item = this.items[i];
                height += item.getHeaderHeightInPixels();
                if (item.expanded) {
                    height += item.height;
                }
                height += this.spacing;
                width = Math.max(width, item.getPreferredWidth(hTheme, hDC));
            }
            if (hCurrentFont != 0L) {
                OS.SelectObject(hDC, oldFont);
                if (hCurrentFont != this.hFont) {
                    OS.DeleteObject(hCurrentFont);
                }
            }
            OS.ReleaseDC(this.handle, hDC);
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
        final Rectangle trim = this.computeTrimInPixels(0, 0, width, height);
        return new Point(trim.width, trim.height);
    }
    
    void createHandle() {
        super.createHandle();
        this.state &= 0xFFFFFFFD;
        this.state |= 0x2000;
    }
    
    void createItem(final ExpandItem item, final int style, final int index) {
        if (0 > index || index > this.itemCount) {
            this.error(6);
        }
        if (this.itemCount == this.items.length) {
            final ExpandItem[] newItems = new ExpandItem[this.itemCount + 4];
            System.arraycopy(this.items, 0, newItems, 0, this.items.length);
            this.items = newItems;
        }
        System.arraycopy(this.items, index, this.items, index + 1, this.itemCount - index);
        this.items[index] = item;
        ++this.itemCount;
        if (this.focusItem == null) {
            this.focusItem = item;
        }
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        item.width = Math.max(0, rect.right - rect.left - this.spacing * 2);
        this.layoutItems(index, true);
    }
    
    void createWidget() {
        super.createWidget();
        this.items = new ExpandItem[4];
        if (!this.isAppThemed()) {
            this.backgroundMode = 1;
        }
    }
    
    int defaultBackground() {
        if (!this.isAppThemed()) {
            return OS.GetSysColor(5);
        }
        return super.defaultBackground();
    }
    
    void destroyItem(final ExpandItem item) {
        int index;
        for (index = 0; index < this.itemCount && this.items[index] != item; ++index) {}
        if (index == this.itemCount) {
            return;
        }
        if (item == this.focusItem) {
            final int focusIndex = (index > 0) ? (index - 1) : 1;
            if (focusIndex < this.itemCount) {
                (this.focusItem = this.items[focusIndex]).redraw(true);
            }
            else {
                this.focusItem = null;
            }
        }
        System.arraycopy(this.items, index + 1, this.items, index, --this.itemCount - index);
        this.items[this.itemCount] = null;
        item.redraw(true);
        this.layoutItems(index, true);
    }
    
    void drawThemeBackground(final long hDC, final long hwnd, final RECT rect) {
        final RECT rect2 = new RECT();
        OS.GetClientRect(this.handle, rect2);
        OS.MapWindowPoints(this.handle, hwnd, rect2, 2);
        OS.DrawThemeBackground(this.display.hExplorerBarTheme(), hDC, 5, 0, rect2, (RECT)null);
    }
    
    void drawWidget(final GC gc, final RECT clipRect) {
        long hTheme = 0L;
        if (this.isAppThemed()) {
            hTheme = this.display.hExplorerBarTheme();
        }
        if (hTheme != 0L) {
            final RECT rect = new RECT();
            OS.GetClientRect(this.handle, rect);
            OS.DrawThemeBackground(hTheme, gc.handle, 1, 0, rect, clipRect);
        }
        else {
            this.drawBackground(gc.handle);
        }
        boolean drawFocus = false;
        if (this.handle == OS.GetFocus()) {
            final int uiState = (int)OS.SendMessage(this.handle, 297, 0L, 0L);
            drawFocus = ((uiState & 0x1) == 0x0);
        }
        long hCurrentFont = 0L;
        long oldFont = 0L;
        if (hTheme == 0L) {
            if (this.hFont != 0L) {
                hCurrentFont = this.hFont;
            }
            else {
                final NONCLIENTMETRICS info = new NONCLIENTMETRICS();
                info.cbSize = NONCLIENTMETRICS.sizeof;
                if (OS.SystemParametersInfo(41, 0, info, 0)) {
                    final LOGFONT logFont = info.lfCaptionFont;
                    hCurrentFont = OS.CreateFontIndirect(logFont);
                }
            }
            if (hCurrentFont != 0L) {
                oldFont = OS.SelectObject(gc.handle, hCurrentFont);
            }
            if (this.foreground != -1) {
                OS.SetTextColor(gc.handle, this.foreground);
            }
        }
        for (int i = 0; i < this.itemCount; ++i) {
            final ExpandItem item = this.items[i];
            item.drawItem(gc, hTheme, clipRect, item == this.focusItem && drawFocus);
        }
        if (hCurrentFont != 0L) {
            OS.SelectObject(gc.handle, oldFont);
            if (hCurrentFont != this.hFont) {
                OS.DeleteObject(hCurrentFont);
            }
        }
    }
    
    Control findBackgroundControl() {
        Control control = super.findBackgroundControl();
        if (!this.isAppThemed() && control == null) {
            control = (Control)this;
        }
        return control;
    }
    
    Control findThemeControl() {
        return (Control)(this.isAppThemed() ? this : super.findThemeControl());
    }
    
    int getBandHeight() {
        final long hDC = OS.GetDC(this.handle);
        final long oldHFont = OS.SelectObject(hDC, (this.hFont == 0L) ? this.defaultFont() : this.hFont);
        final TEXTMETRIC lptm = new TEXTMETRIC();
        OS.GetTextMetrics(hDC, lptm);
        OS.SelectObject(hDC, oldHFont);
        OS.ReleaseDC(this.handle, hDC);
        return Math.max(24, lptm.tmHeight + 4);
    }
    
    public ExpandItem getItem(final int index) {
        this.checkWidget();
        if (0 > index || index >= this.itemCount) {
            this.error(6);
        }
        return this.items[index];
    }
    
    public int getItemCount() {
        this.checkWidget();
        return this.itemCount;
    }
    
    public ExpandItem[] getItems() {
        this.checkWidget();
        final ExpandItem[] result = new ExpandItem[this.itemCount];
        System.arraycopy(this.items, 0, result, 0, this.itemCount);
        return result;
    }
    
    public int getSpacing() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getSpacingInPixels());
    }
    
    int getSpacingInPixels() {
        return this.spacing;
    }
    
    public int indexOf(final ExpandItem item) {
        this.checkWidget();
        if (item == null) {
            this.error(4);
        }
        for (int i = 0; i < this.itemCount; ++i) {
            if (this.items[i] == item) {
                return i;
            }
        }
        return -1;
    }
    
    boolean isAppThemed() {
        return this.background == -1 && this.foreground == -1 && this.hFont == 0L && OS.IsAppThemed();
    }
    
    void layoutItems(final int index, final boolean setScrollbar) {
        if (index < this.itemCount) {
            int y = this.spacing - this.yCurrentScroll;
            for (int i = 0; i < index; ++i) {
                final ExpandItem item = this.items[i];
                if (item.expanded) {
                    y += item.height;
                }
                y += item.getHeaderHeightInPixels() + this.spacing;
            }
            for (int i = index; i < this.itemCount; ++i) {
                final ExpandItem item = this.items[i];
                item.setBoundsInPixels(this.spacing, y, 0, 0, true, false);
                if (item.expanded) {
                    y += item.height;
                }
                y += item.getHeaderHeightInPixels() + this.spacing;
            }
        }
        if (setScrollbar) {
            this.setScrollbar();
        }
    }
    
    void releaseChildren(final boolean destroy) {
        if (this.items != null) {
            for (final ExpandItem item : this.items) {
                if (item != null && !item.isDisposed()) {
                    item.release(false);
                }
            }
            this.items = null;
        }
        this.focusItem = null;
        super.releaseChildren(destroy);
    }
    
    public void removeExpandListener(final ExpandListener listener) {
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
            for (final ExpandItem item : this.items) {
                if (item != null) {
                    item.reskin(flags);
                }
            }
        }
        super.reskinChildren(flags);
    }
    
    void setBackgroundPixel(final int pixel) {
        super.setBackgroundPixel(pixel);
        final int flags = 1157;
        OS.RedrawWindow(this.handle, (RECT)null, 0L, 1157);
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.hFont = ((font != null) ? font.handle : 0L);
        this.layoutItems(0, true);
    }
    
    void setForegroundPixel(final int pixel) {
        super.setForegroundPixel(pixel);
        final int flags = 1157;
        OS.RedrawWindow(this.handle, (RECT)null, 0L, 1157);
    }
    
    void setScrollbar() {
        if (this.itemCount == 0) {
            return;
        }
        if ((this.style & 0x200) == 0x0) {
            return;
        }
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        final int height = rect.bottom - rect.top;
        final ExpandItem item = this.items[this.itemCount - 1];
        int maxHeight = item.y + this.getBandHeight() + this.spacing;
        if (item.expanded) {
            maxHeight += item.height;
        }
        if (this.yCurrentScroll > 0 && height > maxHeight) {
            this.yCurrentScroll = Math.max(0, this.yCurrentScroll + maxHeight - height);
            this.layoutItems(0, false);
        }
        maxHeight += this.yCurrentScroll;
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        info.fMask = 7;
        info.nMin = 0;
        info.nMax = maxHeight;
        info.nPage = height;
        info.nPos = Math.min(this.yCurrentScroll, info.nMax);
        if (info.nPage != 0) {
            final SCROLLINFO scrollinfo2;
            final SCROLLINFO scrollinfo = scrollinfo2 = info;
            ++scrollinfo2.nPage;
        }
        OS.SetScrollInfo(this.handle, 1, info, true);
    }
    
    public void setSpacing(final int spacing) {
        this.checkWidget();
        this.setSpacingInPixels(DPIUtil.autoScaleUp(spacing));
    }
    
    void setSpacingInPixels(final int spacing) {
        if (spacing < 0) {
            return;
        }
        if (spacing == this.spacing) {
            return;
        }
        this.spacing = spacing;
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        final int width = Math.max(0, rect.right - rect.left - spacing * 2);
        for (int i = 0; i < this.itemCount; ++i) {
            final ExpandItem item = this.items[i];
            if (item.width != width) {
                item.setBoundsInPixels(0, 0, width, item.height, false, true);
            }
        }
        this.layoutItems(0, true);
        OS.InvalidateRect(this.handle, (RECT)null, true);
    }
    
    boolean updateTextDirection(final int textDirection) {
        if (super.updateTextDirection(textDirection)) {
            for (final ExpandItem item : this.items) {
                if (item != null) {
                    item.updateTextDirection((textDirection == 100663296) ? 100663296 : (this.style & Integer.MIN_VALUE));
                }
            }
            return true;
        }
        return false;
    }
    
    void showItem(final ExpandItem item) {
        final Control control = item.control;
        if (control != null && !control.isDisposed()) {
            control.setVisible(item.expanded);
        }
        item.redraw(true);
        final int index = this.indexOf(item);
        this.layoutItems(index + 1, true);
    }
    
    void showFocus(final boolean up) {
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        final int height = rect.bottom - rect.top;
        int updateY = 0;
        if (up) {
            if (this.focusItem.y < 0) {
                updateY = Math.min(this.yCurrentScroll, -this.focusItem.y);
            }
        }
        else {
            int itemHeight = this.focusItem.y + this.getBandHeight();
            if (this.focusItem.expanded && height >= this.getBandHeight() + this.focusItem.height) {
                itemHeight += this.focusItem.height;
            }
            if (itemHeight > height) {
                updateY = height - itemHeight;
            }
        }
        if (updateY != 0) {
            this.yCurrentScroll = Math.max(0, this.yCurrentScroll - updateY);
            if ((this.style & 0x200) != 0x0) {
                final SCROLLINFO info = new SCROLLINFO();
                info.cbSize = SCROLLINFO.sizeof;
                info.fMask = 4;
                info.nPos = this.yCurrentScroll;
                OS.SetScrollInfo(this.handle, 1, info, true);
            }
            OS.ScrollWindowEx(this.handle, 0, updateY, (RECT)null, (RECT)null, 0L, (RECT)null, 3);
            for (int i = 0; i < this.itemCount; ++i) {
                final ExpandItem expandItem2;
                final ExpandItem expandItem = expandItem2 = this.items[i];
                expandItem2.y += updateY;
            }
        }
    }
    
    TCHAR windowClass() {
        return this.display.windowClass;
    }
    
    long windowProc() {
        return this.display.windowProc;
    }
    
    LRESULT WM_KEYDOWN(final long wParam, final long lParam) {
        final LRESULT result = super.WM_KEYDOWN(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (this.focusItem == null) {
            return result;
        }
        switch ((int)wParam) {
            case 13:
            case 32: {
                final Event event = new Event();
                event.item = this.focusItem;
                this.sendEvent(this.focusItem.expanded ? 18 : 17, event);
                this.focusItem.expanded = !this.focusItem.expanded;
                this.showItem(this.focusItem);
                return LRESULT.ZERO;
            }
            case 38: {
                final int focusIndex = this.indexOf(this.focusItem);
                if (focusIndex > 0) {
                    this.focusItem.redraw(true);
                    (this.focusItem = this.items[focusIndex - 1]).redraw(true);
                    this.showFocus(true);
                    return LRESULT.ZERO;
                }
                break;
            }
            case 40: {
                final int focusIndex = this.indexOf(this.focusItem);
                if (focusIndex < this.itemCount - 1) {
                    this.focusItem.redraw(true);
                    (this.focusItem = this.items[focusIndex + 1]).redraw(true);
                    this.showFocus(false);
                    return LRESULT.ZERO;
                }
                break;
            }
        }
        return result;
    }
    
    LRESULT WM_KILLFOCUS(final long wParam, final long lParam) {
        final LRESULT result = super.WM_KILLFOCUS(wParam, lParam);
        if (this.focusItem != null) {
            this.focusItem.redraw(true);
        }
        return result;
    }
    
    LRESULT WM_LBUTTONDOWN(final long wParam, final long lParam) {
        final LRESULT result = super.WM_LBUTTONDOWN(wParam, lParam);
        if (result == LRESULT.ZERO) {
            return result;
        }
        final int x = OS.GET_X_LPARAM(lParam);
        final int y = OS.GET_Y_LPARAM(lParam);
        for (int i = 0; i < this.itemCount; ++i) {
            final ExpandItem item = this.items[i];
            final boolean hover = item.isHover(x, y);
            if (hover && this.focusItem != item) {
                this.focusItem.redraw(true);
                (this.focusItem = item).redraw(true);
                this.forceFocus();
                break;
            }
        }
        return result;
    }
    
    LRESULT WM_LBUTTONUP(final long wParam, final long lParam) {
        final LRESULT result = super.WM_LBUTTONUP(wParam, lParam);
        if (result == LRESULT.ZERO) {
            return result;
        }
        if (this.focusItem == null) {
            return result;
        }
        final int x = OS.GET_X_LPARAM(lParam);
        final int y = OS.GET_Y_LPARAM(lParam);
        final boolean hover = this.focusItem.isHover(x, y);
        if (hover) {
            final Event event = new Event();
            event.item = this.focusItem;
            this.sendEvent(this.focusItem.expanded ? 18 : 17, event);
            this.focusItem.expanded = !this.focusItem.expanded;
            this.showItem(this.focusItem);
        }
        return result;
    }
    
    LRESULT WM_MOUSELEAVE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_MOUSELEAVE(wParam, lParam);
        if (result != null) {
            return result;
        }
        for (int i = 0; i < this.itemCount; ++i) {
            final ExpandItem item = this.items[i];
            if (item.hover) {
                item.redraw(item.hover = false);
                break;
            }
        }
        return result;
    }
    
    LRESULT WM_MOUSEMOVE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_MOUSEMOVE(wParam, lParam);
        if (result == LRESULT.ZERO) {
            return result;
        }
        final int x = OS.GET_X_LPARAM(lParam);
        final int y = OS.GET_Y_LPARAM(lParam);
        for (int i = 0; i < this.itemCount; ++i) {
            final ExpandItem item = this.items[i];
            final boolean hover = item.isHover(x, y);
            if (item.hover != hover) {
                item.hover = hover;
                item.redraw(false);
            }
        }
        return result;
    }
    
    LRESULT WM_MOUSEWHEEL(final long wParam, final long lParam) {
        return this.wmScrollWheel(true, wParam, lParam, false);
    }
    
    LRESULT WM_PAINT(final long wParam, final long lParam) {
        if ((this.state & 0x1000) != 0x0) {
            return LRESULT.ZERO;
        }
        final PAINTSTRUCT ps = new PAINTSTRUCT();
        final GCData data = new GCData();
        data.ps = ps;
        data.hwnd = this.handle;
        final GC gc = this.new_GC(data);
        if (gc != null) {
            final int width = ps.right - ps.left;
            final int height = ps.bottom - ps.top;
            if (width != 0 && height != 0) {
                final RECT rect = new RECT();
                OS.SetRect(rect, ps.left, ps.top, ps.right, ps.bottom);
                this.drawWidget(gc, rect);
                if (this.hooks(9) || this.filters(9)) {
                    final Event event = new Event();
                    event.gc = gc;
                    event.setBoundsInPixels(new Rectangle(rect.left, rect.top, width, height));
                    this.sendEvent(9, event);
                    event.gc = null;
                }
            }
            gc.dispose();
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_PRINTCLIENT(final long wParam, final long lParam) {
        final LRESULT result = super.WM_PRINTCLIENT(wParam, lParam);
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        final GCData data = new GCData();
        data.device = (Device)this.display;
        data.foreground = this.getForegroundPixel();
        final GC gc = GC.win32_new(wParam, data);
        this.drawWidget(gc, rect);
        gc.dispose();
        return result;
    }
    
    LRESULT WM_SETCURSOR(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SETCURSOR(wParam, lParam);
        if (result != null) {
            return result;
        }
        final int hitTest = (short)OS.LOWORD(lParam);
        if (hitTest == 1) {
            for (int i = 0; i < this.itemCount; ++i) {
                final ExpandItem item = this.items[i];
                if (item.hover) {
                    final long hCursor = OS.LoadCursor(0L, 32649L);
                    OS.SetCursor(hCursor);
                    return LRESULT.ONE;
                }
            }
        }
        return result;
    }
    
    LRESULT WM_SETFOCUS(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SETFOCUS(wParam, lParam);
        if (this.focusItem != null) {
            this.focusItem.redraw(true);
        }
        return result;
    }
    
    LRESULT WM_SIZE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SIZE(wParam, lParam);
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        final int width = Math.max(0, rect.right - rect.left - this.spacing * 2);
        for (int i = 0; i < this.itemCount; ++i) {
            final ExpandItem item = this.items[i];
            if (item.width != width) {
                item.setBoundsInPixels(0, 0, width, item.height, false, true);
            }
        }
        this.setScrollbar();
        OS.InvalidateRect(this.handle, (RECT)null, true);
        return result;
    }
    
    LRESULT wmScroll(final ScrollBar bar, final boolean update, final long hwnd, final int msg, final long wParam, final long lParam) {
        final LRESULT result = super.wmScroll(bar, true, hwnd, msg, wParam, lParam);
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        info.fMask = 4;
        OS.GetScrollInfo(this.handle, 1, info);
        final int updateY = this.yCurrentScroll - info.nPos;
        OS.ScrollWindowEx(this.handle, 0, updateY, (RECT)null, (RECT)null, 0L, (RECT)null, 3);
        this.yCurrentScroll = info.nPos;
        if (updateY != 0) {
            for (int i = 0; i < this.itemCount; ++i) {
                final ExpandItem expandItem2;
                final ExpandItem expandItem = expandItem2 = this.items[i];
                expandItem2.y += updateY;
            }
        }
        return result;
    }
}
