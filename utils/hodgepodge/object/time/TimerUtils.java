//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package utils.hodgepodge.object.time;

public final class TimerUtils
{
    private final boolean autoReset;
    private long lastTime;
    
    public TimerUtils() {
        this.autoReset = false;
    }
    
    public TimerUtils(final boolean autoReset) {
        this.autoReset = autoReset;
    }
    
    public boolean hasReached(final double milliseconds) {
        final boolean b = this.getCurrentMS() - this.lastTime >= milliseconds;
        if (this.autoReset && b) {
            this.reset();
            return true;
        }
        return b;
    }
    
    public final long getElapsedTime() {
        return this.getCurrentMS() - this.lastTime;
    }
    
    public void reset() {
        this.lastTime = this.getCurrentMS();
    }
    
    private long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
}
