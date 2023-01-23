//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.gdip;

public class ColorPalette
{
    public int Flags;
    public int Count;
    public int[] Entries;
    public static final int sizeof;
    
    public ColorPalette() {
        this.Entries = new int[1];
    }
    
    static {
        sizeof = Gdip.ColorPalette_sizeof();
    }
}
