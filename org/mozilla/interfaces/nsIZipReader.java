//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIZipReader extends nsISupports
{
    public static final String NS_IZIPREADER_IID = "{6ff6a966-9632-11d3-8cd9-0060b0fc14a3}";
    
    void init(final nsIFile p0);
    
    nsIFile getFile();
    
    void open();
    
    void close();
    
    void test(final String p0);
    
    void extract(final String p0, final nsIFile p1);
    
    nsIZipEntry getEntry(final String p0);
    
    nsISimpleEnumerator findEntries(final String p0);
    
    nsIInputStream getInputStream(final String p0);
}
