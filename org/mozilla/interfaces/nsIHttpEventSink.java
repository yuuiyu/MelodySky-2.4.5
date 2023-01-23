//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIHttpEventSink extends nsISupports
{
    public static final String NS_IHTTPEVENTSINK_IID = "{9475a6af-6352-4251-90f9-d65b1cd2ea15}";
    
    void onRedirect(final nsIHttpChannel p0, final nsIChannel p1);
}
