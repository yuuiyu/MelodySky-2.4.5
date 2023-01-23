//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWebProgress extends nsISupports
{
    public static final String NS_IWEBPROGRESS_IID = "{570f39d0-efd0-11d3-b093-00a024ffc08c}";
    public static final long NOTIFY_STATE_REQUEST = 1L;
    public static final long NOTIFY_STATE_DOCUMENT = 2L;
    public static final long NOTIFY_STATE_NETWORK = 4L;
    public static final long NOTIFY_STATE_WINDOW = 8L;
    public static final long NOTIFY_STATE_ALL = 15L;
    public static final long NOTIFY_PROGRESS = 16L;
    public static final long NOTIFY_STATUS = 32L;
    public static final long NOTIFY_SECURITY = 64L;
    public static final long NOTIFY_LOCATION = 128L;
    public static final long NOTIFY_ALL = 255L;
    
    void addProgressListener(final nsIWebProgressListener p0, final long p1);
    
    void removeProgressListener(final nsIWebProgressListener p0);
    
    nsIDOMWindow getDOMWindow();
    
    boolean getIsLoadingDocument();
}
