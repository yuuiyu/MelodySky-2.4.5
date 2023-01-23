//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class ProgressBar extends Control
{
    static final int DELAY = 100;
    static final int TIMER_ID = 100;
    static final int MINIMUM_WIDTH = 100;
    static final long ProgressBarProc;
    static final TCHAR ProgressBarClass;
    
    public ProgressBar(final Composite parent, final int style) {
        super(parent, checkStyle(style));
    }
    
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        return OS.CallWindowProc(ProgressBar.ProgressBarProc, hwnd, msg, wParam, lParam);
    }
    
    static int checkStyle(int style) {
        style |= 0x80000;
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
        if (this.display.progressbarUseColors) {
            final char[] noTheme = { '\0' };
            OS.SetWindowTheme(this.handle, noTheme, noTheme);
        }
        this.startTimer();
    }
    
    int defaultForeground() {
        return OS.GetSysColor(13);
    }
    
    public int getMaximum() {
        this.checkWidget();
        return (int)OS.SendMessage(this.handle, 1031, 0L, 0L);
    }
    
    public int getMinimum() {
        this.checkWidget();
        return (int)OS.SendMessage(this.handle, 1031, 1L, 0L);
    }
    
    public int getSelection() {
        this.checkWidget();
        return (int)OS.SendMessage(this.handle, 1032, 0L, 0L);
    }
    
    public int getState() {
        this.checkWidget();
        final int state = (int)OS.SendMessage(this.handle, 1041, 0L, 0L);
        switch (state) {
            case 1: {
                return 0;
            }
            case 2: {
                return 1;
            }
            case 3: {
                return 4;
            }
            default: {
                return 0;
            }
        }
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.stopTimer();
    }
    
    void startTimer() {
        if ((this.style & 0x2) != 0x0) {
            final int bits = OS.GetWindowLong(this.handle, -16);
            if ((bits & 0x8) == 0x0) {
                OS.SetTimer(this.handle, 100L, 100, 0L);
            }
            else {
                OS.SendMessage(this.handle, 1034, 1L, 100L);
            }
        }
    }
    
    void stopTimer() {
        if ((this.style & 0x2) != 0x0) {
            final int bits = OS.GetWindowLong(this.handle, -16);
            if ((bits & 0x8) == 0x0) {
                OS.KillTimer(this.handle, 100L);
            }
            else {
                OS.SendMessage(this.handle, 1034, 0L, 0L);
            }
        }
    }
    
    void setBackgroundPixel(int pixel) {
        if (pixel == -1) {
            pixel = -16777216;
        }
        OS.SendMessage(this.handle, 8193, 0L, (long)pixel);
    }
    
    void setForegroundPixel(int pixel) {
        if (pixel == -1) {
            pixel = -16777216;
        }
        OS.SendMessage(this.handle, 1033, 0L, (long)pixel);
    }
    
    public void setMaximum(final int value) {
        this.checkWidget();
        final int minimum = (int)OS.SendMessage(this.handle, 1031, 1L, 0L);
        if (0 <= minimum && minimum < value) {
            OS.SendMessage(this.handle, 1030, (long)minimum, (long)value);
        }
    }
    
    public void setMinimum(final int value) {
        this.checkWidget();
        final int maximum = (int)OS.SendMessage(this.handle, 1031, 0L, 0L);
        if (0 <= value && value < maximum) {
            OS.SendMessage(this.handle, 1030, (long)value, (long)maximum);
        }
    }
    
    public void setSelection(final int value) {
        this.checkWidget();
        OS.SendMessage(this.handle, 1026, (long)value, 0L);
        final long state = OS.SendMessage(this.handle, 1041, 0L, 0L);
        if (state != 1L) {
            OS.SendMessage(this.handle, 1026, (long)value, 0L);
        }
    }
    
    public void setState(final int state) {
        this.checkWidget();
        switch (state) {
            case 0: {
                OS.SendMessage(this.handle, 1040, 1L, 0L);
                break;
            }
            case 1: {
                OS.SendMessage(this.handle, 1040, 2L, 0L);
                break;
            }
            case 4: {
                OS.SendMessage(this.handle, 1040, 3L, 0L);
                break;
            }
        }
    }
    
    int widgetStyle() {
        int bits = super.widgetStyle();
        if ((this.style & 0x10000) != 0x0) {
            bits |= 0x1;
        }
        if ((this.style & 0x200) != 0x0) {
            bits |= 0x4;
        }
        if ((this.style & 0x2) != 0x0) {
            bits |= 0x8;
        }
        return bits;
    }
    
    TCHAR windowClass() {
        return ProgressBar.ProgressBarClass;
    }
    
    long windowProc() {
        return ProgressBar.ProgressBarProc;
    }
    
    LRESULT WM_GETDLGCODE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_GETDLGCODE(wParam, lParam);
        if (result != null) {
            return result;
        }
        return new LRESULT(256L);
    }
    
    LRESULT WM_SIZE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SIZE(wParam, lParam);
        if (result != null) {
            return result;
        }
        if ((this.style & 0x2) != 0x0 && !OS.IsAppThemed()) {
            this.forceResize();
            final RECT rect = new RECT();
            OS.GetClientRect(this.handle, rect);
            final int oldBits;
            int newBits = oldBits = OS.GetWindowLong(this.handle, -16);
            if (rect.right - rect.left < 100) {
                newBits &= 0xFFFFFFF7;
            }
            else {
                newBits |= 0x8;
            }
            if (newBits != oldBits) {
                this.stopTimer();
                OS.SetWindowLong(this.handle, -16, newBits);
                this.startTimer();
            }
        }
        return result;
    }
    
    LRESULT WM_TIMER(final long wParam, final long lParam) {
        final LRESULT result = super.WM_TIMER(wParam, lParam);
        if (result != null) {
            return result;
        }
        if ((this.style & 0x2) != 0x0) {
            final int bits = OS.GetWindowLong(this.handle, -16);
            if ((bits & 0x8) == 0x0 && wParam == 100L) {
                OS.SendMessage(this.handle, 1029, 0L, 0L);
            }
        }
        return result;
    }
    
    static {
        ProgressBarClass = new TCHAR(0, "msctls_progress32", true);
        final WNDCLASS lpWndClass = new WNDCLASS();
        OS.GetClassInfo(0L, ProgressBar.ProgressBarClass, lpWndClass);
        ProgressBarProc = lpWndClass.lpfnWndProc;
        lpWndClass.hInstance = OS.GetModuleHandle((char[])null);
        final WNDCLASS wndclass3;
        final WNDCLASS wndclass = wndclass3 = lpWndClass;
        wndclass3.style &= 0xFFFFBFFF;
        final WNDCLASS wndclass4;
        final WNDCLASS wndclass2 = wndclass4 = lpWndClass;
        wndclass4.style |= 0x8;
        OS.RegisterClass(ProgressBar.ProgressBarClass, lpWndClass);
    }
}
