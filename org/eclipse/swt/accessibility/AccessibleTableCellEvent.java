//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import java.util.*;

public class AccessibleTableCellEvent extends EventObject
{
    public Accessible accessible;
    public Accessible[] accessibles;
    public boolean isSelected;
    public int count;
    public int index;
    static final long serialVersionUID = 7231059449172889781L;
    
    public AccessibleTableCellEvent(final Object source) {
        super(source);
    }
    
    @Override
    public String toString() {
        return "AccessibleTableCellEvent { accessibles=" + Arrays.toString(this.accessibles) + " isSelected=" + this.isSelected + " count=" + this.count + " index=" + this.index;
    }
}
