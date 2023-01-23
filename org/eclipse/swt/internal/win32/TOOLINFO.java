//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class TOOLINFO
{
    public int cbSize;
    public int uFlags;
    public long hwnd;
    public long uId;
    public int left;
    public int top;
    public int right;
    public int bottom;
    public long hinst;
    public long lpszText;
    public long lParam;
    public long lpReserved;
    public static int sizeof;
    
    static {
        TOOLINFO.sizeof = OS.TOOLINFO_sizeof();
    }
}
