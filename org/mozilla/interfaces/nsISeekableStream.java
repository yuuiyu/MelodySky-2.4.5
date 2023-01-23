//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISeekableStream extends nsISupports
{
    public static final String NS_ISEEKABLESTREAM_IID = "{8429d350-1040-4661-8b71-f2a6ba455980}";
    public static final int NS_SEEK_SET = 0;
    public static final int NS_SEEK_CUR = 1;
    public static final int NS_SEEK_END = 2;
    
    void seek(final int p0, final long p1);
    
    long tell();
    
    void setEOF();
}
