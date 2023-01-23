//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;

public class PaintObjectEvent extends TypedEvent
{
    public GC gc;
    public int x;
    public int y;
    public int ascent;
    public int descent;
    public StyleRange style;
    public Bullet bullet;
    public int bulletIndex;
    static final long serialVersionUID = 3906081274027192855L;
    
    public PaintObjectEvent(final StyledTextEvent e) {
        super(e);
        this.gc = e.gc;
        this.x = e.x;
        this.y = e.y;
        this.ascent = e.ascent;
        this.descent = e.descent;
        this.style = e.style;
        this.bullet = e.bullet;
        this.bulletIndex = e.bulletIndex;
    }
}
