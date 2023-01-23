//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIChannelEventSink extends nsISupports
{
    public static final String NS_ICHANNELEVENTSINK_IID = "{6757d790-2916-498e-aaca-6b668a956875}";
    public static final long REDIRECT_TEMPORARY = 1L;
    public static final long REDIRECT_PERMANENT = 2L;
    public static final long REDIRECT_INTERNAL = 4L;
    
    void onChannelRedirect(final nsIChannel p0, final nsIChannel p1, final long p2);
}
