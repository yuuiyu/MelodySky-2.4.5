//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFormHistory2 extends nsISupports
{
    public static final String NS_IFORMHISTORY2_IID = "{a61f0a62-ae0a-4382-b474-d259442ca80c}";
    
    boolean getHasEntries();
    
    void addEntry(final String p0, final String p1);
    
    void removeEntry(final String p0, final String p1);
    
    void removeEntriesForName(final String p0);
    
    void removeAllEntries();
    
    boolean nameExists(final String p0);
    
    boolean entryExists(final String p0, final String p1);
}
