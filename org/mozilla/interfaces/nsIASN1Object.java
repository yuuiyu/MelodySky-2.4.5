//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIASN1Object extends nsISupports
{
    public static final String NS_IASN1OBJECT_IID = "{ba8bf582-1dd1-11b2-898c-f40246bc9a63}";
    public static final long ASN1_END_CONTENTS = 0L;
    public static final long ASN1_BOOLEAN = 1L;
    public static final long ASN1_INTEGER = 2L;
    public static final long ASN1_BIT_STRING = 3L;
    public static final long ASN1_OCTET_STRING = 4L;
    public static final long ASN1_NULL = 5L;
    public static final long ASN1_OBJECT_ID = 6L;
    public static final long ASN1_ENUMERATED = 10L;
    public static final long ASN1_UTF8_STRING = 12L;
    public static final long ASN1_SEQUENCE = 16L;
    public static final long ASN1_SET = 17L;
    public static final long ASN1_PRINTABLE_STRING = 19L;
    public static final long ASN1_T61_STRING = 20L;
    public static final long ASN1_IA5_STRING = 22L;
    public static final long ASN1_UTC_TIME = 23L;
    public static final long ASN1_GEN_TIME = 24L;
    public static final long ASN1_VISIBLE_STRING = 26L;
    public static final long ASN1_UNIVERSAL_STRING = 28L;
    public static final long ASN1_BMP_STRING = 30L;
    public static final long ASN1_HIGH_TAG_NUMBER = 31L;
    public static final long ASN1_CONTEXT_SPECIFIC = 32L;
    public static final long ASN1_APPLICATION = 33L;
    public static final long ASN1_PRIVATE = 34L;
    
    long getType();
    
    void setType(final long p0);
    
    long getTag();
    
    void setTag(final long p0);
    
    String getDisplayName();
    
    void setDisplayName(final String p0);
    
    String getDisplayValue();
    
    void setDisplayValue(final String p0);
}
