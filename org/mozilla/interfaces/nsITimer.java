//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITimer extends nsISupports
{
    public static final String NS_ITIMER_IID = "{436a83fa-b396-11d9-bcfa-00112478d626}";
    public static final short TYPE_ONE_SHOT = 0;
    public static final short TYPE_REPEATING_SLACK = 1;
    public static final short TYPE_REPEATING_PRECISE = 2;
    
    void init(final nsIObserver p0, final long p1, final long p2);
    
    void initWithCallback(final nsITimerCallback p0, final long p1, final long p2);
    
    void cancel();
    
    long getDelay();
    
    void setDelay(final long p0);
    
    long getType();
    
    void setType(final long p0);
    
    nsITimerCallback getCallback();
}
