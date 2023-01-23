//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIProxyObjectManager extends nsISupports
{
    public static final String NS_IPROXYOBJECTMANAGER_IID = "{eea90d43-b059-11d2-915e-c12b696c9333}";
    public static final int INVOKE_SYNC = 1;
    public static final int INVOKE_ASYNC = 2;
    public static final int FORCE_PROXY_CREATION = 4;
    
    nsISupports getProxyForObject(final nsIEventQueue p0, final String p1, final nsISupports p2, final int p3);
    
    nsISupports getProxy(final nsIEventQueue p0, final String p1, final nsISupports p2, final String p3, final int p4);
}
