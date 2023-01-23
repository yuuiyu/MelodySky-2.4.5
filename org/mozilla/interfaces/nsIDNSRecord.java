//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDNSRecord extends nsISupports
{
    public static final String NS_IDNSRECORD_IID = "{31c9c52e-1100-457d-abac-d2729e43f506}";
    
    String getCanonicalName();
    
    String getNextAddrAsString();
    
    boolean hasMore();
    
    void rewind();
}
