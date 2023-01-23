//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsINetworkLinkService extends nsISupports
{
    public static final String NS_INETWORKLINKSERVICE_IID = "{61618a52-ea91-4277-a4ab-ebe10d7b9a64}";
    
    boolean getIsLinkUp();
    
    boolean getLinkStatusKnown();
}
