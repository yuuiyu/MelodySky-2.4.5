//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISOAPTransport extends nsISupports
{
    public static final String NS_ISOAPTRANSPORT_IID = "{99ec6695-535f-11d4-9a58-000064657374}";
    
    void syncCall(final nsISOAPCall p0, final nsISOAPResponse p1);
    
    nsISOAPCallCompletion asyncCall(final nsISOAPCall p0, final nsISOAPResponseListener p1, final nsISOAPResponse p2);
    
    void addListener(final nsISOAPTransportListener p0, final boolean p1);
    
    void removeListener(final nsISOAPTransportListener p0, final boolean p1);
}
