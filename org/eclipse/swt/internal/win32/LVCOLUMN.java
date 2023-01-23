//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class LVCOLUMN
{
    public int mask;
    public int fmt;
    public int cx;
    public long pszText;
    public int cchTextMax;
    public int iSubItem;
    public int iImage;
    public int iOrder;
    public static final int sizeof;
    
    static {
        sizeof = OS.LVCOLUMN_sizeof();
    }
}
