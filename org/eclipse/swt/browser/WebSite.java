//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.*;
import org.eclipse.swt.ole.win32.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.internal.win32.*;
import java.util.*;
import org.eclipse.swt.*;
import org.eclipse.swt.internal.ole.win32.*;

class WebSite extends OleControlSite
{
    COMObject iDocHostUIHandler;
    COMObject iDocHostShowUI;
    COMObject iServiceProvider;
    COMObject iInternetSecurityManager;
    COMObject iOleCommandTarget;
    COMObject iAuthenticate;
    COMObject iDispatch;
    boolean ignoreNextMessage;
    boolean ignoreAllMessages;
    boolean isForceTrusted;
    Boolean canExecuteApplets;
    static final int OLECMDID_SHOWSCRIPTERROR = 40;
    static final short[] ACCENTS;
    static final String CONSUME_KEY = "org.eclipse.swt.OleFrame.ConsumeKey";
    
    public WebSite(final Composite parent, final int style, final String progId) {
        super(parent, style, progId);
    }
    
    @Override
    protected void createCOMInterfaces() {
        super.createCOMInterfaces();
        this.iDocHostUIHandler = (COMObject)new llIII(this, new int[] { 2, 0, 0, 4, 1, 5, 0, 0, 1, 1, 1, 3, 3, 2, 2, 1, 3, 2 });
        this.iDocHostShowUI = (COMObject)new lIIIl(this, new int[] { 2, 0, 0, 7, 6 });
        this.iServiceProvider = (COMObject)new llIIl(this, new int[] { 2, 0, 0, 3 });
        this.iInternetSecurityManager = (COMObject)new lIIll(this, new int[] { 2, 0, 0, 1, 1, 3, 4, 8, 7, 3, 3 });
        this.iOleCommandTarget = (COMObject)new lIllI(this, new int[] { 2, 0, 0, 4, 5 });
        this.iAuthenticate = (COMObject)new lIIII(this, new int[] { 2, 0, 0, 3 });
        this.iDispatch = (COMObject)new lIlll(this, new int[] { 2, 0, 0, 1, 3, 5, 8 });
    }
    
    @Override
    protected void disposeCOMInterfaces() {
        super.disposeCOMInterfaces();
        if (this.iDocHostUIHandler != null) {
            this.iDocHostUIHandler.dispose();
            this.iDocHostUIHandler = null;
        }
        if (this.iDocHostShowUI != null) {
            this.iDocHostShowUI.dispose();
            this.iDocHostShowUI = null;
        }
        if (this.iServiceProvider != null) {
            this.iServiceProvider.dispose();
            this.iServiceProvider = null;
        }
        if (this.iInternetSecurityManager != null) {
            this.iInternetSecurityManager.dispose();
            this.iInternetSecurityManager = null;
        }
        if (this.iOleCommandTarget != null) {
            this.iOleCommandTarget.dispose();
            this.iOleCommandTarget = null;
        }
        if (this.iAuthenticate != null) {
            this.iAuthenticate.dispose();
            this.iAuthenticate = null;
        }
        if (this.iDispatch != null) {
            this.iDispatch.dispose();
            this.iDispatch = null;
        }
    }
    
    @Override
    protected int AddRef() {
        return super.AddRef();
    }
    
