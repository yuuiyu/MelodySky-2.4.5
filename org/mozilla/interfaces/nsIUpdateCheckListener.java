//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIUpdateCheckListener extends nsISupports
{
    public static final String NS_IUPDATECHECKLISTENER_IID = "{8cbceb6e-8e27-46f2-8808-444c6499f836}";
    
    void onProgress(final nsIXMLHttpRequest p0, final long p1, final long p2);
    
    void onCheckComplete(final nsIXMLHttpRequest p0, final nsIUpdate[] p1, final long p2);
    
    void onError(final nsIXMLHttpRequest p0, final nsIUpdate p1);
}
