//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Account;

import org.lwjgl.input.*;

public final class SlidingCalculation
{
    private double current;
    private double added;
    private double minus;
    
    public SlidingCalculation() {
    }
    
    public SlidingCalculation(final double added, final double minus) {
        this.added = added;
        this.minus = minus;
    }
    
    public SlidingCalculation(final double current, final double added, final double minus) {
        this.current = current;
        this.added = added;
        this.minus = minus;
    }
    
    public void calculation() {
        if (Mouse.hasWheel()) {
            final int wheel = Mouse.getDWheel();
            if (wheel != 0) {
                if (wheel < 0) {
                    this.current += this.added;
                }
                else {
                    this.current -= this.minus;
                }
            }
        }
    }
    
    public double getCurrent() {
        return this.current;
    }
    
    public void setCurrent(final double current) {
        this.current = current;
    }
    
    public double getAdded() {
        return this.added;
    }
    
    public void setAdded(final double added) {
        this.added = added;
    }
    
    public double getMinus() {
        return this.minus;
    }
    
    public void setMinus(final double minus) {
        this.minus = minus;
    }
}
