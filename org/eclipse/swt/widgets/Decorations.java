//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;

public class Decorations extends Canvas
{
    Image image;
    Image smallImage;
    Image largeImage;
    Image[] images;
    Menu menuBar;
    Menu[] menus;
    Control savedFocus;
    Button defaultButton;
    Button saveDefault;
    int swFlags;
    int nAccel;
    long hAccel;
    boolean moved;
    boolean resized;
    boolean opened;
    int oldX;
    int oldY;
    int oldWidth;
    int oldHeight;
    RECT maxRect;
    
    Decorations() {
        this.oldX = Integer.MIN_VALUE;
        this.oldY = Integer.MIN_VALUE;
        this.oldWidth = Integer.MIN_VALUE;
        this.oldHeight = Integer.MIN_VALUE;
        this.maxRect = new RECT();
    }
    
    public Decorations(final Composite parent, final int style) {
        super(parent, checkStyle(style));
        this.oldX = Integer.MIN_VALUE;
        this.oldY = Integer.MIN_VALUE;
        this.oldWidth = Integer.MIN_VALUE;
        this.oldHeight = Integer.MIN_VALUE;
        this.maxRect = new RECT();
    }
    
    void _setMaximized(final boolean maximized) {
        this.swFlags = (maximized ? 3 : 9);
        if (!OS.IsWindowVisible(this.handle)) {
            return;
        }
        if (maximized == OS.IsZoomed(this.handle)) {
            return;
        }
        OS.ShowWindow(this.handle, this.swFlags);
        OS.UpdateWindow(this.handle);
    }
    
    void _setMinimized(final boolean minimized) {
        this.swFlags = (minimized ? 7 : 9);
        if (!OS.IsWindowVisible(this.handle)) {
            return;
        }
        if (minimized == OS.IsIconic(this.handle)) {
            return;
        }
        int flags = this.swFlags;
        if (flags == 7 && this.handle == OS.GetActiveWindow()) {
            flags = 6;
        }
        OS.ShowWindow(this.handle, flags);
        OS.UpdateWindow(this.handle);
    }
    
    void addMenu(final Menu menu) {
        if (this.menus == null) {
            this.menus = new Menu[4];
        }
        for (int i = 0; i < this.menus.length; ++i) {
            if (this.menus[i] == null) {
                this.menus[i] = menu;
                return;
            }
        }
        final Menu[] newMenus = new Menu[this.menus.length + 4];
        newMenus[this.menus.length] = menu;
        System.arraycopy(this.menus, 0, newMenus, 0, this.menus.length);
        this.menus = newMenus;
    }
    
    void bringToTop() {
        OS.BringWindowToTop(this.handle);
    }
    
    static int checkStyle(int style) {
        if ((style & 0x8) != 0x0) {
            style &= 0xFFFFF30F;
        }
        else if ((style & 0x800000) != 0x0) {
            style |= 0x20;
        }
        if ((style & 0x4C0) != 0x0) {
            style |= 0x20;
        }
        if ((style & 0x480) != 0x0) {
            style |= 0x40;
        }
        if ((style & 0x40) != 0x0) {
            style |= 0x20;
        }
        return style;
    }
    
    void checkBorder() {
    }
    
    void checkComposited(final Composite parent) {
    }
    
    void checkOpened() {
        if (!this.opened) {
            this.resized = false;
        }
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        return OS.DefMDIChildProc(hwnd, msg, wParam, lParam);
    }
    
    void closeWidget() {
        final Event event = new Event();
        this.sendEvent(21, event);
        if (event.doit && !this.isDisposed()) {
            this.dispose();
        }
    }
    
    int compare(final ImageData data1, final ImageData data2, final int width, final int height, final int depth) {
        final int value1 = Math.abs(data1.width - width);
        final int value2 = Math.abs(data2.width - width);
        if (value1 != value2) {
            return (value1 < value2) ? -1 : 1;
        }
        final int transparent1 = data1.getTransparencyType();
        final int transparent2 = data2.getTransparencyType();
        if (transparent1 == transparent2) {
            if (data1.depth == data2.depth) {
                return 0;
            }
            return (data1.depth > data2.depth && data1.depth <= depth) ? -1 : 1;
        }
        else {
            if (transparent1 == 1) {
                return -1;
            }
            if (transparent2 == 1) {
                return 1;
            }
            if (transparent1 == 2) {
                return -1;
            }
            if (transparent2 == 2) {
                return 1;
            }
            if (transparent1 == 4) {
                return -1;
            }
            if (transparent2 == 4) {
                return 1;
            }
            return 0;
        }
    }
    
    Widget computeTabGroup() {
        return (Widget)this;
    }
    
    Control computeTabRoot() {
        return (Control)this;
    }
    
