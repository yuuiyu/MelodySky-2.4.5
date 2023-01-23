//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICookieService extends nsISupports
{
    public static final String NS_ICOOKIESERVICE_IID = "{011c3190-1434-11d6-a618-0010a401eb10}";
    
    String getCookieString(final nsIURI p0, final nsIChannel p1);
    
    String getCookieStringFromHttp(final nsIURI p0, final nsIURI p1, final nsIChannel p2);
    
    void setCookieString(final nsIURI p0, final nsIPrompt p1, final String p2, final nsIChannel p3);
    
    void setCookieStringFromHttp(final nsIURI p0, final nsIURI p1, final nsIPrompt p2, final String p3, final String p4, final nsIChannel p5);
    
    boolean getCookieIconIsVisible();
}
