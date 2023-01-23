//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIMemory extends nsISupports
{
    public static final String NS_IMEMORY_IID = "{59e7e77a-38e4-11d4-8cf5-0060b0fc14a3}";
    
    void heapMinimize(final boolean p0);
    
    boolean isLowMemory();
}
