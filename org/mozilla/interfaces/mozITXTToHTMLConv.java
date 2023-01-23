//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface mozITXTToHTMLConv extends nsIStreamConverter
{
    public static final String MOZITXTTOHTMLCONV_IID = "{77c0e42a-1dd2-11b2-8ebf-edc6606f2f4b}";
    public static final long kEntities = 0L;
    public static final long kURLs = 2L;
    public static final long kGlyphSubstitution = 4L;
    public static final long kStructPhrase = 8L;
    
    String scanTXT(final String p0, final long p1);
    
    String scanHTML(final String p0, final long p1);
    
    long citeLevelTXT(final String p0, final long[] p1);
    
    void findURLInPlaintext(final String p0, final int p1, final int p2, final int[] p3, final int[] p4);
}