    Rectangle computeTrimInPixels(final int x, final int y, final int width, final int height) {
        this.checkWidget();
        final RECT rect = new RECT();
        OS.SetRect(rect, x, y, x + width, y + height);
        final int bits1 = OS.GetWindowLong(this.handle, -16);
        final int bits2 = OS.GetWindowLong(this.handle, -20);
        final boolean hasMenu = OS.GetMenu(this.handle) != 0L;
        OS.AdjustWindowRectEx(rect, bits1, hasMenu, bits2);
        if (this.horizontalBar != null) {
            final RECT rect5;
            final RECT rect2 = rect5 = rect;
            rect5.bottom += OS.GetSystemMetrics(3);
        }
        if (this.verticalBar != null) {
            final RECT rect6;
            final RECT rect3 = rect6 = rect;
            rect6.right += OS.GetSystemMetrics(2);
        }
        if (hasMenu) {
            final RECT testRect = new RECT();
            OS.SetRect(testRect, 0, 0, rect.right - rect.left, rect.bottom - rect.top);
            OS.SendMessage(this.handle, 131, 0L, testRect);
            while (testRect.bottom - testRect.top < height) {
                if (testRect.bottom - testRect.top == 0) {
                    break;
                }
                final RECT rect7;
                final RECT rect4 = rect7 = rect;
                rect7.top -= OS.GetSystemMetrics(15) - OS.GetSystemMetrics(6);
                OS.SetRect(testRect, 0, 0, rect.right - rect.left, rect.bottom - rect.top);
                OS.SendMessage(this.handle, 131, 0L, testRect);
            }
        }
        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }
    
    void createAccelerators() {
        final int nAccel = 0;
        this.nAccel = 0;
        this.hAccel = 0L;
        final MenuItem[] items = this.display.items;
        if (this.menuBar == null || items == null) {
            return;
        }
        final ACCEL accel = new ACCEL();
        final byte[] buffer1 = new byte[ACCEL.sizeof];
        final byte[] buffer2 = new byte[items.length * ACCEL.sizeof];
        for (final MenuItem item : items) {
            if (item != null && item.accelerator != 0) {
                Menu menu = item.parent;
                if (menu.parent == this) {
                    while (menu != null && menu != this.menuBar) {
                        menu = menu.getParentMenu();
                    }
                    if (menu == this.menuBar && item.fillAccel(accel)) {
                        OS.MoveMemory(buffer1, accel, ACCEL.sizeof);
                        System.arraycopy(buffer1, 0, buffer2, this.nAccel * ACCEL.sizeof, ACCEL.sizeof);
                        ++this.nAccel;
                    }
                }
            }
        }
        if (this.nAccel != 0) {
            this.hAccel = OS.CreateAcceleratorTable(buffer2, this.nAccel);
        }
    }
    
    void createHandle() {
        super.createHandle();
        if (this.parent != null || (this.style & 0x4) != 0x0) {
            this.setParent();
            this.setSystemMenu();
        }
    }
    
    void createWidget() {
        super.createWidget();
        this.swFlags = 4;
        this.hAccel = -1L;
    }
    
    void destroyAccelerators() {
        if (this.hAccel != 0L && this.hAccel != -1L) {
            OS.DestroyAcceleratorTable(this.hAccel);
        }
        this.hAccel = -1L;
    }
    
    public void dispose() {
        if (this.isDisposed()) {
            return;
        }
        if (!this.isValidThread()) {
            this.error(22);
        }
        if (!(this instanceof Shell)) {
            if (!this.traverseDecorations(true)) {
                final Shell shell = this.getShell();
                shell.setFocus();
            }
            this.setVisible(false);
        }
        super.dispose();
    }
    
    Menu findMenu(final long hMenu) {
        if (this.menus == null) {
            return null;
        }
        for (final Menu menu : this.menus) {
            if (menu != null && hMenu == menu.handle) {
                return menu;
            }
        }
        return null;
    }
    
    void fixDecorations(final Decorations newDecorations, final Control control, final Menu[] menus) {
        if (this == newDecorations) {
            return;
        }
        if (control == this.savedFocus) {
            this.savedFocus = null;
        }
        if (control == this.defaultButton) {
            this.defaultButton = null;
        }
        if (control == this.saveDefault) {
            this.saveDefault = null;
        }
        if (menus == null) {
            return;
        }
        final Menu menu = control.menu;
        if (menu != null) {
            for (int index = 0; index < menus.length; ++index) {
                if (menus[index] == menu) {
                    control.setMenu((Menu)null);
                    return;
                }
            }
            menu.fixMenus(newDecorations);
            this.destroyAccelerators();
            newDecorations.destroyAccelerators();
        }
    }
    
    Rectangle getBoundsInPixels() {
        this.checkWidget();
        if (!OS.IsIconic(this.handle)) {
            return super.getBoundsInPixels();
        }
        final WINDOWPLACEMENT lpwndpl = new WINDOWPLACEMENT();
        lpwndpl.length = WINDOWPLACEMENT.sizeof;
        OS.GetWindowPlacement(this.handle, lpwndpl);
        if ((lpwndpl.flags & 0x2) != 0x0) {
            final int width = this.maxRect.right - this.maxRect.left;
            final int height = this.maxRect.bottom - this.maxRect.top;
            return new Rectangle(this.maxRect.left, this.maxRect.top, width, height);
        }
        final int width = lpwndpl.right - lpwndpl.left;
        final int height = lpwndpl.bottom - lpwndpl.top;
        return new Rectangle(lpwndpl.left, lpwndpl.top, width, height);
    }
    
