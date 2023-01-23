//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class MSG
{
    public long hwnd;
    public int message;
    public long wParam;
    public long lParam;
    public int time;
    public int x;
    public int y;
    public static final int sizeof;
    
    static {
        sizeof = OS.MSG_sizeof();
    }
}
