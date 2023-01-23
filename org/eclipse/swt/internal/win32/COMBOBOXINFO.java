//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class COMBOBOXINFO
{
    public int cbSize;
    public int itemLeft;
    public int itemTop;
    public int itemRight;
    public int itemBottom;
    public int buttonLeft;
    public int buttonTop;
    public int buttonRight;
    public int buttonBottom;
    public int stateButton;
    public long hwndCombo;
    public long hwndItem;
    public long hwndList;
    public static final int sizeof;
    
    static {
        sizeof = OS.COMBOBOXINFO_sizeof();
    }
}
