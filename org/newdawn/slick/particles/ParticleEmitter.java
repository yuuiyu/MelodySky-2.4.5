//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.particles;

import org.newdawn.slick.*;

public interface ParticleEmitter
{
    void update(final ParticleSystem p0, final int p1);
    
    boolean completed();
    
    void wrapUp();
    
    void updateParticle(final Particle p0, final int p1);
    
    boolean isEnabled();
    
    void setEnabled(final boolean p0);
    
    boolean useAdditive();
    
    Image getImage();
    
    boolean isOriented();
    
    boolean usePoints(final ParticleSystem p0);
    
    void resetState();
}