    Rectangle getClientAreaInPixels() {
        this.checkWidget();
        if (!OS.IsIconic(this.handle)) {
            return super.getClientAreaInPixels();
        }
        final WINDOWPLACEMENT lpwndpl = new WINDOWPLACEMENT();
        lpwndpl.length = WINDOWPLACEMENT.sizeof;
        OS.GetWindowPlacement(this.handle, lpwndpl);
        if ((lpwndpl.flags & 0x2) != 0x0) {
            return new Rectangle(0, 0, this.oldWidth, this.oldHeight);
        }
        int width = lpwndpl.right - lpwndpl.left;
        int height = lpwndpl.bottom - lpwndpl.top;
        if (this.horizontalBar != null) {
            width -= OS.GetSystemMetrics(3);
        }
        if (this.verticalBar != null) {
            height -= OS.GetSystemMetrics(2);
        }
        final RECT rect = new RECT();
        final int bits1 = OS.GetWindowLong(this.handle, -16);
        final int bits2 = OS.GetWindowLong(this.handle, -20);
        final boolean hasMenu = OS.GetMenu(this.handle) != 0L;
        OS.AdjustWindowRectEx(rect, bits1, hasMenu, bits2);
        width = Math.max(0, width - (rect.right - rect.left));
        height = Math.max(0, height - (rect.bottom - rect.top));
        return new Rectangle(0, 0, width, height);
    }
    
    public Button getDefaultButton() {
        this.checkWidget();
        if (this.defaultButton != null && this.defaultButton.isDisposed()) {
            return null;
        }
        return this.defaultButton;
    }
    
    public Image getImage() {
        this.checkWidget();
        return this.image;
    }
    
    public Image[] getImages() {
        this.checkWidget();
        if (this.images == null) {
            return new Image[0];
        }
        final Image[] result = new Image[this.images.length];
        System.arraycopy(this.images, 0, result, 0, this.images.length);
        return result;
    }
    
    Point getLocationInPixels() {
        this.checkWidget();
        if (!OS.IsIconic(this.handle)) {
            return super.getLocationInPixels();
        }
        final WINDOWPLACEMENT lpwndpl = new WINDOWPLACEMENT();
        lpwndpl.length = WINDOWPLACEMENT.sizeof;
        OS.GetWindowPlacement(this.handle, lpwndpl);
        if ((lpwndpl.flags & 0x2) != 0x0) {
            return new Point(this.maxRect.left, this.maxRect.top);
        }
        return new Point(lpwndpl.left, lpwndpl.top);
    }
    
    public boolean getMaximized() {
        this.checkWidget();
        if (OS.IsWindowVisible(this.handle)) {
            return OS.IsZoomed(this.handle);
        }
        return this.swFlags == 3;
    }
    
    public Menu getMenuBar() {
        this.checkWidget();
        return this.menuBar;
    }
    
    public boolean getMinimized() {
        this.checkWidget();
        if (OS.IsWindowVisible(this.handle)) {
            return OS.IsIconic(this.handle);
        }
        return this.swFlags == 7;
    }
    
    String getNameText() {
        return this.getText();
    }
    
    Point getSizeInPixels() {
        this.checkWidget();
        if (!OS.IsIconic(this.handle)) {
            return super.getSizeInPixels();
        }
        final WINDOWPLACEMENT lpwndpl = new WINDOWPLACEMENT();
        lpwndpl.length = WINDOWPLACEMENT.sizeof;
        OS.GetWindowPlacement(this.handle, lpwndpl);
        if ((lpwndpl.flags & 0x2) != 0x0) {
            final int width = this.maxRect.right - this.maxRect.left;
            final int height = this.maxRect.bottom - this.maxRect.top;
            return new Point(width, height);
        }
        final int width = lpwndpl.right - lpwndpl.left;
        final int height = lpwndpl.bottom - lpwndpl.top;
        return new Point(width, height);
    }
    
    public String getText() {
        this.checkWidget();
        final int length = OS.GetWindowTextLength(this.handle);
        if (length == 0) {
            return "";
        }
        final char[] buffer = new char[length + 1];
        OS.GetWindowText(this.handle, buffer, length + 1);
        return new String(buffer, 0, length);
    }
    
    public boolean isReparentable() {
        this.checkWidget();
        return false;
    }
    
    boolean isTabGroup() {
        return true;
    }
    
    boolean isTabItem() {
        return false;
    }
    
    Decorations menuShell() {
        return this;
    }
    
    void releaseChildren(final boolean destroy) {
        if (this.menuBar != null) {
            this.menuBar.release(false);
            this.menuBar = null;
        }
        super.releaseChildren(destroy);
        if (this.menus != null) {
            for (final Menu menu : this.menus) {
                if (menu != null && !menu.isDisposed()) {
                    menu.dispose();
                }
            }
            this.menus = null;
        }
    }
    
