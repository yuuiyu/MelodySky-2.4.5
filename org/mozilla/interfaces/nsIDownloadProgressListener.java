//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDownloadProgressListener extends nsISupports
{
    public static final String NS_IDOWNLOADPROGRESSLISTENER_IID = "{8b193f0a-cf0c-4b5f-b4e3-a388df6f07b2}";
    
    nsIDOMDocument getDocument();
    
    void setDocument(final nsIDOMDocument p0);
    
    void onStateChange(final nsIWebProgress p0, final nsIRequest p1, final long p2, final long p3, final nsIDownload p4);
    
    void onProgressChange(final nsIWebProgress p0, final nsIRequest p1, final long p2, final long p3, final long p4, final long p5, final nsIDownload p6);
    
    void onStatusChange(final nsIWebProgress p0, final nsIRequest p1, final long p2, final String p3, final nsIDownload p4);
    
    void onLocationChange(final nsIWebProgress p0, final nsIRequest p1, final nsIURI p2, final nsIDownload p3);
    
    void onSecurityChange(final nsIWebProgress p0, final nsIRequest p1, final long p2, final nsIDownload p3);
}
