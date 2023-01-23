//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class Menu extends Widget
{
    public long handle;
    int x;
    int y;
    long hBrush;
    int foreground;
    int background;
    Image backgroundImage;
    boolean hasLocation;
    MenuItem cascade;
    Decorations parent;
    MenuItem selectedMenuItem;
    static final int ID_TOOLTIP_TIMER = 110;
    
    public Menu(final Control parent) {
        this(checkNull(parent).menuShell(), 8);
    }
    
    public Menu(final Decorations parent, final int style) {
        this(parent, checkStyle(style), 0L);
    }
    
    public Menu(final Menu parentMenu) {
        this(checkNull(parentMenu).parent, 4);
    }
    
    public Menu(final MenuItem parentItem) {
        this(checkNull(parentItem).parent);
    }
    
    Menu(final Decorations parent, final int style, final long handle) {
        super((Widget)parent, checkStyle(style));
        this.foreground = -1;
        this.background = -1;
        this.parent = parent;
        this.handle = handle;
        this.createWidget();
    }
    
    void _setVisible(final boolean visible) {
        if ((this.style & 0x6) != 0x0) {
            return;
        }
        final long hwndParent = this.parent.handle;
        if (visible) {
            int flags = 0;
            if (OS.GetKeyState(1) >= 0) {
                flags |= 0x2;
            }
            if ((this.style & 0x4000000) != 0x0) {
                flags |= 0x8;
            }
            if ((this.parent.style & 0x8000000) != 0x0) {
                flags &= 0xFFFFFFF7;
                if ((this.style & 0x2000000) != 0x0) {
                    flags |= 0x8;
                }
            }
            int nX = this.x;
            int nY = this.y;
            if (!this.hasLocation) {
                final int pos = OS.GetMessagePos();
                nX = OS.GET_X_LPARAM((long)pos);
                nY = OS.GET_Y_LPARAM((long)pos);
            }
            this.hasLocation = false;
            final Display display = this.display;
            display.sendPreExternalEventDispatchEvent();
            final boolean success = OS.TrackPopupMenu(this.handle, flags, nX, nY, 0, hwndParent, (RECT)null);
            display.sendPostExternalEventDispatchEvent();
            if (!success && OS.GetMenuItemCount(this.handle) == 0) {
                OS.SendMessage(hwndParent, 287, OS.MAKEWPARAM(0, 65535), 0L);
            }
        }
        else {
            OS.SendMessage(hwndParent, 31, 0L, 0L);
        }
        final long hFocus = OS.GetFocus();
        if (hFocus != 0L) {
            OS.NotifyWinEvent(32773, hFocus, -4, 0);
        }
    }
    
    public void addHelpListener(final HelpListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(28, (Listener)typedListener);
    }
    
    public void addMenuListener(final MenuListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(23, (Listener)typedListener);
        this.addListener(22, (Listener)typedListener);
    }
    
    static Control checkNull(final Control control) {
        if (control == null) {
            SWT.error(4);
        }
        return control;
    }
    
    static Menu checkNull(final Menu menu) {
        if (menu == null) {
            SWT.error(4);
        }
        return menu;
    }
    
    static MenuItem checkNull(final MenuItem item) {
        if (item == null) {
            SWT.error(4);
        }
        return item;
    }
    
    static int checkStyle(final int style) {
        return Widget.checkBits(style, 8, 2, 4, 0, 0, 0);
    }
    
    void createHandle() {
        if (this.handle != 0L) {
            return;
        }
        if ((this.style & 0x2) != 0x0) {
            this.handle = OS.CreateMenu();
        }
        else {
            this.handle = OS.CreatePopupMenu();
        }
        if (this.handle == 0L) {
            this.error(2);
        }
        this.updateBackground();
    }
    
    void createItem(final MenuItem item, final int index) {
        final int count = OS.GetMenuItemCount(this.handle);
        if (0 > index || index > count) {
            this.error(6);
        }
        this.display.addMenuItem(item);
        final long hHeap = OS.GetProcessHeap();
        final long pszText = OS.HeapAlloc(hHeap, 8, 4);
        OS.MoveMemory(pszText, new char[] { ' ', '\0' }, 4);
        final MENUITEMINFO info = new MENUITEMINFO();
        info.cbSize = MENUITEMINFO.sizeof;
        info.fMask = 50;
        info.wID = item.id;
        info.dwItemData = item.id;
        info.fType = item.widgetStyle();
        info.dwTypeData = pszText;
        final boolean success = OS.InsertMenuItem(this.handle, index, true, info);
        if (pszText != 0L) {
            OS.HeapFree(hHeap, 0, pszText);
        }
        if (!success) {
            this.display.removeMenuItem(item);
            this.error(14);
        }
        if (this.needsMenuCallback()) {
            info.fMask = 128;
            info.hbmpItem = -1L;
            OS.SetMenuItemInfo(this.handle, index, true, info);
        }
        this.redraw();
    }
    
    void createWidget() {
        this.checkOrientation((Widget)this.parent);
        this.initThemeColors();
        this.createHandle();
        this.parent.addMenu(this);
    }
    
    int defaultBackground() {
        return OS.GetSysColor(4);
    }
    
    int defaultForeground() {
        return OS.GetSysColor(7);
    }
    
    void destroyAccelerators() {
        this.parent.destroyAccelerators();
    }
    
    void destroyItem(final MenuItem item) {
        if (!OS.DeleteMenu(this.handle, item.id, 0)) {
            this.error(15);
        }
        this.redraw();
    }
    
    @Override
    void destroyWidget() {
        final MenuItem cascade = this.cascade;
        final long hMenu = this.handle;
        this.releaseHandle();
        if (cascade != null) {
            cascade.setMenu(null, true);
        }
        else if (hMenu != 0L) {
            OS.DestroyMenu(hMenu);
        }
    }
    
    void fixMenus(final Decorations newParent) {
        if (this.isDisposed()) {
            return;
        }
        for (final MenuItem item : this.getItems()) {
            item.fixMenus(newParent);
        }
        this.parent.removeMenu(this);
        newParent.addMenu(this);
        this.parent = newParent;
    }
    
    Color getBackground() {
        this.checkWidget();
        return Color.win32_new((Device)this.display, (this.background != -1) ? this.background : this.defaultBackground());
    }
    
    Image getBackgroundImage() {
        this.checkWidget();
        return this.backgroundImage;
    }
    
    Rectangle getBounds() {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            if (this.parent.menuBar != this) {
                return new Rectangle(0, 0, 0, 0);
            }
            final long hwndShell = this.parent.handle;
            final MENUBARINFO info = new MENUBARINFO();
            info.cbSize = MENUBARINFO.sizeof;
            if (OS.GetMenuBarInfo(hwndShell, -3, 0, info)) {
                final int width = info.right - info.left;
                final int height = info.bottom - info.top;
                return new Rectangle(info.left, info.top, width, height);
            }
        }
        else {
            final int count = OS.GetMenuItemCount(this.handle);
            if (count != 0) {
                final RECT rect1 = new RECT();
                if (OS.GetMenuItemRect(0L, this.handle, 0, rect1)) {
                    final RECT rect2 = new RECT();
                    if (OS.GetMenuItemRect(0L, this.handle, count - 1, rect2)) {
                        final int x = rect1.left - 2;
                        final int y = rect1.top - 2;
                        final int width2 = rect2.right - rect2.left + 4;
                        final int height2 = rect2.bottom - rect1.top + 4;
                        return new Rectangle(x, y, width2, height2);
                    }
                }
            }
        }
        return new Rectangle(0, 0, 0, 0);
    }
    
    public MenuItem getDefaultItem() {
        this.checkWidget();
        final int id = OS.GetMenuDefaultItem(this.handle, 0, 1);
        if (id == -1) {
            return null;
        }
        final MENUITEMINFO info = new MENUITEMINFO();
        info.cbSize = MENUITEMINFO.sizeof;
        info.fMask = 2;
        if (OS.GetMenuItemInfo(this.handle, id, false, info)) {
            return this.display.getMenuItem(info.wID);
        }
        return null;
    }
    
    public boolean getEnabled() {
        this.checkWidget();
        return (this.state & 0x8) == 0x0;
    }
    
    Color getForeground() {
        this.checkWidget();
        return Color.win32_new((Device)this.display, (this.foreground != -1) ? this.foreground : this.defaultForeground());
    }
    
    public MenuItem getItem(final int index) {
        this.checkWidget();
        int id = 0;
        final MENUITEMINFO info = new MENUITEMINFO();
        info.cbSize = MENUITEMINFO.sizeof;
        info.fMask = 32;
        if (!OS.GetMenuItemInfo(this.handle, index, true, info)) {
            this.error(6);
        }
        id = (int)info.dwItemData;
        return this.display.getMenuItem(id);
    }
    
    public int getItemCount() {
        this.checkWidget();
        return OS.GetMenuItemCount(this.handle);
    }
    
    public MenuItem[] getItems() {
        this.checkWidget();
        int index = 0;
        int count = 0;
        final int length = OS.GetMenuItemCount(this.handle);
        if (length < 0) {
            final int error = OS.GetLastError();
            SWT.error(36, (Throwable)null, " [GetLastError=0x" + Integer.toHexString(error));
        }
        MenuItem[] items = new MenuItem[length];
        final MENUITEMINFO info = new MENUITEMINFO();
        info.cbSize = MENUITEMINFO.sizeof;
        info.fMask = 32;
        while (OS.GetMenuItemInfo(this.handle, index, true, info)) {
            if (count == items.length) {
                final MenuItem[] newItems = new MenuItem[count + 4];
                System.arraycopy(items, 0, newItems, 0, count);
                items = newItems;
            }
            final MenuItem item = this.display.getMenuItem((int)info.dwItemData);
            if (item != null) {
                items[count++] = item;
            }
            ++index;
        }
        if (count == items.length) {
            return items;
        }
        final MenuItem[] result = new MenuItem[count];
        System.arraycopy(items, 0, result, 0, count);
        return result;
    }
    
    @Override
    String getNameText() {
        String result = "";
        final MenuItem[] items = this.getItems();
        final int length = items.length;
        if (length > 0) {
            for (int i = 0; i <= length - 1; ++i) {
                result = result + ((items[i] == null) ? "null" : items[i].getNameText()) + ((i < length - 1) ? ", " : "");
            }
        }
        return result;
    }
    
    public int getOrientation() {
        this.checkWidget();
        return this.style & 0x6000000;
    }
    
    public Decorations getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public MenuItem getParentItem() {
        this.checkWidget();
        return this.cascade;
    }
    
    public Menu getParentMenu() {
        this.checkWidget();
        if (this.cascade != null) {
            return this.cascade.parent;
        }
        return null;
    }
    
    public Shell getShell() {
        this.checkWidget();
        return this.parent.getShell();
    }
    
    public boolean getVisible() {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            return this == this.parent.menuShell().menuBar;
        }
        if ((this.style & 0x8) != 0x0) {
            final Menu[] popups = this.display.popups;
            if (popups == null) {
                return false;
            }
            for (final Menu popup : popups) {
                if (popup == this) {
                    return true;
                }
            }
        }
        final Shell shell = this.getShell();
        Menu menu;
        for (menu = shell.activeMenu; menu != null && menu != this; menu = menu.getParentMenu()) {}
        return this == menu;
    }
    
    void hideCurrentToolTip() {
        if (this.selectedMenuItem != null) {
            this.selectedMenuItem.hideToolTip();
        }
    }
    
    public int indexOf(final MenuItem item) {
        this.checkWidget();
        if (item == null) {
            this.error(4);
        }
        if (item.isDisposed()) {
            this.error(5);
        }
        if (item.parent != this) {
            return -1;
        }
        int index = 0;
        final MENUITEMINFO info = new MENUITEMINFO();
        info.cbSize = MENUITEMINFO.sizeof;
        info.fMask = 32;
        while (OS.GetMenuItemInfo(this.handle, index, true, info)) {
            if (info.dwItemData == item.id) {
                return index;
            }
            ++index;
        }
        return -1;
    }
    
    void initThemeColors() {
        if ((this.style & 0x2) != 0x0) {
            this.foreground = this.display.menuBarForegroundPixel;
            this.background = this.display.menuBarBackgroundPixel;
        }
    }
    
    public boolean isEnabled() {
        this.checkWidget();
        final Menu parentMenu = this.getParentMenu();
        if (parentMenu == null) {
            return this.getEnabled() && this.parent.isEnabled();
        }
        return this.getEnabled() && parentMenu.isEnabled();
    }
    
    public boolean isVisible() {
        this.checkWidget();
        return this.getVisible();
    }
    
    boolean needsMenuCallback() {
        return this.background != -1 || this.backgroundImage != null || this.foreground != -1;
    }
    
    void redraw() {
        if (!this.isVisible()) {
            return;
        }
        if ((this.style & 0x2) != 0x0) {
            this.display.addBar(this);
        }
        else {
            this.update();
        }
    }
    
    @Override
    void releaseHandle() {
        super.releaseHandle();
        this.handle = 0L;
        this.cascade = null;
    }
    
    @Override
    void releaseChildren(final boolean destroy) {
        for (final MenuItem item : this.getItems()) {
            if (item != null && !item.isDisposed()) {
                item.release(false);
            }
        }
        super.releaseChildren(destroy);
    }
    
    @Override
    void releaseParent() {
        super.releaseParent();
        if ((this.style & 0x2) != 0x0) {
            this.display.removeBar(this);
            if (this == this.parent.menuBar) {
                this.parent.setMenuBar((Menu)null);
            }
        }
        else if ((this.style & 0x8) != 0x0) {
            this.display.removePopup(this);
        }
    }
    
    @Override
    void releaseWidget() {
        super.releaseWidget();
        this.backgroundImage = null;
        if (this.hBrush != 0L) {
            OS.DeleteObject(this.hBrush);
        }
        this.hBrush = 0L;
        if (this.parent != null) {
            this.parent.removeMenu(this);
        }
        this.parent = null;
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
    
    public void removeMenuListener(final MenuListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(23, (SWTEventListener)listener);
        this.eventTable.unhook(22, (SWTEventListener)listener);
    }
    
    @Override
    void reskinChildren(final int flags) {
        for (final MenuItem item : this.getItems()) {
            item.reskin(flags);
        }
        super.reskinChildren(flags);
    }
    
    void setBackground(final Color color) {
        this.checkWidget();
        int pixel = -1;
        if (color != null) {
            if (color.isDisposed()) {
                this.error(5);
            }
            pixel = color.handle;
        }
        if (pixel == this.background) {
            return;
        }
        this.background = pixel;
        this.updateBackground();
    }
    
    void setBackgroundImage(final Image image) {
        this.checkWidget();
        if (image != null) {
            if (image.isDisposed()) {
                this.error(5);
            }
            if (image.type != 0) {
                this.error(5);
            }
        }
        if (this.backgroundImage == image) {
            return;
        }
        this.backgroundImage = image;
        this.updateBackground();
    }
    
    void setForeground(final Color color) {
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
        this.foreground = pixel;
        this.updateForeground();
    }
    
    public void setDefaultItem(final MenuItem item) {
        this.checkWidget();
        int newID = -1;
        if (item != null) {
            if (item.isDisposed()) {
                this.error(5);
            }
            if (item.parent != this) {
                return;
            }
            newID = item.id;
        }
        final int oldID = OS.GetMenuDefaultItem(this.handle, 0, 1);
        if (newID == oldID) {
            return;
        }
        OS.SetMenuDefaultItem(this.handle, newID, 0);
        this.redraw();
    }
    
    public void setEnabled(final boolean enabled) {
        this.checkWidget();
        this.state &= 0xFFFFFFF7;
        if (!enabled) {
            this.state |= 0x8;
        }
    }
    
    public void setLocation(final int x, final int y) {
        this.checkWidget();
        this.setLocationInPixels(DPIUtil.autoScaleUp(x), DPIUtil.autoScaleUp(y));
    }
    
    void setLocationInPixels(final int x, final int y) {
        if ((this.style & 0x6) != 0x0) {
            return;
        }
        this.x = x;
        this.y = y;
        this.hasLocation = true;
    }
    
    public void setLocation(Point location) {
        this.checkWidget();
        if (location == null) {
            this.error(4);
        }
        location = DPIUtil.autoScaleUp(location);
        this.setLocationInPixels(location.x, location.y);
    }
    
    public void setOrientation(final int orientation) {
        this.checkWidget();
        if ((this.style & 0x6) != 0x0) {
            return;
        }
        this._setOrientation(orientation);
    }
    
    void _setOrientation(final int orientation) {
        final int flags = 100663296;
        if ((orientation & 0x6000000) == 0x0 || (orientation & 0x6000000) == 0x6000000) {
            return;
        }
        this.style &= 0xF9FFFFFF;
        this.style |= (orientation & 0x6000000);
        this.style &= Integer.MAX_VALUE;
        for (final MenuItem itm : this.getItems()) {
            itm.setOrientation(orientation);
        }
    }
    
    public void setVisible(final boolean visible) {
        this.checkWidget();
        if ((this.style & 0x6) != 0x0) {
            return;
        }
        if (visible) {
            this.display.addPopup(this);
        }
        else {
            this.display.removePopup(this);
            this._setVisible(false);
        }
    }
    
    void update() {
        if ((this.style & 0x2) != 0x0) {
            if (this == this.parent.menuBar) {
                OS.DrawMenuBar(this.parent.handle);
            }
            return;
        }
        boolean hasCheck = false;
        boolean hasImage = false;
        for (final MenuItem item : this.getItems()) {
            if (item.image != null && (hasImage = true) && hasCheck) {
                break;
            }
            if ((item.style & 0x30) != 0x0 && (hasCheck = true) && hasImage) {
                break;
            }
        }
        final MENUINFO lpcmi = new MENUINFO();
        lpcmi.cbSize = MENUINFO.sizeof;
        lpcmi.fMask = 16;
        OS.GetMenuInfo(this.handle, lpcmi);
        if (hasImage && !hasCheck) {
            final MENUINFO menuinfo3;
            final MENUINFO menuinfo = menuinfo3 = lpcmi;
            menuinfo3.dwStyle |= 0x4000000;
        }
        else {
            final MENUINFO menuinfo4;
            final MENUINFO menuinfo2 = menuinfo4 = lpcmi;
            menuinfo4.dwStyle &= 0xFBFFFFFF;
        }
        OS.SetMenuInfo(this.handle, lpcmi);
    }
    
    void updateBackground() {
        if (this.hBrush != 0L) {
            OS.DeleteObject(this.hBrush);
        }
        this.hBrush = 0L;
        if (this.backgroundImage != null) {
            this.hBrush = OS.CreatePatternBrush(this.backgroundImage.handle);
        }
        else if (this.background != -1) {
            this.hBrush = OS.CreateSolidBrush(this.background);
        }
        final MENUINFO lpcmi = new MENUINFO();
        lpcmi.cbSize = MENUINFO.sizeof;
        lpcmi.fMask = 2;
        lpcmi.hbrBack = this.hBrush;
        OS.SetMenuInfo(this.handle, lpcmi);
    }
    
    void updateForeground() {
        final MENUITEMINFO info = new MENUITEMINFO();
        info.cbSize = MENUITEMINFO.sizeof;
        for (int index = 0; OS.GetMenuItemInfo(this.handle, index, true, info); ++index) {
            info.fMask = 128;
            info.hbmpItem = -1L;
            OS.SetMenuItemInfo(this.handle, index, true, info);
        }
        this.redraw();
    }
    
    LRESULT wmTimer(final long wParam, final long lParam) {
        if (wParam == 110L) {
            final POINT pt = new POINT();
            OS.GetCursorPos(pt);
            if (this.selectedMenuItem != null) {
                final RECT rect = new RECT();
                final boolean success = OS.GetMenuItemRect(0L, this.selectedMenuItem.parent.handle, this.selectedMenuItem.index, rect);
                if (!success) {
                    return null;
                }
                if (OS.PtInRect(rect, pt)) {
                    this.selectedMenuItem.showTooltip(pt.x, pt.y + OS.GetSystemMetrics(14) / 2 + 5);
                }
                else {
                    this.selectedMenuItem.showTooltip((rect.right + rect.left) / 2, rect.bottom + 5);
                }
            }
        }
        return null;
    }
}
