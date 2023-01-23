//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.balance;

import net.minecraft.entity.*;
import xyz.Melody.Utils.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import xyz.Melody.Utils.render.*;
import java.util.*;
import xyz.Melody.Event.*;
import net.minecraft.item.*;
import net.minecraft.client.gui.*;
import xyz.Melody.injection.mixins.entity.*;
import net.minecraft.network.*;
import xyz.Melody.Event.events.rendering.*;
import org.lwjgl.input.*;
import net.minecraft.client.settings.*;
import xyz.Melody.Utils.math.*;
import net.minecraft.util.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.network.play.client.*;
import java.util.stream.*;
import net.minecraft.entity.player.*;
import xyz.Melody.System.Managers.Client.*;
import net.minecraft.entity.boss.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;

public class Aura extends Module
{
    public EntityLivingBase curTarget;
    private List<Entity> targets;
    private int index;
    private Mode<Enum> mode;
    private Numbers<Double> cps;
    private Numbers<Double> range;
    private Numbers<Double> fov;
    private Numbers<Double> sinC;
    public Option<Boolean> targetHud;
    private Option<Boolean> noSwing;
    private Option<Boolean> mouseDown;
    private Option<Boolean> ksprint;
    private Option<Boolean> pre;
    private Option<Boolean> rot;
    private Option<Boolean> esp;
    private Option<Boolean> players;
    private Option<Boolean> friend;
    private Option<Boolean> team;
    private Option<Boolean> animals;
    private Option<Boolean> mobs;
    private Option<Boolean> invis;
    private Option<Boolean> death;
    public boolean isBlocking;
    private Comparator<Entity> angleComparator;
    private TimerUtil attackTimer;
    private TimerUtil switchTimer;
    private TimerUtil singleTimer;
    private TimerUtil sinCTimer;
    private boolean cpsReady;
    
    public Aura() {
        super("KillAura", new String[] { "ka", "aura", "killa" }, ModuleType.Balance);
        this.targets = new ArrayList<Entity>();
        this.mode = (Mode<Enum>)new Mode("Mode", (Enum[])AuraMode.values(), (Enum)AuraMode.Single);
        this.cps = (Numbers<Double>)new Numbers("CPS", (Number)10.0, (Number)1.0, (Number)20.0, (Number)0.5);
        this.range = (Numbers<Double>)new Numbers("Range", (Number)4.5, (Number)1.0, (Number)6.0, (Number)0.1);
        this.fov = (Numbers<Double>)new Numbers("Attack FOV", (Number)360.0, (Number)1.0, (Number)360.0, (Number)10.0);
        this.sinC = (Numbers<Double>)new Numbers("Single Switch", (Number)200.0, (Number)1.0, (Number)1000.0, (Number)1.0);
        this.targetHud = (Option<Boolean>)new Option("TargetHUD", (Object)true);
        this.noSwing = (Option<Boolean>)new Option("No Swing", (Object)false);
        this.mouseDown = (Option<Boolean>)new Option("Mouse Down", (Object)false);
        this.ksprint = (Option<Boolean>)new Option("Keep Sprint", (Object)true);
        this.pre = (Option<Boolean>)new Option("PreAttack", (Object)false);
        this.rot = (Option<Boolean>)new Option("Rotation", (Object)true);
        this.esp = (Option<Boolean>)new Option("ESP", (Object)false);
        this.players = (Option<Boolean>)new Option("Players", (Object)true);
        this.friend = (Option<Boolean>)new Option("FriendFilter", (Object)true);
        this.team = (Option<Boolean>)new Option("TeammateFilter", (Object)true);
        this.animals = (Option<Boolean>)new Option("Animals", (Object)true);
        this.mobs = (Option<Boolean>)new Option("Mobs", (Object)false);
        this.invis = (Option<Boolean>)new Option("Invisibles", (Object)false);
        this.death = (Option<Boolean>)new Option("DeathCheck", (Object)true);
        this.angleComparator = Comparator.comparingDouble(e2 -> e2.getDistanceToEntity((Entity)this.mc.thePlayer));
        this.attackTimer = new TimerUtil();
        this.switchTimer = new TimerUtil();
        this.singleTimer = new TimerUtil();
        this.sinCTimer = new TimerUtil();
        this.cpsReady = false;
        this.addValues((Value)this.mode, (Value)this.cps, (Value)this.range, (Value)this.fov, (Value)this.sinC, (Value)this.targetHud, (Value)this.noSwing, (Value)this.mouseDown, (Value)this.ksprint, (Value)this.pre, (Value)this.rot, (Value)this.esp, (Value)this.players, (Value)this.friend, (Value)this.team, (Value)this.animals, (Value)this.mobs, (Value)this.invis, (Value)this.death);
    }
    
