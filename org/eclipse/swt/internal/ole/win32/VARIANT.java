//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class VARIANT
{
    public short vt;
    public short wReserved1;
    public short wReserved2;
    public short wReserved3;
    public int lVal;
    public static final int sizeof;
    
    static {
        sizeof = COM.VARIANT_sizeof();
    }
}
