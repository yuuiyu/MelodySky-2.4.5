//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.accessibility.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.gdip.*;
import java.util.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.internal.win32.*;

public abstract class Control extends Widget implements Drawable
{
    public long handle;
    Composite parent;
    Cursor cursor;
    Menu menu;
    Menu activeMenu;
    String toolTipText;
    Object layoutData;
    Accessible accessible;
    Image backgroundImage;
    Region region;
    Font font;
    int drawCount;
    int foreground;
    int background;
    int backgroundAlpha;
    
    Control() {
        this.backgroundAlpha = 255;
    }
    
    public Control(final Composite parent, final int style) {
        super((Widget)parent, style);
        this.backgroundAlpha = 255;
        this.parent = parent;
        this.createWidget();
    }
    
    public void addControlListener(final ControlListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(11, typedListener);
        this.addListener(10, typedListener);
    }
    
    public void addDragDetectListener(final DragDetectListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(29, typedListener);
    }
    
    public void addFocusListener(final FocusListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(15, typedListener);
        this.addListener(16, typedListener);
    }
    
    public void addGestureListener(final GestureListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(48, typedListener);
    }
    
    public void addHelpListener(final HelpListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(28, typedListener);
    }
    
    public void addKeyListener(final KeyListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(2, typedListener);
        this.addListener(1, typedListener);
    }
    
    public void addMenuDetectListener(final MenuDetectListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(35, typedListener);
    }
    
    public void addMouseListener(final MouseListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(3, typedListener);
        this.addListener(4, typedListener);
        this.addListener(8, typedListener);
    }
    
    public void addMouseTrackListener(final MouseTrackListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(6, typedListener);
        this.addListener(7, typedListener);
        this.addListener(32, typedListener);
    }
    
    public void addMouseMoveListener(final MouseMoveListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(5, typedListener);
    }
    
    public void addMouseWheelListener(final MouseWheelListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(37, typedListener);
    }
    
    public void addPaintListener(final PaintListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(9, typedListener);
    }
    
    public void addTouchListener(final TouchListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(47, typedListener);
    }
    
    public void addTraverseListener(final TraverseListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(31, typedListener);
    }
    
    int binarySearch(final int[] indices, final int start, final int end, final int index) {
        int low = start;
        int high = end - 1;
        while (low <= high) {
            final int mid = low + high >>> 1;
            if (indices[mid] == index) {
                return mid;
            }
            if (indices[mid] < index) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        return -low - 1;
    }
    
    long borderHandle() {
        return this.handle;
    }
    
    void checkBackground() {
        final Shell shell = this.getShell();
        if (this == shell) {
            return;
        }
        this.state &= 0xFFFFFBFF;
        Composite composite = this.parent;
        while (true) {
            final int mode = composite.backgroundMode;
            if (mode != 0 || this.backgroundAlpha == 0) {
                Label_0088: {
                    if (mode == 1 || this.backgroundAlpha == 0) {
                        Control control = this;
                        while ((control.state & 0x100) != 0x0) {
                            control = (Control)control.parent;
                            if (control == composite) {
                                break Label_0088;
                            }
                        }
                        return;
                    }
                }
                this.state |= 0x400;
                return;
            }
            if (composite == shell) {
                return;
            }
            composite = composite.parent;
        }
    }
    
    void checkBorder() {
        if (this.getBorderWidthInPixels() == 0) {
            this.style &= 0xFFFFF7FF;
        }
    }
    
    void checkBuffered() {
        this.style &= 0xDFFFFFFF;
    }
    
    void checkComposited() {
    }
    
    void checkMirrored() {
        if ((this.style & 0x4000000) != 0x0) {
            final int bits = OS.GetWindowLong(this.handle, -20);
            if ((bits & 0x400000) != 0x0) {
                this.style |= 0x8000000;
            }
        }
    }
    
    public Point computeSize(final int wHint, final int hHint) {
        return this.computeSize(wHint, hHint, true);
    }
    
    public Point computeSize(int wHint, int hHint, final boolean changed) {
        this.checkWidget();
        wHint = ((wHint != -1) ? DPIUtil.autoScaleUp(wHint) : wHint);
        hHint = ((hHint != -1) ? DPIUtil.autoScaleUp(hHint) : hHint);
        return DPIUtil.autoScaleDown(this.computeSizeInPixels(wHint, hHint, changed));
    }
    
    Point computeSizeInPixels(final int wHint, final int hHint, final boolean changed) {
        int width = 64;
        int height = 64;
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
    
    Widget computeTabGroup() {
        if (this.isTabGroup()) {
            return this;
        }
        return this.parent.computeTabGroup();
    }
    
    Control computeTabRoot() {
        final Control[] tabList = this.parent._getTabList();
        if (tabList != null) {
            int index;
            for (index = 0; index < tabList.length && tabList[index] != this; ++index) {}
            if (index == tabList.length && this.isTabGroup()) {
                return this;
            }
        }
        return this.parent.computeTabRoot();
    }
    
    Widget[] computeTabList() {
        if (this.isTabGroup() && this.getVisible() && this.getEnabled()) {
            return new Widget[] { this };
        }
        return new Widget[0];
    }
    
    void createHandle() {
        final long hwndParent = this.widgetParent();
        this.handle = OS.CreateWindowEx(this.widgetExtStyle(), this.windowClass(), (TCHAR)null, this.widgetStyle(), Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0, hwndParent, 0L, OS.GetModuleHandle((char[])null), this.widgetCreateStruct());
        if (this.handle == 0L) {
            this.error(2);
        }
        final int bits = OS.GetWindowLong(this.handle, -16);
        if ((bits & 0x40000000) != 0x0) {
            OS.SetWindowLongPtr(this.handle, -12, this.handle);
        }
    }
    
    void checkGesture() {
        if (OS.WIN32_VERSION >= OS.VERSION(6, 1)) {
            final int value = OS.GetSystemMetrics(94);
            if ((value & 0xC0) != 0x0) {
                final GESTURECONFIG config = new GESTURECONFIG();
                config.dwID = 5;
                config.dwWant = 1;
                config.dwBlock = 0;
                OS.SetGestureConfig(this.handle, 0, 1, config, GESTURECONFIG.sizeof);
            }
        }
    }
    
    void createWidget() {
        this.state |= 0x8000;
        final int n = -1;
        this.background = -1;
        this.foreground = -1;
        this.checkOrientation((Widget)this.parent);
        this.createHandle();
        this.checkBackground();
        this.checkBuffered();
        this.checkComposited();
        this.register();
        this.subclass();
        this.setDefaultFont();
        this.checkMirrored();
        this.checkBorder();
        this.checkGesture();
        if ((this.state & 0x400) != 0x0) {
            this.setBackground();
        }
    }
    
    int defaultBackground() {
        return OS.GetSysColor(15);
    }
    
    long defaultFont() {
        return this.display.getSystemFont().handle;
    }
    
    int defaultForeground() {
        return OS.GetSysColor(8);
    }
    
    void deregister() {
        this.display.removeControl(this.handle);
    }
    
    @Override
    void destroyWidget() {
        final long hwnd = this.topHandle();
        this.releaseHandle();
        if (hwnd != 0L) {
            OS.DestroyWindow(hwnd);
        }
    }
    
    public boolean dragDetect(final Event event) {
        this.checkWidget();
        if (event == null) {
            this.error(4);
        }
        final Point loc = event.getLocationInPixels();
        return this.dragDetect(event.button, event.count, event.stateMask, loc.x, loc.y);
    }
    
    public boolean dragDetect(final MouseEvent event) {
        this.checkWidget();
        if (event == null) {
            this.error(4);
        }
        return this.dragDetect(event.button, event.count, event.stateMask, DPIUtil.autoScaleUp(event.x), DPIUtil.autoScaleUp(event.y));
    }
    
    boolean dragDetect(final int button, final int count, final int stateMask, final int x, final int y) {
        if (button != 1 || count != 1) {
            return false;
        }
        final boolean dragging = this.dragDetect(this.handle, x, y, false, null, null);
        if (OS.GetKeyState(1) < 0 && OS.GetCapture() != this.handle) {
            OS.SetCapture(this.handle);
        }
        if (!dragging) {
            if (button == 1 && OS.GetKeyState(27) >= 0) {
                int wParam = 0;
                if ((stateMask & 0x40000) != 0x0) {
                    wParam |= 0x8;
                }
                if ((stateMask & 0x20000) != 0x0) {
                    wParam |= 0x4;
                }
                if ((stateMask & 0x10000) != 0x0) {
                    wParam |= 0x20;
                }
                if ((stateMask & 0x80000) != 0x0) {
                    wParam |= 0x1;
                }
                if ((stateMask & 0x100000) != 0x0) {
                    wParam |= 0x10;
                }
                if ((stateMask & 0x200000) != 0x0) {
                    wParam |= 0x2;
                }
                if ((stateMask & 0x800000) != 0x0) {
                    wParam |= 0x20;
                }
                if ((stateMask & 0x2000000) != 0x0) {
                    wParam |= 0x40;
                }
                final long lParam = OS.MAKELPARAM(x, y);
                OS.SendMessage(this.handle, 514, (long)wParam, lParam);
            }
            return false;
        }
        return this.sendDragEvent(button, stateMask, x, y);
    }
    
    void drawBackground(final long hDC) {
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        this.drawBackground(hDC, rect);
    }
    
    void drawBackground(final long hDC, final RECT rect) {
        this.drawBackground(hDC, rect, -1, 0, 0);
    }
    
    void drawBackground(final long hDC, final RECT rect, int pixel, final int tx, final int ty) {
        Control control = this.findBackgroundControl();
        if (control != null) {
            if (control.backgroundImage != null) {
                this.fillImageBackground(hDC, control, rect, tx, ty);
                return;
            }
            pixel = control.getBackgroundPixel();
        }
        if (pixel == -1 && (this.state & 0x100) != 0x0 && OS.IsAppThemed()) {
            control = this.findThemeControl();
            if (control != null) {
                this.fillThemeBackground(hDC, control, rect);
                return;
            }
        }
        if (pixel == -1) {
            pixel = this.getBackgroundPixel();
        }
        this.fillBackground(hDC, pixel, rect);
    }
    
    void drawImageBackground(final long hDC, final long hwnd, final long hBitmap, final RECT rect, final int tx, final int ty) {
        final RECT rect2 = new RECT();
        OS.GetClientRect(hwnd, rect2);
        OS.MapWindowPoints(hwnd, this.handle, rect2, 2);
        final long hBrush = this.findBrush(hBitmap, 3);
        final POINT lpPoint = new POINT();
        OS.GetWindowOrgEx(hDC, lpPoint);
        OS.SetBrushOrgEx(hDC, -rect2.left - lpPoint.x - tx, -rect2.top - lpPoint.y - ty, lpPoint);
        final long hOldBrush = OS.SelectObject(hDC, hBrush);
        OS.PatBlt(hDC, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top, 15728673);
        OS.SetBrushOrgEx(hDC, lpPoint.x, lpPoint.y, (POINT)null);
        OS.SelectObject(hDC, hOldBrush);
    }
    
    void drawThemeBackground(final long hDC, final long hwnd, final RECT rect) {
    }
    
    void enableDrag(final boolean enabled) {
    }
    
    void maybeEnableDarkSystemTheme() {
        this.maybeEnableDarkSystemTheme(this.handle);
    }
    
    void enableWidget(final boolean enabled) {
        OS.EnableWindow(this.handle, enabled);
    }
    
    void fillBackground(final long hDC, final int pixel, final RECT rect) {
        if (rect.left > rect.right || rect.top > rect.bottom) {
            return;
        }
        OS.FillRect(hDC, rect, this.findBrush(pixel, 0));
    }
    
    void fillImageBackground(final long hDC, final Control control, final RECT rect, final int tx, final int ty) {
        if (rect.left > rect.right || rect.top > rect.bottom) {
            return;
        }
        if (control != null) {
            final Image image = control.backgroundImage;
            if (image != null) {
                control.drawImageBackground(hDC, this.handle, image.handle, rect, tx, ty);
            }
        }
    }
    
    void fillThemeBackground(final long hDC, final Control control, final RECT rect) {
        if (rect.left > rect.right || rect.top > rect.bottom) {
            return;
        }
        if (control != null) {
            control.drawThemeBackground(hDC, this.handle, rect);
        }
    }
    
    Control findBackgroundControl() {
        if ((this.background != -1 || this.backgroundImage != null) && this.backgroundAlpha > 0) {
            return this;
        }
        return (this.parent != null && (this.state & 0x400) != 0x0) ? this.parent.findBackgroundControl() : null;
    }
    
    long findBrush(final long value, final int lbStyle) {
        return this.parent.findBrush(value, lbStyle);
    }
    
    Cursor findCursor() {
        if (this.cursor != null) {
            return this.cursor;
        }
        return this.parent.findCursor();
    }
    
    Control findImageControl() {
        final Control control = this.findBackgroundControl();
        return (control != null && control.backgroundImage != null) ? control : null;
    }
    
    Control findThemeControl() {
        return (this.background == -1 && this.backgroundImage == null) ? this.parent.findThemeControl() : null;
    }
    
    Menu[] findMenus(final Control control) {
        if (this.menu != null && this != control) {
            return new Menu[] { this.menu };
        }
        return new Menu[0];
    }
    
    char findMnemonic(final String string) {
        int index = 0;
        final int length = string.length();
        while (true) {
            if (index < length && string.charAt(index) != '&') {
                ++index;
            }
            else {
                if (++index >= length) {
                    return '\0';
                }
                if (string.charAt(index) != '&') {
                    return string.charAt(index);
                }
                if (++index >= length) {
                    return '\0';
                }
                continue;
            }
        }
    }
    
    void fixChildren(final Shell newShell, final Shell oldShell, final Decorations newDecorations, final Decorations oldDecorations, final Menu[] menus) {
        oldShell.fixShell(newShell, this);
        oldDecorations.fixDecorations(newDecorations, this, menus);
    }
    
    void fixFocus(final Control focusControl) {
        final Shell shell = this.getShell();
        Control control = this;
        while (control != shell && (control = (Control)control.parent) != null) {
            if (control.setFocus()) {
                return;
            }
        }
        shell.setSavedFocus(focusControl);
        OS.SetFocus(0L);
    }
    
    public boolean forceFocus() {
        this.checkWidget();
        if (this.display.focusEvent == 16) {
            return false;
        }
        final Decorations shell = this.menuShell();
        shell.setSavedFocus(this);
        if (!this.isEnabled() || !this.isVisible() || !this.isActive()) {
            return false;
        }
        if (this.isFocusControl()) {
            return true;
        }
        shell.setSavedFocus(null);
        OS.SetFocus(this.handle);
        if (this.isDisposed()) {
            return false;
        }
        shell.setSavedFocus(this);
        return this.isFocusControl();
    }
    
    void forceResize() {
        if (this.parent == null) {
            return;
        }
        final WINDOWPOS[] lpwp = this.parent.lpwp;
        if (lpwp == null) {
            return;
        }
        for (int i = 0; i < lpwp.length; ++i) {
            final WINDOWPOS wp = lpwp[i];
            if (wp != null && wp.hwnd == this.handle) {
                OS.SetWindowPos(wp.hwnd, 0L, wp.x, wp.y, wp.cx, wp.cy, wp.flags);
                lpwp[i] = null;
                return;
            }
        }
    }
    
    public Accessible getAccessible() {
        this.checkWidget();
        if (this.accessible == null) {
            this.accessible = this.new_Accessible(this);
        }
        return this.accessible;
    }
    
    public Color getBackground() {
        this.checkWidget();
        if (this.backgroundAlpha == 0) {
            final Color color = Color.win32_new((Device)this.display, this.background, 0);
            return color;
        }
        Control control = this.findBackgroundControl();
        if (control == null) {
            control = this;
        }
        return Color.win32_new((Device)this.display, control.getBackgroundPixel(), this.backgroundAlpha);
    }
    
    public Image getBackgroundImage() {
        this.checkWidget();
        Control control = this.findBackgroundControl();
        if (control == null) {
            control = this;
        }
        return control.backgroundImage;
    }
    
    int getBackgroundPixel() {
        return (this.background != -1) ? this.background : this.defaultBackground();
    }
    
    public int getBorderWidth() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getBorderWidthInPixels());
    }
    
