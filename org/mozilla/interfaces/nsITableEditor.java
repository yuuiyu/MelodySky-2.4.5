//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITableEditor extends nsISupports
{
    public static final String NS_ITABLEEDITOR_IID = "{4805e684-49b9-11d3-9ce4-ed60bd6cb5bc}";
    public static final short eNoSearch = 0;
    public static final short ePreviousColumn = 1;
    public static final short ePreviousRow = 2;
    
    void insertTableCell(final int p0, final boolean p1);
    
    void insertTableColumn(final int p0, final boolean p1);
    
    void insertTableRow(final int p0, final boolean p1);
    
    void deleteTable();
    
    void deleteTableCellContents();
    
    void deleteTableCell(final int p0);
    
    void deleteTableColumn(final int p0);
    
    void deleteTableRow(final int p0);
    
    void selectTableCell();
    
    void selectBlockOfCells(final nsIDOMElement p0, final nsIDOMElement p1);
    
    void selectTableRow();
    
    void selectTableColumn();
    
    void selectTable();
    
    void selectAllTableCells();
    
    nsIDOMElement switchTableCellHeaderType(final nsIDOMElement p0);
    
    void joinTableCells(final boolean p0);
    
    void splitTableCell();
    
    void normalizeTable(final nsIDOMElement p0);
    
    void getCellIndexes(final nsIDOMElement p0, final int[] p1, final int[] p2);
    
    void getTableSize(final nsIDOMElement p0, final int[] p1, final int[] p2);
    
    nsIDOMElement getCellAt(final nsIDOMElement p0, final int p1, final int p2);
    
    void getCellDataAt(final nsIDOMElement p0, final int p1, final int p2, final nsIDOMElement[] p3, final int[] p4, final int[] p5, final int[] p6, final int[] p7, final int[] p8, final int[] p9, final boolean[] p10);
    
    nsIDOMNode getFirstRow(final nsIDOMElement p0);
    
    nsIDOMNode getNextRow(final nsIDOMNode p0);
    
    void setSelectionAfterTableEdit(final nsIDOMElement p0, final int p1, final int p2, final int p3, final boolean p4);
    
    nsIDOMElement getSelectedOrParentTableElement(final String[] p0, final int[] p1);
    
    long getSelectedCellsType(final nsIDOMElement p0);
    
    nsIDOMElement getFirstSelectedCell(final nsIDOMRange[] p0);
    
    nsIDOMElement getFirstSelectedCellInTable(final int[] p0, final int[] p1);
    
    nsIDOMElement getNextSelectedCell(final nsIDOMRange[] p0);
}
