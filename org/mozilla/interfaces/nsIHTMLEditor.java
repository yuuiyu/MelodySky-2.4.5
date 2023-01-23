//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIHTMLEditor extends nsISupports
{
    public static final String NS_IHTMLEDITOR_IID = "{afc36593-5787-4420-93d9-b2c0ccbf0cad}";
    public static final short eLeft = 0;
    public static final short eCenter = 1;
    public static final short eRight = 2;
    public static final short eJustify = 3;
    
    void addDefaultProperty(final nsIAtom p0, final String p1, final String p2);
    
    void removeDefaultProperty(final nsIAtom p0, final String p1, final String p2);
    
    void removeAllDefaultProperties();
    
    void setCSSInlineProperty(final nsIAtom p0, final String p1, final String p2);
    
    void setInlineProperty(final nsIAtom p0, final String p1, final String p2);
    
    void getInlineProperty(final nsIAtom p0, final String p1, final String p2, final boolean[] p3, final boolean[] p4, final boolean[] p5);
    
    String getInlinePropertyWithAttrValue(final nsIAtom p0, final String p1, final String p2, final boolean[] p3, final boolean[] p4, final boolean[] p5);
    
    void removeAllInlineProperties();
    
    void removeInlineProperty(final nsIAtom p0, final String p1);
    
    void increaseFontSize();
    
    void decreaseFontSize();
    
    boolean canDrag(final nsIDOMEvent p0);
    
    void doDrag(final nsIDOMEvent p0);
    
    void insertFromDrop(final nsIDOMEvent p0);
    
    boolean nodeIsBlock(final nsIDOMNode p0);
    
    void insertHTML(final String p0);
    
    void pasteNoFormatting(final int p0);
    
    void rebuildDocumentFromSource(final String p0);
    
    void insertHTMLWithContext(final String p0, final String p1, final String p2, final String p3, final nsIDOMDocument p4, final nsIDOMNode p5, final int p6, final boolean p7);
    
    void insertElementAtSelection(final nsIDOMElement p0, final boolean p1);
    
    void setDocumentTitle(final String p0);
    
    void updateBaseURL();
    
    void selectElement(final nsIDOMElement p0);
    
    void setCaretAfterElement(final nsIDOMElement p0);
    
    void setParagraphFormat(final String p0);
    
    String getParagraphState(final boolean[] p0);
    
    String getFontFaceState(final boolean[] p0);
    
    String getFontColorState(final boolean[] p0);
    
    String getBackgroundColorState(final boolean[] p0);
    
    String getHighlightColorState(final boolean[] p0);
    
    void getListState(final boolean[] p0, final boolean[] p1, final boolean[] p2, final boolean[] p3);
    
    void getListItemState(final boolean[] p0, final boolean[] p1, final boolean[] p2, final boolean[] p3);
    
    void getAlignment(final boolean[] p0, final short[] p1);
    
    void getIndentState(final boolean[] p0, final boolean[] p1);
    
    void makeOrChangeList(final String p0, final boolean p1, final String p2);
    
    void removeList(final String p0);
    
    void indent(final String p0);
    
    void align(final String p0);
    
    nsIDOMElement getElementOrParentByTagName(final String p0, final nsIDOMNode p1);
    
    nsIDOMElement getSelectedElement(final String p0);
    
    String getHeadContentsAsHTML();
    
    void replaceHeadContentsWithHTML(final String p0);
    
    nsIDOMElement createElementWithDefaults(final String p0);
    
    void insertLinkAroundSelection(final nsIDOMElement p0);
    
    void setBackgroundColor(final String p0);
    
    void setBodyAttribute(final String p0, final String p1);
    
    void ignoreSpuriousDragEvent(final boolean p0);
    
    nsISupportsArray getLinkedObjects();
    
    boolean getIsCSSEnabled();
    
    void setIsCSSEnabled(final boolean p0);
    
    void addInsertionListener(final nsIContentFilter p0);
    
    void removeInsertionListener(final nsIContentFilter p0);
    
    nsIDOMElement createAnonymousElement(final String p0, final nsIDOMNode p1, final String p2, final boolean p3);
    
    nsIDOMElement getSelectionContainer();
    
    void checkSelectionStateForAnonymousButtons(final nsISelection p0);
    
    boolean isAnonymousElement(final nsIDOMElement p0);
    
    boolean getReturnInParagraphCreatesNewParagraph();
    
    void setReturnInParagraphCreatesNewParagraph(final boolean p0);
}
