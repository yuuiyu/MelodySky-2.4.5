//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIUnicharInputStream extends nsISupports
{
    public static final String NS_IUNICHARINPUTSTREAM_IID = "{d5e3bd80-6723-4b92-b0c9-22f6162fd94f}";
    
    long readString(final long p0, final String[] p1);
    
    void close();
}
