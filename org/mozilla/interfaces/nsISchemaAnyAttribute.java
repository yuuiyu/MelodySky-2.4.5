//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISchemaAnyAttribute extends nsISchemaAttributeComponent
{
    public static final String NS_ISCHEMAANYATTRIBUTE_IID = "{3c14a030-6f4e-11d5-9b46-000064657374}";
    public static final int PROCESS_STRICT = 1;
    public static final int PROCESS_SKIP = 2;
    public static final int PROCESS_LAX = 3;
    
    int getProcess();
    
    String getNamespace();
}
