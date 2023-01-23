//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISchemaComponent extends nsISupports
{
    public static final String NS_ISCHEMACOMPONENT_IID = "{5caaa64e-e191-11d8-842a-000393b6661a}";
    
    String getTargetNamespace();
    
    void resolve(final nsIWebServiceErrorHandler p0);
    
    void clear();
}
