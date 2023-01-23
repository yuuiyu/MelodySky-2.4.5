//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class CANDIDATEFORM
{
    public int dwIndex;
    public int dwStyle;
    public POINT ptCurrentPos;
    public RECT rcArea;
    public static final int sizeof;
    
    public CANDIDATEFORM() {
        this.ptCurrentPos = new POINT();
        this.rcArea = new RECT();
    }
    
    static {
        sizeof = OS.CANDIDATEFORM_sizeof();
    }
}
