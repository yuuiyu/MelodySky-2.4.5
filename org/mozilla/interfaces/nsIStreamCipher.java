//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIStreamCipher extends nsISupports
{
    public static final String NS_ISTREAMCIPHER_IID = "{1d507cd6-1630-4710-af1b-4012dbcc514c}";
    
    void init(final nsIKeyObject p0);
    
    void initWithIV(final nsIKeyObject p0, final byte[] p1, final long p2);
    
    void update(final byte[] p0, final long p1);
    
    void updateFromStream(final nsIInputStream p0, final int p1);
    
    void updateFromString(final String p0);
    
    String finish(final boolean p0);
    
    void discard(final int p0);
}
