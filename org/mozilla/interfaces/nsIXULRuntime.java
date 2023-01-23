//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXULRuntime extends nsISupports
{
    public static final String NS_IXULRUNTIME_IID = "{2848ab92-d912-11d9-89f7-001124787b2e}";
    
    boolean getInSafeMode();
    
    boolean getLogConsoleErrors();
    
    void setLogConsoleErrors(final boolean p0);
    
    String getOS();
    
    String getXPCOMABI();
}
