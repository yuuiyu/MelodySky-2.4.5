//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import java.util.regex.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.*;

public class Combo extends Composite
{
    boolean noSelection;
    boolean ignoreDefaultSelection;
    boolean ignoreCharacter;
    boolean ignoreModify;
    boolean ignoreResize;
    boolean lockText;
    int scrollWidth;
    int visibleCount;
    long cbtHook;
    String[] items;
    int[] segments;
    int clearSegmentsCount;
    boolean stateFlagsUsable;
    static final char LTR_MARK = '\u200e';
    static final char RTL_MARK = '\u200f';
    static final int VISIBLE_COUNT = 5;
    public static final int LIMIT;
    static final int CBID_LIST = 1000;
    static final int CBID_EDIT = 1001;
    static long EditProc;
    static long ListProc;
    static final long ComboProc;
    static final TCHAR ComboClass;
    static final int stateFlagsOffset;
    static final int stateFlagsFirstPaint = 33554432;
    private static final Pattern CTRL_BS_PATTERN;
    
    public Combo(final Composite parent, final int style) {
        super(parent, checkStyle(style));
        this.items = new String[0];
        this.clearSegmentsCount = 0;
        this.style |= 0x100;
    }
    
    public void add(final String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        final TCHAR buffer = new TCHAR(this.getCodePage(), string, true);
        final int result = (int)OS.SendMessage(this.handle, 323, 0L, buffer);
        if (result == -1) {
            this.error(14);
        }
        if (result == -2) {
            this.error(14);
        }
        if ((this.style & 0x100) != 0x0) {
            this.setScrollWidth(buffer.chars, true);
        }
    }
    
