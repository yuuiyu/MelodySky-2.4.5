//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Particles.Winter;

import net.minecraft.client.gui.*;
import java.util.*;

public class Particle
{
    public float x;
    public float y;
    public float radius;
    public float speed;
    public float ticks;
    public float opacity;
    
    public Particle(final ScaledResolution sr, final float r, final float s) {
        this.x = new Random().nextFloat() * sr.getScaledWidth();
        this.y = new Random().nextFloat() * sr.getScaledHeight();
        this.ticks = new Random().nextFloat() * sr.getScaledHeight() / 10.0f;
        this.radius = r;
        this.speed = s;
    }
}
