//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDocShell extends nsISupports
{
    public static final String NS_IDOCSHELL_IID = "{9f0c7461-b9a4-47f6-b88c-421dce1bce66}";
    public static final int INTERNAL_LOAD_FLAGS_NONE = 0;
    public static final int INTERNAL_LOAD_FLAGS_INHERIT_OWNER = 1;
    public static final int INTERNAL_LOAD_FLAGS_DONT_SEND_REFERRER = 2;
    public static final int INTERNAL_LOAD_FLAGS_ALLOW_THIRD_PARTY_FIXUP = 4;
    public static final int INTERNAL_LOAD_FLAGS_FIRST_LOAD = 8;
    public static final int ENUMERATE_FORWARDS = 0;
    public static final int ENUMERATE_BACKWARDS = 1;
    public static final long APP_TYPE_UNKNOWN = 0L;
    public static final long APP_TYPE_MAIL = 1L;
    public static final long APP_TYPE_EDITOR = 2L;
    public static final long BUSY_FLAGS_NONE = 0L;
    public static final long BUSY_FLAGS_BUSY = 1L;
    public static final long BUSY_FLAGS_BEFORE_PAGE_LOAD = 2L;
    public static final long BUSY_FLAGS_PAGE_LOADING = 4L;
    public static final long LOAD_CMD_NORMAL = 1L;
    public static final long LOAD_CMD_RELOAD = 2L;
    public static final long LOAD_CMD_HISTORY = 4L;
    
    void createLoadInfo(final nsIDocShellLoadInfo[] p0);
    
    void prepareForNewContentModel();
    
    void setCurrentURI(final nsIURI p0);
    
    nsIContentViewer getContentViewer();
    
    nsIChromeEventHandler getChromeEventHandler();
    
    void setChromeEventHandler(final nsIChromeEventHandler p0);
    
    nsIDocumentCharsetInfo getDocumentCharsetInfo();
    
    void setDocumentCharsetInfo(final nsIDocumentCharsetInfo p0);
    
    boolean getAllowPlugins();
    
    void setAllowPlugins(final boolean p0);
    
    boolean getAllowJavascript();
    
    void setAllowJavascript(final boolean p0);
    
    boolean getAllowMetaRedirects();
    
    void setAllowMetaRedirects(final boolean p0);
    
    boolean getAllowSubframes();
    
    void setAllowSubframes(final boolean p0);
    
    boolean getAllowImages();
    
    void setAllowImages(final boolean p0);
    
    nsISimpleEnumerator getDocShellEnumerator(final int p0, final int p1);
    
    long getAppType();
    
    void setAppType(final long p0);
    
    boolean getAllowAuth();
    
    void setAllowAuth(final boolean p0);
    
    float getZoom();
    
    void setZoom(final float p0);
    
    int getMarginWidth();
    
    void setMarginWidth(final int p0);
    
    int getMarginHeight();
    
    void setMarginHeight(final int p0);
    
    boolean getHasFocus();
    
    void setHasFocus(final boolean p0);
    
    boolean getCanvasHasFocus();
    
    void setCanvasHasFocus(final boolean p0);
    
    void tabToTreeOwner(final boolean p0, final boolean[] p1);
    
    long getBusyFlags();
    
    long getLoadType();
    
    void setLoadType(final long p0);
    
    boolean isBeingDestroyed();
    
    boolean getIsExecutingOnLoadHandler();
    
    nsISupports getLayoutHistoryState();
    
    void setLayoutHistoryState(final nsISupports p0);
    
    boolean getShouldSaveLayoutState();
    
    nsISecureBrowserUI getSecurityUI();
    
    void setSecurityUI(final nsISecureBrowserUI p0);
    
    void suspendRefreshURIs();
    
    void resumeRefreshURIs();
    
    void beginRestore(final nsIContentViewer p0, final boolean p1);
    
    void finishRestore();
    
    boolean getRestoringDocument();
    
    boolean getUseErrorPages();
    
    void setUseErrorPages(final boolean p0);
    
    int getPreviousTransIndex();
    
    int getLoadedTransIndex();
    
    void historyPurged(final int p0);
}
