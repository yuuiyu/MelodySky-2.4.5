//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISOAPHeaderBlock extends nsISOAPBlock
{
    public static final String NS_ISOAPHEADERBLOCK_IID = "{063d4a4e-1dd2-11b2-a365-cbaf1651f140}";
    
    String getActorURI();
    
    void setActorURI(final String p0);
    
    boolean getMustUnderstand();
    
    void setMustUnderstand(final boolean p0);
}
