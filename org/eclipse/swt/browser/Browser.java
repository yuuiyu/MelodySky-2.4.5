//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;

public class Browser extends Composite
{
    WebBrowser webBrowser;
    int userStyle;
    boolean isClosing;
    static int DefaultType;
    static final String NO_INPUT_METHOD = "org.eclipse.swt.internal.gtk.noInputMethod";
    static final String PACKAGE_PREFIX = "org.eclipse.swt.browser.";
    static final String PROPERTY_DEFAULTTYPE = "org.eclipse.swt.browser.DefaultType";
    
    public Browser(final Composite parent, int style) {
        super(checkParent(parent), checkStyle(style));
        this.userStyle = style;
        final String platform = SWT.getPlatform();
        if ("gtk".equals(platform)) {
            parent.getDisplay().setData("org.eclipse.swt.internal.gtk.noInputMethod", null);
        }
        style = this.getStyle();
        this.webBrowser = new BrowserFactory().createWebBrowser(style);
        if (this.webBrowser != null) {
            this.webBrowser.setBrowser(this);
            this.webBrowser.create(parent, style);
            return;
        }
        this.dispose();
        String errMsg = " because there is no underlying browser available.\n";
        final String platform3;
        final String platform2 = platform3 = SWT.getPlatform();
        switch (platform3) {
            case "gtk": {
                errMsg += "Please ensure that WebKit with its GTK 3.x bindings is installed (WebKit2 API level is preferred). Additionally, please note that GTK4 does not currently have Browser support.\n";
                break;
            }
            case "cocoa": {
                errMsg += "SWT failed to load the WebKit library.\n";
                break;
            }
            case "win32": {
                errMsg += "SWT uses either IE or WebKit. Either the SWT.WEBKIT flag is passed and the WebKit library was not loaded properly by SWT, or SWT failed to load IE.\n";
                break;
            }
        }
        SWT.error(2, null, errMsg);
    }
    
    static Composite checkParent(final Composite parent) {
        final String platform = SWT.getPlatform();
        if (!"gtk".equals(platform)) {
            return parent;
        }
        if (parent != null && !parent.isDisposed()) {
            final Display display = parent.getDisplay();
            if (display != null && display.getThread() == Thread.currentThread()) {
                display.setData("org.eclipse.swt.internal.gtk.noInputMethod", "true");
            }
        }
        return parent;
    }
    
    static int checkStyle(int style) {
        final String platform = SWT.getPlatform();
        if (Browser.DefaultType == -1) {
            try {
                Class.forName("org.eclipse.swt.browser.BrowserInitializer");
            }
            catch (ClassNotFoundException ex) {}
            final String value = System.getProperty("org.eclipse.swt.browser.DefaultType");
            if (value != null) {
                int index = 0;
                final int length = value.length();
                do {
                    int newIndex = value.indexOf(44, index);
                    if (newIndex == -1) {
                        newIndex = length;
                    }
                    final String current = value.substring(index, newIndex).trim();
                    if (current.equalsIgnoreCase("webkit")) {
                        Browser.DefaultType = 65536;
                        break;
                    }
                    if (current.equalsIgnoreCase("edge") && "win32".equals(platform)) {
                        Browser.DefaultType = 262144;
                    }
                    else if (current.equalsIgnoreCase("ie") && "win32".equals(platform)) {
                        Browser.DefaultType = 0;
                        break;
                    }
                    index = newIndex + 1;
                } while (index < length);
            }
            if (Browser.DefaultType == -1) {
                Browser.DefaultType = 0;
            }
        }
        if ((style & 0x50000) == 0x0) {
            style |= Browser.DefaultType;
        }
        if ("win32".equals(platform) && (style & 0x40000) != 0x0) {
            style |= 0x1000000;
        }
        return style;
    }
    
    @Override
    protected void checkWidget() {
        super.checkWidget();
    }
    
    public static void clearSessions() {
        WebBrowser.clearSessions();
    }
    
    public static String getCookie(final String name, final String url) {
        if (name == null) {
            SWT.error(4);
        }
        if (url == null) {
            SWT.error(4);
        }
        return WebBrowser.GetCookie(name, url);
    }
    
    public static boolean setCookie(final String value, final String url) {
        if (value == null) {
            SWT.error(4);
        }
        if (url == null) {
            SWT.error(4);
        }
        return WebBrowser.SetCookie(value, url, true);
    }
    
    public void addAuthenticationListener(final AuthenticationListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.webBrowser.addAuthenticationListener(listener);
    }
    
    public void addCloseWindowListener(final CloseWindowListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.webBrowser.addCloseWindowListener(listener);
    }
    
    public void addLocationListener(final LocationListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.webBrowser.addLocationListener(listener);
    }
    
    public void addOpenWindowListener(final OpenWindowListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.webBrowser.addOpenWindowListener(listener);
    }
    
