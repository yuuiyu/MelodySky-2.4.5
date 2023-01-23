//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class SCRIPT_LOGATTR
{
    public boolean fSoftBreak;
    public boolean fWhiteSpace;
    public boolean fCharStop;
    public boolean fWordStop;
    public boolean fInvalid;
    public byte fReserved;
    public static final int sizeof;
    
    static {
        sizeof = OS.SCRIPT_LOGATTR_sizeof();
    }
}
