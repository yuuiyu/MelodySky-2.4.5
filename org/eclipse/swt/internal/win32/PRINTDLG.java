//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class PRINTDLG
{
    public int lStructSize;
    public long hwndOwner;
    public long hDevMode;
    public long hDevNames;
    public long hDC;
    public int Flags;
    public short nFromPage;
    public short nToPage;
    public short nMinPage;
    public short nMaxPage;
    public short nCopies;
    public long hInstance;
    public long lCustData;
    public long lpfnPrintHook;
    public long lpfnSetupHook;
    public long lpPrintTemplateName;
    public long lpSetupTemplateName;
    public long hPrintTemplate;
    public long hSetupTemplate;
    public static final int sizeof;
    
    static {
        sizeof = OS.PRINTDLG_sizeof();
    }
}
