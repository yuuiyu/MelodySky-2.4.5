//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;

public class CTabItem extends Item
{
    CTabFolder parent;
    int x;
    int y;
    int width;
    int height;
    Control control;
    String toolTipText;
    String shortenedText;
    int shortenedTextWidth;
    Font font;
    Color foreground;
    Color selectionForeground;
    Image disabledImage;
    Rectangle closeRect;
    int closeImageState;
    int state;
    boolean showClose;
    boolean showing;
    
    public CTabItem(final CTabFolder parent, final int style) {
        this(parent, style, parent.getItemCount());
    }
    
    public CTabItem(final CTabFolder parent, final int style, final int index) {
        super((Widget)parent, style);
        this.height = 0;
        this.closeRect = new Rectangle(0, 0, 0, 0);
        this.closeImageState = 8;
        this.state = 0;
        this.showClose = false;
        this.showing = false;
        this.showClose = ((style & 0x40) != 0x0);
        parent.createItem(this, index);
    }
    
    @Override
    public void dispose() {
        if (this.isDisposed()) {
            return;
        }
        this.parent.destroyItem(this);
        super.dispose();
        this.parent = null;
        this.control = null;
        this.toolTipText = null;
        this.shortenedText = null;
        this.font = null;
    }
    
    public Rectangle getBounds() {
        this.parent.runUpdate();
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
    
    public Control getControl() {
        this.checkWidget();
        return this.control;
    }
    
    @Deprecated
    public Image getDisabledImage() {
        this.checkWidget();
        return this.disabledImage;
    }
    
    public Color getForeground() {
        this.checkWidget();
        if (this.foreground != null) {
            return this.foreground;
        }
        return this.parent.getForeground();
    }
    
    public Color getSelectionForeground() {
        this.checkWidget();
        if (this.selectionForeground != null) {
            return this.selectionForeground;
        }
        return this.parent.getSelectionForeground();
    }
    
    public Font getFont() {
        this.checkWidget();
        if (this.font != null) {
            return this.font;
        }
        return this.parent.getFont();
    }
    
    public CTabFolder getParent() {
        return this.parent;
    }
    
    public boolean getShowClose() {
        this.checkWidget();
        return this.showClose;
    }
    
    public String getToolTipText() {
        this.checkWidget();
        if (this.toolTipText == null && this.shortenedText != null) {
            final String text = this.getText();
            if (!this.shortenedText.equals(text)) {
                return text;
            }
        }
        return this.toolTipText;
    }
    
    public boolean isShowing() {
        this.checkWidget();
        return this.showing;
    }
    
    public void setControl(final Control control) {
        this.checkWidget();
        if (control != null) {
            if (control.isDisposed()) {
                SWT.error(5);
            }
            if (control.getParent() != this.parent) {
                SWT.error(32);
            }
        }
        if (this.control != null && !this.control.isDisposed()) {
            this.control.setVisible(false);
        }
        this.control = control;
        if (this.control != null) {
            final int index = this.parent.indexOf(this);
            if (index == this.parent.getSelectionIndex()) {
                this.control.setBounds(this.parent.getClientArea());
                this.control.setVisible(true);
            }
            else {
                final int selectedIndex = this.parent.getSelectionIndex();
                Control selectedControl = null;
                if (selectedIndex != -1) {
                    selectedControl = this.parent.getItem(selectedIndex).control;
                }
                if (this.control != selectedControl) {
                    this.control.setVisible(false);
                }
            }
        }
    }
    
    @Deprecated
    public void setDisabledImage(final Image image) {
        this.checkWidget();
        if (image != null && image.isDisposed()) {
            SWT.error(5);
        }
        this.disabledImage = image;
    }
    
    boolean setFocus() {
        return this.control != null && !this.control.isDisposed() && this.control.setFocus();
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        if (font != null && font.isDisposed()) {
            SWT.error(5);
        }
        if (font == null && this.font == null) {
            return;
        }
        if (font != null && font.equals(this.font)) {
            return;
        }
        this.font = font;
        this.parent.updateFolder(12);
    }
    
    public void setForeground(final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            SWT.error(5);
        }
        if (color == this.foreground) {
            return;
        }
        this.foreground = color;
        this.parent.updateFolder(4);
    }
    
    public void setSelectionForeground(final Color color) {
        this.checkWidget();
        if (color != null && color.isDisposed()) {
            SWT.error(5);
        }
        if (color == this.selectionForeground) {
            return;
        }
        this.selectionForeground = color;
        this.parent.updateFolder(4);
    }
    
    @Override
    public void setImage(final Image image) {
        this.checkWidget();
        if (image != null && image.isDisposed()) {
            SWT.error(5);
        }
        final Image oldImage = this.getImage();
        if (image == null && oldImage == null) {
            return;
        }
        if (image != null && image.equals(oldImage)) {
            return;
        }
        super.setImage(image);
        this.parent.updateFolder(12);
    }
    
    public void setShowClose(final boolean close) {
        this.checkWidget();
        if (this.showClose == close) {
            return;
        }
        this.showClose = close;
        this.parent.updateFolder(4);
    }
    
    @Override
    public void setText(final String string) {
        this.checkWidget();
        if (string == null) {
            SWT.error(4);
        }
        if (string.equals(this.getText())) {
            return;
        }
        super.setText(string);
        this.shortenedText = null;
        this.shortenedTextWidth = 0;
        this.parent.updateFolder(12);
    }
    
    public void setToolTipText(final String string) {
        this.checkWidget();
        this.toolTipText = string;
    }
}
