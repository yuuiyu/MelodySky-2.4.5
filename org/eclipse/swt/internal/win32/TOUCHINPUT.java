//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class TOUCHINPUT
{
    public int x;
    public int y;
    public long hSource;
    public int dwID;
    public int dwFlags;
    public int dwMask;
    public int dwTime;
    public long dwExtraInfo;
    public int cxContact;
    public int cyContact;
    public static final int sizeof;
    
    static {
        sizeof = OS.TOUCHINPUT_sizeof();
    }
}
