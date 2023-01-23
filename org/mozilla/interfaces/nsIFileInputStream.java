//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFileInputStream extends nsIInputStream
{
    public static final String NS_IFILEINPUTSTREAM_IID = "{e3d56a20-c7ec-11d3-8cda-0060b0fc14a3}";
    public static final int DELETE_ON_CLOSE = 2;
    public static final int CLOSE_ON_EOF = 4;
    public static final int REOPEN_ON_REWIND = 8;
    
    void init(final nsIFile p0, final int p1, final int p2, final int p3);
}
