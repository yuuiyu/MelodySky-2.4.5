//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIBinaryOutputStream extends nsIOutputStream
{
    public static final String NS_IBINARYOUTPUTSTREAM_IID = "{204ee610-8765-11d3-90cf-0040056a906e}";
    
    void setOutputStream(final nsIOutputStream p0);
    
    void writeBoolean(final boolean p0);
    
    void write8(final short p0);
    
    void write16(final int p0);
    
    void write32(final long p0);
    
    void write64(final double p0);
    
    void writeFloat(final float p0);
    
    void writeDouble(final double p0);
    
    void writeStringZ(final String p0);
    
    void writeWStringZ(final String p0);
    
    void writeUtf8Z(final String p0);
    
    void writeBytes(final String p0, final long p1);
    
    void writeByteArray(final short[] p0, final long p1);
}
