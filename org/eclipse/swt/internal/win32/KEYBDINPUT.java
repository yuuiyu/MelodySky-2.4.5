//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class KEYBDINPUT
{
    public short wVk;
    public short wScan;
    public int dwFlags;
    public int time;
    public long dwExtraInfo;
    public static final int sizeof;
    
    static {
        sizeof = OS.KEYBDINPUT_sizeof();
    }
}
