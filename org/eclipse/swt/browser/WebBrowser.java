//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import java.util.*;

abstract class WebBrowser
{
    Browser browser;
    Map<Integer, BrowserFunction> functions;
    AuthenticationListener[] authenticationListeners;
    CloseWindowListener[] closeWindowListeners;
    LocationListener[] locationListeners;
    OpenWindowListener[] openWindowListeners;
    ProgressListener[] progressListeners;
    StatusTextListener[] statusTextListeners;
    TitleListener[] titleListeners;
    VisibilityWindowListener[] visibilityWindowListeners;
    boolean jsEnabledOnNextPage;
    boolean jsEnabled;
    int nextFunctionIndex;
    Object evaluateResult;
    static final String ERROR_ID = "org.eclipse.swt.browser.error";
    static final String EXECUTE_ID = "SWTExecuteTemporaryFunction";
    static List<String[]> NativePendingCookies;
    static String CookieName;
    static String CookieValue;
    static String CookieUrl;
    static boolean CookieResult;
    static Runnable NativeClearSessions;
    static Runnable NativeGetCookie;
    static Runnable NativeSetCookie;
    static final int[][] KeyTable;
    
    WebBrowser() {
        this.functions = new HashMap<Integer, BrowserFunction>();
        this.authenticationListeners = new AuthenticationListener[0];
        this.closeWindowListeners = new CloseWindowListener[0];
        this.locationListeners = new LocationListener[0];
        this.openWindowListeners = new OpenWindowListener[0];
        this.progressListeners = new ProgressListener[0];
        this.statusTextListeners = new StatusTextListener[0];
        this.titleListeners = new TitleListener[0];
        this.visibilityWindowListeners = new VisibilityWindowListener[0];
        this.jsEnabledOnNextPage = true;
        this.jsEnabled = true;
        this.nextFunctionIndex = 1;
    }
    
    public void addAuthenticationListener(final AuthenticationListener listener) {
        final AuthenticationListener[] newAuthenticationListeners = new AuthenticationListener[this.authenticationListeners.length + 1];
        System.arraycopy(this.authenticationListeners, 0, newAuthenticationListeners, 0, this.authenticationListeners.length);
        (this.authenticationListeners = newAuthenticationListeners)[this.authenticationListeners.length - 1] = listener;
    }
    
    public void addCloseWindowListener(final CloseWindowListener listener) {
        final CloseWindowListener[] newCloseWindowListeners = new CloseWindowListener[this.closeWindowListeners.length + 1];
        System.arraycopy(this.closeWindowListeners, 0, newCloseWindowListeners, 0, this.closeWindowListeners.length);
        (this.closeWindowListeners = newCloseWindowListeners)[this.closeWindowListeners.length - 1] = listener;
    }
    
    public void addLocationListener(final LocationListener listener) {
        final LocationListener[] newLocationListeners = new LocationListener[this.locationListeners.length + 1];
        System.arraycopy(this.locationListeners, 0, newLocationListeners, 0, this.locationListeners.length);
        (this.locationListeners = newLocationListeners)[this.locationListeners.length - 1] = listener;
    }
    
    public void addOpenWindowListener(final OpenWindowListener listener) {
        final OpenWindowListener[] newOpenWindowListeners = new OpenWindowListener[this.openWindowListeners.length + 1];
        System.arraycopy(this.openWindowListeners, 0, newOpenWindowListeners, 0, this.openWindowListeners.length);
        (this.openWindowListeners = newOpenWindowListeners)[this.openWindowListeners.length - 1] = listener;
    }
    
    public void addProgressListener(final ProgressListener listener) {
        final ProgressListener[] newProgressListeners = new ProgressListener[this.progressListeners.length + 1];
        System.arraycopy(this.progressListeners, 0, newProgressListeners, 0, this.progressListeners.length);
        (this.progressListeners = newProgressListeners)[this.progressListeners.length - 1] = listener;
    }
    
    public void addStatusTextListener(final StatusTextListener listener) {
        final StatusTextListener[] newStatusTextListeners = new StatusTextListener[this.statusTextListeners.length + 1];
        System.arraycopy(this.statusTextListeners, 0, newStatusTextListeners, 0, this.statusTextListeners.length);
        (this.statusTextListeners = newStatusTextListeners)[this.statusTextListeners.length - 1] = listener;
    }
    
