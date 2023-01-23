//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.macros;

import net.minecraft.entity.monster.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import net.minecraftforge.client.event.*;
import xyz.Melody.GUI.Notification.*;
import net.minecraftforge.fml.common.eventhandler.*;
import xyz.Melody.*;
import xyz.Melody.Utils.math.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.Utils.Item.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.client.gui.*;
import net.minecraft.inventory.*;
import net.minecraft.client.settings.*;
import net.minecraft.entity.player.*;
import xyz.Melody.module.balance.*;
import org.lwjgl.input.*;
import java.util.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.Utils.render.*;
import net.minecraft.util.*;
import org.lwjgl.opengl.*;
import xyz.Melody.Utils.*;
import net.minecraftforge.event.world.*;
import net.minecraft.client.renderer.*;
import xyz.Melody.Utils.render.gl.*;

public class AutoRuby extends Module
{
    private TimerUtil timer;
    private TimerUtil ewTimer;
    public ArrayList<BlockPos> wps;
    private Mode<Enum> mode;
    private Option<Boolean> killYogs;
    private Option<Boolean> rcKill;
    private Option<Boolean> aim;
    private Option<Boolean> faceDown;
    private Numbers<Double> yogRange;
    private Numbers<Double> weaponSlot;
    private Option<Boolean> routeHelper;
    private Option<Boolean> protect;
    private boolean etherWarped;
    private int curIndex;
    private BlockPos curBP;
    private BlockPos nextBP;
    public boolean started;
    private int lastSlot;
    private boolean restore;
    private boolean shouldSwitchToAbi;
    private boolean shouldCallSB;
    private boolean shouldClickFuel;
    private boolean shouldClickDrill;
    private boolean shouldCombind;
    private int drillSlot;
    private int fuelSlot;
    private ArrayList<EntityMagmaCube> yogs;
    private TimerUtil attackTimer;
    private boolean killingYogs;
    private int ticks;
    private TimerUtil fallSafeTimer;
    
    public AutoRuby() {
        super("AutoGemstone", new String[] { "" }, ModuleType.Macros);
        this.timer = new TimerUtil();
        this.ewTimer = new TimerUtil();
        this.wps = new ArrayList<BlockPos>();
        this.mode = (Mode<Enum>)new Mode("Mode", (Enum[])GemstoneNuker.Gemstone.values(), (Enum)GemstoneNuker.Gemstone.RUBY);
        this.killYogs = (Option<Boolean>)new Option("KillYogs", (Object)true);
        this.rcKill = (Option<Boolean>)new Option("RCKillYogs", (Object)false);
        this.aim = (Option<Boolean>)new Option("RCAimYogs", (Object)false);
        this.faceDown = (Option<Boolean>)new Option("RCFaceDown", (Object)false);
        this.yogRange = (Numbers<Double>)new Numbers("YogRange", (Number)3.5, (Number)1.0, (Number)4.5, (Number)0.5);
        this.weaponSlot = (Numbers<Double>)new Numbers("WeaponSlot", (Number)3.0, (Number)1.0, (Number)8.0, (Number)1.0);
        this.routeHelper = (Option<Boolean>)new Option("RouteHelper", (Object)true);
        this.protect = (Option<Boolean>)new Option("MacroProtect", (Object)true);
        this.etherWarped = false;
        this.curIndex = 0;
        this.started = false;
        this.lastSlot = 0;
        this.restore = false;
        this.shouldSwitchToAbi = false;
        this.shouldCallSB = false;
        this.shouldClickFuel = false;
        this.shouldClickDrill = false;
        this.shouldCombind = false;
        this.drillSlot = 0;
        this.fuelSlot = 0;
        this.yogs = new ArrayList<EntityMagmaCube>();
        this.attackTimer = new TimerUtil();
        this.killingYogs = false;
        this.ticks = 0;
        this.fallSafeTimer = new TimerUtil();
        this.addValues(new Value[] { (Value)this.mode, (Value)this.routeHelper, (Value)this.protect, (Value)this.killYogs, (Value)this.yogRange, (Value)this.weaponSlot, (Value)this.rcKill, (Value)this.aim, (Value)this.faceDown });
        this.setModInfo("Auto Mine Gemstone.");
    }
    
