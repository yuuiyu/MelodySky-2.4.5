//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class CHOOSEFONT
{
    public int lStructSize;
    public long hwndOwner;
    public long hDC;
    public long lpLogFont;
    public int iPointSize;
    public int Flags;
    public int rgbColors;
    public long lCustData;
    public long lpfnHook;
    public long lpTemplateName;
    public long hInstance;
    public long lpszStyle;
    public short nFontType;
    public int nSizeMin;
    public int nSizeMax;
    public static final int sizeof;
    
    static {
        sizeof = OS.CHOOSEFONT_sizeof();
    }
}
