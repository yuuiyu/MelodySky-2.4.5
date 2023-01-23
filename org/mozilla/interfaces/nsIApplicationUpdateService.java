//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIApplicationUpdateService extends nsISupports
{
    public static final String NS_IAPPLICATIONUPDATESERVICE_IID = "{9849c4bf-5197-4d22-baa8-e3b44a1703d2}";
    
    nsIUpdateChecker getBackgroundChecker();
    
    nsIUpdate selectUpdate(final nsIUpdate[] p0, final long p1);
    
    void addDownloadListener(final nsIRequestObserver p0);
    
    void removeDownloadListener(final nsIRequestObserver p0);
    
    String downloadUpdate(final nsIUpdate p0, final boolean p1);
    
    void pauseDownload();
    
    boolean getIsDownloading();
    
    boolean getCanUpdate();
}