    public void addTitleListener(final TitleListener listener) {
        final TitleListener[] newTitleListeners = new TitleListener[this.titleListeners.length + 1];
        System.arraycopy(this.titleListeners, 0, newTitleListeners, 0, this.titleListeners.length);
        (this.titleListeners = newTitleListeners)[this.titleListeners.length - 1] = listener;
    }
    
    public void addVisibilityWindowListener(final VisibilityWindowListener listener) {
        final VisibilityWindowListener[] newVisibilityWindowListeners = new VisibilityWindowListener[this.visibilityWindowListeners.length + 1];
        System.arraycopy(this.visibilityWindowListeners, 0, newVisibilityWindowListeners, 0, this.visibilityWindowListeners.length);
        (this.visibilityWindowListeners = newVisibilityWindowListeners)[this.visibilityWindowListeners.length - 1] = listener;
    }
    
    public abstract boolean back();
    
    public static void clearSessions() {
        if (WebBrowser.NativeClearSessions != null) {
            WebBrowser.NativeClearSessions.run();
        }
    }
    
    public static String GetCookie(final String name, final String url) {
        WebBrowser.CookieName = name;
        WebBrowser.CookieUrl = url;
        WebBrowser.CookieValue = null;
        if (WebBrowser.NativeGetCookie != null) {
            WebBrowser.NativeGetCookie.run();
        }
        final String result = WebBrowser.CookieValue;
        WebBrowser.CookieName = (WebBrowser.CookieValue = (WebBrowser.CookieUrl = null));
        return result;
    }
    
    public static boolean SetCookie(final String value, final String url, final boolean addToPending) {
        WebBrowser.CookieValue = value;
        WebBrowser.CookieUrl = url;
        WebBrowser.CookieResult = false;
        if (WebBrowser.NativeSetCookie != null) {
            WebBrowser.NativeSetCookie.run();
        }
        else if (addToPending && WebBrowser.NativePendingCookies != null) {
            WebBrowser.NativePendingCookies.add(new String[] { value, url });
        }
        WebBrowser.CookieValue = (WebBrowser.CookieUrl = null);
        return WebBrowser.CookieResult;
    }
    
    static void SetPendingCookies(final List<String[]> pendingCookies) {
        for (final String[] current : pendingCookies) {
            SetCookie(current[0], current[1], false);
        }
    }
    
    public abstract void create(final Composite p0, final int p1);
    
    static String CreateErrorString(final String error) {
        return "org.eclipse.swt.browser.error" + error;
    }
    
    static String ExtractError(final String error) {
        return error.substring("org.eclipse.swt.browser.error".length());
    }
    
    public boolean close() {
        return true;
    }
    
    public void createFunction(final BrowserFunction function) {
        for (final BrowserFunction current : this.functions.values()) {
            if (current.name.equals(function.name)) {
                this.deregisterFunction(current);
                break;
            }
        }
        function.index = this.getNextFunctionIndex();
        this.registerFunction(function);
        final StringBuilder functionBuffer = new StringBuilder(function.name);
        functionBuffer.append(" = function ");
        functionBuffer.append(function.name);
        functionBuffer.append("() {var result = callJava(");
        functionBuffer.append(function.index);
        functionBuffer.append(",'");
        functionBuffer.append(function.token);
        functionBuffer.append("',Array.prototype.slice.call(arguments)); if (typeof result == 'string' && result.indexOf('");
        functionBuffer.append("org.eclipse.swt.browser.error");
        functionBuffer.append("') == 0) {var error = new Error(result.substring(");
        functionBuffer.append("org.eclipse.swt.browser.error".length());
        functionBuffer.append(")); throw error;} return result;};");
        final String javaCallDeclaration = this.getJavaCallDeclaration();
        final StringBuilder buffer = new StringBuilder();
        buffer.append(javaCallDeclaration);
        if (function.top) {
            buffer.append(functionBuffer.toString());
        }
        buffer.append("var frameIds = null;");
        if (function.frameNames != null) {
            buffer.append("frameIds = {");
            for (final String frameName : function.frameNames) {
                buffer.append('\'');
                buffer.append(frameName);
                buffer.append("':1,");
            }
            if (function.frameNames.length > 0) {
                buffer.deleteCharAt(buffer.length() - 1);
            }
            buffer.append("};");
        }
        buffer.append("for (var i = 0; i < frames.length; i++) {try {if (!frameIds || (frames[i].name && frameIds[frames[i].name])) {");
        buffer.append("if (!frames[i].callJava) {frames[i].callJava = window.callJava;} frames[i].");
        buffer.append(functionBuffer.toString());
        buffer.append("}} catch(e) {}};");
        this.nonBlockingExecute(function.functionString = buffer.toString());
    }
    
