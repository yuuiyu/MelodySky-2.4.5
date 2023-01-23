//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ICoreWebView2CookieManager extends IUnknown
{
    public ICoreWebView2CookieManager(final long address) {
        super(address);
    }
    
    public int CreateCookie(final char[] name, final char[] value, final char[] domain, final char[] path, final long[] cookie) {
        return COM.VtblCall(3, this.address, name, value, domain, path, cookie);
    }
    
    public int GetCookies(final char[] uri, final IUnknown handler) {
        return COM.VtblCall(5, this.address, uri, handler.getAddress());
    }
    
    public int AddOrUpdateCookie(final ICoreWebView2Cookie cookie) {
        return COM.VtblCall(6, this.address, cookie.getAddress());
    }
    
    public int DeleteCookie(final ICoreWebView2Cookie cookie) {
        return COM.VtblCall(7, this.address, cookie.getAddress());
    }
}
