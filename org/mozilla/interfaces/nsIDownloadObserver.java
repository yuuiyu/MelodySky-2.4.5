//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDownloadObserver extends nsISupports
{
    public static final String NS_IDOWNLOADOBSERVER_IID = "{44b3153e-a54e-4077-a527-b0325e40924e}";
    
    void onDownloadComplete(final nsIDownloader p0, final nsIRequest p1, final nsISupports p2, final long p3, final nsIFile p4);
}
