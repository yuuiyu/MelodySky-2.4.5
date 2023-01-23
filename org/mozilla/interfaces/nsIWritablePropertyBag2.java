//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWritablePropertyBag2 extends nsIPropertyBag2
{
    public static final String NS_IWRITABLEPROPERTYBAG2_IID = "{ee42c54a-19d3-472b-8bc3-76318d5ab5f4}";
    
    void setPropertyAsInt32(final String p0, final int p1);
    
    void setPropertyAsUint32(final String p0, final long p1);
    
    void setPropertyAsInt64(final String p0, final long p1);
    
    void setPropertyAsUint64(final String p0, final double p1);
    
    void setPropertyAsDouble(final String p0, final double p1);
    
    void setPropertyAsAString(final String p0, final String p1);
    
    void setPropertyAsACString(final String p0, final String p1);
    
    void setPropertyAsAUTF8String(final String p0, final String p1);
    
    void setPropertyAsBool(final String p0, final boolean p1);
    
    void setPropertyAsInterface(final String p0, final nsISupports p1);
}
