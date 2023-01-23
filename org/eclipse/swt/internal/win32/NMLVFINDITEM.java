//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class NMLVFINDITEM extends NMHDR
{
    public int iStart;
    public int flags;
    public long psz;
    public long lParam;
    public int x;
    public int y;
    public int vkDirection;
    public static final int sizeof;
    
    static {
        sizeof = OS.NMLVFINDITEM_sizeof();
    }
}
