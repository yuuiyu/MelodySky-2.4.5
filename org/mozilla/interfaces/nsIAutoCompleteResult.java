//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAutoCompleteResult extends nsISupports
{
    public static final String NS_IAUTOCOMPLETERESULT_IID = "{eb43e1dc-2060-4d8e-aebf-3efec4e21cf8}";
    public static final int RESULT_IGNORED = 1;
    public static final int RESULT_FAILURE = 2;
    public static final int RESULT_NOMATCH = 3;
    public static final int RESULT_SUCCESS = 4;
    
    String getSearchString();
    
    int getSearchResult();
    
    int getDefaultIndex();
    
    String getErrorDescription();
    
    long getMatchCount();
    
    String getValueAt(final int p0);
    
    String getCommentAt(final int p0);
    
    String getStyleAt(final int p0);
    
    void removeValueAt(final int p0, final boolean p1);
}
