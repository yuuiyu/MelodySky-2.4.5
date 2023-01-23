//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class Scale extends Control
{
    boolean ignoreResize;
    boolean ignoreSelection;
    static final long TrackBarProc;
    static final TCHAR TrackBarClass;
    boolean createdAsRTL;
    
    public Scale(final Composite parent, final int style) {
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
        return OS.CallWindowProc(Scale.TrackBarProc, hwnd, msg, wParam, lParam);
    }
    
    static int checkStyle(final int style) {
        return Widget.checkBits(style, 256, 512, 0, 0, 0, 0);
    }
    
    Point computeSizeInPixels(final int wHint, final int hHint, final boolean changed) {
        this.checkWidget();
        final int border = this.getBorderWidthInPixels();
        int width = border * 2;
        int height = border * 2;
        final RECT rect = new RECT();
        OS.SendMessage(this.handle, 1049, 0L, rect);
        if ((this.style & 0x100) != 0x0) {
            width += OS.GetSystemMetrics(21) * 10;
            final int scrollY = OS.GetSystemMetrics(3);
            height += rect.top * 2 + scrollY + scrollY / 3;
        }
        else {
            final int scrollX = OS.GetSystemMetrics(2);
            width += rect.left * 2 + scrollX + scrollX / 3;
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
        this.state |= 0x300;
        OS.SendMessage(this.handle, 1032, 0L, 100L);
        OS.SendMessage(this.handle, 1045, 0L, 10L);
        OS.SendMessage(this.handle, 1044, 10L, 0L);
        this.createdAsRTL = ((this.style & 0x4000000) != 0x0);
    }
    
    int defaultForeground() {
        return OS.GetSysColor(15);
    }
    
    public int getIncrement() {
        this.checkWidget();
        return (int)OS.SendMessage(this.handle, 1048, 0L, 0L);
    }
    
    public int getMaximum() {
        this.checkWidget();
        return (int)OS.SendMessage(this.handle, 1026, 0L, 0L);
    }
    
    public int getMinimum() {
        this.checkWidget();
        return (int)OS.SendMessage(this.handle, 1025, 0L, 0L);
    }
    
    public int getPageIncrement() {
        this.checkWidget();
        return (int)OS.SendMessage(this.handle, 1046, 0L, 0L);
    }
    
    public int getSelection() {
        this.checkWidget();
        return (int)OS.SendMessage(this.handle, 1024, 0L, 0L);
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
    
    void setBackgroundImage(final long hImage) {
        super.setBackgroundImage(hImage);
        this.ignoreResize = true;
        OS.SendMessage(this.handle, 5, 0L, 0L);
        this.ignoreResize = false;
    }
    
    void setBackgroundPixel(final int pixel) {
        super.setBackgroundPixel(pixel);
        this.ignoreResize = true;
        OS.SendMessage(this.handle, 5, 0L, 0L);
        this.ignoreResize = false;
    }
    
    void setBoundsInPixels(final int x, final int y, final int width, final int height, int flags, final boolean defer) {
        flags &= 0xFFFFFFDF;
        super.setBoundsInPixels(x, y, width, height, flags, true);
    }
    
    public void setIncrement(final int increment) {
        this.checkWidget();
        if (increment < 1) {
            return;
        }
        final int minimum = (int)OS.SendMessage(this.handle, 1025, 0L, 0L);
        final int maximum = (int)OS.SendMessage(this.handle, 1026, 0L, 0L);
        if (increment > maximum - minimum) {
            return;
        }
        OS.SendMessage(this.handle, 1047, 0L, (long)increment);
    }
    
    public void setMaximum(final int value) {
        this.checkWidget();
        final int minimum = (int)OS.SendMessage(this.handle, 1025, 0L, 0L);
        if (0 <= minimum && minimum < value) {
            OS.SendMessage(this.handle, 1032, 1L, (long)value);
        }
    }
    
    public void setMinimum(final int value) {
        this.checkWidget();
        final int maximum = (int)OS.SendMessage(this.handle, 1026, 0L, 0L);
        if (0 <= value && value < maximum) {
            OS.SendMessage(this.handle, 1031, 1L, (long)value);
        }
    }
    
    public void setPageIncrement(final int pageIncrement) {
        this.checkWidget();
        if (pageIncrement < 1) {
            return;
        }
        final int minimum = (int)OS.SendMessage(this.handle, 1025, 0L, 0L);
        final int maximum = (int)OS.SendMessage(this.handle, 1026, 0L, 0L);
        if (pageIncrement > maximum - minimum) {
            return;
        }
        OS.SendMessage(this.handle, 1045, 0L, (long)pageIncrement);
        OS.SendMessage(this.handle, 1044, (long)pageIncrement, 0L);
    }
    
    public void setSelection(final int value) {
        this.checkWidget();
        OS.SendMessage(this.handle, 1029, 1L, (long)value);
    }
    
    int widgetStyle() {
        final int bits = super.widgetStyle() | 0x10000 | 0x8 | 0x1;
        if ((this.style & 0x100) != 0x0) {
            return bits | 0x0 | 0x400;
        }
        return bits | 0x2;
    }
    
    TCHAR windowClass() {
        return Scale.TrackBarClass;
    }
    
    long windowProc() {
        return Scale.TrackBarProc;
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
        }
        return result;
    }
    
    LRESULT WM_MOUSEWHEEL(final long wParam, final long lParam) {
        final LRESULT result = super.WM_MOUSEWHEEL(wParam, lParam);
        if (result != null) {
            return result;
        }
        final int oldPosition = (int)OS.SendMessage(this.handle, 1024, 0L, 0L);
        this.ignoreSelection = true;
        final long code = this.callWindowProc(this.handle, 522, wParam, lParam);
        this.ignoreSelection = false;
        final int newPosition = (int)OS.SendMessage(this.handle, 1024, 0L, 0L);
        if (oldPosition != newPosition) {
            this.sendSelectionEvent(13, (Event)null, true);
        }
        return new LRESULT(code);
    }
    
    LRESULT WM_PAINT(final long wParam, final long lParam) {
        if ((this.state & 0x1000) != 0x0) {
            return LRESULT.ZERO;
        }
        boolean fixPaint = this.findBackgroundControl() != null;
        if (!fixPaint && OS.IsAppThemed()) {
            final Control control = this.findThemeControl();
            fixPaint = (control != null);
        }
        if (fixPaint) {
            final boolean redraw = this.getDrawing() && OS.IsWindowVisible(this.handle);
            if (redraw) {
                OS.SendMessage(this.handle, 11, 0L, 0L);
            }
            this.ignoreResize = true;
            OS.SendMessage(this.handle, 5, 0L, 0L);
            this.ignoreResize = false;
            if (redraw) {
                OS.SendMessage(this.handle, 11, 1L, 0L);
                OS.InvalidateRect(this.handle, (RECT)null, false);
            }
        }
        return super.WM_PAINT(wParam, lParam);
    }
    
    LRESULT WM_SIZE(final long wParam, final long lParam) {
        if (this.ignoreResize) {
            return null;
        }
        return super.WM_SIZE(wParam, lParam);
    }
    
    LRESULT wmScrollChild(final long wParam, final long lParam) {
        final int code = OS.LOWORD(wParam);
        switch (code) {
            case 4:
            case 8: {
                return null;
            }
            default: {
                if (!this.ignoreSelection) {
                    final Event event = new Event();
                    this.sendSelectionEvent(13, event, true);
                }
                return null;
            }
        }
    }
    
    static {
        TrackBarClass = new TCHAR(0, "msctls_trackbar32", true);
        final WNDCLASS lpWndClass = new WNDCLASS();
        OS.GetClassInfo(0L, Scale.TrackBarClass, lpWndClass);
        TrackBarProc = lpWndClass.lpfnWndProc;
        lpWndClass.hInstance = OS.GetModuleHandle((char[])null);
        final WNDCLASS wndclass3;
        final WNDCLASS wndclass = wndclass3 = lpWndClass;
        wndclass3.style &= 0xFFFFBFFF;
        final WNDCLASS wndclass4;
        final WNDCLASS wndclass2 = wndclass4 = lpWndClass;
        wndclass4.style |= 0x8;
        OS.RegisterClass(Scale.TrackBarClass, lpWndClass);
    }
}
