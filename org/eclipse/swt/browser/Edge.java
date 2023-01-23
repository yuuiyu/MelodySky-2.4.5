//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import java.util.function.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import java.nio.charset.*;
import java.util.*;
import java.net.*;
import java.time.*;
import org.eclipse.swt.internal.ole.win32.*;
import org.eclipse.swt.internal.*;

class Edge extends WebBrowser
{
    static final String SDK_TARGET_VERSION = "89.0.721.0";
    static final String APPLOCAL_DIR_KEY = "org.eclipse.swt.internal.win32.appLocalDir";
    static final String BROWSER_DIR_PROP = "org.eclipse.swt.browser.EdgeDir";
    static final String BROWSER_ARGS_PROP = "org.eclipse.swt.browser.EdgeArgs";
    static final String DATA_DIR_PROP = "org.eclipse.swt.browser.EdgeDataDir";
    static final String LANGUAGE_PROP = "org.eclipse.swt.browser.EdgeLanguage";
    static final String VERSIONT_PROP = "org.eclipse.swt.browser.EdgeVersion";
    static String DataDir;
    static ICoreWebView2Environment Environment;
    static ArrayList<Edge> Instances;
    ICoreWebView2 webView;
    ICoreWebView2_2 webView_2;
    ICoreWebView2Controller controller;
    ICoreWebView2Settings settings;
    ICoreWebView2Environment2 environment2;
    static boolean inCallback;
    boolean inNewWindow;
    HashMap<Long, LocationEvent> navigations;
    
    Edge() {
        this.navigations = new HashMap<Long, LocationEvent>();
    }
    
    static String wstrToString(final long psz, final boolean free) {
        if (psz == 0L) {
            return "";
        }
        final int len = OS.wcslen(psz);
        final char[] data = new char[len];
        OS.MoveMemory(data, psz, len * 2);
        if (free) {
            OS.CoTaskMemFree(psz);
        }
        return String.valueOf(data);
    }
    
    static String bstrToString(final long bstr) {
        if (bstr == 0L) {
            return "";
        }
        final int len = COM.SysStringLen(bstr);
        final char[] data = new char[len];
        OS.MoveMemory(data, bstr, len * 2);
        return String.valueOf(data);
    }
    
    static char[] stringToWstr(final String s) {
        return (char[])((s != null) ? s.toCharArray() : null);
    }
    
    static void error(final int code, final int hr) {
        SWT.error(code, null, String.format(" [0x%08x]", hr));
    }
    
    static IUnknown newCallback(final ICoreWebView2SwtCallback handler) {
        final long punk = COM.CreateSwtWebView2Callback((arg0, arg1) -> {
            Edge.inCallback = true;
            try {
                return handler.Invoke(arg0, arg1);
            }
            finally {
                Edge.inCallback = false;
            }
        });
        if (punk == 0L) {
            error(2, -2147024882);
        }
        return new IUnknown(punk);
    }
    
    IUnknown newHostObject(final ICoreWebView2SwtHost handler) {
        final long pdisp = COM.CreateSwtWebView2Host(handler);
        if (pdisp == 0L) {
            error(2, -2147024882);
        }
        return new IUnknown(pdisp);
    }
    
    static int callAndWait(final long[] ppv, final ToIntFunction<IUnknown> callable) {
        final int[] phr = { 0 };
        final IUnknown completion = newCallback((result, pv) -> {
            phr[0] = (int)result;
            if ((int)result == 0) {
                ppv[0] = pv;
                new IUnknown(pv).AddRef();
            }
            return 0;
        });
        ppv[0] = 0L;
        phr[0] = callable.applyAsInt(completion);
        completion.Release();
        final Display display = Display.getCurrent();
        while (phr[0] == 0 && ppv[0] == 0L) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return phr[0];
    }
    
    static int callAndWait(final String[] pstr, final ToIntFunction<IUnknown> callable) {
        final int[] phr = { 0 };
        final IUnknown completion = newCallback((result, pszJson) -> {
            phr[0] = (int)result;
            if ((int)result == 0) {
                pstr[0] = wstrToString(pszJson, false);
            }
            return 0;
        });
        pstr[0] = null;
        phr[0] = callable.applyAsInt(completion);
        completion.Release();
        final Display display = Display.getCurrent();
        while (phr[0] == 0 && pstr[0] == null) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return phr[0];
    }
    