    String getJavaCallDeclaration() {
        return "if (!window.callJava) {\n\t\twindow.callJava = function callJava(index, token, args) {\n\t\t\treturn external.callJava(index,token,args);\n\t\t}\n};\n";
    }
    
    void deregisterFunction(final BrowserFunction function) {
        this.functions.remove(function.index);
    }
    
    public void destroyFunction(final BrowserFunction function) {
        final String deleteString = this.getDeleteFunctionString(function.name);
        final StringBuilder buffer = new StringBuilder("for (var i = 0; i < frames.length; i++) {try {frames[i].eval(\"");
        buffer.append(deleteString);
        buffer.append("\");} catch (e) {}}");
        this.nonBlockingExecute(buffer.toString());
        this.nonBlockingExecute(deleteString);
        this.deregisterFunction(function);
    }
    
    void nonBlockingExecute(final String script) {
        this.execute(script);
    }
    
    public abstract boolean execute(final String p0);
    
    public Object evaluate(final String script, final boolean trusted) throws SWTException {
        return this.evaluate(script);
    }
    
    public Object evaluate(final String script) throws SWTException {
        final BrowserFunction function = new EvaluateFunction(this.browser, "");
        final int index = this.getNextFunctionIndex();
        function.index = index;
        function.isEvaluate = true;
        this.registerFunction(function);
        final String functionName = "SWTExecuteTemporaryFunction" + index;
        StringBuilder buffer = new StringBuilder("window.");
        buffer.append(functionName);
        buffer.append(" = function ");
        buffer.append(functionName);
        buffer.append("() {\n");
        buffer.append(script);
        buffer.append("\n};");
        this.nonBlockingExecute(buffer.toString());
        buffer = new StringBuilder("if (window.");
        buffer.append(functionName);
        buffer.append(" == undefined) {window.external.callJava(");
        buffer.append(index);
        buffer.append(",'");
        buffer.append(function.token);
        buffer.append("', ['");
        buffer.append("org.eclipse.swt.browser.error");
        buffer.append("']);} else {try {var result = ");
        buffer.append(functionName);
        buffer.append("(); window.external.callJava(");
        buffer.append(index);
        buffer.append(",'");
        buffer.append(function.token);
        buffer.append("', [result]);} catch (e) {window.external.callJava(");
        buffer.append(index);
        buffer.append(",'");
        buffer.append(function.token);
        buffer.append("', ['");
        buffer.append("org.eclipse.swt.browser.error");
        buffer.append("' + e.message]);}}");
        this.nonBlockingExecute(buffer.toString());
        this.nonBlockingExecute(this.getDeleteFunctionString(functionName));
        this.deregisterFunction(function);
        final Object result = this.evaluateResult;
        this.evaluateResult = null;
        if (result instanceof SWTException) {
            throw (SWTException)result;
        }
        return result;
    }
    
    public abstract boolean forward();
    
    public abstract String getBrowserType();
    
    String getDeleteFunctionString(final String functionName) {
        return "delete window." + functionName;
    }
    
    int getNextFunctionIndex() {
        return this.nextFunctionIndex++;
    }
    
    public abstract String getText();
    
    public abstract String getUrl();
    
    public Object getWebBrowser() {
        return null;
    }
    
    public abstract boolean isBackEnabled();
    
    public boolean isFocusControl() {
        return false;
    }
    
    public abstract boolean isForwardEnabled();
    
    public abstract void refresh();
    
    void registerFunction(final BrowserFunction function) {
        this.functions.put(function.index, function);
    }
    
