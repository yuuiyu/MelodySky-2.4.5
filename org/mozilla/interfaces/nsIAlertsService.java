//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAlertsService extends nsISupports
{
    public static final String NS_IALERTSSERVICE_IID = "{647248fd-f925-4e30-93dd-cde26d7e3a90}";
    
    void showAlertNotification(final String p0, final String p1, final String p2, final boolean p3, final String p4, final nsIObserver p5);
}
