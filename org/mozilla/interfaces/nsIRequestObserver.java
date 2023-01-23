//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRequestObserver extends nsISupports
{
    public static final String NS_IREQUESTOBSERVER_IID = "{fd91e2e0-1481-11d3-9333-00104ba0fd40}";
    
    void onStartRequest(final nsIRequest p0, final nsISupports p1);
    
    void onStopRequest(final nsIRequest p0, final nsISupports p1, final long p2);
}
