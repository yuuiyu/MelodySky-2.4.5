//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMHTMLDocument extends nsIDOMDocument
{
    public static final String NS_IDOMHTMLDOCUMENT_IID = "{a6cf9084-15b3-11d2-932e-00805f8add32}";
    
    String getTitle();
    
    void setTitle(final String p0);
    
    String getReferrer();
    
    String getURL();
    
    nsIDOMHTMLElement getBody();
    
    void setBody(final nsIDOMHTMLElement p0);
    
    nsIDOMHTMLCollection getImages();
    
    nsIDOMHTMLCollection getApplets();
    
    nsIDOMHTMLCollection getLinks();
    
    nsIDOMHTMLCollection getForms();
    
    nsIDOMHTMLCollection getAnchors();
    
    String getCookie();
    
    void setCookie(final String p0);
    
    void close();
    
    nsIDOMNodeList getElementsByName(final String p0);
}
