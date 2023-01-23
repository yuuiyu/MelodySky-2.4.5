//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISSLSocketControl extends nsISupports
{
    public static final String NS_ISSLSOCKETCONTROL_IID = "{8b3e8488-1dd2-11b2-b547-956290be347c}";
    
    nsIInterfaceRequestor getNotificationCallbacks();
    
    void setNotificationCallbacks(final nsIInterfaceRequestor p0);
    
    boolean getForceHandshake();
    
    void setForceHandshake(final boolean p0);
    
    void proxyStartSSL();
    
    void startTLS();
}
