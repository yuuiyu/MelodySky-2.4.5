//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITimerManager extends nsISupports
{
    public static final String NS_ITIMERMANAGER_IID = "{8fce8c6a-1dd2-11b2-8352-8cdd2b965efc}";
    
    boolean getUseIdleTimers();
    
    void setUseIdleTimers(final boolean p0);
    
    boolean hasIdleTimers();
    
    void fireNextIdleTimer();
}
