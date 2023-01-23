//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

import org.eclipse.swt.*;

public abstract class Dialog
{
    int style;
    Shell parent;
    String title;
    
    public Dialog(final Shell parent) {
        this(parent, 32768);
    }
    
    public Dialog(final Shell parent, final int style) {
        this.checkParent(parent);
        this.parent = parent;
        this.style = style;
        this.title = "";
    }
    
    protected void checkSubclass() {
        if (!Display.isValidClass(this.getClass())) {
            this.error(43);
        }
    }
    
    void checkParent(final Shell parent) {
        if (parent == null) {
            this.error(4);
        }
        parent.checkWidget();
    }
    
    static int checkStyle(final Shell parent, int style) {
        final int mask = 229376;
        if ((style & 0x10000000) != 0x0) {
            style &= 0xEFFFFFFF;
            if ((style & 0x38000) == 0x0) {
                style |= ((parent == null) ? 65536 : 32768);
            }
        }
        if ((style & 0x38000) == 0x0) {
            style |= 0x10000;
        }
        style &= 0xF7FFFFFF;
        if ((style & 0x6000000) == 0x0 && parent != null) {
            if ((parent.style & 0x2000000) != 0x0) {
                style |= 0x2000000;
            }
            if ((parent.style & 0x4000000) != 0x0) {
                style |= 0x4000000;
            }
        }
        return Widget.checkBits(style, 33554432, 67108864, 0, 0, 0, 0);
    }
    
    void error(final int code) {
        SWT.error(code);
    }
    
    public Shell getParent() {
        return this.parent;
    }
    
    public int getStyle() {
        return this.style;
    }
    
    public String getText() {
        return this.title;
    }
    
    public void setText(final String string) {
        if (string == null) {
            this.error(4);
        }
        this.title = string;
    }
}
