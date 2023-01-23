//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIEventQueue extends nsIEventTarget
{
    public static final String NS_IEVENTQUEUE_IID = "{176afb41-00a4-11d3-9f2a-00400553eef0}";
    
    boolean pendingEvents();
    
    void processPendingEvents();
    
    void eventLoop();
    
    void init(final boolean p0);
    
    void enterMonitor();
    
    void exitMonitor();
    
    boolean isQueueNative();
    
    void stopAcceptingEvents();
}
