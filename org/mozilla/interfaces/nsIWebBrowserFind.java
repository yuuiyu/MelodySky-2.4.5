//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWebBrowserFind extends nsISupports
{
    public static final String NS_IWEBBROWSERFIND_IID = "{2f977d44-5485-11d4-87e2-0010a4e75ef2}";
    
    boolean findNext();
    
    String getSearchString();
    
    void setSearchString(final String p0);
    
    boolean getFindBackwards();
    
    void setFindBackwards(final boolean p0);
    
    boolean getWrapFind();
    
    void setWrapFind(final boolean p0);
    
    boolean getEntireWord();
    
    void setEntireWord(final boolean p0);
    
    boolean getMatchCase();
    
    void setMatchCase(final boolean p0);
    
    boolean getSearchFrames();
    
    void setSearchFrames(final boolean p0);
}
