//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class OSVERSIONINFOEX
{
    public int dwOSVersionInfoSize;
    public int dwMajorVersion;
    public int dwMinorVersion;
    public int dwBuildNumber;
    public int dwPlatformId;
    public char[] szCSDVersion;
    public int wServicePackMajor;
    public int wServicePackMinor;
    public int wSuiteMask;
    public int wProductType;
    public int wReserved;
    public static final int sizeof;
    
    public OSVERSIONINFOEX() {
        this.szCSDVersion = new char[128];
    }
    
    static {
        sizeof = OS.OSVERSIONINFOEX_sizeof();
    }
}