    void releaseWidget() {
        super.releaseWidget();
        if (this.smallImage != null) {
            this.smallImage.dispose();
        }
        if (this.largeImage != null) {
            this.largeImage.dispose();
        }
        final Image smallImage = null;
        this.image = smallImage;
        this.largeImage = smallImage;
        this.smallImage = smallImage;
        this.images = null;
        this.savedFocus = null;
        final Button button = null;
        this.saveDefault = button;
        this.defaultButton = button;
        if (this.hAccel != 0L && this.hAccel != -1L) {
            OS.DestroyAcceleratorTable(this.hAccel);
        }
        this.hAccel = -1L;
    }
    
    void removeMenu(final Menu menu) {
        if (this.menus == null) {
            return;
        }
        for (int i = 0; i < this.menus.length; ++i) {
            if (this.menus[i] == menu) {
                this.menus[i] = null;
                return;
            }
        }
    }
    
    void reskinChildren(final int flags) {
        if (this.menuBar != null) {
            this.menuBar.reskin(flags);
        }
        if (this.menus != null) {
            for (final Menu menu : this.menus) {
                if (menu != null) {
                    menu.reskin(flags);
                }
            }
        }
        super.reskinChildren(flags);
    }
    
    boolean restoreFocus() {
        if (this.display.ignoreRestoreFocus) {
            return true;
        }
        if (this.savedFocus != null && this.savedFocus.isDisposed()) {
            this.savedFocus = null;
        }
        return this.savedFocus != null && this.savedFocus.setFocus();
    }
    
    void saveFocus() {
        final Control control = this.display._getFocusControl();
        if (control != null && control != this && this == control.menuShell()) {
            this.setSavedFocus(control);
        }
    }
    
    void setBoundsInPixels(final int x, final int y, final int width, final int height, final int flags, final boolean defer) {
        this.swFlags = 4;
        if (OS.IsIconic(this.handle)) {
            this.setPlacement(x, y, width, height, flags);
            return;
        }
        this.forceResize();
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        boolean sameOrigin = true;
        if ((0x2 & flags) == 0x0) {
            sameOrigin = (rect.left == x && rect.top == y);
            if (!sameOrigin) {
                this.moved = true;
            }
        }
        boolean sameExtent = true;
        if ((0x1 & flags) == 0x0) {
            sameExtent = (rect.right - rect.left == width && rect.bottom - rect.top == height);
            if (!sameExtent) {
                this.resized = true;
            }
        }
        if (!OS.IsZoomed(this.handle)) {
            super.setBoundsInPixels(x, y, width, height, flags, defer);
            return;
        }
        if (sameOrigin && sameExtent) {
            return;
        }
        this.setPlacement(x, y, width, height, flags);
        this._setMaximized(false);
    }
    
    public void setDefaultButton(final Button button) {
        this.checkWidget();
        if (button != null) {
            if (button.isDisposed()) {
                this.error(5);
            }
            if (button.menuShell() != this) {
                this.error(32);
            }
        }
        this.setDefaultButton(button, true);
    }
    
    void setDefaultButton(final Button button, final boolean save) {
        if (button == null) {
            if (this.defaultButton == this.saveDefault) {
                if (save) {
                    this.saveDefault = null;
                }
                return;
            }
        }
        else {
            if ((button.style & 0x8) == 0x0) {
                return;
            }
            if (button == this.defaultButton) {
                if (save) {
                    this.saveDefault = this.defaultButton;
                }
                return;
            }
        }
        if (this.defaultButton != null && !this.defaultButton.isDisposed()) {
            this.defaultButton.setDefault(false);
        }
        if ((this.defaultButton = button) == null) {
            this.defaultButton = this.saveDefault;
        }
        if (this.defaultButton != null && !this.defaultButton.isDisposed()) {
            this.defaultButton.setDefault(true);
        }
        if (save) {
            this.saveDefault = this.defaultButton;
        }
        if (this.saveDefault != null && this.saveDefault.isDisposed()) {
            this.saveDefault = null;
        }
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        this.setImages(this.image = image, null);
    }
    
    void setImages(final Image image, Image[] images) {
        if (this.smallImage != null) {
            this.smallImage.dispose();
        }
        if (this.largeImage != null) {
            this.largeImage.dispose();
        }
        final Image image2 = null;
        this.largeImage = image2;
        this.smallImage = image2;
        long hSmallIcon = 0L;
        long hLargeIcon = 0L;
        Image smallIcon = null;
        Image largeIcon = null;
        if (image != null) {
            largeIcon = image;
            smallIcon = image;
        }
        else if (images != null && images.length > 0) {
            final int depth = this.display.getIconDepth();
            ImageData[] datas = null;
            if (images.length > 1) {
                final Image[] bestImages = new Image[images.length];
                System.arraycopy(images, 0, bestImages, 0, images.length);
                datas = new ImageData[images.length];
                for (int i = 0; i < datas.length; ++i) {
                    datas[i] = images[i].getImageData(DPIUtil.getDeviceZoom());
                }
                images = bestImages;
                this.sort(images, datas, OS.GetSystemMetrics(49), OS.GetSystemMetrics(50), depth);
            }
            smallIcon = images[0];
            if (images.length > 1) {
                this.sort(images, datas, OS.GetSystemMetrics(11), OS.GetSystemMetrics(12), depth);
            }
            largeIcon = images[0];
        }
        if (smallIcon != null) {
            switch (smallIcon.type) {
                case 0: {
                    this.smallImage = Display.createIcon(smallIcon);
                    hSmallIcon = this.smallImage.handle;
                    break;
                }
                case 1: {
                    hSmallIcon = smallIcon.handle;
                    break;
                }
            }
        }
        OS.SendMessage(this.handle, 128, 0L, hSmallIcon);
        if (largeIcon != null) {
            switch (largeIcon.type) {
                case 0: {
                    this.largeImage = Display.createIcon(largeIcon);
                    hLargeIcon = this.largeImage.handle;
                    break;
                }
                case 1: {
                    hLargeIcon = largeIcon.handle;
                    break;
                }
            }
        }
        OS.SendMessage(this.handle, 128, 1L, hLargeIcon);
        if (hSmallIcon == 0L && hLargeIcon == 0L && (this.style & 0x800) != 0x0) {
            final int flags = 1025;
            OS.RedrawWindow(this.handle, (RECT)null, 0L, 1025);
        }
    }
    
