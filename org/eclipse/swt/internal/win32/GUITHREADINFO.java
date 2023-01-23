//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class GUITHREADINFO
{
    public int cbSize;
    public int flags;
    public long hwndActive;
    public long hwndFocus;
    public long hwndCapture;
    public long hwndMenuOwner;
    public long hwndMoveSize;
    public long hwndCaret;
    public int left;
    public int top;
    public int right;
    public int bottom;
    public static int sizeof;
    
    static {
        GUITHREADINFO.sizeof = OS.GUITHREADINFO_sizeof();
    }
}
