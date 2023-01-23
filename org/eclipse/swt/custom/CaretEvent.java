//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

public class CaretEvent extends TypedEvent
{
    public int caretOffset;
    static final long serialVersionUID = 3257846571587545489L;
    
    CaretEvent(final StyledTextEvent e) {
        super(e);
        this.caretOffset = e.end;
    }
}
