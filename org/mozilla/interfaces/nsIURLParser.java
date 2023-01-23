//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIURLParser extends nsISupports
{
    public static final String NS_IURLPARSER_IID = "{7281076d-cf37-464a-815e-698235802604}";
    
    void parseURL(final String p0, final int p1, final long[] p2, final int[] p3, final long[] p4, final int[] p5, final long[] p6, final int[] p7);
    
    void parseAuthority(final String p0, final int p1, final long[] p2, final int[] p3, final long[] p4, final int[] p5, final long[] p6, final int[] p7, final int[] p8);
    
    void parseUserInfo(final String p0, final int p1, final long[] p2, final int[] p3, final long[] p4, final int[] p5);
    
    void parseServerInfo(final String p0, final int p1, final long[] p2, final int[] p3, final int[] p4);
    
    void parsePath(final String p0, final int p1, final long[] p2, final int[] p3, final long[] p4, final int[] p5, final long[] p6, final int[] p7, final long[] p8, final int[] p9);
    
    void parseFilePath(final String p0, final int p1, final long[] p2, final int[] p3, final long[] p4, final int[] p5, final long[] p6, final int[] p7);
    
    void parseFileName(final String p0, final int p1, final long[] p2, final int[] p3, final long[] p4, final int[] p5);
}
