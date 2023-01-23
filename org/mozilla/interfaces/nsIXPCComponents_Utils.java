//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXPCComponents_Utils extends nsISupports
{
    public static final String NS_IXPCCOMPONENTS_UTILS_IID = "{bcd54a63-34d9-468c-9a55-0fb5d2d8c677}";
    
    void reportError();
    
    void lookupMethod();
    
    nsIXPCComponents_utils_Sandbox getSandbox();
    
    void evalInSandbox(final String p0);
}
