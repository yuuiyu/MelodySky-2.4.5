//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFullScreen extends nsISupports
{
    public static final String NS_IFULLSCREEN_IID = "{9854976e-1dd1-11b2-8350-e6d35099fbce}";
    
    void hideAllOSChrome();
    
    void showAllOSChrome();
    
    nsISimpleEnumerator getChromeItems();
}
