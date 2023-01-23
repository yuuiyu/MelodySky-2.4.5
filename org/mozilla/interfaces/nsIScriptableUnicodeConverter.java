//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIScriptableUnicodeConverter extends nsISupports
{
    public static final String NS_ISCRIPTABLEUNICODECONVERTER_IID = "{1ea19c6c-c59f-4fd7-9fc7-151e946baca0}";
    
    String convertFromUnicode(final String p0);
    
    String finish();
    
    String convertToUnicode(final String p0);
    
    String convertFromByteArray(final byte[] p0, final long p1);
    
    byte[] convertToByteArray(final String p0, final long[] p1);
    
    nsIInputStream convertToInputStream(final String p0);
    
    String getCharset();
    
    void setCharset(final String p0);
}
