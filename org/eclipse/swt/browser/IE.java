//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.*;
import java.net.*;
import org.eclipse.swt.ole.win32.*;
import org.eclipse.swt.internal.win32.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.internal.ole.win32.*;
import java.util.*;
import java.io.*;

class IE extends WebBrowser
{
    OleFrame frame;
    WebSite site;
    OleAutomation auto;
    OleListener domListener;
    OleAutomation[] documents;
    boolean back;
    boolean forward;
    boolean delaySetText;
    boolean ignoreDispose;
    boolean ignoreTraverse;
    boolean performingInitialNavigate;
    boolean installFunctionsOnDocumentComplete;
    boolean untrustedText;
    boolean isRefresh;
    boolean isAboutBlank;
    Point location;
    Point size;
    boolean addressBar;
    boolean menuBar;
    boolean statusBar;
    boolean toolBar;
    long globalDispatch;
    String html;
    String lastNavigateURL;
    String uncRedirect;
    Object[] pendingText;
    Object[] pendingUrl;
    int style;
    int lastKeyCode;
    int lastCharCode;
    int lastMouseMoveX;
    int lastMouseMoveY;
    static boolean Initialized;
    static int IEVersion;
    static int PDFCount;
    static String ProgId;
    static final int BeforeNavigate2 = 250;
    static final int CommandStateChange = 105;
    static final int DocumentComplete = 259;
    static final int DownloadComplete = 104;
    static final int NavigateComplete2 = 252;
    static final int NewWindow2 = 251;
    static final int OnMenuBar = 256;
    static final int OnStatusBar = 257;
    static final int OnToolBar = 255;
    static final int OnVisible = 254;
    static final int ProgressChange = 108;
    static final int RegisterAsBrowser = 552;
    static final int StatusTextChange = 102;
    static final int TitleChange = 113;
    static final int WindowClosing = 263;
    static final int WindowSetHeight = 267;
    static final int WindowSetLeft = 264;
    static final int WindowSetResizable = 262;
    static final int WindowSetTop = 265;
    static final int WindowSetWidth = 266;
    static final int NavigateError = 271;
    static final short CSC_NAVIGATEFORWARD = 1;
    static final short CSC_NAVIGATEBACK = 2;
    static final int INET_E_DEFAULT_ACTION = -2146697199;
    static final int INET_E_RESOURCE_NOT_FOUND = -2146697211;
    static final int READYSTATE_COMPLETE = 4;
    static final int URLPOLICY_ALLOW = 0;
    static final int URLPOLICY_DISALLOW = 3;
    static final int URLPOLICY_JAVA_PROHIBIT = 0;
    static final int URLPOLICY_JAVA_LOW = 196608;
    static final int URLZONE_LOCAL_MACHINE = 0;
    static final int URLZONE_INTRANET = 1;
    static final int URLACTION_ACTIVEX_MIN = 4608;
    static final int URLACTION_ACTIVEX_MAX = 5119;
    static final int URLACTION_ACTIVEX_RUN = 4608;
    static final int URLACTION_FEATURE_ZONE_ELEVATION = 8449;
    static final int URLACTION_JAVA_MIN = 7168;
    static final int URLACTION_JAVA_MAX = 7423;
    static final int URLACTION_SCRIPT_RUN = 5120;
    static final int DISPID_AMBIENT_DLCONTROL = -5512;
    static final int DLCTL_DLIMAGES = 16;
    static final int DLCTL_VIDEOS = 32;
    static final int DLCTL_BGSOUNDS = 64;
    static final int DLCTL_NO_SCRIPTS = 128;
    static final int DLCTL_NO_JAVA = 256;
    static final int DLCTL_NO_RUNACTIVEXCTLS = 512;
    static final int DLCTL_NO_DLACTIVEXCTLS = 1024;
    static final int DLCTL_DOWNLOADONLY = 2048;
    static final int DLCTL_NO_FRAMEDOWNLOAD = 4096;
    static final int DLCTL_RESYNCHRONIZE = 8192;
    static final int DLCTL_PRAGMA_NO_CACHE = 16384;
    static final int DLCTL_FORCEOFFLINE = 268435456;
    static final int DLCTL_NO_CLIENTPULL = 536870912;
    static final int DLCTL_SILENT = 1073741824;
    static final int DOCHOSTUIFLAG_THEME = 262144;
    static final int DOCHOSTUIFLAG_NO3DBORDER = 4;
    static final int DOCHOSTUIFLAG_NO3DOUTERBORDER = 2097152;
    static final int DOCHOSTUIFLAG_ENABLE_REDIRECT_NOTIFICATION = 67108864;
    static final int DOCHOSTUIFLAG_DPI_AWARE = 1073741824;
    static final String ABOUT_BLANK = "about:blank";
    static final String CLSID_SHELLEXPLORER1 = "{EAB22AC3-30C1-11CF-A7EB-0000C05BAE0B}";
    static final int DEFAULT_IE_VERSION = 9999;
    static final String EXTENSION_PDF = ".pdf";
    static final String HTML_DOCUMENT = "HTML Document";
    static final int MAX_PDF = 20;
    static final char SEPARATOR_OS;
    static final String PROPERTY_IEVERSION = "org.eclipse.swt.browser.IEVersion";
    static final String VALUE_DEFAULT = "default";
    static final String EVENT_DOUBLECLICK = "dblclick";
    static final String EVENT_DRAGEND = "dragend";
    static final String EVENT_DRAGSTART = "dragstart";
    static final String EVENT_KEYDOWN = "keydown";
    static final String EVENT_KEYPRESS = "keypress";
    static final String EVENT_KEYUP = "keyup";
    static final String EVENT_MOUSEMOVE = "mousemove";
    static final String EVENT_MOUSEWHEEL = "mousewheel";
    static final String EVENT_MOUSEUP = "mouseup";
    static final String EVENT_MOUSEDOWN = "mousedown";
    static final String EVENT_MOUSEOUT = "mouseout";
    static final String EVENT_MOUSEOVER = "mouseover";
    static final String PROTOCOL_FILE = "file://";
    static final String PROPERTY_ALTKEY = "altKey";
    static final String PROPERTY_BUTTON = "button";
    static final String PROPERTY_CTRLKEY = "ctrlKey";
    static final String PROPERTY_DOCUMENT = "Document";
    static final String PROPERTY_FROMELEMENT = "fromElement";
    static final String PROPERTY_KEYCODE = "keyCode";
    static final String PROPERTY_REPEAT = "repeat";
    static final String PROPERTY_RETURNVALUE = "returnValue";
    static final String PROPERTY_SCREENX = "screenX";
    static final String PROPERTY_SCREENY = "screenY";
    static final String PROPERTY_SHIFTKEY = "shiftKey";
    static final String PROPERTY_TOELEMENT = "toElement";
    static final String PROPERTY_TYPE = "type";
    static final String PROPERTY_WHEELDELTA = "wheelDelta";
    
    IE() {
        this.documents = new OleAutomation[0];
        this.addressBar = true;
        this.menuBar = true;
        this.statusBar = true;
        this.toolBar = true;
    }
    
