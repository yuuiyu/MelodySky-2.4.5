//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMDOMStringList extends nsISupports
{
    public static final String NS_IDOMDOMSTRINGLIST_IID = "{0bbae65c-1dde-11d9-8c46-000a95dc234c}";
    
    String item(final long p0);
    
    long getLength();
    
    boolean contains(final String p0);
}
