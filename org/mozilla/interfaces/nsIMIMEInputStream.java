//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIMIMEInputStream extends nsIInputStream
{
    public static final String NS_IMIMEINPUTSTREAM_IID = "{dcbce63c-1dd1-11b2-b94d-91f6d49a3161}";
    
    boolean getAddContentLength();
    
    void setAddContentLength(final boolean p0);
    
    void addHeader(final String p0, final String p1);
    
    void setData(final nsIInputStream p0);
}
