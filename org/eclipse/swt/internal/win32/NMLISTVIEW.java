//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class NMLISTVIEW extends NMHDR
{
    public int iItem;
    public int iSubItem;
    public int uNewState;
    public int uOldState;
    public int uChanged;
    public int x;
    public int y;
    public long lParam;
    public static int sizeof;
    
    static {
        NMLISTVIEW.sizeof = OS.NMLISTVIEW_sizeof();
    }
}
