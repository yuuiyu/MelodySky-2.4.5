//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAutoCompleteController extends nsISupports
{
    public static final String NS_IAUTOCOMPLETECONTROLLER_IID = "{cf2aca0c-4fb1-42e4-8a54-23e832cb2a98}";
    public static final int STATUS_NONE = 1;
    public static final int STATUS_SEARCHING = 2;
    public static final int STATUS_COMPLETE_NO_MATCH = 3;
    public static final int STATUS_COMPLETE_MATCH = 4;
    public static final int KEY_UP = 1;
    public static final int KEY_DOWN = 2;
    public static final int KEY_LEFT = 3;
    public static final int KEY_RIGHT = 4;
    public static final int KEY_PAGE_UP = 5;
    public static final int KEY_PAGE_DOWN = 6;
    public static final int KEY_HOME = 7;
    public static final int KEY_END = 8;
    
    nsIAutoCompleteInput getInput();
    
    void setInput(final nsIAutoCompleteInput p0);
    
    int getSearchStatus();
    
    long getMatchCount();
    
    void startSearch(final String p0);
    
    void handleText(final boolean p0);
    
    boolean handleEnter();
    
    boolean handleEscape();
    
    void handleStartComposition();
    
    void handleEndComposition();
    
    void handleTab();
    
    boolean handleKeyNavigation(final int p0);
    
    boolean handleDelete();
    
    String getValueAt(final int p0);
    
    String getCommentAt(final int p0);
    
    String getStyleAt(final int p0);
    
    void setSearchString(final String p0);
}
