//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIUploadChannel extends nsISupports
{
    public static final String NS_IUPLOADCHANNEL_IID = "{ddf633d8-e9a4-439d-ad88-de636fd9bb75}";
    
    void setUploadStream(final nsIInputStream p0, final String p1, final int p2);
    
    nsIInputStream getUploadStream();
}
