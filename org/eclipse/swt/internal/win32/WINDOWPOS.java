//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class WINDOWPOS
{
    public long hwnd;
    public long hwndInsertAfter;
    public int x;
    public int y;
    public int cx;
    public int cy;
    public int flags;
    public static final int sizeof;
    
    static {
        sizeof = OS.WINDOWPOS_sizeof();
    }
}
