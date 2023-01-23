//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISAXContentHandler extends nsISupports
{
    public static final String NS_ISAXCONTENTHANDLER_IID = "{2a99c757-dfee-4806-bff3-f721440412e0}";
    
    void startDocument();
    
    void endDocument();
    
    void startElement(final String p0, final String p1, final String p2, final nsISAXAttributes p3);
    
    void endElement(final String p0, final String p1, final String p2);
    
    void characters(final String p0);
    
    void processingInstruction(final String p0, final String p1);
    
    void ignorableWhitespace(final String p0);
    
    void startPrefixMapping(final String p0, final String p1);
    
    void endPrefixMapping(final String p0);
}
