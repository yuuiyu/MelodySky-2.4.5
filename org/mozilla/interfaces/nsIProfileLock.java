//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIProfileLock extends nsISupports
{
    public static final String NS_IPROFILELOCK_IID = "{50e07b0a-f338-4da3-bcdb-f4bb0db94dbe}";
    
    nsILocalFile getDirectory();
    
    nsILocalFile getLocalDirectory();
    
    void unlock();
}