    @SubscribeEvent(receiveCanceled = true)
    public void onChat(final ClientChatReceivedEvent event) {
        final String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
        if (this.started && message.contains("is empty! Refuel it by talking to a Drill Mechanic!")) {
            NotificationPublisher.queue("AutoRuby Drill Empty.", "Trying To Refill Your Drill.", NotificationType.WARN, 10000);
            this.started = false;
            this.restore = true;
            this.shouldSwitchToAbi = true;
        }
    }
    
    @EventHandler
    private void onKillYog(final EventPreUpdate event) {
        if (this.killYogs.getValue()) {
            this.loadYogs();
        }
        else if (!this.yogs.isEmpty()) {
            this.yogs.clear();
        }
        if (!this.yogs.isEmpty()) {
            final EntityMagmaCube mcube = this.yogs.get(0);
            if (this.started) {
                NotificationPublisher.queue("AutoRuby", "Yog Detected, Trying to FUCK it.", NotificationType.WARN, 3000);
                this.started = false;
                this.killingYogs = true;
                this.attackTimer.reset();
            }
            if (mcube != null && mcube.isEntityAlive() && this.killingYogs) {
                this.mc.thePlayer.inventory.currentItem = ((Double)this.weaponSlot.getValue()).intValue() - 1;
                if (this.rcKill.getValue()) {
                    if (this.faceDown.getValue()) {
                        event.setPitch(90.0f);
                        if (this.attackTimer.hasReached(180.0)) {
                            Client.rightClick();
                            this.attackTimer.reset();
                        }
                    }
                    else {
                        if (this.aim.getValue()) {
                            final float[] r = RotationUtil.getPredictedRotations((EntityLivingBase)mcube);
                            event.setYaw(r[0]);
                            event.setPitch(r[1]);
                        }
                        if (this.attackTimer.hasReached(180.0)) {
                            Client.rightClick();
                            this.attackTimer.reset();
                        }
                    }
                }
                else {
                    final float[] r = RotationUtil.getPredictedRotations((EntityLivingBase)mcube);
                    event.setYaw(r[0]);
                    event.setPitch(r[1]);
                    if (this.attackTimer.hasReached(180.0)) {
                        this.mc.thePlayer.swingItem();
                        this.mc.getNetHandler().addToSendQueue((Packet)new C02PacketUseEntity((Entity)mcube, C02PacketUseEntity.Action.ATTACK));
                        this.attackTimer.reset();
                    }
                }
            }
        }
        else if (this.killingYogs) {
            NotificationPublisher.queue("AutoRuby", "OKAY, Continued Mining..", NotificationType.SUCCESS, 3000);
            this.started = true;
            this.killingYogs = false;
            this.attackTimer.reset();
        }
    }
    
