//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ICoreWebView2NavigationCompletedEventArgs extends IUnknown
{
    public ICoreWebView2NavigationCompletedEventArgs(final long address) {
        super(address);
    }
    
    public int get_IsSuccess(final int[] isSuccess) {
        return COM.VtblCall(3, this.address, isSuccess);
    }
    
    public int get_WebErrorStatus(final int[] status) {
        return COM.VtblCall(4, this.address, status);
    }
    
    public int get_NavigationId(final long[] navigationId) {
        return COM.VtblCall(5, this.address, navigationId);
    }
}
