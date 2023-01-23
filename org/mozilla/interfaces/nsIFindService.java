//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFindService extends nsISupports
{
    public static final String NS_IFINDSERVICE_IID = "{5060b801-340e-11d5-be5b-b3e063ec6a3c}";
    
    String getSearchString();
    
    void setSearchString(final String p0);
    
    String getReplaceString();
    
    void setReplaceString(final String p0);
    
    boolean getFindBackwards();
    
    void setFindBackwards(final boolean p0);
    
    boolean getWrapFind();
    
    void setWrapFind(final boolean p0);
    
    boolean getEntireWord();
    
    void setEntireWord(final boolean p0);
    
    boolean getMatchCase();
    
    void setMatchCase(final boolean p0);
}