    static ICoreWebView2CookieManager getCookieManager() {
        if (Edge.Instances.isEmpty()) {
            SWT.error(20, null, " [WebView2: cookie access requires a Browser instance]");
        }
        final Edge instance = Edge.Instances.get(0);
        if (instance.webView_2 == null) {
            SWT.error(20, null, " [WebView2 version 88+ is required to access cookies]");
        }
        final long[] ppv = { 0L };
        final int hr = instance.webView_2.get_CookieManager(ppv);
        if (hr != 0) {
            error(2, hr);
        }
        return new ICoreWebView2CookieManager(ppv[0]);
    }
    
    void checkDeadlock() {
        if (Edge.inCallback || this.inNewWindow) {
            SWT.error(50, null, " [WebView2: deadlock detected]");
        }
    }
    
    ICoreWebView2Environment createEnvironment() {
        if (Edge.Environment != null) {
            return Edge.Environment;
        }
        final Display display = Display.getCurrent();
        final String browserDir = System.getProperty("org.eclipse.swt.browser.EdgeDir");
        String dataDir = System.getProperty("org.eclipse.swt.browser.EdgeDataDir");
        final String browserArgs = System.getProperty("org.eclipse.swt.browser.EdgeArgs");
        final String language = System.getProperty("org.eclipse.swt.browser.EdgeLanguage");
        if (dataDir == null) {
            dataDir = (String)display.getData("org.eclipse.swt.internal.win32.appLocalDir");
        }
        final long pOpts = COM.CreateSwtWebView2Options();
        if (pOpts == 0L) {
            error(2, -2147024882);
        }
        final ICoreWebView2EnvironmentOptions options = new ICoreWebView2EnvironmentOptions(pOpts);
        final char[] pVersion = stringToWstr("89.0.721.0");
        options.put_TargetCompatibleBrowserVersion(pVersion);
        if (browserArgs != null) {
            final char[] pBrowserArgs = stringToWstr(browserArgs);
            options.put_AdditionalBrowserArguments(pBrowserArgs);
        }
        if (language != null) {
            final char[] pLanguage = stringToWstr(language);
            options.put_Language(pLanguage);
        }
        final char[] pBrowserDir = stringToWstr(browserDir);
        final char[] pDataDir = stringToWstr(dataDir);
        final long[] ppv = { 0L };
        final int hr = callAndWait(ppv, completion -> COM.CreateCoreWebView2EnvironmentWithOptions(pBrowserDir, pDataDir, options.getAddress(), completion.getAddress()));
        options.Release();
        if (hr == OS.HRESULT_FROM_WIN32(2)) {
            SWT.error(20, null, " [WebView2 runtime not found]");
        }
        if (hr != 0) {
            error(2, hr);
        }
        Edge.Environment = new ICoreWebView2Environment(ppv[0]);
        Edge.DataDir = dataDir;
        final long[] ppVersion = { 0L };
        Edge.Environment.get_BrowserVersionString(ppVersion);
        final String version = wstrToString(ppVersion[0], true);
        System.setProperty("org.eclipse.swt.browser.EdgeVersion", version);
        display.disposeExec(() -> {
            Edge.Environment.Release();
            Edge.Environment = null;
            return;
        });
        return Edge.Environment;
    }
    
