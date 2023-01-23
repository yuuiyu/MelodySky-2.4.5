//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIToolkitProfile extends nsISupports
{
    public static final String NS_ITOOLKITPROFILE_IID = "{7422b090-4a86-4407-972e-75468a625388}";
    
    nsILocalFile getRootDir();
    
    nsILocalFile getLocalDir();
    
    String getName();
    
    void setName(final String p0);
    
    void remove(final boolean p0);
    
    nsIProfileLock lock(final nsIProfileUnlocker[] p0);
}
