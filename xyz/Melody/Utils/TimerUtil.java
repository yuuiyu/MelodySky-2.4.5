//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils;

import java.util.*;

public class TimerUtil
{
    private long lastMS;
    private long ms;
    
    public TimerUtil() {
        this.ms = this.getCurrentMS();
    }
    
    public final long getElapsedTime() {
        return this.getCurrentMS() - this.ms;
    }
    
    public final boolean elapsed(final long milliseconds) {
        return this.getCurrentMS() - this.ms > milliseconds;
    }
    
    public final void resetStopWatch() {
        this.ms = this.getCurrentMS();
    }
    
    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
    
    public boolean hasReached(final double milliseconds) {
        return this.getCurrentMS() - this.lastMS >= milliseconds;
    }
    
    public void reset() {
        this.lastMS = this.getCurrentMS();
    }
    
    public boolean delay(final float milliSec) {
        return this.getTime() - this.lastMS >= milliSec;
    }
    
    public long getLastMS() {
        return this.lastMS;
    }
    
    public long getTime() {
        return System.nanoTime() / 1000000L;
    }
    
    public static long curTime() {
        return new Date().getTime();
    }
}
