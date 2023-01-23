//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWritableVariant extends nsIVariant
{
    public static final String NS_IWRITABLEVARIANT_IID = "{5586a590-8c82-11d5-90f3-0010a4e73d9a}";
    
    boolean getWritable();
    
    void setWritable(final boolean p0);
    
    void setAsInt8(final short p0);
    
    void setAsInt16(final short p0);
    
    void setAsInt32(final int p0);
    
    void setAsInt64(final long p0);
    
    void setAsUint8(final short p0);
    
    void setAsUint16(final int p0);
    
    void setAsUint32(final long p0);
    
    void setAsUint64(final double p0);
    
    void setAsFloat(final float p0);
    
    void setAsDouble(final double p0);
    
    void setAsBool(final boolean p0);
    
    void setAsChar(final char p0);
    
    void setAsWChar(final char p0);
    
    void setAsID(final String p0);
    
    void setAsAString(final String p0);
    
    void setAsDOMString(final String p0);
    
    void setAsACString(final String p0);
    
    void setAsAUTF8String(final String p0);
    
    void setAsString(final String p0);
    
    void setAsWString(final String p0);
    
    void setAsISupports(final nsISupports p0);
    
    void setAsInterface(final String p0, final nsISupports p1);
    
    void setAsStringWithSize(final long p0, final String p1);
    
    void setAsWStringWithSize(final long p0, final String p1);
    
    void setAsVoid();
    
    void setAsEmpty();
    
    void setAsEmptyArray();
    
    void setFromVariant(final nsIVariant p0);
}
