//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITreeView extends nsISupports
{
    public static final String NS_ITREEVIEW_IID = "{22f034b7-a879-43ad-baee-ba6fd4d466ce}";
    public static final short DROP_BEFORE = -1;
    public static final short DROP_ON = 0;
    public static final short DROP_AFTER = 1;
    public static final short PROGRESS_NORMAL = 1;
    public static final short PROGRESS_UNDETERMINED = 2;
    public static final short PROGRESS_NONE = 3;
    
    int getRowCount();
    
    nsITreeSelection getSelection();
    
    void setSelection(final nsITreeSelection p0);
    
    void getRowProperties(final int p0, final nsISupportsArray p1);
    
    void getCellProperties(final int p0, final nsITreeColumn p1, final nsISupportsArray p2);
    
    void getColumnProperties(final nsITreeColumn p0, final nsISupportsArray p1);
    
    boolean isContainer(final int p0);
    
    boolean isContainerOpen(final int p0);
    
    boolean isContainerEmpty(final int p0);
    
    boolean isSeparator(final int p0);
    
    boolean isSorted();
    
    boolean canDrop(final int p0, final int p1);
    
    void drop(final int p0, final int p1);
    
    int getParentIndex(final int p0);
    
    boolean hasNextSibling(final int p0, final int p1);
    
    int getLevel(final int p0);
    
    String getImageSrc(final int p0, final nsITreeColumn p1);
    
    int getProgressMode(final int p0, final nsITreeColumn p1);
    
    String getCellValue(final int p0, final nsITreeColumn p1);
    
    String getCellText(final int p0, final nsITreeColumn p1);
    
    void setTree(final nsITreeBoxObject p0);
    
    void toggleOpenState(final int p0);
    
    void cycleHeader(final nsITreeColumn p0);
    
    void selectionChanged();
    
    void cycleCell(final int p0, final nsITreeColumn p1);
    
    boolean isEditable(final int p0, final nsITreeColumn p1);
    
    void setCellValue(final int p0, final nsITreeColumn p1, final String p2);
    
    void setCellText(final int p0, final nsITreeColumn p1, final String p2);
    
    void performAction(final String p0);
    
    void performActionOnRow(final String p0, final int p1);
    
    void performActionOnCell(final String p0, final int p1, final nsITreeColumn p2);
}
