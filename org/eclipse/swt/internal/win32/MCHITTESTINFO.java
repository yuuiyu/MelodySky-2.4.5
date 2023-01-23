//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class MCHITTESTINFO
{
    public int cbSize;
    public POINT pt;
    public int uHit;
    public SYSTEMTIME st;
    public static final int sizeof;
    
    public MCHITTESTINFO() {
        this.pt = new POINT();
        this.st = new SYSTEMTIME();
    }
    
    static {
        sizeof = OS.MCHITTESTINFO_sizeof();
    }
}
