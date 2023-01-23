//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIEditor extends nsISupports
{
    public static final String NS_IEDITOR_IID = "{d4882ffb-e927-408b-96be-d4391b456fa9}";
    public static final short eNone = 0;
    public static final short eNext = 1;
    public static final short ePrevious = 2;
    public static final short eNextWord = 3;
    public static final short ePreviousWord = 4;
    public static final short eToBeginningOfLine = 5;
    public static final short eToEndOfLine = 6;
    
    nsISelection getSelection();
    
    void setAttributeOrEquivalent(final nsIDOMElement p0, final String p1, final String p2, final boolean p3);
    
    void removeAttributeOrEquivalent(final nsIDOMElement p0, final String p1, final boolean p2);
    
    void postCreate();
    
    void preDestroy();
    
    long getFlags();
    
    void setFlags(final long p0);
    
    String getContentsMIMEType();
    
    void setContentsMIMEType(final String p0);
    
    boolean getIsDocumentEditable();
    
    nsIDOMDocument getDocument();
    
    nsIDOMElement getRootElement();
    
    nsISelectionController getSelectionController();
    
    void deleteSelection(final short p0);
    
    boolean getDocumentIsEmpty();
    
    boolean getDocumentModified();
    
    String getDocumentCharacterSet();
    
    void setDocumentCharacterSet(final String p0);
    
    void resetModificationCount();
    
    int getModificationCount();
    
    void incrementModificationCount(final int p0);
    
    nsITransactionManager getTransactionManager();
    
    void doTransaction(final nsITransaction p0);
    
    void enableUndo(final boolean p0);
    
    void undo(final long p0);
    
    void canUndo(final boolean[] p0, final boolean[] p1);
    
    void redo(final long p0);
    
    void canRedo(final boolean[] p0, final boolean[] p1);
    
    void beginTransaction();
    
    void endTransaction();
    
    void beginPlaceHolderTransaction(final nsIAtom p0);
    
    void endPlaceHolderTransaction();
    
    boolean shouldTxnSetSelection();
    
    void setShouldTxnSetSelection(final boolean p0);
    
    nsIInlineSpellChecker getInlineSpellChecker();
    
    void cut();
    
    boolean canCut();
    
    void copy();
    
    boolean canCopy();
    
    void paste(final int p0);
    
    boolean canPaste(final int p0);
    
    void selectAll();
    
    void beginningOfDocument();
    
    void endOfDocument();
    
    boolean canDrag(final nsIDOMEvent p0);
    
    void doDrag(final nsIDOMEvent p0);
    
    void insertFromDrop(final nsIDOMEvent p0);
    
    void setAttribute(final nsIDOMElement p0, final String p1, final String p2);
    
    boolean getAttributeValue(final nsIDOMElement p0, final String p1, final String[] p2);
    
    void removeAttribute(final nsIDOMElement p0, final String p1);
    
    void cloneAttribute(final String p0, final nsIDOMNode p1, final nsIDOMNode p2);
    
    void cloneAttributes(final nsIDOMNode p0, final nsIDOMNode p1);
    
    nsIDOMNode createNode(final String p0, final nsIDOMNode p1, final int p2);
    
    void insertNode(final nsIDOMNode p0, final nsIDOMNode p1, final int p2);
    
    void splitNode(final nsIDOMNode p0, final int p1, final nsIDOMNode[] p2);
    
    void joinNodes(final nsIDOMNode p0, final nsIDOMNode p1, final nsIDOMNode p2);
    
    void deleteNode(final nsIDOMNode p0);
    
    void markNodeDirty(final nsIDOMNode p0);
    
    void switchTextDirection();
    
    String outputToString(final String p0, final long p1);
    
    void outputToStream(final nsIOutputStream p0, final String p1, final String p2, final long p3);
    
    void addEditorObserver(final nsIEditorObserver p0);
    
    void removeEditorObserver(final nsIEditorObserver p0);
    
    void addEditActionListener(final nsIEditActionListener p0);
    
    void removeEditActionListener(final nsIEditActionListener p0);
    
    void addDocumentStateListener(final nsIDocumentStateListener p0);
    
    void removeDocumentStateListener(final nsIDocumentStateListener p0);
    
    void dumpContentTree();
    
    void debugDumpContent();
    
    void debugUnitTests(final int[] p0, final int[] p1);
}
