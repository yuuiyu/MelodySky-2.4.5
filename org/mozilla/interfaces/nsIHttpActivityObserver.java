//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIHttpActivityObserver extends nsISupports
{
    public static final String NS_IHTTPACTIVITYOBSERVER_IID = "{412880c8-6c36-48d8-bf8f-84f91f892503}";
    public static final long ACTIVITY_TYPE_SOCKET_TRANSPORT = 1L;
    public static final long ACTIVITY_TYPE_HTTP_TRANSACTION = 2L;
    public static final long ACTIVITY_SUBTYPE_REQUEST_HEADER = 20481L;
    public static final long ACTIVITY_SUBTYPE_REQUEST_BODY_SENT = 20482L;
    public static final long ACTIVITY_SUBTYPE_RESPONSE_START = 20483L;
    public static final long ACTIVITY_SUBTYPE_RESPONSE_HEADER = 20484L;
    public static final long ACTIVITY_SUBTYPE_RESPONSE_COMPLETE = 20485L;
    public static final long ACTIVITY_SUBTYPE_TRANSACTION_CLOSE = 20486L;
    
    void observeActivity(final nsISupports p0, final long p1, final long p2, final double p3, final double p4, final String p5);
    
    boolean getIsActive();
}
