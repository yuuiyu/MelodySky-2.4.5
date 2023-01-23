//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class NOTIFYICONDATA
{
    public int cbSize;
    public long hWnd;
    public int uID;
    public int uFlags;
    public int uCallbackMessage;
    public long hIcon;
    public char[] szTip;
    public int dwState;
    public int dwStateMask;
    public char[] szInfo;
    public char[] szInfoTitle;
    public int uVersion;
    public int dwInfoFlags;
    public static final int sizeof;
    
    public NOTIFYICONDATA() {
        this.szTip = new char[128];
        this.szInfo = new char[256];
        this.szInfoTitle = new char[64];
    }
    
    static {
        sizeof = OS.NOTIFYICONDATA_V2_SIZE;
    }
}
