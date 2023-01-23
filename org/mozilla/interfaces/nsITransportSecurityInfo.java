//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITransportSecurityInfo extends nsISupports
{
    public static final String NS_ITRANSPORTSECURITYINFO_IID = "{68e21b66-1dd2-11b2-aa67-e2b87175e792}";
    
    long getSecurityState();
    
    String getShortSecurityDescription();
}
