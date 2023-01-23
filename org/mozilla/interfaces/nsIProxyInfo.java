//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIProxyInfo extends nsISupports
{
    public static final String NS_IPROXYINFO_IID = "{3fe9308b-1608-4fa0-933c-c5ec2c6175fd}";
    public static final int TRANSPARENT_PROXY_RESOLVES_HOST = 1;
    
    String getHost();
    
    int getPort();
    
    String getType();
    
    long getFlags();
    
    long getFailoverTimeout();
    
    nsIProxyInfo getFailoverProxy();
    
    void setFailoverProxy(final nsIProxyInfo p0);
}