    public void add(final String string, final int index) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        final int count = (int)OS.SendMessage(this.handle, 326, 0L, 0L);
        if (0 > index || index > count) {
            this.error(6);
        }
        final TCHAR buffer = new TCHAR(this.getCodePage(), string, true);
        final int result = (int)OS.SendMessage(this.handle, 330, (long)index, buffer);
        if (result == -2 || result == -1) {
            this.error(14);
        }
        if ((this.style & 0x100) != 0x0) {
            this.setScrollWidth(buffer.chars, true);
        }
    }
    
    public void addModifyListener(final ModifyListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(24, typedListener);
    }
    
    public void addSegmentListener(final SegmentListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        this.addListener(49, new TypedListener((SWTEventListener)listener));
        int selection = -1;
        if (!this.noSelection) {
            selection = (int)OS.SendMessage(this.handle, 327, 0L, 0L);
        }
        this.clearSegments(true);
        this.applyEditSegments();
        this.applyListSegments();
        if (selection != -1) {
            OS.SendMessage(this.handle, 334, (long)selection, 0L);
        }
    }
    
    public void addSelectionListener(final SelectionListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(13, typedListener);
        this.addListener(14, typedListener);
    }
    
    public void addVerifyListener(final VerifyListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(25, typedListener);
    }
    
    void applyEditSegments() {
        final int clearSegmentsCount = this.clearSegmentsCount - 1;
        this.clearSegmentsCount = clearSegmentsCount;
        if (clearSegmentsCount != 0) {
            return;
        }
        if (!this.hooks(49) && !this.filters(49) && (this.state & 0x400000) == 0x0) {
            return;
        }
        final long hwndText = OS.GetDlgItem(this.handle, 1001);
        int length = OS.GetWindowTextLength(hwndText);
        final char[] buffer = new char[length + 1];
        if (length > 0) {
            OS.GetWindowText(hwndText, buffer, length + 1);
        }
        final String string = new String(buffer, 0, length);
        this.segments = null;
        final Event event = this.getSegments(string);
        if (event == null || event.segments == null) {
            return;
        }
        this.segments = event.segments;
        final int nSegments = this.segments.length;
        if (nSegments == 0) {
            return;
        }
        final char[] segmentsChars = event.segmentsChars;
        final int limit = (int)OS.SendMessage(hwndText, 213, 0L, 0L) & Integer.MAX_VALUE;
        OS.SendMessage(hwndText, 197, (long)(limit + Math.min(nSegments, Combo.LIMIT - limit)), 0L);
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
        OS.SendMessage(hwndText, 176, start, end);
        final boolean oldIgnoreCharacter = this.ignoreCharacter;
        final boolean oldIgnoreModify = this.ignoreModify;
        final boolean b = true;
        this.ignoreModify = true;
        this.ignoreCharacter = true;
        newChars[length] = '\0';
        OS.SendMessage(hwndText, 177, 0L, -1L);
        final long undo = OS.SendMessage(hwndText, 198, 0L, 0L);
        OS.SendMessage(hwndText, 194, undo, newChars);
        start[0] = this.translateOffset(start[0]);
        end[0] = this.translateOffset(end[0]);
        if (segmentsChars != null && segmentsChars.length > 0) {
            final int auto = this.state & 0x400000;
            if (segmentsChars[0] == '\u202b') {
                super.updateTextDirection(67108864);
            }
            else if (segmentsChars[0] == '\u202a') {
                super.updateTextDirection(33554432);
            }
            this.state |= auto;
        }
        OS.SendMessage(hwndText, 177, (long)start[0], (long)end[0]);
        this.ignoreCharacter = oldIgnoreCharacter;
        this.ignoreModify = oldIgnoreModify;
    }
    
    void applyListSegments() {
        final int count = (int)OS.SendMessage(this.handle, 326, 0L, 0L);
        if (count == -1) {
            return;
        }
        final boolean add = this.items.length != count;
        if (add) {
            this.items = new String[count];
        }
        int index = this.items.length;
        int selection = -1;
        final int cp = this.getCodePage();
        if (!this.noSelection) {
            selection = (int)OS.SendMessage(this.handle, 327, 0L, 0L);
        }
        while (index-- > 0) {
            TCHAR buffer = null;
            String string;
            if (add) {
                final int length = (int)OS.SendMessage(this.handle, 329, (long)index, 0L);
                if (length == -1) {
                    this.error(1);
                }
                buffer = new TCHAR(cp, length + 1);
                if (OS.SendMessage(this.handle, 328, (long)index, buffer) == -1L) {
                    return;
                }
                final String[] items = this.items;
                final int n = index;
                final String string2 = buffer.toString(0, length);
                items[n] = string2;
                string = string2;
            }
            else {
                string = this.items[index];
            }
            if (OS.SendMessage(this.handle, 324, (long)index, 0L) == -1L) {
                return;
            }
            if (buffer == null) {
                buffer = new TCHAR(cp, string, true);
            }
            if (OS.SendMessage(this.handle, 330, (long)index, buffer) == -1L) {
                return;
            }
        }
        if (selection != -1) {
            OS.SendMessage(this.handle, 334, (long)selection, 0L);
        }
    }
    
    @Override
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        if (hwnd == this.handle) {
            switch (msg) {
                case 5: {
                    this.ignoreResize = true;
                    final boolean oldLockText = this.lockText;
                    if ((this.style & 0x8) == 0x0) {
                        this.lockText = true;
                    }
                    final long result = OS.CallWindowProc(Combo.ComboProc, hwnd, msg, wParam, lParam);
                    if ((this.style & 0x8) == 0x0) {
                        this.lockText = oldLockText;
                    }
                    this.ignoreResize = false;
                    return result;
                }
                default: {
                    return OS.CallWindowProc(Combo.ComboProc, hwnd, msg, wParam, lParam);
                }
            }
        }
        else {
            final long hwndText = OS.GetDlgItem(this.handle, 1001);
            if (hwnd == hwndText) {
                if (this.lockText && msg == 12) {
                    final long hHeap = OS.GetProcessHeap();
                    final int length = OS.GetWindowTextLength(this.handle);
                    final char[] buffer = new char[length + 1];
                    OS.GetWindowText(this.handle, buffer, length + 1);
                    final int byteCount = buffer.length * 2;
                    final long pszText = OS.HeapAlloc(hHeap, 8, byteCount);
                    OS.MoveMemory(pszText, buffer, byteCount);
                    final long code = OS.CallWindowProc(Combo.EditProc, hwndText, msg, wParam, pszText);
                    OS.HeapFree(hHeap, 0, pszText);
                    return code;
                }
                return OS.CallWindowProc(Combo.EditProc, hwnd, msg, wParam, lParam);
            }
            else {
                final long hwndList = OS.GetDlgItem(this.handle, 1000);
                if (hwnd == hwndList) {
                    return OS.CallWindowProc(Combo.ListProc, hwnd, msg, wParam, lParam);
                }
                return OS.DefWindowProc(hwnd, msg, wParam, lParam);
            }
        }
    }
    
    long CBTProc(final long nCode, final long wParam, final long lParam) {
        if ((int)nCode == 3) {
            final char[] buffer = new char[128];
            final int length = OS.GetClassName(wParam, buffer, buffer.length);
            final String className = new String(buffer, 0, length);
            if (className.equals("Edit") || className.equals("EDIT")) {
                final int bits = OS.GetWindowLong(wParam, -16);
                OS.SetWindowLong(wParam, -16, bits & 0xFFFFFEFF);
            }
        }
        return OS.CallNextHookEx(this.cbtHook, (int)nCode, wParam, lParam);
    }
    
    @Override
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    static int checkStyle(int style) {
        style &= 0xFFFFF7FF;
        style &= 0xFFFFFCFF;
        style = Widget.checkBits(style, 4, 64, 0, 0, 0, 0);
        if ((style & 0x40) != 0x0) {
            return style & 0xFFFFFFF7;
        }
        return style;
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
        final long hwndText = OS.GetDlgItem(this.handle, 1001);
        final int limit = (int)OS.SendMessage(hwndText, 213, 0L, 0L) & Integer.MAX_VALUE;
        if (limit < Combo.LIMIT) {
            OS.SendMessage(hwndText, 197, (long)Math.max(1, limit - nSegments), 0L);
        }
        if (!applyText) {
            this.segments = null;
            return;
        }
        final boolean oldIgnoreCharacter = this.ignoreCharacter;
        final boolean oldIgnoreModify = this.ignoreModify;
        final boolean b = true;
        this.ignoreModify = true;
        this.ignoreCharacter = true;
        final int length = OS.GetWindowTextLength(hwndText);
        final int cp = this.getCodePage();
        TCHAR buffer = new TCHAR(cp, length + 1);
        if (length > 0) {
            OS.GetWindowText(hwndText, buffer, length + 1);
        }
        buffer = this.deprocessText(buffer, 0, -1, true);
        final int[] start = { 0 };
        final int[] end = { 0 };
        OS.SendMessage(hwndText, 176, start, end);
        start[0] = this.untranslateOffset(start[0]);
        end[0] = this.untranslateOffset(end[0]);
        this.segments = null;
        OS.SendMessage(hwndText, 177, 0L, -1L);
        final long undo = OS.SendMessage(hwndText, 198, 0L, 0L);
        OS.SendMessage(hwndText, 194, undo, buffer);
        OS.SendMessage(hwndText, 177, (long)start[0], (long)end[0]);
        this.ignoreCharacter = oldIgnoreCharacter;
        this.ignoreModify = oldIgnoreModify;
    }
    
    public void clearSelection() {
        this.checkWidget();
        OS.SendMessage(this.handle, 322, 0L, -1L);
    }
    
    @Override
    Point computeSizeInPixels(final int wHint, final int hHint, final boolean changed) {
        this.checkWidget();
        int width = 0;
        int height = 0;
        if (wHint == -1) {
            long oldFont = 0L;
            final long hDC = OS.GetDC(this.handle);
            final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
            if (newFont != 0L) {
                oldFont = OS.SelectObject(hDC, newFont);
            }
            final int count = (int)OS.SendMessage(this.handle, 326, 0L, 0L);
            final RECT rect = new RECT();
            int flags = 3072;
            if ((this.style & 0x8) == 0x0) {
                flags |= 0x2000;
            }
            int length = OS.GetWindowTextLength(this.handle);
            char[] buffer = new char[length + 1];
            OS.GetWindowText(this.handle, buffer, length + 1);
            OS.DrawText(hDC, buffer, length, rect, flags);
            width = Math.max(width, rect.right - rect.left);
            if ((this.style & 0x100) != 0x0) {
                width = Math.max(width, this.scrollWidth);
            }
            else {
                for (int i = 0; i < count; ++i) {
                    length = (int)OS.SendMessage(this.handle, 329, (long)i, 0L);
                    if (length != -1) {
                        if (length + 1 > buffer.length) {
                            buffer = new char[length + 1];
                        }
                        final int result = (int)OS.SendMessage(this.handle, 328, (long)i, buffer);
                        if (result != -1) {
                            OS.DrawText(hDC, buffer, length, rect, flags);
                            width = Math.max(width, rect.right - rect.left);
                        }
                    }
                }
            }
            if (newFont != 0L) {
                OS.SelectObject(hDC, oldFont);
            }
            OS.ReleaseDC(this.handle, hDC);
        }
        if (hHint == -1 && (this.style & 0x40) != 0x0) {
            final int count2 = (int)OS.SendMessage(this.handle, 326, 0L, 0L);
            final int itemHeight = (int)OS.SendMessage(this.handle, 340, 0L, 0L);
            height = count2 * itemHeight;
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
        if ((this.style & 0x8) != 0x0) {
            width += 8;
        }
        else {
            final long hwndText = OS.GetDlgItem(this.handle, 1001);
            if (hwndText != 0L) {
                final long margins = OS.SendMessage(hwndText, 212, 0L, 0L);
                final int marginWidth = OS.LOWORD(margins) + OS.HIWORD(margins);
                width += marginWidth + 3;
            }
        }
        final COMBOBOXINFO pcbi = new COMBOBOXINFO();
        pcbi.cbSize = COMBOBOXINFO.sizeof;
        if ((this.style & 0x40) == 0x0 && OS.GetComboBoxInfo(this.handle, pcbi)) {
            width += pcbi.itemLeft + (pcbi.buttonRight - pcbi.buttonLeft);
            height = pcbi.buttonBottom - pcbi.buttonTop + pcbi.buttonTop * 2;
        }
        else {
            final int border = OS.GetSystemMetrics(45);
            width += OS.GetSystemMetrics(2) + border * 2;
            final int textHeight = (int)OS.SendMessage(this.handle, 340, -1L, 0L);
            if ((this.style & 0x4) != 0x0) {
                height = textHeight + 6;
            }
            else {
                height += textHeight + 10;
            }
        }
        if ((this.style & 0x40) != 0x0 && (this.style & 0x100) != 0x0) {
            height += OS.GetSystemMetrics(3);
        }
        return new Point(width, height);
    }
    
    public void copy() {
        this.checkWidget();
        OS.SendMessage(this.handle, 769, 0L, 0L);
    }
    
    @Override
    void createHandle() {
        if ((this.style & 0x48) != 0x0) {
            super.createHandle();
        }
        else {
            final int threadId = OS.GetCurrentThreadId();
            final Callback cbtCallback = new Callback((Object)this, "CBTProc", 3);
            this.cbtHook = OS.SetWindowsHookEx(5, cbtCallback.getAddress(), 0L, threadId);
            super.createHandle();
            if (this.cbtHook != 0L) {
                OS.UnhookWindowsHookEx(this.cbtHook);
            }
            this.cbtHook = 0L;
            cbtCallback.dispose();
        }
        this.state &= 0xFFFFFEFD;
        if (this.display.comboUseDarkTheme) {
            OS.AllowDarkModeForWindow(this.handle, true);
            OS.SetWindowTheme(this.handle, "CFD\u0000".toCharArray(), (char[])null);
        }
        this.stateFlagsUsable = this.stateFlagsTest();
        final long hwndText = OS.GetDlgItem(this.handle, 1001);
        if (hwndText != 0L && Combo.EditProc == 0L) {
            Combo.EditProc = OS.GetWindowLongPtr(hwndText, -4);
        }
        final long hwndList = OS.GetDlgItem(this.handle, 1000);
        if (hwndList != 0L && Combo.ListProc == 0L) {
            Combo.ListProc = OS.GetWindowLongPtr(hwndList, -4);
        }
        if ((this.style & 0x40) != 0x0) {
            final int flags = 52;
            OS.SetWindowPos(this.handle, 0L, 0, 0, 16383, 16383, 52);
            OS.SetWindowPos(this.handle, 0L, 0, 0, 0, 0, 52);
        }
    }
    
    @Override
    void createWidget() {
        super.createWidget();
        this.visibleCount = 5;
        if ((this.style & 0x40) == 0x0) {
            final int itemHeight = (int)OS.SendMessage(this.handle, 340, 0L, 0L);
            if (itemHeight != -1 && itemHeight != 0) {
                int maxHeight = 0;
                final long hmonitor = OS.MonitorFromWindow(this.handle, 2);
                final MONITORINFO lpmi = new MONITORINFO();
                lpmi.cbSize = MONITORINFO.sizeof;
                OS.GetMonitorInfo(hmonitor, lpmi);
                maxHeight = (lpmi.rcWork_bottom - lpmi.rcWork_top) / 3;
                this.visibleCount = Math.max(this.visibleCount, maxHeight / itemHeight);
            }
        }
    }
    
    public void cut() {
        this.checkWidget();
        if ((this.style & 0x8) != 0x0) {
            return;
        }
        OS.SendMessage(this.handle, 768, 0L, 0L);
    }
    
    @Override
    int defaultBackground() {
        return OS.GetSysColor(5);
    }
    
    TCHAR deprocessText(final TCHAR text, int start, int end, final boolean terminate) {
        if (text == null || this.segments == null) {
            return text;
        }
        int length = text.length();
        if (length == 0) {
            return text;
        }
        final int nSegments = this.segments.length;
        if (nSegments == 0) {
            return text;
        }
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
        if (end > this.segments[0] && start <= this.segments[nSegments - 1]) {
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
        if (start != 0 || end != length) {
            final char[] newChars = new char[length];
            System.arraycopy(chars, start, newChars, 0, length);
            return new TCHAR(this.getCodePage(), newChars, terminate);
        }
        return text;
    }
    
    @Override
    void deregister() {
        super.deregister();
        final long hwndText = OS.GetDlgItem(this.handle, 1001);
        if (hwndText != 0L) {
            this.display.removeControl(hwndText);
        }
        final long hwndList = OS.GetDlgItem(this.handle, 1000);
        if (hwndList != 0L) {
            this.display.removeControl(hwndList);
        }
    }
    
    public void deselect(final int index) {
        this.checkWidget();
        final int selection = (int)OS.SendMessage(this.handle, 327, 0L, 0L);
        if (index != selection) {
            return;
        }
        OS.SendMessage(this.handle, 334, -1L, 0L);
        this.sendEvent(24);
        this.clearSegments(false);
        --this.clearSegmentsCount;
    }
    
    public void deselectAll() {
        this.checkWidget();
        OS.SendMessage(this.handle, 334, -1L, 0L);
        this.sendEvent(24);
        this.clearSegments(false);
        --this.clearSegmentsCount;
    }
    
    @Override
    boolean dragDetect(final long hwnd, final int x, final int y, final boolean filter, final boolean[] detect, final boolean[] consume) {
        if (filter && (this.style & 0x8) == 0x0) {
            final long hwndText = OS.GetDlgItem(this.handle, 1001);
            if (hwndText != 0L) {
                final int[] start = { 0 };
                final int[] end = { 0 };
                OS.SendMessage(this.handle, 320, start, end);
                if (start[0] != end[0]) {
                    final long lParam = OS.MAKELPARAM(x, y);
                    final int position = OS.LOWORD(OS.SendMessage(hwndText, 215, 0L, lParam));
                    if (start[0] <= position && position < end[0] && super.dragDetect(hwnd, x, y, filter, detect, consume)) {
                        if (consume != null) {
                            consume[0] = true;
                        }
                        return true;
                    }
                }
                return false;
            }
        }
        return super.dragDetect(hwnd, x, y, filter, detect, consume);
    }
    
    public Point getCaretLocation() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getCaretLocationInPixels());
    }
    
    Point getCaretLocationInPixels() {
        final int position = this.translateOffset(this.getCaretPosition());
        final long hwndText = OS.GetDlgItem(this.handle, 1001);
        long caretPos = OS.SendMessage(hwndText, 214, (long)position, 0L);
        if (caretPos == -1L) {
            caretPos = 0L;
            if (position >= OS.GetWindowTextLength(hwndText)) {
                final int[] start = { 0 };
                final int[] end = { 0 };
                OS.SendMessage(hwndText, 176, start, end);
                OS.SendMessage(hwndText, 177, (long)position, (long)position);
                final boolean b = true;
                this.ignoreModify = true;
                this.ignoreCharacter = true;
                OS.SendMessage(hwndText, 194, 0L, new char[] { ' ', '\0' });
                caretPos = OS.SendMessage(hwndText, 214, (long)position, 0L);
                OS.SendMessage(hwndText, 177, (long)position, (long)(position + 1));
                OS.SendMessage(hwndText, 194, 0L, new char[1]);
                final boolean b2 = false;
                this.ignoreModify = false;
                this.ignoreCharacter = false;
                OS.SendMessage(hwndText, 177, (long)start[0], (long)start[0]);
                OS.SendMessage(hwndText, 177, (long)start[0], (long)end[0]);
            }
        }
        final POINT point = new POINT();
        point.x = OS.GET_X_LPARAM(caretPos);
        point.y = OS.GET_Y_LPARAM(caretPos);
        OS.MapWindowPoints(hwndText, this.handle, point, 1);
        return new Point(point.x, point.y);
    }
    
    public int getCaretPosition() {
        this.checkWidget();
        final int[] start = { 0 };
        final int[] end = { 0 };
        final long hwndText = OS.GetDlgItem(this.handle, 1001);
        OS.SendMessage(hwndText, 176, start, end);
        int caret = start[0];
        if (start[0] != end[0]) {
            final int idThread = OS.GetWindowThreadProcessId(hwndText, (int[])null);
            final GUITHREADINFO lpgui = new GUITHREADINFO();
            lpgui.cbSize = GUITHREADINFO.sizeof;
            if (OS.GetGUIThreadInfo(idThread, lpgui) && (lpgui.hwndCaret == hwndText || lpgui.hwndCaret == 0L)) {
                final POINT ptCurrentPos = new POINT();
                if (OS.GetCaretPos(ptCurrentPos)) {
                    final long endPos = OS.SendMessage(hwndText, 214, (long)end[0], 0L);
                    if (endPos == -1L) {
                        final long startPos = OS.SendMessage(hwndText, 214, (long)start[0], 0L);
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
        return this.untranslateOffset(caret);
    }
    
    public String getItem(final int index) {
        this.checkWidget();
        final int length = (int)OS.SendMessage(this.handle, 329, (long)index, 0L);
        if (length != -1) {
            if (this.hooks(49) || this.filters(49) || (this.state & 0x400000) != 0x0) {
                return this.items[index];
            }
            final char[] buffer = new char[length + 1];
            final int result = (int)OS.SendMessage(this.handle, 328, (long)index, buffer);
            if (result != -1) {
                return new String(buffer, 0, length);
            }
        }
        final int count = (int)OS.SendMessage(this.handle, 326, 0L, 0L);
        if (0 <= index && index < count) {
            this.error(8);
        }
        this.error(6);
        return "";
    }
    
    public int getItemCount() {
        this.checkWidget();
        final int count = (int)OS.SendMessage(this.handle, 326, 0L, 0L);
        if (count == -1) {
            this.error(36);
        }
        return count;
    }
    
    public int getItemHeight() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getItemHeightInPixels());
    }
    
    int getItemHeightInPixels() {
        final int result = (int)OS.SendMessage(this.handle, 340, 0L, 0L);
        if (result == -1) {
            this.error(11);
        }
        return result;
    }
    
    public String[] getItems() {
        this.checkWidget();
        final int count = this.getItemCount();
        final String[] result = new String[count];
        for (int i = 0; i < count; ++i) {
            result[i] = this.getItem(i);
        }
        return result;
    }
    
    public boolean getListVisible() {
        this.checkWidget();
        return (this.style & 0x4) == 0x0 || OS.SendMessage(this.handle, 343, 0L, 0L) != 0L;
    }
    
    @Override
    String getNameText() {
        return this.getText();
    }
    
    public void setListVisible(final boolean visible) {
        this.checkWidget();
        OS.SendMessage(this.handle, 335, (long)(visible ? 1 : 0), 0L);
    }
    
    @Override
    public int getOrientation() {
        return super.getOrientation();
    }
    
    Event getSegments(final String string) {
        Event event = null;
        if (this.hooks(49) || this.filters(49)) {
            event = new Event();
            event.text = string;
            this.sendEvent(49, event);
            if (event != null && event.segments != null) {
                int i = 1;
                final int segmentCount = event.segments.length;
                final int lineLength = (string == null) ? 0 : string.length();
                while (i < segmentCount) {
                    if (event.segments[i] < event.segments[i - 1] || event.segments[i] > lineLength) {
                        SWT.error(5);
                    }
                    ++i;
                }
            }
        }
        if ((this.state & 0x400000) != 0x0) {
            int direction = BidiUtil.resolveTextDirection(string);
            if (direction == 0) {
                direction = (((this.style & 0x4000000) != 0x0) ? 67108864 : 33554432);
            }
            int[] oldSegments = null;
            char[] oldSegmentsChars = null;
            if (event == null) {
                event = new Event();
            }
            else {
                oldSegments = event.segments;
                oldSegmentsChars = event.segmentsChars;
            }
            final int nSegments = (oldSegments == null) ? 0 : oldSegments.length;
            event.segments = new int[nSegments + 1];
            event.segmentsChars = new char[nSegments + 1];
            if (oldSegments != null) {
                System.arraycopy(oldSegments, 0, event.segments, 1, nSegments);
            }
            if (oldSegmentsChars != null) {
                System.arraycopy(oldSegmentsChars, 0, event.segmentsChars, 1, nSegments);
            }
            event.segments[0] = 0;
            event.segmentsChars[0] = ((direction == 67108864) ? '\u202b' : '\u202a');
        }
        return event;
    }
    
    String getSegmentsText(final String text, final Event event) {
        if (text == null || event == null) {
            return text;
        }
        final int[] segments = event.segments;
        if (segments == null) {
            return text;
        }
        final int nSegments = segments.length;
        if (nSegments == 0) {
            return text;
        }
        final char[] segmentsChars = event.segmentsChars;
        final int length = text.length();
        final char[] oldChars = new char[length];
        text.getChars(0, length, oldChars, 0);
        final char[] newChars = new char[length + nSegments];
        int charCount = 0;
        int segmentCount = 0;
        final char defaultSeparator = (this.getOrientation() == 67108864) ? '\u200f' : '\u200e';
        while (charCount < length) {
            if (segmentCount < nSegments && charCount == segments[segmentCount]) {
                final char separator = (segmentsChars != null && segmentsChars.length > segmentCount) ? segmentsChars[segmentCount] : defaultSeparator;
                newChars[charCount + segmentCount++] = separator;
            }
            else {
                newChars[charCount + segmentCount] = oldChars[charCount++];
            }
        }
        while (segmentCount < nSegments) {
            segments[segmentCount] = charCount;
            final char separator = (segmentsChars != null && segmentsChars.length > segmentCount) ? segmentsChars[segmentCount] : defaultSeparator;
            newChars[charCount + segmentCount++] = separator;
        }
        return new String(newChars, 0, newChars.length);
    }
    
    public Point getSelection() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0 && (this.style & 0x8) != 0x0) {
            return new Point(0, OS.GetWindowTextLength(this.handle));
        }
        final int[] start = { 0 };
        final int[] end = { 0 };
        OS.SendMessage(this.handle, 320, start, end);
        return new Point(this.untranslateOffset(start[0]), this.untranslateOffset(end[0]));
    }
    
    public int getSelectionIndex() {
        this.checkWidget();
        if (this.noSelection) {
            return -1;
        }
        return (int)OS.SendMessage(this.handle, 327, 0L, 0L);
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
    
    public int getTextHeight() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getTextHeightInPixels());
    }
    
    int getTextHeightInPixels() {
        final COMBOBOXINFO pcbi = new COMBOBOXINFO();
        pcbi.cbSize = COMBOBOXINFO.sizeof;
        if ((this.style & 0x40) == 0x0 && OS.GetComboBoxInfo(this.handle, pcbi)) {
            return pcbi.buttonBottom - pcbi.buttonTop + pcbi.buttonTop * 2;
        }
        final int result = (int)OS.SendMessage(this.handle, 340, -1L, 0L);
        if (result == -1) {
            this.error(11);
        }
        return ((this.style & 0x4) != 0x0) ? (result + 6) : (result + 10);
    }
    
    public int getTextLimit() {
        this.checkWidget();
        final long hwndText = OS.GetDlgItem(this.handle, 1001);
        if (hwndText == 0L) {
            return Combo.LIMIT;
        }
        int limit = (int)OS.SendMessage(hwndText, 213, 0L, 0L) & Integer.MAX_VALUE;
        if (this.segments != null && limit < Combo.LIMIT) {
            limit = Math.max(1, limit - this.segments.length);
        }
        return limit;
    }
    
    public int getVisibleItemCount() {
        this.checkWidget();
        return this.visibleCount;
    }
    
    @Override
    boolean hasFocus() {
        final long hwndFocus = OS.GetFocus();
        if (hwndFocus == this.handle) {
            return true;
        }
        if (hwndFocus == 0L) {
            return false;
        }
        final long hwndText = OS.GetDlgItem(this.handle, 1001);
        if (hwndFocus == hwndText) {
            return true;
        }
        final long hwndList = OS.GetDlgItem(this.handle, 1000);
        return hwndFocus == hwndList;
    }
    
    public int indexOf(final String string) {
        return this.indexOf(string, 0);
    }
    
    public int indexOf(final String string, final int start) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        if (string.length() == 0) {
            for (int count = this.getItemCount(), i = start; i < count; ++i) {
                if (string.equals(this.getItem(i))) {
                    return i;
                }
            }
            return -1;
        }
        final int count = (int)OS.SendMessage(this.handle, 326, 0L, 0L);
        if (0 > start || start >= count) {
            return -1;
        }
        int index = start - 1;
        int last = 0;
        final TCHAR buffer = new TCHAR(this.getCodePage(), string, true);
        do {
            index = (int)OS.SendMessage(this.handle, 344, (long)(last = index), buffer);
            if (index == -1 || index <= last) {
                return -1;
            }
        } while (!string.equals(this.getItem(index)));
        return index;
    }
    
    public void paste() {
        this.checkWidget();
        if ((this.style & 0x8) != 0x0) {
            return;
        }
        OS.SendMessage(this.handle, 770, 0L, 0L);
    }
    
    void stateFlagsAdd(final int flags) {
        final long tagCBoxPtr = OS.GetWindowLongPtr(this.handle, 0);
        if (tagCBoxPtr == 0L) {
            return;
        }
        final long stateFlagsPtr = tagCBoxPtr + Combo.stateFlagsOffset;
        final int[] stateFlags = { 0 };
        OS.MoveMemory(stateFlags, stateFlagsPtr, 4);
        final int[] array = stateFlags;
        final int n = 0;
        final int[] array2 = array;
        final int n2 = 0;
        array2[n2] |= flags;
        OS.MoveMemory(stateFlagsPtr, stateFlags, 4);
    }
    
    boolean stateFlagsTest() {
        final long tagCBoxPtr = OS.GetWindowLongPtr(this.handle, 0);
        if (tagCBoxPtr == 0L) {
            return false;
        }
        final long stateFlagsPtr = tagCBoxPtr + Combo.stateFlagsOffset;
        final int[] stateFlags = { 0 };
        OS.MoveMemory(stateFlags, stateFlagsPtr, 4);
        return stateFlags[0] == 33579010;
    }
    
    @Override
    void register() {
        super.register();
        final long hwndText = OS.GetDlgItem(this.handle, 1001);
        if (hwndText != 0L) {
            this.display.addControl(hwndText, this);
        }
        final long hwndList = OS.GetDlgItem(this.handle, 1000);
        if (hwndList != 0L) {
            this.display.addControl(hwndList, this);
        }
    }
    
    public void remove(final int index) {
        this.checkWidget();
        this.remove(index, true);
    }
    
    void remove(final int index, final boolean notify) {
        char[] buffer = null;
        if ((this.style & 0x100) != 0x0) {
            final int length = (int)OS.SendMessage(this.handle, 329, (long)index, 0L);
            if (length == -1) {
                final int count = (int)OS.SendMessage(this.handle, 326, 0L, 0L);
                if (0 <= index && index < count) {
                    this.error(15);
                }
                this.error(6);
            }
            buffer = new char[length + 1];
            final int result = (int)OS.SendMessage(this.handle, 328, (long)index, buffer);
            if (result == -1) {
                final int count2 = (int)OS.SendMessage(this.handle, 326, 0L, 0L);
                if (0 <= index && index < count2) {
                    this.error(15);
                }
                this.error(6);
            }
        }
        final int length = OS.GetWindowTextLength(this.handle);
        final int code = (int)OS.SendMessage(this.handle, 324, (long)index, 0L);
        if (code == -1) {
            final int count2 = (int)OS.SendMessage(this.handle, 326, 0L, 0L);
            if (0 <= index && index < count2) {
                this.error(15);
            }
            this.error(6);
        }
        else if (code == 0) {
            OS.SendMessage(this.handle, 331, 0L, 0L);
        }
        if ((this.style & 0x100) != 0x0) {
            this.setScrollWidth(buffer, true);
        }
        if (notify && length != OS.GetWindowTextLength(this.handle)) {
            this.sendEvent(24);
            if (this.isDisposed()) {
                return;
            }
        }
    }
    
    public void remove(final int start, final int end) {
        this.checkWidget();
        if (start > end) {
            return;
        }
        final int count = (int)OS.SendMessage(this.handle, 326, 0L, 0L);
        if (0 > start || start > end || end >= count) {
            this.error(6);
        }
        final int textLength = OS.GetWindowTextLength(this.handle);
        RECT rect = null;
        long hDC = 0L;
        long oldFont = 0L;
        long newFont = 0L;
        int newWidth = 0;
        if ((this.style & 0x100) != 0x0) {
            rect = new RECT();
            hDC = OS.GetDC(this.handle);
            newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
            if (newFont != 0L) {
                oldFont = OS.SelectObject(hDC, newFont);
            }
        }
        final int flags = 3104;
        for (int i = start; i <= end; ++i) {
            char[] buffer = null;
            if ((this.style & 0x100) != 0x0) {
                final int length = (int)OS.SendMessage(this.handle, 329, (long)start, 0L);
                if (length == -1) {
                    break;
                }
                buffer = new char[length + 1];
                final int result = (int)OS.SendMessage(this.handle, 328, (long)start, buffer);
                if (result == -1) {
                    break;
                }
            }
            final int result2 = (int)OS.SendMessage(this.handle, 324, (long)start, 0L);
            if (result2 == -1) {
                this.error(15);
            }
            else if (result2 == 0) {
                OS.SendMessage(this.handle, 331, 0L, 0L);
            }
            if ((this.style & 0x100) != 0x0) {
                OS.DrawText(hDC, buffer, -1, rect, 3104);
                newWidth = Math.max(newWidth, rect.right - rect.left);
            }
        }
        if ((this.style & 0x100) != 0x0) {
            if (newFont != 0L) {
                OS.SelectObject(hDC, oldFont);
            }
            OS.ReleaseDC(this.handle, hDC);
            this.setScrollWidth(newWidth, false);
        }
        if (textLength != OS.GetWindowTextLength(this.handle)) {
            this.sendEvent(24);
            if (this.isDisposed()) {
                return;
            }
        }
    }
    
    public void remove(final String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        final int index = this.indexOf(string, 0);
        if (index == -1) {
            this.error(5);
        }
        this.remove(index);
    }
    
    public void removeAll() {
        this.checkWidget();
        OS.SendMessage(this.handle, 331, 0L, 0L);
        this.sendEvent(24);
        if (this.isDisposed()) {
            return;
        }
        if ((this.style & 0x100) != 0x0) {
            this.setScrollWidth(0);
        }
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
        int selection = -1;
        if (!this.noSelection) {
            selection = (int)OS.SendMessage(this.handle, 327, 0L, 0L);
        }
        this.clearSegments(true);
        this.applyEditSegments();
        this.applyListSegments();
        if (selection != -1) {
            OS.SendMessage(this.handle, 334, (long)selection, 0L);
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
    
    @Override
    boolean sendKeyEvent(final int type, final int msg, final long wParam, final long lParam, final Event event) {
        if (!super.sendKeyEvent(type, msg, wParam, lParam, event)) {
            return false;
        }
        if ((this.style & 0x8) != 0x0) {
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
        if (OS.GetKeyState(1) < 0 && OS.GetDlgItem(this.handle, 1001) == OS.GetCapture()) {
            return true;
        }
        String oldText = "";
        final int[] start = { 0 };
        final int[] end = { 0 };
        final long hwndText = OS.GetDlgItem(this.handle, 1001);
        if (hwndText == 0L) {
            return true;
        }
        OS.SendMessage(hwndText, 176, start, end);
        switch (key) {
            case '\b': {
                if (start[0] != end[0]) {
                    break;
                }
                if (start[0] == 0) {
                    return true;
                }
                final int[] array = start;
                final int n = 0;
                --array[n];
                start[0] = Math.max(start[0], 0);
                break;
            }
            case '\u007f': {
                if (start[0] != end[0]) {
                    break;
                }
                final int length = OS.GetWindowTextLength(hwndText);
                if (start[0] == length) {
                    return true;
                }
                final int[] array2 = end;
                final int n2 = 0;
                ++array2[n2];
                end[0] = Math.min(end[0], length);
                break;
            }
            case '\r': {
                return true;
            }
            default: {
                if (key != '\t' && key < ' ') {
                    return true;
                }
                oldText = new String(new char[] { key });
                break;
            }
        }
        final String newText = this.verifyText(oldText, start[0], end[0], event);
        if (newText == null) {
            return false;
        }
        if (newText == oldText) {
            return true;
        }
        final TCHAR buffer = new TCHAR(this.getCodePage(), newText, true);
        OS.SendMessage(hwndText, 177, (long)start[0], (long)end[0]);
        OS.SendMessage(hwndText, 194, 0L, buffer);
        return false;
    }
    
    public void select(final int index) {
        this.checkWidget();
        final int count = (int)OS.SendMessage(this.handle, 326, 0L, 0L);
        if (0 <= index && index < count) {
            final int selection = (int)OS.SendMessage(this.handle, 327, 0L, 0L);
            if (OS.WIN32_VERSION < OS.VERSION(6, 2) && this.getListVisible() && (this.style & 0x8) != 0x0 && count == 1 && selection == -1) {
                OS.SendMessage(this.handle, 256, 40L, 0L);
                this.sendEvent(24);
                return;
            }
            final int code = (int)OS.SendMessage(this.handle, 334, (long)index, 0L);
            if (code != -1 && code != selection) {
                if (OS.WIN32_VERSION < OS.VERSION(6, 2) && this.getListVisible() && (this.style & 0x8) != 0x0) {
                    int firstKey = 38;
                    int secondKey = 40;
                    if (index == 0) {
                        firstKey = 40;
                        secondKey = 38;
                    }
                    OS.SendMessage(this.handle, 256, (long)firstKey, 0L);
                    OS.SendMessage(this.handle, 256, (long)secondKey, 0L);
                }
                this.sendEvent(24);
            }
        }
    }
    
    @Override
    void setBackgroundImage(final long hBitmap) {
        super.setBackgroundImage(hBitmap);
        final long hwndText = OS.GetDlgItem(this.handle, 1001);
        if (hwndText != 0L) {
            OS.InvalidateRect(hwndText, (RECT)null, true);
        }
        final long hwndList = OS.GetDlgItem(this.handle, 1000);
        if (hwndList != 0L) {
            OS.InvalidateRect(hwndList, (RECT)null, true);
        }
    }
    
    @Override
    void setBackgroundPixel(final int pixel) {
        super.setBackgroundPixel(pixel);
        final long hwndText = OS.GetDlgItem(this.handle, 1001);
        if (hwndText != 0L) {
            OS.InvalidateRect(hwndText, (RECT)null, true);
        }
        final long hwndList = OS.GetDlgItem(this.handle, 1000);
        if (hwndList != 0L) {
            OS.InvalidateRect(hwndList, (RECT)null, true);
        }
    }
    
    @Override
    void setBoundsInPixels(final int x, final int y, final int width, int height, int flags) {
        if ((this.style & 0x4) != 0x0) {
            final int visibleCount = (this.getItemCount() == 0) ? 5 : this.visibleCount;
            height = this.getTextHeightInPixels() + this.getItemHeightInPixels() * visibleCount + 2;
            final RECT rect = new RECT();
            OS.GetWindowRect(this.handle, rect);
            if (rect.right - rect.left != 0 && OS.SendMessage(this.handle, 338, 0L, rect) != 0L) {
                final int oldWidth = rect.right - rect.left;
                final int oldHeight = rect.bottom - rect.top;
                if (oldWidth == width && oldHeight == height) {
                    flags |= 0x1;
                }
            }
            OS.SetWindowPos(this.handle, 0L, x, y, width, height, flags);
        }
        else {
            super.setBoundsInPixels(x, y, width, height, flags);
        }
    }
    
    @Override
    public void setFont(final Font font) {
        this.checkWidget();
        final boolean oldLockText = this.lockText;
        if ((this.style & 0x8) == 0x0) {
            this.lockText = true;
        }
        super.setFont(font);
        if ((this.style & 0x8) == 0x0) {
            this.lockText = oldLockText;
        }
        if ((this.style & 0x100) != 0x0) {
            this.setScrollWidth();
        }
    }
    
    @Override
    void setForegroundPixel(final int pixel) {
        super.setForegroundPixel(pixel);
        final long hwndText = OS.GetDlgItem(this.handle, 1001);
        if (hwndText != 0L) {
            OS.InvalidateRect(hwndText, (RECT)null, true);
        }
        final long hwndList = OS.GetDlgItem(this.handle, 1000);
        if (hwndList != 0L) {
            OS.InvalidateRect(hwndList, (RECT)null, true);
        }
    }
    
    public void setItem(final int index, final String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        final int selection = this.getSelectionIndex();
        this.remove(index, false);
        if (this.isDisposed()) {
            return;
        }
        this.add(string, index);
        if (selection != -1) {
            this.select(selection);
        }
    }
    
    public void setItems(final String... items) {
        this.checkWidget();
        if (items == null) {
            this.error(4);
        }
        for (final String item : items) {
            if (item == null) {
                this.error(5);
            }
        }
        RECT rect = null;
        long hDC = 0L;
        long oldFont = 0L;
        long newFont = 0L;
        int newWidth = 0;
        if ((this.style & 0x100) != 0x0) {
            rect = new RECT();
            hDC = OS.GetDC(this.handle);
            newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
            if (newFont != 0L) {
                oldFont = OS.SelectObject(hDC, newFont);
            }
            this.setScrollWidth(0);
        }
        OS.SendMessage(this.handle, 331, 0L, 0L);
        final int codePage = this.getCodePage();
        for (final String item2 : items) {
            final TCHAR buffer = new TCHAR(codePage, item2, true);
            final int code = (int)OS.SendMessage(this.handle, 323, 0L, buffer);
            if (code == -1) {
                this.error(14);
            }
            if (code == -2) {
                this.error(14);
            }
            if ((this.style & 0x100) != 0x0) {
                final int flags = 3104;
                OS.DrawText(hDC, buffer, -1, rect, 3104);
                newWidth = Math.max(newWidth, rect.right - rect.left);
            }
        }
        if ((this.style & 0x100) != 0x0) {
            if (newFont != 0L) {
                OS.SelectObject(hDC, oldFont);
            }
            OS.ReleaseDC(this.handle, hDC);
            this.setScrollWidth(newWidth + 3);
        }
        this.sendEvent(24);
    }
    
    @Override
    public void setOrientation(final int orientation) {
        super.setOrientation(orientation);
    }
    
    void setScrollWidth() {
        int newWidth = 0;
        final RECT rect = new RECT();
        long oldFont = 0L;
        final long hDC = OS.GetDC(this.handle);
        final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
        if (newFont != 0L) {
            oldFont = OS.SelectObject(hDC, newFont);
        }
        final int count = (int)OS.SendMessage(this.handle, 326, 0L, 0L);
        final int flags = 3104;
        for (int i = 0; i < count; ++i) {
            final int length = (int)OS.SendMessage(this.handle, 329, (long)i, 0L);
            if (length != -1) {
                final char[] buffer = new char[length + 1];
                final int result = (int)OS.SendMessage(this.handle, 328, (long)i, buffer);
                if (result != -1) {
                    OS.DrawText(hDC, buffer, -1, rect, 3104);
                    newWidth = Math.max(newWidth, rect.right - rect.left);
                }
            }
        }
        if (newFont != 0L) {
            OS.SelectObject(hDC, oldFont);
        }
        OS.ReleaseDC(this.handle, hDC);
        this.setScrollWidth(newWidth + 3);
    }
    
    void setScrollWidth(int scrollWidth) {
        this.scrollWidth = scrollWidth;
        if ((this.style & 0x40) != 0x0) {
            OS.SendMessage(this.handle, 350, (long)scrollWidth, 0L);
            return;
        }
        boolean scroll = false;
        final int count = (int)OS.SendMessage(this.handle, 326, 0L, 0L);
        if (count > 3) {
            final long hmonitor = OS.MonitorFromWindow(this.handle, 2);
            final MONITORINFO lpmi = new MONITORINFO();
            lpmi.cbSize = MONITORINFO.sizeof;
            OS.GetMonitorInfo(hmonitor, lpmi);
            final int maxWidth = (lpmi.rcWork_right - lpmi.rcWork_left) / 4;
            scroll = (scrollWidth > maxWidth);
        }
        final boolean oldLockText = this.lockText;
        if ((this.style & 0x8) == 0x0) {
            this.lockText = true;
        }
        if (scroll) {
            OS.SendMessage(this.handle, 352, 0L, 0L);
            OS.SendMessage(this.handle, 350, (long)scrollWidth, 0L);
        }
        else {
            scrollWidth += OS.GetSystemMetrics(3);
            OS.SendMessage(this.handle, 352, (long)scrollWidth, 0L);
            OS.SendMessage(this.handle, 350, 0L, 0L);
        }
        if ((this.style & 0x8) == 0x0) {
            this.lockText = oldLockText;
        }
    }
    
    void setScrollWidth(final char[] buffer, final boolean grow) {
        final RECT rect = new RECT();
        long oldFont = 0L;
        final long hDC = OS.GetDC(this.handle);
        final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
        if (newFont != 0L) {
            oldFont = OS.SelectObject(hDC, newFont);
        }
        final int flags = 3104;
        OS.DrawText(hDC, buffer, -1, rect, 3104);
        if (newFont != 0L) {
            OS.SelectObject(hDC, oldFont);
        }
        OS.ReleaseDC(this.handle, hDC);
        this.setScrollWidth(rect.right - rect.left, grow);
    }
    
    void setScrollWidth(final int newWidth, final boolean grow) {
        if (grow) {
            if (newWidth <= this.scrollWidth) {
                return;
            }
            this.setScrollWidth(newWidth + 3);
        }
        else {
            if (newWidth < this.scrollWidth) {
                return;
            }
            this.setScrollWidth();
        }
    }
    
    public void setSelection(final Point selection) {
        this.checkWidget();
        if (selection == null) {
            this.error(4);
        }
        final int start = this.translateOffset(selection.x);
        final int end = this.translateOffset(selection.y);
        final long bits = OS.MAKELPARAM(start, end);
        OS.SendMessage(this.handle, 322, 0L, bits);
    }
    
    public void setText(String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        if ((this.style & 0x8) != 0x0) {
            final int index = this.indexOf(string);
            if (index != -1) {
                this.select(index);
            }
            return;
        }
        this.clearSegments(false);
        int limit = Combo.LIMIT;
        final long hwndText = OS.GetDlgItem(this.handle, 1001);
        if (hwndText != 0L) {
            limit = ((int)OS.SendMessage(hwndText, 213, 0L, 0L) & Integer.MAX_VALUE);
        }
        if (string.length() > limit) {
            string = string.substring(0, limit);
        }
        final TCHAR buffer = new TCHAR(this.getCodePage(), string, true);
        if (OS.SetWindowText(this.handle, buffer)) {
            this.applyEditSegments();
            this.sendEvent(24);
        }
    }
    
    public void setTextLimit(final int limit) {
        this.checkWidget();
        if (limit == 0) {
            this.error(7);
        }
        if (this.segments != null && limit > 0) {
            OS.SendMessage(this.handle, 321, (long)(limit + Math.min(this.segments.length, Combo.LIMIT - limit)), 0L);
        }
        else {
            OS.SendMessage(this.handle, 321, (long)limit, 0L);
        }
    }
    
    @Override
    void setToolTipText(final Shell shell, final String string) {
        final long hwndText = OS.GetDlgItem(this.handle, 1001);
        final long hwndList = OS.GetDlgItem(this.handle, 1000);
        if (hwndText != 0L) {
            shell.setToolTipText(hwndText, string);
        }
        if (hwndList != 0L) {
            shell.setToolTipText(hwndList, string);
        }
        shell.setToolTipText(this.handle, string);
    }
    
    public void setVisibleItemCount(final int count) {
        this.checkWidget();
        if (count < 0) {
            return;
        }
        this.visibleCount = count;
        this.updateDropDownHeight();
    }
    
    @Override
    void subclass() {
        super.subclass();
        final long newProc = this.display.windowProc;
        final long hwndText = OS.GetDlgItem(this.handle, 1001);
        if (hwndText != 0L) {
            OS.SetWindowLongPtr(hwndText, -4, newProc);
        }
        final long hwndList = OS.GetDlgItem(this.handle, 1000);
        if (hwndList != 0L) {
            OS.SetWindowLongPtr(hwndList, -4, newProc);
        }
    }
    
    int translateOffset(int offset) {
        if (this.segments == null) {
            return offset;
        }
        for (int i = 0, nSegments = this.segments.length; i < nSegments && offset - i >= this.segments[i]; ++offset, ++i) {}
        return offset;
    }
    
    @Override
    boolean translateTraversal(final MSG msg) {
        switch ((int)msg.wParam) {
            case 13:
            case 27: {
                if ((this.style & 0x4) != 0x0 && OS.SendMessage(this.handle, 343, 0L, 0L) != 0L) {
                    return false;
                }
                break;
            }
        }
        return super.translateTraversal(msg);
    }
    
    @Override
    boolean traverseEscape() {
        if ((this.style & 0x4) != 0x0 && OS.SendMessage(this.handle, 343, 0L, 0L) != 0L) {
            OS.SendMessage(this.handle, 335, 0L, 0L);
            return true;
        }
        return super.traverseEscape();
    }
    
    @Override
    boolean traverseReturn() {
        if ((this.style & 0x4) != 0x0 && OS.SendMessage(this.handle, 343, 0L, 0L) != 0L) {
            OS.SendMessage(this.handle, 335, 0L, 0L);
            return true;
        }
        return super.traverseReturn();
    }
    
    @Override
    void unsubclass() {
        super.unsubclass();
        final long hwndText = OS.GetDlgItem(this.handle, 1001);
        if (hwndText != 0L && Combo.EditProc != 0L) {
            OS.SetWindowLongPtr(hwndText, -4, Combo.EditProc);
        }
        final long hwndList = OS.GetDlgItem(this.handle, 1000);
        if (hwndList != 0L && Combo.ListProc != 0L) {
            OS.SetWindowLongPtr(hwndList, -4, Combo.ListProc);
        }
    }
    
    int untranslateOffset(int offset) {
        if (this.segments == null) {
            return offset;
        }
        for (int i = 0, nSegments = this.segments.length; i < nSegments && offset > this.segments[i]; --offset, ++i) {}
        return offset;
    }
    
    void updateDropDownHeight() {
        if ((this.style & 0x4) != 0x0) {
            final RECT rect = new RECT();
            OS.SendMessage(this.handle, 338, 0L, rect);
            final int visibleCount = (this.getItemCount() == 0) ? 5 : this.visibleCount;
            final int height = this.getTextHeightInPixels() + this.getItemHeightInPixels() * visibleCount + 2;
            if (height != rect.bottom - rect.top) {
                this.forceResize();
                OS.GetWindowRect(this.handle, rect);
                final int flags = 54;
                OS.SetWindowPos(this.handle, 0L, 0, 0, rect.right - rect.left, height, 54);
            }
        }
    }
    
    void updateDropDownTheme() {
        final COMBOBOXINFO pcbi = new COMBOBOXINFO();
        pcbi.cbSize = COMBOBOXINFO.sizeof;
        if (!OS.GetComboBoxInfo(this.handle, pcbi)) {
            return;
        }
        if (pcbi.hwndList == 0L) {
            return;
        }
        this.maybeEnableDarkSystemTheme(pcbi.hwndList);
    }
    
    @Override
    boolean updateTextDirection(final int textDirection) {
        if (super.updateTextDirection(textDirection)) {
            this.clearSegments(true);
            this.applyEditSegments();
            this.applyListSegments();
            return true;
        }
        return false;
    }
    
    @Override
    void updateOrientation() {
        int bits = OS.GetWindowLong(this.handle, -20);
        if ((this.style & 0x4000000) != 0x0) {
            bits |= 0x400000;
        }
        else {
            bits &= 0xFFBFFFFF;
        }
        bits &= 0xFFFFDFFF;
        OS.SetWindowLong(this.handle, -20, bits);
        long hwndText = 0L;
        long hwndList = 0L;
        final COMBOBOXINFO pcbi = new COMBOBOXINFO();
        pcbi.cbSize = COMBOBOXINFO.sizeof;
        if (OS.GetComboBoxInfo(this.handle, pcbi)) {
            hwndText = pcbi.hwndItem;
            hwndList = pcbi.hwndList;
        }
        if (hwndText != 0L) {
            int bits2 = OS.GetWindowLong(hwndText, -20);
            int bits3 = OS.GetWindowLong(hwndText, -16);
            if ((this.style & 0x4000000) != 0x0) {
                bits2 |= 0x3000;
                bits3 |= 0x2;
            }
            else {
                bits2 &= 0xFFFFCFFF;
                bits3 &= 0xFFFFFFFD;
            }
            OS.SetWindowLong(hwndText, -20, bits2);
            OS.SetWindowLong(hwndText, -16, bits3);
            final RECT rect = new RECT();
            OS.GetWindowRect(hwndText, rect);
            final int width = rect.right - rect.left;
            final int height = rect.bottom - rect.top;
            OS.GetWindowRect(this.handle, rect);
            final int widthCombo = rect.right - rect.left;
            final int heightCombo = rect.bottom - rect.top;
            final int uFlags = 22;
            OS.SetWindowPos(hwndText, 0L, 0, 0, width - 1, height - 1, 22);
            OS.SetWindowPos(this.handle, 0L, 0, 0, widthCombo - 1, heightCombo - 1, 22);
            OS.SetWindowPos(hwndText, 0L, 0, 0, width, height, 22);
            OS.SetWindowPos(this.handle, 0L, 0, 0, widthCombo, heightCombo, 22);
            OS.InvalidateRect(this.handle, (RECT)null, true);
        }
        if (hwndList != 0L) {
            int bits2 = OS.GetWindowLong(hwndList, -20);
            if ((this.style & 0x4000000) != 0x0) {
                bits2 |= 0x400000;
            }
            else {
                bits2 &= 0xFFBFFFFF;
            }
            OS.SetWindowLong(hwndList, -20, bits2);
        }
    }
    
    String verifyText(final String string, final int start, final int end, final Event keyEvent) {
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
    
    @Override
    int widgetExtStyle() {
        return super.widgetExtStyle() & 0xFFEFFFFF;
    }
    
    @Override
    int widgetStyle() {
        final int bits = super.widgetStyle() | 0x40 | 0x400 | 0x100000 | 0x200000;
        if ((this.style & 0x40) != 0x0) {
            return bits | 0x1;
        }
        if ((this.style & 0x8) != 0x0) {
            return bits | 0x3;
        }
        return bits | 0x2;
    }
    
    @Override
    TCHAR windowClass() {
        return Combo.ComboClass;
    }
    
    @Override
    long windowProc() {
        return Combo.ComboProc;
    }
    
    @Override
    long windowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        if (hwnd != this.handle) {
            final long hwndText = OS.GetDlgItem(this.handle, 1001);
            final long hwndList = OS.GetDlgItem(this.handle, 1000);
            if ((hwndText != 0L && hwnd == hwndText) || (hwndList != 0L && hwnd == hwndList)) {
                LRESULT result = null;
                boolean processSegments = false;
                boolean redraw = false;
                switch (msg) {
                    case 258: {
                        processSegments = ((this.hooks(49) || this.filters(49) || (this.state & 0x400000) != 0x0) && !this.ignoreCharacter && OS.GetKeyState(17) >= 0 && OS.GetKeyState(18) >= 0);
                        result = this.wmChar(hwnd, wParam, lParam);
                        break;
                    }
                    case 646: {
                        result = this.wmIMEChar(hwnd, wParam, lParam);
                        break;
                    }
                    case 256: {
                        processSegments = (wParam == 46L && (this.hooks(49) || this.filters(49) || (this.state & 0x400000) != 0x0));
                        result = this.wmKeyDown(hwnd, wParam, lParam);
                        break;
                    }
                    case 257: {
                        result = this.wmKeyUp(hwnd, wParam, lParam);
                        break;
                    }
                    case 262: {
                        result = this.wmSysChar(hwnd, wParam, lParam);
                        break;
                    }
                    case 260: {
                        result = this.wmSysKeyDown(hwnd, wParam, lParam);
                        break;
                    }
                    case 261: {
                        result = this.wmSysKeyUp(hwnd, wParam, lParam);
                        break;
                    }
                    case 533: {
                        result = this.wmCaptureChanged(hwnd, wParam, lParam);
                        break;
                    }
                    case 515: {
                        result = this.wmLButtonDblClk(hwnd, wParam, lParam);
                        break;
                    }
                    case 513: {
                        result = this.wmLButtonDown(hwnd, wParam, lParam);
                        break;
                    }
                    case 514: {
                        result = this.wmLButtonUp(hwnd, wParam, lParam);
                        break;
                    }
                    case 521: {
                        result = this.wmMButtonDblClk(hwnd, wParam, lParam);
                        break;
                    }
                    case 519: {
                        result = this.wmMButtonDown(hwnd, wParam, lParam);
                        break;
                    }
                    case 520: {
                        result = this.wmMButtonUp(hwnd, wParam, lParam);
                        break;
                    }
                    case 673: {
                        result = this.wmMouseHover(hwnd, wParam, lParam);
                        break;
                    }
                    case 675: {
                        result = this.wmMouseLeave(hwnd, wParam, lParam);
                        break;
                    }
                    case 512: {
                        result = this.wmMouseMove(hwnd, wParam, lParam);
                        break;
                    }
                    case 518: {
                        result = this.wmRButtonDblClk(hwnd, wParam, lParam);
                        break;
                    }
                    case 516: {
                        result = this.wmRButtonDown(hwnd, wParam, lParam);
                        break;
                    }
                    case 517: {
                        result = this.wmRButtonUp(hwnd, wParam, lParam);
                        break;
                    }
                    case 525: {
                        result = this.wmXButtonDblClk(hwnd, wParam, lParam);
                        break;
                    }
                    case 523: {
                        result = this.wmXButtonDown(hwnd, wParam, lParam);
                        break;
                    }
                    case 524: {
                        result = this.wmXButtonUp(hwnd, wParam, lParam);
                        break;
                    }
                    case 15: {
                        result = this.wmPaint(hwnd, wParam, lParam);
                        break;
                    }
                    case 123: {
                        result = this.wmContextMenu(hwnd, wParam, lParam);
                        break;
                    }
                    case 198: {
                        if (this.hooks(49) || this.filters(49) || (this.state & 0x400000) != 0x0) {
                            return 0L;
                        }
                        break;
                    }
                    case 199:
                    case 772: {
                        if (this.hooks(49) || this.filters(49) || (this.state & 0x400000) != 0x0) {
                            return 0L;
                        }
                    }
                    case 768:
                    case 769:
                    case 770:
                    case 771: {
                        processSegments = (this.hooks(49) || this.filters(49) || (this.state & 0x400000) != 0x0);
                    }
                    case 12: {
                        if (hwnd == hwndText) {
                            result = this.wmClipboard(hwnd, msg, wParam, lParam);
                            break;
                        }
                        break;
                    }
                }
                if (result != null) {
                    return result.value;
                }
                if (processSegments) {
                    if (this.getDrawing() && OS.IsWindowVisible(hwndText)) {
                        redraw = true;
                        OS.DefWindowProc(hwndText, 11, 0L, 0L);
                    }
                    this.clearSegments(true);
                    final long code = this.callWindowProc(hwnd, msg, wParam, lParam);
                    this.applyEditSegments();
                    if (redraw) {
                        OS.DefWindowProc(hwndText, 11, 1L, 0L);
                        OS.InvalidateRect(hwndText, (RECT)null, true);
                        this.forceScrollingToCaret();
                    }
                    return code;
                }
                return this.callWindowProc(hwnd, msg, wParam, lParam);
            }
        }
        switch (msg) {
            case 334: {
                long code2 = -1L;
                int index = (int)wParam;
                if ((this.style & 0x8) != 0x0 && (this.hooks(25) || this.filters(25))) {
                    String oldText = this.getText();
                    String newText = null;
                    if (wParam == -1L) {
                        newText = "";
                    }
                    else if (0L <= wParam && wParam < this.getItemCount()) {
                        newText = this.getItem((int)wParam);
                    }
                    if (newText != null && !newText.equals(oldText)) {
                        final int length = OS.GetWindowTextLength(this.handle);
                        oldText = newText;
                        newText = this.verifyText(newText, 0, length, null);
                        if (newText == null) {
                            return 0L;
                        }
                        if (!newText.equals(oldText)) {
                            index = this.indexOf(newText);
                            if (index != -1 && index != wParam) {
                                return this.callWindowProc(this.handle, 334, index, lParam);
                            }
                        }
                    }
                }
                if (!this.hooks(49) && !this.filters(49) && (this.state & 0x400000) == 0x0) {
                    break;
                }
                code2 = super.windowProc(hwnd, msg, wParam, lParam);
                if (code2 != -1L && code2 != -2L) {
                    final Event event = this.getSegments(this.items[index]);
                    this.segments = (int[])((event != null) ? event.segments : null);
                    if (event != null && event.segmentsChars != null) {
                        final int auto = this.state & 0x400000;
                        if (event.segmentsChars[0] == '\u202b') {
                            super.updateTextDirection(67108864);
                        }
                        else if (event.segmentsChars[0] == '\u202a') {
                            super.updateTextDirection(33554432);
                        }
                        this.state |= auto;
                    }
                    return code2;
                }
                break;
            }
            case 323:
            case 330:
            case 344: {
                if (lParam == 0L) {
                    break;
                }
                if (!this.hooks(49) && !this.filters(49) && (this.state & 0x400000) == 0x0) {
                    break;
                }
                long code2 = -1L;
                int length2 = OS.wcslen(lParam);
                TCHAR buffer = new TCHAR(this.getCodePage(), length2);
                OS.MoveMemory(buffer, lParam, buffer.length() * 2);
                final String string = buffer.toString(0, length2);
                final Event event2 = this.getSegments(string);
                if (event2 != null && event2.segments != null) {
                    buffer = new TCHAR(this.getCodePage(), this.getSegmentsText(string, event2), true);
                    final long hHeap = OS.GetProcessHeap();
                    length2 = buffer.length() * 2;
                    final long pszText = OS.HeapAlloc(hHeap, 8, length2);
                    OS.MoveMemory(pszText, buffer, length2);
                    code2 = super.windowProc(hwnd, msg, wParam, pszText);
                    OS.HeapFree(hHeap, 0, pszText);
                }
                if (msg == 323 || msg == 330) {
                    final int index2 = (msg == 323) ? this.items.length : ((int)wParam);
                    final String[] newItems = new String[this.items.length + 1];
                    System.arraycopy(this.items, 0, newItems, 0, index2);
                    newItems[index2] = string;
                    System.arraycopy(this.items, index2, newItems, index2 + 1, this.items.length - index2);
                    this.items = newItems;
                }
                if (code2 != -1L && code2 != -2L) {
                    return code2;
                }
                break;
            }
            case 324: {
                if (this.hooks(49) || this.filters(49) || (this.state & 0x400000) != 0x0) {
                    final long code2 = super.windowProc(hwnd, msg, wParam, lParam);
                    if (code2 != -1L && code2 != -2L) {
                        int index = (int)wParam;
                        if (this.items.length == 1) {
                            this.items = new String[0];
                        }
                        else if (this.items.length > 1) {
                            final String[] newItems2 = new String[this.items.length - 1];
                            System.arraycopy(this.items, 0, newItems2, 0, index);
                            System.arraycopy(this.items, index + 1, newItems2, index, this.items.length - index - 1);
                            this.items = newItems2;
                        }
                        if (!this.noSelection) {
                            index = (int)OS.SendMessage(this.handle, 327, 0L, 0L);
                            if (index == wParam) {
                                this.clearSegments(false);
                                this.applyEditSegments();
                            }
                        }
                    }
                    return code2;
                }
                break;
            }
            case 331: {
                if (this.hooks(49) || this.filters(49) || (this.state & 0x400000) != 0x0) {
                    if (this.items.length > 0) {
                        this.items = new String[0];
                    }
                    this.clearSegments(false);
                    this.applyEditSegments();
                    break;
                }
                break;
            }
        }
        return super.windowProc(hwnd, msg, wParam, lParam);
    }
    
    @Override
    LRESULT wmColorChild(final long wParam, final long lParam) {
        final LRESULT result = super.wmColorChild(wParam, lParam);
        final boolean isReadonly = (this.style & 0x8) != 0x0;
        final boolean isCustomColors = result != null;
        if (isReadonly && isCustomColors && this.stateFlagsUsable) {
            this.stateFlagsAdd(33554432);
        }
        return result;
    }
    
    @Override
    LRESULT WM_CTLCOLOR(final long wParam, final long lParam) {
        return this.wmColorChild(wParam, lParam);
    }
    
    @Override
    LRESULT WM_GETDLGCODE(final long wParam, final long lParam) {
        final long code = this.callWindowProc(this.handle, 135, wParam, lParam);
        return new LRESULT(code | 0x1L);
    }
    
    @Override
    LRESULT WM_KILLFOCUS(final long wParam, final long lParam) {
        return null;
    }
    
    @Override
    LRESULT WM_LBUTTONDOWN(final long wParam, final long lParam) {
        final int oldSelection = (int)OS.SendMessage(this.handle, 327, 0L, 0L);
        final LRESULT result = super.WM_LBUTTONDOWN(wParam, lParam);
        if (result == LRESULT.ZERO) {
            return result;
        }
        if ((this.style & 0x8) == 0x0) {
            final int newSelection = (int)OS.SendMessage(this.handle, 327, 0L, 0L);
            if (oldSelection != newSelection) {
                this.sendEvent(24);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
                this.sendSelectionEvent(13, null, true);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
            }
        }
        return result;
    }
    
    @Override
    LRESULT WM_SETFOCUS(final long wParam, final long lParam) {
        return null;
    }
    
    @Override
    LRESULT WM_SIZE(final long wParam, final long lParam) {
        if (this.ignoreResize) {
            return null;
        }
        if ((this.style & 0x40) != 0x0) {
            final LRESULT result = super.WM_SIZE(wParam, lParam);
            if (OS.IsWindowVisible(this.handle)) {
                final int uFlags = 133;
                OS.RedrawWindow(this.handle, (RECT)null, 0L, 133);
            }
            return result;
        }
        final boolean oldLockText = this.lockText;
        if ((this.style & 0x8) == 0x0) {
            this.lockText = true;
        }
        final LRESULT result2 = super.WM_SIZE(wParam, lParam);
        if ((this.style & 0x8) == 0x0) {
            this.lockText = oldLockText;
        }
        if ((this.style & 0x100) != 0x0) {
            this.setScrollWidth(this.scrollWidth);
        }
        this.forceScrollingToCaret();
        return result2;
    }
    
    void forceScrollingToCaret() {
        if ((this.style & 0x8) == 0x0) {
            final Point oldSelection = this.getSelection();
            final Point tmpSelection = new Point(0, 0);
            if (!oldSelection.equals((Object)tmpSelection)) {
                this.setSelection(tmpSelection);
                this.setSelection(oldSelection);
            }
        }
    }
    
    @Override
    LRESULT WM_UPDATEUISTATE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_UPDATEUISTATE(wParam, lParam);
        if (result != null) {
            return result;
        }
        OS.InvalidateRect(this.handle, (RECT)null, true);
        return result;
    }
    
    @Override
    LRESULT WM_WINDOWPOSCHANGING(final long wParam, final long lParam) {
        final LRESULT result = super.WM_WINDOWPOSCHANGING(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (!this.getDrawing()) {
            return result;
        }
        if (!OS.IsWindowVisible(this.handle)) {
            return result;
        }
        if (this.ignoreResize) {
            final WINDOWPOS lpwp = new WINDOWPOS();
            OS.MoveMemory(lpwp, lParam, WINDOWPOS.sizeof);
            if ((lpwp.flags & 0x1) == 0x0) {
                final WINDOWPOS windowpos2;
                final WINDOWPOS windowpos = windowpos2 = lpwp;
                windowpos2.flags |= 0x8;
                OS.MoveMemory(lParam, lpwp, WINDOWPOS.sizeof);
                OS.InvalidateRect(this.handle, (RECT)null, true);
                final RECT rect = new RECT();
                OS.GetWindowRect(this.handle, rect);
                final int width = rect.right - rect.left;
                final int height = rect.bottom - rect.top;
                if (width != 0 && height != 0) {
                    final long hwndParent = this.parent.handle;
                    long hwndChild = OS.GetWindow(hwndParent, 5);
                    OS.MapWindowPoints(0L, hwndParent, rect, 2);
                    final long rgn1 = OS.CreateRectRgn(rect.left, rect.top, rect.right, rect.bottom);
                    while (hwndChild != 0L) {
                        if (hwndChild != this.handle) {
                            OS.GetWindowRect(hwndChild, rect);
                            OS.MapWindowPoints(0L, hwndParent, rect, 2);
                            final long rgn2 = OS.CreateRectRgn(rect.left, rect.top, rect.right, rect.bottom);
                            OS.CombineRgn(rgn1, rgn1, rgn2, 4);
                            OS.DeleteObject(rgn2);
                        }
                        hwndChild = OS.GetWindow(hwndChild, 2);
                    }
                    final int flags = 1029;
                    OS.RedrawWindow(hwndParent, (RECT)null, rgn1, 1029);
                    OS.DeleteObject(rgn1);
                }
            }
        }
        return result;
    }
    
    @Override
    LRESULT wmChar(final long hwnd, final long wParam, final long lParam) {
        if (this.ignoreCharacter) {
            return null;
        }
        final LRESULT result = super.wmChar(hwnd, wParam, lParam);
        if (result != null) {
            return result;
        }
        switch ((int)wParam) {
            case 9: {
                return LRESULT.ZERO;
            }
            case 13: {
                if (!this.ignoreDefaultSelection) {
                    this.sendSelectionEvent(14);
                }
                this.ignoreDefaultSelection = false;
                if (this.getSelectionIndex() == -1 && (this.style & 0x4) != 0x0 && (this.style & 0x8) == 0x0) {
                    if (OS.SendMessage(this.handle, 343, 0L, 0L) != 0L) {
                        OS.SendMessage(this.handle, 335, 0L, 0L);
                    }
                    return LRESULT.ZERO;
                }
            }
            case 27: {
                if ((this.style & 0x4) != 0x0 && OS.SendMessage(this.handle, 343, 0L, 0L) == 0L) {
                    return LRESULT.ZERO;
                }
            }
            case 127: {
                if (OS.GetKeyState(17) >= 0) {
                    break;
                }
                if ((this.style & 0x8) != 0x0) {
                    return LRESULT.ZERO;
                }
                final Point selection = this.getSelection();
                final long hwndText = OS.GetDlgItem(this.handle, 1001);
                int x = selection.x;
                int y = selection.y;
                if (x == y) {
                    final String actText = this.getText().substring(0, x);
                    final Matcher m = Combo.CTRL_BS_PATTERN.matcher(actText);
                    if (m.find()) {
                        x = m.start();
                        y = m.end();
                        OS.SendMessage(hwndText, 177, (long)x, (long)y);
                    }
                }
                if (x < y) {
                    OS.SendMessage(hwndText, 194, 1L, 0L);
                }
                return LRESULT.ZERO;
            }
        }
        return result;
    }
    
    LRESULT wmClipboard(final long hwndText, final int msg, final long wParam, final long lParam) {
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
                OS.SendMessage(hwndText, 176, start, end);
                if (this.untranslateOffset(start[0]) != this.untranslateOffset(end[0])) {
                    newText = "";
                    call = true;
                    break;
                }
                break;
            }
            case 770: {
                OS.SendMessage(hwndText, 176, start, end);
                newText = this.getClipboardText();
                break;
            }
            case 199:
            case 772: {
                if (OS.SendMessage(hwndText, 198, 0L, 0L) != 0L) {
                    this.ignoreModify = true;
                    OS.CallWindowProc(Combo.EditProc, hwndText, msg, wParam, lParam);
                    final int length = OS.GetWindowTextLength(hwndText);
                    final int[] newStart = { 0 };
                    final int[] newEnd = { 0 };
                    OS.SendMessage(hwndText, 176, newStart, newEnd);
                    if (length != 0 && newStart[0] != newEnd[0]) {
                        final char[] buffer = new char[length + 1];
                        OS.GetWindowText(hwndText, buffer, length + 1);
                        newText = new String(buffer, newStart[0], newEnd[0] - newStart[0]);
                    }
                    else {
                        newText = "";
                    }
                    OS.CallWindowProc(Combo.EditProc, hwndText, msg, wParam, lParam);
                    OS.SendMessage(hwndText, 176, start, end);
                    this.ignoreModify = false;
                    break;
                }
                break;
            }
            case 12: {
                if (this.lockText) {
                    return null;
                }
                end[0] = OS.GetWindowTextLength(hwndText);
                final int length = OS.wcslen(lParam);
                final TCHAR buffer2 = new TCHAR(this.getCodePage(), length);
                final int byteCount = buffer2.length() * 2;
                OS.MoveMemory(buffer2, lParam, byteCount);
                newText = buffer2.toString(0, length);
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
                    OS.CallWindowProc(Combo.EditProc, hwndText, msg, wParam, lParam);
                }
                final TCHAR buffer2 = new TCHAR(this.getCodePage(), newText, true);
                if (msg == 12) {
                    final long hHeap = OS.GetProcessHeap();
                    final int byteCount2 = buffer2.length() * 2;
                    final long pszText = OS.HeapAlloc(hHeap, 8, byteCount2);
                    OS.MoveMemory(pszText, buffer2, byteCount2);
                    final long code = OS.CallWindowProc(Combo.EditProc, hwndText, msg, wParam, pszText);
                    OS.HeapFree(hHeap, 0, pszText);
                    return new LRESULT(code);
                }
                OS.SendMessage(hwndText, 194, 0L, buffer2);
                return LRESULT.ZERO;
            }
        }
        return null;
    }
    
    @Override
    LRESULT wmCommandChild(final long wParam, final long lParam) {
        final int code = OS.HIWORD(wParam);
        switch (code) {
            case 5: {
                if (this.ignoreModify) {
                    break;
                }
                this.noSelection = true;
                this.sendEvent(24);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
                this.noSelection = false;
                break;
            }
            case 1: {
                final int index = (int)OS.SendMessage(this.handle, 327, 0L, 0L);
                if (index != -1) {
                    OS.SendMessage(this.handle, 334, (long)index, 0L);
                }
                this.sendEvent(24);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
                this.sendSelectionEvent(13);
                break;
            }
            case 3: {
                this.sendFocusEvent(15);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
                break;
            }
            case 7: {
                this.setCursor();
                this.updateDropDownHeight();
                this.updateDropDownTheme();
                break;
            }
            case 4: {
                this.sendFocusEvent(16);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
                break;
            }
            case 1792:
            case 1793: {
                final Event event = new Event();
                event.doit = true;
                this.sendEvent(44, event);
                if (!event.doit) {
                    final long hwnd = lParam;
                    int bits1 = OS.GetWindowLong(hwnd, -20);
                    int bits2 = OS.GetWindowLong(hwnd, -16);
                    if (code == 1792) {
                        bits1 |= 0x3000;
                        bits2 |= 0x2;
                    }
                    else {
                        bits1 &= 0xFFFFCFFF;
                        bits2 &= 0xFFFFFFFD;
                    }
                    OS.SetWindowLong(hwnd, -20, bits1);
                    OS.SetWindowLong(hwnd, -16, bits2);
                }
                if (this.hooks(49) || this.filters(49) || (this.state & 0x400000) != 0x0) {
                    this.clearSegments(true);
                    this.state &= 0xFFBFFFFF;
                    this.applyEditSegments();
                    break;
                }
                break;
            }
        }
        return super.wmCommandChild(wParam, lParam);
    }
    
    @Override
    LRESULT wmIMEChar(final long hwnd, final long wParam, final long lParam) {
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
        final long result = this.callWindowProc(hwnd, 646, wParam, lParam);
        final MSG msg = new MSG();
        final int flags = 10420227;
        while (OS.PeekMessage(msg, hwnd, 258, 258, 10420227)) {
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
    
    @Override
    LRESULT wmKeyDown(final long hwnd, final long wParam, final long lParam) {
        if (this.ignoreCharacter) {
            return null;
        }
        LRESULT result = super.wmKeyDown(hwnd, wParam, lParam);
        if (result != null) {
            return result;
        }
        this.ignoreDefaultSelection = false;
        switch ((int)wParam) {
            case 37:
            case 38:
            case 39:
            case 40: {
                if (this.segments != null) {
                    long code = 0L;
                    final int[] start = { 0 };
                    final int[] end = { 0 };
                    final int[] newStart = { 0 };
                    final int[] newEnd = { 0 };
                    OS.SendMessage(this.handle, 320, start, end);
                    while (true) {
                        code = this.callWindowProc(hwnd, 256, wParam, lParam);
                        OS.SendMessage(this.handle, 320, newStart, newEnd);
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
                break;
            }
            case 13: {
                if ((this.style & 0x4) != 0x0 && OS.SendMessage(this.handle, 343, 0L, 0L) != 0L) {
                    this.ignoreDefaultSelection = true;
                    break;
                }
                break;
            }
        }
        return result;
    }
    
    @Override
    LRESULT wmSysKeyDown(final long hwnd, final long wParam, final long lParam) {
        final int oldSelection = (int)OS.SendMessage(this.handle, 327, 0L, 0L);
        final LRESULT result = super.wmSysKeyDown(hwnd, wParam, lParam);
        if (result != null) {
            return result;
        }
        if ((this.style & 0x8) == 0x0 && wParam == 40L) {
            final long code = this.callWindowProc(hwnd, 260, wParam, lParam);
            final int newSelection = (int)OS.SendMessage(this.handle, 327, 0L, 0L);
            if (oldSelection != newSelection) {
                this.sendEvent(24);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
                this.sendSelectionEvent(13, null, true);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
            }
            return new LRESULT(code);
        }
        return result;
    }
    
    static {
        CTRL_BS_PATTERN = Pattern.compile("\\r?\\n\\z|[\\p{Punct}]+[\\t ]*\\z|[^\\p{Punct}\\s\\n\\r]*[\\t ]*\\z");
        LIMIT = Integer.MAX_VALUE;
        ComboClass = new TCHAR(0, "COMBOBOX", true);
        final WNDCLASS lpWndClass = new WNDCLASS();
        OS.GetClassInfo(0L, Combo.ComboClass, lpWndClass);
        ComboProc = lpWndClass.lpfnWndProc;
        stateFlagsOffset = ((C.PTR_SIZEOF == 8) ? 104 : 84);
    }
}
