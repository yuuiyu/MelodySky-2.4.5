//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPrefService extends nsISupports
{
    public static final String NS_IPREFSERVICE_IID = "{decb9cc7-c08f-4ea5-be91-a8fc637ce2d2}";
    
    void readUserPrefs(final nsIFile p0);
    
    void resetPrefs();
    
    void resetUserPrefs();
    
    void savePrefFile(final nsIFile p0);
    
    nsIPrefBranch getBranch(final String p0);
    
    nsIPrefBranch getDefaultBranch(final String p0);
}
