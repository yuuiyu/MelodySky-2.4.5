//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAppStartup extends nsISupports
{
    public static final String NS_IAPPSTARTUP_IID = "{e9b0f89b-0529-4d96-98a8-eb5b2b9a8383}";
    public static final long eConsiderQuit = 1L;
    public static final long eAttemptQuit = 2L;
    public static final long eForceQuit = 3L;
    public static final long eRestart = 16L;
    
    void createHiddenWindow();
    
    void run();
    
    void enterLastWindowClosingSurvivalArea();
    
    void exitLastWindowClosingSurvivalArea();
    
    void quit(final long p0);
}
