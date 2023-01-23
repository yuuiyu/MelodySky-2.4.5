//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISchemaUnionType extends nsISchemaSimpleType
{
    public static final String NS_ISCHEMAUNIONTYPE_IID = "{3c14a026-6f4e-11d5-9b46-000064657374}";
    
    long getUnionTypeCount();
    
    nsISchemaSimpleType getUnionType(final long p0);
}
