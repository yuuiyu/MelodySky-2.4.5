//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXULAppInfo extends nsISupports
{
    public static final String NS_IXULAPPINFO_IID = "{a61ede2a-ef09-11d9-a5ce-001124787b2e}";
    
    String getVendor();
    
    String getName();
    
    String getID();
    
    String getVersion();
    
    String getAppBuildID();
    
    String getPlatformVersion();
    
    String getPlatformBuildID();
}