    @Override
    public void create(final Composite parent, final int style) {
        this.style = style;
        this.frame = new OleFrame((Composite)this.browser, 0);
        try {
            this.site = new WebSite(this.frame, 0, IE.ProgId);
        }
        catch (SWTException e2) {
            this.browser.dispose();
            SWT.error(2);
        }
        if (!IE.Initialized) {
            IE.Initialized = true;
            int version = 0;
            final String versionProperty = System.getProperty("org.eclipse.swt.browser.IEVersion");
            if (versionProperty != null) {
                if (versionProperty.equalsIgnoreCase("default")) {
                    version = -1;
                }
                else {
                    try {
                        version = Integer.parseInt(versionProperty);
                    }
                    catch (NumberFormatException ex) {}
                }
            }
            if (version == 0) {
                version = ((IE.IEVersion != 0) ? ((IE.IEVersion >= 10) ? (IE.IEVersion * 1000 + 1) : ((IE.IEVersion >= 8) ? (IE.IEVersion * 1111) : (IE.IEVersion * 1000))) : 9999);
            }
            if (version != -1) {
                final long hKey = -2147483647L;
                final TCHAR subkey = new TCHAR(0, "Software\\Microsoft\\Internet Explorer\\Main\\FeatureControl\\FEATURE_BROWSER_EMULATION", true);
                final long[] key;
                if (OS.RegCreateKeyEx(hKey, subkey, 0, null, 1, 131079, 0L, key = new long[] { 0L }, null) == 0) {
                    final TCHAR lpszFile = new TCHAR(0, 260);
                    OS.GetModuleFileName(0L, lpszFile, lpszFile.length());
                    final String path = lpszFile.toString(0, lpszFile.strlen());
                    final int index = path.lastIndexOf(IE.SEPARATOR_OS);
                    final String executable = (index != -1) ? path.substring(index + 1) : path;
                    final TCHAR lpValueName = new TCHAR(0, executable, true);
                    final int result = OS.RegQueryValueEx(key[0], lpValueName, 0L, null, (int[])null, null);
                    if ((result == 0 || result == 2) && OS.RegSetValueEx(key[0], lpValueName, 0, 4, new int[] { version }, 4) == 0) {
                        final long[] key2;
                        final TCHAR lpSubKey;
                        final TCHAR lpValueName2;
                        parent.getDisplay().addListener(12, event -> {
                            key2 = new long[] { 0L };
                            if (OS.RegOpenKeyEx(-2147483647L, lpSubKey, 0, 131078, key2) == 0) {
                                OS.RegDeleteValue(key2[0], lpValueName2);
                            }
                            return;
                        });
                    }
                    OS.RegCloseKey(key[0]);
                }
            }
        }
        this.site.doVerb(-5);
        this.auto = new OleAutomation(this.site);
        this.domListener = (e -> this.handleDOMEvent(e));
        LocationListener[] oldLocationListeners;
        OleAutomation[] documents3;
        int length7;
        int j = 0;
        OleAutomation locationListener;
        Iterator elements;
        final Listener listener = e -> {
            switch (e.type) {
                case 12: {
                    if (this.ignoreDispose) {
                        this.ignoreDispose = false;
                        break;
                    }
                    else {
                        this.ignoreDispose = true;
                        this.browser.notifyListeners(e.type, e);
                        e.type = 0;
                        if (!this.browser.isClosing) {
                            oldLocationListeners = this.locationListeners;
                            this.locationListeners = new LocationListener[0];
                            this.site.ignoreAllMessages = true;
                            this.execute("window.location.href='about:blank'");
                            this.site.ignoreAllMessages = false;
                            this.locationListeners = oldLocationListeners;
                        }
                        if (!this.frame.isDisposed()) {
                            this.unhookDOMListeners(this.documents);
                        }
                        documents3 = this.documents;
                        for (length7 = documents3.length; j < length7; ++j) {
                            locationListener = documents3[j];
                            locationListener.dispose();
                        }
                        this.documents = null;
                        elements = this.functions.values().iterator();
                        while (elements.hasNext()) {
                            elements.next().dispose(false);
                        }
                        this.functions = null;
                        this.uncRedirect = null;
                        this.lastNavigateURL = null;
                        this.domListener = null;
                        if (this.auto != null) {
                            this.auto.dispose();
                        }
                        this.auto = null;
                        break;
                    }
                    break;
                }
                case 11: {
                    this.frame.setBounds(this.browser.getClientArea());
                    break;
                }
                case 37: {
                    e.doit = false;
                    break;
                }
                case 15: {
                    this.site.setFocus();
                    break;
                }
                case 31: {
                    if (e.detail == 8 && e.widget instanceof WebSite) {
                        this.browser.traverse(8, e);
                        e.doit = false;
                    }
                    if (e.detail == 4 && e.doit) {
                        if (!(e.widget instanceof Browser)) {
                            break;
                        }
                        else {
                            e.type = 0;
                            e.doit = false;
                            break;
                        }
                    }
                    else {
                        break;
                    }
                    break;
                }
            }
            return;
        };
        this.browser.addListener(12, listener);
        this.browser.addListener(15, listener);
        this.browser.addListener(11, listener);
        this.browser.addListener(31, listener);
        this.site.addListener(37, listener);
        this.site.addListener(31, listener);
        Variant varResult1;
        String url1;
        Variant cancel1;
        long l;
        TCHAR filePath1;
        TCHAR tCHAR;
        int[] size1;
        String s;
        Variant cancel2;
        long i;
        LocationEvent newEvent1;
        LocationListener[] locationListeners;
        int length8;
        int k = 0;
        LocationListener locationListener2;
        boolean bl;
        Variant cancel3;
        long pCancel3;
        Variant varResult2;
        IDispatch dispatch1;
        Variant variant1;
        IDispatch top1;
        boolean enabled;
        Variant varResult3;
        int command;
        Variant varResult4;
        boolean enabled2;
        Variant varResult5;
        IDispatch dispatch2;
        Variant varResult6;
        String url2;
        TCHAR tCHAR2;
        TCHAR urlResult2;
        int[] size2;
        String s2;
        Variant variant2;
        IDispatch top2;
        LocationEvent locationEvent;
        LocationListener[] locationListeners2;
        int length9;
        int n5 = 0;
        LocationListener locationListener3;
        int[] rgdispid1;
        Variant pVarResult1;
        int readyState;
        IE ie;
        final Iterator<BrowserFunction> iterator;
        BrowserFunction function1;
        ProgressEvent progressEvent1;
        ProgressListener[] progressListeners;
        int length10;
        int n6 = 0;
        ProgressListener progressListener;
        final Iterator<BrowserFunction> iterator2;
        BrowserFunction function2;
        ProgressEvent progressEvent2;
        ProgressListener[] progressListeners2;
        int length11;
        int n7 = 0;
        ProgressListener progressListener2;
        Variant varResult7;
        String url3;
        Variant varResult8;
        IDispatch dispatch3;
        Variant variant3;
        IDispatch top3;
        boolean isPDF;
        String path2;
        int extensionIndex;
        String extension;
        Variant varResult9;
        IDispatch dispatch4;
        OleAutomation webBrowser;
        Variant variant4;
        IDispatch top4;
        boolean isTop;
        OleAutomation[] documents2;
        int n2;
        int length6;
        OleAutomation document2;
        final Iterator<BrowserFunction> iterator3;
        BrowserFunction function3;
        Variant varResult10;
        String url4;
        Variant varResult11;
        final Object o;
        final int n8;
        int n3;
        String host;
        Variant cancel4;
        long pCancel4;
        final String s3;
        final String url5;
        Variant cancel5;
        long pCancel5;
        WindowEvent windowEvent;
        OpenWindowListener[] openWindowListeners;
        int length12;
        int n9 = 0;
        OpenWindowListener openWindowListener;
        IE browser;
        boolean doit2;
        Variant variant5;
        IDispatch iDispatch;
        Variant ppDisp;
        long byref;
        Variant arg01;
        Variant arg2;
        Variant arg3;
        Variant arg4;
        boolean visible;
        WindowEvent newEvent2;
        Variant pVarResult2;
        VisibilityWindowListener[] visibilityWindowListeners;
        int length13;
        int n10 = 0;
        VisibilityWindowListener visibilityWindowListener;
        VisibilityWindowListener[] visibilityWindowListeners2;
        int length14;
        int n11 = 0;
        VisibilityWindowListener visibilityWindowListener2;
        Variant arg5;
        int nProgress;
        Variant arg6;
        int n4;
        ProgressEvent newEvent3;
        ProgressListener[] progressListeners3;
        int length15;
        int n12 = 0;
        ProgressListener progressListener3;
        Variant arg7;
        final Object o2;
        String text;
        StatusTextEvent newEvent4;
        StatusTextListener[] statusTextListeners;
        int length16;
        int n13 = 0;
        StatusTextListener statusTextListener;
        Variant arg8;
        final Object o3;
        String title;
        TitleEvent newEvent5;
        TitleListener[] titleListeners;
        int length17;
        int n14 = 0;
        TitleListener titleListener;
        WindowEvent newEvent6;
        CloseWindowListener[] closeWindowListeners;
        int length18;
        int n15 = 0;
        CloseWindowListener closeWindowListener;
        Variant cancel6;
        long pCancel6;
        Variant variant6;
        boolean isChildWindow;
        Variant arg9;
        Variant arg10;
        Variant arg11;
        Variant arg12;
        final OleListener oleListener = event -> {
            if (this.auto != null) {
                switch (event.type) {
                    case 250: {
                        if (this.performingInitialNavigate) {
                            break;
                        }
                        else {
                            varResult1 = event.arguments[1];
                            url1 = varResult1.getString();
                            if (this.uncRedirect != null) {
                                if (this.uncRedirect.equals(url1) || (this.uncRedirect.startsWith(url1) && this.uncRedirect.indexOf(92, 2) == url1.length())) {
                                    cancel1 = event.arguments[6];
                                    if (cancel1 != null) {
                                        l = cancel1.getByRef();
                                        OS.MoveMemory(l, new short[] { 0 }, 2);
                                    }
                                    this.setAboutBlank(false);
                                    break;
                                }
                                else {
                                    this.uncRedirect = null;
                                }
                            }
                            if (url1.indexOf(":/") == -1 && url1.indexOf(":\\") != -1) {
                                filePath1 = new TCHAR(0, url1, true);
                                tCHAR = new TCHAR(0, 2084);
                                size1 = new int[] { tCHAR.length() };
                                if (OS.UrlCreateFromPath(filePath1, tCHAR, size1, 0) == 0) {
                                    s = tCHAR.toString(0, size1[0]);
                                }
                                else {
                                    s = "file://" + url1.replace('\\', '/');
                                }
                                url1 = s;
                            }
                            if (url1.startsWith("file://") && this._getUrl().startsWith("about:blank") && this.untrustedText) {
                                cancel2 = event.arguments[6];
                                if (cancel2 == null) {
                                    break;
                                }
                                else {
                                    i = cancel2.getByRef();
                                    OS.MoveMemory(i, new short[] { -1 }, 2);
                                    break;
                                }
                            }
                            else {
                                newEvent1 = new LocationEvent((Widget)this.browser);
                                newEvent1.display = this.browser.getDisplay();
                                newEvent1.widget = (Widget)this.browser;
                                newEvent1.location = url1;
                                newEvent1.doit = true;
                                locationListeners = this.locationListeners;
                                for (length8 = locationListeners.length; k < length8; ++k) {
                                    locationListener2 = locationListeners[k];
                                    locationListener2.changing(newEvent1);
                                }
                                bl = (newEvent1.doit && !this.browser.isDisposed());
                                cancel3 = event.arguments[6];
                                if (cancel3 != null) {
                                    pCancel3 = cancel3.getByRef();
                                    OS.MoveMemory(pCancel3, new short[] { (short)(bl ? 0 : -1) }, 2);
                                }
                                if (!bl) {
                                    break;
                                }
                                else {
                                    varResult2 = event.arguments[0];
                                    dispatch1 = varResult2.getDispatch();
                                    variant1 = new Variant(this.auto);
                                    top1 = variant1.getDispatch();
                                    if (top1.getAddress() != dispatch1.getAddress()) {
                                        break;
                                    }
                                    else {
                                        this.setAboutBlank(url1.startsWith("about:blank"));
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case 105: {
                        enabled = false;
                        varResult3 = event.arguments[0];
                        command = varResult3.getInt();
                        varResult4 = event.arguments[1];
                        enabled2 = varResult4.getBoolean();
                        switch (command) {
                            case 2: {
                                this.back = enabled2;
                                break;
                            }
                            case 1: {
                                this.forward = enabled2;
                                break;
                            }
                        }
                        break;
                    }
                    case 259: {
                        if (this.performingInitialNavigate) {
                            this.performingInitialNavigate = false;
                            if (this.pendingText != null) {
                                this.setText((String)this.pendingText[0], (boolean)this.pendingText[1]);
                            }
                            else if (this.pendingUrl != null) {
                                this.setUrl((String)this.pendingUrl[0], (String)this.pendingUrl[1], (String[])this.pendingUrl[2]);
                            }
                            this.pendingUrl = null;
                            this.pendingText = null;
                            break;
                        }
                        else {
                            varResult5 = event.arguments[0];
                            dispatch2 = varResult5.getDispatch();
                            varResult6 = event.arguments[1];
                            url2 = varResult6.getString();
                            if (url2.indexOf(":/") == -1 && url2.indexOf(":\\") != -1) {
                                tCHAR2 = new TCHAR(0, url2, true);
                                urlResult2 = new TCHAR(0, 2084);
                                size2 = new int[] { urlResult2.length() };
                                if (OS.UrlCreateFromPath(tCHAR2, urlResult2, size2, 0) == 0) {
                                    s2 = urlResult2.toString(0, size2[0]);
                                }
                                else {
                                    s2 = "file://" + url2.replace('\\', '/');
                                }
                                url2 = s2;
                            }
                            if (this.html != null && url2.equals("about:blank")) {
                                if (this.delaySetText) {
                                    this.delaySetText = false;
                                    this.browser.getDisplay().asyncExec(() -> {
                                        if (this.browser.isDisposed() || this.html == null) {
                                            return;
                                        }
                                        else {
                                            this.setHTML(this.html);
                                            this.html = null;
                                            return;
                                        }
                                    });
                                    break;
                                }
                                else {
                                    this.setHTML(this.html);
                                    this.html = null;
                                    break;
                                }
                            }
                            else {
                                variant2 = new Variant(this.auto);
                                top2 = variant2.getDispatch();
                                locationEvent = new LocationEvent((Widget)this.browser);
                                locationEvent.display = this.browser.getDisplay();
                                locationEvent.widget = (Widget)this.browser;
                                locationEvent.location = url2;
                                locationEvent.top = (top2.getAddress() == dispatch2.getAddress());
                                locationListeners2 = this.locationListeners;
                                for (length9 = locationListeners2.length; n5 < length9; ++n5) {
                                    locationListener3 = locationListeners2[n5];
                                    locationListener3.changed(locationEvent);
                                }
                                if (this.browser.isDisposed()) {
                                    return;
                                }
                                else {
                                    rgdispid1 = this.auto.getIDsOfNames(new String[] { "ReadyState" });
                                    pVarResult1 = this.auto.getProperty(rgdispid1[0]);
                                    if (pVarResult1 != null) {
                                        readyState = pVarResult1.getInt();
                                        pVarResult1.dispose();
                                        if (readyState != 4) {
                                            break;
                                        }
                                    }
                                    if (this.globalDispatch != 0L) {
                                        if (dispatch2.getAddress() != this.globalDispatch) {
                                            break;
                                        }
                                        else {
                                            this.globalDispatch = 0L;
                                            ie = (IE)this.browser.webBrowser;
                                            if (ie.installFunctionsOnDocumentComplete) {
                                                ie.installFunctionsOnDocumentComplete = false;
                                                this.functions.values().iterator();
                                                while (iterator.hasNext()) {
                                                    function1 = iterator.next();
                                                    this.execute(function1.functionString);
                                                }
                                            }
                                            progressEvent1 = new ProgressEvent((Widget)this.browser);
                                            progressEvent1.display = this.browser.getDisplay();
                                            progressEvent1.widget = (Widget)this.browser;
                                            progressListeners = this.progressListeners;
                                            for (length10 = progressListeners.length; n6 < length10; ++n6) {
                                                progressListener = progressListeners[n6];
                                                progressListener.completed(progressEvent1);
                                            }
                                            break;
                                        }
                                    }
                                    else {
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case 104: {
                        this.functions.values().iterator();
                        while (iterator2.hasNext()) {
                            function2 = iterator2.next();
                            this.execute(function2.functionString);
                        }
                        if (!this.isRefresh) {
                            break;
                        }
                        else {
                            this.isRefresh = false;
                            progressEvent2 = new ProgressEvent((Widget)this.browser);
                            progressEvent2.display = this.browser.getDisplay();
                            progressEvent2.widget = (Widget)this.browser;
                            progressListeners2 = this.progressListeners;
                            for (length11 = progressListeners2.length; n7 < length11; ++n7) {
                                progressListener2 = progressListeners2[n7];
                                progressListener2.completed(progressEvent2);
                            }
                            break;
                        }
                        break;
                    }
                    case 252: {
                        this.jsEnabled = this.jsEnabledOnNextPage;
                        varResult7 = event.arguments[1];
                        url3 = varResult7.getString();
                        if (!this.performingInitialNavigate) {
                            varResult8 = event.arguments[0];
                            dispatch3 = varResult8.getDispatch();
                            variant3 = new Variant(this.auto);
                            top3 = variant3.getDispatch();
                            if (top3.getAddress() == dispatch3.getAddress()) {
                                this.setAboutBlank(url3.startsWith("about:blank"));
                                this.lastNavigateURL = url3;
                            }
                        }
                        isPDF = false;
                        path2 = null;
                        try {
                            path2 = new URL(url3).getPath();
                        }
                        catch (MalformedURLException ex2) {}
                        if (path2 != null) {
                            extensionIndex = path2.lastIndexOf(46);
                            if (extensionIndex != -1) {
                                extension = path2.substring(extensionIndex);
                                if (extension.equalsIgnoreCase(".pdf")) {
                                    isPDF = true;
                                    ++IE.PDFCount;
                                    if (IE.PDFCount > 20) {
                                        COM.FreeUnusedLibraries = false;
                                    }
                                }
                            }
                        }
                        if (this.uncRedirect != null) {
                            if (this.uncRedirect.equals(url3)) {
                                this.uncRedirect = null;
                                break;
                            }
                            else if (this.uncRedirect.startsWith(url3)) {
                                this.navigate(this.uncRedirect, null, null, true);
                                break;
                            }
                            else {
                                this.uncRedirect = null;
                            }
                        }
                        varResult9 = event.arguments[0];
                        dispatch4 = varResult9.getDispatch();
                        if (this.globalDispatch == 0L) {
                            this.globalDispatch = dispatch4.getAddress();
                        }
                        webBrowser = varResult9.getAutomation();
                        variant4 = new Variant(this.auto);
                        top4 = variant4.getDispatch();
                        isTop = (top4.getAddress() == dispatch4.getAddress());
                        if (isTop) {
                            this.unhookDOMListeners(this.documents);
                            documents2 = this.documents;
                            for (n2 = 0, length6 = documents2.length; n2 < length6; ++n2) {
                                document2 = documents2[n2];
                                document2.dispose();
                            }
                            this.documents = new OleAutomation[0];
                            this.functions.values().iterator();
                            while (iterator3.hasNext()) {
                                function3 = iterator3.next();
                                this.execute(function3.functionString);
                            }
                        }
                        if (!isPDF) {
                            this.hookDOMListeners(webBrowser, isTop);
                        }
                        webBrowser.dispose();
                        break;
                    }
                    case 271: {
                        if (this.uncRedirect != null) {
                            this.uncRedirect = null;
                            break;
                        }
                        else {
                            varResult10 = event.arguments[1];
                            url4 = varResult10.getString();
                            if (url4.startsWith("\\\\")) {
                                varResult11 = event.arguments[3];
                                if (((Variant)o).getInt() == -2146697211) {
                                    url4.indexOf(92, 2);
                                    if ((n3 = n8) == -1) {
                                        break;
                                    }
                                    else {
                                        host = url4.substring(0, n3);
                                        cancel4 = event.arguments[4];
                                        if (cancel4 != null) {
                                            pCancel4 = cancel4.getByRef();
                                            OS.MoveMemory(pCancel4, new short[] { -1 }, 2);
                                        }
                                        this.browser.getDisplay().asyncExec(() -> {
                                            if (this.browser.isDisposed()) {
                                                return;
                                            }
                                            else {
                                                this.uncRedirect = (s3.endsWith("\\") ? s3.substring(0, s3.length() - 1) : s3);
                                                this.navigate(url5, null, null, true);
                                                return;
                                            }
                                        });
                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                            else {
                                break;
                            }
                        }
                        break;
                    }
                    case 251: {
                        cancel5 = event.arguments[1];
                        pCancel5 = cancel5.getByRef();
                        windowEvent = new WindowEvent((Widget)this.browser);
                        windowEvent.display = this.browser.getDisplay();
                        windowEvent.widget = (Widget)this.browser;
                        windowEvent.required = false;
                        openWindowListeners = this.openWindowListeners;
                        for (length12 = openWindowListeners.length; n9 < length12; ++n9) {
                            openWindowListener = openWindowListeners[n9];
                            openWindowListener.open(windowEvent);
                        }
                        browser = null;
                        if (windowEvent.browser != null && windowEvent.browser.webBrowser instanceof IE) {
                            browser = (IE)windowEvent.browser.webBrowser;
                        }
                        doit2 = (browser != null && !browser.browser.isDisposed());
                        if (doit2) {
                            browser.installFunctionsOnDocumentComplete = true;
                            variant5 = new Variant(browser.auto);
                            iDispatch = variant5.getDispatch();
                            ppDisp = event.arguments[0];
                            byref = ppDisp.getByRef();
                            if (byref != 0L) {
                                OS.MoveMemory(byref, new long[] { iDispatch.getAddress() }, C.PTR_SIZEOF);
                            }
                        }
                        if (!windowEvent.required) {
                            break;
                        }
                        else {
                            OS.MoveMemory(pCancel5, new short[] { (short)(doit2 ? 0 : -1) }, 2);
                            break;
                        }
                        break;
                    }
                    case 256: {
                        arg01 = event.arguments[0];
                        this.menuBar = arg01.getBoolean();
                        break;
                    }
                    case 257: {
                        arg2 = event.arguments[0];
                        this.statusBar = arg2.getBoolean();
                        break;
                    }
                    case 255: {
                        arg3 = event.arguments[0];
                        this.toolBar = arg3.getBoolean();
                        if (this.toolBar) {
                            break;
                        }
                        else {
                            this.addressBar = false;
                            this.menuBar = false;
                            break;
                        }
                        break;
                    }
                    case 254: {
                        arg4 = event.arguments[0];
                        visible = arg4.getBoolean();
                        newEvent2 = new WindowEvent((Widget)this.browser);
                        newEvent2.display = this.browser.getDisplay();
                        newEvent2.widget = (Widget)this.browser;
                        if (visible) {
                            if (this.addressBar) {
                                if ((pVarResult2 = this.auto.getProperty(this.auto.getIDsOfNames(new String[] { "AddressBar" })[0])) != null) {
                                    if (pVarResult2.getType() == 11) {
                                        this.addressBar = pVarResult2.getBoolean();
                                    }
                                    pVarResult2.dispose();
                                }
                            }
                            newEvent2.addressBar = this.addressBar;
                            newEvent2.menuBar = this.menuBar;
                            newEvent2.statusBar = this.statusBar;
                            newEvent2.toolBar = this.toolBar;
                            newEvent2.location = this.location;
                            newEvent2.size = this.size;
                            visibilityWindowListeners = this.visibilityWindowListeners;
                            for (length13 = visibilityWindowListeners.length; n10 < length13; ++n10) {
                                visibilityWindowListener = visibilityWindowListeners[n10];
                                visibilityWindowListener.show(newEvent2);
                            }
                            this.location = null;
                            this.size = null;
                            break;
                        }
                        else {
                            visibilityWindowListeners2 = this.visibilityWindowListeners;
                            for (length14 = visibilityWindowListeners2.length; n11 < length14; ++n11) {
                                visibilityWindowListener2 = visibilityWindowListeners2[n11];
                                visibilityWindowListener2.hide(newEvent2);
                            }
                            break;
                        }
                        break;
                    }
                    case 108: {
                        if (this.performingInitialNavigate) {
                            break;
                        }
                        else {
                            arg5 = event.arguments[0];
                            nProgress = ((arg5.getType() != 3) ? 0 : arg5.getInt());
                            arg6 = event.arguments[1];
                            n4 = ((arg6.getType() != 3) ? 0 : arg6.getInt());
                            newEvent3 = new ProgressEvent((Widget)this.browser);
                            newEvent3.display = this.browser.getDisplay();
                            newEvent3.widget = (Widget)this.browser;
                            newEvent3.current = nProgress;
                            newEvent3.total = n4;
                            if (nProgress == -1) {
                                break;
                            }
                            else {
                                progressListeners3 = this.progressListeners;
                                for (length15 = progressListeners3.length; n12 < length15; ++n12) {
                                    progressListener3 = progressListeners3[n12];
                                    progressListener3.changed(newEvent3);
                                }
                                break;
                            }
                        }
                        break;
                    }
                    case 102: {
                        if (!this.performingInitialNavigate) {
                            arg7 = event.arguments[0];
                            if (((Variant)o2).getType() != 8) {
                                break;
                            }
                            else {
                                text = arg7.getString();
                                newEvent4 = new StatusTextEvent((Widget)this.browser);
                                newEvent4.display = this.browser.getDisplay();
                                newEvent4.widget = (Widget)this.browser;
                                newEvent4.text = text;
                                statusTextListeners = this.statusTextListeners;
                                for (length16 = statusTextListeners.length; n13 < length16; ++n13) {
                                    statusTextListener = statusTextListeners[n13];
                                    statusTextListener.changed(newEvent4);
                                }
                                break;
                            }
                        }
                        else {
                            break;
                        }
                        break;
                    }
                    case 113: {
                        if (!this.performingInitialNavigate) {
                            arg8 = event.arguments[0];
                            if (((Variant)o3).getType() != 8) {
                                break;
                            }
                            else {
                                title = arg8.getString();
                                newEvent5 = new TitleEvent((Widget)this.browser);
                                newEvent5.display = this.browser.getDisplay();
                                newEvent5.widget = (Widget)this.browser;
                                newEvent5.title = title;
                                titleListeners = this.titleListeners;
                                for (length17 = titleListeners.length; n14 < length17; ++n14) {
                                    titleListener = titleListeners[n14];
                                    titleListener.changed(newEvent5);
                                }
                                break;
                            }
                        }
                        else {
                            break;
                        }
                        break;
                    }
                    case 263: {
                        this.browser.getDisplay().asyncExec(() -> {
                            if (this.browser.isDisposed()) {
                                return;
                            }
                            else {
                                newEvent6 = new WindowEvent((Widget)this.browser);
                                newEvent6.display = this.browser.getDisplay();
                                newEvent6.widget = (Widget)this.browser;
                                closeWindowListeners = this.closeWindowListeners;
                                for (length18 = closeWindowListeners.length; n15 < length18; ++n15) {
                                    closeWindowListener = closeWindowListeners[n15];
                                    closeWindowListener.close(newEvent6);
                                }
                                this.browser.dispose();
                                return;
                            }
                        });
                        cancel6 = event.arguments[1];
                        pCancel6 = cancel6.getByRef();
                        variant6 = event.arguments[0];
                        isChildWindow = variant6.getBoolean();
                        OS.MoveMemory(pCancel6, new short[] { (short)(isChildWindow ? 0 : -1) }, 2);
                        break;
                    }
                    case 267: {
                        if (this.size == null) {
                            this.size = new Point(0, 0);
                        }
                        arg9 = event.arguments[0];
                        this.size.y = arg9.getInt();
                        break;
                    }
                    case 264: {
                        if (this.location == null) {
                            this.location = new Point(0, 0);
                        }
                        arg10 = event.arguments[0];
                        this.location.x = arg10.getInt();
                        break;
                    }
                    case 265: {
                        if (this.location == null) {
                            this.location = new Point(0, 0);
                        }
                        arg11 = event.arguments[0];
                        this.location.y = arg11.getInt();
                        break;
                    }
                    case 266: {
                        if (this.size == null) {
                            this.size = new Point(0, 0);
                        }
                        arg12 = event.arguments[0];
                        this.size.x = arg12.getInt();
                        break;
                    }
                }
            }
            return;
        };
        this.site.addEventListener(250, oleListener);
        this.site.addEventListener(105, oleListener);
        this.site.addEventListener(259, oleListener);
        this.site.addEventListener(104, oleListener);
        this.site.addEventListener(252, oleListener);
        this.site.addEventListener(271, oleListener);
        this.site.addEventListener(251, oleListener);
        this.site.addEventListener(256, oleListener);
        this.site.addEventListener(257, oleListener);
        this.site.addEventListener(255, oleListener);
        this.site.addEventListener(254, oleListener);
        this.site.addEventListener(108, oleListener);
        this.site.addEventListener(102, oleListener);
        this.site.addEventListener(113, oleListener);
        this.site.addEventListener(263, oleListener);
        this.site.addEventListener(267, oleListener);
        this.site.addEventListener(264, oleListener);
        this.site.addEventListener(265, oleListener);
        this.site.addEventListener(266, oleListener);
        Variant variant7 = new Variant(true);
        this.auto.setProperty(552, variant7);
        variant7.dispose();
        variant7 = new Variant(false);
        final int[] rgdispid2 = this.auto.getIDsOfNames(new String[] { "RegisterAsDropTarget" });
        if (rgdispid2 != null) {
            this.auto.setProperty(rgdispid2[0], variant7);
        }
        variant7.dispose();
    }
    
    @Override
    public boolean back() {
        if (!this.back) {
            return false;
        }
        final int[] rgdispid = this.auto.getIDsOfNames(new String[] { "GoBack" });
        final Variant pVarResult = this.auto.invoke(rgdispid[0]);
        return pVarResult != null && pVarResult.getType() == 0;
    }
    
    @Override
    public boolean close() {
        boolean result = true;
        int[] rgdispid = this.auto.getIDsOfNames(new String[] { "Document" });
        int dispIdMember = rgdispid[0];
        Variant pVarResult = this.auto.getProperty(dispIdMember);
        if (pVarResult == null || pVarResult.getType() == 0) {
            if (pVarResult != null) {
                pVarResult.dispose();
            }
        }
        else {
            final OleAutomation document = pVarResult.getAutomation();
            pVarResult.dispose();
            rgdispid = document.getIDsOfNames(new String[] { "parentWindow" });
            if (rgdispid != null) {
                dispIdMember = rgdispid[0];
                pVarResult = document.getProperty(dispIdMember);
                if (pVarResult == null || pVarResult.getType() == 0) {
                    if (pVarResult != null) {
                        pVarResult.dispose();
                    }
                }
                else {
                    final OleAutomation window = pVarResult.getAutomation();
                    pVarResult.dispose();
                    rgdispid = window.getIDsOfNames(new String[] { "location" });
                    dispIdMember = rgdispid[0];
                    pVarResult = window.getProperty(dispIdMember);
                    if (pVarResult == null || pVarResult.getType() == 0) {
                        if (pVarResult != null) {
                            pVarResult.dispose();
                        }
                    }
                    else {
                        final OleAutomation location = pVarResult.getAutomation();
                        pVarResult.dispose();
                        final LocationListener[] oldListeners = this.locationListeners;
                        this.locationListeners = new LocationListener[0];
                        rgdispid = location.getIDsOfNames(new String[] { "replace" });
                        dispIdMember = rgdispid[0];
                        final Variant[] args = { new Variant("about:blank") };
                        pVarResult = location.invoke(dispIdMember, args);
                        if (pVarResult == null) {
                            result = false;
                        }
                        else {
                            pVarResult.dispose();
                        }
                        args[0].dispose();
                        this.locationListeners = oldListeners;
                        location.dispose();
                    }
                    window.dispose();
                }
            }
            document.dispose();
        }
        return result;
    }
    
    static Variant createSafeArray(final String string) {
        final byte[] bytes = string.getBytes();
        final int length = bytes.length;
        final long pvData = OS.GlobalAlloc(64, length);
        C.memmove(pvData, bytes, length);
        final int cElements1 = length;
        final long pSafeArray = OS.GlobalAlloc(64, SAFEARRAY.sizeof);
        final SAFEARRAY safeArray = new SAFEARRAY();
        safeArray.cDims = 1;
        safeArray.fFeatures = 16;
        safeArray.cbElements = 1;
        safeArray.pvData = pvData;
        final SAFEARRAYBOUND safeArrayBound = safeArray.rgsabound = new SAFEARRAYBOUND();
        safeArrayBound.cElements = cElements1;
        OS.MoveMemory(pSafeArray, safeArray, SAFEARRAY.sizeof);
        final long pVariant = OS.GlobalAlloc(64, Variant.sizeof);
        final short vt = 8209;
        OS.MoveMemory(pVariant, new short[] { vt }, 2);
        OS.MoveMemory(pVariant + 8L, new long[] { pSafeArray }, C.PTR_SIZEOF);
        return new Variant(pVariant, (short)16396);
    }
    
    @Override
    public boolean execute(final String script) {
        if (!this.performingInitialNavigate && this._getUrl().length() == 0) {
            this.navigate("about:blank", null, null, this.performingInitialNavigate = true);
        }
        int[] rgdispid;
        int dispIdMember;
        Variant pVarResult;
        if ((pVarResult = this.auto.getProperty(dispIdMember = (rgdispid = this.auto.getIDsOfNames(new String[] { "Document" }))[0])) == null || pVarResult.getType() == 0) {
            if (pVarResult != null) {
                pVarResult.dispose();
            }
            return false;
        }
        final OleAutomation document = pVarResult.getAutomation();
        pVarResult.dispose();
        rgdispid = document.getIDsOfNames(new String[] { "parentWindow" });
        if (rgdispid == null) {
            document.dispose();
            return false;
        }
        dispIdMember = rgdispid[0];
        pVarResult = document.getProperty(dispIdMember);
        if (pVarResult == null || pVarResult.getType() == 0) {
            if (pVarResult != null) {
                pVarResult.dispose();
            }
            document.dispose();
            return false;
        }
        final OleAutomation ihtmlWindow2 = pVarResult.getAutomation();
        pVarResult.dispose();
        document.dispose();
        rgdispid = ihtmlWindow2.getIDsOfNames(new String[] { "execScript", "code" });
        if (rgdispid == null) {
            ihtmlWindow2.dispose();
            return false;
        }
        final Variant[] rgvarg = { new Variant(script) };
        final int[] rgdispidNamedArgs = { rgdispid[1] };
        pVarResult = ihtmlWindow2.invoke(rgdispid[0], rgvarg, rgdispidNamedArgs);
        rgvarg[0].dispose();
        ihtmlWindow2.dispose();
        if (pVarResult == null) {
            return false;
        }
        pVarResult.dispose();
        return true;
    }
    
    @Override
    public boolean forward() {
        if (!this.forward) {
            return false;
        }
        final int[] rgdispid = this.auto.getIDsOfNames(new String[] { "GoForward" });
        final Variant pVarResult = this.auto.invoke(rgdispid[0]);
        return pVarResult != null && pVarResult.getType() == 0;
    }
    
    @Override
    public String getBrowserType() {
        return "ie";
    }
    
    @Override
    String getDeleteFunctionString(final String functionName) {
        return "window." + functionName + "=undefined";
    }
    
    @Override
    public String getText() {
        int[] rgdispid = this.auto.getIDsOfNames(new String[] { "Document" });
        Variant pVarResult = this.auto.getProperty(rgdispid[0]);
        if (pVarResult == null || pVarResult.getType() == 0) {
            if (pVarResult != null) {
                pVarResult.dispose();
            }
            return "";
        }
        final OleAutomation document = pVarResult.getAutomation();
        pVarResult.dispose();
        rgdispid = document.getIDsOfNames(new String[] { "documentElement" });
        if (rgdispid == null) {
            document.dispose();
            return "";
        }
        pVarResult = document.getProperty(rgdispid[0]);
        document.dispose();
        if (pVarResult == null || pVarResult.getType() == 0 || pVarResult.getType() == 1) {
            if (pVarResult != null) {
                pVarResult.dispose();
            }
            return "";
        }
        final OleAutomation element = pVarResult.getAutomation();
        pVarResult.dispose();
        rgdispid = element.getIDsOfNames(new String[] { "outerHTML" });
        pVarResult = element.getProperty(rgdispid[0]);
        element.dispose();
        if (pVarResult == null || pVarResult.getType() == 0) {
            if (pVarResult != null) {
                pVarResult.dispose();
            }
            return "";
        }
        final String result = pVarResult.getString();
        pVarResult.dispose();
        return result;
    }
    
    @Override
    public String getUrl() {
        final String result = this._getUrl();
        return (result.length() != 0) ? result : "about:blank";
    }
    
    String _getUrl() {
        final int[] rgdispid = this.auto.getIDsOfNames(new String[] { "LocationURL" });
        final Variant pVarResult = this.auto.getProperty(rgdispid[0]);
        if (pVarResult == null || pVarResult.getType() != 8) {
            return "";
        }
        final String result = pVarResult.getString();
        pVarResult.dispose();
        return result;
    }
    
    @Override
    public boolean isBackEnabled() {
        return this.back;
    }
    
    @Override
    public boolean isForwardEnabled() {
        return this.forward;
    }
    
    @Override
    public boolean isFocusControl() {
        return this.site.isFocusControl() || this.frame.isFocusControl();
    }
    
    boolean navigate(final String url, final String postData, final String[] headers, final boolean silent) {
        int count = 1;
        if (postData != null) {
            ++count;
        }
        if (headers != null) {
            ++count;
        }
        final Variant[] rgvarg = new Variant[count];
        final int[] rgdispidNamedArgs = new int[count];
        final int[] rgdispid = this.auto.getIDsOfNames(new String[] { "Navigate", "URL", "PostData", "Headers" });
        int index = 0;
        rgvarg[index] = new Variant(url);
        rgdispidNamedArgs[index++] = rgdispid[1];
        if (postData != null) {
            rgvarg[index] = createSafeArray(postData);
            rgdispidNamedArgs[index++] = rgdispid[2];
        }
        if (headers != null) {
            final StringBuilder buffer = new StringBuilder();
            for (final String current : headers) {
                if (current != null) {
                    final int sep;
                    if ((sep = current.indexOf(58)) != -1) {
                        final String key = current.substring(0, sep).trim();
                        final String value = current.substring(sep + 1).trim();
                        if (key.length() > 0) {
                            if (value.length() > 0) {
                                buffer.append(key);
                                buffer.append(':');
                                buffer.append(value);
                                buffer.append("\r\n");
                            }
                        }
                    }
                }
            }
            rgvarg[index] = new Variant(buffer.toString());
            rgdispidNamedArgs[index++] = rgdispid[3];
        }
        boolean oldValue = false;
        if (silent && IE.IEVersion >= 7) {
            final int hResult = OS.CoInternetIsFeatureEnabled(21, 2);
            oldValue = (hResult == 0);
            OS.CoInternetSetFeatureEnabled(21, 2, true);
        }
        final Variant pVarResult = this.auto.invoke(rgdispid[0], rgvarg, rgdispidNamedArgs);
        if (silent && IE.IEVersion >= 7) {
            OS.CoInternetSetFeatureEnabled(21, 2, oldValue);
        }
        for (int i = 0; i < count; ++i) {
            rgvarg[i].dispose();
        }
        if (pVarResult == null) {
            return false;
        }
        final boolean result = pVarResult.getType() == 0;
        pVarResult.dispose();
        return result;
    }
    
    @Override
    public void refresh() {
        this.uncRedirect = null;
        final String url = this._getUrl();
        final int extensionIndex = url.lastIndexOf(46);
        if (extensionIndex != -1 && url.substring(extensionIndex).equalsIgnoreCase(".pdf") && ++IE.PDFCount > 20) {
            COM.FreeUnusedLibraries = false;
        }
        this.isRefresh = true;
        final int[] rgdispid = this.auto.getIDsOfNames(new String[] { "Refresh" });
        this.auto.invoke(rgdispid[0]);
    }
    
    void setHTML(final String string) {
        final int charCount = string.length();
        final char[] chars = new char[charCount];
        string.getChars(0, charCount, chars, 0);
        final int byteCount = OS.WideCharToMultiByte(65001, 0, chars, charCount, null, 0, null, null);
        final byte[] UTF8BOM = { -17, -69, -65 };
        final long hGlobal = OS.GlobalAlloc(64, UTF8BOM.length + byteCount);
        if (hGlobal != 0L) {
            OS.MoveMemory(hGlobal, UTF8BOM, UTF8BOM.length);
            OS.WideCharToMultiByte(65001, 0, chars, charCount, hGlobal + UTF8BOM.length, byteCount, null, null);
            final long[] ppstm = { 0L };
            if (OS.CreateStreamOnHGlobal(hGlobal, true, ppstm) == 0) {
                final int[] rgdispid = this.auto.getIDsOfNames(new String[] { "Document" });
                final Variant pVarResult = this.auto.getProperty(rgdispid[0]);
                final IDispatch dispatchDocument = pVarResult.getDispatch();
                final long[] ppvObject;
                final int result = dispatchDocument.QueryInterface(COM.IIDIPersistStreamInit, ppvObject = new long[] { 0L });
                if (result == 0) {
                    final IPersistStreamInit persistStreamInit = new IPersistStreamInit(ppvObject[0]);
                    if (persistStreamInit.InitNew() == 0) {
                        persistStreamInit.Load(ppstm[0]);
                    }
                    persistStreamInit.Release();
                }
                pVarResult.dispose();
                final IUnknown stream = new IUnknown(ppstm[0]);
                stream.Release();
            }
            else {
                OS.GlobalFree(hGlobal);
            }
        }
    }
    
    private void setAboutBlank(final boolean value) {
        this.isAboutBlank = value;
        this.updateForceTrusted();
    }
    
    private void setUntrustedText(final boolean value) {
        this.untrustedText = value;
        this.updateForceTrusted();
    }
    
    private void updateForceTrusted() {
        this.site.isForceTrusted = (this.isAboutBlank && !this.untrustedText);
    }
    
    @Override
    public boolean setText(final String html, final boolean trusted) {
        if (this.performingInitialNavigate) {
            this.pendingText = new Object[] { html, trusted };
            this.pendingUrl = null;
            return true;
        }
        final boolean blankLoading = this.html != null;
        this.html = html;
        this.setUntrustedText(!trusted);
        if (blankLoading) {
            return true;
        }
        if (this._getUrl().length() != 0) {
            int[] rgdispid = this.auto.getIDsOfNames(new String[] { "ReadyState" });
            final Variant pVarResult = this.auto.getProperty(rgdispid[0]);
            if (pVarResult == null) {
                return false;
            }
            this.delaySetText = (pVarResult.getInt() != 4);
            pVarResult.dispose();
            rgdispid = this.auto.getIDsOfNames(new String[] { "Stop" });
            this.auto.invoke(rgdispid[0]);
        }
        int[] rgdispid = this.auto.getIDsOfNames(new String[] { "Navigate", "URL" });
        final Variant[] rgvarg = { new Variant("about:blank") };
        final int[] rgdispidNamedArgs = { rgdispid[1] };
        boolean oldValue = false;
        if (IE.IEVersion >= 7) {
            final int hResult = OS.CoInternetIsFeatureEnabled(21, 2);
            oldValue = (hResult == 0);
            OS.CoInternetSetFeatureEnabled(21, 2, true);
        }
        final Variant pVarResult2 = this.auto.invoke(rgdispid[0], rgvarg, rgdispidNamedArgs);
        if (IE.IEVersion >= 7) {
            OS.CoInternetSetFeatureEnabled(21, 2, oldValue);
        }
        rgvarg[0].dispose();
        if (pVarResult2 == null) {
            return false;
        }
        final boolean result = pVarResult2.getType() == 0;
        pVarResult2.dispose();
        return result;
    }
    
    @Override
    public boolean setUrl(final String url, final String postData, final String[] headers) {
        this.uncRedirect = null;
        this.html = null;
        if (this._getUrl().length() == 0 && !"about:blank".equalsIgnoreCase(url)) {
            this.pendingText = null;
            this.pendingUrl = new Object[] { url, postData, headers };
            this.navigate("about:blank", null, null, this.performingInitialNavigate = true);
            return true;
        }
        if (url.endsWith(".xml")) {
            final int[] rgdispid = this.auto.getIDsOfNames(new String[] { "Stop" });
            this.auto.invoke(rgdispid[0]);
        }
        return this.navigate(url, postData, headers, false);
    }
    
    @Override
    public void stop() {
        if (this.performingInitialNavigate) {
            this.pendingUrl = null;
            this.pendingText = null;
            return;
        }
        if (this._getUrl().length() == 0) {
            return;
        }
        this.setAboutBlank(this.getUrl().startsWith("about:blank"));
        this.uncRedirect = null;
        final int[] rgdispid = this.auto.getIDsOfNames(new String[] { "Stop" });
        this.auto.invoke(rgdispid[0]);
    }
    
    @Override
    boolean translateMnemonics() {
        return false;
    }
    
    void handleDOMEvent(final OleEvent e) {
        if (e.arguments == null || e.arguments.length == 0) {
            return;
        }
        final Variant arg = e.arguments[0];
        final OleAutomation event = arg.getAutomation();
        int[] rgdispid = event.getIDsOfNames(new String[] { "type" });
        int dispIdMember = rgdispid[0];
        Variant pVarResult = event.getProperty(dispIdMember);
        final String eventType = pVarResult.getString();
        pVarResult.dispose();
        if (eventType.equals("keydown")) {
            rgdispid = event.getIDsOfNames(new String[] { "keyCode" });
            dispIdMember = rgdispid[0];
            pVarResult = event.getProperty(dispIdMember);
            this.lastKeyCode = this.translateKey(pVarResult.getInt());
            pVarResult.dispose();
            rgdispid = event.getIDsOfNames(new String[] { "returnValue" });
            pVarResult = event.getProperty(rgdispid[0]);
            final boolean consume = pVarResult != null && pVarResult.getType() == 11 && !pVarResult.getBoolean();
            pVarResult.dispose();
            final MSG msg = new MSG();
            final int flags = 0x2 | (consume ? 1 : 0);
            if (OS.PeekMessage(msg, this.frame.handle, 258, 258, flags)) {
                event.dispose();
                return;
            }
            if (consume) {
                event.dispose();
                return;
            }
            rgdispid = event.getIDsOfNames(new String[] { "repeat" });
            dispIdMember = rgdispid[0];
            pVarResult = event.getProperty(dispIdMember);
            final boolean repeating = pVarResult.getBoolean();
            pVarResult.dispose();
            if (repeating) {
                event.dispose();
                return;
            }
            int mask = 0;
            rgdispid = event.getIDsOfNames(new String[] { "altKey" });
            dispIdMember = rgdispid[0];
            pVarResult = event.getProperty(dispIdMember);
            if (pVarResult.getBoolean()) {
                mask |= 0x10000;
            }
            pVarResult.dispose();
            rgdispid = event.getIDsOfNames(new String[] { "ctrlKey" });
            dispIdMember = rgdispid[0];
            pVarResult = event.getProperty(dispIdMember);
            if (pVarResult.getBoolean()) {
                mask |= 0x40000;
            }
            pVarResult.dispose();
            rgdispid = event.getIDsOfNames(new String[] { "shiftKey" });
            dispIdMember = rgdispid[0];
            pVarResult = event.getProperty(dispIdMember);
            if (pVarResult.getBoolean()) {
                mask |= 0x20000;
            }
            pVarResult.dispose();
            final Event keyEvent = new Event();
            keyEvent.widget = (Widget)this.browser;
            keyEvent.type = 1;
            keyEvent.keyCode = this.lastKeyCode;
            keyEvent.stateMask = mask;
            final Event event2 = keyEvent;
            event2.stateMask &= ~this.lastKeyCode;
            switch (this.lastKeyCode) {
                case 8: {
                    keyEvent.character = '\b';
                    this.lastCharCode = 8;
                    break;
                }
                case 13: {
                    keyEvent.character = '\r';
                    this.lastCharCode = 13;
                    break;
                }
                case 127: {
                    keyEvent.character = '\u007f';
                    this.lastCharCode = 127;
                    break;
                }
                case 9: {
                    keyEvent.character = '\t';
                    this.lastCharCode = 9;
                    break;
                }
            }
            if (!this.sendKeyEvent(keyEvent)) {
                rgdispid = event.getIDsOfNames(new String[] { "returnValue" });
                dispIdMember = rgdispid[0];
                final Variant pVarFalse = new Variant(false);
                event.setProperty(dispIdMember, pVarFalse);
                pVarFalse.dispose();
            }
            if (this.lastKeyCode == 16777230) {
                this.isRefresh = true;
            }
            event.dispose();
        }
        else if (eventType.equals("keypress")) {
            int mask2 = 0;
            rgdispid = event.getIDsOfNames(new String[] { "ctrlKey" });
            dispIdMember = rgdispid[0];
            pVarResult = event.getProperty(dispIdMember);
            if (pVarResult.getBoolean()) {
                mask2 |= 0x40000;
            }
            pVarResult.dispose();
            rgdispid = event.getIDsOfNames(new String[] { "shiftKey" });
            dispIdMember = rgdispid[0];
            pVarResult = event.getProperty(dispIdMember);
            if (pVarResult.getBoolean()) {
                mask2 |= 0x20000;
            }
            pVarResult.dispose();
            rgdispid = event.getIDsOfNames(new String[] { "altKey" });
            dispIdMember = rgdispid[0];
            pVarResult = event.getProperty(dispIdMember);
            if (pVarResult.getBoolean()) {
                mask2 |= 0x10000;
            }
            pVarResult.dispose();
            rgdispid = event.getIDsOfNames(new String[] { "keyCode" });
            dispIdMember = rgdispid[0];
            pVarResult = event.getProperty(dispIdMember);
            this.lastCharCode = pVarResult.getInt();
            pVarResult.dispose();
            if (this.lastCharCode == 13 || this.lastCharCode == 10) {
                event.dispose();
                return;
            }
            final Event keyEvent2 = new Event();
            keyEvent2.widget = (Widget)this.browser;
            keyEvent2.type = 1;
            keyEvent2.keyCode = this.lastKeyCode;
            keyEvent2.character = (char)this.lastCharCode;
            keyEvent2.stateMask = mask2;
            if (!this.sendKeyEvent(keyEvent2)) {
                rgdispid = event.getIDsOfNames(new String[] { "returnValue" });
                dispIdMember = rgdispid[0];
                final Variant pVarFalse2 = new Variant(false);
                event.setProperty(dispIdMember, pVarFalse2);
                pVarFalse2.dispose();
            }
            event.dispose();
        }
        else {
            if (!eventType.equals("keyup")) {
                if (eventType.equals("mouseover")) {
                    rgdispid = event.getIDsOfNames(new String[] { "fromElement" });
                    dispIdMember = rgdispid[0];
                    pVarResult = event.getProperty(dispIdMember);
                    final boolean isInternal = pVarResult.getType() != 0;
                    pVarResult.dispose();
                    if (isInternal) {
                        event.dispose();
                        return;
                    }
                }
                if (eventType.equals("mouseout")) {
                    rgdispid = event.getIDsOfNames(new String[] { "toElement" });
                    dispIdMember = rgdispid[0];
                    pVarResult = event.getProperty(dispIdMember);
                    final boolean isInternal = pVarResult.getType() != 0;
                    pVarResult.dispose();
                    if (isInternal) {
                        event.dispose();
                        return;
                    }
                }
                int mask2 = 0;
                Event newEvent = new Event();
                newEvent.widget = (Widget)this.browser;
                rgdispid = event.getIDsOfNames(new String[] { "screenX" });
                dispIdMember = rgdispid[0];
                pVarResult = event.getProperty(dispIdMember);
                final int screenX = pVarResult.getInt();
                pVarResult.dispose();
                rgdispid = event.getIDsOfNames(new String[] { "screenY" });
                dispIdMember = rgdispid[0];
                pVarResult = event.getProperty(dispIdMember);
                final int screenY = pVarResult.getInt();
                pVarResult.dispose();
                Point position = DPIUtil.autoScaleDown(new Point(screenX, screenY));
                position = this.browser.getDisplay().map(null, (Control)this.browser, position);
                newEvent.x = position.x;
                newEvent.y = position.y;
                rgdispid = event.getIDsOfNames(new String[] { "ctrlKey" });
                dispIdMember = rgdispid[0];
                pVarResult = event.getProperty(dispIdMember);
                if (pVarResult.getBoolean()) {
                    mask2 |= 0x40000;
                }
                pVarResult.dispose();
                rgdispid = event.getIDsOfNames(new String[] { "altKey" });
                dispIdMember = rgdispid[0];
                pVarResult = event.getProperty(dispIdMember);
                if (pVarResult.getBoolean()) {
                    mask2 |= 0x10000;
                }
                pVarResult.dispose();
                rgdispid = event.getIDsOfNames(new String[] { "shiftKey" });
                dispIdMember = rgdispid[0];
                pVarResult = event.getProperty(dispIdMember);
                if (pVarResult.getBoolean()) {
                    mask2 |= 0x20000;
                }
                pVarResult.dispose();
                newEvent.stateMask = mask2;
                rgdispid = event.getIDsOfNames(new String[] { "button" });
                dispIdMember = rgdispid[0];
                pVarResult = event.getProperty(dispIdMember);
                int button = pVarResult.getInt();
                pVarResult.dispose();
                switch (button) {
                    case 1: {
                        button = 1;
                        break;
                    }
                    case 2: {
                        button = 3;
                        break;
                    }
                    case 4: {
                        button = 2;
                        break;
                    }
                }
                if (eventType.equals("mousedown")) {
                    newEvent.type = 3;
                    newEvent.button = button;
                    newEvent.count = 1;
                }
                else if (eventType.equals("mouseup") || eventType.equals("dragend")) {
                    newEvent.type = 4;
                    newEvent.button = ((button != 0) ? button : 1);
                    newEvent.count = 1;
                    switch (newEvent.button) {
                        case 1: {
                            final Event event3 = newEvent;
                            event3.stateMask |= 0x80000;
                            break;
                        }
                        case 2: {
                            final Event event4 = newEvent;
                            event4.stateMask |= 0x100000;
                            break;
                        }
                        case 3: {
                            final Event event5 = newEvent;
                            event5.stateMask |= 0x200000;
                            break;
                        }
                        case 4: {
                            final Event event6 = newEvent;
                            event6.stateMask |= 0x800000;
                            break;
                        }
                        case 5: {
                            final Event event7 = newEvent;
                            event7.stateMask |= 0x2000000;
                            break;
                        }
                    }
                }
                else if (eventType.equals("mousewheel")) {
                    newEvent.type = 37;
                    rgdispid = event.getIDsOfNames(new String[] { "wheelDelta" });
                    dispIdMember = rgdispid[0];
                    pVarResult = event.getProperty(dispIdMember);
                    newEvent.count = pVarResult.getInt() / 120 * 3;
                    pVarResult.dispose();
                }
                else if (eventType.equals("mousemove")) {
                    if (newEvent.x == this.lastMouseMoveX && newEvent.y == this.lastMouseMoveY) {
                        event.dispose();
                        return;
                    }
                    newEvent.type = 5;
                    this.lastMouseMoveX = newEvent.x;
                    this.lastMouseMoveY = newEvent.y;
                }
                else if (eventType.equals("mouseover")) {
                    newEvent.type = 6;
                }
                else if (eventType.equals("mouseout")) {
                    newEvent.type = 7;
                }
                else if (eventType.equals("dragstart")) {
                    newEvent.type = 29;
                    newEvent.button = 1;
                    final Event event8 = newEvent;
                    event8.stateMask |= 0x80000;
                }
                event.dispose();
                this.browser.notifyListeners(newEvent.type, newEvent);
                if (eventType.equals("dblclick")) {
                    newEvent = new Event();
                    newEvent.widget = (Widget)this.browser;
                    newEvent.type = 8;
                    newEvent.x = position.x;
                    newEvent.y = position.y;
                    newEvent.stateMask = mask2;
                    newEvent.type = 8;
                    newEvent.button = 1;
                    newEvent.count = 2;
                    this.browser.notifyListeners(newEvent.type, newEvent);
                }
                return;
            }
            rgdispid = event.getIDsOfNames(new String[] { "keyCode" });
            dispIdMember = rgdispid[0];
            pVarResult = event.getProperty(dispIdMember);
            final int keyCode = this.translateKey(pVarResult.getInt());
            pVarResult.dispose();
            if (keyCode == 0) {
                this.lastCharCode = 0;
                this.lastKeyCode = 0;
                event.dispose();
                return;
            }
            if (keyCode != this.lastKeyCode) {
                this.lastKeyCode = keyCode;
                this.lastCharCode = 0;
            }
            int mask3 = 0;
            rgdispid = event.getIDsOfNames(new String[] { "ctrlKey" });
            dispIdMember = rgdispid[0];
            pVarResult = event.getProperty(dispIdMember);
            if (pVarResult.getBoolean()) {
                mask3 |= 0x40000;
            }
            pVarResult.dispose();
            rgdispid = event.getIDsOfNames(new String[] { "altKey" });
            dispIdMember = rgdispid[0];
            pVarResult = event.getProperty(dispIdMember);
            if (pVarResult.getBoolean()) {
                mask3 |= 0x10000;
            }
            pVarResult.dispose();
            rgdispid = event.getIDsOfNames(new String[] { "shiftKey" });
            dispIdMember = rgdispid[0];
            pVarResult = event.getProperty(dispIdMember);
            if (pVarResult.getBoolean()) {
                mask3 |= 0x20000;
            }
            pVarResult.dispose();
            final Event keyEvent3 = new Event();
            keyEvent3.widget = (Widget)this.browser;
            keyEvent3.type = 2;
            keyEvent3.keyCode = this.lastKeyCode;
            keyEvent3.character = (char)this.lastCharCode;
            keyEvent3.stateMask = mask3;
            switch (this.lastKeyCode) {
                case 65536:
                case 131072:
                case 262144:
                case 4194304: {
                    final Event event9 = keyEvent3;
                    event9.stateMask |= this.lastKeyCode;
                    break;
                }
            }
            this.browser.notifyListeners(keyEvent3.type, keyEvent3);
            if (!keyEvent3.doit) {
                rgdispid = event.getIDsOfNames(new String[] { "returnValue" });
                dispIdMember = rgdispid[0];
                final Variant pVarFalse3 = new Variant(false);
                event.setProperty(dispIdMember, pVarFalse3);
                pVarFalse3.dispose();
            }
            this.lastCharCode = 0;
            this.lastKeyCode = 0;
            event.dispose();
        }
    }
    
    void hookDOMListeners(final OleAutomation webBrowser, final boolean isTop) {
        final int[] rgdispid = webBrowser.getIDsOfNames(new String[] { "Document" });
        final int dispIdMember = rgdispid[0];
        final Variant pVarResult = webBrowser.getProperty(dispIdMember);
        if (pVarResult == null) {
            return;
        }
        if (pVarResult.getType() == 0) {
            pVarResult.dispose();
            return;
        }
        final OleAutomation document = pVarResult.getAutomation();
        pVarResult.dispose();
        this.unhookDOMListeners(new OleAutomation[] { document });
        this.site.addEventListener(document, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -602, this.domListener);
        this.site.addEventListener(document, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -603, this.domListener);
        this.site.addEventListener(document, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -604, this.domListener);
        this.site.addEventListener(document, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -605, this.domListener);
        this.site.addEventListener(document, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -607, this.domListener);
        this.site.addEventListener(document, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", 1033, this.domListener);
        this.site.addEventListener(document, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -601, this.domListener);
        this.site.addEventListener(document, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -606, this.domListener);
        this.site.addEventListener(document, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -2147418101, this.domListener);
        this.site.addEventListener(document, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -2147418091, this.domListener);
        if (isTop) {
            this.site.addEventListener(document, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -2147418104, this.domListener);
            this.site.addEventListener(document, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -2147418103, this.domListener);
        }
        final OleAutomation[] newDocuments = new OleAutomation[this.documents.length + 1];
        System.arraycopy(this.documents, 0, newDocuments, 0, this.documents.length);
        newDocuments[this.documents.length] = document;
        this.documents = newDocuments;
    }
    
    void unhookDOMListeners(final OleAutomation[] documents) {
        final char[] buffer = "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}\u0000".toCharArray();
        final GUID guid;
        if (COM.IIDFromString(buffer, guid = new GUID()) == 0) {
            for (final OleAutomation document : documents) {
                this.site.removeEventListener(document, guid, -602, this.domListener);
                this.site.removeEventListener(document, guid, -603, this.domListener);
                this.site.removeEventListener(document, guid, -604, this.domListener);
                this.site.removeEventListener(document, guid, -605, this.domListener);
                this.site.removeEventListener(document, guid, -607, this.domListener);
                this.site.removeEventListener(document, guid, 1033, this.domListener);
                this.site.removeEventListener(document, guid, -601, this.domListener);
                this.site.removeEventListener(document, guid, -606, this.domListener);
                this.site.removeEventListener(document, guid, -2147418101, this.domListener);
                this.site.removeEventListener(document, guid, -2147418091, this.domListener);
                this.site.removeEventListener(document, guid, -2147418104, this.domListener);
                this.site.removeEventListener(document, guid, -2147418103, this.domListener);
            }
        }
    }
    
    static {
        IE.ProgId = "Shell.Explorer";
        SEPARATOR_OS = File.separatorChar;
        IE.NativeClearSessions = (() -> OS.InternetSetOption(0L, 42, 0L, 0));
        final TCHAR url;
        TCHAR cookieData;
        final int[] size;
        final String allCookies;
        final StringTokenizer tokenizer;
        String cookie;
        int index;
        IE.NativeGetCookie = (() -> {
            url = new TCHAR(0, IE.CookieUrl, true);
            cookieData = new TCHAR(0, 8192);
            size = new int[] { cookieData.length() };
            if (!OS.InternetGetCookie(url, null, cookieData, size)) {
                size[0] /= 2;
                cookieData = new TCHAR(0, size[0]);
                if (!OS.InternetGetCookie(url, null, cookieData, size)) {
                    return;
                }
            }
            allCookies = cookieData.toString(0, size[0]);
            tokenizer = new StringTokenizer(allCookies, ";");
            while (tokenizer.hasMoreTokens()) {
                cookie = tokenizer.nextToken();
                index = cookie.indexOf(61);
                if (index != -1) {
                    if (!cookie.substring(0, index).trim().equals(IE.CookieName)) {
                        continue;
                    }
                    else {
                        IE.CookieValue = cookie.substring(index + 1).trim();
                    }
                }
            }
            return;
        });
        final TCHAR url2;
        final TCHAR value;
        IE.NativeSetCookie = (() -> {
            url2 = new TCHAR(0, IE.CookieUrl, true);
            value = new TCHAR(0, IE.CookieValue, true);
            IE.CookieResult = OS.InternetSetCookie(url2, null, value);
            return;
        });
        TCHAR key = new TCHAR(0, "Software\\Microsoft\\Internet Explorer", true);
        long[] phkResult = { 0L };
        if (OS.RegOpenKeyEx(-2147483646L, key, 0, 131097, phkResult) == 0) {
            TCHAR buffer = new TCHAR(0, "svcVersion", true);
            final int[] lpcbData = { 0 };
            int result = OS.RegQueryValueEx(phkResult[0], buffer, 0L, null, (TCHAR)null, lpcbData);
            if (result != 0) {
                buffer = new TCHAR(0, "Version", true);
                result = OS.RegQueryValueEx(phkResult[0], buffer, 0L, null, (TCHAR)null, lpcbData);
            }
            final TCHAR lpData;
            final String versionString;
            final int index2;
            if (result == 0 && (result = OS.RegQueryValueEx(phkResult[0], buffer, 0L, null, lpData = new TCHAR(0, lpcbData[0] / 2), lpcbData)) == 0 && (index2 = (versionString = lpData.toString(0, lpData.strlen())).indexOf(".")) != -1) {
                final String majorString = versionString.substring(0, index2);
                try {
                    IE.IEVersion = Integer.parseInt(majorString);
                }
                catch (NumberFormatException ex) {}
            }
            OS.RegCloseKey(phkResult[0]);
        }
        final long hKey = -2147483648L;
        key = new TCHAR(0, "Shell.Explorer\\CLSID", true);
        if (OS.RegOpenKeyEx(hKey, key, 0, 131097, phkResult = new long[] { 0L }) == 0) {
            final int[] lpcbData = { 0 };
            int result2 = OS.RegQueryValueEx(phkResult[0], null, 0L, null, (TCHAR)null, lpcbData);
            final TCHAR lpData2;
            if (result2 == 0 && (result2 = OS.RegQueryValueEx(phkResult[0], null, 0L, null, lpData2 = new TCHAR(0, lpcbData[0] / 2), lpcbData)) == 0 && lpData2.toString(0, lpData2.strlen()).equals("{EAB22AC3-30C1-11CF-A7EB-0000C05BAE0B}")) {
                final long hKey2 = -2147483648L;
                key = new TCHAR(0, "Shell.Explorer.2", true);
                final long[] phkResult2;
                if (OS.RegOpenKeyEx(hKey2, key, 0, 131097, phkResult2 = new long[] { 0L }) == 0) {
                    OS.RegCloseKey(phkResult2[0]);
                    IE.ProgId = "Shell.Explorer.2";
                }
            }
            OS.RegCloseKey(phkResult[0]);
        }
        if (IE.NativePendingCookies != null) {
            WebBrowser.SetPendingCookies(IE.NativePendingCookies);
        }
        IE.NativePendingCookies = null;
    }
}
