//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISchemaAttributeComponent extends nsISchemaComponent
{
    public static final String NS_ISCHEMAATTRIBUTECOMPONENT_IID = "{3c14a02d-6f4e-11d5-9b46-000064657374}";
    public static final int COMPONENT_TYPE_ATTRIBUTE = 1;
    public static final int COMPONENT_TYPE_GROUP = 2;
    public static final int COMPONENT_TYPE_ANY = 3;
    
    String getName();
    
    int getComponentType();
}
