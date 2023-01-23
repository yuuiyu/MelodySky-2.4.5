//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRequest extends nsISupports
{
    public static final String NS_IREQUEST_IID = "{ef6bfbd2-fd46-48d8-96b7-9f8f0fd387fe}";
    public static final long LOAD_NORMAL = 0L;
    public static final long LOAD_BACKGROUND = 1L;
    public static final long INHIBIT_CACHING = 128L;
    public static final long INHIBIT_PERSISTENT_CACHING = 256L;
    public static final long LOAD_BYPASS_CACHE = 512L;
    public static final long LOAD_FROM_CACHE = 1024L;
    public static final long VALIDATE_ALWAYS = 2048L;
    public static final long VALIDATE_NEVER = 4096L;
    public static final long VALIDATE_ONCE_PER_SESSION = 8192L;
    
    String getName();
    
    boolean isPending();
    
    long getStatus();
    
    void cancel(final long p0);
    
    void suspend();
    
    void resume();
    
    nsILoadGroup getLoadGroup();
    
    void setLoadGroup(final nsILoadGroup p0);
    
    long getLoadFlags();
    
    void setLoadFlags(final long p0);
}
