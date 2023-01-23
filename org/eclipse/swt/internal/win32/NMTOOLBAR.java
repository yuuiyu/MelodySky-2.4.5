//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class NMTOOLBAR extends NMHDR
{
    public int iItem;
    public int iBitmap;
    public int idCommand;
    public byte fsState;
    public byte fsStyle;
    public long dwData;
    public long iString;
    public int cchText;
    public long pszText;
    public int left;
    public int top;
    public int right;
    public int bottom;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMTOOLBAR_sizeof();
    }
}