    @Override
    public void create(final Composite parent, final int style) {
        this.checkDeadlock();
        final ICoreWebView2Environment environment = this.createEnvironment();
        final long[] ppv = { 0L };
        int hr = environment.QueryInterface(COM.IID_ICoreWebView2Environment2, ppv);
        if (hr == 0) {
            this.environment2 = new ICoreWebView2Environment2(ppv[0]);
        }
        hr = callAndWait(ppv, completion -> environment.CreateCoreWebView2Controller(this.browser.handle, completion));
        if (hr != 0) {
            error(2, hr);
        }
        (this.controller = new ICoreWebView2Controller(ppv[0])).get_CoreWebView2(ppv);
        (this.webView = new ICoreWebView2(ppv[0])).get_Settings(ppv);
        this.settings = new ICoreWebView2Settings(ppv[0]);
        hr = this.webView.QueryInterface(COM.IID_ICoreWebView2_2, ppv);
        if (hr == 0) {
            this.webView_2 = new ICoreWebView2_2(ppv[0]);
        }
        final long[] token = { 0L };
        IUnknown handler = newCallback(this::handleCloseRequested);
        this.webView.add_WindowCloseRequested(handler, token);
        handler.Release();
        handler = newCallback(this::handleNavigationStarting);
        this.webView.add_NavigationStarting(handler, token);
        handler.Release();
        handler = newCallback(this::handleFrameNavigationStarting);
        this.webView.add_FrameNavigationStarting(handler, token);
        handler.Release();
        handler = newCallback(this::handleNavigationCompleted);
        this.webView.add_NavigationCompleted(handler, token);
        handler.Release();
        handler = newCallback(this::handleFrameNavigationCompleted);
        this.webView.add_FrameNavigationCompleted(handler, token);
        handler.Release();
        handler = newCallback(this::handleDocumentTitleChanged);
        this.webView.add_DocumentTitleChanged(handler, token);
        handler.Release();
        handler = newCallback(this::handleNewWindowRequested);
        this.webView.add_NewWindowRequested(handler, token);
        handler.Release();
        handler = newCallback(this::handleSourceChanged);
        this.webView.add_SourceChanged(handler, token);
        handler.Release();
        handler = newCallback(this::handleMoveFocusRequested);
        this.controller.add_MoveFocusRequested(handler, token);
        handler.Release();
        if (this.webView_2 != null) {
            handler = newCallback(this::handleDOMContentLoaded);
            this.webView_2.add_DOMContentLoaded(handler, token);
            handler.Release();
        }
        final IUnknown hostDisp = this.newHostObject(this::handleCallJava);
        final long[] hostObj = { 9L, hostDisp.getAddress(), 0L };
        this.webView.AddHostObjectToScript("swt\u0000".toCharArray(), hostObj);
        hostDisp.Release();
        this.browser.addListener(12, this::browserDispose);
        this.browser.addListener(15, this::browserFocusIn);
        this.browser.addListener(11, this::browserResize);
        this.browser.addListener(10, this::browserMove);
        Edge.Instances.add(this);
    }
    
    void browserDispose(final Event event) {
        Edge.Instances.remove(this);
        if (this.webView_2 != null) {
            this.webView_2.Release();
        }
        if (this.environment2 != null) {
            this.environment2.Release();
        }
        this.settings.Release();
        this.webView.Release();
        this.webView_2 = null;
        this.environment2 = null;
        this.settings = null;
        this.webView = null;
        if (Edge.inCallback) {
            final ICoreWebView2Controller controller1 = this.controller;
            this.controller.put_IsVisible(false);
            final ICoreWebView2Controller coreWebView2Controller;
            this.browser.getDisplay().asyncExec(() -> {
                coreWebView2Controller.Close();
                coreWebView2Controller.Release();
                return;
            });
        }
        else {
            this.controller.Close();
            this.controller.Release();
        }
        this.controller = null;
    }
    
    void browserFocusIn(final Event event) {
        this.controller.MoveFocus(0);
    }
    
    void browserMove(final Event event) {
        this.controller.NotifyParentWindowPositionChanged();
    }
    
    void browserResize(final Event event) {
        final RECT rect = new RECT();
        OS.GetClientRect(this.browser.handle, rect);
        this.controller.put_Bounds(rect);
        this.controller.put_IsVisible(true);
    }
    
