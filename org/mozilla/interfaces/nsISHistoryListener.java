//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISHistoryListener extends nsISupports
{
    public static final String NS_ISHISTORYLISTENER_IID = "{3b07f591-e8e1-11d4-9882-00c04fa02f40}";
    
    void onHistoryNewEntry(final nsIURI p0);
    
    boolean onHistoryGoBack(final nsIURI p0);
    
    boolean onHistoryGoForward(final nsIURI p0);
    
    boolean onHistoryReload(final nsIURI p0, final long p1);
    
    boolean onHistoryGotoIndex(final int p0, final nsIURI p1);
    
    boolean onHistoryPurge(final int p0);
}
