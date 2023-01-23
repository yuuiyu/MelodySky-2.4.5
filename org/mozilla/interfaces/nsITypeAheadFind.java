//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITypeAheadFind extends nsISupports
{
    public static final String NS_ITYPEAHEADFIND_IID = "{376da416-e6b3-4bac-98f3-6aa408742751}";
    public static final int FIND_FOUND = 0;
    public static final int FIND_NOTFOUND = 1;
    public static final int FIND_WRAPPED = 2;
    
    void init(final nsIDocShell p0);
    
    int find(final String p0, final boolean p1);
    
    int findNext();
    
    int findPrevious();
    
    void setDocShell(final nsIDocShell p0);
    
    String getSearchString();
    
    boolean getFocusLinks();
    
    void setFocusLinks(final boolean p0);
    
    boolean getCaseSensitive();
    
    void setCaseSensitive(final boolean p0);
    
    nsIDOMElement getFoundLink();
    
    nsIDOMWindow getCurrentWindow();
}
