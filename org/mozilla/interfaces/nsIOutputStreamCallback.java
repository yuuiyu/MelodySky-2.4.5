//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIOutputStreamCallback extends nsISupports
{
    public static final String NS_IOUTPUTSTREAMCALLBACK_IID = "{40dbcdff-9053-42c5-a57c-3ec910d0f148}";
    
    void onOutputStreamReady(final nsIAsyncOutputStream p0);
}
