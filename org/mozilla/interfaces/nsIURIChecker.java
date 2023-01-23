//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIURIChecker extends nsIRequest
{
    public static final String NS_IURICHECKER_IID = "{4660c1a1-be2d-4c78-9baf-c22984176c28}";
    
    void init(final nsIURI p0);
    
    nsIChannel getBaseChannel();
    
    void asyncCheck(final nsIRequestObserver p0, final nsISupports p1);
}
