//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISupportsArray extends nsICollection
{
    public static final String NS_ISUPPORTSARRAY_IID = "{791eafa0-b9e6-11d1-8031-006008159b5a}";
    
    int getIndexOf(final nsISupports p0);
    
    int getIndexOfStartingAt(final nsISupports p0, final long p1);
    
    int getLastIndexOf(final nsISupports p0);
    
    void deleteLastElement(final nsISupports p0);
    
    void deleteElementAt(final long p0);
    
    void compact();
    
    nsISupportsArray _clone();
}
