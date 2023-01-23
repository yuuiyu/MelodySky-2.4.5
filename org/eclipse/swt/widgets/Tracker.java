//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;

public class Tracker extends Widget
{
    Control parent;
    boolean tracking;
    boolean cancelled;
    boolean stippled;
    Rectangle[] rectangles;
    Rectangle[] proportions;
    Rectangle bounds;
    long resizeCursor;
    Cursor clientCursor;
    int cursorOrientation;
    boolean inEvent;
    boolean drawn;
    long hwndTransparent;
    long hwndOpaque;
    long oldTransparentProc;
    long oldOpaqueProc;
    int oldX;
    int oldY;
    static final int STEPSIZE_SMALL = 1;
    static final int STEPSIZE_LARGE = 9;
    
    public Tracker(final Composite parent, final int style) {
        super((Widget)parent, checkStyle(style));
        this.rectangles = new Rectangle[0];
        this.proportions = this.rectangles;
        this.cursorOrientation = 0;
        this.inEvent = false;
        this.parent = (Control)parent;
    }
    
    public Tracker(Display display, final int style) {
        this.rectangles = new Rectangle[0];
        this.proportions = this.rectangles;
        this.cursorOrientation = 0;
        this.inEvent = false;
        if (display == null) {
            display = Display.getCurrent();
        }
        if (display == null) {
            display = Display.getDefault();
        }
        if (!display.isValidThread()) {
            this.error(22);
        }
        this.style = checkStyle(style);
        this.display = display;
        this.reskinWidget();
    }
    
    public void addControlListener(final ControlListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(11, (Listener)typedListener);
        this.addListener(10, (Listener)typedListener);
    }
    
    public void addKeyListener(final KeyListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(2, (Listener)typedListener);
        this.addListener(1, (Listener)typedListener);
    }
    
    Point adjustMoveCursor() {
        if (this.bounds == null) {
            return null;
        }
        final int newX = this.bounds.x + this.bounds.width / 2;
        final int newY = this.bounds.y;
        final POINT pt = new POINT();
        pt.x = newX;
        pt.y = newY;
        if (this.parent != null) {
            OS.ClientToScreen(this.parent.handle, pt);
        }
        OS.SetCursorPos(pt.x, pt.y);
        return new Point(pt.x, pt.y);
    }
    
    Point adjustResizeCursor() {
        if (this.bounds == null) {
            return null;
        }
        int newX;
        if ((this.cursorOrientation & 0x4000) != 0x0) {
            newX = this.bounds.x;
        }
        else if ((this.cursorOrientation & 0x20000) != 0x0) {
            newX = this.bounds.x + this.bounds.width;
        }
        else {
            newX = this.bounds.x + this.bounds.width / 2;
        }
        int newY;
        if ((this.cursorOrientation & 0x80) != 0x0) {
            newY = this.bounds.y;
        }
        else if ((this.cursorOrientation & 0x400) != 0x0) {
            newY = this.bounds.y + this.bounds.height;
        }
        else {
            newY = this.bounds.y + this.bounds.height / 2;
        }
        final POINT pt = new POINT();
        pt.x = newX;
        pt.y = newY;
        if (this.parent != null) {
            OS.ClientToScreen(this.parent.handle, pt);
        }
        OS.SetCursorPos(pt.x, pt.y);
        if (this.clientCursor == null) {
            long newCursor = 0L;
            switch (this.cursorOrientation) {
                case 128: {
                    newCursor = OS.LoadCursor(0L, 32645L);
                    break;
                }
                case 1024: {
                    newCursor = OS.LoadCursor(0L, 32645L);
                    break;
                }
                case 16384: {
                    newCursor = OS.LoadCursor(0L, 32644L);
                    break;
                }
                case 131072: {
                    newCursor = OS.LoadCursor(0L, 32644L);
                    break;
                }
                case 16512: {
                    newCursor = OS.LoadCursor(0L, 32642L);
                    break;
                }
                case 132096: {
                    newCursor = OS.LoadCursor(0L, 32642L);
                    break;
                }
                case 17408: {
                    newCursor = OS.LoadCursor(0L, 32643L);
                    break;
                }
                case 131200: {
                    newCursor = OS.LoadCursor(0L, 32643L);
                    break;
                }
                default: {
                    newCursor = OS.LoadCursor(0L, 32646L);
                    break;
                }
            }
            OS.SetCursor(newCursor);
            if (this.resizeCursor != 0L) {
                OS.DestroyCursor(this.resizeCursor);
            }
            this.resizeCursor = newCursor;
        }
        return new Point(pt.x, pt.y);
    }
    
