//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIUserInfo extends nsISupports
{
    public static final String NS_IUSERINFO_IID = "{6c1034f0-1dd2-11b2-aa14-e6657ed7bb0b}";
    
    String getFullname();
    
    String getEmailAddress();
    
    String getUsername();
    
    String getDomain();
}
