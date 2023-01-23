//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIKeyObjectFactory extends nsISupports
{
    public static final String NS_IKEYOBJECTFACTORY_IID = "{264eb54d-e20d-49a0-890c-1a5986ea81c4}";
    
    nsIKeyObject lookupKeyByName(final String p0);
    
    nsIKeyObject unwrapKey(final short p0, final byte[] p1, final long p2);
    
    nsIKeyObject keyFromString(final short p0, final String p1);
}
