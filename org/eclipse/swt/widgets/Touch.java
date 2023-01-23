//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.widgets;

public final class Touch
{
    public long id;
    public TouchSource source;
    public int state;
    public boolean primary;
    public int x;
    public int y;
    
    Touch(final long identity, final TouchSource source, final int state, final boolean primary, final int x, final int y) {
        this.id = identity;
        this.source = source;
        this.state = state;
        this.primary = primary;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        return "Touch {id=" + this.id + " source=" + this.source + " state=" + this.state + " primary=" + this.primary + " x=" + this.x + " y=" + this.y;
    }
}
