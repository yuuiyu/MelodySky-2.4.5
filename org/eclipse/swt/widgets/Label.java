//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;

public class Label extends Control
{
    String text;
    Image image;
    boolean isImageMode;
    static final int MARGIN = 4;
    static final long LabelProc;
    static final TCHAR LabelClass;
    
    public Label(final Composite parent, final int style) {
        super(parent, checkStyle(style));
        this.text = "";
    }
    
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        if (OS.WIN32_VERSION >= OS.VERSION(6, 1)) {
            switch (msg) {
                case 515: {
                    return OS.DefWindowProc(hwnd, msg, wParam, lParam);
                }
            }
        }
        return OS.CallWindowProc(Label.LabelProc, hwnd, msg, wParam, lParam);
    }
    
    static int checkStyle(int style) {
        style |= 0x80000;
        if ((style & 0x2) != 0x0) {
            style = Widget.checkBits(style, 512, 256, 0, 0, 0, 0);
            return Widget.checkBits(style, 8, 4, 32, 0, 0, 0);
        }
        return Widget.checkBits(style, 16384, 16777216, 131072, 0, 0, 0);
    }
    
    Point computeSizeInPixels(final int wHint, final int hHint, final boolean changed) {
        this.checkWidget();
        int width = 0;
        int height = 0;
        final int border = this.getBorderWidthInPixels();
        if ((this.style & 0x2) != 0x0) {
            final int lineWidth = OS.GetSystemMetrics(5);
            if ((this.style & 0x100) != 0x0) {
                width = 64;
                height = lineWidth * 2;
            }
            else {
                width = lineWidth * 2;
                height = 64;
            }
            if (wHint != -1) {
                width = wHint;
            }
            if (hHint != -1) {
                height = hHint;
            }
            width += border * 2;
            height += border * 2;
            return new Point(width, height);
        }
        if (this.isImageMode) {
            final Rectangle rect = this.image.getBoundsInPixels();
            width += rect.width;
            height += rect.height;
        }
        else {
            final long hDC = OS.GetDC(this.handle);
            final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
            final long oldFont = OS.SelectObject(hDC, newFont);
            final int length = OS.GetWindowTextLength(this.handle);
            if (length == 0) {
                final TEXTMETRIC tm = new TEXTMETRIC();
                OS.GetTextMetrics(hDC, tm);
                height = Math.max(height, tm.tmHeight);
            }
            else {
                final RECT rect2 = new RECT();
                int flags = 9280;
                if ((this.style & 0x40) != 0x0 && wHint != -1) {
                    flags |= 0x10;
                    rect2.right = Math.max(0, wHint - width);
                }
                final char[] buffer = new char[length + 1];
                OS.GetWindowText(this.handle, buffer, length + 1);
                OS.DrawText(hDC, buffer, length, rect2, flags);
                width += rect2.right - rect2.left;
                height = Math.max(height, rect2.bottom - rect2.top);
            }
            if (newFont != 0L) {
                OS.SelectObject(hDC, oldFont);
            }
            OS.ReleaseDC(this.handle, hDC);
        }
        if (wHint != -1) {
            width = wHint;
        }
        if (hHint != -1) {
            height = hHint;
        }
        width += border * 2;
        height += border * 2;
        return new Point(width, height);
    }
    
    void createHandle() {
        super.createHandle();
        this.state |= 0x100;
    }
    
    public int getAlignment() {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            return 0;
        }
        if ((this.style & 0x4000) != 0x0) {
            return 16384;
        }
        if ((this.style & 0x1000000) != 0x0) {
            return 16777216;
        }
        if ((this.style & 0x20000) != 0x0) {
            return 131072;
        }
        return 16384;
    }
    
    public Image getImage() {
        this.checkWidget();
        return this.image;
    }
    
    String getNameText() {
        return this.getText();
    }
    
    public String getText() {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            return "";
        }
        return this.text;
    }
    
    boolean isUseWsBorder() {
        return super.isUseWsBorder() || (this.display != null && this.display.useWsBorderLabel);
    }
    
    boolean mnemonicHit(final char key) {
        for (Control control = this; control.parent != null; control = (Control)control.parent) {
            Control[] children;
            int index;
            for (children = control.parent._getChildren(), index = 0; index < children.length && children[index] != control; ++index) {}
            if (++index < children.length && children[index].setFocus()) {
                return true;
            }
        }
        return false;
    }
    
    boolean mnemonicMatch(final char key) {
        final char mnemonic = this.findMnemonic(this.getText());
        return mnemonic != '\0' && Character.toUpperCase(key) == Character.toUpperCase(mnemonic);
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.text = null;
        this.image = null;
    }
    
    int resolveTextDirection() {
        return ((this.style & 0x2) != 0x0) ? 0 : BidiUtil.resolveTextDirection(this.text);
    }
    
    public void setAlignment(final int alignment) {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        if ((alignment & 0x1024000) == 0x0) {
            return;
        }
        this.style &= 0xFEFDBFFF;
        this.style |= (alignment & 0x1024000);
        this.updateStyleBits(this.getEnabled());
        OS.InvalidateRect(this.handle, (RECT)null, true);
    }
    
    public void setEnabled(final boolean enabled) {
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        this.updateStyleBits(enabled);
        super.setEnabled(enabled);
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        this.isImageMode = ((this.image = image) != null);
        this.updateStyleBits(this.getEnabled());
        OS.InvalidateRect(this.handle, (RECT)null, true);
    }
    
    public void setText(String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        this.isImageMode = false;
        this.updateStyleBits(this.getEnabled());
        if (string.equals(this.text)) {
            return;
        }
        this.text = string;
        string = Display.withCrLf(string);
        final TCHAR buffer = new TCHAR(this.getCodePage(), string, true);
        OS.SetWindowText(this.handle, buffer);
        if ((this.state & 0x400000) != 0x0) {
            this.updateTextDirection(100663296);
        }
    }
    
    void updateStyleBits(final boolean isEnabled) {
        boolean useOwnerDraw = this.isImageMode;
        if (!useOwnerDraw && this.display.disabledLabelForegroundPixel != -1 && !isEnabled) {
            useOwnerDraw = true;
        }
        final int oldBits;
        int newBits = oldBits = OS.GetWindowLong(this.handle, -16);
        newBits &= 0xFFFFFFF2;
        newBits &= 0xFFFFFFF0;
        if (useOwnerDraw) {
            newBits |= 0xD;
        }
        else {
            if ((this.style & 0x4000) != 0x0) {
                if ((this.style & 0x40) != 0x0) {
                    newBits |= 0x0;
                }
                else {
                    newBits |= 0xC;
                }
            }
            if ((this.style & 0x1000000) != 0x0) {
                newBits |= 0x1;
            }
            if ((this.style & 0x20000) != 0x0) {
                newBits |= 0x2;
            }
        }
        if (oldBits != newBits) {
            OS.SetWindowLong(this.handle, -16, newBits);
        }
    }
    
    int widgetExtStyle() {
        final int bits = super.widgetExtStyle() & 0xFFFFFDFF;
        if ((this.style & 0x800) != 0x0) {
            return bits | 0x20000;
        }
        return bits;
    }
    
    int widgetStyle() {
        int bits = super.widgetStyle() | 0x100;
        if ((this.style & 0x2) != 0x0) {
            return bits | 0xD;
        }
        if ((this.style & 0x40) != 0x0) {
            bits |= 0x2000;
        }
        if ((this.style & 0x1000000) != 0x0) {
            return bits | 0x1;
        }
        if ((this.style & 0x20000) != 0x0) {
            return bits | 0x2;
        }
        if ((this.style & 0x40) != 0x0) {
            return bits | 0x0;
        }
        return bits | 0xC;
    }
    
    TCHAR windowClass() {
        return Label.LabelClass;
    }
    
    long windowProc() {
        return Label.LabelProc;
    }
    
    LRESULT WM_ERASEBKGND(final long wParam, final long lParam) {
        final LRESULT result = super.WM_ERASEBKGND(wParam, lParam);
        if (result != null) {
            return result;
        }
        final int bits = OS.GetWindowLong(this.handle, -16);
        if ((bits & 0xD) == 0xD) {
            return LRESULT.ONE;
        }
        return result;
    }
    
    LRESULT WM_SIZE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SIZE(wParam, lParam);
        if (this.isDisposed()) {
            return result;
        }
        if ((this.style & 0x2) != 0x0) {
            OS.InvalidateRect(this.handle, (RECT)null, true);
            return result;
        }
        final int bits = OS.GetWindowLong(this.handle, -16);
        if ((bits & 0xD) == 0xD) {
            OS.InvalidateRect(this.handle, (RECT)null, true);
            return result;
        }
        if ((bits & 0xC) != 0xC) {
            OS.InvalidateRect(this.handle, (RECT)null, true);
            return result;
        }
        return result;
    }
    
    LRESULT WM_UPDATEUISTATE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_UPDATEUISTATE(wParam, lParam);
        if (result != null) {
            return result;
        }
        boolean redraw = this.findImageControl() != null;
        if (!redraw && (this.state & 0x100) != 0x0 && OS.IsAppThemed()) {
            redraw = (this.findThemeControl() != null);
        }
        if (redraw) {
            OS.InvalidateRect(this.handle, (RECT)null, false);
            final long code = OS.DefWindowProc(this.handle, 296, wParam, lParam);
            return new LRESULT(code);
        }
        return result;
    }
    
    void wmDrawChildSeparator(final DRAWITEMSTRUCT struct) {
        if ((this.style & 0x20) != 0x0) {
            return;
        }
        final RECT rect = new RECT();
        final int lineWidth = OS.GetSystemMetrics(5);
        final int flags = ((this.style & 0x4) != 0x0) ? 10 : 6;
        if ((this.style & 0x100) != 0x0) {
            final int bottom = struct.top + Math.max(lineWidth * 2, (struct.bottom - struct.top) / 2);
            OS.SetRect(rect, struct.left, struct.top, struct.right, bottom);
            OS.DrawEdge(struct.hDC, rect, flags, 8);
        }
        else {
            final int right = struct.left + Math.max(lineWidth * 2, (struct.right - struct.left) / 2);
            OS.SetRect(rect, struct.left, struct.top, right, struct.bottom);
            OS.DrawEdge(struct.hDC, rect, flags, 4);
        }
    }
    
    void wmDrawChildImage(final DRAWITEMSTRUCT struct) {
        final int width = struct.right - struct.left;
        final int height = struct.bottom - struct.top;
        if (width == 0 || height == 0) {
            return;
        }
        final Rectangle imageRect = this.image.getBoundsInPixels();
        int x = 0;
        if ((this.style & 0x1000000) != 0x0) {
            x = Math.max(0, (width - imageRect.width) / 2);
        }
        else if ((this.style & 0x20000) != 0x0) {
            x = width - imageRect.width;
        }
        final GCData data = new GCData();
        data.device = (Device)this.display;
        final GC gc = GC.win32_new(struct.hDC, data);
        final Image image = this.getEnabled() ? this.image : new Image((Device)this.display, this.image, 1);
        gc.drawImage(image, DPIUtil.autoScaleDown(x), DPIUtil.autoScaleDown(Math.max(0, (height - imageRect.height) / 2)));
        if (image != this.image) {
            image.dispose();
        }
        gc.dispose();
    }
    
    void wmDrawChildText(final DRAWITEMSTRUCT struct) {
        final int width = struct.right - struct.left;
        final int height = struct.bottom - struct.top;
        if (width == 0 || height == 0) {
            return;
        }
        final RECT rect = new RECT();
        rect.left = struct.left;
        rect.top = struct.top;
        rect.right = struct.right;
        rect.bottom = struct.bottom;
        int flags = 8256;
        if ((this.style & 0x4000) != 0x0) {
            flags |= 0x0;
        }
        if ((this.style & 0x1000000) != 0x0) {
            flags |= 0x1;
        }
        if ((this.style & 0x20000) != 0x0) {
            flags |= 0x2;
        }
        if ((this.style & 0x40) != 0x0) {
            flags |= 0x10;
        }
        final long uiState = OS.SendMessage(this.handle, 297, 0L, 0L);
        if ((uiState & 0x2L) != 0x0L) {
            flags |= 0x100000;
        }
        if (!this.getEnabled()) {
            int foregroundPixel = OS.GetSysColor(17);
            if (this.display.disabledLabelForegroundPixel != -1) {
                foregroundPixel = this.display.disabledLabelForegroundPixel;
            }
            OS.SetTextColor(struct.hDC, foregroundPixel);
        }
        final char[] buffer = this.text.toCharArray();
        OS.DrawText(struct.hDC, buffer, buffer.length, rect, flags);
    }
    
    LRESULT wmDrawChild(final long wParam, final long lParam) {
        final DRAWITEMSTRUCT struct = new DRAWITEMSTRUCT();
        OS.MoveMemory(struct, lParam, DRAWITEMSTRUCT.sizeof);
        this.drawBackground(struct.hDC);
        if ((this.style & 0x2) != 0x0) {
            this.wmDrawChildSeparator(struct);
        }
        else if (this.isImageMode) {
            this.wmDrawChildImage(struct);
        }
        else {
            this.wmDrawChildText(struct);
        }
        return null;
    }
    
    static {
        LabelClass = new TCHAR(0, "STATIC", true);
        final WNDCLASS lpWndClass = new WNDCLASS();
        OS.GetClassInfo(0L, Label.LabelClass, lpWndClass);
        LabelProc = lpWndClass.lpfnWndProc;
    }
}
