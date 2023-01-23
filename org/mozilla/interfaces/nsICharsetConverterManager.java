//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICharsetConverterManager extends nsISupports
{
    public static final String NS_ICHARSETCONVERTERMANAGER_IID = "{f5323a76-c8f7-4c65-8d0c-1250e969c7d5}";
    
    nsISupports getUnicodeDecoder(final String p0);
    
    nsISupports getUnicodeDecoderRaw(final String p0);
    
    nsISupports getUnicodeEncoder(final String p0);
    
    nsISupports getUnicodeEncoderRaw(final String p0);
    
    String getCharsetAlias(final String p0);
    
    nsIUTF8StringEnumerator getDecoderList();
    
    nsIUTF8StringEnumerator getEncoderList();
    
    nsIUTF8StringEnumerator getCharsetDetectorList();
    
    String getCharsetTitle(final String p0);
    
    String getCharsetData(final String p0, final String p1);
    
    nsIAtom getCharsetLangGroup(final String p0);
    
    nsIAtom getCharsetLangGroupRaw(final String p0);
}