    public void removeAuthenticationListener(final AuthenticationListener listener) {
        if (this.authenticationListeners.length == 0) {
            return;
        }
        int index = -1;
        for (int i = 0; i < this.authenticationListeners.length; ++i) {
            if (listener == this.authenticationListeners[i]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }
        if (this.authenticationListeners.length == 1) {
            this.authenticationListeners = new AuthenticationListener[0];
            return;
        }
        final AuthenticationListener[] newAuthenticationListeners = new AuthenticationListener[this.authenticationListeners.length - 1];
        System.arraycopy(this.authenticationListeners, 0, newAuthenticationListeners, 0, index);
        System.arraycopy(this.authenticationListeners, index + 1, newAuthenticationListeners, index, this.authenticationListeners.length - index - 1);
        this.authenticationListeners = newAuthenticationListeners;
    }
    
    public void removeCloseWindowListener(final CloseWindowListener listener) {
        if (this.closeWindowListeners.length == 0) {
            return;
        }
        int index = -1;
        for (int i = 0; i < this.closeWindowListeners.length; ++i) {
            if (listener == this.closeWindowListeners[i]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }
        if (this.closeWindowListeners.length == 1) {
            this.closeWindowListeners = new CloseWindowListener[0];
            return;
        }
        final CloseWindowListener[] newCloseWindowListeners = new CloseWindowListener[this.closeWindowListeners.length - 1];
        System.arraycopy(this.closeWindowListeners, 0, newCloseWindowListeners, 0, index);
        System.arraycopy(this.closeWindowListeners, index + 1, newCloseWindowListeners, index, this.closeWindowListeners.length - index - 1);
        this.closeWindowListeners = newCloseWindowListeners;
    }
    
    public void removeLocationListener(final LocationListener listener) {
        if (this.locationListeners.length == 0) {
            return;
        }
        int index = -1;
        for (int i = 0; i < this.locationListeners.length; ++i) {
            if (listener == this.locationListeners[i]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }
        if (this.locationListeners.length == 1) {
            this.locationListeners = new LocationListener[0];
            return;
        }
        final LocationListener[] newLocationListeners = new LocationListener[this.locationListeners.length - 1];
        System.arraycopy(this.locationListeners, 0, newLocationListeners, 0, index);
        System.arraycopy(this.locationListeners, index + 1, newLocationListeners, index, this.locationListeners.length - index - 1);
        this.locationListeners = newLocationListeners;
    }
    
    public void removeOpenWindowListener(final OpenWindowListener listener) {
        if (this.openWindowListeners.length == 0) {
            return;
        }
        int index = -1;
        for (int i = 0; i < this.openWindowListeners.length; ++i) {
            if (listener == this.openWindowListeners[i]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }
        if (this.openWindowListeners.length == 1) {
            this.openWindowListeners = new OpenWindowListener[0];
            return;
        }
        final OpenWindowListener[] newOpenWindowListeners = new OpenWindowListener[this.openWindowListeners.length - 1];
        System.arraycopy(this.openWindowListeners, 0, newOpenWindowListeners, 0, index);
        System.arraycopy(this.openWindowListeners, index + 1, newOpenWindowListeners, index, this.openWindowListeners.length - index - 1);
        this.openWindowListeners = newOpenWindowListeners;
    }
    
    public void removeProgressListener(final ProgressListener listener) {
        if (this.progressListeners.length == 0) {
            return;
        }
        int index = -1;
        for (int i = 0; i < this.progressListeners.length; ++i) {
            if (listener == this.progressListeners[i]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }
        if (this.progressListeners.length == 1) {
            this.progressListeners = new ProgressListener[0];
            return;
        }
        final ProgressListener[] newProgressListeners = new ProgressListener[this.progressListeners.length - 1];
        System.arraycopy(this.progressListeners, 0, newProgressListeners, 0, index);
        System.arraycopy(this.progressListeners, index + 1, newProgressListeners, index, this.progressListeners.length - index - 1);
        this.progressListeners = newProgressListeners;
    }
    
    public void removeStatusTextListener(final StatusTextListener listener) {
        if (this.statusTextListeners.length == 0) {
            return;
        }
        int index = -1;
        for (int i = 0; i < this.statusTextListeners.length; ++i) {
            if (listener == this.statusTextListeners[i]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }
        if (this.statusTextListeners.length == 1) {
            this.statusTextListeners = new StatusTextListener[0];
            return;
        }
        final StatusTextListener[] newStatusTextListeners = new StatusTextListener[this.statusTextListeners.length - 1];
        System.arraycopy(this.statusTextListeners, 0, newStatusTextListeners, 0, index);
        System.arraycopy(this.statusTextListeners, index + 1, newStatusTextListeners, index, this.statusTextListeners.length - index - 1);
        this.statusTextListeners = newStatusTextListeners;
    }
    
    public void removeTitleListener(final TitleListener listener) {
        if (this.titleListeners.length == 0) {
            return;
        }
        int index = -1;
        for (int i = 0; i < this.titleListeners.length; ++i) {
            if (listener == this.titleListeners[i]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }
        if (this.titleListeners.length == 1) {
            this.titleListeners = new TitleListener[0];
            return;
        }
        final TitleListener[] newTitleListeners = new TitleListener[this.titleListeners.length - 1];
        System.arraycopy(this.titleListeners, 0, newTitleListeners, 0, index);
        System.arraycopy(this.titleListeners, index + 1, newTitleListeners, index, this.titleListeners.length - index - 1);
        this.titleListeners = newTitleListeners;
    }
    
    public void removeVisibilityWindowListener(final VisibilityWindowListener listener) {
        if (this.visibilityWindowListeners.length == 0) {
            return;
        }
        int index = -1;
        for (int i = 0; i < this.visibilityWindowListeners.length; ++i) {
            if (listener == this.visibilityWindowListeners[i]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }
        if (this.visibilityWindowListeners.length == 1) {
            this.visibilityWindowListeners = new VisibilityWindowListener[0];
            return;
        }
        final VisibilityWindowListener[] newVisibilityWindowListeners = new VisibilityWindowListener[this.visibilityWindowListeners.length - 1];
        System.arraycopy(this.visibilityWindowListeners, 0, newVisibilityWindowListeners, 0, index);
        System.arraycopy(this.visibilityWindowListeners, index + 1, newVisibilityWindowListeners, index, this.visibilityWindowListeners.length - index - 1);
        this.visibilityWindowListeners = newVisibilityWindowListeners;
    }
    
    boolean sendKeyEvent(final Event event) {
        int traversal = 0;
        boolean traverseDoit = true;
        switch (event.keyCode) {
            case 27: {
                traversal = 2;
                traverseDoit = true;
                break;
            }
            case 13: {
                traversal = 4;
                traverseDoit = false;
                break;
            }
            case 16777218:
            case 16777220: {
                traversal = 64;
                traverseDoit = false;
                break;
            }
            case 16777217:
            case 16777219: {
                traversal = 32;
                traverseDoit = false;
                break;
            }
            case 9: {
                traversal = (((event.stateMask & 0x20000) != 0x0) ? 8 : 16);
                traverseDoit = ((event.stateMask & 0x40000) != 0x0);
                break;
            }
            case 16777222: {
                if ((event.stateMask & 0x40000) != 0x0) {
                    traversal = 512;
                    traverseDoit = true;
                    break;
                }
                break;
            }
            case 16777221: {
                if ((event.stateMask & 0x40000) != 0x0) {
                    traversal = 256;
                    traverseDoit = true;
                    break;
                }
                break;
            }
            default: {
                if (this.translateMnemonics() && event.character != '\0' && (event.stateMask & 0x50000) == 0x10000) {
                    traversal = 128;
                    traverseDoit = true;
                    break;
                }
                break;
            }
        }
        boolean doit = true;
        if (traversal != 0) {
            final boolean oldEventDoit = event.doit;
            event.doit = traverseDoit;
            doit = !this.browser.traverse(traversal, event);
            event.doit = oldEventDoit;
        }
        if (doit) {
            this.browser.notifyListeners(event.type, event);
            doit = event.doit;
        }
        return doit;
    }
    
    public void setBrowser(final Browser browser) {
        this.browser = browser;
    }
    
    public abstract boolean setText(final String p0, final boolean p1);
    
    public abstract boolean setUrl(final String p0, final String p1, final String[] p2);
    
    public abstract void stop();
    
    int translateKey(final int key) {
        for (final int[] element : WebBrowser.KeyTable) {
            if (element[0] == key) {
                return element[1];
            }
        }
        return 0;
    }
    
    boolean translateMnemonics() {
        return true;
    }
    
    static {
        WebBrowser.NativePendingCookies = new ArrayList<String[]>();
        KeyTable = new int[][] { { 18, 65536 }, { 16, 131072 }, { 17, 262144 }, { 224, 4194304 }, { 65, 97 }, { 66, 98 }, { 67, 99 }, { 68, 100 }, { 69, 101 }, { 70, 102 }, { 71, 103 }, { 72, 104 }, { 73, 105 }, { 74, 106 }, { 75, 107 }, { 76, 108 }, { 77, 109 }, { 78, 110 }, { 79, 111 }, { 80, 112 }, { 81, 113 }, { 82, 114 }, { 83, 115 }, { 84, 116 }, { 85, 117 }, { 86, 118 }, { 87, 119 }, { 88, 120 }, { 89, 121 }, { 90, 122 }, { 48, 48 }, { 49, 49 }, { 50, 50 }, { 51, 51 }, { 52, 52 }, { 53, 53 }, { 54, 54 }, { 55, 55 }, { 56, 56 }, { 57, 57 }, { 32, 32 }, { 59, 59 }, { 61, 61 }, { 188, 44 }, { 190, 46 }, { 191, 47 }, { 219, 91 }, { 221, 93 }, { 222, 39 }, { 192, 96 }, { 220, 92 }, { 108, 124 }, { 226, 60 }, { 37, 16777219 }, { 39, 16777220 }, { 38, 16777217 }, { 40, 16777218 }, { 45, 16777225 }, { 36, 16777223 }, { 35, 16777224 }, { 46, 127 }, { 33, 16777221 }, { 34, 16777222 }, { 8, 8 }, { 13, 13 }, { 9, 9 }, { 27, 27 }, { 12, 127 }, { 112, 16777226 }, { 113, 16777227 }, { 114, 16777228 }, { 115, 16777229 }, { 116, 16777230 }, { 117, 16777231 }, { 118, 16777232 }, { 119, 16777233 }, { 120, 16777234 }, { 121, 16777235 }, { 122, 16777236 }, { 123, 16777237 }, { 124, 16777238 }, { 125, 16777239 }, { 126, 16777240 }, { 127, 0 }, { 128, 0 }, { 129, 0 }, { 130, 0 }, { 131, 0 }, { 132, 0 }, { 133, 0 }, { 134, 0 }, { 135, 0 }, { 96, 16777264 }, { 97, 16777265 }, { 98, 16777266 }, { 99, 16777267 }, { 100, 16777268 }, { 101, 16777269 }, { 102, 16777270 }, { 103, 16777271 }, { 104, 16777272 }, { 105, 16777273 }, { 14, 16777296 }, { 107, 16777259 }, { 109, 16777261 }, { 106, 16777258 }, { 111, 16777263 }, { 110, 16777262 }, { 20, 16777298 }, { 144, 16777299 }, { 145, 16777300 }, { 44, 16777303 }, { 6, 16777297 }, { 19, 16777301 }, { 3, 16777302 }, { 186, 59 }, { 187, 61 }, { 189, 45 } };
    }
    
    public class EvaluateFunction extends BrowserFunction
    {
        public EvaluateFunction(final Browser browser, final String name) {
            super(browser, name, true, new String[0], false);
        }
        
        public Object function(final Object[] arguments) {
            if (arguments[0] instanceof String) {
                final String string = (String)arguments[0];
                if (string.startsWith("org.eclipse.swt.browser.error")) {
                    final String errorString = WebBrowser.ExtractError(string);
                    if (errorString.length() > 0) {
                        WebBrowser.this.evaluateResult = new SWTException(50, errorString);
                    }
                    else {
                        WebBrowser.this.evaluateResult = new SWTException(50);
                    }
                    return null;
                }
            }
            WebBrowser.this.evaluateResult = arguments[0];
            return null;
        }
    }
}
