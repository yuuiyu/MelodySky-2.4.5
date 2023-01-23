//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIInputStreamTee extends nsIInputStream
{
    public static final String NS_IINPUTSTREAMTEE_IID = "{44e8b2c8-1ecb-4a63-8b23-3e3500c34f32}";
    
    nsIInputStream getSource();
    
    void setSource(final nsIInputStream p0);
    
    nsIOutputStream getSink();
    
    void setSink(final nsIOutputStream p0);
}
