//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISchemaAttributeGroup extends nsISchemaAttributeComponent
{
    public static final String NS_ISCHEMAATTRIBUTEGROUP_IID = "{3c14a02f-6f4e-11d5-9b46-000064657374}";
    
    long getAttributeCount();
    
    nsISchemaAttributeComponent getAttributeByIndex(final long p0);
    
    nsISchemaAttributeComponent getAttributeByName(final String p0);
}
