//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMNSHTMLAnchorElement extends nsISupports
{
    public static final String NS_IDOMNSHTMLANCHORELEMENT_IID = "{a6cf911c-15b3-11d2-932e-00805f8add32}";
    
    String getProtocol();
    
    void setProtocol(final String p0);
    
    String getHost();
    
    void setHost(final String p0);
    
    String getHostname();
    
    void setHostname(final String p0);
    
    String getPathname();
    
    void setPathname(final String p0);
    
    String getSearch();
    
    void setSearch(final String p0);
    
    String getPort();
    
    void setPort(final String p0);
    
    String getHash();
    
    void setHash(final String p0);
    
    String getText();
    
    String toString();
}
