//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import java.util.*;
import org.eclipse.swt.graphics.*;

public class AccessibleTextEvent extends EventObject
{
    public int childID;
    public int offset;
    public int length;
    public Accessible accessible;
    public String result;
    public int count;
    public int index;
    public int start;
    public int end;
    public int type;
    public int x;
    public int y;
    public int width;
    public int height;
    public int[] ranges;
    public Rectangle[] rectangles;
    static final long serialVersionUID = 3977019530868308275L;
    
    public AccessibleTextEvent(final Object source) {
        super(source);
    }
    
    @Override
    public String toString() {
        return "AccessibleTextEvent {childID=" + this.childID + " offset=" + this.offset + " length=" + this.length;
    }
}
