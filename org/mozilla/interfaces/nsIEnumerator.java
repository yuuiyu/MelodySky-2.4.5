//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIEnumerator extends nsISupports
{
    public static final String NS_IENUMERATOR_IID = "{ad385286-cbc4-11d2-8cca-0060b0fc14a3}";
    
    void first();
    
    void next();
    
    nsISupports currentItem();
    
    void isDone();
}
