//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMNameList extends nsISupports
{
    public static final String NS_IDOMNAMELIST_IID = "{faaf1b80-1ddd-11d9-8c46-000a95dc234c}";
    
    String getName(final long p0);
    
    String getNamespaceURI(final long p0);
    
    long getLength();
    
    boolean contains(final String p0);
    
    boolean containsNS(final String p0, final String p1);
}
