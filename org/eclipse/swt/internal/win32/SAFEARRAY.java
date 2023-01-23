//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class SAFEARRAY
{
    public short cDims;
    public short fFeatures;
    public int cbElements;
    public int cLocks;
    public long pvData;
    public SAFEARRAYBOUND rgsabound;
    public static final int sizeof;
    
    static {
        sizeof = OS.SAFEARRAY_sizeof();
    }
}
