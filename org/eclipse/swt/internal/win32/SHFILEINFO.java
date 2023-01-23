//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class SHFILEINFO
{
    public long hIcon;
    public int iIcon;
    public int dwAttributes;
    public char[] szDisplayName;
    public char[] szTypeName;
    public static int sizeof;
    
    public SHFILEINFO() {
        this.szDisplayName = new char[260];
        this.szTypeName = new char[80];
    }
    
    static {
        SHFILEINFO.sizeof = OS.SHFILEINFO_sizeof();
    }
}