    static int checkStyle(int style) {
        if ((style & 0x24480) == 0x0) {
            style |= 0x24480;
        }
        return style;
    }
    
    public void close() {
        this.checkWidget();
        this.tracking = false;
    }
    
    Rectangle computeBounds() {
        if (this.rectangles.length == 0) {
            return null;
        }
        int xMin = this.rectangles[0].x;
        int yMin = this.rectangles[0].y;
        int xMax = this.rectangles[0].x + this.rectangles[0].width;
        int yMax = this.rectangles[0].y + this.rectangles[0].height;
        for (int i = 1; i < this.rectangles.length; ++i) {
            if (this.rectangles[i].x < xMin) {
                xMin = this.rectangles[i].x;
            }
            if (this.rectangles[i].y < yMin) {
                yMin = this.rectangles[i].y;
            }
            final int rectRight = this.rectangles[i].x + this.rectangles[i].width;
            if (rectRight > xMax) {
                xMax = rectRight;
            }
            final int rectBottom = this.rectangles[i].y + this.rectangles[i].height;
            if (rectBottom > yMax) {
                yMax = rectBottom;
            }
        }
        return new Rectangle(xMin, yMin, xMax - xMin, yMax - yMin);
    }
    
    Rectangle[] computeProportions(final Rectangle[] rects) {
        final Rectangle[] result = new Rectangle[rects.length];
        this.bounds = this.computeBounds();
        if (this.bounds != null) {
            for (int i = 0; i < rects.length; ++i) {
                int x = 0;
                int y = 0;
                int width = 0;
                int height = 0;
                if (this.bounds.width != 0) {
                    x = (rects[i].x - this.bounds.x) * 100 / this.bounds.width;
                    width = rects[i].width * 100 / this.bounds.width;
                }
                else {
                    width = 100;
                }
                if (this.bounds.height != 0) {
                    y = (rects[i].y - this.bounds.y) * 100 / this.bounds.height;
                    height = rects[i].height * 100 / this.bounds.height;
                }
                else {
                    height = 100;
                }
                result[i] = new Rectangle(x, y, width, height);
            }
        }
        return result;
    }
    
    void drawRectangles(final Rectangle[] rects, final boolean stippled) {
        if (this.hwndOpaque != 0L) {
            final RECT rect1 = new RECT();
            final int bandWidth = stippled ? 3 : 1;
            for (final Rectangle rect2 : rects) {
                rect1.left = rect2.x - bandWidth;
                rect1.top = rect2.y - bandWidth;
                rect1.right = rect2.x + rect2.width + bandWidth * 2;
                rect1.bottom = rect2.y + rect2.height + bandWidth * 2;
                OS.MapWindowPoints(0L, this.hwndOpaque, rect1, 2);
                OS.RedrawWindow(this.hwndOpaque, rect1, 0L, 1);
            }
            return;
        }
        int bandWidth2 = 1;
        final long hwndTrack = (this.parent == null) ? OS.GetDesktopWindow() : this.parent.handle;
        final long hDC = OS.GetDCEx(hwndTrack, 0L, 2);
        long hBitmap = 0L;
        long hBrush = 0L;
        long oldBrush = 0L;
        if (stippled) {
            bandWidth2 = 3;
            final byte[] bits = { -86, 0, 85, 0, -86, 0, 85, 0, -86, 0, 85, 0, -86, 0, 85, 0 };
            hBitmap = OS.CreateBitmap(8, 8, 1, 1, bits);
            hBrush = OS.CreatePatternBrush(hBitmap);
            oldBrush = OS.SelectObject(hDC, hBrush);
        }
        for (final Rectangle rect3 : rects) {
            OS.PatBlt(hDC, rect3.x, rect3.y, rect3.width, bandWidth2, 5898313);
            OS.PatBlt(hDC, rect3.x, rect3.y + bandWidth2, bandWidth2, rect3.height - bandWidth2 * 2, 5898313);
            OS.PatBlt(hDC, rect3.x + rect3.width - bandWidth2, rect3.y + bandWidth2, bandWidth2, rect3.height - bandWidth2 * 2, 5898313);
            OS.PatBlt(hDC, rect3.x, rect3.y + rect3.height - bandWidth2, rect3.width, bandWidth2, 5898313);
        }
        if (stippled) {
            OS.SelectObject(hDC, oldBrush);
            OS.DeleteObject(hBrush);
            OS.DeleteObject(hBitmap);
        }
        OS.ReleaseDC(hwndTrack, hDC);
    }
    
