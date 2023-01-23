//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWebServiceProxyFactory extends nsISupports
{
    public static final String NS_IWEBSERVICEPROXYFACTORY_IID = "{693611be-bb38-40e0-a98e-b46ff8a5bcca}";
    
    nsIWebServiceProxy createProxy(final String p0, final String p1, final String p2, final boolean p3);
    
    void createProxyAsync(final String p0, final String p1, final String p2, final boolean p3, final nsIWebServiceProxyCreationListener p4);
}
