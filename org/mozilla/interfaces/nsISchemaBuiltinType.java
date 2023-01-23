//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISchemaBuiltinType extends nsISchemaSimpleType
{
    public static final String NS_ISCHEMABUILTINTYPE_IID = "{3c14a024-6f4e-11d5-9b46-000064657374}";
    public static final int BUILTIN_TYPE_ANYTYPE = 1;
    public static final int BUILTIN_TYPE_STRING = 2;
    public static final int BUILTIN_TYPE_NORMALIZED_STRING = 3;
    public static final int BUILTIN_TYPE_TOKEN = 4;
    public static final int BUILTIN_TYPE_BYTE = 5;
    public static final int BUILTIN_TYPE_UNSIGNEDBYTE = 6;
    public static final int BUILTIN_TYPE_BASE64BINARY = 7;
    public static final int BUILTIN_TYPE_HEXBINARY = 8;
    public static final int BUILTIN_TYPE_INTEGER = 9;
    public static final int BUILTIN_TYPE_POSITIVEINTEGER = 10;
    public static final int BUILTIN_TYPE_NEGATIVEINTEGER = 11;
    public static final int BUILTIN_TYPE_NONNEGATIVEINTEGER = 12;
    public static final int BUILTIN_TYPE_NONPOSITIVEINTEGER = 13;
    public static final int BUILTIN_TYPE_INT = 14;
    public static final int BUILTIN_TYPE_UNSIGNEDINT = 15;
    public static final int BUILTIN_TYPE_LONG = 16;
    public static final int BUILTIN_TYPE_UNSIGNEDLONG = 17;
    public static final int BUILTIN_TYPE_SHORT = 18;
    public static final int BUILTIN_TYPE_UNSIGNEDSHORT = 19;
    public static final int BUILTIN_TYPE_DECIMAL = 20;
    public static final int BUILTIN_TYPE_FLOAT = 21;
    public static final int BUILTIN_TYPE_DOUBLE = 22;
    public static final int BUILTIN_TYPE_BOOLEAN = 23;
    public static final int BUILTIN_TYPE_TIME = 24;
    public static final int BUILTIN_TYPE_DATETIME = 25;
    public static final int BUILTIN_TYPE_DURATION = 26;
    public static final int BUILTIN_TYPE_DATE = 27;
    public static final int BUILTIN_TYPE_GMONTH = 28;
    public static final int BUILTIN_TYPE_GYEAR = 29;
    public static final int BUILTIN_TYPE_GYEARMONTH = 30;
    public static final int BUILTIN_TYPE_GDAY = 31;
    public static final int BUILTIN_TYPE_GMONTHDAY = 32;
    public static final int BUILTIN_TYPE_NAME = 33;
    public static final int BUILTIN_TYPE_QNAME = 34;
    public static final int BUILTIN_TYPE_NCNAME = 35;
    public static final int BUILTIN_TYPE_ANYURI = 36;
    public static final int BUILTIN_TYPE_LANGUAGE = 37;
    public static final int BUILTIN_TYPE_ID = 38;
    public static final int BUILTIN_TYPE_IDREF = 39;
    public static final int BUILTIN_TYPE_IDREFS = 40;
    public static final int BUILTIN_TYPE_ENTITY = 41;
    public static final int BUILTIN_TYPE_ENTITIES = 42;
    public static final int BUILTIN_TYPE_NOTATION = 43;
    public static final int BUILTIN_TYPE_NMTOKEN = 44;
    public static final int BUILTIN_TYPE_NMTOKENS = 45;
    
    int getBuiltinType();
}
