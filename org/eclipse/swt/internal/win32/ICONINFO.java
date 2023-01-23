//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class ICONINFO
{
    public boolean fIcon;
    public int xHotspot;
    public int yHotspot;
    public long hbmMask;
    public long hbmColor;
    public static final int sizeof;
    
    static {
        sizeof = OS.ICONINFO_sizeof();
    }
}
