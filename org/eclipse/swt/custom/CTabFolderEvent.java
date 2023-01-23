//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

public class CTabFolderEvent extends TypedEvent
{
    public Widget item;
    public boolean doit;
    public int x;
    public int y;
    public int width;
    public int height;
    static final long serialVersionUID = 3760566386225066807L;
    
    CTabFolderEvent(final Widget w) {
        super(w);
    }
    
    @Override
    public String toString() {
        final String string = super.toString();
        return string.substring(0, string.length() - 1) + " item=" + this.item + " doit=" + this.doit + " x=" + this.x + " y=" + this.y + " width=" + this.width + " height=" + this.height;
    }
}
