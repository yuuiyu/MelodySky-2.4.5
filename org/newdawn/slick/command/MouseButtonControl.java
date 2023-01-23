//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.command;

public class MouseButtonControl implements Control
{
    private int button;
    
    public MouseButtonControl(final int button) {
        this.button = button;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof MouseButtonControl && ((MouseButtonControl)o).button == this.button;
    }
    
    @Override
    public int hashCode() {
        return this.button;
    }
}
