//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.Fishing;

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
import net.minecraft.client.gui.*;
import net.minecraft.client.entity.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.network.play.server.*;
import net.minecraft.network.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.Utils.*;
import xyz.Melody.Utils.render.*;
import net.minecraftforge.event.world.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import xyz.Melody.module.balance.*;
import net.minecraft.entity.*;
import xyz.Melody.System.Managers.Client.*;
import java.util.*;
import net.minecraft.util.*;

public class SlugFishing extends Module
{
    private int tickTimer1;
    private Vec3 soundVec;
    private Numbers<Double> emberSlot;
    private Numbers<Double> trophySlot;
    private Numbers<Double> emberDelay;
    private Option<Boolean> plist;
    private Option<Boolean> unGrab;
    private Option<Boolean> lockRod;
    private Option<Boolean> admin;
    private Option<Boolean> lockView;
    private Option<Boolean> dead;
    private Option<Boolean> escape;
    private Numbers<Double> escapeRange;
    private Option<Boolean> showDebug;
    private Mode<Enum> rotationMode;
    private Option<Boolean> rotation;
    private Mode<Enum> moveMode;
    private Option<Boolean> move;
    private Option<Boolean> holdShift;
    private Numbers<Double> angle;
    private Option<Boolean> soundBB;
    private Numbers<Double> soundRadius;
    private Enum<?> currentStage;
    private boolean backRotaion;
    private boolean soundReady;
    private boolean soundCDReady;
    private boolean motionReady;
    private Vec3 lockedVec;
    private TimerUtil moveTimer;
    private boolean moveDone;
    private boolean moved;
    private boolean needToEscape;
    private TimerUtil escapeDelay;
    private boolean swapedToEmberArmor;
    private boolean swapedToTrophyArmor;
    private TimerUtil secondTimer;
    private TimerUtil finishedTimer;
    private TimerUtil throwFaileTimer;
    private TimerUtil wdFaileTimer;
    private TimerUtil emberTimer;
    private TimerUtil timerTimer;
    private int page;
    private int slot;
    private boolean shouldOpenWardrobe;
    private boolean escaped;
    
    public SlugFishing() {
        super("SlugFishing", new String[] { "slug" }, ModuleType.Fishing);
        this.tickTimer1 = 0;
        this.soundVec = null;
        this.emberSlot = (Numbers<Double>)new Numbers("Ember Slot", (Number)5.0, (Number)0.0, (Number)20.0, (Number)1.0);
        this.trophySlot = (Numbers<Double>)new Numbers("Trophy Slot", (Number)5.0, (Number)0.0, (Number)20.0, (Number)1.0);
        this.emberDelay = (Numbers<Double>)new Numbers("EmberSwapDelay", (Number)750.0, (Number)250.0, (Number)1500.0, (Number)10.0);
        this.plist = (Option<Boolean>)new Option("EnablePlayerList", (Object)true);
        this.unGrab = (Option<Boolean>)new Option("UnGrabMouse", (Object)false);
        this.lockRod = (Option<Boolean>)new Option("LockRod", (Object)false);
        this.admin = (Option<Boolean>)new Option("AntiAdmin", (Object)false);
        this.lockView = (Option<Boolean>)new Option("LockView", (Object)false);
        this.dead = (Option<Boolean>)new Option("DeathCheck", (Object)true);
        this.escape = (Option<Boolean>)new Option("Escape", (Object)false);
        this.escapeRange = (Numbers<Double>)new Numbers("Escape Range", (Number)5.0, (Number)0.0, (Number)20.0, (Number)1.0);
        this.showDebug = (Option<Boolean>)new Option("Show Debug", (Object)false);
        this.rotationMode = (Mode<Enum>)new Mode("RotationMode", (Enum[])rotations.values(), (Enum)rotations.Yaw);
        this.rotation = (Option<Boolean>)new Option("NoRotationAFK", (Object)true);
        this.moveMode = (Mode<Enum>)new Mode("MoveMode", (Enum[])moves.values(), (Enum)moves.AD);
        this.move = (Option<Boolean>)new Option("NoMovingAFK", (Object)true);
        this.holdShift = (Option<Boolean>)new Option("Sneaking", (Object)false);
        this.angle = (Numbers<Double>)new Numbers("RotationAngle", (Number)1.0, (Number)1.0, (Number)5.0, (Number)1.0);
        this.soundBB = (Option<Boolean>)new Option("SoundBox", (Object)false);
        this.soundRadius = (Numbers<Double>)new Numbers("SoundRadius", (Number)0.5, (Number)0.1, (Number)5.0, (Number)0.1);
        this.currentStage = stage.NONE;
        this.backRotaion = false;
        this.soundReady = false;
        this.soundCDReady = false;
        this.motionReady = false;
        this.lockedVec = new Vec3(0.0, 0.0, 0.0);
        this.moveTimer = new TimerUtil();
        this.moveDone = false;
        this.moved = false;
        this.needToEscape = false;
        this.escapeDelay = new TimerUtil();
        this.swapedToEmberArmor = false;
        this.swapedToTrophyArmor = false;
        this.secondTimer = new TimerUtil();
        this.finishedTimer = new TimerUtil();
        this.throwFaileTimer = new TimerUtil();
        this.wdFaileTimer = new TimerUtil();
        this.emberTimer = new TimerUtil();
        this.timerTimer = new TimerUtil();
        this.page = 0;
        this.slot = 0;
        this.shouldOpenWardrobe = false;
        this.escaped = false;
        this.addValues(new Value[] { (Value)this.emberSlot, (Value)this.trophySlot, (Value)this.emberDelay, (Value)this.plist, (Value)this.unGrab, (Value)this.lockRod, (Value)this.lockView, (Value)this.dead, (Value)this.admin, (Value)this.escape, (Value)this.escapeRange, (Value)this.showDebug, (Value)this.rotationMode, (Value)this.rotation, (Value)this.moveMode, (Value)this.move, (Value)this.holdShift, (Value)this.angle, (Value)this.soundBB, (Value)this.soundRadius });
        this.setColor(new Color(191, 191, 191).getRGB());
        this.setModInfo("Auto Swap Ember/Trophy Armor and Fishing.");
    }
    
