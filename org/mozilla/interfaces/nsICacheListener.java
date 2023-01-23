//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICacheListener extends nsISupports
{
    public static final String NS_ICACHELISTENER_IID = "{638c3848-778b-4851-8ff3-9400f65b8773}";
    
    void onCacheEntryAvailable(final nsICacheEntryDescriptor p0, final int p1, final long p2);
}
