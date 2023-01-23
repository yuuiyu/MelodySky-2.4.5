//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface inIFileSearch extends inISearchProcess
{
    public static final String INIFILESEARCH_IID = "{efa53257-526d-4350-9088-343a510346b8}";
    
    String getBasePath();
    
    void setBasePath(final String p0);
    
    boolean getReturnRelativePaths();
    
    void setReturnRelativePaths(final boolean p0);
    
    long getDirectoryDepth(final nsIFile p0);
    
    nsISupportsArray getSubDirectories(final nsIFile p0);
    
    String getFilenameCriteria();
    
    void setFilenameCriteria(final String p0);
    
    String getTextCriteria();
    
    void setTextCriteria(final String p0);
    
    nsIFile getSearchPath();
    
    void setSearchPath(final nsIFile p0);
    
    boolean getSearchRecursive();
    
    void setSearchRecursive(final boolean p0);
    
    long getDirectoriesSearched();
    
    nsIFile getCurrentDirectory();
    
    nsIFile getFileResultAt(final int p0);
}
