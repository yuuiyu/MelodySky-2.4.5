//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIProfileUnlocker extends nsISupports
{
    public static final String NS_IPROFILEUNLOCKER_IID = "{08923af1-e7a3-4fae-ba02-128502193994}";
    public static final long ATTEMPT_QUIT = 0L;
    public static final long FORCE_QUIT = 1L;
    
    void unlock(final long p0);
}
