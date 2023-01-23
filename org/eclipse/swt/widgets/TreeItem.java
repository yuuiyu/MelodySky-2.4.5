//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.win32.*;

public class TreeItem extends Item
{
    public long handle;
    Tree parent;
    String[] strings;
    Image[] images;
    Font font;
    Font[] cellFont;
    boolean cached;
    int background;
    int foreground;
    int[] cellBackground;
    int[] cellForeground;
    
    public TreeItem(final Tree parent, final int style) {
        this(parent, style, -65536L, -65534L, 0L);
    }
    
    public TreeItem(final Tree parent, final int style, final int index) {
        this(parent, style, -65536L, findPrevious(parent, index), 0L);
    }
    
    public TreeItem(final TreeItem parentItem, final int style) {
        this(checkNull(parentItem).parent, style, parentItem.handle, -65534L, 0L);
    }
    
    public TreeItem(final TreeItem parentItem, final int style, final int index) {
        this(checkNull(parentItem).parent, style, parentItem.handle, findPrevious(parentItem, index), 0L);
    }
    
    TreeItem(final Tree parent, final int style, final long hParent, final long hInsertAfter, final long hItem) {
        super((Widget)parent, style);
        this.background = -1;
        this.foreground = -1;
        (this.parent = parent).createItem(this, hParent, hInsertAfter, hItem);
    }
    
    static TreeItem checkNull(final TreeItem item) {
        if (item == null) {
            SWT.error(4);
        }
        return item;
    }
    
    static long findPrevious(final Tree parent, final int index) {
        if (parent == null) {
            return 0L;
        }
        if (index < 0) {
            SWT.error(6);
        }
        if (index == 0) {
            return -65535L;
        }
        final long hwnd = parent.handle;
        final long hFirstItem = OS.SendMessage(hwnd, 4362, 0L, 0L);
        final long hItem = parent.findItem(hFirstItem, index - 1);
        if (hItem == 0L) {
            SWT.error(6);
        }
        return hItem;
    }
    
    static long findPrevious(final TreeItem parentItem, final int index) {
        if (parentItem == null) {
            return 0L;
        }
        if (index < 0) {
            SWT.error(6);
        }
        if (index == 0) {
            return -65535L;
        }
        final Tree parent = parentItem.parent;
        final long hwnd = parent.handle;
        final long hParent = parentItem.handle;
        final long hFirstItem = OS.SendMessage(hwnd, 4362, 4L, hParent);
        final long hItem = parent.findItem(hFirstItem, index - 1);
        if (hItem == 0L) {
            SWT.error(6);
        }
        return hItem;
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    void clear() {
        this.text = "";
        this.image = null;
        this.strings = null;
        this.images = null;
        if ((this.parent.style & 0x20) != 0x0) {
            final long hwnd = this.parent.handle;
            final TVITEM tvItem = new TVITEM();
            tvItem.mask = 24;
            tvItem.stateMask = 61440;
            tvItem.state = 4096;
            tvItem.hItem = this.handle;
            OS.SendMessage(hwnd, 4415, 0L, tvItem);
        }
        final int n = -1;
        this.foreground = -1;
        this.background = -1;
        this.font = null;
        final int[] array = null;
        this.cellForeground = array;
        this.cellBackground = array;
        this.cellFont = null;
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = false;
        }
    }
    
    public void clear(final int index, final boolean all) {
        this.checkWidget();
        final long hwnd = this.parent.handle;
        long hItem = OS.SendMessage(hwnd, 4362, 4L, this.handle);
        if (hItem == 0L) {
            this.error(6);
        }
        hItem = this.parent.findItem(hItem, index);
        if (hItem == 0L) {
            this.error(6);
        }
        final TVITEM tvItem = new TVITEM();
        tvItem.mask = 20;
        this.parent.clear(hItem, tvItem);
        if (all) {
            hItem = OS.SendMessage(hwnd, 4362, 4L, hItem);
            this.parent.clearAll(hItem, tvItem, all);
        }
    }
    
    public void clearAll(final boolean all) {
        this.checkWidget();
        final long hwnd = this.parent.handle;
        final long hItem = OS.SendMessage(hwnd, 4362, 4L, this.handle);
        if (hItem == 0L) {
            return;
        }
        final TVITEM tvItem = new TVITEM();
        tvItem.mask = 20;
        this.parent.clearAll(hItem, tvItem, all);
    }
    
