//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDocShellTreeOwner extends nsISupports
{
    public static final String NS_IDOCSHELLTREEOWNER_IID = "{9e508466-5ebb-4618-abfa-9ad47bed0b2e}";
    
    nsIDocShellTreeItem findItemWithName(final String p0, final nsIDocShellTreeItem p1, final nsIDocShellTreeItem p2);
    
    void contentShellAdded(final nsIDocShellTreeItem p0, final boolean p1, final String p2);
    
    nsIDocShellTreeItem getPrimaryContentShell();
    
    void sizeShellTo(final nsIDocShellTreeItem p0, final int p1, final int p2);
    
    void setPersistence(final boolean p0, final boolean p1, final boolean p2);
    
    void getPersistence(final boolean[] p0, final boolean[] p1, final boolean[] p2);
}
