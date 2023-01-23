//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWebServiceProxyCreationListener extends nsISupports
{
    public static final String NS_IWEBSERVICEPROXYCREATIONLISTENER_IID = "{a711250b-47da-4f16-a1fd-593de16375a1}";
    
    void onLoad(final nsIWebServiceProxy p0);
    
    void onError(final nsIException p0);
}
