//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWebServiceProxy extends nsISupports
{
    public static final String NS_IWEBSERVICEPROXY_IID = "{2122421c-1326-41db-87f8-25519d8a12cb}";
    
    nsIWSDLPort getPort();
    
    boolean getIsAsync();
    
    String getQualifier();
    
    nsISimpleEnumerator getPendingCalls();
    
    String getPrimaryInterfaceName();
    
    String getPrimaryAsyncListenerInterfaceName();
    
    nsIScriptableInterfaces getInterfaces();
}