    @Override
    public void onDisable() {
        this.curTarget = null;
        this.targets.clear();
        if (Client.instance.getModuleManager().getModuleByClass(AutoBlock.class).isEnabled() && this.mc.thePlayer.getItemInUseCount() > 0) {
            this.unBlock();
        }
    }
    
    @Override
    public void onEnable() {
        this.curTarget = null;
        this.index = 0;
    }
    
    public static double random(final double min, final double max) {
        final Random random = new Random();
        return min + (int)(random.nextDouble() * (max - min));
    }
    
    private boolean cpsReady() {
        return this.attackTimer.hasReached(1000.0 / ((double)this.cps.getValue() + 5.0 + MathUtil.randomDouble(-3.0, 3.0)));
    }
    
    @EventHandler
    public void onRender(final EventRender3D event) {
        if (this.curTarget == null) {
            return;
        }
        if ((boolean)this.mouseDown.getValue() && !this.mc.gameSettings.keyBindAttack.isKeyDown()) {
            return;
        }
        if (this.esp.getValue()) {
            if (this.mode.getValue() == AuraMode.Multi) {
                for (final Entity ent : this.targets) {
                    final Color col = new Color(60, 127, 130, 120);
                    RenderUtil.drawFilledESP(ent, col, event, 3.0f);
                }
                final Color col2 = new Color(200, 30, 30, 190);
                RenderUtil.drawFilledESP((Entity)this.curTarget, col2, event, 6.0f);
                return;
            }
            final float partialTicks = event.getPartialTicks();
            GL11.glPushMatrix();
            GL11.glDisable(3553);
            RenderUtil.startDrawing();
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glLineWidth(4.0f);
            GL11.glBegin(3);
            final double x = this.curTarget.lastTickPosX + (this.curTarget.posX - this.curTarget.lastTickPosX) * partialTicks - this.mc.getRenderManager().viewerPosX;
            final double y = this.curTarget.lastTickPosY + (this.curTarget.posY - this.curTarget.lastTickPosY) * partialTicks - this.mc.getRenderManager().viewerPosY;
            final double z = this.curTarget.lastTickPosZ + (this.curTarget.posZ - this.curTarget.lastTickPosZ) * partialTicks - this.mc.getRenderManager().viewerPosZ;
            for (int i = 0; i <= 10; ++i) {
                RenderUtil.glColor(FadeUtil.fade(FadeUtil.BLUE.getColor()).getRGB());
                GL11.glVertex3d(x + 1.1 * Math.cos(i * 6.283185307179586 / 9.0), y, z + 1.1 * Math.sin(i * 6.283185307179586 / 9.0));
            }
            GL11.glEnd();
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
            RenderUtil.stopDrawing();
            GL11.glEnable(3553);
            GL11.glPopMatrix();
        }
    }
    
    private boolean hasSword() {
        return this.mc.thePlayer.getCurrentEquippedItem() != null && this.mc.thePlayer.inventory.getCurrentItem().getItem() instanceof ItemSword;
    }
    
    @EventHandler
    private void onTick(final EventTick event) {
        if (!(boolean)this.ksprint.getValue()) {
            this.mc.thePlayer.setSprinting(false);
        }
        if ((boolean)this.death.getValue() && this.mc.thePlayer != null) {
            if (!this.mc.thePlayer.isEntityAlive() || (this.mc.currentScreen != null && this.mc.currentScreen instanceof GuiGameOver)) {
                this.setEnabled(false);
                return;
            }
            if (this.mc.thePlayer.ticksExisted <= 1) {
                this.setEnabled(false);
            }
        }
    }
    
