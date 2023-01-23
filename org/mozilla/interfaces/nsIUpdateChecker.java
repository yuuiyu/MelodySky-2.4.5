//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIUpdateChecker extends nsISupports
{
    public static final String NS_IUPDATECHECKER_IID = "{877ace25-8bc5-452a-8586-9c1cf2871994}";
    public static final int CURRENT_CHECK = 1;
    public static final int CURRENT_SESSION = 2;
    public static final int ANY_CHECKS = 3;
    
    void checkForUpdates(final nsIUpdateCheckListener p0, final boolean p1);
    
    void stopChecking(final int p0);
}
