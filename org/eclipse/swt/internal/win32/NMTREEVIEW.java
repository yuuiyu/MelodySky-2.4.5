//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class NMTREEVIEW
{
    public NMHDR hdr;
    public int action;
    public TVITEM itemOld;
    public TVITEM itemNew;
    public POINT ptDrag;
    public static final int sizeof;
    
    public NMTREEVIEW() {
        this.hdr = new NMHDR();
        this.itemOld = new TVITEM();
        this.itemNew = new TVITEM();
        this.ptDrag = new POINT();
    }
    
    static {
        sizeof = OS.NMTREEVIEW_sizeof();
    }
}
