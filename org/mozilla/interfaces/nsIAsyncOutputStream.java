//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAsyncOutputStream extends nsIOutputStream
{
    public static final String NS_IASYNCOUTPUTSTREAM_IID = "{10dc9c94-8aff-49c6-8af9-d7fdb7339dae}";
    public static final long WAIT_CLOSURE_ONLY = 1L;
    
    void closeWithStatus(final long p0);
    
    void asyncWait(final nsIOutputStreamCallback p0, final long p1, final long p2, final nsIEventTarget p3);
}
