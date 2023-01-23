//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIURI extends nsISupports
{
    public static final String NS_IURI_IID = "{07a22cc0-0ce5-11d3-9331-00104ba0fd40}";
    
    String getSpec();
    
    void setSpec(final String p0);
    
    String getPrePath();
    
    String getScheme();
    
    void setScheme(final String p0);
    
    String getUserPass();
    
    void setUserPass(final String p0);
    
    String getUsername();
    
    void setUsername(final String p0);
    
    String getPassword();
    
    void setPassword(final String p0);
    
    String getHostPort();
    
    void setHostPort(final String p0);
    
    String getHost();
    
    void setHost(final String p0);
    
    int getPort();
    
    void setPort(final int p0);
    
    String getPath();
    
    void setPath(final String p0);
    
    boolean _equals(final nsIURI p0);
    
    boolean schemeIs(final String p0);
    
    nsIURI _clone();
    
    String resolve(final String p0);
    
    String getAsciiSpec();
    
    String getAsciiHost();
    
    String getOriginCharset();
}
