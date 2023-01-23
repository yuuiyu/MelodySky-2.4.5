//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIStreamListener extends nsIRequestObserver
{
    public static final String NS_ISTREAMLISTENER_IID = "{1a637020-1482-11d3-9333-00104ba0fd40}";
    
    void onDataAvailable(final nsIRequest p0, final nsISupports p1, final nsIInputStream p2, final long p3, final long p4);
}
