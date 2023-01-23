//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMRange extends nsISupports
{
    public static final String NS_IDOMRANGE_IID = "{a6cf90ce-15b3-11d2-932e-00805f8add32}";
    public static final int START_TO_START = 0;
    public static final int START_TO_END = 1;
    public static final int END_TO_END = 2;
    public static final int END_TO_START = 3;
    
    nsIDOMNode getStartContainer();
    
    int getStartOffset();
    
    nsIDOMNode getEndContainer();
    
    int getEndOffset();
    
    boolean getCollapsed();
    
    nsIDOMNode getCommonAncestorContainer();
    
    void setStart(final nsIDOMNode p0, final int p1);
    
    void setEnd(final nsIDOMNode p0, final int p1);
    
    void setStartBefore(final nsIDOMNode p0);
    
    void setStartAfter(final nsIDOMNode p0);
    
    void setEndBefore(final nsIDOMNode p0);
    
    void setEndAfter(final nsIDOMNode p0);
    
    void collapse(final boolean p0);
    
    void selectNode(final nsIDOMNode p0);
    
    void selectNodeContents(final nsIDOMNode p0);
    
    short compareBoundaryPoints(final int p0, final nsIDOMRange p1);
    
    void deleteContents();
    
    nsIDOMDocumentFragment extractContents();
    
    nsIDOMDocumentFragment cloneContents();
    
    void insertNode(final nsIDOMNode p0);
    
    void surroundContents(final nsIDOMNode p0);
    
    nsIDOMRange cloneRange();
    
    String toString();
    
    void detach();
}
