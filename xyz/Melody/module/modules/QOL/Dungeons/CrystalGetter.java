//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Dungeons;

import xyz.Melody.Utils.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraft.entity.item.*;

public class CrystalGetter extends Module
{
    private TimerUtil timer;
    private EntityEnderCrystal crystal;
    private ArrayList<EntityEnderCrystal> crystalList;
    private Numbers<Double> range;
    
    public CrystalGetter() {
        super("CrystalGetter", new String[] { "cr" }, ModuleType.Dungeons);
        this.timer = new TimerUtil();
        this.crystalList = new ArrayList<EntityEnderCrystal>();
        this.range = (Numbers<Double>)new Numbers("Range", (Number)6.0, (Number)1.0, (Number)6.0, (Number)1.0);
        this.addValues(new Value[] { (Value)this.range });
        this.setModInfo("Auto Get Crystal in Range in F7/M7.");
    }
    
    @EventHandler
    public void onTick(final EventTick event) {
        this.crystal = this.getClosestCrystal();
    }
    
    @EventHandler
    public void onRightClick(final EventPreUpdate event) {
        if (this.crystal == null) {
            return;
        }
        if (this.mc.thePlayer.getDistanceToEntity((Entity)this.crystal) > (double)this.range.getValue()) {
            return;
        }
        final List<Entity> armorStand;
        if (this.crystal != null && this.timer.hasReached(200.0) && !(armorStand = (List<Entity>)this.mc.theWorld.getEntitiesInAABBexcluding((Entity)this.crystal, this.crystal.getEntityBoundingBox(), entity -> entity instanceof EntityArmorStand && entity.getCustomNameTag().contains("CLICK HERE"))).isEmpty() && armorStand.get(0) != null) {
            this.mc.playerController.interactWithEntitySendPacket((EntityPlayer)this.mc.thePlayer, (Entity)armorStand.get(0));
            this.timer.reset();
        }
    }
    
    private EntityEnderCrystal getClosestCrystal() {
        this.crystalList.clear();
        for (final Entity ent : this.mc.theWorld.loadedEntityList) {
            if (ent instanceof EntityEnderCrystal) {
                this.crystalList.add((EntityEnderCrystal)ent);
            }
        }
        this.crystalList.sort((o1, o2) -> (int)(o1.getDistanceToEntity((Entity)this.mc.thePlayer) - o2.getDistanceToEntity((Entity)this.mc.thePlayer)));
        if (this.crystalList.size() > 0) {
            return this.crystalList.get(0);
        }
        return null;
    }
}
