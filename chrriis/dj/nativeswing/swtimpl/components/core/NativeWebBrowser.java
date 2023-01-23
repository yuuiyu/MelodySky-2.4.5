//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.core;

import chrriis.dj.nativeswing.swtimpl.components.internal.*;
import java.util.regex.*;
import java.lang.ref.*;
import chrriis.dj.nativeswing.swtimpl.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.events.*;
import java.util.*;
import javax.swing.event.*;
import chrriis.dj.nativeswing.common.*;
import chrriis.dj.nativeswing.swtimpl.core.*;
import chrriis.dj.nativeswing.*;
import java.awt.*;
import javax.swing.*;
import chrriis.dj.nativeswing.swtimpl.components.*;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.ole.win32.*;
import java.lang.reflect.*;

class NativeWebBrowser extends SWTNativeComponent implements INativeWebBrowser
{
    private WebBrowserRuntime runtime;
    private String xulRunnerHome;
    private Reference<JWebBrowser> webBrowser;
    private static Pattern JAVASCRIPT_LINE_COMMENT_PATTERN;
    private static volatile Boolean isFixedJS;
    private String status;
    private String title;
    private int loadingProgress;
    private Map<String, WebBrowserFunction> nameToFunctionMap;
    private WebBrowserAuthenticationHandler authenticationHandler;
    private Component embeddableComponent;
    
    @Override
    public WebBrowserRuntime getRuntime() {
        return this.runtime;
    }
    
    @Override
    protected Object[] getNativePeerCreationParameters() {
        return new Object[] { this.runtime, this.xulRunnerHome };
    }
    
    protected static Control createControl(final Composite parent, final Object[] parameters) {
        String xulRunnerPath = (String)parameters[1];
        if (xulRunnerPath != null) {
            NSSystemPropertySWT.ORG_ECLIPSE_SWT_BROWSER_XULRUNNERPATH.set(xulRunnerPath);
        }
        else {
            xulRunnerPath = NSSystemPropertySWT.ORG_ECLIPSE_SWT_BROWSER_XULRUNNERPATH.get();
            if (xulRunnerPath == null) {
                xulRunnerPath = System.getenv("XULRUNNER_HOME");
                if (xulRunnerPath != null) {
                    NSSystemPropertySWT.ORG_ECLIPSE_SWT_BROWSER_XULRUNNERPATH.set(xulRunnerPath);
                }
            }
        }
        int style = 0;
        final WebBrowserRuntime wbRuntime = (WebBrowserRuntime)parameters[0];
        switch (lIlIll.$SwitchMap$chrriis$dj$nativeswing$swtimpl$components$internal$INativeWebBrowser$WebBrowserRuntime[wbRuntime.ordinal()]) {
            case 2: {
                style |= 0x8000;
                break;
            }
            case 1: {
                style |= 0x10000;
                break;
            }
            case 3: {
                style |= 0x40000;
                break;
            }
        }
        final Browser browser = new Browser(parent, style);
        configureBrowserFunctions(browser);
        browser.addCloseWindowListener((CloseWindowListener)new lIIIIl(browser));
        browser.addOpenWindowListener((OpenWindowListener)new lIllIl(browser));
        browser.addLocationListener((LocationListener)new llIII(browser));
        browser.addTitleListener((TitleListener)new lIlIII(browser));
        browser.addStatusTextListener((StatusTextListener)new lIIIlI(browser));
        browser.addProgressListener((ProgressListener)new lIllII(browser));
        registerDefaultPopupMenu(browser);
        return (Control)browser;
    }
    
    private static void configureBrowserFunctions(final Browser browser) {
        if ("edge".equals(browser.getBrowserType())) {
            new NSBrowserFocusBrowserFunction(browser);
        }
        new NSCommandBrowserFunction(browser);
        new NSConsolePrintingBrowserFunction(browser, false);
        new NSConsolePrintingBrowserFunction(browser, true);
    }
    
    public NativeWebBrowser(final JWebBrowser webBrowser, WebBrowserRuntime runtime) {
        this.loadingProgress = 100;
        this.webBrowser = new WeakReference<JWebBrowser>(webBrowser);
        if (runtime == WebBrowserRuntime.DEFAULT) {
            final String runtimeProperty = NSSystemPropertySWT.WEBBROWSER_RUNTIME.get();
            if ("xulrunner".equals(runtimeProperty)) {
                runtime = WebBrowserRuntime.XULRUNNER;
            }
            else if ("webkit".equals(runtimeProperty)) {
                runtime = WebBrowserRuntime.WEBKIT;
            }
            else if ("edge".equals(runtimeProperty)) {
                runtime = WebBrowserRuntime.EDGE;
            }
        }
        if ((this.runtime = runtime) == WebBrowserRuntime.XULRUNNER) {
            this.xulRunnerHome = NSSystemPropertySWT.WEBBROWSER_XULRUNNER_HOME.get();
        }
    }
    
