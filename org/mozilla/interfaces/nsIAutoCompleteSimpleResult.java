//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAutoCompleteSimpleResult extends nsIAutoCompleteResult
{
    public static final String NS_IAUTOCOMPLETESIMPLERESULT_IID = "{cc79f293-7114-4287-870b-d28aa61aa7df}";
    
    void setSearchString(final String p0);
    
    void setErrorDescription(final String p0);
    
    void setDefaultIndex(final int p0);
    
    void setSearchResult(final int p0);
    
    void appendMatch(final String p0, final String p1);
}
