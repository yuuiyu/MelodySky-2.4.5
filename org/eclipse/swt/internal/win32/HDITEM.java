//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class HDITEM
{
    public int mask;
    public int cxy;
    public long pszText;
    public long hbm;
    public int cchTextMax;
    public int fmt;
    public long lParam;
    public int iImage;
    public int iOrder;
    public int type;
    public long pvFilter;
    public static int sizeof;
    
    static {
        HDITEM.sizeof = OS.HDITEM_sizeof();
    }
}
