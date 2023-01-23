//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIZipReaderCache extends nsISupports
{
    public static final String NS_IZIPREADERCACHE_IID = "{52c45d86-0cc3-11d4-986e-00c04fa0cf4a}";
    
    void init(final long p0);
    
    nsIZipReader getZip(final nsIFile p0);
}
