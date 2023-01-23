//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISocketTransportService extends nsISupports
{
    public static final String NS_ISOCKETTRANSPORTSERVICE_IID = "{7b19ac06-a5fb-11d9-9f82-0011246ecd24}";
    
    nsISocketTransport createTransport(final String[] p0, final long p1, final String p2, final int p3, final nsIProxyInfo p4);
}
