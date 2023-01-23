//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIProtocolProxyFilter extends nsISupports
{
    public static final String NS_IPROTOCOLPROXYFILTER_IID = "{f424abd3-32b4-456c-9f45-b7e3376cb0d1}";
    
    nsIProxyInfo applyFilter(final nsIProtocolProxyService p0, final nsIURI p1, final nsIProxyInfo p2);
}
