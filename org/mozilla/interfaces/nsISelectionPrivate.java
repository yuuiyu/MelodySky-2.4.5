//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISelectionPrivate extends nsISupports
{
    public static final String NS_ISELECTIONPRIVATE_IID = "{3225ca54-d7e1-4ff5-8ee9-091b0bfcda1f}";
    public static final short ENDOFPRECEDINGLINE = 0;
    public static final short STARTOFNEXTLINE = 1;
    public static final int TABLESELECTION_NONE = 0;
    public static final int TABLESELECTION_CELL = 1;
    public static final int TABLESELECTION_ROW = 2;
    public static final int TABLESELECTION_COLUMN = 3;
    public static final int TABLESELECTION_TABLE = 4;
    public static final int TABLESELECTION_ALLCELLS = 5;
    
    boolean getInterlinePosition();
    
    void setInterlinePosition(final boolean p0);
    
    void startBatchChanges();
    
    void endBatchChanges();
    
    nsIEnumerator getEnumerator();
    
    String toStringWithFormat(final String p0, final long p1, final int p2);
    
    void addSelectionListener(final nsISelectionListener p0);
    
    void removeSelectionListener(final nsISelectionListener p0);
    
    int getTableSelectionType(final nsIDOMRange p0);
}
