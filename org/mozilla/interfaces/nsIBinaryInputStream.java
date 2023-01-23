//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIBinaryInputStream extends nsIInputStream
{
    public static final String NS_IBINARYINPUTSTREAM_IID = "{7b456cb0-8772-11d3-90cf-0040056a906e}";
    
    void setInputStream(final nsIInputStream p0);
    
    boolean readBoolean();
    
    short read8();
    
    int read16();
    
    long read32();
    
    double read64();
    
    float readFloat();
    
    double readDouble();
    
    String readCString();
    
    String readString();
    
    String readBytes(final long p0);
    
    short[] readByteArray(final long p0);
}
