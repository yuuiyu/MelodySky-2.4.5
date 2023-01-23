//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface jsdIScriptHook extends nsISupports
{
    public static final String JSDISCRIPTHOOK_IID = "{ae89a7e2-1dd1-11b2-8c2f-af82086291a5}";
    
    void onScriptCreated(final jsdIScript p0);
    
    void onScriptDestroyed(final jsdIScript p0);
}
