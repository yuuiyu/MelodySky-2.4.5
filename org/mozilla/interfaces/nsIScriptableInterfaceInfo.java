//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIScriptableInterfaceInfo extends nsISupports
{
    public static final String NS_ISCRIPTABLEINTERFACEINFO_IID = "{f902d5ba-2ef6-444e-8a17-52cb70715c10}";
    
    void init(final String p0);
    
    void initWithName(final String p0);
    
    String getName();
    
    String getInterfaceID();
    
    boolean getIsValid();
    
    boolean getIsScriptable();
    
    nsIScriptableInterfaceInfo getParent();
    
    int getMethodCount();
    
    int getConstantCount();
    
    nsIScriptableMethodInfo getMethodInfo(final int p0);
    
    nsIScriptableMethodInfo getMethodInfoForName(final String p0, final int[] p1);
    
    nsIScriptableConstant getConstant(final int p0);
    
    nsIScriptableInterfaceInfo getInfoForParam(final int p0, final nsIScriptableParamInfo p1);
    
    String getIIDForParam(final int p0, final nsIScriptableParamInfo p1);
    
    nsIScriptableDataType getTypeForParam(final int p0, final nsIScriptableParamInfo p1, final int p2);
    
    short getSizeIsArgNumberForParam(final int p0, final nsIScriptableParamInfo p1, final int p2);
    
    short getLengthIsArgNumberForParam(final int p0, final nsIScriptableParamInfo p1, final int p2);
    
    short getInterfaceIsArgNumberForParam(final int p0, final nsIScriptableParamInfo p1);
    
    boolean isIID(final String p0);
    
    boolean getIsFunction();
    
    boolean hasAncestor(final String p0);
}
