//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXPInstallManager extends nsISupports
{
    public static final String NS_IXPINSTALLMANAGER_IID = "{566689cb-9926-4bec-a66e-a034e364ad2c}";
    
    void initManagerFromChrome(final String[] p0, final long p1, final nsIXPIProgressDialog p2);
    
    void initManagerWithHashes(final String[] p0, final String[] p1, final long p2, final nsIXPIProgressDialog p3);
}
