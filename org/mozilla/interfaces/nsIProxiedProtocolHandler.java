//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIProxiedProtocolHandler extends nsIProtocolHandler
{
    public static final String NS_IPROXIEDPROTOCOLHANDLER_IID = "{0a24fed4-1dd2-11b2-a75c-9f8b9a8f9ba7}";
    
    nsIChannel newProxiedChannel(final nsIURI p0, final nsIProxyInfo p1);
}
