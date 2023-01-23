//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class WNDCLASS
{
    public int style;
    public long lpfnWndProc;
    public int cbClsExtra;
    public int cbWndExtra;
    public long hInstance;
    public long hIcon;
    public long hCursor;
    public long hbrBackground;
    public long lpszMenuName;
    public long lpszClassName;
    public static final int sizeof;
    
    static {
        sizeof = OS.WNDCLASS_sizeof();
    }
}
