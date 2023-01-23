//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIInputStreamPump extends nsIRequest
{
    public static final String NS_IINPUTSTREAMPUMP_IID = "{400f5468-97e7-4d2b-9c65-a82aecc7ae82}";
    
    void init(final nsIInputStream p0, final long p1, final long p2, final long p3, final long p4, final boolean p5);
    
    void asyncRead(final nsIStreamListener p0, final nsISupports p1);
}
