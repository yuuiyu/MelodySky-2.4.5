//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIProgressEventSink extends nsISupports
{
    public static final String NS_IPROGRESSEVENTSINK_IID = "{d974c99e-4148-4df9-8d98-de834a2f6462}";
    
    void onProgress(final nsIRequest p0, final nsISupports p1, final double p2, final double p3);
    
    void onStatus(final nsIRequest p0, final nsISupports p1, final long p2, final String p3);
}
