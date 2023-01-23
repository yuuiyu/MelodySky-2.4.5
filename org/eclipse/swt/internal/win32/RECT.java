//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class RECT
{
    public int left;
    public int top;
    public int right;
    public int bottom;
    public static final int sizeof;
    
    public RECT() {
    }
    
    public RECT(final int left, final int top, final int right, final int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }
    
    static {
        sizeof = OS.RECT_sizeof();
    }
}
