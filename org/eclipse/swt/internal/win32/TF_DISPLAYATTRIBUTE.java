//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class TF_DISPLAYATTRIBUTE
{
    public TF_DA_COLOR crText;
    public TF_DA_COLOR crBk;
    public int lsStyle;
    public boolean fBoldLine;
    public TF_DA_COLOR crLine;
    public int bAttr;
    public static final int sizeof;
    
    public TF_DISPLAYATTRIBUTE() {
        this.crText = new TF_DA_COLOR();
        this.crBk = new TF_DA_COLOR();
        this.crLine = new TF_DA_COLOR();
    }
    
    static {
        sizeof = OS.TF_DISPLAYATTRIBUTE_sizeof();
    }
}
