//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import org.eclipse.swt.*;
import java.util.*;

public class BrowserFunction
{
    Browser browser;
    String name;
    String functionString;
    int index;
    boolean isEvaluate;
    boolean top;
    String token;
    String[] frameNames;
    
    public BrowserFunction(final Browser browser, final String name) {
        this(browser, name, true, null, true);
    }
    
    public BrowserFunction(final Browser browser, final String name, final boolean top, final String[] frameNames) {
        this(browser, name, top, frameNames, true);
    }
    
    BrowserFunction(final Browser browser, final String name, final boolean top, final String[] frameNames, final boolean create) {
        if (browser == null) {
            SWT.error(4);
        }
        if (name == null) {
            SWT.error(4);
        }
        if (browser.isDisposed()) {
            SWT.error(24);
        }
        browser.checkWidget();
        this.browser = browser;
        this.name = name;
        this.top = top;
        this.frameNames = frameNames;
        final Random random = new Random();
        final byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        final StringBuilder buffer = new StringBuilder();
        for (final byte b : bytes) {
            buffer.append(Integer.toHexString(b & 0xFF));
        }
        this.token = buffer.toString();
        if (create) {
            browser.webBrowser.createFunction(this);
        }
    }
    
    public void dispose() {
        this.dispose(true);
    }
    
    void dispose(final boolean remove) {
        if (this.index < 0) {
            return;
        }
        if (remove) {
            this.browser.webBrowser.destroyFunction(this);
        }
        this.browser = null;
        final String s = null;
        this.functionString = s;
        this.name = s;
        this.index = -1;
    }
    
    public Object function(final Object[] arguments) {
        if (this.index < 0) {
            SWT.error(49);
        }
        this.browser.checkWidget();
        return null;
    }
    
    public Browser getBrowser() {
        if (this.index < 0) {
            SWT.error(49);
        }
        this.browser.checkWidget();
        return this.browser;
    }
    
    public String getName() {
        if (this.index < 0) {
            SWT.error(49);
        }
        this.browser.checkWidget();
        return this.name;
    }
    
    public boolean isDisposed() {
        return this.index < 0;
    }
}
