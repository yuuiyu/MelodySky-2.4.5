//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWebBrowserChrome extends nsISupports
{
    public static final String NS_IWEBBROWSERCHROME_IID = "{ba434c60-9d52-11d3-afb0-00a024ffc08c}";
    public static final long STATUS_SCRIPT = 1L;
    public static final long STATUS_SCRIPT_DEFAULT = 2L;
    public static final long STATUS_LINK = 3L;
    public static final long CHROME_DEFAULT = 1L;
    public static final long CHROME_WINDOW_BORDERS = 2L;
    public static final long CHROME_WINDOW_CLOSE = 4L;
    public static final long CHROME_WINDOW_RESIZE = 8L;
    public static final long CHROME_MENUBAR = 16L;
    public static final long CHROME_TOOLBAR = 32L;
    public static final long CHROME_LOCATIONBAR = 64L;
    public static final long CHROME_STATUSBAR = 128L;
    public static final long CHROME_PERSONAL_TOOLBAR = 256L;
    public static final long CHROME_SCROLLBARS = 512L;
    public static final long CHROME_TITLEBAR = 1024L;
    public static final long CHROME_EXTRA = 2048L;
    public static final long CHROME_WITH_SIZE = 4096L;
    public static final long CHROME_WITH_POSITION = 8192L;
    public static final long CHROME_WINDOW_MIN = 16384L;
    public static final long CHROME_WINDOW_POPUP = 32768L;
    public static final long CHROME_WINDOW_RAISED = 33554432L;
    public static final long CHROME_WINDOW_LOWERED = 67108864L;
    public static final long CHROME_CENTER_SCREEN = 134217728L;
    public static final long CHROME_DEPENDENT = 268435456L;
    public static final long CHROME_MODAL = 536870912L;
    public static final long CHROME_OPENAS_DIALOG = 1073741824L;
    public static final long CHROME_OPENAS_CHROME = 2147483648L;
    public static final long CHROME_ALL = 4094L;
    
    void setStatus(final long p0, final String p1);
    
    nsIWebBrowser getWebBrowser();
    
    void setWebBrowser(final nsIWebBrowser p0);
    
    long getChromeFlags();
    
    void setChromeFlags(final long p0);
    
    void destroyBrowserWindow();
    
    void sizeBrowserTo(final int p0, final int p1);
    
    void showAsModal();
    
    boolean isWindowModal();
    
    void exitModalEventLoop(final long p0);
}