    public static void clearSessionCookies() {
        new CMN_clearSessionCookies(null).asyncExec(true, new Object[0]);
    }
    
    public static String getCookie(final String url, final String name) {
        return (String)new CMN_getCookie(null).syncExec(true, new Object[] { url, name });
    }
    
    public static void setCookie(final String url, final String value) {
        new CMN_setCookie(null).asyncExec(true, new Object[] { url, value });
    }
    
    @Override
    public String getResourceLocation() {
        return (String)this.runSync(new CMN_getResourceLocation(null), new Object[0]);
    }
    
    @Override
    public boolean navigate(final String resourceLocation, final WebBrowserNavigationParameters parameters) {
        return Boolean.TRUE.equals(this.runSync(new CMN_navigate(null), resourceLocation, (parameters == null) ? null : parameters.getPostData(), (parameters == null) ? null : parameters.getHeaders()));
    }
    
    @Override
    public String getHTMLContent() {
        return (String)this.runSync(new CMN_getHTMLContent(null), new Object[0]);
    }
    
    @Override
    public boolean setHTMLContent(final String html) {
        return Boolean.TRUE.equals(this.runSync(new CMN_setHTMLContent(null), html));
    }
    
    @Override
    public boolean isJavascriptEnabled() {
        return Boolean.TRUE.equals(this.runSync(new CMN_isJavascriptEnabled(null), new Object[0]));
    }
    
    @Override
    public void setJavascriptEnabled(final boolean isJavascriptEnabled) {
        this.runAsync(new CMN_setJavascriptEnabled(null), isJavascriptEnabled);
    }
    
    private static String fixJavascript(final Browser browser, String script) {
        if ("mozilla".equals(browser.getBrowserType())) {
            if (NativeWebBrowser.isFixedJS == null) {
                NativeWebBrowser.isFixedJS = "%25".equals(browser.evaluate("return '%25'"));
            }
            if (!NativeWebBrowser.isFixedJS) {
                script = NativeWebBrowser.JAVASCRIPT_LINE_COMMENT_PATTERN.matcher(script).replaceAll("");
                script = Utils.encodeURL(script);
            }
        }
        return script;
    }
    
    @Override
    public boolean executeJavascriptAndWait(final String script) {
        return Boolean.TRUE.equals(this.runSync(new CMN_executeJavascript(null), script));
    }
    
    @Override
    public void executeJavascript(final String script) {
        this.runAsync(new CMN_executeJavascript(null), script);
    }
    
    @Override
    public Object executeJavascriptWithResult(final String script) {
        return this.runSync(new CMN_executeJavascriptWithResult(null), script);
    }
    
    @Override
    public void stopLoading() {
        this.runAsync(new CMN_stopLoading(null), new Object[0]);
    }
    
    @Override
    public void reloadPage() {
        this.runAsync(new CMN_reloadPage(null), new Object[0]);
    }
    
    @Override
    public boolean isBackNavigationEnabled() {
        return Boolean.TRUE.equals(this.runSync(new CMN_isBackNavigationEnabled(null), new Object[0]));
    }
    
    @Override
    public void navigateBack() {
        this.runAsync(new CMN_navigateBack(null), new Object[0]);
    }
    
    @Override
    public boolean isForwardNavigationEnabled() {
        return Boolean.TRUE.equals(this.runSync(new CMN_isForwardNavigationEnabled(null), new Object[0]));
    }
    
    @Override
    public void navigateForward() {
        this.runAsync(new CMN_navigateForward(null), new Object[0]);
    }
    
