//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISHTransaction extends nsISupports
{
    public static final String NS_ISHTRANSACTION_IID = "{2edf705f-d252-4971-9f09-71dd0f760dc6}";
    
    nsISHEntry getSHEntry();
    
    void setSHEntry(final nsISHEntry p0);
    
    nsISHTransaction getPrev();
    
    void setPrev(final nsISHTransaction p0);
    
    nsISHTransaction getNext();
    
    void setNext(final nsISHTransaction p0);
    
    boolean getPersist();
    
    void setPersist(final boolean p0);
    
    void create(final nsISHEntry p0, final nsISHTransaction p1);
}
