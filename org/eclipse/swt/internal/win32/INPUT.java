//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class INPUT
{
    public int type;
    public KEYBDINPUT ki;
    public MOUSEINPUT mi;
    public static final int sizeof;
    
    static {
        sizeof = OS.INPUT_sizeof();
    }
}
