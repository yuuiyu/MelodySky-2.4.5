//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIScriptableInputStream extends nsISupports
{
    public static final String NS_ISCRIPTABLEINPUTSTREAM_IID = "{a2a32f90-9b90-11d3-a189-0050041caf44}";
    
    void close();
    
    void init(final nsIInputStream p0);
    
    long available();
    
    String read(final long p0);
}
