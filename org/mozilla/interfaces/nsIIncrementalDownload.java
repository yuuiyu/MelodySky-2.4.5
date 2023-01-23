//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIIncrementalDownload extends nsIRequest
{
    public static final String NS_IINCREMENTALDOWNLOAD_IID = "{6687823f-56c4-461d-93a1-7f6cb7dfbfba}";
    
    void init(final nsIURI p0, final nsIFile p1, final int p2, final int p3);
    
    nsIURI getURI();
    
    nsIURI getFinalURI();
    
    nsIFile getDestination();
    
    long getTotalSize();
    
    long getCurrentSize();
    
    void start(final nsIRequestObserver p0, final nsISupports p1);
}
