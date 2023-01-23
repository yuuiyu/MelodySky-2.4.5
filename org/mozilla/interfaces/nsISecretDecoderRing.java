//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISecretDecoderRing extends nsISupports
{
    public static final String NS_ISECRETDECODERRING_IID = "{0ec80360-075c-11d4-9fd4-00c04f1b83d8}";
    
    String encryptString(final String p0);
    
    String decryptString(final String p0);
    
    void changePassword();
    
    void logout();
    
    void logoutAndTeardown();
}
