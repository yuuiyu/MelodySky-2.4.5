//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class NMLINK extends NMHDR
{
    public int mask;
    public int iLink;
    public int state;
    public int stateMask;
    public char[] szID;
    public char[] szUrl;
    public static final int sizeof;
    
    public NMLINK() {
        this.szID = new char[48];
        this.szUrl = new char[2084];
    }
    
    static {
        sizeof = OS.NMLINK_sizeof();
    }
}
