//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPassword extends nsISupports
{
    public static final String NS_IPASSWORD_IID = "{cf39c2b0-1e4b-11d5-a549-0010a401eb10}";
    
    String getHost();
    
    String getUser();
    
    String getPassword();
}
