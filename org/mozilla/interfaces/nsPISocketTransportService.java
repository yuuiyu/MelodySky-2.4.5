//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsPISocketTransportService extends nsISocketTransportService
{
    public static final String NS_PISOCKETTRANSPORTSERVICE_IID = "{6f704e69-a5fb-11d9-8ce8-0011246ecd24}";
    
    void init();
    
    void shutdown();
    
    boolean getAutodialEnabled();
    
    void setAutodialEnabled(final boolean p0);
}
