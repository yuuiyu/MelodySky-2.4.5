//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class TVHITTESTINFO
{
    public int x;
    public int y;
    public int flags;
    public long hItem;
    public static int sizeof;
    
    static {
        TVHITTESTINFO.sizeof = OS.TVHITTESTINFO_sizeof();
    }
}