    @Override
    public Object evaluate(final String script) throws SWTException {
        this.checkDeadlock();
        if (!this.jsEnabled) {
            return null;
        }
        final String script2 = "(function() {try { " + script + " } catch (e) { return 'org.eclipse.swt.browser.error' + e.message; } })();\u0000";
        final String[] pJson = { null };
        final int hr = callAndWait(pJson, completion -> this.webView.ExecuteScript(script2.toCharArray(), completion));
        if (hr != 0) {
            error(50, hr);
        }
        final Object data = JSON.parse(pJson[0]);
        if (data instanceof String && ((String)data).startsWith("org.eclipse.swt.browser.error")) {
            final String errorMessage = ((String)data).substring("org.eclipse.swt.browser.error".length());
            throw new SWTException(50, errorMessage);
        }
        return data;
    }
    
    @Override
    public boolean execute(final String script) {
        if (!this.jsEnabled) {
            return false;
        }
        final IUnknown completion = newCallback((result, json) -> 0);
        final int hr = this.webView.ExecuteScript(stringToWstr(script), completion);
        completion.Release();
        return hr == 0;
    }
    
    @Override
    public String getBrowserType() {
        return "edge";
    }
    
    @Override
    String getJavaCallDeclaration() {
        return "if (!window.callJava) { window.callJava = function(index, token, args) {\nreturn JSON.parse(window.chrome.webview.hostObjects.sync.swt.CallJava(index, token, JSON.stringify(args)));\n}};\n";
    }
    
    @Override
    public String getText() {
        return (String)this.evaluate("return document.documentElement.outerHTML;");
    }
    
    @Override
    public String getUrl() {
        final long[] ppsz = { 0L };
        this.webView.get_Source(ppsz);
        return wstrToString(ppsz[0], true);
    }
    
    int handleCloseRequested(final long pView, final long pArgs) {
        WindowEvent event;
        CloseWindowListener[] closeWindowListeners;
        int length;
        int i;
        CloseWindowListener listener;
        this.browser.getDisplay().asyncExec(() -> {
            if (this.browser.isDisposed()) {
                return;
            }
            else {
                event = new WindowEvent((Widget)this.browser);
                event.display = this.browser.getDisplay();
                event.widget = (Widget)this.browser;
                closeWindowListeners = this.closeWindowListeners;
                length = closeWindowListeners.length;
                i = 0;
                while (i < length) {
                    listener = closeWindowListeners[i];
                    listener.close(event);
                    if (this.browser.isDisposed()) {
                        return;
                    }
                    else {
                        ++i;
                    }
                }
                this.browser.dispose();
                return;
            }
        });
        return 0;
    }
    
    int handleDocumentTitleChanged(final long pView, final long pArgs) {
        final long[] ppsz = { 0L };
        this.webView.get_DocumentTitle(ppsz);
        final String title = wstrToString(ppsz[0], true);
        TitleEvent event;
        final String title2;
        TitleListener[] titleListeners;
        int i;
        int length;
        TitleListener listener;
        this.browser.getDisplay().asyncExec(() -> {
            if (this.browser.isDisposed()) {
                return;
            }
            else {
                event = new TitleEvent((Widget)this.browser);
                event.display = this.browser.getDisplay();
                event.widget = (Widget)this.browser;
                event.title = title2;
                titleListeners = this.titleListeners;
                for (i = 0, length = titleListeners.length; i < length; ++i) {
                    listener = titleListeners[i];
                    listener.changed(event);
                    if (this.browser.isDisposed()) {}
                }
                return;
            }
        });
        return 0;
    }
    
    long handleCallJava(final int index, final long bstrToken, final long bstrArgsJson) {
        Object result = null;
        final String token = bstrToString(bstrToken);
        final BrowserFunction function = this.functions.get(index);
        if (function != null && token.equals(function.token)) {
            try {
                final String argsJson = bstrToString(bstrArgsJson);
                final Object args = JSON.parse(argsJson.toCharArray());
                result = function.function((Object[])args);
            }
            catch (Throwable e) {
                result = WebBrowser.CreateErrorString(e.getLocalizedMessage());
            }
        }
        final String json = JSON.stringify(result);
        return COM.SysAllocStringLen(json.toCharArray(), json.length());
    }
    
    int handleFrameNavigationStarting(final long pView, final long pArgs) {
        return this.handleNavigationStarting(pView, pArgs, false);
    }
    
