//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIURILoader extends nsISupports
{
    public static final String NS_IURILOADER_IID = "{5cf6420c-74f3-4a7c-bc1d-f5756d79ea07}";
    
    void registerContentListener(final nsIURIContentListener p0);
    
    void unRegisterContentListener(final nsIURIContentListener p0);
    
    void openURI(final nsIChannel p0, final boolean p1, final nsIInterfaceRequestor p2);
    
    void stop(final nsISupports p0);
}
