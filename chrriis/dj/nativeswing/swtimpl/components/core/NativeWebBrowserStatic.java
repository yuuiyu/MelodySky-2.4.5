//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.core;

import chrriis.dj.nativeswing.swtimpl.components.internal.*;

class NativeWebBrowserStatic implements INativeWebBrowserStatic
{
    @Override
    public void clearSessionCookies() {
        NativeWebBrowser.clearSessionCookies();
    }
    
    @Override
    public String getCookie(final String url, final String name) {
        return NativeWebBrowser.getCookie(url, name);
    }
    
    @Override
    public void setCookie(final String url, final String value) {
        NativeWebBrowser.setCookie(url, value);
    }
}
