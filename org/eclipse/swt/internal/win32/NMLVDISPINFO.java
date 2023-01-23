//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class NMLVDISPINFO extends NMHDR
{
    public int mask;
    public int iItem;
    public int iSubItem;
    public int state;
    public int stateMask;
    public long pszText;
    public int cchTextMax;
    public int iImage;
    public long lParam;
    public int iIndent;
    public int iGroupId;
    public int cColumns;
    public long puColumns;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMLVDISPINFO_sizeof();
    }
}
