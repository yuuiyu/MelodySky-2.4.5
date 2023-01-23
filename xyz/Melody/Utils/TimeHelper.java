//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils;

public class TimeHelper
{
    public long lastMs;
    
    public TimeHelper() {
        this.lastMs = 0L;
    }
    
    public boolean isDelayComplete(final long delay) {
        return System.currentTimeMillis() - this.lastMs > delay;
    }
    
    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
    
    public void reset() {
        this.lastMs = System.currentTimeMillis();
    }
    
    public long getLastMs() {
        return this.lastMs;
    }
    
    public void setLastMs(final int i) {
        this.lastMs = System.currentTimeMillis() + i;
    }
    
    public boolean hasReached(final long milliseconds) {
        return this.getCurrentMS() - this.lastMs >= milliseconds;
    }
    
    public boolean hasReached(final float timeLeft) {
        return this.getCurrentMS() - this.lastMs >= timeLeft;
    }
    
    public boolean delay(final double nextDelay) {
        return System.currentTimeMillis() - this.lastMs >= nextDelay;
    }
}
