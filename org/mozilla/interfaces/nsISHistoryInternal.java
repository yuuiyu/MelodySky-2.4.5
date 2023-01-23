//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISHistoryInternal extends nsISupports
{
    public static final String NS_ISHISTORYINTERNAL_IID = "{494fac3c-64f4-41b8-b209-b4ada899613b}";
    
    void addEntry(final nsISHEntry p0, final boolean p1);
    
    nsISHTransaction getRootTransaction();
    
    nsIDocShell getRootDocShell();
    
    void setRootDocShell(final nsIDocShell p0);
    
    void updateIndex();
    
    void replaceEntry(final int p0, final nsISHEntry p1);
    
    nsISHistoryListener getListener();
    
    void evictContentViewers(final int p0, final int p1);
    
    int getHistoryMaxTotalViewers();
}
