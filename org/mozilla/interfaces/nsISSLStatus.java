//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISSLStatus extends nsISupports
{
    public static final String NS_ISSLSTATUS_IID = "{7b2ca1ca-1dd2-11b2-87ec-d217dbe22b85}";
    
    nsIX509Cert getServerCert();
    
    String getCipherName();
    
    long getKeyLength();
    
    long getSecretKeyLength();
}
