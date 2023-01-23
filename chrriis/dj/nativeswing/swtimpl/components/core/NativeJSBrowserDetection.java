//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.core;

import org.eclipse.swt.browser.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

class NativeJSBrowserDetection
{
    public final String browserName;
    public final String browserVersion;
    
    public NativeJSBrowserDetection(final Browser browser) {
        final Shell shell = new Shell(browser.getDisplay());
        shell.setLayout((Layout)new FillLayout());
        final Browser browser_ = new Browser((Composite)shell, browser.getStyle());
        browser_.setText("<html></html>");
        String browserName = null;
        String versionSearch = null;
        final String userAgent = (String)browser_.evaluate("return navigator.userAgent");
        final String navigatorVendor = (String)browser_.evaluate("return navigator.vendor");
        if (browserName == null && userAgent != null && userAgent.indexOf("Chrome") != -1) {
            browserName = (versionSearch = "Chrome");
        }
        if (browserName == null && userAgent != null && userAgent.indexOf("OmniWeb") != -1) {
            browserName = "OmniWeb";
            versionSearch = "OmniWeb/";
        }
        if (browserName == null && navigatorVendor != null && navigatorVendor.indexOf("Apple") != -1) {
            browserName = "Safari";
            versionSearch = "AppleWebKit";
        }
        if (browserName == null && browser_.evaluate("return window.opera") != null) {
            browserName = (versionSearch = "Opera");
        }
        if (browserName == null && navigatorVendor != null && navigatorVendor.indexOf("iCab") != -1) {
            browserName = (versionSearch = "iCab");
        }
        if (browserName == null && navigatorVendor != null && navigatorVendor.indexOf("KDE") != -1) {
            browserName = (versionSearch = "Konqueror");
        }
        if (browserName == null && userAgent != null && userAgent.indexOf("Firefox") != -1) {
            browserName = (versionSearch = "Firefox");
        }
        if (browserName == null && navigatorVendor != null && navigatorVendor.indexOf("Camino") != -1) {
            browserName = (versionSearch = "Camino");
        }
        if (browserName == null && userAgent != null && userAgent.indexOf("Netscape") != -1) {
            browserName = (versionSearch = "Netscape");
        }
        if (browserName == null && userAgent != null && userAgent.indexOf("MSIE") != -1) {
            browserName = "IE";
            versionSearch = "MSIE";
        }
        if (browserName == null && userAgent != null && userAgent.indexOf("Gecko") != -1) {
            browserName = "Mozilla";
            versionSearch = "rv";
        }
        if (browserName == null && userAgent != null && userAgent.indexOf("Mozilla") != -1) {
            browserName = "Netscape";
            versionSearch = "Mozilla";
        }
        String browserVersion = null;
        if (browserName != null) {
            if (userAgent != null) {
                final int index = userAgent.indexOf(versionSearch);
                if (index >= 0) {
                    browserVersion = userAgent.substring(index + versionSearch.length() + 1);
                }
            }
            if (browserVersion == null) {
                final String appVersion = (String)browser_.evaluate("return navigator.appVersion");
                if (appVersion != null) {
                    final int index2 = appVersion.indexOf(versionSearch);
                    if (index2 >= 0) {
                        browserVersion = appVersion.substring(index2 + versionSearch.length() + 1);
                    }
                }
            }
            if (browserVersion != null) {
                int index = -1;
                for (int i = 0; i < browserVersion.length(); ++i) {
                    final char c = browserVersion.charAt(i);
                    if (!Character.isDigit(c) && c != '.' && c != '_' && c != '-' && (c < 'a' || c > 'z') && c < 'A' && c < 'Z') {
                        index = i;
                        break;
                    }
                }
                if (index > 0) {
                    browserVersion = browserVersion.substring(0, index);
                }
            }
        }
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }
}
