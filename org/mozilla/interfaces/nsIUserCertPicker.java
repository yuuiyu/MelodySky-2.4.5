//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIUserCertPicker extends nsISupports
{
    public static final String NS_IUSERCERTPICKER_IID = "{06d018e0-d41b-4629-a4fc-daaa6029888e}";
    
    nsIX509Cert pickByUsage(final nsIInterfaceRequestor p0, final String p1, final int p2, final boolean p3, final boolean p4, final boolean[] p5);
}
