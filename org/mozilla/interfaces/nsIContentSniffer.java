//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIContentSniffer extends nsISupports
{
    public static final String NS_ICONTENTSNIFFER_IID = "{a5710331-74ec-45fb-aa85-ed3bc7c36924}";
    
    String getMIMETypeFromContent(final byte[] p0, final long p1);
}