    @EventHandler
    public void onDrawGuiBackground(final EventTick event) {
        if (this.ticks < 20) {
            ++this.ticks;
            return;
        }
        this.ticks = 0;
        if (this.shouldSwitchToAbi) {
            for (int i = 0; i < 9; ++i) {
                final ItemStack itemStack = this.mc.thePlayer.inventory.mainInventory[i];
                if (itemStack != null && itemStack.getItem() != null && ItemUtils.getSkyBlockID(itemStack).startsWith("ABIPHONE")) {
                    this.mc.thePlayer.inventory.currentItem = i;
                    break;
                }
                if (i == 8) {
                    NotificationPublisher.queue("AutoRuby Auto Refill.", "No Abiphone Found in Hotbar.", NotificationType.ERROR, 10000);
                }
            }
            Client.rightClick();
            this.shouldCallSB = true;
            this.shouldSwitchToAbi = false;
        }
        final GuiScreen gui = this.mc.currentScreen;
        if (Client.inSkyblock && gui instanceof GuiChest) {
            final Container container = ((GuiChest)gui).inventorySlots;
            if (container instanceof ContainerChest) {
                final String chestName = this.getGuiName(gui);
                if (chestName.startsWith("Abiphone") && this.shouldCallSB) {
                    final List<Slot> slots = (List<Slot>)container.inventorySlots;
                    for (final Slot slot : slots) {
                        final ItemStack is = slot.getStack();
                        if (is == null) {
                            continue;
                        }
                        if (is.hasDisplayName() && StringUtils.stripControlCodes(is.getDisplayName()).equals("Jotraeline Greatforge")) {
                            this.clickSlot(slot.slotNumber, 0, 0, 0);
                            this.shouldCallSB = false;
                            this.shouldCombind = false;
                            break;
                        }
                    }
                }
                if (chestName.equals("Drill Anvil")) {
                    for (int j = 0; j < 9; ++j) {
                        final ItemStack itemStack2 = this.mc.thePlayer.inventory.mainInventory[j];
                        if (itemStack2 != null && itemStack2.getItem() != null) {
                            if (ItemUtils.getSkyBlockID(itemStack2).contains("DRILL") && !container.inventorySlots.get(29).getHasStack()) {
                                final int slotNum = j + 1;
                                this.clickSlot(80 + slotNum, 0, 0, 1);
                                this.drillSlot = 80 + slotNum;
                                return;
                            }
                            if (ItemUtils.getSkyBlockID(itemStack2).contains("OIL_BARREL") && !container.inventorySlots.get(33).getHasStack() && container.inventorySlots.get(29).getHasStack()) {
                                final int slotNum = j + 1;
                                this.clickSlot(80 + slotNum, 0, 0, 1);
                                this.fuelSlot = 80 + slotNum;
                                return;
                            }
                        }
                    }
                    if (container.inventorySlots.get(29).getHasStack() && container.inventorySlots.get(33).getHasStack()) {
                        this.clickSlot(22, 0, 0, 0);
                        this.shouldCombind = true;
                    }
                    if (this.shouldCombind && !container.inventorySlots.get(29).getHasStack()) {
                        if (!this.shouldClickDrill && Block.getBlockFromItem(container.inventorySlots.get(13).getStack().getItem()) != Blocks.barrier) {
                            this.clickSlot(13, 0, 0, 0);
                            this.shouldClickDrill = true;
                            return;
                        }
                        if (!this.shouldClickFuel) {
                            this.clickSlot(this.drillSlot, 0, 0, 0);
                            this.clickSlot(33, 0, 0, 0);
                            this.clickSlot(this.fuelSlot, 0, 0, 0);
                            this.shouldClickFuel = true;
                            return;
                        }
                        this.mc.thePlayer.closeScreen();
                        this.reset();
                        if (this.restore) {
                            this.started = true;
                            this.restore = false;
                        }
                    }
                }
            }
        }
    }
    
    private void reset() {
        this.shouldSwitchToAbi = false;
        this.shouldCallSB = false;
        this.shouldClickFuel = false;
        this.shouldClickDrill = false;
        this.shouldCombind = false;
    }
    
