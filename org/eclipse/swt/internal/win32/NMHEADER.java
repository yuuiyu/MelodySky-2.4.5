//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class NMHEADER extends NMHDR
{
    public int iItem;
    public int iButton;
    public long pitem;
    public static int sizeof;
    
    static {
        NMHEADER.sizeof = OS.NMHEADER_sizeof();
    }
}
