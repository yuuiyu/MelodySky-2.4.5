//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIUTF8StringEnumerator extends nsISupports
{
    public static final String NS_IUTF8STRINGENUMERATOR_IID = "{9bdf1010-3695-4907-95ed-83d0410ec307}";
    
    boolean hasMore();
    
    String getNext();
}
