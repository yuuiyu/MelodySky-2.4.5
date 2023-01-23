//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class SCROLLINFO
{
    public int cbSize;
    public int fMask;
    public int nMin;
    public int nMax;
    public int nPage;
    public int nPos;
    public int nTrackPos;
    public static final int sizeof;
    
    static {
        sizeof = OS.SCROLLINFO_sizeof();
    }
}
