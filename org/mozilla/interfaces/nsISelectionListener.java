//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISelectionListener extends nsISupports
{
    public static final String NS_ISELECTIONLISTENER_IID = "{a6cf90e2-15b3-11d2-932e-00805f8add32}";
    public static final short NO_REASON = 0;
    public static final short DRAG_REASON = 1;
    public static final short MOUSEDOWN_REASON = 2;
    public static final short MOUSEUP_REASON = 4;
    public static final short KEYPRESS_REASON = 8;
    public static final short SELECTALL_REASON = 16;
    
    void notifySelectionChanged(final nsIDOMDocument p0, final nsISelection p1, final short p2);
}
