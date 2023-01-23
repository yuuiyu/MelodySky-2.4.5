//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFontEnumerator extends nsISupports
{
    public static final String NS_IFONTENUMERATOR_IID = "{a6cf9114-15b3-11d2-932e-00805f8add32}";
    
    String[] enumerateAllFonts(final long[] p0);
    
    String[] enumerateFonts(final String p0, final String p1, final long[] p2);
    
    boolean haveFontFor(final String p0);
    
    String getDefaultFont(final String p0, final String p1);
    
    boolean updateFontList();
}
