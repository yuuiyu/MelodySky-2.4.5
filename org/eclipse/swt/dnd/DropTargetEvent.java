//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

public class DropTargetEvent extends TypedEvent
{
    public int x;
    public int y;
    public int detail;
    public int operations;
    public int feedback;
    public Widget item;
    public TransferData currentDataType;
    public TransferData[] dataTypes;
    static final long serialVersionUID = 3256727264573338678L;
    
    public DropTargetEvent(final DNDEvent e) {
        super((Event)e);
        this.data = e.data;
        this.x = e.x;
        this.y = e.y;
        this.detail = e.detail;
        this.currentDataType = e.dataType;
        this.dataTypes = e.dataTypes;
        this.operations = e.operations;
        this.feedback = e.feedback;
        this.item = e.item;
    }
    
    void updateEvent(final DNDEvent e) {
        e.widget = this.widget;
        e.time = this.time;
        e.data = this.data;
        e.x = this.x;
        e.y = this.y;
        e.detail = this.detail;
        e.dataType = this.currentDataType;
        e.dataTypes = this.dataTypes;
        e.operations = this.operations;
        e.feedback = this.feedback;
        e.item = this.item;
    }
    
    @Override
    public String toString() {
        final String string = super.toString();
        final StringBuilder sb = new StringBuilder();
        sb.append(string.substring(0, string.length() - 1));
        sb.append(" x=");
        sb.append(this.x);
        sb.append(" y=");
        sb.append(this.y);
        sb.append(" item=");
        sb.append(this.item);
        sb.append(" operations=");
        sb.append(this.operations);
        sb.append(" operation=");
        sb.append(this.detail);
        sb.append(" feedback=");
        sb.append(this.feedback);
        sb.append(" dataTypes={ ");
        if (this.dataTypes != null) {
            for (final TransferData dataType : this.dataTypes) {
                sb.append(dataType.type);
                sb.append(' ');
            }
        }
        sb.append('}');
        sb.append(" currentDataType=");
        sb.append((this.currentDataType != null) ? this.currentDataType.type : 48);
        sb.append('}');
        return sb.toString();
    }
}
