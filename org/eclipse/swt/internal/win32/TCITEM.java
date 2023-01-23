//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class TCITEM
{
    public int mask;
    public int dwState;
    public int dwStateMask;
    public long pszText;
    public int cchTextMax;
    public int iImage;
    public long lParam;
    public static final int sizeof;
    
    static {
        sizeof = OS.TCITEM_sizeof();
    }
}
