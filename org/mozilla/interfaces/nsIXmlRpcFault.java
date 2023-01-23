//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXmlRpcFault extends nsISupports
{
    public static final String NS_IXMLRPCFAULT_IID = "{691cb864-0a7e-448c-98ee-4a7f359cf145}";
    
    int getFaultCode();
    
    String getFaultString();
    
    void init(final int p0, final String p1);
    
    String toString();
}
