//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIIOService extends nsISupports
{
    public static final String NS_IIOSERVICE_IID = "{bddeda3f-9020-4d12-8c70-984ee9f7935e}";
    
    nsIProtocolHandler getProtocolHandler(final String p0);
    
    long getProtocolFlags(final String p0);
    
    nsIURI newURI(final String p0, final String p1, final nsIURI p2);
    
    nsIURI newFileURI(final nsIFile p0);
    
    nsIChannel newChannelFromURI(final nsIURI p0);
    
    nsIChannel newChannel(final String p0, final String p1, final nsIURI p2);
    
    boolean getOffline();
    
    void setOffline(final boolean p0);
    
    boolean allowPort(final int p0, final String p1);
    
    String extractScheme(final String p0);
}
