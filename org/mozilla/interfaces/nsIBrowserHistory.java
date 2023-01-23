//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIBrowserHistory extends nsIGlobalHistory2
{
    public static final String NS_IBROWSERHISTORY_IID = "{c43079c3-3d8d-4b7c-af14-0e30ab46865f}";
    
    void addPageWithDetails(final nsIURI p0, final String p1, final long p2);
    
    String getLastPageVisited();
    
    long getCount();
    
    void removePage(final nsIURI p0);
    
    void removePagesFromHost(final String p0, final boolean p1);
    
    void removeAllPages();
    
    void hidePage(final nsIURI p0);
    
    void markPageAsTyped(final nsIURI p0);
}
