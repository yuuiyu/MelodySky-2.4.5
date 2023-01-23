//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIStorageStream extends nsISupports
{
    public static final String NS_ISTORAGESTREAM_IID = "{604ad9d0-753e-11d3-90ca-34278643278f}";
    
    void init(final long p0, final long p1, final nsIMemory p2);
    
    nsIOutputStream getOutputStream(final int p0);
    
    nsIInputStream newInputStream(final int p0);
    
    long getLength();
    
    void setLength(final long p0);
    
    boolean getWriteInProgress();
}