    public void setImages(final Image[] images) {
        this.checkWidget();
        if (images == null) {
            this.error(5);
        }
        for (final Image image : images) {
            if (image == null || image.isDisposed()) {
                this.error(5);
            }
        }
        this.setImages(null, this.images = images);
    }
    
    public void setMaximized(final boolean maximized) {
        this.checkWidget();
        Display.lpStartupInfo = null;
        this._setMaximized(maximized);
    }
    
    public void setMenuBar(final Menu menu) {
        this.checkWidget();
        if (this.menuBar == menu) {
            return;
        }
        if (menu != null) {
            if (menu.isDisposed()) {
                this.error(5);
            }
            if ((menu.style & 0x2) == 0x0) {
                this.error(33);
            }
            if (menu.parent != this) {
                this.error(32);
            }
        }
        if (menu != null) {
            this.display.removeBar(menu);
        }
        this.menuBar = menu;
        final long hMenu = (this.menuBar != null) ? this.menuBar.handle : 0L;
        OS.SetMenu(this.handle, hMenu);
        this.destroyAccelerators();
    }
    
    public void setMinimized(final boolean minimized) {
        this.checkWidget();
        Display.lpStartupInfo = null;
        this._setMinimized(minimized);
    }
    
    public void setOrientation(final int orientation) {
        super.setOrientation(orientation);
        if (this.menus != null) {
            for (final Menu menu : this.menus) {
                if (menu != null && !menu.isDisposed() && (menu.getStyle() & 0x8) != 0x0) {
                    menu._setOrientation(menu.getOrientation());
                }
            }
        }
    }
    
    void setParent() {
        final long hwndParent = this.parent.handle;
        this.display.lockActiveWindow = true;
        OS.SetParent(this.handle, hwndParent);
        if (!OS.IsWindowVisible(hwndParent)) {
            OS.ShowWindow(this.handle, 8);
        }
        int bits = OS.GetWindowLong(this.handle, -16);
        bits &= 0xBFFFFFFF;
        OS.SetWindowLong(this.handle, -16, bits | Integer.MIN_VALUE);
        OS.SetWindowLongPtr(this.handle, -12, 0L);
        final int flags = 19;
        OS.SetWindowPos(this.handle, 1L, 0, 0, 0, 0, 19);
        this.display.lockActiveWindow = false;
    }
    
    void setPlacement(final int x, final int y, final int width, final int height, final int flags) {
        final WINDOWPLACEMENT lpwndpl = new WINDOWPLACEMENT();
        lpwndpl.length = WINDOWPLACEMENT.sizeof;
        OS.GetWindowPlacement(this.handle, lpwndpl);
        lpwndpl.showCmd = 8;
        if (OS.IsIconic(this.handle)) {
            lpwndpl.showCmd = 7;
        }
        else if (OS.IsZoomed(this.handle)) {
            lpwndpl.showCmd = 3;
        }
        boolean sameOrigin = true;
        if ((flags & 0x2) == 0x0) {
            sameOrigin = (lpwndpl.left != x || lpwndpl.top != y);
            lpwndpl.right = x + (lpwndpl.right - lpwndpl.left);
            lpwndpl.bottom = y + (lpwndpl.bottom - lpwndpl.top);
            lpwndpl.left = x;
            lpwndpl.top = y;
        }
        boolean sameExtent = true;
        if ((flags & 0x1) == 0x0) {
            sameExtent = (lpwndpl.right - lpwndpl.left != width || lpwndpl.bottom - lpwndpl.top != height);
            lpwndpl.right = lpwndpl.left + width;
            lpwndpl.bottom = lpwndpl.top + height;
        }
        OS.SetWindowPlacement(this.handle, lpwndpl);
        if (OS.IsIconic(this.handle)) {
            if (sameOrigin) {
                this.moved = true;
                final Point location = this.getLocationInPixels();
                this.oldX = location.x;
                this.oldY = location.y;
                this.sendEvent(10);
                if (this.isDisposed()) {
                    return;
                }
            }
            if (sameExtent) {
                this.resized = true;
                final Rectangle rect = this.getClientAreaInPixels();
                this.oldWidth = rect.width;
                this.oldHeight = rect.height;
                this.sendEvent(11);
                if (this.isDisposed()) {
                    return;
                }
                if (this.layout != null) {
                    this.markLayout(false, false);
                    this.updateLayout(true, false);
                }
            }
        }
    }
    
