//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIClientAuthDialogs extends nsISupports
{
    public static final String NS_ICLIENTAUTHDIALOGS_IID = "{fa4c7520-1433-11d5-ba24-00108303b117}";
    
    void chooseCertificate(final nsIInterfaceRequestor p0, final String p1, final String p2, final String p3, final String[] p4, final String[] p5, final long p6, final int[] p7, final boolean[] p8);
}
