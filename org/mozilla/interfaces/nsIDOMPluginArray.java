//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMPluginArray extends nsISupports
{
    public static final String NS_IDOMPLUGINARRAY_IID = "{f6134680-f28b-11d2-8360-c90899049c3c}";
    
    long getLength();
    
    nsIDOMPlugin item(final long p0);
    
    nsIDOMPlugin namedItem(final String p0);
}
