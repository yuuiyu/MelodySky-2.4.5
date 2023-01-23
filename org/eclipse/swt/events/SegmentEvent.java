//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.widgets.*;

public class SegmentEvent extends TypedEvent
{
    public int lineOffset;
    public String lineText;
    public int[] segments;
    public char[] segmentsChars;
    static final long serialVersionUID = -2414889726745247762L;
    
    public SegmentEvent(final Event e) {
        super(e);
        this.lineText = e.text;
        this.lineOffset = e.detail;
    }
}
