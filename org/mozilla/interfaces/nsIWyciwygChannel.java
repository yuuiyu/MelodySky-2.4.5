//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWyciwygChannel extends nsIChannel
{
    public static final String NS_IWYCIWYGCHANNEL_IID = "{280da566-6f19-4487-a8ca-70c5ba1602c1}";
    
    void writeToCacheEntry(final String p0);
    
    void closeCacheEntry(final long p0);
    
    void setSecurityInfo(final nsISupports p0);
}
