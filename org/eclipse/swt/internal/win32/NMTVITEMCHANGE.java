//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class NMTVITEMCHANGE extends NMHDR
{
    public int uChanged;
    public long hItem;
    public int uStateNew;
    public int uStateOld;
    public long lParam;
    public static int sizeof;
    
    static {
        NMTVITEMCHANGE.sizeof = OS.NMTVITEMCHANGE_sizeof();
    }
}
