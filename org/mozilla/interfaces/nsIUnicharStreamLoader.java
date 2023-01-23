//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIUnicharStreamLoader extends nsISupports
{
    public static final String NS_IUNICHARSTREAMLOADER_IID = "{8a3eca16-167e-443d-9485-7e84ed822e95}";
    public static final long DEFAULT_SEGMENT_SIZE = 4096L;
    
    void init(final nsIChannel p0, final nsIUnicharStreamLoaderObserver p1, final nsISupports p2, final long p3);
    
    nsIChannel getChannel();
    
    String getCharset();
}
