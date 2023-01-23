//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISearchContext extends nsISupports
{
    public static final String NS_ISEARCHCONTEXT_IID = "{31aba0f0-2d93-11d3-8069-00600811a9c3}";
    
    String getSearchString();
    
    void setSearchString(final String p0);
    
    String getReplaceString();
    
    void setReplaceString(final String p0);
    
    boolean getSearchBackwards();
    
    void setSearchBackwards(final boolean p0);
    
    boolean getCaseSensitive();
    
    void setCaseSensitive(final boolean p0);
    
    boolean getWrapSearch();
    
    void setWrapSearch(final boolean p0);
    
    nsIDOMWindowInternal getTargetWindow();
    
    nsIDOMWindowInternal getFindDialog();
    
    void setFindDialog(final nsIDOMWindowInternal p0);
    
    nsIDOMWindowInternal getReplaceDialog();
    
    void setReplaceDialog(final nsIDOMWindowInternal p0);
}
