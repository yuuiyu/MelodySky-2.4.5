//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;

public abstract class Item extends Widget
{
    String text;
    Image image;
    static final int TEXT_LIMIT = 8192;
    static final String ELLIPSIS = "...";
    
    public Item(final Widget parent, final int style) {
        super(parent, style);
        this.text = "";
    }
    
    public Item(final Widget parent, final int style, final int index) {
        this(parent, style);
    }
    
    @Override
    protected void checkSubclass() {
    }
    
    public Image getImage() {
        this.checkWidget();
        return this.image;
    }
    
    @Override
    String getNameText() {
        return this.getText();
    }
    
    public String getText() {
        this.checkWidget();
        return this.text;
    }
    
    @Override
    void releaseWidget() {
        super.releaseWidget();
        this.text = null;
        this.image = null;
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if (this.image == image) {
            return;
        }
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        this.image = image;
    }
    
    public void setText(final String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        this.text = string;
        if ((this.state & 0x400000) != 0x0) {
            this.updateTextDirection(100663296);
        }
    }
    
    boolean updateTextDirection(int textDirection) {
        if (textDirection == 100663296) {
            this.state |= 0x400000;
            textDirection = (((this.style ^ BidiUtil.resolveTextDirection(this.text)) == 0x0) ? 0 : Integer.MIN_VALUE);
        }
        else {
            this.state &= 0xFFBFFFFF;
        }
        if (((this.style & Integer.MIN_VALUE) ^ textDirection) != 0x0) {
            this.style ^= Integer.MIN_VALUE;
            return true;
        }
        return textDirection == 100663296;
    }
}
