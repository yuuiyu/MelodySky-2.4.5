//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import java.util.regex.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;

public abstract class Scrollable extends Control
{
    ScrollBar horizontalBar;
    ScrollBar verticalBar;
    static final Pattern CTRL_BS_PATTERN;
    
    Scrollable() {
    }
    
    public Scrollable(final Composite parent, final int style) {
        super(parent, style);
    }
    
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        return OS.DefWindowProc(hwnd, msg, wParam, lParam);
    }
    
    public Rectangle computeTrim(int x, int y, int width, int height) {
        this.checkWidget();
        x = DPIUtil.autoScaleUp(x);
        y = DPIUtil.autoScaleUp(y);
        width = DPIUtil.autoScaleUp(width);
        height = DPIUtil.autoScaleUp(height);
        return DPIUtil.autoScaleDown(this.computeTrimInPixels(x, y, width, height));
    }
    
    Rectangle computeTrimInPixels(final int x, final int y, final int width, final int height) {
        final long scrolledHandle = this.scrolledHandle();
        final RECT rect = new RECT();
        OS.SetRect(rect, x, y, x + width, y + height);
        final int bits1 = OS.GetWindowLong(scrolledHandle, -16);
        final int bits2 = OS.GetWindowLong(scrolledHandle, -20);
        OS.AdjustWindowRectEx(rect, bits1, false, bits2);
        if (this.horizontalBar != null) {
            final RECT rect4;
            final RECT rect2 = rect4 = rect;
            rect4.bottom += OS.GetSystemMetrics(3);
        }
        if (this.verticalBar != null) {
            final RECT rect5;
            final RECT rect3 = rect5 = rect;
            rect5.right += OS.GetSystemMetrics(2);
        }
        final int nWidth = rect.right - rect.left;
        final int nHeight = rect.bottom - rect.top;
        return new Rectangle(rect.left, rect.top, nWidth, nHeight);
    }
    
    void createHandle() {
        super.createHandle();
        this.maybeEnableDarkSystemTheme();
    }
    
    ScrollBar createScrollBar(final int type) {
        final ScrollBar bar = new ScrollBar(this, type);
        if ((this.state & 0x2) != 0x0) {
            bar.setMaximum(100);
            bar.setThumb(10);
        }
        return bar;
    }
    
    void createWidget() {
        super.createWidget();
        if ((this.style & 0x100) != 0x0) {
            this.horizontalBar = this.createScrollBar(256);
        }
        if ((this.style & 0x200) != 0x0) {
            this.verticalBar = this.createScrollBar(512);
        }
    }
    
    void updateBackgroundColor() {
        switch (this.applyThemeBackground()) {
            case 0: {
                this.state &= 0xFFFFFEFF;
                break;
            }
            case 1: {
                this.state |= 0x100;
                break;
            }
        }
        super.updateBackgroundColor();
    }
    
    int applyThemeBackground() {
        return (this.backgroundAlpha == 0) ? 1 : 0;
    }
    
    void destroyScrollBar(final int type) {
        final long hwnd = this.scrolledHandle();
        int bits = OS.GetWindowLong(hwnd, -16);
        if ((type & 0x100) != 0x0) {
            this.style &= 0xFFFFFEFF;
            bits &= 0xFFEFFFFF;
        }
        if ((type & 0x200) != 0x0) {
            this.style &= 0xFFFFFDFF;
            bits &= 0xFFDFFFFF;
        }
        OS.SetWindowLong(hwnd, -16, bits);
    }
    
    public Rectangle getClientArea() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getClientAreaInPixels());
    }
    
    Rectangle getClientAreaInPixels() {
        this.forceResize();
        final RECT rect = new RECT();
        final long scrolledHandle = this.scrolledHandle();
        OS.GetClientRect(scrolledHandle, rect);
        int x = rect.left;
        int y = rect.top;
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        if (scrolledHandle != this.handle) {
            OS.GetClientRect(this.handle, rect);
            OS.MapWindowPoints(this.handle, scrolledHandle, rect, 2);
            x = -rect.left;
            y = -rect.top;
        }
        return new Rectangle(x, y, width, height);
    }
    
    public ScrollBar getHorizontalBar() {
        this.checkWidget();
        return this.horizontalBar;
    }
    
    public int getScrollbarsMode() {
        this.checkWidget();
        return 0;
    }
    
    public ScrollBar getVerticalBar() {
        this.checkWidget();
        return this.verticalBar;
    }
    
    void releaseChildren(final boolean destroy) {
        if (this.horizontalBar != null) {
            this.horizontalBar.release(false);
            this.horizontalBar = null;
        }
        if (this.verticalBar != null) {
            this.verticalBar.release(false);
            this.verticalBar = null;
        }
        super.releaseChildren(destroy);
    }
    
    void reskinChildren(final int flags) {
        if (this.horizontalBar != null) {
            this.horizontalBar.reskin(flags);
        }
        if (this.verticalBar != null) {
            this.verticalBar.reskin(flags);
        }
        super.reskinChildren(flags);
    }
    
    long scrolledHandle() {
        return this.handle;
    }
    
    int widgetStyle() {
        int bits = super.widgetStyle() | 0x10000;
        if ((this.style & 0x100) != 0x0) {
            bits |= 0x100000;
        }
        if ((this.style & 0x200) != 0x0) {
            bits |= 0x200000;
        }
        return bits;
    }
    
    TCHAR windowClass() {
        return this.display.windowClass;
    }
    
    long windowProc() {
        return this.display.windowProc;
    }
    
    LRESULT WM_HSCROLL(final long wParam, final long lParam) {
        final LRESULT result = super.WM_HSCROLL(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (this.horizontalBar != null && lParam == 0L) {
            return this.wmScroll(this.horizontalBar, (this.state & 0x2) != 0x0, this.handle, 276, wParam, lParam);
        }
        return result;
    }
    
    LRESULT WM_MOUSEWHEEL(final long wParam, final long lParam) {
        return this.wmScrollWheel((this.state & 0x2) != 0x0, wParam, lParam, false);
    }
    
    LRESULT WM_MOUSEHWHEEL(final long wParam, final long lParam) {
        return this.wmScrollWheel((this.state & 0x2) != 0x0, -1L * wParam, lParam, true);
    }
    
    LRESULT WM_SIZE(final long wParam, final long lParam) {
        final long code = this.callWindowProc(this.handle, 5, wParam, lParam);
        super.WM_SIZE(wParam, lParam);
        if (code == 0L) {
            return LRESULT.ZERO;
        }
        return new LRESULT(code);
    }
    
    LRESULT WM_VSCROLL(final long wParam, final long lParam) {
        final LRESULT result = super.WM_VSCROLL(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (this.verticalBar != null && lParam == 0L) {
            return this.wmScroll(this.verticalBar, (this.state & 0x2) != 0x0, this.handle, 277, wParam, lParam);
        }
        return result;
    }
    
    LRESULT wmScrollWheel(final boolean update, final long wParam, final long lParam, boolean horzWheel) {
        final LRESULT result = super.WM_MOUSEWHEEL(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (!update) {
            final int vPosition = (this.verticalBar == null) ? 0 : this.verticalBar.getSelection();
            final int hPosition = (this.horizontalBar == null) ? 0 : this.horizontalBar.getSelection();
            final long code = this.callWindowProc(this.handle, 522, wParam, lParam);
            if (this.verticalBar != null) {
                final int position = this.verticalBar.getSelection();
                if (position != vPosition) {
                    final Event event = new Event();
                    event.detail = ((position < vPosition) ? 16777221 : 16777222);
                    this.verticalBar.sendSelectionEvent(13, event, true);
                }
            }
            if (this.horizontalBar != null) {
                final int position = this.horizontalBar.getSelection();
                if (position != hPosition) {
                    final Event event = new Event();
                    event.detail = ((position < hPosition) ? 16777221 : 16777222);
                    this.horizontalBar.sendSelectionEvent(13, event, true);
                }
            }
            return new LRESULT(code);
        }
        if ((wParam & 0x8L) != 0x0L) {
            return null;
        }
        if ((wParam & 0x4L) != 0x0L) {
            horzWheel = !horzWheel;
        }
        boolean vertical;
        if (this.verticalBar != null && this.verticalBar.getEnabled() && !horzWheel) {
            vertical = true;
        }
        else {
            if (this.horizontalBar == null || !this.horizontalBar.getEnabled() || !horzWheel) {
                return null;
            }
            vertical = false;
        }
        final ScrollBar bar = vertical ? this.verticalBar : this.horizontalBar;
        final Widget.MouseWheelData wheelData = (Widget)this.new MouseWheelData(vertical, bar, wParam, this.display.scrollRemainderBar);
        if (wheelData.count == 0) {
            return null;
        }
        final SCROLLINFO info = new SCROLLINFO();
        info.cbSize = SCROLLINFO.sizeof;
        info.fMask = 4;
        OS.GetScrollInfo(this.handle, bar.scrollBarType(), info);
        final SCROLLINFO scrollinfo2;
        final SCROLLINFO scrollinfo = scrollinfo2 = info;
        scrollinfo2.nPos -= wheelData.count;
        OS.SetScrollInfo(this.handle, bar.scrollBarType(), info, true);
        final int msg = vertical ? 277 : 276;
        OS.SendMessage(this.handle, msg, 4L, 0L);
        return LRESULT.ZERO;
    }
    
    LRESULT wmScroll(final ScrollBar bar, final boolean update, final long hwnd, final int msg, final long wParam, final long lParam) {
        LRESULT result = null;
        if (update) {
            final int type = (msg != 276) ? 1 : 0;
            final SCROLLINFO info = new SCROLLINFO();
            info.cbSize = SCROLLINFO.sizeof;
            info.fMask = 21;
            OS.GetScrollInfo(hwnd, type, info);
            info.fMask = 4;
            final int code = OS.LOWORD(wParam);
            switch (code) {
                case 8: {
                    return null;
                }
                case 4:
                case 5: {
                    info.nPos = info.nTrackPos;
                    break;
                }
                case 6: {
                    info.nPos = info.nMin;
                    break;
                }
                case 7: {
                    info.nPos = info.nMax;
                    break;
                }
                case 1: {
                    final SCROLLINFO scrollinfo3;
                    final SCROLLINFO scrollinfo = scrollinfo3 = info;
                    scrollinfo3.nPos += bar.getIncrement();
                    break;
                }
                case 0: {
                    final int increment = bar.getIncrement();
                    info.nPos = Math.max(info.nMin, info.nPos - increment);
                    break;
                }
                case 3: {
                    final SCROLLINFO scrollinfo4;
                    final SCROLLINFO scrollinfo2 = scrollinfo4 = info;
                    scrollinfo4.nPos += bar.getPageIncrement();
                    break;
                }
                case 2: {
                    final int pageIncrement = bar.getPageIncrement();
                    info.nPos = Math.max(info.nMin, info.nPos - pageIncrement);
                    break;
                }
            }
            OS.SetScrollInfo(hwnd, type, info, true);
        }
        else {
            final long code2 = this.callWindowProc(hwnd, msg, wParam, lParam);
            result = ((code2 == 0L) ? LRESULT.ZERO : new LRESULT(code2));
        }
        bar.wmScrollChild(wParam, lParam);
        return result;
    }
    
    static {
        CTRL_BS_PATTERN = Pattern.compile("\\r?\\n\\z|[\\p{Punct}]+[\\t ]*\\z|[^\\p{Punct}\\s\\n\\r]*[\\t ]*\\z");
    }
}
