//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsPIXPIProxy extends nsISupports
{
    public static final String NS_PIXPIPROXY_IID = "{6f9d2890-167d-11d5-8daf-000064657374}";
    
    void refreshPlugins(final boolean p0);
    
    void notifyRestartNeeded();
    
    void alert(final String p0, final String p1);
    
    int confirmEx(final String p0, final String p1, final long p2, final String p3, final String p4, final String p5, final String p6, final boolean[] p7);
}
