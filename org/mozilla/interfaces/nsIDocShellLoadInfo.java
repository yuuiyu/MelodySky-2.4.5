//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDocShellLoadInfo extends nsISupports
{
    public static final String NS_IDOCSHELLLOADINFO_IID = "{4f813a88-7aca-4607-9896-d97270cdf15e}";
    public static final int loadNormal = 0;
    public static final int loadNormalReplace = 1;
    public static final int loadHistory = 2;
    public static final int loadReloadNormal = 3;
    public static final int loadReloadBypassCache = 4;
    public static final int loadReloadBypassProxy = 5;
    public static final int loadReloadBypassProxyAndCache = 6;
    public static final int loadLink = 7;
    public static final int loadRefresh = 8;
    public static final int loadReloadCharsetChange = 9;
    public static final int loadBypassHistory = 10;
    public static final int loadStopContent = 11;
    public static final int loadStopContentAndReplace = 12;
    public static final int loadNormalExternal = 13;
    
    nsIURI getReferrer();
    
    void setReferrer(final nsIURI p0);
    
    nsISupports getOwner();
    
    void setOwner(final nsISupports p0);
    
    boolean getInheritOwner();
    
    void setInheritOwner(final boolean p0);
    
    int getLoadType();
    
    void setLoadType(final int p0);
    
    nsISHEntry getSHEntry();
    
    void setSHEntry(final nsISHEntry p0);
    
    String getTarget();
    
    void setTarget(final String p0);
    
    nsIInputStream getPostDataStream();
    
    void setPostDataStream(final nsIInputStream p0);
    
    nsIInputStream getHeadersStream();
    
    void setHeadersStream(final nsIInputStream p0);
    
    boolean getSendReferrer();
    
    void setSendReferrer(final boolean p0);
}
