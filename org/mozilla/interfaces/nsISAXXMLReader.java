//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISAXXMLReader extends nsIStreamListener
{
    public static final String NS_ISAXXMLREADER_IID = "{5556997e-d816-4218-8b54-803d4261206e}";
    
    nsIURI getBaseURI();
    
    void setBaseURI(final nsIURI p0);
    
    nsISAXContentHandler getContentHandler();
    
    void setContentHandler(final nsISAXContentHandler p0);
    
    nsISAXDTDHandler getDtdHandler();
    
    void setDtdHandler(final nsISAXDTDHandler p0);
    
    nsISAXErrorHandler getErrorHandler();
    
    void setErrorHandler(final nsISAXErrorHandler p0);
    
    nsISAXLexicalHandler getLexicalHandler();
    
    void setLexicalHandler(final nsISAXLexicalHandler p0);
    
    void setFeature(final String p0, final boolean p1);
    
    boolean getFeature(final String p0);
    
    void setProperty(final String p0, final nsISupports p1);
    
    boolean getProperty(final String p0);
    
    void parseFromString(final String p0, final String p1);
    
    void parseFromStream(final nsIInputStream p0, final String p1, final String p2);
    
    void parseAsync(final nsIRequestObserver p0);
}
