//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXULWindow extends nsISupports
{
    public static final String NS_IXULWINDOW_IID = "{b6c2f9e1-53a0-45f2-a2b8-fe37861fe8a8}";
    public static final long lowestZ = 0L;
    public static final long loweredZ = 4L;
    public static final long normalZ = 5L;
    public static final long raisedZ = 6L;
    public static final long highestZ = 9L;
    
    nsIDocShell getDocShell();
    
    boolean getIntrinsicallySized();
    
    void setIntrinsicallySized(final boolean p0);
    
    nsIDocShellTreeItem getPrimaryContentShell();
    
    nsIDocShellTreeItem getContentShellById(final String p0);
    
    void addChildWindow(final nsIXULWindow p0);
    
    void removeChildWindow(final nsIXULWindow p0);
    
    void center(final nsIXULWindow p0, final boolean p1, final boolean p2);
    
    void showModal();
    
    long getZLevel();
    
    void setZLevel(final long p0);
    
    long getContextFlags();
    
    void setContextFlags(final long p0);
    
    long getChromeFlags();
    
    void setChromeFlags(final long p0);
    
    nsIXULWindow createNewWindow(final int p0, final nsIAppShell p1);
    
    nsIXULBrowserWindow getXULBrowserWindow();
    
    void setXULBrowserWindow(final nsIXULBrowserWindow p0);
}
