//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import java.util.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import java.util.regex.*;
import org.eclipse.swt.internal.win32.*;

public class Text extends Scrollable
{
    int tabs;
    int oldStart;
    int oldEnd;
    boolean doubleClick;
    boolean ignoreModify;
    boolean ignoreVerify;
    boolean ignoreCharacter;
    boolean allowPasswordChar;
    String message;
    int[] segments;
    int clearSegmentsCount;
    long hwndActiveIcon;
    static final char LTR_MARK = '\u200e';
    static final char RTL_MARK = '\u200f';
    static final int IDI_SEARCH = 101;
    static final int IDI_CANCEL = 102;
    static final int IDI_SEARCH_DARKTHEME = 103;
    static final int IDI_CANCEL_DARKTHEME = 104;
    public static final int LIMIT;
    public static final String DELIMITER;
    static final long EditProc;
    static final TCHAR EditClass;
    
    public Text(final Composite parent, final int style) {
        super(parent, checkStyle(style));
        this.clearSegmentsCount = 0;
    }
    
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        boolean redraw = false;
        switch (msg) {
            case 20: {
                if (this.findImageControl() != null) {
                    return 0L;
                }
                break;
            }
            case 276:
            case 277: {
                redraw = (this.findImageControl() != null && this.getDrawing() && OS.IsWindowVisible(this.handle));
                if (redraw) {
                    OS.DefWindowProc(this.handle, 11, 0L, 0L);
                    break;
                }
                break;
            }
            case 15: {
                final boolean doubleBuffer = this.findImageControl() != null;
                boolean drawMessage = false;
                if ((this.style & 0x4) != 0x0 && (this.style & 0x8) != 0x0 && this.message.length() > 0) {
                    drawMessage = (hwnd != OS.GetFocus() && OS.GetWindowTextLength(this.handle) == 0);
                }
                if (doubleBuffer || drawMessage) {
                    long paintDC = 0L;
                    final PAINTSTRUCT ps = new PAINTSTRUCT();
                    paintDC = OS.BeginPaint(this.handle, ps);
                    final int width = ps.right - ps.left;
                    final int height = ps.bottom - ps.top;
                    if (width != 0 && height != 0) {
                        long hDC = paintDC;
                        long hBitmap = 0L;
                        long hOldBitmap = 0L;
                        POINT lpPoint1 = null;
                        POINT lpPoint2 = null;
                        if (doubleBuffer) {
                            hDC = OS.CreateCompatibleDC(paintDC);
                            lpPoint1 = new POINT();
                            lpPoint2 = new POINT();
                            OS.SetWindowOrgEx(hDC, ps.left, ps.top, lpPoint1);
                            OS.SetBrushOrgEx(hDC, ps.left, ps.top, lpPoint2);
                            hBitmap = OS.CreateCompatibleBitmap(paintDC, width, height);
                            hOldBitmap = OS.SelectObject(hDC, hBitmap);
                            final RECT rect = new RECT();
                            OS.SetRect(rect, ps.left, ps.top, ps.right, ps.bottom);
                            this.drawBackground(hDC, rect);
                        }
                        OS.CallWindowProc(Text.EditProc, hwnd, 15, hDC, lParam);
                        if (drawMessage) {
                            final RECT rect = new RECT();
                            OS.GetClientRect(this.handle, rect);
                            final long margins = OS.SendMessage(this.handle, 212, 0L, 0L);
                            final RECT rect8;
                            final RECT rect2 = rect8 = rect;
                            rect8.left += OS.LOWORD(margins);
                            final RECT rect9;
                            final RECT rect3 = rect9 = rect;
                            rect9.right -= OS.HIWORD(margins);
                            if ((this.style & 0x800) != 0x0) {
                                final RECT rect10;
                                final RECT rect4 = rect10 = rect;
                                ++rect10.left;
                                final RECT rect11;
                                final RECT rect5 = rect11 = rect;
                                ++rect11.top;
                                final RECT rect12;
                                final RECT rect6 = rect12 = rect;
                                --rect12.right;
                                final RECT rect13;
                                final RECT rect7 = rect13 = rect;
                                --rect13.bottom;
                            }
                            final char[] buffer = this.message.toCharArray();
                            int uFormat = 8192;
                            final boolean rtl = (this.style & 0x4000000) != 0x0;
                            if (rtl) {
                                uFormat |= 0x20000;
                            }
                            final int alignment = this.style & 0x1024000;
                            switch (alignment) {
                                case 16384: {
                                    uFormat |= (rtl ? 2 : 0);
                                    break;
                                }
                                case 16777216: {
                                    uFormat |= 0x1;
                                }
                                case 131072: {
                                    uFormat |= (rtl ? 0 : 2);
                                    break;
                                }
                            }
                            final long hFont = OS.SendMessage(hwnd, 49, 0L, 0L);
                            final long hOldFont = OS.SelectObject(hDC, hFont);
                            OS.SetTextColor(hDC, OS.GetSysColor(17));
                            OS.SetBkMode(hDC, 1);
                            OS.DrawText(hDC, buffer, buffer.length, rect, uFormat);
                            OS.SelectObject(hDC, hOldFont);
                        }
                        if (doubleBuffer) {
                            OS.SetWindowOrgEx(hDC, lpPoint1.x, lpPoint1.y, (POINT)null);
                            OS.SetBrushOrgEx(hDC, lpPoint2.x, lpPoint2.y, (POINT)null);
                            OS.BitBlt(paintDC, ps.left, ps.top, width, height, hDC, 0, 0, 13369376);
                            OS.SelectObject(hDC, hOldBitmap);
                            OS.DeleteObject(hBitmap);
                            OS.DeleteObject(hDC);
                        }
                    }
                    OS.EndPaint(this.handle, ps);
                    return 0L;
                }
                break;
            }
        }
        if ((this.style & 0x80) != 0x0) {
            switch (msg) {
                case 512: {
                    final POINT pt = new POINT();
                    OS.POINTSTOPOINT(pt, lParam);
                    long hwndIcon = OS.ChildWindowFromPointEx(this.handle, pt, 1);
                    if (hwndIcon == this.handle) {
                        hwndIcon = 0L;
                    }
                    if (hwndIcon != this.hwndActiveIcon) {
                        if (this.hwndActiveIcon != 0L) {
                            OS.InvalidateRect(this.hwndActiveIcon, (RECT)null, false);
                        }
                        if (hwndIcon != 0L) {
                            OS.InvalidateRect(hwndIcon, (RECT)null, false);
                        }
                        this.hwndActiveIcon = hwndIcon;
                        break;
                    }
                    break;
                }
                case 675: {
                    if (this.hwndActiveIcon != 0L) {
                        OS.InvalidateRect(this.hwndActiveIcon, (RECT)null, false);
                        this.hwndActiveIcon = 0L;
                        break;
                    }
                    break;
                }
                case 513: {
                    if (this.hwndActiveIcon != 0L) {
                        OS.InvalidateRect(this.hwndActiveIcon, (RECT)null, false);
                        return 0L;
                    }
                    break;
                }
                case 514: {
                    if (this.hwndActiveIcon != 0L) {
                        final Event e = new Event();
                        if (this.hwndActiveIcon == OS.GetDlgItem(this.handle, 512)) {
                            e.detail = 512;
                        }
                        else {
                            e.detail = 256;
                            this.setText("");
                        }
                        this.setFocus();
                        this.selectAll();
                        this.sendSelectionEvent(14, e, false);
                        break;
                    }
                    break;
                }
            }
        }
        final long code = OS.CallWindowProc(Text.EditProc, hwnd, msg, wParam, lParam);
        switch (msg) {
            case 276:
            case 277: {
                if (redraw) {
                    OS.DefWindowProc(this.handle, 11, 1L, 0L);
                    OS.InvalidateRect(this.handle, (RECT)null, true);
                    break;
                }
                break;
            }
        }
        return code;
    }
    
    void createHandle() {
        long editStyle = this.widgetStyle();
        if ((editStyle & 0x800000L) == 0x0L) {
            super.createHandle();
        }
        else {
            this.style &= 0xFFFFF7FF;
            super.createHandle();
            this.style |= 0x800;
            editStyle = OS.GetWindowLongPtr(this.handle, -16);
            editStyle |= 0x800000L;
            OS.SetWindowLongPtr(this.handle, -16, editStyle);
            OS.SetWindowPos(this.handle, 0L, 0, 0, 0, 0, 39);
        }
        OS.SendMessage(this.handle, 197, 0L, 0L);
        if ((this.style & 0x8) != 0x0 && this.applyThemeBackground() == 1) {
            this.state |= 0x100;
        }
        if ((this.style & 0x80) != 0x0) {
            if (this.display.hIconSearch == 0L) {
                final long[] phicon = { 0L };
                final int searchIconResource = this.display.textUseDarkthemeIcons ? 103 : 101;
                int hresult = OS.LoadIconMetric(OS.GetLibraryHandle(), (long)searchIconResource, 0, phicon);
                if (hresult != 0) {
                    this.error(2);
                }
                this.display.hIconSearch = phicon[0];
                final int cancelIconResource = this.display.textUseDarkthemeIcons ? 104 : 102;
                hresult = OS.LoadIconMetric(OS.GetLibraryHandle(), (long)cancelIconResource, 0, phicon);
                if (hresult != 0) {
                    this.error(2);
                }
                this.display.hIconCancel = phicon[0];
            }
            if ((this.style & 0x200) != 0x0) {
                final long hwndSearch = OS.CreateWindowEx(0, Label.LabelClass, (TCHAR)null, 1409286157, 0, 0, 0, 0, this.handle, 512L, OS.GetModuleHandle((char[])null), (CREATESTRUCT)null);
                if (hwndSearch == 0L) {
                    this.error(2);
                }
            }
            if ((this.style & 0x100) != 0x0) {
                this.state |= 0x2000;
                final long hwndCancel = OS.CreateWindowEx(0, Label.LabelClass, (TCHAR)null, 1140850701, 0, 0, 0, 0, this.handle, 256L, OS.GetModuleHandle((char[])null), (CREATESTRUCT)null);
                if (hwndCancel == 0L) {
                    this.error(2);
                }
            }
        }
    }
    
    int applyThemeBackground() {
        return (this.backgroundAlpha == 0 || (this.style & 0xB00) == 0x0) ? 1 : 0;
    }
    
    public void addModifyListener(final ModifyListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(24, (Listener)typedListener);
    }
    
    public void addSegmentListener(final SegmentListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        this.addListener(49, (Listener)new TypedListener((SWTEventListener)listener));
        this.clearSegments(true);
        this.applySegments();
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
    
    public void addVerifyListener(final VerifyListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(25, (Listener)typedListener);
    }
    
    public void append(String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        string = Display.withCrLf(string);
        final int length = OS.GetWindowTextLength(this.handle);
        if (this.hooks(25) || this.filters(25)) {
            string = this.verifyText(string, length, length, null);
            if (string == null) {
                return;
            }
        }
        OS.SendMessage(this.handle, 177, (long)length, (long)length);
        this.clearSegments(true);
        final TCHAR buffer = new TCHAR(this.getCodePage(), string, true);
        this.ignoreCharacter = true;
        OS.SendMessage(this.handle, 194, 0L, buffer);
        this.ignoreCharacter = false;
        OS.SendMessage(this.handle, 183, 0L, 0L);
        if ((this.state & 0x400000) != 0x0) {
            super.updateTextDirection(100663296);
        }
        this.applySegments();
    }
    
    void applySegments() {
        if (this.isDisposed() || --this.clearSegmentsCount != 0) {
            return;
        }
        if (!this.hooks(49) && !this.filters(49)) {
            return;
        }
        int length = OS.GetWindowTextLength(this.handle);
        final char[] buffer = new char[length + 1];
        if (length > 0) {
            OS.GetWindowText(this.handle, buffer, length + 1);
        }
        final String string = new String(buffer, 0, length);
        final Event event = new Event();
        event.text = string;
        event.segments = this.segments;
        this.sendEvent(49, event);
        this.segments = event.segments;
        if (this.segments == null) {
            return;
        }
        int nSegments = this.segments.length;
        if (nSegments == 0) {
            return;
        }
        length = ((string == null) ? 0 : string.length());
        for (int i = 1; i < nSegments; ++i) {
            if (event.segments[i] < event.segments[i - 1] || event.segments[i] > length) {
                this.error(5);
            }
        }
        char[] segmentsChars = event.segmentsChars;
        final char[] segmentsCharsCrLf = (char[])((segmentsChars == null) ? null : Display.withCrLf(segmentsChars));
        if (segmentsChars != segmentsCharsCrLf) {
            final int[] segmentsCrLf = new int[nSegments + Math.min(nSegments, segmentsCharsCrLf.length - segmentsChars.length)];
            int j = 0;
            int c = 0;
            while (j < segmentsChars.length && j < nSegments) {
                if (segmentsChars[j] == '\n' && segmentsCharsCrLf[j + c] == '\r') {
                    segmentsCrLf[j + c++] = this.segments[j];
                }
                segmentsCrLf[j + c] = this.segments[j];
                ++j;
            }
            this.segments = segmentsCrLf;
            nSegments = this.segments.length;
            segmentsChars = segmentsCharsCrLf;
        }
        final int limit = (int)OS.SendMessage(this.handle, 213, 0L, 0L) & Integer.MAX_VALUE;
        OS.SendMessage(this.handle, 197, (long)(limit + Math.min(nSegments, Text.LIMIT - limit)), 0L);
        length += nSegments;
        final char[] newChars = new char[length + 1];
        int charCount = 0;
        int segmentCount = 0;
        final char defaultSeparator = (this.getOrientation() == 67108864) ? '\u200f' : '\u200e';
        while (charCount < length) {
            if (segmentCount < nSegments && charCount - segmentCount == this.segments[segmentCount]) {
                final char separator = (segmentsChars != null && segmentsChars.length > segmentCount) ? segmentsChars[segmentCount] : defaultSeparator;
                newChars[charCount++] = separator;
                ++segmentCount;
            }
            else {
                if (string == null) {
                    continue;
                }
                newChars[charCount] = string.charAt(charCount++ - segmentCount);
            }
        }
        while (segmentCount < nSegments) {
            this.segments[segmentCount] = charCount - segmentCount;
            final char separator = (segmentsChars != null && segmentsChars.length > segmentCount) ? segmentsChars[segmentCount] : defaultSeparator;
            newChars[charCount++] = separator;
            ++segmentCount;
        }
        final int[] start = { 0 };
        final int[] end = { 0 };
        OS.SendMessage(this.handle, 176, start, end);
        final boolean oldIgnoreCharacter = this.ignoreCharacter;
        final boolean oldIgnoreModify = this.ignoreModify;
        final boolean oldIgnoreVerify = this.ignoreVerify;
        final boolean ignoreCharacter = true;
        this.ignoreVerify = true;
        this.ignoreModify = true;
        this.ignoreCharacter = true;
        newChars[length] = '\0';
        OS.SendMessage(this.handle, 177, 0L, -1L);
        final long undo = OS.SendMessage(this.handle, 198, 0L, 0L);
        OS.SendMessage(this.handle, 194, undo, newChars);
        start[0] = this.translateOffset(start[0]);
        end[0] = this.translateOffset(end[0]);
        OS.SendMessage(this.handle, 177, (long)start[0], (long)end[0]);
        this.ignoreCharacter = oldIgnoreCharacter;
        this.ignoreModify = oldIgnoreModify;
        this.ignoreVerify = oldIgnoreVerify;
    }
    
    static int checkStyle(int style) {
        if ((style & 0x4) != 0x0 && (style & 0x2) != 0x0) {
            style &= 0xFFFFFFFD;
        }
        style = Widget.checkBits(style, 16384, 16777216, 131072, 0, 0, 0);
        if ((style & 0x80) != 0x0) {
            style |= 0x804;
            style &= 0xFFBFFFBF;
        }
        else if ((style & 0x4) != 0x0) {
            style &= 0xFFFFFCBF;
        }
        if ((style & 0x40) != 0x0) {
            style |= 0x2;
            style &= 0xFFFFFEFF;
        }
        if ((style & 0x2) != 0x0) {
            style &= 0xFFBFFFFF;
        }
        if ((style & 0x6) != 0x0) {
            return style;
        }
        if ((style & 0x300) != 0x0) {
            return style | 0x2;
        }
        return style | 0x4;
    }
    
    void clearSegments(final boolean applyText) {
        if (this.clearSegmentsCount++ != 0) {
            return;
        }
        if (this.segments == null) {
            return;
        }
        final int nSegments = this.segments.length;
        if (nSegments == 0) {
            return;
        }
        final int limit = (int)OS.SendMessage(this.handle, 213, 0L, 0L) & Integer.MAX_VALUE;
        if (limit < Text.LIMIT) {
            OS.SendMessage(this.handle, 197, (long)Math.max(1, limit - nSegments), 0L);
        }
        if (!applyText) {
            this.segments = null;
            return;
        }
        final boolean oldIgnoreCharacter = this.ignoreCharacter;
        final boolean oldIgnoreModify = this.ignoreModify;
        final boolean oldIgnoreVerify = this.ignoreVerify;
        final boolean ignoreCharacter = true;
        this.ignoreVerify = true;
        this.ignoreModify = true;
        this.ignoreCharacter = true;
        final int length = OS.GetWindowTextLength(this.handle);
        final int cp = this.getCodePage();
        TCHAR buffer = new TCHAR(cp, length + 1);
        if (length > 0) {
            OS.GetWindowText(this.handle, buffer, length + 1);
        }
        buffer = this.deprocessText(buffer, 0, -1, true);
        final int[] start = { 0 };
        final int[] end = { 0 };
        OS.SendMessage(this.handle, 176, start, end);
        start[0] = this.untranslateOffset(start[0]);
        end[0] = this.untranslateOffset(end[0]);
        this.segments = null;
        OS.SendMessage(this.handle, 177, 0L, -1L);
        final long undo = OS.SendMessage(this.handle, 198, 0L, 0L);
        OS.SendMessage(this.handle, 194, undo, buffer);
        OS.SendMessage(this.handle, 177, (long)start[0], (long)end[0]);
        this.ignoreCharacter = oldIgnoreCharacter;
        this.ignoreModify = oldIgnoreModify;
        this.ignoreVerify = oldIgnoreVerify;
    }
    
    public void clearSelection() {
        this.checkWidget();
        OS.SendMessage(this.handle, 177, -1L, 0L);
    }
    
    Point computeSizeInPixels(final int wHint, final int hHint, final boolean changed) {
        this.checkWidget();
        int height = 0;
        int width = 0;
        if (wHint == -1 || hHint == -1) {
            long oldFont = 0L;
            final long hDC = OS.GetDC(this.handle);
            final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
            if (newFont != 0L) {
                oldFont = OS.SelectObject(hDC, newFont);
            }
            final TEXTMETRIC tm = new TEXTMETRIC();
            OS.GetTextMetrics(hDC, tm);
            final int count = ((this.style & 0x4) != 0x0) ? 1 : ((int)OS.SendMessage(this.handle, 186, 0L, 0L));
            height = count * tm.tmHeight;
            final RECT rect = new RECT();
            int flags = 11264;
            final boolean wrap = (this.style & 0x2) != 0x0 && (this.style & 0x40) != 0x0;
            if (wrap && wHint != -1) {
                flags |= 0x10;
                rect.right = wHint;
            }
            final int length = OS.GetWindowTextLength(this.handle);
            if (length != 0) {
                final char[] buffer = new char[length + 1];
                OS.GetWindowText(this.handle, buffer, length + 1);
                OS.DrawText(hDC, buffer, length, rect, flags);
                Arrays.fill(buffer, '\0');
                width = rect.right - rect.left;
            }
            if (wrap && hHint == -1) {
                final int newHeight = rect.bottom - rect.top;
                if (newHeight != 0) {
                    height = newHeight;
                }
            }
            if ((this.style & 0x4) != 0x0 && this.message.length() > 0) {
                OS.SetRect(rect, 0, 0, 0, 0);
                final char[] buffer = this.message.toCharArray();
                OS.DrawText(hDC, buffer, buffer.length, rect, flags);
                width = Math.max(width, rect.right - rect.left);
            }
            if (newFont != 0L) {
                OS.SelectObject(hDC, oldFont);
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
    
    Rectangle computeTrimInPixels(final int x, final int y, final int width, final int height) {
        this.checkWidget();
        final Rectangle rect = super.computeTrimInPixels(x, y, width, height);
        final long margins = OS.SendMessage(this.handle, 212, 0L, 0L);
        final Rectangle rectangle12;
        final Rectangle rectangle = rectangle12 = rect;
        rectangle12.x -= OS.LOWORD(margins);
        final Rectangle rectangle13;
        final Rectangle rectangle2 = rectangle13 = rect;
        rectangle13.width += OS.LOWORD(margins) + OS.HIWORD(margins);
        if ((this.style & 0x100) != 0x0) {
            final Rectangle rectangle14;
            final Rectangle rectangle3 = rectangle14 = rect;
            ++rectangle14.width;
        }
        if ((this.style & 0x800) != 0x0) {
            final Rectangle rectangle15;
            final Rectangle rectangle4 = rectangle15 = rect;
            --rectangle15.x;
            final Rectangle rectangle16;
            final Rectangle rectangle5 = rectangle16 = rect;
            --rectangle16.y;
            final Rectangle rectangle17;
            final Rectangle rectangle6 = rectangle17 = rect;
            rectangle17.width += 2;
            final Rectangle rectangle18;
            final Rectangle rectangle7 = rectangle18 = rect;
            rectangle18.height += 2;
            if (this.isUseWsBorder()) {
                final int dx = OS.GetSystemMetrics(45) - OS.GetSystemMetrics(5);
                final int dy = OS.GetSystemMetrics(46) - OS.GetSystemMetrics(6);
                final Rectangle rectangle19;
                final Rectangle rectangle8 = rectangle19 = rect;
                rectangle19.x -= dx;
                final Rectangle rectangle20;
                final Rectangle rectangle9 = rectangle20 = rect;
                rectangle20.y -= dy;
                final Rectangle rectangle21;
                final Rectangle rectangle10 = rectangle21 = rect;
                rectangle21.width += 2 * dx;
                final Rectangle rectangle22;
                final Rectangle rectangle11 = rectangle22 = rect;
                rectangle22.height += 2 * dy;
            }
        }
        return rect;
    }
    
    public void copy() {
        this.checkWidget();
        OS.SendMessage(this.handle, 769, 0L, 0L);
    }
    
    ScrollBar createScrollBar(final int type) {
        return ((this.style & 0x80) == 0x0) ? super.createScrollBar(type) : null;
    }
    
    void createWidget() {
        super.createWidget();
        this.message = "";
        this.doubleClick = true;
        this.setTabStops(this.tabs = 8);
        this.fixAlignment();
    }
    
    public void cut() {
        this.checkWidget();
        if ((this.style & 0x8) != 0x0) {
            return;
        }
        OS.SendMessage(this.handle, 768, 0L, 0L);
    }
    
    int defaultBackground() {
        final int bits = OS.GetWindowLong(this.handle, -16);
        if ((bits & 0x800) != 0x0 || !OS.IsWindowEnabled(this.handle)) {
            return OS.GetSysColor(15);
        }
        return OS.GetSysColor(5);
    }
    
    TCHAR deprocessText(final TCHAR text, int start, int end, final boolean terminate) {
        if (text == null) {
            return null;
        }
        int length = text.length();
        if (start < 0) {
            start = 0;
        }
        final char[] chars = text.chars;
        if (text.chars[length - 1] == '\0') {
            --length;
        }
        if (end == -1) {
            end = length;
        }
        if (this.segments != null && end > this.segments[0]) {
            final int nSegments = this.segments.length;
            if (nSegments > 0 && start <= this.segments[nSegments - 1]) {
                int nLeadSegments;
                for (nLeadSegments = 0; start - nLeadSegments > this.segments[nLeadSegments]; ++nLeadSegments) {}
                int segmentCount = nLeadSegments;
                for (int i = start; i < end; ++i) {
                    if (segmentCount < nSegments && i - segmentCount == this.segments[segmentCount]) {
                        ++segmentCount;
                    }
                    else {
                        chars[i - segmentCount + nLeadSegments] = chars[i];
                    }
                }
                length = end - start - segmentCount + nLeadSegments;
            }
        }
        if (start != 0 || end != length) {
            final char[] newChars = new char[length];
            System.arraycopy(chars, start, newChars, 0, length);
            return new TCHAR(this.getCodePage(), newChars, terminate);
        }
        return text;
    }
    
    boolean dragDetect(final long hwnd, final int x, final int y, final boolean filter, final boolean[] detect, final boolean[] consume) {
        if (filter) {
            final int[] start = { 0 };
            final int[] end = { 0 };
            OS.SendMessage(this.handle, 176, start, end);
            if (start[0] != end[0]) {
                final long lParam = OS.MAKELPARAM(x, y);
                final int position = OS.LOWORD(OS.SendMessage(this.handle, 215, 0L, lParam));
                if (start[0] <= position && position < end[0] && super.dragDetect(hwnd, x, y, filter, detect, consume)) {
                    if (consume != null) {
                        consume[0] = true;
                    }
                    return true;
                }
            }
            return false;
        }
        return super.dragDetect(hwnd, x, y, filter, detect, consume);
    }
    
    void maybeEnableDarkSystemTheme() {
        if (this.hasCustomBackground() || this.hasCustomForeground()) {
            super.maybeEnableDarkSystemTheme();
        }
    }
    
    void fixAlignment() {
        if ((this.style & 0x8000000) != 0x0) {
            return;
        }
        int bits1 = OS.GetWindowLong(this.handle, -20);
        int bits2 = OS.GetWindowLong(this.handle, -16);
        if ((this.style & 0x2000000) != 0x0) {
            if ((this.style & 0x20000) != 0x0) {
                bits1 |= 0x1000;
                bits2 |= 0x2;
            }
            if ((this.style & 0x4000) != 0x0) {
                bits1 &= 0xFFFFEFFF;
                bits2 &= 0xFFFFFFFD;
            }
        }
        else {
            if ((this.style & 0x20000) != 0x0) {
                bits1 &= 0xFFFFEFFF;
                bits2 &= 0xFFFFFFFD;
            }
            if ((this.style & 0x4000) != 0x0) {
                bits1 |= 0x1000;
                bits2 |= 0x2;
            }
        }
        if ((this.style & 0x1000000) != 0x0) {
            bits2 |= 0x1;
        }
        OS.SetWindowLong(this.handle, -20, bits1);
        OS.SetWindowLong(this.handle, -16, bits2);
    }
    
    int getBorderWidthInPixels() {
        this.checkWidget();
        return super.getBorderWidthInPixels();
    }
    
    public int getCaretLineNumber() {
        this.checkWidget();
        return (int)OS.SendMessage(this.handle, 201, -1L, 0L);
    }
    
    public Point getCaretLocation() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getCaretLocationInPixels());
    }
    
    Point getCaretLocationInPixels() {
        final int position = this.translateOffset(this.getCaretPosition());
        long caretPos = OS.SendMessage(this.handle, 214, (long)position, 0L);
        if (caretPos == -1L) {
            caretPos = 0L;
            if (position >= OS.GetWindowTextLength(this.handle)) {
                final int[] start = { 0 };
                final int[] end = { 0 };
                OS.SendMessage(this.handle, 176, start, end);
                OS.SendMessage(this.handle, 177, (long)position, (long)position);
                final boolean b = true;
                this.ignoreModify = true;
                this.ignoreCharacter = true;
                OS.SendMessage(this.handle, 194, 0L, new char[] { ' ', '\0' });
                caretPos = OS.SendMessage(this.handle, 214, (long)position, 0L);
                OS.SendMessage(this.handle, 177, (long)position, (long)(position + 1));
                OS.SendMessage(this.handle, 194, 0L, new char[] { '\0' });
                final boolean b2 = false;
                this.ignoreModify = false;
                this.ignoreCharacter = false;
                OS.SendMessage(this.handle, 177, (long)start[0], (long)start[0]);
                OS.SendMessage(this.handle, 177, (long)start[0], (long)end[0]);
            }
        }
        return new Point(OS.GET_X_LPARAM(caretPos), OS.GET_Y_LPARAM(caretPos));
    }
    
    public int getCaretPosition() {
        this.checkWidget();
        final int[] start = { 0 };
        final int[] end = { 0 };
        OS.SendMessage(this.handle, 176, start, end);
        int caret = start[0];
        if (start[0] != end[0]) {
            final int startLine = (int)OS.SendMessage(this.handle, 201, (long)start[0], 0L);
            final int endLine = (int)OS.SendMessage(this.handle, 201, (long)end[0], 0L);
            if (startLine == endLine) {
                final int idThread = OS.GetWindowThreadProcessId(this.handle, (int[])null);
                final GUITHREADINFO lpgui = new GUITHREADINFO();
                lpgui.cbSize = GUITHREADINFO.sizeof;
                if (OS.GetGUIThreadInfo(idThread, lpgui) && (lpgui.hwndCaret == this.handle || lpgui.hwndCaret == 0L)) {
                    final POINT ptCurrentPos = new POINT();
                    if (OS.GetCaretPos(ptCurrentPos)) {
                        final long endPos = OS.SendMessage(this.handle, 214, (long)end[0], 0L);
                        if (endPos == -1L) {
                            final long startPos = OS.SendMessage(this.handle, 214, (long)start[0], 0L);
                            final int startX = OS.GET_X_LPARAM(startPos);
                            if (ptCurrentPos.x > startX) {
                                caret = end[0];
                            }
                        }
                        else {
                            final int endX = OS.GET_X_LPARAM(endPos);
                            if (ptCurrentPos.x >= endX) {
                                caret = end[0];
                            }
                        }
                    }
                }
            }
            else {
                final int caretPos = (int)OS.SendMessage(this.handle, 187, -1L, 0L);
                final int caretLine = (int)OS.SendMessage(this.handle, 201, (long)caretPos, 0L);
                if (caretLine == endLine) {
                    caret = end[0];
                }
            }
        }
        return this.untranslateOffset(caret);
    }
    
    public int getCharCount() {
        this.checkWidget();
        final int length = OS.GetWindowTextLength(this.handle);
        return this.untranslateOffset(length);
    }
    
    public boolean getDoubleClickEnabled() {
        this.checkWidget();
        return this.doubleClick;
    }
    
    public char getEchoChar() {
        this.checkWidget();
        return (char)OS.SendMessage(this.handle, 210, 0L, 0L);
    }
    
    public boolean getEditable() {
        this.checkWidget();
        final int bits = OS.GetWindowLong(this.handle, -16);
        return (bits & 0x800) == 0x0;
    }
    
    public int getLineCount() {
        this.checkWidget();
        return (int)OS.SendMessage(this.handle, 186, 0L, 0L);
    }
    
    public String getLineDelimiter() {
        this.checkWidget();
        return Text.DELIMITER;
    }
    
    public int getLineHeight() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getLineHeightInPixels());
    }
    
    int getLineHeightInPixels() {
        long oldFont = 0L;
        final long hDC = OS.GetDC(this.handle);
        final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
        if (newFont != 0L) {
            oldFont = OS.SelectObject(hDC, newFont);
        }
        final TEXTMETRIC tm = new TEXTMETRIC();
        OS.GetTextMetrics(hDC, tm);
        if (newFont != 0L) {
            OS.SelectObject(hDC, oldFont);
        }
        OS.ReleaseDC(this.handle, hDC);
        return tm.tmHeight;
    }
    
    public int getOrientation() {
        return super.getOrientation();
    }
    
    public String getMessage() {
        this.checkWidget();
        return this.message;
    }
    
    int getPosition(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        final long lParam = OS.MAKELPARAM(point.x, point.y);
        final int position = OS.LOWORD(OS.SendMessage(this.handle, 215, 0L, lParam));
        return this.untranslateOffset(position);
    }
    
    public Point getSelection() {
        this.checkWidget();
        final int[] start = { 0 };
        final int[] end = { 0 };
        OS.SendMessage(this.handle, 176, start, end);
        return new Point(this.untranslateOffset(start[0]), this.untranslateOffset(end[0]));
    }
    
    public int getSelectionCount() {
        this.checkWidget();
        final Point selection = this.getSelection();
        return selection.y - selection.x;
    }
    
    public String getSelectionText() {
        this.checkWidget();
        final int length = OS.GetWindowTextLength(this.handle);
        if (length == 0) {
            return "";
        }
        final int[] start = { 0 };
        final int[] end = { 0 };
        OS.SendMessage(this.handle, 176, start, end);
        if (start[0] == end[0]) {
            return "";
        }
        TCHAR buffer = new TCHAR(this.getCodePage(), length + 1);
        OS.GetWindowText(this.handle, buffer, length + 1);
        if (this.segments != null) {
            buffer = this.deprocessText(buffer, start[0], end[0], false);
            return buffer.toString();
        }
        return buffer.toString(start[0], end[0] - start[0]);
    }
    
    public int getTabs() {
        this.checkWidget();
        return this.tabs;
    }
    
    int getTabWidth(final int tabs) {
        long oldFont = 0L;
        final RECT rect = new RECT();
        final long hDC = OS.GetDC(this.handle);
        final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
        if (newFont != 0L) {
            oldFont = OS.SelectObject(hDC, newFont);
        }
        final int flags = 3104;
        OS.DrawText(hDC, new char[] { ' ' }, 1, rect, 3104);
        if (newFont != 0L) {
            OS.SelectObject(hDC, oldFont);
        }
        OS.ReleaseDC(this.handle, hDC);
        return (rect.right - rect.left) * tabs;
    }
    
    public String getText() {
        this.checkWidget();
        final int length = OS.GetWindowTextLength(this.handle);
        if (length == 0) {
            return "";
        }
        TCHAR buffer = new TCHAR(this.getCodePage(), length + 1);
        OS.GetWindowText(this.handle, buffer, length + 1);
        if (this.segments != null) {
            buffer = this.deprocessText(buffer, 0, -1, false);
            return buffer.toString();
        }
        return buffer.toString(0, length);
    }
    
    public char[] getTextChars() {
        this.checkWidget();
        final int length = OS.GetWindowTextLength(this.handle);
        if (length == 0) {
            return new char[0];
        }
        TCHAR buffer = new TCHAR(this.getCodePage(), length + 1);
        OS.GetWindowText(this.handle, buffer, length + 1);
        if (this.segments != null) {
            buffer = this.deprocessText(buffer, 0, -1, false);
        }
        final char[] chars = new char[length];
        System.arraycopy(buffer.chars, 0, chars, 0, length);
        buffer.clear();
        return chars;
    }
    
    public String getText(int start, int end) {
        this.checkWidget();
        if (start > end || 0 > end) {
            return "";
        }
        final int length = OS.GetWindowTextLength(this.handle);
        end = Math.min(end, this.untranslateOffset(length) - 1);
        if (start > end) {
            return "";
        }
        start = Math.max(0, start);
        return this.getText().substring(start, end + 1);
    }
    
    public int getTextLimit() {
        this.checkWidget();
        int limit = (int)OS.SendMessage(this.handle, 213, 0L, 0L) & Integer.MAX_VALUE;
        if (this.segments != null && limit < Text.LIMIT) {
            limit = Math.max(1, limit - this.segments.length);
        }
        return limit;
    }
    
    public int getTopIndex() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            return 0;
        }
        return (int)OS.SendMessage(this.handle, 206, 0L, 0L);
    }
    
    public int getTopPixel() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getTopPixelInPixels());
    }
    
    int getTopPixelInPixels() {
        final int[] buffer = new int[2];
        final long code = OS.SendMessage(this.handle, 1245, 0L, buffer);
        if (code == 1L) {
            return buffer[1];
        }
        return this.getTopIndex() * this.getLineHeightInPixels();
    }
    
    public void insert(String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        string = Display.withCrLf(string);
        if (this.hooks(25) || this.filters(25)) {
            final int[] start = { 0 };
            final int[] end = { 0 };
            OS.SendMessage(this.handle, 176, start, end);
            string = this.verifyText(string, start[0], end[0], null);
            if (string == null) {
                return;
            }
        }
        this.clearSegments(true);
        final TCHAR buffer = new TCHAR(this.getCodePage(), string, true);
        this.ignoreCharacter = true;
        OS.SendMessage(this.handle, 194, 0L, buffer);
        this.ignoreCharacter = false;
        if ((this.state & 0x400000) != 0x0) {
            super.updateTextDirection(100663296);
        }
        this.applySegments();
    }
    
    boolean isUseWsBorder() {
        return super.isUseWsBorder() || (this.display != null && this.display.useWsBorderText);
    }
    
    public void paste() {
        this.checkWidget();
        if ((this.style & 0x8) != 0x0) {
            return;
        }
        OS.SendMessage(this.handle, 770, 0L, 0L);
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.message = null;
    }
    
    public void removeModifyListener(final ModifyListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(24, (SWTEventListener)listener);
    }
    
    public void removeSegmentListener(final SegmentListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        this.eventTable.unhook(49, (SWTEventListener)listener);
        this.clearSegments(true);
        this.applySegments();
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
    
    public void removeVerifyListener(final VerifyListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(25, (SWTEventListener)listener);
    }
    
    int resolveTextDirection() {
        int textDirection = 0;
        final int length = OS.GetWindowTextLength(this.handle);
        if (length > 0) {
            TCHAR buffer = new TCHAR(this.getCodePage(), length + 1);
            OS.GetWindowText(this.handle, buffer, length + 1);
            if (this.segments != null) {
                buffer = this.deprocessText(buffer, 0, -1, false);
                textDirection = BidiUtil.resolveTextDirection(buffer.toString());
            }
            else {
                textDirection = BidiUtil.resolveTextDirection(buffer.toString(0, length));
            }
            if (textDirection == 0) {
                textDirection = (((this.style & 0x4000000) != 0x0) ? 67108864 : 33554432);
            }
        }
        return textDirection;
    }
    
    public void selectAll() {
        this.checkWidget();
        OS.SendMessage(this.handle, 177, 0L, -1L);
    }
    
    boolean sendKeyEvent(final int type, final int msg, final long wParam, final long lParam, final Event event) {
        if (!super.sendKeyEvent(type, msg, wParam, lParam, event)) {
            return false;
        }
        if ((this.style & 0x8) != 0x0) {
            return true;
        }
        if (this.ignoreVerify) {
            return true;
        }
        if (type != 1) {
            return true;
        }
        if (msg != 258 && msg != 256 && msg != 646) {
            return true;
        }
        if (event.character == '\0') {
            return true;
        }
        if (!this.hooks(25) && !this.filters(25)) {
            return true;
        }
        final char key = event.character;
        final int stateMask = event.stateMask;
        switch (msg) {
            case 258: {
                if (key != '\b' && key != '\u007f' && key != '\r' && key != '\t' && key != '\n') {
                    break;
                }
            }
            case 256: {
                if ((stateMask & 0x70000) != 0x0) {
                    return false;
                }
                break;
            }
        }
        if (OS.GetKeyState(1) < 0 && this.handle == OS.GetCapture()) {
            return true;
        }
        String oldText = "";
        final int[] start = { 0 };
        final int[] end = { 0 };
        OS.SendMessage(this.handle, 176, start, end);
        switch (key) {
            case '\b': {
                if (start[0] != end[0]) {
                    break;
                }
                if (start[0] == 0) {
                    return true;
                }
                final int lineStart = (int)OS.SendMessage(this.handle, 187, -1L, 0L);
                if (start[0] == lineStart) {
                    final int[] array = start;
                    final int n = 0;
                    array[n] -= Text.DELIMITER.length();
                }
                else {
                    final int[] array2 = start;
                    final int n2 = 0;
                    --array2[n2];
                }
                start[0] = Math.max(start[0], 0);
                break;
            }
            case '\u007f': {
                if (start[0] != end[0]) {
                    break;
                }
                final int length = OS.GetWindowTextLength(this.handle);
                if (start[0] == length) {
                    return true;
                }
                final int line = (int)OS.SendMessage(this.handle, 201, (long)end[0], 0L);
                final int lineStart2 = (int)OS.SendMessage(this.handle, 187, (long)(line + 1), 0L);
                if (end[0] == lineStart2 - Text.DELIMITER.length()) {
                    final int[] array3 = end;
                    final int n3 = 0;
                    array3[n3] += Text.DELIMITER.length();
                }
                else {
                    final int[] array4 = end;
                    final int n4 = 0;
                    ++array4[n4];
                }
                end[0] = Math.min(end[0], length);
                break;
            }
            case '\r': {
                if ((this.style & 0x4) != 0x0) {
                    return true;
                }
                oldText = Text.DELIMITER;
                break;
            }
            default: {
                if (key != '\t' && key < ' ') {
                    return true;
                }
                oldText = new String(new char[] { key });
                break;
            }
        }
        String newText = this.verifyText(oldText, start[0], end[0], event);
        if (newText == null) {
            return false;
        }
        if (newText == oldText) {
            return true;
        }
        newText = Display.withCrLf(newText);
        final TCHAR buffer = new TCHAR(this.getCodePage(), newText, true);
        OS.SendMessage(this.handle, 177, (long)start[0], (long)end[0]);
        this.ignoreCharacter = true;
        OS.SendMessage(this.handle, 194, 0L, buffer);
        return this.ignoreCharacter = false;
    }
    
    void setBackgroundImage(final long hBitmap) {
        final int flags = 133;
        OS.RedrawWindow(this.handle, (RECT)null, 0L, 133);
    }
    
    void setBackgroundPixel(final int pixel) {
        this.maybeEnableDarkSystemTheme();
        final int flags = 133;
        OS.RedrawWindow(this.handle, (RECT)null, 0L, 133);
    }
    
    void setBoundsInPixels(final int x, final int y, final int width, final int height, final int flags) {
        if ((flags & 0x1) == 0x0 && width != 0) {
            final RECT rect = new RECT();
            OS.GetWindowRect(this.handle, rect);
            final long margins = OS.SendMessage(this.handle, 212, 0L, 0L);
            final int marginWidth = OS.LOWORD(margins) + OS.HIWORD(margins);
            if (rect.right - rect.left <= marginWidth) {
                final int[] start = { 0 };
                final int[] end = { 0 };
                OS.SendMessage(this.handle, 176, start, end);
                if (start[0] != 0 || end[0] != 0) {
                    OS.SetWindowPos(this.handle, 0L, x, y, width, height, flags);
                    OS.SendMessage(this.handle, 177, 0L, 0L);
                    OS.SendMessage(this.handle, 177, (long)start[0], (long)end[0]);
                    return;
                }
            }
        }
        super.setBoundsInPixels(x, y, width, height, flags);
        if ((flags & 0x1) == 0x0) {
            final int bits = OS.GetWindowLong(this.handle, -16);
            if ((bits & 0x4) != 0x0) {
                long oldFont = 0L;
                final long hDC = OS.GetDC(this.handle);
                final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
                if (newFont != 0L) {
                    oldFont = OS.SelectObject(hDC, newFont);
                }
                final TEXTMETRIC tm = new TEXTMETRIC();
                OS.GetTextMetrics(hDC, tm);
                if (newFont != 0L) {
                    OS.SelectObject(hDC, oldFont);
                }
                OS.ReleaseDC(this.handle, hDC);
                final RECT rect2 = new RECT();
                OS.GetClientRect(this.handle, rect2);
                if (rect2.bottom - rect2.top < tm.tmHeight) {
                    final long margins2 = OS.SendMessage(this.handle, 212, 0L, 0L);
                    final RECT rect5;
                    final RECT rect3 = rect5 = rect2;
                    rect5.left += OS.LOWORD(margins2);
                    final RECT rect6;
                    final RECT rect4 = rect6 = rect2;
                    rect6.right -= OS.HIWORD(margins2);
                    rect2.top = 0;
                    rect2.bottom = tm.tmHeight;
                    OS.SendMessage(this.handle, 179, 0L, rect2);
                }
            }
        }
    }
    
    void setDefaultFont() {
        super.setDefaultFont();
        this.setMargins();
    }
    
    public void setDoubleClickEnabled(final boolean doubleClick) {
        this.checkWidget();
        this.doubleClick = doubleClick;
    }
    
    public void setEchoChar(final char echo) {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        this.allowPasswordChar = true;
        OS.SendMessage(this.handle, 204, (long)echo, 0L);
        this.allowPasswordChar = false;
        OS.InvalidateRect(this.handle, (RECT)null, true);
    }
    
    public void setEditable(final boolean editable) {
        this.checkWidget();
        this.style &= 0xFFFFFFF7;
        if (!editable) {
            this.style |= 0x8;
        }
        OS.SendMessage(this.handle, 207, (long)(editable ? 0 : 1), 0L);
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        super.setFont(font);
        this.setTabStops(this.tabs);
        this.setMargins();
    }
    
    void setForegroundPixel(final int pixel) {
        this.maybeEnableDarkSystemTheme();
        super.setForegroundPixel(pixel);
    }
    
    void setMargins() {
        if ((this.style & 0x80) != 0x0) {
            final boolean rtl = (this.style & 0x4000000) != 0x0;
            final int fLeading = rtl ? 2 : 1;
            final int fTrailing = rtl ? 1 : 2;
            int flags = 0;
            if ((this.style & 0x200) != 0x0) {
                flags |= fLeading;
            }
            if ((this.style & 0x100) != 0x0) {
                flags |= fTrailing;
            }
            if (flags != 0) {
                final int iconWidth = OS.GetSystemMetrics(49);
                OS.SendMessage(this.handle, 211, (long)flags, OS.MAKELPARAM(iconWidth, iconWidth));
            }
        }
    }
    
    public void setMessage(final String message) {
        this.checkWidget();
        if (message == null) {
            this.error(4);
        }
        this.message = message;
        final int bits = OS.GetWindowLong(this.handle, -16);
        if ((bits & 0x4) == 0x0) {
            final int length = message.length();
            final char[] chars = new char[length + 1];
            message.getChars(0, length, chars, 0);
            OS.SendMessage(this.handle, 5377, 0L, chars);
        }
    }
    
    public void setOrientation(final int orientation) {
        super.setOrientation(orientation);
    }
    
    public void setSelection(int start) {
        this.checkWidget();
        start = this.translateOffset(start);
        OS.SendMessage(this.handle, 177, (long)start, (long)start);
        OS.SendMessage(this.handle, 183, 0L, 0L);
    }
    
    public void setSelection(int start, int end) {
        this.checkWidget();
        start = this.translateOffset(start);
        end = this.translateOffset(end);
        OS.SendMessage(this.handle, 177, (long)start, (long)end);
        OS.SendMessage(this.handle, 183, 0L, 0L);
    }
    
    public void setRedraw(final boolean redraw) {
        this.checkWidget();
        super.setRedraw(redraw);
        if (!this.getDrawing()) {
            return;
        }
        final int[] start = { 0 };
        final int[] end = { 0 };
        OS.SendMessage(this.handle, 176, start, end);
        if (!redraw) {
            this.oldStart = start[0];
            this.oldEnd = end[0];
        }
        else {
            if (this.oldStart == start[0] && this.oldEnd == end[0]) {
                return;
            }
            OS.SendMessage(this.handle, 183, 0L, 0L);
        }
    }
    
    public void setSelection(final Point selection) {
        this.checkWidget();
        if (selection == null) {
            this.error(4);
        }
        this.setSelection(selection.x, selection.y);
    }
    
    public void setTabs(final int tabs) {
        this.checkWidget();
        if (tabs < 0) {
            return;
        }
        this.setTabStops(this.tabs = tabs);
    }
    
    void setTabStops(final int tabs) {
        final int width = this.getTabWidth(tabs) * 4 / OS.LOWORD((long)OS.GetDialogBaseUnits());
        OS.SendMessage(this.handle, 203, 1L, new int[] { width });
    }
    
    public void setText(String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        string = Display.withCrLf(string);
        if (this.hooks(25) || this.filters(25)) {
            final int length = OS.GetWindowTextLength(this.handle);
            string = this.verifyText(string, 0, length, null);
            if (string == null) {
                return;
            }
        }
        this.clearSegments(false);
        final int limit = (int)OS.SendMessage(this.handle, 213, 0L, 0L) & Integer.MAX_VALUE;
        if (string.length() > limit) {
            string = string.substring(0, limit);
        }
        final TCHAR buffer = new TCHAR(this.getCodePage(), string, true);
        OS.SetWindowText(this.handle, buffer);
        if ((this.state & 0x400000) != 0x0) {
            super.updateTextDirection(100663296);
        }
        this.applySegments();
        final int bits = OS.GetWindowLong(this.handle, -16);
        if ((bits & 0x4) != 0x0) {
            this.sendEvent(24);
        }
    }
    
    public void setTextChars(char[] text) {
        this.checkWidget();
        if (text == null) {
            this.error(4);
        }
        text = Display.withCrLf(text);
        if (this.hooks(25) || this.filters(25)) {
            final int length = OS.GetWindowTextLength(this.handle);
            final String string = this.verifyText(new String(text), 0, length, null);
            if (string == null) {
                return;
            }
            text = new char[string.length()];
            string.getChars(0, text.length, text, 0);
        }
        this.clearSegments(false);
        final int limit = (int)OS.SendMessage(this.handle, 213, 0L, 0L) & Integer.MAX_VALUE;
        if (text.length > limit) {
            final char[] temp = new char[limit];
            System.arraycopy(text, 0, temp, 0, limit);
            text = temp;
        }
        final TCHAR buffer = new TCHAR(this.getCodePage(), text, true);
        OS.SetWindowText(this.handle, buffer);
        buffer.clear();
        if ((this.state & 0x400000) != 0x0) {
            super.updateTextDirection(100663296);
        }
        this.applySegments();
        final int bits = OS.GetWindowLong(this.handle, -16);
        if ((bits & 0x4) != 0x0) {
            this.sendEvent(24);
        }
    }
    
    public void setTextLimit(final int limit) {
        this.checkWidget();
        if (limit == 0) {
            this.error(7);
        }
        if (this.segments != null && limit > 0) {
            OS.SendMessage(this.handle, 197, (long)(limit + Math.min(this.segments.length, Text.LIMIT - limit)), 0L);
        }
        else {
            OS.SendMessage(this.handle, 197, (long)limit, 0L);
        }
    }
    
    public void setTopIndex(int index) {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            return;
        }
        final int count = (int)OS.SendMessage(this.handle, 186, 0L, 0L);
        index = Math.min(Math.max(index, 0), count - 1);
        final int topIndex = (int)OS.SendMessage(this.handle, 206, 0L, 0L);
        OS.SendMessage(this.handle, 182, 0L, (long)(index - topIndex));
    }
    
    public void showSelection() {
        this.checkWidget();
        OS.SendMessage(this.handle, 183, 0L, 0L);
    }
    
    int translateOffset(int offset) {
        if (this.segments == null) {
            return offset;
        }
        for (int i = 0, nSegments = this.segments.length; i < nSegments && offset - i >= this.segments[i]; ++offset, ++i) {}
        return offset;
    }
    
    int untranslateOffset(int offset) {
        if (this.segments == null) {
            return offset;
        }
        for (int i = 0, nSegments = this.segments.length; i < nSegments && offset > this.segments[i]; --offset, ++i) {}
        return offset;
    }
    
    void updateMenuLocation(final Event event) {
        final Point point = this.display.mapInPixels((Control)this, (Control)null, this.getCaretLocationInPixels());
        event.setLocationInPixels(point.x, point.y + this.getLineHeightInPixels());
    }
    
    void updateOrientation() {
        int bits = OS.GetWindowLong(this.handle, -20);
        if ((this.style & 0x4000000) != 0x0) {
            bits |= 0x6000;
        }
        else {
            bits &= 0xFFFF9FFF;
        }
        OS.SetWindowLong(this.handle, -20, bits);
        this.fixAlignment();
    }
    
    boolean updateTextDirection(final int textDirection) {
        if (super.updateTextDirection(textDirection)) {
            this.clearSegments(true);
            this.applySegments();
            return true;
        }
        return false;
    }
    
    String verifyText(final String string, final int start, final int end, final Event keyEvent) {
        if (this.ignoreVerify) {
            return string;
        }
        final Event event = new Event();
        event.text = string;
        event.start = start;
        event.end = end;
        if (keyEvent != null) {
            event.character = keyEvent.character;
            event.keyCode = keyEvent.keyCode;
            event.stateMask = keyEvent.stateMask;
        }
        event.start = this.untranslateOffset(event.start);
        event.end = this.untranslateOffset(event.end);
        this.sendEvent(25, event);
        if (!event.doit || this.isDisposed()) {
            return null;
        }
        return event.text;
    }
    
    int widgetStyle() {
        int bits = super.widgetStyle() | 0x80;
        if ((this.style & 0x80) != 0x0) {
            bits &= 0xFFEFFFFF;
            bits &= 0xFFDFFFFF;
        }
        if ((this.style & 0x400000) != 0x0) {
            bits |= 0x20;
        }
        if ((this.style & 0x1000000) != 0x0) {
            bits |= 0x1;
        }
        if ((this.style & 0x20000) != 0x0) {
            bits |= 0x2;
        }
        if ((this.style & 0x8) != 0x0) {
            bits |= 0x800;
        }
        if ((this.style & 0x80) != 0x0) {
            bits |= 0x2000000;
        }
        if ((this.style & 0x4) != 0x0) {
            if ((this.style & 0x8) != 0x0 && (this.style & 0x400B00) == 0x0 && OS.IsAppThemed()) {
                bits |= 0x4;
            }
            return bits;
        }
        bits |= 0x144;
        if ((this.style & 0x40) != 0x0) {
            bits &= 0xFFEFFF7F;
        }
        return bits;
    }
    
    TCHAR windowClass() {
        return Text.EditClass;
    }
    
    long windowProc() {
        return Text.EditProc;
    }
    
    long windowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        boolean processSegments = this.hooks(49) || this.filters(49);
        boolean redraw = false;
        boolean updateDirection = (this.state & 0x400000) != 0x0;
        if (processSegments || updateDirection) {
            switch (msg) {
                case 198: {
                    if (processSegments) {
                        return 0L;
                    }
                    updateDirection = false;
                    break;
                }
                case 199:
                case 772: {
                    if (processSegments) {
                        return 0L;
                    }
                    break;
                }
                case 256: {
                    if (wParam != 46L) {
                        processSegments = (updateDirection = false);
                        break;
                    }
                    break;
                }
                case 769: {
                    processSegments = (this.segments != null);
                    updateDirection = false;
                    break;
                }
                case 258: {
                    if (this.ignoreCharacter || OS.GetKeyState(17) < 0 || OS.GetKeyState(18) < 0) {
                        processSegments = (updateDirection = false);
                        break;
                    }
                    break;
                }
                case 768:
                case 770:
                case 771: {
                    break;
                }
                default: {
                    processSegments = (updateDirection = false);
                    break;
                }
            }
        }
        if (processSegments) {
            if (this.getDrawing() && OS.IsWindowVisible(this.handle)) {
                redraw = true;
                OS.DefWindowProc(this.handle, 11, 0L, 0L);
            }
            this.clearSegments(true);
        }
        if (msg == 199) {
            final int bits = OS.GetWindowLong(this.handle, -16);
            if ((bits & 0x4) == 0x0) {
                final LRESULT result = this.wmClipboard(199, wParam, lParam);
                if (result != null) {
                    return result.value;
                }
                return this.callWindowProc(hwnd, 199, wParam, lParam);
            }
        }
        if (msg == 204 && !this.allowPasswordChar) {
            return 1L;
        }
        if (msg == Display.SWT_RESTORECARET) {
            this.callWindowProc(hwnd, 8, 0L, 0L);
            this.callWindowProc(hwnd, 7, 0L, 0L);
            return 1L;
        }
        final long code = super.windowProc(hwnd, msg, wParam, lParam);
        if (updateDirection) {
            super.updateTextDirection(100663296);
        }
        if (processSegments) {
            this.applySegments();
            if (redraw) {
                OS.DefWindowProc(this.handle, 11, 1L, 0L);
                OS.InvalidateRect(this.handle, (RECT)null, true);
            }
            OS.SendMessage(this.handle, 183, 0L, 0L);
        }
        return code;
    }
    
    LRESULT WM_CHAR(final long wParam, final long lParam) {
        if (this.ignoreCharacter) {
            return null;
        }
        final LRESULT result = super.WM_CHAR(wParam, lParam);
        if (result != null) {
            return result;
        }
        switch ((int)wParam) {
            case 127: {
                if (OS.GetKeyState(17) >= 0) {
                    break;
                }
                if ((this.style & 0x8) != 0x0 || (this.style & 0x400000) != 0x0) {
                    return LRESULT.ZERO;
                }
                final Point selection = this.getSelection();
                int x = selection.x;
                int y = selection.y;
                if (x == y) {
                    final String actText = this.getText(0, x - 1);
                    final Matcher m = Text.CTRL_BS_PATTERN.matcher(actText);
                    if (m.find()) {
                        x = m.start();
                        y = m.end();
                        OS.SendMessage(this.handle, 177, (long)x, (long)y);
                    }
                }
                if (x < y) {
                    OS.SendMessage(this.handle, 194, 1L, 0L);
                }
                return LRESULT.ZERO;
            }
        }
        if ((this.style & 0x4) != 0x0) {
            switch ((int)wParam) {
                case 13: {
                    this.sendSelectionEvent(14);
                }
                case 9:
                case 27: {
                    return LRESULT.ZERO;
                }
            }
        }
        return result;
    }
    
    LRESULT WM_CLEAR(final long wParam, final long lParam) {
        final LRESULT result = super.WM_CLEAR(wParam, lParam);
        if (result != null) {
            return result;
        }
        return this.wmClipboard(771, wParam, lParam);
    }
    
    LRESULT WM_CUT(final long wParam, final long lParam) {
        final LRESULT result = super.WM_CUT(wParam, lParam);
        if (result != null) {
            return result;
        }
        return this.wmClipboard(768, wParam, lParam);
    }
    
    LRESULT WM_DRAWITEM(final long wParam, final long lParam) {
        final DRAWITEMSTRUCT struct = new DRAWITEMSTRUCT();
        OS.MoveMemory(struct, lParam, DRAWITEMSTRUCT.sizeof);
        final RECT rect = new RECT();
        OS.SetRect(rect, struct.left, struct.top, struct.right, struct.bottom);
        final POINT pt = new POINT();
        OS.MapWindowPoints(struct.hwndItem, this.handle, pt, 1);
        this.drawBackground(struct.hDC, rect, -1, pt.x, pt.y);
        if (struct.CtlID == 256 && struct.hwndItem == this.hwndActiveIcon && OS.IsAppThemed()) {
            final int state = (OS.GetKeyState(1) < 0) ? 3 : 2;
            OS.DrawThemeBackground(this.display.hButtonThemeAuto(), struct.hDC, 1, state, rect, (RECT)null);
        }
        final long hIcon = (struct.CtlID == 512) ? this.display.hIconSearch : this.display.hIconCancel;
        final int y = (rect.bottom - rect.right) / 2;
        OS.DrawIconEx(struct.hDC, 0, y, hIcon, 0, 0, 0, 0L, 3);
        return LRESULT.ONE;
    }
    
    LRESULT WM_ERASEBKGND(final long wParam, final long lParam) {
        final LRESULT result = super.WM_ERASEBKGND(wParam, lParam);
        if ((this.style & 0x8) != 0x0 && (this.style & 0xB00) == 0x0) {
            final int bits = OS.GetWindowLong(this.handle, -16);
            if ((bits & 0x4) != 0x0) {
                Control control = this.findBackgroundControl();
                if (control == null && this.background == -1 && (this.state & 0x100) != 0x0 && OS.IsAppThemed()) {
                    control = this.findThemeControl();
                    if (control != null) {
                        final RECT rect = new RECT();
                        OS.GetClientRect(this.handle, rect);
                        this.fillThemeBackground(wParam, control, rect);
                        return LRESULT.ONE;
                    }
                }
            }
        }
        return result;
    }
    
    LRESULT WM_GETDLGCODE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_GETDLGCODE(wParam, lParam);
        if (result != null) {
            return result;
        }
        if ((this.style & 0x8) != 0x0) {
            long code = this.callWindowProc(this.handle, 135, wParam, lParam);
            code &= 0xFFFFFFFFFFFFFFF9L;
            return new LRESULT(code);
        }
        return null;
    }
    
    LRESULT WM_GETOBJECT(final long wParam, final long lParam) {
        if ((this.style & 0x80) != 0x0 && this.accessible == null) {
            this.accessible = this.new_Accessible((Control)this);
        }
        return super.WM_GETOBJECT(wParam, lParam);
    }
    
    LRESULT WM_IME_CHAR(final long wParam, final long lParam) {
        final Display display = this.display;
        display.lastKey = 0;
        display.lastAscii = (int)wParam;
        final Display display2 = display;
        final Display display3 = display;
        final Display display4 = display;
        final boolean lastVirtual = false;
        display4.lastDead = false;
        display3.lastNull = false;
        display2.lastVirtual = false;
        if (!this.sendKeyEvent(1, 646, wParam, lParam)) {
            return LRESULT.ZERO;
        }
        this.ignoreCharacter = true;
        final long result = this.callWindowProc(this.handle, 646, wParam, lParam);
        final MSG msg = new MSG();
        final int flags = 10420227;
        while (OS.PeekMessage(msg, this.handle, 258, 258, 10420227)) {
            OS.TranslateMessage(msg);
            OS.DispatchMessage(msg);
        }
        this.ignoreCharacter = false;
        this.sendKeyEvent(2, 646, wParam, lParam);
        final Display display5 = display;
        final Display display6 = display;
        final int n = 0;
        display6.lastAscii = 0;
        display5.lastKey = 0;
        return new LRESULT(result);
    }
    
    LRESULT WM_LBUTTONDBLCLK(final long wParam, final long lParam) {
        LRESULT result = null;
        this.sendMouseEvent(3, 1, this.handle, lParam);
        if (!this.sendMouseEvent(8, 1, this.handle, lParam)) {
            result = LRESULT.ZERO;
        }
        if (!this.display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
            OS.SetCapture(this.handle);
        }
        if (!this.doubleClick) {
            return LRESULT.ZERO;
        }
        final int[] start = { 0 };
        final int[] end = { 0 };
        OS.SendMessage(this.handle, 176, start, end);
        if (start[0] == end[0]) {
            final int length = OS.GetWindowTextLength(this.handle);
            if (length == start[0]) {
                final int code = (int)OS.SendMessage(this.handle, 193, (long)length, 0L);
                if (code == 0) {
                    return LRESULT.ZERO;
                }
            }
        }
        return result;
    }
    
    LRESULT WM_PASTE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_PASTE(wParam, lParam);
        if (result != null) {
            return result;
        }
        return this.wmClipboard(770, wParam, lParam);
    }
    
    LRESULT WM_SETCURSOR(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SETCURSOR(wParam, lParam);
        if (result != null) {
            return result;
        }
        if ((this.style & 0x80) != 0x0) {
            final int hitTest = (short)OS.LOWORD(lParam);
            if (hitTest == 1) {
                final POINT pt = new POINT();
                OS.GetCursorPos(pt);
                OS.ScreenToClient(this.handle, pt);
                final long hwndIcon = OS.ChildWindowFromPointEx(this.handle, pt, 1);
                if (hwndIcon != this.handle) {
                    OS.SetCursor(OS.LoadCursor(0L, 32512L));
                    return LRESULT.ONE;
                }
            }
        }
        return null;
    }
    
    LRESULT WM_SIZE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SIZE(wParam, lParam);
        if (this.isDisposed()) {
            return result;
        }
        if ((this.style & 0x80) != 0x0) {
            final boolean rtl = (this.style & 0x4000000) != 0x0;
            final long hwndLeading = OS.GetDlgItem(this.handle, rtl ? 256 : 512);
            final long hwndTrailing = OS.GetDlgItem(this.handle, rtl ? 512 : 256);
            final int width = OS.LOWORD(lParam);
            final int height = OS.HIWORD(lParam);
            final int iconWidth = OS.GetSystemMetrics(49);
            final int flags = 276;
            if (hwndLeading != 0L) {
                OS.SetWindowPos(hwndLeading, 0L, 0, 0, iconWidth, height, 276);
            }
            if (hwndTrailing != 0L) {
                OS.SetWindowPos(hwndTrailing, 0L, width - iconWidth, 0, iconWidth, height, 276);
            }
        }
        return result;
    }
    
    LRESULT WM_UNDO(final long wParam, final long lParam) {
        final LRESULT result = super.WM_UNDO(wParam, lParam);
        if (result != null) {
            return result;
        }
        return this.wmClipboard(772, wParam, lParam);
    }
    
    LRESULT wmClipboard(final int msg, final long wParam, final long lParam) {
        if ((this.style & 0x8) != 0x0) {
            return null;
        }
        if (!this.hooks(25) && !this.filters(25)) {
            return null;
        }
        boolean call = false;
        final int[] start = { 0 };
        final int[] end = { 0 };
        String newText = null;
        switch (msg) {
            case 768:
            case 771: {
                OS.SendMessage(this.handle, 176, start, end);
                if (start[0] != end[0]) {
                    newText = "";
                    call = true;
                    break;
                }
                break;
            }
            case 770: {
                OS.SendMessage(this.handle, 176, start, end);
                newText = this.getClipboardText();
                break;
            }
            case 199:
            case 772: {
                if (OS.SendMessage(this.handle, 198, 0L, 0L) != 0L) {
                    final boolean b = true;
                    this.ignoreCharacter = true;
                    this.ignoreModify = true;
                    this.callWindowProc(this.handle, msg, wParam, lParam);
                    final int length = OS.GetWindowTextLength(this.handle);
                    final int[] newStart = { 0 };
                    final int[] newEnd = { 0 };
                    OS.SendMessage(this.handle, 176, newStart, newEnd);
                    if (length != 0 && newStart[0] != newEnd[0]) {
                        final char[] buffer = new char[length + 1];
                        OS.GetWindowText(this.handle, buffer, length + 1);
                        newText = new String(buffer, newStart[0], newEnd[0] - newStart[0]);
                    }
                    else {
                        newText = "";
                    }
                    this.callWindowProc(this.handle, msg, wParam, lParam);
                    OS.SendMessage(this.handle, 176, start, end);
                    final boolean b2 = false;
                    this.ignoreCharacter = false;
                    this.ignoreModify = false;
                    break;
                }
                break;
            }
        }
        if (newText != null) {
            final String oldText = newText;
            newText = this.verifyText(newText, start[0], end[0], null);
            if (newText == null) {
                return LRESULT.ZERO;
            }
            if (!newText.equals(oldText)) {
                if (call) {
                    this.callWindowProc(this.handle, msg, wParam, lParam);
                }
                newText = Display.withCrLf(newText);
                final TCHAR buffer2 = new TCHAR(this.getCodePage(), newText, true);
                this.ignoreCharacter = true;
                OS.SendMessage(this.handle, 194, 0L, buffer2);
                this.ignoreCharacter = false;
                return LRESULT.ZERO;
            }
        }
        if (msg == 772) {
            final boolean b3 = true;
            this.ignoreCharacter = true;
            this.ignoreVerify = true;
            this.callWindowProc(this.handle, 772, wParam, lParam);
            final boolean b4 = false;
            this.ignoreCharacter = false;
            this.ignoreVerify = false;
            return LRESULT.ONE;
        }
        return null;
    }
    
    LRESULT wmColorChild(final long wParam, final long lParam) {
        if ((this.style & 0x8) != 0x0 && (this.style & 0xB00) == 0x0) {
            final int bits = OS.GetWindowLong(this.handle, -16);
            if ((bits & 0x4) != 0x0) {
                Control control = this.findBackgroundControl();
                if (control == null && this.background == -1 && (this.state & 0x100) != 0x0 && OS.IsAppThemed()) {
                    control = this.findThemeControl();
                    if (control != null) {
                        OS.SetTextColor(wParam, this.getForegroundPixel());
                        OS.SetBkColor(wParam, this.getBackgroundPixel());
                        OS.SetBkMode(wParam, 1);
                        return new LRESULT(OS.GetStockObject(5));
                    }
                }
            }
        }
        return super.wmColorChild(wParam, lParam);
    }
    
    LRESULT wmCommandChild(final long wParam, final long lParam) {
        final int code = OS.HIWORD(wParam);
        switch (code) {
            case 768: {
                if (this.findImageControl() != null) {
                    OS.InvalidateRect(this.handle, (RECT)null, true);
                }
                if ((this.style & 0x80) != 0x0) {
                    final boolean showCancel = OS.GetWindowTextLength(this.handle) != 0;
                    final long hwndCancel = OS.GetDlgItem(this.handle, 256);
                    if (hwndCancel != 0L) {
                        OS.ShowWindow(hwndCancel, showCancel ? 5 : 0);
                    }
                }
                if (this.ignoreModify) {
                    break;
                }
                this.sendEvent(24);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
                break;
            }
            case 1792:
            case 1793: {
                this.state &= 0xFFBFFFFF;
                int bits = OS.GetWindowLong(this.handle, -20);
                if ((bits & 0x2000) != 0x0) {
                    this.style &= 0xFDFFFFFF;
                    this.style |= 0x4000000;
                }
                else {
                    this.style &= 0xFBFFFFFF;
                    this.style |= 0x2000000;
                }
                final Event event = new Event();
                event.doit = true;
                this.sendEvent(44, event);
                if (!event.doit) {
                    if (code == 1792) {
                        bits |= 0x6000;
                        this.style &= 0xFDFFFFFF;
                        this.style |= 0x4000000;
                    }
                    else {
                        bits &= 0xFFFF9FFF;
                        this.style &= 0xFBFFFFFF;
                        this.style |= 0x2000000;
                    }
                    OS.SetWindowLong(this.handle, -20, bits);
                }
                else {
                    this.clearSegments(true);
                    this.applySegments();
                }
                this.fixAlignment();
                break;
            }
        }
        return super.wmCommandChild(wParam, lParam);
    }
    
    LRESULT wmKeyDown(final long hwnd, final long wParam, final long lParam) {
        LRESULT result = super.wmKeyDown(hwnd, wParam, lParam);
        if (result != null) {
            return result;
        }
        if (this.segments != null) {
            switch ((int)wParam) {
                case 37:
                case 38:
                case 39:
                case 40: {
                    long code = 0L;
                    final int[] start = { 0 };
                    final int[] end = { 0 };
                    final int[] newStart = { 0 };
                    final int[] newEnd = { 0 };
                    OS.SendMessage(this.handle, 176, start, end);
                    while (true) {
                        code = this.callWindowProc(this.handle, 256, wParam, lParam);
                        OS.SendMessage(this.handle, 176, newStart, newEnd);
                        if (newStart[0] != start[0]) {
                            if (this.untranslateOffset(newStart[0]) != this.untranslateOffset(start[0])) {
                                break;
                            }
                        }
                        else {
                            if (newEnd[0] == end[0]) {
                                break;
                            }
                            if (this.untranslateOffset(newEnd[0]) != this.untranslateOffset(end[0])) {
                                break;
                            }
                        }
                        start[0] = newStart[0];
                        end[0] = newEnd[0];
                    }
                    result = ((code == 0L) ? LRESULT.ZERO : new LRESULT(code));
                    break;
                }
            }
        }
        return result;
    }
    
    static {
        LIMIT = Integer.MAX_VALUE;
        DELIMITER = "\r\n";
        EditClass = new TCHAR(0, "EDIT", true);
        final WNDCLASS lpWndClass = new WNDCLASS();
        OS.GetClassInfo(0L, Text.EditClass, lpWndClass);
        EditProc = lpWndClass.lpfnWndProc;
    }
}
