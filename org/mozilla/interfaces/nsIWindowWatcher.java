//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWindowWatcher extends nsISupports
{
    public static final String NS_IWINDOWWATCHER_IID = "{002286a8-494b-43b3-8ddd-49e3fc50622b}";
    
    nsIDOMWindow openWindow(final nsIDOMWindow p0, final String p1, final String p2, final String p3, final nsISupports p4);
    
    void registerNotification(final nsIObserver p0);
    
    void unregisterNotification(final nsIObserver p0);
    
    nsISimpleEnumerator getWindowEnumerator();
    
    nsIPrompt getNewPrompter(final nsIDOMWindow p0);
    
    nsIAuthPrompt getNewAuthPrompter(final nsIDOMWindow p0);
    
    void setWindowCreator(final nsIWindowCreator p0);
    
    nsIWebBrowserChrome getChromeForWindow(final nsIDOMWindow p0);
    
    nsIDOMWindow getWindowByName(final String p0, final nsIDOMWindow p1);
    
    nsIDOMWindow getActiveWindow();
    
    void setActiveWindow(final nsIDOMWindow p0);
}
