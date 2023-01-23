//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ICoreWebView2NavigationStartingEventArgs extends IUnknown
{
    public ICoreWebView2NavigationStartingEventArgs(final long address) {
        super(address);
    }
    
    public int get_Uri(final long[] uri) {
        return COM.VtblCall(3, this.address, uri);
    }
    
    public int get_IsUserInitiated(final int[] isUserInitiated) {
        return COM.VtblCall(4, this.address, isUserInitiated);
    }
    
    public int get_IsRedirected(final int[] isRedirected) {
        return COM.VtblCall(5, this.address, isRedirected);
    }
    
    public int get_RequestHeaders(final long[] requestHeaders) {
        return COM.VtblCall(6, this.address, requestHeaders);
    }
    
    public int get_Cancel(final int[] cancel) {
        return COM.VtblCall(7, this.address, cancel);
    }
    
    public int put_Cancel(final boolean cancel) {
        return COM.VtblCall(8, this.address, (int)(cancel ? 1 : 0));
    }
    
    public int get_NavigationId(final long[] navigationId) {
        return COM.VtblCall(9, this.address, navigationId);
    }
}
