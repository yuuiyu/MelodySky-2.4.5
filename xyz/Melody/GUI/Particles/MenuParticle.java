//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Particles;

import java.util.*;

public class MenuParticle
{
    public double x;
    public double y;
    public double preX;
    public double preY;
    public double depth;
    public double motionX;
    public double motionY;
    public float alpha;
    public boolean remove;
    public boolean followMouse;
    public Random rand;
    public boolean alphaDecay;
    
    public MenuParticle(final double x, final double y, final double depth, final boolean followMouse) {
        this.alpha = 1.0f;
        this.remove = false;
        this.followMouse = false;
        this.rand = new Random();
        this.alphaDecay = false;
        this.x = x;
        this.y = y;
        this.depth = depth;
        this.followMouse = followMouse;
    }
    
    public void update(final int mouseX, final int mouseY, final ArrayList<MenuParticle> particles) {
        if (this.followMouse) {
            float angle = (float)Math.toDegrees(Math.atan2(mouseY - this.y, mouseX - this.x));
            if (angle < 0.0f) {
                angle += 360.0f;
            }
            final double mX = Math.cos(Math.toRadians(angle));
            final double mY = Math.sin(Math.toRadians(angle));
            this.motionX += mX * 1.0;
            this.motionY += mY * 1.0;
        }
        this.preX = this.x;
        this.preY = this.y;
        this.x += this.motionX;
        this.y += this.motionY;
        this.motionY *= 0.985;
        this.motionX *= 0.985;
        if (this.alphaDecay) {
            this.alpha *= 0.995f;
            this.motionX *= 1.1;
            this.motionY *= 1.1;
        }
    }
    
    public MenuParticle addMotion(final double x, final double y) {
        this.motionX += x;
        this.motionY += y;
        return this;
    }
}
