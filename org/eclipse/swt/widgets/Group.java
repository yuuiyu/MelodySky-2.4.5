//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class Group extends Composite
{
    String text;
    static final int CLIENT_INSET = 3;
    static final long GroupProc;
    static final TCHAR GroupClass;
    
    public Group(final Composite parent, final int style) {
        super(parent, checkStyle(style));
        this.text = "";
    }
    
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        switch (msg) {
            case 513:
            case 515: {
                return OS.DefWindowProc(hwnd, msg, wParam, lParam);
            }
            default: {
                return OS.CallWindowProc(Group.GroupProc, hwnd, msg, wParam, lParam);
            }
        }
    }
    
    static int checkStyle(int style) {
        style |= 0x80000;
        return style & 0xFFFFFCFF;
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    Point computeSizeInPixels(final int wHint, final int hHint, final boolean changed) {
        this.checkWidget();
        final Point size = super.computeSizeInPixels(wHint, hHint, changed);
        final int length = this.text.length();
        if (length != 0) {
            final String string = this.fixText(false);
            final char[] buffer = ((string == null) ? this.text : string).toCharArray();
            long oldFont = 0L;
            final long hDC = OS.GetDC(this.handle);
            final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
            if (newFont != 0L) {
                oldFont = OS.SelectObject(hDC, newFont);
            }
            final RECT rect = new RECT();
            final int flags = 1056;
            OS.DrawText(hDC, buffer, buffer.length, rect, 1056);
            if (newFont != 0L) {
                OS.SelectObject(hDC, oldFont);
            }
            OS.ReleaseDC(this.handle, hDC);
            final int offsetY = OS.IsAppThemed() ? 0 : 1;
            size.x = Math.max(size.x, rect.right - rect.left + 18 + offsetY);
        }
        return size;
    }
    
    Rectangle computeTrimInPixels(final int x, final int y, final int width, final int height) {
        this.checkWidget();
        final Rectangle trim = super.computeTrimInPixels(x, y, width, height);
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
        final int offsetY = OS.IsAppThemed() ? 0 : 1;
        final Rectangle rectangle5;
        final Rectangle rectangle = rectangle5 = trim;
        rectangle5.x -= 3;
        final Rectangle rectangle6;
        final Rectangle rectangle2 = rectangle6 = trim;
        rectangle6.y -= tm.tmHeight + offsetY;
        final Rectangle rectangle7;
        final Rectangle rectangle3 = rectangle7 = trim;
        rectangle7.width += 6;
        final Rectangle rectangle8;
        final Rectangle rectangle4 = rectangle8 = trim;
        rectangle8.height += tm.tmHeight + 3 + offsetY;
        return trim;
    }
    
    void createHandle() {
        final Composite parent3;
        final Composite parent = parent3 = this.parent;
        parent3.state |= 0x100000;
        super.createHandle();
        final Composite parent4;
        final Composite parent2 = parent4 = this.parent;
        parent4.state &= 0xFFEFFFFF;
        this.state |= 0x200;
        this.state &= 0xFFFFFFFD;
    }
    
    void enableWidget(final boolean enabled) {
        super.enableWidget(enabled);
        final String string = this.fixText(enabled);
        if (string != null) {
            final TCHAR buffer = new TCHAR(this.getCodePage(), string, true);
            OS.SetWindowText(this.handle, buffer);
        }
        if (enabled && this.hasCustomForeground()) {
            OS.InvalidateRect(this.handle, (RECT)null, true);
        }
    }
    
    String fixText(final boolean enabled) {
        if (this.text.length() == 0) {
            return null;
        }
        if ((this.style & 0x4000000) != 0x0) {
            String string = null;
            if (!enabled && !OS.IsAppThemed()) {
                string = " " + this.text;
            }
            return ((this.style & Integer.MIN_VALUE) == 0x0) ? string : ((string != null) ? ("\u202a" + string) : ("\u202a" + this.text));
        }
        if ((this.style & Integer.MIN_VALUE) != 0x0) {
            return "\u202b" + this.text;
        }
        return null;
    }
    
    Rectangle getClientAreaInPixels() {
        this.checkWidget();
        this.forceResize();
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
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
        final int offsetY = OS.IsAppThemed() ? 0 : 1;
        final int x = 3;
        final int y = tm.tmHeight + offsetY;
        final int width = Math.max(0, rect.right - 6);
        final int height = Math.max(0, rect.bottom - y - 3);
        return new Rectangle(3, y, width, height);
    }
    
    String getNameText() {
        return this.getText();
    }
    
    public String getText() {
        this.checkWidget();
        return this.text;
    }
    
    boolean mnemonicHit(final char key) {
        return this.setFocus();
    }
    
    boolean mnemonicMatch(final char key) {
        final char mnemonic = this.findMnemonic(this.getText());
        return mnemonic != '\0' && Character.toUpperCase(key) == Character.toUpperCase(mnemonic);
    }
    
    void printWidget(final long hwnd, final long hdc, final GC gc) {
        boolean success = false;
        if (OS.GetDeviceCaps(gc.handle, 2) != 2) {
            final int bits = OS.GetWindowLong(hwnd, -16);
            if ((bits & 0x10000000) == 0x0) {
                OS.ShowWindow(hwnd, 5);
            }
            success = OS.PrintWindow(hwnd, hdc, 0);
            if ((bits & 0x10000000) == 0x0) {
                OS.ShowWindow(hwnd, 0);
            }
        }
        if (!success) {
            final int flags = 14;
            OS.SendMessage(hwnd, 791, hdc, 14L);
            final int nSavedDC = OS.SaveDC(hdc);
            final Control[] children = this._getChildren();
            final Rectangle rect = this.getBoundsInPixels();
            OS.IntersectClipRect(hdc, 0, 0, rect.width, rect.height);
            for (int i = children.length - 1; i >= 0; --i) {
                final Point location = children[i].getLocationInPixels();
                final int graphicsMode = OS.GetGraphicsMode(hdc);
                if (graphicsMode == 2) {
                    final float[] lpXform = { 1.0f, 0.0f, 0.0f, 1.0f, (float)location.x, (float)location.y };
                    OS.ModifyWorldTransform(hdc, lpXform, 2);
                }
                else {
                    OS.SetWindowOrgEx(hdc, -location.x, -location.y, (POINT)null);
                }
                final long topHandle = children[i].topHandle();
                final int bits2 = OS.GetWindowLong(topHandle, -16);
                if ((bits2 & 0x10000000) != 0x0) {
                    children[i].printWidget(topHandle, hdc, gc);
                }
                if (graphicsMode == 2) {
                    final float[] lpXform2 = { 1.0f, 0.0f, 0.0f, 1.0f, (float)(-location.x), (float)(-location.y) };
                    OS.ModifyWorldTransform(hdc, lpXform2, 2);
                }
            }
            OS.RestoreDC(hdc, nSavedDC);
        }
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.text = null;
    }
    
    int resolveTextDirection() {
        return BidiUtil.resolveTextDirection(this.text);
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        final Rectangle oldRect = this.getClientAreaInPixels();
        super.setFont(font);
        final Rectangle newRect = this.getClientAreaInPixels();
        if (!oldRect.equals((Object)newRect)) {
            this.sendResize();
        }
    }
    
    public void setText(String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        this.text = string;
        if ((this.state & 0x400000) == 0x0 || !this.updateTextDirection(100663296)) {
            string = this.fixText(OS.IsWindowEnabled(this.handle));
            final TCHAR buffer = new TCHAR(this.getCodePage(), (string == null) ? this.text : string, true);
            OS.SetWindowText(this.handle, buffer);
        }
    }
    
    boolean updateTextDirection(final int textDirection) {
        if (super.updateTextDirection(textDirection)) {
            final String string = this.fixText(OS.IsWindowEnabled(this.handle));
            final TCHAR buffer = new TCHAR(this.getCodePage(), (string == null) ? this.text : string, true);
            OS.SetWindowText(this.handle, buffer);
            return true;
        }
        return false;
    }
    
    int widgetStyle() {
        return super.widgetStyle() | 0x7 | 0x2000000 | 0x4000000;
    }
    
    TCHAR windowClass() {
        return Group.GroupClass;
    }
    
    long windowProc() {
        return Group.GroupProc;
    }
    
    LRESULT WM_ERASEBKGND(final long wParam, final long lParam) {
        final LRESULT result = super.WM_ERASEBKGND(wParam, lParam);
        if (result != null) {
            return result;
        }
        this.drawBackground(wParam);
        return LRESULT.ONE;
    }
    
    LRESULT WM_NCHITTEST(final long wParam, final long lParam) {
        final LRESULT result = super.WM_NCHITTEST(wParam, lParam);
        if (result != null) {
            return result;
        }
        long code = this.callWindowProc(this.handle, 132, wParam, lParam);
        if (code == -1L) {
            code = 1L;
        }
        return new LRESULT(code);
    }
    
    LRESULT WM_MOUSEMOVE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_MOUSEMOVE(wParam, lParam);
        if (result != null) {
            return result;
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_PAINT(final long wParam, final long lParam) {
        final LRESULT result = super.WM_PAINT(wParam, lParam);
        if (this.hasCustomForeground() && this.text.length() != 0) {
            final String string = this.fixText(false);
            final char[] buffer = ((string == null) ? this.text : string).toCharArray();
            final long hDC = OS.GetDC(this.handle);
            final RECT rect = new RECT();
            OS.GetClientRect(this.handle, rect);
            final RECT rect4;
            final RECT rect2 = rect4 = rect;
            rect4.left += 9;
            long oldFont = 0L;
            final long newFont = OS.SendMessage(this.handle, 49, 0L, 0L);
            if (newFont != 0L) {
                oldFont = OS.SelectObject(hDC, newFont);
            }
            OS.DrawText(hDC, buffer, buffer.length, rect, 1056);
            final RECT rect5;
            final RECT rect3 = rect5 = rect;
            rect5.right += 3;
            this.drawBackground(hDC, rect);
            OS.SetBkMode(hDC, 1);
            OS.SetTextColor(hDC, this.getForegroundPixel());
            OS.DrawText(hDC, buffer, buffer.length, rect, 32);
            if (newFont != 0L) {
                OS.SelectObject(hDC, oldFont);
            }
            OS.ReleaseDC(this.handle, hDC);
            OS.ValidateRect(this.handle, rect);
        }
        return result;
    }
    
    LRESULT WM_PRINTCLIENT(final long wParam, final long lParam) {
        final LRESULT result = super.WM_PRINTCLIENT(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (OS.IsAppThemed()) {
            final int nSavedDC = OS.SaveDC(wParam);
            final long code = this.callWindowProc(this.handle, 792, wParam, lParam);
            OS.RestoreDC(wParam, nSavedDC);
            return new LRESULT(code);
        }
        return result;
    }
    
    LRESULT WM_UPDATEUISTATE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_UPDATEUISTATE(wParam, lParam);
        if (result != null) {
            return result;
        }
        boolean redraw = this.findImageControl() != null;
        if (!redraw) {
            if ((this.state & 0x100) != 0x0 && OS.IsAppThemed()) {
                redraw = (this.findThemeControl() != null);
            }
            if (!redraw) {
                redraw = (this.findBackgroundControl() != null);
            }
        }
        if (redraw) {
            OS.InvalidateRect(this.handle, (RECT)null, false);
            final long code = OS.DefWindowProc(this.handle, 296, wParam, lParam);
            return new LRESULT(code);
        }
        return result;
    }
    
    LRESULT WM_WINDOWPOSCHANGING(final long wParam, final long lParam) {
        final LRESULT result = super.WM_WINDOWPOSCHANGING(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (!OS.IsWindowVisible(this.handle)) {
            return result;
        }
        final WINDOWPOS lpwp = new WINDOWPOS();
        OS.MoveMemory(lpwp, lParam, WINDOWPOS.sizeof);
        if ((lpwp.flags & 0x9) != 0x0) {
            return result;
        }
        final RECT rect = new RECT();
        OS.SetRect(rect, 0, 0, lpwp.cx, lpwp.cy);
        OS.SendMessage(this.handle, 131, 0L, rect);
        final int newWidth = rect.right - rect.left;
        final int newHeight = rect.bottom - rect.top;
        OS.GetClientRect(this.handle, rect);
        int oldWidth = rect.right - rect.left;
        final int oldHeight = rect.bottom - rect.top;
        if (newWidth == oldWidth && newHeight == oldHeight) {
            return result;
        }
        if (newWidth != oldWidth) {
            int left;
            if (newWidth < (left = oldWidth)) {
                left = newWidth;
            }
            OS.SetRect(rect, left - 3, 0, newWidth, newHeight);
            OS.InvalidateRect(this.handle, rect, true);
        }
        if (newHeight != oldHeight) {
            int bottom;
            if (newHeight < (bottom = oldHeight)) {
                bottom = newHeight;
            }
            if (newWidth < oldWidth) {
                oldWidth -= 3;
            }
            OS.SetRect(rect, 0, bottom - 3, oldWidth, newHeight);
            OS.InvalidateRect(this.handle, rect, true);
        }
        return result;
    }
    
    static {
        GroupClass = new TCHAR(0, "SWT_GROUP", true);
        final WNDCLASS lpWndClass = new WNDCLASS();
        final TCHAR WC_BUTTON = new TCHAR(0, "BUTTON", true);
        OS.GetClassInfo(0L, WC_BUTTON, lpWndClass);
        GroupProc = lpWndClass.lpfnWndProc;
        final long hInstance = OS.GetModuleHandle((char[])null);
        if (!OS.GetClassInfo(hInstance, Group.GroupClass, lpWndClass)) {
            lpWndClass.hInstance = hInstance;
            final WNDCLASS wndclass2;
            final WNDCLASS wndclass = wndclass2 = lpWndClass;
            wndclass2.style &= 0xFFFFFFFC;
            OS.RegisterClass(Group.GroupClass, lpWndClass);
        }
    }
}
