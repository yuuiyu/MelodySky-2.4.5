//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import java.util.*;

public class AccessibleHyperlinkEvent extends EventObject
{
    public Accessible accessible;
    public String result;
    public int index;
    static final long serialVersionUID = 6253098373844074544L;
    
    public AccessibleHyperlinkEvent(final Object source) {
        super(source);
    }
    
    @Override
    public String toString() {
        return "AccessibleHyperlinkEvent {accessible=" + this.accessible + " string=" + this.result + " index=" + this.index;
    }
}
