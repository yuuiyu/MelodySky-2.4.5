//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class LVHITTESTINFO
{
    public int x;
    public int y;
    public int flags;
    public int iItem;
    public int iSubItem;
    public static int sizeof;
    
    static {
        LVHITTESTINFO.sizeof = OS.LVHITTESTINFO_sizeof();
    }
}
