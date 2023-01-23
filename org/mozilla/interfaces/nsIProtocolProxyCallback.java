//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIProtocolProxyCallback extends nsISupports
{
    public static final String NS_IPROTOCOLPROXYCALLBACK_IID = "{a9967200-f95e-45c2-beb3-9b060d874bfd}";
    
    void onProxyAvailable(final nsICancelable p0, final nsIURI p1, final nsIProxyInfo p2, final long p3);
}
