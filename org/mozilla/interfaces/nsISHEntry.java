//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISHEntry extends nsIHistoryEntry
{
    public static final String NS_ISHENTRY_IID = "{542a98b9-2889-4922-aaf4-02b6056f4136}";
    
    void setURI(final nsIURI p0);
    
    nsIURI getReferrerURI();
    
    void setReferrerURI(final nsIURI p0);
    
    nsIContentViewer getContentViewer();
    
    void setContentViewer(final nsIContentViewer p0);
    
    boolean getSticky();
    
    void setSticky(final boolean p0);
    
    nsISupports getWindowState();
    
    void setWindowState(final nsISupports p0);
    
    void addChildShell(final nsIDocShellTreeItem p0);
    
    nsIDocShellTreeItem childShellAt(final int p0);
    
    void clearChildShells();
    
    nsISupportsArray getRefreshURIList();
    
    void setRefreshURIList(final nsISupportsArray p0);
    
    void syncPresentationState();
    
    void setTitle(final String p0);
    
    nsIInputStream getPostData();
    
    void setPostData(final nsIInputStream p0);
    
    nsISupports getLayoutHistoryState();
    
    void setLayoutHistoryState(final nsISupports p0);
    
    nsISHEntry getParent();
    
    void setParent(final nsISHEntry p0);
    
    long getLoadType();
    
    void setLoadType(final long p0);
    
    long getID();
    
    void setID(final long p0);
    
    long getPageIdentifier();
    
    void setPageIdentifier(final long p0);
    
    nsISupports getCacheKey();
    
    void setCacheKey(final nsISupports p0);
    
    boolean getSaveLayoutStateFlag();
    
    void setSaveLayoutStateFlag(final boolean p0);
    
    boolean getExpirationStatus();
    
    void setExpirationStatus(final boolean p0);
    
    String getContentType();
    
    void setContentType(final String p0);
    
    void setScrollPosition(final int p0, final int p1);
    
    void getScrollPosition(final int[] p0, final int[] p1);
    
    void create(final nsIURI p0, final String p1, final nsIInputStream p2, final nsISupports p3, final nsISupports p4, final String p5);
    
    nsISHEntry _clone();
    
    void setIsSubFrame(final boolean p0);
    
    nsIContentViewer getAnyContentViewer(final nsISHEntry[] p0);
}
