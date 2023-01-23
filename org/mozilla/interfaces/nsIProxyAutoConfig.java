//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIProxyAutoConfig extends nsISupports
{
    public static final String NS_IPROXYAUTOCONFIG_IID = "{a42619df-0a1c-46fb-8154-0e9b8f8f1ea8}";
    
    void init(final String p0, final String p1);
    
    String getProxyForURI(final String p0, final String p1);
}
