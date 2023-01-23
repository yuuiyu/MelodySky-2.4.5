//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class CREATESTRUCT
{
    public long lpCreateParams;
    public long hInstance;
    public long hMenu;
    public long hwndParent;
    public int cy;
    public int cx;
    public int y;
    public int x;
    public int style;
    public long lpszName;
    public long lpszClass;
    public int dwExStyle;
    public static final int sizeof;
    
    static {
        sizeof = OS.CREATESTRUCT_sizeof();
    }
}
