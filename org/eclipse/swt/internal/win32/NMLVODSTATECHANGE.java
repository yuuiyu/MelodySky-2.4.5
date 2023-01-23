//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class NMLVODSTATECHANGE extends NMHDR
{
    public int iFrom;
    public int iTo;
    public int uNewState;
    public int uOldState;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMLVODSTATECHANGE_sizeof();
    }
}
