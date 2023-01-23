//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMDOMException extends nsISupports
{
    public static final String NS_IDOMDOMEXCEPTION_IID = "{a6cf910a-15b3-11d2-932e-00805f8add32}";
    public static final int INDEX_SIZE_ERR = 1;
    public static final int DOMSTRING_SIZE_ERR = 2;
    public static final int HIERARCHY_REQUEST_ERR = 3;
    public static final int WRONG_DOCUMENT_ERR = 4;
    public static final int INVALID_CHARACTER_ERR = 5;
    public static final int NO_DATA_ALLOWED_ERR = 6;
    public static final int NO_MODIFICATION_ALLOWED_ERR = 7;
    public static final int NOT_FOUND_ERR = 8;
    public static final int NOT_SUPPORTED_ERR = 9;
    public static final int INUSE_ATTRIBUTE_ERR = 10;
    public static final int INVALID_STATE_ERR = 11;
    public static final int SYNTAX_ERR = 12;
    public static final int INVALID_MODIFICATION_ERR = 13;
    public static final int NAMESPACE_ERR = 14;
    public static final int INVALID_ACCESS_ERR = 15;
    public static final int VALIDATION_ERR = 16;
    public static final int TYPE_MISMATCH_ERR = 17;
    
    long getCode();
}