    public Rectangle[] getRectangles() {
        this.checkWidget();
        final Rectangle[] result = this.getRectanglesInPixels();
        for (int i = 0; i < result.length; ++i) {
            result[i] = DPIUtil.autoScaleDown(result[i]);
        }
        return result;
    }
    
    Rectangle[] getRectanglesInPixels() {
        final Rectangle[] result = new Rectangle[this.rectangles.length];
        for (int i = 0; i < this.rectangles.length; ++i) {
            final Rectangle current = this.rectangles[i];
            result[i] = new Rectangle(current.x, current.y, current.width, current.height);
        }
        return result;
    }
    
    public boolean getStippled() {
        this.checkWidget();
        return this.stippled;
    }
    
    void moveRectangles(int xChange, int yChange) {
        if (this.bounds == null) {
            return;
        }
        if (xChange < 0 && (this.style & 0x4000) == 0x0) {
            xChange = 0;
        }
        if (xChange > 0 && (this.style & 0x20000) == 0x0) {
            xChange = 0;
        }
        if (yChange < 0 && (this.style & 0x80) == 0x0) {
            yChange = 0;
        }
        if (yChange > 0 && (this.style & 0x400) == 0x0) {
            yChange = 0;
        }
        if (xChange == 0 && yChange == 0) {
            return;
        }
        final Rectangle bounds3;
        final Rectangle bounds = bounds3 = this.bounds;
        bounds3.x += xChange;
        final Rectangle bounds4;
        final Rectangle bounds2 = bounds4 = this.bounds;
        bounds4.y += yChange;
        for (final Rectangle rectangle3 : this.rectangles) {
            final Rectangle rectangle = rectangle3;
            rectangle3.x += xChange;
            final Rectangle rectangle4;
            final Rectangle rectangle2 = rectangle4 = rectangle;
            rectangle4.y += yChange;
        }
    }
    
