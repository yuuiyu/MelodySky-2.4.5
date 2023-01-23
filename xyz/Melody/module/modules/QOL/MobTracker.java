//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL;

import java.awt.*;
import net.minecraft.entity.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.rendering.*;
import net.minecraft.entity.projectile.*;
import xyz.Melody.Utils.*;
import net.minecraft.entity.passive.*;
import xyz.Melody.*;
import net.minecraft.entity.boss.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.ClientLib.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.item.*;

public class MobTracker extends Module
{
    private Option<Boolean> ghost;
    private Option<Boolean> bat;
    private Option<Boolean> starMob;
    private Option<Boolean> dragon;
    private Option<Boolean> arrows;
    private Option<Boolean> necron;
    private Option<Boolean> farming;
    private HashMap<Integer, Color> mobIDs;
    public HashMap<EntityLivingBase, Color> checked;
    private ArrayList<Entity> endangeredAnimals;
    
    public MobTracker() {
        super("MobTracker", new String[] { "mt" }, ModuleType.Render);
        this.ghost = (Option<Boolean>)new Option("Ghost", (Object)true);
        this.bat = (Option<Boolean>)new Option("Bat", (Object)true);
        this.starMob = (Option<Boolean>)new Option("Starred Mob", (Object)true);
        this.dragon = (Option<Boolean>)new Option("Ender Dragon", (Object)true);
        this.arrows = (Option<Boolean>)new Option("Arrows", (Object)false);
        this.necron = (Option<Boolean>)new Option("Wither", (Object)true);
        this.farming = (Option<Boolean>)new Option("Endangered Animal", (Object)true);
        this.mobIDs = new HashMap<Integer, Color>();
        this.checked = new HashMap<EntityLivingBase, Color>();
        this.endangeredAnimals = new ArrayList<Entity>();
        this.addValues(new Value[] { (Value)this.ghost, (Value)this.bat, (Value)this.starMob, (Value)this.dragon, (Value)this.necron, (Value)this.arrows, (Value)this.farming });
        this.setModInfo("Entity ESP.");
    }
    
    public void onEnable() {
        super.onEnable();
    }
    
    public void onDisable() {
        super.onDisable();
    }
    
    @EventHandler
    public void onRenderEntityModel(final EventRenderEntityModel event) {
        if (this.starMob.getValue()) {
            if (!this.mobIDs.isEmpty() && this.mobIDs.containsKey(event.getEntity().getEntityId()) && !this.checked.containsKey(event.getEntity())) {
                this.checked.put(event.getEntity(), this.mobIDs.get(event.getEntity().getEntityId()));
            }
            if (this.checked.containsKey(event.getEntity())) {
                OutlineUtils.outlineEntity(event, this.checked.get(event.getEntity()));
            }
        }
    }
    
