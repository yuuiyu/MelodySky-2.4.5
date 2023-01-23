//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;

public class ScrollBar extends Widget
{
    Scrollable parent;
    int increment;
    int pageIncrement;
    
    ScrollBar(final Scrollable parent, final int style) {
        super((Widget)parent, checkStyle(style));
        this.parent = parent;
        this.createWidget();
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
        return Widget.checkBits(style, 256, 512, 0, 0, 0, 0);
    }
    
    void createWidget() {
        this.increment = 1;
        this.pageIncrement = 10;
    }
    
    @Override
    void destroyWidget() {
        final long hwnd = this.hwndScrollBar();
        final int type = this.scrollBarType();
        OS.ShowScrollBar(hwnd, type, false);
        this.parent.destroyScrollBar(this.style);
        this.releaseHandle();
    }
    
    Rectangle getBounds() {
        this.parent.forceResize();
        final RECT rect = new RECT();
        OS.GetClientRect(this.parent.scrolledHandle(), rect);
        int x = 0;
        int y = 0;
        int width;
        int height;
        if ((this.style & 0x100) != 0x0) {
            y = rect.bottom - rect.top;
            width = rect.right - rect.left;
            height = OS.GetSystemMetrics(3);
        }
        else {
            x = rect.right - rect.left;
            width = OS.GetSystemMetrics(2);
            height = rect.bottom - rect.top;
        }
        return new Rectangle(x, y, width, height);
    }
    
    public boolean getEnabled() {
        this.checkWidget();
        return (this.state & 0x8) == 0x0;
    }
    
    public int getIncrement() {
        this.checkWidget();
        return this.increment;
    }
    
    public int getMaximum() {
        this.checkWidget();
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        info.fMask = 1;
        final long hwnd = this.hwndScrollBar();
        final int type = this.scrollBarType();
        OS.GetScrollInfo(hwnd, type, info);
        return info.nMax;
    }
    
    public int getMinimum() {
        this.checkWidget();
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        info.fMask = 1;
        final long hwnd = this.hwndScrollBar();
        final int type = this.scrollBarType();
        OS.GetScrollInfo(hwnd, type, info);
        return info.nMin;
    }
    
    public int getPageIncrement() {
        this.checkWidget();
        return this.pageIncrement;
    }
    
    public Scrollable getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public int getSelection() {
        this.checkWidget();
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        info.fMask = 4;
        final long hwnd = this.hwndScrollBar();
        final int type = this.scrollBarType();
        OS.GetScrollInfo(hwnd, type, info);
        return info.nPos;
    }
    