    public boolean open() {
        this.checkWidget();
        this.cancelled = false;
        this.tracking = true;
        final int vStyle = this.style & 0x480;
        if (vStyle == 128 || vStyle == 1024) {
            this.cursorOrientation |= vStyle;
        }
        final int hStyle = this.style & 0x24000;
        if (hStyle == 16384 || hStyle == 131072) {
            this.cursorOrientation |= hStyle;
        }
        Callback newProc = null;
        final boolean mouseDown = OS.GetKeyState(1) < 0;
        if (this.parent == null) {
            final Rectangle bounds = this.display.getBoundsInPixels();
            OS.SetLayeredWindowAttributes(this.hwndTransparent = OS.CreateWindowEx(134742144, this.display.windowClass, (TCHAR)null, Integer.MIN_VALUE, bounds.x, bounds.y, bounds.width, bounds.height, 0L, 0L, OS.GetModuleHandle((char[])null), (CREATESTRUCT)null), 0, (byte)1, 2);
            OS.SetLayeredWindowAttributes(this.hwndOpaque = OS.CreateWindowEx(134742144, this.display.windowClass, (TCHAR)null, Integer.MIN_VALUE, bounds.x, bounds.y, bounds.width, bounds.height, this.hwndTransparent, 0L, OS.GetModuleHandle((char[])null), (CREATESTRUCT)null), 16777215, (byte)0, 3);
            this.drawn = false;
            newProc = new Callback((Object)this, "transparentProc", 4);
            final long newProcAddress = newProc.getAddress();
            this.oldTransparentProc = OS.GetWindowLongPtr(this.hwndTransparent, -4);
            OS.SetWindowLongPtr(this.hwndTransparent, -4, newProcAddress);
            this.oldOpaqueProc = OS.GetWindowLongPtr(this.hwndOpaque, -4);
            OS.SetWindowLongPtr(this.hwndOpaque, -4, newProcAddress);
            OS.ShowWindow(this.hwndTransparent, 4);
            OS.ShowWindow(this.hwndOpaque, 4);
        }
        else if (!mouseDown) {
            final Rectangle bounds = this.display.getBoundsInPixels();
            this.hwndTransparent = OS.CreateWindowEx(32, this.display.windowClass, (TCHAR)null, Integer.MIN_VALUE, bounds.x, bounds.y, bounds.width, bounds.height, 0L, 0L, OS.GetModuleHandle((char[])null), (CREATESTRUCT)null);
            newProc = new Callback((Object)this, "transparentProc", 4);
            final long newProcAddress = newProc.getAddress();
            this.oldTransparentProc = OS.GetWindowLongPtr(this.hwndTransparent, -4);
            OS.SetWindowLongPtr(this.hwndTransparent, -4, newProcAddress);
            OS.ShowWindow(this.hwndTransparent, 4);
        }
        this.update();
        this.drawRectangles(this.rectangles, this.stippled);
        Point cursorPos = null;
        if (mouseDown) {
            final POINT pt = new POINT();
            OS.GetCursorPos(pt);
            cursorPos = new Point(pt.x, pt.y);
        }
        else if ((this.style & 0x10) != 0x0) {
            cursorPos = this.adjustResizeCursor();
        }
        else {
            cursorPos = this.adjustMoveCursor();
        }
        if (cursorPos != null) {
            this.oldX = cursorPos.x;
            this.oldY = cursorPos.y;
        }
        final Display display = this.display;
        try {
            final MSG msg = new MSG();
            while (this.tracking && !this.cancelled && (this.parent == null || !this.parent.isDisposed())) {
                display.runSkin();
                display.runDeferredLayouts();
                display.sendPreExternalEventDispatchEvent();
                OS.GetMessage(msg, 0L, 0, 0);
                display.sendPostExternalEventDispatchEvent();
                OS.TranslateMessage(msg);
                switch (msg.message) {
                    case 512:
                    case 514: {
                        this.wmMouse(msg.message, msg.wParam, msg.lParam);
                        break;
                    }
                    case 646: {
                        this.wmIMEChar(msg.hwnd, msg.wParam, msg.lParam);
                        break;
                    }
                    case 258: {
                        this.wmChar(msg.hwnd, msg.wParam, msg.lParam);
                        break;
                    }
                    case 256: {
                        this.wmKeyDown(msg.hwnd, msg.wParam, msg.lParam);
                        break;
                    }
                    case 257: {
                        this.wmKeyUp(msg.hwnd, msg.wParam, msg.lParam);
                        break;
                    }
                    case 262: {
                        this.wmSysChar(msg.hwnd, msg.wParam, msg.lParam);
                        break;
                    }
                    case 260: {
                        this.wmSysKeyDown(msg.hwnd, msg.wParam, msg.lParam);
                        break;
                    }
                    case 261: {
                        this.wmSysKeyUp(msg.hwnd, msg.wParam, msg.lParam);
                        break;
                    }
                }
                if (256 <= msg.message && msg.message <= 264) {
                    continue;
                }
                if (512 <= msg.message && msg.message <= 525) {
                    continue;
                }
                if (this.hwndOpaque == 0L && msg.message == 15) {
                    this.update();
                    this.drawRectangles(this.rectangles, this.stippled);
                }
                OS.DispatchMessage(msg);
                if (this.hwndOpaque == 0L && msg.message == 15) {
                    this.drawRectangles(this.rectangles, this.stippled);
                }
                display.runAsyncMessages(false);
            }
            if (mouseDown) {
                OS.ReleaseCapture();
            }
            if (!this.isDisposed()) {
                this.update();
                this.drawRectangles(this.rectangles, this.stippled);
            }
        }
        finally {
            if (this.hwndTransparent != 0L) {
                OS.DestroyWindow(this.hwndTransparent);
                this.hwndTransparent = 0L;
            }
            this.hwndOpaque = 0L;
            if (newProc != null) {
                newProc.dispose();
                final long n = 0L;
                this.oldOpaqueProc = 0L;
                this.oldTransparentProc = 0L;
            }
            if (this.resizeCursor != 0L) {
                OS.DestroyCursor(this.resizeCursor);
                this.resizeCursor = 0L;
            }
        }
        this.tracking = false;
        return !this.cancelled;
    }
    
    @Override
    void releaseWidget() {
        super.releaseWidget();
        this.parent = null;
        final Rectangle[] array = null;
        this.proportions = array;
        this.rectangles = array;
        this.bounds = null;
    }
    
