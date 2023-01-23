//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.graphics.*;

public class Sash extends Control
{
    boolean dragging;
    int startX;
    int startY;
    int lastX;
    int lastY;
    static final int INCREMENT = 1;
    static final int PAGE_INCREMENT = 9;
    
    public Sash(final Composite parent, final int style) {
        super(parent, checkStyle(style));
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
        return OS.DefWindowProc(hwnd, msg, wParam, lParam);
    }
    
    void createHandle() {
        super.createHandle();
        this.state |= 0x100;
    }
    
    static int checkStyle(final int style) {
        return Widget.checkBits(style, 256, 512, 0, 0, 0, 0);
    }
    
    Point computeSizeInPixels(final int wHint, final int hHint, final boolean changed) {
        this.checkWidget();
        final int border = this.getBorderWidthInPixels();
        int width = border * 2;
        int height = border * 2;
        if ((this.style & 0x100) != 0x0) {
            width += 64;
            height += 3;
        }
        else {
            width += 3;
            height += 64;
        }
        if (wHint != -1) {
            width = wHint + border * 2;
        }
        if (hHint != -1) {
            height = hHint + border * 2;
        }
        return new Point(width, height);
    }
    
    void drawBand(final int x, final int y, final int width, final int height) {
        if ((this.style & 0x10000) != 0x0) {
            return;
        }
        final long hwndTrack = this.parent.handle;
        final byte[] bits = { -86, 0, 85, 0, -86, 0, 85, 0, -86, 0, 85, 0, -86, 0, 85, 0 };
        final long stippleBitmap = OS.CreateBitmap(8, 8, 1, 1, bits);
        final long stippleBrush = OS.CreatePatternBrush(stippleBitmap);
        final long hDC = OS.GetDCEx(hwndTrack, 0L, 2);
        final long oldBrush = OS.SelectObject(hDC, stippleBrush);
        OS.PatBlt(hDC, x, y, width, height, 5898313);
        OS.SelectObject(hDC, oldBrush);
        OS.ReleaseDC(hwndTrack, hDC);
        OS.DeleteObject(stippleBrush);
        OS.DeleteObject(stippleBitmap);
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
    
    TCHAR windowClass() {
        return this.display.windowClass;
    }
    
    long windowProc() {
        return this.display.windowProc;
    }
    
    LRESULT WM_ERASEBKGND(final long wParam, final long lParam) {
        super.WM_ERASEBKGND(wParam, lParam);
        this.drawBackground(wParam);
        return LRESULT.ONE;
    }
    
    LRESULT WM_KEYDOWN(final long wParam, final long lParam) {
        final LRESULT result = super.WM_KEYDOWN(wParam, lParam);
        if (result != null) {
            return result;
        }
        switch ((int)wParam) {
            case 37:
            case 38:
            case 39:
            case 40: {
                if (OS.GetKeyState(1) < 0) {
                    return result;
                }
                int step = (OS.GetKeyState(17) < 0) ? 1 : 9;
                if ((this.style & 0x200) != 0x0) {
                    if (wParam == 38L) {
                        break;
                    }
                    if (wParam == 40L) {
                        break;
                    }
                    if (wParam == 37L) {
                        step = -step;
                    }
                    if ((this.parent.style & 0x8000000) != 0x0) {
                        step = -step;
                    }
                }
                else {
                    if (wParam == 37L) {
                        break;
                    }
                    if (wParam == 39L) {
                        break;
                    }
                    if (wParam == 38L) {
                        step = -step;
                    }
                }
                final RECT rect = new RECT();
                OS.GetWindowRect(this.handle, rect);
                final int width = rect.right - rect.left;
                final int height = rect.bottom - rect.top;
                final long hwndTrack = this.parent.handle;
                final RECT clientRect = new RECT();
                OS.GetClientRect(hwndTrack, clientRect);
                final int clientWidth = clientRect.right - clientRect.left;
                final int clientHeight = clientRect.bottom - clientRect.top;
                OS.MapWindowPoints(0L, hwndTrack, rect, 2);
                final POINT cursorPt = new POINT();
                int newX = rect.left;
                int newY = rect.top;
                if ((this.style & 0x200) != 0x0) {
                    final POINT point = cursorPt;
                    final int min = Math.min(Math.max(clientRect.left, newX + step), clientWidth - width);
                    point.x = min;
                    newX = min;
                    cursorPt.y = rect.top + height / 2;
                }
                else {
                    cursorPt.x = rect.left + width / 2;
                    final POINT point2 = cursorPt;
                    final int min2 = Math.min(Math.max(clientRect.top, newY + step), clientHeight - height);
                    point2.y = min2;
                    newY = min2;
                }
                if (newX == rect.left && newY == rect.top) {
                    return result;
                }
                OS.ClientToScreen(hwndTrack, cursorPt);
                OS.SetCursorPos(cursorPt.x, cursorPt.y);
                final Event event = new Event();
                event.setBoundsInPixels(new Rectangle(newX, newY, width, height));
                this.sendSelectionEvent(13, event, true);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
                if (event.doit && (this.style & 0x10000) != 0x0) {
                    this.setBoundsInPixels(event.getBoundsInPixels());
                }
                return result;
            }
        }
        return result;
    }
    
    LRESULT WM_GETDLGCODE(final long wParam, final long lParam) {
        return new LRESULT(256L);
    }
    
    LRESULT WM_LBUTTONDOWN(final long wParam, final long lParam) {
        final LRESULT result = super.WM_LBUTTONDOWN(wParam, lParam);
        if (result == LRESULT.ZERO) {
            return result;
        }
        final long hwndTrack = this.parent.handle;
        final POINT pt = new POINT();
        OS.POINTSTOPOINT(pt, lParam);
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        OS.MapWindowPoints(this.handle, 0L, pt, 1);
        this.startX = pt.x - rect.left;
        this.startY = pt.y - rect.top;
        OS.MapWindowPoints(0L, hwndTrack, rect, 2);
        this.lastX = rect.left;
        this.lastY = rect.top;
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        final Event event = new Event();
        event.setBoundsInPixels(new Rectangle(this.lastX, this.lastY, width, height));
        if ((this.style & 0x10000) == 0x0) {
            event.detail = 1;
        }
        this.sendSelectionEvent(13, event, true);
        if (this.isDisposed()) {
            return LRESULT.ZERO;
        }
        final Rectangle bounds = event.getBoundsInPixels();
        if (event.doit) {
            this.dragging = true;
            this.lastX = bounds.x;
            this.lastY = bounds.y;
            this.menuShell().bringToTop();
            if (this.isDisposed()) {
                return LRESULT.ZERO;
            }
            final int flags = 384;
            OS.RedrawWindow(hwndTrack, (RECT)null, 0L, 384);
            this.drawBand(bounds.x, bounds.y, width, height);
            if ((this.style & 0x10000) != 0x0) {
                this.setBoundsInPixels(bounds.x, bounds.y, width, height);
            }
        }
        return result;
    }
    
    LRESULT WM_LBUTTONUP(final long wParam, final long lParam) {
        final LRESULT result = super.WM_LBUTTONUP(wParam, lParam);
        if (result == LRESULT.ZERO) {
            return result;
        }
        if (!this.dragging) {
            return result;
        }
        this.dragging = false;
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        final Event event = new Event();
        event.setBoundsInPixels(new Rectangle(this.lastX, this.lastY, width, height));
        this.drawBand(this.lastX, this.lastY, width, height);
        this.sendSelectionEvent(13, event, true);
        if (this.isDisposed()) {
            return result;
        }
        final Rectangle bounds = event.getBoundsInPixels();
        if (event.doit && (this.style & 0x10000) != 0x0) {
            this.setBoundsInPixels(bounds.x, bounds.y, width, height);
        }
        return result;
    }
    
    LRESULT WM_MOUSEMOVE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_MOUSEMOVE(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (!this.dragging || (wParam & 0x1L) == 0x0L) {
            return result;
        }
        final POINT pt = new POINT();
        OS.POINTSTOPOINT(pt, lParam);
        final long hwndTrack = this.parent.handle;
        OS.MapWindowPoints(this.handle, hwndTrack, pt, 1);
        final RECT rect = new RECT();
        final RECT clientRect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        OS.GetClientRect(hwndTrack, clientRect);
        int newX = this.lastX;
        int newY = this.lastY;
        if ((this.style & 0x200) != 0x0) {
            final int clientWidth = clientRect.right - clientRect.left;
            newX = Math.min(Math.max(0, pt.x - this.startX), clientWidth - width);
        }
        else {
            final int clientHeight = clientRect.bottom - clientRect.top;
            newY = Math.min(Math.max(0, pt.y - this.startY), clientHeight - height);
        }
        if (newX == this.lastX && newY == this.lastY) {
            return result;
        }
        this.drawBand(this.lastX, this.lastY, width, height);
        final Event event = new Event();
        event.setBoundsInPixels(new Rectangle(newX, newY, width, height));
        if ((this.style & 0x10000) == 0x0) {
            event.detail = 1;
        }
        this.sendSelectionEvent(13, event, true);
        if (this.isDisposed()) {
            return LRESULT.ZERO;
        }
        if (event.doit) {
            final Rectangle boundsInPixels = event.getBoundsInPixels();
            this.lastX = boundsInPixels.x;
            this.lastY = boundsInPixels.y;
        }
        final int flags = 384;
        OS.RedrawWindow(hwndTrack, (RECT)null, 0L, 384);
        this.drawBand(this.lastX, this.lastY, width, height);
        if ((this.style & 0x10000) != 0x0) {
            this.setBoundsInPixels(this.lastX, this.lastY, width, height);
        }
        return result;
    }
    
    LRESULT WM_SETCURSOR(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SETCURSOR(wParam, lParam);
        if (result != null) {
            return result;
        }
        final int hitTest = (short)OS.LOWORD(lParam);
        if (hitTest == 1) {
            long hCursor = 0L;
            if ((this.style & 0x100) != 0x0) {
                hCursor = OS.LoadCursor(0L, 32645L);
            }
            else {
                hCursor = OS.LoadCursor(0L, 32644L);
            }
            OS.SetCursor(hCursor);
            return LRESULT.ONE;
        }
        return result;
    }
}
