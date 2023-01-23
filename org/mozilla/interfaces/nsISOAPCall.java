//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISOAPCall extends nsISOAPMessage
{
    public static final String NS_ISOAPCALL_IID = "{a8fefe40-52bc-11d4-9a57-000064657374}";
    
    String getTransportURI();
    
    void setTransportURI(final String p0);
    
    boolean getVerifySourceHeader();
    
    void setVerifySourceHeader(final boolean p0);
    
    nsISOAPResponse invoke();
    
    nsISOAPCallCompletion asyncInvoke(final nsISOAPResponseListener p0);
}
