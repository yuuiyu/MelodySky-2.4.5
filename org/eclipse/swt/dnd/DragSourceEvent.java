//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class DragSourceEvent extends TypedEvent
{
    public int detail;
    public boolean doit;
    public int x;
    public int y;
    public TransferData dataType;
    public Image image;
    public int offsetX;
    public int offsetY;
    static final long serialVersionUID = 3257002142513770808L;
    
    public DragSourceEvent(final DNDEvent e) {
        super((Event)e);
        this.data = e.data;
        this.detail = e.detail;
        this.doit = e.doit;
        this.dataType = e.dataType;
        this.x = e.x;
        this.y = e.y;
        this.image = e.image;
        this.offsetX = e.offsetX;
        this.offsetY = e.offsetY;
    }
    
    void updateEvent(final DNDEvent e) {
        e.widget = this.widget;
        e.time = this.time;
        e.data = this.data;
        e.detail = this.detail;
        e.doit = this.doit;
        e.dataType = this.dataType;
        e.x = this.x;
        e.y = this.y;
        e.image = this.image;
        e.offsetX = this.offsetX;
        e.offsetY = this.offsetY;
    }
    
    @Override
    public String toString() {
        final String string = super.toString();
        return string.substring(0, string.length() - 1) + " operation=" + this.detail + " type=" + ((this.dataType != null) ? this.dataType.type : 0) + " doit=" + this.doit;
    }
}
