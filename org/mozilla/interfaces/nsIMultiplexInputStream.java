//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIMultiplexInputStream extends nsIInputStream
{
    public static final String NS_IMULTIPLEXINPUTSTREAM_IID = "{a076fd12-1dd1-11b2-b19a-d53b5dffaade}";
    
    long getCount();
    
    void appendStream(final nsIInputStream p0);
    
    void insertStream(final nsIInputStream p0, final long p1);
    
    void removeStream(final long p0);
    
    nsIInputStream getStream(final long p0);
}
