//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.Fishing;

import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import java.awt.*;
import xyz.Melody.*;
import xyz.Melody.module.modules.others.*;
import net.minecraft.client.settings.*;
import xyz.Melody.Event.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.multiplayer.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.item.*;
import net.minecraft.network.*;
import xyz.Melody.Utils.math.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.Utils.*;
import xyz.Melody.Utils.render.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.entity.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.event.world.*;
import java.util.stream.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.*;
import xyz.Melody.module.balance.*;
import xyz.Melody.System.Managers.Client.*;
import java.util.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;

public class AutoFish extends Module
{
    private int tickTimer;
    private int tickTimer1;
    private int dickTimer;
    private Vec3 soundVec;
    private Option<Boolean> plist;
    private Option<Boolean> unGrab;
    private Option<Boolean> lockRod;
    private Option<Boolean> admin;
    private Option<Boolean> lockView;
    private Option<Boolean> dead;
    private Option<Boolean> waterCheck;
    private Option<Boolean> escape;
    private Numbers<Double> escapeRange;
    private Option<Boolean> kill;
    private Option<Boolean> rckill;
    private Numbers<Double> killRange;
    private Numbers<Double> rccd;
    private Numbers<Double> angleDiff;
    private Numbers<Double> angleSize;
    private Numbers<Double> killSize;
    private Option<Boolean> autoThrow;
    private Option<Boolean> showDebug;
    private Option<Boolean> packetDebug;
    private Mode<Enum> rotationMode;
    private Option<Boolean> rotation;
    private Mode<Enum> moveMode;
    private Option<Boolean> move;
    private Option<Boolean> holdShift;
    private Option<Boolean> randomDelay;
    private Numbers<Double> angle;
    private Numbers<Double> tickTimerVale;
    private Option<Boolean> soundBB;
    private Numbers<Double> soundRadius;
    private Option<Boolean> squid;
    private Option<Boolean> guard;
    private Option<Boolean> skeleton;
    private Option<Boolean> zombie;
    private Option<Boolean> witch;
    private Option<Boolean> cat;
    private Option<Boolean> silverfish;
    private Option<Boolean> golem;
    private Option<Boolean> rabbit;
    private Option<Boolean> sheep;
    private Option<Boolean> endermite;
    private Option<Boolean> blaze;
    private Option<Boolean> pigman;
    private Option<Boolean> horse;
    private Option<Boolean> player;
    private Enum<?> currentStage;
    private boolean backRotaion;
    private boolean soundReady;
    private boolean soundCDReady;
    private boolean motionReady;
    private int extraDelay;
    private boolean delaySet;
    private List<Entity> allSCNear;
    private EntityLivingBase currentSC;
    private TimerUtil attackTimer;
    private TimerUtil rightClickTimer;
    private boolean reachedSize;
    private int lcIndex;
    private boolean yawRecorded;
    private boolean pitchRecorded;
    private float lastRotationYaw;
    private float lastRotationPitch;
    private boolean yawRestored;
    private boolean pitchRestored;
    private float yawDiff;
    private float pitchDiff;
    private boolean shouldSwitchToWeapon;
    private boolean shouldSwitchToRod;
    private boolean switchedToRod;
    private Vec3 lockedVec;
    private TimerUtil reThrowTimer;
    private TimerUtil moveTimer;
    private boolean moveDone;
    private boolean moved;
    private boolean moveBack;
    private boolean needToEscape;
    private TimerUtil escapeDelay;
    private EntityPlayer playerCaused;
    private boolean escaped;
    
