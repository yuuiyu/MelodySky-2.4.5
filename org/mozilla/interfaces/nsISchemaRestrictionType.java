//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISchemaRestrictionType extends nsISchemaSimpleType
{
    public static final String NS_ISCHEMARESTRICTIONTYPE_IID = "{3c14a027-6f4e-11d5-9b46-000064657374}";
    
    nsISchemaSimpleType getBaseType();
    
    long getFacetCount();
    
    nsISchemaFacet getFacet(final long p0);
}
