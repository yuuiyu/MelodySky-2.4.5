//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIZipEntry extends nsISupports
{
    public static final String NS_IZIPENTRY_IID = "{6ca5e43e-9632-11d3-8cd9-0060b0fc14a3}";
    
    String getName();
    
    int getCompression();
    
    long getSize();
    
    long getRealSize();
    
    long getCRC32();
}
