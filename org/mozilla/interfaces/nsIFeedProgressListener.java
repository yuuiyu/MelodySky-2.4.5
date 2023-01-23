//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFeedProgressListener extends nsIFeedResultListener
{
    public static final String NS_IFEEDPROGRESSLISTENER_IID = "{ebfd5de5-713c-40c0-ad7c-f095117fa580}";
    
    void reportError(final String p0, final int p1, final boolean p2);
    
    void handleStartFeed(final nsIFeedResult p0);
    
    void handleFeedAtFirstEntry(final nsIFeedResult p0);
    
    void handleEntry(final nsIFeedEntry p0, final nsIFeedResult p1);
}
