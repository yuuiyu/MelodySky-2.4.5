//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITransportEventSink extends nsISupports
{
    public static final String NS_ITRANSPORTEVENTSINK_IID = "{eda4f520-67f7-484b-a691-8c3226a5b0a6}";
    
    void onTransportStatus(final nsITransport p0, final long p1, final double p2, final double p3);
}
