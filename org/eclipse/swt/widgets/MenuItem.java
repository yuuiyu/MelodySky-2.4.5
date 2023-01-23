//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;

public class MenuItem extends Item
{
    Menu parent;
    Menu menu;
    long hBitmap;
    int id;
    int accelerator;
    int userId;
    int index;
    ToolTip itemToolTip;
    static final int MARGIN_WIDTH = 1;
    static final int MARGIN_HEIGHT = 1;
    
    public MenuItem(final Menu parent, final int style) {
        super((Widget)parent, checkStyle(style));
        (this.parent = parent).createItem(this, this.index = parent.getItemCount());
    }
    
    public MenuItem(final Menu parent, final int style, final int index) {
        super((Widget)parent, checkStyle(style));
        (this.parent = parent).createItem(this, this.index = index);
    }
    
    MenuItem(final Menu parent, final Menu menu, final int style, final int index) {
        super((Widget)parent, checkStyle(style));
        this.parent = parent;
        this.menu = menu;
        this.index = index;
        if (menu != null) {
            menu.cascade = this;
        }
        this.display.addMenuItem(this);
    }
    
    public void addArmListener(final ArmListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(30, (Listener)typedListener);
    }
    
    public void addHelpListener(final HelpListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener((SWTEventListener)listener);
        this.addListener(28, (Listener)typedListener);
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
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    static int checkStyle(final int style) {
        return Widget.checkBits(style, 8, 32, 16, 2, 64, 0);
    }
    
    void destroyWidget() {
        this.parent.destroyItem(this);
        this.releaseHandle();
    }
    
    boolean fillAccel(final ACCEL accel) {
        final byte fVirt2 = 0;
        accel.fVirt = 0;
        final short n = 0;
        accel.key = 0;
        accel.cmd = 0;
        if (this.accelerator == 0 || !this.getEnabled()) {
            return false;
        }
        if ((this.accelerator & 0x400000) != 0x0) {
            return false;
        }
        int fVirt3 = 1;
        int key = this.accelerator & 0x100FFFF;
        int vKey = Display.untranslateKey(key);
        if (vKey != 0) {
            key = vKey;
        }
        else {
            switch (key) {
                case 27: {
                    key = 27;
                    break;
                }
                case 127: {
                    key = 46;
                    break;
                }
                default: {
                    if (key == 0) {
                        return false;
                    }
                    vKey = OS.VkKeyScan((short)key);
                    if (vKey != -1) {
                        key = (vKey & 0xFF);
                        break;
                    }
                    if (key != (int)OS.CharUpper((long)(short)key)) {
                        fVirt3 = 0;
                        break;
                    }
                    break;
                }
            }
        }
        accel.key = (short)key;
        accel.cmd = (short)this.id;
        accel.fVirt = (byte)fVirt3;
        if ((this.accelerator & 0x10000) != 0x0) {
            accel.fVirt |= 0x10;
        }
        if ((this.accelerator & 0x20000) != 0x0) {
            accel.fVirt |= 0x4;
        }
        if ((this.accelerator & 0x40000) != 0x0) {
            accel.fVirt |= 0x8;
        }
        return true;
    }
    
    void fixMenus(final Decorations newParent) {
        if (this.menu != null && !this.menu.isDisposed() && !newParent.isDisposed()) {
            this.menu.fixMenus(newParent);
        }
    }
    
    public int getAccelerator() {
        this.checkWidget();
        return this.accelerator;
    }
    
    Rectangle getBounds() {
        this.checkWidget();
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return new Rectangle(0, 0, 0, 0);
        }
        if ((this.parent.style & 0x2) != 0x0) {
            final Decorations shell = this.parent.parent;
            if (shell.menuBar != this.parent) {
                return new Rectangle(0, 0, 0, 0);
            }
            final long hwndShell = shell.handle;
            final MENUBARINFO info1 = new MENUBARINFO();
            info1.cbSize = MENUBARINFO.sizeof;
            if (!OS.GetMenuBarInfo(hwndShell, -3, 1, info1)) {
                return new Rectangle(0, 0, 0, 0);
            }
            final MENUBARINFO info2 = new MENUBARINFO();
            info2.cbSize = MENUBARINFO.sizeof;
            if (!OS.GetMenuBarInfo(hwndShell, -3, index + 1, info2)) {
                return new Rectangle(0, 0, 0, 0);
            }
            final int x = info2.left - info1.left;
            final int y = info2.top - info1.top;
            final int width = info2.right - info2.left;
            final int height = info2.bottom - info2.top;
            return new Rectangle(x, y, width, height);
        }
        else {
            final long hMenu = this.parent.handle;
            final RECT rect1 = new RECT();
            if (!OS.GetMenuItemRect(0L, hMenu, 0, rect1)) {
                return new Rectangle(0, 0, 0, 0);
            }
            final RECT rect2 = new RECT();
            if (!OS.GetMenuItemRect(0L, hMenu, index, rect2)) {
                return new Rectangle(0, 0, 0, 0);
            }
            final int x2 = rect2.left - rect1.left + 2;
            final int y2 = rect2.top - rect1.top + 2;
            final int width2 = rect2.right - rect2.left;
            final int height2 = rect2.bottom - rect2.top;
            return new Rectangle(x2, y2, width2, height2);
        }
    }
    
