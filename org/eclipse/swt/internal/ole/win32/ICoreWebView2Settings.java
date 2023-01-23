//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ICoreWebView2Settings extends IUnknown
{
    public ICoreWebView2Settings(final long address) {
        super(address);
    }
    
    public int get_IsScriptEnabled(final int[] isScriptEnabled) {
        return COM.VtblCall(3, this.address, isScriptEnabled);
    }
    
    public int put_IsScriptEnabled(final boolean isScriptEnabled) {
        return COM.VtblCall(4, this.address, (int)(isScriptEnabled ? 1 : 0));
    }
    
    public int get_IsWebMessageEnabled(final int[] isWebMessageEnabled) {
        return COM.VtblCall(5, this.address, isWebMessageEnabled);
    }
    
    public int put_IsWebMessageEnabled(final boolean isWebMessageEnabled) {
        return COM.VtblCall(6, this.address, (int)(isWebMessageEnabled ? 1 : 0));
    }
    
    public int get_AreDefaultScriptDialogsEnabled(final int[] areDefaultScriptDialogsEnabled) {
        return COM.VtblCall(7, this.address, areDefaultScriptDialogsEnabled);
    }
    
    public int put_AreDefaultScriptDialogsEnabled(final boolean areDefaultScriptDialogsEnabled) {
        return COM.VtblCall(8, this.address, (int)(areDefaultScriptDialogsEnabled ? 1 : 0));
    }
    
    public int get_IsStatusBarEnabled(final int[] isStatusBarEnabled) {
        return COM.VtblCall(9, this.address, isStatusBarEnabled);
    }
    
    public int put_IsStatusBarEnabled(final boolean isStatusBarEnabled) {
        return COM.VtblCall(10, this.address, (int)(isStatusBarEnabled ? 1 : 0));
    }
    
    public int get_AreDevToolsEnabled(final int[] areDevToolsEnabled) {
        return COM.VtblCall(11, this.address, areDevToolsEnabled);
    }
    
    public int put_AreDevToolsEnabled(final boolean areDevToolsEnabled) {
        return COM.VtblCall(12, this.address, (int)(areDevToolsEnabled ? 1 : 0));
    }
    
    public int get_AreDefaultContextMenusEnabled(final int[] enabled) {
        return COM.VtblCall(13, this.address, enabled);
    }
    
    public int put_AreDefaultContextMenusEnabled(final boolean enabled) {
        return COM.VtblCall(14, this.address, (int)(enabled ? 1 : 0));
    }
    
    public int get_AreHostObjectsAllowed(final int[] allowed) {
        return COM.VtblCall(15, this.address, allowed);
    }
    
    public int put_AreHostObjectsAllowed(final boolean allowed) {
        return COM.VtblCall(16, this.address, (int)(allowed ? 1 : 0));
    }
    
    public int get_IsZoomControlEnabled(final int[] enabled) {
        return COM.VtblCall(17, this.address, enabled);
    }
    
    public int put_IsZoomControlEnabled(final boolean enabled) {
        return COM.VtblCall(18, this.address, (int)(enabled ? 1 : 0));
    }
    
    public int get_IsBuiltInErrorPageEnabled(final int[] enabled) {
        return COM.VtblCall(19, this.address, enabled);
    }
    
    public int put_IsBuiltInErrorPageEnabled(final boolean enabled) {
        return COM.VtblCall(20, this.address, (int)(enabled ? 1 : 0));
    }
}
