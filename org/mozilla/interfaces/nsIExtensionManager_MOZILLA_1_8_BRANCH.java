//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIExtensionManager_MOZILLA_1_8_BRANCH extends nsIExtensionManager
{
    public static final String NS_IEXTENSIONMANAGER_MOZILLA_1_8_BRANCH_IID = "{0fd5caa9-1ffc-42b1-aea1-3fbe73116070}";
    
    void cancelUninstallItem(final String p0);
    
    nsIUpdateItem[] getDependentItemListForID(final String p0, final boolean p1, final long[] p2);
    
    void checkForBlocklistChanges();
    
    void sortTypeByProperty(final long p0, final String p1, final boolean p2);
}
