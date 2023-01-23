//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

public final class ExtendedModifyEvent extends TypedEvent
{
    public int start;
    public int length;
    public String replacedText;
    static final long serialVersionUID = 3258696507027830832L;
    
    public ExtendedModifyEvent(final StyledTextEvent e) {
        super(e);
        this.start = e.start;
        this.length = e.end - e.start;
        this.replacedText = e.text;
    }
}
