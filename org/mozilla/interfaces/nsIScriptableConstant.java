//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIScriptableConstant extends nsISupports
{
    public static final String NS_ISCRIPTABLECONSTANT_IID = "{0f6c5b09-88b0-43ca-b55c-578f24f3d810}";
    
    String getName();
    
    nsIScriptableDataType getType();
    
    nsIVariant getValue();
}
