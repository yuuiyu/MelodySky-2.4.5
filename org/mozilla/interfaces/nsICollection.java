//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICollection extends nsISerializable
{
    public static final String NS_ICOLLECTION_IID = "{83b6019c-cbc4-11d2-8cca-0060b0fc14a3}";
    
    long count();
    
    nsISupports getElementAt(final long p0);
    
    nsISupports queryElementAt(final long p0, final String p1);
    
    void setElementAt(final long p0, final nsISupports p1);
    
    void appendElement(final nsISupports p0);
    
    void removeElement(final nsISupports p0);
    
    nsIEnumerator enumerate();
    
    void clear();
}
