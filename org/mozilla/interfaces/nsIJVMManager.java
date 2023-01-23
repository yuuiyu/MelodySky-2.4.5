//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIJVMManager extends nsISupports
{
    public static final String NS_IJVMMANAGER_IID = "{a1e5ed50-aa4a-11d1-85b2-00805f0e4dfe}";
    
    void showJavaConsole();
    
    boolean isAllPermissionGranted(final String p0, final String p1, final String p2, final String p3);
    
    boolean getJavaEnabled();
}
