//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMCrypto extends nsISupports
{
    public static final String NS_IDOMCRYPTO_IID = "{d2b675a5-f05b-4172-bac2-24cc39ffd398}";
    
    String getVersion();
    
    boolean getEnableSmartCardEvents();
    
    void setEnableSmartCardEvents(final boolean p0);
    
    nsIDOMCRMFObject generateCRMFRequest();
    
    String importUserCertificates(final String p0, final String p1, final boolean p2);
    
    String popChallengeResponse(final String p0);
    
    String random(final int p0);
    
    String signText(final String p0, final String p1);
    
    void alert(final String p0);
    
    void logout();
    
    void disableRightClick();
}
