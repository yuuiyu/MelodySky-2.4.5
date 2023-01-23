//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIChannel extends nsIRequest
{
    public static final String NS_ICHANNEL_IID = "{c63a055a-a676-4e71-bf3c-6cfa11082018}";
    public static final long LOAD_DOCUMENT_URI = 65536L;
    public static final long LOAD_RETARGETED_DOCUMENT_URI = 131072L;
    public static final long LOAD_REPLACE = 262144L;
    public static final long LOAD_INITIAL_DOCUMENT_URI = 524288L;
    public static final long LOAD_TARGETED = 1048576L;
    public static final long LOAD_CALL_CONTENT_SNIFFERS = 2097152L;
    
    nsIURI getOriginalURI();
    
    void setOriginalURI(final nsIURI p0);
    
    nsIURI getURI();
    
    nsISupports getOwner();
    
    void setOwner(final nsISupports p0);
    
    nsIInterfaceRequestor getNotificationCallbacks();
    
    void setNotificationCallbacks(final nsIInterfaceRequestor p0);
    
    nsISupports getSecurityInfo();
    
    String getContentType();
    
    void setContentType(final String p0);
    
    String getContentCharset();
    
    void setContentCharset(final String p0);
    
    int getContentLength();
    
    void setContentLength(final int p0);
    
    nsIInputStream open();
    
    void asyncOpen(final nsIStreamListener p0, final nsISupports p1);
}
