//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICookieManager2 extends nsICookieManager
{
    public static final String NS_ICOOKIEMANAGER2_IID = "{3e73ff5f-154e-494f-b640-3c654ba2cc2b}";
    
    void add(final String p0, final String p1, final String p2, final String p3, final boolean p4, final boolean p5, final long p6);
    
    boolean findMatchingCookie(final nsICookie2 p0, final long[] p1);
}
