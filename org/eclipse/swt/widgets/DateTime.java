//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class DateTime extends Composite
{
    static final int MIN_YEAR = 1752;
    static final int MAX_YEAR = 9999;
    boolean doubleClick;
    boolean ignoreSelection;
    SYSTEMTIME lastSystemTime;
    SYSTEMTIME time;
    static final long DateTimeProc;
    static final TCHAR DateTimeClass;
    static final long CalendarProc;
    static final TCHAR CalendarClass;
    static final char SINGLE_QUOTE = '\'';
    static final char DAY_FORMAT_CONSTANT = 'd';
    static final char MONTH_FORMAT_CONSTANT = 'M';
    static final char YEAR_FORMAT_CONSTANT = 'y';
    static final char HOURS_FORMAT_CONSTANT = 'h';
    static final char MINUTES_FORMAT_CONSTANT = 'm';
    static final char SECONDS_FORMAT_CONSTANT = 's';
    static final char AMPM_FORMAT_CONSTANT = 't';
    
    public DateTime(final Composite parent, final int style) {
        super(parent, checkStyle(style));
        this.time = new SYSTEMTIME();
        if ((this.style & 0x8000) != 0x0) {
            final String buffer = ((this.style & 0x20) != 0x0) ? this.getCustomShortDateFormat() : this.getCustomShortTimeFormat();
            final TCHAR lpszFormat = new TCHAR(0, buffer, true);
            OS.SendMessage(this.handle, 4146, 0L, lpszFormat);
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
    
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        return OS.CallWindowProc(this.windowProc(), hwnd, msg, wParam, lParam);
    }
    
    static int checkStyle(int style) {
        style &= 0xFFFFFCFF;
        style = Widget.checkBits(style, 32, 128, 1024, 0, 0, 0);
        style = Widget.checkBits(style, 65536, 32768, 268435456, 0, 0, 0);
        if ((style & 0x20) == 0x0) {
            style &= 0xFFFFFFFB;
        }
        return style;
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    Point computeSizeInPixels(final int wHint, final int hHint, final boolean changed) {
        this.checkWidget();
        int width = 0;
        int height = 0;
        if (wHint == -1 || hHint == -1) {
            if ((this.style & 0x400) != 0x0) {
                final RECT rect = new RECT();
                OS.SendMessage(this.handle, 4105, 0L, rect);
                width = rect.right;
                height = rect.bottom;
            }
            else {
                if ((this.style & 0x4000) != 0x0) {
                    final int bits = OS.GetWindowLong(this.handle, -16);
                    OS.SendMessage(this.handle, 4107, 0L, (long)(bits | 0x4));
                }
                final SIZE size = new SIZE();
                OS.SendMessage(this.handle, 4111, 0L, size);
                width = size.cx;
                height = size.cy;
                final int upDownHeight = OS.GetSystemMetrics(20) + 7;
                height = Math.max(height, upDownHeight);
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
        return new Point(width, height);
    }
    
    void createHandle() {
        super.createHandle();
        this.state &= 0xFFFFFEFD;
        if ((this.style & 0x800) == 0x0) {
            int bits = OS.GetWindowLong(this.handle, -20);
            bits &= 0xFFFDFDFF;
            OS.SetWindowLong(this.handle, -20, bits);
        }
    }
    
    int defaultBackground() {
        return OS.GetSysColor(5);
    }
    
    String getCustomShortDateFormat() {
        final TCHAR tchar = new TCHAR(this.getCodePage(), 80);
        final int size = OS.GetLocaleInfo(1024, 4102, tchar, 80);
        return (size != 0) ? tchar.toString(0, size - 1) : "M/yyyy";
    }
    
    String getCustomShortTimeFormat() {
        final StringBuilder buffer = new StringBuilder(this.getTimeFormat());
        final int length = buffer.length();
        boolean inQuotes = false;
        int start = 0;
        int end = 0;
        while (start < length) {
            final char ch = buffer.charAt(start);
            if (ch == '\'') {
                inQuotes = !inQuotes;
            }
            else if (ch == 's' && !inQuotes) {
                for (end = start + 1; end < length && buffer.charAt(end) == 's'; ++end) {}
                while (start > 0 && buffer.charAt(start) != 'm') {
                    --start;
                }
                ++start;
                break;
            }
            ++start;
        }
        if (start < end) {
            buffer.delete(start, end);
        }
        return buffer.toString();
    }
    
    String getTimeFormat() {
        final TCHAR tchar = new TCHAR(this.getCodePage(), 80);
        final int size = OS.GetLocaleInfo(1024, 4099, tchar, 80);
        return (size > 0) ? tchar.toString(0, size - 1) : "h:mm:ss tt";
    }
    
    public int getDay() {
        this.checkWidget();
        final SYSTEMTIME systime = new SYSTEMTIME();
        final int msg = ((this.style & 0x400) != 0x0) ? 4097 : 4097;
        OS.SendMessage(this.handle, msg, 0L, systime);
        return systime.wDay;
    }
    
    public int getHours() {
        this.checkWidget();
        if ((this.style & 0x400) != 0x0) {
            return this.time.wHour;
        }
        final SYSTEMTIME systime = new SYSTEMTIME();
        final int msg = ((this.style & 0x400) != 0x0) ? 4097 : 4097;
        OS.SendMessage(this.handle, msg, 0L, systime);
        return systime.wHour;
    }
    
    public int getMinutes() {
        this.checkWidget();
        if ((this.style & 0x400) != 0x0) {
            return this.time.wMinute;
        }
        final SYSTEMTIME systime = new SYSTEMTIME();
        final int msg = ((this.style & 0x400) != 0x0) ? 4097 : 4097;
        OS.SendMessage(this.handle, msg, 0L, systime);
        return systime.wMinute;
    }
    
    public int getMonth() {
        this.checkWidget();
        final SYSTEMTIME systime = new SYSTEMTIME();
        final int msg = ((this.style & 0x400) != 0x0) ? 4097 : 4097;
        OS.SendMessage(this.handle, msg, 0L, systime);
        return systime.wMonth - 1;
    }
    
    String getNameText() {
        return ((this.style & 0x80) != 0x0) ? (this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds()) : (this.getMonth() + 1 + "/" + this.getDay() + "/" + this.getYear());
    }
    
    public int getSeconds() {
        this.checkWidget();
        if ((this.style & 0x400) != 0x0) {
            return this.time.wSecond;
        }
        final SYSTEMTIME systime = new SYSTEMTIME();
        final int msg = ((this.style & 0x400) != 0x0) ? 4097 : 4097;
        OS.SendMessage(this.handle, msg, 0L, systime);
        return systime.wSecond;
    }
    
    public int getYear() {
        this.checkWidget();
        final SYSTEMTIME systime = new SYSTEMTIME();
        final int msg = ((this.style & 0x400) != 0x0) ? 4097 : 4097;
        OS.SendMessage(this.handle, msg, 0L, systime);
        return systime.wYear;
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.lastSystemTime = null;
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
    
    public void setDate(final int year, final int month, final int day) {
        this.checkWidget();
        if (year < 1752 || year > 9999) {
            return;
        }
        final SYSTEMTIME systime = new SYSTEMTIME();
        int msg = ((this.style & 0x400) != 0x0) ? 4097 : 4097;
        OS.SendMessage(this.handle, msg, 0L, systime);
        msg = (((this.style & 0x400) != 0x0) ? 4098 : 4098);
        systime.wYear = (short)year;
        systime.wMonth = (short)(month + 1);
        systime.wDay = (short)day;
        OS.SendMessage(this.handle, msg, 0L, systime);
        this.lastSystemTime = null;
    }
    
    public void setDay(final int day) {
        this.checkWidget();
        final SYSTEMTIME systime = new SYSTEMTIME();
        int msg = ((this.style & 0x400) != 0x0) ? 4097 : 4097;
        OS.SendMessage(this.handle, msg, 0L, systime);
        msg = (((this.style & 0x400) != 0x0) ? 4098 : 4098);
        systime.wDay = (short)day;
        OS.SendMessage(this.handle, msg, 0L, systime);
        this.lastSystemTime = null;
    }
    
    public void setHours(final int hours) {
        this.checkWidget();
        if (hours < 0 || hours > 23) {
            return;
        }
        final SYSTEMTIME systime = new SYSTEMTIME();
        int msg = ((this.style & 0x400) != 0x0) ? 4097 : 4097;
        OS.SendMessage(this.handle, msg, 0L, systime);
        msg = (((this.style & 0x400) != 0x0) ? 4098 : 4098);
        systime.wHour = (short)hours;
        OS.SendMessage(this.handle, msg, 0L, systime);
        if ((this.style & 0x400) != 0x0 && hours >= 0 && hours <= 23) {
            this.time.wHour = (short)hours;
        }
    }
    
    public void setMinutes(final int minutes) {
        this.checkWidget();
        if (minutes < 0 || minutes > 59) {
            return;
        }
        final SYSTEMTIME systime = new SYSTEMTIME();
        int msg = ((this.style & 0x400) != 0x0) ? 4097 : 4097;
        OS.SendMessage(this.handle, msg, 0L, systime);
        msg = (((this.style & 0x400) != 0x0) ? 4098 : 4098);
        systime.wMinute = (short)minutes;
        OS.SendMessage(this.handle, msg, 0L, systime);
        if ((this.style & 0x400) != 0x0 && minutes >= 0 && minutes <= 59) {
            this.time.wMinute = (short)minutes;
        }
    }
    
    public void setMonth(final int month) {
        this.checkWidget();
        final SYSTEMTIME systime = new SYSTEMTIME();
        int msg = ((this.style & 0x400) != 0x0) ? 4097 : 4097;
        OS.SendMessage(this.handle, msg, 0L, systime);
        msg = (((this.style & 0x400) != 0x0) ? 4098 : 4098);
        systime.wMonth = (short)(month + 1);
        OS.SendMessage(this.handle, msg, 0L, systime);
        this.lastSystemTime = null;
    }
    
    public void setOrientation(final int orientation) {
        if ((this.style & 0x400) != 0x0) {
            super.setOrientation(orientation);
        }
    }
    
    public void setSeconds(final int seconds) {
        this.checkWidget();
        if (seconds < 0 || seconds > 59) {
            return;
        }
        final SYSTEMTIME systime = new SYSTEMTIME();
        int msg = ((this.style & 0x400) != 0x0) ? 4097 : 4097;
        OS.SendMessage(this.handle, msg, 0L, systime);
        msg = (((this.style & 0x400) != 0x0) ? 4098 : 4098);
        systime.wSecond = (short)seconds;
        OS.SendMessage(this.handle, msg, 0L, systime);
        if ((this.style & 0x400) != 0x0 && seconds >= 0 && seconds <= 59) {
            this.time.wSecond = (short)seconds;
        }
    }
    
    public void setTime(final int hours, final int minutes, final int seconds) {
        this.checkWidget();
        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59 || seconds < 0 || seconds > 59) {
            return;
        }
        final SYSTEMTIME systime = new SYSTEMTIME();
        int msg = ((this.style & 0x400) != 0x0) ? 4097 : 4097;
        OS.SendMessage(this.handle, msg, 0L, systime);
        msg = (((this.style & 0x400) != 0x0) ? 4098 : 4098);
        systime.wHour = (short)hours;
        systime.wMinute = (short)minutes;
        systime.wSecond = (short)seconds;
        OS.SendMessage(this.handle, msg, 0L, systime);
        if ((this.style & 0x400) != 0x0 && hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59 && seconds >= 0 && seconds <= 59) {
            this.time.wHour = (short)hours;
            this.time.wMinute = (short)minutes;
            this.time.wSecond = (short)seconds;
        }
    }
    
    public void setYear(final int year) {
        this.checkWidget();
        if (year < 1752 || year > 9999) {
            return;
        }
        final SYSTEMTIME systime = new SYSTEMTIME();
        int msg = ((this.style & 0x400) != 0x0) ? 4097 : 4097;
        OS.SendMessage(this.handle, msg, 0L, systime);
        msg = (((this.style & 0x400) != 0x0) ? 4098 : 4098);
        systime.wYear = (short)year;
        OS.SendMessage(this.handle, msg, 0L, systime);
        this.lastSystemTime = null;
    }
    
    int widgetStyle() {
        int bits = super.widgetStyle() | 0x10000;
        if ((this.style & 0x4000) != 0x0) {
            bits |= 0x4;
        }
        if ((this.style & 0x400) != 0x0) {
            return bits | 0x10;
        }
        bits &= 0xFDFFFFFF;
        if ((this.style & 0x80) != 0x0) {
            bits |= 0x9;
        }
        if ((this.style & 0x20) != 0x0) {
            bits |= (((this.style & 0x10000) != 0x0) ? 12 : 4);
            if ((this.style & 0x4) == 0x0) {
                bits |= 0x1;
            }
        }
        return bits;
    }
    
    TCHAR windowClass() {
        return ((this.style & 0x400) != 0x0) ? DateTime.CalendarClass : DateTime.DateTimeClass;
    }
    
    long windowProc() {
        return ((this.style & 0x400) != 0x0) ? DateTime.CalendarProc : DateTime.DateTimeProc;
    }
    
    LRESULT wmNotifyChild(final NMHDR hdr, final long wParam, final long lParam) {
        switch (hdr.code) {
            case -753: {
                this.display.captureChanged = true;
                break;
            }
            case -749: {
                if (this.ignoreSelection) {
                    break;
                }
                final SYSTEMTIME systime = new SYSTEMTIME();
                OS.SendMessage(this.handle, 4097, 0L, systime);
                this.sendSelectionEvent(13);
                break;
            }
            case -759: {
                final SYSTEMTIME systime = new SYSTEMTIME();
                OS.SendMessage(this.handle, 4097, 0L, systime);
                if (this.lastSystemTime != null && systime.wDay == this.lastSystemTime.wDay && systime.wMonth == this.lastSystemTime.wMonth && systime.wYear == this.lastSystemTime.wYear) {
                    break;
                }
                this.sendSelectionEvent(13);
                if ((this.style & 0x80) == 0x0) {
                    this.lastSystemTime = systime;
                    break;
                }
                break;
            }
        }
        return super.wmNotifyChild(hdr, wParam, lParam);
    }
    
    LRESULT WM_CHAR(final long wParam, final long lParam) {
        final LRESULT result = super.WM_CHAR(wParam, lParam);
        if (result != null) {
            return result;
        }
        switch ((int)wParam) {
            case 13: {
                this.sendSelectionEvent(14);
            }
            case 9:
            case 27: {
                return LRESULT.ZERO;
            }
            default: {
                return result;
            }
        }
    }
    
    LRESULT WM_LBUTTONDBLCLK(final long wParam, final long lParam) {
        final LRESULT result = super.WM_LBUTTONDBLCLK(wParam, lParam);
        if (this.isDisposed()) {
            return LRESULT.ZERO;
        }
        if ((this.style & 0x400) != 0x0) {
            final MCHITTESTINFO pMCHitTest = new MCHITTESTINFO();
            pMCHitTest.cbSize = MCHITTESTINFO.sizeof;
            final POINT pt = new POINT();
            pt.x = OS.GET_X_LPARAM(lParam);
            pt.y = OS.GET_Y_LPARAM(lParam);
            pMCHitTest.pt = pt;
            final long code = OS.SendMessage(this.handle, 4110, 0L, pMCHitTest);
            if ((code & 0x20001L) == 0x20001L) {
                this.doubleClick = true;
            }
        }
        return result;
    }
    
    LRESULT WM_LBUTTONDOWN(final long wParam, final long lParam) {
        final LRESULT result = super.WM_LBUTTONDOWN(wParam, lParam);
        if (result == LRESULT.ZERO) {
            return result;
        }
        this.doubleClick = false;
        if ((this.style & 0x400) != 0x0 && (this.style & 0x80000) == 0x0) {
            OS.SetFocus(this.handle);
        }
        return result;
    }
    
    LRESULT WM_LBUTTONUP(final long wParam, final long lParam) {
        final LRESULT result = super.WM_LBUTTONUP(wParam, lParam);
        if (this.isDisposed()) {
            return LRESULT.ZERO;
        }
        if (this.doubleClick) {
            this.sendSelectionEvent(14);
        }
        this.doubleClick = false;
        return result;
    }
    
    LRESULT WM_TIMER(final long wParam, final long lParam) {
        final LRESULT result = super.WM_TIMER(wParam, lParam);
        if (result != null) {
            return result;
        }
        this.ignoreSelection = true;
        final long code = this.callWindowProc(this.handle, 275, wParam, lParam);
        this.ignoreSelection = false;
        return (code == 0L) ? LRESULT.ZERO : new LRESULT(code);
    }
    
    static {
        DateTimeClass = new TCHAR(0, "SysDateTimePick32", true);
        CalendarClass = new TCHAR(0, "SysMonthCal32", true);
        WNDCLASS lpWndClass = new WNDCLASS();
        OS.GetClassInfo(0L, DateTime.DateTimeClass, lpWndClass);
        DateTimeProc = lpWndClass.lpfnWndProc;
        lpWndClass.hInstance = OS.GetModuleHandle((char[])null);
        final WNDCLASS wndclass5;
        final WNDCLASS wndclass = wndclass5 = lpWndClass;
        wndclass5.style &= 0xFFFFBFFF;
        final WNDCLASS wndclass6;
        final WNDCLASS wndclass2 = wndclass6 = lpWndClass;
        wndclass6.style |= 0x8;
        OS.RegisterClass(DateTime.DateTimeClass, lpWndClass);
        lpWndClass = new WNDCLASS();
        OS.GetClassInfo(0L, DateTime.CalendarClass, lpWndClass);
        CalendarProc = lpWndClass.lpfnWndProc;
        lpWndClass.hInstance = OS.GetModuleHandle((char[])null);
        final WNDCLASS wndclass7;
        final WNDCLASS wndclass3 = wndclass7 = lpWndClass;
        wndclass7.style &= 0xFFFFBFFF;
        final WNDCLASS wndclass8;
        final WNDCLASS wndclass4 = wndclass8 = lpWndClass;
        wndclass8.style |= 0x8;
        OS.RegisterClass(DateTime.CalendarClass, lpWndClass);
    }
}