    int getBorderWidthInPixels() {
        final long borderHandle = this.borderHandle();
        final int bits1 = OS.GetWindowLong(borderHandle, -20);
        if ((bits1 & 0x200) != 0x0) {
            return OS.GetSystemMetrics(45);
        }
        if ((bits1 & 0x20000) != 0x0) {
            return OS.GetSystemMetrics(5);
        }
        final int bits2 = OS.GetWindowLong(borderHandle, -16);
        if ((bits2 & 0x800000) == 0x0) {
            return 0;
        }
        if (this.isUseWsBorder()) {
            return OS.GetSystemMetrics(45);
        }
        return OS.GetSystemMetrics(5);
    }
    
    public Rectangle getBounds() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getBoundsInPixels());
    }
    
    Rectangle getBoundsInPixels() {
        this.forceResize();
        final RECT rect = new RECT();
        OS.GetWindowRect(this.topHandle(), rect);
        final long hwndParent = (this.parent == null) ? 0L : this.parent.handle;
        OS.MapWindowPoints(0L, hwndParent, rect, 2);
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        return new Rectangle(rect.left, rect.top, width, height);
    }
    
    int getCodePage() {
        return 0;
    }
    
    String getClipboardText() {
        String string = "";
        if (OS.OpenClipboard(0L)) {
            final long hMem = OS.GetClipboardData(13);
            if (hMem != 0L) {
                final int byteCount = OS.GlobalSize(hMem) / 2 * 2;
                final long ptr = OS.GlobalLock(hMem);
                if (ptr != 0L) {
                    final TCHAR buffer = new TCHAR(0, byteCount / 2);
                    OS.MoveMemory(buffer, ptr, byteCount);
                    string = buffer.toString(0, buffer.strlen());
                    OS.GlobalUnlock(hMem);
                }
            }
            OS.CloseClipboard();
        }
        return string;
    }
    
    public Cursor getCursor() {
        this.checkWidget();
        return this.cursor;
    }
    
    public boolean getDragDetect() {
        this.checkWidget();
        return (this.state & 0x8000) != 0x0;
    }
    
    boolean getDrawing() {
        return this.drawCount <= 0;
    }
    
    public boolean getEnabled() {
        this.checkWidget();
        return OS.IsWindowEnabled(this.handle);
    }
    
    public Font getFont() {
        this.checkWidget();
        if (this.font != null) {
            return this.font;
        }
        long hFont = OS.SendMessage(this.handle, 49, 0L, 0L);
        if (hFont == 0L) {
            hFont = this.defaultFont();
        }
        return Font.win32_new((Device)this.display, hFont);
    }
    
    public Color getForeground() {
        this.checkWidget();
        return Color.win32_new((Device)this.display, this.getForegroundPixel());
    }
    
    int getForegroundPixel() {
        return (this.foreground != -1) ? this.foreground : this.defaultForeground();
    }
    
    public Object getLayoutData() {
        this.checkWidget();
        return this.layoutData;
    }
    
    public Point getLocation() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getLocationInPixels());
    }
    
    Point getLocationInPixels() {
        this.forceResize();
        final RECT rect = new RECT();
        OS.GetWindowRect(this.topHandle(), rect);
        final long hwndParent = (this.parent == null) ? 0L : this.parent.handle;
        OS.MapWindowPoints(0L, hwndParent, rect, 2);
        return new Point(rect.left, rect.top);
    }
    
    public Menu getMenu() {
        this.checkWidget();
        return this.menu;
    }
    
    public Monitor getMonitor() {
        this.checkWidget();
        final long hmonitor = OS.MonitorFromWindow(this.handle, 2);
        return this.display.getMonitor(hmonitor);
    }
    
    public int getOrientation() {
        this.checkWidget();
        return this.style & 0x6000000;
    }
    
    public Composite getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    Control[] getPath() {
        int count = 0;
        final Shell shell = this.getShell();
        for (Control control = this; control != shell; control = (Control)control.parent) {
            ++count;
        }
        Control control = this;
        final Control[] result = new Control[count];
        while (control != shell) {
            result[--count] = control;
            control = (Control)control.parent;
        }
        return result;
    }
    
    public Region getRegion() {
        this.checkWidget();
        return this.region;
    }
    
    public Shell getShell() {
        this.checkWidget();
        return this.parent.getShell();
    }
    
    public Point getSize() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getSizeInPixels());
    }
    
    Point getSizeInPixels() {
        this.forceResize();
        final RECT rect = new RECT();
        OS.GetWindowRect(this.topHandle(), rect);
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        return new Point(width, height);
    }
    
    int getSlightlyDifferentColor(final int pixel) {
        return this.getDifferentColor(pixel, 0.1);
    }
    
    int getDifferentColor(final int pixel) {
        return this.getDifferentColor(pixel, 0.2);
    }
    
    int getDifferentColor(final int pixel, final double factor) {
        int red = pixel & 0xFF;
        int green = (pixel & 0xFF00) >> 8;
        int blue = (pixel & 0xFF0000) >> 16;
        red += (int)this.calcDiff(red, factor);
        green += (int)this.calcDiff(green, factor);
        blue += (int)this.calcDiff(blue, factor);
        return (red & 0xFF) | (green & 0xFF) << 8 | (blue & 0xFF) << 16;
    }
    
    long calcDiff(final int component, final double factor) {
        if (component > 127) {
            return Math.round(component * -1 * factor);
        }
        return Math.round((255 - component) * factor);
    }
    
    int getSlightlyDifferentBackgroundColor(final int pixel) {
        final int offset = 8;
        int red = pixel & 0xFF;
        int green = (pixel & 0xFF00) >> 8;
        int blue = (pixel & 0xFF0000) >> 16;
        red = ((red > 127) ? (red - 8) : (red + 8));
        green = ((green > 127) ? (green - 8) : (green + 8));
        blue = ((blue > 127) ? (blue - 8) : (blue + 8));
        return (red & 0xFF) | (green & 0xFF) << 8 | (blue & 0xFF) << 16;
    }
    
    public int getTextDirection() {
        this.checkWidget();
        final int flags = 4202496;
        final int bits = OS.GetWindowLong(this.handle, -20) & 0x402000;
        return (bits == 0 || bits == 4202496) ? 33554432 : 67108864;
    }
    
    public String getToolTipText() {
        this.checkWidget();
        return this.toolTipText;
    }
    
    public boolean getTouchEnabled() {
        this.checkWidget();
        return OS.IsTouchWindow(this.handle, (long[])null);
    }
    
    public boolean getVisible() {
        this.checkWidget();
        if (!this.getDrawing()) {
            return (this.state & 0x10) == 0x0;
        }
        final int bits = OS.GetWindowLong(this.handle, -16);
        return (bits & 0x10000000) != 0x0;
    }
    
    boolean hasCursor() {
        final RECT rect = new RECT();
        if (!OS.GetClientRect(this.handle, rect)) {
            return false;
        }
        OS.MapWindowPoints(this.handle, 0L, rect, 2);
        final POINT pt = new POINT();
        return OS.GetCursorPos(pt) && OS.PtInRect(rect, pt);
    }
    
    boolean hasCustomBackground() {
        return this.background != -1;
    }
    
    boolean hasCustomForeground() {
        return this.foreground != -1;
    }
    
    boolean hasFocus() {
        for (long hwndFocus = OS.GetFocus(); hwndFocus != 0L; hwndFocus = OS.GetParent(hwndFocus)) {
            if (hwndFocus == this.handle) {
                return true;
            }
            if (this.display.getControl(hwndFocus) != null) {
                return false;
            }
        }
        return false;
    }
    
    public long internal_new_GC(final GCData data) {
        this.checkWidget();
        long hwnd = this.handle;
        if (data != null && data.hwnd != 0L) {
            hwnd = data.hwnd;
        }
        if (data != null) {
            data.hwnd = hwnd;
        }
        long hDC = 0L;
        if (data == null || data.ps == null) {
            hDC = OS.GetDC(hwnd);
        }
        else {
            hDC = OS.BeginPaint(hwnd, data.ps);
        }
        if (hDC == 0L) {
            this.error(2);
        }
        if (data != null) {
            final int mask = 100663296;
            if ((data.style & 0x6000000) != 0x0) {
                data.layout = (((data.style & 0x4000000) != 0x0) ? 1 : 0);
            }
            else {
                final int flags = OS.GetLayout(hDC);
                if ((flags & 0x1) != 0x0) {
                    data.style |= 0xC000000;
                }
                else {
                    data.style |= 0x2000000;
                }
            }
            data.device = this.display;
            final int foreground = this.getForegroundPixel();
            if (foreground != OS.GetTextColor(hDC)) {
                data.foreground = foreground;
            }
            Control control = this.findBackgroundControl();
            if (control == null) {
                control = this;
            }
            final int background = control.getBackgroundPixel();
            if (background != OS.GetBkColor(hDC)) {
                data.background = background;
            }
            data.font = ((this.font != null) ? this.font : Font.win32_new((Device)this.display, OS.SendMessage(hwnd, 49, 0L, 0L)));
            data.uiState = (int)OS.SendMessage(hwnd, 297, 0L, 0L);
        }
        return hDC;
    }
    
    public void internal_dispose_GC(final long hDC, final GCData data) {
        this.checkWidget();
        long hwnd = this.handle;
        if (data != null && data.hwnd != 0L) {
            hwnd = data.hwnd;
        }
        if (data == null || data.ps == null) {
            OS.ReleaseDC(hwnd, hDC);
        }
        else {
            OS.EndPaint(hwnd, data.ps);
        }
    }
    
    boolean isActive() {
        final Dialog dialog = this.display.getModalDialog();
        if (dialog != null) {
            final Shell dialogShell = dialog.parent;
            if (dialogShell != null && !dialogShell.isDisposed() && dialogShell != this.getShell()) {
                return false;
            }
        }
        Shell shell = null;
        final Shell[] modalShells = this.display.modalShells;
        if (modalShells != null) {
            final int bits = 196608;
            int index = modalShells.length;
            while (--index >= 0) {
                final Shell modal = modalShells[index];
                if (modal != null) {
                    if ((modal.style & 0x30000) != 0x0) {
                        Control control;
                        for (control = this; control != null && control != modal; control = (Control)control.parent) {}
                        if (control != modal) {
                            return false;
                        }
                        break;
                    }
                    else {
                        if ((modal.style & 0x8000) == 0x0) {
                            continue;
                        }
                        if (shell == null) {
                            shell = this.getShell();
                        }
                        if (modal.parent == shell) {
                            return false;
                        }
                        continue;
                    }
                }
            }
        }
        if (shell == null) {
            shell = this.getShell();
        }
        return shell.getEnabled();
    }
    
    public boolean isEnabled() {
        this.checkWidget();
        return this.getEnabled() && this.parent.isEnabled();
    }
    
    public boolean isFocusControl() {
        this.checkWidget();
        final Control focusControl = this.display.focusControl;
        if (focusControl != null && !focusControl.isDisposed()) {
            return this == focusControl;
        }
        return this.hasFocus();
    }
    
    boolean isFocusAncestor(Control control) {
        while (control != null && control != this && !(control instanceof Shell)) {
            control = (Control)control.parent;
        }
        return control == this;
    }
    
    public boolean isReparentable() {
        this.checkWidget();
        return true;
    }
    
    boolean isShowing() {
        if (!this.isVisible()) {
            return false;
        }
        for (Control control = this; control != null; control = (Control)control.parent) {
            final Point size = control.getSizeInPixels();
            if (size.x == 0 || size.y == 0) {
                return false;
            }
        }
        return true;
    }
    
    boolean isTabGroup() {
        final Control[] tabList = this.parent._getTabList();
        if (tabList != null) {
            for (final Control element : tabList) {
                if (element == this) {
                    return true;
                }
            }
        }
        final int bits = OS.GetWindowLong(this.handle, -16);
        return (bits & 0x10000) != 0x0;
    }
    
    boolean isTabItem() {
        final Control[] tabList = this.parent._getTabList();
        if (tabList != null) {
            for (final Control element : tabList) {
                if (element == this) {
                    return false;
                }
            }
        }
        final int bits = OS.GetWindowLong(this.handle, -16);
        if ((bits & 0x10000) != 0x0) {
            return false;
        }
        final long code = OS.SendMessage(this.handle, 135, 0L, 0L);
        return (code & 0x100L) == 0x0L && (code & 0x4L) == 0x0L && (code & 0x1L) == 0x0L && (code & 0x2L) == 0x0L;
    }
    
    public boolean isVisible() {
        this.checkWidget();
        return OS.IsWindowVisible(this.handle) || (this.getVisible() && this.parent.isVisible());
    }
    
    boolean isUseWsBorder() {
        return this.display != null && this.display.useWsBorderAll;
    }
    
    @Override
    void mapEvent(final long hwnd, final Event event) {
        if (hwnd != this.handle) {
            final POINT point = new POINT();
            final Point loc = event.getLocationInPixels();
            point.x = loc.x;
            point.y = loc.y;
            OS.MapWindowPoints(hwnd, this.handle, point, 1);
            event.setLocationInPixels(point.x, point.y);
        }
    }
    
    void markLayout(final boolean changed, final boolean all) {
    }
    
    Decorations menuShell() {
        return this.parent.menuShell();
    }
    
    boolean mnemonicHit(final char key) {
        return false;
    }
    
    boolean mnemonicMatch(final char key) {
        return false;
    }
    
    public void moveAbove(final Control control) {
        this.checkWidget();
        final long topHandle = this.topHandle();
        long hwndAbove = 0L;
        if (control != null) {
            if (control.isDisposed()) {
                this.error(5);
            }
            if (this.parent != control.parent) {
                return;
            }
            final long hwnd = control.topHandle();
            if (hwnd == 0L || hwnd == topHandle) {
                return;
            }
            hwndAbove = OS.GetWindow(hwnd, 3);
            if (hwndAbove == 0L || hwndAbove == hwnd) {
                hwndAbove = 0L;
            }
        }
        final int flags = 19;
        OS.SetWindowPos(topHandle, hwndAbove, 0, 0, 0, 0, 19);
    }
    
    public void moveBelow(final Control control) {
        this.checkWidget();
        final long topHandle = this.topHandle();
        long hwndAbove = 1L;
        if (control != null) {
            if (control.isDisposed()) {
                this.error(5);
            }
            if (this.parent != control.parent) {
                return;
            }
            hwndAbove = control.topHandle();
        }
        else {
            final Shell shell = this.getShell();
            if (this == shell && this.parent != null) {
                long hwndParent;
                long hwnd;
                for (hwnd = (hwndParent = this.parent.handle), hwndAbove = OS.GetWindow(hwnd, 3); hwndAbove != 0L && hwndAbove != hwnd && OS.GetWindow(hwndAbove, 4) != hwndParent; hwndAbove = OS.GetWindow(hwnd = hwndAbove, 3)) {}
                if (hwndAbove == hwnd) {
                    return;
                }
            }
        }
        if (hwndAbove == 0L || hwndAbove == topHandle) {
            return;
        }
        final int flags = 19;
        OS.SetWindowPos(topHandle, hwndAbove, 0, 0, 0, 0, 19);
    }
    
    Accessible new_Accessible(final Control control) {
        return Accessible.internal_new_Accessible(this);
    }
    
    @Override
    GC new_GC(final GCData data) {
        return GC.win32_new((Drawable)this, data);
    }
    
    public void pack() {
        this.checkWidget();
        this.pack(true);
    }
    
    public void pack(final boolean changed) {
        this.checkWidget();
        this.setSize(this.computeSize(-1, -1, changed));
    }
    
    public boolean print(final GC gc) {
        this.checkWidget();
        if (gc == null) {
            this.error(4);
        }
        if (gc.isDisposed()) {
            this.error(5);
        }
        final long topHandle = this.topHandle();
        long hdc = gc.handle;
        int state = 0;
        final long gdipGraphics = gc.getGCData().gdipGraphics;
        if (gdipGraphics != 0L) {
            long clipRgn = 0L;
            Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, 3);
            final long rgn = Gdip.Region_new();
            if (rgn == 0L) {
                this.error(2);
            }
            Gdip.Graphics_GetClip(gdipGraphics, rgn);
            if (!Gdip.Region_IsInfinite(rgn, gdipGraphics)) {
                clipRgn = Gdip.Region_GetHRGN(rgn, gdipGraphics);
            }
            Gdip.Region_delete(rgn);
            Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, 4);
            float[] lpXform = null;
            final long matrix = Gdip.Matrix_new(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
            if (matrix == 0L) {
                this.error(2);
            }
            Gdip.Graphics_GetTransform(gdipGraphics, matrix);
            if (!Gdip.Matrix_IsIdentity(matrix)) {
                lpXform = new float[6];
                Gdip.Matrix_GetElements(matrix, lpXform);
            }
            Gdip.Matrix_delete(matrix);
            hdc = Gdip.Graphics_GetHDC(gdipGraphics);
            state = OS.SaveDC(hdc);
            if (lpXform != null) {
                OS.SetGraphicsMode(hdc, 2);
                OS.SetWorldTransform(hdc, lpXform);
            }
            if (clipRgn != 0L) {
                OS.SelectClipRgn(hdc, clipRgn);
                OS.DeleteObject(clipRgn);
            }
        }
        final int flags = 384;
        OS.RedrawWindow(topHandle, (RECT)null, 0L, 384);
        this.printWidget(topHandle, hdc, gc);
        if (gdipGraphics != 0L) {
            OS.RestoreDC(hdc, state);
            Gdip.Graphics_ReleaseHDC(gdipGraphics, hdc);
        }
        return true;
    }
    
    void printWidget(final long hwnd, final long hdc, final GC gc) {
        boolean success = false;
        if (OS.GetDeviceCaps(gc.handle, 2) != 2) {
            long hwndParent;
            long hwndShell;
            for (hwndShell = (hwndParent = OS.GetParent(hwnd)); OS.GetParent(hwndShell) != 0L && OS.GetWindow(hwndShell, 4) == 0L; hwndShell = OS.GetParent(hwndShell)) {}
            final RECT rect1 = new RECT();
            OS.GetWindowRect(hwnd, rect1);
            boolean fixPrintWindow = !OS.IsWindowVisible(hwnd);
            if (!fixPrintWindow) {
                final RECT rect2 = new RECT();
                OS.GetWindowRect(hwndShell, rect2);
                OS.IntersectRect(rect2, rect1, rect2);
                fixPrintWindow = !OS.EqualRect(rect2, rect1);
            }
            if (!fixPrintWindow) {
                final long rgn = OS.CreateRectRgn(0, 0, 0, 0);
                for (long parent = OS.GetParent(hwnd); parent != hwndShell && !fixPrintWindow; parent = OS.GetParent(parent)) {
                    if (OS.GetWindowRgn(parent, rgn) != 0) {
                        fixPrintWindow = true;
                    }
                }
                OS.DeleteObject(rgn);
            }
            final int bits1 = OS.GetWindowLong(hwnd, -16);
            final int bits2 = OS.GetWindowLong(hwnd, -20);
            long hwndInsertAfter = OS.GetWindow(hwnd, 3);
            if (hwndInsertAfter == 0L || hwndInsertAfter == hwnd) {
                hwndInsertAfter = 0L;
            }
            if (fixPrintWindow) {
                final int x = OS.GetSystemMetrics(76);
                final int y = OS.GetSystemMetrics(77);
                final int width = OS.GetSystemMetrics(78);
                final int height = OS.GetSystemMetrics(79);
                final int flags = 53;
                if ((bits1 & 0x10000000) != 0x0) {
                    OS.DefWindowProc(hwnd, 11, 0L, 0L);
                }
                OS.SetWindowPos(hwnd, 0L, x + width, y + height, 0, 0, 53);
                OS.SetWindowLong(hwnd, -16, (bits1 & 0xBFFFFFFF) | Integer.MIN_VALUE);
                OS.SetWindowLong(hwnd, -20, bits2 | 0x80);
                final Shell shell = this.getShell();
                final Control savedFocus = shell.savedFocus;
                OS.SetParent(hwnd, 0L);
                shell.setSavedFocus(savedFocus);
                if ((bits1 & 0x10000000) != 0x0) {
                    OS.DefWindowProc(hwnd, 11, 1L, 0L);
                }
            }
            if ((bits1 & 0x10000000) == 0x0) {
                OS.ShowWindow(hwnd, 5);
            }
            success = OS.PrintWindow(hwnd, hdc, 0);
            if ((bits1 & 0x10000000) == 0x0) {
                OS.ShowWindow(hwnd, 0);
            }
            if (fixPrintWindow) {
                if ((bits1 & 0x10000000) != 0x0) {
                    OS.DefWindowProc(hwnd, 11, 0L, 0L);
                }
                OS.SetWindowLong(hwnd, -16, bits1);
                OS.SetWindowLong(hwnd, -20, bits2);
                OS.SetParent(hwnd, hwndParent);
                OS.MapWindowPoints(0L, hwndParent, rect1, 2);
                final int flags2 = 49;
                OS.SetWindowPos(hwnd, hwndInsertAfter, rect1.left, rect1.top, rect1.right - rect1.left, rect1.bottom - rect1.top, 49);
                if ((bits1 & 0x10000000) != 0x0) {
                    OS.DefWindowProc(hwnd, 11, 1L, 0L);
                }
            }
        }
        if (!success) {
            final int flags3 = 30;
            OS.SendMessage(hwnd, 791, hdc, 30L);
        }
    }
    
    public void requestLayout() {
        this.getShell().layout(new Control[] { this }, 4);
    }
    
    public void redraw() {
        this.checkWidget();
        this.redrawInPixels(null, false);
    }
    
    public void redraw(int x, int y, int width, int height, final boolean all) {
        this.checkWidget();
        x = DPIUtil.autoScaleUp(x);
        y = DPIUtil.autoScaleUp(y);
        width = DPIUtil.autoScaleUp(width);
        height = DPIUtil.autoScaleUp(height);
        if (width <= 0 || height <= 0) {
            return;
        }
        final RECT rect = new RECT();
        OS.SetRect(rect, x, y, x + width, y + height);
        this.redrawInPixels(rect, all);
    }
    
    void redrawInPixels(final RECT rect, final boolean all) {
        if (!OS.IsWindowVisible(this.handle)) {
            return;
        }
        int flags = 5;
        if (all) {
            flags |= 0x80;
        }
        OS.RedrawWindow(this.handle, rect, 0L, flags);
    }
    
    boolean redrawChildren() {
        if (!OS.IsWindowVisible(this.handle)) {
            return false;
        }
        final Control control = this.findBackgroundControl();
        if (control == null) {
            if ((this.state & 0x100) != 0x0 && OS.IsAppThemed()) {
                OS.InvalidateRect(this.handle, (RECT)null, true);
                return true;
            }
        }
        else if (control.backgroundImage != null) {
            OS.InvalidateRect(this.handle, (RECT)null, true);
            return true;
        }
        return false;
    }
    
    void register() {
        this.display.addControl(this.handle, this);
    }
    
    @Override
    void releaseHandle() {
        super.releaseHandle();
        this.handle = 0L;
        this.parent = null;
    }
    
    @Override
    void releaseParent() {
        this.parent.removeControl(this);
    }
    
    @Override
    void releaseWidget() {
        super.releaseWidget();
        if (this.toolTipText != null) {
            this.setToolTipText(this.getShell(), null);
        }
        this.toolTipText = null;
        if (this.menu != null && !this.menu.isDisposed()) {
            this.menu.dispose();
        }
        this.backgroundImage = null;
        this.menu = null;
        this.cursor = null;
        this.unsubclass();
        this.deregister();
        this.layoutData = null;
        if (this.accessible != null) {
            this.accessible.internal_dispose_Accessible();
        }
        this.accessible = null;
        this.region = null;
        this.font = null;
    }
    
    public void removeControlListener(final ControlListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(10, (SWTEventListener)listener);
        this.eventTable.unhook(11, (SWTEventListener)listener);
    }
    
    public void removeDragDetectListener(final DragDetectListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(29, (SWTEventListener)listener);
    }
    
    public void removeFocusListener(final FocusListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(15, (SWTEventListener)listener);
        this.eventTable.unhook(16, (SWTEventListener)listener);
    }
    
    public void removeGestureListener(final GestureListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(48, (SWTEventListener)listener);
    }
    
    public void removeHelpListener(final HelpListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(28, (SWTEventListener)listener);
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
    
    public void removeMenuDetectListener(final MenuDetectListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(35, (SWTEventListener)listener);
    }
    
    public void removeMouseTrackListener(final MouseTrackListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(6, (SWTEventListener)listener);
        this.eventTable.unhook(7, (SWTEventListener)listener);
        this.eventTable.unhook(32, (SWTEventListener)listener);
    }
    
    public void removeMouseListener(final MouseListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(3, (SWTEventListener)listener);
        this.eventTable.unhook(4, (SWTEventListener)listener);
        this.eventTable.unhook(8, (SWTEventListener)listener);
    }
    
    public void removeMouseMoveListener(final MouseMoveListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(5, (SWTEventListener)listener);
    }
    
    public void removeMouseWheelListener(final MouseWheelListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(37, (SWTEventListener)listener);
    }
    
    public void removePaintListener(final PaintListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(9, (SWTEventListener)listener);
    }
    
    public void removeTouchListener(final TouchListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(47, (SWTEventListener)listener);
    }
    
    public void removeTraverseListener(final TraverseListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(31, (SWTEventListener)listener);
    }
    
    int resolveTextDirection() {
        return 0;
    }
    
    void showWidget(final boolean visible) {
        final long topHandle = this.topHandle();
        OS.ShowWindow(topHandle, visible ? 5 : 0);
        if (this.handle != topHandle) {
            OS.ShowWindow(this.handle, visible ? 5 : 0);
        }
    }
    
    @Override
    boolean sendFocusEvent(final int type) {
        final Shell shell = this.getShell();
        final Display display = this.display;
        display.focusEvent = type;
        (display.focusControl = this).sendEvent(type);
        display.focusEvent = 0;
        display.focusControl = null;
        if (!shell.isDisposed()) {
            switch (type) {
                case 15: {
                    shell.setActiveControl(this);
                    break;
                }
                case 16: {
                    if (shell != display.getActiveShell()) {
                        shell.setActiveControl(null);
                        break;
                    }
                    break;
                }
            }
        }
        return true;
    }
    
    boolean sendGestureEvent(final GESTUREINFO gi) {
        if (gi.hwndTarget != this.handle) {
            return true;
        }
        final Event event = new Event();
        int type = 0;
        final Point globalPt = new Point((int)gi.x, (int)gi.y);
        final Point point = this.toControlInPixels(globalPt.x, globalPt.y);
        event.setLocationInPixels(point.x, point.y);
        switch (gi.dwID) {
            case 3: {
                type = 48;
                event.detail = 32;
                final int fingerDistance = (int)gi.ullArguments;
                if ((gi.dwFlags & 0x1) != 0x0) {
                    event.detail = 2;
                    final Display display = this.display;
                    final Display display2 = this.display;
                    final double n = fingerDistance;
                    display2.lastDistance = n;
                    display.magStartDistance = n;
                }
                else if ((gi.dwFlags & 0x4) != 0x0) {
                    event.detail = 4;
                }
                if (fingerDistance == this.display.lastDistance && event.detail == 32) {
                    return true;
                }
                if (fingerDistance != 0) {
                    event.magnification = fingerDistance / this.display.magStartDistance;
                }
                this.display.lastDistance = fingerDistance;
                break;
            }
            case 4: {
                type = 48;
                event.detail = 64;
                if ((gi.dwFlags & 0x1) != 0x0) {
                    event.detail = 2;
                    this.display.lastX = point.x;
                    this.display.lastY = point.y;
                }
                else if ((gi.dwFlags & 0x4) != 0x0) {
                    event.detail = 4;
                }
                if (this.display.lastX == point.x && this.display.lastY == point.y && event.detail == 64) {
                    return true;
                }
                event.xDirection = point.x - this.display.lastX;
                event.yDirection = point.y - this.display.lastY;
                this.display.lastX = point.x;
                this.display.lastY = point.y;
                break;
            }
            case 5: {
                type = 48;
                event.detail = 8;
                final double rotationInRadians = OS.GID_ROTATE_ANGLE_FROM_ARGUMENT(gi.ullArguments);
                if ((gi.dwFlags & 0x1) != 0x0) {
                    event.detail = 2;
                    this.display.rotationAngle = rotationInRadians;
                }
                else if ((gi.dwFlags & 0x4) != 0x0) {
                    event.detail = 4;
                }
                if (this.display.rotationAngle == rotationInRadians && event.detail == 8) {
                    return true;
                }
                event.rotation = rotationInRadians * 180.0 / 3.141592653589793;
                this.display.rotationAngle = rotationInRadians;
                break;
            }
        }
        if (type == 0) {
            return true;
        }
        this.setInputState(event, type);
        this.sendEvent(type, event);
        return event.doit;
    }
    
    void sendMove() {
        this.sendEvent(10);
    }
    
    void sendResize() {
        this.sendEvent(11);
    }
    
    void sendTouchEvent(final TOUCHINPUT[] touchInput) {
        final Event event = new Event();
        final POINT pt = new POINT();
        OS.GetCursorPos(pt);
        OS.ScreenToClient(this.handle, pt);
        event.setLocationInPixels(pt.x, pt.y);
        final Touch[] touches = new Touch[touchInput.length];
        final Monitor monitor = this.getMonitor();
        for (int i = 0; i < touchInput.length; ++i) {
            final TOUCHINPUT ti = touchInput[i];
            final TouchSource inputSource = this.display.findTouchSource(ti.hSource, monitor);
            int state = 0;
            if ((ti.dwFlags & 0x2) != 0x0) {
                state = 1;
            }
            if ((ti.dwFlags & 0x4) != 0x0) {
                state = 4;
            }
            if ((ti.dwFlags & 0x1) != 0x0) {
                state = 2;
            }
            final boolean primary = (ti.dwFlags & 0x10) != 0x0;
            final int x = OS.TOUCH_COORD_TO_PIXEL(ti.x);
            final int y = OS.TOUCH_COORD_TO_PIXEL(ti.y);
            touches[i] = new Touch(ti.dwID, inputSource, state, primary, x, y);
        }
        event.touches = touches;
        this.setInputState(event, 47);
        this.postEvent(47, event);
    }
    
    void setBackground() {
        Control control = this.findBackgroundControl();
        if (control == null) {
            control = this;
        }
        if (control.backgroundImage != null) {
            final Shell shell = this.getShell();
            shell.releaseBrushes();
            this.setBackgroundImage(control.backgroundImage.handle);
        }
        else {
            this.setBackgroundPixel((control.background == -1) ? control.defaultBackground() : control.background);
        }
    }
    
    public void setBackground(final Color color) {
        this.checkWidget();
        this._setBackground(color);
        if (color != null) {
            this.updateBackgroundMode();
        }
    }
    
    private void _setBackground(final Color color) {
        int pixel = -1;
        int alpha = 255;
        if (color != null) {
            if (color.isDisposed()) {
                this.error(5);
            }
            pixel = color.handle;
            alpha = color.getAlpha();
        }
        if (pixel == this.background && alpha == this.backgroundAlpha) {
            return;
        }
        this.background = pixel;
        this.backgroundAlpha = alpha;
        this.updateBackgroundColor();
    }
    
    public void setBackgroundImage(final Image image) {
        this.checkWidget();
        if (image != null) {
            if (image.isDisposed()) {
                this.error(5);
            }
            if (image.type != 0) {
                this.error(5);
            }
        }
        if (this.backgroundImage == image && this.backgroundAlpha > 0) {
            return;
        }
        this.backgroundAlpha = 255;
        this.backgroundImage = image;
        final Shell shell = this.getShell();
        shell.releaseBrushes();
        this.updateBackgroundImage();
    }
    
    void setBackgroundImage(final long hBitmap) {
        final int flags = 1029;
        OS.RedrawWindow(this.handle, (RECT)null, 0L, 1029);
    }
    
    void setBackgroundPixel(final int pixel) {
        final int flags = 1029;
        OS.RedrawWindow(this.handle, (RECT)null, 0L, 1029);
    }
    
    public void setBounds(int x, int y, int width, int height) {
        this.checkWidget();
        x = DPIUtil.autoScaleUp(x);
        y = DPIUtil.autoScaleUp(y);
        width = DPIUtil.autoScaleUp(width);
        height = DPIUtil.autoScaleUp(height);
        this.setBoundsInPixels(x, y, width, height);
    }
    
    void setBoundsInPixels(final int x, final int y, final int width, final int height) {
        final int flags = 52;
        this.setBoundsInPixels(x, y, Math.max(0, width), Math.max(0, height), 52);
    }
    
    void setBoundsInPixels(final int x, final int y, final int width, final int height, final int flags) {
        this.setBoundsInPixels(x, y, width, height, flags, true);
    }
    
    void setBoundsInPixels(final int x, final int y, final int width, final int height, int flags, final boolean defer) {
        if (this.findImageControl() != null) {
            if (this.backgroundImage == null) {
                flags |= 0x100;
            }
        }
        else if (OS.GetWindow(this.handle, 5) == 0L && OS.IsAppThemed() && this.findThemeControl() != null) {
            flags |= 0x100;
        }
        final long topHandle = this.topHandle();
        if (defer && this.parent != null) {
            this.forceResize();
            if (this.parent.lpwp != null) {
                int index;
                WINDOWPOS[] lpwp;
                for (index = 0, lpwp = this.parent.lpwp; index < lpwp.length && lpwp[index] != null; ++index) {}
                if (index == lpwp.length) {
                    final WINDOWPOS[] newLpwp = new WINDOWPOS[lpwp.length + 4];
                    System.arraycopy(lpwp, 0, newLpwp, 0, lpwp.length);
                    final Composite parent = this.parent;
                    final WINDOWPOS[] lpwp2 = newLpwp;
                    parent.lpwp = lpwp2;
                    lpwp = lpwp2;
                }
                final WINDOWPOS wp = new WINDOWPOS();
                wp.hwnd = topHandle;
                wp.x = x;
                wp.y = y;
                wp.cx = width;
                wp.cy = height;
                wp.flags = flags;
                lpwp[index] = wp;
                return;
            }
        }
        OS.SetWindowPos(topHandle, 0L, x, y, width, height, flags);
    }
    
    public void setBounds(final Rectangle rect) {
        this.checkWidget();
        if (rect == null) {
            this.error(4);
        }
        this.setBoundsInPixels(DPIUtil.autoScaleUp(rect));
    }
    
    void setBoundsInPixels(final Rectangle rect) {
        this.setBoundsInPixels(rect.x, rect.y, rect.width, rect.height);
    }
    
    public void setCapture(final boolean capture) {
        this.checkWidget();
        if (capture) {
            OS.SetCapture(this.handle);
        }
        else if (OS.GetCapture() == this.handle) {
            OS.ReleaseCapture();
        }
    }
    
    void setCursor() {
        final long lParam = OS.MAKELPARAM(1, 512);
        OS.SendMessage(this.handle, 32, this.handle, lParam);
    }
    
    public void setCursor(final Cursor cursor) {
        this.checkWidget();
        if (cursor != null && cursor.isDisposed()) {
            this.error(5);
        }
        this.cursor = cursor;
        long hwndCursor = OS.GetCapture();
        if (hwndCursor == 0L) {
            final POINT pt = new POINT();
            if (!OS.GetCursorPos(pt)) {
                return;
            }
            long hwnd;
            for (hwnd = (hwndCursor = OS.WindowFromPoint(pt)); hwnd != 0L && hwnd != this.handle; hwnd = OS.GetParent(hwnd)) {}
            if (hwnd == 0L) {
                return;
            }
        }
        Control control = this.display.getControl(hwndCursor);
        if (control == null) {
            control = this;
        }
        control.setCursor();
    }
    
    void setDefaultFont() {
        final long hFont = this.display.getSystemFont().handle;
        OS.SendMessage(this.handle, 48, hFont, 0L);
    }
    
    public void setDragDetect(final boolean dragDetect) {
        this.checkWidget();
        if (dragDetect) {
            this.state |= 0x8000;
        }
        else {
            this.state &= 0xFFFF7FFF;
        }
        this.enableDrag(dragDetect);
    }
    
    public void setEnabled(final boolean enabled) {
        this.checkWidget();
        Control control = null;
        boolean fixFocus = false;
        if (!enabled && this.display.focusEvent != 16) {
            control = this.display.getFocusControl();
            fixFocus = this.isFocusAncestor(control);
        }
        this.enableWidget(enabled);
        if (fixFocus) {
            this.fixFocus(control);
        }
    }
    
    public boolean setFocus() {
        this.checkWidget();
        return (this.style & 0x80000) == 0x0 && this.forceFocus();
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        long hFont = 0L;
        if (font != null) {
            if (font.isDisposed()) {
                this.error(5);
            }
            hFont = font.handle;
        }
        this.font = font;
        if (hFont == 0L) {
            hFont = this.defaultFont();
        }
        OS.SendMessage(this.handle, 48, hFont, 1L);
    }
    
    public void setForeground(final Color color) {
        this.checkWidget();
        int pixel = -1;
        if (color != null) {
            if (color.isDisposed()) {
                this.error(5);
            }
            pixel = color.handle;
        }
        if (pixel == this.foreground) {
            return;
        }
        this.setForegroundPixel(this.foreground = pixel);
    }
    
    void setForegroundPixel(final int pixel) {
        OS.InvalidateRect(this.handle, (RECT)null, true);
    }
    
    public void setLayoutData(final Object layoutData) {
        this.checkWidget();
        this.layoutData = layoutData;
    }
    
    public void setLocation(int x, int y) {
        this.checkWidget();
        x = DPIUtil.autoScaleUp(x);
        y = DPIUtil.autoScaleUp(y);
        this.setLocationInPixels(x, y);
    }
    
    void setLocationInPixels(final int x, final int y) {
        final int flags = 53;
        this.setBoundsInPixels(x, y, 0, 0, 53);
    }
    
    public void setLocation(Point location) {
        this.checkWidget();
        if (location == null) {
            this.error(4);
        }
        location = DPIUtil.autoScaleUp(location);
        this.setLocationInPixels(location.x, location.y);
    }
    
    public void setMenu(final Menu menu) {
        this.checkWidget();
        if (menu != null) {
            if (menu.isDisposed()) {
                this.error(5);
            }
            if ((menu.style & 0x8) == 0x0) {
                this.error(37);
            }
            if (menu.parent != this.menuShell()) {
                this.error(32);
            }
        }
        this.menu = menu;
    }
    
    public void setOrientation(final int orientation) {
        this.checkWidget();
        final int flags = 100663296;
        if ((orientation & 0x6000000) == 0x0 || (orientation & 0x6000000) == 0x6000000) {
            return;
        }
        this.style &= 0xF7FFFFFF;
        this.style &= 0xF9FFFFFF;
        this.style |= (orientation & 0x6000000);
        this.style &= Integer.MAX_VALUE;
        this.updateOrientation();
        this.checkMirrored();
    }
    
    boolean setRadioFocus(final boolean tabbing) {
        return false;
    }
    
    boolean setRadioSelection(final boolean value) {
        return false;
    }
    
    public void setRedraw(final boolean redraw) {
        this.checkWidget();
        if (this.drawCount == 0) {
            final int bits = OS.GetWindowLong(this.handle, -16);
            if ((bits & 0x10000000) == 0x0) {
                this.state |= 0x10;
            }
        }
        if (redraw) {
            if (--this.drawCount == 0) {
                final long topHandle = this.topHandle();
                OS.SendMessage(topHandle, 11, 1L, 0L);
                if (this.handle != topHandle) {
                    OS.SendMessage(this.handle, 11, 1L, 0L);
                }
                if ((this.state & 0x10) != 0x0) {
                    this.state &= 0xFFFFFFEF;
                    OS.ShowWindow(topHandle, 0);
                    if (this.handle != topHandle) {
                        OS.ShowWindow(this.handle, 0);
                    }
                }
                else {
                    final int flags = 1157;
                    OS.RedrawWindow(topHandle, (RECT)null, 0L, 1157);
                }
            }
        }
        else if (this.drawCount++ == 0) {
            final long topHandle = this.topHandle();
            OS.SendMessage(topHandle, 11, 0L, 0L);
            if (this.handle != topHandle) {
                OS.SendMessage(this.handle, 11, 0L, 0L);
            }
        }
    }
    
    public void setRegion(final Region region) {
        this.checkWidget();
        if (region != null && region.isDisposed()) {
            this.error(5);
        }
        long hRegion = 0L;
        if (region != null) {
            hRegion = OS.CreateRectRgn(0, 0, 0, 0);
            OS.CombineRgn(hRegion, region.handle, hRegion, 2);
        }
        OS.SetWindowRgn(this.handle, hRegion, true);
        this.region = region;
    }
    
    public void setSize(int width, int height) {
        this.checkWidget();
        width = DPIUtil.autoScaleUp(width);
        height = DPIUtil.autoScaleUp(height);
        this.setSizeInPixels(width, height);
    }
    
    void setSizeInPixels(final int width, final int height) {
        final int flags = 54;
        this.setBoundsInPixels(0, 0, Math.max(0, width), Math.max(0, height), 54);
    }
    
    public void setSize(Point size) {
        this.checkWidget();
        if (size == null) {
            this.error(4);
        }
        size = DPIUtil.autoScaleUp(size);
        this.setSizeInPixels(size.x, size.y);
    }
    
    @Override
    boolean setTabItemFocus() {
        return this.isShowing() && this.forceFocus();
    }
    
    public void setTextDirection(int textDirection) {
        this.checkWidget();
        textDirection &= 0x6000000;
        this.updateTextDirection(textDirection);
        if (textDirection == 100663296) {
            this.state |= 0x400000;
        }
        else {
            this.state &= 0xFFBFFFFF;
        }
    }
    
    public void setToolTipText(final String string) {
        this.checkWidget();
        if (!Objects.equals(string, this.toolTipText)) {
            this.toolTipText = string;
            this.setToolTipText(this.getShell(), string);
        }
    }
    
    void setToolTipText(final Shell shell, final String string) {
        shell.setToolTipText(this.handle, string);
    }
    
    public void setTouchEnabled(final boolean enabled) {
        this.checkWidget();
        if (enabled) {
            OS.RegisterTouchWindow(this.handle, 0);
        }
        else {
            OS.UnregisterTouchWindow(this.handle);
        }
    }
    
    public void setVisible(final boolean visible) {
        this.checkWidget();
        if (!this.getDrawing()) {
            if ((this.state & 0x10) == 0x0 == visible) {
                return;
            }
        }
        else {
            final int bits = OS.GetWindowLong(this.handle, -16);
            if ((bits & 0x10000000) != 0x0 == visible) {
                return;
            }
        }
        if (visible) {
            this.sendEvent(22);
            if (this.isDisposed()) {
                return;
            }
        }
        Control control = null;
        boolean fixFocus = false;
        if (!visible && this.display.focusEvent != 16) {
            control = this.display.getFocusControl();
            fixFocus = this.isFocusAncestor(control);
        }
        if (!this.getDrawing()) {
            this.state = (visible ? (this.state & 0xFFFFFFEF) : (this.state | 0x10));
        }
        else {
            this.showWidget(visible);
            if (this.isDisposed()) {
                return;
            }
        }
        if (!visible) {
            this.sendEvent(23);
            if (this.isDisposed()) {
                return;
            }
        }
        if (fixFocus) {
            this.fixFocus(control);
        }
    }
    
    void sort(final int[] items) {
        final int length = items.length;
        for (int gap = length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < length; ++i) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (items[j] <= items[j + gap]) {
                        final int swap = items[j];
                        items[j] = items[j + gap];
                        items[j + gap] = swap;
                    }
                }
            }
        }
    }
    
    void subclass() {
        final long oldProc = this.windowProc();
        final long newProc = this.display.windowProc;
        if (oldProc == newProc) {
            return;
        }
        OS.SetWindowLongPtr(this.handle, -4, newProc);
    }
    
    public Point toControl(final int x, final int y) {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.toControlInPixels(DPIUtil.autoScaleUp(x), DPIUtil.autoScaleUp(y)));
    }
    
    Point toControlInPixels(final int x, final int y) {
        final POINT pt = new POINT();
        pt.x = x;
        pt.y = y;
        OS.ScreenToClient(this.handle, pt);
        return new Point(pt.x, pt.y);
    }
    
    public Point toControl(Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        point = DPIUtil.autoScaleUp(point);
        return DPIUtil.autoScaleDown(this.toControlInPixels(point.x, point.y));
    }
    
    public Point toDisplay(final int x, final int y) {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.toDisplayInPixels(DPIUtil.autoScaleUp(x), DPIUtil.autoScaleUp(y)));
    }
    
    Point toDisplayInPixels(final int x, final int y) {
        final POINT pt = new POINT();
        pt.x = x;
        pt.y = y;
        OS.ClientToScreen(this.handle, pt);
        return new Point(pt.x, pt.y);
    }
    
    public Point toDisplay(Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        point = DPIUtil.autoScaleUp(point);
        return DPIUtil.autoScaleDown(this.toDisplayInPixels(point.x, point.y));
    }
    
    long topHandle() {
        return this.handle;
    }
    
    boolean translateAccelerator(final MSG msg) {
        return this.menuShell().translateAccelerator(msg);
    }
    
    boolean translateMnemonic(final Event event, final Control control) {
        if (control == this) {
            return false;
        }
        if (!this.isVisible() || !this.isEnabled()) {
            return false;
        }
        event.doit = this.mnemonicMatch(event.character);
        return this.traverse(event);
    }
    
    boolean translateMnemonic(final MSG msg) {
        if (msg.wParam < 32L) {
            return false;
        }
        final long hwnd = msg.hwnd;
        if (OS.GetKeyState(18) >= 0) {
            final long code = OS.SendMessage(hwnd, 135, 0L, 0L);
            if ((code & 0x4L) != 0x0L) {
                return false;
            }
            if ((code & 0x2000L) == 0x0L) {
                return false;
            }
        }
        final Decorations shell = this.menuShell();
        if (shell.isVisible() && shell.isEnabled()) {
            this.display.lastAscii = (int)msg.wParam;
            final Display display = this.display;
            final Display display2 = this.display;
            final boolean b = false;
            display2.lastDead = false;
            display.lastNull = false;
            final Event event = new Event();
            event.detail = 128;
            if (this.setKeyState(event, 31, msg.wParam, msg.lParam)) {
                return this.translateMnemonic(event, null) || shell.translateMnemonic(event, this);
            }
        }
        return false;
    }
    
    boolean translateTraversal(final MSG msg) {
        final long hwnd = msg.hwnd;
        final int key = (int)msg.wParam;
        if (key == 18) {
            if ((msg.lParam & 0x40000000L) == 0x0L) {
                OS.SendMessage(hwnd, 295, 3L, 0L);
            }
            return false;
        }
        int detail = 0;
        boolean doit = true;
        boolean all = false;
        boolean lastVirtual = false;
        final int lastKey = key;
        int lastAscii = 0;
        switch (key) {
            case 27: {
                all = true;
                lastAscii = 27;
                final long code = OS.SendMessage(hwnd, 135, 0L, 0L);
                if ((code & 0x4L) != 0x0L && (code & 0x8L) == 0x0L) {
                    doit = false;
                }
                detail = 2;
                break;
            }
            case 13: {
                all = true;
                lastAscii = 13;
                final long code = OS.SendMessage(hwnd, 135, 0L, 0L);
                if ((code & 0x4L) != 0x0L) {
                    doit = false;
                }
                detail = 4;
                break;
            }
            case 9: {
                lastAscii = 9;
                final boolean next = OS.GetKeyState(16) >= 0;
                final long code2 = OS.SendMessage(hwnd, 135, 0L, 0L);
                if ((code2 & 0x6L) != 0x0L) {
                    if ((code2 & 0x8L) != 0x0L) {
                        if (next && OS.GetKeyState(17) >= 0) {
                            doit = false;
                        }
                    }
                    else {
                        doit = false;
                    }
                }
                detail = (next ? 16 : 8);
                break;
            }
            case 37:
            case 38:
            case 39:
            case 40: {
                lastVirtual = true;
                final long code = OS.SendMessage(hwnd, 135, 0L, 0L);
                if ((code & 0x1L) != 0x0L) {
                    doit = false;
                }
                boolean next2 = key == 40 || key == 39;
                if (this.parent != null && (this.parent.style & 0x8000000) != 0x0 && (key == 37 || key == 39)) {
                    next2 = !next2;
                }
                detail = (next2 ? 64 : 32);
                break;
            }
            case 33:
            case 34: {
                all = true;
                lastVirtual = true;
                if (OS.GetKeyState(17) >= 0) {
                    return false;
                }
                final long code = OS.SendMessage(hwnd, 135, 0L, 0L);
                if ((code & 0x4L) != 0x0L && (code & 0x8L) == 0x0L) {
                    doit = false;
                }
                detail = ((key == 33) ? 256 : 512);
                break;
            }
            default: {
                return false;
            }
        }
        final Event event = new Event();
        event.doit = doit;
        event.detail = detail;
        this.display.lastKey = lastKey;
        this.display.lastAscii = lastAscii;
        this.display.lastVirtual = lastVirtual;
        final Display display = this.display;
        final Display display2 = this.display;
        final boolean b = false;
        display2.lastDead = false;
        display.lastNull = false;
        if (!this.setKeyState(event, 31, msg.wParam, msg.lParam)) {
            return false;
        }
        final Shell shell = this.getShell();
        Control control = this;
        while (!control.traverse(event)) {
            if (!event.doit && control.hooks(31)) {
                return false;
            }
            if (control == shell) {
                return false;
            }
            control = (Control)control.parent;
            if (!all || control == null) {
                return false;
            }
        }
        OS.SendMessage(hwnd, 295, 3L, 0L);
        return true;
    }
    
    boolean traverse(final Event event) {
        this.sendEvent(31, event);
        if (this.isDisposed()) {
            return true;
        }
        if (!event.doit) {
            return false;
        }
        switch (event.detail) {
            case 0: {
                return true;
            }
            case 2: {
                return this.traverseEscape();
            }
            case 4: {
                return this.traverseReturn();
            }
            case 16: {
                return this.traverseGroup(true);
            }
            case 8: {
                return this.traverseGroup(false);
            }
            case 64: {
                return this.traverseItem(true);
            }
            case 32: {
                return this.traverseItem(false);
            }
            case 128: {
                return this.traverseMnemonic(event.character);
            }
            case 512: {
                return this.traversePage(true);
            }
            case 256: {
                return this.traversePage(false);
            }
            default: {
                return false;
            }
        }
    }
    
    public boolean traverse(final int traversal) {
        this.checkWidget();
        final Event event = new Event();
        event.doit = true;
        event.detail = traversal;
        return this.traverse(event);
    }
    
    public boolean traverse(final int traversal, final Event event) {
        this.checkWidget();
        if (event == null) {
            this.error(4);
        }
        return this.traverse(traversal, event.character, event.keyCode, event.keyLocation, event.stateMask, event.doit);
    }
    
    public boolean traverse(final int traversal, final KeyEvent event) {
        this.checkWidget();
        if (event == null) {
            this.error(4);
        }
        return this.traverse(traversal, event.character, event.keyCode, event.keyLocation, event.stateMask, event.doit);
    }
    
    boolean traverse(int traversal, final char character, final int keyCode, final int keyLocation, final int stateMask, boolean doit) {
        if (traversal == 0) {
            switch (keyCode) {
                case 27: {
                    traversal = 2;
                    doit = true;
                    break;
                }
                case 13: {
                    traversal = 4;
                    doit = true;
                    break;
                }
                case 16777218:
                case 16777220: {
                    traversal = 64;
                    doit = false;
                    break;
                }
                case 16777217:
                case 16777219: {
                    traversal = 32;
                    doit = false;
                    break;
                }
                case 9: {
                    traversal = (((stateMask & 0x20000) != 0x0) ? 8 : 16);
                    doit = true;
                    break;
                }
                case 16777222: {
                    if ((stateMask & 0x40000) != 0x0) {
                        traversal = 512;
                        doit = true;
                        break;
                    }
                    break;
                }
                case 16777221: {
                    if ((stateMask & 0x40000) != 0x0) {
                        traversal = 256;
                        doit = true;
                        break;
                    }
                    break;
                }
                default: {
                    if (character != '\0' && (stateMask & 0x50000) == 0x10000) {
                        traversal = 128;
                        doit = true;
                        break;
                    }
                    break;
                }
            }
        }
        final Event event = new Event();
        event.character = character;
        event.detail = traversal;
        event.doit = doit;
        event.keyCode = keyCode;
        event.keyLocation = keyLocation;
        event.stateMask = stateMask;
        final Shell shell = this.getShell();
        boolean all = false;
        switch (traversal) {
            case 2:
            case 4:
            case 256:
            case 512: {
                all = true;
            }
            case 8:
            case 16:
            case 32:
            case 64: {
                Control control = this;
                while (!control.traverse(event)) {
                    if (!event.doit && control.hooks(31)) {
                        return false;
                    }
                    if (control == shell) {
                        return false;
                    }
                    control = (Control)control.parent;
                    if (!all || control == null) {
                        return false;
                    }
                }
                OS.SendMessage(this.handle, 295, 3L, 0L);
                return true;
            }
            case 128: {
                return this.translateMnemonic(event, null) || shell.translateMnemonic(event, this);
            }
            default: {
                return false;
            }
        }
    }
    
    boolean traverseEscape() {
        return false;
    }
    
    boolean traverseGroup(final boolean next) {
        final Control root = this.computeTabRoot();
        Widget group;
        Widget[] list;
        int length;
        int index;
        for (group = this.computeTabGroup(), list = root.computeTabList(), length = list.length, index = 0; index < length && list[index] != group; ++index) {}
        if (index == length) {
            return false;
        }
        final int start = index;
        final int offset = next ? 1 : -1;
        while ((index = (index + offset + length) % length) != start) {
            final Widget widget = list[index];
            if (!widget.isDisposed() && widget.setTabGroupFocus()) {
                return true;
            }
        }
        return !group.isDisposed() && group.setTabGroupFocus();
    }
    
    boolean traverseItem(final boolean next) {
        Control[] children;
        int length;
        int index;
        for (children = this.parent._getChildren(), length = children.length, index = 0; index < length && children[index] != this; ++index) {}
        if (index == length) {
            return false;
        }
        final int start = index;
        final int offset = next ? 1 : -1;
        while ((index = (index + offset + length) % length) != start) {
            final Control child = children[index];
            if (!child.isDisposed() && child.isTabItem() && child.setTabItemFocus()) {
                return true;
            }
        }
        return false;
    }
    
    boolean traverseMnemonic(final char key) {
        if (this.mnemonicHit(key)) {
            OS.SendMessage(this.handle, 295, 3L, 0L);
            return true;
        }
        return false;
    }
    
    boolean traversePage(final boolean next) {
        return false;
    }
    
    boolean traverseReturn() {
        return false;
    }
    
    void unsubclass() {
        final long newProc = this.windowProc();
        final long oldProc = this.display.windowProc;
        if (oldProc == newProc) {
            return;
        }
        OS.SetWindowLongPtr(this.handle, -4, newProc);
    }
    
    public void update() {
        this.checkWidget();
        this.update(false);
    }
    
    void update(final boolean all) {
        int flags = 256;
        if (all) {
            flags |= 0x80;
        }
        OS.RedrawWindow(this.handle, (RECT)null, 0L, flags);
    }
    
    void updateBackgroundColor() {
        Control control = this.findBackgroundControl();
        if (control == null) {
            control = this;
        }
        this.setBackgroundPixel(control.background);
    }
    
    void updateBackgroundImage() {
        final Control control = this.findBackgroundControl();
        final Image image = (control != null) ? control.backgroundImage : this.backgroundImage;
        this.setBackgroundImage((image != null) ? image.handle : 0L);
    }
    
    void updateBackgroundMode() {
        final int oldState = this.state & 0x400;
        this.checkBackground();
        if (oldState != (this.state & 0x400)) {
            this.setBackground();
        }
    }
    
    void updateFont(final Font oldFont, final Font newFont) {
        if (this.getFont().equals((Object)oldFont)) {
            this.setFont(newFont);
        }
    }
    
    void updateLayout(final boolean resize, final boolean all) {
    }
    
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
        OS.InvalidateRect(this.handle, (RECT)null, true);
    }
    
    boolean updateTextDirection(int textDirection) {
        if (textDirection == 100663296) {
            textDirection = this.resolveTextDirection();
            this.state |= 0x400000;
        }
        else {
            this.state &= 0xFFBFFFFF;
        }
        if (textDirection == 0) {
            return false;
        }
        int bits = OS.GetWindowLong(this.handle, -20);
        final int flags = 4202496;
        boolean oldRtl = (bits & 0x402000) != 0x0 && (bits & 0x402000) != 0x402000;
        final boolean newRtl = textDirection == 67108864;
        if (newRtl == oldRtl) {
            return false;
        }
        oldRtl = ((bits & 0x400000) != 0x0);
        if (newRtl != oldRtl) {
            bits |= 0x2000;
            this.style |= Integer.MIN_VALUE;
        }
        else {
            bits &= 0xFFFFDFFF;
            this.style &= Integer.MAX_VALUE;
        }
        OS.SetWindowLong(this.handle, -20, bits);
        OS.InvalidateRect(this.handle, (RECT)null, true);
        return true;
    }
    
    CREATESTRUCT widgetCreateStruct() {
        return null;
    }
    
    int widgetExtStyle() {
        int bits = 0;
        if (!this.isUseWsBorder() && (this.style & 0x800) != 0x0) {
            bits |= 0x200;
        }
        bits |= 0x100000;
        if ((this.style & 0x4000000) != 0x0) {
            bits |= 0x400000;
        }
        if ((this.style & Integer.MIN_VALUE) != 0x0) {
            bits |= 0x2000;
        }
        return bits;
    }
    
    long widgetParent() {
        return this.parent.handle;
    }
    
    int widgetStyle() {
        int bits = 1409286144;
        if (this.isUseWsBorder() && (this.style & 0x800) != 0x0) {
            bits |= 0x800000;
        }
        return bits;
    }
    
    public boolean setParent(final Composite parent) {
        this.checkWidget();
        if (parent == null) {
            this.error(4);
        }
        if (parent.isDisposed()) {
            this.error(5);
        }
        if (this.parent == parent) {
            return true;
        }
        if (!this.isReparentable()) {
            return false;
        }
        this.releaseParent();
        final Shell newShell = parent.getShell();
        final Shell oldShell = this.getShell();
        final Decorations newDecorations = parent.menuShell();
        final Decorations oldDecorations = this.menuShell();
        if (oldShell != newShell || oldDecorations != newDecorations) {
            final Menu[] menus = oldShell.findMenus(this);
            this.fixChildren(newShell, oldShell, newDecorations, oldDecorations, menus);
        }
        final long topHandle = this.topHandle();
        if (OS.SetParent(topHandle, parent.handle) == 0L) {
            return false;
        }
        this.parent = parent;
        final int flags = 19;
        OS.SetWindowPos(topHandle, 1L, 0, 0, 0, 0, 19);
        this.reskin(1);
        return true;
    }
    
    abstract TCHAR windowClass();
    
    abstract long windowProc();
    
    long windowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        final Display display = this.display;
        LRESULT result = null;
        switch (msg) {
            case 6: {
                result = this.WM_ACTIVATE(wParam, lParam);
                break;
            }
            case 533: {
                result = this.WM_CAPTURECHANGED(wParam, lParam);
                break;
            }
            case 295: {
                result = this.WM_CHANGEUISTATE(wParam, lParam);
                break;
            }
            case 258: {
                result = this.WM_CHAR(wParam, lParam);
                break;
            }
            case 771: {
                result = this.WM_CLEAR(wParam, lParam);
                break;
            }
            case 16: {
                result = this.WM_CLOSE(wParam, lParam);
                break;
            }
            case 273: {
                result = this.WM_COMMAND(wParam, lParam);
                break;
            }
            case 123: {
                result = this.WM_CONTEXTMENU(wParam, lParam);
                break;
            }
            case 306:
            case 307:
            case 308:
            case 309:
            case 310:
            case 311:
            case 312: {
                result = this.WM_CTLCOLOR(wParam, lParam);
                break;
            }
            case 768: {
                result = this.WM_CUT(wParam, lParam);
                break;
            }
            case 2: {
                result = this.WM_DESTROY(wParam, lParam);
                break;
            }
            case 43: {
                result = this.WM_DRAWITEM(wParam, lParam);
                break;
            }
            case 22: {
                result = this.WM_ENDSESSION(wParam, lParam);
                break;
            }
            case 289: {
                result = this.WM_ENTERIDLE(wParam, lParam);
                break;
            }
            case 20: {
                result = this.WM_ERASEBKGND(wParam, lParam);
                break;
            }
            case 281: {
                result = this.WM_GESTURE(wParam, lParam);
                break;
            }
            case 135: {
                result = this.WM_GETDLGCODE(wParam, lParam);
                break;
            }
            case 49: {
                result = this.WM_GETFONT(wParam, lParam);
                break;
            }
            case 61: {
                result = this.WM_GETOBJECT(wParam, lParam);
                break;
            }
            case 36: {
                result = this.WM_GETMINMAXINFO(wParam, lParam);
                break;
            }
            case 83: {
                result = this.WM_HELP(wParam, lParam);
                break;
            }
            case 276: {
                result = this.WM_HSCROLL(wParam, lParam);
                break;
            }
            case 646: {
                result = this.WM_IME_CHAR(wParam, lParam);
                break;
            }
            case 271: {
                result = this.WM_IME_COMPOSITION(wParam, lParam);
                break;
            }
            case 269: {
                result = this.WM_IME_COMPOSITION_START(wParam, lParam);
                break;
            }
            case 270: {
                result = this.WM_IME_ENDCOMPOSITION(wParam, lParam);
                break;
            }
            case 279: {
                result = this.WM_INITMENUPOPUP(wParam, lParam);
                break;
            }
            case 81: {
                result = this.WM_INPUTLANGCHANGE(wParam, lParam);
                break;
            }
            case 786: {
                result = this.WM_HOTKEY(wParam, lParam);
                break;
            }
            case 256: {
                result = this.WM_KEYDOWN(wParam, lParam);
                break;
            }
            case 257: {
                result = this.WM_KEYUP(wParam, lParam);
                break;
            }
            case 8: {
                result = this.WM_KILLFOCUS(wParam, lParam);
                break;
            }
            case 515: {
                result = this.WM_LBUTTONDBLCLK(wParam, lParam);
                break;
            }
            case 513: {
                result = this.WM_LBUTTONDOWN(wParam, lParam);
                break;
            }
            case 514: {
                result = this.WM_LBUTTONUP(wParam, lParam);
                break;
            }
            case 521: {
                result = this.WM_MBUTTONDBLCLK(wParam, lParam);
                break;
            }
            case 519: {
                result = this.WM_MBUTTONDOWN(wParam, lParam);
                break;
            }
            case 520: {
                result = this.WM_MBUTTONUP(wParam, lParam);
                break;
            }
            case 44: {
                result = this.WM_MEASUREITEM(wParam, lParam);
                break;
            }
            case 288: {
                result = this.WM_MENUCHAR(wParam, lParam);
                break;
            }
            case 287: {
                result = this.WM_MENUSELECT(wParam, lParam);
                break;
            }
            case 33: {
                result = this.WM_MOUSEACTIVATE(wParam, lParam);
                break;
            }
            case 673: {
                result = this.WM_MOUSEHOVER(wParam, lParam);
                break;
            }
            case 675: {
                result = this.WM_MOUSELEAVE(wParam, lParam);
                break;
            }
            case 512: {
                result = this.WM_MOUSEMOVE(wParam, lParam);
                break;
            }
            case 522: {
                result = this.WM_MOUSEWHEEL(wParam, lParam);
                break;
            }
            case 526: {
                result = this.WM_MOUSEHWHEEL(wParam, lParam);
                break;
            }
            case 3: {
                result = this.WM_MOVE(wParam, lParam);
                break;
            }
            case 134: {
                result = this.WM_NCACTIVATE(wParam, lParam);
                break;
            }
            case 131: {
                result = this.WM_NCCALCSIZE(wParam, lParam);
                break;
            }
            case 132: {
                result = this.WM_NCHITTEST(wParam, lParam);
                break;
            }
            case 161: {
                result = this.WM_NCLBUTTONDOWN(wParam, lParam);
                break;
            }
            case 133: {
                result = this.WM_NCPAINT(wParam, lParam);
                break;
            }
            case 78: {
                result = this.WM_NOTIFY(wParam, lParam);
                break;
            }
            case 15: {
                result = this.WM_PAINT(wParam, lParam);
                break;
            }
            case 529: {
                result = this.WM_ENTERMENULOOP(wParam, lParam);
                break;
            }
            case 530: {
                result = this.WM_EXITMENULOOP(wParam, lParam);
                break;
            }
            case 561: {
                result = this.WM_ENTERSIZEMOVE(wParam, lParam);
                break;
            }
            case 562: {
                result = this.WM_EXITSIZEMOVE(wParam, lParam);
                break;
            }
            case 528: {
                result = this.WM_PARENTNOTIFY(wParam, lParam);
                break;
            }
            case 770: {
                result = this.WM_PASTE(wParam, lParam);
                break;
            }
            case 791: {
                result = this.WM_PRINT(wParam, lParam);
                break;
            }
            case 792: {
                result = this.WM_PRINTCLIENT(wParam, lParam);
                break;
            }
            case 17: {
                result = this.WM_QUERYENDSESSION(wParam, lParam);
                break;
            }
            case 19: {
                result = this.WM_QUERYOPEN(wParam, lParam);
                break;
            }
            case 518: {
                result = this.WM_RBUTTONDBLCLK(wParam, lParam);
                break;
            }
            case 516: {
                result = this.WM_RBUTTONDOWN(wParam, lParam);
                break;
            }
            case 517: {
                result = this.WM_RBUTTONUP(wParam, lParam);
                break;
            }
            case 32: {
                result = this.WM_SETCURSOR(wParam, lParam);
                break;
            }
            case 7: {
                result = this.WM_SETFOCUS(wParam, lParam);
                break;
            }
            case 48: {
                result = this.WM_SETFONT(wParam, lParam);
                break;
            }
            case 26: {
                result = this.WM_SETTINGCHANGE(wParam, lParam);
                break;
            }
            case 11: {
                result = this.WM_SETREDRAW(wParam, lParam);
                break;
            }
            case 24: {
                result = this.WM_SHOWWINDOW(wParam, lParam);
                break;
            }
            case 5: {
                result = this.WM_SIZE(wParam, lParam);
                break;
            }
            case 262: {
                result = this.WM_SYSCHAR(wParam, lParam);
                break;
            }
            case 21: {
                result = this.WM_SYSCOLORCHANGE(wParam, lParam);
                break;
            }
            case 274: {
                result = this.WM_SYSCOMMAND(wParam, lParam);
                break;
            }
            case 260: {
                result = this.WM_SYSKEYDOWN(wParam, lParam);
                break;
            }
            case 261: {
                result = this.WM_SYSKEYUP(wParam, lParam);
                break;
            }
            case 715: {
                result = this.WM_TABLET_FLICK(wParam, lParam);
                break;
            }
            case 275: {
                result = this.WM_TIMER(wParam, lParam);
                break;
            }
            case 576: {
                result = this.WM_TOUCH(wParam, lParam);
                break;
            }
            case 772: {
                result = this.WM_UNDO(wParam, lParam);
                break;
            }
            case 293: {
                result = this.WM_UNINITMENUPOPUP(wParam, lParam);
                break;
            }
            case 296: {
                result = this.WM_UPDATEUISTATE(wParam, lParam);
                break;
            }
            case 277: {
                result = this.WM_VSCROLL(wParam, lParam);
                break;
            }
            case 71: {
                result = this.WM_WINDOWPOSCHANGED(wParam, lParam);
                break;
            }
            case 70: {
                result = this.WM_WINDOWPOSCHANGING(wParam, lParam);
                break;
            }
            case 525: {
                result = this.WM_XBUTTONDBLCLK(wParam, lParam);
                break;
            }
            case 523: {
                result = this.WM_XBUTTONDOWN(wParam, lParam);
                break;
            }
            case 524: {
                result = this.WM_XBUTTONUP(wParam, lParam);
                break;
            }
            case 736: {
                result = this.WM_DPICHANGED(wParam, lParam);
                break;
            }
        }
        if (result != null) {
            return result.value;
        }
        display.sendPreExternalEventDispatchEvent();
        try {
            return this.callWindowProc(hwnd, msg, wParam, lParam);
        }
        finally {
            display.sendPostExternalEventDispatchEvent();
        }
    }
    
    LRESULT WM_ACTIVATE(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_CAPTURECHANGED(final long wParam, final long lParam) {
        return this.wmCaptureChanged(this.handle, wParam, lParam);
    }
    
    LRESULT WM_CHANGEUISTATE(final long wParam, final long lParam) {
        if ((this.state & 0x100000) != 0x0) {
            return LRESULT.ZERO;
        }
        return null;
    }
    
    LRESULT WM_CHAR(final long wParam, final long lParam) {
        return this.wmChar(this.handle, wParam, lParam);
    }
    
    LRESULT WM_CLEAR(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_CLOSE(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_COMMAND(final long wParam, final long lParam) {
        if (lParam == 0L) {
            final Decorations shell = this.menuShell();
            if (shell.isEnabled()) {
                final int id = OS.LOWORD(wParam);
                final MenuItem item = this.display.getMenuItem(id);
                if (item != null && item.isEnabled()) {
                    return item.wmCommandChild(wParam, lParam);
                }
            }
            return null;
        }
        final Control control = this.display.getControl(lParam);
        if (control == null) {
            return null;
        }
        return control.wmCommandChild(wParam, lParam);
    }
    
    LRESULT WM_CONTEXTMENU(final long wParam, final long lParam) {
        return this.wmContextMenu(this.handle, wParam, lParam);
    }
    
    LRESULT WM_CTLCOLOR(final long wParam, final long lParam) {
        final Control control = this.display.getControl(lParam);
        if (control == null) {
            return null;
        }
        return control.wmColorChild(wParam, lParam);
    }
    
    LRESULT WM_CUT(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_DESTROY(final long wParam, final long lParam) {
        OS.KillTimer(this.handle, 110L);
        return null;
    }
    
    LRESULT WM_DPICHANGED(final long wParam, final long lParam) {
        final int nativeZoom = DPIUtil.mapDPIToZoom(OS.HIWORD(wParam));
        final int newSWTZoom = DPIUtil.getZoomForAutoscaleProperty(nativeZoom);
        final int oldSWTZoom = DPIUtil.getDeviceZoom();
        if (newSWTZoom != oldSWTZoom) {
            final Event event = new Event();
            event.type = 55;
            event.widget = this;
            event.detail = newSWTZoom;
            event.doit = true;
            this.notifyListeners(55, event);
            return LRESULT.ZERO;
        }
        return LRESULT.ONE;
    }
    
    LRESULT WM_DRAWITEM(final long wParam, final long lParam) {
        final DRAWITEMSTRUCT struct = new DRAWITEMSTRUCT();
        OS.MoveMemory(struct, lParam, DRAWITEMSTRUCT.sizeof);
        if (struct.CtlType == 1) {
            final MenuItem item = this.display.getMenuItem(struct.itemID);
            if (item == null) {
                return null;
            }
            return item.wmDrawChild(wParam, lParam);
        }
        else {
            final Control control = this.display.getControl(struct.hwndItem);
            if (control == null) {
                return null;
            }
            return control.wmDrawChild(wParam, lParam);
        }
    }
    
    LRESULT WM_ENDSESSION(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_ENTERIDLE(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_ENTERMENULOOP(final long wParam, final long lParam) {
        this.display.externalEventLoop = true;
        return null;
    }
    
    LRESULT WM_ENTERSIZEMOVE(final long wParam, final long lParam) {
        this.display.externalEventLoop = true;
        return null;
    }
    
    LRESULT WM_ERASEBKGND(final long wParam, final long lParam) {
        if ((this.state & 0x200) != 0x0 && this.findImageControl() != null) {
            return LRESULT.ONE;
        }
        if ((this.state & 0x100) != 0x0 && OS.IsAppThemed() && this.findThemeControl() != null) {
            return LRESULT.ONE;
        }
        return null;
    }
    
    LRESULT WM_EXITMENULOOP(final long wParam, final long lParam) {
        this.display.externalEventLoop = false;
        return null;
    }
    
    LRESULT WM_EXITSIZEMOVE(final long wParam, final long lParam) {
        this.display.externalEventLoop = false;
        return null;
    }
    
    LRESULT WM_GESTURE(final long wParam, final long lParam) {
        if (this.hooks(48) || this.filters(48)) {
            final GESTUREINFO gi = new GESTUREINFO();
            gi.cbSize = GESTUREINFO.sizeof;
            if (OS.GetGestureInfo(lParam, gi) && !this.sendGestureEvent(gi)) {
                OS.CloseGestureInfoHandle(lParam);
                return LRESULT.ZERO;
            }
        }
        return null;
    }
    
    LRESULT WM_GETDLGCODE(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_GETFONT(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_GETOBJECT(final long wParam, final long lParam) {
        if (this.accessible != null) {
            final long result = this.accessible.internal_WM_GETOBJECT(wParam, lParam);
            if (result != 0L) {
                return new LRESULT(result);
            }
        }
        return null;
    }
    
    LRESULT WM_GETMINMAXINFO(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_HOTKEY(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_HELP(final long wParam, final long lParam) {
        final HELPINFO lphi = new HELPINFO();
        OS.MoveMemory(lphi, lParam, HELPINFO.sizeof);
        final Decorations shell = this.menuShell();
        if (!shell.isEnabled()) {
            return null;
        }
        if (lphi.iContextType == 2) {
            final MenuItem item = this.display.getMenuItem(lphi.iCtrlId);
            if (item != null && item.isEnabled()) {
                Widget widget = null;
                if (item.hooks(28)) {
                    widget = item;
                }
                else {
                    final Menu menu = item.parent;
                    if (menu.hooks(28)) {
                        widget = menu;
                    }
                }
                if (widget != null) {
                    final long hwndShell = shell.handle;
                    OS.SendMessage(hwndShell, 31, 0L, 0L);
                    widget.postEvent(28);
                    return LRESULT.ONE;
                }
            }
            return null;
        }
        if (this.hooks(28)) {
            this.postEvent(28);
            return LRESULT.ONE;
        }
        return null;
    }
    
    LRESULT WM_HSCROLL(final long wParam, final long lParam) {
        final Control control = this.display.getControl(lParam);
        if (control == null) {
            return null;
        }
        return control.wmScrollChild(wParam, lParam);
    }
    
    LRESULT WM_IME_CHAR(final long wParam, final long lParam) {
        return this.wmIMEChar(this.handle, wParam, lParam);
    }
    
    LRESULT WM_IME_COMPOSITION(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_IME_COMPOSITION_START(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_IME_ENDCOMPOSITION(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_UNINITMENUPOPUP(final long wParam, final long lParam) {
        final Menu hiddenMenu = this.menuShell().findMenu(wParam);
        if (hiddenMenu != null) {
            final Shell shell = this.getShell();
            hiddenMenu.sendEvent(23);
            if (hiddenMenu == shell.activeMenu) {
                shell.activeMenu = null;
            }
        }
        return null;
    }
    
    LRESULT WM_INITMENUPOPUP(final long wParam, final long lParam) {
        if (this.display.accelKeyHit) {
            return null;
        }
        final Shell shell = this.getShell();
        final Menu oldMenu = shell.activeMenu;
        Menu newMenu = null;
        if (OS.HIWORD(lParam) == 0) {
            newMenu = this.menuShell().findMenu(wParam);
            if (newMenu != null) {
                newMenu.update();
            }
        }
        Menu menu;
        for (menu = newMenu; menu != null && menu != oldMenu; menu = menu.getParentMenu()) {}
        if (menu == null) {
            menu = shell.activeMenu;
            while (menu != null) {
                menu.sendEvent(23);
                if (menu.isDisposed()) {
                    break;
                }
                Menu ancestor;
                for (menu = menu.getParentMenu(), ancestor = newMenu; ancestor != null && ancestor != menu; ancestor = ancestor.getParentMenu()) {}
                if (ancestor != null) {
                    break;
                }
            }
        }
        if (newMenu != null && newMenu.isDisposed()) {
            newMenu = null;
        }
        if ((shell.activeMenu = newMenu) != null && newMenu != oldMenu) {
            newMenu.sendEvent(22);
        }
        return null;
    }
    
    LRESULT WM_INPUTLANGCHANGE(final long wParam, final long lParam) {
        this.menuShell().destroyAccelerators();
        return null;
    }
    
    LRESULT WM_KEYDOWN(final long wParam, final long lParam) {
        return this.wmKeyDown(this.handle, wParam, lParam);
    }
    
    LRESULT WM_KEYUP(final long wParam, final long lParam) {
        return this.wmKeyUp(this.handle, wParam, lParam);
    }
    
    LRESULT WM_KILLFOCUS(final long wParam, final long lParam) {
        if (wParam == 0L) {
            this.menuShell().setSavedFocus(this);
        }
        return this.wmKillFocus(this.handle, wParam, lParam);
    }
    
    LRESULT WM_LBUTTONDBLCLK(final long wParam, final long lParam) {
        return this.wmLButtonDblClk(this.handle, wParam, lParam);
    }
    
    LRESULT WM_LBUTTONDOWN(final long wParam, final long lParam) {
        return this.wmLButtonDown(this.handle, wParam, lParam);
    }
    
    LRESULT WM_LBUTTONUP(final long wParam, final long lParam) {
        return this.wmLButtonUp(this.handle, wParam, lParam);
    }
    
    LRESULT WM_MBUTTONDBLCLK(final long wParam, final long lParam) {
        return this.wmMButtonDblClk(this.handle, wParam, lParam);
    }
    
    LRESULT WM_MBUTTONDOWN(final long wParam, final long lParam) {
        return this.wmMButtonDown(this.handle, wParam, lParam);
    }
    
    LRESULT WM_MBUTTONUP(final long wParam, final long lParam) {
        return this.wmMButtonUp(this.handle, wParam, lParam);
    }
    
    LRESULT WM_MEASUREITEM(final long wParam, final long lParam) {
        final MEASUREITEMSTRUCT struct = new MEASUREITEMSTRUCT();
        OS.MoveMemory(struct, lParam, MEASUREITEMSTRUCT.sizeof);
        if (struct.CtlType == 1) {
            final MenuItem item = this.display.getMenuItem(struct.itemID);
            if (item == null) {
                return null;
            }
            return item.wmMeasureChild(wParam, lParam);
        }
        else {
            final long hwnd = OS.GetDlgItem(this.handle, struct.CtlID);
            final Control control = this.display.getControl(hwnd);
            if (control == null) {
                return null;
            }
            return control.wmMeasureChild(wParam, lParam);
        }
    }
    
    LRESULT WM_MENUCHAR(final long wParam, final long lParam) {
        final int type = OS.HIWORD(wParam);
        if (type == 0 || type == 8192) {
            this.display.mnemonicKeyHit = false;
            return new LRESULT(OS.MAKELRESULT(0, 1));
        }
        return null;
    }
    
    LRESULT WM_MENUSELECT(final long wParam, final long lParam) {
        final int code = OS.HIWORD(wParam);
        final Shell shell = this.getShell();
        OS.KillTimer(this.handle, 110L);
        if (this.activeMenu != null) {
            this.activeMenu.hideCurrentToolTip();
        }
        if (code == 65535 && lParam == 0L) {
            for (Menu menu = shell.activeMenu; menu != null; menu = menu.getParentMenu()) {
                this.display.mnemonicKeyHit = true;
                menu.sendEvent(23);
                if (menu.isDisposed()) {
                    break;
                }
            }
            shell.activeMenu = null;
            return null;
        }
        if ((code & 0x2000) != 0x0) {
            return null;
        }
        if ((code & 0x80) != 0x0) {
            MenuItem item = null;
            final Decorations menuShell = this.menuShell();
            if ((code & 0x10) != 0x0) {
                final int index = OS.LOWORD(wParam);
                final MENUITEMINFO info = new MENUITEMINFO();
                info.cbSize = MENUITEMINFO.sizeof;
                info.fMask = 4;
                if (OS.GetMenuItemInfo(lParam, index, true, info)) {
                    final Menu newMenu = menuShell.findMenu(info.hSubMenu);
                    if (newMenu != null) {
                        item = newMenu.cascade;
                        this.activeMenu = newMenu;
                        this.activeMenu.selectedMenuItem = newMenu.cascade;
                        OS.SetTimer(this.handle, 110L, 1045, 0L);
                    }
                }
            }
            else {
                final Menu newMenu2 = menuShell.findMenu(lParam);
                if (newMenu2 != null) {
                    final int id = OS.LOWORD(wParam);
                    item = this.display.getMenuItem(id);
                }
                this.activeMenu = ((newMenu2 == null) ? this.menu : newMenu2);
                if (item != null && this.activeMenu != null) {
                    this.activeMenu.selectedMenuItem = item;
                    OS.SetTimer(this.handle, 110L, 1045, 0L);
                }
            }
            if (item != null) {
                item.sendEvent(30);
            }
        }
        return null;
    }
    
    LRESULT WM_MOUSEACTIVATE(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_MOUSEHOVER(final long wParam, final long lParam) {
        return this.wmMouseHover(this.handle, wParam, lParam);
    }
    
    LRESULT WM_MOUSELEAVE(final long wParam, final long lParam) {
        this.getShell().fixToolTip();
        return this.wmMouseLeave(this.handle, wParam, lParam);
    }
    
    LRESULT WM_MOUSEMOVE(final long wParam, final long lParam) {
        return this.wmMouseMove(this.handle, wParam, lParam);
    }
    
    LRESULT WM_MOUSEWHEEL(final long wParam, final long lParam) {
        return this.wmMouseWheel(this.handle, wParam, lParam);
    }
    
    LRESULT WM_MOUSEHWHEEL(final long wParam, final long lParam) {
        return this.wmMouseHWheel(this.handle, wParam, lParam);
    }
    
    LRESULT WM_MOVE(final long wParam, final long lParam) {
        this.state |= 0x10000;
        if (this.findImageControl() != null) {
            if (this != this.getShell()) {
                this.redrawChildren();
            }
        }
        else if ((this.state & 0x100) != 0x0 && OS.IsAppThemed() && OS.IsWindowVisible(this.handle) && this.findThemeControl() != null) {
            this.redrawChildren();
        }
        if ((this.state & 0x20000) == 0x0) {
            this.sendEvent(10);
        }
        return null;
    }
    
    LRESULT WM_NCACTIVATE(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_NCCALCSIZE(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_NCHITTEST(final long wParam, final long lParam) {
        if (!OS.IsWindowEnabled(this.handle)) {
            return null;
        }
        if (!this.isActive()) {
            return new LRESULT(-1L);
        }
        return null;
    }
    
    LRESULT WM_NCLBUTTONDOWN(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_NCPAINT(final long wParam, final long lParam) {
        return this.wmNCPaint(this.handle, wParam, lParam);
    }
    
    LRESULT WM_NOTIFY(final long wParam, final long lParam) {
        final NMHDR hdr = new NMHDR();
        OS.MoveMemory(hdr, lParam, NMHDR.sizeof);
        return this.wmNotify(hdr, wParam, lParam);
    }
    
    LRESULT WM_PAINT(final long wParam, final long lParam) {
        if ((this.state & 0x1000) != 0x0) {
            return LRESULT.ZERO;
        }
        return this.wmPaint(this.handle, wParam, lParam);
    }
    
    LRESULT WM_PARENTNOTIFY(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_PASTE(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_PRINT(final long wParam, final long lParam) {
        return this.wmPrint(this.handle, wParam, lParam);
    }
    
    LRESULT WM_PRINTCLIENT(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_QUERYENDSESSION(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_QUERYOPEN(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_RBUTTONDBLCLK(final long wParam, final long lParam) {
        return this.wmRButtonDblClk(this.handle, wParam, lParam);
    }
    
    LRESULT WM_RBUTTONDOWN(final long wParam, final long lParam) {
        return this.wmRButtonDown(this.handle, wParam, lParam);
    }
    
    LRESULT WM_RBUTTONUP(final long wParam, final long lParam) {
        return this.wmRButtonUp(this.handle, wParam, lParam);
    }
    
    LRESULT WM_SETCURSOR(final long wParam, final long lParam) {
        final int hitTest = (short)OS.LOWORD(lParam);
        if (hitTest == 1) {
            final Control control = this.display.getControl(wParam);
            if (control == null) {
                return null;
            }
            final Cursor cursor = control.findCursor();
            if (cursor != null) {
                OS.SetCursor(cursor.handle);
                return LRESULT.ONE;
            }
        }
        return null;
    }
    
    LRESULT WM_SETFOCUS(final long wParam, final long lParam) {
        return this.wmSetFocus(this.handle, wParam, lParam);
    }
    
    LRESULT WM_SETTINGCHANGE(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_SETFONT(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_SETREDRAW(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_SHOWWINDOW(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_SIZE(final long wParam, final long lParam) {
        this.state |= 0x40000;
        if ((this.state & 0x80000) == 0x0) {
            this.sendEvent(11);
        }
        return null;
    }
    
    LRESULT WM_SYSCHAR(final long wParam, final long lParam) {
        return this.wmSysChar(this.handle, wParam, lParam);
    }
    
    LRESULT WM_SYSCOLORCHANGE(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_SYSCOMMAND(final long wParam, final long lParam) {
        if ((wParam & 0xF000L) == 0x0L) {
            final Decorations shell = this.menuShell();
            if (shell.isEnabled()) {
                final MenuItem item = this.display.getMenuItem(OS.LOWORD(wParam));
                if (item != null) {
                    item.wmCommandChild(wParam, lParam);
                }
            }
            return LRESULT.ZERO;
        }
        final int cmd = (int)wParam & 0xFFF0;
        Label_0342: {
            switch (cmd) {
                case 61696: {
                    if (lParam == 0L) {
                        final Decorations shell2 = this.menuShell();
                        final Menu menu = shell2.getMenuBar();
                        if (menu != null) {
                            break Label_0342;
                        }
                        final Control control = this.display._getFocusControl();
                        if (control != null && (control.hooks(1) || control.hooks(2))) {
                            this.display.mnemonicKeyHit = false;
                            return LRESULT.ZERO;
                        }
                        break Label_0342;
                    }
                    else {
                        if (!this.hooks(1) && !this.hooks(2)) {
                            break Label_0342;
                        }
                        if (lParam == 32L) {
                            break Label_0342;
                        }
                        final Decorations shell2 = this.menuShell();
                        final Menu menu = shell2.getMenuBar();
                        if (menu == null) {
                            this.display.mnemonicKeyHit = false;
                            break Label_0342;
                        }
                        char key = (char)lParam;
                        if (key != '\0') {
                            key = Character.toUpperCase(key);
                            for (final MenuItem item2 : menu.getItems()) {
                                final String text = item2.getText();
                                final char mnemonic = this.findMnemonic(text);
                                if (text.length() > 0 && mnemonic == '\0') {
                                    final char ch = text.charAt(0);
                                    if (Character.toUpperCase(ch) == key) {
                                        this.display.mnemonicKeyHit = false;
                                        return LRESULT.ZERO;
                                    }
                                }
                            }
                        }
                        break Label_0342;
                    }
                    break;
                }
                case 61552:
                case 61568: {
                    final Decorations shell2 = this.menuShell();
                    if (!shell2.isEnabled() || !shell2.isActive()) {
                        return LRESULT.ZERO;
                    }
                    break;
                }
                case 61472: {
                    this.menuShell().saveFocus();
                    break;
                }
            }
        }
        return null;
    }
    
    LRESULT WM_SYSKEYDOWN(final long wParam, final long lParam) {
        return this.wmSysKeyDown(this.handle, wParam, lParam);
    }
    
    LRESULT WM_SYSKEYUP(final long wParam, final long lParam) {
        return this.wmSysKeyUp(this.handle, wParam, lParam);
    }
    
    LRESULT WM_TABLET_FLICK(final long wParam, final long lParam) {
        if (!this.hooks(48) && !this.filters(48)) {
            return null;
        }
        final Event event = new Event();
        final FLICK_DATA fData = new FLICK_DATA();
        final long[] source = { wParam };
        OS.MoveMemory(fData, source, OS.FLICK_DATA_sizeof());
        final FLICK_POINT fPoint = new FLICK_POINT();
        source[0] = lParam;
        OS.MoveMemory(fPoint, source, OS.FLICK_POINT_sizeof());
        switch (fData.iFlickDirection) {
            case 0: {
                event.xDirection = 1;
                event.yDirection = 0;
                break;
            }
            case 1: {
                event.xDirection = 1;
                event.yDirection = -1;
                break;
            }
            case 2: {
                event.xDirection = 0;
                event.yDirection = -1;
                break;
            }
            case 3: {
                event.xDirection = -1;
                event.yDirection = -1;
                break;
            }
            case 4: {
                event.xDirection = -1;
                event.yDirection = 0;
                break;
            }
            case 5: {
                event.xDirection = -1;
                event.yDirection = 1;
                break;
            }
            case 6: {
                event.xDirection = 0;
                event.yDirection = 1;
                break;
            }
            case 7: {
                event.xDirection = 1;
                event.yDirection = 1;
                break;
            }
        }
        event.setLocationInPixels(fPoint.x, fPoint.y);
        event.type = 48;
        event.detail = 16;
        this.setInputState(event, 48);
        this.sendEvent(48, event);
        return event.doit ? null : LRESULT.ONE;
    }
    
    LRESULT WM_TOUCH(final long wParam, final long lParam) {
        LRESULT result = null;
        if (this.hooks(47) || this.filters(47)) {
            final int cInputs = OS.LOWORD(wParam);
            final long hHeap = OS.GetProcessHeap();
            final long pInputs = OS.HeapAlloc(hHeap, 8, cInputs * TOUCHINPUT.sizeof);
            if (pInputs != 0L) {
                if (OS.GetTouchInputInfo(lParam, cInputs, pInputs, TOUCHINPUT.sizeof)) {
                    final TOUCHINPUT[] ti = new TOUCHINPUT[cInputs];
                    for (int i = 0; i < cInputs; ++i) {
                        OS.MoveMemory(ti[i] = new TOUCHINPUT(), pInputs + i * TOUCHINPUT.sizeof, TOUCHINPUT.sizeof);
                    }
                    this.sendTouchEvent(ti);
                    OS.CloseTouchInputHandle(lParam);
                    result = LRESULT.ZERO;
                }
                OS.HeapFree(hHeap, 0, pInputs);
            }
        }
        return result;
    }
    
    LRESULT WM_TIMER(final long wParam, final long lParam) {
        if (wParam == 110L && this.activeMenu != null) {
            OS.KillTimer(this.handle, 110L);
            this.activeMenu.wmTimer(wParam, lParam);
        }
        return null;
    }
    
    LRESULT WM_UNDO(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_UPDATEUISTATE(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT WM_VSCROLL(final long wParam, final long lParam) {
        final Control control = this.display.getControl(lParam);
        if (control == null) {
            return null;
        }
        return control.wmScrollChild(wParam, lParam);
    }
    
    LRESULT WM_WINDOWPOSCHANGED(final long wParam, final long lParam) {
        try {
            final Display display3;
            final Display display = display3 = this.display;
            ++display3.resizeCount;
            final long code = this.callWindowProc(this.handle, 71, wParam, lParam);
            return (code == 0L) ? LRESULT.ZERO : new LRESULT(code);
        }
        finally {
            final Display display4;
            final Display display2 = display4 = this.display;
            --display4.resizeCount;
        }
    }
    
    LRESULT WM_WINDOWPOSCHANGING(final long wParam, final long lParam) {
        if (!this.getDrawing()) {
            final Shell shell = this.getShell();
            if (shell != this) {
                final WINDOWPOS lpwp = new WINDOWPOS();
                OS.MoveMemory(lpwp, lParam, WINDOWPOS.sizeof);
                if ((lpwp.flags & 0x2) == 0x0 || (lpwp.flags & 0x1) == 0x0) {
                    final RECT rect = new RECT();
                    OS.GetWindowRect(this.topHandle(), rect);
                    final int width = rect.right - rect.left;
                    final int height = rect.bottom - rect.top;
                    if (width != 0 && height != 0) {
                        final long hwndParent = (this.parent == null) ? 0L : this.parent.handle;
                        OS.MapWindowPoints(0L, hwndParent, rect, 2);
                        final long rgn1 = OS.CreateRectRgn(rect.left, rect.top, rect.right, rect.bottom);
                        final long rgn2 = OS.CreateRectRgn(lpwp.x, lpwp.y, lpwp.x + lpwp.cx, lpwp.y + lpwp.cy);
                        OS.CombineRgn(rgn1, rgn1, rgn2, 4);
                        final int flags = 1157;
                        OS.RedrawWindow(hwndParent, (RECT)null, rgn1, 1157);
                        OS.DeleteObject(rgn1);
                        OS.DeleteObject(rgn2);
                    }
                }
            }
        }
        return null;
    }
    
    LRESULT WM_XBUTTONDBLCLK(final long wParam, final long lParam) {
        return this.wmXButtonDblClk(this.handle, wParam, lParam);
    }
    
    LRESULT WM_XBUTTONDOWN(final long wParam, final long lParam) {
        return this.wmXButtonDown(this.handle, wParam, lParam);
    }
    
    LRESULT WM_XBUTTONUP(final long wParam, final long lParam) {
        return this.wmXButtonUp(this.handle, wParam, lParam);
    }
    
    LRESULT wmColorChild(final long wParam, final long lParam) {
        Control control = this.findBackgroundControl();
        if (control == null) {
            if ((this.state & 0x100) != 0x0 && OS.IsAppThemed()) {
                control = this.findThemeControl();
                if (control != null) {
                    final RECT rect = new RECT();
                    OS.GetClientRect(this.handle, rect);
                    OS.SetTextColor(wParam, this.getForegroundPixel());
                    OS.SetBkColor(wParam, this.getBackgroundPixel());
                    this.fillThemeBackground(wParam, control, rect);
                    OS.SetBkMode(wParam, 1);
                    return new LRESULT(OS.GetStockObject(5));
                }
            }
            if (this.foreground == -1) {
                return null;
            }
        }
        if (control == null) {
            control = this;
        }
        final int forePixel = this.getForegroundPixel();
        final int backPixel = control.getBackgroundPixel();
        OS.SetTextColor(wParam, forePixel);
        OS.SetBkColor(wParam, backPixel);
        if (control.backgroundImage != null) {
            final RECT rect2 = new RECT();
            OS.GetClientRect(this.handle, rect2);
            final long hwnd = control.handle;
            final long hBitmap = control.backgroundImage.handle;
            OS.MapWindowPoints(this.handle, hwnd, rect2, 2);
            final POINT lpPoint = new POINT();
            OS.GetWindowOrgEx(wParam, lpPoint);
            OS.SetBrushOrgEx(wParam, -rect2.left - lpPoint.x, -rect2.top - lpPoint.y, lpPoint);
            final long hBrush = this.findBrush(hBitmap, 3);
            if ((this.state & 0x200) != 0x0) {
                final long hOldBrush = OS.SelectObject(wParam, hBrush);
                OS.MapWindowPoints(hwnd, this.handle, rect2, 2);
                OS.PatBlt(wParam, rect2.left, rect2.top, rect2.right - rect2.left, rect2.bottom - rect2.top, 15728673);
                OS.SelectObject(wParam, hOldBrush);
            }
            OS.SetBkMode(wParam, 1);
            return new LRESULT(hBrush);
        }
        final long hBrush2 = this.findBrush(backPixel, 0);
        if ((this.state & 0x200) != 0x0) {
            final RECT rect3 = new RECT();
            OS.GetClientRect(this.handle, rect3);
            final long hOldBrush2 = OS.SelectObject(wParam, hBrush2);
            OS.PatBlt(wParam, rect3.left, rect3.top, rect3.right - rect3.left, rect3.bottom - rect3.top, 15728673);
            OS.SelectObject(wParam, hOldBrush2);
        }
        return new LRESULT(hBrush2);
    }
    
    LRESULT wmCommandChild(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT wmDrawChild(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT wmMeasureChild(final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT wmNotify(final NMHDR hdr, final long wParam, final long lParam) {
        final Control control = this.display.getControl(hdr.hwndFrom);
        if (control == null) {
            return null;
        }
        return control.wmNotifyChild(hdr, wParam, lParam);
    }
    
    LRESULT wmNotifyChild(final NMHDR hdr, final long wParam, final long lParam) {
        return null;
    }
    
    LRESULT wmScrollChild(final long wParam, final long lParam) {
        return null;
    }
}