    @Override
    protected int QueryInterface(final long riid, final long ppvObject) {
        final int result = super.QueryInterface(riid, ppvObject);
        if (result == 0) {
            return result;
        }
        if (riid == 0L || ppvObject == 0L) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, riid, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIDocHostUIHandler)) {
            OS.MoveMemory(ppvObject, new long[] { this.iDocHostUIHandler.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIDocHostShowUI)) {
            OS.MoveMemory(ppvObject, new long[] { this.iDocHostShowUI.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIServiceProvider)) {
            OS.MoveMemory(ppvObject, new long[] { this.iServiceProvider.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIInternetSecurityManager)) {
            OS.MoveMemory(ppvObject, new long[] { this.iInternetSecurityManager.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIOleCommandTarget)) {
            OS.MoveMemory(ppvObject, new long[] { this.iOleCommandTarget.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        OS.MoveMemory(ppvObject, new long[] { 0L }, C.PTR_SIZEOF);
        return -2147467262;
    }
    
    int EnableModeless(final int EnableModeless) {
        return -2147467263;
    }
    
    int FilterDataObject(final long pDO, final long ppDORet) {
        return -2147467263;
    }
    
    int GetDropTarget(final long pDropTarget, final long ppDropTarget) {
        return -2147467263;
    }
    
    int GetExternal(final long ppDispatch) {
        OS.MoveMemory(ppDispatch, new long[] { this.iDispatch.getAddress() }, C.PTR_SIZEOF);
        this.AddRef();
        return 0;
    }
    
    int GetHostInfo(final long pInfo) {
        int info = 1141112832;
        final IE browser = (IE)((Browser)this.getParent().getParent()).webBrowser;
        if ((browser.style & 0x800) == 0x0) {
            info |= 0x200000;
        }
        final DOCHOSTUIINFO uiInfo = new DOCHOSTUIINFO();
        OS.MoveMemory(uiInfo, pInfo, DOCHOSTUIINFO.sizeof);
        uiInfo.dwFlags = info;
        OS.MoveMemory(pInfo, uiInfo, DOCHOSTUIINFO.sizeof);
        return 0;
    }
    
    int GetOptionKeyPath(final long pchKey, final int dw) {
        return -2147467263;
    }
    
    int HideUI() {
        return -2147467263;
    }
    
    int OnDocWindowActivate(final int fActivate) {
        return -2147467263;
    }
    
    int OnFrameWindowActivate(final int fActivate) {
        return -2147467263;
    }
    
    @Override
    protected int Release() {
        return super.Release();
    }
    
    int ResizeBorder(final long prcBorder, final long pUIWindow, final int fFrameWindow) {
        return -2147467263;
    }
    
    int ShowContextMenu(final int dwID, final long ppt, final long pcmdtReserved, final long pdispReserved) {
        final Browser browser = (Browser)this.getParent().getParent();
        final Event event = new Event();
        final POINT pt = new POINT();
        OS.MoveMemory(pt, ppt, POINT.sizeof);
        pt.x = DPIUtil.autoScaleDown(pt.x);
        pt.y = DPIUtil.autoScaleDown(pt.y);
        event.x = pt.x;
        event.y = pt.y;
        browser.notifyListeners(35, event);
        if (!event.doit) {
            return 0;
        }
        final Menu menu = browser.getMenu();
        if (menu != null && !menu.isDisposed()) {
            if (pt.x != event.x || pt.y != event.y) {
                menu.setLocation(event.x, event.y);
            }
            menu.setVisible(true);
            return 0;
        }
        return 1;
    }
    
    int ShowUI(final int dwID, final long pActiveObject, final long pCommandTarget, final long pFrame, final long pDoc) {
        return 1;
    }
    
    int TranslateAccelerator(final long lpMsg, final long pguidCmdGroup, final int nCmdID) {
        final Menu menubar = this.getShell().getMenuBar();
        if (menubar != null && !menubar.isDisposed() && menubar.isEnabled()) {
            final Shell shell = menubar.getShell();
            final long hwnd = shell.handle;
            final long hAccel = OS.SendMessage(hwnd, 32769, 0L, 0L);
            if (hAccel != 0L) {
                final MSG msg = new MSG();
                OS.MoveMemory(msg, lpMsg, MSG.sizeof);
                if (OS.TranslateAccelerator(hwnd, hAccel, msg) != 0) {
                    return 0;
                }
            }
        }
        int result = 1;
        final MSG msg2 = new MSG();
        OS.MoveMemory(msg2, lpMsg, MSG.sizeof);
        Label_0442: {
            if (msg2.message == 256) {
                switch ((int)msg2.wParam) {
                    case 116: {
                        final OleAutomation auto = new OleAutomation(this);
                        final int[] rgdispid = auto.getIDsOfNames(new String[] { "LocationURL" });
                        final Variant pVarResult = auto.getProperty(rgdispid[0]);
                        auto.dispose();
                        if (pVarResult != null) {
                            if (pVarResult.getType() == 8) {
                                final String url = pVarResult.getString();
                                if (url.equals("about:blank")) {
                                    result = 0;
                                }
                            }
                            pVarResult.dispose();
                        }
                        break Label_0442;
                    }
                    case 8:
                    case 9:
                    case 13:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40: {
                        break Label_0442;
                    }
                    case 76:
                    case 78:
                    case 79: {
                        if (OS.GetKeyState(17) < 0 && OS.GetKeyState(18) >= 0 && OS.GetKeyState(16) >= 0 && (msg2.wParam == 78L || IE.IEVersion >= 8)) {
                            this.frame.setData("org.eclipse.swt.OleFrame.ConsumeKey", "false");
                            result = 0;
                            break Label_0442;
                        }
                        break;
                    }
                }
                OS.TranslateMessage(msg2);
                this.frame.setData("org.eclipse.swt.OleFrame.ConsumeKey", "true");
            }
        }
        switch (msg2.message) {
            case 256:
            case 257: {
                boolean isAccent = false;
                switch ((int)msg2.wParam) {
                    case 16:
                    case 17:
                    case 18:
                    case 20:
                    case 144:
                    case 145: {
                        break;
                    }
                    default: {
                        final int mapKey = OS.MapVirtualKey((int)msg2.wParam, 2);
                        if (mapKey == 0) {
                            break;
                        }
                        isAccent = ((mapKey & Integer.MIN_VALUE) != 0x0);
                        if (!isAccent) {
                            for (final short element : WebSite.ACCENTS) {
                                final int value = OS.VkKeyScan(element);
                                if (value != -1 && (value & 0xFF) == msg2.wParam) {
                                    final int state = value >> 8;
                                    if (OS.GetKeyState(16) < 0 == ((state & 0x1) != 0x0) && OS.GetKeyState(17) < 0 == ((state & 0x2) != 0x0) && OS.GetKeyState(18) < 0 == ((state & 0x4) != 0x0)) {
                                        if ((state & 0x7) != 0x0) {
                                            isAccent = true;
                                            break;
                                        }
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                        break;
                    }
                }
                if (isAccent) {
                    result = 0;
                    break;
                }
                break;
            }
        }
        return result;
    }
    
    int TranslateUrl(final int dwTranslate, final long pchURLIn, final long ppchURLOut) {
        return -2147467263;
    }
    
    int UpdateUI() {
        return -2147467263;
    }
    
    int ShowMessage(final long hwnd, final long lpstrText, final long lpstrCaption, final int dwType, final long lpstrHelpFile, final int dwHelpContext, final long plResult) {
        final boolean ignore = this.ignoreNextMessage || this.ignoreAllMessages;
        this.ignoreNextMessage = false;
        return ignore ? 0 : 1;
    }
    
    int ShowHelp(final long hwnd, final long pszHelpFile, final int uCommand, final int dwData, final long pt, final long pDispatchObjectHit) {
        final Browser browser = (Browser)this.getParent().getParent();
        final Event event = new Event();
        event.type = 28;
        event.display = this.getDisplay();
        event.widget = (Widget)browser;
        final Shell shell = browser.getShell();
        Control control;
        for (control = (Control)browser; !control.isListening(28); control = control.getParent()) {
            if (control == shell) {
                return 0;
            }
        }
        control.notifyListeners(28, event);
        return 0;
    }
    
    int QueryService(final long guidService, final long riid, final long ppvObject) {
        if (riid == 0L || ppvObject == 0L) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, riid, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIInternetSecurityManager)) {
            OS.MoveMemory(ppvObject, new long[] { this.iInternetSecurityManager.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIAuthenticate)) {
            OS.MoveMemory(ppvObject, new long[] { this.iAuthenticate.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        OS.MoveMemory(ppvObject, new long[] { 0L }, C.PTR_SIZEOF);
        return -2147467262;
    }
    
    int SetSecuritySite(final long pSite) {
        return -2146697199;
    }
    
    int GetSecuritySite(final long ppSite) {
        return -2146697199;
    }
    
    int MapUrlToZone(final long pwszUrl, final long pdwZone, final int dwFlags) {
        if (this.isForceTrusted) {
            OS.MoveMemory(pdwZone, new int[] { 1 }, 4);
            return 0;
        }
        return -2146697199;
    }
    
    int GetSecurityId(final long pwszUrl, final long pbSecurityId, final long pcbSecurityId, final long dwReserved) {
        return -2146697199;
    }
    
    int ProcessUrlAction(final long pwszUrl, final int dwAction, final long pPolicy, final int cbPolicy, final long pContext, final int cbContext, final int dwFlags, final int dwReserved) {
        this.ignoreNextMessage = false;
        if (dwAction == 8449) {
            final IE ie = (IE)((Browser)this.getParent().getParent()).webBrowser;
            if (ie.auto != null && ie._getUrl().startsWith("about:blank") && !ie.untrustedText) {
                if (cbPolicy >= 4) {
                    OS.MoveMemory(pPolicy, new int[] { 0 }, 4);
                }
                return 0;
            }
        }
        int policy = -2146697199;
        if (dwAction >= 7168 && dwAction <= 7423) {
            if (this.canExecuteApplets()) {
                policy = 196608;
            }
            else {
                policy = 0;
                this.ignoreNextMessage = true;
            }
        }
        if (dwAction == 4608 && pContext != 0L) {
            final GUID guid = new GUID();
            COM.MoveMemory(guid, pContext, GUID.sizeof);
            if (COM.IsEqualGUID(guid, COM.IIDJavaBeansBridge) && !this.canExecuteApplets()) {
                policy = 3;
                this.ignoreNextMessage = true;
            }
            if (COM.IsEqualGUID(guid, COM.IIDShockwaveActiveXControl)) {
                policy = 3;
                this.ignoreNextMessage = true;
            }
        }
        if (dwAction == 5120) {
            final IE browser = (IE)((Browser)this.getParent().getParent()).webBrowser;
            policy = (browser.jsEnabled ? 0 : 3);
        }
        if (policy == -2146697199) {
            return -2146697199;
        }
        if (cbPolicy >= 4) {
            OS.MoveMemory(pPolicy, new int[] { policy }, 4);
        }
        return (policy != 0) ? 1 : 0;
    }
    
    boolean canExecuteApplets() {
        if (IE.IEVersion < 7) {
            return false;
        }
        if (this.canExecuteApplets == null) {
            final WebBrowser webBrowser = ((Browser)this.getParent().getParent()).webBrowser;
            final String script = "try {var element = document.createElement('object');element.classid='clsid:CAFEEFAC-DEC7-0000-0000-ABCDEFFEDCBA';return element.object.isPlugin2();} catch (err) {};return false;";
            this.canExecuteApplets = (Boolean)webBrowser.evaluate("try {var element = document.createElement('object');element.classid='clsid:CAFEEFAC-DEC7-0000-0000-ABCDEFFEDCBA';return element.object.isPlugin2();} catch (err) {};return false;");
            if (this.canExecuteApplets) {
                try {
                    Class.forName("sun.plugin2.main.server.IExplorerPlugin");
                    Class.forName("com.sun.deploy.services.Service");
                    Class.forName("com.sun.javaws.Globals");
                }
                catch (ClassNotFoundException e) {
                    this.canExecuteApplets = Boolean.FALSE;
                }
            }
        }
        return this.canExecuteApplets;
    }
    
    int QueryCustomPolicy(final long pwszUrl, final long guidKey, final long ppPolicy, final long pcbPolicy, final long pContext, final int cbContext, final int dwReserved) {
        return -2146697199;
    }
    
    int SetZoneMapping(final int dwZone, final long lpszPattern, final int dwFlags) {
        return -2146697199;
    }
    
    int GetZoneMappings(final int dwZone, final long ppenumString, final int dwFlags) {
        return -2147467263;
    }
    
    int QueryStatus(final long pguidCmdGroup, final int cCmds, final long prgCmds, final long pCmdText) {
        return -2147221248;
    }
    
    int Exec(final long pguidCmdGroup, final int nCmdID, final int nCmdExecOpt, final long pvaIn, final long pvaOut) {
        if (pguidCmdGroup != 0L) {
            final GUID guid = new GUID();
            COM.MoveMemory(guid, pguidCmdGroup, GUID.sizeof);
            if (COM.IsEqualGUID(guid, COM.CGID_DocHostCommandHandler) && nCmdID == 40) {
                return 0;
            }
            if (nCmdID == 1 && COM.IsEqualGUID(guid, COM.CGID_Explorer) && (nCmdExecOpt & 0xFFFF) == 0xA) {
                final IE browser = (IE)((Browser)this.getParent().getParent()).webBrowser;
                browser.toolBar = ((nCmdExecOpt & 0xFFFF0000) != 0x0);
            }
        }
        return -2147221248;
    }
    
    int Authenticate(final long hwnd, final long szUsername, final long szPassword) {
        final IE browser = (IE)((Browser)this.getParent().getParent()).webBrowser;
        for (final AuthenticationListener authenticationListener : browser.authenticationListeners) {
            final AuthenticationEvent event = new AuthenticationEvent((Widget)browser.browser);
            event.location = browser.lastNavigateURL;
            authenticationListener.authenticate(event);
            if (!event.doit) {
                return -2147024891;
            }
            if (event.user != null && event.password != null) {
                final TCHAR user = new TCHAR(0, event.user, true);
                int size = user.length() * 2;
                final long userPtr = OS.CoTaskMemAlloc(size);
                OS.MoveMemory(userPtr, user, size);
                final TCHAR password = new TCHAR(0, event.password, true);
                size = password.length() * 2;
                final long passwordPtr = OS.CoTaskMemAlloc(size);
                OS.MoveMemory(passwordPtr, password, size);
                C.memmove(hwnd, new long[] { 0L }, C.PTR_SIZEOF);
                C.memmove(szUsername, new long[] { userPtr }, C.PTR_SIZEOF);
                C.memmove(szPassword, new long[] { passwordPtr }, C.PTR_SIZEOF);
                return 0;
            }
        }
        C.memmove(hwnd, new long[] { this.getShell().handle }, C.PTR_SIZEOF);
        return 0;
    }
    
    int GetTypeInfoCount(final long pctinfo) {
        C.memmove(pctinfo, new int[] { 0 }, 4L);
        return 0;
    }
    
    int GetTypeInfo(final int iTInfo, final int lcid, final long ppTInfo) {
        return 0;
    }
    
    int GetIDsOfNames(final int riid, final long rgszNames, final int cNames, final int lcid, final long rgDispId) {
        final long[] ptr = { 0L };
        OS.MoveMemory(ptr, rgszNames, C.PTR_SIZEOF);
        final int length = OS.wcslen(ptr[0]);
        final char[] buffer = new char[length];
        OS.MoveMemory(buffer, ptr[0], length * 2);
        final String functionName = String.valueOf(buffer);
        int result = 0;
        final int[] ids = new int[cNames];
        if (functionName.equals("callJava")) {
            for (int i = 0; i < cNames; ++i) {
                ids[i] = i + 1;
            }
        }
        else {
            result = -2147352570;
            for (int i = 0; i < cNames; ++i) {
                ids[i] = -1;
            }
        }
        OS.MoveMemory(rgDispId, ids, cNames * 4);
        return result;
    }
    
    int Invoke(final int dispIdMember, final long riid, final int lcid, final int dwFlags, final long pDispParams, final long pVarResult, final long pExcepInfo, final long pArgErr) {
        final IE ie = (IE)((Browser)this.getParent().getParent()).webBrowser;
        final Map<Integer, BrowserFunction> functions = (Map<Integer, BrowserFunction>)ie.functions;
        if (functions == null) {
            if (pVarResult != 0L) {
                OS.MoveMemory(pVarResult, new long[] { 0L }, C.PTR_SIZEOF);
            }
            return 0;
        }
        final DISPPARAMS dispParams = new DISPPARAMS();
        COM.MoveMemory(dispParams, pDispParams, DISPPARAMS.sizeof);
        if (dispParams.cArgs != 3) {
            if (pVarResult != 0L) {
                OS.MoveMemory(pVarResult, new long[] { 0L }, C.PTR_SIZEOF);
            }
            return 0;
        }
        long ptr = dispParams.rgvarg + 2 * Variant.sizeof;
        Variant variant = Variant.win32_new(ptr);
        if (variant.getType() != 3) {
            variant.dispose();
            if (pVarResult != 0L) {
                OS.MoveMemory(pVarResult, new long[] { 0L }, C.PTR_SIZEOF);
            }
            return 0;
        }
        final int index = variant.getInt();
        variant.dispose();
        if (index <= 0) {
            if (pVarResult != 0L) {
                OS.MoveMemory(pVarResult, new long[] { 0L }, C.PTR_SIZEOF);
            }
            return 0;
        }
        ptr = dispParams.rgvarg + Variant.sizeof;
        variant = Variant.win32_new(ptr);
        final int type = variant.getType();
        if (type != 8) {
            variant.dispose();
            if (pVarResult != 0L) {
                OS.MoveMemory(pVarResult, new long[] { 0L }, C.PTR_SIZEOF);
            }
            return 0;
        }
        final String token = variant.getString();
        variant.dispose();
        variant = Variant.win32_new(dispParams.rgvarg);
        final BrowserFunction function = functions.get(index);
        Object returnValue = null;
        if (function != null && token.equals(function.token)) {
            try {
                final Object temp = this.convertToJava(variant);
                if (temp instanceof Object[]) {
                    final Object[] args = (Object[])temp;
                    try {
                        returnValue = function.function(args);
                    }
                    catch (Exception e) {
                        returnValue = WebBrowser.CreateErrorString(e.getLocalizedMessage());
                    }
                }
            }
            catch (IllegalArgumentException e2) {
                if (function.isEvaluate) {
                    function.function((Object[])new String[] { WebBrowser.CreateErrorString(new SWTException(51).getLocalizedMessage()) });
                }
                returnValue = WebBrowser.CreateErrorString(e2.getLocalizedMessage());
            }
        }
        variant.dispose();
        if (pVarResult != 0L) {
            try {
                variant = this.convertToJS(returnValue);
            }
            catch (SWTException e3) {
                variant = this.convertToJS(WebBrowser.CreateErrorString(e3.getLocalizedMessage()));
            }
            Variant.win32_copy(pVarResult, variant);
            variant.dispose();
        }
        return 0;
    }
    
    Object convertToJava(final Variant variant) {
        switch (variant.getType()) {
            case 0:
            case 1: {
                return null;
            }
            case 8: {
                return variant.getString();
            }
            case 11: {
                return variant.getBoolean();
            }
            case 2:
            case 3:
            case 4:
            case 5:
            case 20: {
                return variant.getDouble();
            }
            case 9: {
                Object[] args = null;
                final OleAutomation auto = variant.getAutomation();
                final TYPEATTR typeattr = auto.getTypeInfoAttributes();
                if (typeattr != null) {
                    final GUID guid = new GUID();
                    guid.Data1 = typeattr.guid_Data1;
                    guid.Data2 = typeattr.guid_Data2;
                    guid.Data3 = typeattr.guid_Data3;
                    guid.Data4 = typeattr.guid_Data4;
                    if (COM.IsEqualGUID(guid, COM.IIDIJScriptTypeInfo)) {
                        int[] rgdispid = auto.getIDsOfNames(new String[] { "length" });
                        if (rgdispid != null) {
                            final Variant varLength = auto.getProperty(rgdispid[0]);
                            final int length = varLength.getInt();
                            varLength.dispose();
                            args = new Object[length];
                            for (int i = 0; i < length; ++i) {
                                rgdispid = auto.getIDsOfNames(new String[] { String.valueOf(i) });
                                if (rgdispid != null) {
                                    final Variant current = auto.getProperty(rgdispid[0]);
                                    try {
                                        args[i] = this.convertToJava(current);
                                        current.dispose();
                                    }
                                    catch (IllegalArgumentException e) {
                                        current.dispose();
                                        auto.dispose();
                                        throw e;
                                    }
                                }
                            }
                        }
                    }
                    else {
                        auto.dispose();
                        SWT.error(5);
                    }
                }
                auto.dispose();
                return args;
            }
            default: {
                SWT.error(5);
                return null;
            }
        }
    }
    
    Variant convertToJS(final Object value) {
        if (value == null) {
            return Variant.NULL;
        }
        if (value instanceof String) {
            return new Variant((String)value);
        }
        if (value instanceof Boolean) {
            return new Variant((boolean)value);
        }
        if (value instanceof Number) {
            return new Variant(((Number)value).doubleValue());
        }
        if (!(value instanceof Object[])) {
            SWT.error(51);
            return null;
        }
        final IE browser = (IE)((Browser)this.getParent().getParent()).webBrowser;
        OleAutomation auto = browser.auto;
        int[] rgdispid = auto.getIDsOfNames(new String[] { "Document" });
        if (rgdispid == null) {
            return new Variant();
        }
        Variant pVarResult = auto.getProperty(rgdispid[0]);
        if (pVarResult == null) {
            return new Variant();
        }
        if (pVarResult.getType() == 0) {
            pVarResult.dispose();
            return new Variant();
        }
        final OleAutomation document = pVarResult.getAutomation();
        pVarResult.dispose();
        rgdispid = document.getIDsOfNames(new String[] { "parentWindow" });
        if (rgdispid == null) {
            document.dispose();
            return new Variant();
        }
        pVarResult = document.getProperty(rgdispid[0]);
        if (pVarResult == null || pVarResult.getType() == 0) {
            if (pVarResult != null) {
                pVarResult.dispose();
            }
            document.dispose();
            return new Variant();
        }
        final OleAutomation ihtmlWindow2 = pVarResult.getAutomation();
        pVarResult.dispose();
        document.dispose();
        rgdispid = ihtmlWindow2.getIDsOfNames(new String[] { "Array" });
        if (rgdispid == null) {
            ihtmlWindow2.dispose();
            return new Variant();
        }
        final Variant arrayType = ihtmlWindow2.getProperty(rgdispid[0]);
        ihtmlWindow2.dispose();
        final IDispatch arrayTypeDispatch = arrayType.getDispatch();
        final long[] result = { 0L };
        int rc = arrayTypeDispatch.QueryInterface(COM.IIDIDispatchEx, result);
        arrayType.dispose();
        if (rc != 0) {
            return new Variant();
        }
        final IDispatchEx arrayTypeDispatchEx = new IDispatchEx(result[0]);
        result[0] = 0L;
        final long resultPtr = OS.GlobalAlloc(64, VARIANT.sizeof);
        final DISPPARAMS params = new DISPPARAMS();
        rc = arrayTypeDispatchEx.InvokeEx(0, 2048, 16384, params, resultPtr, null, 0L);
        if (rc != 0) {
            OS.GlobalFree(resultPtr);
            return new Variant();
        }
        final Variant array = Variant.win32_new(resultPtr);
        OS.GlobalFree(resultPtr);
        final Object[] arrayValue = (Object[])value;
        auto = array.getAutomation();
        final int[] rgdispids = auto.getIDsOfNames(new String[] { "push" });
        if (rgdispids != null) {
            for (final Object currentObject : arrayValue) {
                try {
                    final Variant variant = this.convertToJS(currentObject);
                    auto.invoke(rgdispids[0], new Variant[] { variant });
                    variant.dispose();
                }
                catch (SWTException e) {
                    auto.dispose();
                    array.dispose();
                    throw e;
                }
            }
        }
        auto.dispose();
        return array;
    }
    
    static {
        ACCENTS = new short[] { 126, 96, 39, 94, 34 };
    }
}
