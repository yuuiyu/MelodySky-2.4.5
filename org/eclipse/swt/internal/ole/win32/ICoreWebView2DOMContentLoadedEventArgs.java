//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ICoreWebView2DOMContentLoadedEventArgs extends IUnknown
{
    public ICoreWebView2DOMContentLoadedEventArgs(final long address) {
        super(address);
    }
    
    public int get_NavigationId(final long[] navigationId) {
        return COM.VtblCall(3, this.address, navigationId);
    }
}
