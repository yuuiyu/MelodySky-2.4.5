//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISchemaComplexType extends nsISchemaType
{
    public static final String NS_ISCHEMACOMPLEXTYPE_IID = "{3c14a028-6f4e-11d5-9b46-000064657374}";
    public static final int CONTENT_MODEL_EMPTY = 1;
    public static final int CONTENT_MODEL_SIMPLE = 2;
    public static final int CONTENT_MODEL_ELEMENT_ONLY = 3;
    public static final int CONTENT_MODEL_MIXED = 4;
    public static final int DERIVATION_EXTENSION_SIMPLE = 1;
    public static final int DERIVATION_RESTRICTION_SIMPLE = 2;
    public static final int DERIVATION_EXTENSION_COMPLEX = 3;
    public static final int DERIVATION_RESTRICTION_COMPLEX = 4;
    public static final int DERIVATION_SELF_CONTAINED = 5;
    
    int getContentModel();
    
    int getDerivation();
    
    nsISchemaType getBaseType();
    
    nsISchemaSimpleType getSimpleBaseType();
    
    nsISchemaModelGroup getModelGroup();
    
    long getAttributeCount();
    
    nsISchemaAttributeComponent getAttributeByIndex(final long p0);
    
    nsISchemaAttributeComponent getAttributeByName(final String p0);
    
    boolean getAbstract();
    
    boolean getIsArray();
    
    nsISchemaType getArrayType();
    
    long getArrayDimension();
}
