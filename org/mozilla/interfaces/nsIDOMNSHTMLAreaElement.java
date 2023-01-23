//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMNSHTMLAreaElement extends nsISupports
{
    public static final String NS_IDOMNSHTMLAREAELEMENT_IID = "{3dce9071-f3b9-4280-a6ee-776cdfe3dd9e}";
    
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
    
    String toString();
}
