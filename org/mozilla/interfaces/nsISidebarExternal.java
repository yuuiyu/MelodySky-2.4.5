//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISidebarExternal extends nsISupports
{
    public static final String NS_ISIDEBAREXTERNAL_IID = "{4350fb73-9305-41df-a669-11d26222d420}";
    
    void addSearchProvider(final String p0);
    
    long isSearchProviderInstalled(final String p0);
}
