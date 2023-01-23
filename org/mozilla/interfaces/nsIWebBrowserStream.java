//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWebBrowserStream extends nsISupports
{
    public static final String NS_IWEBBROWSERSTREAM_IID = "{86d02f0e-219b-4cfc-9c88-bd98d2cce0b8}";
    
    void openStream(final nsIURI p0, final String p1);
    
    void appendToStream(final byte[] p0, final long p1);
    
    void closeStream();
}
