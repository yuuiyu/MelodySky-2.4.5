//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFeedProcessor extends nsIStreamListener
{
    public static final String NS_IFEEDPROCESSOR_IID = "{8a0b2908-21b0-45d7-b14d-30df0f92afc7}";
    
    nsIFeedResultListener getListener();
    
    void setListener(final nsIFeedResultListener p0);
    
    void parseFromStream(final nsIInputStream p0, final nsIURI p1);
    
    void parseFromString(final String p0, final nsIURI p1);
    
    void parseAsync(final nsIRequestObserver p0, final nsIURI p1);
}
