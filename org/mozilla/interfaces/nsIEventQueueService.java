//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIEventQueueService extends nsISupports
{
    public static final String NS_IEVENTQUEUESERVICE_IID = "{a6cf90dc-15b3-11d2-932e-00805f8add32}";
    public static final int CURRENT_THREAD_EVENT_QUEUE = 0;
    public static final int UI_THREAD_EVENT_QUEUE = 1;
    
    void createThreadEventQueue();
    
    void createMonitoredThreadEventQueue();
    
    void destroyThreadEventQueue();
    
    nsIEventQueue createFromIThread(final nsIThread p0, final boolean p1);
    
    nsIEventQueue pushThreadEventQueue();
    
    void popThreadEventQueue(final nsIEventQueue p0);
    
    nsIEventQueue getSpecialEventQueue(final int p0);
}
