//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISchemaType extends nsISchemaComponent
{
    public static final String NS_ISCHEMATYPE_IID = "{3c14a022-6f4e-11d5-9b46-000064657374}";
    public static final int SCHEMA_TYPE_SIMPLE = 1;
    public static final int SCHEMA_TYPE_COMPLEX = 2;
    public static final int SCHEMA_TYPE_PLACEHOLDER = 3;
    
    String getName();
    
    int getSchemaType();
}
