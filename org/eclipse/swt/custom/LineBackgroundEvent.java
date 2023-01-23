//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class LineBackgroundEvent extends TypedEvent
{
    public int lineOffset;
    public String lineText;
    public Color lineBackground;
    static final long serialVersionUID = 3978711687853324342L;
    
    public LineBackgroundEvent(final StyledTextEvent e) {
        super(e);
        this.lineOffset = e.detail;
        this.lineText = e.text;
        this.lineBackground = e.lineBackground;
    }
}