    void setSavedFocus(final Control control) {
        this.savedFocus = control;
    }
    
    void setSystemMenu() {
        final long hMenu = OS.GetSystemMenu(this.handle, false);
        if (hMenu == 0L) {
            return;
        }
        final int oldCount = OS.GetMenuItemCount(hMenu);
        if ((this.style & 0x10) == 0x0) {
            OS.DeleteMenu(hMenu, 61440, 0);
        }
        if ((this.style & 0x80) == 0x0) {
            OS.DeleteMenu(hMenu, 61472, 0);
        }
        if ((this.style & 0x400) == 0x0) {
            OS.DeleteMenu(hMenu, 61488, 0);
        }
        if ((this.style & 0x480) == 0x0) {
            OS.DeleteMenu(hMenu, 61728, 0);
        }
        final int newCount = OS.GetMenuItemCount(hMenu);
        if ((this.style & 0x40) == 0x0 || newCount != oldCount) {
            OS.DeleteMenu(hMenu, 61744, 0);
            final MENUITEMINFO info = new MENUITEMINFO();
            info.cbSize = MENUITEMINFO.sizeof;
            info.fMask = 2;
            int index;
            for (index = 0; index < newCount && (!OS.GetMenuItemInfo(hMenu, index, true, info) || info.wID != 61536); ++index) {}
            if (index != newCount) {
                OS.DeleteMenu(hMenu, index - 1, 1024);
                if ((this.style & 0x40) == 0x0) {
                    OS.DeleteMenu(hMenu, 61536, 0);
                }
            }
        }
    }
    
    public void setText(final String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        final TCHAR buffer = new TCHAR(0, string, true);
        if ((this.state & 0x4000) != 0x0) {
            final long hHeap = OS.GetProcessHeap();
            final int byteCount = buffer.length() * 2;
            final long pszText = OS.HeapAlloc(hHeap, 8, byteCount);
            OS.MoveMemory(pszText, buffer, byteCount);
            OS.DefWindowProc(this.handle, 12, 0L, pszText);
            if (pszText != 0L) {
                OS.HeapFree(hHeap, 0, pszText);
            }
        }
        else {
            OS.SetWindowText(this.handle, buffer);
        }
        if ((this.state & 0x400000) != 0x0) {
            this.updateTextDirection(100663296);
        }
    }
    
    public void setVisible(final boolean visible) {
        this.checkWidget();
        if (!this.getDrawing()) {
            if ((this.state & 0x10) == 0x0 == visible) {
                return;
            }
        }
        else if (visible == OS.IsWindowVisible(this.handle)) {
            return;
        }
        if (visible) {
            this.sendEvent(22);
            if (this.isDisposed()) {
                return;
            }
            if (!this.getDrawing()) {
                this.state &= 0xFFFFFFEF;
            }
            else {
                if (this.menuBar != null) {
                    this.display.removeBar(this.menuBar);
                    OS.DrawMenuBar(this.handle);
                }
                final STARTUPINFO lpStartUpInfo = Display.lpStartupInfo;
                if (lpStartUpInfo != null && (lpStartUpInfo.dwFlags & 0x1) != 0x0) {
                    OS.ShowWindow(this.handle, (int)lpStartUpInfo.wShowWindow);
                }
                else {
                    OS.ShowWindow(this.handle, this.swFlags);
                }
                if (this.isDisposed()) {
                    return;
                }
                this.opened = true;
                if (!this.moved) {
                    this.moved = true;
                    final Point location = this.getLocationInPixels();
                    this.oldX = location.x;
                    this.oldY = location.y;
                }
                if (!this.resized) {
                    this.resized = true;
                    final Rectangle rect = this.getClientAreaInPixels();
                    this.oldWidth = rect.width;
                    this.oldHeight = rect.height;
                }
                if (OS.IsAppThemed() || !OS.IsHungAppWindow(this.handle)) {
                    OS.UpdateWindow(this.handle);
                }
            }
        }
        else {
            if (OS.IsIconic(this.handle)) {
                this.swFlags = 7;
            }
            else if (OS.IsZoomed(this.handle)) {
                this.swFlags = 3;
            }
            else {
                this.swFlags = 4;
            }
            if (!this.getDrawing()) {
                this.state |= 0x10;
            }
            else {
                OS.ShowWindow(this.handle, 0);
            }
            if (this.isDisposed()) {
                return;
            }
            this.sendEvent(23);
        }
    }
    
