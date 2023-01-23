//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXULChromeRegistry extends nsIChromeRegistry
{
    public static final String NS_IXULCHROMEREGISTRY_IID = "{3e51f40b-b4b0-4e60-ac45-6c63477ebe41}";
    
    void reloadChrome();
    
    String getSelectedLocale(final String p0);
    
    void refreshSkins();
    
    boolean allowScriptsForPackage(final nsIURI p0);
}
