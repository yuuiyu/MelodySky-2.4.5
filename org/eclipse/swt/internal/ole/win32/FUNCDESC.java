//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class FUNCDESC
{
    public int memid;
    public long lprgscode;
    public long lprgelemdescParam;
    public int funckind;
    public int invkind;
    public int callconv;
    public short cParams;
    public short cParamsOpt;
    public short oVft;
    public short cScodes;
    public long elemdescFunc_tdesc_union;
    public short elemdescFunc_tdesc_vt;
    public long elemdescFunc_paramdesc_pparamdescex;
    public short elemdescFunc_paramdesc_wParamFlags;
    public short wFuncFlags;
    public static final int sizeof;
    
    static {
        sizeof = COM.FUNCDESC_sizeof();
    }
}
