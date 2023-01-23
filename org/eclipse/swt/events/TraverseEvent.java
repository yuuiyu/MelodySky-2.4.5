//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.widgets.*;

public final class TraverseEvent extends KeyEvent
{
    public int detail;
    static final long serialVersionUID = 3257565105301239349L;
    
    public TraverseEvent(final Event e) {
        super(e);
        this.detail = e.detail;
    }
    
    public String toString() {
        final String string = super.toString();
        return string.substring(0, string.length() - 1) + " detail=" + this.detail;
    }
}
