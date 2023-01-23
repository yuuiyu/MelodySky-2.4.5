//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class WINDOWPLACEMENT
{
    public int length;
    public int flags;
    public int showCmd;
    public int ptMinPosition_x;
    public int ptMinPosition_y;
    public int ptMaxPosition_x;
    public int ptMaxPosition_y;
    public int left;
    public int top;
    public int right;
    public int bottom;
    public static final int sizeof;
    
    static {
        sizeof = OS.WINDOWPLACEMENT_sizeof();
    }
}
