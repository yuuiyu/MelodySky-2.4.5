//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

public class MovementEvent extends TypedEvent
{
    public int lineOffset;
    public String lineText;
    public int offset;
    public int newOffset;
    public int movement;
    static final long serialVersionUID = 3978765487853324342L;
    
    public MovementEvent(final StyledTextEvent e) {
        super(e);
        this.lineOffset = e.detail;
        this.lineText = e.text;
        this.movement = e.count;
        this.offset = e.start;
        this.newOffset = e.end;
    }
}
