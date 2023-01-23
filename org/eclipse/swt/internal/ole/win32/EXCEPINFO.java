//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public final class EXCEPINFO
{
    public short wCode;
    public short wReserved;
    public long bstrSource;
    public long bstrDescription;
    public long bstrHelpFile;
    public int dwHelpContext;
    public long pvReserved;
    public long pfnDeferredFillIn;
    public int scode;
    public static final int sizeof;
    
    static {
        sizeof = COM.EXCEPINFO_sizeof();
    }
}
