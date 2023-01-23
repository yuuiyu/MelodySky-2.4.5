//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIX509Cert extends nsISupports
{
    public static final String NS_IX509CERT_IID = "{f0980f60-ee3d-11d4-998b-00b0d02354a0}";
    public static final long UNKNOWN_CERT = 0L;
    public static final long CA_CERT = 1L;
    public static final long USER_CERT = 2L;
    public static final long EMAIL_CERT = 4L;
    public static final long SERVER_CERT = 8L;
    public static final long VERIFIED_OK = 0L;
    public static final long NOT_VERIFIED_UNKNOWN = 1L;
    public static final long CERT_REVOKED = 2L;
    public static final long CERT_EXPIRED = 4L;
    public static final long CERT_NOT_TRUSTED = 8L;
    public static final long ISSUER_NOT_TRUSTED = 16L;
    public static final long ISSUER_UNKNOWN = 32L;
    public static final long INVALID_CA = 64L;
    public static final long USAGE_NOT_ALLOWED = 128L;
    public static final long CERT_USAGE_SSLClient = 0L;
    public static final long CERT_USAGE_SSLServer = 1L;
    public static final long CERT_USAGE_SSLServerWithStepUp = 2L;
    public static final long CERT_USAGE_SSLCA = 3L;
    public static final long CERT_USAGE_EmailSigner = 4L;
    public static final long CERT_USAGE_EmailRecipient = 5L;
    public static final long CERT_USAGE_ObjectSigner = 6L;
    public static final long CERT_USAGE_UserCertImport = 7L;
    public static final long CERT_USAGE_VerifyCA = 8L;
    public static final long CERT_USAGE_ProtectedObjectSigner = 9L;
    public static final long CERT_USAGE_StatusResponder = 10L;
    public static final long CERT_USAGE_AnyCA = 11L;
    
    String getNickname();
    
    String getEmailAddress();
    
    String[] getEmailAddresses(final long[] p0);
    
    boolean containsEmailAddress(final String p0);
    
    String getSubjectName();
    
    String getCommonName();
    
    String getOrganization();
    
    String getOrganizationalUnit();
    
    String getSha1Fingerprint();
    
    String getMd5Fingerprint();
    
    String getTokenName();
    
    String getIssuerName();
    
    String getSerialNumber();
    
    String getIssuerCommonName();
    
    String getIssuerOrganization();
    
    String getIssuerOrganizationUnit();
    
    nsIX509Cert getIssuer();
    
    nsIX509CertValidity getValidity();
    
    String getDbKey();
    
    String getWindowTitle();
    
    nsIArray getChain();
    
    void getUsagesArray(final boolean p0, final long[] p1, final long[] p2, final String[][] p3);
    
    void getUsagesString(final boolean p0, final long[] p1, final String[] p2);
    
    long verifyForUsage(final long p0);
    
    nsIASN1Object getASN1Structure();
    
    byte[] getRawDER(final long[] p0);
    
    boolean _equals(final nsIX509Cert p0);
}
