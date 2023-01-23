//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIScriptableDataType extends nsISupports
{
    public static final String NS_ISCRIPTABLEDATATYPE_IID = "{312e3b94-dc98-4ccc-b2fb-e3406f905cc6}";
    
    boolean getIsPointer();
    
    boolean getIsUniquePointer();
    
    boolean getIsReference();
    
    boolean getIsArithmetic();
    
    boolean getIsInterfacePointer();
    
    boolean getIsArray();
    
    boolean getIsDependent();
    
    int getDataType();
}