    int handleNavigationStarting(final long pView, final long pArgs) {
        return this.handleNavigationStarting(pView, pArgs, true);
    }
    
    int handleNavigationStarting(final long pView, final long pArgs, final boolean top) {
        final ICoreWebView2NavigationStartingEventArgs args = new ICoreWebView2NavigationStartingEventArgs(pArgs);
        final long[] ppszUrl = { 0L };
        final int hr = args.get_Uri(ppszUrl);
        if (hr != 0) {
            return hr;
        }
        final String url = wstrToString(ppszUrl[0], true);
        final long[] pNavId = { 0L };
        args.get_NavigationId(pNavId);
        final LocationEvent event = new LocationEvent((Widget)this.browser);
        event.display = this.browser.getDisplay();
        event.widget = (Widget)this.browser;
        event.location = url;
        event.top = top;
        event.doit = true;
        for (final LocationListener listener : this.locationListeners) {
            listener.changing(event);
            if (this.browser.isDisposed()) {
                return 0;
            }
        }
        if (event.doit) {
            this.navigations.put(pNavId[0], event);
            this.jsEnabled = this.jsEnabledOnNextPage;
            this.settings.put_IsScriptEnabled(this.jsEnabled);
            if (!this.functions.isEmpty()) {
                final StringBuilder sb = new StringBuilder();
                for (final BrowserFunction function : this.functions.values()) {
                    sb.append(function.functionString);
                }
                this.execute(sb.toString());
            }
        }
        else {
            args.put_Cancel(true);
        }
        return 0;
    }
    
    int handleSourceChanged(final long pView, final long pArgs) {
        final long[] ppsz = { 0L };
        final int hr = this.webView.get_Source(ppsz);
        if (hr != 0) {
            return hr;
        }
        final String url = wstrToString(ppsz[0], true);
        LocationEvent event;
        final String location;
        LocationListener[] locationListeners;
        int i;
        int length;
        LocationListener listener;
        this.browser.getDisplay().asyncExec(() -> {
            if (this.browser.isDisposed()) {
                return;
            }
            else {
                event = new LocationEvent((Widget)this.browser);
                event.display = this.browser.getDisplay();
                event.widget = (Widget)this.browser;
                event.location = location;
                event.top = true;
                locationListeners = this.locationListeners;
                for (i = 0, length = locationListeners.length; i < length; ++i) {
                    listener = locationListeners[i];
                    listener.changed(event);
                    if (this.browser.isDisposed()) {}
                }
                return;
            }
        });
        return 0;
    }
    
    void sendProgressCompleted() {
        ProgressEvent event;
        ProgressListener[] progressListeners;
        int i;
        int length;
        ProgressListener listener;
        this.browser.getDisplay().asyncExec(() -> {
            if (!this.browser.isDisposed()) {
                event = new ProgressEvent((Widget)this.browser);
                event.display = this.browser.getDisplay();
                event.widget = (Widget)this.browser;
                progressListeners = this.progressListeners;
                for (i = 0, length = progressListeners.length; i < length; ++i) {
                    listener = progressListeners[i];
                    listener.completed(event);
                    if (this.browser.isDisposed()) {}
                }
            }
        });
    }
    
    int handleDOMContentLoaded(final long pView, final long pArgs) {
        final ICoreWebView2DOMContentLoadedEventArgs args = new ICoreWebView2DOMContentLoadedEventArgs(pArgs);
        final long[] pNavId = { 0L };
        args.get_NavigationId(pNavId);
        final LocationEvent startEvent = this.navigations.get(pNavId[0]);
        if (startEvent != null && startEvent.top) {
            this.sendProgressCompleted();
        }
        return 0;
    }
    
    int handleNavigationCompleted(final long pView, final long pArgs) {
        return this.handleNavigationCompleted(pView, pArgs, true);
    }
    
    int handleFrameNavigationCompleted(final long pView, final long pArgs) {
        return this.handleNavigationCompleted(pView, pArgs, false);
    }
    
