//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIThread extends nsISupports
{
    public static final String NS_ITHREAD_IID = "{6be5e380-6886-11d3-9382-00104ba0fd40}";
    public static final long PRIORITY_LOW = 0L;
    public static final long PRIORITY_NORMAL = 1L;
    public static final long PRIORITY_HIGH = 2L;
    public static final long PRIORITY_URGENT = 3L;
    public static final long SCOPE_LOCAL = 0L;
    public static final long SCOPE_GLOBAL = 1L;
    public static final long SCOPE_BOUND = 2L;
    public static final long STATE_JOINABLE = 0L;
    public static final long STATE_UNJOINABLE = 1L;
    
    void join();
    
    void interrupt();
    
    long getPriority();
    
    void setPriority(final long p0);
    
    long getScope();
    
    long getState();
    
    void init(final nsIRunnable p0, final long p1, final long p2, final long p3, final long p4);
    
    nsIThread getCurrentThread();
    
    void sleep(final long p0);
}
