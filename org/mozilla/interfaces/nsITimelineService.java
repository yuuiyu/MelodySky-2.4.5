//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITimelineService extends nsISupports
{
    public static final String NS_ITIMELINESERVICE_IID = "{93276790-3daf-11d5-b67d-000064657374}";
    
    void mark(final String p0);
    
    void indent();
    
    void outdent();
    
    void enter(final String p0);
    
    void leave(final String p0);
    
    void startTimer(final String p0);
    
    void stopTimer(final String p0);
    
    void markTimer(final String p0);
    
    void resetTimer(final String p0);
    
    void markTimerWithComment(final String p0, final String p1);
}
