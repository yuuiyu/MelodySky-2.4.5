//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class NONCLIENTMETRICS
{
    public int cbSize;
    public int iBorderWidth;
    public int iScrollWidth;
    public int iScrollHeight;
    public int iCaptionWidth;
    public int iCaptionHeight;
    public LOGFONT lfCaptionFont;
    public int iSmCaptionWidth;
    public int iSmCaptionHeight;
    public LOGFONT lfSmCaptionFont;
    public int iMenuWidth;
    public int iMenuHeight;
    public LOGFONT lfMenuFont;
    public LOGFONT lfStatusFont;
    public LOGFONT lfMessageFont;
    public static final int sizeof;
    
    public NONCLIENTMETRICS() {
        this.lfCaptionFont = new LOGFONT();
        this.lfSmCaptionFont = new LOGFONT();
        this.lfMenuFont = new LOGFONT();
        this.lfStatusFont = new LOGFONT();
        this.lfMessageFont = new LOGFONT();
    }
    
    static {
        sizeof = OS.NONCLIENTMETRICS_sizeof();
    }
}
