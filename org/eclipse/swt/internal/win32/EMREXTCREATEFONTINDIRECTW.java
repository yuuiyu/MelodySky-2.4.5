//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class EMREXTCREATEFONTINDIRECTW
{
    public EMR emr;
    public int ihFont;
    public EXTLOGFONTW elfw;
    public static final int sizeof;
    
    public EMREXTCREATEFONTINDIRECTW() {
        this.emr = new EMR();
        this.elfw = new EXTLOGFONTW();
    }
    
    static {
        sizeof = OS.EMREXTCREATEFONTINDIRECTW_sizeof();
    }
}
