//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISecurityWarningDialogs extends nsISupports
{
    public static final String NS_ISECURITYWARNINGDIALOGS_IID = "{1c399d06-1dd2-11b2-bc58-c87cbcacdb78}";
    
    boolean confirmEnteringSecure(final nsIInterfaceRequestor p0);
    
    boolean confirmEnteringWeak(final nsIInterfaceRequestor p0);
    
    boolean confirmLeavingSecure(final nsIInterfaceRequestor p0);
    
    boolean confirmMixedMode(final nsIInterfaceRequestor p0);
    
    boolean confirmPostToInsecure(final nsIInterfaceRequestor p0);
    
    boolean confirmPostToInsecureFromSecure(final nsIInterfaceRequestor p0);
}
