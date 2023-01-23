//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.graphics.*;

public class TabItem extends Item
{
    TabFolder parent;
    Control control;
    String toolTipText;
    
    public TabItem(final TabFolder parent, final int style) {
        super((Widget)parent, style);
        (this.parent = parent).createItem(this, parent.getItemCount());
    }
    
    public TabItem(final TabFolder parent, final int style, final int index) {
        super((Widget)parent, style);
        (this.parent = parent).createItem(this, index);
    }
    
    void _setText(final int index, String string) {
        if (this.image != null && string.indexOf(38) != -1) {
            final int length = string.length();
            final char[] text = new char[length];
            string.getChars(0, length, text, 0);
            int i = 0;
            int j = 0;
            for (i = 0; i < length; ++i) {
                if (text[i] != '&') {
                    text[j++] = text[i];
                }
            }
            if (j < i) {
                string = new String(text, 0, j);
            }
        }
        final long hwnd = this.parent.handle;
        final long hHeap = OS.GetProcessHeap();
        final TCHAR buffer = new TCHAR(this.parent.getCodePage(), string, true);
        final int byteCount = buffer.length() * 2;
        final long pszText = OS.HeapAlloc(hHeap, 8, byteCount);
        OS.MoveMemory(pszText, buffer, byteCount);
        final TCITEM tcItem = new TCITEM();
        tcItem.mask = 1;
        tcItem.pszText = pszText;
        OS.SendMessage(hwnd, 4925, (long)index, tcItem);
        OS.HeapFree(hHeap, 0, pszText);
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    void destroyWidget() {
        this.parent.destroyItem(this);
        this.releaseHandle();
    }
    
    public Control getControl() {
        this.checkWidget();
        return this.control;
    }
    
    public Rectangle getBounds() {
        this.checkWidget();
        return DPIUtil.autoScaleDown(this.getBoundsInPixels());
    }
    
    Rectangle getBoundsInPixels() {
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return new Rectangle(0, 0, 0, 0);
        }
        final RECT itemRect = new RECT();
        OS.SendMessage(this.parent.handle, 4874, (long)index, itemRect);
        return new Rectangle(itemRect.left, itemRect.top, itemRect.right - itemRect.left, itemRect.bottom - itemRect.top);
    }
    
    public TabFolder getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public String getToolTipText() {
        this.checkWidget();
        return this.toolTipText;
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
    }
    
    void releaseParent() {
        super.releaseParent();
        final int index = this.parent.indexOf(this);
        if (index == this.parent.getSelectionIndex() && this.control != null) {
            this.control.setVisible(false);
        }
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.control = null;
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
        if (this.control != null && this.control.isDisposed()) {
            this.control = null;
        }
        final Control oldControl = this.control;
        final Control newControl = control;
        this.control = control;
        final int index = this.parent.indexOf(this);
        final int selectionIndex = this.parent.getSelectionIndex();
        if (index != selectionIndex && newControl != null) {
            if (selectionIndex != -1) {
                final Control selectedControl = this.parent.getItem(selectionIndex).getControl();
                if (selectedControl == newControl) {
                    return;
                }
            }
            newControl.setVisible(false);
            return;
        }
        if (newControl != null) {
            newControl.setBounds(this.parent.getClientAreaInPixels());
            newControl.setVisible(true);
        }
        if (oldControl != null && newControl != null && oldControl != newControl) {
            oldControl.setVisible(false);
        }
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        super.setImage(image);
        if (this.text.indexOf(38) != -1) {
            this._setText(index, this.text);
        }
        final long hwnd = this.parent.handle;
        final TCITEM tcItem = new TCITEM();
        tcItem.mask = 2;
        tcItem.iImage = this.parent.imageIndex(image);
        OS.SendMessage(hwnd, 4925, (long)index, tcItem);
    }
    
    public void setText(final String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        if (string.equals(this.text)) {
            return;
        }
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        super.setText(string);
        final int textDirection = ((this.state & 0x400000) != 0x0) ? 100663296 : (this.style & Integer.MIN_VALUE);
        if (!this.updateTextDirection(textDirection)) {
            this._setText(index, string);
        }
    }
    
    boolean updateTextDirection(final int textDirection) {
        if (super.updateTextDirection(textDirection)) {
            final int index = this.parent.indexOf(this);
            if (index != -1) {
                if ((textDirection & 0x4000000) != 0x0) {
                    this._setText(index, "\u202b" + this.text);
                    return true;
                }
                if ((textDirection & 0x2000000) != 0x0) {
                    this._setText(index, "\u202a" + this.text);
                    return true;
                }
            }
        }
        return false;
    }
    
    public void setToolTipText(final String string) {
        this.checkWidget();
        this.toolTipText = string;
    }
}
