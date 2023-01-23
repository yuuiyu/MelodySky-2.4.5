//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMPopupBlockedEvent extends nsIDOMEvent
{
    public static final String NS_IDOMPOPUPBLOCKEDEVENT_IID = "{9e201104-78e9-4cb3-aff5-7f0a9cf446c0}";
    
    nsIURI getRequestingWindowURI();
    
    nsIURI getPopupWindowURI();
    
    String getPopupWindowFeatures();
    
    void initPopupBlockedEvent(final String p0, final boolean p1, final boolean p2, final nsIURI p3, final nsIURI p4, final String p5);
}
