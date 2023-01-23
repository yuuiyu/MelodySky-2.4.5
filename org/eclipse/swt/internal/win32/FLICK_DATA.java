//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class FLICK_DATA
{
    public int iFlickActionCommandCode;
    public byte iFlickDirection;
    public boolean fControlModifier;
    public boolean fMenuModifier;
    public boolean fAltGRModifier;
    public boolean fWinModifier;
    public boolean fShiftModifier;
    public int iReserved;
    public boolean fOnInkingSurface;
    public int iActionArgument;
    public static final int sizeof;
    
    static {
        sizeof = OS.FLICK_DATA_sizeof();
    }
}
