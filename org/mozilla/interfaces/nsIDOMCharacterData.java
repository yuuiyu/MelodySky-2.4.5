//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMCharacterData extends nsIDOMNode
{
    public static final String NS_IDOMCHARACTERDATA_IID = "{a6cf9072-15b3-11d2-932e-00805f8add32}";
    
    String getData();
    
    void setData(final String p0);
    
    long getLength();
    
    String substringData(final long p0, final long p1);
    
    void appendData(final String p0);
    
    void insertData(final long p0, final String p1);
    
    void deleteData(final long p0, final long p1);
    
    void replaceData(final long p0, final long p1, final String p2);
}
