//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class NMHDR
{
    public long hwndFrom;
    public long idFrom;
    public int code;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMHDR_sizeof();
    }
}
