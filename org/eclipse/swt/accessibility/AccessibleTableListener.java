//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.*;

public interface AccessibleTableListener extends SWTEventListener
{
    void deselectColumn(final AccessibleTableEvent p0);
    
    void deselectRow(final AccessibleTableEvent p0);
    
    @Deprecated
    void getCaption(final AccessibleTableEvent p0);
    
    void getCell(final AccessibleTableEvent p0);
    
    void getColumn(final AccessibleTableEvent p0);
    
    void getColumnCount(final AccessibleTableEvent p0);
    
    void getColumnDescription(final AccessibleTableEvent p0);
    
    void getColumnHeader(final AccessibleTableEvent p0);
    
    void getColumnHeaderCells(final AccessibleTableEvent p0);
    
    void getColumns(final AccessibleTableEvent p0);
    
    void getRow(final AccessibleTableEvent p0);
    
    void getRowCount(final AccessibleTableEvent p0);
    
    void getRowDescription(final AccessibleTableEvent p0);
    
    void getRowHeader(final AccessibleTableEvent p0);
    
    void getRowHeaderCells(final AccessibleTableEvent p0);
    
    void getRows(final AccessibleTableEvent p0);
    
    void getSelectedCellCount(final AccessibleTableEvent p0);
    
    void getSelectedCells(final AccessibleTableEvent p0);
    
    void getSelectedColumnCount(final AccessibleTableEvent p0);
    
    void getSelectedColumns(final AccessibleTableEvent p0);
    
    void getSelectedRowCount(final AccessibleTableEvent p0);
    
    void getSelectedRows(final AccessibleTableEvent p0);
    
    @Deprecated
    void getSummary(final AccessibleTableEvent p0);
    
    void getVisibleColumns(final AccessibleTableEvent p0);
    
    void getVisibleRows(final AccessibleTableEvent p0);
    
    void isColumnSelected(final AccessibleTableEvent p0);
    
    void isRowSelected(final AccessibleTableEvent p0);
    
    void selectColumn(final AccessibleTableEvent p0);
    
    void selectRow(final AccessibleTableEvent p0);
    
    void setSelectedColumn(final AccessibleTableEvent p0);
    
    void setSelectedRow(final AccessibleTableEvent p0);
}
