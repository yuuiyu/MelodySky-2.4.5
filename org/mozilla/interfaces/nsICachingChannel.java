//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICachingChannel extends nsISupports
{
    public static final String NS_ICACHINGCHANNEL_IID = "{b1f95f5e-ee05-4434-9d34-89a935d7feef}";
    public static final long LOAD_BYPASS_LOCAL_CACHE = 268435456L;
    public static final long LOAD_BYPASS_LOCAL_CACHE_IF_BUSY = 536870912L;
    public static final long LOAD_ONLY_FROM_CACHE = 1073741824L;
    public static final long LOAD_ONLY_IF_MODIFIED = 2147483648L;
    
    nsISupports getCacheToken();
    
    void setCacheToken(final nsISupports p0);
    
    nsISupports getCacheKey();
    
    void setCacheKey(final nsISupports p0);
    
    boolean getCacheAsFile();
    
    void setCacheAsFile(final boolean p0);
    
    nsIFile getCacheFile();
    
    boolean isFromCache();
}
