//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIUnicharStreamListener extends nsIRequestObserver
{
    public static final String NS_IUNICHARSTREAMLISTENER_IID = "{4a7e9b62-fef8-400d-9865-d6820f630b4c}";
    
    void onUnicharDataAvailable(final nsIRequest p0, final nsISupports p1, final String p2);
}
