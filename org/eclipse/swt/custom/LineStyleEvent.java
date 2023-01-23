//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

public class LineStyleEvent extends TypedEvent
{
    public int lineOffset;
    public String lineText;
    public int[] ranges;
    public StyleRange[] styles;
    public int alignment;
    public int indent;
    int verticalIndent;
    public int wrapIndent;
    public boolean justify;
    public Bullet bullet;
    public int bulletIndex;
    public int[] tabStops;
    static final long serialVersionUID = 3906081274027192884L;
    
    public LineStyleEvent(final StyledTextEvent e) {
        super(e);
        this.styles = e.styles;
        this.ranges = e.ranges;
        this.lineOffset = e.detail;
        this.lineText = e.text;
        this.alignment = e.alignment;
        this.justify = e.justify;
        this.indent = e.indent;
        this.verticalIndent = e.verticalIndent;
        this.wrapIndent = e.wrapIndent;
        this.bullet = e.bullet;
        this.bulletIndex = e.bulletIndex;
        this.tabStops = e.tabStops;
    }
}
