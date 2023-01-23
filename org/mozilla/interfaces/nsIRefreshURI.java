//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRefreshURI extends nsISupports
{
    public static final String NS_IREFRESHURI_IID = "{69efc430-2efe-11d2-9e5d-006008bf092e}";
    
    void refreshURI(final nsIURI p0, final int p1, final boolean p2, final boolean p3);
    
    void setupRefreshURI(final nsIChannel p0);
    
    void setupRefreshURIFromHeader(final nsIURI p0, final String p1);
    
    void cancelRefreshURITimers();
}
