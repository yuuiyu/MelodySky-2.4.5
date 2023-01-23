//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class MENUINFO
{
    public int cbSize;
    public int fMask;
    public int dwStyle;
    public int cyMax;
    public long hbrBack;
    public int dwContextHelpID;
    public long dwMenuData;
    public static final int sizeof;
    
    static {
        sizeof = OS.MENUINFO_sizeof();
    }
}
