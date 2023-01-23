//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICMSSecureMessage extends nsISupports
{
    public static final String NS_ICMSSECUREMESSAGE_IID = "{14b4394a-1dd2-11b2-b4fd-ba4a194fe97e}";
    
    String getCertByPrefID(final String p0);
    
    nsIX509Cert decodeCert(final String p0);
    
    String sendMessage(final String p0, final String p1);
    
    String receiveMessage(final String p0);
}
