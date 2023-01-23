//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class MINMAXINFO
{
    public int ptReserved_x;
    public int ptReserved_y;
    public int ptMaxSize_x;
    public int ptMaxSize_y;
    public int ptMaxPosition_x;
    public int ptMaxPosition_y;
    public int ptMinTrackSize_x;
    public int ptMinTrackSize_y;
    public int ptMaxTrackSize_x;
    public int ptMaxTrackSize_y;
    public static final int sizeof;
    
    static {
        sizeof = OS.MINMAXINFO_sizeof();
    }
}
