//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIHttpChannelInternal extends nsISupports
{
    public static final String NS_IHTTPCHANNELINTERNAL_IID = "{f3764874-ed7e-4873-883c-11d67a4e3638}";
    
    nsIURI getDocumentURI();
    
    void setDocumentURI(final nsIURI p0);
    
    void getRequestVersion(final long[] p0, final long[] p1);
    
    void getResponseVersion(final long[] p0, final long[] p1);
    
    void setCookie(final String p0);
    
    nsIProxyInfo getProxyInfo();
}
