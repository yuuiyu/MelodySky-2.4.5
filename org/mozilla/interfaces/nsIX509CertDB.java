//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIX509CertDB extends nsISupports
{
    public static final String NS_IX509CERTDB_IID = "{da48b3c0-1284-11d5-ac67-000064657374}";
    public static final long UNTRUSTED = 0L;
    public static final long TRUSTED_SSL = 1L;
    public static final long TRUSTED_EMAIL = 2L;
    public static final long TRUSTED_OBJSIGN = 4L;
    
    nsIX509Cert findCertByNickname(final nsISupports p0, final String p1);
    
    nsIX509Cert findCertByDBKey(final String p0, final nsISupports p1);
    
    void findCertNicknames(final nsISupports p0, final long p1, final long[] p2, final String[][] p3);
    
    nsIX509Cert findEmailEncryptionCert(final String p0);
    
    nsIX509Cert findEmailSigningCert(final String p0);
    
    nsIX509Cert findCertByEmailAddress(final nsISupports p0, final String p1);
    
    void importCertificates(final byte[] p0, final long p1, final long p2, final nsIInterfaceRequestor p3);
    
    void importEmailCertificate(final byte[] p0, final long p1, final nsIInterfaceRequestor p2);
    
    void importServerCertificate(final byte[] p0, final long p1, final nsIInterfaceRequestor p2);
    
    void importUserCertificate(final byte[] p0, final long p1, final nsIInterfaceRequestor p2);
    
    void deleteCertificate(final nsIX509Cert p0);
    
    void setCertTrust(final nsIX509Cert p0, final long p1, final long p2);
    
    boolean isCertTrusted(final nsIX509Cert p0, final long p1, final long p2);
    
    void importCertsFromFile(final nsISupports p0, final nsILocalFile p1, final long p2);
    
    void importPKCS12File(final nsISupports p0, final nsILocalFile p1);
    
    void exportPKCS12File(final nsISupports p0, final nsILocalFile p1, final long p2, final nsIX509Cert[] p3);
    
    nsIArray getOCSPResponders();
    
    boolean getIsOcspOn();
    
    nsIX509Cert constructX509FromBase64(final String p0);
}
