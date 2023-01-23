//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIUTF8ConverterService extends nsISupports
{
    public static final String NS_IUTF8CONVERTERSERVICE_IID = "{249f52a3-2599-4b00-ba40-0481364831a2}";
    
    String convertStringToUTF8(final String p0, final String p1, final boolean p2);
    
    String convertURISpecToUTF8(final String p0, final String p1);
}
