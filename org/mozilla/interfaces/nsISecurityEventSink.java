//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISecurityEventSink extends nsISupports
{
    public static final String NS_ISECURITYEVENTSINK_IID = "{a71aee68-dd38-4736-bd79-035fea1a1ec6}";
    
    void onSecurityChange(final nsISupports p0, final long p1);
}
