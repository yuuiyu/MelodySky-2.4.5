//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class MENUBARINFO
{
    public int cbSize;
    public int left;
    public int top;
    public int right;
    public int bottom;
    public long hMenu;
    public long hwndMenu;
    public boolean fBarFocused;
    public boolean fFocused;
    public static final int sizeof;
    
    static {
        sizeof = OS.MENUBARINFO_sizeof();
    }
}
