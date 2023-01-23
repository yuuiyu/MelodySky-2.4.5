//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIStringEnumerator extends nsISupports
{
    public static final String NS_ISTRINGENUMERATOR_IID = "{50d3ef6c-9380-4f06-9fb2-95488f7d141c}";
    
    boolean hasMore();
    
    String getNext();
}
