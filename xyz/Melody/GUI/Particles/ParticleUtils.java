//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Particles;

public final class ParticleUtils
{
    private static final ParticleGenerator particleGenerator;
    
    public static void drawParticles(final int mouseX, final int mouseY) {
        ParticleUtils.particleGenerator.draw(mouseX, mouseY);
    }
    
    public static void drawParticles() {
        ParticleUtils.particleGenerator.draw(-10, -10);
    }
    
    static {
        particleGenerator = new ParticleGenerator(100);
    }
}
