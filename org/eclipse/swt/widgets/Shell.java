//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class Shell extends Decorations
{
    Menu activeMenu;
    ToolTip[] toolTips;
    long hwndMDIClient;
    long lpstrTip;
    long toolTipHandle;
    long balloonTipHandle;
    long menuItemToolTipHandle;
    int minWidth;
    int minHeight;
    int maxWidth;
    int maxHeight;
    long[] brushes;
    boolean showWithParent;
    boolean fullScreen;
    boolean wasMaximized;
    boolean modified;
    boolean center;
    String toolTitle;
    String balloonTitle;
    long toolIcon;
    long balloonIcon;
    long windowProc;
    Control lastActive;
    static long ToolTipProc;
    static final long DialogProc;
    static final TCHAR DialogClass;
    static final int[] SYSTEM_COLORS;
    static final int BRUSHES_SIZE = 32;
    
    public Shell() {
        this((Display)null);
    }
    
    public Shell(final int style) {
        this((Display)null, style);
    }
    
    public Shell(final Display display) {
        this(display, 1264);
    }
    
    public Shell(final Display display, final int style) {
        this(display, null, style, 0L, false);
    }
    
    Shell(Display display, final Shell parent, final int style, final long handle, final boolean embedded) {
        this.minWidth = -1;
        this.minHeight = -1;
        this.maxWidth = -1;
        this.maxHeight = -1;
        this.checkSubclass();
        if (display == null) {
            display = Display.getCurrent();
        }
        if (display == null) {
            display = Display.getDefault();
        }
        if (!display.isValidThread()) {
            this.error(22);
        }
        if (parent != null && parent.isDisposed()) {
            this.error(5);
        }
        this.center = (parent != null && (style & 0x10000000) != 0x0);
        this.style = checkStyle(parent, style);
        this.parent = (Composite)parent;
        this.display = display;
        this.handle = handle;
        if (handle != 0L && !embedded) {
            this.state |= 0x4000;
        }
        this.reskinWidget();
        this.createWidget();
    }
    
    public Shell(final Shell parent) {
        this(parent, 2144);
    }
    
    public Shell(final Shell parent, final int style) {
        this((parent != null) ? parent.display : null, parent, style, 0L, false);
    }
    
    public static Shell win32_new(final Display display, final long handle) {
        return new Shell(display, null, 8, handle, true);
    }
    
    public static Shell internal_new(final Display display, final long handle) {
        return new Shell(display, null, 8, handle, false);
    }
    
    static int checkStyle(final Shell parent, int style) {
        style = Decorations.checkStyle(style);
        style &= 0xBFFFFFFF;
        final int mask = 229376;
        if ((style & 0x10000000) != 0x0) {
            style &= 0xEFFFFFFF;
            style |= ((parent == null) ? 1264 : 2144);
            if ((style & 0x38000) == 0x0) {
                style |= ((parent == null) ? 65536 : 32768);
            }
        }
        final int bits = style & 0xFFFC7FFF;
        if ((style & 0x20000) != 0x0) {
            return bits | 0x20000;
        }
        if ((style & 0x10000) != 0x0) {
            return bits | 0x10000;
        }
        if ((style & 0x8000) != 0x0) {
            return bits | 0x8000;
        }
        return bits;
    }
    
    public void addShellListener(final ShellListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(21, (Listener)typedListener);
        this.addListener(19, (Listener)typedListener);
        this.addListener(20, (Listener)typedListener);
        this.addListener(26, (Listener)typedListener);
        this.addListener(27, (Listener)typedListener);
    }
    
    long balloonTipHandle() {
        if (this.balloonTipHandle == 0L) {
            this.createBalloonTipHandle();
        }
        return this.balloonTipHandle;
    }
    
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        if (hwnd == this.toolTipHandle || hwnd == this.balloonTipHandle || hwnd == this.menuItemToolTipHandle) {
            return OS.CallWindowProc(Shell.ToolTipProc, hwnd, msg, wParam, lParam);
        }
        if (this.hwndMDIClient != 0L) {
            return OS.DefFrameProc(hwnd, this.hwndMDIClient, msg, wParam, lParam);
        }
        if (this.windowProc != 0L) {
            return OS.CallWindowProc(this.windowProc, hwnd, msg, wParam, lParam);
        }
        if ((this.style & 0x4) != 0x0) {
            final int trim = 3312;
            if ((this.style & 0xCF0) == 0x0) {
                return OS.DefWindowProc(hwnd, msg, wParam, lParam);
            }
        }
        if ((this.style & 0x800000) != 0x0) {
            this.setItemEnabled(61456, false);
        }
        if (this.parent == null) {
            return OS.DefWindowProc(hwnd, msg, wParam, lParam);
        }
        switch (msg) {
            case 7:
            case 8: {
                return OS.DefWindowProc(hwnd, msg, wParam, lParam);
            }
            default: {
                return OS.CallWindowProc(Shell.DialogProc, hwnd, msg, wParam, lParam);
            }
        }
    }
    
    void center() {
        if (this.parent == null) {
            return;
        }
        final Rectangle rect = this.getBoundsInPixels();
        final Rectangle parentRect = this.display.mapInPixels((Control)this.parent, (Control)null, this.parent.getClientAreaInPixels());
        int x = Math.max(parentRect.x, parentRect.x + (parentRect.width - rect.width) / 2);
        int y = Math.max(parentRect.y, parentRect.y + (parentRect.height - rect.height) / 2);
        final Rectangle monitorRect = this.parent.getMonitor().getClientArea();
        if (x + rect.width > monitorRect.x + monitorRect.width) {
            x = Math.max(monitorRect.x, monitorRect.x + monitorRect.width - rect.width);
        }
        else {
            x = Math.max(x, monitorRect.x);
        }
        if (y + rect.height > monitorRect.y + monitorRect.height) {
            y = Math.max(monitorRect.y, monitorRect.y + monitorRect.height - rect.height);
        }
        else {
            y = Math.max(y, monitorRect.y);
        }
        this.setLocationInPixels(x, y);
    }
    
    public void close() {
        this.checkWidget();
        this.closeWidget();
    }
    
    void createBalloonTipHandle() {
        this.balloonTipHandle = OS.CreateWindowEx(0, new TCHAR(0, "tooltips_class32", true), (TCHAR)null, 67, Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0, this.handle, 0L, OS.GetModuleHandle((char[])null), (CREATESTRUCT)null);
        if (this.balloonTipHandle == 0L) {
            this.error(2);
        }
        if (Shell.ToolTipProc == 0L) {
            Shell.ToolTipProc = OS.GetWindowLongPtr(this.balloonTipHandle, -4);
        }
        OS.SendMessage(this.balloonTipHandle, 1048, 0L, 32767L);
        this.display.addControl(this.balloonTipHandle, (Control)this);
        OS.SetWindowLongPtr(this.balloonTipHandle, -4, this.display.windowProc);
    }
    
    void setTitleColoring() {
        int attributeID = 0;
        if (OS.WIN32_BUILD >= 19041) {
            attributeID = 20;
        }
        else {
            if (OS.WIN32_BUILD < 17763) {
                return;
            }
            attributeID = 19;
        }
        final int[] value = { 1 };
        OS.DwmSetWindowAttribute(this.handle, attributeID, value, 4);
    }
    
    void createHandle() {
        final boolean embedded = this.handle != 0L && (this.state & 0x4000) == 0x0;
        if (this.handle == 0L || embedded) {
            super.createHandle();
        }
        else {
            this.state |= 0x2;
            if ((this.style & 0x300) == 0x0) {
                this.state |= 0x100;
            }
            this.windowProc = OS.GetWindowLongPtr(this.handle, -4);
        }
        if (!embedded) {
            if (this.display.useShellTitleColoring) {
                this.setTitleColoring();
            }
            int bits = OS.GetWindowLong(this.handle, -16);
            bits &= 0xFF3FFFFF;
            bits |= Integer.MIN_VALUE;
            if ((this.style & 0x20) != 0x0) {
                bits |= 0xC00000;
            }
            if ((this.style & 0x8) == 0x0 && (this.style & 0x810) == 0x0) {
                bits |= 0x800000;
            }
            OS.SetWindowLong(this.handle, -16, bits);
            final int flags = 55;
            OS.SetWindowPos(this.handle, 0L, 0, 0, 0, 0, 55);
        }
    }
    
    void createMenuItemToolTipHandle() {
        this.menuItemToolTipHandle = this.createToolTipHandle(0L);
    }
    
    void createToolTip(final ToolTip toolTip) {
        int id = 0;
        if (this.toolTips == null) {
            this.toolTips = new ToolTip[4];
        }
        while (id < this.toolTips.length && this.toolTips[id] != null) {
            ++id;
        }
        if (id == this.toolTips.length) {
            final ToolTip[] newToolTips = new ToolTip[this.toolTips.length + 4];
            System.arraycopy(this.toolTips, 0, newToolTips, 0, this.toolTips.length);
            this.toolTips = newToolTips;
        }
        this.toolTips[id] = toolTip;
        toolTip.id = id + 108;
        final TOOLINFO lpti = new TOOLINFO();
        lpti.cbSize = TOOLINFO.sizeof;
        lpti.hwnd = this.handle;
        lpti.uId = toolTip.id;
        lpti.uFlags = 32;
        lpti.lpszText = -1L;
        OS.SendMessage(toolTip.hwndToolTip(), 1074, 0L, lpti);
    }
    
    void createToolTipHandle() {
        this.toolTipHandle = this.createToolTipHandle(this.handle);
    }
    
    long createToolTipHandle(final long parent) {
        final long toolTipHandle = OS.CreateWindowEx(0, new TCHAR(0, "tooltips_class32", true), (TCHAR)null, 3, Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0, parent, 0L, OS.GetModuleHandle((char[])null), (CREATESTRUCT)null);
        if (toolTipHandle == 0L) {
            this.error(2);
        }
        if (Shell.ToolTipProc == 0L) {
            Shell.ToolTipProc = OS.GetWindowLongPtr(toolTipHandle, -4);
        }
        OS.SendMessage(toolTipHandle, 1048, 0L, 32767L);
        this.display.addControl(toolTipHandle, (Control)this);
        OS.SetWindowLongPtr(toolTipHandle, -4, this.display.windowProc);
        return toolTipHandle;
    }
    
    void deregister() {
        super.deregister();
        if (this.toolTipHandle != 0L) {
            this.display.removeControl(this.toolTipHandle);
        }
        if (this.balloonTipHandle != 0L) {
            this.display.removeControl(this.balloonTipHandle);
        }
        if (this.menuItemToolTipHandle != 0L) {
            this.display.removeControl(this.menuItemToolTipHandle);
        }
    }
    
    void destroyToolTip(final ToolTip toolTip) {
        if (this.toolTips == null) {
            return;
        }
        this.toolTips[toolTip.id - 108] = null;
        if (this.balloonTipHandle != 0L) {
            final TOOLINFO lpti = new TOOLINFO();
            lpti.cbSize = TOOLINFO.sizeof;
            lpti.uId = toolTip.id;
            lpti.hwnd = this.handle;
            OS.SendMessage(this.balloonTipHandle, 1075, 0L, lpti);
        }
        toolTip.id = -1;
    }
    
    void destroyWidget() {
        this.fixActiveShell();
        super.destroyWidget();
    }
    
    void enableWidget(final boolean enabled) {
        if (enabled) {
            this.state &= 0xFFFFFFF7;
        }
        else {
            this.state |= 0x8;
        }
        if (Display.TrimEnabled) {
            if (this.isActive()) {
                this.setItemEnabled(61536, enabled);
            }
        }
        else {
            OS.EnableWindow(this.handle, enabled);
        }
    }
    
    long findBrush(final long value, final int lbStyle) {
        if (lbStyle == 0) {
            for (final int element : Shell.SYSTEM_COLORS) {
                if (value == OS.GetSysColor(element)) {
                    return OS.GetSysColorBrush(element);
                }
            }
        }
        if (this.brushes == null) {
            this.brushes = new long[32];
        }
        final LOGBRUSH logBrush = new LOGBRUSH();
        for (final long hBrush : this.brushes) {
            if (hBrush == 0L) {
                break;
            }
            OS.GetObject(hBrush, LOGBRUSH.sizeof, logBrush);
            switch (logBrush.lbStyle) {
                case 0: {
                    if (lbStyle == 0 && logBrush.lbColor == value) {
                        return hBrush;
                    }
                    break;
                }
                case 3: {
                    if (lbStyle == 3 && logBrush.lbHatch == value) {
                        return hBrush;
                    }
                    break;
                }
            }
        }
        int length = this.brushes.length;
        long hBrush2 = this.brushes[--length];
        if (hBrush2 != 0L) {
            OS.DeleteObject(hBrush2);
        }
        System.arraycopy(this.brushes, 0, this.brushes, 1, length);
        switch (lbStyle) {
            case 0: {
                hBrush2 = OS.CreateSolidBrush((int)value);
                break;
            }
            case 3: {
                hBrush2 = OS.CreatePatternBrush(value);
                break;
            }
        }
        return this.brushes[0] = hBrush2;
    }
    
    Control findBackgroundControl() {
        return (Control)((this.background != -1 || this.backgroundImage != null) ? this : null);
    }
    
    Cursor findCursor() {
        return this.cursor;
    }
    
    Control findThemeControl() {
        return null;
    }
    
    ToolTip findToolTip(int id) {
        if (this.toolTips == null) {
            return null;
        }
        id -= 108;
        return (0 <= id && id < this.toolTips.length) ? this.toolTips[id] : null;
    }
    
    void fixActiveShell() {
        final long hwndParent = OS.GetParent(this.handle);
        if (hwndParent != 0L && this.handle == OS.GetActiveWindow() && !OS.IsWindowEnabled(hwndParent) && OS.IsWindowVisible(hwndParent)) {
            OS.SetActiveWindow(hwndParent);
        }
    }
    
    void fixShell(final Shell newShell, final Control control) {
        if (this == newShell) {
            return;
        }
        if (control == this.lastActive) {
            this.setActiveControl(null);
        }
        final String toolTipText = control.toolTipText;
        if (toolTipText != null) {
            control.setToolTipText(this, (String)null);
            control.setToolTipText(newShell, toolTipText);
        }
    }
    
    void fixToolTip() {
        if (this.toolTipHandle == 0L) {
            return;
        }
        final TOOLINFO lpti = new TOOLINFO();
        lpti.cbSize = TOOLINFO.sizeof;
        if (OS.SendMessage(this.toolTipHandle, 1083, 0L, lpti) != 0L && (lpti.uFlags & 0x1) != 0x0) {
            OS.SendMessage(this.toolTipHandle, 1075, 0L, lpti);
            OS.SendMessage(this.toolTipHandle, 1074, 0L, lpti);
        }
        final TOOLINFO lptiMt = new TOOLINFO();
        lptiMt.cbSize = TOOLINFO.sizeof;
        if (OS.SendMessage(this.menuItemToolTipHandle, 1083, 0L, lptiMt) != 0L && (lptiMt.uFlags & 0x1) != 0x0) {
            OS.SendMessage(this.menuItemToolTipHandle, 1075, 0L, lptiMt);
            OS.SendMessage(this.menuItemToolTipHandle, 1074, 0L, lptiMt);
        }
    }
    
    public void forceActive() {
        this.checkWidget();
        if (!this.isVisible()) {
            return;
        }
        OS.SetForegroundWindow(this.handle);
    }
    
    void forceResize() {
    }
    
    public int getAlpha() {
        this.checkWidget();
        final byte[] pbAlpha = { 0 };
        if (OS.GetLayeredWindowAttributes(this.handle, (int[])null, pbAlpha, (int[])null)) {
            return pbAlpha[0] & 0xFF;
        }
        return 255;
    }
    
    Rectangle getBoundsInPixels() {
        if (OS.IsIconic(this.handle)) {
            return super.getBoundsInPixels();
        }
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        return new Rectangle(rect.left, rect.top, width, height);
    }
    
    ToolTip getCurrentToolTip() {
        if (this.toolTipHandle != 0L) {
            final ToolTip tip = this.getCurrentToolTip(this.toolTipHandle);
            if (tip != null) {
                return tip;
            }
        }
        if (this.balloonTipHandle != 0L) {
            final ToolTip tip = this.getCurrentToolTip(this.balloonTipHandle);
            if (tip != null) {
                return tip;
            }
        }
        if (this.menuItemToolTipHandle != 0L) {
            final ToolTip tip = this.getCurrentToolTip(this.menuItemToolTipHandle);
            if (tip != null) {
                return tip;
            }
        }
        return null;
    }
    
    ToolTip getCurrentToolTip(final long hwndToolTip) {
        if (hwndToolTip == 0L) {
            return null;
        }
        if (OS.SendMessage(hwndToolTip, 1083, 0L, 0L) != 0L) {
            final TOOLINFO lpti = new TOOLINFO();
            lpti.cbSize = TOOLINFO.sizeof;
            if (OS.SendMessage(hwndToolTip, 1083, 0L, lpti) != 0L && (lpti.uFlags & 0x1) == 0x0) {
                return this.findToolTip((int)lpti.uId);
            }
        }
        return null;
    }
    
    public boolean getEnabled() {
        this.checkWidget();
        return (this.state & 0x8) == 0x0;
    }
    
    public boolean getFullScreen() {
        this.checkWidget();
        return this.fullScreen;
    }
    
    public int getImeInputMode() {
        this.checkWidget();
        if (!OS.IsDBLocale) {
            return 0;
        }
        final long hIMC = OS.ImmGetContext(this.handle);
        final int[] lpfdwConversion = { 0 };
        final int[] lpfdwSentence = { 0 };
        boolean open = OS.ImmGetOpenStatus(hIMC);
        if (open) {
            open = OS.ImmGetConversionStatus(hIMC, lpfdwConversion, lpfdwSentence);
        }
        OS.ImmReleaseContext(this.handle, hIMC);
        if (!open) {
            return 0;
        }
        int result = 0;
        if ((lpfdwConversion[0] & 0x10) != 0x0) {
            result |= 0x20;
        }
        if ((lpfdwConversion[0] & 0x8) != 0x0) {
            result |= 0x2;
        }
        if ((lpfdwConversion[0] & 0x2) != 0x0) {
            return result | 0x10;
        }
        if ((lpfdwConversion[0] & 0x1) != 0x0) {
            return result | 0x8;
        }
        return result | 0x4;
    }
    
    Point getLocationInPixels() {
        if (OS.IsIconic(this.handle)) {
            return super.getLocationInPixels();
        }
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        return new Point(rect.left, rect.top);
    }
    
    public boolean getMaximized() {
        this.checkWidget();
        return !this.fullScreen && super.getMaximized();
    }
    
    public Point getMaximumSize() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getMaximumSizeInPixels());
    }
    
    Point getMaximumSizeInPixels() {
        int width = Math.min(Integer.MAX_VALUE, this.maxWidth);
        final int trim = 1248;
        if ((this.style & 0x8) == 0x0 && (this.style & 0x4E0) != 0x0) {
            width = Math.min(width, OS.GetSystemMetrics(59));
        }
        int height = Math.min(Integer.MAX_VALUE, this.maxHeight);
        if ((this.style & 0x8) == 0x0 && (this.style & 0x4E0) != 0x0) {
            if ((this.style & 0x10) != 0x0) {
                height = Math.min(height, OS.GetSystemMetrics(60));
            }
            else {
                final RECT rect = new RECT();
                final int bits1 = OS.GetWindowLong(this.handle, -16);
                final int bits2 = OS.GetWindowLong(this.handle, -20);
                OS.AdjustWindowRectEx(rect, bits1, false, bits2);
                height = Math.min(height, rect.bottom - rect.top);
            }
        }
        return new Point(width, height);
    }
    
    public Point getMinimumSize() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getMinimumSizeInPixels());
    }
    
    Point getMinimumSizeInPixels() {
        int width = Math.max(0, this.minWidth);
        final int trim = 1248;
        if ((this.style & 0x8) == 0x0 && (this.style & 0x4E0) != 0x0) {
            width = Math.max(width, OS.GetSystemMetrics(34));
        }
        int height = Math.max(0, this.minHeight);
        if ((this.style & 0x8) == 0x0 && (this.style & 0x4E0) != 0x0) {
            if ((this.style & 0x10) != 0x0) {
                height = Math.max(height, OS.GetSystemMetrics(35));
            }
            else {
                final RECT rect = new RECT();
                final int bits1 = OS.GetWindowLong(this.handle, -16);
                final int bits2 = OS.GetWindowLong(this.handle, -20);
                OS.AdjustWindowRectEx(rect, bits1, false, bits2);
                height = Math.max(height, rect.bottom - rect.top);
            }
        }
        return new Point(width, height);
    }
    
    public boolean getModified() {
        this.checkWidget();
        return this.modified;
    }
    
    public Region getRegion() {
        this.checkWidget();
        return this.region;
    }
    
    public Shell getShell() {
        this.checkWidget();
        return this;
    }
    
    Point getSizeInPixels() {
        if (OS.IsIconic(this.handle)) {
            return super.getSizeInPixels();
        }
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        return new Point(width, height);
    }
    
    public Shell[] getShells() {
        this.checkWidget();
        int count = 0;
        final Shell[] shells3;
        final Shell[] array;
        final Shell[] shells2 = array = (shells3 = this.display.getShells());
        for (Control shell : array) {
            do {
                shell = (Control)shell.getParent();
            } while (shell != null && shell != this);
            if (shell == this) {
                ++count;
            }
        }
        int index = 0;
        final Shell[] result = new Shell[count];
        for (Control shell2 : shells3) {
            final Shell activeshell2 = (Shell)shell2;
            do {
                shell2 = (Control)shell2.getParent();
            } while (shell2 != null && shell2 != this);
            if (shell2 == this) {
                result[index++] = activeshell2;
            }
        }
        return result;
    }
    
    public ToolBar getToolBar() {
        this.checkWidget();
        return null;
    }
    
    Composite findDeferredControl() {
        return (Composite)((this.layoutCount > 0) ? this : null);
    }
    
    public boolean isEnabled() {
        this.checkWidget();
        return this.getEnabled();
    }
    
    public boolean isVisible() {
        this.checkWidget();
        return this.getVisible();
    }
    
    long hwndMDIClient() {
        if (this.hwndMDIClient == 0L) {
            final int widgetStyle = 1174405121;
            this.hwndMDIClient = OS.CreateWindowEx(0, new TCHAR(0, "MDICLIENT", true), (TCHAR)null, 1174405121, 0, 0, 0, 0, this.handle, 0L, OS.GetModuleHandle((char[])null), new CREATESTRUCT());
        }
        return this.hwndMDIClient;
    }
    
    long menuItemToolTipHandle() {
        if (this.menuItemToolTipHandle == 0L) {
            this.createMenuItemToolTipHandle();
        }
        return this.menuItemToolTipHandle;
    }
    
    public void open() {
        this.checkWidget();
        final STARTUPINFO lpStartUpInfo = Display.lpStartupInfo;
        if (lpStartUpInfo == null || (lpStartUpInfo.dwFlags & 0x1) == 0x0) {
            this.bringToTop();
            if (this.isDisposed()) {
                return;
            }
        }
        OS.SendMessage(this.handle, 295, 3L, 0L);
        this.setVisible(true);
        if (this.isDisposed()) {
            return;
        }
        final MSG msg = new MSG();
        final int flags = 4194306;
        OS.PeekMessage(msg, 0L, 0, 0, 4194306);
        boolean restored = this.restoreFocus();
        if (!restored) {
            restored = this.traverseGroup(true);
        }
        if (restored) {
            final Control focusControl = this.display.getFocusControl();
            if (focusControl instanceof Button && (focusControl.style & 0x8) != 0x0) {
                restored = false;
            }
        }
        if (!restored) {
            if (this.saveDefault != null && !this.saveDefault.isDisposed()) {
                this.saveDefault.setFocus();
            }
            else {
                this.setFocus();
            }
        }
    }
    
    public boolean print(final GC gc) {
        this.checkWidget();
        if (gc == null) {
            this.error(4);
        }
        if (gc.isDisposed()) {
            this.error(5);
        }
        return false;
    }
    
    void register() {
        super.register();
        if (this.toolTipHandle != 0L) {
            this.display.addControl(this.toolTipHandle, (Control)this);
        }
        if (this.balloonTipHandle != 0L) {
            this.display.addControl(this.balloonTipHandle, (Control)this);
        }
        if (this.menuItemToolTipHandle != 0L) {
            this.display.addControl(this.menuItemToolTipHandle, (Control)this);
        }
    }
    
    void releaseBrushes() {
        if (this.brushes != null) {
            for (final long brush : this.brushes) {
                if (brush != 0L) {
                    OS.DeleteObject(brush);
                }
            }
        }
        this.brushes = null;
    }
    
    void releaseChildren(final boolean destroy) {
        for (final Shell shell : this.getShells()) {
            if (shell != null && !shell.isDisposed()) {
                shell.release(false);
            }
        }
        if (this.toolTips != null) {
            for (final ToolTip toolTip : this.toolTips) {
                if (toolTip != null && !toolTip.isDisposed()) {
                    toolTip.release(false);
                }
            }
        }
        this.toolTips = null;
        super.releaseChildren(destroy);
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.hwndMDIClient = 0L;
    }
    
    void releaseParent() {
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.releaseBrushes();
        this.activeMenu = null;
        this.display.clearModal(this);
        if (this.lpstrTip != 0L) {
            final long hHeap = OS.GetProcessHeap();
            OS.HeapFree(hHeap, 0, this.lpstrTip);
        }
        this.lpstrTip = 0L;
        final long toolTipHandle = 0L;
        this.menuItemToolTipHandle = 0L;
        this.balloonTipHandle = 0L;
        this.toolTipHandle = 0L;
        this.lastActive = null;
        final String s = null;
        this.balloonTitle = s;
        this.toolTitle = s;
    }
    
    void removeMenu(final Menu menu) {
        super.removeMenu(menu);
        if (menu == this.activeMenu) {
            this.activeMenu = null;
        }
    }
    
    public void removeShellListener(final ShellListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(21, (SWTEventListener)listener);
        this.eventTable.unhook(19, (SWTEventListener)listener);
        this.eventTable.unhook(20, (SWTEventListener)listener);
        this.eventTable.unhook(26, (SWTEventListener)listener);
        this.eventTable.unhook(27, (SWTEventListener)listener);
    }
    
    public void requestLayout() {
        this.layout((Control[])null, 4);
    }
    
    void reskinChildren(final int flags) {
        for (final Shell shell : this.getShells()) {
            if (shell != null) {
                shell.reskin(flags);
            }
        }
        if (this.toolTips != null) {
            for (final ToolTip toolTip : this.toolTips) {
                if (toolTip != null) {
                    toolTip.reskin(flags);
                }
            }
        }
        super.reskinChildren(flags);
    }
    
    boolean sendKeyEvent(final int type, final int msg, final long wParam, final long lParam, final Event event) {
        return this.isEnabled() && this.isActive() && super.sendKeyEvent(type, msg, wParam, lParam, event);
    }
    
    public void setActive() {
        this.checkWidget();
        if (!this.isVisible()) {
            return;
        }
        this.bringToTop();
    }
    
    void setActiveControl(final Control control) {
        this.setActiveControl(control, 0);
    }
    
    void setActiveControl(Control control, final int type) {
        if (control != null && control.isDisposed()) {
            control = null;
        }
        if (this.lastActive != null && this.lastActive.isDisposed()) {
            this.lastActive = null;
        }
        if (this.lastActive == control) {
            return;
        }
        final Control[] activate = (control == null) ? new Control[0] : control.getPath();
        final Control[] deactivate = (this.lastActive == null) ? new Control[0] : this.lastActive.getPath();
        this.lastActive = control;
        int index = 0;
        for (int length = Math.min(activate.length, deactivate.length); index < length && activate[index] == deactivate[index]; ++index) {}
        for (int i = deactivate.length - 1; i >= index; --i) {
            if (!deactivate[i].isDisposed()) {
                deactivate[i].sendEvent(27);
            }
        }
        for (int i = activate.length - 1; i >= index; --i) {
            if (!activate[i].isDisposed()) {
                final Event event = new Event();
                event.detail = type;
                activate[i].sendEvent(26, event);
            }
        }
    }
    
    public void setAlpha(int alpha) {
        this.checkWidget();
        alpha &= 0xFF;
        final int bits = OS.GetWindowLong(this.handle, -20);
        if (alpha == 255) {
            OS.SetWindowLong(this.handle, -20, bits & 0xFFF7FFFF);
            final int flags = 1157;
            OS.RedrawWindow(this.handle, (RECT)null, 0L, 1157);
        }
        else {
            OS.SetWindowLong(this.handle, -20, bits | 0x80000);
            OS.SetLayeredWindowAttributes(this.handle, 0, (byte)alpha, 2);
        }
    }
    
    void setBoundsInPixels(final int x, final int y, final int width, final int height, int flags, final boolean defer) {
        if (this.fullScreen) {
            this.setFullScreen(false);
        }
        final int bits = OS.GetWindowLong(this.handle, -20);
        if ((bits & 0x80000) != 0x0) {
            flags &= 0xFFFFFFDF;
        }
        super.setBoundsInPixels(x, y, width, height, flags, false);
    }
    
    public void setEnabled(final boolean enabled) {
        this.checkWidget();
        if ((this.state & 0x8) == 0x0 == enabled) {
            return;
        }
        super.setEnabled(enabled);
        if (enabled && this.handle == OS.GetActiveWindow() && !this.restoreFocus()) {
            this.traverseGroup(true);
        }
    }
    
    public void setFullScreen(final boolean fullScreen) {
        this.checkWidget();
        if (this.fullScreen == fullScreen) {
            return;
        }
        int stateFlags = fullScreen ? 3 : 9;
        int styleFlags = OS.GetWindowLong(this.handle, -16);
        final int mask = 1248;
        if ((this.style & 0x4E0) != 0x0) {
            if (fullScreen) {
                styleFlags &= 0xFF38FFFF;
            }
            else {
                styleFlags |= 0xC00000;
                if ((this.style & 0x400) != 0x0) {
                    styleFlags |= 0x10000;
                }
                if ((this.style & 0x80) != 0x0) {
                    styleFlags |= 0x20000;
                }
                if ((this.style & 0x10) != 0x0) {
                    styleFlags |= 0x40000;
                }
            }
        }
        if (fullScreen) {
            this.wasMaximized = this.getMaximized();
        }
        final boolean visible = this.isVisible();
        if (!visible && !this.wasMaximized) {
            this.swFlags = stateFlags;
        }
        OS.SetWindowLong(this.handle, -16, styleFlags);
        if (this.wasMaximized) {
            OS.ShowWindow(this.handle, 0);
            stateFlags = 3;
        }
        if (visible) {
            OS.ShowWindow(this.handle, stateFlags);
        }
        OS.UpdateWindow(this.handle);
        this.fullScreen = fullScreen;
    }
    
    public void setImeInputMode(final int mode) {
        this.checkWidget();
        if (!OS.IsDBLocale) {
            return;
        }
        final boolean imeOn = mode != 0;
        final long hIMC = OS.ImmGetContext(this.handle);
        OS.ImmSetOpenStatus(hIMC, imeOn);
        if (imeOn) {
            final int[] lpfdwConversion = { 0 };
            final int[] lpfdwSentence = { 0 };
            if (OS.ImmGetConversionStatus(hIMC, lpfdwConversion, lpfdwSentence)) {
                int newBits = 0;
                int oldBits = 3;
                if ((mode & 0x10) != 0x0) {
                    newBits = 3;
                    oldBits = 0;
                }
                else if ((mode & 0x8) != 0x0) {
                    newBits = 1;
                    oldBits = 2;
                }
                boolean fullShape = (mode & 0x2) != 0x0;
                if ((mode & 0x8) != 0x0) {
                    final long hkl = OS.GetKeyboardLayout(0);
                    final int langid = OS.PRIMARYLANGID(OS.LOWORD(hkl));
                    if (langid == 17) {
                        fullShape = true;
                    }
                }
                if (fullShape) {
                    newBits |= 0x8;
                }
                else {
                    oldBits |= 0x8;
                }
                if ((mode & 0x20) != 0x0) {
                    newBits |= 0x10;
                }
                else {
                    oldBits |= 0x10;
                }
                final int[] array = lpfdwConversion;
                final int n = 0;
                final int[] array3 = array;
                final int n3 = 0;
                array3[n3] |= newBits;
                final int[] array2 = lpfdwConversion;
                final int n2 = 0;
                final int[] array4 = array2;
                final int n4 = 0;
                array4[n4] &= ~oldBits;
                OS.ImmSetConversionStatus(hIMC, lpfdwConversion[0], lpfdwSentence[0]);
            }
        }
        OS.ImmReleaseContext(this.handle, hIMC);
    }
    
    public void setMaximumSize(final int width, final int height) {
        this.checkWidget();
        this.setMaximumSizeInPixels(DPIUtil.autoScaleUp(width), DPIUtil.autoScaleUp(height));
    }
    
    public void setMaximumSize(Point size) {
        this.checkWidget();
        if (size == null) {
            this.error(4);
        }
        size = DPIUtil.autoScaleUp(size);
        this.setMaximumSizeInPixels(size.x, size.y);
    }
    
    void setMaximumSizeInPixels(final int width, final int height) {
        int widthLimit = 0;
        int heightLimit = 0;
        final int trim = 1248;
        if ((this.style & 0x8) == 0x0 && (this.style & 0x4E0) != 0x0) {
            widthLimit = OS.GetSystemMetrics(59);
            if ((this.style & 0x10) != 0x0) {
                heightLimit = OS.GetSystemMetrics(60);
            }
            else {
                final RECT rect = new RECT();
                final int bits1 = OS.GetWindowLong(this.handle, -16);
                final int bits2 = OS.GetWindowLong(this.handle, -20);
                OS.AdjustWindowRectEx(rect, bits1, false, bits2);
                heightLimit = rect.bottom - rect.top;
            }
        }
        this.maxWidth = Math.min(widthLimit, width);
        this.maxHeight = Math.min(heightLimit, height);
        final Point size = this.getSizeInPixels();
        final int newWidth = Math.min(size.x, this.maxWidth);
        final int newHeight = Math.min(size.y, this.maxHeight);
        if (this.maxWidth >= widthLimit) {
            this.maxWidth = -1;
        }
        if (this.maxHeight >= heightLimit) {
            this.maxHeight = -1;
        }
        if (newWidth != size.x || newHeight != size.y) {
            this.setSizeInPixels(newWidth, newHeight);
        }
    }
    
    public void setMinimumSize(final int width, final int height) {
        this.checkWidget();
        this.setMinimumSizeInPixels(DPIUtil.autoScaleUp(width), DPIUtil.autoScaleUp(height));
    }
    
    void setMinimumSizeInPixels(final int width, final int height) {
        int widthLimit = 0;
        int heightLimit = 0;
        final int trim = 1248;
        if ((this.style & 0x8) == 0x0 && (this.style & 0x4E0) != 0x0) {
            widthLimit = OS.GetSystemMetrics(34);
            if ((this.style & 0x10) != 0x0) {
                heightLimit = OS.GetSystemMetrics(35);
            }
            else {
                final RECT rect = new RECT();
                final int bits1 = OS.GetWindowLong(this.handle, -16);
                final int bits2 = OS.GetWindowLong(this.handle, -20);
                OS.AdjustWindowRectEx(rect, bits1, false, bits2);
                heightLimit = rect.bottom - rect.top;
            }
        }
        this.minWidth = Math.max(widthLimit, width);
        this.minHeight = Math.max(heightLimit, height);
        final Point size = this.getSizeInPixels();
        final int newWidth = Math.max(size.x, this.minWidth);
        final int newHeight = Math.max(size.y, this.minHeight);
        if (this.minWidth <= widthLimit) {
            this.minWidth = -1;
        }
        if (this.minHeight <= heightLimit) {
            this.minHeight = -1;
        }
        if (newWidth != size.x || newHeight != size.y) {
            this.setSizeInPixels(newWidth, newHeight);
        }
    }
    
    public void setMinimumSize(Point size) {
        this.checkWidget();
        if (size == null) {
            this.error(4);
        }
        size = DPIUtil.autoScaleUp(size);
        this.setMinimumSizeInPixels(size.x, size.y);
    }
    
    public void setModified(final boolean modified) {
        this.checkWidget();
        this.modified = modified;
    }
    
    void setItemEnabled(final int cmd, final boolean enabled) {
        final long hMenu = OS.GetSystemMenu(this.handle, false);
        if (hMenu == 0L) {
            return;
        }
        int flags = 0;
        if (!enabled) {
            flags = 3;
        }
        OS.EnableMenuItem(hMenu, cmd, 0x0 | flags);
    }
    
    void setParent() {
    }
    
    public void setRegion(final Region region) {
        this.checkWidget();
        if ((this.style & 0x8) == 0x0) {
            return;
        }
        if (region != null) {
            final Rectangle bounds = region.getBounds();
            this.setSize(bounds.x + bounds.width, bounds.y + bounds.height);
        }
        super.setRegion(region);
    }
    
    void setToolTipText(final long hwnd, final String text) {
        final TOOLINFO lpti = new TOOLINFO();
        lpti.cbSize = TOOLINFO.sizeof;
        lpti.hwnd = this.handle;
        lpti.uId = hwnd;
        final long hwndToolTip = this.toolTipHandle();
        this.maybeEnableDarkSystemTheme(hwndToolTip);
        if (text == null) {
            OS.SendMessage(hwndToolTip, 1075, 0L, lpti);
        }
        else if (OS.SendMessage(hwndToolTip, 1077, 0L, lpti) == 0L) {
            lpti.uFlags = 17;
            lpti.lpszText = -1L;
            OS.SendMessage(hwndToolTip, 1074, 0L, lpti);
        }
        else if (OS.SendMessage(hwndToolTip, 1083, 0L, lpti) != 0L && lpti.uId == hwnd) {
            OS.SendMessage(hwndToolTip, 1053, 0L, 0L);
        }
    }
    
    void setToolTipText(final NMTTDISPINFO lpnmtdi, final char[] buffer) {
        if (!this.hasCursor()) {
            return;
        }
        final long hHeap = OS.GetProcessHeap();
        if (this.lpstrTip != 0L) {
            OS.HeapFree(hHeap, 0, this.lpstrTip);
        }
        final int byteCount = buffer.length * 2;
        OS.MoveMemory(this.lpstrTip = OS.HeapAlloc(hHeap, 8, byteCount), buffer, byteCount);
        lpnmtdi.lpszText = this.lpstrTip;
    }
    
    void setToolTipTitle(final long hwndToolTip, String text, final int icon) {
        if (hwndToolTip != this.toolTipHandle && hwndToolTip != this.balloonTipHandle && hwndToolTip != this.menuItemToolTipHandle) {
            return;
        }
        if (hwndToolTip == this.toolTipHandle || hwndToolTip == this.menuItemToolTipHandle) {
            if ((text == this.toolTitle || (this.toolTitle != null && this.toolTitle.equals(text))) && icon == this.toolIcon) {
                return;
            }
            this.toolTitle = text;
            this.toolIcon = icon;
        }
        else if (hwndToolTip == this.balloonTipHandle) {
            if ((text == this.balloonTitle || (this.balloonTitle != null && this.balloonTitle.equals(text))) && icon == this.toolIcon) {
                return;
            }
            this.balloonTitle = text;
            this.balloonIcon = icon;
        }
        if (text != null) {
            if (text.length() > 99) {
                text = text.substring(0, 99);
            }
            final TCHAR pszTitle = new TCHAR(this.getCodePage(), text, true);
            OS.SendMessage(hwndToolTip, 1057, (long)icon, pszTitle);
        }
        else {
            OS.SendMessage(hwndToolTip, 1057, 0L, 0L);
        }
    }
    
    public void setVisible(final boolean visible) {
        this.checkWidget();
        final int mask = 229376;
        if ((this.style & 0x38000) != 0x0) {
            if (visible) {
                this.display.setModalShell(this);
                if ((this.style & 0x30000) != 0x0) {
                    this.display.setModalDialog((Dialog)null);
                }
                final Control control = this.display._getFocusControl();
                if (control != null && !control.isActive()) {
                    this.bringToTop();
                    if (this.isDisposed()) {
                        return;
                    }
                }
                long hwndShell = OS.GetActiveWindow();
                if (hwndShell == 0L && this.parent != null) {
                    hwndShell = this.parent.handle;
                }
                if (hwndShell != 0L) {
                    OS.SendMessage(hwndShell, 31, 0L, 0L);
                }
                OS.ReleaseCapture();
            }
            else {
                this.display.clearModal(this);
            }
        }
        else {
            this.updateModal();
        }
        if (this.showWithParent && !visible) {
            OS.ShowOwnedPopups(this.handle, false);
        }
        if (!visible) {
            this.fixActiveShell();
        }
        if (visible && this.center && !this.moved) {
            this.center();
            if (this.isDisposed()) {
                return;
            }
        }
        super.setVisible(visible);
        if (this.isDisposed()) {
            return;
        }
        if (this.showWithParent != visible && (this.showWithParent = visible)) {
            OS.ShowOwnedPopups(this.handle, true);
        }
        if (visible && this.parent != null && (this.parent.state & 0x4000) != 0x0) {
            final long hwndParent = this.parent.handle;
            final int style = OS.GetWindowLong(hwndParent, -20);
            if ((style & 0x80) != 0x0) {
                OS.SetWindowLong(hwndParent, -20, style & 0xFFFFFF7F);
                OS.ShowWindow(hwndParent, 0);
                OS.ShowWindow(hwndParent, 9);
            }
        }
    }
    
    void subclass() {
        super.subclass();
        if (Shell.ToolTipProc != 0L) {
            final long newProc = this.display.windowProc;
            if (this.toolTipHandle != 0L) {
                OS.SetWindowLongPtr(this.toolTipHandle, -4, newProc);
            }
            if (this.balloonTipHandle != 0L) {
                OS.SetWindowLongPtr(this.balloonTipHandle, -4, newProc);
            }
            if (this.menuItemToolTipHandle != 0L) {
                OS.SetWindowLongPtr(this.menuItemToolTipHandle, -4, newProc);
            }
        }
    }
    
    long toolTipHandle() {
        if (this.toolTipHandle == 0L) {
            this.createToolTipHandle();
        }
        return this.toolTipHandle;
    }
    
    boolean translateAccelerator(final MSG msg) {
        return this.isEnabled() && this.isActive() && (this.menuBar == null || this.menuBar.isEnabled()) && (this.translateMDIAccelerator(msg) || this.translateMenuAccelerator(msg));
    }
    
    boolean traverseEscape() {
        if (this.parent == null) {
            return false;
        }
        if (!this.isVisible() || !this.isEnabled()) {
            return false;
        }
        this.close();
        return true;
    }
    
    void unsubclass() {
        super.unsubclass();
        if (Shell.ToolTipProc != 0L) {
            if (this.toolTipHandle != 0L) {
                OS.SetWindowLongPtr(this.toolTipHandle, -4, Shell.ToolTipProc);
            }
            if (this.toolTipHandle != 0L) {
                OS.SetWindowLongPtr(this.toolTipHandle, -4, Shell.ToolTipProc);
            }
            if (this.menuItemToolTipHandle != 0L) {
                OS.SetWindowLongPtr(this.menuItemToolTipHandle, -4, Shell.ToolTipProc);
            }
        }
    }
    
    void updateModal() {
        if (Display.TrimEnabled) {
            this.setItemEnabled(61536, this.isActive());
        }
        else {
            OS.EnableWindow(this.handle, this.isActive());
        }
    }
    
    CREATESTRUCT widgetCreateStruct() {
        return null;
    }
    
    long widgetParent() {
        if (this.handle != 0L) {
            return this.handle;
        }
        return (this.parent != null) ? this.parent.handle : 0L;
    }
    
    int widgetExtStyle() {
        int bits = super.widgetExtStyle() & 0xFFFFFFBF;
        if ((this.style & 0x4) != 0x0) {
            bits |= 0x80;
        }
        if (this.parent == null && (this.style & 0x4000) != 0x0) {
            final int trim = 1248;
            if ((this.style & 0x8) != 0x0 || (this.style & 0x4E0) == 0x0) {
                bits |= 0x80;
            }
        }
        if ((this.style & 0x4000) != 0x0) {
            bits |= 0x8;
        }
        return bits;
    }
    
    TCHAR windowClass() {
        if ((this.style & 0x4) != 0x0) {
            final int trim = 3312;
            if ((this.style & 0xCF0) == 0x0) {
                return this.display.windowShadowClass;
            }
        }
        return (this.parent != null) ? Shell.DialogClass : super.windowClass();
    }
    
    long windowProc() {
        if (this.windowProc != 0L) {
            return this.windowProc;
        }
        if ((this.style & 0x4) != 0x0) {
            final int trim = 3312;
            if ((this.style & 0xCF0) == 0x0) {
                return super.windowProc();
            }
        }
        return (this.parent != null) ? Shell.DialogProc : super.windowProc();
    }
    
    Rectangle getClientRectInWindow() {
        final RECT windowRect = new RECT();
        OS.GetWindowRect(this.handle, windowRect);
        final POINT clientWindowLT = new POINT();
        OS.ClientToScreen(this.handle, clientWindowLT);
        final POINT point3;
        final POINT point = point3 = clientWindowLT;
        point3.x -= windowRect.left;
        final POINT point4;
        final POINT point2 = point4 = clientWindowLT;
        point4.y -= windowRect.top;
        final RECT clientRect = new RECT();
        OS.GetClientRect(this.handle, clientRect);
        return new Rectangle(clientWindowLT.x + clientRect.left, clientWindowLT.y + clientRect.top, clientRect.right - clientRect.left, clientRect.bottom - clientRect.top);
    }
    
    void overpaintMenuBorder() {
        if (this.menuBar == null || this.display.menuBarBorderPen == 0L) {
            return;
        }
        final Rectangle clientArea = this.getClientRectInWindow();
        final long dc = OS.GetWindowDC(this.handle);
        final long oldPen = OS.SelectObject(dc, this.display.menuBarBorderPen);
        OS.MoveToEx(dc, clientArea.x, clientArea.y - 1, 0L);
        OS.LineTo(dc, clientArea.x + clientArea.width, clientArea.y - 1);
        OS.SelectObject(dc, oldPen);
        OS.ReleaseDC(this.handle, dc);
    }
    
    long windowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        if ((this.style & 0x800000) != 0x0 && msg == 161 && wParam == 2L) {
            return 0L;
        }
        if (hwnd == this.toolTipHandle || hwnd == this.balloonTipHandle || hwnd == this.menuItemToolTipHandle) {
            switch (msg) {
                case 275: {
                    if (wParam != 100L) {
                        break;
                    }
                    final ToolTip tip = this.getCurrentToolTip(hwnd);
                    if (tip != null && tip.autoHide) {
                        tip.setVisible(false);
                        break;
                    }
                    break;
                }
                case 513: {
                    final ToolTip tip = this.getCurrentToolTip(hwnd);
                    if (tip != null) {
                        tip.setVisible(false);
                        tip.sendSelectionEvent(13);
                        break;
                    }
                    break;
                }
            }
            return this.callWindowProc(hwnd, msg, wParam, lParam);
        }
        if (hwnd == this.handle && msg == Display.TASKBARBUTTONCREATED && this.display.taskBar != null) {
            for (final TaskItem item : this.display.taskBar.items) {
                if (item != null && item.shell == this) {
                    item.recreate();
                    break;
                }
            }
        }
        switch (msg) {
            case 133:
            case 134: {
                final long ret = super.windowProc(hwnd, msg, wParam, lParam);
                this.overpaintMenuBorder();
                return ret;
            }
            default: {
                return super.windowProc(hwnd, msg, wParam, lParam);
            }
        }
    }
    
    int widgetStyle() {
        int bits = super.widgetStyle();
        if (this.handle != 0L) {
            return bits | 0x40000000;
        }
        bits &= 0xBFFFFFFF;
        return bits | 0x0 | 0xC00000;
    }
    
    LRESULT WM_ACTIVATE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_ACTIVATE(wParam, lParam);
        if (OS.LOWORD(wParam) == 0 && (lParam == 0L || (lParam != this.toolTipHandle && lParam != this.balloonTipHandle && lParam != this.menuItemToolTipHandle))) {
            final ToolTip tip = this.getCurrentToolTip();
            if (tip != null) {
                tip.setVisible(false);
            }
        }
        return (this.parent != null) ? LRESULT.ZERO : result;
    }
    
    LRESULT WM_DESTROY(final long wParam, final long lParam) {
        final LRESULT result = super.WM_DESTROY(wParam, lParam);
        final int bits = OS.GetWindowLong(this.handle, -16);
        if ((bits & 0x40000000) != 0x0) {
            this.releaseParent();
            this.release(false);
        }
        return result;
    }
    
    LRESULT WM_ERASEBKGND(final long wParam, final long lParam) {
        final LRESULT result = super.WM_ERASEBKGND(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (OS.WIN32_VERSION == OS.VERSION(6, 0)) {
            this.drawBackground(wParam);
            return LRESULT.ONE;
        }
        return result;
    }
    
    LRESULT WM_ENTERIDLE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_ENTERIDLE(wParam, lParam);
        if (result != null) {
            return result;
        }
        final Display display = this.display;
        if (display.runAsyncMessages(false)) {
            display.wakeThread();
        }
        return result;
    }
    
    LRESULT WM_GETMINMAXINFO(final long wParam, final long lParam) {
        final LRESULT result = super.WM_GETMINMAXINFO(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (this.minWidth != -1 || this.minHeight != -1 || this.maxWidth != -1 || this.maxHeight != -1) {
            final MINMAXINFO info = new MINMAXINFO();
            OS.MoveMemory(info, lParam, MINMAXINFO.sizeof);
            if (this.minWidth != -1) {
                info.ptMinTrackSize_x = this.minWidth;
            }
            if (this.minHeight != -1) {
                info.ptMinTrackSize_y = this.minHeight;
            }
            if (this.maxWidth != -1) {
                info.ptMaxTrackSize_x = this.maxWidth;
            }
            if (this.maxHeight != -1) {
                info.ptMaxTrackSize_y = this.maxHeight;
            }
            OS.MoveMemory(lParam, info, MINMAXINFO.sizeof);
            return LRESULT.ZERO;
        }
        return result;
    }
    
    LRESULT WM_MOUSEACTIVATE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_MOUSEACTIVATE(wParam, lParam);
        if (result != null) {
            return result;
        }
        final int hittest = (short)OS.LOWORD(lParam);
        switch (hittest) {
            case -2:
            case -1:
            case 0: {
                break;
            }
            default: {
                final Control control = this.display._getFocusControl();
                if (control == null) {
                    break;
                }
                final Decorations decorations = control.menuShell();
                if (decorations.getShell() != this) {
                    break;
                }
                if (decorations == this) {
                    break;
                }
                this.display.ignoreRestoreFocus = true;
                this.display.lastHittest = hittest;
                this.display.lastHittestControl = null;
                if (hittest == 5 || hittest == 3) {
                    this.display.lastHittestControl = control;
                    return null;
                }
                return new LRESULT(3L);
            }
        }
        if (hittest == 5) {
            return null;
        }
        final POINT pt = new POINT();
        if (!OS.GetCursorPos(pt)) {
            final int pos = OS.GetMessagePos();
            OS.POINTSTOPOINT(pt, (long)pos);
        }
        final long hwnd = OS.WindowFromPoint(pt);
        if (hwnd == 0L) {
            return null;
        }
        final Control control2 = this.display.findControl(hwnd);
        if (control2 != null && (control2.state & 0x2) != 0x0 && (control2.style & 0x80000) != 0x0) {
            final int bits = 540672;
            if ((this.style & 0x84000) == 0x84000 && (hittest == 18 || hittest == 1)) {
                return new LRESULT(3L);
            }
        }
        final long code = this.callWindowProc(this.handle, 33, wParam, lParam);
        this.setActiveControl(control2, 3);
        return new LRESULT(code);
    }
    
    LRESULT WM_MOVE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_MOVE(wParam, lParam);
        if (result != null) {
            return result;
        }
        final ToolTip tip = this.getCurrentToolTip();
        if (tip != null) {
            tip.setVisible(false);
        }
        return result;
    }
    
    LRESULT WM_NCHITTEST(final long wParam, final long lParam) {
        if (!OS.IsWindowEnabled(this.handle)) {
            return null;
        }
        if (!this.isEnabled() || !this.isActive()) {
            if (!Display.TrimEnabled) {
                return new LRESULT(0L);
            }
            long hittest = this.callWindowProc(this.handle, 132, wParam, lParam);
            if (hittest == 1L || hittest == 5L) {
                hittest = 18L;
            }
            return new LRESULT(hittest);
        }
        else {
            if (this.menuBar != null && !this.menuBar.getEnabled()) {
                long hittest = this.callWindowProc(this.handle, 132, wParam, lParam);
                if (hittest == 5L) {
                    hittest = 18L;
                }
                return new LRESULT(hittest);
            }
            return null;
        }
    }
    
    LRESULT WM_NCLBUTTONDOWN(final long wParam, final long lParam) {
        final LRESULT result = super.WM_NCLBUTTONDOWN(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (!this.display.ignoreRestoreFocus) {
            return result;
        }
        final Display display = this.display;
        display.lockActiveWindow = true;
        final long code = this.callWindowProc(this.handle, 161, wParam, lParam);
        display.lockActiveWindow = false;
        final Control focusControl = display.lastHittestControl;
        if (focusControl != null && !focusControl.isDisposed()) {
            focusControl.setFocus();
        }
        display.lastHittestControl = null;
        display.ignoreRestoreFocus = false;
        return new LRESULT(code);
    }
    
    LRESULT WM_SETCURSOR(final long wParam, final long lParam) {
        final int msg = OS.HIWORD(lParam);
        if (msg == 513) {
            if (!Display.TrimEnabled) {
                final Shell modalShell = this.display.getModalShell();
                if (modalShell != null && !this.isActive()) {
                    final long hwndModal = modalShell.handle;
                    if (OS.IsWindowEnabled(hwndModal)) {
                        OS.SetActiveWindow(hwndModal);
                    }
                }
            }
            if (!OS.IsWindowEnabled(this.handle)) {
                final long hwndPopup = OS.GetLastActivePopup(this.handle);
                if (hwndPopup != 0L && hwndPopup != this.handle && this.display.getControl(hwndPopup) == null && OS.IsWindowEnabled(hwndPopup)) {
                    OS.SetActiveWindow(hwndPopup);
                }
            }
        }
        final int hitTest = (short)OS.LOWORD(lParam);
        if (hitTest == -2 && !this.getEnabled()) {
            final Control control = this.display.getControl(wParam);
            if (control == this && this.cursor != null) {
                final POINT pt = new POINT();
                final int pos = OS.GetMessagePos();
                OS.POINTSTOPOINT(pt, (long)pos);
                OS.ScreenToClient(this.handle, pt);
                final RECT rect = new RECT();
                OS.GetClientRect(this.handle, rect);
                if (OS.PtInRect(rect, pt)) {
                    OS.SetCursor(this.cursor.handle);
                    switch (msg) {
                        case 513:
                        case 516:
                        case 519:
                        case 523: {
                            OS.MessageBeep(0);
                            break;
                        }
                    }
                    return LRESULT.ONE;
                }
            }
        }
        return super.WM_SETCURSOR(wParam, lParam);
    }
    
    LRESULT WM_SHOWWINDOW(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SHOWWINDOW(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (lParam == 3L) {
            for (Control control = (Control)this; control != null; control = (Control)control.parent) {
                final Shell shell = control.getShell();
                if (!shell.showWithParent) {
                    return LRESULT.ZERO;
                }
            }
        }
        return result;
    }
    
    LRESULT WM_WINDOWPOSCHANGING(final long wParam, final long lParam) {
        final LRESULT result = super.WM_WINDOWPOSCHANGING(wParam, lParam);
        if (result != null) {
            return result;
        }
        final WINDOWPOS lpwp = new WINDOWPOS();
        OS.MoveMemory(lpwp, lParam, WINDOWPOS.sizeof);
        if ((lpwp.flags & 0x1) == 0x0) {
            lpwp.cx = Math.max(lpwp.cx, this.minWidth);
            final int trim = 1248;
            if ((this.style & 0x8) == 0x0 && (this.style & 0x4E0) != 0x0) {
                lpwp.cx = Math.max(lpwp.cx, OS.GetSystemMetrics(34));
            }
            lpwp.cy = Math.max(lpwp.cy, this.minHeight);
            if ((this.style & 0x8) == 0x0 && (this.style & 0x4E0) != 0x0) {
                if ((this.style & 0x10) != 0x0) {
                    lpwp.cy = Math.max(lpwp.cy, OS.GetSystemMetrics(35));
                }
                else {
                    final RECT rect = new RECT();
                    final int bits1 = OS.GetWindowLong(this.handle, -16);
                    final int bits2 = OS.GetWindowLong(this.handle, -20);
                    OS.AdjustWindowRectEx(rect, bits1, false, bits2);
                    lpwp.cy = Math.max(lpwp.cy, rect.bottom - rect.top);
                }
            }
            OS.MoveMemory(lParam, lpwp, WINDOWPOS.sizeof);
        }
        return result;
    }
    
    static {
        DialogClass = new TCHAR(0, "#32770", true);
        SYSTEM_COLORS = new int[] { 15, 5, 18, 8, 13, 0 };
        final WNDCLASS lpWndClass = new WNDCLASS();
        OS.GetClassInfo(0L, Shell.DialogClass, lpWndClass);
        DialogProc = lpWndClass.lpfnWndProc;
    }
}
