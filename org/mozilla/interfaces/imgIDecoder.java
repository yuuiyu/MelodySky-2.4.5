//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface imgIDecoder extends nsISupports
{
    public static final String IMGIDECODER_IID = "{9eebf43a-1dd1-11b2-953e-f1782f4cbad3}";
    
    void init(final imgILoad p0);
    
    void close();
    
    void flush();
    
    long writeFrom(final nsIInputStream p0, final long p1);
}
