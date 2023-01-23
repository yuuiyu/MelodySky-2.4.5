//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class REBARBANDINFO
{
    public int cbSize;
    public int fMask;
    public int fStyle;
    public int clrFore;
    public int clrBack;
    public long lpText;
    public int cch;
    public int iImage;
    public long hwndChild;
    public int cxMinChild;
    public int cyMinChild;
    public int cx;
    public long hbmBack;
    public int wID;
    public int cyChild;
    public int cyMaxChild;
    public int cyIntegral;
    public int cxIdeal;
    public long lParam;
    public int cxHeader;
    public static final int sizeof;
    
    static {
        sizeof = OS.REBARBANDINFO_sizeof();
    }
}
