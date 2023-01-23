//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class SCROLLBARINFO
{
    public int cbSize;
    public RECT rcScrollBar;
    public int dxyLineButton;
    public int xyThumbTop;
    public int xyThumbBottom;
    public int reserved;
    public int[] rgstate;
    public static final int sizeof;
    
    public SCROLLBARINFO() {
        this.rcScrollBar = new RECT();
        this.rgstate = new int[6];
    }
    
    static {
        sizeof = OS.SCROLLBARINFO_sizeof();
    }
}
