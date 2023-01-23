//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIListBoxObject extends nsISupports
{
    public static final String NS_ILISTBOXOBJECT_IID = "{fde7c970-0b4e-49f4-b1eb-974ae6c96336}";
    
    nsIListBoxObject getListboxBody();
    
    int getRowCount();
    
    int getNumberOfVisibleRows();
    
    int getIndexOfFirstVisibleRow();
    
    void ensureIndexIsVisible(final int p0);
    
    void scrollToIndex(final int p0);
    
    void scrollByLines(final int p0);
    
    nsIDOMElement getItemAtIndex(final int p0);
    
    int getIndexOfItem(final nsIDOMElement p0);
}