    int handleNavigationCompleted(final long pView, final long pArgs, final boolean top) {
        final ICoreWebView2NavigationCompletedEventArgs args = new ICoreWebView2NavigationCompletedEventArgs(pArgs);
        final long[] pNavId = { 0L };
        args.get_NavigationId(pNavId);
        final LocationEvent startEvent = this.navigations.remove(pNavId[0]);
        if (this.webView_2 == null && startEvent != null && startEvent.top) {
            this.sendProgressCompleted();
        }
        return 0;
    }
    
    void updateWindowFeatures(final ICoreWebView2NewWindowRequestedEventArgs args, final WindowEvent event) {
        final long[] ppv = { 0L };
        final int hr = args.get_WindowFeatures(ppv);
        if (hr != 0) {
            return;
        }
        final ICoreWebView2WindowFeatures features = new ICoreWebView2WindowFeatures(ppv[0]);
        final int[] px = { 0 };
        final int[] py = { 0 };
        features.get_HasPosition(px);
        if (px[0] != 0) {
            features.get_Left(px);
            features.get_Top(py);
            event.location = new Point(px[0], py[0]);
        }
        features.get_HasSize(px);
        if (px[0] != 0) {
            features.get_Width(px);
            features.get_Height(py);
            event.size = new Point(px[0], py[0]);
        }
        features.get_ShouldDisplayMenuBar(px);
        event.menuBar = (px[0] != 0);
        features.get_ShouldDisplayStatus(px);
        event.statusBar = (px[0] != 0);
        features.get_ShouldDisplayToolbar(px);
        event.toolBar = (px[0] != 0);
    }
    
    int handleNewWindowRequested(final long pView, final long pArgs) {
        final ICoreWebView2NewWindowRequestedEventArgs args = new ICoreWebView2NewWindowRequestedEventArgs(pArgs);
        args.AddRef();
        final long[] ppv = { 0L };
        args.GetDeferral(ppv);
        final ICoreWebView2Deferral deferral = new ICoreWebView2Deferral(ppv[0]);
        this.inNewWindow = true;
        WindowEvent openEvent;
        OpenWindowListener[] openWindowListeners;
        int length;
        int i;
        OpenWindowListener openListener;
        WebBrowser other;
        final ICoreWebView2NewWindowRequestedEventArgs args2;
        WindowEvent showEvent;
        VisibilityWindowListener[] visibilityWindowListeners;
        int j;
        int length2;
        VisibilityWindowListener showListener;
        final ICoreWebView2Deferral coreWebView2Deferral;
        this.browser.getDisplay().asyncExec(() -> {
            try {
                if (!this.browser.isDisposed()) {
                    openEvent = new WindowEvent((Widget)this.browser);
                    openEvent.display = this.browser.getDisplay();
                    openEvent.widget = (Widget)this.browser;
                    openEvent.required = false;
                    openWindowListeners = this.openWindowListeners;
                    length = openWindowListeners.length;
                    i = 0;
                    while (i < length) {
                        openListener = openWindowListeners[i];
                        openListener.open(openEvent);
                        if (this.browser.isDisposed()) {
                            return;
                        }
                        else {
                            ++i;
                        }
                    }
                    if (openEvent.browser != null && !openEvent.browser.isDisposed()) {
                        other = openEvent.browser.webBrowser;
                        args2.put_Handled(true);
                        if (other instanceof Edge) {
                            args2.put_NewWindow(((Edge)other).webView.getAddress());
                            showEvent = new WindowEvent((Widget)other.browser);
                            showEvent.display = this.browser.getDisplay();
                            showEvent.widget = (Widget)other.browser;
                            this.updateWindowFeatures(args2, showEvent);
                            visibilityWindowListeners = other.visibilityWindowListeners;
                            for (j = 0, length2 = visibilityWindowListeners.length; j < length2; ++j) {
                                showListener = visibilityWindowListeners[j];
                                showListener.show(showEvent);
                                if (other.browser.isDisposed()) {}
                            }
                        }
                    }
                    else if (openEvent.required) {
                        args2.put_Handled(true);
                    }
                }
            }
            finally {
                coreWebView2Deferral.Complete();
                coreWebView2Deferral.Release();
                args2.Release();
                this.inNewWindow = false;
            }
            return;
        });
        return 0;
    }
    
