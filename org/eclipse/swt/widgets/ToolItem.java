//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;

public class ToolItem extends Item
{
    ToolBar parent;
    Control control;
    String toolTipText;
    Image disabledImage;
    Image hotImage;
    Image disabledImage2;
    int id;
    short cx;
    int foreground;
    int background;
    
    public ToolItem(final ToolBar parent, final int style) {
        super((Widget)parent, checkStyle(style));
        this.foreground = -1;
        this.background = -1;
        (this.parent = parent).createItem(this, parent.getItemCount());
    }
    
    public ToolItem(final ToolBar parent, final int style, final int index) {
        super((Widget)parent, checkStyle(style));
        this.foreground = -1;
        this.background = -1;
        (this.parent = parent).createItem(this, index);
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
    
    static int checkStyle(final int style) {
        return Widget.checkBits(style, 8, 32, 16, 2, 4, 0);
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    void click(final boolean dropDown) {
        final long hwnd = this.parent.handle;
        if (OS.GetKeyState(1) < 0) {
            return;
        }
        final int index = (int)OS.SendMessage(hwnd, 1049, (long)this.id, 0L);
        final RECT rect = new RECT();
        OS.SendMessage(hwnd, 1053, (long)index, rect);
        final int hotIndex = (int)OS.SendMessage(hwnd, 1095, 0L, 0L);
        final int y = rect.top + (rect.bottom - rect.top) / 2;
        final long lParam = OS.MAKELPARAM(dropDown ? (rect.right - 1) : rect.left, y);
        this.parent.ignoreMouse = true;
        OS.SendMessage(hwnd, 513, 0L, lParam);
        OS.SendMessage(hwnd, 514, 0L, lParam);
        this.parent.ignoreMouse = false;
        if (hotIndex != -1) {
            OS.SendMessage(hwnd, 1096, (long)hotIndex, 0L);
        }
    }
    
    Widget[] computeTabList() {
        if (this.isTabGroup() && this.getEnabled()) {
            if ((this.style & 0x2) == 0x0) {
                return new Widget[] { (Widget)this };
            }
            if (this.control != null) {
                return this.control.computeTabList();
            }
        }
        return new Widget[0];
    }
    
    void destroyWidget() {
        this.parent.destroyItem(this);
        this.releaseHandle();
    }
    
    public Rectangle getBounds() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getBoundsInPixels());
    }
    
    Rectangle getBoundsInPixels() {
        final long hwnd = this.parent.handle;
        final int index = (int)OS.SendMessage(hwnd, 1049, (long)this.id, 0L);
        final RECT rect = new RECT();
        OS.SendMessage(hwnd, 1053, (long)index, rect);
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        return new Rectangle(rect.left, rect.top, width, height);
    }
    
    public Control getControl() {
        this.checkWidget();
        return this.control;
    }
    
    public Image getDisabledImage() {
        this.checkWidget();
        return this.disabledImage;
    }
    
    public Color getBackground() {
        this.checkWidget();
        return Color.win32_new((Device)this.display, this.parent.getBackgroundPixel(this));
    }
    
