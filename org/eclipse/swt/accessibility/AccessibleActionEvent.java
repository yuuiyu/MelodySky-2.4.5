//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import java.util.*;

public class AccessibleActionEvent extends EventObject
{
    public String result;
    public int count;
    public int index;
    public boolean localized;
    static final long serialVersionUID = 2849066792640153087L;
    
    public AccessibleActionEvent(final Object source) {
        super(source);
    }
    
    @Override
    public String toString() {
        return "AccessibleActionEvent {string=" + this.result + " count=" + this.count + " index=" + this.index;
    }
}
