//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWebScriptsAccessService extends nsISupports
{
    public static final String NS_IWEBSCRIPTSACCESSSERVICE_IID = "{57e2860b-4266-4a85-bfde-ae39d945b014}";
    
    boolean canAccess(final nsIURI p0, final String p1);
    
    void invalidateCache(final String p0);
}
