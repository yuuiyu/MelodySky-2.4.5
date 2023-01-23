//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIMutableArray extends nsIArray
{
    public static final String NS_IMUTABLEARRAY_IID = "{af059da0-c85b-40ec-af07-ae4bfdc192cc}";
    
    void appendElement(final nsISupports p0, final boolean p1);
    
    void removeElementAt(final long p0);
    
    void insertElementAt(final nsISupports p0, final long p1, final boolean p2);
    
    void replaceElementAt(final nsISupports p0, final long p1, final boolean p2);
    
    void clear();
}
