//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWSDLPart extends nsISupports
{
    public static final String NS_IWSDLPART_IID = "{0458dac4-65de-11d5-9b42-00104bdf5339}";
    
    String getName();
    
    nsIWSDLBinding getBinding();
    
    String getType();
    
    String getElementName();
    
    nsISchemaComponent getSchemaComponent();
}
