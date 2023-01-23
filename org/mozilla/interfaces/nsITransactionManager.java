//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITransactionManager extends nsISupports
{
    public static final String NS_ITRANSACTIONMANAGER_IID = "{58e330c2-7b48-11d2-98b9-00805f297d89}";
    
    void doTransaction(final nsITransaction p0);
    
    void undoTransaction();
    
    void redoTransaction();
    
    void clear();
    
    void beginBatch();
    
    void endBatch();
    
    int getNumberOfUndoItems();
    
    int getNumberOfRedoItems();
    
    int getMaxTransactionCount();
    
    void setMaxTransactionCount(final int p0);
    
    nsITransaction peekUndoStack();
    
    nsITransaction peekRedoStack();
    
    nsITransactionList getUndoList();
    
    nsITransactionList getRedoList();
    
    void addListener(final nsITransactionListener p0);
    
    void removeListener(final nsITransactionListener p0);
}
