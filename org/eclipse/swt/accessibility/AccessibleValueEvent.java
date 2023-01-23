//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import java.util.*;

public class AccessibleValueEvent extends EventObject
{
    public Number value;
    static final long serialVersionUID = -465979079760740668L;
    
    public AccessibleValueEvent(final Object source) {
        super(source);
    }
    
    @Override
    public String toString() {
        return "AccessibleValueEvent {value=" + this.value;
    }
}
