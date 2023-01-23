//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.widgets.*;

public class SelectionEvent extends TypedEvent
{
    public Widget item;
    public int detail;
    public int x;
    public int y;
    public int width;
    public int height;
    public int stateMask;
    public String text;
    public boolean doit;
    static final long serialVersionUID = 3976735856884987953L;
    
    public SelectionEvent(final Event e) {
        super(e);
        this.item = e.item;
        this.x = e.x;
        this.y = e.y;
        this.width = e.width;
        this.height = e.height;
        this.detail = e.detail;
        this.stateMask = e.stateMask;
        this.text = e.text;
        this.doit = e.doit;
    }
    
    @Override
    public String toString() {
        final String string = super.toString();
        return string.substring(0, string.length() - 1) + " item=" + this.item + " detail=" + this.detail + " x=" + this.x + " y=" + this.y + " width=" + this.width + " height=" + this.height + " stateMask=0x" + Integer.toHexString(this.stateMask) + " text=" + this.text + " doit=" + this.doit;
    }
}
