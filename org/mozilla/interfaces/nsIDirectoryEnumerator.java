//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDirectoryEnumerator extends nsISupports
{
    public static final String NS_IDIRECTORYENUMERATOR_IID = "{31f7f4ae-6916-4f2d-a81e-926a4e3022ee}";
    
    nsIFile getNextFile();
    
    void close();
}
