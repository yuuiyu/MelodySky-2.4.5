//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;

public class TableItem extends Item
{
    Table parent;
    String[] strings;
    Image[] images;
    Font font;
    Font[] cellFont;
    boolean checked;
    boolean grayed;
    boolean cached;
    int imageIndent;
    int background;
    int foreground;
    int[] cellBackground;
    int[] cellForeground;
    
    public TableItem(final Table parent, final int style) {
        this(parent, style, checkNull(parent).getItemCount(), true);
    }
    
    public TableItem(final Table parent, final int style, final int index) {
        this(parent, style, index, true);
    }
    
    TableItem(final Table parent, final int style, final int index, final boolean create) {
        super((Widget)parent, style);
        this.background = -1;
        this.foreground = -1;
        this.parent = parent;
        if (create) {
            parent.createItem(this, index);
        }
    }
    
    static Table checkNull(final Table control) {
        if (control == null) {
            SWT.error(4);
        }
        return control;
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
        this.imageIndent = 0;
        final boolean b = false;
        this.grayed = false;
        this.checked = false;
        this.font = null;
        final int n = -1;
        this.foreground = -1;
        this.background = -1;
        this.cellFont = null;
        final int[] array = null;
        this.cellForeground = array;
        this.cellBackground = array;
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = false;
        }
    }
    
    void destroyWidget() {
        this.parent.destroyItem(this);
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
        final int itemIndex = this.parent.indexOf(this);
        if (itemIndex == -1) {
            return new Rectangle(0, 0, 0, 0);
        }
        final RECT rect = this.getBounds(itemIndex, 0, true, false, false);
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
        final int itemIndex = this.parent.indexOf(this);
        if (itemIndex == -1) {
            return new Rectangle(0, 0, 0, 0);
        }
        final RECT rect = this.getBounds(itemIndex, index, true, true, true);
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        return new Rectangle(rect.left, rect.top, width, height);
    }
    
    RECT getBounds(final int row, final int column, final boolean getText, final boolean getImage, final boolean fullText) {
        return this.getBounds(row, column, getText, getImage, fullText, false, 0L);
    }
    
    RECT getBounds(final int row, final int column, final boolean getText, final boolean getImage, final boolean fullText, final boolean fullImage, final long hDC) {
        if (!getText && !getImage) {
            return new RECT();
        }
        final int columnCount = this.parent.getColumnCount();
        if (0 > column || column >= Math.max(1, columnCount)) {
            return new RECT();
        }
        if (this.parent.fixScrollWidth) {
            this.parent.setScrollWidth((TableItem)null, true);
        }
        final RECT rect = new RECT();
        final long hwnd = this.parent.handle;
        final int bits = (int)OS.SendMessage(hwnd, 4151, 0L, 0L);
        if (column == 0 && (bits & 0x20) == 0x0) {
            if (this.parent.explorerTheme) {
                rect.left = 1;
                this.parent.ignoreCustomDraw = true;
                final long code = OS.SendMessage(hwnd, 4110, (long)row, rect);
                this.parent.ignoreCustomDraw = false;
                if (code == 0L) {
                    return new RECT();
                }
                if (getText) {
                    int width = 0;
                    long hFont = this.fontHandle(column);
                    if (hFont == -1L && hDC == 0L) {
                        final TCHAR buffer = new TCHAR(this.parent.getCodePage(), this.text, true);
                        width = (int)OS.SendMessage(hwnd, 4183, 0L, buffer);
                    }
                    else {
                        final char[] buffer2 = this.text.toCharArray();
                        final long textDC = (hDC != 0L) ? hDC : OS.GetDC(hwnd);
                        long oldFont = -1L;
                        if (hDC == 0L) {
                            if (hFont == -1L) {
                                hFont = OS.SendMessage(hwnd, 49, 0L, 0L);
                            }
                            oldFont = OS.SelectObject(textDC, hFont);
                        }
                        final RECT textRect = new RECT();
                        final int flags = 3104;
                        OS.DrawText(textDC, buffer2, buffer2.length, textRect, 3104);
                        width = textRect.right - textRect.left;
                        if (hDC == 0L) {
                            if (oldFont != -1L) {
                                OS.SelectObject(textDC, oldFont);
                            }
                            OS.ReleaseDC(hwnd, textDC);
                        }
                    }
                    if (!getImage) {
                        rect.left = rect.right;
                    }
                    final RECT rect7;
                    final RECT rect2 = rect7 = rect;
                    rect7.right += width + 8;
                }
            }
            else if (getText) {
                rect.left = 3;
                this.parent.ignoreCustomDraw = true;
                long code = OS.SendMessage(hwnd, 4110, (long)row, rect);
                this.parent.ignoreCustomDraw = false;
                if (code == 0L) {
                    return new RECT();
                }
                if (!getImage) {
                    final RECT iconRect = new RECT();
                    iconRect.left = 1;
                    this.parent.ignoreCustomDraw = true;
                    code = OS.SendMessage(hwnd, 4110, (long)row, iconRect);
                    this.parent.ignoreCustomDraw = false;
                    if (code != 0L) {
                        rect.left = iconRect.right;
                    }
                }
            }
            else {
                rect.left = 1;
                this.parent.ignoreCustomDraw = true;
                final long code = OS.SendMessage(hwnd, 4110, (long)row, rect);
                this.parent.ignoreCustomDraw = false;
                if (code == 0L) {
                    return new RECT();
                }
            }
            if (fullText || fullImage) {
                final RECT headerRect = new RECT();
                final long hwndHeader = OS.SendMessage(hwnd, 4127, 0L, 0L);
                OS.SendMessage(hwndHeader, 4615, 0L, headerRect);
                OS.MapWindowPoints(hwndHeader, hwnd, headerRect, 2);
                if (getText && fullText) {
                    rect.right = headerRect.right;
                }
                if (getImage && fullImage) {
                    rect.left = headerRect.left;
                }
            }
        }
        else {
            final boolean hasImage = (column == 0 && this.image != null) || (this.images != null && this.images[column] != null);
            rect.top = column;
            if (fullText || fullImage || hDC == 0L) {
                rect.left = (getText ? 2 : 1);
                this.parent.ignoreCustomDraw = true;
                long code2 = OS.SendMessage(hwnd, 4152, (long)row, rect);
                this.parent.ignoreCustomDraw = false;
                if (code2 == 0L) {
                    return new RECT();
                }
                if (column == 0 && getText && getImage) {
                    final RECT iconRect2 = new RECT();
                    iconRect2.left = 1;
                    this.parent.ignoreCustomDraw = true;
                    code2 = OS.SendMessage(hwnd, 4152, (long)row, iconRect2);
                    this.parent.ignoreCustomDraw = false;
                    if (code2 != 0L) {
                        rect.left = iconRect2.left;
                    }
                }
                if (hasImage) {
                    if (column != 0 && getText && !getImage) {
                        final RECT iconRect2 = new RECT();
                        iconRect2.top = column;
                        iconRect2.left = 1;
                        if (OS.SendMessage(hwnd, 4152, (long)row, iconRect2) != 0L) {
                            rect.left = iconRect2.right + 2;
                        }
                    }
                }
                else if (getImage && !getText) {
                    rect.right = rect.left;
                }
                if (column == 0 && fullImage) {
                    final RECT headerRect2 = new RECT();
                    final long hwndHeader2 = OS.SendMessage(hwnd, 4127, 0L, 0L);
                    OS.SendMessage(hwndHeader2, 4615, 0L, headerRect2);
                    OS.MapWindowPoints(hwndHeader2, hwnd, headerRect2, 2);
                    rect.left = headerRect2.left;
                }
            }
            else {
                rect.left = 1;
                this.parent.ignoreCustomDraw = true;
                final long code2 = OS.SendMessage(hwnd, 4152, (long)row, rect);
                this.parent.ignoreCustomDraw = false;
                if (code2 == 0L) {
                    return new RECT();
                }
                if (!hasImage) {
                    rect.right = rect.left;
                }
                if (getText) {
                    final String string = (column == 0) ? this.text : ((this.strings != null) ? this.strings[column] : null);
                    if (string != null) {
                        final RECT textRect2 = new RECT();
                        final char[] buffer2 = string.toCharArray();
                        final int flags2 = 3104;
                        OS.DrawText(hDC, buffer2, buffer2.length, textRect2, 3104);
                        final RECT rect8;
                        final RECT rect3 = rect8 = rect;
                        rect8.right += textRect2.right - textRect2.left + 12 + 2;
                    }
                }
            }
        }
        final int gridWidth = this.parent.getLinesVisible() ? 1 : 0;
        final RECT rect9;
        final RECT rect4 = rect9 = rect;
        rect9.top -= gridWidth;
        if (column != 0) {
            final RECT rect10;
            final RECT rect5 = rect10 = rect;
            rect10.left += gridWidth;
        }
        rect.right = Math.max(rect.right, rect.left);
        final RECT rect11;
        final RECT rect6 = rect11 = rect;
        rect11.top += gridWidth;
        rect.bottom = Math.max(rect.bottom - gridWidth, rect.top);
        return rect;
    }
    
    public boolean getChecked() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        return (this.parent.style & 0x20) != 0x0 && this.checked;
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
        return (this.parent.style & 0x20) != 0x0 && this.grayed;
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
        final int itemIndex = this.parent.indexOf(this);
        if (itemIndex == -1) {
            return new Rectangle(0, 0, 0, 0);
        }
        final RECT rect = this.getBounds(itemIndex, index, false, true, false);
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        return new Rectangle(rect.left, rect.top, width, height);
    }
    
    public int getImageIndent() {
        this.checkWidget();
        if (!this.parent.checkData(this, true)) {
            this.error(24);
        }
        return this.imageIndent;
    }
    
    String getNameText() {
        if ((this.parent.style & 0x10000000) != 0x0 && !this.cached) {
            return "*virtual*";
        }
        return super.getNameText();
    }
    
    public Table getParent() {
        this.checkWidget();
        return this.parent;
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
        final int itemIndex = this.parent.indexOf(this);
        if (itemIndex == -1) {
            return new Rectangle(0, 0, 0, 0);
        }
        final RECT rect;
        final RECT rect3;
        final RECT bounds = rect3 = (rect = this.getBounds(itemIndex, index, (boolean)(1 != 0), (boolean)(0 != 0), (boolean)(1 != 0)));
        rect3.left += 2;
        if (index != 0) {
            final RECT rect4;
            final RECT rect2 = rect4 = rect;
            rect4.left += 4;
        }
        rect.left = Math.min(rect.left, rect.right);
        final RECT rect5 = rect;
        rect5.right -= 4;
        final int width = Math.max(0, rect.right - rect.left);
        final int height = Math.max(0, rect.bottom - rect.top);
        return new Rectangle(rect.left, rect.top, width, height);
    }
    
    void redraw() {
        if (this.parent.currentItem == this || !this.parent.getDrawing()) {
            return;
        }
        final long hwnd = this.parent.handle;
        if (!OS.IsWindowVisible(hwnd)) {
            return;
        }
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        OS.SendMessage(hwnd, 4117, (long)index, (long)index);
    }
    
    void redraw(final int column, final boolean drawText, final boolean drawImage) {
        if (this.parent.currentItem == this || !this.parent.getDrawing()) {
            return;
        }
        final long hwnd = this.parent.handle;
        if (!OS.IsWindowVisible(hwnd)) {
            return;
        }
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        final RECT rect = this.getBounds(index, column, drawText, drawImage, true);
        OS.InvalidateRect(hwnd, rect, true);
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.strings = null;
        this.images = null;
        this.cellFont = null;
        final int[] array = null;
        this.cellForeground = array;
        this.cellBackground = array;
    }
    
    public void setBackground(final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            this.error(5);
        }
        int pixel = -1;
        if (color != null) {
            this.parent.setCustomDraw(true);
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
            this.parent.setCustomDraw(true);
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
        if (this.checked == checked) {
            return;
        }
        this.setChecked(checked, false);
    }
    
    void setChecked(final boolean checked, final boolean notify) {
        this.checked = checked;
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        if (notify) {
            final Event event = new Event();
            event.item = (Widget)this;
            event.detail = 32;
            this.parent.sendSelectionEvent(13, event, false);
        }
        this.redraw();
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
            this.parent.setCustomDraw(true);
        }
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        if ((this.parent.style & 0x10000000) == 0x0 && this.cached) {
            final int itemIndex = this.parent.indexOf(this);
            if (itemIndex != -1) {
                final long hwnd = this.parent.handle;
                final LVITEM lvItem = new LVITEM();
                lvItem.mask = 1;
                lvItem.iItem = itemIndex;
                lvItem.pszText = -1L;
                OS.SendMessage(hwnd, 4172, 0L, lvItem);
                this.cached = false;
            }
        }
        this.parent.setScrollWidth(this, false);
        this.redraw();
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
            this.parent.setCustomDraw(true);
        }
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        if (index == 0) {
            if ((this.parent.style & 0x10000000) == 0x0 && this.cached) {
                final int itemIndex = this.parent.indexOf(this);
                if (itemIndex != -1) {
                    final long hwnd = this.parent.handle;
                    final LVITEM lvItem = new LVITEM();
                    lvItem.mask = 1;
                    lvItem.iItem = itemIndex;
                    lvItem.pszText = -1L;
                    OS.SendMessage(hwnd, 4172, 0L, lvItem);
                    this.cached = false;
                }
            }
            this.parent.setScrollWidth(this, false);
        }
        this.redraw(index, true, false);
    }
    
    public void setForeground(final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            this.error(5);
        }
        int pixel = -1;
        if (color != null) {
            this.parent.setCustomDraw(true);
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
            this.parent.setCustomDraw(true);
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
        if (this.grayed == grayed) {
            return;
        }
        this.grayed = grayed;
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        this.redraw();
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
            this.parent.setScrollWidth(this, false);
        }
        final boolean drawText = (image == null && oldImage != null) || (image != null && oldImage == null);
        this.redraw(index, drawText, true);
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        this.setImage(0, image);
    }
    
    @Deprecated
    public void setImageIndent(final int indent) {
        this.checkWidget();
        if (indent < 0) {
            return;
        }
        if (this.imageIndent == indent) {
            return;
        }
        this.imageIndent = indent;
        if ((this.parent.style & 0x10000000) != 0x0) {
            this.cached = true;
        }
        else {
            final int index = this.parent.indexOf(this);
            if (index != -1) {
                final long hwnd = this.parent.handle;
                final LVITEM lvItem = new LVITEM();
                lvItem.mask = 16;
                lvItem.iItem = index;
                lvItem.iIndent = indent;
                OS.SendMessage(hwnd, 4172, 0L, lvItem);
            }
        }
        this.parent.setScrollWidth(this, false);
        this.redraw();
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
            if ((this.parent.style & 0x10000000) == 0x0 && this.cached) {
                final int itemIndex = this.parent.indexOf(this);
                if (itemIndex != -1) {
                    final long hwnd = this.parent.handle;
                    final LVITEM lvItem = new LVITEM();
                    lvItem.mask = 1;
                    lvItem.iItem = itemIndex;
                    lvItem.pszText = -1L;
                    OS.SendMessage(hwnd, 4172, 0L, lvItem);
                    this.cached = false;
                }
            }
            this.parent.setScrollWidth(this, false);
        }
        this.redraw(index, true, false);
    }
    
    public void setText(final String string) {
        this.checkWidget();
        this.setText(0, string);
    }
}