    public boolean getEnabled() {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            return (this.state & 0x8) == 0x0;
        }
        final long hwnd = this.parent.handle;
        final long fsState = OS.SendMessage(hwnd, 1042, (long)this.id, 0L);
        return (fsState & 0x4L) != 0x0L;
    }
    
    public Color getForeground() {
        this.checkWidget();
        return Color.win32_new((Device)this.display, this.parent.getForegroundPixel(this));
    }
    
    public Image getHotImage() {
        this.checkWidget();
        return this.hotImage;
    }
    
    public Image getImage() {
        return super.getImage();
    }
    
    public ToolBar getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public boolean getSelection() {
        this.checkWidget();
        if ((this.style & 0x30) == 0x0) {
            return false;
        }
        final long hwnd = this.parent.handle;
        final long fsState = OS.SendMessage(hwnd, 1042, (long)this.id, 0L);
        return (fsState & 0x1L) != 0x0L;
    }
    
    public String getToolTipText() {
        this.checkWidget();
        return this.toolTipText;
    }
    
    public int getWidth() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getWidthInPixels());
    }
    
    int getWidthInPixels() {
        final long hwnd = this.parent.handle;
        final int index = (int)OS.SendMessage(hwnd, 1049, (long)this.id, 0L);
        final RECT rect = new RECT();
        OS.SendMessage(hwnd, 1053, (long)index, rect);
        return rect.right - rect.left;
    }
    
    public boolean isEnabled() {
        this.checkWidget();
        return this.getEnabled() && this.parent.isEnabled();
    }
    
    boolean isTabGroup() {
        final ToolItem[] tabList = this.parent._getTabItemList();
        if (tabList != null) {
            for (final ToolItem item : tabList) {
                if (item == this) {
                    return true;
                }
            }
        }
        if ((this.style & 0x2) != 0x0) {
            return true;
        }
        final int index = this.parent.indexOf(this);
        if (index == 0) {
            return true;
        }
        final ToolItem previous = this.parent.getItem(index - 1);
        return (previous.getStyle() & 0x2) != 0x0;
    }
    
    void redraw() {
        final RECT rect = new RECT();
        if (OS.SendMessage(this.parent.handle, 1075, (long)this.id, rect) != 0L) {
            OS.InvalidateRect(this.parent.handle, rect, true);
        }
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.releaseImages();
        this.control = null;
        this.toolTipText = null;
        final Image image = null;
        this.hotImage = image;
        this.disabledImage = image;
        if (this.disabledImage2 != null) {
            this.disabledImage2.dispose();
        }
        this.disabledImage2 = null;
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
        this.id = -1;
    }
    
    void releaseImages() {
        final TBBUTTONINFO info = new TBBUTTONINFO();
        info.cbSize = TBBUTTONINFO.sizeof;
        info.dwMask = 9;
        final long hwnd = this.parent.handle;
        OS.SendMessage(hwnd, 1087, (long)this.id, info);
        if ((info.fsStyle & 0x1) == 0x0 && info.iImage != -2) {
            final ImageList imageList = this.parent.getImageList();
            final ImageList hotImageList = this.parent.getHotImageList();
            final ImageList disabledImageList = this.parent.getDisabledImageList();
            if (imageList != null) {
                imageList.put(info.iImage, (Image)null);
            }
            if (hotImageList != null) {
                hotImageList.put(info.iImage, (Image)null);
            }
            if (disabledImageList != null) {
                disabledImageList.put(info.iImage, (Image)null);
            }
        }
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
    
    void resizeControl() {
        if (this.control != null && !this.control.isDisposed()) {
            final Rectangle itemRect = this.getBounds();
            this.control.setSize(itemRect.width, itemRect.height);
            final Rectangle rect = this.control.getBounds();
            rect.x = itemRect.x + (itemRect.width - rect.width) / 2;
            rect.y = itemRect.y + (itemRect.height - rect.height) / 2;
            this.control.setLocation(rect.x, rect.y);
        }
    }
    
    void selectRadio() {
        int index;
        ToolItem[] items;
        for (index = 0, items = this.parent.getItems(); index < items.length && items[index] != this; ++index) {}
        for (int i = index - 1; i >= 0 && items[i].setRadioSelection(false); --i) {}
        for (int j = index + 1; j < items.length && items[j].setRadioSelection(false); ++j) {}
        this.setSelection(true);
    }
    
    public void setBackground(final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            this.error(5);
        }
        final ToolBar parent2;
        final ToolBar parent = parent2 = this.parent;
        parent2.state |= 0x1000000;
        final int pixel = (color != null) ? color.handle : -1;
        if (pixel == this.background) {
            return;
        }
        this.background = pixel;
        this.redraw();
    }
    
    public void setControl(final Control control) {
        this.checkWidget();
        if (control != null) {
            if (control.isDisposed()) {
                this.error(5);
            }
            if (control.parent != this.parent) {
                this.error(32);
            }
        }
        if ((this.style & 0x2) == 0x0) {
            return;
        }
        this.parent.layout(true);
        this.control = control;
        if ((this.parent.style & 0x240) != 0x0) {
            boolean changed = false;
            final long hwnd = this.parent.handle;
            final TBBUTTONINFO info = new TBBUTTONINFO();
            info.cbSize = TBBUTTONINFO.sizeof;
            info.dwMask = 12;
            OS.SendMessage(hwnd, 1087, (long)this.id, info);
            if (control == null) {
                if ((info.fsStyle & 0x1) == 0x0) {
                    changed = true;
                    final TBBUTTONINFO tbbuttoninfo9;
                    final TBBUTTONINFO tbbuttoninfo = tbbuttoninfo9 = info;
                    tbbuttoninfo9.fsStyle &= 0xFFFFFFBF;
                    final TBBUTTONINFO tbbuttoninfo10;
                    final TBBUTTONINFO tbbuttoninfo2 = tbbuttoninfo10 = info;
                    tbbuttoninfo10.fsStyle |= 0x1;
                    if ((this.state & 0x8) != 0x0) {
                        final TBBUTTONINFO tbbuttoninfo11;
                        final TBBUTTONINFO tbbuttoninfo3 = tbbuttoninfo11 = info;
                        tbbuttoninfo11.fsState &= 0xFFFFFFFB;
                    }
                    else {
                        final TBBUTTONINFO tbbuttoninfo12;
                        final TBBUTTONINFO tbbuttoninfo4 = tbbuttoninfo12 = info;
                        tbbuttoninfo12.fsState |= 0x4;
                    }
                }
            }
            else if ((info.fsStyle & 0x1) != 0x0) {
                changed = true;
                final TBBUTTONINFO tbbuttoninfo13;
                final TBBUTTONINFO tbbuttoninfo5 = tbbuttoninfo13 = info;
                tbbuttoninfo13.fsStyle &= 0xFFFFFFFE;
                final TBBUTTONINFO tbbuttoninfo14;
                final TBBUTTONINFO tbbuttoninfo6 = tbbuttoninfo14 = info;
                tbbuttoninfo14.fsStyle |= 0x40;
                final TBBUTTONINFO tbbuttoninfo15;
                final TBBUTTONINFO tbbuttoninfo7 = tbbuttoninfo15 = info;
                tbbuttoninfo15.fsState &= 0xFFFFFFFB;
                final TBBUTTONINFO tbbuttoninfo16;
                final TBBUTTONINFO tbbuttoninfo8 = tbbuttoninfo16 = info;
                tbbuttoninfo16.dwMask |= 0x1;
                info.iImage = -2;
            }
            if (changed) {
                OS.SendMessage(hwnd, 1088, (long)this.id, info);
                if (OS.SendMessage(hwnd, 1064, 0L, 0L) > 1L) {
                    OS.InvalidateRect(hwnd, (RECT)null, true);
                }
            }
        }
        this.resizeControl();
    }
    
    public void setEnabled(final boolean enabled) {
        this.checkWidget();
        final long hwnd = this.parent.handle;
        int fsState = (int)OS.SendMessage(hwnd, 1042, (long)this.id, 0L);
        if ((fsState & 0x4) != 0x0 == enabled) {
            return;
        }
        if (enabled) {
            fsState |= 0x4;
            this.state &= 0xFFFFFFF7;
        }
        else {
            fsState &= 0xFFFFFFFB;
            this.state |= 0x8;
        }
        OS.SendMessage(hwnd, 1041, (long)this.id, (long)fsState);
        if ((this.style & 0x2) == 0x0 && this.image != null) {
            this.updateImages(enabled && this.parent.getEnabled());
        }
        if (!enabled && this.parent.lastFocusId == this.id) {
            this.parent.lastFocusId = -1;
        }
    }
    
    public void setDisabledImage(final Image image) {
        this.checkWidget();
        if (this.disabledImage == image) {
            return;
        }
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        this.parent.layout(this.isImageSizeChanged(this.disabledImage, image));
        this.disabledImage = image;
        this.updateImages(this.getEnabled() && this.parent.getEnabled());
    }
    
    public void setForeground(final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            this.error(5);
        }
        final ToolBar parent2;
        final ToolBar parent = parent2 = this.parent;
        parent2.state |= 0x1000000;
        final int pixel = (color != null) ? color.handle : -1;
        if (pixel == this.foreground) {
            return;
        }
        this.foreground = pixel;
        this.redraw();
    }
    
    public void setHotImage(final Image image) {
        this.checkWidget();
        if (this.hotImage == image) {
            return;
        }
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        this.parent.layout(this.isImageSizeChanged(this.hotImage, image));
        this.hotImage = image;
        this.updateImages(this.getEnabled() && this.parent.getEnabled());
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if (this.image == image) {
            return;
        }
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        this.parent.layout(this.isImageSizeChanged(super.image, image));
        super.setImage(image);
        this.updateImages(this.getEnabled() && this.parent.getEnabled());
    }
    
    boolean isImageSizeChanged(final Image oldImage, final Image image) {
        boolean changed = true;
        if (oldImage != null && !oldImage.isDisposed() && image != null && !image.isDisposed()) {
            changed = !oldImage.getBounds().equals((Object)image.getBounds());
        }
        return changed;
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
    
    public void setSelection(final boolean selected) {
        this.checkWidget();
        if ((this.style & 0x30) == 0x0) {
            return;
        }
        final long hwnd = this.parent.handle;
        int fsState = (int)OS.SendMessage(hwnd, 1042, (long)this.id, 0L);
        if ((fsState & 0x1) != 0x0 == selected) {
            return;
        }
        if (selected) {
            fsState |= 0x1;
        }
        else {
            fsState &= 0xFFFFFFFE;
        }
        OS.SendMessage(hwnd, 1041, (long)this.id, (long)fsState);
        if ((this.style & 0x30) != 0x0 && (!this.getEnabled() || !this.parent.getEnabled())) {
            this.updateImages(false);
        }
    }
    
    boolean setTabItemFocus() {
        if (this.parent.setTabItemFocus()) {
            final long hwnd = this.parent.handle;
            final int index = (int)OS.SendMessage(hwnd, 1049, (long)this.id, 0L);
            OS.SendMessage(hwnd, 1096, (long)index, 0L);
            return true;
        }
        return false;
    }
    
    void _setText(final String string) {
        final long hwnd = this.parent.handle;
        final TBBUTTONINFO info = new TBBUTTONINFO();
        info.cbSize = TBBUTTONINFO.sizeof;
        info.dwMask = 10;
        info.fsStyle = (byte)(this.widgetStyle() | 0x10);
        final long hHeap = OS.GetProcessHeap();
        long pszText = 0L;
        if (string.length() != 0) {
            final TBBUTTONINFO tbbuttoninfo2;
            final TBBUTTONINFO tbbuttoninfo = tbbuttoninfo2 = info;
            tbbuttoninfo2.fsStyle |= 0x40;
            TCHAR buffer;
            if ((this.style & Integer.MIN_VALUE) != 0x0) {
                final int bits = OS.GetWindowLong(hwnd, -20);
                if ((bits & 0x400000) != 0x0) {
                    buffer = new TCHAR(this.parent.getCodePage(), "\u202a" + string, true);
                }
                else {
                    buffer = new TCHAR(this.parent.getCodePage(), "\u202b" + string, true);
                }
            }
            else {
                buffer = new TCHAR(this.parent.getCodePage(), string, true);
            }
            final int byteCount = buffer.length() * 2;
            pszText = OS.HeapAlloc(hHeap, 8, byteCount);
            OS.MoveMemory(pszText, buffer, byteCount);
            info.pszText = pszText;
        }
        OS.SendMessage(hwnd, 1088, (long)this.id, info);
        if (pszText != 0L) {
            OS.HeapFree(hHeap, 0, pszText);
        }
    }
    
    public void setText(final String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        if (string.equals(this.text)) {
            return;
        }
        this.parent.layout(true);
        super.setText(string);
        if ((this.state & 0x400000) == 0x0 || !this.updateTextDirection(100663296)) {
            this._setText(string);
        }
        this.parent.setDropDownItems(false);
        final long hwnd = this.parent.handle;
        final long hFont = OS.SendMessage(hwnd, 49, 0L, 0L);
        OS.SendMessage(hwnd, 48, hFont, 0L);
        this.parent.setDropDownItems(true);
        this.parent.layoutItems();
    }
    
    boolean updateTextDirection(final int textDirection) {
        if (super.updateTextDirection(textDirection) && this.text.length() != 0) {
            this._setText(this.text);
            return true;
        }
        return false;
    }
    
    public void setToolTipText(final String string) {
        this.checkWidget();
        this.toolTipText = string;
    }
    
    public void setWidth(final int width) {
        this.checkWidget();
        this.setWidthInPixels(DPIUtil.autoScaleUp(width));
    }
    
    void setWidthInPixels(final int width) {
        if ((this.style & 0x2) == 0x0) {
            return;
        }
        if (width < 0) {
            return;
        }
        final long hwnd = this.parent.handle;
        final TBBUTTONINFO info = new TBBUTTONINFO();
        info.cbSize = TBBUTTONINFO.sizeof;
        info.dwMask = 64;
        final TBBUTTONINFO tbbuttoninfo = info;
        final short n = (short)width;
        this.cx = n;
        tbbuttoninfo.cx = n;
        OS.SendMessage(hwnd, 1088, (long)this.id, info);
        this.parent.layoutItems();
    }
    
    void updateImages(final boolean enabled) {
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        final long hwnd = this.parent.handle;
        final TBBUTTONINFO info = new TBBUTTONINFO();
        info.cbSize = TBBUTTONINFO.sizeof;
        info.dwMask = 1;
        OS.SendMessage(hwnd, 1087, (long)this.id, info);
        if (info.iImage == -2 && this.image == null) {
            return;
        }
        ImageList imageList = this.parent.getImageList();
        ImageList hotImageList = this.parent.getHotImageList();
        ImageList disabledImageList = this.parent.getDisabledImageList();
        if (info.iImage == -2) {
            final Rectangle bounds = this.image.getBoundsInPixels();
            final int listStyle = this.parent.style & 0x4000000;
            if (imageList == null) {
                imageList = this.display.getImageListToolBar(listStyle, bounds.width, bounds.height);
            }
            if (disabledImageList == null) {
                disabledImageList = this.display.getImageListToolBarDisabled(listStyle, bounds.width, bounds.height);
            }
            if (hotImageList == null) {
                hotImageList = this.display.getImageListToolBarHot(listStyle, bounds.width, bounds.height);
            }
            Image disabled = this.disabledImage;
            if (this.disabledImage == null) {
                if (this.disabledImage2 != null) {
                    this.disabledImage2.dispose();
                }
                this.disabledImage2 = null;
                disabled = this.image;
                if (!enabled) {
                    final Image disabledImage2 = new Image((Device)this.display, this.image, 1);
                    this.disabledImage2 = disabledImage2;
                    disabled = disabledImage2;
                }
            }
            Image image2 = this.image;
            Image hot = this.hotImage;
            if ((this.style & 0x30) != 0x0 && !enabled) {
                image2 = (hot = disabled);
            }
            info.iImage = imageList.add(image2);
            disabledImageList.add(disabled);
            hotImageList.add((hot != null) ? hot : image2);
            this.parent.setImageList(imageList);
            this.parent.setDisabledImageList(disabledImageList);
            this.parent.setHotImageList(hotImageList);
        }
        else {
            Image disabled2 = null;
            if (disabledImageList != null) {
                if (this.image != null) {
                    if (this.disabledImage2 != null) {
                        this.disabledImage2.dispose();
                    }
                    this.disabledImage2 = null;
                    disabled2 = this.disabledImage;
                    if (this.disabledImage == null) {
                        disabled2 = this.image;
                        if (!enabled) {
                            final Image disabledImage3 = new Image((Device)this.display, this.image, 1);
                            this.disabledImage2 = disabledImage3;
                            disabled2 = disabledImage3;
                        }
                    }
                }
                disabledImageList.put(info.iImage, disabled2);
            }
            Image image3 = this.image;
            Image hot2 = this.hotImage;
            if ((this.style & 0x30) != 0x0 && !enabled) {
                image3 = (hot2 = disabled2);
            }
            if (imageList != null) {
                imageList.put(info.iImage, image3);
            }
            if (hotImageList != null) {
                hotImageList.put(info.iImage, (hot2 != null) ? hot2 : image3);
            }
            if (this.image == null) {
                info.iImage = -2;
            }
        }
        final TBBUTTONINFO tbbuttoninfo2;
        final TBBUTTONINFO tbbuttoninfo = tbbuttoninfo2 = info;
        tbbuttoninfo2.dwMask |= 0x40;
        info.cx = 0;
        OS.SendMessage(hwnd, 1088, (long)this.id, info);
        final long hFont = OS.SendMessage(hwnd, 49, 0L, 0L);
        OS.SendMessage(hwnd, 48, hFont, 0L);
        this.parent.layoutItems();
    }
    
    int widgetStyle() {
        if ((this.style & 0x4) != 0x0) {
            return 8;
        }
        if ((this.style & 0x8) != 0x0) {
            return 0;
        }
        if ((this.style & 0x20) != 0x0) {
            return 2;
        }
        if ((this.style & 0x10) != 0x0) {
            return 2;
        }
        if ((this.style & 0x2) != 0x0) {
            return 1;
        }
        return 0;
    }
    
    LRESULT wmCommandChild(final long wParam, final long lParam) {
        if ((this.style & 0x10) != 0x0 && (this.parent.getStyle() & 0x400000) == 0x0) {
            this.selectRadio();
        }
        this.sendSelectionEvent(13);
        return null;
    }
}
