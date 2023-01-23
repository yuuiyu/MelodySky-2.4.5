//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRDFResource extends nsIRDFNode
{
    public static final String NS_IRDFRESOURCE_IID = "{fb9686a7-719a-49dc-9107-10dea5739341}";
    
    String getValue();
    
    String getValueUTF8();
    
    void init(final String p0);
    
    boolean equalsString(final String p0);
    
    nsISupports getDelegate(final String p0, final String p1);
    
    void releaseDelegate(final String p0);
}
