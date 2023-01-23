//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWebProgressListener extends nsISupports
{
    public static final String NS_IWEBPROGRESSLISTENER_IID = "{570f39d1-efd0-11d3-b093-00a024ffc08c}";
    public static final long STATE_START = 1L;
    public static final long STATE_REDIRECTING = 2L;
    public static final long STATE_TRANSFERRING = 4L;
    public static final long STATE_NEGOTIATING = 8L;
    public static final long STATE_STOP = 16L;
    public static final long STATE_IS_REQUEST = 65536L;
    public static final long STATE_IS_DOCUMENT = 131072L;
    public static final long STATE_IS_NETWORK = 262144L;
    public static final long STATE_IS_WINDOW = 524288L;
    public static final long STATE_RESTORING = 16777216L;
    public static final long STATE_IS_INSECURE = 4L;
    public static final long STATE_IS_BROKEN = 1L;
    public static final long STATE_IS_SECURE = 2L;
    public static final long STATE_SECURE_HIGH = 262144L;
    public static final long STATE_SECURE_MED = 65536L;
    public static final long STATE_SECURE_LOW = 131072L;
    
    void onStateChange(final nsIWebProgress p0, final nsIRequest p1, final long p2, final long p3);
    
    void onProgressChange(final nsIWebProgress p0, final nsIRequest p1, final int p2, final int p3, final int p4, final int p5);
    
    void onLocationChange(final nsIWebProgress p0, final nsIRequest p1, final nsIURI p2);
    
    void onStatusChange(final nsIWebProgress p0, final nsIRequest p1, final long p2, final String p3);
    
    void onSecurityChange(final nsIWebProgress p0, final nsIRequest p1, final long p2);
}
