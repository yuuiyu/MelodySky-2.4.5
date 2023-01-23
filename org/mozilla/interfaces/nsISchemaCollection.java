//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISchemaCollection extends nsISupports
{
    public static final String NS_ISCHEMACOLLECTION_IID = "{427c5511-941b-48c0-9abc-8ec9ea5d964b}";
    
    nsISchema getSchema(final String p0);
    
    nsISchemaElement getElement(final String p0, final String p1);
    
    nsISchemaAttribute getAttribute(final String p0, final String p1);
    
    nsISchemaType getType(final String p0, final String p1);
}
