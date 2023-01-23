//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISelection extends nsISupports
{
    public static final String NS_ISELECTION_IID = "{b2c7ed59-8634-4352-9e37-5484c8b6e4e1}";
    
    nsIDOMNode getAnchorNode();
    
    int getAnchorOffset();
    
    nsIDOMNode getFocusNode();
    
    int getFocusOffset();
    
    boolean getIsCollapsed();
    
    int getRangeCount();
    
    nsIDOMRange getRangeAt(final int p0);
    
    void collapse(final nsIDOMNode p0, final int p1);
    
    void extend(final nsIDOMNode p0, final int p1);
    
    void collapseToStart();
    
    void collapseToEnd();
    
    boolean containsNode(final nsIDOMNode p0, final boolean p1);
    
    void selectAllChildren(final nsIDOMNode p0);
    
    void addRange(final nsIDOMRange p0);
    
    void removeRange(final nsIDOMRange p0);
    
    void removeAllRanges();
    
    void deleteFromDocument();
    
    void selectionLanguageChange(final boolean p0);
    
    String toString();
}
