//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIJSXMLHttpRequest extends nsISupports
{
    public static final String NS_IJSXMLHTTPREQUEST_IID = "{9deabc90-28d5-41d3-a660-474f2254f4ba}";
    
    nsIDOMEventListener getOnload();
    
    void setOnload(final nsIDOMEventListener p0);
    
    nsIDOMEventListener getOnerror();
    
    void setOnerror(final nsIDOMEventListener p0);
    
    nsIDOMEventListener getOnprogress();
    
    void setOnprogress(final nsIDOMEventListener p0);
    
    nsIOnReadyStateChangeHandler getOnreadystatechange();
    
    void setOnreadystatechange(final nsIOnReadyStateChangeHandler p0);
}
