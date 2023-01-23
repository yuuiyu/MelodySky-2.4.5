//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIScriptLoaderObserver extends nsISupports
{
    public static final String NS_ISCRIPTLOADEROBSERVER_IID = "{501209d3-7edf-437d-9948-3c6d1c08ef7f}";
    
    void scriptAvailable(final long p0, final nsISupports p1, final boolean p2, final boolean p3, final nsIURI p4, final int p5, final String p6);
    
    void scriptEvaluated(final long p0, final nsISupports p1, final boolean p2, final boolean p3);
}
