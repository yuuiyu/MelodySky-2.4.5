//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class Canvas extends Composite
{
    Caret caret;
    IME ime;
    
    Canvas() {
    }
    
    public Canvas(final Composite parent, final int style) {
        super(parent, style);
    }
    
    public void drawBackground(final GC gc, int x, int y, int width, int height) {
        x = DPIUtil.autoScaleUp(x);
        y = DPIUtil.autoScaleUp(y);
        width = DPIUtil.autoScaleUp(width);
        height = DPIUtil.autoScaleUp(height);
        this.drawBackgroundInPixels(gc, x, y, width, height, 0, 0);
    }
    
    public Caret getCaret() {
        this.checkWidget();
        return this.caret;
    }
    
    public IME getIME() {
        this.checkWidget();
        return this.ime;
    }
    
    @Override
    boolean isUseWsBorder() {
        return super.isUseWsBorder() || (this.display != null && this.display.useWsBorderCanvas);
    }
    
    @Override
    void releaseChildren(final boolean destroy) {
        if (this.caret != null) {
            this.caret.release(false);
            this.caret = null;
        }
        if (this.ime != null) {
            this.ime.release(false);
            this.ime = null;
        }
        super.releaseChildren(destroy);
    }
    
    @Override
    void reskinChildren(final int flags) {
        if (this.caret != null) {
            this.caret.reskin(flags);
        }
        if (this.ime != null) {
            this.ime.reskin(flags);
        }
        super.reskinChildren(flags);
    }
    
    public void scroll(int destX, int destY, int x, int y, int width, int height, final boolean all) {
        this.checkWidget();
        destX = DPIUtil.autoScaleUp(destX);
        destY = DPIUtil.autoScaleUp(destY);
        x = DPIUtil.autoScaleUp(x);
        y = DPIUtil.autoScaleUp(y);
        width = DPIUtil.autoScaleUp(width);
        height = DPIUtil.autoScaleUp(height);
        this.scrollInPixels(destX, destY, x, y, width, height, all);
    }
    
    void scrollInPixels(final int destX, final int destY, final int x, final int y, final int width, final int height, final boolean all) {
        this.forceResize();
        final boolean isFocus = this.caret != null && this.caret.isFocusCaret();
        if (isFocus) {
            this.caret.killFocus();
        }
        final RECT sourceRect = new RECT();
        OS.SetRect(sourceRect, x, y, x + width, y + height);
        final RECT clientRect = new RECT();
        OS.GetClientRect(this.handle, clientRect);
        if (OS.IntersectRect(clientRect, sourceRect, clientRect)) {
            final int flags = 384;
            OS.RedrawWindow(this.handle, (RECT)null, 0L, 384);
        }
        final int deltaX = destX - x;
        final int deltaY = destY - y;
        if (this.findImageControl() != null) {
            int flags2 = 1029;
            if (all) {
                flags2 |= 0x80;
            }
            OS.RedrawWindow(this.handle, sourceRect, 0L, flags2);
            OS.OffsetRect(sourceRect, deltaX, deltaY);
            OS.RedrawWindow(this.handle, sourceRect, 0L, flags2);
        }
        else {
            final int flags2 = 6;
            OS.ScrollWindowEx(this.handle, deltaX, deltaY, sourceRect, (RECT)null, 0L, (RECT)null, 6);
        }
        if (all) {
            for (final Control child : this._getChildren()) {
                final Rectangle rect = child.getBoundsInPixels();
                if (Math.min(x + width, rect.x + rect.width) >= Math.max(x, rect.x) && Math.min(y + height, rect.y + rect.height) >= Math.max(y, rect.y)) {
                    child.setLocationInPixels(rect.x + deltaX, rect.y + deltaY);
                }
            }
        }
        if (isFocus) {
            this.caret.setFocus();
        }
    }
    
    public void setCaret(final Caret caret) {
        this.checkWidget();
        final Caret newCaret = caret;
        final Caret oldCaret = this.caret;
        this.caret = newCaret;
        if (this.hasFocus()) {
            if (oldCaret != null) {
                oldCaret.killFocus();
            }
            if (newCaret != null) {
                if (newCaret.isDisposed()) {
                    this.error(5);
                }
                newCaret.setFocus();
            }
        }
    }
    
    @Override
    public void setFont(final Font font) {
        this.checkWidget();
        if (this.caret != null) {
            this.caret.setFont(font);
        }
        super.setFont(font);
    }
    
    public void setIME(final IME ime) {
        this.checkWidget();
        if (ime != null && ime.isDisposed()) {
            this.error(5);
        }
        this.ime = ime;
    }
    
    @Override
    TCHAR windowClass() {
        if (this.display.useOwnDC) {
            return this.display.windowOwnDCClass;
        }
        return super.windowClass();
    }
    
    @Override
    long windowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (msg == Display.SWT_RESTORECARET && (this.state & 0x2) != 0x0 && this.caret != null) {
            this.caret.killFocus();
            this.caret.setFocus();
            return 1L;
        }
        return super.windowProc(hwnd, msg, wParam, lParam);
    }
    
    @Override
    LRESULT WM_CHAR(final long wParam, final long lParam) {
        final LRESULT result = super.WM_CHAR(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (this.caret != null) {
            switch ((int)wParam) {
                case 8:
                case 27:
                case 127: {
                    break;
                }
                default: {
                    if (OS.GetKeyState(17) < 0) {
                        break;
                    }
                    final int[] value = { 0 };
                    if (OS.SystemParametersInfo(4128, 0, value, 0) && value[0] != 0) {
                        OS.SetCursor(0L);
                        break;
                    }
                    break;
                }
            }
        }
        return result;
    }
    
    @Override
    LRESULT WM_IME_COMPOSITION(final long wParam, final long lParam) {
        if (this.ime != null) {
            final LRESULT result = this.ime.WM_IME_COMPOSITION(wParam, lParam);
            if (result != null) {
                return result;
            }
        }
        return super.WM_IME_COMPOSITION(wParam, lParam);
    }
    
    @Override
    LRESULT WM_IME_COMPOSITION_START(final long wParam, final long lParam) {
        if (this.ime != null) {
            final LRESULT result = this.ime.WM_IME_COMPOSITION_START(wParam, lParam);
            if (result != null) {
                return result;
            }
        }
        return super.WM_IME_COMPOSITION_START(wParam, lParam);
    }
    
    @Override
    LRESULT WM_IME_ENDCOMPOSITION(final long wParam, final long lParam) {
        if (this.ime != null) {
            final LRESULT result = this.ime.WM_IME_ENDCOMPOSITION(wParam, lParam);
            if (result != null) {
                return result;
            }
        }
        return super.WM_IME_ENDCOMPOSITION(wParam, lParam);
    }
    
    @Override
    LRESULT WM_INPUTLANGCHANGE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_INPUTLANGCHANGE(wParam, lParam);
        if (this.caret != null && this.caret.isFocusCaret()) {
            this.caret.setIMEFont();
            this.caret.resizeIME();
        }
        return result;
    }
    
    @Override
    LRESULT WM_KEYDOWN(final long wParam, final long lParam) {
        final LRESULT result = super.WM_KEYDOWN(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (this.ime != null) {
            this.ime.WM_KEYDOWN(wParam, lParam);
        }
        return result;
    }
    
    @Override
    LRESULT WM_KILLFOCUS(final long wParam, final long lParam) {
        if (this.ime != null) {
            final LRESULT result = this.ime.WM_KILLFOCUS(wParam, lParam);
            if (result != null) {
                return result;
            }
        }
        final Caret caret = this.caret;
        final LRESULT result2 = super.WM_KILLFOCUS(wParam, lParam);
        if (caret != null) {
            caret.killFocus();
        }
        return result2;
    }
    
    @Override
    LRESULT WM_LBUTTONDOWN(final long wParam, final long lParam) {
        if (this.ime != null) {
            final LRESULT result = this.ime.WM_LBUTTONDOWN(wParam, lParam);
            if (result != null) {
                return result;
            }
        }
        return super.WM_LBUTTONDOWN(wParam, lParam);
    }
    
    @Override
    LRESULT WM_SETFOCUS(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SETFOCUS(wParam, lParam);
        if (this.caret != null && this.caret.isFocusCaret()) {
            this.caret.setFocus();
        }
        return result;
    }
    
    @Override
    LRESULT WM_SIZE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SIZE(wParam, lParam);
        if (this.caret != null && this.caret.isFocusCaret()) {
            this.caret.resizeIME();
        }
        return result;
    }
    
    @Override
    LRESULT WM_WINDOWPOSCHANGED(final long wParam, final long lParam) {
        final LRESULT result = super.WM_WINDOWPOSCHANGED(wParam, lParam);
        final boolean isFocus = (this.style & 0x4000000) != 0x0 && this.caret != null && this.caret.isFocusCaret();
        if (isFocus) {
            this.caret.setFocus();
        }
        return result;
    }
    
    @Override
    LRESULT WM_WINDOWPOSCHANGING(final long wParam, final long lParam) {
        final LRESULT result = super.WM_WINDOWPOSCHANGING(wParam, lParam);
        if (result != null) {
            return result;
        }
        final boolean isFocus = (this.style & 0x4000000) != 0x0 && this.caret != null && this.caret.isFocusCaret();
        if (isFocus) {
            this.caret.killFocus();
        }
        return result;
    }
}
