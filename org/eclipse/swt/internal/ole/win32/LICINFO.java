//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public final class LICINFO
{
    public int cbLicInfo;
    public boolean fRuntimeKeyAvail;
    public boolean fLicVerified;
    public static final int sizeof;
    
    static {
        sizeof = COM.LICINFO_sizeof();
    }
}
