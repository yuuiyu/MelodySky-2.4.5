//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIJVMConfigManager extends nsISupports
{
    public static final String NS_IJVMCONFIGMANAGER_IID = "{ca29fff1-a677-493c-9d80-3dc60432212b}";
    
    nsIArray getJVMConfigList();
    
    void setCurrentJVMConfig(final nsIJVMConfig p0);
}
