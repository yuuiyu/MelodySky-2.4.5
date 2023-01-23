//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIOutputStream extends nsISupports
{
    public static final String NS_IOUTPUTSTREAM_IID = "{0d0acd2a-61b4-11d4-9877-00c04fa0cf4a}";
    
    void close();
    
    void flush();
    
    long write(final String p0, final long p1);
    
    long writeFrom(final nsIInputStream p0, final long p1);
    
    boolean isNonBlocking();
}
