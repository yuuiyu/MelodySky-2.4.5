//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class PROPERTYKEY
{
    public byte[] fmtid;
    public int pid;
    public static final int sizeof;
    
    public PROPERTYKEY() {
        this.fmtid = new byte[16];
    }
    
    static {
        sizeof = OS.PROPERTYKEY_sizeof();
    }
}
