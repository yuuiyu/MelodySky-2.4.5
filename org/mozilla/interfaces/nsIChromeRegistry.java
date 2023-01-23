//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIChromeRegistry extends nsISupports
{
    public static final String NS_ICHROMEREGISTRY_IID = "{68389281-f6d0-4533-841d-344a2018140c}";
    public static final int NONE = 0;
    public static final int PARTIAL = 1;
    public static final int FULL = 2;
    
    nsIURI convertChromeURL(final nsIURI p0);
    
    void checkForNewChrome();
}
