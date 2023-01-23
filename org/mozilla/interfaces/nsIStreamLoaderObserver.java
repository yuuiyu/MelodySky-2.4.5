//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIStreamLoaderObserver extends nsISupports
{
    public static final String NS_ISTREAMLOADEROBSERVER_IID = "{359f7990-d4e9-11d3-a1a5-0050041caf44}";
    
    void onStreamComplete(final nsIStreamLoader p0, final nsISupports p1, final long p2, final long p3, final byte[] p4);
}
