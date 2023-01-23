//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public class ICoreWebView2 extends IUnknown
{
    public ICoreWebView2(final long address) {
        super(address);
    }
    
    public int get_Settings(final long[] settings) {
        return COM.VtblCall(3, this.address, settings);
    }
    
    public int get_Source(final long[] uri) {
        return COM.VtblCall(4, this.address, uri);
    }
    
    public int Navigate(final char[] uri) {
        return COM.VtblCall(5, this.address, uri);
    }
    
    public int NavigateToString(final char[] htmlContent) {
        return COM.VtblCall(6, this.address, htmlContent);
    }
    
    public int add_NavigationStarting(final IUnknown eventHandler, final long[] token) {
        return COM.VtblCall(7, this.address, eventHandler.address, token);
    }
    
    public int add_ContentLoading(final IUnknown eventHandler, final long[] token) {
        return COM.VtblCall(9, this.address, eventHandler.address, token);
    }
    
    public int add_SourceChanged(final IUnknown eventHandler, final long[] token) {
        return COM.VtblCall(11, this.address, eventHandler.address, token);
    }
    
    public int add_HistoryChanged(final IUnknown eventHandler, final long[] token) {
        return COM.VtblCall(13, this.address, eventHandler.address, token);
    }
    
    public int add_NavigationCompleted(final IUnknown eventHandler, final long[] token) {
        return COM.VtblCall(15, this.address, eventHandler.address, token);
    }
    
    public int add_FrameNavigationStarting(final IUnknown eventHandler, final long[] token) {
        return COM.VtblCall(17, this.address, eventHandler.address, token);
    }
    
    public int add_FrameNavigationCompleted(final IUnknown eventHandler, final long[] token) {
        return COM.VtblCall(19, this.address, eventHandler.address, token);
    }
    
    public int AddScriptToExecuteOnDocumentCreated(final char[] javaScript, final long handler) {
        return COM.VtblCall(27, this.address, javaScript, handler);
    }
    
    public int ExecuteScript(final char[] javaScript, final IUnknown handler) {
        return COM.VtblCall(29, this.address, javaScript, handler.address);
    }
    
    public int Reload() {
        return COM.VtblCall(31, this.address);
    }
    
    public int PostWebMessageAsJson(final char[] webMessageAsJson) {
        return COM.VtblCall(32, this.address, webMessageAsJson);
    }
    
    public int add_WebMessageReceived(final long handler, final long[] token) {
        return COM.VtblCall(34, this.address, handler, token);
    }
    
    public int get_CanGoBack(final int[] canGoBack) {
        return COM.VtblCall(38, this.address, canGoBack);
    }
    
    public int get_CanGoForward(final int[] canGoForward) {
        return COM.VtblCall(39, this.address, canGoForward);
    }
    
    public int GoBack() {
        return COM.VtblCall(40, this.address);
    }
    
    public int GoForward() {
        return COM.VtblCall(41, this.address);
    }
    
    public int Stop() {
        return COM.VtblCall(43, this.address);
    }
    
    public int add_NewWindowRequested(final IUnknown eventHandler, final long[] token) {
        return COM.VtblCall(44, this.address, eventHandler.address, token);
    }
    
    public int add_DocumentTitleChanged(final IUnknown eventHandler, final long[] token) {
        return COM.VtblCall(46, this.address, eventHandler.address, token);
    }
    
    public int get_DocumentTitle(final long[] title) {
        return COM.VtblCall(48, this.address, title);
    }
    
    public int AddHostObjectToScript(final char[] name, final long[] object) {
        return COM.VtblCall(49, this.address, name, object);
    }
    
    public int add_ContainsFullScreenElementChanged(final long eventHandler, final long[] token) {
        return COM.VtblCall(52, this.address, eventHandler, token);
    }
    
    public int get_ContainsFullScreenElement(final int[] containsFullScreenElement) {
        return COM.VtblCall(54, this.address, containsFullScreenElement);
    }
    
    public int add_WindowCloseRequested(final IUnknown eventHandler, final long[] token) {
        return COM.VtblCall(59, this.address, eventHandler.address, token);
    }
}
