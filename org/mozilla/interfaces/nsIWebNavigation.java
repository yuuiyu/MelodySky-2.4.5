//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWebNavigation extends nsISupports
{
    public static final String NS_IWEBNAVIGATION_IID = "{f5d9e7b0-d930-11d3-b057-00a024ffc08c}";
    public static final long LOAD_FLAGS_MASK = 65535L;
    public static final long LOAD_FLAGS_NONE = 0L;
    public static final long LOAD_FLAGS_IS_REFRESH = 16L;
    public static final long LOAD_FLAGS_IS_LINK = 32L;
    public static final long LOAD_FLAGS_BYPASS_HISTORY = 64L;
    public static final long LOAD_FLAGS_REPLACE_HISTORY = 128L;
    public static final long LOAD_FLAGS_BYPASS_CACHE = 256L;
    public static final long LOAD_FLAGS_BYPASS_PROXY = 512L;
    public static final long LOAD_FLAGS_CHARSET_CHANGE = 1024L;
    public static final long LOAD_FLAGS_STOP_CONTENT = 2048L;
    public static final long LOAD_FLAGS_FROM_EXTERNAL = 4096L;
    public static final long LOAD_FLAGS_ALLOW_THIRD_PARTY_FIXUP = 8192L;
    public static final long LOAD_FLAGS_FIRST_LOAD = 16384L;
    public static final long STOP_NETWORK = 1L;
    public static final long STOP_CONTENT = 2L;
    public static final long STOP_ALL = 3L;
    
    boolean getCanGoBack();
    
    boolean getCanGoForward();
    
    void goBack();
    
    void goForward();
    
    void gotoIndex(final int p0);
    
    void loadURI(final String p0, final long p1, final nsIURI p2, final nsIInputStream p3, final nsIInputStream p4);
    
    void reload(final long p0);
    
    void stop(final long p0);
    
    nsIDOMDocument getDocument();
    
    nsIURI getCurrentURI();
    
    nsIURI getReferringURI();
    
    nsISHistory getSessionHistory();
    
    void setSessionHistory(final nsISHistory p0);
}