    @EventHandler
    private void tickWayPoints(final EventTick e) {
        if (this.started) {
            KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindSneak.getKeyCode(), true);
        }
        if (this.started && (boolean)this.protect.getValue()) {
            boolean cancanneedmacro = false;
            String name = null;
            for (final EntityPlayer ep : this.mc.theWorld.playerEntities) {
                final AntiBot ab = (AntiBot)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)AntiBot.class);
                if (!ab.isInTablist(ep)) {
                    continue;
                }
                if (ep != this.mc.thePlayer && this.mc.thePlayer.canEntityBeSeen((Entity)ep)) {
                    cancanneedmacro = true;
                    name = ep.getName();
                    break;
                }
                cancanneedmacro = false;
            }
            if (cancanneedmacro) {
                this.started = false;
                WindowsNotification.show("AutoRuby Player Detected", "Player Name: " + name + ".");
                NotificationPublisher.queue("AutoRuby", "Player Detected, Auto Disabled.", NotificationType.WARN, 10000);
                Helper.sendMessage("Player Name: " + name + ".");
            }
        }
        if (Keyboard.isKeyDown(56) && this.mc.currentScreen == null && !this.started && this.mc.objectMouseOver != null && this.mc.objectMouseOver.getBlockPos() != null) {
            if (this.mc.theWorld.getBlockState(this.mc.objectMouseOver.getBlockPos()).getBlock() == Blocks.air) {
                return;
            }
            if (!this.wps.contains(this.mc.objectMouseOver.getBlockPos())) {
                this.wps.add(this.mc.objectMouseOver.getBlockPos());
            }
        }
    }
    
    @EventHandler
    private void idk(final EventTick event) {
        final GemstoneNuker gsn = (GemstoneNuker)Client.instance.getModuleManager().getModuleByClass(GemstoneNuker.class);
        if (this.started) {
            for (int i = 0; i < this.wps.size(); ++i) {
                final BlockPos pos2 = this.wps.get(i);
                final BlockPos standing = new BlockPos(this.mc.thePlayer.getPositionVector()).down();
                final BlockPos wp = new BlockPos(new Vec3i(pos2.getX(), pos2.getY(), pos2.getZ()));
                final BlockPos sp = new BlockPos(new Vec3i(standing.getX(), standing.getY(), standing.getZ()));
                if (sp.getX() == wp.getX() && sp.getY() == wp.getY() && sp.getZ() == wp.getZ()) {
                    this.curIndex = i;
                    this.curBP = sp;
                }
            }
            if (this.curBP == null) {
                return;
            }
            final BlockPos standing2 = new BlockPos(this.mc.thePlayer.getPositionVector()).down();
            final BlockPos cp = new BlockPos(new Vec3i(this.curBP.getX(), this.curBP.getY(), this.curBP.getZ()));
            final BlockPos sp2 = new BlockPos(new Vec3i(standing2.getX(), standing2.getY(), standing2.getZ()));
            if (cp.getX() != sp2.getX() || cp.getY() != sp2.getY() || cp.getZ() != sp2.getZ()) {
                this.nextBP = cp;
            }
            if (this.curBP != new BlockPos(this.mc.thePlayer.getPositionVector()).down() && this.fallSafeTimer.hasReached(5000.0)) {
                final ArrayList<BlockPos> waypoints = (ArrayList<BlockPos>)this.wps.clone();
                waypoints.sort(Comparator.comparingDouble(pos -> this.mc.thePlayer.getDistance((double)pos.getX(), (double)pos.getY(), (double)pos.getZ())));
            }
            this.fallSafeTimer.reset();
            if (this.curBP == null) {
                this.curBP = this.wps.get(this.curIndex);
                if (new BlockPos(this.mc.thePlayer.getPositionVector()).down() != this.curBP) {
                    Helper.sendMessage("[AutoRuby] Something went wrong, Please enter '.ar start' again.");
                    this.started = false;
                    return;
                }
            }
            if (!gsn.gemstones.isEmpty()) {
                this.timer.reset();
                this.ewTimer.reset();
                this.etherWarped = false;
                this.nextBP = null;
                this.mc.thePlayer.inventory.currentItem = 0;
            }
            else {
                if (this.nextBP == null) {
                    if (this.curIndex + 1 < this.wps.size()) {
                        this.nextBP = this.wps.get(this.curIndex + 1);
                    }
                    if (this.curIndex + 1 >= this.wps.size()) {
                        this.curIndex = -1;
                        this.nextBP = this.wps.get(this.curIndex + 1);
                    }
                    this.ewTimer.reset();
                    this.timer.reset();
                    this.etherWarped = false;
                }
                if (this.nextBP != null) {
                    final float[] r = gsn.getRotations(this.nextBP, gsn.getClosestEnum(this.nextBP));
                    if (this.timer.hasReached(200.0)) {
                        this.mc.thePlayer.rotationYaw = r[0];
                        this.mc.thePlayer.rotationPitch = r[1];
                        if (this.timer.hasReached(550.0)) {
                            if (!this.timer.hasReached(580.0)) {
                                this.ewTimer.reset();
                            }
                            if (!this.etherWarped && this.nextBP != null) {
                                this.etherWarp(this.nextBP);
                            }
                            if (gsn.isEnabled()) {
                                gsn.setEnabled(false);
                            }
                            if (this.timer.hasReached(1550.0)) {
                                ++this.curIndex;
                                this.curBP = this.nextBP;
                                gsn.setEnabled(true);
                                this.nextBP = null;
                                this.timer.reset();
                            }
                        }
                    }
                }
            }
        }
    }
    
    @EventHandler
    private void R3D(final EventRender3D event) {
        for (int i = 0; i < this.wps.size(); ++i) {
            final BlockPos pos = this.wps.get(i);
            BlockPos npos = null;
            if (i + 1 == this.wps.size()) {
                npos = this.wps.get(0);
            }
            else {
                npos = this.wps.get(i + 1);
            }
            if (pos == this.nextBP) {
                RenderUtil.drawSolidBlockESP(pos, ColorUtils.addAlpha(FadeUtil.PURPLE.getColor(), 190).getRGB(), 2.0f, event.getPartialTicks());
            }
            else if (pos == this.curBP) {
                RenderUtil.drawSolidBlockESP(pos, ColorUtils.addAlpha(FadeUtil.GREEN.getColor(), 190).getRGB(), 2.0f, event.getPartialTicks());
            }
            else {
                RenderUtil.drawSolidBlockESP(pos, ColorUtils.addAlpha(FadeUtil.BLUE.getColor(), 190).getRGB(), 2.0f, event.getPartialTicks());
            }
            this.renderTag(pos, EnumChatFormatting.LIGHT_PURPLE + "#" + (i + 1) + EnumChatFormatting.WHITE + ": " + pos.getX() + " " + pos.getY() + " " + pos.getZ());
            if (this.routeHelper.getValue()) {
                final double nposX = npos.getX() - this.mc.getRenderManager().viewerPosX + 0.5;
                final double nposY = npos.getY() - this.mc.getRenderManager().viewerPosY + 0.5;
                final double nposZ = npos.getZ() - this.mc.getRenderManager().viewerPosZ + 0.5;
                final double posX = pos.getX() - this.mc.getRenderManager().viewerPosX + 0.5;
                final double posY = pos.getY() - this.mc.getRenderManager().viewerPosY + 1.0 + this.mc.thePlayer.getEyeHeight();
                final double posZ = pos.getZ() - this.mc.getRenderManager().viewerPosZ + 0.5;
                final double dposX = pos.getX() - this.mc.getRenderManager().viewerPosX + 0.5;
                final double dposY = pos.getY() - this.mc.getRenderManager().viewerPosY + 0.5;
                final double dposZ = pos.getZ() - this.mc.getRenderManager().viewerPosZ + 0.5;
                RenderUtil.startDrawing();
                GL11.glEnable(2848);
                RenderUtil.setColor(Colors.MAGENTA.c);
                GL11.glLineWidth(3.0f);
                GL11.glBegin(1);
                GL11.glVertex3d(nposX, nposY, nposZ);
                GL11.glVertex3d(posX, posY, posZ);
                GL11.glEnd();
                RenderUtil.setColor(FadeUtil.BLUE.getColor().getRGB());
                GL11.glLineWidth(3.0f);
                GL11.glBegin(1);
                GL11.glVertex3d(posX, posY, posZ);
                GL11.glVertex3d(dposX, dposY, dposZ);
                GL11.glEnd();
                GL11.glDisable(2848);
                RenderUtil.stopDrawing();
            }
        }
    }
    
    public void onEnable() {
        final GemstoneNuker gsn = (GemstoneNuker)Client.instance.getModuleManager().getModuleByClass(GemstoneNuker.class);
        gsn.mode.setValue(this.mode.getValue());
        gsn.protect.setValue((Object)false);
        if (!gsn.isEnabled()) {
            gsn.setEnabled(true);
        }
        this.timer.reset();
        this.ewTimer.reset();
        this.etherWarped = false;
        Helper.sendMessage("[AutoRuby] Press ALT to add waypoints.");
        Helper.sendMessage("[AutoRuby] Standing on the first waypoint and Use '.autoruby start' to start mining.");
        super.onEnable();
    }
    
    public void onDisable() {
        final GemstoneNuker gsn = (GemstoneNuker)Client.instance.getModuleManager().getModuleByClass(GemstoneNuker.class);
        if (gsn.isEnabled()) {
            gsn.setEnabled(false);
        }
        KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindSneak.getKeyCode(), false);
        Helper.sendMessage("Remember to save the waypoints~");
        this.timer.reset();
        this.ewTimer.reset();
        this.etherWarped = false;
        this.curBP = null;
        this.nextBP = null;
        this.curIndex = 0;
        super.onDisable();
    }
    
    @SubscribeEvent
    public void clear(final WorldEvent.Load event) {
        Helper.sendMessage("[MacroProtection] Auto Disabled " + EnumChatFormatting.GREEN + this.getName() + EnumChatFormatting.GRAY + " due to World Change.");
        this.setEnabled(false);
    }
    
    private void etherWarp(final BlockPos pos) {
        if (this.etherWarped) {
            return;
        }
        if ((this.mc.thePlayer.getHeldItem() != null && !ItemUtils.getSkyBlockID(this.mc.thePlayer.getHeldItem()).equals("ASPECT_OF_THE_VOID")) || this.mc.thePlayer.getHeldItem() == null) {
            this.lastSlot = this.mc.thePlayer.inventory.currentItem;
            for (int i = 0; i < 9; ++i) {
                final ItemStack itemStack = this.mc.thePlayer.inventory.mainInventory[i];
                if (itemStack != null && itemStack.getItem() != null && ItemUtils.getSkyBlockID(itemStack).equals("ASPECT_OF_THE_VOID")) {
                    this.mc.thePlayer.inventory.currentItem = i;
                    break;
                }
            }
        }
        if (this.ewTimer.hasReached(750.0)) {
            Client.rightClick();
            this.ewTimer.reset();
            this.etherWarped = true;
            this.mc.thePlayer.inventory.currentItem = 0;
        }
    }
    
    private void loadYogs() {
        this.yogs.clear();
        for (final Entity entity : this.mc.theWorld.loadedEntityList) {
            if (!entity.isDead) {
                if (!entity.isEntityAlive()) {
                    continue;
                }
                if (!(entity instanceof EntityMagmaCube) || this.mc.thePlayer.getDistanceToEntity(entity) >= (double)this.yogRange.getValue()) {
                    continue;
                }
                this.yogs.add((EntityMagmaCube)entity);
            }
        }
        this.yogs.sort(Comparator.comparingDouble(sb -> this.mc.thePlayer.getDistanceToEntity(sb)));
    }
    
    private void renderTag(final BlockPos bp, final String str) {
        float size = (float)(this.mc.thePlayer.getDistance((double)bp.getX(), (double)bp.getY(), (double)bp.getZ()) / 10.0);
        if (size < 1.1f) {
            size = 1.1f;
        }
        float scale = size * 1.8f;
        scale /= 100.0f;
        final double pX = bp.getX() - this.mc.getRenderManager().viewerPosX + 0.5;
        final double pY = bp.getY() - this.mc.getRenderManager().viewerPosY + 0.3;
        final double pZ = bp.getZ() - this.mc.getRenderManager().viewerPosZ + 0.5;
        GL11.glPushMatrix();
        GlStateManager.resetColor();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glTranslated(pX, pY, pZ);
        GL11.glRotatef(-this.mc.getRenderManager().playerViewY, 0.0f, 2.0f, 0.0f);
        GL11.glRotatef(this.mc.getRenderManager().playerViewX, 2.0f, 0.0f, 0.0f);
        GL11.glScalef(-scale, -scale, scale);
        GLUtil.setGLCap(2929, false);
        GLUtil.setGLCap(3042, true);
        final float nw = -this.mc.fontRendererObj.getStringWidth(str) / 2 - 4.6f;
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.fontRendererObj.drawString(str, (int)nw + 4, -13, -1);
        GLUtil.revertAllCaps();
        GL11.glPopMatrix();
        GlStateManager.resetColor();
    }
    
    private void clickSlot(final int slot, final int incrementWindowId, final int button, final int mode) {
        this.mc.playerController.windowClick(this.mc.thePlayer.openContainer.windowId + incrementWindowId, slot, button, mode, (EntityPlayer)this.mc.thePlayer);
    }
    
    public String getGuiName(final GuiScreen gui) {
        if (gui instanceof GuiChest) {
            return ((ContainerChest)((GuiChest)gui).inventorySlots).getLowerChestInventory().getDisplayName().getUnformattedText();
        }
        return "";
    }
}