    private void block() {
        if (this.hasSword()) {
            ((EPSPAccessor)this.mc.thePlayer).setItemInUseCount(this.mc.thePlayer.getHeldItem().getMaxItemUseDuration());
            this.mc.thePlayer.sendQueue.getNetworkManager().sendPacket((Packet)new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, this.mc.thePlayer.getHeldItem(), 0.0f, 0.0f, 0.0f));
            this.isBlocking = true;
        }
    }
    
    private void unBlock() {
        if (this.hasSword() && this.isBlocking) {
            if (!this.mc.thePlayer.isBlocking() && this.mc.thePlayer.getItemInUseCount() > 0) {
                ((EPSPAccessor)this.mc.thePlayer).setItemInUseCount(0);
            }
            this.mc.thePlayer.sendQueue.getNetworkManager().sendPacket((Packet)new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.RELEASE_USE_ITEM, new BlockPos(-1, -1, -1), EnumFacing.DOWN));
            this.isBlocking = false;
        }
    }
    
    @EventHandler
    private void onAuraLoad(final EventRender2D event) {
        this.cpsReady = this.cpsReady();
        if (this.mc.thePlayer.getHeldItem() != null && this.mc.thePlayer.getHeldItem().getItem() instanceof ItemSword && Mouse.isButtonDown(1) && this.curTarget != null) {
            KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindUseItem.getKeyCode(), false);
        }
        this.targets.removeIf(e -> this.mc.thePlayer.getDistanceToEntity(e) > (double)this.range.getValue() || !e.isEntityAlive() || e.isDead);
        if (this.targets.size() > 0 && this.mode.getValue() == AuraMode.Multi) {
            if (this.curTarget == null) {
                this.curTarget = (EntityLivingBase)this.targets.get(0);
            }
            if (this.curTarget.hurtTime > 0 || this.switchTimer.hasReached((double)this.sinC.getValue())) {
                this.curTarget.hurtTime = 0;
                ++this.index;
                if (this.index + 1 > this.targets.size()) {
                    this.index = 0;
                }
                this.curTarget = (EntityLivingBase)this.targets.get(this.index);
                this.switchTimer.reset();
            }
        }
    }
    
    @EventHandler
    private void tickAura(final EventTick event) {
        if ((boolean)this.mouseDown.getValue() && !this.mc.gameSettings.keyBindAttack.isKeyDown() && this.sinCTimer.hasReached(200.0)) {
            this.curTarget = null;
            if (this.isBlocking) {
                this.unBlock();
            }
            this.sinCTimer.reset();
            return;
        }
        if (this.mode.getValue() == AuraMode.Single && (this.curTarget == null || this.curTarget.isDead || !this.curTarget.isEntityAlive() || this.mc.thePlayer.getDistanceToEntity((Entity)this.curTarget) > (double)this.range.getValue() || RotationUtil.isInFov(this.mc.thePlayer.rotationYaw, this.mc.thePlayer.rotationPitch, this.curTarget.posX, this.curTarget.posY, this.curTarget.posZ) > (double)this.fov.getValue())) {
            this.curTarget = null;
            (this.targets = this.getTargets((Double)this.range.getValue())).sort(this.angleComparator);
        }
        if (this.curTarget != null && (this.curTarget.getHealth() == 0.0f || !this.curTarget.isEntityAlive() || this.mc.thePlayer.getDistanceToEntity((Entity)this.curTarget) > (double)this.range.getValue())) {
            this.curTarget = null;
            ++this.index;
        }
    }
    
    @EventHandler
    private void onUpdate(final EventPreUpdate event) {
        if (!(boolean)this.mouseDown.getValue() || ((boolean)this.mouseDown.getValue() && this.mc.gameSettings.keyBindAttack.isKeyDown())) {
            if (this.mode.getValue() == AuraMode.Single && this.curTarget == null && !this.targets.isEmpty()) {
                this.curTarget = (EntityLivingBase)this.targets.get(0);
            }
            final boolean autoblock = Client.instance.getModuleManager().getModuleByClass(AutoBlock.class).isEnabled() || (Mouse.isButtonDown(1) && this.mc.currentScreen == null && this.mc.thePlayer.getHeldItem() != null && this.mc.thePlayer.getHeldItem().getItem() instanceof ItemSword);
            if (this.curTarget == null && autoblock && this.isBlocking && this.hasSword()) {
                this.unBlock();
            }
            if (this.hasSword() && this.curTarget != null && autoblock && !this.isBlocking) {
                this.block();
            }
            if (this.mode.getValue() == AuraMode.Multi) {
                if (this.targets.isEmpty() || this.curTarget == null || this.singleTimer.hasReached((double)this.sinC.getValue())) {
                    this.targets = this.getTargets((Double)this.range.getValue());
                }
                for (final Entity e : this.targets) {
                    if (e == null || !e.isEntityAlive() || e.isDead || this.mc.thePlayer.getDistanceToEntity(e) > (double)this.range.getValue()) {
                        this.targets = this.getTargets((Double)this.range.getValue());
                    }
                }
                this.sinCTimer.reset();
            }
            if (this.targets.size() > 1 && this.mode.getValue() == AuraMode.Single && this.curTarget != null) {
                if (this.curTarget.getDistanceToEntity((Entity)this.mc.thePlayer) > (double)this.range.getValue()) {
                    this.curTarget = null;
                }
                else if (this.curTarget.isDead) {
                    this.curTarget = null;
                }
                this.singleTimer.reset();
            }
            if ((boolean)this.pre.getValue() && this.curTarget != null) {
                if ((boolean)this.pre.getValue() && this.cpsReady) {
                    if (this.hasSword() && this.mc.thePlayer.isBlocking() && this.isValidEntity((Entity)this.curTarget)) {
                        this.unBlock();
                    }
                    this.attack();
                    if (!this.mc.thePlayer.isBlocking() && this.hasSword() && autoblock) {
                        this.block();
                    }
                }
                this.curTarget = null;
            }
        }
        if (!this.targets.isEmpty() && this.curTarget != null && this.mode.getValue() != AuraMode.Multi && (boolean)this.rot.getValue()) {
            final float yaw = this.getRotationFormEntity(this.curTarget)[0];
            final float pitch = this.getRotationFormEntity(this.curTarget)[1];
            final float smoothYaw = (float)MathUtil.randomDouble(yaw - new Random().nextFloat() * 0.1, yaw + new Random().nextFloat() * 0.1);
            final float smoothPitch = (float)MathUtil.randomDouble(pitch - new Random().nextFloat() * 0.1, pitch + new Random().nextFloat() * 0.1);
            this.mc.thePlayer.rotationYawHead = smoothYaw;
            Client.instance.rotationPitchHead = smoothPitch;
            this.mc.thePlayer.renderYawOffset = smoothYaw;
            event.setYaw(this.mc.thePlayer.prevRenderYawOffset = smoothYaw);
            event.setPitch(smoothPitch);
        }
    }
    
    private float smoothRotation(final float current, final float target, final float maxIncrement) {
        float deltaAngle = MathHelper.wrapAngleTo180_float(target - current);
        if (deltaAngle > maxIncrement) {
            deltaAngle = maxIncrement;
        }
        if (deltaAngle < -maxIncrement) {
            deltaAngle = -maxIncrement;
        }
        return current + deltaAngle / 2.0f;
    }
    
    public float[] getRotationFormEntity(final EntityLivingBase target) {
        return RotationUtil.getPredictedRotations(target);
    }
    
    @EventHandler
    private void onUpdatePost(final EventPostUpdate e) {
        if ((boolean)this.mouseDown.getValue() && !this.mc.gameSettings.keyBindAttack.isKeyDown()) {
            return;
        }
        if (this.pre.getValue()) {
            return;
        }
        if (this.curTarget != null) {
            if (this.cpsReady) {
                if (this.hasSword() && this.mc.thePlayer.isBlocking() && this.isValidEntity((Entity)this.curTarget)) {
                    this.unBlock();
                }
                this.attack();
            }
            final boolean autoblock = Client.instance.getModuleManager().getModuleByClass(AutoBlock.class).isEnabled() || (Mouse.isButtonDown(1) && this.mc.currentScreen == null && this.mc.thePlayer.getHeldItem() != null && this.mc.thePlayer.getHeldItem().getItem() instanceof ItemSword);
            if (!this.mc.thePlayer.isBlocking() && this.hasSword() && autoblock) {
                this.block();
            }
        }
    }
    
    private void attack() {
        final boolean autoblock = Client.instance.getModuleManager().getModuleByClass(AutoBlock.class).isEnabled() || (Mouse.isButtonDown(1) && this.mc.currentScreen == null && this.mc.thePlayer.getHeldItem() != null && this.mc.thePlayer.getHeldItem().getItem() instanceof ItemSword);
        if ((boolean)this.noSwing.getValue() && autoblock) {
            this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C0APacketAnimation());
        }
        else {
            this.mc.thePlayer.swingItem();
        }
        this.mc.thePlayer.onEnchantmentCritical((Entity)this.curTarget);
        this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C02PacketUseEntity((Entity)this.curTarget, C02PacketUseEntity.Action.ATTACK));
        if (this.cpsReady) {
            this.attackTimer.reset();
        }
        if (this.isBlocking && !autoblock) {
            this.unBlock();
        }
    }
    
    public List<Entity> getTargets(final Double value) {
        if (this.mode.getValue() != AuraMode.Multi) {
            this.mc.theWorld.loadedEntityList.sort(Comparator.comparingDouble(e -> this.mc.thePlayer.getDistanceToEntity(e)));
            return (List<Entity>)this.mc.theWorld.loadedEntityList.subList(0, (this.mc.theWorld.loadedEntityList.size() > 4) ? 4 : this.mc.theWorld.loadedEntityList.size()).stream().filter(e -> this.isValidEntity(e)).collect(Collectors.toList());
        }
        return (List<Entity>)this.mc.theWorld.loadedEntityList.stream().filter(e -> this.mc.thePlayer.getDistanceToEntity(e) <= value && this.isValidEntity(e)).collect(Collectors.toList());
    }
    
    private boolean isValidEntity(final Entity ent) {
        if (ent == this.mc.thePlayer) {
            return false;
        }
        if (this.mc.thePlayer.getDistanceToEntity(ent) > (double)this.range.getValue()) {
            return false;
        }
        if (RotationUtil.isInFov(this.mc.thePlayer.rotationYaw, this.mc.thePlayer.rotationPitch, ent.posX, ent.posY, ent.posZ) > (double)this.fov.getValue()) {
            return false;
        }
        final AntiBot ab = (AntiBot)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)AntiBot.class);
        return !ab.isEntityBot(ent) && ((ent instanceof EntityPlayer && (boolean)this.players.getValue() && !this.isOnSameTeam(ent)) || (ent.isEntityAlive() && (!ent.isInvisible() || (boolean)this.invis.getValue()) && (!(ent instanceof EntityPlayer) || !FriendManager.isFriend(ent.getName()) || !(boolean)this.friend.getValue()) && (((ent instanceof EntityMob || ent instanceof EntityGhast || ent instanceof EntityGolem || ent instanceof EntityDragon || ent instanceof EntitySlime) && (boolean)this.mobs.getValue()) || ((ent instanceof EntitySquid || ent instanceof EntityBat || ent instanceof EntityVillager) && (boolean)this.animals.getValue()) || (ent instanceof EntityAnimal && (boolean)this.animals.getValue()))));
    }
    
    public boolean isOnSameTeam(final Entity entity) {
        if (!(boolean)this.team.getValue()) {
            return false;
        }
        if (this.mc.thePlayer.getDisplayName().getUnformattedText().startsWith("§")) {
            if (this.mc.thePlayer.getDisplayName().getUnformattedText().length() <= 2 || entity.getDisplayName().getUnformattedText().length() <= 2) {
                return false;
            }
            if (this.mc.thePlayer.getDisplayName().getUnformattedText().substring(0, 2).equals(entity.getDisplayName().getUnformattedText().substring(0, 2))) {
                return true;
            }
        }
        return false;
    }
    
    enum AuraMode
    {
        Multi, 
        Single;
    }
    
    enum rotationMode
    {
        Tick, 
        Packet;
    }
}
