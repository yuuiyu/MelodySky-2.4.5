//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class PAINTSTRUCT
{
    public long hdc;
    public boolean fErase;
    public int left;
    public int top;
    public int right;
    public int bottom;
    public boolean fRestore;
    public boolean fIncUpdate;
    public byte[] rgbReserved;
    public static final int sizeof;
    
    public PAINTSTRUCT() {
        this.rgbReserved = new byte[32];
    }
    
    static {
        sizeof = OS.PAINTSTRUCT_sizeof();
    }
}
