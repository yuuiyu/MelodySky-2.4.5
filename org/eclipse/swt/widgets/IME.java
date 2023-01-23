//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.ole.win32.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class IME extends Widget
{
    Canvas parent;
    int caretOffset;
    int startOffset;
    int commitCount;
    String text;
    int[] ranges;
    TextStyle[] styles;
    static final int WM_MSIME_MOUSE;
    static final int UNDERLINE_IME_DOT = 65536;
    static final int UNDERLINE_IME_DASH = 131072;
    static final int UNDERLINE_IME_THICK = 196608;
    
    IME() {
    }
    
    public IME(final Canvas parent, final int style) {
        super((Widget)parent, style);
        this.parent = parent;
        this.createWidget();
    }
    
    void createWidget() {
        this.text = "";
        this.startOffset = -1;
        if (this.parent.getIME() == null) {
            this.parent.setIME(this);
        }
    }
    
    public int getCaretOffset() {
        this.checkWidget();
        return this.startOffset + this.caretOffset;
    }
    
    public int getCommitCount() {
        this.checkWidget();
        return this.commitCount;
    }
    
    public int getCompositionOffset() {
        this.checkWidget();
        return this.startOffset;
    }
    
    TF_DISPLAYATTRIBUTE getDisplayAttribute(final short langid, final int attInfo) {
        final long[] ppv = { 0L };
        int hr = COM.CoCreateInstance(COM.CLSID_TF_InputProcessorProfiles, 0L, 1, COM.IID_ITfInputProcessorProfiles, ppv);
        TF_DISPLAYATTRIBUTE pda = null;
        if (hr == 0) {
            final ITfInputProcessorProfiles pProfiles = new ITfInputProcessorProfiles(ppv[0]);
            final GUID pclsid = new GUID();
            final GUID pguidProfile = new GUID();
            hr = pProfiles.GetDefaultLanguageProfile((int)langid, COM.GUID_TFCAT_TIP_KEYBOARD, pclsid, pguidProfile);
            if (hr == 0) {
                hr = COM.CoCreateInstance(pclsid, 0L, 1, COM.IID_ITfDisplayAttributeProvider, ppv);
                if (hr == 0) {
                    final ITfDisplayAttributeProvider pProvider = new ITfDisplayAttributeProvider(ppv[0]);
                    hr = pProvider.EnumDisplayAttributeInfo(ppv);
                    if (hr == 0) {
                        final IEnumTfDisplayAttributeInfo pEnum = new IEnumTfDisplayAttributeInfo(ppv[0]);
                        final TF_DISPLAYATTRIBUTE tempPda = new TF_DISPLAYATTRIBUTE();
                        while ((hr = pEnum.Next(1, ppv, (int[])null)) == 0) {
                            final ITfDisplayAttributeInfo pDispInfo = new ITfDisplayAttributeInfo(ppv[0]);
                            pDispInfo.GetAttributeInfo(tempPda);
                            pDispInfo.Release();
                            if (tempPda.bAttr == attInfo) {
                                pda = tempPda;
                                break;
                            }
                        }
                        pEnum.Release();
                    }
                    pProvider.Release();
                }
            }
            pProfiles.Release();
        }
        if (pda == null) {
            pda = new TF_DISPLAYATTRIBUTE();
            switch (attInfo) {
                case 0: {
                    pda.lsStyle = 4;
                    break;
                }
                case 1:
                case 2: {
                    pda.lsStyle = 1;
                    pda.fBoldLine = (attInfo == 1);
                    break;
                }
            }
        }
        return pda;
    }
    
    public int[] getRanges() {
        this.checkWidget();
        if (this.ranges == null) {
            return new int[0];
        }
        final int[] result = new int[this.ranges.length];
        for (int i = 0; i < result.length; ++i) {
            result[i] = this.ranges[i] + this.startOffset;
        }
        return result;
    }
    
    public TextStyle[] getStyles() {
        this.checkWidget();
        if (this.styles == null) {
            return new TextStyle[0];
        }
        final TextStyle[] result = new TextStyle[this.styles.length];
        System.arraycopy(this.styles, 0, result, 0, this.styles.length);
        return result;
    }
    
    public String getText() {
        this.checkWidget();
        return this.text;
    }
    
    public boolean getWideCaret() {
        this.checkWidget();
        final long layout = OS.GetKeyboardLayout(0);
        final short langID = (short)OS.LOWORD(layout);
        return OS.PRIMARYLANGID((int)langID) == 18;
    }
    
    boolean isInlineEnabled() {
        return OS.IsDBLocale && this.hooks(43);
    }
    
    @Override
    void releaseParent() {
        super.releaseParent();
        if (this == this.parent.getIME()) {
            this.parent.setIME((IME)null);
        }
    }
    
    @Override
    void releaseWidget() {
        super.releaseWidget();
        this.parent = null;
        this.text = null;
        this.styles = null;
        this.ranges = null;
    }
    
    public void setCompositionOffset(final int offset) {
        this.checkWidget();
        if (offset < 0) {
            return;
        }
        if (this.startOffset != -1) {
            this.startOffset = offset;
        }
    }
    
    LRESULT WM_IME_COMPOSITION(final long wParam, final long lParam) {
        if (!this.isInlineEnabled()) {
            return null;
        }
        this.ranges = null;
        this.styles = null;
        final int n = 0;
        this.commitCount = 0;
        this.caretOffset = 0;
        final long hwnd = this.parent.handle;
        final long hIMC = OS.ImmGetContext(hwnd);
        if (hIMC != 0L) {
            char[] buffer = null;
            if ((lParam & 0x800L) != 0x0L) {
                int length = OS.ImmGetCompositionString(hIMC, 2048, (char[])null, 0);
                if (length > 0) {
                    buffer = new char[length / 2];
                    OS.ImmGetCompositionString(hIMC, 2048, buffer, length);
                    if (this.startOffset == -1) {
                        final Event event = new Event();
                        event.detail = 3;
                        this.sendEvent(43, event);
                        this.startOffset = event.start;
                    }
                    Event event = new Event();
                    event.detail = 1;
                    event.start = this.startOffset;
                    event.end = this.startOffset + this.text.length();
                    final Event event2 = event;
                    final String s = (buffer != null) ? new String(buffer) : "";
                    this.text = s;
                    event2.text = s;
                    this.commitCount = this.text.length();
                    this.sendEvent(43, event);
                    final String chars = this.text;
                    this.text = "";
                    this.startOffset = -1;
                    this.commitCount = 0;
                    if (event.doit) {
                        final Display display = this.display;
                        display.lastKey = 0;
                        final Display display2 = display;
                        final Display display3 = display;
                        final Display display4 = display;
                        final boolean lastVirtual = false;
                        display4.lastDead = false;
                        display3.lastNull = false;
                        display2.lastVirtual = false;
                        length = chars.length();
                        for (int i = 0; i < length; ++i) {
                            final char c = chars.charAt(i);
                            display.lastAscii = c;
                            event = new Event();
                            event.character = c;
                            this.parent.sendEvent(1, event);
                        }
                    }
                }
                if ((lParam & 0x8L) == 0x0L) {
                    return LRESULT.ONE;
                }
            }
            buffer = null;
            if ((lParam & 0x8L) != 0x0L) {
                int length = OS.ImmGetCompositionString(hIMC, 8, (char[])null, 0);
                if (length > 0) {
                    buffer = new char[length / 2];
                    OS.ImmGetCompositionString(hIMC, 8, buffer, length);
                    if ((lParam & 0x80L) != 0x0L) {
                        this.caretOffset = OS.ImmGetCompositionString(hIMC, 128, (char[])null, 0);
                    }
                    int[] clauses = null;
                    if ((lParam & 0x20L) != 0x0L) {
                        length = OS.ImmGetCompositionString(hIMC, 32, (int[])null, 0);
                        if (length > 0) {
                            clauses = new int[length / 4];
                            OS.ImmGetCompositionString(hIMC, 32, clauses, length);
                        }
                    }
                    if ((lParam & 0x10L) != 0x0L && clauses != null) {
                        length = OS.ImmGetCompositionString(hIMC, 16, (byte[])null, 0);
                        if (length > 0) {
                            final byte[] attrs = new byte[length];
                            OS.ImmGetCompositionString(hIMC, 16, attrs, length);
                            length = clauses.length - 1;
                            this.ranges = new int[length * 2];
                            this.styles = new TextStyle[length];
                            final long layout = OS.GetKeyboardLayout(0);
                            final short langID = (short)OS.LOWORD(layout);
                            TF_DISPLAYATTRIBUTE attr = null;
                            TextStyle style = null;
                            for (int j = 0; j < length; ++j) {
                                this.ranges[j * 2] = clauses[j];
                                this.ranges[j * 2 + 1] = clauses[j + 1] - 1;
                                final TextStyle[] styles = this.styles;
                                final int n2 = j;
                                final TextStyle textStyle = new TextStyle();
                                styles[n2] = textStyle;
                                style = textStyle;
                                if (clauses[j] >= 0 && clauses[j] < attrs.length) {
                                    attr = this.getDisplayAttribute(langID, attrs[clauses[j]]);
                                    if (attr != null) {
                                        switch (attr.crText.type) {
                                            case 2: {
                                                style.foreground = Color.win32_new((Device)this.display, attr.crText.cr);
                                                break;
                                            }
                                            case 1: {
                                                final int colorRef = OS.GetSysColor(attr.crText.cr);
                                                style.foreground = Color.win32_new((Device)this.display, colorRef);
                                                break;
                                            }
                                        }
                                        switch (attr.crBk.type) {
                                            case 2: {
                                                style.background = Color.win32_new((Device)this.display, attr.crBk.cr);
                                                break;
                                            }
                                            case 1: {
                                                final int colorRef = OS.GetSysColor(attr.crBk.cr);
                                                style.background = Color.win32_new((Device)this.display, colorRef);
                                                break;
                                            }
                                        }
                                        switch (attr.crLine.type) {
                                            case 2: {
                                                style.underlineColor = Color.win32_new((Device)this.display, attr.crLine.cr);
                                                break;
                                            }
                                            case 1: {
                                                final int colorRef = OS.GetSysColor(attr.crLine.cr);
                                                style.underlineColor = Color.win32_new((Device)this.display, colorRef);
                                                break;
                                            }
                                        }
                                        style.underline = (attr.lsStyle != 0);
                                        switch (attr.lsStyle) {
                                            case 4: {
                                                style.underlineStyle = 3;
                                                break;
                                            }
                                            case 3: {
                                                style.underlineStyle = 131072;
                                                break;
                                            }
                                            case 2: {
                                                style.underlineStyle = 65536;
                                                break;
                                            }
                                            case 1: {
                                                style.underlineStyle = (attr.fBoldLine ? 196608 : 0);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                OS.ImmReleaseContext(hwnd, hIMC);
            }
            int end = this.startOffset + this.text.length();
            if (this.startOffset == -1) {
                final Event event = new Event();
                event.detail = 3;
                this.sendEvent(43, event);
                this.startOffset = event.start;
                end = event.end;
            }
            Event event = new Event();
            event.detail = 1;
            event.start = this.startOffset;
            event.end = end;
            final Event event3 = event;
            final String s2 = (buffer != null) ? new String(buffer) : "";
            this.text = s2;
            event3.text = s2;
            this.sendEvent(43, event);
            if (this.text.length() == 0) {
                this.startOffset = -1;
                this.ranges = null;
                this.styles = null;
            }
        }
        return LRESULT.ONE;
    }
    
    LRESULT WM_IME_COMPOSITION_START(final long wParam, final long lParam) {
        return this.isInlineEnabled() ? LRESULT.ONE : null;
    }
    
    LRESULT WM_IME_ENDCOMPOSITION(final long wParam, final long lParam) {
        this.startOffset = -1;
        this.caretOffset = 0;
        return this.isInlineEnabled() ? LRESULT.ONE : null;
    }
    
    LRESULT WM_KEYDOWN(final long wParam, final long lParam) {
        if (wParam == 25L) {
            final long hKL = OS.GetKeyboardLayout(0);
            final short langID = (short)OS.LOWORD(hKL);
            if (OS.PRIMARYLANGID((int)langID) == 18) {
                final Event event = new Event();
                event.detail = 3;
                this.sendEvent(43, event);
                if (event.start == event.end) {
                    event.text = null;
                    event.end = event.start + 1;
                    this.sendEvent(43, event);
                }
                if (event.text != null && event.text.length() > 0) {
                    final int length = event.text.length();
                    if (length > 1) {
                        event.end = event.start + 1;
                    }
                    final long hwnd = this.parent.handle;
                    final long hIMC = OS.ImmGetContext(hwnd);
                    final TCHAR buffer = new TCHAR(0, event.text, true);
                    final long rc = OS.ImmEscape(hKL, hIMC, 4104, buffer);
                    if (rc != 0L) {
                        this.sendEvent(43, event);
                    }
                }
            }
        }
        return null;
    }
    
    LRESULT WM_KILLFOCUS(final long wParam, final long lParam) {
        if (!this.isInlineEnabled()) {
            return null;
        }
        final long hwnd = this.parent.handle;
        final long hIMC = OS.ImmGetContext(hwnd);
        if (hIMC != 0L) {
            if (OS.ImmGetOpenStatus(hIMC)) {
                OS.ImmNotifyIME(hIMC, 21, 1, 0);
            }
            OS.ImmReleaseContext(hwnd, hIMC);
        }
        return null;
    }
    
    LRESULT WM_LBUTTONDOWN(final long wParam, final long lParam) {
        if (!this.isInlineEnabled()) {
            return null;
        }
        final long hwnd = this.parent.handle;
        final long hIMC = OS.ImmGetContext(hwnd);
        if (hIMC != 0L) {
            if (OS.ImmGetOpenStatus(hIMC) && OS.ImmGetCompositionString(hIMC, 8, (char[])null, 0) > 0) {
                final Event event = new Event();
                event.detail = 2;
                event.setLocationInPixels(OS.GET_X_LPARAM(lParam), OS.GET_Y_LPARAM(lParam));
                this.sendEvent(43, event);
                int offset = event.index;
                final int length = this.text.length();
                if (offset != -1 && this.startOffset != -1 && this.startOffset <= offset && offset < this.startOffset + length) {
                    final long imeWnd = OS.ImmGetDefaultIMEWnd(hwnd);
                    offset = event.index + event.count - this.startOffset;
                    final int trailing = (event.count > 0) ? 1 : 2;
                    final long param = OS.MAKEWPARAM(OS.MAKEWORD(1, trailing), offset);
                    OS.SendMessage(imeWnd, IME.WM_MSIME_MOUSE, param, hIMC);
                }
                else {
                    OS.ImmNotifyIME(hIMC, 21, 1, 0);
                }
            }
            OS.ImmReleaseContext(hwnd, hIMC);
        }
        return null;
    }
    
    static {
        WM_MSIME_MOUSE = OS.RegisterWindowMessage(new TCHAR(0, "MSIMEMouseOperation", true));
    }
}
