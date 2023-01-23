//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIUnicharStreamLoaderObserver extends nsISupports
{
    public static final String NS_IUNICHARSTREAMLOADEROBSERVER_IID = "{e06e8b08-8cdd-4503-a0a0-6f3b943602af}";
    
    String onDetermineCharset(final nsIUnicharStreamLoader p0, final nsISupports p1, final String p2, final long p3);
    
    void onStreamComplete(final nsIUnicharStreamLoader p0, final nsISupports p1, final long p2, final nsIUnicharInputStream p3);
}