    private static void registerDefaultPopupMenu(final Browser browser) {
        final Menu oldMenu = browser.getMenu();
        if (oldMenu != null) {
            oldMenu.dispose();
        }
        if (!"mozilla".equals(browser.getBrowserType())) {
            browser.setMenu((Menu)null);
            return;
        }
        final Menu menu = new Menu((Decorations)browser.getShell(), 8);
        final String className = JWebBrowser.class.getName();
        final ResourceBundle bundle = ResourceBundle.getBundle(className.substring(0, className.lastIndexOf(46)).replace('.', '/') + "/resource/WebBrowser");
        final MenuItem backMenuItem = new MenuItem(menu, 8);
        backMenuItem.setText(bundle.getString("SystemMenuBack"));
        backMenuItem.setImage(new Image((Device)browser.getDisplay(), JWebBrowser.class.getResourceAsStream(bundle.getString("SystemMenuBackIcon"))));
        backMenuItem.addSelectionListener((SelectionListener)new lllII(browser));
        final MenuItem forwardMenuItem = new MenuItem(menu, 8);
        forwardMenuItem.setText(bundle.getString("SystemMenuForward"));
        forwardMenuItem.setImage(new Image((Device)browser.getDisplay(), JWebBrowser.class.getResourceAsStream(bundle.getString("SystemMenuForwardIcon"))));
        forwardMenuItem.addSelectionListener((SelectionListener)new lIlll(browser));
        final MenuItem reloadMenuItem = new MenuItem(menu, 8);
        reloadMenuItem.setText(bundle.getString("SystemMenuReload"));
        reloadMenuItem.setImage(new Image((Device)browser.getDisplay(), JWebBrowser.class.getResourceAsStream(bundle.getString("SystemMenuReloadIcon"))));
        reloadMenuItem.addSelectionListener((SelectionListener)new lllIl(browser));
        final MenuItem stopMenuItem = new MenuItem(menu, 8);
        stopMenuItem.setText(bundle.getString("SystemMenuStop"));
        stopMenuItem.setImage(new Image((Device)browser.getDisplay(), JWebBrowser.class.getResourceAsStream(bundle.getString("SystemMenuStopIcon"))));
        stopMenuItem.addSelectionListener((SelectionListener)new llIIl(browser));
        menu.addMenuListener((MenuListener)new llllI(backMenuItem, browser, forwardMenuItem, stopMenuItem));
        browser.setMenu(menu);
    }
    
    @Override
    public void setDefaultPopupMenuRegistered(final boolean isDefaultPopupMenuRegistered) {
        this.runAsync(new CMN_setDefaultPopupMenuRegistered(null), isDefaultPopupMenuRegistered);
    }
    
    @Override
    public String getStatusText() {
        return (this.status == null) ? "" : this.status;
    }
    
    @Override
    public String getPageTitle() {
        return (this.title == null) ? "" : this.title;
    }
    
    @Override
    public int getLoadingProgress() {
        return this.loadingProgress;
    }
    
    @Override
    public void registerFunction(final WebBrowserFunction function) {
        final String functionName = function.getName();
        if (this.nameToFunctionMap == null) {
            this.nameToFunctionMap = new HashMap<String, WebBrowserFunction>();
        }
        else {
            final WebBrowserFunction oldFunction = this.nameToFunctionMap.get(functionName);
            if (oldFunction == function) {
                return;
            }
            if (oldFunction != null) {
                this.unregisterFunction(oldFunction);
            }
        }
        this.nameToFunctionMap.put(functionName, function);
        this.runAsync(new CMN_registerFunction(null), functionName);
    }
    
    @Override
    public void unregisterFunction(final WebBrowserFunction function) {
        if (this.nameToFunctionMap == null) {
            return;
        }
        final String functionName = function.getName();
        final WebBrowserFunction currentFunction = this.nameToFunctionMap.get(functionName);
        if (currentFunction != function) {
            return;
        }
        this.nameToFunctionMap.remove(function);
        if (this.nameToFunctionMap.isEmpty()) {
            this.nameToFunctionMap = null;
        }
        this.runAsync(new CMN_unregisterFunction(null), functionName);
    }
    
    @Override
    public void setAuthenticationHandler(final WebBrowserAuthenticationHandler authenticationHandler) {
        if (this.authenticationHandler == authenticationHandler) {
            return;
        }
        final boolean isActivated = this.authenticationHandler == null;
        final boolean isDeactivated = authenticationHandler == null;
        this.authenticationHandler = authenticationHandler;
        if (isActivated || isDeactivated) {
            this.runAsync(new CMN_setAuthenticationHandler(null), isActivated);
        }
    }
    
    @Override
    public WebBrowserAuthenticationHandler getAuthenticationHandler() {
        return this.authenticationHandler;
    }
    
    @Override
    public String getBrowserType() {
        return (String)this.runSync(new CMN_getBrowserType(null), new Object[0]);
    }
    
    @Override
    public String getBrowserVersion() {
        return (String)this.runSync(new CMN_getBrowserVersion(null), new Object[0]);
    }
    
    @Override
    public void addWebBrowserListener(final WebBrowserListener listener) {
        this.listenerList.add(WebBrowserListener.class, listener);
    }
    
    @Override
    public void removeWebBrowserListener(final WebBrowserListener listener) {
        this.listenerList.remove(WebBrowserListener.class, listener);
    }
    
    @Override
    public Component createEmbeddableComponent(final Map<Object, Object> optionMap) {
        return this.embeddableComponent = super.createEmbeddableComponent(optionMap);
    }
    
    @Override
    protected void disposeNativePeer() {
        super.disposeNativePeer();
    }
    
