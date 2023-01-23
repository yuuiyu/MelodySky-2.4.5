//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXMLHttpRequest extends nsISupports
{
    public static final String NS_IXMLHTTPREQUEST_IID = "{7b3b3c62-9d53-4abc-bc89-c33ce78f439f}";
    
    nsIChannel getChannel();
    
    nsIDOMDocument getResponseXML();
    
    String getResponseText();
    
    long getStatus();
    
    String getStatusText();
    
    void abort();
    
    String getAllResponseHeaders();
    
    String getResponseHeader(final String p0);
    
    void open(final String p0, final String p1);
    
    void send(final nsIVariant p0);
    
    void setRequestHeader(final String p0, final String p1);
    
    int getReadyState();
    
    void overrideMimeType(final String p0);
    
    boolean getMultipart();
    
    void setMultipart(final boolean p0);
}
