//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ICoreWebView2_2 extends ICoreWebView2
{
    public ICoreWebView2_2(final long address) {
        super(address);
    }
    
    public int NavigateWithWebResourceRequest(final IUnknown request) {
        return COM.VtblCall(63, this.address, request.address);
    }
    
    public int add_DOMContentLoaded(final IUnknown handler, final long[] token) {
        return COM.VtblCall(64, this.address, handler.getAddress(), token);
    }
    
    public int get_CookieManager(final long[] cookieManager) {
        return COM.VtblCall(66, this.address, cookieManager);
    }
}
