//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.widgets.*;

public final class ShellEvent extends TypedEvent
{
    public boolean doit;
    static final long serialVersionUID = 3257569490479888441L;
    
    public ShellEvent(final Event e) {
        super(e);
        this.doit = e.doit;
    }
    
    @Override
    public String toString() {
        final String string = super.toString();
        return string.substring(0, string.length() - 1) + " doit=" + this.doit;
    }
}
