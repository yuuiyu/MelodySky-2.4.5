//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class SCRIPT_ANALYSIS
{
    public short eScript;
    public boolean fRTL;
    public boolean fLayoutRTL;
    public boolean fLinkBefore;
    public boolean fLinkAfter;
    public boolean fLogicalOrder;
    public boolean fNoGlyphIndex;
    public SCRIPT_STATE s;
    public static final int sizeof;
    
    public SCRIPT_ANALYSIS() {
        this.s = new SCRIPT_STATE();
    }
    
    static {
        sizeof = OS.SCRIPT_ANALYSIS_sizeof();
    }
}
