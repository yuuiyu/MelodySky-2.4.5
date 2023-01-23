//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class SCRIPT_STATE
{
    public short uBidiLevel;
    public boolean fOverrideDirection;
    public boolean fInhibitSymSwap;
    public boolean fCharShape;
    public boolean fDigitSubstitute;
    public boolean fInhibitLigate;
    public boolean fDisplayZWG;
    public boolean fArabicNumContext;
    public boolean fGcpClusters;
    public boolean fReserved;
    public short fEngineReserved;
    public static final int sizeof;
    
    static {
        sizeof = OS.SCRIPT_STATE_sizeof();
    }
}
