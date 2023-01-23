//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAsyncInputStream extends nsIInputStream
{
    public static final String NS_IASYNCINPUTSTREAM_IID = "{15a15329-00de-44e8-ab06-0d0b0d43dc5b}";
    public static final long WAIT_CLOSURE_ONLY = 1L;
    
    void closeWithStatus(final long p0);
    
    void asyncWait(final nsIInputStreamCallback p0, final long p1, final long p2, final nsIEventTarget p3);
}
