//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIConsoleService extends nsISupports
{
    public static final String NS_ICONSOLESERVICE_IID = "{a647f184-1dd1-11b2-a9d1-8537b201161b}";
    
    void logMessage(final nsIConsoleMessage p0);
    
    void logStringMessage(final String p0);
    
    void getMessageArray(final nsIConsoleMessage[][] p0, final long[] p1);
    
    void registerListener(final nsIConsoleListener p0);
    
    void unregisterListener(final nsIConsoleListener p0);
}
