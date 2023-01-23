//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITreeSelection extends nsISupports
{
    public static final String NS_ITREESELECTION_IID = "{f8a13364-184e-4da3-badf-5c04837537f8}";
    
    nsITreeBoxObject getTree();
    
    void setTree(final nsITreeBoxObject p0);
    
    boolean getSingle();
    
    int getCount();
    
    boolean isSelected(final int p0);
    
    void select(final int p0);
    
    void timedSelect(final int p0, final int p1);
    
    void toggleSelect(final int p0);
    
    void rangedSelect(final int p0, final int p1, final boolean p2);
    
    void clearRange(final int p0, final int p1);
    
    void clearSelection();
    
    void invertSelection();
    
    void selectAll();
    
    int getRangeCount();
    
    void getRangeAt(final int p0, final int[] p1, final int[] p2);
    
    void invalidateSelection();
    
    void adjustSelection(final int p0, final int p1);
    
    boolean getSelectEventsSuppressed();
    
    void setSelectEventsSuppressed(final boolean p0);
    
    int getCurrentIndex();
    
    void setCurrentIndex(final int p0);
    
    int getShiftSelectPivot();
}
