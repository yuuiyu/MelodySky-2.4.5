//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class TBBUTTONINFO
{
    public int cbSize;
    public int dwMask;
    public int idCommand;
    public int iImage;
    public byte fsState;
    public byte fsStyle;
    public short cx;
    public long lParam;
    public long pszText;
    public int cchText;
    public static final int sizeof;
    
    static {
        sizeof = OS.TBBUTTONINFO_sizeof();
    }
}
