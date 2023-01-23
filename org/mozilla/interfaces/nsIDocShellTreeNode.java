//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDocShellTreeNode extends nsISupports
{
    public static final String NS_IDOCSHELLTREENODE_IID = "{37f1ab73-f224-44b1-82f0-d2834ab1cec0}";
    
    int getChildCount();
    
    void addChild(final nsIDocShellTreeItem p0);
    
    void removeChild(final nsIDocShellTreeItem p0);
    
    nsIDocShellTreeItem getChildAt(final int p0);
    
    nsIDocShellTreeItem findChildWithName(final String p0, final boolean p1, final boolean p2, final nsIDocShellTreeItem p3, final nsIDocShellTreeItem p4);
}
