//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISchemaAttribute extends nsISchemaAttributeComponent
{
    public static final String NS_ISCHEMAATTRIBUTE_IID = "{3c14a02e-6f4e-11d5-9b46-000064657374}";
    public static final int USE_OPTIONAL = 1;
    public static final int USE_PROHIBITED = 2;
    public static final int USE_REQUIRED = 3;
    
    nsISchemaSimpleType getType();
    
    String getDefaultValue();
    
    String getFixedValue();
    
    int getUse();
}
