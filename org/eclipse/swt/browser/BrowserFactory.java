//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import org.eclipse.swt.*;

class BrowserFactory
{
    private Class<?> chromiumClass;
    
    WebBrowser createWebBrowser(final int style) {
        WebBrowser browser = null;
        if ((style & 0x20000) != 0x0) {
            browser = this.createChromium();
            if (browser != null) {
                return browser;
            }
        }
        if ((style & 0x40000) != 0x0) {
            try {
                return new Edge();
            }
            catch (SWTError e) {
                System.err.println(e);
            }
        }
        return new IE();
    }
    
    private WebBrowser createChromium() {
        if (this.chromiumClass == null) {
            try {
                this.chromiumClass = Class.forName("org.eclipse.swt.browser.ChromiumImpl");
                return (WebBrowser)this.chromiumClass.newInstance();
            }
            catch (ClassNotFoundException e4) {
                System.err.println("SWT.CHROMIUM style was used but chromium.swt fragment/jar is missing from classpath.");
            }
            catch (NoClassDefFoundError | InstantiationException | IllegalAccessException noClassDefFoundError) {}
            catch (UnsatisfiedLinkError e5) {
                System.err.println("SWT.CHROMIUM style was used but chromium.swt " + SWT.getPlatform() + " (or CEF binaries) fragment/jar is missing.");
            }
            catch (SWTError e3) {
                System.err.println(e3.getMessage());
            }
        }
        return null;
    }
}
