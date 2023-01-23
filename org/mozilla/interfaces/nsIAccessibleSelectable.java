//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAccessibleSelectable extends nsISupports
{
    public static final String NS_IACCESSIBLESELECTABLE_IID = "{34d268d6-1dd2-11b2-9d63-83a5e0ada290}";
    
    nsIArray getSelectedChildren();
    
    int getSelectionCount();
    
    void addChildToSelection(final int p0);
    
    void removeChildFromSelection(final int p0);
    
    void clearSelection();
    
    nsIAccessible refSelection(final int p0);
    
    boolean isChildSelected(final int p0);
    
    boolean selectAllSelection();
}
