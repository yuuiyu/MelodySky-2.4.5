//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class STARTUPINFO
{
    public int cb;
    public long lpReserved;
    public long lpDesktop;
    public long lpTitle;
    public int dwX;
    public int dwY;
    public int dwXSize;
    public int dwYSize;
    public int dwXCountChars;
    public int dwYCountChars;
    public int dwFillAttribute;
    public int dwFlags;
    public short wShowWindow;
    public short cbReserved2;
    public long lpReserved2;
    public long hStdInput;
    public long hStdOutput;
    public long hStdError;
    public static int sizeof;
    
    static {
        STARTUPINFO.sizeof = OS.STARTUPINFO_sizeof();
    }
}
