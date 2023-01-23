//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXmlRpcClient extends nsISupports
{
    public static final String NS_IXMLRPCCLIENT_IID = "{4d7d15c0-3747-4f7f-b6b3-792a5ea1a9aa}";
    public static final long INT = 1L;
    public static final long BOOLEAN = 2L;
    public static final long STRING = 3L;
    public static final long DOUBLE = 4L;
    public static final long DATETIME = 5L;
    public static final long ARRAY = 6L;
    public static final long STRUCT = 7L;
    
    void init(final String p0);
    
    void setAuthentication(final String p0, final String p1);
    
    void clearAuthentication(final String p0, final String p1);
    
    void setEncoding(final String p0);
    
    nsIURL getServerURL();
    
    void asyncCall(final nsIXmlRpcClientListener p0, final nsISupports p1, final String p2, final nsISupports[] p3, final long p4);
    
    boolean getInProgress();
    
    nsIXmlRpcFault getFault();
    
    nsISupports getResult();
    
    long getResponseStatus();
    
    long getResponseString();
    
    nsISupports createType(final long p0, final String[] p1);
}
