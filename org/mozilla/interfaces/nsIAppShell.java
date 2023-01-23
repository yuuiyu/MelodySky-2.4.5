//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAppShell extends nsISupports
{
    public static final String NS_IAPPSHELL_IID = "{a0757c31-eeac-11d1-9ec1-00aa002fb821}";
    
    void create(final int[] p0, final String[] p1);
    
    void run();
    
    void spinup();
    
    void spindown();
    
    void listenToEventQueue(final long p0, final boolean p1);
    
    void getNativeEvent(final boolean p0, final long p1);
    
    void dispatchNativeEvent(final boolean p0, final long p1);
    
    void exit();
}
