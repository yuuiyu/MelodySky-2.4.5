//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class VARDESC
{
    public int memid;
    public long lpstrSchema;
    public int oInst;
    public long elemdescVar_tdesc_union;
    public short elemdescVar_tdesc_vt;
    public long elemdescVar_paramdesc_pparamdescex;
    public short elemdescVar_paramdesc_wParamFlags;
    public short wVarFlags;
    public int varkind;
    public static final int sizeof;
    
    static {
        sizeof = COM.VARDESC_sizeof();
    }
}
