//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import java.util.*;

public class AccessibleEditableTextEvent extends EventObject
{
    public int start;
    public int end;
    public String string;
    public String result;
    static final long serialVersionUID = -5045447704486894646L;
    
    public AccessibleEditableTextEvent(final Object source) {
        super(source);
    }
    
    @Override
    public String toString() {
        return "AccessibleEditableTextEvent {start=" + this.start + " end=" + this.end + " string=" + this.string + " result=" + this.result;
    }
}
