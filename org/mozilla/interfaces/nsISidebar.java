//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISidebar extends nsISupports
{
    public static final String NS_ISIDEBAR_IID = "{577cb745-8caf-11d3-aaef-00805f8a4905}";
    
    void addPanel(final String p0, final String p1, final String p2);
    
    void addPersistentPanel(final String p0, final String p1, final String p2);
    
    void addSearchEngine(final String p0, final String p1, final String p2, final String p3);
}
