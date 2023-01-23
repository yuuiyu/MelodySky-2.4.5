//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIURIContentListener extends nsISupports
{
    public static final String NS_IURICONTENTLISTENER_IID = "{94928ab3-8b63-11d3-989d-001083010e9b}";
    
    boolean onStartURIOpen(final nsIURI p0);
    
    boolean doContent(final String p0, final boolean p1, final nsIRequest p2, final nsIStreamListener[] p3);
    
    boolean isPreferred(final String p0, final String[] p1);
    
    boolean canHandleContent(final String p0, final boolean p1, final String[] p2);
    
    nsISupports getLoadCookie();
    
    void setLoadCookie(final nsISupports p0);
    
    nsIURIContentListener getParentContentListener();
    
    void setParentContentListener(final nsIURIContentListener p0);
}
