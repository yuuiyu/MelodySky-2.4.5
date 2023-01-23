//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMWindow extends nsISupports
{
    public static final String NS_IDOMWINDOW_IID = "{a6cf906b-15b3-11d2-932e-00805f8add32}";
    
    nsIDOMDocument getDocument();
    
    nsIDOMWindow getParent();
    
    nsIDOMWindow getTop();
    
    nsIDOMBarProp getScrollbars();
    
    String getName();
    
    void setName(final String p0);
    
    int getScrollX();
    
    int getScrollY();
    
    void scrollTo(final int p0, final int p1);
    
    void scrollBy(final int p0, final int p1);
    
    nsISelection getSelection();
    
    void scrollByLines(final int p0);
    
    void scrollByPages(final int p0);
    
    void sizeToContent();
}
