//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class NMREBARCHILDSIZE extends NMHDR
{
    public int uBand;
    public int wID;
    public int rcChild_left;
    public int rcChild_top;
    public int rcChild_right;
    public int rcChild_bottom;
    public int rcBand_left;
    public int rcBand_top;
    public int rcBand_right;
    public int rcBand_bottom;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMREBARCHILDSIZE_sizeof();
    }
}
