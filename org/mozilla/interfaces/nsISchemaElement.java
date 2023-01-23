//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISchemaElement extends nsISchemaParticle
{
    public static final String NS_ISCHEMAELEMENT_IID = "{3c14a02c-6f4e-11d5-9b46-000064657374}";
    
    nsISchemaType getType();
    
    String getDefaultValue();
    
    String getFixedValue();
    
    boolean getNillable();
    
    boolean getAbstract();
}
