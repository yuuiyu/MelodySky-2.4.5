//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL;

import xyz.Melody.module.*;
import java.awt.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.monster.*;
import xyz.Melody.Utils.Item.*;
import net.minecraft.item.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.*;
import java.util.*;
import xyz.Melody.Event.*;

public class HideSummonings extends Module
{
    private final ArrayList<String> summonItemIDs;
    
    public HideSummonings() {
        super("HideSummonings", new String[] { "hidemob" }, ModuleType.QOL);
        this.summonItemIDs = new ArrayList<String>(Arrays.asList("HEAVY_HELMET", "ZOMBIE_KNIGHT_HELMET", "SKELETOR_HELMET", "ROTTEN_HELMET", "SKELETON_SOLDIER_HELMET"));
        this.setColor(new Color(158, 205, 125).getRGB());
        this.setModInfo("Hide Summoning Mobs.");
    }
    
    public boolean isSummon(final Entity entity) {
        if (entity instanceof EntityPlayer) {
            final String entName = entity.getName();
            return entName.equals("Lost Adventurer") || entName.equals("Frozen Adventurer") || entName.equals("Shadow Assassin") || entName.equals("Shadow Assassin") || entName.equals("Angry Archaeologist") || entName.equals("King Midas") || entName.equals("Redstone Warrior") || entName.equals("Crypt Dreadlord") || entName.equals("Crypt Soulstealer");
        }
        if (entity instanceof EntityZombie || entity instanceof EntitySkeleton) {
            for (int i = 0; i < 5; ++i) {
                final ItemStack item = ((EntityMob)entity).getEquipmentInSlot(i);
                if (this.summonItemIDs.contains(ItemUtils.getSkyBlockID(item))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @EventHandler
    private void onUpdate(final EventTick event) {
        if (Client.inDungeons || !Client.inSkyblock) {
            return;
        }
        for (final Entity entity : this.mc.theWorld.loadedEntityList) {
            if (this.isSummon(entity)) {
                this.mc.theWorld.removeEntity(entity);
            }
        }
    }
}
