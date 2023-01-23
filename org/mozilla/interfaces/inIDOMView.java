//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface inIDOMView extends nsISupports
{
    public static final String INIDOMVIEW_IID = "{3eb4c760-dffd-4983-94a8-18bcb99100e4}";
    
    nsIDOMNode getRootNode();
    
    void setRootNode(final nsIDOMNode p0);
    
    boolean getShowAnonymousContent();
    
    void setShowAnonymousContent(final boolean p0);
    
    boolean getShowSubDocuments();
    
    void setShowSubDocuments(final boolean p0);
    
    boolean getShowWhitespaceNodes();
    
    void setShowWhitespaceNodes(final boolean p0);
    
    long getWhatToShow();
    
    void setWhatToShow(final long p0);
    
    nsIDOMNode getNodeFromRowIndex(final int p0);
    
    int getRowIndexFromNode(final nsIDOMNode p0);
    
    void rebuild();
}
