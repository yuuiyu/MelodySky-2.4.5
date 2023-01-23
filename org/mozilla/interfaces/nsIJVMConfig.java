//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIJVMConfig extends nsISupports
{
    public static final String NS_IJVMCONFIG_IID = "{3e333e20-b190-42d8-b993-d5fa435e46c4}";
    
    String getVersion();
    
    String getType();
    
    String getOS();
    
    String getArch();
    
    nsIFile getPath();
    
    nsIFile getMozillaPluginPath();
    
    String getDescription();
}
