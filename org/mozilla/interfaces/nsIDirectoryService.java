//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDirectoryService extends nsISupports
{
    public static final String NS_IDIRECTORYSERVICE_IID = "{57a66a60-d43a-11d3-8cc2-00609792278c}";
    
    void init();
    
    void registerProvider(final nsIDirectoryServiceProvider p0);
    
    void unregisterProvider(final nsIDirectoryServiceProvider p0);
}
