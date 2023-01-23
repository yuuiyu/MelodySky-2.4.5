//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIScriptContextOwner extends nsISupports
{
    public static final String NS_ISCRIPTCONTEXTOWNER_IID = "{a94ec640-0bba-11d2-b326-00805f8a3859}";
    
    nsISupports getScriptContext();
    
    nsISupports getScriptGlobalObject();
    
    void releaseScriptContext(final nsISupports p0);
    
    void reportScriptError(final String p0, final String p1, final int p2, final String p3);
}
