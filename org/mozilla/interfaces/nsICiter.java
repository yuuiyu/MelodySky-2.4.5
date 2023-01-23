//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICiter extends nsISupports
{
    public static final String NS_ICITER_IID = "{a6cf9102-15b3-11d2-932e-00805f8add32}";
    
    String getCiteString(final String p0);
    
    String stripCites(final String p0);
    
    String rewrap(final String p0, final long p1, final long p2, final boolean p3);
}
