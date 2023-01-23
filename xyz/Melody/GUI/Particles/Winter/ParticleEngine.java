//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Particles.Winter;

import net.minecraft.client.renderer.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import com.google.common.collect.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import org.lwjgl.opengl.*;
import xyz.Melody.Utils.render.gl.*;
import java.util.*;

public class ParticleEngine
{
    public ArrayList<Particle> particles;
    public float lastMouseX;
    public float lastMouseY;
    
    public ParticleEngine() {
        this.particles = new ArrayList<Particle>();
    }
    
    public void render(final float mouseX, final float mouseY) {
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        final float xOffset = mouseX;
        final float yOffset = mouseY;
        this.particles.size();
        while (this.particles.size() < sr.getScaledWidth() / 19) {
            this.particles.add(new Particle(sr, new Random().nextFloat() * 2.0f + 2.0f, new Random().nextFloat() * 5.0f + 5.0f));
        }
        final List<Particle> toremove = (List<Particle>)Lists.newArrayList();
        for (final Particle p : this.particles) {
            if (p.opacity < 32.0f) {
                final Particle particle = p;
                particle.opacity += 2.0f;
            }
            if (p.opacity > 32.0f) {
                p.opacity = 32.0f;
            }
            final Color c = new Color(255, 255, 255, (int)p.opacity);
            RenderUtil.drawFilledCircle(p.x + (float)Math.sin(p.ticks / 2.0f) * 50.0f + -xOffset / 5.0f, p.ticks * p.speed * p.ticks / 10.0f + -yOffset / 5.0f, p.radius * (p.opacity / 32.0f), c);
            final Particle particle2 = p;
            particle2.ticks += (float)0.05;
            if (p.ticks * p.speed * p.ticks / 10.0f + -yOffset / 5.0f > sr.getScaledHeight() || p.ticks * p.speed * p.ticks / 10.0f + -yOffset / 5.0f < 0.0f || p.x + Math.sin(p.ticks / 2.0f) * 50.0 + -xOffset / 5.0f > sr.getScaledWidth() || p.x + Math.sin(p.ticks / 2.0f) * 50.0 + -xOffset / 5.0f < 0.0) {
                toremove.add(p);
            }
        }
        this.particles.removeAll(toremove);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        this.lastMouseX = (float)GLUtils.getMouseX();
        this.lastMouseY = (float)GLUtils.getMouseY();
    }
}
