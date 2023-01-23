//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class SCRIPT_ITEM
{
    public int iCharPos;
    public SCRIPT_ANALYSIS a;
    public static final int sizeof;
    
    public SCRIPT_ITEM() {
        this.a = new SCRIPT_ANALYSIS();
    }
    
    static {
        sizeof = OS.SCRIPT_ITEM_sizeof();
    }
}