    int handleMoveFocusRequested(final long pView, final long pArgs) {
        final ICoreWebView2MoveFocusRequestedEventArgs args = new ICoreWebView2MoveFocusRequestedEventArgs(pArgs);
        final int[] pReason = { 0 };
        args.get_Reason(pReason);
        args.put_Handled(true);
        switch (pReason[0]) {
            case 1: {
                this.browser.traverse(16);
                break;
            }
            case 2: {
                this.browser.traverse(8);
                break;
            }
        }
        return 0;
    }
    
    @Override
    public boolean isBackEnabled() {
        final int[] pval = { 0 };
        this.webView.get_CanGoBack(pval);
        return pval[0] != 0;
    }
    
    @Override
    public boolean isForwardEnabled() {
        final int[] pval = { 0 };
        this.webView.get_CanGoForward(pval);
        return pval[0] != 0;
    }
    
    @Override
    public boolean back() {
        return this.isBackEnabled() && this.webView.GoBack() == 0;
    }
    
    @Override
    public boolean forward() {
        return this.isForwardEnabled() && this.webView.GoForward() == 0;
    }
    
    @Override
    public void refresh() {
        this.webView.Reload();
    }
    
    @Override
    public void stop() {
        this.webView.Stop();
    }
    
    @Override
    public boolean setText(final String html, final boolean trusted) {
        final char[] data = new char[html.length() + 1];
        html.getChars(0, html.length(), data, 0);
        return this.webView.NavigateToString(data) == 0;
    }
    
