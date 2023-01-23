//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ICoreWebView2Environment extends IUnknown
{
    public ICoreWebView2Environment(final long address) {
        super(address);
    }
    
    public int CreateCoreWebView2Controller(final long parentWindow, final IUnknown handler) {
        return COM.VtblCall(3, this.address, parentWindow, handler.address);
    }
    
    public int get_BrowserVersionString(final long[] versionInfo) {
        return COM.VtblCall(5, this.address, versionInfo);
    }
}
