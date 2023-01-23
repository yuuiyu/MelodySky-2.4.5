//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.render;

import xyz.Melody.module.*;
import java.awt.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.*;
import xyz.Melody.Event.*;

public class HideImplosionParticle extends Module
{
    public HideImplosionParticle() {
        super("ImplosionParticle", new String[] { "implosionparticle" }, ModuleType.Render);
        this.setModInfo("Hide Implosion(Wither Impact) Particles.");
        this.setColor(new Color(244, 255, 149).getRGB());
    }
    
    @EventHandler
    private void onPacketRCV(final EventPacketRecieve event) {
        if (event.getPacket() instanceof S2APacketParticles) {
            final S2APacketParticles s2a = (S2APacketParticles)event.getPacket();
            final boolean dist = s2a.isLongDistance();
            final float speed = s2a.getParticleSpeed();
            final int count = s2a.getParticleCount();
            final float x = s2a.getXOffset();
            final float y = s2a.getYOffset();
            final float z = s2a.getZOffset();
            final EnumParticleTypes type = s2a.getParticleType();
            if (type == EnumParticleTypes.EXPLOSION_LARGE && dist && speed == 8.0f && count == 8 && x == 0.0f && y == 0.0f && z == 0.0f) {
                event.setCancelled(true);
            }
        }
    }
}
