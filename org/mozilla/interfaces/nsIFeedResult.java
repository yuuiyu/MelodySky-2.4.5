//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFeedResult extends nsISupports
{
    public static final String NS_IFEEDRESULT_IID = "{7a180b78-0f46-4569-8c22-f3d720ea1c57}";
    
    boolean getBozo();
    
    void setBozo(final boolean p0);
    
    nsIFeedContainer getDoc();
    
    void setDoc(final nsIFeedContainer p0);
    
    nsIURI getUri();
    
    void setUri(final nsIURI p0);
    
    String getVersion();
    
    void setVersion(final String p0);
    
    nsIURI getStylesheet();
    
    void setStylesheet(final nsIURI p0);
    
    nsIProperties getHeaders();
    
    void setHeaders(final nsIProperties p0);
    
    void registerExtensionPrefix(final String p0, final String p1);
}