    public Point getSize() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getSizeInPixels());
    }
    
    Point getSizeInPixels() {
        this.parent.forceResize();
        final RECT rect = new RECT();
        OS.GetClientRect(this.parent.scrolledHandle(), rect);
        int width;
        int height;
        if ((this.style & 0x100) != 0x0) {
            width = rect.right - rect.left;
            height = OS.GetSystemMetrics(3);
        }
        else {
            width = OS.GetSystemMetrics(2);
            height = rect.bottom - rect.top;
        }
        return new Point(width, height);
    }
    
    public int getThumb() {
        this.checkWidget();
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        info.fMask = 2;
        final long hwnd = this.hwndScrollBar();
        final int type = this.scrollBarType();
        OS.GetScrollInfo(hwnd, type, info);
        if (info.nPage != 0) {
            final SCROLLINFO scrollinfo2;
            final SCROLLINFO scrollinfo = scrollinfo2 = info;
            --scrollinfo2.nPage;
        }
        return info.nPage;
    }
    
    public Rectangle getThumbBounds() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getThumbBoundsInPixels());
    }
    
    Rectangle getThumbBoundsInPixels() {
        this.parent.forceResize();
        final SCROLLBARINFO info = new SCROLLBARINFO();
        info.cbSize = SCROLLBARINFO.sizeof;
        int x;
        int y;
        int width;
        int height;
        if ((this.style & 0x100) != 0x0) {
            OS.GetScrollBarInfo(this.parent.handle, -6, info);
            x = info.rcScrollBar.left + info.xyThumbTop;
            y = info.rcScrollBar.top;
            width = info.xyThumbBottom - info.xyThumbTop;
            height = info.rcScrollBar.bottom - info.rcScrollBar.top;
        }
        else {
            OS.GetScrollBarInfo(this.parent.handle, -5, info);
            x = info.rcScrollBar.left;
            y = info.rcScrollBar.top + info.xyThumbTop;
            width = info.rcScrollBar.right - info.rcScrollBar.left;
            height = info.xyThumbBottom - info.xyThumbTop;
        }
        final RECT rect = new RECT();
        rect.left = x;
        rect.top = y;
        rect.right = x + width;
        rect.bottom = y + height;
        OS.MapWindowPoints(0L, this.parent.handle, rect, 2);
        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }
    
    public Rectangle getThumbTrackBounds() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getThumbTrackBoundsInPixels());
    }
    
    Rectangle getThumbTrackBoundsInPixels() {
        this.parent.forceResize();
        final SCROLLBARINFO info = new SCROLLBARINFO();
        info.cbSize = SCROLLBARINFO.sizeof;
        int x = 0;
        int y = 0;
        int width;
        int height;
        if ((this.style & 0x100) != 0x0) {
            OS.GetScrollBarInfo(this.parent.handle, -6, info);
            final int size = OS.GetSystemMetrics(3);
            y = info.rcScrollBar.top;
            width = info.rcScrollBar.right - info.rcScrollBar.left;
            height = size;
            if (width <= 2 * size) {
                x = info.rcScrollBar.left + width / 2;
                width = 0;
            }
            else {
                x = info.rcScrollBar.left + size;
                width -= 2 * size;
            }
        }
        else {
            OS.GetScrollBarInfo(this.parent.handle, -5, info);
            final int size = OS.GetSystemMetrics(20);
            x = info.rcScrollBar.left;
            width = size;
            height = info.rcScrollBar.bottom - info.rcScrollBar.top;
            if (height <= 2 * size) {
                y = info.rcScrollBar.top + height / 2;
                height = 0;
            }
            else {
                y = info.rcScrollBar.top + size;
                height -= 2 * size;
            }
        }
        final RECT rect = new RECT();
        rect.left = x;
        rect.top = y;
        rect.right = x + width;
        rect.bottom = y + height;
        OS.MapWindowPoints(0L, this.parent.handle, rect, 2);
        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }
    
    public boolean getVisible() {
        this.checkWidget();
        final SCROLLBARINFO psbi = new SCROLLBARINFO();
        psbi.cbSize = SCROLLBARINFO.sizeof;
        final int idObject = ((this.style & 0x200) != 0x0) ? -5 : -6;
        OS.GetScrollBarInfo(this.hwndScrollBar(), idObject, psbi);
        return (psbi.rgstate[0] & 0x8000) == 0x0;
    }
    
    long hwndScrollBar() {
        return this.parent.scrolledHandle();
    }
    
    public boolean isEnabled() {
        this.checkWidget();
        return this.getEnabled() && this.parent.isEnabled();
    }
    
    public boolean isVisible() {
        this.checkWidget();
        return this.getVisible() && this.parent.isVisible();
    }
    
    @Override
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
    }
    
    @Override
    void releaseParent() {
        super.releaseParent();
        if (this.parent.horizontalBar == this) {
            this.parent.horizontalBar = null;
        }
        if (this.parent.verticalBar == this) {
            this.parent.verticalBar = null;
        }
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
    
    int scrollBarType() {
        return ((this.style & 0x200) != 0x0) ? 1 : 0;
    }
    
    public void setEnabled(final boolean enabled) {
        this.checkWidget();
        final long hwnd = this.hwndScrollBar();
        final int type = this.scrollBarType();
        final int flags = enabled ? 0 : 3;
        OS.EnableScrollBar(hwnd, type, flags);
        if (enabled) {
            this.state &= 0xFFFFFFF7;
        }
        else {
            this.state |= 0x8;
        }
    }
    
    public void setIncrement(final int value) {
        this.checkWidget();
        if (value < 1) {
            return;
        }
        this.increment = value;
    }
    
    public void setMaximum(final int value) {
        this.checkWidget();
        if (value < 0) {
            return;
        }
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        final long hwnd = this.hwndScrollBar();
        final int type = this.scrollBarType();
        info.fMask = 9;
        OS.GetScrollInfo(hwnd, type, info);
        if (value - info.nMin - info.nPage < 1) {
            return;
        }
        info.nMax = value;
        this.SetScrollInfo(hwnd, type, info, true);
    }
    
    public void setMinimum(final int value) {
        this.checkWidget();
        if (value < 0) {
            return;
        }
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        final long hwnd = this.hwndScrollBar();
        final int type = this.scrollBarType();
        info.fMask = 9;
        OS.GetScrollInfo(hwnd, type, info);
        if (info.nMax - value - info.nPage < 1) {
            return;
        }
        info.nMin = value;
        this.SetScrollInfo(hwnd, type, info, true);
    }
    
    public void setPageIncrement(final int value) {
        this.checkWidget();
        if (value < 1) {
            return;
        }
        this.pageIncrement = value;
    }
    
    boolean SetScrollInfo(final long hwnd, final int flags, final SCROLLINFO info, boolean fRedraw) {
        boolean barVisible = false;
        final boolean visible = this.getVisible();
        ScrollBar bar = null;
        switch (flags) {
            case 0: {
                bar = this.parent.getVerticalBar();
                break;
            }
            case 1: {
                bar = this.parent.getHorizontalBar();
                break;
            }
        }
        barVisible = (bar != null && bar.getVisible());
        if (!visible || (this.state & 0x8) != 0x0) {
            fRedraw = false;
        }
        final boolean result = OS.SetScrollInfo(hwnd, flags, info, fRedraw);
        if (!visible) {
            OS.ShowScrollBar(hwnd, barVisible ? flags : 3, false);
        }
        if (visible && bar != null && !barVisible) {
            OS.ShowScrollBar(hwnd, (int)((flags == 0) ? 1 : 0), false);
        }
        if ((this.state & 0x8) != 0x0) {
            OS.EnableScrollBar(hwnd, flags, 3);
        }
        return result;
    }
    
    public void setSelection(final int selection) {
        this.checkWidget();
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        final long hwnd = this.hwndScrollBar();
        final int type = this.scrollBarType();
        info.fMask = 4;
        info.nPos = selection;
        this.SetScrollInfo(hwnd, type, info, true);
    }
    
    public void setThumb(final int value) {
        this.checkWidget();
        if (value < 1) {
            return;
        }
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        final long hwnd = this.hwndScrollBar();
        final int type = this.scrollBarType();
        info.fMask = 11;
        OS.GetScrollInfo(hwnd, type, info);
        info.nPage = value;
        if (info.nPage != 0) {
            final SCROLLINFO scrollinfo2;
            final SCROLLINFO scrollinfo = scrollinfo2 = info;
            ++scrollinfo2.nPage;
        }
        this.SetScrollInfo(hwnd, type, info, true);
    }
    
    public void setValues(final int selection, final int minimum, final int maximum, final int thumb, final int increment, final int pageIncrement) {
        this.checkWidget();
        if (minimum < 0) {
            return;
        }
        if (maximum < 0) {
            return;
        }
        if (thumb < 1) {
            return;
        }
        if (increment < 1) {
            return;
        }
        if (pageIncrement < 1) {
            return;
        }
        this.increment = increment;
        this.pageIncrement = pageIncrement;
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        info.fMask = 15;
        info.nPos = selection;
        info.nMin = minimum;
        info.nMax = maximum;
        info.nPage = thumb;
        if (info.nPage != 0) {
            final SCROLLINFO scrollinfo2;
            final SCROLLINFO scrollinfo = scrollinfo2 = info;
            ++scrollinfo2.nPage;
        }
        final long hwnd = this.hwndScrollBar();
        final int type = this.scrollBarType();
        this.SetScrollInfo(hwnd, type, info, true);
    }
    
    public void setVisible(final boolean visible) {
        this.checkWidget();
        if (visible == this.getVisible()) {
            return;
        }
        this.state = (visible ? (this.state & 0xFFFFFFEF) : (this.state | 0x10));
        final long hwnd = this.hwndScrollBar();
        int type = this.scrollBarType();
        if (!visible && OS.IsAppThemed()) {
            final SCROLLBARINFO psbi = new SCROLLBARINFO();
            psbi.cbSize = SCROLLBARINFO.sizeof;
            final int idObject = ((this.style & 0x200) != 0x0) ? -6 : -5;
            OS.GetScrollBarInfo(hwnd, idObject, psbi);
            if ((psbi.rgstate[0] & 0x8000) != 0x0) {
                OS.ShowScrollBar(hwnd, (int)((type != 1) ? 1 : 0), true);
                type = 3;
            }
        }
        if (OS.ShowScrollBar(hwnd, type, visible)) {
            if ((this.state & 0x8) == 0x0) {
                final SCROLLINFO info = new SCROLLINFO();
                info.cbSize = SCROLLINFO.sizeof;
                info.fMask = 3;
                OS.GetScrollInfo(hwnd, type, info);
                if (info.nMax - info.nMin - info.nPage >= 0) {
                    OS.EnableScrollBar(hwnd, type, 0);
                }
            }
            this.sendEvent(visible ? 22 : 23);
        }
    }
    
    LRESULT wmScrollChild(final long wParam, final long lParam) {
        final int code = OS.LOWORD(wParam);
        if (code == 8) {
            return null;
        }
        final Event event = new Event();
        switch (code) {
            case 4: {
                event.detail = 0;
                break;
            }
            case 5: {
                event.detail = 1;
                break;
            }
            case 6: {
                event.detail = 16777223;
                break;
            }
            case 7: {
                event.detail = 16777224;
                break;
            }
            case 1: {
                event.detail = 16777218;
                break;
            }
            case 0: {
                event.detail = 16777217;
                break;
            }
            case 3: {
                event.detail = 16777222;
                break;
            }
            case 2: {
                event.detail = 16777221;
                break;
            }
        }
        this.sendSelectionEvent(13, event, true);
        return null;
    }
}