    public void removeControlListener(final ControlListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(11, (SWTEventListener)listener);
        this.eventTable.unhook(10, (SWTEventListener)listener);
    }
    
    public void removeKeyListener(final KeyListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(2, (SWTEventListener)listener);
        this.eventTable.unhook(1, (SWTEventListener)listener);
    }
    
    void resizeRectangles(int xChange, int yChange) {
        if (this.bounds == null) {
            return;
        }
        if (xChange < 0 && (this.style & 0x4000) != 0x0 && (this.cursorOrientation & 0x20000) == 0x0) {
            this.cursorOrientation |= 0x4000;
        }
        if (xChange > 0 && (this.style & 0x20000) != 0x0 && (this.cursorOrientation & 0x4000) == 0x0) {
            this.cursorOrientation |= 0x20000;
        }
        if (yChange < 0 && (this.style & 0x80) != 0x0 && (this.cursorOrientation & 0x400) == 0x0) {
            this.cursorOrientation |= 0x80;
        }
        if (yChange > 0 && (this.style & 0x400) != 0x0 && (this.cursorOrientation & 0x80) == 0x0) {
            this.cursorOrientation |= 0x400;
        }
        if ((this.cursorOrientation & 0x4000) != 0x0) {
            if (xChange > this.bounds.width) {
                if ((this.style & 0x20000) == 0x0) {
                    return;
                }
                this.cursorOrientation |= 0x20000;
                this.cursorOrientation &= 0xFFFFBFFF;
                final Rectangle bounds9;
                final Rectangle bounds = bounds9 = this.bounds;
                bounds9.x += this.bounds.width;
                xChange -= this.bounds.width;
                this.bounds.width = 0;
                if (this.proportions.length > 1) {
                    for (final Rectangle proportion : this.proportions) {
                        proportion.x = 100 - proportion.x - proportion.width;
                    }
                }
            }
        }
        else if ((this.cursorOrientation & 0x20000) != 0x0 && this.bounds.width < -xChange) {
            if ((this.style & 0x4000) == 0x0) {
                return;
            }
            this.cursorOrientation |= 0x4000;
            this.cursorOrientation &= 0xFFFDFFFF;
            xChange += this.bounds.width;
            this.bounds.width = 0;
            if (this.proportions.length > 1) {
                for (final Rectangle proportion2 : this.proportions) {
                    proportion2.x = 100 - proportion2.x - proportion2.width;
                }
            }
        }
        if ((this.cursorOrientation & 0x80) != 0x0) {
            if (yChange > this.bounds.height) {
                if ((this.style & 0x400) == 0x0) {
                    return;
                }
                this.cursorOrientation |= 0x400;
                this.cursorOrientation &= 0xFFFFFF7F;
                final Rectangle bounds10;
                final Rectangle bounds2 = bounds10 = this.bounds;
                bounds10.y += this.bounds.height;
                yChange -= this.bounds.height;
                this.bounds.height = 0;
                if (this.proportions.length > 1) {
                    for (final Rectangle proportion : this.proportions) {
                        proportion.y = 100 - proportion.y - proportion.height;
                    }
                }
            }
        }
        else if ((this.cursorOrientation & 0x400) != 0x0 && this.bounds.height < -yChange) {
            if ((this.style & 0x80) == 0x0) {
                return;
            }
            this.cursorOrientation |= 0x80;
            this.cursorOrientation &= 0xFFFFFBFF;
            yChange += this.bounds.height;
            this.bounds.height = 0;
            if (this.proportions.length > 1) {
                for (final Rectangle proportion2 : this.proportions) {
                    proportion2.y = 100 - proportion2.y - proportion2.height;
                }
            }
        }
        if ((this.cursorOrientation & 0x4000) != 0x0) {
            final Rectangle bounds11;
            final Rectangle bounds3 = bounds11 = this.bounds;
            bounds11.x += xChange;
            final Rectangle bounds12;
            final Rectangle bounds4 = bounds12 = this.bounds;
            bounds12.width -= xChange;
        }
        else if ((this.cursorOrientation & 0x20000) != 0x0) {
            final Rectangle bounds13;
            final Rectangle bounds5 = bounds13 = this.bounds;
            bounds13.width += xChange;
        }
        if ((this.cursorOrientation & 0x80) != 0x0) {
            final Rectangle bounds14;
            final Rectangle bounds6 = bounds14 = this.bounds;
            bounds14.y += yChange;
            final Rectangle bounds15;
            final Rectangle bounds7 = bounds15 = this.bounds;
            bounds15.height -= yChange;
        }
        else if ((this.cursorOrientation & 0x400) != 0x0) {
            final Rectangle bounds16;
            final Rectangle bounds8 = bounds16 = this.bounds;
            bounds16.height += yChange;
        }
        final Rectangle[] newRects = new Rectangle[this.rectangles.length];
        for (int i = 0; i < this.rectangles.length; ++i) {
            final Rectangle proportion3 = this.proportions[i];
            newRects[i] = new Rectangle(proportion3.x * this.bounds.width / 100 + this.bounds.x, proportion3.y * this.bounds.height / 100 + this.bounds.y, proportion3.width * this.bounds.width / 100, proportion3.height * this.bounds.height / 100);
        }
        this.rectangles = newRects;
    }
    
