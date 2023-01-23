//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMLocation extends nsISupports
{
    public static final String NS_IDOMLOCATION_IID = "{a6cf906d-15b3-11d2-932e-00805f8add32}";
    
    String getHash();
    
    void setHash(final String p0);
    
    String getHost();
    
    void setHost(final String p0);
    
    String getHostname();
    
    void setHostname(final String p0);
    
    String getHref();
    
    void setHref(final String p0);
    
    String getPathname();
    
    void setPathname(final String p0);
    
    String getPort();
    
    void setPort(final String p0);
    
    String getProtocol();
    
    void setProtocol(final String p0);
    
    String getSearch();
    
    void setSearch(final String p0);
    
    void replace(final String p0);
    
    void assign(final String p0);
    
    String toString();
}
