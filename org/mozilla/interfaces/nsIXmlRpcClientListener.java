//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXmlRpcClientListener extends nsISupports
{
    public static final String NS_IXMLRPCCLIENTLISTENER_IID = "{27e60cd8-6d63-4d87-b7d1-82c09e0c7363}";
    
    void onResult(final nsIXmlRpcClient p0, final nsISupports p1, final nsISupports p2);
    
    void onFault(final nsIXmlRpcClient p0, final nsISupports p1, final nsIXmlRpcFault p2);
    
    void onError(final nsIXmlRpcClient p0, final nsISupports p1, final long p2, final String p3);
}