    public void setCursor(final Cursor newCursor) {
        this.checkWidget();
        this.clientCursor = newCursor;
        if (newCursor != null && this.inEvent) {
            OS.SetCursor(this.clientCursor.handle);
        }
    }
    
    public void setRectangles(final Rectangle[] rectangles) {
        this.checkWidget();
        if (rectangles == null) {
            this.error(4);
        }
        final Rectangle[] rectanglesInPixels = new Rectangle[rectangles.length];
        for (int i = 0; i < rectangles.length; ++i) {
            rectanglesInPixels[i] = DPIUtil.autoScaleUp(rectangles[i]);
        }
        this.setRectanglesInPixels(rectanglesInPixels);
    }
    
    void setRectanglesInPixels(final Rectangle[] rectangles) {
        this.rectangles = new Rectangle[rectangles.length];
        for (int i = 0; i < rectangles.length; ++i) {
            final Rectangle current = rectangles[i];
            if (current == null) {
                this.error(4);
            }
            this.rectangles[i] = new Rectangle(current.x, current.y, current.width, current.height);
        }
        this.proportions = this.computeProportions(rectangles);
    }
    
    public void setStippled(final boolean stippled) {
        this.checkWidget();
        this.stippled = stippled;
    }
    
    long transparentProc(final long hwnd, final long msg, final long wParam, final long lParam) {
        switch ((int)msg) {
            case 132: {
                if (this.inEvent) {
                    return -1L;
                }
                break;
            }
            case 32: {
                if (this.clientCursor != null) {
                    OS.SetCursor(this.clientCursor.handle);
                    return 1L;
                }
                if (this.resizeCursor != 0L) {
                    OS.SetCursor(this.resizeCursor);
                    return 1L;
                }
                break;
            }
            case 15: {
                if (this.hwndOpaque == hwnd) {
                    final PAINTSTRUCT ps = new PAINTSTRUCT();
                    final long hDC = OS.BeginPaint(hwnd, ps);
                    long hBitmap = 0L;
                    long hBrush = 0L;
                    long oldBrush = 0L;
                    final long transparentBrush = OS.CreateSolidBrush(16777215);
                    oldBrush = OS.SelectObject(hDC, transparentBrush);
                    OS.PatBlt(hDC, ps.left, ps.top, ps.right - ps.left, ps.bottom - ps.top, 15728673);
                    OS.SelectObject(hDC, oldBrush);
                    OS.DeleteObject(transparentBrush);
                    int bandWidth = 1;
                    if (this.stippled) {
                        bandWidth = 3;
                        final byte[] bits = { -86, 0, 85, 0, -86, 0, 85, 0, -86, 0, 85, 0, -86, 0, 85, 0 };
                        hBitmap = OS.CreateBitmap(8, 8, 1, 1, bits);
                        hBrush = OS.CreatePatternBrush(hBitmap);
                        oldBrush = OS.SelectObject(hDC, hBrush);
                        OS.SetBkColor(hDC, 15790320);
                    }
                    else {
                        oldBrush = OS.SelectObject(hDC, OS.GetStockObject(4));
                    }
                    final RECT rect1 = new RECT();
                    for (final Rectangle rect2 : this.rectangles) {
                        rect1.left = rect2.x;
                        rect1.top = rect2.y;
                        rect1.right = rect2.x + rect2.width;
                        rect1.bottom = rect2.y + rect2.height;
                        OS.MapWindowPoints(0L, this.hwndOpaque, rect1, 2);
                        final int width = rect1.right - rect1.left;
                        final int height = rect1.bottom - rect1.top;
                        OS.PatBlt(hDC, rect1.left, rect1.top, width, bandWidth, 15728673);
                        OS.PatBlt(hDC, rect1.left, rect1.top + bandWidth, bandWidth, height - bandWidth * 2, 15728673);
                        OS.PatBlt(hDC, rect1.right - bandWidth, rect1.top + bandWidth, bandWidth, height - bandWidth * 2, 15728673);
                        OS.PatBlt(hDC, rect1.left, rect1.bottom - bandWidth, width, bandWidth, 15728673);
                    }
                    OS.SelectObject(hDC, oldBrush);
                    if (this.stippled) {
                        OS.DeleteObject(hBrush);
                        OS.DeleteObject(hBitmap);
                    }
                    OS.EndPaint(hwnd, ps);
                    if (!this.drawn) {
                        OS.SetLayeredWindowAttributes(this.hwndOpaque, 16777215, (byte)(-1), 3);
                        this.drawn = true;
                    }
                    return 0L;
                }
                break;
            }
        }
        return OS.CallWindowProc((hwnd == this.hwndTransparent) ? this.oldTransparentProc : this.oldOpaqueProc, hwnd, (int)msg, wParam, lParam);
    }
    
