//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class ToolBar extends Composite
{
    int lastFocusId;
    int lastArrowId;
    int lastHotId;
    int _width;
    int _height;
    int _count;
    int _wHint;
    int _hHint;
    long currentToolItemToolTip;
    ToolItem[] items;
    ToolItem[] tabItemList;
    boolean ignoreResize;
    boolean ignoreMouse;
    ImageList imageList;
    ImageList disabledImageList;
    ImageList hotImageList;
    static final long ToolBarProc;
    static final TCHAR ToolBarClass;
    static final int DEFAULT_WIDTH = 24;
    static final int DEFAULT_HEIGHT = 22;
    
    public ToolBar(final Composite parent, final int style) {
        super(parent, checkStyle(style));
        this._count = -1;
        this._wHint = -1;
        this._hHint = -1;
        if ((style & 0x200) != 0x0) {
            this.style |= 0x200;
            int bits = OS.GetWindowLong(this.handle, -16);
            if (OS.IsAppThemed() && (style & 0x20000) != 0x0) {
                bits |= 0x1000;
            }
            OS.SetWindowLong(this.handle, -16, bits | 0x80);
        }
        else {
            this.style |= 0x100;
        }
    }
    
    long callWindowProc(final long hwnd, final int msg, final long wParam, final long lParam) {
        if (this.handle == 0L) {
            return 0L;
        }
        if (msg == 262) {
            return OS.DefWindowProc(hwnd, msg, wParam, lParam);
        }
        return OS.CallWindowProc(ToolBar.ToolBarProc, hwnd, msg, wParam, lParam);
    }
    
    static int checkStyle(int style) {
        if ((style & 0x800000) == 0x0) {
            style |= 0x80000;
        }
        if ((style & 0x200) != 0x0) {
            style &= 0xFFFFFFBF;
        }
        return style & 0xFFFFFCFF;
    }
    
    void checkBuffered() {
        super.checkBuffered();
        this.style |= 0x20000000;
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    public void layout(final boolean changed) {
        this.checkWidget();
        this.clearSizeCache(changed);
        super.layout(changed);
    }
    
    void clearSizeCache(final boolean changed) {
        if (changed) {
            final int count = -1;
            this._hHint = -1;
            this._wHint = -1;
            this._count = -1;
        }
    }
    
    Point computeSizeInPixels(final int wHint, final int hHint, final boolean changed) {
        final int count = (int)OS.SendMessage(this.handle, 1048, 0L, 0L);
        if (count == this._count && wHint == this._wHint && hHint == this._hHint) {
            return new Point(this._width, this._height);
        }
        this._count = count;
        this._wHint = wHint;
        this._hHint = hHint;
        int width = 0;
        int height = 0;
        if ((this.style & 0x200) != 0x0) {
            final RECT rect = new RECT();
            final TBBUTTON lpButton = new TBBUTTON();
            for (int i = 0; i < count; ++i) {
                OS.SendMessage(this.handle, 1053, (long)i, rect);
                height = Math.max(height, rect.bottom);
                OS.SendMessage(this.handle, 1047, (long)i, lpButton);
                if ((lpButton.fsStyle & 0x1) != 0x0) {
                    final TBBUTTONINFO info = new TBBUTTONINFO();
                    info.cbSize = TBBUTTONINFO.sizeof;
                    info.dwMask = 64;
                    OS.SendMessage(this.handle, 1087, (long)lpButton.idCommand, info);
                    width = Math.max(width, info.cx);
                }
                else {
                    width = Math.max(width, rect.right);
                }
            }
        }
        else {
            final RECT oldRect = new RECT();
            OS.GetWindowRect(this.handle, oldRect);
            final int oldWidth = oldRect.right - oldRect.left;
            final int oldHeight = oldRect.bottom - oldRect.top;
            final int border = this.getBorderWidthInPixels();
            final int newWidth = (wHint == -1) ? 16383 : (wHint + border * 2);
            final int newHeight = (hHint == -1) ? 16383 : (hHint + border * 2);
            final boolean redraw = this.getDrawing() && OS.IsWindowVisible(this.handle);
            this.ignoreResize = true;
            if (redraw) {
                OS.UpdateWindow(this.handle);
            }
            final int flags = 30;
            OS.SetWindowPos(this.handle, 0L, 0, 0, newWidth, newHeight, 30);
            if (count != 0) {
                final RECT rect2 = new RECT();
                OS.SendMessage(this.handle, 1053, (long)(count - 1), rect2);
                width = Math.max(width, rect2.right);
                height = Math.max(height, rect2.bottom);
            }
            OS.SetWindowPos(this.handle, 0L, 0, 0, oldWidth, oldHeight, 30);
            if (redraw) {
                OS.ValidateRect(this.handle, (RECT)null);
            }
            this.ignoreResize = false;
        }
        if (width == 0) {
            width = 24;
        }
        if (height == 0) {
            height = 22;
        }
        if (wHint != -1) {
            width = wHint;
        }
        if (hHint != -1) {
            height = hHint;
        }
        final Rectangle trim = this.computeTrimInPixels(0, 0, width, height);
        width = trim.width;
        height = trim.height;
        this._width = width;
        this._height = height;
        return new Point(width, height);
    }
    
    Rectangle computeTrimInPixels(final int x, final int y, final int width, final int height) {
        final Rectangle trim = super.computeTrimInPixels(x, y, width, height);
        final int bits = OS.GetWindowLong(this.handle, -16);
        if ((bits & 0x40) == 0x0) {
            final Rectangle rectangle2;
            final Rectangle rectangle = rectangle2 = trim;
            rectangle2.height += 2;
        }
        return trim;
    }
    
    Widget computeTabGroup() {
        final ToolItem[] items = this._getItems();
        if (this.tabItemList == null) {
            int i;
            for (i = 0; i < items.length && items[i].control == null; ++i) {}
            if (i == items.length) {
                return super.computeTabGroup();
            }
        }
        int index = (int)OS.SendMessage(this.handle, 1095, 0L, 0L);
        if (index == -1) {
            index = this.lastHotId;
        }
        while (index >= 0) {
            final ToolItem item = items[index];
            if (item.isTabGroup()) {
                return (Widget)item;
            }
            --index;
        }
        return super.computeTabGroup();
    }
    
    Widget[] computeTabList() {
        final ToolItem[] items = this._getItems();
        if (this.tabItemList == null) {
            int i;
            for (i = 0; i < items.length && items[i].control == null; ++i) {}
            if (i == items.length) {
                return super.computeTabList();
            }
        }
        Widget[] result = new Widget[0];
        if (!this.isTabGroup() || !this.isEnabled() || !this.isVisible()) {
            return result;
        }
        final ToolItem[] list;
        final ToolItem[] array2;
        final ToolItem[] array = array2 = (list = ((this.tabList != null) ? this._getTabItemList() : items));
        for (final ToolItem child : array2) {
            final Widget[] childList = child.computeTabList();
            if (childList.length != 0) {
                final Widget[] newResult = new Widget[result.length + childList.length];
                System.arraycopy(result, 0, newResult, 0, result.length);
                System.arraycopy(childList, 0, newResult, result.length, childList.length);
                result = newResult;
            }
        }
        if (result.length == 0) {
            result = new Widget[] { (Widget)this };
        }
        return result;
    }
    
    void createHandle() {
        super.createHandle();
        this.state &= 0xFFFFFFFD;
        if ((this.style & 0x800000) != 0x0 && !OS.IsAppThemed()) {
            int bits = OS.GetWindowLong(this.handle, -16);
            bits &= 0xFFFF7FFF;
            OS.SetWindowLong(this.handle, -16, bits);
        }
        final long hFont = OS.GetStockObject(13);
        OS.SendMessage(this.handle, 48, hFont, 0L);
        OS.SendMessage(this.handle, 1054, (long)TBBUTTON.sizeof, 0L);
        OS.SendMessage(this.handle, 1056, 0L, 0L);
        OS.SendMessage(this.handle, 1055, 0L, 0L);
        final int bits2 = 137;
        OS.SendMessage(this.handle, 1108, 0L, 137L);
    }
    
    void createItem(final ToolItem item, final int index) {
        final int count = (int)OS.SendMessage(this.handle, 1048, 0L, 0L);
        if (0 > index || index > count) {
            this.error(6);
        }
        int id;
        for (id = 0; id < this.items.length && this.items[id] != null; ++id) {}
        if (id == this.items.length) {
            final ToolItem[] newItems = new ToolItem[this.items.length + 4];
            System.arraycopy(this.items, 0, newItems, 0, this.items.length);
            this.items = newItems;
        }
        final int bits = item.widgetStyle();
        final TBBUTTON lpButton = new TBBUTTON();
        lpButton.idCommand = id;
        lpButton.fsStyle = (byte)bits;
        lpButton.fsState = 4;
        if ((bits & 0x1) == 0x0) {
            lpButton.iBitmap = -2;
        }
        if (OS.SendMessage(this.handle, 1091, (long)index, lpButton) == 0L) {
            this.error(14);
        }
        this.items[item.id = id] = item;
        if ((this.style & 0x200) != 0x0) {
            this.setRowCount(count + 1);
        }
        this.layoutItems();
    }
    
    void createWidget() {
        super.createWidget();
        this.items = new ToolItem[4];
        final int lastFocusId = -1;
        this.lastHotId = -1;
        this.lastArrowId = -1;
        this.lastFocusId = -1;
    }
    
    int applyThemeBackground() {
        return -1;
    }
    
    void destroyItem(final ToolItem item) {
        final TBBUTTONINFO info = new TBBUTTONINFO();
        info.cbSize = TBBUTTONINFO.sizeof;
        info.dwMask = 9;
        final int index = (int)OS.SendMessage(this.handle, 1087, (long)item.id, info);
        if ((info.fsStyle & 0x1) == 0x0 && info.iImage != -2) {
            if (this.imageList != null) {
                this.imageList.put(info.iImage, (Image)null);
            }
            if (this.hotImageList != null) {
                this.hotImageList.put(info.iImage, (Image)null);
            }
            if (this.disabledImageList != null) {
                this.disabledImageList.put(info.iImage, (Image)null);
            }
        }
        OS.SendMessage(this.handle, 1046, (long)index, 0L);
        if (item.id == this.lastFocusId) {
            this.lastFocusId = -1;
        }
        if (item.id == this.lastArrowId) {
            this.lastArrowId = -1;
        }
        if (item.id == this.lastHotId) {
            this.lastHotId = -1;
        }
        this.items[item.id] = null;
        item.id = -1;
        final int count = (int)OS.SendMessage(this.handle, 1048, 0L, 0L);
        if (count == 0) {
            if (this.imageList != null) {
                OS.SendMessage(this.handle, 1072, 0L, 0L);
                this.display.releaseToolImageList(this.imageList);
            }
            if (this.hotImageList != null) {
                OS.SendMessage(this.handle, 1076, 0L, 0L);
                this.display.releaseToolHotImageList(this.hotImageList);
            }
            if (this.disabledImageList != null) {
                OS.SendMessage(this.handle, 1078, 0L, 0L);
                this.display.releaseToolDisabledImageList(this.disabledImageList);
            }
            final ImageList imageList = null;
            this.disabledImageList = imageList;
            this.hotImageList = imageList;
            this.imageList = imageList;
            this.items = new ToolItem[4];
        }
        if ((this.style & 0x200) != 0x0) {
            this.setRowCount(count - 1);
        }
        this.layoutItems();
    }
    
    void enableWidget(final boolean enabled) {
        super.enableWidget(enabled);
        for (final ToolItem item : this.items) {
            if (item != null && (item.style & 0x2) == 0x0) {
                item.updateImages(enabled && item.getEnabled());
            }
        }
    }
    
    ImageList getDisabledImageList() {
        return this.disabledImageList;
    }
    
    ImageList getHotImageList() {
        return this.hotImageList;
    }
    
    ImageList getImageList() {
        return this.imageList;
    }
    
    public ToolItem getItem(final int index) {
        this.checkWidget();
        final int count = (int)OS.SendMessage(this.handle, 1048, 0L, 0L);
        if (0 > index || index >= count) {
            this.error(6);
        }
        final TBBUTTON lpButton = new TBBUTTON();
        final long result = OS.SendMessage(this.handle, 1047, (long)index, lpButton);
        if (result == 0L) {
            this.error(8);
        }
        return this.items[lpButton.idCommand];
    }
    
    public ToolItem getItem(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        return this.getItemInPixels(DPIUtil.autoScaleUp(point));
    }
    
    ToolItem getItemInPixels(final Point point) {
        for (final ToolItem item : this.getItems()) {
            final Rectangle rect = item.getBoundsInPixels();
            if (rect.contains(point)) {
                return item;
            }
        }
        return null;
    }
    
    public int getItemCount() {
        this.checkWidget();
        return (int)OS.SendMessage(this.handle, 1048, 0L, 0L);
    }
    
    public ToolItem[] getItems() {
        this.checkWidget();
        return this._getItems();
    }
    
    ToolItem[] _getItems() {
        final int count = (int)OS.SendMessage(this.handle, 1048, 0L, 0L);
        final TBBUTTON lpButton = new TBBUTTON();
        final ToolItem[] result = new ToolItem[count];
        for (int i = 0; i < count; ++i) {
            OS.SendMessage(this.handle, 1047, (long)i, lpButton);
            result[i] = this.items[lpButton.idCommand];
        }
        return result;
    }
    
    public int getRowCount() {
        this.checkWidget();
        if ((this.style & 0x200) != 0x0) {
            return (int)OS.SendMessage(this.handle, 1048, 0L, 0L);
        }
        return (int)OS.SendMessage(this.handle, 1064, 0L, 0L);
    }
    
    ToolItem[] _getTabItemList() {
        if (this.tabItemList == null) {
            return this.tabItemList;
        }
        int count = 0;
        for (final ToolItem item : this.tabItemList) {
            if (!item.isDisposed()) {
                ++count;
            }
        }
        if (count == this.tabItemList.length) {
            return this.tabItemList;
        }
        final ToolItem[] newList = new ToolItem[count];
        int index = 0;
        for (final ToolItem item2 : this.tabItemList) {
            if (!item2.isDisposed()) {
                newList[index++] = item2;
            }
        }
        return this.tabItemList = newList;
    }
    
    public int indexOf(final ToolItem item) {
        this.checkWidget();
        if (item == null) {
            this.error(4);
        }
        if (item.isDisposed()) {
            this.error(5);
        }
        return (int)OS.SendMessage(this.handle, 1049, (long)item.id, 0L);
    }
    
    void layoutItems() {
        this.clearSizeCache(true);
        if (OS.IsAppThemed() && (this.style & 0x20000) != 0x0 && (this.style & 0x200) == 0x0) {
            boolean hasText = false;
            boolean hasImage = false;
            for (final ToolItem item : this.items) {
                if (item != null) {
                    if (!hasText) {
                        hasText = (item.text.length() != 0);
                    }
                    if (!hasImage) {
                        hasImage = (item.image != null);
                    }
                    if (hasText && hasImage) {
                        break;
                    }
                }
            }
            final int oldBits;
            int newBits = oldBits = OS.GetWindowLong(this.handle, -16);
            if (hasText && hasImage) {
                newBits |= 0x1000;
            }
            else {
                newBits &= 0xFFFFEFFF;
            }
            if (newBits != oldBits) {
                this.setDropDownItems(false);
                OS.SetWindowLong(this.handle, -16, newBits);
                final long hFont = OS.SendMessage(this.handle, 49, 0L, 0L);
                OS.SendMessage(this.handle, 48, hFont, 0L);
                this.setDropDownItems(true);
            }
        }
        if ((this.style & 0x40) != 0x0) {
            OS.SendMessage(this.handle, 1057, 0L, 0L);
        }
        if ((this.style & 0x200) != 0x0) {
            final int itemCount = (int)OS.SendMessage(this.handle, 1048, 0L, 0L);
            if (itemCount > 1) {
                final TBBUTTONINFO info = new TBBUTTONINFO();
                info.cbSize = TBBUTTONINFO.sizeof;
                info.dwMask = 64;
                final long size = OS.SendMessage(this.handle, 1082, 0L, 0L);
                info.cx = (short)OS.LOWORD(size);
                int index = 0;
                int extraPadding = 0;
                while (index < this.items.length) {
                    final ToolItem item2 = this.items[index];
                    if (item2 != null && (item2.style & 0x4) != 0x0) {
                        extraPadding = 1;
                        break;
                    }
                    ++index;
                }
                if (index < this.items.length) {
                    final long padding = OS.SendMessage(this.handle, 1110, 0L, 0L);
                    final TBBUTTONINFO tbbuttoninfo2;
                    final TBBUTTONINFO tbbuttoninfo = tbbuttoninfo2 = info;
                    tbbuttoninfo2.cx += (short)(OS.LOWORD(padding + extraPadding) * 2);
                }
                for (final ToolItem item3 : this.items) {
                    if (item3 != null && (item3.style & 0x2) == 0x0) {
                        OS.SendMessage(this.handle, 1088, (long)item3.id, info);
                    }
                }
            }
        }
        if ((this.style & 0x240) != 0x0) {
            final int bits = OS.GetWindowLong(this.handle, -16);
            if ((bits & 0x1000) != 0x0) {
                final TBBUTTONINFO info = new TBBUTTONINFO();
                info.cbSize = TBBUTTONINFO.sizeof;
                info.dwMask = 64;
                for (final ToolItem item : this.items) {
                    if (item != null && item.cx > 0) {
                        info.cx = item.cx;
                        OS.SendMessage(this.handle, 1088, (long)item.id, info);
                    }
                }
            }
        }
        for (final ToolItem item4 : this.items) {
            if (item4 != null) {
                item4.resizeControl();
            }
        }
    }
    
    boolean mnemonicHit(final char ch) {
        final int[] id = { 0 };
        if (OS.SendMessage(this.handle, 1114, (long)ch, id) == 0L) {
            return false;
        }
        if ((this.style & 0x800000) != 0x0 && !this.setTabGroupFocus()) {
            return false;
        }
        final int index = (int)OS.SendMessage(this.handle, 1049, (long)id[0], 0L);
        if (index == -1) {
            return false;
        }
        OS.SendMessage(this.handle, 1096, (long)index, 0L);
        this.items[id[0]].click(false);
        return true;
    }
    
    boolean mnemonicMatch(final char ch) {
        final int[] id = { 0 };
        if (OS.SendMessage(this.handle, 1114, (long)ch, id) == 0L) {
            return false;
        }
        final int index = (int)OS.SendMessage(this.handle, 1049, (long)id[0], 0L);
        return index != -1 && this.findMnemonic(this.items[id[0]].text) != '\0';
    }
    
    void releaseChildren(final boolean destroy) {
        if (this.items != null) {
            for (final ToolItem item : this.items) {
                if (item != null && !item.isDisposed()) {
                    item.release(false);
                }
            }
            this.items = null;
        }
        super.releaseChildren(destroy);
    }
    
    void releaseWidget() {
        super.releaseWidget();
        if (this.imageList != null) {
            OS.SendMessage(this.handle, 1072, 0L, 0L);
            this.display.releaseToolImageList(this.imageList);
        }
        if (this.hotImageList != null) {
            OS.SendMessage(this.handle, 1076, 0L, 0L);
            this.display.releaseToolHotImageList(this.hotImageList);
        }
        if (this.disabledImageList != null) {
            OS.SendMessage(this.handle, 1078, 0L, 0L);
            this.display.releaseToolDisabledImageList(this.disabledImageList);
        }
        final ImageList imageList = null;
        this.disabledImageList = imageList;
        this.hotImageList = imageList;
        this.imageList = imageList;
    }
    
    void removeControl(final Control control) {
        super.removeControl(control);
        for (final ToolItem item : this.items) {
            if (item != null && item.control == control) {
                item.setControl(null);
            }
        }
    }
    
    void reskinChildren(final int flags) {
        if (this.items != null) {
            for (final ToolItem item : this.items) {
                if (item != null) {
                    item.reskin(flags);
                }
            }
        }
        super.reskinChildren(flags);
    }
    
    void setBackgroundImage(final long hBitmap) {
        super.setBackgroundImage(hBitmap);
        this.setBackgroundTransparent(hBitmap != 0L);
    }
    
    void setBackgroundPixel(final int pixel) {
        super.setBackgroundPixel(pixel);
        this.setBackgroundTransparent(pixel != -1);
    }
    
    void setBackgroundTransparent(final boolean transparent) {
        if ((this.style & 0x800000) != 0x0 && !OS.IsAppThemed()) {
            int bits = OS.GetWindowLong(this.handle, -16);
            if (!transparent && this.findBackgroundControl() == null) {
                bits &= 0xFFFF7FFF;
            }
            else {
                bits |= 0x8000;
            }
            OS.SetWindowLong(this.handle, -16, bits);
        }
    }
    
    void setBoundsInPixels(final int x, final int y, final int width, final int height, final int flags) {
        if (this.parent.lpwp != null && this.getDrawing() && OS.IsWindowVisible(this.handle)) {
            this.parent.setResizeChildren(false);
            this.parent.setResizeChildren(true);
        }
        super.setBoundsInPixels(x, y, width, height, flags);
    }
    
    void setDefaultFont() {
        super.setDefaultFont();
        OS.SendMessage(this.handle, 1056, 0L, 0L);
        OS.SendMessage(this.handle, 1055, 0L, 0L);
    }
    
    void setDropDownItems(final boolean set) {
        if (OS.IsAppThemed()) {
            boolean hasText = false;
            boolean hasImage = false;
            for (final ToolItem item : this.items) {
                if (item != null) {
                    if (!hasText) {
                        hasText = (item.text.length() != 0);
                    }
                    if (!hasImage) {
                        hasImage = (item.image != null);
                    }
                    if (hasText && hasImage) {
                        break;
                    }
                }
            }
            if (hasImage && !hasText) {
                for (final ToolItem item : this.items) {
                    if (item != null && (item.style & 0x4) != 0x0) {
                        final TBBUTTONINFO info = new TBBUTTONINFO();
                        info.cbSize = TBBUTTONINFO.sizeof;
                        info.dwMask = 8;
                        OS.SendMessage(this.handle, 1087, (long)item.id, info);
                        if (set) {
                            final TBBUTTONINFO tbbuttoninfo3;
                            final TBBUTTONINFO tbbuttoninfo = tbbuttoninfo3 = info;
                            tbbuttoninfo3.fsStyle |= 0x8;
                        }
                        else {
                            final TBBUTTONINFO tbbuttoninfo4;
                            final TBBUTTONINFO tbbuttoninfo2 = tbbuttoninfo4 = info;
                            tbbuttoninfo4.fsStyle &= 0xFFFFFFF7;
                        }
                        OS.SendMessage(this.handle, 1088, (long)item.id, info);
                    }
                }
            }
        }
    }
    
    void setDisabledImageList(final ImageList imageList) {
        if (this.disabledImageList == imageList) {
            return;
        }
        long hImageList = 0L;
        if ((this.disabledImageList = imageList) != null) {
            hImageList = this.disabledImageList.getHandle();
        }
        this.setDropDownItems(false);
        OS.SendMessage(this.handle, 1078, 0L, hImageList);
        this.setDropDownItems(true);
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        this.setDropDownItems(false);
        super.setFont(font);
        this.setDropDownItems(true);
        int index = 0;
        final int mask = 60;
        while (index < this.items.length) {
            final ToolItem item = this.items[index];
            if (item != null && (item.style & 0x3C) != 0x0) {
                break;
            }
            ++index;
        }
        if (index == this.items.length) {
            OS.SendMessage(this.handle, 1056, 0L, 0L);
            OS.SendMessage(this.handle, 1055, 0L, 0L);
        }
        this.layoutItems();
    }
    
    void setHotImageList(final ImageList imageList) {
        if (this.hotImageList == imageList) {
            return;
        }
        long hImageList = 0L;
        if ((this.hotImageList = imageList) != null) {
            hImageList = this.hotImageList.getHandle();
        }
        this.setDropDownItems(false);
        OS.SendMessage(this.handle, 1076, 0L, hImageList);
        this.setDropDownItems(true);
    }
    
    void setImageList(final ImageList imageList) {
        if (this.imageList == imageList) {
            return;
        }
        long hImageList = 0L;
        if ((this.imageList = imageList) != null) {
            hImageList = imageList.getHandle();
        }
        this.setDropDownItems(false);
        OS.SendMessage(this.handle, 1072, 0L, hImageList);
        this.setDropDownItems(true);
    }
    
    public boolean setParent(final Composite parent) {
        this.checkWidget();
        if (!super.setParent(parent)) {
            return false;
        }
        final long hwndParent = parent.handle;
        OS.SendMessage(this.handle, 1061, hwndParent, 0L);
        final long hwndShell = parent.getShell().handle;
        final long hwndToolTip = OS.SendMessage(this.handle, 1059, 0L, 0L);
        OS.SetWindowLongPtr(hwndToolTip, -8, hwndShell);
        return true;
    }
    
    public void setRedraw(final boolean redraw) {
        this.checkWidget();
        this.setDropDownItems(false);
        super.setRedraw(redraw);
        this.setDropDownItems(true);
    }
    
    void setRowCount(int count) {
        if ((this.style & 0x200) != 0x0) {
            final RECT rect = new RECT();
            OS.GetWindowRect(this.handle, rect);
            OS.MapWindowPoints(0L, this.parent.handle, rect, 2);
            this.ignoreResize = true;
            count += 2;
            OS.SendMessage(this.handle, 1063, OS.MAKEWPARAM(count, 1), 0L);
            final int flags = 22;
            OS.SetWindowPos(this.handle, 0L, 0, 0, rect.right - rect.left, rect.bottom - rect.top, 22);
            this.ignoreResize = false;
        }
    }
    
    void setTabItemList(ToolItem[] tabList) {
        this.checkWidget();
        if (tabList != null) {
            for (final ToolItem item : tabList) {
                if (item == null) {
                    this.error(5);
                }
                if (item.isDisposed()) {
                    this.error(5);
                }
                if (item.parent != this) {
                    this.error(32);
                }
            }
            final ToolItem[] newList = new ToolItem[tabList.length];
            System.arraycopy(tabList, 0, newList, 0, tabList.length);
            tabList = newList;
        }
        this.tabItemList = tabList;
    }
    
    boolean setTabItemFocus() {
        int index;
        for (index = 0; index < this.items.length; ++index) {
            final ToolItem item = this.items[index];
            if (item != null && (item.style & 0x2) == 0x0 && item.getEnabled()) {
                break;
            }
        }
        return index != this.items.length && super.setTabItemFocus();
    }
    
    boolean updateTextDirection(final int textDirection) {
        if (super.updateTextDirection(textDirection)) {
            final ToolItem[] items = this._getItems();
            int i = items.length;
            while (i-- > 0) {
                items[i].updateTextDirection(this.style & Integer.MIN_VALUE);
            }
            return true;
        }
        return false;
    }
    
    String toolTipText(final NMTTDISPINFO hdr) {
        if ((hdr.uFlags & 0x1) != 0x0) {
            return null;
        }
        if (!this.hasCursor()) {
            return "";
        }
        final int index = (int)hdr.idFrom;
        final long hwndToolTip = OS.SendMessage(this.handle, 1059, 0L, 0L);
        if (hwndToolTip == hdr.hwndFrom) {
            if (this.currentToolItemToolTip != hwndToolTip) {
                this.maybeEnableDarkSystemTheme(hdr.hwndFrom);
                this.currentToolItemToolTip = hdr.hwndFrom;
            }
            final int flags = -2080374784;
            if ((this.style & 0x84000000) != 0x0 && (this.style & 0x84000000) != 0x84000000) {
                hdr.uFlags |= 0x4;
            }
            else {
                hdr.uFlags &= 0xFFFFFFFB;
            }
            if (this.toolTipText != null) {
                return "";
            }
            if (0 <= index && index < this.items.length) {
                final ToolItem item = this.items[index];
                if (item != null) {
                    if (this.lastArrowId != -1) {
                        return "";
                    }
                    return item.toolTipText;
                }
            }
        }
        return super.toolTipText(hdr);
    }
    
    void updateOrientation() {
        super.updateOrientation();
        if (this.imageList != null) {
            final Point size = this.imageList.getImageSize();
            final ImageList newImageList = this.display.getImageListToolBar(this.style & 0x4000000, size.x, size.y);
            final ImageList newHotImageList = this.display.getImageListToolBarHot(this.style & 0x4000000, size.x, size.y);
            final ImageList newDisabledImageList = this.display.getImageListToolBarDisabled(this.style & 0x4000000, size.x, size.y);
            final TBBUTTONINFO info = new TBBUTTONINFO();
            info.cbSize = TBBUTTONINFO.sizeof;
            info.dwMask = 1;
            for (int count = (int)OS.SendMessage(this.handle, 1048, 0L, 0L), i = 0; i < count; ++i) {
                final ToolItem item = this.items[i];
                if ((item.style & 0x2) == 0x0 && item.image != null) {
                    OS.SendMessage(this.handle, 1087, (long)item.id, info);
                    if (info.iImage != -2) {
                        final Image image = this.imageList.get(info.iImage);
                        final Image hot = this.hotImageList.get(info.iImage);
                        final Image disabled = this.disabledImageList.get(info.iImage);
                        this.imageList.put(info.iImage, (Image)null);
                        this.hotImageList.put(info.iImage, (Image)null);
                        this.disabledImageList.put(info.iImage, (Image)null);
                        info.iImage = newImageList.add(image);
                        newHotImageList.add(hot);
                        newDisabledImageList.add(disabled);
                        OS.SendMessage(this.handle, 1088, (long)item.id, info);
                    }
                }
            }
            this.display.releaseToolImageList(this.imageList);
            this.display.releaseToolHotImageList(this.hotImageList);
            this.display.releaseToolDisabledImageList(this.disabledImageList);
            OS.SendMessage(this.handle, 1072, 0L, newImageList.getHandle());
            OS.SendMessage(this.handle, 1076, 0L, newHotImageList.getHandle());
            OS.SendMessage(this.handle, 1078, 0L, newDisabledImageList.getHandle());
            this.imageList = newImageList;
            this.hotImageList = newHotImageList;
            this.disabledImageList = newDisabledImageList;
            OS.InvalidateRect(this.handle, (RECT)null, true);
        }
    }
    
    int widgetStyle() {
        int bits = super.widgetStyle() | 0x4 | 0x100 | 0x2000;
        if (OS.IsAppThemed()) {
            bits |= 0x8000;
        }
        if ((this.style & 0x8) == 0x0) {
            bits |= 0x40;
        }
        if ((this.style & 0x40) != 0x0) {
            bits |= 0x200;
        }
        if ((this.style & 0x800000) != 0x0) {
            bits |= 0x800;
        }
        if (!OS.IsAppThemed() && (this.style & 0x20000) != 0x0) {
            bits |= 0x1000;
        }
        return bits;
    }
    
    TCHAR windowClass() {
        return ToolBar.ToolBarClass;
    }
    
    long windowProc() {
        return ToolBar.ToolBarProc;
    }
    
    LRESULT WM_CAPTURECHANGED(final long wParam, final long lParam) {
        final LRESULT result = super.WM_CAPTURECHANGED(wParam, lParam);
        if (result != null) {
            return result;
        }
        for (final ToolItem item : this.items) {
            if (item != null) {
                int fsState = (int)OS.SendMessage(this.handle, 1042, (long)item.id, 0L);
                if ((fsState & 0x2) != 0x0) {
                    fsState &= 0xFFFFFFFD;
                    OS.SendMessage(this.handle, 1041, (long)item.id, (long)fsState);
                }
            }
        }
        return result;
    }
    
    LRESULT WM_CHAR(final long wParam, final long lParam) {
        final LRESULT result = super.WM_CHAR(wParam, lParam);
        if (result != null) {
            return result;
        }
        switch ((int)wParam) {
            case 32: {
                final int index = (int)OS.SendMessage(this.handle, 1095, 0L, 0L);
                if (index == -1) {
                    break;
                }
                final TBBUTTON lpButton = new TBBUTTON();
                final long code = OS.SendMessage(this.handle, 1047, (long)index, lpButton);
                if (code != 0L) {
                    this.items[lpButton.idCommand].click(false);
                    return LRESULT.ZERO;
                }
                break;
            }
        }
        return result;
    }
    
    LRESULT WM_COMMAND(final long wParam, final long lParam) {
        final LRESULT result = super.WM_COMMAND(wParam, lParam);
        if (result != null) {
            return result;
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_GETDLGCODE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_GETDLGCODE(wParam, lParam);
        if (result != null) {
            return result;
        }
        return new LRESULT(8193L);
    }
    
    LRESULT WM_KEYDOWN(final long wParam, final long lParam) {
        final LRESULT result = super.WM_KEYDOWN(wParam, lParam);
        if (result != null) {
            return result;
        }
        switch ((int)wParam) {
            case 32: {
                return LRESULT.ZERO;
            }
            default: {
                return result;
            }
        }
    }
    
    LRESULT WM_KILLFOCUS(final long wParam, final long lParam) {
        final int index = (int)OS.SendMessage(this.handle, 1095, 0L, 0L);
        final TBBUTTON lpButton = new TBBUTTON();
        final long code = OS.SendMessage(this.handle, 1047, (long)index, lpButton);
        if (code != 0L) {
            this.lastFocusId = lpButton.idCommand;
        }
        return super.WM_KILLFOCUS(wParam, lParam);
    }
    
    LRESULT WM_LBUTTONDOWN(final long wParam, final long lParam) {
        if (this.ignoreMouse) {
            return null;
        }
        return super.WM_LBUTTONDOWN(wParam, lParam);
    }
    
    LRESULT WM_LBUTTONUP(final long wParam, final long lParam) {
        if (this.ignoreMouse) {
            return null;
        }
        return super.WM_LBUTTONUP(wParam, lParam);
    }
    
    LRESULT WM_MOUSELEAVE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_MOUSELEAVE(wParam, lParam);
        if (result != null) {
            return result;
        }
        final TOOLINFO lpti = new TOOLINFO();
        lpti.cbSize = TOOLINFO.sizeof;
        final long hwndToolTip = OS.SendMessage(this.handle, 1059, 0L, 0L);
        if (OS.SendMessage(hwndToolTip, 1083, 0L, lpti) != 0L && (lpti.uFlags & 0x1) == 0x0) {
            OS.SendMessage(hwndToolTip, 1075, 0L, lpti);
            OS.SendMessage(hwndToolTip, 1074, 0L, lpti);
        }
        return result;
    }
    
    LRESULT WM_MOUSEMOVE(final long wParam, final long lParam) {
        if (OS.GetMessagePos() != this.display.lastMouse) {
            this.lastArrowId = -1;
        }
        return super.WM_MOUSEMOVE(wParam, lParam);
    }
    
    LRESULT WM_NOTIFY(final long wParam, final long lParam) {
        final LRESULT result = super.WM_NOTIFY(wParam, lParam);
        if (result != null) {
            return result;
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_SETFOCUS(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SETFOCUS(wParam, lParam);
        if (this.lastFocusId != -1 && this.handle == OS.GetFocus()) {
            final int index = (int)OS.SendMessage(this.handle, 1049, (long)this.lastFocusId, 0L);
            OS.SendMessage(this.handle, 1096, (long)index, 0L);
        }
        return result;
    }
    
    LRESULT WM_SIZE(final long wParam, final long lParam) {
        if (this.ignoreResize) {
            final long code = this.callWindowProc(this.handle, 5, wParam, lParam);
            if (code == 0L) {
                return LRESULT.ZERO;
            }
            return new LRESULT(code);
        }
        else {
            final LRESULT result = super.WM_SIZE(wParam, lParam);
            if (this.isDisposed()) {
                return result;
            }
            if ((this.style & 0x800) != 0x0 && (this.style & 0x40) != 0x0) {
                final RECT windowRect = new RECT();
                OS.GetWindowRect(this.handle, windowRect);
                int index = 0;
                final int border = this.getBorderWidthInPixels() * 2;
                final RECT rect = new RECT();
                int count;
                for (count = (int)OS.SendMessage(this.handle, 1048, 0L, 0L); index < count; ++index) {
                    OS.SendMessage(this.handle, 1053, (long)index, rect);
                    OS.MapWindowPoints(this.handle, 0L, rect, 2);
                    if (rect.right > windowRect.right - border * 2) {
                        break;
                    }
                }
                int bits = (int)OS.SendMessage(this.handle, 1109, 0L, 0L);
                if (index == count) {
                    bits |= 0x10;
                }
                else {
                    bits &= 0xFFFFFFEF;
                }
                OS.SendMessage(this.handle, 1108, 0L, (long)bits);
            }
            this.layoutItems();
            return result;
        }
    }
    
    LRESULT WM_WINDOWPOSCHANGING(final long wParam, final long lParam) {
        final LRESULT result = super.WM_WINDOWPOSCHANGING(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (this.ignoreResize) {
            return result;
        }
        if (!this.getDrawing()) {
            return result;
        }
        if ((this.style & 0x40) == 0x0) {
            return result;
        }
        if (!OS.IsWindowVisible(this.handle)) {
            return result;
        }
        if (OS.SendMessage(this.handle, 1064, 0L, 0L) == 1L) {
            return result;
        }
        final WINDOWPOS lpwp = new WINDOWPOS();
        OS.MoveMemory(lpwp, lParam, WINDOWPOS.sizeof);
        if ((lpwp.flags & 0x9) != 0x0) {
            return result;
        }
        final RECT oldRect = new RECT();
        OS.GetClientRect(this.handle, oldRect);
        final RECT newRect = new RECT();
        OS.SetRect(newRect, 0, 0, lpwp.cx, lpwp.cy);
        OS.SendMessage(this.handle, 131, 0L, newRect);
        final int oldWidth = oldRect.right - oldRect.left;
        final int newWidth = newRect.right - newRect.left;
        if (newWidth > oldWidth) {
            final RECT rect = new RECT();
            final int newHeight = newRect.bottom - newRect.top;
            OS.SetRect(rect, oldWidth - 2, 0, oldWidth, newHeight);
            OS.InvalidateRect(this.handle, rect, false);
        }
        return result;
    }
    
    LRESULT wmCommandChild(final long wParam, final long lParam) {
        final ToolItem child = this.items[OS.LOWORD(wParam)];
        if (child == null) {
            return null;
        }
        return child.wmCommandChild(wParam, lParam);
    }
    
    int getForegroundPixel(final ToolItem item) {
        if (item != null && item.foreground != -1) {
            return item.foreground;
        }
        return this.getForegroundPixel();
    }
    
    int getBackgroundPixel(final ToolItem item) {
        if (item != null && item.background != -1) {
            return item.background;
        }
        return this.getBackgroundPixel();
    }
    
    LRESULT wmNotifyChild(final NMHDR hdr, final long wParam, final long lParam) {
        Label_0706: {
            switch (hdr.code) {
                case -710: {
                    final NMTOOLBAR lpnmtb = new NMTOOLBAR();
                    OS.MoveMemory(lpnmtb, lParam, NMTOOLBAR.sizeof);
                    final ToolItem child = this.items[lpnmtb.iItem];
                    if (child != null) {
                        final Event event = new Event();
                        event.detail = 4;
                        final int index = (int)OS.SendMessage(this.handle, 1049, (long)lpnmtb.iItem, 0L);
                        final RECT rect = new RECT();
                        OS.SendMessage(this.handle, 1053, (long)index, rect);
                        event.setLocationInPixels(rect.left, rect.bottom);
                        child.sendSelectionEvent(13, event, false);
                        break;
                    }
                    break;
                }
                case -12: {
                    final NMTBCUSTOMDRAW nmcd = new NMTBCUSTOMDRAW();
                    OS.MoveMemory(nmcd, lParam, NMTBCUSTOMDRAW.sizeof);
                    final ToolItem child = this.items[(int)nmcd.dwItemSpec];
                    switch (nmcd.dwDrawStage) {
                        case 3: {
                            final int bits = OS.GetWindowLong(this.handle, -16);
                            if ((bits & 0x800) == 0x0) {
                                this.drawBackground(nmcd.hdc);
                            }
                            else {
                                final RECT rect2 = new RECT();
                                OS.SetRect(rect2, nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
                                this.drawBackground(nmcd.hdc, rect2);
                            }
                            return new LRESULT(4L);
                        }
                        case 1: {
                            long result = 0L;
                            if (this.background != -1 || (this.foreground != -1 && OS.IsWindowEnabled(this.handle)) || (this.state & 0x1000000) != 0x0) {
                                result = 32L;
                            }
                            return new LRESULT(result);
                        }
                        case 65537: {
                            long result = 8388608L;
                            nmcd.clrBtnFace = this.getBackgroundPixel(child);
                            nmcd.clrText = this.getForegroundPixel(child);
                            OS.MoveMemory(lParam, nmcd, NMTBCUSTOMDRAW.sizeof);
                            if (child != null && child.background != -1) {
                                final RECT rect3 = new RECT(nmcd.left, nmcd.top, nmcd.right, nmcd.bottom);
                                OS.SetDCBrushColor(nmcd.hdc, child.background);
                                OS.FillRect(nmcd.hdc, rect3, OS.GetStockObject(18));
                                result |= 0x400000L;
                            }
                            return new LRESULT(result);
                        }
                        default: {
                            break Label_0706;
                        }
                    }
                    break;
                }
                case -713: {
                    final NMTBHOTITEM lpnmhi = new NMTBHOTITEM();
                    OS.MoveMemory(lpnmhi, lParam, NMTBHOTITEM.sizeof);
                    switch (lpnmhi.dwFlags) {
                        case 1: {
                            if (this.lastArrowId != -1) {
                                return LRESULT.ONE;
                            }
                            break;
                        }
                        case 2: {
                            final RECT client = new RECT();
                            OS.GetClientRect(this.handle, client);
                            final int index2 = (int)OS.SendMessage(this.handle, 1049, (long)lpnmhi.idNew, 0L);
                            final RECT rect4 = new RECT();
                            OS.SendMessage(this.handle, 1053, (long)index2, rect4);
                            if (rect4.right > client.right || rect4.bottom > client.bottom) {
                                return LRESULT.ONE;
                            }
                            this.lastArrowId = lpnmhi.idNew;
                            break;
                        }
                        default: {
                            this.lastArrowId = -1;
                            break;
                        }
                    }
                    if ((lpnmhi.dwFlags & 0x20) == 0x0) {
                        this.lastHotId = lpnmhi.idNew;
                        break;
                    }
                    break;
                }
            }
        }
        return super.wmNotifyChild(hdr, wParam, lParam);
    }
    
    static {
        ToolBarClass = new TCHAR(0, "ToolbarWindow32", true);
        final WNDCLASS lpWndClass = new WNDCLASS();
        OS.GetClassInfo(0L, ToolBar.ToolBarClass, lpWndClass);
        ToolBarProc = lpWndClass.lpfnWndProc;
    }
}
