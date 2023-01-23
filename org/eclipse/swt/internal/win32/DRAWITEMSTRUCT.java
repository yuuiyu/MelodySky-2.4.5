//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class DRAWITEMSTRUCT
{
    public int CtlType;
    public int CtlID;
    public int itemID;
    public int itemAction;
    public int itemState;
    public long hwndItem;
    public long hDC;
    public int left;
    public int top;
    public int bottom;
    public int right;
    public long itemData;
    public static final int sizeof;
    
    static {
        sizeof = OS.DRAWITEMSTRUCT_sizeof();
    }
}
