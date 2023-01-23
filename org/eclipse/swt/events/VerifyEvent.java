//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.widgets.*;

public final class VerifyEvent extends KeyEvent
{
    public int start;
    public int end;
    public String text;
    static final long serialVersionUID = 3257003246269577014L;
    
    public VerifyEvent(final Event e) {
        super(e);
        this.start = e.start;
        this.end = e.end;
        this.text = e.text;
    }
    
    public String toString() {
        final String string = super.toString();
        return string.substring(0, string.length() - 1) + " start=" + this.start + " end=" + this.end + " text=" + this.text;
    }
}
