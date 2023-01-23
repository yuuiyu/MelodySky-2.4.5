//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface imgICache extends nsISupports
{
    public static final String IMGICACHE_IID = "{f1b74aae-5661-4753-a21c-66dd644afebc}";
    
    void clearCache(final boolean p0);
    
    void removeEntry(final nsIURI p0);
    
    nsIProperties findEntryProperties(final nsIURI p0);
}