    @Override
    public boolean setUrl(String url, final String postData, final String[] headers) {
        if (!url.matches("[a-z][a-z0-9+.-]*:.*")) {
            url = "http://" + url;
        }
        final char[] pszUrl = stringToWstr(url);
        int hr;
        if (postData != null || headers != null) {
            if (this.environment2 == null || this.webView_2 == null) {
                SWT.error(20, null, " [WebView2 version 88+ is required to set postData and headers]");
            }
            final long[] ppRequest = { 0L };
            char[] pszMethod = null;
            char[] pszHeaders = null;
            IStream stream = null;
            if (postData != null) {
                pszMethod = "POST\u0000".toCharArray();
                final byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);
                final long pStream = COM.SHCreateMemStream(postDataBytes, postData.length());
                if (pStream == 0L) {
                    error(2, -2147024882);
                }
                stream = new IStream(pStream);
            }
            else {
                pszMethod = "GET\u0000".toCharArray();
            }
            if (headers != null) {
                final String hblock = String.join("\r\n", Arrays.asList(headers));
                pszHeaders = new char[hblock.length() + 1];
                hblock.getChars(0, hblock.length(), pszHeaders, 0);
            }
            hr = this.environment2.CreateWebResourceRequest(pszUrl, pszMethod, stream, pszHeaders, ppRequest);
            if (stream != null) {
                stream.Release();
            }
            if (hr != 0) {
                error(2, hr);
            }
            final IUnknown request = new IUnknown(ppRequest[0]);
            hr = this.webView_2.NavigateWithWebResourceRequest(request);
            request.Release();
        }
        else {
            hr = this.webView.Navigate(pszUrl);
        }
        return hr == 0;
    }
    
    static {
        Library.loadLibrary("WebView2Loader");
        Edge.Instances = new ArrayList<Edge>();
        final ICoreWebView2CookieManager manager;
        long[] ppv;
        int hr;
        ICoreWebView2CookieList cookieList;
        int[] count;
        int[] isSession;
        int i;
        int hr2;
        ICoreWebView2Cookie cookie;
        Edge.NativeClearSessions = (() -> {
            manager = getCookieManager();
            if (manager == null) {
                return;
            }
            else {
                ppv = new long[] { 0L };
                hr = callAndWait(ppv, completion -> manager.GetCookies(null, completion));
                if (hr != 0) {
                    error(2, hr);
                }
                cookieList = new ICoreWebView2CookieList(ppv[0]);
                count = new int[] { 0 };
                isSession = new int[] { 0 };
                cookieList.get_Count(count);
                for (i = 0; i < count[0]; ++i) {
                    hr2 = cookieList.GetValueAtIndex(i, ppv);
                    if (hr2 != 0) {
                        error(2, hr2);
                    }
                    cookie = new ICoreWebView2Cookie(ppv[0]);
                    cookie.get_IsSession(isSession);
                    if (isSession[0] != 0) {
                        manager.DeleteCookie(cookie);
                    }
                    cookie.Release();
                }
                cookieList.Release();
                manager.Release();
                try {
                    Thread.sleep(5L);
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                return;
            }
        });
        final ICoreWebView2CookieManager manager2;
        char[] uri;
        long[] ppv2;
        int hr3;
        ICoreWebView2CookieList cookieList2;
        int[] count2;
        int j;
        int hr4;
        ICoreWebView2Cookie cookie2;
        String name;
        Edge.NativeGetCookie = (() -> {
            manager2 = getCookieManager();
            if (manager2 == null) {
                return;
            }
            else {
                uri = stringToWstr(Edge.CookieUrl);
                ppv2 = new long[] { 0L };
                hr3 = callAndWait(ppv2, completion -> manager2.GetCookies(uri, completion));
                if (hr3 != 0) {
                    error(2, hr3);
                }
                cookieList2 = new ICoreWebView2CookieList(ppv2[0]);
                count2 = new int[] { 0 };
                cookieList2.get_Count(count2);
                j = 0;
                while (j < count2[0]) {
                    hr4 = cookieList2.GetValueAtIndex(j, ppv2);
                    if (hr4 != 0) {
                        error(2, hr4);
                    }
                    cookie2 = new ICoreWebView2Cookie(ppv2[0]);
                    cookie2.get_Name(ppv2);
                    name = wstrToString(ppv2[0], true);
                    if (Edge.CookieName.equals(name)) {
                        cookie2.get_Value(ppv2);
                        Edge.CookieValue = wstrToString(ppv2[0], true);
                    }
                    cookie2.Release();
                    if (Edge.CookieValue != null) {
                        break;
                    }
                    else {
                        ++j;
                    }
                }
                cookieList2.Release();
                manager2.Release();
                return;
            }
        });
        final HttpCookie parser;
        URL origin;
        final ICoreWebView2CookieManager manager3;
        char[] name2;
        char[] value;
        char[] domain;
        char[] path;
        long[] ppv3;
        int hr5;
        ICoreWebView2Cookie cookie3;
        int hr6;
        Edge.NativeSetCookie = (() -> {
            parser = HttpCookie.parse(Edge.CookieValue).get(0);
            try {
                origin = new URL(Edge.CookieUrl);
            }
            catch (MalformedURLException e2) {
                return;
            }
            if (parser.getDomain() == null) {
                parser.setDomain(origin.getHost());
            }
            if (parser.getPath() == null) {
                parser.setPath(origin.getPath());
            }
            manager3 = getCookieManager();
            if (manager3 != null) {
                name2 = stringToWstr(parser.getName());
                value = stringToWstr(parser.getValue());
                domain = stringToWstr(parser.getDomain());
                path = stringToWstr(parser.getPath());
                ppv3 = new long[] { 0L };
                hr5 = manager3.CreateCookie(name2, value, domain, path, ppv3);
                if (hr5 != 0) {
                    manager3.Release();
                }
                else {
                    cookie3 = new ICoreWebView2Cookie(ppv3[0]);
                    if (parser.getMaxAge() != -1L) {
                        cookie3.put_Expires((double)(Instant.now().getEpochSecond() + parser.getMaxAge()));
                    }
                    cookie3.put_IsSecure(parser.getSecure());
                    cookie3.put_IsHttpOnly(parser.isHttpOnly());
                    hr6 = manager3.AddOrUpdateCookie(cookie3);
                    cookie3.Release();
                    manager3.Release();
                    Edge.CookieResult = (hr6 >= 0);
                }
            }
        });
    }
}
