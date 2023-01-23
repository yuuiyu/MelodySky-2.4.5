//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDirectoryIterator extends nsISupports
{
    public static final String NS_IDIRECTORYITERATOR_IID = "{d8c0a083-0868-11d3-915f-d9d889d48e3c}";
    
    void init(final nsIFileSpec p0, final boolean p1);
    
    boolean exists();
    
    void next();
    
    nsIFileSpec getCurrentSpec();
}
