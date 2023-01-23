//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

import org.eclipse.swt.internal.win32.*;

public class ICoreWebView2Controller extends IUnknown
{
    public ICoreWebView2Controller(final long address) {
        super(address);
    }
    
    public int put_IsVisible(final boolean isVisible) {
        return COM.VtblCall(4, this.address, (int)(isVisible ? 1 : 0));
    }
    
    public int put_Bounds(final RECT bounds) {
        return COM.VtblCall_put_Bounds(6, this.address, bounds);
    }
    
    public int MoveFocus(final int reason) {
        return COM.VtblCall(12, this.address, reason);
    }
    
    public int add_MoveFocusRequested(final IUnknown eventHandler, final long[] token) {
        return COM.VtblCall(13, this.address, eventHandler.address, token);
    }
    
    public int add_GotFocus(final IUnknown eventHandler, final long[] token) {
        return COM.VtblCall(15, this.address, eventHandler.address, token);
    }
    
    public int add_LostFocus(final IUnknown eventHandler, final long[] token) {
        return COM.VtblCall(17, this.address, eventHandler.address, token);
    }
    
    public int add_AcceleratorKeyPressed(final IUnknown eventHandler, final long[] token) {
        return COM.VtblCall(19, this.address, eventHandler.address, token);
    }
    
    public int NotifyParentWindowPositionChanged() {
        return COM.VtblCall(23, this.address);
    }
    
    public int Close() {
        return COM.VtblCall(24, this.address);
    }
    
    public int get_CoreWebView2(final long[] coreWebView2) {
        return COM.VtblCall(25, this.address, coreWebView2);
    }
}
