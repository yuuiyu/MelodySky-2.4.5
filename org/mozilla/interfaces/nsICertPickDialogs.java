//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICertPickDialogs extends nsISupports
{
    public static final String NS_ICERTPICKDIALOGS_IID = "{51d59b08-1dd2-11b2-ad4a-a51b92f8a184}";
    
    void pickCertificate(final nsIInterfaceRequestor p0, final String[] p1, final String[] p2, final long p3, final int[] p4, final boolean[] p5);
}
