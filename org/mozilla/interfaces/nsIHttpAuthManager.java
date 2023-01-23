//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIHttpAuthManager extends nsISupports
{
    public static final String NS_IHTTPAUTHMANAGER_IID = "{7ce8e9d1-8b4b-4883-a307-66fe12a50153}";
    
    void getAuthIdentity(final String p0, final String p1, final int p2, final String p3, final String p4, final String p5, final String[] p6, final String[] p7, final String[] p8);
    
    void setAuthIdentity(final String p0, final String p1, final int p2, final String p3, final String p4, final String p5, final String p6, final String p7, final String p8);
    
    void clearAll();
}
