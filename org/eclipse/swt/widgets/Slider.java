//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class Slider extends Control
{
    int increment;
    int pageIncrement;
    boolean ignoreFocus;
    static final long ScrollBarProc;
    static final TCHAR ScrollBarClass;
    
    public Slider(final Composite parent, final int style) {
        super(parent, checkStyle(style));
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
    
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        switch (msg) {
            case 513:
            case 515: {
                this.display.runDeferredEvents();
                break;
            }
        }
        return OS.CallWindowProc(Slider.ScrollBarProc, hwnd, msg, wParam, lParam);
    }
    
    static int checkStyle(final int style) {
        return Widget.checkBits(style, 256, 512, 0, 0, 0, 0);
    }
    
    Point computeSizeInPixels(final int wHint, final int hHint, final boolean changed) {
        this.checkWidget();
        final int border = this.getBorderWidthInPixels();
        int width = border * 2;
        int height = border * 2;
        if ((this.style & 0x100) != 0x0) {
            width += OS.GetSystemMetrics(21) * 10;
            height += OS.GetSystemMetrics(3);
        }
        else {
            width += OS.GetSystemMetrics(2);
            height += OS.GetSystemMetrics(20) * 10;
        }
        if (wHint != -1) {
            width = wHint + border * 2;
        }
        if (hHint != -1) {
            height = hHint + border * 2;
        }
        return new Point(width, height);
    }
    
    void createHandle() {
        super.createHandle();
        this.maybeEnableDarkSystemTheme();
    }
    
    void createWidget() {
        super.createWidget();
        this.increment = 1;
        this.pageIncrement = 10;
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        info.fMask = 23;
        info.nMax = 100;
        info.nPage = 11;
        OS.SetScrollInfo(this.handle, 2, info, true);
    }
    
    int defaultBackground() {
        return OS.GetSysColor(0);
    }
    
    int defaultForeground() {
        return OS.GetSysColor(15);
    }
    
    void enableWidget(final boolean enabled) {
        super.enableWidget(enabled);
        final int flags = enabled ? 0 : 3;
        OS.EnableScrollBar(this.handle, 2, flags);
        if (enabled) {
            this.state &= 0xFFFFFFF7;
        }
        else {
            this.state |= 0x8;
        }
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
        OS.GetScrollInfo(this.handle, 2, info);
        return info.nMax;
    }
    
    public int getMinimum() {
        this.checkWidget();
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        info.fMask = 1;
        OS.GetScrollInfo(this.handle, 2, info);
        return info.nMin;
    }
    
    public int getPageIncrement() {
        this.checkWidget();
        return this.pageIncrement;
    }
    
    public int getSelection() {
        this.checkWidget();
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        info.fMask = 4;
        OS.GetScrollInfo(this.handle, 2, info);
        return info.nPos;
    }
    
    public int getThumb() {
        this.checkWidget();
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        info.fMask = 2;
        OS.GetScrollInfo(this.handle, 2, info);
        if (info.nPage != 0) {
            final SCROLLINFO scrollinfo2;
            final SCROLLINFO scrollinfo = scrollinfo2 = info;
            --scrollinfo2.nPage;
        }
        return info.nPage;
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
    
    void setBoundsInPixels(final int x, final int y, final int width, final int height, final int flags) {
        super.setBoundsInPixels(x, y, width, height, flags);
        if (OS.GetFocus() == this.handle) {
            this.ignoreFocus = true;
            OS.SendMessage(this.handle, 7, 0L, 0L);
            this.ignoreFocus = false;
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
        info.fMask = 9;
        OS.GetScrollInfo(this.handle, 2, info);
        if (value - info.nMin - info.nPage < 1) {
            return;
        }
        info.nMax = value;
        this.SetScrollInfo(this.handle, 2, info, true);
    }
    
    public void setMinimum(final int value) {
        this.checkWidget();
        if (value < 0) {
            return;
        }
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        info.fMask = 9;
        OS.GetScrollInfo(this.handle, 2, info);
        if (info.nMax - value - info.nPage < 1) {
            return;
        }
        info.nMin = value;
        this.SetScrollInfo(this.handle, 2, info, true);
    }
    
    public void setPageIncrement(final int value) {
        this.checkWidget();
        if (value < 1) {
            return;
        }
        this.pageIncrement = value;
    }
    
    boolean SetScrollInfo(final long hwnd, final int flags, final SCROLLINFO info, boolean fRedraw) {
        if ((this.state & 0x8) != 0x0) {
            fRedraw = false;
        }
        final boolean result = OS.SetScrollInfo(hwnd, flags, info, fRedraw);
        if ((this.state & 0x8) != 0x0) {
            OS.EnableWindow(this.handle, false);
            OS.EnableScrollBar(this.handle, 2, 3);
        }
        if (OS.GetFocus() == this.handle) {
            this.ignoreFocus = true;
            OS.SendMessage(this.handle, 7, 0L, 0L);
            this.ignoreFocus = false;
        }
        return result;
    }
    
    public void setSelection(final int value) {
        this.checkWidget();
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        info.fMask = 4;
        info.nPos = value;
        this.SetScrollInfo(this.handle, 2, info, true);
    }
    
    public void setThumb(final int value) {
        this.checkWidget();
        if (value < 1) {
            return;
        }
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        info.fMask = 11;
        OS.GetScrollInfo(this.handle, 2, info);
        info.nPage = value;
        if (info.nPage != 0) {
            final SCROLLINFO scrollinfo2;
            final SCROLLINFO scrollinfo = scrollinfo2 = info;
            ++scrollinfo2.nPage;
        }
        this.SetScrollInfo(this.handle, 2, info, true);
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
        this.SetScrollInfo(this.handle, 2, info, true);
    }
    
    int widgetExtStyle() {
        int bits = super.widgetExtStyle();
        if ((this.style & 0x800) != 0x0) {
            bits &= 0xFFFFFDFF;
        }
        return bits;
    }
    
    int widgetStyle() {
        int bits = super.widgetStyle() | 0x10000;
        if ((this.style & 0x800) != 0x0) {
            bits &= 0xFF7FFFFF;
        }
        if ((this.style & 0x100) != 0x0) {
            return bits | 0x0;
        }
        return bits | 0x1;
    }
    
    TCHAR windowClass() {
        return Slider.ScrollBarClass;
    }
    
    long windowProc() {
        return Slider.ScrollBarProc;
    }
    
    LRESULT WM_KEYDOWN(final long wParam, final long lParam) {
        final LRESULT result = super.WM_KEYDOWN(wParam, lParam);
        if (result != null) {
            return result;
        }
        if ((this.style & 0x200) != 0x0) {
            return result;
        }
        if ((this.style & 0x8000000) != 0x0) {
            switch ((int)wParam) {
                case 37:
                case 39: {
                    final int key = (wParam == 37L) ? 39 : 37;
                    final long code = this.callWindowProc(this.handle, 256, key, lParam);
                    return new LRESULT(code);
                }
            }
        }
        return result;
    }
    
    LRESULT WM_LBUTTONDBLCLK(final long wParam, final long lParam) {
        final int oldBits = OS.GetWindowLong(this.handle, -16);
        final int newBits = oldBits & 0xFFFEFFFF;
        OS.SetWindowLong(this.handle, -16, newBits);
        final LRESULT result = super.WM_LBUTTONDBLCLK(wParam, lParam);
        if (this.isDisposed()) {
            return LRESULT.ZERO;
        }
        OS.SetWindowLong(this.handle, -16, oldBits);
        if (result == LRESULT.ZERO) {
            return result;
        }
        if (OS.GetCapture() == this.handle) {
            OS.ReleaseCapture();
        }
        if (!this.sendMouseEvent(4, 1, this.handle, lParam)) {
            return LRESULT.ZERO;
        }
        return result;
    }
    
    LRESULT WM_LBUTTONDOWN(final long wParam, final long lParam) {
        final int oldBits = OS.GetWindowLong(this.handle, -16);
        final int newBits = oldBits & 0xFFFEFFFF;
        OS.SetWindowLong(this.handle, -16, newBits);
        final LRESULT result = super.WM_LBUTTONDOWN(wParam, lParam);
        if (this.isDisposed()) {
            return LRESULT.ZERO;
        }
        OS.SetWindowLong(this.handle, -16, oldBits);
        if (result == LRESULT.ZERO) {
            return result;
        }
        if (OS.GetCapture() == this.handle) {
            OS.ReleaseCapture();
        }
        if (!this.sendMouseEvent(4, 1, this.handle, lParam)) {
            return LRESULT.ONE;
        }
        return result;
    }
    
    LRESULT WM_SETFOCUS(final long wParam, final long lParam) {
        if (this.ignoreFocus) {
            return null;
        }
        return super.WM_SETFOCUS(wParam, lParam);
    }
    
    LRESULT wmScrollChild(final long wParam, final long lParam) {
        final int code = OS.LOWORD(wParam);
        if (code == 8) {
            return null;
        }
        final Event event = new Event();
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        info.fMask = 21;
        OS.GetScrollInfo(this.handle, 2, info);
        info.fMask = 4;
        switch (code) {
            case 4: {
                event.detail = 0;
                info.nPos = info.nTrackPos;
                break;
            }
            case 5: {
                event.detail = 1;
                info.nPos = info.nTrackPos;
                break;
            }
            case 6: {
                event.detail = 16777223;
                info.nPos = info.nMin;
                break;
            }
            case 7: {
                event.detail = 16777224;
                info.nPos = info.nMax;
                break;
            }
            case 1: {
                event.detail = 16777218;
                final SCROLLINFO scrollinfo3;
                final SCROLLINFO scrollinfo = scrollinfo3 = info;
                scrollinfo3.nPos += this.increment;
                break;
            }
            case 0: {
                event.detail = 16777217;
                info.nPos = Math.max(info.nMin, info.nPos - this.increment);
                break;
            }
            case 3: {
                event.detail = 16777222;
                final SCROLLINFO scrollinfo4;
                final SCROLLINFO scrollinfo2 = scrollinfo4 = info;
                scrollinfo4.nPos += this.pageIncrement;
                break;
            }
            case 2: {
                event.detail = 16777221;
                info.nPos = Math.max(info.nMin, info.nPos - this.pageIncrement);
                break;
            }
        }
        OS.SetScrollInfo(this.handle, 2, info, true);
        this.sendSelectionEvent(13, event, true);
        return null;
    }
    
    static {
        ScrollBarClass = new TCHAR(0, "SCROLLBAR", true);
        final WNDCLASS lpWndClass = new WNDCLASS();
        OS.GetClassInfo(0L, Slider.ScrollBarClass, lpWndClass);
        ScrollBarProc = lpWndClass.lpfnWndProc;
    }
}
