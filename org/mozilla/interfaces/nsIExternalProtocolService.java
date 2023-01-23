//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIExternalProtocolService extends nsISupports
{
    public static final String NS_IEXTERNALPROTOCOLSERVICE_IID = "{a49813a4-98b7-4bdb-998c-8bd9704af0c0}";
    
    boolean externalProtocolHandlerExists(final String p0);
    
    boolean isExposedProtocol(final String p0);
    
    void loadUrl(final nsIURI p0);
    
    void loadURI(final nsIURI p0, final nsIPrompt p1);
    
    String getApplicationDescription(final String p0);
}
