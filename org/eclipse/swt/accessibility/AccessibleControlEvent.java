//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import java.util.*;

public class AccessibleControlEvent extends EventObject
{
    public int childID;
    public Accessible accessible;
    public int x;
    public int y;
    public int width;
    public int height;
    public int detail;
    public String result;
    public Object[] children;
    static final long serialVersionUID = 3257281444169529141L;
    
    public AccessibleControlEvent(final Object source) {
        super(source);
    }
    
    @Override
    public String toString() {
        return "AccessibleControlEvent {childID=" + this.childID + " accessible=" + this.accessible + " x=" + this.x + " y=" + this.y + " width=" + this.width + " height=" + this.height + " detail=" + this.detail + " result=" + this.result;
    }
}
