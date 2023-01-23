//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import org.eclipse.swt.widgets.*;

public class KeyEvent extends TypedEvent
{
    public char character;
    public int keyCode;
    public int keyLocation;
    public int stateMask;
    public boolean doit;
    static final long serialVersionUID = 3256442491011412789L;
    
    public KeyEvent(final Event e) {
        super(e);
        this.character = e.character;
        this.keyCode = e.keyCode;
        this.keyLocation = e.keyLocation;
        this.stateMask = e.stateMask;
        this.doit = e.doit;
    }
    
    @Override
    public String toString() {
        final String string = super.toString();
        return string.substring(0, string.length() - 1) + " character='" + ((this.character == '\0') ? "\\0" : String.valueOf(this.character)) + "'=0x" + Integer.toHexString(this.character) + " keyCode=0x" + Integer.toHexString(this.keyCode) + " keyLocation=0x" + Integer.toHexString(this.keyLocation) + " stateMask=0x" + Integer.toHexString(this.stateMask) + " doit=" + this.doit;
    }
}