    void destroyWidget() {
        final TVITEM tvItem = new TVITEM();
        tvItem.mask = 20;
        this.parent.releaseItem(this.handle, tvItem, false);
        this.parent.destroyItem(this, this.handle);
        this.releaseHandle();
    }
    
    long fontHandle(final int index) {
        if (this.cellFont != null && this.cellFont[index] != null) {
            return this.cellFont[index].handle;
        }
        if (this.font != null) {
            return this.font.handle;
        }
        return -1L;
    }
    
    public Color getBackground() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        if (this.background == -1) {
            return this.parent.getBackground();
        }
        return Color.win32_new((Device)this.display, this.background);
    }
    
    public Color getBackground(final int index) {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final int count = Math.max(1, this.parent.getColumnCount());
        if (0 > index || index > count - 1) {
            return this.getBackground();
        }
        final int pixel = (this.cellBackground != null) ? this.cellBackground[index] : -1;
        return (pixel == -1) ? this.getBackground() : Color.win32_new((Device)this.display, pixel);
    }
    
    public Rectangle getBounds() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getBoundsInPixels());
    }
    
    Rectangle getBoundsInPixels() {
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final RECT rect = this.getBounds(0, true, false, false);
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        return new Rectangle(rect.left, rect.top, width, height);
    }
    
    public Rectangle getBounds(final int index) {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getBoundsInPixels(index));
    }
    
    Rectangle getBoundsInPixels(final int index) {
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final RECT rect = this.getBounds(index, true, true, true);
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        return new Rectangle(rect.left, rect.top, width, height);
    }
    
    RECT getBounds(final int index, final boolean getText, final boolean getImage, final boolean fullText) {
        return this.getBounds(index, getText, getImage, fullText, false, true, 0L);
    }
    
    RECT getBounds(final int index, final boolean getText, final boolean getImage, final boolean fullText, final boolean fullImage, final boolean clip, final long hDC) {
        if (!getText && !getImage) {
            return new RECT();
        }
        final long hwnd = this.parent.handle;
        if ((this.parent.style & 0x10000000) == 0x0 && !this.cached && !this.parent.painted) {
            final TVITEM tvItem = new TVITEM();
            tvItem.mask = 17;
            tvItem.hItem = this.handle;
            tvItem.pszText = -1L;
            this.parent.ignoreCustomDraw = true;
            OS.SendMessage(hwnd, 4415, 0L, tvItem);
            this.parent.ignoreCustomDraw = false;
        }
        boolean firstColumn = index == 0;
        int columnCount = 0;
        final long hwndHeader = this.parent.hwndHeader;
        if (hwndHeader != 0L) {
            columnCount = this.parent.columnCount;
            firstColumn = (index == OS.SendMessage(hwndHeader, 4623, 0L, 0L));
        }
        final RECT rect = new RECT();
        if (firstColumn) {
            final boolean full = columnCount == 0 && getText && getImage && fullText && fullImage;
            if (!OS.TreeView_GetItemRect(hwnd, this.handle, rect, !full)) {
                return new RECT();
            }
            if (getImage && !fullImage) {
                if (OS.SendMessage(hwnd, 4360, 0L, 0L) != 0L) {
                    final Point size = this.parent.getImageSize();
                    final RECT rect5;
                    final RECT rect2 = rect5 = rect;
                    rect5.left -= size.x + 3;
                    if (!getText) {
                        rect.right = rect.left + size.x;
                    }
                }
                else if (!getText) {
                    rect.right = rect.left;
                }
            }
            if ((fullText || fullImage || clip) && hwndHeader != 0L) {
                RECT headerRect = new RECT();
                if (columnCount != 0) {
                    if (OS.SendMessage(hwndHeader, 4615, (long)index, headerRect) == 0L) {
                        return new RECT();
                    }
                }
                else {
                    headerRect.right = this.parent.scrollWidth;
                    if (headerRect.right == 0) {
                        headerRect = rect;
                    }
                }
                if (fullText && clip) {
                    rect.right = headerRect.right;
                }
                if (fullImage) {
                    rect.left = headerRect.left;
                }
                if (clip && headerRect.right < rect.right) {
                    rect.right = headerRect.right;
                }
            }
        }
        else {
            if (0 > index || index >= columnCount) {
                return new RECT();
            }
            final RECT headerRect2 = new RECT();
            if (OS.SendMessage(hwndHeader, 4615, (long)index, headerRect2) == 0L) {
                return new RECT();
            }
            if (!OS.TreeView_GetItemRect(hwnd, this.handle, rect, false)) {
                return new RECT();
            }
            rect.left = headerRect2.left;
            if (fullText && getImage && clip) {
                rect.right = headerRect2.right;
            }
            else {
                rect.right = headerRect2.left;
                Image image = null;
                if (index == 0) {
                    image = this.image;
                }
                else if (this.images != null) {
                    image = this.images[index];
                }
                if (image != null) {
                    final Point size2 = this.parent.getImageSize();
                    final RECT rect6;
                    final RECT rect3 = rect6 = rect;
                    rect6.right += size2.x;
                }
                if (getText) {
                    if (fullText && clip) {
                        rect.left = rect.right + 3;
                        rect.right = headerRect2.right;
                    }
                    else {
                        final String string = (index == 0) ? this.text : ((this.strings != null) ? this.strings[index] : null);
                        if (string != null) {
                            final RECT textRect = new RECT();
                            final char[] buffer = string.toCharArray();
                            final int flags = 3104;
                            long hNewDC = hDC;
                            long hFont = 0L;
                            if (hDC == 0L) {
                                hNewDC = OS.GetDC(hwnd);
                                hFont = this.fontHandle(index);
                                if (hFont == -1L) {
                                    hFont = OS.SendMessage(hwnd, 49, 0L, 0L);
                                }
                                hFont = OS.SelectObject(hNewDC, hFont);
                            }
                            OS.DrawText(hNewDC, buffer, buffer.length, textRect, 3104);
                            if (hDC == 0L) {
                                OS.SelectObject(hNewDC, hFont);
                                OS.ReleaseDC(hwnd, hNewDC);
                            }
                            if (getImage) {
                                final RECT rect7;
                                final RECT rect4 = rect7 = rect;
                                rect7.right += textRect.right - textRect.left + 9;
                            }
                            else {
                                rect.left = rect.right + 3;
                                rect.right = rect.left + (textRect.right - textRect.left) + 3;
                            }
                        }
                    }
                }
                if (clip && headerRect2.right < rect.right) {
                    rect.right = headerRect2.right;
                }
            }
        }
        final int gridWidth = (this.parent.linesVisible && columnCount != 0) ? 1 : 0;
        if (getText || !getImage) {
            rect.right = Math.max(rect.left, rect.right - gridWidth);
        }
        rect.bottom = Math.max(rect.top, rect.bottom - gridWidth);
        return rect;
    }
    
    public boolean getChecked() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        if ((this.parent.style & 0x20) == 0x0) {
            return false;
        }
        final long hwnd = this.parent.handle;
        final TVITEM tvItem = new TVITEM();
        tvItem.mask = 24;
        tvItem.stateMask = 61440;
        tvItem.hItem = this.handle;
        final long result = OS.SendMessage(hwnd, 4414, 0L, tvItem);
        return result != 0L && (tvItem.state >> 12 & 0x1) == 0x0;
    }
    
    public boolean getExpanded() {
        this.checkWidget();
        final long hwnd = this.parent.handle;
        final int state = (int)OS.SendMessage(hwnd, 4391, this.handle, 32L);
        return (state & 0x20) != 0x0;
    }
    
    public Font getFont() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        return (this.font != null) ? this.font : this.parent.getFont();
    }
    
    public Font getFont(final int index) {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final int count = Math.max(1, this.parent.getColumnCount());
        if (0 > index || index > count - 1) {
            return this.getFont();
        }
        if (this.cellFont == null || this.cellFont[index] == null) {
            return this.getFont();
        }
        return this.cellFont[index];
    }
    
    public Color getForeground() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        if (this.foreground == -1) {
            return this.parent.getForeground();
        }
        return Color.win32_new((Device)this.display, this.foreground);
    }
    
    public Color getForeground(final int index) {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final int count = Math.max(1, this.parent.getColumnCount());
        if (0 > index || index > count - 1) {
            return this.getForeground();
        }
        final int pixel = (this.cellForeground != null) ? this.cellForeground[index] : -1;
        return (pixel == -1) ? this.getForeground() : Color.win32_new((Device)this.display, pixel);
    }
    
    public boolean getGrayed() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        if ((this.parent.style & 0x20) == 0x0) {
            return false;
        }
        final long hwnd = this.parent.handle;
        final TVITEM tvItem = new TVITEM();
        tvItem.mask = 24;
        tvItem.stateMask = 61440;
        tvItem.hItem = this.handle;
        final long result = OS.SendMessage(hwnd, 4414, 0L, tvItem);
        return result != 0L && tvItem.state >> 12 > 2;
    }
    
    public TreeItem getItem(final int index) {
        this.checkWidget();
        if (index < 0) {
            this.error(6);
        }
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final long hwnd = this.parent.handle;
        final long hFirstItem = OS.SendMessage(hwnd, 4362, 4L, this.handle);
        if (hFirstItem == 0L) {
            this.error(6);
        }
        final long hItem = this.parent.findItem(hFirstItem, index);
        if (hItem == 0L) {
            this.error(6);
        }
        return this.parent._getItem(hItem);
    }
    
    public int getItemCount() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final long hwnd = this.parent.handle;
        final long hItem = OS.SendMessage(hwnd, 4362, 4L, this.handle);
        if (hItem == 0L) {
            return 0;
        }
        return this.parent.getItemCount(hItem);
    }
    
    public TreeItem[] getItems() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final long hwnd = this.parent.handle;
        final long hItem = OS.SendMessage(hwnd, 4362, 4L, this.handle);
        if (hItem == 0L) {
            return new TreeItem[0];
        }
        return this.parent.getItems(hItem);
    }
    
    public Image getImage() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        return super.getImage();
    }
    
    public Image getImage(final int index) {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        if (index == 0) {
            return this.getImage();
        }
        if (this.images != null && 0 <= index && index < this.images.length) {
            return this.images[index];
        }
        return null;
    }
    
    public Rectangle getImageBounds(final int index) {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getImageBoundsInPixels(index));
    }
    
    Rectangle getImageBoundsInPixels(final int index) {
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final RECT rect = this.getBounds(index, false, true, false);
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        return new Rectangle(rect.left, rect.top, width, height);
    }
    
    public Tree getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public TreeItem getParentItem() {
        this.checkWidget();
        final long hwnd = this.parent.handle;
        final long hItem = OS.SendMessage(hwnd, 4362, 3L, this.handle);
        return (hItem != 0L) ? this.parent._getItem(hItem) : null;
    }
    
    public String getText() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        return super.getText();
    }
    
    public String getText(final int index) {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        if (index == 0) {
            return this.getText();
        }
        if (this.strings != null && 0 <= index && index < this.strings.length) {
            final String string = this.strings[index];
            return (string != null) ? string : "";
        }
        return "";
    }
    
    public Rectangle getTextBounds(final int index) {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getTextBoundsInPixels(index));
    }
    
    Rectangle getTextBoundsInPixels(final int index) {
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        final RECT rect = this.getBounds(index, true, false, true);
        if (index == 0) {
            final RECT rect3;
            final RECT rect2 = rect3 = rect;
            rect3.left += 2;
        }
        rect.left = Math.min(rect.left, rect.right);
        rect.right = rect.right - 3 + 1;
        final int width = Math.max(0, rect.right - rect.left);
        final int height = Math.max(0, rect.bottom - rect.top);
        return new Rectangle(rect.left, rect.top, width, height);
    }
    
    public int indexOf(final TreeItem item) {
        this.checkWidget();
        if (item == null) {
            this.error(4);
        }
        if (item.isDisposed()) {
            this.error(5);
        }
        final long hwnd = this.parent.handle;
        final long hItem = OS.SendMessage(hwnd, 4362, 4L, this.handle);
        return (hItem == 0L) ? -1 : this.parent.findIndex(hItem, item.handle);
    }
    
    void redraw() {
        if (this.parent.currentItem == this || !this.parent.getDrawing()) {
            return;
        }
        final long hwnd = this.parent.handle;
        if (!OS.IsWindowVisible(hwnd)) {
            return;
        }
        boolean full = (this.parent.style & 0x10010000) != 0x0;
        if (!full) {
            full = (this.parent.columnCount != 0);
            if (!full && (this.parent.hooks(40) || this.parent.hooks(42))) {
                full = true;
            }
        }
        final RECT rect = new RECT();
        if (OS.TreeView_GetItemRect(hwnd, this.handle, rect, !full)) {
            OS.InvalidateRect(hwnd, rect, true);
        }
    }
    
    void redraw(final int column, final boolean drawText, final boolean drawImage) {
        if (this.parent.currentItem == this || !this.parent.getDrawing()) {
            return;
        }
        final long hwnd = this.parent.handle;
        if (!OS.IsWindowVisible(hwnd)) {
            return;
        }
        final boolean fullImage = column == 0 && drawText && drawImage;
        final RECT rect = this.getBounds(column, drawText, drawImage, true, fullImage, true, 0L);
        OS.InvalidateRect(hwnd, rect, true);
    }
    
    void releaseChildren(final boolean destroy) {
        if (destroy) {
            final TVITEM tvItem = new TVITEM();
            tvItem.mask = 20;
            this.parent.releaseItems(this.handle, tvItem);
        }
        super.releaseChildren(destroy);
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.handle = 0L;
        this.parent = null;
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.strings = null;
        this.images = null;
        final int[] array = null;
        this.cellForeground = array;
        this.cellBackground = array;
        this.cellFont = null;
    }
    
    public void removeAll() {
        this.checkWidget();
        final long hwnd = this.parent.handle;
        final TVITEM tvItem = new TVITEM();
        tvItem.mask = 20;
        tvItem.hItem = OS.SendMessage(hwnd, 4362, 4L, this.handle);
        final boolean disableRedraw = this.parent.itemCount > 30;
        if (disableRedraw) {
            this.parent.setRedraw(false);
        }
        try {
            while (tvItem.hItem != 0L) {
                OS.SendMessage(hwnd, 4414, 0L, tvItem);
                final TreeItem item = (tvItem.lParam != -1L) ? this.parent.items[(int)tvItem.lParam] : null;
                if (item != null && !item.isDisposed()) {
                    item.dispose();
                }
                else {
                    this.parent.releaseItem(tvItem.hItem, tvItem, false);
                    this.parent.destroyItem((TreeItem)null, tvItem.hItem);
                }
                tvItem.hItem = OS.SendMessage(hwnd, 4362, 4L, this.handle);
            }
        }
        finally {
            if (disableRedraw) {
                this.parent.setRedraw(true);
            }
        }
    }
    
    public void setBackground(final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            this.error(5);
        }
        int pixel = -1;
        if (color != null) {
            this.parent.customDraw = true;
            pixel = color.handle;
        }
        if (this.background == pixel) {
            return;
        }
        this.background = pixel;
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        this.redraw();
    }
    
    public void setBackground(final int index, final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            this.error(5);
        }
        final int count = Math.max(1, this.parent.getColumnCount());
        if (0 > index || index > count - 1) {
            return;
        }
        int pixel = -1;
        if (color != null) {
            this.parent.customDraw = true;
            pixel = color.handle;
        }
        if (this.cellBackground == null) {
            this.cellBackground = new int[count];
            for (int i = 0; i < count; ++i) {
                this.cellBackground[i] = -1;
            }
        }
        if (this.cellBackground[index] == pixel) {
            return;
        }
        this.cellBackground[index] = pixel;
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        this.redraw(index, true, true);
    }
    
    public void setChecked(final boolean checked) {
        this.checkWidget();
        if ((this.parent.style & 0x20) == 0x0) {
            return;
        }
        final long hwnd = this.parent.handle;
        final TVITEM tvItem = new TVITEM();
        tvItem.mask = 24;
        tvItem.stateMask = 61440;
        tvItem.hItem = this.handle;
        OS.SendMessage(hwnd, 4414, 0L, tvItem);
        int state = tvItem.state >> 12;
        if (checked) {
            if ((state & 0x1) != 0x0) {
                ++state;
            }
        }
        else if ((state & 0x1) == 0x0) {
            --state;
        }
        state <<= 12;
        if (tvItem.state == state) {
            return;
        }
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        tvItem.state = state;
        OS.SendMessage(hwnd, 4415, 0L, tvItem);
        if ((this.parent.style & 0x10000000) != 0x0 && this.parent.currentItem == this && OS.IsWindowVisible(hwnd)) {
            final RECT rect = new RECT();
            if (OS.TreeView_GetItemRect(hwnd, this.handle, rect, false)) {
                OS.InvalidateRect(hwnd, rect, true);
            }
        }
    }
    
    public void setExpanded(final boolean expanded) {
        this.checkWidget();
        final long hwnd = this.parent.handle;
        if (OS.SendMessage(hwnd, 4362, 4L, this.handle) == 0L) {
            return;
        }
        final int state = (int)OS.SendMessage(hwnd, 4391, this.handle, 32L);
        if ((state & 0x20) != 0x0 == expanded) {
            return;
        }
        RECT oldRect = null;
        RECT[] rects = null;
        SCROLLINFO oldInfo = null;
        int count = 0;
        long hBottomItem = 0L;
        boolean redraw = false;
        final boolean noScroll = true;
        long hTopItem = OS.SendMessage(hwnd, 4362, 5L, 0L);
        if (hTopItem != 0L) {
            oldInfo = new SCROLLINFO();
            oldInfo.cbSize = SCROLLINFO.sizeof;
            oldInfo.fMask = 23;
            if (!OS.GetScrollInfo(hwnd, 0, oldInfo)) {
                oldInfo = null;
            }
            if (this.parent.getDrawing() && OS.IsWindowVisible(hwnd)) {
                final boolean noAnimate = true;
                count = (int)OS.SendMessage(hwnd, 4368, 0L, 0L);
                rects = new RECT[count + 1];
                long hItem;
                int index;
                RECT rect;
                for (hItem = hTopItem, index = 0; hItem != 0L && index < count; hItem = OS.SendMessage(hwnd, 4362, 6L, hItem)) {
                    rect = new RECT();
                    if (OS.TreeView_GetItemRect(hwnd, hItem, rect, true)) {
                        rects[index++] = rect;
                    }
                }
                redraw = true;
                count = index;
                hBottomItem = hItem;
                oldRect = new RECT();
                OS.GetClientRect(hwnd, oldRect);
                final long topHandle = this.parent.topHandle();
                OS.UpdateWindow(topHandle);
                OS.DefWindowProc(topHandle, 11, 0L, 0L);
                if (hwnd != topHandle) {
                    OS.UpdateWindow(hwnd);
                    OS.DefWindowProc(hwnd, 11, 0L, 0L);
                }
            }
        }
        final long hOldItem = OS.SendMessage(hwnd, 4362, 9L, 0L);
        this.parent.ignoreExpand = true;
        OS.SendMessage(hwnd, 4354, expanded ? 2L : 1L, this.handle);
        this.parent.ignoreExpand = false;
        if (hTopItem != 0L) {
            boolean collapsed = false;
            if (!expanded) {
                for (RECT rect2 = new RECT(); hTopItem != 0L && !OS.TreeView_GetItemRect(hwnd, hTopItem, rect2, false); hTopItem = OS.SendMessage(hwnd, 4362, 3L, hTopItem), collapsed = true) {}
            }
            boolean scrolled = true;
            if (hTopItem != 0L) {
                OS.SendMessage(hwnd, 4363, 5L, hTopItem);
                scrolled = (hTopItem != OS.SendMessage(hwnd, 4362, 5L, 0L));
            }
            if (!collapsed && !scrolled && oldInfo != null) {
                final SCROLLINFO newInfo = new SCROLLINFO();
                newInfo.cbSize = SCROLLINFO.sizeof;
                newInfo.fMask = 23;
                if (OS.GetScrollInfo(hwnd, 0, newInfo) && oldInfo.nPos != newInfo.nPos) {
                    final long lParam = OS.MAKELPARAM(4, oldInfo.nPos);
                    OS.SendMessage(hwnd, 276, lParam, 0L);
                }
            }
            if (redraw) {
                boolean fixScroll = false;
                if (!collapsed && !scrolled) {
                    final RECT newRect = new RECT();
                    OS.GetClientRect(hwnd, newRect);
                    if (OS.EqualRect(oldRect, newRect)) {
                        long hItem2;
                        int index2;
                        RECT rect3;
                        for (hItem2 = hTopItem, index2 = 0; hItem2 != 0L && index2 < count; hItem2 = OS.SendMessage(hwnd, 4362, 6L, hItem2), ++index2) {
                            rect3 = new RECT();
                            if (OS.TreeView_GetItemRect(hwnd, hItem2, rect3, true) && !OS.EqualRect(rect3, rects[index2])) {
                                break;
                            }
                        }
                        fixScroll = (index2 == count && hItem2 == hBottomItem);
                    }
                }
                final long topHandle2 = this.parent.topHandle();
                OS.DefWindowProc(topHandle2, 11, 1L, 0L);
                if (hwnd != topHandle2) {
                    OS.DefWindowProc(hwnd, 11, 1L, 0L);
                }
                if (fixScroll) {
                    this.parent.updateScrollBar();
                    final SCROLLINFO info = new SCROLLINFO();
                    info.cbSize = SCROLLINFO.sizeof;
                    info.fMask = 23;
                    if (OS.GetScrollInfo(hwnd, 1, info)) {
                        OS.SetScrollInfo(hwnd, 1, info, true);
                    }
                    if (this.handle == hBottomItem) {
                        final RECT rect4 = new RECT();
                        if (OS.TreeView_GetItemRect(hwnd, hBottomItem, rect4, false)) {
                            OS.InvalidateRect(hwnd, rect4, true);
                        }
                    }
                }
                else {
                    final int flags = 1157;
                    OS.RedrawWindow(topHandle2, (RECT)null, 0L, 1157);
                }
            }
        }
        final long hNewItem = OS.SendMessage(hwnd, 4362, 9L, 0L);
        if (hNewItem != hOldItem) {
            final Event event = new Event();
            if (hNewItem != 0L) {
                event.item = (Widget)this.parent._getItem(hNewItem);
                this.parent.hAnchor = hNewItem;
            }
            this.parent.sendSelectionEvent(13, event, true);
        }
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        if (font != null && font.isDisposed()) {
            this.error(5);
        }
        final Font oldFont = this.font;
        if (oldFont == font) {
            return;
        }
        this.font = font;
        if (oldFont != null && oldFont.equals((Object)font)) {
            return;
        }
        if (font != null) {
            this.parent.customDraw = true;
        }
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        if ((this.parent.style & 0x10000000) == 0x0 && !this.cached && !this.parent.painted) {
            return;
        }
        final long hwnd = this.parent.handle;
        final TVITEM tvItem = new TVITEM();
        tvItem.mask = 17;
        tvItem.hItem = this.handle;
        tvItem.pszText = -1L;
        OS.SendMessage(hwnd, 4415, 0L, tvItem);
    }
    
    public void setFont(final int index, final Font font) {
        this.checkWidget();
        if (font != null && font.isDisposed()) {
            this.error(5);
        }
        final int count = Math.max(1, this.parent.getColumnCount());
        if (0 > index || index > count - 1) {
            return;
        }
        if (this.cellFont == null) {
            if (font == null) {
                return;
            }
            this.cellFont = new Font[count];
        }
        final Font oldFont = this.cellFont[index];
        if (oldFont == font) {
            return;
        }
        this.cellFont[index] = font;
        if (oldFont != null && oldFont.equals((Object)font)) {
            return;
        }
        if (font != null) {
            this.parent.customDraw = true;
        }
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        if (index == 0) {
            if ((this.parent.style & 0x10000000) == 0x0 && !this.cached && !this.parent.painted) {
                return;
            }
            final long hwnd = this.parent.handle;
            final TVITEM tvItem = new TVITEM();
            tvItem.mask = 17;
            tvItem.hItem = this.handle;
            tvItem.pszText = -1L;
            OS.SendMessage(hwnd, 4415, 0L, tvItem);
        }
        else {
            this.redraw(index, true, false);
        }
    }
    
    public void setForeground(final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            this.error(5);
        }
        int pixel = -1;
        if (color != null) {
            this.parent.customDraw = true;
            pixel = color.handle;
        }
        if (this.foreground == pixel) {
            return;
        }
        this.foreground = pixel;
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        this.redraw();
    }
    
    public void setForeground(final int index, final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            this.error(5);
        }
        final int count = Math.max(1, this.parent.getColumnCount());
        if (0 > index || index > count - 1) {
            return;
        }
        int pixel = -1;
        if (color != null) {
            this.parent.customDraw = true;
            pixel = color.handle;
        }
        if (this.cellForeground == null) {
            this.cellForeground = new int[count];
            for (int i = 0; i < count; ++i) {
                this.cellForeground[i] = -1;
            }
        }
        if (this.cellForeground[index] == pixel) {
            return;
        }
        this.cellForeground[index] = pixel;
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        this.redraw(index, true, false);
    }
    
    public void setGrayed(final boolean grayed) {
        this.checkWidget();
        if ((this.parent.style & 0x20) == 0x0) {
            return;
        }
        final long hwnd = this.parent.handle;
        final TVITEM tvItem = new TVITEM();
        tvItem.mask = 24;
        tvItem.stateMask = 61440;
        tvItem.hItem = this.handle;
        OS.SendMessage(hwnd, 4414, 0L, tvItem);
        int state = tvItem.state >> 12;
        if (grayed) {
            if (state <= 2) {
                state += 2;
            }
        }
        else if (state > 2) {
            state -= 2;
        }
        state <<= 12;
        if (tvItem.state == state) {
            return;
        }
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        tvItem.state = state;
        OS.SendMessage(hwnd, 4415, 0L, tvItem);
        if ((this.parent.style & 0x10000000) != 0x0 && this.parent.currentItem == this && OS.IsWindowVisible(hwnd)) {
            final RECT rect = new RECT();
            if (OS.TreeView_GetItemRect(hwnd, this.handle, rect, false)) {
                OS.InvalidateRect(hwnd, rect, true);
            }
        }
    }
    
    public void setImage(final Image[] images) {
        this.checkWidget();
        if (images == null) {
            this.error(4);
        }
        for (int i = 0; i < images.length; ++i) {
            this.setImage(i, images[i]);
        }
    }
    
    public void setImage(final int index, final Image image) {
        this.checkWidget();
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        Image oldImage = null;
        if (index == 0) {
            if (image != null && image.type == 1 && image.equals((Object)this.image)) {
                return;
            }
            oldImage = this.image;
            super.setImage(image);
        }
        final int count = Math.max(1, this.parent.getColumnCount());
        if (0 > index || index > count - 1) {
            return;
        }
        if (this.images == null && index != 0) {
            (this.images = new Image[count])[0] = image;
        }
        if (this.images != null) {
            if (image != null && image.type == 1 && image.equals((Object)this.images[index])) {
                return;
            }
            oldImage = this.images[index];
            this.images[index] = image;
        }
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        this.parent.imageIndex(image, index);
        if (index == 0) {
            if ((this.parent.style & 0x10000000) == 0x0 && !this.cached && !this.parent.painted) {
                return;
            }
            final long hwnd = this.parent.handle;
            final TVITEM tvItem = new TVITEM();
            tvItem.mask = 50;
            tvItem.hItem = this.handle;
            final TVITEM tvitem = tvItem;
            final TVITEM tvitem2 = tvItem;
            final int n = -1;
            tvitem2.iSelectedImage = -1;
            tvitem.iImage = -1;
            final TVITEM tvitem4;
            final TVITEM tvitem3 = tvitem4 = tvItem;
            tvitem4.mask |= 0x1;
            tvItem.pszText = -1L;
            OS.SendMessage(hwnd, 4415, 0L, tvItem);
        }
        else {
            final boolean drawText = (image == null && oldImage != null) || (image != null && oldImage == null);
            this.redraw(index, drawText, true);
        }
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        this.setImage(0, image);
    }
    
    public void setItemCount(int count) {
        this.checkWidget();
        count = Math.max(0, count);
        this.parent.setItemCount(count, this.handle);
    }
    
    public void setText(final String[] strings) {
        this.checkWidget();
        if (strings == null) {
            this.error(4);
        }
        for (int i = 0; i < strings.length; ++i) {
            final String string = strings[i];
            if (string != null) {
                this.setText(i, string);
            }
        }
    }
    
    public void setText(final int index, final String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        if (index == 0) {
            if (string.equals(this.text)) {
                return;
            }
            super.setText(string);
        }
        final int count = Math.max(1, this.parent.getColumnCount());
        if (0 > index || index > count - 1) {
            return;
        }
        if (this.strings == null && index != 0) {
            (this.strings = new String[count])[0] = this.text;
        }
        if (this.strings != null) {
            if (string.equals(this.strings[index])) {
                return;
            }
            this.strings[index] = string;
        }
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        if (index == 0) {
            if ((this.parent.style & 0x10000000) == 0x0 && !this.cached && !this.parent.painted) {
                return;
            }
            final long hwnd = this.parent.handle;
            final TVITEM tvItem = new TVITEM();
            tvItem.mask = 17;
            tvItem.hItem = this.handle;
            tvItem.pszText = -1L;
            OS.SendMessage(hwnd, 4415, 0L, tvItem);
        }
        else {
            this.redraw(index, true, false);
        }
    }
    
    public void setText(final String string) {
        this.checkWidget();
        this.setText(0, string);
    }
    
    void sort() {
        this.checkWidget();
        if ((this.parent.style & 0x10000000) != 0x0) {
            return;
        }
        this.parent.sort(this.handle, false);
    }
    
    String getNameText() {
        if ((this.parent.style & 0x10000000) != 0x0 && !this.cached) {
            return "*virtual*";
        }
        return super.getNameText();
    }
}
