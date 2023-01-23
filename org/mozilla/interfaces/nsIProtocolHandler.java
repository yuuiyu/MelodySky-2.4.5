//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIProtocolHandler extends nsISupports
{
    public static final String NS_IPROTOCOLHANDLER_IID = "{15fd6940-8ea7-11d3-93ad-00104ba0fd40}";
    public static final long URI_STD = 0L;
    public static final long URI_NORELATIVE = 1L;
    public static final long URI_NOAUTH = 2L;
    public static final long ALLOWS_PROXY = 4L;
    public static final long ALLOWS_PROXY_HTTP = 8L;
    
    String getScheme();
    
    int getDefaultPort();
    
    long getProtocolFlags();
    
    nsIURI newURI(final String p0, final String p1, final nsIURI p2);
    
    nsIChannel newChannel(final nsIURI p0);
    
    boolean allowPort(final int p0, final String p1);
}