    void update() {
        if (this.hwndOpaque != 0L) {
            return;
        }
        if (this.parent != null) {
            if (this.parent.isDisposed()) {
                return;
            }
            final Shell shell = this.parent.getShell();
            shell.update(true);
        }
        else {
            this.display.update();
        }
    }
    
    @Override
    LRESULT wmKeyDown(final long hwnd, final long wParam, final long lParam) {
        final LRESULT result = super.wmKeyDown(hwnd, wParam, lParam);
        if (result != null) {
            return result;
        }
        final boolean isMirrored = this.parent != null && (this.parent.style & 0x8000000) != 0x0;
        final int stepSize = (OS.GetKeyState(17) < 0) ? 1 : 9;
        int xChange = 0;
        int yChange = 0;
        switch ((int)wParam) {
            case 27: {
                this.cancelled = true;
                this.tracking = false;
                break;
            }
            case 13: {
                this.tracking = false;
                break;
            }
            case 37: {
                xChange = (isMirrored ? stepSize : (-stepSize));
                break;
            }
            case 39: {
                xChange = (isMirrored ? (-stepSize) : stepSize);
                break;
            }
            case 38: {
                yChange = -stepSize;
                break;
            }
            case 40: {
                yChange = stepSize;
                break;
            }
        }
        if (xChange != 0 || yChange != 0) {
            final Rectangle[] oldRectangles = this.rectangles;
            final boolean oldStippled = this.stippled;
            final Rectangle[] rectsToErase = new Rectangle[this.rectangles.length];
            for (int i = 0; i < this.rectangles.length; ++i) {
                final Rectangle current = this.rectangles[i];
                rectsToErase[i] = new Rectangle(current.x, current.y, current.width, current.height);
            }
            final Event event = new Event();
            event.setLocationInPixels(this.oldX + xChange, this.oldY + yChange);
            Point cursorPos;
            if ((this.style & 0x10) != 0x0) {
                this.resizeRectangles(xChange, yChange);
                this.inEvent = true;
                this.sendEvent(11, event);
                this.inEvent = false;
                if (this.isDisposed()) {
                    this.cancelled = true;
                    return LRESULT.ONE;
                }
                boolean draw = false;
                if (this.rectangles != oldRectangles) {
                    final int length = this.rectangles.length;
                    if (length != rectsToErase.length) {
                        draw = true;
                    }
                    else {
                        for (int j = 0; j < length; ++j) {
                            if (!this.rectangles[j].equals((Object)rectsToErase[j])) {
                                draw = true;
                                break;
                            }
                        }
                    }
                }
                else {
                    draw = true;
                }
                if (draw) {
                    this.drawRectangles(rectsToErase, oldStippled);
                    this.update();
                    this.drawRectangles(this.rectangles, this.stippled);
                }
                cursorPos = this.adjustResizeCursor();
            }
            else {
                this.moveRectangles(xChange, yChange);
                this.inEvent = true;
                this.sendEvent(10, event);
                this.inEvent = false;
                if (this.isDisposed()) {
                    this.cancelled = true;
                    return LRESULT.ONE;
                }
                boolean draw = false;
                if (this.rectangles != oldRectangles) {
                    final int length = this.rectangles.length;
                    if (length != rectsToErase.length) {
                        draw = true;
                    }
                    else {
                        for (int j = 0; j < length; ++j) {
                            if (!this.rectangles[j].equals((Object)rectsToErase[j])) {
                                draw = true;
                                break;
                            }
                        }
                    }
                }
                else {
                    draw = true;
                }
                if (draw) {
                    this.drawRectangles(rectsToErase, oldStippled);
                    this.update();
                    this.drawRectangles(this.rectangles, this.stippled);
                }
                cursorPos = this.adjustMoveCursor();
            }
            if (cursorPos != null) {
                this.oldX = cursorPos.x;
                this.oldY = cursorPos.y;
            }
        }
        return result;
    }
    
