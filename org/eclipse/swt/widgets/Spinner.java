//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class Spinner extends Composite
{
    long hwndText;
    long hwndUpDown;
    boolean ignoreModify;
    boolean ignoreCharacter;
    int pageIncrement;
    int digits;
    static final long EditProc;
    static final TCHAR EditClass;
    static final long UpDownProc;
    static final TCHAR UpDownClass;
    public static final int LIMIT;
    
    public Spinner(final Composite parent, final int style) {
        super(parent, checkStyle(style));
    }
    
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        if (hwnd == this.hwndText) {
            return OS.CallWindowProc(Spinner.EditProc, hwnd, msg, wParam, lParam);
        }
        if (hwnd == this.hwndUpDown) {
            return OS.CallWindowProc(Spinner.UpDownProc, hwnd, msg, wParam, lParam);
        }
        return OS.DefWindowProc(this.handle, msg, wParam, lParam);
    }
    
    static int checkStyle(final int style) {
        return style & 0xFFFFFCFF;
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    void createHandle() {
        super.createHandle();
        this.state &= 0xFFFFFEFD;
        final long hInstance = OS.GetModuleHandle((char[])null);
        int textExStyle = 0;
        int textStyle = 1409286272;
        if ((this.style & 0x8) != 0x0) {
            textStyle |= 0x800;
        }
        if ((this.style & 0x4000000) != 0x0) {
            textExStyle |= 0x400000;
        }
        this.hwndText = OS.CreateWindowEx(textExStyle, Spinner.EditClass, (TCHAR)null, textStyle, 0, 0, 0, 0, this.handle, 0L, hInstance, (CREATESTRUCT)null);
        if (this.hwndText == 0L) {
            this.error(2);
        }
        OS.SetWindowLongPtr(this.hwndText, -12, this.hwndText);
        int upDownStyle = 1342177296;
        if ((this.style & 0x40) != 0x0) {
            upDownStyle |= 0x1;
        }
        this.hwndUpDown = OS.CreateWindowEx(0, Spinner.UpDownClass, (TCHAR)null, upDownStyle, 0, 0, 0, 0, this.handle, 0L, hInstance, (CREATESTRUCT)null);
        if (this.hwndUpDown == 0L) {
            this.error(2);
        }
        final int flags = 19;
        OS.SetWindowPos(this.hwndText, this.hwndUpDown, 0, 0, 0, 0, 19);
        OS.SetWindowLongPtr(this.hwndUpDown, -12, this.hwndUpDown);
        OS.SendMessage(this.hwndUpDown, 1135, 0L, 100L);
        OS.SendMessage(this.hwndUpDown, 1137, 0L, 0L);
        this.pageIncrement = 10;
        this.digits = 0;
        OS.SetWindowText(this.hwndText, new char[] { '0', '\0' });
    }
    
    public void addModifyListener(final ModifyListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(24, (Listener)typedListener);
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
    
    void addVerifyListener(final VerifyListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(25, (Listener)typedListener);
    }
    
    Point computeSizeInPixels(final int wHint, final int hHint, final boolean changed) {
        this.checkWidget();
        int width = 0;
        int height = 0;
        if (wHint == -1 || hHint == -1) {
            long oldFont = 0L;
            final long hDC = OS.GetDC(this.hwndText);
            final long newFont = OS.SendMessage(this.hwndText, 49, 0L, 0L);
            if (newFont != 0L) {
                oldFont = OS.SelectObject(hDC, newFont);
            }
            final TEXTMETRIC tm = new TEXTMETRIC();
            OS.GetTextMetrics(hDC, tm);
            height = tm.tmHeight;
            final RECT rect = new RECT();
            final int[] max = { 0 };
            OS.SendMessage(this.hwndUpDown, 1136, (int[])null, max);
            String string = String.valueOf(max[0]);
            if (this.digits > 0) {
                final StringBuilder buffer = new StringBuilder();
                buffer.append(string);
                buffer.append(this.getDecimalSeparator());
                for (int count = this.digits - string.length(); count >= 0; --count) {
                    buffer.append("0");
                }
                string = buffer.toString();
            }
            final char[] buffer2 = string.toCharArray();
            final int flags = 11264;
            OS.DrawText(hDC, buffer2, buffer2.length, rect, 11264);
            width = rect.right - rect.left;
            if (newFont != 0L) {
                OS.SelectObject(hDC, oldFont);
            }
            OS.ReleaseDC(this.hwndText, hDC);
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
        else {
            final int borderAdjustment = ((this.style & 0x800) != 0x0) ? -1 : 3;
            final int upDownHeight = OS.GetSystemMetrics(20);
            height = Math.max(height, upDownHeight + borderAdjustment);
        }
        final Rectangle trim = this.computeTrimInPixels(0, 0, width, height);
        return new Point(trim.width, trim.height);
    }
    
    Rectangle computeTrimInPixels(int x, int y, int width, int height) {
        this.checkWidget();
        final RECT rect = new RECT();
        OS.SetRect(rect, x, y, x + width, y + height);
        int bits0 = OS.GetWindowLong(this.handle, -16);
        int bits2 = OS.GetWindowLong(this.handle, -20);
        if ((bits0 & 0x800000) != 0x0) {
            bits0 &= 0xFF7FFFFF;
            bits2 |= 0x200;
        }
        OS.AdjustWindowRectEx(rect, bits0, false, bits2);
        width = rect.right - rect.left;
        height = rect.bottom - rect.top;
        final long margins = OS.SendMessage(this.hwndText, 212, 0L, 0L);
        x -= OS.LOWORD(margins);
        width += OS.LOWORD(margins) + OS.HIWORD(margins);
        if ((this.style & 0x800) != 0x0) {
            --x;
            --y;
            width += 2;
            height += 2;
        }
        width += OS.GetSystemMetrics(2);
        return new Rectangle(x, y, width, height);
    }
    
    public void copy() {
        this.checkWidget();
        OS.SendMessage(this.hwndText, 769, 0L, 0L);
    }
    
    public void cut() {
        this.checkWidget();
        if ((this.style & 0x8) != 0x0) {
            return;
        }
        OS.SendMessage(this.hwndText, 768, 0L, 0L);
    }
    
    int defaultBackground() {
        return OS.GetSysColor(5);
    }
    
    void enableWidget(final boolean enabled) {
        super.enableWidget(enabled);
        OS.EnableWindow(this.hwndText, enabled);
        OS.EnableWindow(this.hwndUpDown, enabled);
    }
    
    void deregister() {
        super.deregister();
        this.display.removeControl(this.hwndText);
        this.display.removeControl(this.hwndUpDown);
    }
    
    boolean hasFocus() {
        final long hwndFocus = OS.GetFocus();
        return hwndFocus == this.handle || hwndFocus == this.hwndText || hwndFocus == this.hwndUpDown;
    }
    
    public int getDigits() {
        this.checkWidget();
        return this.digits;
    }
    
    String getDecimalSeparator() {
        final char[] data = new char[4];
        final int size = OS.GetLocaleInfo(1024, 14, data, 4);
        return (size != 0) ? new String(data, 0, size - 1) : ".";
    }
    
    public int getIncrement() {
        this.checkWidget();
        final UDACCEL udaccel = new UDACCEL();
        OS.SendMessage(this.hwndUpDown, 1132, 1L, udaccel);
        return udaccel.nInc;
    }
    
    public int getMaximum() {
        this.checkWidget();
        final int[] max = { 0 };
        OS.SendMessage(this.hwndUpDown, 1136, (int[])null, max);
        return max[0];
    }
    
    public int getMinimum() {
        this.checkWidget();
        final int[] min = { 0 };
        OS.SendMessage(this.hwndUpDown, 1136, min, (int[])null);
        return min[0];
    }
    
    public int getPageIncrement() {
        this.checkWidget();
        return this.pageIncrement;
    }
    
    public int getSelection() {
        this.checkWidget();
        return (int)OS.SendMessage(this.hwndUpDown, 1138, 0L, 0L);
    }
    
    int getSelectionText(final boolean[] parseFail) {
        final int length = OS.GetWindowTextLength(this.hwndText);
        final char[] buffer = new char[length + 1];
        OS.GetWindowText(this.hwndText, buffer, length + 1);
        final String string = new String(buffer, 0, length);
        try {
            int value;
            if (this.digits > 0) {
                final String decimalSeparator = this.getDecimalSeparator();
                final int index = string.indexOf(decimalSeparator);
                if (index != -1) {
                    final int startIndex = (string.startsWith("+") || string.startsWith("-")) ? 1 : 0;
                    final String wholePart = (startIndex != index) ? string.substring(startIndex, index) : "0";
                    String decimalPart = string.substring(index + 1);
                    if (decimalPart.length() > this.digits) {
                        decimalPart = decimalPart.substring(0, this.digits);
                    }
                    else {
                        for (int i = this.digits - decimalPart.length(), j = 0; j < i; ++j) {}
                    }
                    int wholeValue = Integer.parseInt(wholePart);
                    final int decimalValue = Integer.parseInt(decimalPart);
                    for (int k = 0; k < this.digits; ++k) {
                        wholeValue *= 10;
                    }
                    value = wholeValue + decimalValue;
                    if (string.startsWith("-")) {
                        value = -value;
                    }
                }
                else {
                    value = Integer.parseInt(string);
                    for (int l = 0; l < this.digits; ++l) {
                        value *= 10;
                    }
                }
            }
            else {
                value = Integer.parseInt(string);
            }
            final int[] max = { 0 };
            final int[] min = { 0 };
            OS.SendMessage(this.hwndUpDown, 1136, min, max);
            if (min[0] <= value && value <= max[0]) {
                return value;
            }
        }
        catch (NumberFormatException ex) {}
        parseFail[0] = true;
        return -1;
    }
    
    public String getText() {
        this.checkWidget();
        final int length = OS.GetWindowTextLength(this.hwndText);
        if (length == 0) {
            return "";
        }
        final char[] buffer = new char[length + 1];
        OS.GetWindowText(this.hwndText, buffer, length + 1);
        return new String(buffer, 0, length);
    }
    
    public int getTextLimit() {
        this.checkWidget();
        return (int)OS.SendMessage(this.hwndText, 213, 0L, 0L) & Integer.MAX_VALUE;
    }
    
    boolean isUseWsBorder() {
        return true;
    }
    
    public void paste() {
        this.checkWidget();
        if ((this.style & 0x8) != 0x0) {
            return;
        }
        OS.SendMessage(this.hwndText, 770, 0L, 0L);
    }
    
    void register() {
        super.register();
        this.display.addControl(this.hwndText, (Control)this);
        this.display.addControl(this.hwndUpDown, (Control)this);
    }
    
    void releaseHandle() {
        super.releaseHandle();
        final long n = 0L;
        this.hwndUpDown = 0L;
        this.hwndText = 0L;
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
    
    void removeVerifyListener(final VerifyListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(25, (SWTEventListener)listener);
    }
    
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
        if (OS.GetKeyState(1) < 0) {
            return true;
        }
        String oldText = "";
        final int[] start = { 0 };
        final int[] end = { 0 };
        OS.SendMessage(this.hwndText, 176, start, end);
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
                final int length = OS.GetWindowTextLength(this.hwndText);
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
        OS.SendMessage(this.hwndText, 177, (long)start[0], (long)end[0]);
        OS.SendMessage(this.hwndText, 194, 0L, buffer);
        return false;
    }
    
    void setBackgroundImage(final long hBitmap) {
        super.setBackgroundImage(hBitmap);
        OS.InvalidateRect(this.hwndText, (RECT)null, true);
    }
    
    void setBackgroundPixel(final int pixel) {
        super.setBackgroundPixel(pixel);
        OS.InvalidateRect(this.hwndText, (RECT)null, true);
    }
    
    public void setDigits(final int value) {
        this.checkWidget();
        if (value < 0) {
            this.error(5);
        }
        if (value == this.digits) {
            return;
        }
        this.digits = value;
        final int pos = (int)OS.SendMessage(this.hwndUpDown, 1138, 0L, 0L);
        this.setSelection(pos, false, true, false);
    }
    
    void setForegroundPixel(final int pixel) {
        super.setForegroundPixel(pixel);
        OS.InvalidateRect(this.hwndText, (RECT)null, true);
    }
    
    public void setIncrement(final int value) {
        this.checkWidget();
        if (value < 1) {
            return;
        }
        final long hHeap = OS.GetProcessHeap();
        final int count = (int)OS.SendMessage(this.hwndUpDown, 1132, 0L, (UDACCEL)null);
        final long udaccels = OS.HeapAlloc(hHeap, 8, UDACCEL.sizeof * count);
        OS.SendMessage(this.hwndUpDown, 1132, (long)count, udaccels);
        int first = -1;
        final UDACCEL udaccel = new UDACCEL();
        for (int i = 0; i < count; ++i) {
            final long offset = udaccels + i * UDACCEL.sizeof;
            OS.MoveMemory(udaccel, offset, UDACCEL.sizeof);
            if (first == -1) {
                first = udaccel.nInc;
            }
            udaccel.nInc = udaccel.nInc / first * value;
            OS.MoveMemory(offset, udaccel, UDACCEL.sizeof);
        }
        OS.SendMessage(this.hwndUpDown, 1131, (long)count, udaccels);
        OS.HeapFree(hHeap, 0, udaccels);
    }
    
    public void setMaximum(final int value) {
        this.checkWidget();
        final int[] min = { 0 };
        OS.SendMessage(this.hwndUpDown, 1136, min, (int[])null);
        if (value < min[0]) {
            return;
        }
        final int pos = (int)OS.SendMessage(this.hwndUpDown, 1138, 0L, 0L);
        OS.SendMessage(this.hwndUpDown, 1135, (long)min[0], (long)value);
        if (pos > value) {
            this.setSelection(value, true, true, false);
        }
    }
    
    public void setMinimum(final int value) {
        this.checkWidget();
        final int[] max = { 0 };
        OS.SendMessage(this.hwndUpDown, 1136, (int[])null, max);
        if (value > max[0]) {
            return;
        }
        final int pos = (int)OS.SendMessage(this.hwndUpDown, 1138, 0L, 0L);
        OS.SendMessage(this.hwndUpDown, 1135, (long)value, (long)max[0]);
        if (pos < value) {
            this.setSelection(value, true, true, false);
        }
    }
    
    public void setPageIncrement(final int value) {
        this.checkWidget();
        if (value < 1) {
            return;
        }
        this.pageIncrement = value;
    }
    
    public void setSelection(int value) {
        this.checkWidget();
        final int[] max = { 0 };
        final int[] min = { 0 };
        OS.SendMessage(this.hwndUpDown, 1136, min, max);
        value = Math.min(Math.max(min[0], value), max[0]);
        this.setSelection(value, true, true, false);
    }
    
    void setSelection(final int value, final boolean setPos, final boolean setText, final boolean notify) {
        if (setPos) {
            OS.SendMessage(this.hwndUpDown, 1137, 0L, (long)value);
        }
        if (setText) {
            String string;
            if (this.digits == 0) {
                string = String.valueOf(value);
            }
            else {
                string = String.valueOf(Math.abs(value));
                final String decimalSeparator = this.getDecimalSeparator();
                int index = string.length() - this.digits;
                final StringBuilder buffer = new StringBuilder();
                if (value < 0) {
                    buffer.append("-");
                }
                if (index > 0) {
                    buffer.append(string.substring(0, index));
                    buffer.append(decimalSeparator);
                    buffer.append(string.substring(index));
                }
                else {
                    buffer.append("0");
                    buffer.append(decimalSeparator);
                    while (index++ < 0) {
                        buffer.append("0");
                    }
                    buffer.append(string);
                }
                string = buffer.toString();
            }
            if (this.hooks(25) || this.filters(25)) {
                final int length = OS.GetWindowTextLength(this.hwndText);
                string = this.verifyText(string, 0, length, null);
                if (string == null) {
                    return;
                }
            }
            final TCHAR buffer2 = new TCHAR(this.getCodePage(), string, true);
            OS.SetWindowText(this.hwndText, buffer2);
            OS.SendMessage(this.hwndText, 177, 0L, -1L);
            OS.NotifyWinEvent(32773, this.hwndText, -4, 0);
        }
        if (notify) {
            this.sendSelectionEvent(13);
        }
    }
    
    public void setTextLimit(final int limit) {
        this.checkWidget();
        if (limit == 0) {
            this.error(7);
        }
        OS.SendMessage(this.hwndText, 197, (long)limit, 0L);
    }
    
    void setToolTipText(final Shell shell, final String string) {
        shell.setToolTipText(this.hwndText, string);
        shell.setToolTipText(this.hwndUpDown, string);
    }
    
    public void setValues(int selection, final int minimum, final int maximum, final int digits, final int increment, final int pageIncrement) {
        this.checkWidget();
        if (maximum < minimum) {
            return;
        }
        if (digits < 0) {
            return;
        }
        if (increment < 1) {
            return;
        }
        if (pageIncrement < 1) {
            return;
        }
        selection = Math.min(Math.max(minimum, selection), maximum);
        this.setIncrement(increment);
        this.pageIncrement = pageIncrement;
        this.digits = digits;
        OS.SendMessage(this.hwndUpDown, 1135, (long)minimum, (long)maximum);
        this.setSelection(selection, true, true, false);
    }
    
    void subclass() {
        super.subclass();
        final long newProc = this.display.windowProc;
        OS.SetWindowLongPtr(this.hwndText, -4, newProc);
        OS.SetWindowLongPtr(this.hwndUpDown, -4, newProc);
    }
    
    void unsubclass() {
        super.unsubclass();
        OS.SetWindowLongPtr(this.hwndText, -4, Spinner.EditProc);
        OS.SetWindowLongPtr(this.hwndUpDown, -4, Spinner.UpDownProc);
    }
    
    void updateOrientation() {
        super.updateOrientation();
        int bits = OS.GetWindowLong(this.hwndText, -20);
        int bits2 = OS.GetWindowLong(this.hwndText, -16);
        if ((this.style & 0x4000000) != 0x0) {
            bits |= 0x1000;
            bits2 |= 0x2;
        }
        else {
            bits &= 0xFFFFEFFF;
            bits2 &= 0xFFFFFFFD;
        }
        OS.SetWindowLong(this.hwndText, -16, bits2);
        OS.SetWindowLong(this.hwndText, -20, bits);
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        OS.SetWindowPos(this.handle, 0L, 0, 0, width - 1, height - 1, 6);
        OS.SetWindowPos(this.handle, 0L, 0, 0, width, height, 6);
    }
    
    String verifyText(String string, final int start, final int end, final Event keyEvent) {
        final Event event = new Event();
        event.text = string;
        event.start = start;
        event.end = end;
        if (keyEvent != null) {
            event.character = keyEvent.character;
            event.keyCode = keyEvent.keyCode;
            event.stateMask = keyEvent.stateMask;
        }
        int index = 0;
        if (this.digits > 0) {
            final String decimalSeparator = this.getDecimalSeparator();
            index = string.indexOf(decimalSeparator);
            if (index != -1) {
                string = string.substring(0, index) + string.substring(index + 1);
            }
            index = 0;
        }
        if (string.length() > 0) {
            final int[] min = { 0 };
            OS.SendMessage(this.hwndUpDown, 1136, min, (int[])null);
            if (min[0] < 0 && string.charAt(0) == '-') {
                ++index;
            }
        }
        while (index < string.length() && Character.isDigit(string.charAt(index))) {
            ++index;
        }
        event.doit = (index == string.length());
        this.sendEvent(25, event);
        if (!event.doit || this.isDisposed()) {
            return null;
        }
        return event.text;
    }
    
    long windowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (hwnd != this.hwndText && hwnd != this.hwndUpDown) {
            return super.windowProc(hwnd, msg, wParam, lParam);
        }
        LRESULT result = null;
        switch (msg) {
            case 258: {
                result = this.wmChar(hwnd, wParam, lParam);
                break;
            }
            case 646: {
                result = this.wmIMEChar(hwnd, wParam, lParam);
                break;
            }
            case 256: {
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
            case 7: {
                result = this.wmSetFocus(hwnd, wParam, lParam);
                break;
            }
            case 8: {
                result = this.wmKillFocus(hwnd, wParam, lParam);
                break;
            }
            case 15: {
                result = this.wmPaint(hwnd, wParam, lParam);
                break;
            }
            case 791: {
                result = this.wmPrint(hwnd, wParam, lParam);
                break;
            }
            case 123: {
                result = this.wmContextMenu(hwnd, wParam, lParam);
                break;
            }
            case 199:
            case 768:
            case 770:
            case 771:
            case 772: {
                if (hwnd == this.hwndText) {
                    result = this.wmClipboard(hwnd, msg, wParam, lParam);
                    break;
                }
                break;
            }
        }
        if (result != null) {
            return result.value;
        }
        return this.callWindowProc(hwnd, msg, wParam, lParam);
    }
    
    LRESULT WM_ERASEBKGND(final long wParam, final long lParam) {
        super.WM_ERASEBKGND(wParam, lParam);
        this.drawBackground(wParam);
        return LRESULT.ONE;
    }
    
    LRESULT WM_KILLFOCUS(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_SETFOCUS(final long wParam, final long lParam) {
        OS.SetFocus(this.hwndText);
        OS.SendMessage(this.hwndText, 177, 0L, -1L);
        return null;
    }
    
    LRESULT WM_SETFONT(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SETFONT(wParam, lParam);
        if (result != null) {
            return result;
        }
        OS.SendMessage(this.hwndText, 48, wParam, lParam);
        return result;
    }
    
    LRESULT WM_SIZE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SIZE(wParam, lParam);
        if (this.isDisposed()) {
            return result;
        }
        final int width = OS.LOWORD(lParam);
        final int height = OS.HIWORD(lParam);
        final int upDownWidth = OS.GetSystemMetrics(2) - 1;
        final int textWidth = width - upDownWidth;
        int borderAdjustment = 0;
        if ((this.style & 0x800) != 0x0 && !this.display.useWsBorderText) {
            borderAdjustment = OS.GetSystemMetrics(46) - OS.GetSystemMetrics(6);
            ++borderAdjustment;
        }
        final int flags = 52;
        OS.SetWindowPos(this.hwndText, 0L, 0, borderAdjustment, textWidth, height - borderAdjustment, 52);
        OS.SetWindowPos(this.hwndUpDown, 0L, textWidth, 0, upDownWidth, height, 52);
        return result;
    }
    
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
    
    LRESULT wmChar(final long hwnd, final long wParam, final long lParam) {
        if (this.ignoreCharacter) {
            return null;
        }
        final LRESULT result = super.wmChar(hwnd, wParam, lParam);
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
    
    LRESULT wmClipboard(final long hwndText, final int msg, final long wParam, final long lParam) {
        if ((this.style & 0x8) != 0x0) {
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
                if (start[0] != end[0]) {
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
                    OS.CallWindowProc(Spinner.EditProc, hwndText, msg, wParam, lParam);
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
                    OS.CallWindowProc(Spinner.EditProc, hwndText, msg, wParam, lParam);
                    OS.SendMessage(hwndText, 176, start, end);
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
                    OS.CallWindowProc(Spinner.EditProc, hwndText, msg, wParam, lParam);
                }
                final TCHAR buffer2 = new TCHAR(this.getCodePage(), newText, true);
                if (msg == 12) {
                    final long hHeap = OS.GetProcessHeap();
                    final int byteCount = buffer2.length() * 2;
                    final long pszText = OS.HeapAlloc(hHeap, 8, byteCount);
                    OS.MoveMemory(pszText, buffer2, byteCount);
                    final long code = OS.CallWindowProc(Spinner.EditProc, hwndText, msg, wParam, pszText);
                    OS.HeapFree(hHeap, 0, pszText);
                    return new LRESULT(code);
                }
                OS.SendMessage(hwndText, 194, 0L, buffer2);
                return LRESULT.ZERO;
            }
        }
        return null;
    }
    
    LRESULT wmCommandChild(final long wParam, final long lParam) {
        final int code = OS.HIWORD(wParam);
        switch (code) {
            case 768: {
                if (this.ignoreModify) {
                    break;
                }
                final boolean[] parseFail = { false };
                final int value = this.getSelectionText(parseFail);
                if (!parseFail[0]) {
                    final int pos = (int)OS.SendMessage(this.hwndUpDown, 1138, 0L, 0L);
                    if (pos != value) {
                        this.setSelection(value, true, false, true);
                    }
                }
                this.sendEvent(24);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
                break;
            }
        }
        return super.wmCommandChild(wParam, lParam);
    }
    
    LRESULT wmKeyDown(final long hwnd, final long wParam, final long lParam) {
        if (this.ignoreCharacter) {
            return null;
        }
        final LRESULT result = super.wmKeyDown(hwnd, wParam, lParam);
        if (result != null) {
            return result;
        }
        final UDACCEL udaccel = new UDACCEL();
        OS.SendMessage(this.hwndUpDown, 1132, 1L, udaccel);
        int delta = 0;
        switch ((int)wParam) {
            case 38: {
                delta = udaccel.nInc;
                break;
            }
            case 40: {
                delta = -udaccel.nInc;
                break;
            }
            case 33: {
                delta = this.pageIncrement;
                break;
            }
            case 34: {
                delta = -this.pageIncrement;
                break;
            }
        }
        if (delta != 0) {
            final boolean[] parseFail = { false };
            int value = this.getSelectionText(parseFail);
            if (parseFail[0]) {
                value = (int)OS.SendMessage(this.hwndUpDown, 1138, 0L, 0L);
            }
            int newValue = value + delta;
            final int[] max = { 0 };
            final int[] min = { 0 };
            OS.SendMessage(this.hwndUpDown, 1136, min, max);
            if ((this.style & 0x40) != 0x0) {
                if (newValue < min[0]) {
                    newValue = max[0];
                }
                if (newValue > max[0]) {
                    newValue = min[0];
                }
            }
            newValue = Math.min(Math.max(min[0], newValue), max[0]);
            if (value != newValue) {
                this.setSelection(newValue, true, true, true);
            }
        }
        switch ((int)wParam) {
            case 38:
            case 40: {
                return LRESULT.ZERO;
            }
            default: {
                return result;
            }
        }
    }
    
    LRESULT wmKillFocus(final long hwnd, final long wParam, final long lParam) {
        final boolean[] parseFail = { false };
        int value = this.getSelectionText(parseFail);
        if (parseFail[0]) {
            value = (int)OS.SendMessage(this.hwndUpDown, 1138, 0L, 0L);
            this.setSelection(value, false, true, false);
        }
        return super.wmKillFocus(hwnd, wParam, lParam);
    }
    
    LRESULT wmNotifyChild(final NMHDR hdr, final long wParam, final long lParam) {
        switch (hdr.code) {
            case -722: {
                final NMUPDOWN lpnmud = new NMUPDOWN();
                OS.MoveMemory(lpnmud, lParam, NMUPDOWN.sizeof);
                int value = lpnmud.iPos + lpnmud.iDelta;
                final int[] max = { 0 };
                final int[] min = { 0 };
                OS.SendMessage(this.hwndUpDown, 1136, min, max);
                if ((this.style & 0x40) != 0x0) {
                    if (value < min[0]) {
                        value = max[0];
                    }
                    if (value > max[0]) {
                        value = min[0];
                    }
                }
                value = Math.min(Math.max(min[0], value), max[0]);
                if (value != lpnmud.iPos) {
                    this.setSelection(value, true, true, true);
                }
                return LRESULT.ONE;
            }
            default: {
                return super.wmNotifyChild(hdr, wParam, lParam);
            }
        }
    }
    
    LRESULT wmScrollChild(final long wParam, final long lParam) {
        final int code = OS.LOWORD(wParam);
        switch (code) {
            case 4: {
                this.sendSelectionEvent(13);
                break;
            }
        }
        return super.wmScrollChild(wParam, lParam);
    }
    
    static {
        EditClass = new TCHAR(0, "EDIT", true);
        UpDownClass = new TCHAR(0, "msctls_updown32", true);
        final WNDCLASS lpWndClass = new WNDCLASS();
        OS.GetClassInfo(0L, Spinner.EditClass, lpWndClass);
        EditProc = lpWndClass.lpfnWndProc;
        OS.GetClassInfo(0L, Spinner.UpDownClass, lpWndClass);
        UpDownProc = lpWndClass.lpfnWndProc;
        LIMIT = Integer.MAX_VALUE;
    }
}
