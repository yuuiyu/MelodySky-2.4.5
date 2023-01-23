//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.ClickNew;

public class Opacity
{
    private float opacity;
    private long lastMS;
    
    public Opacity(final int opacity) {
        this.opacity = (float)opacity;
        this.lastMS = System.currentTimeMillis();
    }
    
    public void interpolate(final float targetOpacity) {
        final long currentMS = System.currentTimeMillis();
        final long delta = currentMS - this.lastMS;
        this.lastMS = currentMS;
        this.opacity = AnimationUtil.calculateCompensation(targetOpacity, this.opacity, delta, 20);
    }
    
    public void interp(final float targetOpacity, final int speed) {
        final long currentMS = System.currentTimeMillis();
        final long delta = currentMS - this.lastMS;
        this.lastMS = currentMS;
        this.opacity = AnimationUtil.calculateCompensation(targetOpacity, this.opacity, delta, speed);
    }
    
    public float getOpacity() {
        return this.opacity;
    }
    
    public void setOpacity(final float opacity) {
        this.opacity = opacity;
    }
}
