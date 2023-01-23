//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIUnicodeNormalizer extends nsISupports
{
    public static final String NS_IUNICODENORMALIZER_IID = "{b43a461f-1bcf-4329-820b-66e48c979e14}";
    
    void normalizeUnicodeNFD(final String p0, final String[] p1);
    
    void normalizeUnicodeNFC(final String p0, final String[] p1);
    
    void normalizeUnicodeNFKD(final String p0, final String[] p1);
    
    void normalizeUnicodeNFKC(final String p0, final String[] p1);
}
