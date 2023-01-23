//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITextToSubURI extends nsISupports
{
    public static final String NS_ITEXTTOSUBURI_IID = "{8b042e24-6f87-11d3-b3c8-00805f8a6670}";
    
    String convertAndEscape(final String p0, final String p1);
    
    String unEscapeAndConvert(final String p0, final String p1);
    
    String unEscapeURIForUI(final String p0, final String p1);
    
    String unEscapeNonAsciiURI(final String p0, final String p1);
}
