//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICacheSession extends nsISupports
{
    public static final String NS_ICACHESESSION_IID = "{ae9e84b5-3e2d-457e-8fcd-5bbd2a8b832e}";
    
    boolean getDoomEntriesIfExpired();
    
    void setDoomEntriesIfExpired(final boolean p0);
    
    nsICacheEntryDescriptor openCacheEntry(final String p0, final int p1, final boolean p2);
    
    void asyncOpenCacheEntry(final String p0, final int p1, final nsICacheListener p2);
    
    void evictEntries();
    
    boolean isStorageEnabled();
}
