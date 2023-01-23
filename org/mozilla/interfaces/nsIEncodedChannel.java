//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIEncodedChannel extends nsISupports
{
    public static final String NS_IENCODEDCHANNEL_IID = "{30d7ec3a-f376-4652-9276-3092ec57abb6}";
    
    nsIUTF8StringEnumerator getContentEncodings();
    
    boolean getApplyConversion();
    
    void setApplyConversion(final boolean p0);
}
