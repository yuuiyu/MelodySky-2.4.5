//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public final class SHDRAGIMAGE
{
    public SIZE sizeDragImage;
    public POINT ptOffset;
    public long hbmpDragImage;
    public int crColorKey;
    public static final int sizeof;
    
    public SHDRAGIMAGE() {
        this.sizeDragImage = new SIZE();
        this.ptOffset = new POINT();
    }
    
    static {
        sizeof = OS.SHDRAGIMAGE_sizeof();
    }
}
