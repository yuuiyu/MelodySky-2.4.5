//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICryptoHash extends nsISupports
{
    public static final String NS_ICRYPTOHASH_IID = "{1e5b7c43-4688-45ce-92e1-77ed931e3bbe}";
    public static final short MD2 = 1;
    public static final short MD5 = 2;
    public static final short SHA1 = 3;
    public static final short SHA256 = 4;
    public static final short SHA384 = 5;
    public static final short SHA512 = 6;
    
    void init(final long p0);
    
    void initWithString(final String p0);
    
    void update(final byte[] p0, final long p1);
    
    void updateFromStream(final nsIInputStream p0, final long p1);
    
    String finish(final boolean p0);
}
