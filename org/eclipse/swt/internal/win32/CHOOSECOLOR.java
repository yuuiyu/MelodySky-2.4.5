//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class CHOOSECOLOR
{
    public int lStructSize;
    public long hwndOwner;
    public long hInstance;
    public int rgbResult;
    public long lpCustColors;
    public int Flags;
    public long lCustData;
    public long lpfnHook;
    public long lpTemplateName;
    public static final int sizeof;
    
    static {
        sizeof = OS.CHOOSECOLOR_sizeof();
    }
}
