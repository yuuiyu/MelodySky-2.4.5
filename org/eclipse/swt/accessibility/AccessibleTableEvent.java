//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import java.util.*;

public class AccessibleTableEvent extends EventObject
{
    public Accessible accessible;
    public Accessible[] accessibles;
    public String result;
    public int column;
    public int row;
    public int count;
    public boolean isSelected;
    public int[] selected;
    static final long serialVersionUID = 1624586163666270447L;
    
    public AccessibleTableEvent(final Object source) {
        super(source);
    }
    
    @Override
    public String toString() {
        return "AccessibleTableEvent {accessible=" + this.accessible + " accessibles=" + Arrays.toString(this.accessibles) + " string=" + this.result + " isSelected=" + this.isSelected + " column=" + this.column + " count=" + this.count + " row=" + this.row + " selected=" + Arrays.toString(this.selected);
    }
}
