//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class SHELLEXECUTEINFO
{
    public int cbSize;
    public int fMask;
    public long hwnd;
    public long lpVerb;
    public long lpFile;
    public long lpParameters;
    public long lpDirectory;
    public int nShow;
    public long hInstApp;
    public long lpIDList;
    public long lpClass;
    public long hkeyClass;
    public int dwHotKey;
    public long hIcon;
    public long hProcess;
    public static final int sizeof;
    
    static {
        sizeof = OS.SHELLEXECUTEINFO_sizeof();
    }
}
