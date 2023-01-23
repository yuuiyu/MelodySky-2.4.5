//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISchema extends nsISchemaComponent
{
    public static final String NS_ISCHEMA_IID = "{3c14a021-6f4e-11d5-9b46-000064657374}";
    
    String getSchemaNamespace();
    
    long getTypeCount();
    
    nsISchemaType getTypeByIndex(final long p0);
    
    nsISchemaType getTypeByName(final String p0);
    
    long getAttributeCount();
    
    nsISchemaAttribute getAttributeByIndex(final long p0);
    
    nsISchemaAttribute getAttributeByName(final String p0);
    
    long getElementCount();
    
    nsISchemaElement getElementByIndex(final long p0);
    
    nsISchemaElement getElementByName(final String p0);
    
    long getAttributeGroupCount();
    
    nsISchemaAttributeGroup getAttributeGroupByIndex(final long p0);
    
    nsISchemaAttributeGroup getAttributeGroupByName(final String p0);
    
    long getModelGroupCount();
    
    nsISchemaModelGroup getModelGroupByIndex(final long p0);
    
    nsISchemaModelGroup getModelGroupByName(final String p0);
    
    nsISchemaCollection getCollection();
}