    public void onEnable() {
        if (this.unGrab.getValue()) {
            Client.ungrabMouse();
        }
        if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.entityHit == null) {
            this.lockedVec = this.mc.objectMouseOver.hitVec;
        }
        final PlayerList pl = (PlayerList)Client.instance.getModuleManager().getModuleByClass(PlayerList.class);
        if (!pl.isEnabled() && (boolean)this.plist.getValue()) {
            pl.setEnabled(true);
        }
        this.emberTimer.reset();
        this.timerTimer.reset();
        this.throwFaileTimer.reset();
        this.finishedTimer.reset();
        this.secondTimer.reset();
        this.wdFaileTimer.reset();
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
        this.page = 0;
        this.slot = 0;
        this.shouldOpenWardrobe = false;
        this.emberTimer.reset();
        this.timerTimer.reset();
        this.throwFaileTimer.reset();
        this.finishedTimer.reset();
        this.secondTimer.reset();
        this.wdFaileTimer.reset();
        this.needToEscape = false;
        this.lockedVec = new Vec3(0.0, 0.0, 0.0);
        this.moveDone = false;
        this.escaped = false;
        this.tickTimer1 = 0;
        this.soundVec = null;
        this.currentStage = stage.NONE;
        this.backRotaion = false;
        this.soundReady = false;
        this.soundCDReady = false;
        this.motionReady = false;
        this.escapeDelay.reset();
        this.escaped = false;
        super.onDisable();
    }
    
    @EventHandler
    private void onPlayerDetected(final EventTick event) {
        if (this.playerInRange()) {
            this.needToEscape = true;
        }
        if ((boolean)this.escape.getValue() && !this.escaped) {
            if (this.needToEscape && this.escapeDelay.hasReached(5000.0)) {
                Helper.sendMessage("[AutoFish] Player Detected, Warping to Private Island.");
                this.mc.thePlayer.sendChatMessage("/l");
                this.escaped = true;
                this.setEnabled(false);
                this.escapeDelay.reset();
            }
            if (!this.needToEscape) {
                this.escapeDelay.reset();
            }
        }
        if ((boolean)this.admin.getValue() && PlayerListUtils.tabContains("[ADMIN]")) {
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
    private void onLockRod(final EventTick event) {
        if (!(boolean)this.lockRod.getValue()) {
            return;
        }
        for (int i = 0; i < 9; ++i) {
            final ItemStack itemStack = this.mc.thePlayer.inventory.mainInventory[i];
            if (itemStack != null && itemStack.getItem() != null && itemStack.getItem() instanceof ItemFishingRod) {
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
        if (this.currentStage != stage.NONE) {
            final Rotation r = this.vec3ToRotation(this.lockedVec);
            this.mc.thePlayer.rotationYaw = this.smoothRotation(this.mc.thePlayer.rotationYaw, r.yaw, 30.0f);
            this.mc.thePlayer.rotationPitch = this.smoothRotation(this.mc.thePlayer.rotationPitch, r.pitch, 30.0f);
        }
    }
    
    @EventHandler
    private void onDebugDraw(final EventRender2D event) {
        if (!(boolean)this.showDebug.getValue()) {
            return;
        }
        final ScaledResolution scale = new ScaledResolution(this.mc);
        this.mc.fontRendererObj.drawString("Current Stage: " + this.currentStage, scale.getScaledWidth() / 2 + 6, scale.getScaledHeight() / 2 + 6, -1);
        this.mc.fontRendererObj.drawString("Time: " + (this.secondTimer.getCurrentMS() - this.secondTimer.getLastMS()) / 1000L, scale.getScaledWidth() / 2 + 6, scale.getScaledHeight() / 2 + 18, -1);
        this.mc.fontRendererObj.drawString("SoundCDTimer: " + this.tickTimer1 + ((this.tickTimer1 == 50) ? " (Ready)" : ""), scale.getScaledWidth() / 2 + 6, scale.getScaledHeight() / 2 + 30, -1);
        this.mc.fontRendererObj.drawString("SoundMonitor: " + this.soundReady, scale.getScaledWidth() / 2 + 6, scale.getScaledHeight() / 2 + 54, -1);
        this.mc.fontRendererObj.drawString("SoundReady: " + this.soundCDReady, scale.getScaledWidth() / 2 + 6, scale.getScaledHeight() / 2 + 66, -1);
        this.mc.fontRendererObj.drawString("MotionReady: " + this.motionReady, scale.getScaledWidth() / 2 + 6, scale.getScaledHeight() / 2 + 78, -1);
    }
    
    @EventHandler
    private void onSneak(final EventTick event) {
        if (this.holdShift.getValue()) {
            KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindSneak.getKeyCode(), true);
        }
    }
    
    private void swapEmber(final int slot) {
        Helper.sendMessage("Swap to Ember Armor.");
        this.openWD(1, slot);
        this.swapedToEmberArmor = true;
    }
    
    private void swapTrophy(final int slot) {
        Helper.sendMessage("Swap to Trophy Hunter Armor.");
        this.openWD(1, slot);
        this.swapedToTrophyArmor = true;
    }
    
    @EventHandler
    private void onTick(final EventTick event) {
        if (this.currentStage == stage.NONE) {
            if (this.mc.thePlayer.getHeldItem() == null || !(this.mc.thePlayer.getHeldItem().getItem() instanceof ItemFishingRod)) {
                Helper.sendMessage("[Slug Fishing] Please Hold a Fishing rod to use This Feature.");
                this.setEnabled(false);
                return;
            }
            this.currentStage = stage.EMBER;
        }
        if (this.currentStage == stage.FINISH) {
            this.soundReady = false;
            this.soundCDReady = false;
            this.motionReady = false;
            this.tickTimer1 = 0;
            if (this.finishedTimer.hasReached(200.0)) {
                this.currentStage = stage.EMBER;
                this.finishedTimer.reset();
                this.emberTimer.reset();
            }
        }
        if (this.currentStage == stage.EMBER && this.emberTimer.hasReached(200.0)) {
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
            this.swapEmber(((Double)this.emberSlot.getValue()).intValue());
            if (this.swapedToEmberArmor) {
                this.currentStage = stage.TIMER;
            }
            this.emberTimer.reset();
            this.timerTimer.reset();
        }
        if (this.currentStage == stage.TIMER && this.timerTimer.hasReached((double)((Double)this.emberDelay.getValue()).longValue())) {
            if (this.swapedToEmberArmor) {
                if (this.mc.thePlayer.fishEntity == null) {
                    Client.rightClick();
                }
                this.swapedToEmberArmor = false;
                this.secondTimer.reset();
            }
            if (this.secondTimer.hasReached(25000.0)) {
                this.currentStage = stage.TROPHY;
                this.secondTimer.reset();
            }
            this.timerTimer.reset();
        }
        if (this.currentStage == stage.TROPHY) {
            this.swapTrophy(((Double)this.trophySlot.getValue()).intValue());
            if (this.swapedToTrophyArmor) {
                this.finishedTimer.reset();
                this.throwFaileTimer.reset();
                this.currentStage = stage.WAITING;
            }
        }
        if (this.currentStage == stage.WAITING) {
            this.swapedToTrophyArmor = false;
            if (this.soundCDReady && this.motionReady) {
                if (this.mc.thePlayer.fishEntity != null) {
                    Client.rightClick();
                }
                this.finishedTimer.reset();
                this.throwFaileTimer.reset();
                this.currentStage = stage.FINISH;
            }
        }
        if (this.mc.thePlayer.getHeldItem() == null || !(this.mc.thePlayer.getHeldItem().getItem() instanceof ItemFishingRod)) {
            this.currentStage = stage.NONE;
        }
        if (this.currentStage == stage.NONE) {
            this.reset();
            this.tickTimer1 = 0;
            this.soundVec = null;
        }
        if (this.currentStage == stage.WAITING && this.mc.thePlayer.fishEntity == null && this.throwFaileTimer.hasReached(500.0)) {
            this.currentStage = stage.NONE;
            this.throwFaileTimer.reset();
        }
    }
    
    @EventHandler
    public void onPacket(final EventPacketRecieve e) {
        if (this.currentStage != stage.WAITING) {
            return;
        }
        final Packet<?> packet = (Packet<?>)e.getPacket();
        if (packet instanceof S29PacketSoundEffect) {
            final S29PacketSoundEffect sound = (S29PacketSoundEffect)packet;
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
    }
    
    @EventHandler
    private void onCDReady(final EventTick event) {
        if (this.currentStage == stage.WAITING) {
            if (this.tickTimer1 < 50) {
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
    
    public void openWD(final int page, final int slot) {
        this.page = page;
        this.slot = slot;
        this.shouldOpenWardrobe = true;
        this.mc.thePlayer.sendChatMessage("/pets");
        this.wdFaileTimer.reset();
    }
    
    @EventHandler
    public void onDrawGuiBackground(final EventTick event) {
        final GuiScreen gui = this.mc.currentScreen;
        if (Client.inSkyblock && this.shouldOpenWardrobe && gui instanceof GuiChest) {
            final Container container = ((GuiChest)gui).inventorySlots;
            if (container instanceof ContainerChest) {
                final String chestName = this.getGuiName(gui);
                if (chestName.endsWith("Pets")) {
                    this.clickSlot(48, 0);
                    this.clickSlot(32, 1);
                    if (this.page == 1) {
                        if (this.slot > 0 && this.slot < 10) {
                            this.clickSlot(this.slot + 35, 2);
                            this.mc.thePlayer.closeScreen();
                        }
                    }
                    else if (this.page == 2) {
                        this.clickSlot(53, 2);
                        if (this.slot > 0 && this.slot < 10) {
                            this.clickSlot(this.slot + 35, 3);
                            this.mc.thePlayer.closeScreen();
                        }
                    }
                    this.shouldOpenWardrobe = false;
                }
            }
        }
    }
    
    public String getGuiName(final GuiScreen gui) {
        if (gui instanceof GuiChest) {
            return ((ContainerChest)((GuiChest)gui).inventorySlots).getLowerChestInventory().getDisplayName().getUnformattedText();
        }
        return "";
    }
    
    private void clickSlot(final int slot, final int incrementWindowId) {
        this.mc.playerController.windowClick(this.mc.thePlayer.openContainer.windowId + incrementWindowId, slot, 2, 3, (EntityPlayer)this.mc.thePlayer);
    }
    
    private boolean playerInRange() {
        if (!(boolean)this.escape.getValue()) {
            return false;
        }
        for (final EntityPlayer player : this.mc.theWorld.playerEntities) {
            if (((AntiBot)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)AntiBot.class)).isEntityBot((Entity)player)) {
                continue;
            }
            if (FriendManager.isFriend(player.getName())) {
                continue;
            }
            if (this.mc.thePlayer.getDistanceToEntity((Entity)player) < ((Double)this.escapeRange.getValue()).intValue() && player != this.mc.thePlayer) {
                return true;
            }
        }
        return false;
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
        EMBER, 
        TIMER, 
        TROPHY, 
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