    @EventHandler
    private void onRender3D(final EventRender3D event) {
        this.mobIDs.clear();
        for (final EntityLivingBase elb : this.checked.keySet()) {
            if (elb == null) {
                this.checked.remove(elb);
                break;
            }
            if (!elb.isEntityAlive()) {
                this.checked.remove(elb);
                break;
            }
            if (elb.isDead) {
                this.checked.remove(elb);
                break;
            }
        }
        for (final Entity entity : this.mc.theWorld.getLoadedEntityList()) {
            if (entity instanceof EntityArrow && (boolean)this.arrows.getValue()) {
                final Color c = new Color(Colors.WHITE.c);
                final Color col = new Color(c.getRed(), c.getGreen(), c.getBlue());
                RenderUtil.drawFilledESP(entity, col, event);
            }
            if (entity instanceof EntityCreeper && ScoreboardUtils.scoreboardContains("The Mist") && (boolean)this.ghost.getValue()) {
                final Color c = new Color(Colors.RED.c);
                final Color col = new Color(c.getRed(), c.getGreen(), c.getBlue());
                if (!this.checked.keySet().contains(entity)) {
                    this.checked.put((EntityLivingBase)entity, col);
                    RenderUtil.drawFilledESP(entity, col, event);
                }
            }
            if (entity instanceof EntityBat && (boolean)this.bat.getValue()) {
                final Color c = new Color(Colors.BLUE.c);
                final Color col = new Color(c.getRed(), c.getGreen(), c.getBlue());
                if (!this.checked.keySet().contains(entity)) {
                    this.checked.put((EntityLivingBase)entity, col);
                    RenderUtil.drawFilledESP(entity, col, event);
                }
            }
            if (entity instanceof EntityDragon && (boolean)this.dragon.getValue() && !Client.inDungeons) {
                final Color c = new Color(Colors.YELLOW.c);
                final Color col = new Color(c.getRed(), c.getGreen(), c.getBlue());
                RenderUtil.drawFilledESP(entity, col, event);
            }
            if (entity instanceof EntityWither && (boolean)this.necron.getValue() && !entity.isInvisible() && Client.inDungeons) {
                final Color c = FadeUtil.PURPLE.getColor();
                final Color col = new Color(c.getRed(), c.getGreen(), c.getBlue());
                RenderUtil.drawFilledESP(entity, col, event);
            }
            if ((boolean)this.farming.getValue() && Client.instance.sbArea.getCurrentArea() == SkyblockArea.Areas.The_Farming_Island && entity.hasCustomName()) {
                final String name = entity.getCustomNameTag().toLowerCase();
                if (this.isEndangeredAnimal(name) != null && this.isEndangeredAnimal(name) != EAnimalTypes.NONE) {
                    final List<Entity> mobs = (List<Entity>)this.mc.theWorld.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().expand(0.0, 2.0, 0.0), e -> !(e instanceof EntityArmorStand) && e != this.mc.thePlayer);
                    if (!mobs.isEmpty() && mobs.get(0) instanceof EntityLivingBase && !this.checked.keySet().contains(mobs.get(0))) {
                        final Entity shabi = mobs.get(0);
                        final String string = this.isEndangeredAnimal(name).toString();
                        switch (string) {
                            case "Trackable": {
                                final Color col2 = FadeUtil.PURPLE.getColor();
                                RenderUtil.drawFilledESP(shabi, col2, event, 2.3f);
                                break;
                            }
                            case "Untrackable": {
                                final Color col2 = FadeUtil.BLUE.getColor();
                                RenderUtil.drawFilledESP(shabi, col2, event, 2.3f);
                                break;
                            }
                            case "Undetected": {
                                final Color col2 = FadeUtil.GREEN.getColor();
                                RenderUtil.drawFilledESP(shabi, col2, event, 2.3f);
                                break;
                            }
                            case "Endangered": {
                                final Color col2 = FadeUtil.WHITE.getColor();
                                RenderUtil.drawFilledESP(shabi, col2, event, 2.3f);
                                break;
                            }
                            case "Elusive": {
                                final Color col2 = FadeUtil.RED.getColor();
                                RenderUtil.drawFilledESP(shabi, col2, event, 2.3f);
                                break;
                            }
                        }
                    }
                }
            }
            if (this.starMob.getValue()) {
                final Entity entity2 = entity;
                if (entity.hasCustomName() && entity.getCustomNameTag().contains("\u272f")) {
                    final List<Entity> mobs = (List<Entity>)this.mc.theWorld.getEntitiesInAABBexcluding(entity2, entity2.getEntityBoundingBox().expand(0.0, 3.0, 0.0), e -> !(e instanceof EntityArmorStand) && e != this.mc.thePlayer);
                    if (!mobs.isEmpty()) {
                        final boolean isMiniBoss = entity.getName().toUpperCase().equals("SHADOW ASSASSIN") || entity.getName().toUpperCase().equals("LOST ADVENTURER") || entity.getName().toUpperCase().equals("DIAMOND GUY");
                        if (entity != this.mc.thePlayer && !isMiniBoss) {
                            this.mobIDs.put(mobs.get(0).getEntityId(), new Color(135, 206, 250));
                            if (mobs.get(0) instanceof EntityLivingBase && !this.checked.keySet().contains(mobs.get(0))) {
                                RenderUtil.drawFilledESP(mobs.get(0), new Color(135, 206, 250), event);
                            }
                        }
                    }
                }
                if (entity instanceof EntityEnderman && entity.isInvisible()) {
                    entity.setInvisible(false);
                }
                if (!(entity instanceof EntityPlayer)) {
                    continue;
                }
                final String upperCase = entity.getName().toUpperCase();
                switch (upperCase) {
                    case "SHADOW ASSASSIN": {
                        entity.setInvisible(false);
                        this.mobIDs.put(entity.getEntityId(), new Color(Colors.RED.c));
                        if (!this.checked.keySet().contains(entity)) {
                            RenderUtil.drawFilledESP(entity, new Color(Colors.RED.c), event);
                            continue;
                        }
                        continue;
                    }
                    case "LOST ADVENTURER": {
                        this.mobIDs.put(entity.getEntityId(), new Color(Colors.GREEN.c));
                        if (!this.checked.keySet().contains(entity)) {
                            RenderUtil.drawFilledESP(entity, new Color(Colors.GREEN.c), event);
                            continue;
                        }
                        continue;
                    }
                    case "DIAMOND GUY": {
                        this.mobIDs.put(entity.getEntityId(), new Color(Colors.BLUE.c));
                        if (!this.checked.keySet().contains(entity)) {
                            RenderUtil.drawFilledESP(entity, new Color(Colors.BLUE.c), event);
                            continue;
                        }
                        continue;
                    }
                }
            }
        }
    }
    
    private EAnimalTypes isEndangeredAnimal(final String name) {
        if (name.contains("trackable")) {
            return EAnimalTypes.Trackable;
        }
        if (name.contains("untrackable")) {
            return EAnimalTypes.Untrackable;
        }
        if (name.contains("undetected")) {
            return EAnimalTypes.Undetected;
        }
        if (name.contains("endangered")) {
            return EAnimalTypes.Endangered;
        }
        if (name.contains("elusive")) {
            return EAnimalTypes.Elusive;
        }
        return EAnimalTypes.NONE;
    }
    
    @SubscribeEvent
    public void clear(final WorldEvent.Load event) {
        this.checked.clear();
    }
    
    enum EAnimalTypes
    {
        NONE, 
        Trackable, 
        Untrackable, 
        Undetected, 
        Endangered, 
        Elusive;
    }
}
