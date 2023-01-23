//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import java.util.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;

public class Link extends Control
{
    String text;
    int linkForeground;
    String[] ids;
    char[] mnemonics;
    int nextFocusItem;
    static final long LinkProc;
    static final TCHAR LinkClass;
    
    public Link(final Composite parent, final int style) {
        super(parent, style);
        this.linkForeground = -1;
        this.nextFocusItem = -1;
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
            case 15: {
                if (wParam != 0L) {
                    OS.SendMessage(hwnd, 792, wParam, 0L);
                    return 0L;
                }
                break;
            }
        }
        return OS.CallWindowProc(Link.LinkProc, hwnd, msg, wParam, lParam);
    }
    
    Point computeSizeInPixels(final int wHint, final int hHint, final boolean changed) {
        this.checkWidget();
        int width;
        int height;
        if (this.text.isEmpty()) {
            final long hDC = OS.GetDC(this.handle);
            final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
            final long oldFont = OS.SelectObject(hDC, newFont);
            final TEXTMETRIC lptm = new TEXTMETRIC();
            OS.GetTextMetrics(hDC, lptm);
            width = 0;
            height = lptm.tmHeight;
            if (newFont != 0L) {
                OS.SelectObject(hDC, oldFont);
            }
            OS.ReleaseDC(this.handle, hDC);
        }
        else {
            final SIZE size = new SIZE();
            final int maxWidth = (wHint == -1) ? Integer.MAX_VALUE : wHint;
            OS.SendMessage(this.handle, 1793, (long)maxWidth, size);
            width = size.cx;
            height = size.cy;
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
        this.state |= 0x100;
    }
    
    void createWidget() {
        super.createWidget();
        this.text = "";
        this.ids = new String[0];
        this.mnemonics = new char[0];
    }
    
    void enableWidget(final boolean enabled) {
        super.enableWidget(enabled);
        OS.InvalidateRect(this.handle, (RECT)null, true);
    }
    
    int getFocusItem() {
        final LITEM item = new LITEM();
        item.mask = 3;
        item.stateMask = 1;
        while (OS.SendMessage(this.handle, 1795, 0L, item) != 0L) {
            if ((item.state & 0x1) != 0x0) {
                return item.iLink;
            }
            final LITEM litem2;
            final LITEM litem = litem2 = item;
            ++litem2.iLink;
        }
        return -1;
    }
    
    public Color getLinkForeground() {
        this.checkWidget();
        if (this.linkForeground != -1) {
            return Color.win32_new((Device)this.display, this.linkForeground);
        }
        return Color.win32_new((Device)this.display, OS.GetSysColor(26));
    }
    
    String getNameText() {
        return this.getText();
    }
    
    public String getText() {
        this.checkWidget();
        return this.text;
    }
    
    boolean mnemonicHit(final char key) {
        final char uckey = Character.toUpperCase(key);
        for (int i = 0; i < this.mnemonics.length; ++i) {
            if (uckey == this.mnemonics[i]) {
                this.nextFocusItem = i;
                return this.setFocus() && this.setFocusItem(i);
            }
        }
        return false;
    }
    
    boolean mnemonicMatch(final char key) {
        final char uckey = Character.toUpperCase(key);
        for (final char mnemonic : this.mnemonics) {
            if (uckey == mnemonic) {
                return true;
            }
        }
        return false;
    }
    
    void parse(final String string) {
        final int length = string.length();
        this.ids = new String[length / 7];
        this.mnemonics = new char[length / 7];
        int index = 0;
        int state = 0;
        int linkIndex = 0;
        int linkStart = 0;
        int linkEnd = 0;
        int refStart = 0;
        int refEnd = 0;
        char mnemonic = '\0';
        while (index < length) {
            final char c = Character.toLowerCase(string.charAt(index));
            Label_0603: {
                switch (state) {
                    case 0: {
                        if (c == '<') {
                            ++state;
                            break;
                        }
                        if (c == '&') {
                            state = 16;
                            break;
                        }
                        break;
                    }
                    case 1: {
                        if (c == 'a') {
                            ++state;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        switch (c) {
                            case 'h': {
                                state = 7;
                                break Label_0603;
                            }
                            case '>': {
                                linkStart = index + 1;
                                ++state;
                                break Label_0603;
                            }
                            default: {
                                if (Character.isWhitespace(c)) {
                                    break Label_0603;
                                }
                                state = 13;
                                break Label_0603;
                            }
                        }
                        break;
                    }
                    case 3: {
                        if (c == '<') {
                            linkEnd = index;
                            ++state;
                            break;
                        }
                        break;
                    }
                    case 4: {
                        state = ((c == '/') ? (state + 1) : 3);
                        break;
                    }
                    case 5: {
                        state = ((c == 'a') ? (state + 1) : 3);
                        break;
                    }
                    case 6: {
                        if (c == '>') {
                            if (refStart == 0) {
                                refStart = linkStart;
                                refEnd = linkEnd;
                            }
                            this.ids[linkIndex] = string.substring(refStart, refEnd);
                            if (mnemonic != '\0') {
                                this.mnemonics[linkIndex] = mnemonic;
                            }
                            ++linkIndex;
                            linkStart = (linkEnd = (refStart = (refEnd = (mnemonic = '\0'))));
                            state = 0;
                            break;
                        }
                        state = 3;
                        break;
                    }
                    case 7: {
                        state = ((c == 'r') ? (state + 1) : 0);
                        break;
                    }
                    case 8: {
                        state = ((c == 'e') ? (state + 1) : 0);
                        break;
                    }
                    case 9: {
                        state = ((c == 'f') ? (state + 1) : 0);
                        break;
                    }
                    case 10: {
                        state = ((c == '=') ? (state + 1) : 0);
                        break;
                    }
                    case 11: {
                        if (c == '\"') {
                            ++state;
                            refStart = index + 1;
                            break;
                        }
                        state = 0;
                        break;
                    }
                    case 12: {
                        if (c == '\"') {
                            refEnd = index;
                            state = 2;
                            break;
                        }
                        break;
                    }
                    case 13: {
                        if (Character.isWhitespace(c)) {
                            state = 0;
                            break;
                        }
                        if (c == '=') {
                            ++state;
                            break;
                        }
                        break;
                    }
                    case 14: {
                        state = ((c == '\"') ? (state + 1) : 0);
                        break;
                    }
                    case 15: {
                        if (c == '\"') {
                            state = 2;
                            break;
                        }
                        break;
                    }
                    case 16: {
                        if (c == '<') {
                            state = 1;
                            break;
                        }
                        state = 0;
                        if (c != '&') {
                            mnemonic = Character.toUpperCase(c);
                            break;
                        }
                        break;
                    }
                    default: {
                        state = 0;
                        break;
                    }
                }
            }
            ++index;
        }
        this.ids = Arrays.copyOf(this.ids, linkIndex);
        this.mnemonics = Arrays.copyOf(this.mnemonics, linkIndex);
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.ids = null;
        this.mnemonics = null;
        this.text = null;
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
    
    boolean setFocusItem(final int index) {
        int bits = 0;
        if (index > 0) {
            bits = OS.GetWindowLong(this.handle, -16);
        }
        final LITEM item = new LITEM();
        item.mask = 3;
        item.stateMask = 1;
        final int activeIndex = this.getFocusItem();
        if (activeIndex == index) {
            return true;
        }
        if (activeIndex >= 0) {
            item.iLink = activeIndex;
            OS.SendMessage(this.handle, 1794, 0L, item);
        }
        item.iLink = index;
        item.state = 1;
        final long result = OS.SendMessage(this.handle, 1794, 0L, item);
        if (index > 0) {
            OS.SetWindowLong(this.handle, -16, bits);
        }
        return result != 0L;
    }
    
    public void setLinkForeground(final Color color) {
        this.checkWidget();
        int pixel = -1;
        if (color != null) {
            if (color.isDisposed()) {
                this.error(5);
            }
            pixel = color.handle;
        }
        if (pixel == this.linkForeground) {
            return;
        }
        this.linkForeground = pixel;
        OS.InvalidateRect(this.handle, (RECT)null, true);
    }
    
    public void setText(final String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        if (string.equals(this.text)) {
            return;
        }
        this.text = string;
        if ((this.state & 0x400000) != 0x0) {
            this.updateTextDirection(100663296);
        }
        final TCHAR buffer = new TCHAR(this.getCodePage(), string, true);
        OS.SetWindowText(this.handle, buffer);
        this.parse(string);
    }
    
    int resolveTextDirection() {
        return BidiUtil.resolveTextDirection(this.text);
    }
    
    boolean updateTextDirection(final int textDirection) {
        if (super.updateTextDirection(textDirection)) {
            final int flags = 100663296;
            this.style &= 0xF7FFFFFF;
            this.style &= 0xF9FFFFFF;
            this.style |= (textDirection & 0x6000000);
            this.updateOrientation();
            this.checkMirrored();
            return true;
        }
        return false;
    }
    
    int widgetStyle() {
        final int bits = super.widgetStyle();
        return bits | 0x10000;
    }
    
    TCHAR windowClass() {
        return Link.LinkClass;
    }
    
    long windowProc() {
        return Link.LinkProc;
    }
    
    LRESULT WM_CHAR(final long wParam, final long lParam) {
        final LRESULT result = super.WM_CHAR(wParam, lParam);
        if (result != null) {
            return result;
        }
        switch ((int)wParam) {
            case 9:
            case 13:
            case 32: {
                final long code = this.callWindowProc(this.handle, 256, wParam, lParam);
                return new LRESULT(code);
            }
            default: {
                return result;
            }
        }
    }
    
    LRESULT WM_GETDLGCODE(final long wParam, final long lParam) {
        long code = this.callWindowProc(this.handle, 135, wParam, lParam);
        final int count = this.ids.length;
        if (count == 0) {
            code |= 0x100L;
        }
        else if (count > 1) {
            final int limit = (OS.GetKeyState(16) < 0) ? 0 : (count - 1);
            if (this.getFocusItem() != limit) {
                code |= 0x2L;
            }
        }
        return new LRESULT(code);
    }
    
    LRESULT WM_KEYDOWN(final long wParam, final long lParam) {
        final LRESULT result = super.WM_KEYDOWN(wParam, lParam);
        if (result != null) {
            return result;
        }
        switch ((int)wParam) {
            case 9:
            case 13:
            case 32: {
                return LRESULT.ZERO;
            }
            default: {
                return result;
            }
        }
    }
    
    LRESULT WM_KILLFOCUS(final long wParam, final long lParam) {
        this.nextFocusItem = this.getFocusItem();
        return super.WM_KILLFOCUS(wParam, lParam);
    }
    
    LRESULT WM_NCHITTEST(final long wParam, final long lParam) {
        final LRESULT result = super.WM_NCHITTEST(wParam, lParam);
        if (result != null) {
            return result;
        }
        return new LRESULT(1L);
    }
    
    LRESULT WM_SETCURSOR(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SETCURSOR(wParam, lParam);
        if (result != null) {
            return result;
        }
        final long fDone = this.callWindowProc(this.handle, 32, wParam, lParam);
        if (fDone == 0L) {
            OS.DefWindowProc(this.handle, 32, wParam, lParam);
        }
        return LRESULT.ONE;
    }
    
    LRESULT WM_SETFOCUS(final long wParam, final long lParam) {
        if (this.ids.length > 1) {
            if (OS.GetKeyState(9) < 0) {
                if (OS.GetKeyState(16) < 0) {
                    this.setFocusItem(this.ids.length - 1);
                }
            }
            else if (this.nextFocusItem > 0) {
                this.setFocusItem(this.nextFocusItem);
            }
        }
        return super.WM_SETFOCUS(wParam, lParam);
    }
    
    LRESULT wmNotifyChild(final NMHDR hdr, final long wParam, final long lParam) {
        Label_0239: {
            switch (hdr.code) {
                case -4:
                case -2: {
                    final NMLINK item = new NMLINK();
                    OS.MoveMemory(item, lParam, NMLINK.sizeof);
                    final Event event = new Event();
                    event.text = this.ids[item.iLink];
                    this.sendSelectionEvent(13, event, true);
                    break;
                }
                case -12: {
                    final NMCUSTOMDRAW nmcd = new NMCUSTOMDRAW();
                    OS.MoveMemory(nmcd, lParam, NMCUSTOMDRAW.sizeof);
                    switch (nmcd.dwDrawStage) {
                        case 1: {
                            if (!OS.IsWindowEnabled(this.handle) || this.linkForeground != -1) {
                                return new LRESULT(32L);
                            }
                            break Label_0239;
                        }
                        case 65537: {
                            if (!OS.IsWindowEnabled(this.handle)) {
                                OS.SetTextColor(nmcd.hdc, OS.GetSysColor(17));
                                break Label_0239;
                            }
                            if (this.linkForeground != -1 && nmcd.dwItemSpec != -1L) {
                                OS.SetTextColor(nmcd.hdc, this.linkForeground);
                                break Label_0239;
                            }
                            break Label_0239;
                        }
                        default: {
                            break Label_0239;
                        }
                    }
                    break;
                }
            }
        }
        return super.wmNotifyChild(hdr, wParam, lParam);
    }
    
    static {
        LinkClass = new TCHAR(0, "SysLink", true);
        final WNDCLASS lpWndClass = new WNDCLASS();
        OS.GetClassInfo(0L, Link.LinkClass, lpWndClass);
        LinkProc = lpWndClass.lpfnWndProc;
        lpWndClass.hInstance = OS.GetModuleHandle((char[])null);
        final WNDCLASS wndclass3;
        final WNDCLASS wndclass = wndclass3 = lpWndClass;
        wndclass3.style &= 0xFFFFBFFF;
        final WNDCLASS wndclass4;
        final WNDCLASS wndclass2 = wndclass4 = lpWndClass;
        wndclass4.style |= 0x8;
        OS.RegisterClass(Link.LinkClass, lpWndClass);
    }
}
