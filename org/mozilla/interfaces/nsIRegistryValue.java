//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRegistryValue extends nsISupports
{
    public static final String NS_IREGISTRYVALUE_IID = "{5316c380-b2f8-11d2-a374-0080c6f80e4b}";
    
    String getName();
    
    String getNameUTF8();
    
    long getType();
    
    long getLength();
}