    @Override
    public boolean unloadAndDispose() {
        if (this.isNativePeerInitialized() && Boolean.TRUE.equals(this.runSync(new CMN_unloadAndDispose(null), new Object[0]))) {
            return false;
        }
        this.disposeNativePeer();
        return true;
    }
    
    @Override
    public boolean print(final boolean isShowingDialog) {
        return Boolean.TRUE.equals(this.runSync(new CMN_print(null), isShowingDialog));
    }
    
    static {
        NativeWebBrowser.JAVASCRIPT_LINE_COMMENT_PATTERN = Pattern.compile("^\\s*//.*$", 8);
    }
    
    private static class CMJ_closeWindow extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final NativeWebBrowser nativeWebBrowser = (NativeWebBrowser)this.getNativeComponent();
            final JWebBrowser webBrowser = (nativeWebBrowser == null) ? null : nativeWebBrowser.webBrowser.get();
            if (webBrowser == null) {
                return null;
            }
            final Object[] listeners = nativeWebBrowser.listenerList.getListenerList();
            WebBrowserEvent e = null;
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == WebBrowserListener.class) {
                    if (e == null) {
                        e = new WebBrowserEvent(webBrowser);
                    }
                    ((WebBrowserListener)listeners[i + 1]).windowClosing(e);
                }
            }
            final JWebBrowserWindow browserWindow = webBrowser.getWebBrowserWindow();
            if (browserWindow != null) {
                browserWindow.dispose();
            }
            webBrowser.disposeNativePeer();
            return null;
        }
    }
    
    private static class CMJ_createWindow extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final NativeWebBrowser nativeWebBrowser = (NativeWebBrowser)this.getNativeComponent();
            final JWebBrowser webBrowser = (nativeWebBrowser == null) ? null : nativeWebBrowser.webBrowser.get();
            if (webBrowser == null) {
                return null;
            }
            JWebBrowser jWebBrowser = null;
            switch (lIlIll.$SwitchMap$chrriis$dj$nativeswing$swtimpl$components$internal$INativeWebBrowser$WebBrowserRuntime[nativeWebBrowser.getRuntime().ordinal()]) {
                case 1: {
                    jWebBrowser = new JWebBrowser(new NSOption[] { JWebBrowser.useWebkitRuntime() });
                    break;
                }
                case 2: {
                    jWebBrowser = new JWebBrowser(new NSOption[] { JWebBrowser.useXULRunnerRuntime() });
                    break;
                }
                case 3: {
                    jWebBrowser = new JWebBrowser(new NSOption[] { JWebBrowser.useEdgeRuntime() });
                    break;
                }
                default: {
                    jWebBrowser = new JWebBrowser(new NSOption[] { JWebBrowser.useEdgeRuntime() });
                    break;
                }
            }
            final Object[] listeners = nativeWebBrowser.listenerList.getListenerList();
            WebBrowserWindowWillOpenEvent e = null;
            for (int i = listeners.length - 2; i >= 0 && jWebBrowser != null; i -= 2) {
                if (listeners[i] == WebBrowserListener.class) {
                    if (e == null) {
                        e = new WebBrowserWindowWillOpenEvent(webBrowser, jWebBrowser);
                    }
                    ((WebBrowserListener)listeners[i + 1]).windowWillOpen(e);
                    jWebBrowser = (e.isConsumed() ? null : e.getNewWebBrowser());
                }
            }
            if (jWebBrowser == null) {
                return null;
            }
            if (!jWebBrowser.isNativePeerInitialized()) {
                Window windowAncestor = SwingUtilities.getWindowAncestor(jWebBrowser);
                if (windowAncestor == null) {
                    final Window parentWindow = e.isDialogWindow() ? SwingUtilities.getWindowAncestor(webBrowser) : null;
                    windowAncestor = (Window)WebBrowserWindowFactory.create(parentWindow, jWebBrowser);
                }
                jWebBrowser.getNativeComponent().initializeNativePeer();
            }
            return ((NativeWebBrowser)jWebBrowser.getNativeComponent()).getComponentID();
        }
    }
    
    private static class CMJ_showWindow extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final NativeWebBrowser nativeWebBrowser = (NativeWebBrowser)this.getNativeComponent();
            final JWebBrowser webBrowser = (nativeWebBrowser == null) ? null : nativeWebBrowser.webBrowser.get();
            if (webBrowser == null) {
                return null;
            }
            final int componentID = (int)args[0];
            final JWebBrowser newWebBrowser = ((NativeWebBrowser)SWTNativeComponent.getNativeComponentRegistry().get(componentID)).webBrowser.get();
            newWebBrowser.setMenuBarVisible((boolean)args[1]);
            newWebBrowser.setButtonBarVisible((boolean)args[2]);
            newWebBrowser.setLocationBarVisible((boolean)args[3]);
            newWebBrowser.setStatusBarVisible((boolean)args[4]);
            final Point location = (Point)args[5];
            final Dimension size = (Dimension)args[6];
            final JWebBrowserWindow browserWindow = newWebBrowser.getWebBrowserWindow();
            if (browserWindow != null) {
                if (size != null) {
                    ((Window)browserWindow).validate();
                    final Dimension windowSize = browserWindow.getSize();
                    final Dimension webBrowserSize = ((NativeWebBrowser)browserWindow.getWebBrowser().getNativeComponent()).embeddableComponent.getSize();
                    if (size.width > 0) {
                        final Dimension dimension = windowSize;
                        dimension.width -= webBrowserSize.width;
                        final Dimension dimension2 = windowSize;
                        dimension2.width += size.width;
                    }
                    if (size.height > 0) {
                        final Dimension dimension3 = windowSize;
                        dimension3.height -= webBrowserSize.height;
                        final Dimension dimension4 = windowSize;
                        dimension4.height += size.height;
                    }
                    browserWindow.setSize(windowSize);
                }
                if (location != null) {
                    browserWindow.setLocation(location);
                }
            }
            final Object[] listeners = nativeWebBrowser.listenerList.getListenerList();
            WebBrowserWindowOpeningEvent e = null;
            for (int i = listeners.length - 2; i >= 0 && newWebBrowser != null; i -= 2) {
                if (listeners[i] == WebBrowserListener.class) {
                    if (e == null) {
                        e = new WebBrowserWindowOpeningEvent(webBrowser, newWebBrowser, location, size);
                    }
                    ((WebBrowserListener)listeners[i + 1]).windowOpening(e);
                }
            }
            new lIIlII(this, newWebBrowser).start();
            return null;
        }
    }
    
    private static class CMJ_locationChanged extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final NativeWebBrowser nativeWebBrowser = (NativeWebBrowser)this.getNativeComponent();
            final JWebBrowser webBrowser = (nativeWebBrowser == null) ? null : nativeWebBrowser.webBrowser.get();
            if (webBrowser == null) {
                return null;
            }
            final Object[] listeners = nativeWebBrowser.listenerList.getListenerList();
            final String location = (String)args[0];
            final boolean isTopFrame = (boolean)args[1];
            WebBrowserNavigationEvent e = null;
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == WebBrowserListener.class) {
                    if (e == null) {
                        e = new WebBrowserNavigationEvent(webBrowser, location, isTopFrame);
                    }
                    ((WebBrowserListener)listeners[i + 1]).locationChanged(e);
                }
            }
            return null;
        }
    }
    
    private static class CMJ_commandReceived extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final NativeWebBrowser nativeWebBrowser = (NativeWebBrowser)this.getNativeComponent();
            final JWebBrowser webBrowser = (nativeWebBrowser == null) ? null : nativeWebBrowser.webBrowser.get();
            if (webBrowser == null) {
                return null;
            }
            final Object[] listeners = nativeWebBrowser.listenerList.getListenerList();
            WebBrowserCommandEvent e = null;
            final String command = (String)args[0];
            final Object[] arguments = (Object[])args[1];
            final boolean isInternal = command.startsWith("[Chrriis]");
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == WebBrowserListener.class) {
                    if (e == null) {
                        e = new WebBrowserCommandEvent(webBrowser, command, arguments);
                    }
                    final WebBrowserListener webBrowserListener = (WebBrowserListener)listeners[i + 1];
                    if (!isInternal || webBrowserListener.getClass().getName().startsWith("chrriis.")) {
                        webBrowserListener.commandReceived(e);
                    }
                }
            }
            return null;
        }
    }
    
    private static class CMJ_locationChanging extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final NativeWebBrowser nativeWebBrowser = (NativeWebBrowser)this.getNativeComponent();
            final JWebBrowser webBrowser = (nativeWebBrowser == null) ? null : nativeWebBrowser.webBrowser.get();
            if (webBrowser == null) {
                return false;
            }
            final Object[] listeners = nativeWebBrowser.listenerList.getListenerList();
            final String location = (String)args[0];
            final boolean isTopFrame = (boolean)args[1];
            boolean isNavigating = true;
            WebBrowserNavigationEvent e = null;
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == WebBrowserListener.class) {
                    if (e == null) {
                        e = new WebBrowserNavigationEvent(webBrowser, location, isTopFrame);
                    }
                    ((WebBrowserListener)listeners[i + 1]).locationChanging(e);
                    isNavigating &= !e.isConsumed();
                }
            }
            return isNavigating;
        }
    }
    
    private static class CMJ_locationChangeCanceled extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final NativeWebBrowser nativeWebBrowser = (NativeWebBrowser)this.getNativeComponent();
            final JWebBrowser webBrowser = (nativeWebBrowser == null) ? null : nativeWebBrowser.webBrowser.get();
            if (webBrowser == null) {
                return null;
            }
            final Object[] listeners = nativeWebBrowser.listenerList.getListenerList();
            final String location = (String)args[0];
            final boolean isTopFrame = (boolean)args[1];
            WebBrowserNavigationEvent e = null;
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == WebBrowserListener.class) {
                    if (e == null) {
                        e = new WebBrowserNavigationEvent(webBrowser, location, isTopFrame);
                    }
                    ((WebBrowserListener)listeners[i + 1]).locationChangeCanceled(e);
                }
            }
            return null;
        }
    }
    
    private static class CMJ_updateTitle extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final NativeWebBrowser nativeWebBrowser = (NativeWebBrowser)this.getNativeComponent();
            final JWebBrowser webBrowser = (nativeWebBrowser == null) ? null : nativeWebBrowser.webBrowser.get();
            if (webBrowser == null) {
                return null;
            }
            nativeWebBrowser.title = (String)args[0];
            final Object[] listeners = nativeWebBrowser.listenerList.getListenerList();
            WebBrowserEvent e = null;
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == WebBrowserListener.class) {
                    if (e == null) {
                        e = new WebBrowserEvent(webBrowser);
                    }
                    ((WebBrowserListener)listeners[i + 1]).titleChanged(e);
                }
            }
            return null;
        }
    }
    
    private static class CMJ_updateStatus extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final NativeWebBrowser nativeWebBrowser = (NativeWebBrowser)this.getNativeComponent();
            final JWebBrowser webBrowser = (nativeWebBrowser == null) ? null : nativeWebBrowser.webBrowser.get();
            if (webBrowser == null) {
                return null;
            }
            nativeWebBrowser.status = (String)args[0];
            final Object[] listeners = nativeWebBrowser.listenerList.getListenerList();
            WebBrowserEvent e = null;
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == WebBrowserListener.class) {
                    if (e == null) {
                        e = new WebBrowserEvent(webBrowser);
                    }
                    ((WebBrowserListener)listeners[i + 1]).statusChanged(e);
                }
            }
            return null;
        }
    }
    
    private static class CMJ_updateLoadingProgress extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final NativeWebBrowser nativeWebBrowser = (NativeWebBrowser)this.getNativeComponent();
            final JWebBrowser webBrowser = (nativeWebBrowser == null) ? null : nativeWebBrowser.webBrowser.get();
            if (webBrowser == null) {
                return null;
            }
            nativeWebBrowser.loadingProgress = (int)args[0];
            final Object[] listeners = nativeWebBrowser.listenerList.getListenerList();
            WebBrowserEvent e = null;
            for (int i = listeners.length - 2; i >= 0; i -= 2) {
                if (listeners[i] == WebBrowserListener.class) {
                    if (e == null) {
                        e = new WebBrowserEvent(webBrowser);
                    }
                    ((WebBrowserListener)listeners[i + 1]).loadingProgressChanged(e);
                }
            }
            return null;
        }
    }
    
    private static class NSCommandBrowserFunction extends BrowserFunction
    {
        public NSCommandBrowserFunction(final Browser browser) {
            super(browser, "sendNSCommand");
        }
        
        public Object function(Object[] args) {
            final String command = (String)((args.length >= 1) ? ((args[0] instanceof String) ? args[0] : "") : "");
            Object[] commandArgs;
            if (args.length > 1) {
                commandArgs = new Object[args.length - 1];
                System.arraycopy(args, 1, commandArgs, 0, commandArgs.length);
                args = commandArgs;
            }
            else {
                commandArgs = new Object[0];
            }
            new CMJ_commandReceived(null).asyncExec((Control)this.getBrowser(), command, commandArgs);
            return null;
        }
    }
    
    private static class CMJ_browserFocus extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final NativeWebBrowser nativeWebBrowser = (NativeWebBrowser)this.getNativeComponent();
            final JWebBrowser webBrowser = (nativeWebBrowser == null) ? null : nativeWebBrowser.webBrowser.get();
            if (webBrowser == null) {
                return null;
            }
            final Window windowAncestor = SwingUtilities.getWindowAncestor(nativeWebBrowser);
            final Component focusOwner = windowAncestor.getFocusOwner();
            if (focusOwner != null && focusOwner != nativeWebBrowser) {
                nativeWebBrowser.requestFocus();
            }
            MenuSelectionManager.defaultManager().clearSelectedPath();
            return null;
        }
    }
    
    private static class NSBrowserFocusBrowserFunction extends BrowserFunction
    {
        public NSBrowserFocusBrowserFunction(final Browser browser) {
            super(browser, "nsBrowserFocus");
        }
        
        public Object function(final Object[] args) {
            new CMJ_browserFocus(null).asyncExec((Control)this.getBrowser(), new Object[0]);
            return null;
        }
    }
    
    private static class CMJ_consolePrinting extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final NativeWebBrowser nativeWebBrowser = (NativeWebBrowser)this.getNativeComponent();
            final JWebBrowser webBrowser = (nativeWebBrowser == null) ? null : nativeWebBrowser.webBrowser.get();
            if (webBrowser == null) {
                return null;
            }
            System.out.println(args[0]);
            return null;
        }
    }
    
    private static class NSConsolePrintingBrowserFunction extends BrowserFunction
    {
        public NSConsolePrintingBrowserFunction(final Browser browser, final boolean isErr) {
            super(browser, isErr ? "nsConsoleErr" : "nsConsoleOut");
        }
        
        public Object function(final Object[] args) {
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length; ++i) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(args[i]);
            }
            new CMJ_consolePrinting(null).asyncExec((Control)this.getBrowser(), sb.toString());
            return null;
        }
    }
    
    private static class CMN_clearSessionCookies extends CommandMessage
    {
        public Object run(final Object[] args) {
            Browser.clearSessions();
            return null;
        }
    }
    
    private static class CMN_getCookie extends CommandMessage
    {
        public Object run(final Object[] args) {
            return Browser.getCookie((String)args[1], (String)args[0]);
        }
    }
    
    private static class CMN_setCookie extends CommandMessage
    {
        public Object run(final Object[] args) {
            Browser.setCookie((String)args[1], (String)args[0]);
            return null;
        }
    }
    
    private static class CMN_getResourceLocation extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            return ((Browser)this.getControl()).getUrl();
        }
    }
    
    private static class CMN_navigate extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            return ((Browser)this.getControl()).setUrl((String)args[0], (String)args[1], (String[])args[2]);
        }
    }
    
    private static class CMN_getHTMLContent extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            return ((Browser)this.getControl()).getText();
        }
    }
    
    private static class CMN_setHTMLContent extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            return ((Browser)this.getControl()).setText((String)args[0]);
        }
    }
    
    private static class CMN_isJavascriptEnabled extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            return ((Browser)this.getControl()).getJavascriptEnabled();
        }
    }
    
    private static class CMN_setJavascriptEnabled extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            ((Browser)this.getControl()).setJavascriptEnabled((boolean)args[0]);
            return null;
        }
    }
    
    private static class CMN_executeJavascript extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final String script = (String)args[0];
            final Browser browser = (Browser)this.getControl();
            return browser.execute(fixJavascript(browser, script));
        }
    }
    
    private static class CMN_executeJavascriptWithResult extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final String script = (String)args[0];
            final Browser browser = (Browser)this.getControl();
            return browser.evaluate(fixJavascript(browser, script));
        }
    }
    
    private static class CMN_stopLoading extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            ((Browser)this.getControl()).stop();
            return null;
        }
    }
    
    private static class CMN_reloadPage extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            ((Browser)this.getControl()).refresh();
            return null;
        }
    }
    
    private static class CMN_isBackNavigationEnabled extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            return ((Browser)this.getControl()).isBackEnabled();
        }
    }
    
    private static class CMN_navigateBack extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            return ((Browser)this.getControl()).back();
        }
    }
    
    private static class CMN_isForwardNavigationEnabled extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            return ((Browser)this.getControl()).isForwardEnabled();
        }
    }
    
    private static class CMN_navigateForward extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            return ((Browser)this.getControl()).forward();
        }
    }
    
    private static class CMN_setDefaultPopupMenuRegistered extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final Browser browser = (Browser)this.getControl();
            final boolean isDefaultPopupMenuRegistered = (boolean)args[0];
            if (isDefaultPopupMenuRegistered) {
                registerDefaultPopupMenu(browser);
            }
            else {
                final Menu oldMenu = browser.getMenu();
                if (oldMenu != null) {
                    oldMenu.dispose();
                }
                final Menu menu = new Menu((Decorations)browser.getShell(), 8);
                menu.addMenuListener((MenuListener)new llIll(this, menu));
                browser.setMenu(menu);
            }
            return null;
        }
    }
    
    private static class CMJ_invokeFunction extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final NativeWebBrowser nativeWebBrowser = (NativeWebBrowser)this.getNativeComponent();
            final JWebBrowser webBrowser = (nativeWebBrowser == null) ? null : nativeWebBrowser.webBrowser.get();
            if (webBrowser == null) {
                return null;
            }
            if (nativeWebBrowser.nameToFunctionMap != null) {
                final WebBrowserFunction function = nativeWebBrowser.nameToFunctionMap.get(args[0]);
                if (function != null) {
                    return function.invoke(webBrowser, (Object[])args[1]);
                }
            }
            return null;
        }
    }
    
    private static class CMN_registerFunction extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final Browser browser = (Browser)this.getControl();
            final String functionName = (String)args[0];
            final BrowserFunction browserFunction = (BrowserFunction)new lIIlIl(this, browser, functionName);
            browser.setData("nsFunction_" + functionName, (Object)browserFunction);
            return null;
        }
    }
    
    private static class CMN_unregisterFunction extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final Browser browser = (Browser)this.getControl();
            final String key = "nsFunction_" + (String)args[0];
            final BrowserFunction browserFunction = (BrowserFunction)browser.getData(key);
            browser.setData(key, (Object)null);
            browserFunction.dispose();
            return null;
        }
    }
    
    private static class CMJ_getCredentials extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final NativeWebBrowser nativeWebBrowser = (NativeWebBrowser)this.getNativeComponent();
            final JWebBrowser webBrowser = (nativeWebBrowser == null) ? null : nativeWebBrowser.webBrowser.get();
            if (webBrowser == null) {
                return null;
            }
            final WebBrowserAuthenticationHandler authenticationHandler = nativeWebBrowser.getAuthenticationHandler();
            if (authenticationHandler == null) {
                return new Object[] { true, null, null };
            }
            final String resourceLocation = (String)args[0];
            final Credentials credentials = authenticationHandler.getCredentials(webBrowser, resourceLocation);
            if (credentials == null) {
                return new Object[] { false, null, null };
            }
            return new Object[] { true, credentials.getUserName(), credentials.getPassword() };
        }
    }
    
    private static class CMN_setAuthenticationHandler extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final Browser browser = (Browser)this.getControl();
            final boolean isActive = (boolean)args[0];
            if (isActive) {
                final AuthenticationListener authenticationListener = (AuthenticationListener)new lIIIII(this, browser);
                browser.setData("Browser.authenticationListener", (Object)authenticationListener);
                browser.addAuthenticationListener(authenticationListener);
            }
            else {
                browser.removeAuthenticationListener((AuthenticationListener)browser.getData("Browser.authenticationListener"));
                browser.setData("Browser.authenticationListener", (Object)null);
            }
            return null;
        }
    }
    
    private static class CMN_getBrowserType extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            return ((Browser)this.getControl()).getBrowserType();
        }
    }
    
    private static class CMN_getBrowserVersion extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            return new NativeJSBrowserDetection((Browser)this.getControl()).browserVersion;
        }
    }
    
    private static class CMN_unloadAndDispose extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            boolean isAlive = true;
            final Browser browser = (Browser)this.getControl();
            if (browser != null && !browser.isDisposed()) {
                final Shell shell = browser.getShell();
                if (browser.close()) {
                    isAlive = false;
                    if (shell != null) {
                        shell.dispose();
                    }
                }
            }
            return isAlive;
        }
    }
    
    private static class CMN_print extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final boolean isShowingDialog = (boolean)args[0];
            final Browser browser = (Browser)this.getControl();
            if (Utils.IS_WINDOWS && "ie".equals(browser.getBrowserType())) {
                try {
                    final Class<?> ieClass = Class.forName("org.eclipse.swt.browser.IE");
                    final Field webBrowserField = Browser.class.getDeclaredField("webBrowser");
                    webBrowserField.setAccessible(true);
                    final Object swtWebBrowser = webBrowserField.get(browser);
                    if (ieClass.isInstance(swtWebBrowser)) {
                        final Field autoField = ieClass.getDeclaredField("auto");
                        autoField.setAccessible(true);
                        final OleAutomation swtBrowserAutomation = (OleAutomation)autoField.get(swtWebBrowser);
                        final int[] rgdispid = swtBrowserAutomation.getIDsOfNames(new String[] { "ExecWB", "cmdID", "cmdexecopt" });
                        final Variant[] rgvarg = { new Variant(6), new Variant(isShowingDialog ? 1 : 2) };
                        final int[] rgdispidNamedArgs = { rgdispid[1], rgdispid[2] };
                        swtBrowserAutomation.invoke(rgdispid[0], rgvarg, rgdispidNamedArgs);
                        return true;
                    }
                }
                catch (Throwable t) {}
            }
            if (!isShowingDialog) {
                return false;
            }
            return browser.execute("print();");
        }
    }
}