    public void addProgressListener(final ProgressListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.webBrowser.addProgressListener(listener);
    }
    
    public void addStatusTextListener(final StatusTextListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.webBrowser.addStatusTextListener(listener);
    }
    
    public void addTitleListener(final TitleListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.webBrowser.addTitleListener(listener);
    }
    
    public void addVisibilityWindowListener(final VisibilityWindowListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.webBrowser.addVisibilityWindowListener(listener);
    }
    
    public boolean back() {
        this.checkWidget();
        return this.webBrowser.back();
    }
    
    @Override
    protected void checkSubclass() {
        final String name = this.getClass().getName();
        final int index = name.lastIndexOf(46);
        if (!name.substring(0, index + 1).equals("org.eclipse.swt.browser.")) {
            SWT.error(43);
        }
    }
    
    public boolean execute(final String script) {
        this.checkWidget();
        if (script == null) {
            SWT.error(4);
        }
        return this.webBrowser.execute(script);
    }
    
    public boolean close() {
        this.checkWidget();
        if (this.webBrowser.close()) {
            this.isClosing = true;
            this.dispose();
            this.isClosing = false;
            return true;
        }
        return false;
    }
    
    public Object evaluate(final String script) throws SWTException {
        this.checkWidget();
        return this.evaluate(script, false);
    }
    
    public Object evaluate(final String script, final boolean trusted) throws SWTException {
        this.checkWidget();
        if (script == null) {
            SWT.error(4);
        }
        return this.webBrowser.evaluate(script, trusted);
    }
    
    public boolean forward() {
        this.checkWidget();
        return this.webBrowser.forward();
    }
    
    public String getBrowserType() {
        this.checkWidget();
        return this.webBrowser.getBrowserType();
    }
    
    public boolean getJavascriptEnabled() {
        this.checkWidget();
        return this.webBrowser.jsEnabledOnNextPage;
    }
    
    @Override
    public int getStyle() {
        return super.getStyle() | (this.userStyle & 0x800);
    }
    
    public String getText() {
        this.checkWidget();
        return this.webBrowser.getText();
    }
    
    public String getUrl() {
        this.checkWidget();
        return this.webBrowser.getUrl();
    }
    
    @Deprecated
    public Object getWebBrowser() {
        this.checkWidget();
        return this.webBrowser.getWebBrowser();
    }
    
    public boolean isBackEnabled() {
        this.checkWidget();
        return this.webBrowser.isBackEnabled();
    }
    
    @Override
    public boolean isFocusControl() {
        this.checkWidget();
        return this.webBrowser.isFocusControl() || super.isFocusControl();
    }
    
    public boolean isForwardEnabled() {
        this.checkWidget();
        return this.webBrowser.isForwardEnabled();
    }
    
    public void refresh() {
        this.checkWidget();
        this.webBrowser.refresh();
    }
    
    public void removeAuthenticationListener(final AuthenticationListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.webBrowser.removeAuthenticationListener(listener);
    }
    
    public void removeCloseWindowListener(final CloseWindowListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.webBrowser.removeCloseWindowListener(listener);
    }
    
    public void removeLocationListener(final LocationListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.webBrowser.removeLocationListener(listener);
    }
    
    public void removeOpenWindowListener(final OpenWindowListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.webBrowser.removeOpenWindowListener(listener);
    }
    
    public void removeProgressListener(final ProgressListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.webBrowser.removeProgressListener(listener);
    }
    
    public void removeStatusTextListener(final StatusTextListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.webBrowser.removeStatusTextListener(listener);
    }
    
    public void removeTitleListener(final TitleListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.webBrowser.removeTitleListener(listener);
    }
    
    public void removeVisibilityWindowListener(final VisibilityWindowListener listener) {
        this.checkWidget();
        if (listener == null) {
            SWT.error(4);
        }
        this.webBrowser.removeVisibilityWindowListener(listener);
    }
    
    public void setJavascriptEnabled(final boolean enabled) {
        this.checkWidget();
        this.webBrowser.jsEnabledOnNextPage = enabled;
    }
    
    public boolean setText(final String html) {
        this.checkWidget();
        return this.setText(html, true);
    }
    
    public boolean setText(final String html, final boolean trusted) {
        this.checkWidget();
        if (html == null) {
            SWT.error(4);
        }
        return this.webBrowser.setText(html, trusted);
    }
    
    public boolean setUrl(final String url) {
        this.checkWidget();
        return this.setUrl(url, null, null);
    }
    
    public boolean setUrl(final String url, final String postData, final String[] headers) {
        this.checkWidget();
        if (url == null) {
            SWT.error(4);
        }
        return this.webBrowser.setUrl(url, postData, headers);
    }
    
    public void stop() {
        this.checkWidget();
        this.webBrowser.stop();
    }
    
    static {
        Browser.DefaultType = -1;
    }
}
