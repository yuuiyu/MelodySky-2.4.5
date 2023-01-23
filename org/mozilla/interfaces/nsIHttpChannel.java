//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIHttpChannel extends nsIChannel
{
    public static final String NS_IHTTPCHANNEL_IID = "{9277fe09-f0cc-4cd9-bbce-581dd94b0260}";
    
    String getRequestMethod();
    
    void setRequestMethod(final String p0);
    
    nsIURI getReferrer();
    
    void setReferrer(final nsIURI p0);
    
    String getRequestHeader(final String p0);
    
    void setRequestHeader(final String p0, final String p1, final boolean p2);
    
    void visitRequestHeaders(final nsIHttpHeaderVisitor p0);
    
    boolean getAllowPipelining();
    
    void setAllowPipelining(final boolean p0);
    
    long getRedirectionLimit();
    
    void setRedirectionLimit(final long p0);
    
    long getResponseStatus();
    
    String getResponseStatusText();
    
    boolean getRequestSucceeded();
    
    String getResponseHeader(final String p0);
    
    void setResponseHeader(final String p0, final String p1, final boolean p2);
    
    void visitResponseHeaders(final nsIHttpHeaderVisitor p0);
    
    boolean isNoStoreResponse();
    
    boolean isNoCacheResponse();
}
