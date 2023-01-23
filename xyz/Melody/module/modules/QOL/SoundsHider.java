//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.network.play.server.*;
import net.minecraft.network.*;
import xyz.Melody.Event.*;

public class SoundsHider extends Module
{
    private Option<Boolean> emanHurt;
    private Option<Boolean> emanDie;
    private Option<Boolean> emanAnger;
    private Option<Boolean> abcd;
    private Option<Boolean> jerryChine;
    private Option<Boolean> explosions;
    
    public SoundsHider() {
        super("SoundsHider", new String[] { "nbt" }, ModuleType.QOL);
        this.emanHurt = (Option<Boolean>)new Option("Enderman Hurt", (Object)false);
        this.emanDie = (Option<Boolean>)new Option("Enderman Die", (Object)true);
        this.emanAnger = (Option<Boolean>)new Option("Enderman Anger", (Object)true);
        this.abcd = (Option<Boolean>)new Option("Ability Cooldown", (Object)true);
        this.jerryChine = (Option<Boolean>)new Option("Jerry-ChineGun", (Object)false);
        this.explosions = (Option<Boolean>)new Option("Explosions", (Object)false);
        this.addValues(new Value[] { (Value)this.emanHurt, (Value)this.emanAnger, (Value)this.emanDie, (Value)this.abcd, (Value)this.jerryChine, (Value)this.explosions });
        this.setModInfo("Hide Fuckin Sounds.");
    }
    
    @EventHandler
    private void onPacketRCV(final EventPacketRecieve e) {
        final Packet<?> packet = (Packet<?>)e.getPacket();
        if (packet instanceof S29PacketSoundEffect) {
            final S29PacketSoundEffect sound = (S29PacketSoundEffect)packet;
            if ((boolean)this.emanHurt.getValue() && sound.getSoundName().contains("mob.endermen.hit")) {
                e.setCancelled(true);
            }
            if ((boolean)this.emanDie.getValue() && sound.getSoundName().contains("mob.endermen.death")) {
                e.setCancelled(true);
            }
            if ((boolean)this.emanAnger.getValue() && (sound.getSoundName().contains("mob.endermen.scream") || sound.getSoundName().contains("mob.endermen.stare"))) {
                e.setCancelled(true);
            }
            if ((boolean)this.explosions.getValue() && sound.getSoundName().contains("random.explode")) {
                e.setCancelled(true);
            }
            if (this.jerryChine.getValue()) {
                if (sound.getSoundName().contains("mob.villager.yes") && sound.getVolume() == 0.35f) {
                    e.setCancelled(true);
                }
                if (sound.getSoundName().contains("mob.villager.haggle") && sound.getVolume() == 0.5f) {
                    e.setCancelled(true);
                }
            }
            if ((boolean)this.abcd.getValue() && sound.getSoundName().contains("mob.endermen.portal") && sound.getPitch() == 0.0f && sound.getVolume() == 8.0f) {
                e.setCancelled(true);
            }
        }
    }
}
