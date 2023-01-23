//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISchemaSimpleType extends nsISchemaType
{
    public static final String NS_ISCHEMASIMPLETYPE_IID = "{3c14a023-6f4e-11d5-9b46-000064657374}";
    public static final int SIMPLE_TYPE_BUILTIN = 1;
    public static final int SIMPLE_TYPE_LIST = 2;
    public static final int SIMPLE_TYPE_UNION = 3;
    public static final int SIMPLE_TYPE_RESTRICTION = 4;
    
    int getSimpleType();
}