    public AutoFish() {
        super("AutoFish", new String[] { "af", "fishing", "fish" }, ModuleType.Fishing);
        this.tickTimer = 0;
        this.tickTimer1 = 0;
        this.dickTimer = 0;
        this.soundVec = null;
        this.plist = (Option<Boolean>)new Option("EnablePlayerList", (Object)true);
        this.unGrab = (Option<Boolean>)new Option("UnGrabMouse", (Object)false);
        this.lockRod = (Option<Boolean>)new Option("LockRod", (Object)false);
        this.admin = (Option<Boolean>)new Option("AntiAdmin", (Object)false);
        this.lockView = (Option<Boolean>)new Option("LockView", (Object)false);
        this.dead = (Option<Boolean>)new Option("DeathCheck", (Object)true);
        this.waterCheck = (Option<Boolean>)new Option("Water/Lava Check", (Object)true);
        this.escape = (Option<Boolean>)new Option("Escape", (Object)false);
        this.escapeRange = (Numbers<Double>)new Numbers("Escape Range", (Number)5.0, (Number)0.0, (Number)20.0, (Number)1.0);
        this.kill = (Option<Boolean>)new Option("AutoKill", (Object)false);
        this.rckill = (Option<Boolean>)new Option("RightClickKill", (Object)false);
        this.killRange = (Numbers<Double>)new Numbers("KillRange", (Number)3.0, (Number)0.0, (Number)4.2, (Number)0.1);
        this.rccd = (Numbers<Double>)new Numbers("RcDelay(ms)", (Number)2500.0, (Number)100.0, (Number)5000.0, (Number)100.0);
        this.angleDiff = (Numbers<Double>)new Numbers("AngleDiff", (Number)1.0E-4, (Number)0.0, (Number)0.1, (Number)1.0E-4);
        this.angleSize = (Numbers<Double>)new Numbers("AngleSize", (Number)60.0, (Number)10.0, (Number)100.0, (Number)5.0);
        this.killSize = (Numbers<Double>)new Numbers("ScKillSize", (Number)1.0, (Number)1.0, (Number)20.0, (Number)1.0);
        this.autoThrow = (Option<Boolean>)new Option("AutoThrow", (Object)false);
        this.showDebug = (Option<Boolean>)new Option("Show Debug", (Object)false);
        this.packetDebug = (Option<Boolean>)new Option("PacketDebug", (Object)false);
        this.rotationMode = (Mode<Enum>)new Mode("RotationMode", (Enum[])rotations.values(), (Enum)rotations.Yaw);
        this.rotation = (Option<Boolean>)new Option("NoRotationAFK", (Object)true);
        this.moveMode = (Mode<Enum>)new Mode("MoveMode", (Enum[])moves.values(), (Enum)moves.AD);
        this.move = (Option<Boolean>)new Option("NoMovingAFK", (Object)true);
        this.holdShift = (Option<Boolean>)new Option("Sneaking", (Object)false);
        this.randomDelay = (Option<Boolean>)new Option("RandomDelay", (Object)true);
        this.angle = (Numbers<Double>)new Numbers("RotationAngle", (Number)1.0, (Number)1.0, (Number)5.0, (Number)1.0);
        this.tickTimerVale = (Numbers<Double>)new Numbers("TickTimer", (Number)80.0, (Number)20.0, (Number)200.0, (Number)10.0);
        this.soundBB = (Option<Boolean>)new Option("SoundBox", (Object)false);
        this.soundRadius = (Numbers<Double>)new Numbers("SoundRadius", (Number)0.5, (Number)0.1, (Number)5.0, (Number)0.1);
        this.squid = (Option<Boolean>)new Option("KillSquids", (Object)true);
        this.guard = (Option<Boolean>)new Option("KillGuardians", (Object)true);
        this.skeleton = (Option<Boolean>)new Option("KillSkeletons", (Object)true);
        this.zombie = (Option<Boolean>)new Option("KillZombies", (Object)true);
        this.witch = (Option<Boolean>)new Option("KillWitches", (Object)true);
        this.cat = (Option<Boolean>)new Option("KillOcelots", (Object)true);
        this.silverfish = (Option<Boolean>)new Option("KillSilverFishes", (Object)true);
        this.golem = (Option<Boolean>)new Option("KillGolems", (Object)true);
        this.rabbit = (Option<Boolean>)new Option("KillRabbits", (Object)true);
        this.sheep = (Option<Boolean>)new Option("KillSheeps", (Object)true);
        this.endermite = (Option<Boolean>)new Option("KillEnderMites", (Object)true);
        this.blaze = (Option<Boolean>)new Option("KillBlazes", (Object)true);
        this.pigman = (Option<Boolean>)new Option("KillPigmans", (Object)true);
        this.horse = (Option<Boolean>)new Option("KillHorses", (Object)true);
        this.player = (Option<Boolean>)new Option("KillOthers", (Object)true);
        this.currentStage = stage.NONE;
        this.backRotaion = false;
        this.soundReady = false;
        this.soundCDReady = false;
        this.motionReady = false;
        this.extraDelay = 0;
        this.delaySet = false;
        this.allSCNear = new ArrayList<Entity>();
        this.currentSC = null;
        this.attackTimer = new TimerUtil();
        this.rightClickTimer = new TimerUtil();
        this.reachedSize = false;
        this.lcIndex = 0;
        this.yawRecorded = false;
        this.pitchRecorded = false;
        this.lastRotationYaw = 0.0f;
        this.lastRotationPitch = 0.0f;
        this.yawRestored = true;
        this.pitchRestored = true;
        this.yawDiff = 0.0f;
        this.pitchDiff = 0.0f;
        this.shouldSwitchToWeapon = false;
        this.shouldSwitchToRod = false;
        this.switchedToRod = false;
        this.lockedVec = new Vec3(0.0, 0.0, 0.0);
        this.reThrowTimer = new TimerUtil();
        this.moveTimer = new TimerUtil();
        this.moveDone = false;
        this.moved = false;
        this.moveBack = false;
        this.needToEscape = false;
        this.escapeDelay = new TimerUtil();
        this.escaped = false;
        this.addValues(new Value[] { (Value)this.plist, (Value)this.unGrab, (Value)this.lockRod, (Value)this.lockView, (Value)this.dead, (Value)this.admin, (Value)this.waterCheck, (Value)this.escape, (Value)this.escapeRange, (Value)this.autoThrow, (Value)this.showDebug, (Value)this.packetDebug, (Value)this.rotationMode, (Value)this.rotation, (Value)this.moveMode, (Value)this.move, (Value)this.holdShift, (Value)this.randomDelay, (Value)this.angle, (Value)this.tickTimerVale, (Value)this.soundBB, (Value)this.soundRadius, (Value)this.angleDiff, (Value)this.angleSize, (Value)this.kill, (Value)this.rckill, (Value)this.rccd, (Value)this.killSize, (Value)this.killRange, (Value)this.squid, (Value)this.guard, (Value)this.skeleton, (Value)this.zombie, (Value)this.witch, (Value)this.cat, (Value)this.silverfish, (Value)this.golem, (Value)this.rabbit, (Value)this.sheep, (Value)this.endermite, (Value)this.blaze, (Value)this.pigman, (Value)this.horse, (Value)this.player });
        this.setColor(new Color(191, 191, 191).getRGB());
        this.setModInfo("Just Auto Fish.");
    }
    
