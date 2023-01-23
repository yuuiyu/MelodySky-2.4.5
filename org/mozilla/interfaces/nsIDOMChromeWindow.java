//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMChromeWindow extends nsISupports
{
    public static final String NS_IDOMCHROMEWINDOW_IID = "{445fa0fc-2151-4cb4-83d3-34c3e39453de}";
    public static final int STATE_MAXIMIZED = 1;
    public static final int STATE_MINIMIZED = 2;
    public static final int STATE_NORMAL = 3;
    
    String getTitle();
    
    void setTitle(final String p0);
    
    int getWindowState();
    
    nsIBrowserDOMWindow getBrowserDOMWindow();
    
    void setBrowserDOMWindow(final nsIBrowserDOMWindow p0);
    
    void getAttention();
    
    void getAttentionWithCycleCount(final int p0);
    
    void setCursor(final String p0);
    
    void maximize();
    
    void minimize();
    
    void restore();
}
