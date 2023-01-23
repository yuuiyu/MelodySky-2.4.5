//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class TRACKMOUSEEVENT
{
    public int cbSize;
    public int dwFlags;
    public long hwndTrack;
    public int dwHoverTime;
    public static final int sizeof;
    
    static {
        sizeof = OS.TRACKMOUSEEVENT_sizeof();
    }
}
