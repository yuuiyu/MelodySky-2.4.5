//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.events.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class TabFolder extends Composite
{
    TabItem[] items;
    ImageList imageList;
    static final long TabFolderProc;
    static final TCHAR TabFolderClass;
    boolean createdAsRTL;
    static final int ID_UPDOWN = 1;
    
    public TabFolder(final Composite parent, final int style) {
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
        return OS.CallWindowProc(TabFolder.TabFolderProc, hwnd, msg, wParam, lParam);
    }
    
    static int checkStyle(int style) {
        style = Widget.checkBits(style, 128, 1024, 0, 0, 0, 0);
        return style & 0xFFFFFCFF;
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    Point computeSizeInPixels(final int wHint, final int hHint, final boolean changed) {
        this.checkWidget();
        final Point size = super.computeSizeInPixels(wHint, hHint, changed);
        final RECT insetRect = new RECT();
        final RECT itemRect = new RECT();
        OS.SendMessage(this.handle, 4904, 0L, insetRect);
        int width = insetRect.left - insetRect.right;
        final int count = (int)OS.SendMessage(this.handle, 4868, 0L, 0L);
        if (count != 0) {
            OS.SendMessage(this.handle, 4874, (long)(count - 1), itemRect);
            width = Math.max(width, itemRect.right - insetRect.right);
        }
        final RECT rect = new RECT();
        OS.SetRect(rect, 0, 0, width, size.y);
        OS.SendMessage(this.handle, 4904, 1L, rect);
        final int border = this.getBorderWidthInPixels();
        final RECT rect4;
        final RECT rect2 = rect4 = rect;
        rect4.left -= border;
        final RECT rect5;
        final RECT rect3 = rect5 = rect;
        rect5.right += border;
        width = rect.right - rect.left;
        size.x = Math.max(width, size.x);
        return size;
    }
    
    Rectangle computeTrimInPixels(final int x, final int y, final int width, final int height) {
        this.checkWidget();
        final RECT rect = new RECT();
        OS.SetRect(rect, x, y, x + width, y + height);
        OS.SendMessage(this.handle, 4904, 1L, rect);
        final int border = this.getBorderWidthInPixels();
        final RECT rect6;
        final RECT rect2 = rect6 = rect;
        rect6.left -= border;
        final RECT rect7;
        final RECT rect3 = rect7 = rect;
        rect7.right += border;
        final RECT rect8;
        final RECT rect4 = rect8 = rect;
        rect8.top -= border;
        final RECT rect9;
        final RECT rect5 = rect9 = rect;
        rect9.bottom += border;
        final int newWidth = rect.right - rect.left;
        final int newHeight = rect.bottom - rect.top;
        return new Rectangle(rect.left, rect.top, newWidth, newHeight);
    }
    
    void createItem(final TabItem item, final int index) {
        final int count = (int)OS.SendMessage(this.handle, 4868, 0L, 0L);
        if (0 > index || index > count) {
            this.error(6);
        }
        if (count == this.items.length) {
            final TabItem[] newItems = new TabItem[this.items.length + 4];
            System.arraycopy(this.items, 0, newItems, 0, this.items.length);
            this.items = newItems;
        }
        final TCITEM tcItem = new TCITEM();
        if (OS.SendMessage(this.handle, 4926, (long)index, tcItem) == -1L) {
            this.error(14);
        }
        System.arraycopy(this.items, index, this.items, index + 1, count - index);
        this.items[index] = item;
        if (count == 0) {
            final Event event = new Event();
            event.item = (Widget)this.items[0];
            this.sendSelectionEvent(13, event, true);
        }
    }
    
    void createHandle() {
        super.createHandle();
        this.state &= 0xFFFFFEFD;
        final long hwndToolTip = OS.SendMessage(this.handle, 4909, 0L, 0L);
        OS.SendMessage(hwndToolTip, 1048, 0L, 32767L);
        this.createdAsRTL = ((this.style & 0x4000000) != 0x0);
    }
    
    void createWidget() {
        super.createWidget();
        this.items = new TabItem[4];
    }
    
    void destroyItem(final TabItem item) {
        int count;
        int index;
        for (count = (int)OS.SendMessage(this.handle, 4868, 0L, 0L), index = 0; index < count && this.items[index] != item; ++index) {}
        if (index == count) {
            return;
        }
        final int selectionIndex = (int)OS.SendMessage(this.handle, 4875, 0L, 0L);
        if (OS.SendMessage(this.handle, 4872, (long)index, 0L) == 0L) {
            this.error(15);
        }
        System.arraycopy(this.items, index + 1, this.items, index, --count - index);
        this.items[count] = null;
        if (count == 0) {
            if (this.imageList != null) {
                OS.SendMessage(this.handle, 4867, 0L, 0L);
                this.display.releaseImageList(this.imageList);
            }
            this.imageList = null;
            this.items = new TabItem[4];
        }
        if (count > 0 && index == selectionIndex) {
            this.setSelection(Math.max(0, selectionIndex - 1), true);
        }
    }
    
    void drawThemeBackground(final long hDC, final long hwnd, final RECT rect) {
        final RECT rect2 = new RECT();
        OS.GetClientRect(this.handle, rect2);
        OS.MapWindowPoints(this.handle, hwnd, rect2, 2);
        if (OS.IntersectRect(new RECT(), rect2, rect)) {
            OS.DrawThemeBackground(this.display.hTabTheme(), hDC, 10, 0, rect2, (RECT)null);
        }
    }
    
    Control findThemeControl() {
        return (Control)this;
    }
    
    Rectangle getClientAreaInPixels() {
        this.checkWidget();
        this.forceResize();
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        OS.SendMessage(this.handle, 4904, 0L, rect);
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        return new Rectangle(rect.left, rect.top, width, height);
    }
    
    public TabItem getItem(final int index) {
        this.checkWidget();
        final int count = (int)OS.SendMessage(this.handle, 4868, 0L, 0L);
        if (0 > index || index >= count) {
            this.error(6);
        }
        return this.items[index];
    }
    
    public TabItem getItem(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        final TCHITTESTINFO pinfo = new TCHITTESTINFO();
        pinfo.x = point.x;
        pinfo.y = point.y;
        final int index = (int)OS.SendMessage(this.handle, 4877, 0L, pinfo);
        if (index == -1) {
            return null;
        }
        return this.items[index];
    }
    
    public int getItemCount() {
        this.checkWidget();
        return (int)OS.SendMessage(this.handle, 4868, 0L, 0L);
    }
    
    public TabItem[] getItems() {
        this.checkWidget();
        final int count = (int)OS.SendMessage(this.handle, 4868, 0L, 0L);
        final TabItem[] result = new TabItem[count];
        System.arraycopy(this.items, 0, result, 0, count);
        return result;
    }
    
    public TabItem[] getSelection() {
        this.checkWidget();
        final int index = (int)OS.SendMessage(this.handle, 4875, 0L, 0L);
        if (index == -1) {
            return new TabItem[0];
        }
        return new TabItem[] { this.items[index] };
    }
    
    public int getSelectionIndex() {
        this.checkWidget();
        return (int)OS.SendMessage(this.handle, 4875, 0L, 0L);
    }
    
    int imageIndex(final Image image) {
        if (image == null) {
            return -1;
        }
        if (this.imageList == null) {
            final Rectangle bounds = image.getBoundsInPixels();
            this.imageList = this.display.getImageList(this.style & 0x4000000, bounds.width, bounds.height);
            final int index = this.imageList.add(image);
            final long hImageList = this.imageList.getHandle();
            OS.SendMessage(this.handle, 4867, 0L, hImageList);
            return index;
        }
        int index2 = this.imageList.indexOf(image);
        if (index2 == -1) {
            index2 = this.imageList.add(image);
        }
        else {
            this.imageList.put(index2, image);
        }
        return index2;
    }
    
    public int indexOf(final TabItem item) {
        this.checkWidget();
        if (item == null) {
            this.error(4);
        }
        for (int count = (int)OS.SendMessage(this.handle, 4868, 0L, 0L), i = 0; i < count; ++i) {
            if (this.items[i] == item) {
                return i;
            }
        }
        return -1;
    }
    
    Point minimumSize(final int wHint, final int hHint, final boolean flushCache) {
        int width = 0;
        int height = 0;
        for (final Control child : this._getChildren()) {
            int index;
            int count;
            for (index = 0, count = (int)OS.SendMessage(this.handle, 4868, 0L, 0L); index < count && this.items[index].control != child; ++index) {}
            if (index == count) {
                final Rectangle rect = DPIUtil.autoScaleUp(child.getBounds());
                width = Math.max(width, rect.x + rect.width);
                height = Math.max(height, rect.y + rect.height);
            }
            else {
                final Point size = DPIUtil.autoScaleUp(child.computeSize(DPIUtil.autoScaleDown(wHint), DPIUtil.autoScaleDown(hHint), flushCache));
                width = Math.max(width, size.x);
                height = Math.max(height, size.y);
            }
        }
        return new Point(width, height);
    }
    
    boolean mnemonicHit(final char key) {
        for (int i = 0; i < this.items.length; ++i) {
            final TabItem item = this.items[i];
            if (item != null) {
                final char ch = this.findMnemonic(item.getText());
                if (Character.toUpperCase(key) == Character.toUpperCase(ch) && this.forceFocus()) {
                    if (i != this.getSelectionIndex()) {
                        this.setSelection(i, true);
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    boolean mnemonicMatch(final char key) {
        for (final TabItem item : this.items) {
            if (item != null) {
                final char ch = this.findMnemonic(item.getText());
                if (Character.toUpperCase(key) == Character.toUpperCase(ch)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    void releaseChildren(final boolean destroy) {
        if (this.items != null) {
            for (int count = (int)OS.SendMessage(this.handle, 4868, 0L, 0L), i = 0; i < count; ++i) {
                final TabItem item = this.items[i];
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
            OS.SendMessage(this.handle, 4867, 0L, 0L);
            this.display.releaseImageList(this.imageList);
        }
        this.imageList = null;
    }
    
    void removeControl(final Control control) {
        super.removeControl(control);
        for (int count = (int)OS.SendMessage(this.handle, 4868, 0L, 0L), i = 0; i < count; ++i) {
            final TabItem item = this.items[i];
            if (item.control == control) {
                item.setControl(null);
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
    
    void reskinChildren(final int flags) {
        if (this.items != null) {
            for (int count = (int)OS.SendMessage(this.handle, 4868, 0L, 0L), i = 0; i < count; ++i) {
                final TabItem item = this.items[i];
                if (item != null) {
                    item.reskin(flags);
                }
            }
        }
        super.reskinChildren(flags);
    }
    
    public void setSelection(final TabItem item) {
        this.checkWidget();
        if (item == null) {
            this.error(4);
        }
        this.setSelection(new TabItem[] { item });
    }
    
    public void setSelection(final TabItem[] items) {
        this.checkWidget();
        if (items == null) {
            this.error(4);
        }
        if (items.length == 0) {
            this.setSelection(-1, false);
        }
        else {
            for (int i = items.length - 1; i >= 0; --i) {
                final int index = this.indexOf(items[i]);
                if (index != -1) {
                    this.setSelection(index, false);
                }
            }
        }
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        final Rectangle oldRect = this.getClientAreaInPixels();
        super.setFont(font);
        final Rectangle newRect = this.getClientAreaInPixels();
        if (!oldRect.equals((Object)newRect)) {
            this.sendResize();
            final int index = (int)OS.SendMessage(this.handle, 4875, 0L, 0L);
            if (index != -1) {
                final TabItem item = this.items[index];
                final Control control = item.control;
                if (control != null && !control.isDisposed()) {
                    control.setBoundsInPixels(this.getClientAreaInPixels());
                }
            }
        }
    }
    
    public void setSelection(final int index) {
        this.checkWidget();
        final int count = (int)OS.SendMessage(this.handle, 4868, 0L, 0L);
        if (0 > index || index >= count) {
            return;
        }
        this.setSelection(index, false);
    }
    
    void setSelection(final int index, final boolean notify) {
        final int oldIndex = (int)OS.SendMessage(this.handle, 4875, 0L, 0L);
        if (oldIndex == index) {
            return;
        }
        if (oldIndex != -1) {
            final TabItem item = this.items[oldIndex];
            final Control control = item.control;
            if (control != null && !control.isDisposed()) {
                control.setVisible(false);
            }
        }
        OS.SendMessage(this.handle, 4876, (long)index, 0L);
        final int newIndex = (int)OS.SendMessage(this.handle, 4875, 0L, 0L);
        if (newIndex != -1) {
            final TabItem item2 = this.items[newIndex];
            final Control control2 = item2.control;
            if (control2 != null && !control2.isDisposed()) {
                control2.setBoundsInPixels(this.getClientAreaInPixels());
                control2.setVisible(true);
            }
            if (notify) {
                final Event event = new Event();
                event.item = (Widget)item2;
                this.sendSelectionEvent(13, event, true);
            }
        }
    }
    
    boolean updateTextDirection(int textDirection) {
        if (super.updateTextDirection(textDirection)) {
            if (textDirection != 100663296) {
                textDirection = (this.style & Integer.MIN_VALUE);
            }
            for (int i = 0, n = this.items.length; i < n && this.items[i] != null; ++i) {
                this.items[i].updateTextDirection(textDirection);
            }
            return true;
        }
        return false;
    }
    
    String toolTipText(final NMTTDISPINFO hdr) {
        if ((hdr.uFlags & 0x1) != 0x0) {
            return null;
        }
        final int index = (int)hdr.idFrom;
        final long hwndToolTip = OS.SendMessage(this.handle, 4909, 0L, 0L);
        if (hwndToolTip == hdr.hwndFrom) {
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
                final TabItem item = this.items[index];
                if (item != null) {
                    return item.toolTipText;
                }
            }
        }
        return super.toolTipText(hdr);
    }
    
    boolean traversePage(final boolean next) {
        final int count = this.getItemCount();
        if (count <= 1) {
            return false;
        }
        int index = this.getSelectionIndex();
        if (index == -1) {
            index = 0;
        }
        else {
            final int offset = next ? 1 : -1;
            index = (index + offset + count) % count;
        }
        this.setSelection(index, true);
        if (index == this.getSelectionIndex()) {
            OS.SendMessage(this.handle, 295, 3L, 0L);
            return true;
        }
        return false;
    }
    
    void updateOrientation() {
        super.updateOrientation();
        for (long hwndChild = OS.GetWindow(this.handle, 5); hwndChild != 0L; hwndChild = OS.GetWindow(hwndChild, 2)) {
            final char[] buffer = new char[128];
            final int length = OS.GetClassName(hwndChild, buffer, buffer.length);
            final String className = new String(buffer, 0, length);
            if (className.equals("msctls_updown32")) {
                int bits = OS.GetWindowLong(hwndChild, -20);
                if ((this.style & 0x4000000) != 0x0) {
                    bits |= 0x400000;
                }
                else {
                    bits &= 0xFFBFFFFF;
                }
                bits &= 0xFFFFDFFF;
                OS.SetWindowLong(hwndChild, -20, bits);
                OS.InvalidateRect(hwndChild, (RECT)null, true);
                break;
            }
        }
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        OS.SetWindowPos(this.handle, 0L, 0, 0, width - 1, height - 1, 6);
        OS.SetWindowPos(this.handle, 0L, 0, 0, width, height, 6);
        if (this.imageList != null) {
            final Point size = this.imageList.getImageSize();
            this.display.releaseImageList(this.imageList);
            this.imageList = this.display.getImageList(this.style & 0x4000000, size.x, size.y);
            final long hImageList = this.imageList.getHandle();
            OS.SendMessage(this.handle, 4867, 0L, hImageList);
            final TCITEM tcItem = new TCITEM();
            tcItem.mask = 2;
            for (int i = 0; i < this.items.length; ++i) {
                final TabItem item = this.items[i];
                if (item == null) {
                    break;
                }
                final Image image = item.image;
                if (image != null) {
                    tcItem.iImage = this.imageIndex(image);
                    OS.SendMessage(this.handle, 4925, (long)i, tcItem);
                }
            }
        }
    }
    
    int widgetStyle() {
        int bits = super.widgetStyle() | 0x2000000;
        if ((this.style & 0x80000) != 0x0) {
            bits |= 0x8000;
        }
        if ((this.style & 0x400) != 0x0) {
            bits |= 0x2;
        }
        return bits | 0x0 | 0x4000;
    }
    
    TCHAR windowClass() {
        return TabFolder.TabFolderClass;
    }
    
    long windowProc() {
        return TabFolder.TabFolderProc;
    }
    
    LRESULT WM_GETDLGCODE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_GETDLGCODE(wParam, lParam);
        if (result != null) {
            return result;
        }
        return new LRESULT(8193L);
    }
    
    LRESULT WM_GETOBJECT(final long wParam, final long lParam) {
        if (this.accessible == null) {
            this.accessible = this.new_Accessible((Control)this);
        }
        return super.WM_GETOBJECT(wParam, lParam);
    }
    
    LRESULT WM_KEYDOWN(final long wParam, final long lParam) {
        final LRESULT result = super.WM_KEYDOWN(wParam, lParam);
        if (result != null) {
            return result;
        }
        switch ((int)wParam) {
            case 37:
            case 39: {
                final boolean isRTL = (this.style & 0x4000000) != 0x0;
                if (isRTL != this.createdAsRTL) {
                    final long code = this.callWindowProc(this.handle, 256, (wParam == 39L) ? 37L : 39L, lParam);
                    return new LRESULT(code);
                }
                break;
            }
        }
        return result;
    }
    
    LRESULT WM_MOUSELEAVE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_MOUSELEAVE(wParam, lParam);
        if (result != null) {
            return result;
        }
        final TOOLINFO lpti = new TOOLINFO();
        lpti.cbSize = TOOLINFO.sizeof;
        final long hwndToolTip = OS.SendMessage(this.handle, 4909, 0L, 0L);
        if (OS.SendMessage(hwndToolTip, 1083, 0L, lpti) != 0L && (lpti.uFlags & 0x1) == 0x0) {
            OS.SendMessage(hwndToolTip, 1075, 0L, lpti);
            OS.SendMessage(hwndToolTip, 1074, 0L, lpti);
        }
        return result;
    }
    
    LRESULT WM_NCHITTEST(final long wParam, final long lParam) {
        final LRESULT result = super.WM_NCHITTEST(wParam, lParam);
        if (result != null) {
            return result;
        }
        final long hittest = OS.DefWindowProc(this.handle, 132, wParam, lParam);
        return new LRESULT(hittest);
    }
    
    LRESULT WM_NOTIFY(final long wParam, final long lParam) {
        final LRESULT result = super.WM_NOTIFY(wParam, lParam);
        if (result != null) {
            return result;
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_PARENTNOTIFY(final long wParam, final long lParam) {
        final LRESULT result = super.WM_PARENTNOTIFY(wParam, lParam);
        if (result != null) {
            return result;
        }
        if ((this.style & 0x4000000) != 0x0) {
            final int code = OS.LOWORD(wParam);
            switch (code) {
                case 1: {
                    final int id = OS.HIWORD(wParam);
                    final long hwnd = lParam;
                    if (id == 1) {
                        final int bits = OS.GetWindowLong(hwnd, -20);
                        OS.SetWindowLong(hwnd, -20, bits | 0x400000);
                        break;
                    }
                    break;
                }
            }
        }
        return result;
    }
    
    LRESULT WM_SIZE(final long wParam, final long lParam) {
        final LRESULT result = super.WM_SIZE(wParam, lParam);
        if (this.isDisposed()) {
            return result;
        }
        final int index = (int)OS.SendMessage(this.handle, 4875, 0L, 0L);
        if (index != -1) {
            final TabItem item = this.items[index];
            final Control control = item.control;
            if (control != null && !control.isDisposed()) {
                control.setBoundsInPixels(this.getClientAreaInPixels());
            }
        }
        return result;
    }
    
    LRESULT WM_WINDOWPOSCHANGING(final long wParam, final long lParam) {
        final LRESULT result = super.WM_WINDOWPOSCHANGING(wParam, lParam);
        if (result != null) {
            return result;
        }
        if (!OS.IsWindowVisible(this.handle)) {
            return result;
        }
        final WINDOWPOS lpwp = new WINDOWPOS();
        OS.MoveMemory(lpwp, lParam, WINDOWPOS.sizeof);
        if ((lpwp.flags & 0x9) != 0x0) {
            return result;
        }
        final int bits = OS.GetWindowLong(this.handle, -16);
        if ((bits & 0x200) != 0x0) {
            OS.InvalidateRect(this.handle, (RECT)null, true);
            return result;
        }
        final RECT rect = new RECT();
        OS.SetRect(rect, 0, 0, lpwp.cx, lpwp.cy);
        OS.SendMessage(this.handle, 131, 0L, rect);
        final int newWidth = rect.right - rect.left;
        final int newHeight = rect.bottom - rect.top;
        OS.GetClientRect(this.handle, rect);
        int oldWidth = rect.right - rect.left;
        final int oldHeight = rect.bottom - rect.top;
        if (newWidth == oldWidth && newHeight == oldHeight) {
            return result;
        }
        final RECT inset = new RECT();
        OS.SendMessage(this.handle, 4904, 0L, inset);
        final int marginX = -inset.right;
        final int marginY = -inset.bottom;
        if (newWidth != oldWidth) {
            int left;
            if (newWidth < (left = oldWidth)) {
                left = newWidth;
            }
            OS.SetRect(rect, left - marginX, 0, newWidth, newHeight);
            OS.InvalidateRect(this.handle, rect, true);
        }
        if (newHeight != oldHeight) {
            int bottom;
            if (newHeight < (bottom = oldHeight)) {
                bottom = newHeight;
            }
            if (newWidth < oldWidth) {
                oldWidth -= marginX;
            }
            OS.SetRect(rect, 0, bottom - marginY, oldWidth, newHeight);
            OS.InvalidateRect(this.handle, rect, true);
        }
        return result;
    }
    
    LRESULT wmNotifyChild(final NMHDR hdr, final long wParam, final long lParam) {
        final int code = hdr.code;
        switch (code) {
            case -552:
            case -551: {
                TabItem item = null;
                final int index = (int)OS.SendMessage(this.handle, 4875, 0L, 0L);
                if (index != -1) {
                    item = this.items[index];
                }
                if (item != null) {
                    final Control control = item.control;
                    if (control != null && !control.isDisposed()) {
                        if (code == -551) {
                            control.setBoundsInPixels(this.getClientAreaInPixels());
                        }
                        control.setVisible(code == -551);
                    }
                }
                if (code == -551) {
                    final Event event = new Event();
                    event.item = (Widget)item;
                    this.sendSelectionEvent(13, event, false);
                    break;
                }
                break;
            }
        }
        return super.wmNotifyChild(hdr, wParam, lParam);
    }
    
    static {
        TabFolderClass = new TCHAR(0, "SysTabControl32", true);
        final WNDCLASS lpWndClass = new WNDCLASS();
        OS.GetClassInfo(0L, TabFolder.TabFolderClass, lpWndClass);
        TabFolderProc = lpWndClass.lpfnWndProc;
        lpWndClass.hInstance = OS.GetModuleHandle((char[])null);
        final WNDCLASS wndclass2;
        final WNDCLASS wndclass = wndclass2 = lpWndClass;
        wndclass2.style &= 0xFFFFBFFC;
        OS.RegisterClass(TabFolder.TabFolderClass, lpWndClass);
    }
}