    public boolean getEnabled() {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            return (this.state & 0x8) == 0x0;
        }
        final long hMenu = this.parent.handle;
        final MENUITEMINFO info = new MENUITEMINFO();
        info.cbSize = MENUITEMINFO.sizeof;
        info.fMask = 1;
        final boolean success = OS.GetMenuItemInfo(hMenu, this.id, false, info);
        if (!success) {
            this.error(31);
        }
        return (info.fState & 0x3) == 0x0;
    }
    
    public int getID() {
        this.checkWidget();
        return this.userId;
    }
    
    public Menu getMenu() {
        this.checkWidget();
        return this.menu;
    }
    
    String getNameText() {
        if ((this.style & 0x2) != 0x0) {
            return "|";
        }
        return super.getNameText();
    }
    
    public Menu getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public boolean getSelection() {
        this.checkWidget();
        if ((this.style & 0x30) == 0x0) {
            return false;
        }
        final long hMenu = this.parent.handle;
        final MENUITEMINFO info = new MENUITEMINFO();
        info.cbSize = MENUITEMINFO.sizeof;
        info.fMask = 1;
        final boolean success = OS.GetMenuItemInfo(hMenu, this.id, false, info);
        if (!success) {
            this.error(9);
        }
        return (info.fState & 0x8) != 0x0;
    }
    
    public String getToolTipText() {
        this.checkWidget();
        return (this.itemToolTip == null || this.itemToolTip.isDisposed()) ? null : this.itemToolTip.getMessage();
    }
    
    void hideToolTip() {
        if (this.itemToolTip == null || this.itemToolTip.isDisposed()) {
            return;
        }
        this.itemToolTip.setVisible(false);
    }
    
    public boolean isEnabled() {
        return this.getEnabled() && this.parent.isEnabled();
    }
    
    void releaseChildren(final boolean destroy) {
        if (this.menu != null) {
            this.menu.release(false);
            this.menu = null;
        }
        super.releaseChildren(destroy);
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
        this.id = -1;
    }
    
    void releaseParent() {
        super.releaseParent();
        if (this.menu != null) {
            this.menu.dispose();
        }
        this.menu = null;
    }
    
    void releaseWidget() {
        super.releaseWidget();
        if (this.hBitmap != 0L) {
            OS.DeleteObject(this.hBitmap);
        }
        this.hBitmap = 0L;
        if (this.accelerator != 0) {
            this.parent.destroyAccelerators();
        }
        this.accelerator = 0;
        if (this.itemToolTip != null && !this.itemToolTip.isDisposed()) {
            this.itemToolTip.setVisible(false);
            this.itemToolTip.dispose();
            this.itemToolTip = null;
        }
        this.display.removeMenuItem(this);
    }
    
    public void removeArmListener(final ArmListener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(30, (SWTEventListener)listener);
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
    
    void reskinChildren(final int flags) {
        if (this.menu != null) {
            this.menu.reskin(flags);
        }
        super.reskinChildren(flags);
    }
    
    void selectRadio() {
        int index;
        MenuItem[] items;
        for (index = 0, items = this.parent.getItems(); index < items.length && items[index] != this; ++index) {}
        for (int i = index - 1; i >= 0 && items[i].setRadioSelection(false); --i) {}
        for (int j = index + 1; j < items.length && items[j].setRadioSelection(false); ++j) {}
        this.setSelection(true);
    }
    
    public void setAccelerator(final int accelerator) {
        this.checkWidget();
        if (this.accelerator == accelerator) {
            return;
        }
        this.accelerator = accelerator;
        this.parent.destroyAccelerators();
    }
    
    public void setEnabled(final boolean enabled) {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            if (enabled) {
                this.state &= 0xFFFFFFF7;
            }
            else {
                this.state |= 0x8;
            }
        }
        final long hMenu = this.parent.handle;
        final MENUITEMINFO info = new MENUITEMINFO();
        info.cbSize = MENUITEMINFO.sizeof;
        info.fMask = 1;
        boolean success = OS.GetMenuItemInfo(hMenu, this.id, false, info);
        if (!success) {
            final int error = OS.GetLastError();
            SWT.error(30, (Throwable)null, " [GetLastError=0x" + Integer.toHexString(error));
        }
        final int bits = 3;
        if (enabled) {
            if ((info.fState & 0x3) == 0x0) {
                return;
            }
            final MENUITEMINFO menuiteminfo3;
            final MENUITEMINFO menuiteminfo = menuiteminfo3 = info;
            menuiteminfo3.fState &= 0xFFFFFFFC;
        }
        else {
            if ((info.fState & 0x3) == 0x3) {
                return;
            }
            final MENUITEMINFO menuiteminfo4;
            final MENUITEMINFO menuiteminfo2 = menuiteminfo4 = info;
            menuiteminfo4.fState |= 0x3;
        }
        success = OS.SetMenuItemInfo(hMenu, this.id, false, info);
        if (!success) {
            success = (this.id == OS.GetMenuDefaultItem(hMenu, 0, 1));
            if (!success) {
                final int error2 = OS.GetLastError();
                SWT.error(30, (Throwable)null, " [GetLastError=0x" + Integer.toHexString(error2));
            }
        }
        this.parent.destroyAccelerators();
        this.parent.redraw();
    }
    
    public void setID(final int id) {
        this.checkWidget();
        if (id < 0) {
            this.error(5);
        }
        this.userId = id;
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if (this.image == image) {
            return;
        }
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        super.setImage(image);
        final MENUITEMINFO info = new MENUITEMINFO();
        info.cbSize = MENUITEMINFO.sizeof;
        info.fMask = 128;
        if (this.parent.needsMenuCallback()) {
            info.hbmpItem = -1L;
        }
        else if (OS.IsAppThemed()) {
            if (this.hBitmap != 0L) {
                OS.DeleteObject(this.hBitmap);
            }
            final MENUITEMINFO menuiteminfo = info;
            final long n = (image != null) ? Display.create32bitDIB(image) : 0L;
            this.hBitmap = n;
            menuiteminfo.hbmpItem = n;
        }
        else {
            info.hbmpItem = ((image != null) ? -1L : 0L);
        }
        final long hMenu = this.parent.handle;
        OS.SetMenuItemInfo(hMenu, this.id, false, info);
        this.parent.redraw();
    }
    
    public void setMenu(final Menu menu) {
        this.checkWidget();
        if ((this.style & 0x40) == 0x0) {
            this.error(27);
        }
        if (menu != null) {
            if (menu.isDisposed()) {
                this.error(5);
            }
            if ((menu.style & 0x4) == 0x0) {
                this.error(21);
            }
            if (menu.parent != this.parent.parent) {
                this.error(32);
            }
        }
        this.setMenu(menu, false);
    }
    
    void setMenu(final Menu menu, final boolean dispose) {
        final Menu oldMenu = this.menu;
        if (oldMenu == menu) {
            return;
        }
        if (oldMenu != null) {
            oldMenu.cascade = null;
        }
        this.menu = menu;
        final long hMenu = this.parent.handle;
        final MENUITEMINFO info = new MENUITEMINFO();
        info.cbSize = MENUITEMINFO.sizeof;
        info.fMask = 32;
        int index;
        for (index = 0; OS.GetMenuItemInfo(hMenu, index, true, info) && info.dwItemData != this.id; ++index) {}
        if (info.dwItemData != this.id) {
            return;
        }
        final int cch = 128;
        final long hHeap = OS.GetProcessHeap();
        final int byteCount = 256;
        final long pszText = OS.HeapAlloc(hHeap, 8, 256);
        info.fMask = 35;
        final MENUITEMINFO menuiteminfo3;
        final MENUITEMINFO menuiteminfo = menuiteminfo3 = info;
        menuiteminfo3.fMask |= 0xC0;
        info.dwTypeData = pszText;
        info.cch = 128;
        boolean success = OS.GetMenuItemInfo(hMenu, index, true, info);
        if (menu != null) {
            menu.cascade = this;
            final MENUITEMINFO menuiteminfo4;
            final MENUITEMINFO menuiteminfo2 = menuiteminfo4 = info;
            menuiteminfo4.fMask |= 0x4;
            info.hSubMenu = menu.handle;
        }
        if (dispose || oldMenu == null) {
            success = OS.SetMenuItemInfo(hMenu, index, true, info);
        }
        else {
            OS.RemoveMenu(hMenu, index, 1024);
            success = OS.InsertMenuItem(hMenu, index, true, info);
        }
        if (pszText != 0L) {
            OS.HeapFree(hHeap, 0, pszText);
        }
        if (!success) {
            final int error = OS.GetLastError();
            SWT.error(29, (Throwable)null, " [GetLastError=0x" + Integer.toHexString(error));
        }
        this.parent.destroyAccelerators();
    }
    
    boolean setRadioSelection(final boolean value) {
        if ((this.style & 0x10) == 0x0) {
            return false;
        }
        if (this.getSelection() != value) {
            this.setSelection(value);
            this.sendSelectionEvent(13);
        }
        return true;
    }
    
    void setOrientation(final int orientation) {
        final long hMenu = this.parent.handle;
        final MENUITEMINFO info = new MENUITEMINFO();
        info.cbSize = MENUITEMINFO.sizeof;
        info.fMask = 256;
        info.fType = this.widgetStyle();
        OS.SetMenuItemInfo(hMenu, this.id, false, info);
        if (this.menu != null) {
            this.menu._setOrientation(orientation);
        }
    }
    
    public void setSelection(final boolean selected) {
        this.checkWidget();
        if ((this.style & 0x30) == 0x0) {
            return;
        }
        final long hMenu = this.parent.handle;
        final MENUITEMINFO info = new MENUITEMINFO();
        info.cbSize = MENUITEMINFO.sizeof;
        info.fMask = 1;
        boolean success = OS.GetMenuItemInfo(hMenu, this.id, false, info);
        if (!success) {
            this.error(28);
        }
        final MENUITEMINFO menuiteminfo3;
        final MENUITEMINFO menuiteminfo = menuiteminfo3 = info;
        menuiteminfo3.fState &= 0xFFFFFFF7;
        if (selected) {
            final MENUITEMINFO menuiteminfo4;
            final MENUITEMINFO menuiteminfo2 = menuiteminfo4 = info;
            menuiteminfo4.fState |= 0x8;
        }
        success = OS.SetMenuItemInfo(hMenu, this.id, false, info);
        if (!success) {
            success = (this.id == OS.GetMenuDefaultItem(hMenu, 0, 1));
            if (!success) {
                final int error = OS.GetLastError();
                SWT.error(28, (Throwable)null, " [GetLastError=0x" + Integer.toHexString(error));
            }
        }
        this.parent.redraw();
    }
    
    public void setText(final String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        if (this.text.equals(string)) {
            return;
        }
        super.setText(string);
        final long hHeap = OS.GetProcessHeap();
        long pszText = 0L;
        final MENUITEMINFO info = new MENUITEMINFO();
        info.cbSize = MENUITEMINFO.sizeof;
        final long hMenu = this.parent.handle;
        final TCHAR buffer = new TCHAR(0, string, true);
        final int byteCount = buffer.length() * 2;
        pszText = OS.HeapAlloc(hHeap, 8, byteCount);
        OS.MoveMemory(pszText, buffer, byteCount);
        info.fMask = 64;
        info.dwTypeData = pszText;
        final boolean success = OS.SetMenuItemInfo(hMenu, this.id, false, info);
        if (pszText != 0L) {
            OS.HeapFree(hHeap, 0, pszText);
        }
        if (!success) {
            final int error = OS.GetLastError();
            SWT.error(13, (Throwable)null, " [GetLastError=0x" + Integer.toHexString(error));
        }
        this.parent.redraw();
    }
    
    public void setToolTipText(final String toolTip) {
        this.checkWidget();
        if (toolTip == null && this.itemToolTip != null) {
            if (!this.itemToolTip.isDisposed()) {
                this.itemToolTip.setVisible(false);
                this.itemToolTip.dispose();
            }
            this.itemToolTip = null;
        }
        if (toolTip == null || toolTip.trim().length() == 0 || (this.itemToolTip != null && toolTip.equals(this.itemToolTip.getMessage()))) {
            return;
        }
        if (this.itemToolTip != null) {
            this.itemToolTip.dispose();
        }
        (this.itemToolTip = new MenuItemToolTip(this.getParent().getShell())).setMessage(toolTip);
        this.itemToolTip.setVisible(false);
    }
    
    void showTooltip(final int x, final int y) {
        if (this.itemToolTip == null || this.itemToolTip.isDisposed()) {
            return;
        }
        this.itemToolTip.setLocationInPixels(x, y);
        this.itemToolTip.setVisible(true);
    }
    
    int widgetStyle() {
        int bits = 0;
        final Decorations shell = this.parent.parent;
        if ((shell.style & 0x8000000) != 0x0) {
            if ((this.parent.style & 0x2000000) != 0x0) {
                bits |= 0x6000;
            }
        }
        else if ((this.parent.style & 0x4000000) != 0x0) {
            bits |= 0x6000;
        }
        if ((this.style & 0x2) != 0x0) {
            return bits | 0x800;
        }
        if ((this.style & 0x10) != 0x0) {
            return bits | 0x200;
        }
        return bits | 0x0;
    }
    
    LRESULT wmCommandChild(final long wParam, final long lParam) {
        if ((this.style & 0x20) != 0x0) {
            this.setSelection(!this.getSelection());
        }
        else if ((this.style & 0x10) != 0x0) {
            if ((this.parent.getStyle() & 0x400000) != 0x0) {
                this.setSelection(!this.getSelection());
            }
            else {
                this.selectRadio();
            }
        }
        this.sendSelectionEvent(13);
        return null;
    }
    
    LRESULT wmDrawChild(final long wParam, final long lParam) {
        final DRAWITEMSTRUCT struct = new DRAWITEMSTRUCT();
        OS.MoveMemory(struct, lParam, DRAWITEMSTRUCT.sizeof);
        if (this.image != null) {
            final GCData data = new GCData();
            data.device = (Device)this.display;
            final GC gc = GC.win32_new(struct.hDC, data);
            final int x = ((this.parent.style & 0x2) != 0x0) ? 2 : struct.left;
            final Image image = this.getEnabled() ? this.image : new Image((Device)this.display, this.image, 1);
            gc.drawImage(image, DPIUtil.autoScaleDown(x), DPIUtil.autoScaleDown(struct.top + 1));
            if (this.image != image) {
                image.dispose();
            }
            gc.dispose();
        }
        if (this.parent.foreground != -1) {
            OS.SetTextColor(struct.hDC, this.parent.foreground);
        }
        return null;
    }
    
    LRESULT wmMeasureChild(final long wParam, final long lParam) {
        final MEASUREITEMSTRUCT struct = new MEASUREITEMSTRUCT();
        OS.MoveMemory(struct, lParam, MEASUREITEMSTRUCT.sizeof);
        if ((this.parent.style & 0x2) != 0x0 && this.parent.needsMenuCallback()) {
            struct.itemWidth = DPIUtil.autoScaleUpUsingNativeDPI(5);
            OS.MoveMemory(lParam, struct, MEASUREITEMSTRUCT.sizeof);
            return null;
        }
        int width = 0;
        int height = 0;
        if (this.image != null) {
            final Rectangle rect = this.image.getBoundsInPixels();
            width = rect.width;
            height = rect.height;
        }
        else {
            final MENUINFO lpcmi = new MENUINFO();
            lpcmi.cbSize = MENUINFO.sizeof;
            lpcmi.fMask = 16;
            final long hMenu = this.parent.handle;
            OS.GetMenuInfo(hMenu, lpcmi);
            if ((lpcmi.dwStyle & 0x4000000) == 0x0) {
                for (final MenuItem item : this.parent.getItems()) {
                    if (item.image != null) {
                        final Rectangle rect2 = item.image.getBoundsInPixels();
                        width = Math.max(width, rect2.width);
                    }
                }
            }
        }
        if (width != 0 || height != 0) {
            struct.itemWidth = width + 2;
            struct.itemHeight = height + 2;
            OS.MoveMemory(lParam, struct, MEASUREITEMSTRUCT.sizeof);
        }
        return null;
    }
    
    private static final class MenuItemToolTip extends ToolTip
    {
        public MenuItemToolTip(final Shell parent) {
            super(parent, 0);
            this.maybeEnableDarkSystemTheme(this.hwndToolTip());
        }
        
        @Override
        long hwndToolTip() {
            return this.parent.menuItemToolTipHandle();
        }
    }
}
