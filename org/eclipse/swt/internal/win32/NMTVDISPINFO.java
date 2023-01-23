//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class NMTVDISPINFO extends NMHDR
{
    public int mask;
    public long hItem;
    public int state;
    public int stateMask;
    public long pszText;
    public int cchTextMax;
    public int iImage;
    public int iSelectedImage;
    public int cChildren;
    public long lParam;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMTVDISPINFO_sizeof();
    }
}
