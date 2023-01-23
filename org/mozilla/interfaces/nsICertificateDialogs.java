//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICertificateDialogs extends nsISupports
{
    public static final String NS_ICERTIFICATEDIALOGS_IID = "{a03ca940-09be-11d5-ac5d-000064657374}";
    
    boolean confirmDownloadCACert(final nsIInterfaceRequestor p0, final nsIX509Cert p1, final long[] p2);
    
    void notifyCACertExists(final nsIInterfaceRequestor p0);
    
    boolean setPKCS12FilePassword(final nsIInterfaceRequestor p0, final String[] p1);
    
    boolean getPKCS12FilePassword(final nsIInterfaceRequestor p0, final String[] p1);
    
    void viewCert(final nsIInterfaceRequestor p0, final nsIX509Cert p1);
    
    void crlImportStatusDialog(final nsIInterfaceRequestor p0, final nsICRLInfo p1);
}