    void sort(final Image[] images, final ImageData[] datas, final int width, final int height, final int depth) {
        final int length = images.length;
        if (length <= 1) {
            return;
        }
        for (int gap = length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < length; ++i) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (this.compare(datas[j], datas[j + gap], width, height, depth) >= 0) {
                        final Image swap = images[j];
                        images[j] = images[j + gap];
                        images[j + gap] = swap;
                        final ImageData swapData = datas[j];
                        datas[j] = datas[j + gap];
                        datas[j + gap] = swapData;
                    }
                }
            }
        }
    }
    
    boolean translateAccelerator(final MSG msg) {
        if (!this.isEnabled() || !this.isActive()) {
            return false;
        }
        if (this.menuBar != null && !this.menuBar.isEnabled()) {
            return false;
        }
        if (this.translateMDIAccelerator(msg) || this.translateMenuAccelerator(msg)) {
            return true;
        }
        final Decorations decorations = this.parent.menuShell();
        return decorations.translateAccelerator(msg);
    }
    
    boolean translateMenuAccelerator(final MSG msg) {
        if (this.hAccel == -1L) {
            this.createAccelerators();
        }
        return this.hAccel != 0L && OS.TranslateAccelerator(this.handle, this.hAccel, msg) != 0;
    }
    
    boolean translateMDIAccelerator(final MSG msg) {
        if (!(this instanceof Shell)) {
            final Shell shell = this.getShell();
            final long hwndMDIClient = shell.hwndMDIClient;
            if (hwndMDIClient != 0L && OS.TranslateMDISysAccel(hwndMDIClient, msg)) {
                return true;
            }
            if (msg.message == 256) {
                if (OS.GetKeyState(17) >= 0) {
                    return false;
                }
                switch ((int)msg.wParam) {
                    case 115: {
                        OS.PostMessage(this.handle, 16, 0L, 0L);
                        return true;
                    }
                    case 117: {
                        if (this.traverseDecorations(true)) {
                            return true;
                        }
                        break;
                    }
                }
                return false;
            }
            else if (msg.message == 260) {
                switch ((int)msg.wParam) {
                    case 115: {
                        OS.PostMessage(shell.handle, 16, 0L, 0L);
                        return true;
                    }
                    default: {
                        return false;
                    }
                }
            }
        }
        return false;
    }
    
    boolean traverseDecorations(final boolean next) {
        Control[] children;
        int length;
        int index;
        for (children = this.parent._getChildren(), length = children.length, index = 0; index < length && children[index] != this; ++index) {}
        final int start = index;
        final int offset = next ? 1 : -1;
        while ((index = (index + offset + length) % length) != start) {
            final Control child = children[index];
            if (!child.isDisposed() && child instanceof Decorations && child.setFocus()) {
                return true;
            }
        }
        return false;
    }
    
    boolean traverseItem(final boolean next) {
        return false;
    }
    
    boolean traverseReturn() {
        if (this.defaultButton == null || this.defaultButton.isDisposed()) {
            return false;
        }
        if (!this.defaultButton.isVisible() || !this.defaultButton.isEnabled()) {
            return false;
        }
        this.defaultButton.click();
        return true;
    }
    
    CREATESTRUCT widgetCreateStruct() {
        return new CREATESTRUCT();
    }
    
    int widgetExtStyle() {
        int bits = super.widgetExtStyle() | 0x40;
        bits &= 0xFFFFFDFF;
        if ((this.style & 0x8) != 0x0) {
            return bits;
        }
        if ((this.style & 0x10) != 0x0) {
            return bits;
        }
        if ((this.style & 0x800) != 0x0) {
            bits |= 0x1;
        }
        return bits;
    }
    
    long widgetParent() {
        final Shell shell = this.getShell();
        return shell.hwndMDIClient();
    }
    
    int widgetStyle() {
        int bits = super.widgetStyle() & 0xEFFEFFFF;
        bits &= 0xFF7FFFFF;
        if ((this.style & 0x8) != 0x0) {
            if (this.parent == null) {
                bits |= 0xA0000;
            }
            return bits;
        }
        if ((this.style & 0x20) != 0x0) {
            bits |= 0xC00000;
        }
        if ((this.style & 0x80) != 0x0) {
            bits |= 0x20000;
        }
        if ((this.style & 0x400) != 0x0) {
            bits |= 0x10000;
        }
        if ((this.style & 0x10) != 0x0) {
            bits |= 0x40000;
        }
        else if ((this.style & 0x800) == 0x0) {
            bits |= 0x800000;
        }
        if ((this.style & 0x40) != 0x0) {
            bits |= 0x80000;
        }
        return bits;
    }
    
    long windowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        switch (msg) {
            case 32768:
            case 32769: {
                if (this.hAccel == -1L) {
                    this.createAccelerators();
                }
                return (msg == 32768) ? this.nAccel : this.hAccel;
            }
            default: {
                return super.windowProc(hwnd, msg, wParam, lParam);
            }
        }
    }
    
    LRESULT WM_ACTIVATE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_ACTIVATE(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (OS.GetParent(lParam) == this.handle) {
            final char[] buffer = new char[128];
            final int length = OS.GetClassName(lParam, buffer, buffer.length);
            final String className = new String(buffer, 0, length);
            if (className.equals("SunAwtWindow")) {
                return LRESULT.ZERO;
            }
        }
        final int loWord = OS.LOWORD(wParam);
        if (loWord != 0) {
            if (OS.HIWORD(wParam) != 0) {
                return result;
            }
            final Control control = this.display.findControl(lParam);
            if ((control == null || control instanceof Shell) && this instanceof Shell) {
                final Event event = new Event();
                event.detail = ((loWord == 2) ? 3 : 0);
                this.sendEvent(26, event);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
            }
            if (this.restoreFocus()) {
                return LRESULT.ZERO;
            }
        }
        else {
            final Display display = this.display;
            final boolean lockWindow = display.isXMouseActive();
            if (lockWindow) {
                display.lockActiveWindow = true;
            }
            final Control control2 = display.findControl(lParam);
            if ((control2 == null || control2 instanceof Shell) && this instanceof Shell) {
                this.sendEvent(27);
                if (!this.isDisposed()) {
                    final Shell shell = this.getShell();
                    shell.setActiveControl(null);
                }
            }
            if (lockWindow) {
                display.lockActiveWindow = false;
            }
            if (this.isDisposed()) {
                return LRESULT.ZERO;
            }
            this.saveFocus();
        }
        return result;
    }
    
    LRESULT WM_CLOSE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_CLOSE(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (this.isEnabled() && this.isActive()) {
            this.closeWidget();
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_KILLFOCUS(final long wParam, final long lParam) {
        final LRESULT result = super.WM_KILLFOCUS(wParam, lParam);
        this.saveFocus();
        return result;
    }
    
    LRESULT WM_MOVE(final long wParam, final long lParam) {
        if (this.moved) {
            final Point location = this.getLocationInPixels();
            if (location.x == this.oldX && location.y == this.oldY) {
                return null;
            }
            this.oldX = location.x;
            this.oldY = location.y;
        }
        return super.WM_MOVE(wParam, lParam);
    }
    
    LRESULT WM_NCACTIVATE(final long wParam, final long lParam) {
        LRESULT result = super.WM_NCACTIVATE(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (wParam == 0L) {
            if (this.display.lockActiveWindow) {
                return LRESULT.ZERO;
            }
            final Control control = this.display.findControl(lParam);
            if (control != null) {
                final Shell shell = this.getShell();
                final Decorations decorations = control.menuShell();
                if (decorations.getShell() == shell) {
                    if (this instanceof Shell) {
                        return LRESULT.ONE;
                    }
                    if (this.display.ignoreRestoreFocus && this.display.lastHittest != 1) {
                        result = LRESULT.ONE;
                    }
                }
            }
        }
        if (!(this instanceof Shell)) {
            final long hwndShell = this.getShell().handle;
            OS.SendMessage(hwndShell, 134, wParam, lParam);
        }
        return result;
    }
    
    LRESULT WM_QUERYOPEN(final long wParam, final long lParam) {
        final LRESULT result = super.WM_QUERYOPEN(wParam, lParam);
        if (result != null) {
            return result;
        }
        this.sendEvent(20);
        return result;
    }
    
    LRESULT WM_SETFOCUS(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SETFOCUS(wParam, lParam);
        if (this.isDisposed()) {
            return result;
        }
        if (this.savedFocus != this) {
            this.restoreFocus();
        }
        return result;
    }
    
    LRESULT WM_SIZE(final long wParam, final long lParam) {
        LRESULT result = null;
        boolean changed = true;
        if (this.resized) {
            int newWidth = 0;
            int newHeight = 0;
            switch ((int)wParam) {
                case 2: {
                    OS.GetWindowRect(this.handle, this.maxRect);
                }
                case 0: {
                    newWidth = OS.LOWORD(lParam);
                    newHeight = OS.HIWORD(lParam);
                    break;
                }
                case 1: {
                    final Rectangle rect = this.getClientAreaInPixels();
                    newWidth = rect.width;
                    newHeight = rect.height;
                    break;
                }
            }
            changed = (newWidth != this.oldWidth || newHeight != this.oldHeight);
            if (changed) {
                this.oldWidth = newWidth;
                this.oldHeight = newHeight;
            }
        }
        if (changed) {
            result = super.WM_SIZE(wParam, lParam);
            if (this.isDisposed()) {
                return result;
            }
        }
        if (wParam == 1L) {
            this.sendEvent(19);
        }
        return result;
    }
    
    LRESULT WM_SYSCOMMAND(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SYSCOMMAND(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (!(this instanceof Shell)) {
            final int cmd = (int)wParam & 0xFFF0;
            switch (cmd) {
                case 61536: {
                    OS.PostMessage(this.handle, 16, 0L, 0L);
                    return LRESULT.ZERO;
                }
                case 61504: {
                    this.traverseDecorations(true);
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
        if (this.display.lockActiveWindow) {
            final WINDOWPOS lpwp = new WINDOWPOS();
            OS.MoveMemory(lpwp, lParam, WINDOWPOS.sizeof);
            final WINDOWPOS windowpos2;
            final WINDOWPOS windowpos = windowpos2 = lpwp;
            windowpos2.flags |= 0x4;
            OS.MoveMemory(lParam, lpwp, WINDOWPOS.sizeof);
        }
        return result;
    }
}
