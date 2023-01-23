//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDocShellTreeItem extends nsISupports
{
    public static final String NS_IDOCSHELLTREEITEM_IID = "{7d935d63-6d2a-4600-afb5-9a4f7d68b825}";
    public static final int typeChrome = 0;
    public static final int typeContent = 1;
    public static final int typeContentWrapper = 2;
    public static final int typeChromeWrapper = 3;
    public static final int typeAll = Integer.MAX_VALUE;
    
    String getName();
    
    void setName(final String p0);
    
    boolean nameEquals(final String p0);
    
    int getItemType();
    
    void setItemType(final int p0);
    
    nsIDocShellTreeItem getParent();
    
    nsIDocShellTreeItem getSameTypeParent();
    
    nsIDocShellTreeItem getRootTreeItem();
    
    nsIDocShellTreeItem getSameTypeRootTreeItem();
    
    nsIDocShellTreeItem findItemWithName(final String p0, final nsISupports p1, final nsIDocShellTreeItem p2);
    
    nsIDocShellTreeOwner getTreeOwner();
    
    int getChildOffset();
    
    void setChildOffset(final int p0);
}
