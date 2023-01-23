//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class NMCUSTOMDRAW extends NMHDR
{
    public int dwDrawStage;
    public long hdc;
    public int left;
    public int top;
    public int right;
    public int bottom;
    public long dwItemSpec;
    public int uItemState;
    public long lItemlParam;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMCUSTOMDRAW_sizeof();
    }
}
