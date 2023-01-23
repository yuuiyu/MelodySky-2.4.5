//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import java.util.*;

public class AccessibleEvent extends EventObject
{
    public int childID;
    public String result;
    static final long serialVersionUID = 3257567304224026934L;
    
    public AccessibleEvent(final Object source) {
        super(source);
    }
    
    @Override
    public String toString() {
        return "AccessibleEvent {childID=" + this.childID + " result=" + this.result;
    }
}
