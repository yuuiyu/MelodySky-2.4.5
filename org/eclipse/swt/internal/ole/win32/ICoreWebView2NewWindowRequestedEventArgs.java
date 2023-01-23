//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ICoreWebView2NewWindowRequestedEventArgs extends IUnknown
{
    public ICoreWebView2NewWindowRequestedEventArgs(final long address) {
        super(address);
    }
    
    public int get_Uri(final long[] uri) {
        return COM.VtblCall(3, this.address, uri);
    }
    
    public int put_NewWindow(final long newWindow) {
        return COM.VtblCall(4, this.address, newWindow);
    }
    
    public int get_NewWindow(final long[] newWindow) {
        return COM.VtblCall(5, this.address, newWindow);
    }
    
    public int put_Handled(final boolean handled) {
        return COM.VtblCall(6, this.address, (int)(handled ? 1 : 0));
    }
    
    public int get_Handled(final int[] handled) {
        return COM.VtblCall(7, this.address, handled);
    }
    
    public int get_IsUserInitiated(final int[] isUserInitiated) {
        return COM.VtblCall(8, this.address, isUserInitiated);
    }
    
    public int GetDeferral(final long[] deferral) {
        return COM.VtblCall(9, this.address, deferral);
    }
    
    public int get_WindowFeatures(final long[] value) {
        return COM.VtblCall(10, this.address, value);
    }
}
