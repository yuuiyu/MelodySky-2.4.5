//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class GESTUREINFO
{
    public int cbSize;
    public int dwFlags;
    public int dwID;
    public long hwndTarget;
    public short x;
    public short y;
    public int dwInstanceID;
    public int dwSequenceID;
    public long ullArguments;
    public int cbExtraArgs;
    public static final int sizeof;
    
    static {
        sizeof = OS.GESTUREINFO_sizeof();
    }
}
