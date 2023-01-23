//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWebBrowserFocus extends nsISupports
{
    public static final String NS_IWEBBROWSERFOCUS_IID = "{9c5d3c58-1dd1-11b2-a1c9-f3699284657a}";
    
    void activate();
    
    void deactivate();
    
    void setFocusAtFirstElement();
    
    void setFocusAtLastElement();
    
    nsIDOMWindow getFocusedWindow();
    
    void setFocusedWindow(final nsIDOMWindow p0);
    
    nsIDOMElement getFocusedElement();
    
    void setFocusedElement(final nsIDOMElement p0);
}
