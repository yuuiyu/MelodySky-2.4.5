//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class Caret extends Widget
{
    private static Caret currentCaret;
    Canvas parent;
    int x;
    int y;
    int width;
    int height;
    boolean moved;
    boolean resized;
    boolean isVisible;
    Image image;
    Font font;
    LOGFONT oldFont;
    
    public Caret(final Canvas parent, final int style) {
        super((Widget)parent, style);
        this.parent = parent;
        this.createWidget();
    }
    
    void createWidget() {
        this.isVisible = true;
        if (this.parent.getCaret() == null) {
            this.parent.setCaret(this);
        }
    }
    
    long defaultFont() {
        final long hwnd = this.parent.handle;
        final long hwndIME = OS.ImmGetDefaultIMEWnd(hwnd);
        long hFont = 0L;
        if (hwndIME != 0L) {
            hFont = OS.SendMessage(hwndIME, 49, 0L, 0L);
        }
        if (hFont == 0L) {
            hFont = OS.SendMessage(hwnd, 49, 0L, 0L);
        }
        if (hFont == 0L) {
            return this.parent.defaultFont();
        }
        return hFont;
    }
    
    public Rectangle getBounds() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getBoundsInPixels());
    }
    
    Rectangle getBoundsInPixels() {
        if (this.image != null) {
            final Rectangle rect = this.image.getBoundsInPixels();
            return new Rectangle(this.x, this.y, rect.width, rect.height);
        }
        if (this.width == 0) {
            final int[] buffer = { 0 };
            if (OS.SystemParametersInfo(8198, 0, buffer, 0)) {
                return new Rectangle(this.x, this.y, buffer[0], this.height);
            }
        }
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
    
    public Font getFont() {
        this.checkWidget();
        if (this.font == null) {
            final long hFont = this.defaultFont();
            return Font.win32_new((Device)this.display, hFont);
        }
        return this.font;
    }
    
    public Image getImage() {
        this.checkWidget();
        return this.image;
    }
    
    public Point getLocation() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getLocationInPixels());
    }
    
    Point getLocationInPixels() {
        return new Point(this.x, this.y);
    }
    
    public Canvas getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public Point getSize() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getSizeInPixels());
    }
    
    Point getSizeInPixels() {
        if (this.image != null) {
            final Rectangle rect = this.image.getBoundsInPixels();
            return new Point(rect.width, rect.height);
        }
        if (this.width == 0) {
            final int[] buffer = { 0 };
            if (OS.SystemParametersInfo(8198, 0, buffer, 0)) {
                return new Point(buffer[0], this.height);
            }
        }
        return new Point(this.width, this.height);
    }
    
    public boolean getVisible() {
        this.checkWidget();
        return this.isVisible;
    }
    
    boolean hasFocus() {
        return this.parent.handle == OS.GetFocus();
    }
    
    boolean isFocusCaret() {
        return this.parent.caret == this && this.hasFocus();
    }
    
    public boolean isVisible() {
        this.checkWidget();
        return this.isVisible && this.parent.isVisible() && this.hasFocus();
    }
    
    void killFocus() {
        OS.DestroyCaret();
        this.restoreIMEFont();
    }
    
    void move() {
        this.moved = false;
        this.setCurrentCaret(this);
        if (!OS.SetCaretPos(this.x, this.y)) {
            return;
        }
        this.resizeIME();
    }
    
    void resizeIME() {
        if (!OS.IsDBLocale) {
            return;
        }
        final POINT ptCurrentPos = new POINT();
        if (!OS.GetCaretPos(ptCurrentPos)) {
            return;
        }
        final long hwnd = this.parent.handle;
        final long hIMC = OS.ImmGetContext(hwnd);
        final IME ime = this.parent.getIME();
        if (ime != null && ime.isInlineEnabled()) {
            final Point size = this.getSizeInPixels();
            final CANDIDATEFORM lpCandidate = new CANDIDATEFORM();
            lpCandidate.dwStyle = 128;
            lpCandidate.ptCurrentPos = ptCurrentPos;
            OS.SetRect(lpCandidate.rcArea = new RECT(), ptCurrentPos.x, ptCurrentPos.y, ptCurrentPos.x + size.x, ptCurrentPos.y + size.y);
            OS.ImmSetCandidateWindow(hIMC, lpCandidate);
        }
        else {
            final RECT rect = new RECT();
            OS.GetClientRect(hwnd, rect);
            final COMPOSITIONFORM lpCompForm = new COMPOSITIONFORM();
            lpCompForm.dwStyle = 1;
            lpCompForm.x = ptCurrentPos.x;
            lpCompForm.y = ptCurrentPos.y;
            lpCompForm.left = rect.left;
            lpCompForm.right = rect.right;
            lpCompForm.top = rect.top;
            lpCompForm.bottom = rect.bottom;
            OS.ImmSetCompositionWindow(hIMC, lpCompForm);
        }
        OS.ImmReleaseContext(hwnd, hIMC);
    }
    
    @Override
    void releaseParent() {
        super.releaseParent();
        if (this.parent != null && this == this.parent.caret) {
            if (!this.parent.isDisposed()) {
                this.parent.setCaret((Caret)null);
            }
            else {
                this.parent.caret = null;
            }
        }
    }
    
    @Override
    void releaseWidget() {
        super.releaseWidget();
        if (this.isCurrentCaret()) {
            this.setCurrentCaret(null);
        }
        this.parent = null;
        this.image = null;
        this.font = null;
        this.oldFont = null;
    }
    
    void resize() {
        this.resized = false;
        final long hwnd = this.parent.handle;
        OS.DestroyCaret();
        final long hBitmap = (this.image != null) ? this.image.handle : 0L;
        int width = this.width;
        if (this.image == null && width == 0) {
            final int[] buffer = { 0 };
            if (OS.SystemParametersInfo(8198, 0, buffer, 0)) {
                width = buffer[0];
            }
        }
        OS.CreateCaret(hwnd, hBitmap, width, this.height);
        OS.SetCaretPos(this.x, this.y);
        OS.ShowCaret(hwnd);
        this.move();
    }
    
    void restoreIMEFont() {
        if (!OS.IsDBLocale) {
            return;
        }
        if (this.oldFont == null) {
            return;
        }
        final long hwnd = this.parent.handle;
        final long hIMC = OS.ImmGetContext(hwnd);
        OS.ImmSetCompositionFont(hIMC, this.oldFont);
        OS.ImmReleaseContext(hwnd, hIMC);
        this.oldFont = null;
    }
    
    public void setBounds(final int x, final int y, final int width, final int height) {
        this.checkWidget();
        this.setBoundsInPixels(DPIUtil.autoScaleUp(x), DPIUtil.autoScaleUp(y), DPIUtil.autoScaleUp(width), DPIUtil.autoScaleUp(height));
    }
    
    void setBoundsInPixels(final int x, final int y, final int width, final int height) {
        final boolean samePosition = this.x == x && this.y == y;
        final boolean sameExtent = this.width == width && this.height == height;
        if (samePosition && sameExtent && this.isCurrentCaret()) {
            return;
        }
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        if (sameExtent) {
            this.moved = true;
            if (this.isVisible && this.hasFocus()) {
                this.move();
            }
        }
        else {
            this.resized = true;
            if (this.isVisible && this.hasFocus()) {
                this.resize();
            }
        }
    }
    
    public void setBounds(final Rectangle rect) {
        if (rect == null) {
            this.error(4);
        }
        this.setBoundsInPixels(DPIUtil.autoScaleUp(rect));
    }
    
    void setBoundsInPixels(final Rectangle rect) {
        this.setBoundsInPixels(rect.x, rect.y, rect.width, rect.height);
    }
    
    void setFocus() {
        final long hwnd = this.parent.handle;
        long hBitmap = 0L;
        if (this.image != null) {
            hBitmap = this.image.handle;
        }
        int width = this.width;
        if (this.image == null && width == 0) {
            final int[] buffer = { 0 };
            if (OS.SystemParametersInfo(8198, 0, buffer, 0)) {
                width = buffer[0];
            }
        }
        OS.CreateCaret(hwnd, hBitmap, width, this.height);
        this.move();
        this.setIMEFont();
        if (this.isVisible) {
            OS.ShowCaret(hwnd);
        }
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        if (font != null && font.isDisposed()) {
            this.error(5);
        }
        this.font = font;
        if (this.hasFocus()) {
            this.setIMEFont();
        }
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        this.image = image;
        if (this.isVisible && this.hasFocus()) {
            this.resize();
        }
    }
    
    void setIMEFont() {
        if (!OS.IsDBLocale) {
            return;
        }
        long hFont = 0L;
        if (this.font != null) {
            hFont = this.font.handle;
        }
        if (hFont == 0L) {
            hFont = this.defaultFont();
        }
        final long hwnd = this.parent.handle;
        final long hIMC = OS.ImmGetContext(hwnd);
        if (this.oldFont == null) {
            this.oldFont = new LOGFONT();
            if (!OS.ImmGetCompositionFont(hIMC, this.oldFont)) {
                this.oldFont = null;
            }
        }
        final LOGFONT logFont = new LOGFONT();
        if (OS.GetObject(hFont, LOGFONT.sizeof, logFont) != 0) {
            OS.ImmSetCompositionFont(hIMC, logFont);
        }
        OS.ImmReleaseContext(hwnd, hIMC);
    }
    
    public void setLocation(final int x, final int y) {
        this.checkWidget();
        this.setLocationInPixels(DPIUtil.autoScaleUp(x), DPIUtil.autoScaleUp(y));
    }
    
    void setLocationInPixels(final int x, final int y) {
        if (this.x == x && this.y == y && this.isCurrentCaret()) {
            return;
        }
        this.x = x;
        this.y = y;
        this.moved = true;
        if (this.isVisible && this.hasFocus()) {
            this.move();
        }
    }
    
    private boolean isCurrentCaret() {
        return Caret.currentCaret == this;
    }
    
    private void setCurrentCaret(final Caret caret) {
        Caret.currentCaret = caret;
    }
    
    public void setLocation(Point location) {
        this.checkWidget();
        if (location == null) {
            this.error(4);
        }
        location = DPIUtil.autoScaleUp(location);
        this.setLocationInPixels(location.x, location.y);
    }
    
    public void setSize(final int width, final int height) {
        this.checkWidget();
        this.setSizeInPixels(DPIUtil.autoScaleUp(width), DPIUtil.autoScaleUp(height));
    }
    
    void setSizeInPixels(final int width, final int height) {
        if (this.width == width && this.height == height && this.isCurrentCaret()) {
            return;
        }
        this.width = width;
        this.height = height;
        this.resized = true;
        if (this.isVisible && this.hasFocus()) {
            this.resize();
        }
    }
    
    public void setSize(Point size) {
        this.checkWidget();
        if (size == null) {
            this.error(4);
        }
        size = DPIUtil.autoScaleUp(size);
        this.setSizeInPixels(size.x, size.y);
    }
    
    public void setVisible(final boolean visible) {
        this.checkWidget();
        if (visible == this.isVisible) {
            return;
        }
        this.isVisible = visible;
        final long hwnd = this.parent.handle;
        if (OS.GetFocus() != hwnd) {
            return;
        }
        if (!this.isVisible) {
            OS.HideCaret(hwnd);
        }
        else {
            if (this.resized) {
                this.resize();
            }
            else if (this.moved) {
                this.move();
            }
            OS.ShowCaret(hwnd);
        }
    }
}
