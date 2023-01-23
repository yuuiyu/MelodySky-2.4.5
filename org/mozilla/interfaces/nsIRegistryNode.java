//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRegistryNode extends nsISupports
{
    public static final String NS_IREGISTRYNODE_IID = "{d1b54831-ac07-11d2-805e-00600811a9c3}";
    
    String getNameUTF8();
    
    String getName();
    
    long getKey();
}
