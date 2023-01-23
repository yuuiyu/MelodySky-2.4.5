//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class MENUITEMINFO
{
    public int cbSize;
    public int fMask;
    public int fType;
    public int fState;
    public int wID;
    public long hSubMenu;
    public long hbmpChecked;
    public long hbmpUnchecked;
    public long dwItemData;
    public long dwTypeData;
    public int cch;
    public long hbmpItem;
    public static final int sizeof;
    
    static {
        sizeof = OS.MENUITEMINFO_sizeof();
    }
}
