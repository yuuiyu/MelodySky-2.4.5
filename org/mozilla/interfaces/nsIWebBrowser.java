//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWebBrowser extends nsISupports
{
    public static final String NS_IWEBBROWSER_IID = "{69e5df00-7b8b-11d3-af61-00a024ffc08c}";
    
    void addWebBrowserListener(final nsISupports p0, final String p1);
    
    void removeWebBrowserListener(final nsISupports p0, final String p1);
    
    nsIWebBrowserChrome getContainerWindow();
    
    void setContainerWindow(final nsIWebBrowserChrome p0);
    
    nsIURIContentListener getParentURIContentListener();
    
    void setParentURIContentListener(final nsIURIContentListener p0);
    
    nsIDOMWindow getContentDOMWindow();
}
