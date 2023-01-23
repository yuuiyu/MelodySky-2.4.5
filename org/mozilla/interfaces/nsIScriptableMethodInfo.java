//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIScriptableMethodInfo extends nsISupports
{
    public static final String NS_ISCRIPTABLEMETHODINFO_IID = "{9228afa2-187c-4feb-9228-5108e640ca33}";
    
    boolean getIsGetter();
    
    boolean getIsSetter();
    
    boolean getIsNotXPCOM();
    
    boolean getIsConstructor();
    
    boolean getIsHidden();
    
    String getName();
    
    short getParamCount();
    
    nsIScriptableParamInfo getParam(final short p0);
    
    nsIScriptableParamInfo getResult();
}
