//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISHistory extends nsISupports
{
    public static final String NS_ISHISTORY_IID = "{7294fe9b-14d8-11d5-9882-00c04fa02f40}";
    
    int getCount();
    
    int getIndex();
    
    int getMaxLength();
    
    void setMaxLength(final int p0);
    
    nsIHistoryEntry getEntryAtIndex(final int p0, final boolean p1);
    
    void purgeHistory(final int p0);
    
    void addSHistoryListener(final nsISHistoryListener p0);
    
    void removeSHistoryListener(final nsISHistoryListener p0);
    
    nsISimpleEnumerator getSHistoryEnumerator();
}