    @Override
    LRESULT wmSysKeyDown(final long hwnd, final long wParam, final long lParam) {
        final LRESULT result = super.wmSysKeyDown(hwnd, wParam, lParam);
        if (result != null) {
            return result;
        }
        this.cancelled = true;
        this.tracking = false;
        return result;
    }
    
    LRESULT wmMouse(final int message, final long wParam, final long lParam) {
        final boolean isMirrored = this.parent != null && (this.parent.style & 0x8000000) != 0x0;
        final int newPos = OS.GetMessagePos();
        int newX = OS.GET_X_LPARAM((long)newPos);
        int newY = OS.GET_Y_LPARAM((long)newPos);
        if (newX != this.oldX || newY != this.oldY) {
            final Rectangle[] oldRectangles = this.rectangles;
            final boolean oldStippled = this.stippled;
            final Rectangle[] rectsToErase = new Rectangle[this.rectangles.length];
            for (int i = 0; i < this.rectangles.length; ++i) {
                final Rectangle current = this.rectangles[i];
                rectsToErase[i] = new Rectangle(current.x, current.y, current.width, current.height);
            }
            final Event event = new Event();
            event.setLocationInPixels(newX, newY);
            if ((this.style & 0x10) != 0x0) {
                if (isMirrored) {
                    this.resizeRectangles(this.oldX - newX, newY - this.oldY);
                }
                else {
                    this.resizeRectangles(newX - this.oldX, newY - this.oldY);
                }
                this.inEvent = true;
                this.sendEvent(11, event);
                this.inEvent = false;
                if (this.isDisposed()) {
                    this.cancelled = true;
                    return LRESULT.ONE;
                }
                boolean draw = false;
                if (this.rectangles != oldRectangles) {
                    final int length = this.rectangles.length;
                    if (length != rectsToErase.length) {
                        draw = true;
                    }
                    else {
                        for (int j = 0; j < length; ++j) {
                            if (!this.rectangles[j].equals((Object)rectsToErase[j])) {
                                draw = true;
                                break;
                            }
                        }
                    }
                }
                else {
                    draw = true;
                }
                if (draw) {
                    this.drawRectangles(rectsToErase, oldStippled);
                    this.update();
                    this.drawRectangles(this.rectangles, this.stippled);
                }
                final Point cursorPos = this.adjustResizeCursor();
                if (cursorPos != null) {
                    newX = cursorPos.x;
                    newY = cursorPos.y;
                }
            }
            else {
                if (isMirrored) {
                    this.moveRectangles(this.oldX - newX, newY - this.oldY);
                }
                else {
                    this.moveRectangles(newX - this.oldX, newY - this.oldY);
                }
                this.inEvent = true;
                this.sendEvent(10, event);
                this.inEvent = false;
                if (this.isDisposed()) {
                    this.cancelled = true;
                    return LRESULT.ONE;
                }
                boolean draw = false;
                if (this.rectangles != oldRectangles) {
                    final int length = this.rectangles.length;
                    if (length != rectsToErase.length) {
                        draw = true;
                    }
                    else {
                        for (int j = 0; j < length; ++j) {
                            if (!this.rectangles[j].equals((Object)rectsToErase[j])) {
                                draw = true;
                                break;
                            }
                        }
                    }
                }
                else {
                    draw = true;
                }
                if (draw) {
                    this.drawRectangles(rectsToErase, oldStippled);
                    this.update();
                    this.drawRectangles(this.rectangles, this.stippled);
                }
            }
            this.oldX = newX;
            this.oldY = newY;
        }
        this.tracking = (message != 514);
        return null;
    }
}
