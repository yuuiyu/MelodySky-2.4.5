//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIUpdateTimerManager extends nsISupports
{
    public static final String NS_IUPDATETIMERMANAGER_IID = "{0765c92c-6145-4253-9db4-594d8023087e}";
    
    void registerTimer(final String p0, final nsITimerCallback p1, final long p2);
}
