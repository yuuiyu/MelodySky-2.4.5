//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class NMTTDISPINFO extends NMHDR
{
    public long lpszText;
    public char[] szText;
    public long hinst;
    public int uFlags;
    public long lParam;
    public static final int sizeof;
    
    public NMTTDISPINFO() {
        this.szText = new char[80];
    }
    
    static {
        sizeof = OS.NMTTDISPINFO_sizeof();
    }
}
