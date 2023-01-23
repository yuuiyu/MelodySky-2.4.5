//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIScriptableParamInfo extends nsISupports
{
    public static final String NS_ISCRIPTABLEPARAMINFO_IID = "{2309482b-4631-455f-833f-5e4e9ce38589}";
    
    boolean getIsIn();
    
    boolean getIsOut();
    
    boolean getIsRetval();
    
    boolean getIsShared();
    
    boolean getIsDipper();
    
    nsIScriptableDataType getType();
}
