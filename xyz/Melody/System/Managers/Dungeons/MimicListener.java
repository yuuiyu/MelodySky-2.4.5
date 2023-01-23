//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Managers.Dungeons;

import net.minecraft.entity.*;
import net.minecraft.client.*;
import net.minecraftforge.common.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.*;
import java.util.*;
import xyz.Melody.Event.*;
import net.minecraftforge.event.entity.living.*;
import xyz.Melody.module.modules.QOL.Dungeons.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.monster.*;

public class MimicListener
{
    private List<Entity> possibleMimic;
    private Minecraft mc;
    
    public MimicListener() {
        this.possibleMimic = new ArrayList<Entity>();
        this.mc = Minecraft.getMinecraft();
    }
    
    public void init() {
        MinecraftForge.EVENT_BUS.register((Object)new MimicListener());
        EventBus.getInstance().register(new Object[] { this });
    }
    
    @EventHandler
    public void onEntityUpdate(final EventTick event) {
        if (this.mc.thePlayer == null || this.mc.theWorld == null) {
            return;
        }
        if (!Client.inDungeons) {
            return;
        }
        if (Client.instance.dungeonUtils.foundMimic) {
            return;
        }
        for (final Entity entity : this.mc.theWorld.loadedEntityList) {
            if (entity.hasCustomName() && entity.getCustomNameTag().contains("Mimic")) {
                this.possibleMimic = (List<Entity>)this.mc.theWorld.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().expand(0.0, 1.0, 0.0), e -> !(e instanceof EntityArmorStand) && e instanceof EntityZombie && ((EntityZombie)e).isChild());
            }
        }
    }
    
    @SubscribeEvent
    public void onEntityDeath(final LivingDeathEvent event) {
        if (Client.inDungeons && !Client.instance.dungeonUtils.foundMimic && !this.possibleMimic.isEmpty() && event.entity == this.possibleMimic.get(0)) {
            final SayMimicKilled smk = (SayMimicKilled)Client.instance.getModuleManager().getModuleByClass((Class)SayMimicKilled.class);
            if (smk.isEnabled()) {
                if (smk.mode.getValue() == SayMimicKilled.Chats.All) {
                    this.mc.thePlayer.sendChatMessage("/ac Mimic Killed.");
                }
                if (smk.mode.getValue() == SayMimicKilled.Chats.Party) {
                    this.mc.thePlayer.sendChatMessage("/pc Mimic Killed.");
                }
            }
            Client.instance.dungeonUtils.foundMimic = true;
        }
    }
}
