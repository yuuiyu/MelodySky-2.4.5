//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIStreamLoader extends nsISupports
{
    public static final String NS_ISTREAMLOADER_IID = "{31d37360-8e5a-11d3-93ad-00104ba0fd40}";
    
    void init(final nsIChannel p0, final nsIStreamLoaderObserver p1, final nsISupports p2);
    
    long getNumBytesRead();
    
    nsIRequest getRequest();
}
