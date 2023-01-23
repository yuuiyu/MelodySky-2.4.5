//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsILoadGroup extends nsIRequest
{
    public static final String NS_ILOADGROUP_IID = "{3de0a31c-feaf-400f-9f1e-4ef71f8b20cc}";
    
    nsIRequestObserver getGroupObserver();
    
    void setGroupObserver(final nsIRequestObserver p0);
    
    nsIRequest getDefaultLoadRequest();
    
    void setDefaultLoadRequest(final nsIRequest p0);
    
    void addRequest(final nsIRequest p0, final nsISupports p1);
    
    void removeRequest(final nsIRequest p0, final nsISupports p1, final long p2);
    
    nsISimpleEnumerator getRequests();
    
    long getActiveCount();
    
    nsIInterfaceRequestor getNotificationCallbacks();
    
    void setNotificationCallbacks(final nsIInterfaceRequestor p0);
}
