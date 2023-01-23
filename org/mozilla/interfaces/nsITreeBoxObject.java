//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITreeBoxObject extends nsISupports
{
    public static final String NS_ITREEBOXOBJECT_IID = "{55f3b431-1aa8-4e23-ad3d-a9f5644bdaa6}";
    
    nsITreeColumns getColumns();
    
    nsITreeView getView();
    
    void setView(final nsITreeView p0);
    
    boolean getFocused();
    
    void setFocused(final boolean p0);
    
    nsIDOMElement getTreeBody();
    
    int getRowHeight();
    
    int getFirstVisibleRow();
    
    int getLastVisibleRow();
    
    int getPageLength();
    
    void ensureRowIsVisible(final int p0);
    
    void scrollToRow(final int p0);
    
    void scrollByLines(final int p0);
    
    void scrollByPages(final int p0);
    
    void invalidate();
    
    void invalidateColumn(final nsITreeColumn p0);
    
    void invalidateRow(final int p0);
    
    void invalidateCell(final int p0, final nsITreeColumn p1);
    
    void invalidateRange(final int p0, final int p1);
    
    int getRowAt(final int p0, final int p1);
    
    void getCellAt(final int p0, final int p1, final int[] p2, final nsITreeColumn[] p3, final String[] p4);
    
    void getCoordsForCellItem(final int p0, final nsITreeColumn p1, final String p2, final int[] p3, final int[] p4, final int[] p5, final int[] p6);
    
    boolean isCellCropped(final int p0, final nsITreeColumn p1);
    
    void rowCountChanged(final int p0, final int p1);
    
    void beginUpdateBatch();
    
    void endUpdateBatch();
    
    void clearStyleAndImageCaches();
}
