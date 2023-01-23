//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAsyncStreamCopier extends nsIRequest
{
    public static final String NS_IASYNCSTREAMCOPIER_IID = "{eaa49141-c21c-4fe8-a79b-77860a3910aa}";
    
    void init(final nsIInputStream p0, final nsIOutputStream p1, final nsIEventTarget p2, final boolean p3, final boolean p4, final long p5);
    
    void asyncCopy(final nsIRequestObserver p0, final nsISupports p1);
}
