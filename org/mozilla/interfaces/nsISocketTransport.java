//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISocketTransport extends nsITransport
{
    public static final String NS_ISOCKETTRANSPORT_IID = "{66418cc8-5f5d-4f52-a7f9-db8fb3b2cfe6}";
    public static final long TIMEOUT_CONNECT = 0L;
    public static final long TIMEOUT_READ_WRITE = 1L;
    public static final long STATUS_RESOLVING = 2152398851L;
    public static final long STATUS_CONNECTING_TO = 2152398855L;
    public static final long STATUS_CONNECTED_TO = 2152398852L;
    public static final long STATUS_SENDING_TO = 2152398853L;
    public static final long STATUS_WAITING_FOR = 2152398858L;
    public static final long STATUS_RECEIVING_FROM = 2152398854L;
    
    String getHost();
    
    int getPort();
    
    nsISupports getSecurityInfo();
    
    nsIInterfaceRequestor getSecurityCallbacks();
    
    void setSecurityCallbacks(final nsIInterfaceRequestor p0);
    
    boolean isAlive();
    
    long getTimeout(final long p0);
    
    void setTimeout(final long p0, final long p1);
}
