//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.internal;

import chrriis.dj.nativeswing.swtimpl.components.*;
import java.util.*;
import java.awt.*;

public interface INativeWebBrowser
{
    public static final String BROWSER_FOCUS_FUNCTION = "nsBrowserFocus";
    public static final String CONSOLE_OUT_FUNCTION = "nsConsoleOut";
    public static final String CONSOLE_ERR_FUNCTION = "nsConsoleErr";
    public static final String COMMAND_FUNCTION = "sendNSCommand";
    public static final String COMMAND_LOCATION_PREFIX = "command://";
    public static final String COMMAND_STATUS_PREFIX = "scommand://";
    
    WebBrowserRuntime getRuntime();
    
    String getResourceLocation();
    
    boolean navigate(final String p0, final WebBrowserNavigationParameters p1);
    
    String getHTMLContent();
    
    boolean setHTMLContent(final String p0);
    
    boolean isJavascriptEnabled();
    
    void setJavascriptEnabled(final boolean p0);
    
    boolean executeJavascriptAndWait(final String p0);
    
    void executeJavascript(final String p0);
    
    Object executeJavascriptWithResult(final String p0);
    
    void stopLoading();
    
    void reloadPage();
    
    boolean isBackNavigationEnabled();
    
    void navigateBack();
    
    boolean isForwardNavigationEnabled();
    
    void navigateForward();
    
    void setDefaultPopupMenuRegistered(final boolean p0);
    
    String getStatusText();
    
    String getPageTitle();
    
    int getLoadingProgress();
    
    void registerFunction(final WebBrowserFunction p0);
    
    void unregisterFunction(final WebBrowserFunction p0);
    
    void setAuthenticationHandler(final WebBrowserAuthenticationHandler p0);
    
    WebBrowserAuthenticationHandler getAuthenticationHandler();
    
    String getBrowserType();
    
    String getBrowserVersion();
    
    void addWebBrowserListener(final WebBrowserListener p0);
    
    void removeWebBrowserListener(final WebBrowserListener p0);
    
    Component createEmbeddableComponent(final Map<Object, Object> p0);
    
    boolean unloadAndDispose();
    
    void requestFocus();
    
    boolean isNativePeerDisposed();
    
    boolean isNativePeerInitialized();
    
    boolean print(final boolean p0);
    
    public enum WebBrowserRuntime
    {
        DEFAULT, 
        XULRUNNER, 
        WEBKIT, 
        EDGE;
    }
}
