//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIUpdateManager extends nsISupports
{
    public static final String NS_IUPDATEMANAGER_IID = "{fede66a9-9f96-4507-a22a-775ee885577e}";
    
    nsIUpdate getUpdateAt(final int p0);
    
    int getUpdateCount();
    
    nsIUpdate getActiveUpdate();
    
    void setActiveUpdate(final nsIUpdate p0);
    
    void saveUpdates();
}
