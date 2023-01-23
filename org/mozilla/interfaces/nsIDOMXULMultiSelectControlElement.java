//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMXULMultiSelectControlElement extends nsIDOMXULSelectControlElement
{
    public static final String NS_IDOMXULMULTISELECTCONTROLELEMENT_IID = "{82c72eca-9886-473e-94cd-9de5694b3f88}";
    
    String getSelType();
    
    void setSelType(final String p0);
    
    nsIDOMXULSelectControlItemElement getCurrentItem();
    
    void setCurrentItem(final nsIDOMXULSelectControlItemElement p0);
    
    int getCurrentIndex();
    
    void setCurrentIndex(final int p0);
    
    nsIDOMNodeList getSelectedItems();
    
    void addItemToSelection(final nsIDOMXULSelectControlItemElement p0);
    
    void removeItemFromSelection(final nsIDOMXULSelectControlItemElement p0);
    
    void toggleItemSelection(final nsIDOMXULSelectControlItemElement p0);
    
    void selectItem(final nsIDOMXULSelectControlItemElement p0);
    
    void selectItemRange(final nsIDOMXULSelectControlItemElement p0, final nsIDOMXULSelectControlItemElement p1);
    
    void selectAll();
    
    void invertSelection();
    
    void clearSelection();
    
    int getSelectedCount();
    
    nsIDOMXULSelectControlItemElement getSelectedItem(final int p0);
}
