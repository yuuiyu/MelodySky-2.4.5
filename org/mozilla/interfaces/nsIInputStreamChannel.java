//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIInputStreamChannel extends nsIChannel
{
    public static final String NS_IINPUTSTREAMCHANNEL_IID = "{560a64ce-6d66-44db-b38e-864469c52d03}";
    
    void setURI(final nsIURI p0);
    
    nsIInputStream getContentStream();
    
    void setContentStream(final nsIInputStream p0);
}
