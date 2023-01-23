//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAutoCompleteInput extends nsISupports
{
    public static final String NS_IAUTOCOMPLETEINPUT_IID = "{e312267f-8f57-43e8-a904-ff9b5d3f5aef}";
    
    nsIAutoCompletePopup getPopup();
    
    nsIAutoCompleteController getController();
    
    boolean getPopupOpen();
    
    void setPopupOpen(final boolean p0);
    
    boolean getDisableAutoComplete();
    
    void setDisableAutoComplete(final boolean p0);
    
    boolean getCompleteDefaultIndex();
    
    void setCompleteDefaultIndex(final boolean p0);
    
    boolean getCompleteSelectedIndex();
    
    void setCompleteSelectedIndex(final boolean p0);
    
    boolean getForceComplete();
    
    void setForceComplete(final boolean p0);
    
    long getMinResultsForPopup();
    
    void setMinResultsForPopup(final long p0);
    
    long getMaxRows();
    
    void setMaxRows(final long p0);
    
    long getShowCommentColumn();
    
    void setShowCommentColumn(final long p0);
    
    long getTimeout();
    
    void setTimeout(final long p0);
    
    String getSearchParam();
    
    void setSearchParam(final String p0);
    
    long getSearchCount();
    
    String getSearchAt(final long p0);
    
    String getTextValue();
    
    void setTextValue(final String p0);
    
    int getSelectionStart();
    
    int getSelectionEnd();
    
    void selectTextRange(final int p0, final int p1);
    
    void onSearchComplete();
    
    boolean onTextEntered();
    
    boolean onTextReverted();
}
