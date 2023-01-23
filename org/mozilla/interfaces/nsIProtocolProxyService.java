//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIProtocolProxyService extends nsISupports
{
    public static final String NS_IPROTOCOLPROXYSERVICE_IID = "{e38ab577-786e-4a7f-936b-7ae4c7d877b2}";
    public static final long RESOLVE_NON_BLOCKING = 1L;
    
    nsIProxyInfo resolve(final nsIURI p0, final long p1);
    
    nsICancelable asyncResolve(final nsIURI p0, final long p1, final nsIProtocolProxyCallback p2);
    
    nsIProxyInfo newProxyInfo(final String p0, final String p1, final int p2, final long p3, final long p4, final nsIProxyInfo p5);
    
    nsIProxyInfo getFailoverForProxy(final nsIProxyInfo p0, final nsIURI p1, final long p2);
    
    void registerFilter(final nsIProtocolProxyFilter p0, final long p1);
    
    void unregisterFilter(final nsIProtocolProxyFilter p0);
}
