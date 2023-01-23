//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIBadCertListener extends nsISupports
{
    public static final String NS_IBADCERTLISTENER_IID = "{86960956-edb0-11d4-998b-00b0d02354a0}";
    public static final short UNINIT_ADD_FLAG = -1;
    public static final short ADD_TRUSTED_FOR_SESSION = 1;
    public static final short ADD_TRUSTED_PERMANENTLY = 2;
    
    boolean confirmUnknownIssuer(final nsIInterfaceRequestor p0, final nsIX509Cert p1, final short[] p2);
    
    boolean confirmMismatchDomain(final nsIInterfaceRequestor p0, final String p1, final nsIX509Cert p2);
    
    boolean confirmCertExpired(final nsIInterfaceRequestor p0, final nsIX509Cert p1);
    
    void notifyCrlNextupdate(final nsIInterfaceRequestor p0, final String p1, final nsIX509Cert p2);
}
