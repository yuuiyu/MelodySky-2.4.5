//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class EXTLOGFONTW
{
    public LOGFONT elfLogFont;
    public static final int sizeof;
    
    public EXTLOGFONTW() {
        this.elfLogFont = new LOGFONT();
    }
    
    static {
        sizeof = OS.EXTLOGFONTW_sizeof();
    }
}
