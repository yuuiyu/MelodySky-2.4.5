//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIServerSocketListener extends nsISupports
{
    public static final String NS_ISERVERSOCKETLISTENER_IID = "{836d98ec-fee2-4bde-b609-abd5e966eabd}";
    
    void onSocketAccepted(final nsIServerSocket p0, final nsISocketTransport p1);
    
    void onStopListening(final nsIServerSocket p0, final long p1);
}