    public void onEnable() {
        if (this.unGrab.getValue()) {
            Client.ungrabMouse();
        }
        final PlayerList pl = (PlayerList)Client.instance.getModuleManager().getModuleByClass(PlayerList.class);
        if (!pl.isEnabled() && (boolean)this.plist.getValue()) {
            pl.setEnabled(true);
        }
        if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.entityHit == null) {
            this.lockedVec = this.mc.objectMouseOver.hitVec;
        }
        super.onEnable();
    }
    
    public void onDisable() {
        if (this.holdShift.getValue()) {
            KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindSneak.getKeyCode(), false);
        }
        if (this.unGrab.getValue()) {
            Client.regrabMouse();
        }
        final PlayerList pl = (PlayerList)Client.instance.getModuleManager().getModuleByClass(PlayerList.class);
        if (pl.isEnabled() && (boolean)this.plist.getValue()) {
            pl.setEnabled(false);
        }
        this.playerCaused = null;
        this.needToEscape = false;
        this.lockedVec = new Vec3(0.0, 0.0, 0.0);
        this.reThrowTimer.reset();
        this.moveDone = false;
        this.escaped = false;
        this.tickTimer = 0;
        this.tickTimer1 = 0;
        this.dickTimer = 0;
        this.soundVec = null;
        this.currentStage = stage.NONE;
        this.backRotaion = false;
        this.soundReady = false;
        this.soundCDReady = false;
        this.motionReady = false;
        this.extraDelay = 0;
        this.delaySet = false;
        this.currentSC = null;
        this.yawRecorded = false;
        this.pitchRecorded = false;
        this.lastRotationYaw = 0.0f;
        this.lastRotationPitch = 0.0f;
        this.shouldSwitchToWeapon = false;
        this.shouldSwitchToRod = false;
        this.yawRestored = true;
        this.pitchRestored = true;
        this.moveBack = false;
        this.switchedToRod = false;
        this.reachedSize = false;
        this.attackTimer.reset();
        this.rightClickTimer.reset();
        this.escapeDelay.reset();
        this.escaped = false;
        super.onDisable();
    }
    
    private boolean shouldAttack() {
        return this.attackTimer.hasReached(1000.0 / (8.0 + MathUtil.randomDouble(-1.0, 1.0)));
    }
    
    @EventHandler
    private void onPlayerDetected(final EventTick event) {
        if (this.playerInRange() != null) {
            this.playerCaused = this.playerInRange();
            this.needToEscape = true;
        }
        if ((boolean)this.escape.getValue() && !this.escaped) {
            if (this.needToEscape && this.escapeDelay.hasReached(3000.0)) {
                WindowsNotification.show("MelodySky - AutoFish", "Escaped. Player Name: " + this.playerCaused.getName() + ".");
                Helper.sendMessage("[AutoFish] Player Detected, Warping to Main Lobby.");
                Helper.sendMessage("[AutoFish] Player Name: " + this.playerCaused.getName() + ".");
                this.mc.thePlayer.sendChatMessage("/l");
                this.escaped = true;
                this.setEnabled(false);
                this.escapeDelay.reset();
            }
            if (!this.needToEscape) {
                this.escapeDelay.reset();
            }
        }
        if ((boolean)this.admin.getValue() && PlayerListUtils.tabContains("[ADMIN]") && this.allSCNear.isEmpty()) {
            Helper.sendMessage("[AutoFish] Admin Detected, Warping to Private Island.");
            this.mc.thePlayer.sendChatMessage("/l");
        }
    }
    
    @SubscribeEvent(receiveCanceled = true)
    public void onChat(final ClientChatReceivedEvent event) {
        final String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if (message.startsWith("From [ADMIN]") || message.startsWith("From [GM]") || message.startsWith("From [YOUTUBE]")) {
            Helper.sendMessage("[AutoFish] Admin Detected, Quitting Server.");
            final boolean flag = this.mc.isIntegratedServerRunning();
            this.mc.theWorld.sendQuittingDisconnectingPacket();
            this.mc.loadWorld((WorldClient)null);
            if (flag) {
                this.mc.displayGuiScreen((GuiScreen)new GuiMainMenu());
            }
            else {
                this.mc.displayGuiScreen((GuiScreen)new GuiMultiplayer((GuiScreen)new GuiMainMenu()));
            }
        }
    }
    
    @EventHandler
    private void onDead(final EventTick event) {
        if (!(boolean)this.dead.getValue()) {
            return;
        }
        if (!this.mc.thePlayer.isEntityAlive() || this.mc.thePlayer.isDead) {
            Helper.sendMessage("[AutoFish] Detected mc.thePlayer.isDead, Disabled AutoFish.");
            this.setEnabled(false);
            return;
        }
        if (this.mc.thePlayer.ticksExisted <= 1) {
            Helper.sendMessage("[AutoFish] Detected mc.thePlayer.tickExisted <= 1, Disabled AutoFish.");
            this.setEnabled(false);
            return;
        }
        if (this.mc.thePlayer.getHealth() == 0.0f) {
            Helper.sendMessage("[AutoFish] Detected mc.thePlayer.getHealth() == 0, Disabled AutoFish.");
            this.setEnabled(false);
        }
    }
    
    @EventHandler
    private void onReThrow(final EventTick event) {
        if (!(boolean)this.waterCheck.getValue()) {
            return;
        }
        if (this.reachedSize || !this.pitchRestored || !this.yawRestored) {
            return;
        }
        if (this.mc.thePlayer.getHeldItem() != null && this.mc.thePlayer.getHeldItem().getItem() instanceof ItemFishingRod) {
            if (this.mc.thePlayer.fishEntity != null) {
                if (!this.mc.thePlayer.fishEntity.isInWater() && !this.mc.thePlayer.fishEntity.isInLava() && this.reThrowTimer.hasReached(10000.0)) {
                    Client.rightClick();
                    this.currentStage = stage.NONE;
                    this.reThrowTimer.reset();
                }
            }
            else {
                this.reThrowTimer.reset();
            }
        }
    }
    
    @EventHandler
    private void onLockRod(final EventTick event) {
        if (!(boolean)this.lockRod.getValue()) {
            return;
        }
        if (this.reachedSize || !this.pitchRestored || !this.yawRestored) {
            return;
        }
        for (int i = 0; i < 9; ++i) {
            final ItemStack itemStack = this.mc.thePlayer.inventory.mainInventory[i];
            if (itemStack != null && itemStack.getItem() != null && itemStack.getItem() instanceof ItemFishingRod && this.yawRestored && this.pitchRestored) {
                this.mc.thePlayer.inventory.currentItem = i;
                break;
            }
        }
    }
    
    @EventHandler
    private void lockView(final EventRender2D event) {
        if (!(boolean)this.lockView.getValue()) {
            return;
        }
        if (this.reachedSize || !this.pitchRestored || !this.yawRestored) {
            return;
        }
        if (this.currentStage != stage.NONE) {
            final Rotation r = this.vec3ToRotation(this.lockedVec);
            this.mc.thePlayer.rotationYaw = this.smoothRotation(this.mc.thePlayer.rotationYaw, r.yaw, 30.0f);
            this.mc.thePlayer.rotationPitch = this.smoothRotation(this.mc.thePlayer.rotationPitch, r.pitch, 30.0f);
        }
    }
    
    @EventHandler
    private void onKillSC(final EventPreUpdate event) {
        if (!(boolean)this.kill.getValue()) {
            return;
        }
        this.loadSCs();
        if (!this.needToEscape) {
            if (((Double)this.killSize.getValue()).intValue() > 1) {
                if (this.allSCNear.size() >= ((Double)this.killSize.getValue()).intValue()) {
                    this.reachedSize = true;
                }
            }
            else {
                this.reachedSize = true;
            }
        }
        else {
            this.reachedSize = true;
        }
        if (this.currentSC == null && !this.allSCNear.isEmpty()) {
            this.currentSC = (EntityLivingBase)this.allSCNear.get(0);
        }
        if (this.currentSC != null && this.shouldSwitchToWeapon && this.reachedSize) {
            this.mc.thePlayer.inventory.currentItem = 0;
            this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C09PacketHeldItemChange(0));
            this.rightClickTimer.reset();
            this.attackTimer.reset();
            this.shouldSwitchToWeapon = false;
        }
        final float rotationSpeed = ((Double)this.angleSize.getValue()).floatValue() * 3.0f;
        if (this.currentSC != null && this.shouldAttack() && this.reachedSize) {
            if (this.mc.thePlayer.inventory.currentItem != 0) {
                this.shouldSwitchToWeapon = true;
            }
            else if (this.mc.thePlayer.inventory.currentItem == 0) {
                this.switchedToRod = false;
                if (!(boolean)this.rckill.getValue()) {
                    this.attack(this.currentSC);
                }
                if (this.rckill.getValue()) {
                    if (this.rightClickTimer.hasReached((double)((Double)this.rccd.getValue()).longValue()) && this.mc.thePlayer.inventory.currentItem == 0) {
                        Client.rightClick();
                        this.rightClickTimer.reset();
                    }
                    if (!this.yawRecorded) {
                        this.lastRotationYaw = this.mc.thePlayer.rotationYaw;
                        this.yawRestored = false;
                        this.yawRecorded = true;
                    }
                    if (!this.pitchRecorded) {
                        this.lastRotationPitch = this.mc.thePlayer.rotationPitch;
                        this.pitchRestored = false;
                        this.pitchRecorded = true;
                    }
                    if (this.yawRecorded && this.pitchRecorded) {
                        final float targetYaw = RotationUtil.getRotationToEntity((Entity)this.currentSC)[0];
                        final float targetPitch = RotationUtil.getRotationToEntity((Entity)this.currentSC)[1];
                        this.mc.thePlayer.rotationYaw = this.smoothRotation(this.mc.thePlayer.rotationYaw, targetYaw, rotationSpeed);
                        this.mc.thePlayer.rotationPitch = this.smoothRotation(this.mc.thePlayer.rotationPitch, targetPitch, rotationSpeed);
                    }
                }
            }
        }
        final float resetDifference = ((Double)this.angleDiff.getValue()).floatValue() * 10.0f;
        if (this.currentSC == null && this.allSCNear.isEmpty()) {
            if (this.yawRecorded) {
                this.yawDiff = Math.abs(this.mc.thePlayer.rotationYaw - this.lastRotationYaw);
                if (Math.abs(this.mc.thePlayer.rotationYaw - this.lastRotationYaw) > 360.0f - resetDifference) {
                    this.mc.thePlayer.rotationYaw = this.lastRotationYaw;
                    this.mc.thePlayer.rotationPitch = this.lastRotationPitch;
                    this.yawRestored = true;
                    this.yawRecorded = false;
                }
                if (Math.abs(this.mc.thePlayer.rotationYaw - this.lastRotationYaw) > resetDifference) {
                    final float targetYaw2 = this.lastRotationYaw;
                    this.mc.thePlayer.rotationYaw = this.smoothRotation(this.mc.thePlayer.rotationYaw, targetYaw2, rotationSpeed);
                }
                else {
                    this.mc.thePlayer.rotationYaw = this.lastRotationYaw;
                    this.yawRestored = true;
                    this.yawRecorded = false;
                }
            }
            if (this.pitchRecorded) {
                this.pitchDiff = Math.abs(this.mc.thePlayer.rotationPitch - this.lastRotationPitch);
                if (Math.abs(this.mc.thePlayer.rotationPitch - this.lastRotationPitch) > resetDifference) {
                    final float targetPitch = this.lastRotationPitch;
                    this.mc.thePlayer.rotationPitch = this.smoothRotation(this.mc.thePlayer.rotationPitch, targetPitch, rotationSpeed);
                }
                else {
                    this.mc.thePlayer.rotationPitch = this.lastRotationPitch;
                    this.pitchRestored = true;
                    this.pitchRecorded = false;
                }
            }
            this.reachedSize = false;
        }
        if (this.currentSC != null && this.mc.thePlayer.getDistanceToEntity((Entity)this.currentSC) > (double)this.killRange.getValue()) {
            if (!this.switchedToRod) {
                this.shouldSwitchToRod = true;
            }
            this.currentSC = null;
        }
        if (this.allSCNear.isEmpty() && !this.switchedToRod) {
            this.shouldSwitchToRod = true;
        }
        if (this.currentSC != null && !this.currentSC.isEntityAlive()) {
            if (!this.switchedToRod) {
                this.shouldSwitchToRod = true;
            }
            this.currentSC = null;
        }
        if (this.currentSC == null && this.shouldSwitchToRod && !this.reachedSize) {
            for (int i = 0; i < 9; ++i) {
                final ItemStack itemStack = this.mc.thePlayer.inventory.mainInventory[i];
                if (itemStack != null && itemStack.getItem() != null && itemStack.getItem() instanceof ItemFishingRod && this.yawRestored && this.pitchRestored) {
                    this.dickTimer = 40;
                    this.lastRotationYaw = 0.0f;
                    this.lastRotationPitch = 0.0f;
                    this.mc.thePlayer.inventory.currentItem = i;
                    this.shouldSwitchToRod = false;
                    this.switchedToRod = true;
                    break;
                }
            }
        }
        if (this.currentSC != null && this.reachedSize) {
            this.reset();
            this.tickTimer = 0;
            this.tickTimer1 = 0;
            this.dickTimer = 0;
            this.soundVec = null;
        }
    }
    
    @EventHandler
    private void onESPSC(final EventRender3D event) {
        if (!(boolean)this.soundBB.getValue()) {
            return;
        }
        if (this.currentSC != null && this.reachedSize) {
            RenderUtil.entityOutlineAXIS((Entity)this.currentSC, Colors.AQUA.c, event);
        }
    }
    
    @EventHandler
    private void onDebugDraw(final EventRender2D event) {
        if (!(boolean)this.showDebug.getValue()) {
            return;
        }
        final ScaledResolution scale = new ScaledResolution(this.mc);
        this.mc.fontRendererObj.drawString("Current Stage: " + this.currentStage, scale.getScaledWidth() / 2 + 6, scale.getScaledHeight() / 2 + 6, -1);
        this.mc.fontRendererObj.drawString("TickTimer: " + this.tickTimer, scale.getScaledWidth() / 2 + 6, scale.getScaledHeight() / 2 + 18, -1);
        this.mc.fontRendererObj.drawString("SoundCDTimer: " + this.tickTimer1 + ((this.tickTimer1 == 50) ? " (Ready)" : ""), scale.getScaledWidth() / 2 + 6, scale.getScaledHeight() / 2 + 30, -1);
        this.mc.fontRendererObj.drawString("AutoThrowTimer: " + this.dickTimer, scale.getScaledWidth() / 2 + 6, scale.getScaledHeight() / 2 + 42, -1);
        this.mc.fontRendererObj.drawString("SoundMonitor: " + this.soundReady, scale.getScaledWidth() / 2 + 6, scale.getScaledHeight() / 2 + 54, -1);
        this.mc.fontRendererObj.drawString("SoundReady: " + this.soundCDReady, scale.getScaledWidth() / 2 + 6, scale.getScaledHeight() / 2 + 66, -1);
        this.mc.fontRendererObj.drawString("MotionReady: " + this.motionReady, scale.getScaledWidth() / 2 + 6, scale.getScaledHeight() / 2 + 78, -1);
        if (this.randomDelay.getValue()) {
            this.mc.fontRendererObj.drawString("ExtraDelay: " + this.extraDelay, scale.getScaledWidth() / 2 + 6, scale.getScaledHeight() / 2 + 90, -1);
        }
        this.mc.fontRendererObj.drawString("YawReady: " + this.yawRestored, scale.getScaledWidth() / 2 + 6, scale.getScaledHeight() / 2 + 102, -1);
        this.mc.fontRendererObj.drawString("YawDiff: " + this.yawDiff, scale.getScaledWidth() / 2 + 6, scale.getScaledHeight() / 2 + 114, -1);
        this.mc.fontRendererObj.drawString("PitchReady: " + this.pitchRestored, scale.getScaledWidth() / 2 + 6, scale.getScaledHeight() / 2 + 126, -1);
        this.mc.fontRendererObj.drawString("PitchDiff: " + this.pitchDiff, scale.getScaledWidth() / 2 + 6, scale.getScaledHeight() / 2 + 138, -1);
    }
    
    @EventHandler
    private void onSneak(final EventTick event) {
        if (this.holdShift.getValue()) {
            KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindSneak.getKeyCode(), true);
        }
    }
    
    @EventHandler
    private void onTick(final EventTick event) {
        if (this.mc.thePlayer.fishEntity != null && this.currentStage != stage.FINISH) {
            this.currentStage = stage.WAITING;
        }
        if (this.mc.thePlayer.getHeldItem() == null || !(this.mc.thePlayer.getHeldItem().getItem() instanceof ItemFishingRod)) {
            this.currentStage = stage.NONE;
        }
        if (this.currentStage == stage.NONE) {
            this.reset();
            this.tickTimer = 0;
            this.tickTimer1 = 0;
            this.soundVec = null;
        }
        if (this.currentStage == stage.WAITING && this.mc.thePlayer.fishEntity == null) {
            this.currentStage = stage.NONE;
        }
        if (this.mc.thePlayer.getHeldItem() != null && (boolean)this.autoThrow.getValue() && this.currentStage == stage.NONE && this.mc.thePlayer.getHeldItem().getItem() instanceof ItemFishingRod) {
            if (this.dickTimer < 20) {
                ++this.dickTimer;
                return;
            }
            if (this.rotation.getValue()) {
                if (this.backRotaion) {
                    if (this.rotationMode.getValue() == rotations.Yaw) {
                        final EntityPlayerSP thePlayer = this.mc.thePlayer;
                        thePlayer.rotationYaw -= ((Double)this.angle.getValue()).floatValue();
                    }
                    else {
                        final EntityPlayerSP thePlayer2 = this.mc.thePlayer;
                        thePlayer2.rotationPitch -= ((Double)this.angle.getValue()).floatValue();
                    }
                    this.backRotaion = !this.backRotaion;
                }
                else {
                    if (this.rotationMode.getValue() == rotations.Yaw) {
                        final EntityPlayerSP thePlayer3 = this.mc.thePlayer;
                        thePlayer3.rotationYaw += ((Double)this.angle.getValue()).floatValue();
                    }
                    else {
                        final EntityPlayerSP thePlayer4 = this.mc.thePlayer;
                        thePlayer4.rotationPitch += ((Double)this.angle.getValue()).floatValue();
                    }
                    this.backRotaion = !this.backRotaion;
                }
            }
            Client.rightClick();
        }
        this.dickTimer = 0;
    }
    
    @EventHandler
    public void onPacket(final EventPacketRecieve e) {
        if (this.currentStage != stage.WAITING) {
            return;
        }
        if (this.mc.thePlayer.fishEntity == null) {
            return;
        }
        final Packet<?> packet = (Packet<?>)e.getPacket();
        if (packet instanceof S29PacketSoundEffect) {
            final S29PacketSoundEffect sound = (S29PacketSoundEffect)packet;
            if (this.packetDebug.getValue()) {
                Helper.sendMessage("Current Sound: " + sound.getSoundName());
            }
            if (sound.getSoundName().contains("game.player.swim.splash") || sound.getSoundName().contains("random.splash")) {
                final float radius = ((Double)this.soundRadius.getValue()).floatValue();
                if (Math.abs(sound.getX() - this.mc.thePlayer.fishEntity.posX) <= radius && Math.abs(sound.getZ() - this.mc.thePlayer.fishEntity.posZ) <= radius) {
                    this.soundReady = true;
                    this.soundVec = new Vec3(sound.getX(), sound.getY(), sound.getZ());
                }
            }
        }
        if (packet instanceof S12PacketEntityVelocity) {
            final S12PacketEntityVelocity velocity = (S12PacketEntityVelocity)packet;
            if (velocity.getMotionX() == 0 && velocity.getMotionY() != 0 && velocity.getMotionZ() == 0) {
                this.motionReady = true;
            }
        }
    }
    
    @EventHandler
    private void onMove(final EventTick event) {
        if (this.move.getValue()) {
            final int firstStep = (this.moveMode.getValue() == moves.AD) ? this.mc.gameSettings.keyBindLeft.getKeyCode() : this.mc.gameSettings.keyBindForward.getKeyCode();
            final int secondStep = (this.moveMode.getValue() == moves.AD) ? this.mc.gameSettings.keyBindRight.getKeyCode() : this.mc.gameSettings.keyBindBack.getKeyCode();
            if (!this.moveDone) {
                if (this.currentStage == stage.FINISH && !this.moved) {
                    this.moveTimer.reset();
                    KeyBinding.setKeyBindState(firstStep, true);
                    this.moved = true;
                }
                if (this.moved && this.moveTimer.hasReached(50.0)) {
                    KeyBinding.setKeyBindState(firstStep, false);
                    if (this.moveTimer.hasReached(100.0)) {
                        KeyBinding.setKeyBindState(secondStep, true);
                        if (this.moveTimer.hasReached(150.0)) {
                            KeyBinding.setKeyBindState(secondStep, false);
                            this.moveDone = true;
                        }
                    }
                }
            }
            else if (this.currentStage != stage.FINISH) {
                this.moved = false;
                this.moveTimer.reset();
                this.moveDone = false;
                KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindLeft.getKeyCode(), false);
                KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindRight.getKeyCode(), false);
            }
        }
    }
    
    @EventHandler
    private void onReady(final EventTick event) {
        if (this.soundCDReady && this.motionReady && this.currentStage != stage.FINISH) {
            this.currentStage = stage.FINISH;
            Client.rightClick();
            this.reset();
        }
    }
    
    @EventHandler
    private void onCDReady(final EventTick event) {
        if (this.currentStage == stage.WAITING) {
            if (this.tickTimer1 < 55) {
                this.soundCDReady = false;
                this.soundReady = false;
                ++this.tickTimer1;
            }
            else {
                this.soundCDReady = this.soundReady;
            }
        }
    }
    
    @EventHandler
    private void onThrow(final EventTick event) {
        if (this.currentStage == stage.FINISH) {
            if (!this.delaySet) {
                this.extraDelay = (this.randomDelay.getValue() ? Math.abs((int)(Math.random() * 50.0)) : 0);
                this.delaySet = true;
            }
            if (this.tickTimer < ((Double)this.tickTimerVale.getValue()).intValue() + this.extraDelay) {
                ++this.tickTimer;
                return;
            }
            Client.rightClick();
            if (this.rotation.getValue()) {
                if (this.backRotaion) {
                    if (this.rotationMode.getValue() == rotations.Yaw) {
                        final EntityPlayerSP thePlayer = this.mc.thePlayer;
                        thePlayer.rotationYaw -= ((Double)this.angle.getValue()).floatValue();
                    }
                    else {
                        final EntityPlayerSP thePlayer2 = this.mc.thePlayer;
                        thePlayer2.rotationPitch -= ((Double)this.angle.getValue()).floatValue();
                    }
                    this.backRotaion = !this.backRotaion;
                }
                else {
                    if (this.rotationMode.getValue() == rotations.Yaw) {
                        final EntityPlayerSP thePlayer3 = this.mc.thePlayer;
                        thePlayer3.rotationYaw += ((Double)this.angle.getValue()).floatValue();
                    }
                    else {
                        final EntityPlayerSP thePlayer4 = this.mc.thePlayer;
                        thePlayer4.rotationPitch += ((Double)this.angle.getValue()).floatValue();
                    }
                    this.backRotaion = !this.backRotaion;
                }
            }
            this.currentStage = stage.WAITING;
            this.tickTimer1 = 0;
            this.tickTimer = 0;
            this.delaySet = false;
        }
    }
    
    @EventHandler
    private void onR3D(final EventRender3D event) {
        if (!(boolean)this.soundBB.getValue()) {
            return;
        }
        if (this.mc.thePlayer.fishEntity != null) {
            final AxisAlignedBB fishingEntityBB = this.mc.thePlayer.fishEntity.getEntityBoundingBox().expand((double)this.soundRadius.getValue(), 0.0, (double)this.soundRadius.getValue());
            RenderUtil.drawOutlinedBoundingBox(fishingEntityBB, Colors.AQUA.c, 2.0f, event.getPartialTicks());
        }
        if (this.soundVec != null) {
            final AxisAlignedBB soundVecBB = new AxisAlignedBB(this.soundVec.xCoord + 0.05, this.soundVec.yCoord + 0.05, this.soundVec.zCoord + 0.05, this.soundVec.xCoord - 0.05, this.soundVec.yCoord - 0.05, this.soundVec.zCoord - 0.05);
            RenderUtil.drawOutlinedBoundingBox(soundVecBB, Colors.RED.c, 2.0f, event.getPartialTicks());
        }
    }
    
    @SubscribeEvent
    public void clear(final WorldEvent.Load event) {
        Helper.sendMessage("[MacroProtection] Auto Disabled " + EnumChatFormatting.GREEN + this.getName() + EnumChatFormatting.GRAY + " due to World Change.");
        this.setEnabled(false);
    }
    
    private void reset() {
        this.soundReady = false;
        this.soundCDReady = false;
        this.motionReady = false;
    }
    
    private void loadSCs() {
        this.allSCNear.clear();
        (this.allSCNear = this.getTargets((Double)this.killRange.getValue())).sort(Comparator.comparingDouble(e -> this.mc.thePlayer.getDistanceSqToEntity(e)));
    }
    
    public List<Entity> getTargets(final Double value) {
        return (List<Entity>)this.mc.theWorld.loadedEntityList.stream().filter(e -> this.isSC(e) && this.mc.thePlayer.getDistanceToEntity(e) < value).collect(Collectors.toList());
    }
    
    private void attack(final EntityLivingBase entity) {
        this.mc.thePlayer.swingItem();
        this.mc.thePlayer.sendQueue.addToSendQueue((Packet)new C02PacketUseEntity((Entity)entity, C02PacketUseEntity.Action.ATTACK));
        this.attackTimer.reset();
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
    
    private EntityPlayer playerInRange() {
        if (!(boolean)this.escape.getValue()) {
            return null;
        }
        for (final EntityPlayer player : this.mc.theWorld.playerEntities) {
            if (((AntiBot)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)AntiBot.class)).isEntityBot((Entity)player)) {
                continue;
            }
            if (FriendManager.isFriend(player.getName())) {
                continue;
            }
            if (this.mc.thePlayer.getDistanceToEntity((Entity)player) < ((Double)this.escapeRange.getValue()).intValue() && player != this.mc.thePlayer) {
                return player;
            }
        }
        return null;
    }
    
    public Rotation vec3ToRotation(final Vec3 vec) {
        final double diffX = vec.xCoord - this.mc.thePlayer.posX;
        final double diffY = vec.yCoord - this.mc.thePlayer.posY - this.mc.thePlayer.getEyeHeight();
        final double diffZ = vec.zCoord - this.mc.thePlayer.posZ;
        final double dist = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float pitch = (float)(-Math.atan2(dist, diffY));
        float yaw = (float)Math.atan2(diffZ, diffX);
        pitch = (float)wrapAngleTo180((pitch * 180.0f / 3.141592653589793 + 90.0) * -1.0);
        yaw = (float)wrapAngleTo180(yaw * 180.0f / 3.141592653589793 - 90.0);
        return new Rotation(pitch, yaw);
    }
    
    private static double wrapAngleTo180(final double angle) {
        return angle - Math.floor(angle / 360.0 + 0.5) * 360.0;
    }
    
    private boolean isSC(final Entity e) {
        return e != this.mc.thePlayer && this.mc.thePlayer.getDistanceToEntity(e) <= (double)this.killRange.getValue() && !e.isDead && e.isEntityAlive() && (e.getDisplayName() == null || !PlayerListUtils.tabContains(e.getName())) && ((e instanceof EntitySquid && (boolean)this.squid.getValue()) || (e instanceof EntityGuardian && (boolean)this.guard.getValue()) || (e instanceof EntityZombie && (boolean)this.zombie.getValue()) || (e instanceof EntitySkeleton && (boolean)this.skeleton.getValue()) || (e instanceof EntityWitch && (boolean)this.witch.getValue()) || (e instanceof EntityOcelot && (boolean)this.cat.getValue()) || (e instanceof EntitySilverfish && (boolean)this.silverfish.getValue()) || (e instanceof EntityGolem && (boolean)this.golem.getValue()) || (e instanceof EntityRabbit && (boolean)this.rabbit.getValue()) || (e instanceof EntitySheep && (boolean)this.sheep.getValue()) || (e instanceof EntityEndermite && (boolean)this.endermite.getValue()) || (e instanceof EntityBlaze && (boolean)this.blaze.getValue()) || (e instanceof EntityPigZombie && (boolean)this.pigman.getValue()) || (e instanceof EntityPlayer && (boolean)this.player.getValue()) || (e instanceof EntityHorse && (boolean)this.horse.getValue()));
    }
    
    enum rotations
    {
        Yaw, 
        Pitch;
    }
    
    enum moves
    {
        WS, 
        AD;
    }
    
    enum stage
    {
        NONE, 
        WAITING, 
        FINISH;
    }
    
    private static class Rotation
    {
        public float pitch;
        public float yaw;
        
        public Rotation(final float pitch, final float yaw) {
            this.pitch = pitch;
            this.yaw = yaw;
        }
    }
}
