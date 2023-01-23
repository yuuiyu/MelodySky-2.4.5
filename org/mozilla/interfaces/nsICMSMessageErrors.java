//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICMSMessageErrors extends nsISupports
{
    public static final String NS_ICMSMESSAGEERRORS_IID = "{f2aec680-60a0-49f0-afe5-6cf1d3f15e0d}";
    public static final int SUCCESS = 0;
    public static final int GENERAL_ERROR = 1;
    public static final int VERIFY_NOT_SIGNED = 1024;
    public static final int VERIFY_NO_CONTENT_INFO = 1025;
    public static final int VERIFY_BAD_DIGEST = 1026;
    public static final int VERIFY_NOCERT = 1028;
    public static final int VERIFY_UNTRUSTED = 1029;
    public static final int VERIFY_ERROR_UNVERIFIED = 1031;
    public static final int VERIFY_ERROR_PROCESSING = 1032;
    public static final int VERIFY_BAD_SIGNATURE = 1033;
    public static final int VERIFY_DIGEST_MISMATCH = 1034;
    public static final int VERIFY_UNKNOWN_ALGO = 1035;
    public static final int VERIFY_UNSUPPORTED_ALGO = 1036;
    public static final int VERIFY_MALFORMED_SIGNATURE = 1037;
    public static final int VERIFY_HEADER_MISMATCH = 1038;
    public static final int VERIFY_NOT_YET_ATTEMPTED = 1039;
    public static final int VERIFY_CERT_WITHOUT_ADDRESS = 1040;
    public static final int ENCRYPT_NO_BULK_ALG = 1056;
}
