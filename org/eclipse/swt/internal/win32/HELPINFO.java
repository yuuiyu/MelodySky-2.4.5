//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class HELPINFO
{
    public int cbSize;
    public int iContextType;
    public int iCtrlId;
    public long hItemHandle;
    public int dwContextId;
    public int x;
    public int y;
    public static final int sizeof;
    
    static {
        sizeof = OS.HELPINFO_sizeof();
    }
}
